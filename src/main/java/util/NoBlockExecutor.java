package util;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.List;

/**
 * <p>
 *     如果线程池满额运行，不阻塞等待其他任务执行，
 *     直接切换到当前线程执行，适用于需要实时处理的批量任务，有效利用空余线程执行。
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class NoBlockExecutor {

    private static final Log log = LogFactory.getLog(NoBlockExecutor.class);

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(20,20, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static <V> List<V> invokeAll(Collection<Callable<V>> tasks){
        List<Future<V>> futureList = new ArrayList();
        List<V> result = new ArrayList();
        try {
            for(Callable<V> task : tasks){
                try {
                    futureList.add(executor.submit(task));
                } catch (RejectedExecutionException e) {
                    V ret = task.call();
                    result.add(ret);
                }
            }
            for(Future<V> future : futureList){
                result.add(future.get());
            }
        } catch (Exception e) {
            log.error("执行任务报错：" + e);
        }
        return result;

    }

}