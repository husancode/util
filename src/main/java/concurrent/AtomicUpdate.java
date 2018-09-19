package concurrent;

import sun.misc.Unsafe;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/4/12
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class AtomicUpdate {

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicUpdate.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;

}
