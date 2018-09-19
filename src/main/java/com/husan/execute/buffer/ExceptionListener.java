package com.husan.execute.buffer;

import java.util.List;

/**
 */
public interface ExceptionListener<T> {
	
	void onException(List<T> records);
	
}
