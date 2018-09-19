package com.husan.codec;

import com.husan.lang.ByteUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 */
public final class MD5Plus {

    protected MessageDigest messagedigest = null;

    /**
     * 创建一个新的实例MD5Util.
     */
    public MD5Plus() throws NoSuchAlgorithmException {
        messagedigest = MessageDigest.getInstance("MD5");
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
     * @param bytes
     * @return
     */
    public byte[] calcMD5(byte[] bytes) {
        messagedigest.update(bytes);
        return messagedigest.digest();
    }

}
