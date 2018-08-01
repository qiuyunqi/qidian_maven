package com.xiaohe.qd.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 公共工具类
 * 
 */
public class CommonUtil {

	//private static final Log log = LogFactory.getLog(CommonUtil.class);

	/**
	 * 得到String的MD5码
	 * 
	 * @param srcString
	 *            将要加密码的字符串
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
	 * 
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
		DecimalFormat format = new DecimalFormat("0.00");
		if ("0.00".equals(format.format(byteSize))) {
			return "0.01KB";
		}
		return format.format(byteSize) + suffix;
	}

	/**
	 * 格式化数字
	 * 
	 * @param number
	 *            原数字
	 * @param fmt
	 *            格式 如 "0.00"
	 * @return
	 */
	public static String formatNumber(double number, String fmt) {
		DecimalFormat format = new DecimalFormat(fmt);
		return format.format(number);
	}

	// ========================== 生成文件名
	// ==========================================
	/**
	 * 生成临时文件名，格式：nowTime_userId_random
	 * 
	 * @param userId
	 * @return
	 */
	public static String createFileTempName(int userId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成通知附件名，格式：nowTime_orgId_userId_random
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public static String createFileNoticeName(int userId, int orgId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(orgId).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成网盘附件名，格式：nowTime_orgId_userId_random
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public static String createFileWebdiskName(int userId, int orgId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(orgId).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成站内信附件名，格式：nowTime_sendUserId_receiveUserId_random
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @return
	 */
	public static String createFileLetterName(int sendUserId, int receiveUserId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(sendUserId).append("_").append(receiveUserId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成作业附件名，格式：nowTime_orgId_userId_random
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public static String createFileHomeworkName(int userId, int orgId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(orgId).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成标题图片名，格式：nowTime_userId_random
	 * 
	 * @param userId
	 * @return
	 */
	public static String createFileImageName(int userId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成相片名，格式：nowTime_orgId_userId_random
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public static String createFilePhotoName(int userId, int orgId) {
		StringBuilder fileName = new StringBuilder();
		String nowTime = DateUtil.getStrFromDate(new Date(), "yyyyMMddHHmmss"); // 当前时间
		int random = new Random().nextInt(999999); // 100W内的一个随机数
		fileName.append(nowTime).append("_").append(orgId).append("_").append(userId).append("_").append(random);
		return fileName.toString();
	}

	/**
	 * 生成个人头像名，格式：userId
	 * 
	 * @param userId
	 * @return
	 */
	public static String createFilePictureName(int userId) {
		return String.valueOf(userId);
	}

	/**
	 * 生成组织logo名，格式：orgId
	 * 
	 * @param orgId
	 * @return
	 */
	public static String createFileLogoName(int orgId) {
		return String.valueOf(orgId);
	}

	/*
	 * public static String getErweima(String link) { try { Map<String, String>
	 * map = new HashMap<String, String>(); map.put("data", link);
	 * map.put("do_text", "1"); Document doc =
	 * Jsoup.connect("http://qr.jibaoku.com/creat.php").data(map).post(); String
	 * src = doc.select("img").attr("src"); return src; } catch (Exception e) {
	 * 
	 * } return null; }
	 */

	/**
	 * 手机验证
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String SMS(String postData, String postUrl) {
		try {
			postData = "sname=dlydswcf&spwd=XazE9m9Q&scorpid=&sprdid=1012818&sdst=" + postUrl + "&smsg=" + URLEncoder.encode(postData, "utf-8");
			// 发送POST请求
			URL url = new URL("http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	/*
	 * public static boolean sendSms_old(String phones, String[] args) { String
	 * msg =
	 * HttpClientUtil.postHTTP("http://service.winic.org/sys_port/gateway/?",
	 * "whkzdd", "1qaz2wsx", phones, "【超级合伙人】你的验证码是" + args[0] +
	 * ",为了保护您的账号安全，验证短信请勿转发给其他人", ""); String str = msg.split("/")[0]; if
	 * (str.equals("000")) { return true; } else { return false; } }
	 */

	/*
	 * public static boolean sendSmsProgram_old(String phones, String message) {
	 * // String content="【超级合伙人联盟】"+message; String content = message; String
	 * msg =
	 * HttpClientUtil.postHTTP("http://service.winic.org/sys_port/gateway/?",
	 * "whkzdd", "1qaz2wsx", phones, content, ""); String str =
	 * msg.split("/")[0]; if (str.equals("000")) { System.out.println(phones +
	 * "---发送成功---"); return true; } else { return false; } }
	 */

	/*
	 * public static boolean sendSms(String phones, String[] args) { String uri
	 * = "http://222.73.117.158/msg/main.do";//应用地址 String account =
	 * "kunzhou_pc";//账号 String pswd = "kzDD_2015";//密码 // String account =
	 * "vip_cjhhr";//账号 // String pswd = "Tch123456";//密码 // String mobiles =
	 * "13800210021,13800138000";//手机号码，多个号码使用","分割 // String content =
	 * "【坤州大德】你的验证码是"+args[0]+",为了保护您的账号安全，验证短信请勿转发给其他人";//短信内容 String content =
	 * "你的验证码是"+args[0]+",为了保护您的账号安全，验证短信请勿转发给其他人";//短信内容 boolean needstatus =
	 * false;//是否需要状态报告，需要true，不需要false String product = "";//产品ID String extno
	 * = "";//扩展码
	 * 
	 * String str = ""; try { String returnString = HttpSender.batchSend(uri,
	 * account, pswd, phones, content, needstatus, product, extno);
	 * System.out.println(returnString); str=returnString.split(",")[1]; //TODO
	 * 处理返回值,参见HTTP协议文档 } catch (Exception e) { //TODO 处理异常 e.printStackTrace();
	 * } if(str.equals("0")){ return true; }else{ return false; } }
	 */
	/*
	 * public static boolean sendSmsProgram(String phones, String message) { //
	 * String content="【坤州大德】"+message; String content=message;
	 * 
	 * String uri = "http://222.73.117.158/msg/main.do";//应用地址 String account =
	 * "kunzhou_pc";//账号 String pswd = "kzDD_2015";//密码 // String account =
	 * "vip_cjhhr";//账号 // String pswd = "Tch123456";//密码 // String mobiles =
	 * "13800210021,13800138000";//手机号码，多个号码使用","分割 // String content =
	 * "【坤州大德】你的验证码是"+args[0]+",为了保护您的账号安全，验证短信请勿转发给其他人";//短信内容 boolean
	 * needstatus = false;//是否需要状态报告，需要true，不需要false String product = "";//产品ID
	 * String extno = "";//扩展码
	 * 
	 * String str = ""; try { String returnString = HttpSender.batchSend(uri,
	 * account, pswd, phones, content, needstatus, product, extno);
	 * System.out.println(returnString); str=returnString.split(",")[1]; //TODO
	 * 处理返回值,参见HTTP协议文档 } catch (Exception e) { //TODO 处理异常 e.printStackTrace();
	 * } if(str.equals("0")){ return true; }else{ return false; } }
	 */

	/**
	 * 发送验证码短信
	 * 
	 * @param phones
	 * @param args
	 * @return
	 */
	/*
	 * public static boolean sendSms(String phone, String[] args) { String
	 * apikey = "190285e774686294dddc3de3286e812d"; String str = ""; String
	 * message = "【超级合伙人】您的验证码是" + args[0] + ",为了保护您的账号安全，验证短信请勿转发给其他人。"; try {
	 * String returnString = SendMessageUtil.sendSms(apikey, message, phone);
	 * System.out.println(returnString); JSONObject obj = new
	 * JSONObject(returnString); str = obj.get("code").toString(); } catch
	 * (Exception e) { e.printStackTrace(); } if (str.equals("0")) { return
	 * true; } else { return false; } }
	 */

	/**
	 * 发送常规短信
	 * 
	 * @param phones
	 * @param message
	 * @return
	 */
	/*
	 * public static boolean sendSmsProgram(String phone, String message) {
	 * String apikey = "190285e774686294dddc3de3286e812d"; String str = "";
	 * message = "【超级合伙人】" + message; try { String returnString =
	 * SendMessageUtil.sendSms(apikey, message, phone);
	 * System.out.println(returnString); JSONObject obj = new
	 * JSONObject(returnString); str = obj.get("code").toString(); } catch
	 * (Exception e) { e.printStackTrace(); } if (str.equals("0")) { return
	 * true; } else { return false; } }
	 */

	/**
	 * 发送语音短信
	 * 
	 * @param phones
	 * @param message
	 * @return
	 */
	/*
	 * public static boolean sendSmsVoice(String phone, String code) { String
	 * apikey = "190285e774686294dddc3de3286e812d"; String str = ""; try {
	 * String returnString = SendMessageUtil.sendVoice(apikey, phone, code);
	 * System.out.println(returnString); JSONObject obj = new
	 * JSONObject(returnString); str = obj.get("code").toString(); } catch
	 * (Exception e) { e.printStackTrace(); } if (str.equals("0")) { return
	 * true; } else { return false; } }
	 */

	/**
	 * 发送短信
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// HttpClientUtil.getHTTP("http://service.winic.org/sys_port/gateway/?id=userid&pwd=password&to=13928783309,13800008888&content="+URLEncoder.encode("这是一条测试短信！",
		// "GB2312")+"&time=");
		// HttpClientUtil.postHTTP("http://service.winic.org/sys_port/gateway/?",
		// "userid", "password", "13928783309,13800008888",
		// "this is a test msg!这是一条测试短信！", "");

		// sendSmsProgram("15927471230","测试000011222");
		// sendSmsProgram_old("13707173794,13098833176,18071756773",
		// "内部消息，7天收益翻番，还能抢IPhone 6s，点击查看详情http://t.cn/R4Qu44P");
		// sendSmsProgram_old("13707173794,13098833176,18071756773","猜涨跌，春节就靠你发红包了，点击查看详情http://t.cn/R4Qu44P");
		// sendSmsProgram_old("13707173794,13098833176,18071756773","不是说好了要一起赚取收益，走上人生巅峰的么？怎么客官您都不来了呢？点击查看涨跌赢http://t.cn/R4Qu44P");
		// String[] str={"111","BBB","CCC"};
		// sendSms_old("13707173794,18071756773",str);
		// String[] str = {"测试0000111sendSms"};
		// sendSms("13707173794", str);
	}

	/**
	 * 手机验证
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String SMSnotify(String postData, String postUrl) {
		try {

			postData = "action=send&userid=103&account=ABC&password=abc12345&sendTime=&extno=&mobile=" + postUrl + "&content=" + URLEncoder.encode(postData, "utf-8");
			// 发送POST请求
			URL url = new URL("http://inter.ueswt.com/sms.aspx");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 对json数组中的元素按某个key的值排序
	 * @param ja json数组
	 * @param field 要排序的key
	 * @param isAsc 是否升序
	 */
	@SuppressWarnings("unchecked")
	public static void jsonArraySort(JSONArray ja,final String field, boolean isAsc){
		Collections.sort(ja, new Comparator<JSONObject>() {
			public int compare(JSONObject o1, JSONObject o2) {
				Object f1 = o1.get(field);
				Object f2 = o2.get(field);
				if(f1 instanceof Number && f2 instanceof Number){
					return ((Number)f1).intValue() - ((Number)f2).intValue();
				}else{
					return f1.toString().compareTo(f2.toString());
				}
			}
		});
		if(!isAsc){
			Collections.reverse(ja);
		}
	}
}
