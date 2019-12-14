package com.husan.codec;

import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;


/**
 * 单向散列：用于对某几个字段进行签名，
 * 各端采用相同的散列，编码方式进行比对
 */
public class SHAUtils {

    private static final int KEY_BYTE_LENGTH = 16;

    /**
     * sha256 单向散列算法
     * @param original
     * @return
     */
    public static byte[] sha256(String original){
        MessageDigest messageDigest;
        byte[] hash = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(original.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * length=16,取前16个字节转成hexString，刚好32位字符
     * @param original
     * @param length
     * @return
     */
    public static String toHexString(String original, int length){
        String hexString = "";
        byte[] hash = sha256(original);
        if (null != hash) {
            byte[] newHash = new byte[length];
            System.arraycopy(hash, 0, newHash, 0, length);
            hexString = Hex.encodeHexString(newHash);
        }
        return hexString;
    }


    public static void main(String[] args) {
        String orginal = "1234567";
        byte[] encr = sha256(orginal);
        System.out.println(encr.length);
        System.out.println(new String(encr));
        String ad = toHexString(orginal, 16);
        System.out.println(ad);
    }

}
