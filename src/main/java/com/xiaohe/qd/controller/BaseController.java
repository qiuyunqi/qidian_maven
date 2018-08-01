package com.xiaohe.qd.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xiaohe.qd.util.Constants;

public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());

	private int pageNo = 0;
	private int pageSize = Constants.DEFAULT_PAGE_SIZE;
	// 没有权限
	public static final String NOPERMIT = "nopermission";
	// 不存在
	public static final String NOTEXIST = "not_exist";
	// 系统关闭
	public static final String SYSCLOSED = "sys_close";
	// 未激活
	public static final String UNCHECKED = "unchecked";
	// 被屏蔽
	public static final String BLACK = "black";
	// 后台未登录
	public static final String NOLOGIN = "no_login";
	
	public static final String URL_SUFFIX = ".html";
	// 当前被选择的用户ID
	protected Long usrId;
	// 学校ID
	protected Long schId;
	// 权限
	protected Integer permission;
	// 新手
	protected Boolean newHand;
	// 用户引导的key
	protected String tipKey;
	// 用户编辑器里面上传的图片
	protected String[] upImgs;

	/**
	 * 输出字符串
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void write(HttpServletResponse response, String message) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(message);
		response.getWriter().flush();
	}

	/**
	 * 输出字符串
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void writeJson(HttpServletResponse response, Object obj) throws Exception {
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}

	// =================================
	public int getPageNo() {
		return Math.max(1, pageNo);
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	public Long getUsrId() {
		return usrId;
	}

	public void setNewHand(Boolean newHand) {
		this.newHand = newHand;
	}

	public Boolean getNewHand() {
		return newHand;
	}

	public void setTipKey(String tipKey) {
		this.tipKey = tipKey;
	}

	public String getTipKey() {
		return tipKey;
	}

	public void setUpImgs(String[] upImgs) {
		this.upImgs = upImgs;
	}

	public String[] getUpImgs() {
		return upImgs;
	}

	public void setSchId(Long schId) {
		this.schId = schId;
	}

	public Long getSchId() {
		return schId;
	}
}
