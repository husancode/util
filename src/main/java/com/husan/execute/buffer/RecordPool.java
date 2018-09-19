package com.husan.execute.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author joe.zhao
 */
public class RecordPool<T> {
	
	private final static Logger		LOG					= LoggerFactory
																.getLogger(RecordPool.class);
	private final static int		DEFAULT_BATCH_SIZE	= 500;
	
	private final BlockingQueue<T>	queue;
	private final int				batchSize;
	
	public RecordPool(int poolSize, int batchSize) {
		queue = new LinkedBlockingQueue<T>(poolSize);
		this.batchSize = batchSize < 0 ? DEFAULT_BATCH_SIZE : batchSize;
	}
	
	public final boolean add(final T record) {
		boolean ret = false;
		if (null != record) {
			ret = queue.offer(record);
			if (!ret) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("add the record to the queue failed, the queue may be full.");
				}
			}
		}
		return ret;
	}
	
	public final List<T> asList() {
		List<T> recordsCopy = new ArrayList<T>();
		try {
			if (0 < queue.size()) {
				synchronized (queue) {
					int num = queue.size() >= batchSize ? batchSize : queue
							.size();
					queue.drainTo(recordsCopy, num);
				}
			}
		} catch (Exception ex) {
			LOG.error(">>> Execute Get Queue Exception:", ex);
		}
		return recordsCopy;
	}
	
	public List<T> getWholeRecords() {
		List<T> recordsCopy = new ArrayList<T>();
		synchronized (queue) {
			int num = queue.size();
			if (0 != num) {
				queue.drainTo(recordsCopy, num);
			}
		}
		return recordsCopy;
	}
	
	public int remainCapacity() {
		return this.queue.remainingCapacity();
	}
	
	public int size() {
		return this.queue.size();
	}
	
}
