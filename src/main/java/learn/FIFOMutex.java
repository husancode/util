package learn;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *     Here is a sketch of a first-in-first-out
 * non-reentrant lock class:
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/24
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */

public class FIFOMutex {

    private final AtomicBoolean locked = new AtomicBoolean(false);

    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();


    public void lock(){
        boolean wasInterrupted = false;
        Thread thread = Thread.currentThread();
        waiters.add(thread);
        while(thread != waiters.peek() || !locked.compareAndSet(false, true)){
            LockSupport.park(this);
            if(Thread.interrupted()){
                wasInterrupted = true;
            }
        }
        waiters.remove(thread);
        if(wasInterrupted){
            thread.interrupt();
        }

    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }


}
