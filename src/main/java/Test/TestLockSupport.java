package Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *     LockSupport.park(this) 将当前线程阻塞，此时cpu不会分配时间片给此线程。线程状态在jvisualvm中为驻留状态。
 当线程被中断、unpark或不知名原因会立即返回，应放在条件判断的循环中执行。
 注意： 当使用Thread.interrupt()中断线程时，线程中断标识设置，   LockSupport.park(this)将一直返回，等同于失去阻塞作用。
 并且  LockSupport.park(this)不会抛出中断异常或清除 线程中断标识。
 可以使用 Thread.interrupted()清除标识位。
 while(conditionCheck()){
 LockSupport.park(this);
 }
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/24
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TestLockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Job(), "name1");
        Thread t2 = new Thread(new Job1(), "name2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("dsf");
        t1.interrupt();
        t2.interrupt();
    }

    static class Job implements Runnable{
        @Override
        public void run() {
            while(true){
                System.out.println("before park:"+Thread.currentThread().getName());
                LockSupport.park(this);
                System.out.println("after park:"+ Thread.currentThread().getName());
            }
        }
    }

    static class Job1 implements Runnable{
        @Override
        public void run() {
            while(true){
                System.out.println("before park:" + Thread.currentThread().getName());
                LockSupport.park(this);
                System.out.println("after park:"+ Thread.currentThread().getName());
                if(Thread.interrupted()){
                    System.out.println("interrputed:"+Thread.currentThread().getName());
                }
            }
        }
    }
}
