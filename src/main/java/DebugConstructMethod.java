import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/31
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class DebugConstructMethod {

    private static AtomicInteger count = BTraceUtils.newAtomicInteger(0);

    @OnMethod(clazz="java.net.Socket", method = "<init>")
    public static void traceConstructMethod(){
        BTraceUtils.println("-------------------");
        BTraceUtils.println(BTraceUtils.Threads.name(BTraceUtils.currentThread()));
        BTraceUtils.jstack();
        BTraceUtils.incrementAndGet(count);
        BTraceUtils.println("===================");
    }

    @OnTimer(10000)
    public static void printCount(){
        BTraceUtils.println("-------------------");
        BTraceUtils.println("socket create count:" + BTraceUtils.str(count));
        count = BTraceUtils.newAtomicInteger(0);
        BTraceUtils.println("===================");
    }
}
