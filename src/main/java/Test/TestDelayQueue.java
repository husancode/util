package Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     多个线程同时take()数据，只有1个lead线程会自动设置超时唤醒。
 *     其他线程一直处于等待状态，当take成功（并且不是最后一个）或offer添加数据后会随机唤醒一个。
 *     5个线程，5个take()阻塞，取完后最终还有4个take阻塞住，因为其中一个取完最后1个元素后不会唤醒其他线程。
 *     finally {L
 if (leader == null && q.peek() != null)
 available.signal();
 lock.unlock();
 }
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/25
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TestDelayQueue {

    public static void main(String[] args) {
        final DelayQueue<Job> delayQueue = new DelayQueue();
        long start = System.currentTimeMillis();
        for(int i=1; i<=100; i++){
            Job job = new Job(start+ 1000*i);
            delayQueue.offer(job);
        }
        for(int i=0; i<5; i++){
            new Thread(){
                @Override
                public void run() {
                    while( delayQueue.peek() != null){
                        Job job = null;
                        try {
                            job = delayQueue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+":"+job.getEffectTime());
                    }
                }
            }.start();
        }

    }


    static class Job implements Delayed{

        private long effectTime;

        public Job(long effectTime){
            this.effectTime = effectTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert( effectTime-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        public long getEffectTime(){
            return this.effectTime;
        }
    }
}
