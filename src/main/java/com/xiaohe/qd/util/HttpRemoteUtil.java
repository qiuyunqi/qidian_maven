package com.xiaohe.qd.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class HttpRemoteUtil {
	
	/**
     * 以Get方法访问
     * @param url 请求地址
     * @param argsMap 携带的参数
     * @return String 返回结果
     * @throws Exception
     */
    public static String GETMethod(String url, Map<String, Object> argsMap) throws Exception {
        byte[] dataByte = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        url = formatGetParameter(url, argsMap);
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            byte[] responseBytes = getData(httpEntity);
            dataByte = responseBytes;
            httpGet.abort();
        }
        String result = bytesToString(dataByte);
        return result;
    }
	
    /**
     * 构造GET请求地址的参数拼接
     * @param url
     * @param argsMap
     * @return String
     */
    private static String formatGetParameter(String url, Map<String, Object> argsMap) {
        if (url != null && url.length() > 0) {
            if (!url.endsWith("?")) {
                url = url + "?";
            }

            if (argsMap != null && !argsMap.isEmpty()) {
                Set<Entry<String, Object>> entrySet = argsMap.entrySet();
                Iterator<Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    if (entry != null) {
                        String key = entry.getKey();
                        String value = (String) entry.getValue();
                        url = url + key + "=" + value;
                        if (iterator.hasNext()) {
                            url = url + "&";
                        }
                    }
                }
            }
        }
        return url;
    }
    
    /**
     * 以Post方法访问
     * @param url 请求地址
     * @param argsMap 携带的参数
     * @return String 返回结果
     * @throws Exception
     */
    public static String POSTMethod(String url, Map<String, Object> argsMap) throws Exception {
        byte[] dataByte = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        // 设置参数
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(setHttpParams(argsMap), "UTF-8");
        httpPost.setEntity(encodedFormEntity);
        // 执行请求
        HttpResponse httpResponse = httpClient.execute(httpPost);
        // 获取返回的数据
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            byte[] responseBytes = getData(httpEntity);
            dataByte = responseBytes;
            httpPost.abort();
        }
        // 将字节数组转换成为字符串
        String result = bytesToString(dataByte);
        return result;
    }

    /**
     * 获取数据
     * @param httpEntity
     * @return
     * @throws Exception
     */
    private static byte[] getData(HttpEntity httpEntity) throws Exception {
        BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bufferedHttpEntity.writeTo(byteArrayOutputStream);
        byte[] responseBytes = byteArrayOutputStream.toByteArray();
        return responseBytes;
    }

    /**
     * 设置HttpPost请求参数
     * @param argsMap
     * @return BasicHttpParams
     */
    private static List<BasicNameValuePair> setHttpParams(Map<String, Object> argsMap) {
        List<BasicNameValuePair> nameValuePairList = new ArrayList<BasicNameValuePair>();
        // 设置请求参数
        if (argsMap != null && !argsMap.isEmpty()) {
            Set<Entry<String, Object>> set = argsMap.entrySet();
            Iterator<Entry<String, Object>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                nameValuePairList.add(basicNameValuePair);
            }
        }
        return nameValuePairList;
    }

    /**
     * 将字节数组转换成字符串
     * @param bytes
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String bytesToString(byte[] bytes) throws UnsupportedEncodingException {
        if (bytes != null) {
            String returnStr = new String(bytes, "utf-8");
            return returnStr;
        }
        return null;
    }
}
