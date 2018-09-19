
package Test;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;
import java.security.SecureRandom;

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
public class Test {
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        for( int i=0; i< 1000000;i++){
            random.nextBytes(bytes);
            System.out.println(binary(bytes, 8));
        }

        System.exit(0);
    }
}
