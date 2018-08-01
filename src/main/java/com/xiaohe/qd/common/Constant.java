package com.xiaohe.qd.common;

import java.io.File;

public class Constant {

	public final static String TYPE_NUMBER = "number"; // 参数类型

	public final static String TYPE_STRING = "string"; // 参数类型

	public final static String TYPE_DATE = "date"; // 参数类型

	public final static Integer PAGESIZE = 15;

	// 设定内容的显示字数
	public final static int SUMMARY_NUM = 150;

	// 设置查询超时时间:5s
	public final static int QUERY_TIMEOUT_TIME = 8000;

	// 设置查询连接池的数目
	public final static int THREAD_POOL_NUMBER = 10;

	// 关键词默认值
	public final static String GGCM_SEARCH_KEY_DEFAULT = "headline";

	//日期样式
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YYYYMMDD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_MAX_HH_MM_SS = " 23:59:59";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSE="yyyy年MM月dd日 HH:mm:ss (E)";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	//数据状态 - 1 有效;0-失效（删除）
	public static final int DATA_STAT_ON = 1;
	public static final int DATA_STAT_OFF = 0;
	
	//系统参数类型
	public static final String PARAM_TYPE_DEPT_GRADE="dept_grade";
	
	public static final String USER_DEFAULT_PASSWORD="111111";
	
	//邮件附件暂存地址
	public static final String MAIL_ATTACHMENT_PATH="D:"+File.separator+"MailAttachment";
	//虚拟路径
	public static final String MAIL_VIRTURE_PATH="MailAttachment";
	//储存地址分隔符
	public static final String FILE_SPLIT=File.separator;
	
	//上传文件暂存路径
	public static final String FILE_UPLOAD_LOCATION = "D:"+File.separator+"FileUpload"+File.separator;

	//压缩文件后缀
	public static final String COMPRESSOR_suffix=".zip";
	
	// 邮件状态：0-已发送；1-接收；2-草稿
	public static final String MAIL_STATE_SEND="0";
	public static final String MAIL_STATE_RECIEVE="1";
	
	// 局长信箱
	public static final Integer CATELOGY_ID_EXECUT_PUBLIC_MAILBOX = 8;
	

}
