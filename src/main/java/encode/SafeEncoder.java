package encode;

import java.io.UnsupportedEncodingException;


/**
 * The only reason to have this is to be able to compatible with java 1.5 :(
 */
public class SafeEncoder {

  public static final String CHARSET = "UTF-8";

  public static byte[][] encodeMany(final String... strs) throws UnsupportedEncodingException {
    byte[][] many = new byte[strs.length][];
    for (int i = 0; i < strs.length; i++) {
      many[i] = encode(strs[i]);
    }
    return many;
  }

  public static byte[] encode(final String str) throws UnsupportedEncodingException {

      if (str == null) {
        throw new NullPointerException("value cannot be null");
      }
      return str.getBytes(CHARSET);
  }

  public static String encode(final byte[] data) throws UnsupportedEncodingException {

      return new String(data, CHARSET);

  }
}
