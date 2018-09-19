import com.alibaba.fastjson.JSON;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace(unsafe = true)
public class BTraceMethodNoSecuity {

    @OnMethod(
            clazz = "com.web.api.MessageController",
            method = "getMessageUnreadCount",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER)
    )
    public static void traceMethodCallingMethod(@ProbeClassName String pcm, @ProbeMethodName String pmn,
                                                @TargetInstance Object instance,
                                                @TargetMethodOrField String method,
                                                @Duration long duration) {


        BTraceUtils.println("TargetInstance:" + BTraceUtils.str(instance) +
                ",TargetMethodOrField:" + BTraceUtils.str(method) + ",duration:" + duration / 1000000);

    }

    @OnMethod(
            clazz = "com.web.api.MessageController",
            method = "getMessageUnreadCount",
            location =@Location(Kind.RETURN)
    )
    public static void traceMethodArgs(@Self Object self, Object authParam,
                                       @Return Object result, @Duration long time){
        BTraceUtils.println("--------------------------");
        BTraceUtils.println("authParam:"+ JSON.toJSONString(authParam));
        BTraceUtils.println("result:"+JSON.toJSONString(result));
        //costTime失效，被覆盖为0了
        //BTraceUtils.println("cost time:" + time/1000000);
        BTraceUtils.println("==========================");
    }

}
