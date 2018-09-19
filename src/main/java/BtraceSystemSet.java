import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/7/17
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace(unsafe = true)
public class BtraceSystemSet {

    @OnMethod(
            clazz="java.lang.System",
            method="setProperty",
            location=@Location(Kind.ENTRY)
    )
    public static void sysProp(@Self Object self, String key, String value) {
        if("message.cloud.subscription.subject.intelbras".equals(key)){
            BTraceUtils.println(key+":"+value);
        }
        BTraceUtils.Threads.jstack();
        println("==========================");
    }

    @OnMethod(
            clazz="java.lang.System",
            method="setProperties",
            location=@Location(Kind.ENTRY)
    )
    public static void sysProps(@Self Object self, Object properties){
        BTraceUtils.println("properties:"+properties);
        BTraceUtils.Threads.jstack();
        println("==========================");
    }
}
