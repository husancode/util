package security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import text.ByteUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p>
 * MD5校验工具类
 * </p>
 * 
 * @author DingLuofeng 2012-11-27 下午3:31:31
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2012-11-27
 * @modify by reason:{方法名}:{原因}
 */
public final class MD5Plus {

    private final Log logger = LogFactory.getLog(MD5Plus.class);

    protected MessageDigest messagedigest = null;

    /**
     * 创建一个新的实例MD5Util.
     */
    public MD5Plus() {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(MD5Plus.class.getName() + "初始化失败，MessageDigest不支持MD5。", e);
        }
    }

    /**
     * 生成字符串的md5校验值
     * 
     * @param s
     * @return
     */
    public String calcMD5String(String s) {
        byte[] calcMD5 = calcMD5(s.getBytes());
        return ByteUtil.toHexString(calcMD5);
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     * 
     * @param password
     *            要校验的字符串
     * @param md5PwdStr
     *            已知的md5校验码
     * @return
     */
    public boolean checkPassword(String password, String md5PwdStr) {
        String s = calcMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * 生成文件的md5校验值
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public String getFileMD5String(File file) throws IOException {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
        } finally {
            if (null != fis) {
                fis.close();
            }
        }
        return ByteUtil.toHexString(messagedigest.digest());
    }

    /**
     * <p>
     * 计算MD5值
     * </p>
     * 
     * @author DingLuofeng 2012-11-27 下午3:46:56
     * @param bytes
     * @return
     */
    public byte[] calcMD5(byte[] bytes) {
        messagedigest.update(bytes);
        return messagedigest.digest();
    }

    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        MD5Plus md5Util = new MD5Plus();
        // File file = new File("C:/12345.txt");
        String md5 = md5Util.calcMD5String("12345");

        System.out.println(md5);
        // String md5 = getMD5String("a");

        long end = System.currentTimeMillis();
        System.out.println("md5:" + md5 + " time:" + ((end - begin)) + "ms");

        System.out.println(ByteUtil.toHexString("hik$".getBytes()));
    }
}
