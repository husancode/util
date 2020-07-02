package util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: husan
 * @Date: 2019/5/31 13:22
 * @Description:
 * http请求发送
 */
public class HttpClient {


	private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

	private static final String KEY_STORE_TYPE_JKS = "jks";


	private static CloseableHttpClient client;

	static{
		//采用绕过验证的方式处理https请求
		SSLContext sslcontext = null;
		try {
			sslcontext = createIgnoreVerifySSL();
		} catch (Exception e) {
			log.error("", e);
		}

		//设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE))
				.build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);


		//连接池设置：
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(40);
		client = HttpClients.custom().setConnectionManager(connManager).build();

	}

	/**
	 *  GET方式发送Http请求
	 * @param url       请求的url地址
	 * @param headerMap 请求头设置
	 * @param readTimeout       读取数据的超时时间: 单位毫秒
	 * @param connectTimeout    连接超时时间 ：单位毫秒
	 * @return
	 * @throws IOException
	 */
	public static HttpResponse httpGet(String url, Map<String, String> headerMap, int readTimeout, int connectTimeout) throws IOException {
		if(log.isDebugEnabled()){
			log.debug("url:" + url);
			log.debug("headerMap:" + mapToString(headerMap));
		}

		HttpEntity entity = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			if(headerMap != null){
				for(Map.Entry<String, String> entry : headerMap.entrySet()){
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
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
			return new HttpResponse(response.getStatusLine().getStatusCode(), respStr);
		} finally{
			EntityUtils.consume(entity);
		}
	}

	/**
	 * 获取流，使用后手动关闭
	 * @param url
	 * @param headerMap
	 * @param readTimeout
	 * @param connectTimeout
	 * @return
	 * @throws IOException
	 */
	public static InputStream httpGetWithStream(String url, Map<String, String> headerMap, int readTimeout, int connectTimeout) throws IOException{
		if(log.isDebugEnabled()){
			log.debug("url:" + url);
			log.debug("headerMap:" + mapToString(headerMap));
		}

		HttpEntity entity = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			if(headerMap != null){
				for(Map.Entry<String, String> entry : headerMap.entrySet()){
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = client.execute(httpGet);
			String respStr = null;
			entity = response.getEntity();
			if(entity != null){
				return entity.getContent();
			}
		} finally{
			//EntityUtils.consume(entity);
		}
		return null;
	}

	/**
	 * POST方式发送Http请求
	 * @param url       请求的url地址
	 * @param headerMap 请求头信息
	 * @param paramMap  请求参数
	 * @param readTimeout   读取数据的超时时间：单位毫秒
	 * @param connectTimeout 连接超时时间：单位毫秒
	 * @return
	 * @throws IOException
	 */
	public static HttpResponse httpPost(String url, Map<String, String> headerMap, Map<String, String> paramMap,
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
				List<NameValuePair> nameValuePairs = new ArrayList<>();
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
			return new HttpResponse(response.getStatusLine().getStatusCode(), respStr);
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

	/**
	 * 绕过验证
	 *
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	public static void main(String[] args) throws IOException {

		String url = "https://test.jgwork.zjzwfw.gov.cn/cas/tgcValidate?TGC=TGT-24-xaROcJ46dlWMFHmgDnZJ93xRjK9dpTNtqAoqep9dz6GbvNWTVv-cas01.example.org";
		HttpResponse resp = HttpClient.httpGet(url, null, 30, 30);
		System.out.println(resp.getData());
		System.out.println(resp.getStatusCode());
	}


	private static final String COMMA_DELIMITER = ",";

	private static final String EQUAL_DELIMITER = "=";

	private static final String UTF_8 = "UTF-8";
}
