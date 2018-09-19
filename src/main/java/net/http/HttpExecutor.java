package net.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * <p>
 *     使用连接池复用http连接:
 *     暂时支持get，post有需要再新增
 *     需服务端支持http长连接
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/7/6
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class HttpExecutor {

    private static final Log log = LogFactory.getLog(HttpExecutor.class);
    private static CloseableHttpClient client;

    static{
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //连接池设置：
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(40);
        client = HttpClients.custom()
                .setConnectionManager(cm).build();
    }

    /**
     *  GET方式发送Http请求，返回一个string的字符串
     * @param url       请求的url地址
     * @param headerMap 请求头设置
     * @param readTimeout       读取数据的超时时间: 单位毫秒
     * @param connectTimeout    连接超时时间 ：单位毫秒
     * @return
     * @throws IOException
     */
    public static String httpGet(String url, Map<String, String> headerMap, int readTimeout, int connectTimeout) throws IOException {
        if(log.isDebugEnabled()){
            log.debug("url:" + url);
            log.debug("headerMap:" + mapToString(headerMap));
        }

        HttpEntity entity = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            for(Map.Entry<String, String> entry : headerMap.entrySet()){
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = client.execute(httpGet);
            String respStr = null;
            entity = response.getEntity();
            if(entity != null) {
                respStr = EntityUtils.toString(entity, UTF_8);
            }
            log.debug(respStr);
            return respStr;
        } finally{
           EntityUtils.consume(entity);
        }
    }

    /**
     * POST方式发送Http请求，返回一个string的字符串
     * @param url       请求的url地址
     * @param headerMap 请求头信息
     * @param paramMap  请求参数
     * @param readTimeout   读取数据的超时时间：单位毫秒
     * @param connectTimeout 连接超时时间：单位毫秒
     * @return
     * @throws IOException
     */
    public static String httpPost(String url, Map<String, String> headerMap, Map<String, String> paramMap,
                                  int readTimeout, int connectTimeout)throws IOException{
        if(log.isDebugEnabled()){
            log.debug("url:"+url);
            log.debug("headerMap:"+mapToString(headerMap));
            log.debug("paramMap:"+mapToString(paramMap));
        }
        HttpEntity entity = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if(headerMap != null){
                for(Map.Entry<String, String> entry : headerMap.entrySet()){
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            if(paramMap != null){
                List <NameValuePair> nameValuePairs = new ArrayList<>();
                for(Map.Entry<String, String> entry : paramMap.entrySet()){
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = client.execute(httpPost);
            String respStr = null;
            entity = response.getEntity();
            if(entity != null) {
                respStr = EntityUtils.toString(entity, UTF_8);
            }
            log.debug(respStr);
            return respStr;
        } finally {
            EntityUtils.consume(entity);
        }


    }

    private static<K,V> String mapToString(Map<K, V> map){
        StringBuilder builder = new StringBuilder();
        if(map != null){
            for(Map.Entry<K,V> entry : map.entrySet()){
                builder.append(entry.getKey()).append(EQUAL_DELIMITER).append(entry.getValue()).append(COMMA_DELIMITER);
            }
        }
        return builder.toString();
    }

    private static final String COMMA_DELIMITER = ",";

    private static final String EQUAL_DELIMITER = "=";

    private static final String UTF_8 = "UTF-8";
}
