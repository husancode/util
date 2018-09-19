package com.husan.execute.buffer;

import java.util.List;

/**
 * the interface of batch executor
 * 
 */
public interface IBatchExecutor<T> {
	
	void execute(List<T> records);
	
}
