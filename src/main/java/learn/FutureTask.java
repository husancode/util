package learn;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/23
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class FutureTask implements RunnableFuture<String>{

    private String result;
    private volatile boolean isDone;
    private final ReentrantLock mainLock = new ReentrantLock();
    private final Condition doneCondition = mainLock.newCondition();
    @Override
    public void run() {
        try{
            try{
                String ret = caculate();
                result = ret;
            }finally {
                mainLock.lock();
                isDone = true;
                doneCondition.signalAll();
                mainLock.unlock();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {


        }
    }

    private String caculate() throws InterruptedException {
        Random random = new Random();
        int sleep = random.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return String.valueOf(sleep);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String get() throws InterruptedException, ExecutionException {
        mainLock.lock();
        try{
            while(!isDone){
                doneCondition.await();
            }

        }finally {
            mainLock.unlock();
        }
        return result;
    }

    @Override
    public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long nanos = unit.toNanos(timeout);
        mainLock.lock();
        try{
            while(!isDone){
                if(nanos <= 0){
                    return null;
                }
                nanos = doneCondition.awaitNanos(nanos);
            }

        }finally {
            mainLock.unlock();
        }
        return result;
    }
}
