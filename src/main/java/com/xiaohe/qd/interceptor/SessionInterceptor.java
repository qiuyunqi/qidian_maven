package com.xiaohe.qd.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.model.SysPurview;
import com.xiaohe.qd.model.SysRole;
import com.xiaohe.qd.service.SysAdminService;
import com.xiaohe.qd.service.SysRolePurviewService;
import com.xiaohe.qd.service.SysRoleService;
import com.xiaohe.qd.util.WebUtil;

public class SessionInterceptor implements HandlerInterceptor {
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysRolePurviewService sysRolePurviewService;

	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前调用
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		String privUrl = requestUrl.substring(contextPath.length());
		// logger.info(privUrl);
		if (excludeUrls != null && excludeUrls.contains(privUrl)) {
			return true;
		} else {
			SysAdmin admin = null;
			if (WebUtil.getCookieByName(request, "admin_token") != null) {// 自动登录
				String token = WebUtil.getCookieByName(request, "admin_token");
				admin = sysAdminService.findLoginByToken(token);
				if (null == admin) {// 跳转登录页面
					if (privUrl.startsWith("/web/admin/login") || privUrl.startsWith("/web/admin/index")) { // 登录的URL
						request.getRequestDispatcher("/WEB-INF/view/adminLogin/login.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "您还没有登录或登录已超时，");
						request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
					}
					return false;
				} else {
					request.getSession().setAttribute("admin", admin);
					if (privUrl.startsWith("/web/admin/home")) {
						return true;
					}
					if (privUrl.startsWith("/web/admin/logout")) {
						return true;
					}
					// 检查权限
					List<SysPurview> priviList = (List<SysPurview>) request.getSession().getAttribute("priviList");
					if (null == priviList) { // sesson过期,重新存储用户对应的角色权限 和用户对象
						List<SysRole> roleList = sysRoleService.findRoleListByRoleId(admin.getId());
						for (SysRole role : roleList) {
							priviList = sysRolePurviewService.findPurviewListByRoleId(role.getId());
						}
						request.getSession().setAttribute("priviList", priviList);
						request.getSession().setAttribute("admin", admin);
					}
					if (admin.getType() == 1) { // 超管
						return true;
					} else {
						List<String> privilegeUrls = new ArrayList<String>();
						for (SysPurview p : priviList) {
							privilegeUrls.add(p.getUrl());
						}
						if (!privilegeUrls.contains(privUrl)) {
							request.setAttribute("msg", "您没有权限访问该页面!");
							request.getRequestDispatcher("/WEB-INF/view/noPurview.jsp").forward(request, response);
							return false;
						} else {
							for (String pUrl : privilegeUrls) {
								if (privUrl.equals(pUrl)) {
									return true;
								}
							}
						}
					}
				}
				return false;
			} else {
				request.getRequestDispatcher("/WEB-INF/view/adminLogin/login.jsp").forward(request, response);
				return false;
			}
		}
	}
}
