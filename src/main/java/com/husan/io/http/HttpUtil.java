package com.husan.io.http;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * <p>
 * Http工具类
 * </p>
 * 
 */
public class HttpUtil {

    public static final String HTTP_CONN_TIMEOUT = "http_conn_timeout";

    public static final String HTTP_READ_TIMEOUT = "http_read_timeout";

    public static final String GET = "GET";

    public static final String POST = "POST";

    public static final String PUT = "PUT";

    public static final String DELETE = "DELETE";

    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    private static HttpURLConnection getConn(String requestUrl, String method) throws IOException {
        return getConn(new URL(requestUrl), method, "application/x-www-form-urlencoded;charset=UTF-8", null);
    }

    private static HttpURLConnection getConn(String requestUrl, String method, Map<String, String> headers)
        throws IOException {
        return getConn(new URL(requestUrl), method, "application/x-www-form-urlencoded;charset=UTF-8", headers);
    }

    private static HttpURLConnection getConn(URL url, String method, String contentType, Map<String, String> headers)
        throws IOException {
        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;// 默认都认证通过
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json, text/javascript, */*;");
        conn.setRequestProperty("Content-Type", contentType);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        int connTimeout = Integer.parseInt(System.getProperty(HTTP_CONN_TIMEOUT, "15000"));
        int readTimeout = Integer.parseInt(System.getProperty(HTTP_READ_TIMEOUT, "15000"));
        conn.setConnectTimeout(connTimeout);
        conn.setReadTimeout(readTimeout);
        return conn;
    }

    /**
     * GET请求
     * 
     * @param requestURL
     *            请求URL
     * @return
     */
    public static HttpCommonResponse httpGetConnect(String requestURL) throws IOException {
        HttpCommonResponse response = null;
        HttpURLConnection connection = null;
        try {
            connection = getConn(requestURL, GET);
            connection.connect();
            response = buildHttpCommonResponse(connection);
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    /**
     * 支持设置头内容的http get请求获取内容
     * 
     * @param requestURL
     *            请求的URL
     * @param headers
     *            请求的header内容
     * @return
     */
    public static HttpCommonResponse httpGetConnect(String requestURL, Map<String, String> headers) throws IOException {
        HttpCommonResponse response = null;
        HttpURLConnection connection = null;
        try {
            connection = getConn(requestURL, GET, headers);
            connection.connect();
            response = buildHttpCommonResponse(connection);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    /**
     * <p>
     * POST请求包含往请求中写content数据
     * </p>
     * 
     * @author zhangxin 2013-6-13 下午1:55:51
     * @param requestURL
     * @param headers
     * @param content
     * @return
     */
    public static HttpCommonResponse httpPostConnect(String requestURL, Map<String, String> headers, String content) throws IOException {
        return httpConnect(requestURL, headers, content, POST);
    }

    /**
     * 请求包含往请求中写content数据
     * 
     * @param requestURL
     *            请求的URL
     * @param headers
     *            请求的header内容
     * @param content
     *            请求的内容
     * @param method
     *            请求的方法
     * @return
     */
    public static HttpCommonResponse httpConnect(String requestURL, Map<String, String> headers, String content,
            String method) throws IOException {
        HttpCommonResponse response = null;
        OutputStream os = null;
        HttpURLConnection connection = null;
        try {
            connection = getConn(requestURL, method, headers);
            if (StringUtils.isNotEmpty(content)) {
                connection.setDoOutput(true);
                connection.connect();
                os = connection.getOutputStream();
                os.write(content.getBytes("UTF-8"));
                os.flush();
            }
            response = buildHttpCommonResponse(connection);
        }  finally {
            os.close();
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }



    /**
     * 构建响应对象 目前返回content只支持文本
     * 
     * @param connection
     * @return
     * @throws IOException
     */
    private static HttpCommonResponse buildHttpCommonResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = null;
        HttpCommonResponse response = new HttpCommonResponse();
        try {
            response.setHttpCode(connection.getResponseCode());
            response.setContentType(connection.getContentType());
            response.setContentLength(connection.getContentLength());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                response.setContent(sb.toString());
            }
        } finally {
            reader.close();
        }
        return response;
    }

    /**
     * <p>
     * 发送UDP请求并返回
     * </p>
     * 
     * @param msg
     * @param ip
     * @param port
     * @return
     */
    public static String sendUDPMsg(String msg, String ip, Integer port) throws IOException {
        String backMsg = null;
        byte[] buf = msg.getBytes();
            InetAddress address = InetAddress.getByName(ip); // 服务器地址
            DatagramPacket dataGramPacket = new DatagramPacket(buf, buf.length, address, port);
            DatagramSocket socket = new DatagramSocket(); // 创建套接字
            socket.send(dataGramPacket); // 通过套接字发送数据
            socket.setSoTimeout(12000);
            // 接收服务器反馈数据
            byte[] backbuf = new byte[1024];
            DatagramPacket backPacket = new DatagramPacket(backbuf, backbuf.length);
            socket.receive(backPacket); // 接收返回数据
            backMsg = new String(backbuf, 0, backPacket.getLength());
            socket.close();
        return backMsg;
    }

    public static class HttpCommonResponse {

        private int httpCode; // http响应

        private String contentType; // 响应类型

        private long contentLength; // 内容长度

        private String content; // 响应内容

        public HttpCommonResponse() {

        }

        public int getHttpCode() {
            return httpCode;
        }

        public void setHttpCode(int httpCode) {
            this.httpCode = httpCode;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public long getContentLength() {
            return contentLength;
        }

        public void setContentLength(long contentLength) {
            this.contentLength = contentLength;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "HttpCommonResponse [httpCode=" + httpCode + ", contentType=" + contentType + ", contentLength="
                    + contentLength + ", content=" + content + "]";
        }

        public boolean isSuccess() {
            return httpCode == HttpURLConnection.HTTP_OK;
        }

    }

    public static void main(String[] args) throws IOException {
        String requestURL = "http://10.82.4.119:8080/isok";
        HttpCommonResponse httpPostConnect = HttpUtil.httpGetConnect(requestURL);
        System.out.println(httpPostConnect.getContent());
    }

}
