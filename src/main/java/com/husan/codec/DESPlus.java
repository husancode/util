package com.husan.codec;

import com.husan.lang.ByteUtil;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * <p>
 * </p>
 * 
 */
public final class DESPlus {
    private static String strDefaultKey = "national";

    public final static String DES = "DES";

    public final static String DES_ECB_NOPADDING = "DES/ECB/NoPadding";

    // public final static String DES_CBC_PKCS5Padding = "DES/CBC/PKCS5Padding";

    private Cipher encryptCipher = null;

    private Cipher decryptCipher = null;

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public DESPlus() throws Exception {
        this(strDefaultKey, DES);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESPlus(String strKey) throws Exception {
        this(strKey, DES);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESPlus(String strKey, String algorithm) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(strKey.getBytes());

        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(desKey);

        // Cipher对象实际完成加密操作
        encryptCipher = Cipher.getInstance(algorithm);

        // 用密匙初始化Cipher对象
        encryptCipher.init(Cipher.ENCRYPT_MODE, securekey, random);

        decryptCipher = Cipher.getInstance(algorithm);
        decryptCipher.init(Cipher.DECRYPT_MODE, securekey, random);
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        byte[] encrypt = encrypt(strIn.getBytes());
        return ByteUtil.toHexString(encrypt);
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        byte[] byteArray = ByteUtil.toByteArray(strIn);
        byte[] decrypt = decrypt(byteArray);
        return new String(decrypt);
    }

    /**
     * <p>
     * DES加密后再Base64加密
     * </p>
     * 
     * @author hanlifeng 2013-5-28 下午3:15:03
     * @param src
     * @return
     * @throws Exception
     */
    public String encrytWithBase64(String src) throws Exception {
        byte[] des = encrypt(src.getBytes());
        return Base64Util.encryptBASE64(des);
    }

    /**
     * <p>
     * Base64解密后在DES解密
     * </p>
     * @param src
     * @return
     * @throws Exception
     */
    public String decryptWithBase64(String src) throws Exception {
        byte[] des = Base64Util.decryptBASE64(src);
        byte[] decrypt = decrypt(des);
        return new String(decrypt);
    }

}
