package com.xiaohe.qd.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * 公共工具类
 * 
 */

public class CommonUtils {

	//private static final Log log = LogFactory.getLog(CommonUtils.class);

	/**
	 * 得到String的MD5码
	 * 
	 * @param srcString 将要加密码的字符串
	 * @return String 加密后的字符串
	 */
	public static String getMd5(String srcString) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(srcString.getBytes("UTF8"));
			byte s[] = md.digest();
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < s.length; i++) {
				result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化文件大小
	 * @param size
	 * @return
	 */
	public static String formatDataSize(Long dataSize) {
		double byteSize = dataSize / 1024;
		String suffix = "KB";
		if (byteSize > 1000) {
			byteSize = byteSize / 1024;
			suffix = "MB";
		}
		DecimalFormat format = new DecimalFormat("#.00");
		return format.format(byteSize) + suffix;
	}
	
	/**
	 * 格式化数字
	 * @param number 原数字
	 * @param fmt 格式 如 "0.00"
	 * @return
	 */
	public static String formatNumber(double number, String fmt) {
		DecimalFormat format = new DecimalFormat(fmt);
		return format.format(number);
	}

}
