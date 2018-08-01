package com.xiaohe.qd.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.xiaohe.qd.common.Constant;

public class Utils {

	public static boolean isBlank(String src) {
		if (src != null && !"".equals(src)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefile(String delpath) throws Exception {
		try {

			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + Constant.FILE_SPLIT + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
						System.out
								.println(delfile.getAbsolutePath() + "删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath +Constant.FILE_SPLIT+ filelist[i]);
					}
				}
				System.out.println(file.getAbsolutePath() + "删除成功");
				file.delete();
			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}
	
	/**
	 * 附件保存物理地址
	 * @param mailId
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public static String getAttachPath(String mailId,String subject) {
		String destDir=Constant.MAIL_ATTACHMENT_PATH+Constant.FILE_SPLIT+mailId.replaceAll("<", "").replaceAll(">", "")
				+Constant.FILE_SPLIT+subject;
		return destDir;
	}
	
	/**
	 * 附件保存虚拟地址
	 * @param mailId
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public static String getAttachVirtualPath(String mailId,String subject) {
		String destDir=mailId.replaceAll("<", "").replaceAll(">", "")
				+Constant.FILE_SPLIT+subject;
		return destDir;
	}
	
	/**
	 * 得到字符串在list中存在的string
	 * @param checkStr 要检查的字符串
	 * @param list 字符串列表
	 * @return 没查到值返回null
	 */
	public static String getStringByListExists(String checkStr,List<String> list){
		try{
			for (String obj : list) {
				if(checkStr.indexOf(obj)!=-1){
					return obj;
				}
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
