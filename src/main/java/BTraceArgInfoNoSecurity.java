import com.alibaba.fastjson.JSON;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

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
@BTrace(unsafe = true)
public class BTraceArgInfoNoSecurity {
    @OnMethod(
            clazz = "com.test.web.api.MessageController",
            method = "getMessageUnreadCount",
            location =@Location(Kind.RETURN)
    )
    public static void traceMethodArgs(@Self Object self, Object authParam,
                                       @Return Object result, @Duration long time){
        BTraceUtils.println("--------------------------");
        BTraceUtils.println("authParam:"+ JSON.toJSONString(authParam));
        BTraceUtils.println("result:"+JSON.toJSONString(result));
        BTraceUtils.println("cost time:" + time/1000000);
        BTraceUtils.println("==========================");
    }
}
