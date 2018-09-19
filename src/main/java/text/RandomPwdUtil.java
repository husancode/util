package text;

import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base32;
import security.MD5Util;

/**
 * <p></p>
 * @author hukaji 2015-6-29 上午09:04:09
 */
public class RandomPwdUtil {
    
    private static final String MD5_SALT = "source";

    /**
     * 随机生成16位密码
     */
    public static String randomPwd(){
        SecureRandom ng = Holder.numberGenerator;
        Base32 base = Holder.base32;
        byte[] salt = new byte[10];
        ng.nextBytes(salt);    
        return base.encodeAsString(salt);
    }

    //随机生成16位数字密码
    //HexFillWIthNum，对应4bit，不一一对应有重复几率
    public static String randomPwdWithOnlyNumber(){
        SecureRandom ng = Holder.numberGenerator;
        byte[] salt = new byte[8];
        ng.nextBytes(salt);
        return HexFillWithNum.encodeHexString(salt);
    }
    
    public static String md5Encrypt(String source){
        return MD5Util.md5(MD5_SALT+source);
    }
    
    public static void main(String[] args) {
        String pwd = "7876543210654331";
        //System.out.println(md5Encrypt(pwd));
    }
    
    //延迟加载
    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
        static final Base32 base32 = new Base32();
    }

}
