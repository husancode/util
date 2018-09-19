package net.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

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
public class HttpClientRequestTask implements Runnable {

    private static final Log log = LogFactory.getLog(HttpClientRequestTask.class);

    private static CloseableHttpClient client;

    static{
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(300);
        cm.setDefaultMaxPerRoute(HttpCompareTest.threadNum);
        client = HttpClients.custom()
                .setConnectionManager(cm).build();
    }

    private String param;

    public HttpClientRequestTask(String param){
        this.param = param;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            HttpPost post = new HttpPost(HttpURLConnectionRequestTask.TEST_URL);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("param", param));
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            post.setEntity(uefEntity);
            CloseableHttpResponse response = client.execute(post);
            // 服务器返回内容
            String respStr = null;
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                respStr = EntityUtils.toString(entity, "UTF-8");
            }
            log.info(respStr);
            // 释放资源
            EntityUtils.consume(entity);
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e);
        }

    }
}
