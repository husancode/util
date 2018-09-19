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
public class DebugCallTime {

    @OnMethod(
            clazz = "com.web.api.MessageController",
            method = "getMessageUnreadCount",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER)
    )
    public static void traceMethodCallingMethod(@ProbeClassName String pcm, @ProbeMethodName String pmn,
                              @TargetInstance Object instance, @TargetMethodOrField String method, @Duration long duration) {

        //if(duration > 2000000){
        BTraceUtils.println("TargetInstance:" + BTraceUtils.str(instance) + ",TargetMethodOrField:" + BTraceUtils.str(method) + ",duration:" + duration / 1000000);
        //}
    }

}
