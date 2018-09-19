package com.husan.codec;

import com.husan.lang.ByteUtil;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p>
 * JCE常用算法速度：MD5 > SHA-1 > SHA-256 > MD2
 * </p>
 */
public final class MD5Util {

    /**
     * 创建一个新的实例MD5Util.
     */
    private MD5Util() {
    }

    /**
     * 生成字符串的md5校验值
     * 
     * @param s
     * @return
     */
    public static String calcMD5String(String s) throws NoSuchAlgorithmException {
        return new MD5Plus().calcMD5String(s);
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
    public static boolean checkPassword(String password, String md5PwdStr) throws NoSuchAlgorithmException {
        return new MD5Plus().checkPassword(password, md5PwdStr);
    }

    /**
     * 生成文件的md5校验值
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
        return new MD5Plus().getFileMD5String(file);
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
    public static byte[] calcMD5(byte[] bytes) throws NoSuchAlgorithmException {
        return new MD5Plus().calcMD5(bytes);
    }

    /**
     * <p>
     * 计算32位MD5
     * </p>
     * 
     * @param data
     * @return
     */
    public static String md5(String data) {
        byte[] digest = digest("MD5", data.getBytes());
        return ByteUtil.toHexString(digest);
    }

    /**
     * <p>
     * 计算32位MD5
     * </p>
     * 
     * @param data
     * @return
     */
    public static String md5(byte[] data) {
        byte[] digest = digest("MD5", data);
        return ByteUtil.toHexString(digest);
    }

    public static String sha1(String data) {
        byte[] digest = digest("SHA1", data.getBytes());
        return ByteUtil.toHexString(digest);
    }

    public static String md2(String data) {
        try {
            byte[] ret = digest("MD2", data.getBytes());
            return ByteUtil.toHexString(ret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final static byte[] digest(String algorithm, byte[] data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return digest.digest(data);
    }

}
