package com.xiaohe.qd.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.log4j.Logger;

public class Md5Util {
	
	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Logger log = Logger.getLogger(Md5Util.class);
	/**
	 * 获取任意的随机字符串
	 */
	public static final String getRandomNum(int size){
		StringBuffer strbuf = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<size;i++){
			strbuf.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return strbuf.toString();
	}
	
	/**
	 * md5加密（ITS）
	 * @param args
	 */
	@SuppressWarnings("unused")
	public synchronized static final String getMD5Str(String str){
		
		MessageDigest messageDigest = null;
		String charset = "iso8859-1";
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			
			/**
			 *  String.getByte("utf-8")和new String(byte[],"utf-8")各是什么意思？
			 *	1. 返回String解码为utf-8的字节序列；
			 *	2. 使用指定字符集解码的字节数组构造一个新的String。
			 */
			if(charset==null){
				messageDigest.update(str.getBytes());
			}else{
				messageDigest.update(str.getBytes(charset));
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			log.error("md5 error:"+e.getMessage(),e);
		} catch (UnsupportedEncodingException e) {
			log.error("md5 error:"+e.getMessage(),e);
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5Strbuf = new StringBuffer();
		
		for(int i=0;i<byteArray.length;i++){
			/**
			 * java代码中Integer.toHexString(b&0xff)
			 * 括号中为什么要写b&0xff
			 * 把整数转换成16进制字符串
			 */
			if(Integer.toHexString(0xFF&byteArray[i]).length()==1){
				md5Strbuf.append("0").append(Integer.toHexString(0xFF&byteArray[i]));
			}else{
				md5Strbuf.append(Integer.toHexString(0xFF&byteArray[i]));
			}
		}
		return md5Strbuf.toString();
	}
	
	//测试结果
	public static void main(String[] args) {
		String str = getRandomNum(5);
		System.out.println(str);
		
		//汉字编码格式影响MD5密码生成结果
		String pwd = "12345";
		String md5 = getMD5Str(pwd);
		System.out.println(md5);
	}
}
