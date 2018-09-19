
package text;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public final class UUID {

    public static String newUUID(){
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String newUUID(int len){
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

    private UUID(){}
}
