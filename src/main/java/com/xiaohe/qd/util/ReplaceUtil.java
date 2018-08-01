package com.xiaohe.qd.util;
/**
 * 屏蔽关键字
 * 
 * @author han
 * 
 */
public class ReplaceUtil {

	/*public static String[] readProperties(String src) {
		if (null == src)
			src = "/keyword.txt";

		InputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			String str1 = "";
			fis = ReplaceUtil.class.getResourceAsStream(src);// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
			// 当读取的一行不为空时,把读到的str的值赋给str1
			while ((str = br.readLine()) != null) {
				str1 += str + ",";
			}
			return str1.split(",");
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/

	public static String replaceCheck(String[] keys, String name) {
		for (String key : keys) {
			if (name.contains(key)) {
				name = name.replace(key, "****");// 对于符合map中的key值实现替换功能
			}
		}
		return name;
	}

	public static void main(String[] args) {
		String name = "习近平hahahahahTMD";
		String[] keys = (null);
		String content = replaceCheck(keys, name);
		System.out.println(content);
	}
}
