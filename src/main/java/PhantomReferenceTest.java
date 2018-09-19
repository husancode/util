import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/6
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class PhantomReferenceTest {

    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        Cleaner cleaner = Cleaner.create(abc, new Runnable() {
            @Override
            public void run() {

                System.out.println("clear abc:");

            }
        });
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
    }
}
