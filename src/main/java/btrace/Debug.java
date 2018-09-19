package btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/15
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
@BTrace
public class Debug {
    @Export static long counter;
    @OnMethod(clazz="btrace.BtraceCase", method = "add", location =@Location(Kind.RETURN))
    public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time){
        BTraceUtils.println("parameter:a" + a + ",b=" + b +",result:" + result +",object:"+ self);
        BTraceUtils.println("cost time:" + time);
        counter++;
    }

    @OnTimer(1000)
    public static void run(){
        BTraceUtils.println("execute count:" + counter);
    }
}
