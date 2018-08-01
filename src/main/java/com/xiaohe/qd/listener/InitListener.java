package com.xiaohe.qd.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xiaohe.qd.util.Property;
import com.xiaohe.qd.util.ReplaceUtil;

public class InitListener implements ServletContextListener {

	// 装载权限列表的URL数据
	public void contextInitialized(ServletContextEvent sc) {
		String src = "/keyword.txt";

		InputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			String str1 = "";
			fis = ReplaceUtil.class.getResourceAsStream(src);// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis, "UTF-8");// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
			// 当读取的一行不为空时,把读到的str的值赋给str1
			while ((str = br.readLine()) != null) {
				str1 += str + ",";
			}
			sc.getServletContext().setAttribute("keys", str1.split(","));
			sc.getServletContext().setAttribute("HHR_PREFIX", Property.getProperty("HHR_PREFIX"));
			sc.getServletContext().setAttribute("QIDIAN_PREFIX", Property.getProperty("QIDIAN_PREFIX"));
			sc.getServletContext().setAttribute("ZHIBO_PREFIX", Property.getProperty("ZHIBO_PREFIX"));
			System.out.println("------------> 已准备数据关键词列表 <------------");
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
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
