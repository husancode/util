package jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/5
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("file","","F:\\q11.txt");
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while( (line=reader.readLine()) != null){
            System.out.println(line);
        }
    }
}
