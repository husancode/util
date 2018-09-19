import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/30
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class Debug2 {

    private static long count;

    @OnMethod(
            clazz = "btrace.BtraceCase",
            method = "add",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER)
    )
    public static void trace3(@ProbeClassName String pcm, @ProbeMethodName String pmn,
                              @TargetInstance Object instance, @TargetMethodOrField String method, @Duration long duration) {
        BTraceUtils.println("TargetInstance:" + BTraceUtils.str(instance)+",TargetMethodOrField:"+BTraceUtils.str(method)+",duration:"+duration/1000000);
    }


   /* @OnTimer(4000)
    public static void traceMemory() {
        BTraceUtils.println("heap:");
        BTraceUtils.println(BTraceUtils.heapUsage());
        BTraceUtils.println("no-heap:");
        BTraceUtils.println(BTraceUtils.nonHeapUsage());
    }*/


    @OnTimer(4000)
    public static void traceDeadLock(){
        BTraceUtils.deadlocks();
    }


}
