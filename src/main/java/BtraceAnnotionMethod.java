import com.alibaba.fastjson.JSON;
import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/1
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */

@BTrace(unsafe = true)
public class BtraceAnnotionMethod {

   @OnMethod(clazz="@org.springframework.stereotype.Controller",
            method = "@org.springframework.web.bind.annotation.RequestMapping",
            location = @Location(Kind.ENTRY)
    )
    public static void trackAnnotionMethodArgs(@ProbeClassName String className,
                                           @ProbeMethodName String methodName,
                                           AnyType[] args){
        BTraceUtils.print(className + ":" + methodName + ",args:");
        BTraceUtils.print(JSON.toJSONString(args));
        BTraceUtils.println();
    }

   @OnMethod(clazz="@org.springframework.stereotype.Controller",
            method = "@org.springframework.web.bind.annotation.RequestMapping",
            location = @Location(Kind.RETURN)
    )
    public static void trackAnnotionMethodResult(@ProbeClassName String className,
                                                 @ProbeMethodName String methodName,
                                                 @Return AnyType result,
                                                 @Duration long duration){
        BTraceUtils.print(className + ":" + methodName + ",cost time:"+duration/1000000);
        BTraceUtils.print(",result:"+result);
        BTraceUtils.println();

    }
}
