package com.husan.lang;

import java.nio.ByteBuffer;

/**
 * Byte处理工具类
 * 
 */
public final class ByteUtil {

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    private static final char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 大端对其方式，顺序读取,读取完报文长度position还原为读取前的位置
     * 
     * @param ioBuffer
     * @param length
     * @return
     */
    public static int getDataLength(ByteBuffer ioBuffer, int length) {
        int position = ioBuffer.position();
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            int b = ioBuffer.get() & 0xFF;
            result |= b << (i * 8);
        }
        ioBuffer.position(position);
        return result;
    }

    /**
     * 大端对其方式,短整型转字节数组
     */
    public static byte[] shortToByteArray(short s) {
        byte[] byteArray = new byte[2];
        byteArray[1] = (byte) (0xFF & s);
        byteArray[0] = (byte) ((0xFF00 & s) >> 8);
        return byteArray;
    }

    /**
     * 大端对其方式,字节数组转短整型
     */
    public static short byteArrayToShort(byte[] byteArray) {
        short s = (short) (byteArray[1] & 0xFF);
        s |= (short) ((byteArray[0] << 8) & 0xFF00);
        return s;
    }

    /**
     * 大端对其方式,字节数组转int类型
     */
    public static int byteArrayToInt(byte[] byteArray) {
        int i = byteArray[3] & 0xFF;
        i |= (byteArray[2] << 8) & 0xFF00;
        i |= (byteArray[1] << 16) & 0xFF0000;
        i |= (byteArray[0] << 24) & 0xFF000000;
        return i;
    }

    /**
     * 大端对其方式,int类型转字节数组
     */
    public static byte[] intToByteArray(int i) {
        byte[] byteArray = new byte[4];
        byteArray[3] = (byte) (0xFF & i);
        byteArray[2] = (byte) ((0xFF00 & i) >> 8);
        byteArray[1] = (byte) ((0xFF0000 & i) >> 16);
        byteArray[0] = (byte) ((0xFF000000 & i) >> 24);
        return byteArray;
    }

    /**
     * <p>
     * 把byte数组转成HexString
     * </p>
     * 
     * @author DingLuofeng 2012-11-27 下午3:52:57
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes) {
        return byteArrayToHex(bytes, 0, bytes.length);
    }

    /**
     * <p>
     * 把byte数组转成HexString
     * </p>
     * 
     * @author DingLuofeng 2013-5-28 上午8:52:46
     * @param bytes
     *            需要转换的byte数组
     * @param offset
     *            数组偏移量
     * @param length
     *            需要转换的长度
     * @return
     */
    private static String byteArrayToHex(byte bytes[], int offset, int length) {
        StringBuffer stringbuffer = new StringBuffer(2 * length);
        int k = offset + length;
        for (int l = offset; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * <p>
     * 把十六进制字符串转化为Byte数组,和toHexString方法互逆
     * </p>
     * 
     * @author DingLuofeng 2013-5-28 上午9:11:27
     * @param hexString
     * @return
     * @throws Exception
     */
    public static byte[] toByteArray(String hexString) throws Exception {
        byte[] arrB = hexString.getBytes();
        int iLen = arrB.length;
        if (iLen % 2 != 0) {
            throw new IllegalArgumentException("hexString's length must be multiple of 2.");
        }
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public static void main(String[] args) {
        ByteBuffer bufTmp = ByteBuffer.allocate(8);
        // String aa = "AAAAQA";
        // int ln = aa.getBytes().length;
        // System.out.println(ln);
        // bufTmp.putShort((short) ln);
        // bufTmp.put(aa.getBytes());
        // bufTmp.flip();
        bufTmp.putInt(5);

        int datalength = getDataLength(bufTmp, 2);
        System.out.println(datalength);

        byte[] intToByteArray = intToByteArray(5);
        int byteArrayToInt = byteArrayToInt(intToByteArray);
        System.out.println(byteArrayToInt);

    }
}
