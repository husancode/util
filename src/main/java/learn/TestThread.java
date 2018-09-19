
package learn;

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
public class TestThread {
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().interrupt();
                    System.out.println("innndfs:" + Thread.interrupted());
                    Thread.sleep(10000);
                    System.out.println("111111");
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException:"+Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("1:" + Thread.interrupted());
       // t.interrupt();
        System.out.println("end");
        System.out.println(t.isInterrupted());

    }
}
