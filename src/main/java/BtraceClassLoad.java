import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/7
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class BtraceClassLoad {
    @OnMethod(
            clazz="+java.lang.ClassLoader",
            method="defineClass",
            location=@Location(Kind.RETURN)
    )
    public static void defineclass(@Return Class cl) {
        println("loaded " +  BTraceUtils.Reflective.name(cl));
        BTraceUtils.Threads.jstack();
        println("==========================");
    }


}
