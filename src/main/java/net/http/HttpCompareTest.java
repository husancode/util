package net.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
public class HttpCompareTest {

    private static final Log log = LogFactory.getLog(HttpCompareTest.class);

    public static int threadNum = Integer.parseInt(System.getProperty("thread.num", "40"));

    public static int requestNum = Integer.parseInt(System.getProperty("request.num", "1000"));

    public static void main(String[] args)  throws InterruptedException{
        PropertyConfigurator.configure(HttpCompareTest.class.getClassLoader()
                .getResource("log4j.properties"));
        ExecutorService executor = HttpRequestExectutor.getExecutor();
        testHttpClient(executor);
        executor = null;
        System.gc();
        TimeUnit.SECONDS.sleep(100);
        executor = HttpRequestExectutor.getExecutor();
        testHttpURLConnection(executor);
        executor = null;
        System.gc();
        TimeUnit.SECONDS.sleep(100);
        executor = HttpRequestExectutor.getExecutor();
        testHttpClient(executor);
        executor = null;
        System.gc();
        TimeUnit.SECONDS.sleep(100);
        executor = HttpRequestExectutor.getExecutor();
        testHttpURLConnection(executor);
    }

    public static void testHttpClient(ExecutorService executor) throws InterruptedException {
        long start = System.currentTimeMillis();
        for(int i=0; i< requestNum; i++){
            String param = String.valueOf(System.nanoTime());
            HttpClientRequestTask task = new HttpClientRequestTask(param);
            executor.execute(task);
        }
        executor.shutdown();

        while(!executor.awaitTermination(3000, TimeUnit.SECONDS)){

        }
        long end = System.currentTimeMillis();
        System.out.println("testHttpClient costTime:" + (end - start));
        log.info("costTime:"+(end-start));
    }

    public static void testHttpURLConnection(ExecutorService executor)  throws InterruptedException{
        System.setProperty("http.maxConnections", String.valueOf(threadNum));
        long start = System.currentTimeMillis();
        for(int i=0; i< requestNum; i++){
            String param = "param="+System.nanoTime();
            HttpURLConnectionRequestTask task = new HttpURLConnectionRequestTask(param);
            executor.execute(task);
        }
        executor.shutdown();

        while(!executor.awaitTermination(3000, TimeUnit.SECONDS)){

        }
        long end = System.currentTimeMillis();
        System.out.println("testHttpURLConnection costTime:" + (end - start));
        log.info("testHttpURLConnection costTime:"+(end-start));
    }
}
