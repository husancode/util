package btrace;
import com.sun.btrace.annotations.OnTimer;

import java.util.Random;

/**
 */
public class BtraceCase {
    public static Random random = new Random();
    public int size;

    public static void main(String[] args) throws Exception {
        new BtraceCase().run();
    }
    
    public void run() throws Exception {
        while (true) {
            add(random.nextInt(10), random.nextInt(10));
        }
    }

    public final int add(int a, int b) throws Exception {
        long seconds = random.nextInt(10) * 100;
        Thread.sleep(seconds);
        int c= a + b;
        return c;
    }


}
