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
public class BtraceSendMessage {

    @OnMethod(clazz="com.ys.product.ysmq.server.sdk.MessageProducer",
            method = "sendMsg",
            location =@Location(Kind.ENTRY))
    public static void run(@Self Object self, Object message){
        BTraceUtils.println("send message: "+ BTraceUtils.str(message));
    }

}
