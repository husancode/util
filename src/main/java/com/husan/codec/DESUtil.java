package com.husan.codec;

/**
 * <p>
 * </p>
 */
public final class DESUtil {

    /**
     * 创建一个新的实例DESUtil.
     */
    private DESUtil() {
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
        return new DESPlus(secretKey).encrypt(arrB);
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return 加密后的字节数组
     * @throws Exception
     */
    public static byte[] encrypt(byte[] arrB, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).encrypt(arrB);
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
        return new DESPlus(secretKey).encrypt(strIn);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String strIn, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).encrypt(strIn);
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
        return new DESPlus(secretKey).decrypt(arrB);
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decrypt(byte[] arrB, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).decrypt(arrB);
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
        return new DESPlus(secretKey).decrypt(strIn);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String strIn, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).decrypt(strIn);
    }

    /**
     * <p>
     * base64返回DES加密后的字符串
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return
     * @throws Exception
     */
    public static String encrytWithBase64(String src, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).encrytWithBase64(src);
    }

    /**
     * <p>
     * base64返回DES加密后的字符串
     * </p>
     * @param src
     *            需加密的字符串
     * @param secretKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static String encrytWithBase64(String src, String secretKey) throws Exception {
        return new DESPlus(secretKey).encrytWithBase64(src);
    }

    /**
     * <p>
     * 解密base64的DES加密字符串
     * </p>
     * @param src
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static String decryptWithBase64(String src, String secretKey) throws Exception {
        return new DESPlus(secretKey).decryptWithBase64(src);
    }

    /**
     * <p>
     * 解密base64的DES加密字符串
     * </p>
     * @param src
     *            需解密的字符串
     * @param secretKey
     *            密钥
     * @param algorithm
     *            加密算法 eg："DES/ECB/NoPadding"、"DES"
     * @return
     * @throws Exception
     */
    public static String decryptWithBase64(String src, String secretKey, String algorithm) throws Exception {
        return new DESPlus(secretKey, algorithm).decryptWithBase64(src);
    }
}
