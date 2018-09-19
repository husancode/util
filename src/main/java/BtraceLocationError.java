import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;
import com.sun.btrace.annotations.Self;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/19
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class BtraceLocationError {

    @OnMethod(clazz="btrace.ErrorDebug", method = "compare", location = @Location(Kind.ERROR))
    public static void printError(@Self Object object,String str1, String str2, @Duration long duration){

        BTraceUtils.println("str1:"+str1+",str2:"+str2);
        BTraceUtils.println("duration:" + duration);

    }

}
