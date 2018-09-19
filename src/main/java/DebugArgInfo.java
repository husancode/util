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
public class DebugArgInfo {

    @OnMethod(
            clazz = "com.web.api.UserController",
            method = "login",
            location =@Location(Kind.RETURN)
    )
    public static void traceMethodArgs(@Self Object self, Object authParam, Object loginReq, Object request, @Return Object result, @Duration long time){
        BTraceUtils.println("self:" + BTraceUtils.str(self));
        BTraceUtils.println("authParam:"+BTraceUtils.str(authParam));
        BTraceUtils.println("loginReq:"+BTraceUtils.str(loginReq));
        BTraceUtils.println("request:"+BTraceUtils.str(request));
        BTraceUtils.println("result:"+BTraceUtils.str(result));
        BTraceUtils.println("cost time:" + time/1000000);

    }

}
