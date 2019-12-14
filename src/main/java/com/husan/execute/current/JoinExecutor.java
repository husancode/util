package com.husan.execute.current;

import com.husan.lang.ObjectUtil;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * @Auther: husan
 * @Date: 2019/12/12 17:15
 * @Description:
 */
public class JoinExecutor {

	private static ExecutorService defaultExecutor;


	/**
	 * 任务执行，归并
	 * @param executor：任务执行器
	 * @param task1：任务1
	 * @param task2： 任务2
	 * @param joinFuncton： 任务归并函数
	 * @param <R>
	 * @param <T>
	 * @param <H>
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static  <R, T, H> R execute(@Nullable  ExecutorService executor, Callable<T> task1, Callable<H> task2,
									   BiFunction<T,H,R> joinFuncton) throws ExecutionException, InterruptedException {
		if(executor == null){
			executor = getDefaultExecutor();
		}
		Future<T> result1 =  executor.submit(task1);
		Future<H> result2 = executor.submit(task2);
		return joinFuncton.apply(result1.get(), result2.get());
	}

	/**
	 * 执行任务，结果累加
	 * @param executor 可使用自己配置的execuor，如果为null,则使用默认的
	 * @param task1
	 * @param task2
	 * @param <T>
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static <T> T mergeExecute(@Nullable ExecutorService executor, Callable<T> task1, Callable<T> task2) throws ExecutionException, InterruptedException {
		BiFunction<T, T, T> mergeFunction = new BiFunction<T, T, T>() {
			@Override
			public T apply(T t, T h) {
				return ObjectUtil.addNumberField(t, h);
			}
		};
		return execute(executor, task1, task2, mergeFunction);
	}

	/**
	 * 默认执行器
	 * @return
	 */
	private static ExecutorService getDefaultExecutor(){
		if(defaultExecutor == null){
			synchronized (JoinExecutor.class){
				if(defaultExecutor == null){
					defaultExecutor = new ThreadPoolExecutor(4, 50,
							60, TimeUnit.SECONDS, new SynchronousQueue<>(),
							Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
				}
			}
		}
		return defaultExecutor;

	}

}
