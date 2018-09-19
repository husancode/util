package net.http;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/2/8
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class HttpURLConnectionRequestTask implements Runnable{

    private static final Log log = LogFactory.getLog(HttpURLConnectionRequestTask.class);

    public static final String TEST_URL = "http://10.82.3.109:8080/gather/isOk";

    private static final int TIME_OUT = 300;

    private String param;

    public HttpURLConnectionRequestTask(String param){
        this.param = param;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        long start = System.currentTimeMillis();
        try {
            URL realUrl = new URL(TEST_URL);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //关闭keep-alive
            //conn.setRequestProperty("Connection","close");
            // 连接超时时间设置默认 300ms
            conn.setConnectTimeout(TIME_OUT);
            // 请求超时时间设置3000ms
            conn.setReadTimeout(TIME_OUT * 10);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = in.readLine();
            log.info(line);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            log.error(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error(ex);
            }
        }

    }
}
