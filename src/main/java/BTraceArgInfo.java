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
@BTrace
public class BTraceArgInfo {

    @OnMethod(
            clazz = "com.test.sdk.rest.RestExecuter",
            method = "post",
            location =@Location(Kind.ENTRY)
    )
    public static void traceArraayList(@Self Object self, String url, String json, Object headers){
        BTraceUtils.println("--------------------------");
        BTraceUtils.println("url:" + url);
        BTraceUtils.println("json:" + json);
        BTraceUtils.println("==========================");
    }

}
