package com.husan.codec;

import javax.crypto.spec.IvParameterSpec;

/**
 * <p>
 * </p>
 */
public class AESUtil {

    /**
     * 创建一个新的实例AESUtil.
     */
    private AESUtil() {
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encrypt(byte[] arrB, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).encrypt(arrB);
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @param secretKey
     *            密钥
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encrypt(byte[] arrB, String secretKey) throws Exception {
        return new AESPlus(secretKey).encrypt(arrB);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String strIn, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).encrypt(strIn);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param iv
     *            加密数组
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptCBCPKCS5(String strIn, String secretKey, byte[] iv) throws Exception {
        return new AESPlus(secretKey, new IvParameterSpec(iv), AESPlus.AES_CBC_PKCS5Padding).encrypt(strIn);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @param iv
     *            加密数组
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptCBCPKCS5(String strIn, String secretKey, byte[] iv) throws Exception {
        return new AESPlus(secretKey, new IvParameterSpec(iv), AESPlus.AES_CBC_PKCS5Padding).decrypt(strIn);
    }

    /**
     * 加密字符串
     * 
     * @param arrB
     *            需解密的字节数组
     * @param secretKey
     *            密钥
     * @param iv
     *            加密数组
     * @return 加密后的字符串
     * @throws Exception
     */
    public static byte[] encryptCBCPKCS5(byte[] arrB, String secretKey, byte[] iv) throws Exception {
        return new AESPlus(secretKey, new IvParameterSpec(iv), AESPlus.AES_CBC_PKCS5Padding).encrypt(arrB);
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @param secretKey
     *            密钥
     * @param iv
     *            加密数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decryptCBCPKCS5(byte[] arrB, String secretKey, byte[] iv) throws Exception {
        return new AESPlus(secretKey, new IvParameterSpec(iv), AESPlus.AES_CBC_PKCS5Padding).decrypt(arrB);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String strIn, String secretKey) throws Exception {
        return new AESPlus(secretKey).encrypt(strIn);
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decrypt(byte[] arrB, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).decrypt(arrB);
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @param secretKey
     *            密钥
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decrypt(byte[] arrB, String secretKey) throws Exception {
        return new AESPlus(secretKey).decrypt(arrB);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String strIn, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).decrypt(strIn);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String strIn, String secretKey) throws Exception {
        return new AESPlus(secretKey).decrypt(strIn);
    }

    /**
     * <p>
     * 用base64编码AES的加密数据
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return
     * @throws Exception
     */
    public static String encrytWithBase64(String src, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).encrytWithBase64(src);
    }

    /**
     * <p>
     * 用base64编码AES的加密数据
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static String encrytWithBase64(String src, String secretKey) throws Exception {
        return new AESPlus(secretKey).encrytWithBase64(src);
    }

    /**
     * <p>
     * 解密用base64编码的AES加密数据
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："AES/ECB/PKCS5Padding"、"AES"、"AES/ECB/NoPadding"
     * @return
     * @throws Exception
     */
    public static String decryptWithBase64(String src, String secretKey, String algorithm) throws Exception {
        return new AESPlus(secretKey, algorithm).decryptWithBase64(src);
    }

    /**
     * <p>
     * 解密用base64编码的AES加密数据
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static String decryptWithBase64(String src, String secretKey) throws Exception {
        return new AESPlus(secretKey).decryptWithBase64(src);
    }


}
