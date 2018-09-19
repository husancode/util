import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/17
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class BtraceFilter {

    @OnMethod(clazz = "+javax.servlet.Filter", method = "doFilter", location = @Location(Kind.RETURN))
    public static void onExecuterFilter(@ProbeClassName String className, @ProbeMethodName String methodName, @Duration long time){

        BTraceUtils.println(className+"."+methodName+":cost time:"+ time/1000000);

    }

}
