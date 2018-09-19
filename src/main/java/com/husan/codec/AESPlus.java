package com.husan.codec;

import com.husan.lang.ByteUtil;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * <p>
 * </p>
 */
public class AESPlus {
    private static String strDefaultKey = "national";

    public final static String AES = "AES";

    public final static String AES_NOPADDING = "AES/ECB/NoPadding";

    public final static String AES_ECB_PKCS5Padding = "AES/ECB/PKCS5Padding";

    public final static String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";

    private Cipher encryptCipher = null;

    private Cipher decryptCipher = null;

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public AESPlus() throws Exception {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public AESPlus(String strKey) throws Exception {
        this(strKey, AES);
    }

    /**
     * 创建一个新的实例AESPlus.
     * 
     * @param strKey
     * @param algorithm
     * @throws Exception
     */
    public AESPlus(String strKey, String algorithm) throws Exception {
        this(strKey, algorithm, true);
    }

    /**
     * 指定密钥构造方法 创建一个新的实例AESPlus.
     * 
     * @param strKey
     * @param algorithm
     * @param secureRandom
     *            是否随机加密</br> <b><font color=red>（false
     *            情况下采用AES_ECB_PKCS5Padding模式可以和C++实现互解）</font></b>
     * @throws Exception
     */
    public AESPlus(String strKey, String algorithm, boolean secureRandom) throws Exception {
        byte[] rawKey = new byte[16];
        if (secureRandom) {
            rawKey = getRawKey(strKey.getBytes());
        } else {
            byte[] key = strKey.getBytes();
            int length = key.length >= 16 ? 16 : key.length;
            System.arraycopy(key, 0, rawKey, 0, length);
        }
        SecretKeySpec aesKey = new SecretKeySpec(rawKey, "AES");
        // Cipher对象实际完成加密操作
        encryptCipher = Cipher.getInstance(algorithm);
        // 用密匙初始化Cipher对象
        encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        decryptCipher = Cipher.getInstance(algorithm);
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey);
    }

    public AESPlus(String strKey, IvParameterSpec iv, String algorithm) throws Exception {

        byte[] bytes = strKey.getBytes();
        byte[] rawKey = new byte[16];
        System.arraycopy(bytes, 0, rawKey, 0, bytes.length);

        SecretKeySpec aesKey = new SecretKeySpec(rawKey, "AES");
        // Cipher对象实际完成加密操作
        encryptCipher = Cipher.getInstance(algorithm);

        // 用密匙初始化Cipher对象
        encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);

        decryptCipher = Cipher.getInstance(algorithm);
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
    }

    private final byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
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

    public String encrytWithBase64(String src) throws Exception {
        byte[] des = encrypt(src.getBytes());
        return Base64Util.encryptBASE64(des);
    }

    public String decryptWithBase64(String src) throws Exception {
        byte[] des = Base64Util.decryptBASE64(src);
        byte[] decrypt = decrypt(des);
        return new String(decrypt);
    }

}
