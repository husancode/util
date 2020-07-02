package util;

/**
 * @Auther: husan
 * @Date: 2019/5/31 19:00
 * @Description:
 */
public class HttpResponse {

	/**
	 * http响应码
	 */
	private int statusCode;
	/**
	 * http body数据
	 */
	private String data;

	public HttpResponse(int statusCode, String data){
		this.statusCode = statusCode;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
