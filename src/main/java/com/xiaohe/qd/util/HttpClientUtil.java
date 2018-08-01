package com.xiaohe.qd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {
	private static final Log logger = LogFactory.getLog(HttpClientUtil.class);

	// http://service.winic.org/sys_port/gateway/?id=userid&pwd=password&to=13928783309,13800008888&content=这里填短信内容！&time=

	// HTTP 通过 GET方式交互
	/**
	 * http get 方式交互
	 */
	public static String getHTTP(String URL) {
		String responseMsg = "";

		// 构造HTTPClient的实例
		HttpClient httpClient = new HttpClient();

		GetMethod getmethod = new GetMethod(URL);

		// 使用系统系统的默认的恢复策略
		getmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		try {
			// ִ执行 HTTPClient方法，调用HTTP接口
			httpClient.executeMethod(getmethod);
			// 读取返回的内容
			byte[] responseBody = getmethod.getResponseBody();

			// 处理返回内容
			responseMsg = new String(responseBody);

			// 返回结果显示
			// System.out.println("HTTP GET 方式执行结果："+responseMsg);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放操作
			getmethod.releaseConnection();
		}
		return responseMsg;
	}

	public static String getHTTP_New(String URL) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(URL);
		// 此处可以在getMethod上添加请求参数
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			responseMsg = new String(responseBody);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}

	// HTTP 通过POST方式交互
	/**
	 * http post 方式交互
	 */
	public static String postHTTP(String URL, String uid, String pwd, String tos, String content, String otime) {
		String ResultStrMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("GB2312");

		PostMethod method = new PostMethod(URL);

		// 把参数值放入到PostMethod对象中

		// 方式一
		NameValuePair[] dataparam = { new NameValuePair("id", uid), new NameValuePair("pwd", pwd), new NameValuePair("to", tos), new NameValuePair("content", content), new NameValuePair("time", otime) };

		method.addParameters(dataparam);

		// 方式二
		// method.addParameter("", "");
		// method.addParameter("", "");

		try {
			// 执行接口方法，调用接口方法
			httpClient.executeMethod(method);
			// 读取返回的值ֵ
			ResultStrMsg = method.getResponseBodyAsString().trim();

			// System.out.println("HTTP POST 方式执行结果："+ResultStrMsg);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return ResultStrMsg;
	}

	public static String postHTTP_new(String URL, String uid, String pwd, String tos, String content, String otime) {
		String ResultStrMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("GB2312");

		PostMethod method = new PostMethod(URL);

		// 把参数值放入到PostMethod对象中

		// 方式一
		NameValuePair[] dataparam = { new NameValuePair("account", uid), new NameValuePair("pswd", pwd), new NameValuePair("mobile", tos), new NameValuePair("msg", content), new NameValuePair("needstatus", otime) };

		method.addParameters(dataparam);

		// 方式二
		// method.addParameter("", "");
		// method.addParameter("", "");

		try {
			// 执行接口方法，调用接口方法
			httpClient.executeMethod(method);
			// 读取返回的值ֵ
			ResultStrMsg = method.getResponseBodyAsString().trim();

			// System.out.println("HTTP POST 方式执行结果："+ResultStrMsg);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return ResultStrMsg;
	}

	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("Weixin server connection timed out.");
		} catch (Exception e) {
			logger.error("https request error:{}", e);
		}
		return jsonObject;
	}

}
