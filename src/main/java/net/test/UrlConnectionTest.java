package net.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     sun.net.www.protocol.http.Handler
 URLConnection conn = realUrl.openConnection(); 创建了1个sun.net.www.protocol.http.HttpURLConnection.
 最后创建sun.net.www.http.HttpClient开启一个socket连接
 socket.connect(new InetSocketAddress(var1, var2), this.connectTimeout);
  当使用间隔不超过4秒时，会重用之前创建的socket连接。 5秒间隔，服务器端会主动断开连接。下次发起请求会被告知已断开
 连接，然后客户端重新创建一个socket连接。
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/2/7
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class UrlConnectionTest {

    private static final int TIME_OUT = 300;

    private static final String RESULT = "true";

    public static void main(String[] args) throws InterruptedException {
        for( int i =0; i<10 ;i++){
            PrintWriter out = null;
            BufferedReader in = null;
            long start = System.currentTimeMillis();

            try {
                URL realUrl = new URL("http://10.82.3.109:8080/gather/isOk");
                URLConnection conn = realUrl.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 连接超时时间设置默认 300ms
                conn.setConnectTimeout(TIME_OUT);
                // 请求超时时间设置3000ms
                conn.setReadTimeout(TIME_OUT * 10);
                out = new PrintWriter(conn.getOutputStream());
                out.print("param=test1");
                out.flush();
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = in.readLine();
                System.out.println(line);
            } catch (IOException e) {
                System.err.println("catch Exeption");
                e.printStackTrace();
            }finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                long end = System.currentTimeMillis();
                System.out.println(("cost time :" + (end - start)));

            }
            TimeUnit.MILLISECONDS.sleep(4000);
       }

    }
}
