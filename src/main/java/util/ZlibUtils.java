package util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Zlib压缩工具类
 * Created by xucaifu on 2016/5/9.
 */
public class ZlibUtils {

    public static byte[] compress(String original){
        //将报文zlib压缩
        byte[] input = original.getBytes(StandardCharsets.UTF_8);
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        byte[] output = new byte[input.length];
        int lenAfterCompress = compresser.deflate(output);
        return Arrays.copyOf(output,lenAfterCompress);
    }

    public static String uncompress(byte[] compressed) throws DataFormatException {
        Inflater decompresser = new Inflater();
        decompresser.setInput(compressed, 0, compressed.length);
        byte[] output = new byte[compressed.length * 10];
        int resultLength = decompresser.inflate(output);
        decompresser.end();
        return new String(output,0,resultLength,StandardCharsets.UTF_8);
    }

}
