package com.xiaohe.qd.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlCollectUtil {
	/**
	 * 通过网站域名URL获取该网站的源码
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String get_Html_1(String str_url) throws IOException {
		URL url = new URL(str_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(conn.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 通过网站域名URL获取该网站的源码
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String get_Html_2(String str_url) throws IOException {
		URL url = new URL(str_url);
		String content = "";
		StringBuffer page = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			while ((content = in.readLine()) != null) {
				page.append(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page.toString();
	}

	/**
	 * 通过网站域名URL获取该网站的源码
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String get_Html_3(String str_url) throws Exception {
		URL url = new URL(str_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000); // 设置连接超时
		java.io.InputStream inStream = conn.getInputStream(); // 通过输入流获取html二进制数据

		byte[] data = readInputStream(inStream); // 把二进制数据转化为byte字节数据
		String htmlSource = new String(data);
		return htmlSource;
	}

	/**
	 * 把二进制流转化为byte字节数组
	 * 
	 * @param inStream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] readInputStream(java.io.InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1204];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
