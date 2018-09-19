package com.husan.execute.buffer;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelayExecuteBuffer<T> {
	
	private final static Logger				LOG						= LoggerFactory
																			.getLogger(DelayExecuteBuffer.class);
	private AtomicReference<RecordPool<T>>	recordPool				= new AtomicReference<RecordPool<T>>();
	private final static long				DEFAULT_CHECK_INTERVAL	= 1000;
	private final static int				DEFAULT_POOLSIZE		= 1024;
	private final static int				DEFAULT_BATCHSIZE		= 100;
	private final static int				DEFAULT_THREADS			= 1;
	
	// buffer的消费线程组，但是消费者的生产线程组，一个单线程
	private ScheduledExecutorService		scheduler				= null;
	
	private TransactionStatisticer			statisticer;
	private final String					name;
	private final long						checkInterval;
	private final int						poolSize;
	private final int						batchSize;
	private final int						threads;
	private final IBatchExecutor<T>			batchExecutor;
	private ExceptionListener<T>			exceptionListener;
	
	public DelayExecuteBuffer(String name, long checkInterval, int poolSize,
			int batchSize, int threads, IBatchExecutor<T> batchExecutor) {
		this.name = StringUtils.isEmpty(name) ? "DelayExecuteBuffer" : name;
		this.checkInterval = checkInterval < 0 ? DEFAULT_CHECK_INTERVAL
				: checkInterval;
		this.poolSize = poolSize < 0 ? DEFAULT_POOLSIZE : poolSize;
		this.batchSize = batchSize < 0 ? DEFAULT_BATCHSIZE : batchSize;
		this.threads = threads < 0 ? DEFAULT_THREADS : threads;
		this.batchExecutor = batchExecutor;
	}
	
	public final synchronized void start() {
		// 实例化线程池
		if (1 == this.threads) {
			this.scheduler = Executors.newSingleThreadScheduledExecutor();
		} else {
			this.scheduler = Executors.newScheduledThreadPool(threads);
		}
		
		this.recordPool.set(new RecordPool<T>(poolSize, batchSize));
		
		for (int i = 0; i < threads; i++) {
			final int idx = i;
			scheduler.scheduleWithFixedDelay(new Runnable() {
				
				@Override
				public void run() {
					
					// set name for thread
					Thread.currentThread().setName(name + "-" + idx);
					
					if (LOG.isTraceEnabled()) {
						LOG.trace(
								"schedule: pop from record pool [{}]. poolSize=[{}], remainCapacity=[{}]",
								new Object[] { name, poolSize,
										getRecordPool().remainCapacity() });
					}
					
					final List<T> records = getRecordPool().asList();
					
					if (!records.isEmpty()) {
						if (LOG.isTraceEnabled()) {
							LOG.trace(
									"schedule: pop from record pool [{}]. poolSize=[{}], remainCapacity=[{}], size=[{}]",
									new Object[] { name, poolSize,
											getRecordPool().remainCapacity(),
											records.size() });
						}
						
						execute(records);
						
					}
				}
				
			}, checkInterval, checkInterval, TimeUnit.MILLISECONDS);
		}
	}
	
	public final synchronized void stop() {
		flush();
		if (null != scheduler) {
			scheduler.shutdown();
		}
	}
	
	// synchronized
	public final boolean add(final T record) {
		if (!full()) {
			boolean ret = getRecordPool().add(record);
			
			if (LOG.isTraceEnabled()) {
				LOG.trace(
						"add record to pool [{}]. poolSize=[{}], remainCapacity=[{}], record=[{}], ret=[{}]",
						new Object[] { name, poolSize,
								getRecordPool().remainCapacity(), record, ret });
			}
			return ret;
		}
		
		if (LOG.isWarnEnabled()) {
			LOG.warn("record pool [{}] is full size=[{}].", name,
					getRecordPool().size());
		}
		return false;
	}
	
	public void flush() {
		List<T> records = getRecordPool().getWholeRecords();
		execute(records);
	}
	
	private void execute(List<T> records) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Execute records. size=[{}]", records.size());
		}
		
		try {
			
			// / AOP start
			// statisticer the start
			if (statisticer != null) {
				statisticer.incHandledTransactionStart();
			}
			
			batchExecutor.execute(records);
			
			// / AOP end
			// statistic the end
			if (statisticer != null) {
				statisticer.incHandledTransactionEnd();
			}
			
		} catch (Exception e) {
			LOG.error("", e);
			if (null != exceptionListener) {
				LOG.error(
						"Execute records failed, calling exception listener... pool=[{}]",
						name);
				exceptionListener.onException(records);
			}
		}
	}
	
	/**
	 * @return the statisticer
	 */
	public TransactionStatisticer getStatisticer() {
		return statisticer;
	}
	
	public void setStatisticer(TransactionStatisticer statisticer) {
		this.statisticer = statisticer;
	}
	
	public boolean full() {
		return getRecordPool().remainCapacity() == 0;
	}
	
	public int size() {
		return getRecordPool().size();
	}
	
	public RecordPool<T> getRecordPool() {
		return this.recordPool.get();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the checkInterval
	 */
	public long getCheckInterval() {
		return checkInterval;
	}
	
	/**
	 * @return the poolSize
	 */
	public int getPoolSize() {
		return poolSize;
	}
	
	/**
	 * @return the batchSize
	 */
	public int getBatchSize() {
		return batchSize;
	}
	
	public void setExceptionListener(ExceptionListener<T> exceptionListener) {
		this.exceptionListener = exceptionListener;
	}
	
}
