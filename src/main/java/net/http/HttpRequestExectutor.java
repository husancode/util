package net.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/2/8
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class HttpRequestExectutor {

    private static Log log = LogFactory.getLog(HttpRequestExectutor.class);

    private static ExecutorService executor;

    public static ExecutorService getExecutor(){

        executor =  new ThreadPoolExecutor(HttpCompareTest.threadNum, HttpCompareTest.threadNum, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(4000000), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // TODO Auto-generated method stub
                log.info("rejected:"+r);
            }
        });
        return executor;
    }

}
