import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

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
public class BtraceClassInit {

    @OnMethod(clazz="btrace.Hello", method = "<init>")
    public static void traceConstructMethod(){
        BTraceUtils.println("-------------------");
        BTraceUtils.jstack();
        BTraceUtils.println("===================");
    }
}
