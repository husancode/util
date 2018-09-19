package btrace;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/1
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class NaoTime {
    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        long end = System.nanoTime();
        long cost = end - start;
        System.out.println(cost/1000000);

    }
}
