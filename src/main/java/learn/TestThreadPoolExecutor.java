
package learn;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TestThreadPoolExecutor {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3, 6, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));


    public static void test(int num){
        for(int i=0; i<num; i++){
            executor.execute(new Job());
        }
        executor.shutdown();
        System.out.println("1:" + executor.getPoolSize());

    }
    public static void main(String[] args) throws InterruptedException{
        try {
            test(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    static class Job implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                Random random = new Random();
                if(random.nextInt(3)%2 ==0){
                    throw  new IllegalArgumentException("ddd");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+executor.getPoolSize());
        }
    }
}
