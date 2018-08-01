package com.xiaohe.qd.controller.qidianadmin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.model.SysIpBlacklist;
import com.xiaohe.qd.model.SysPurview;
import com.xiaohe.qd.model.SysRole;
import com.xiaohe.qd.service.SysAdminService;
import com.xiaohe.qd.service.SysIpBlacklistService;
import com.xiaohe.qd.service.SysPurviewService;
import com.xiaohe.qd.service.SysRolePurviewService;
import com.xiaohe.qd.service.SysRoleService;
import com.xiaohe.qd.util.CommonUtils;
import com.xiaohe.qd.util.IP4;
import com.xiaohe.qd.util.WebUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminLoginController extends BaseController {
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysRolePurviewService sysRolePurviewService;
	@Resource
	private SysIpBlacklistService sysIpBlacklistService;
	@Resource
	private SysPurviewService sysPurviewService;

	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public String index(Model model, HttpSession session) {
		return "redirect:/web/admin/login";
	}

	/**
	 * 后台登录页面 （如果记住账号，传入账号，如果自动登陆，直接跳转到首页）
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, ModelMap map, HttpSession session) {
		try {
			if (WebUtil.getCookieByName(request, "remain_token") != null) {
				String account = WebUtil.getCookieByName(request, "remain_token");
				map.put("account", account);
			}
			if (WebUtil.getCookieByName(request, "admin_token") != null) {
				SysAdmin admin = sysAdminService.findLoginByToken(WebUtil.getCookieByName(request, "admin_token"));
				if (admin != null && admin.getIsAuto() != null && admin.getIsAuto()) {// 自动登录
					// 存储用户的基本信息
					session.setAttribute("admin", admin);
					return "redirect:/web/admin/home";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminLogin/login";
	}

	/**
	 * 登录操作 （异步操作，后续可以做跳转）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginAjax", produces = "text/html;charset=UTF-8")
	public void loginAjax(HttpServletRequest request, HttpServletResponse response, ModelMap map, HttpSession session, String account, String password, Boolean isRemain, Boolean isAuto) {
		try {
			if (StringUtil.isBlank(account)) {
				write(response, "-2");// 请输入登录账号
				return;
			}
			if (StringUtil.isBlank(password)) {
				write(response, "-3");// 请输入登录密码
				return;
			}
			SysAdmin admin = sysAdminService.findAdminByAccount(account);
			if (admin == null) {
				write(response, "-4");// 账号不存在
				return;
			}
			// 登录ip地址
			String ipStr = IP4.getIP4(request);
			// ip表size
			int ipBlackCount = sysIpBlacklistService.countIpBlack(new HashMap<String, Object>());
			// ip表size0,不校验
			if (ipBlackCount != 0 && !admin.getAccount().equals("admin")) {
				// 根据ip地址查询记录
				SysIpBlacklist ipBlacklist = sysIpBlacklistService.findBlackByRegIp(ipStr);
				// 如果查不到ip地址对应的表记录,或者有记录但是黑名单状态,均禁止登录
				if (ipBlacklist == null || (ipBlacklist != null && ipBlacklist.getIsBlack() == 1)) {
					log.info("您的IP地址: " + ipStr + " 在黑名单中");
					write(response, "-6");
					return;
				}
			}
			// 禁止登录时间校验
			if (admin.getForbidLoginTime() != null) {
				if (admin.getForbidLoginTime().compareTo(new Date()) >= 0) {
					log.info("您的IP地址: " + ipStr + " 禁止登录中");
					write(response, "-7");
					return;
				}
			}
			// 密码错误
			if (!admin.getPassword().equals(CommonUtils.getMd5(password))) {
				// 密码错误次数小于5
				if (admin.getLoginErrorTimes() < 5) {
					// 密码错误次数累加
					admin.setLoginErrorTimes(admin.getLoginErrorTimes() == null ? 1 : admin.getLoginErrorTimes() + 1);
					sysAdminService.save(admin);
				}
				// 密码错误次数达到5时:
				// 1.禁止登录一天
				// 2.邮件通知超级管理员admin
				// 3.将当前ip加入黑名单
				if (admin.getLoginErrorTimes() != null && admin.getLoginErrorTimes() == 5) {
					Calendar c = Calendar.getInstance();
					c.setTime(new Date());
					c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
					admin.setForbidLoginTime(c.getTime());
					sysAdminService.save(admin);

					String[] emailAddresses = new String[1];
					emailAddresses[0] = admin.getEmail();
					// 这里发送邮件熊峰补充
					// mailEngine.sendMessage(emailAddresses,
					// "资产管理平台<service@hhr360.com>", "后台用户:" + admin.getName() +
					// "连续登录错误达到5次,禁止登录1天,解禁时间:" +
					// admin.getForbidLoginTime().toLocaleString(),
					// "后台用户禁止登录通知", null, null);

					SysIpBlacklist ipBlack = sysIpBlacklistService.findBlackByRegIp(ipStr);
					// 未找到ip地址对应的表记录,不创建新记录,避免内部人员误操作封住自身ip
					if (ipBlack != null) {
						ipBlack.setIp(ipStr);
						ipBlack.setIsBlack(1);
						ipBlack.setUpdateAdmin(admin);
						ipBlack.setUpdateTime(new Date());
						sysIpBlacklistService.save(ipBlack);
					}
					log.info("您的IP地址: " + ipStr + " 密码连续错误5次,禁止登录1天");
					write(response, "-8");
					return;
				}
				write(response, "-5");
				return;
			}
			// 正常登录
			String token = UUID.randomUUID().toString();
			WebUtil.addCookie(response, "admin_token", token, 8640000);
			admin.setLoginToken(token);
			admin.setUpdateLoginTime(new Date());
			// 设置登录ip
			admin.setLoginIp(ipStr);
			// 正常登录将登录错误次数清零
			admin.setLoginErrorTimes(0);
			if (isAuto != null && isAuto) {
				admin.setIsAuto(isAuto);
			} // 记住账号
			if (isRemain != null && isRemain) {
				WebUtil.addCookie(response, "remain_token", admin.getAccount(), 30 * 24 * 60 * 60);// 记住账号
			} else {
				WebUtil.addCookie(response, "remain_token", "", 30 * 24 * 60 * 60);
			}
			// 存储用户的基本信息
			session.setAttribute("admin", admin);
			// 存储用户对应的角色权限
			List<SysRole> roleList = sysRoleService.findRoleListByRoleId(admin.getId());
			List<SysPurview> priviList = null;
			if (roleList.size() > 0) {
				for (SysRole role : roleList) {
					priviList = sysRolePurviewService.findPurviewListByRoleId(role.getId());
				}
			}
			session.setAttribute("priviList", priviList);
			sysAdminService.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home", produces = "text/html;charset=UTF-8")
	public String home(HttpServletRequest request, ModelMap map, SysAdmin admin) {
		try {
			admin = (SysAdmin) request.getSession().getAttribute("admin");
			if (null != admin) {
				List<SysRole> roles = sysRoleService.findRoleListByRoleId(admin.getId());
				String roleName = "";
				if (roles != null && roles.size() > 0) {
					for (SysRole sysRole : roles) {
						roleName = roleName + sysRole.getRoleName() + "，";// 将该管理员的所有角色拼接起来用逗号分隔
					}
					roleName = roleName.substring(0, roleName.length() - 1);
				}
				map.put("roleName", roleName);

				// 加载所有权限
				List<String> allPrivilegeUrls = sysPurviewService.getAllPrivilegeUrls();
				map.put("allPrivilegeUrls", allPrivilegeUrls);

				// 加载顶级菜单
				List<SysPurview> topPrivilegeList = sysPurviewService.findTopPrivilege();
				map.put("topPrivilegeList", topPrivilegeList);
				return "adminLogin/home";
			} else {
				return "redirect:/web/admin/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/admin/login";
	}

	/**
	 * 退出登录
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", produces = "text/html;charset=UTF-8")
	public void logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String token = WebUtil.getCookieByName(request, "admin_token");
			if (null != token && !"".equals(token)) {
				SysAdmin admin = sysAdminService.findLoginByToken(token);
				if (admin != null) {
					admin.setLoginToken("");
					sysAdminService.save(admin);
					write(response, "-1");// 退出成功
				} else {
					write(response, "-2");// 请先登录
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改管理员密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editAdminPwd", produces = "text/html;charset=UTF-8")
	public String editAdminPwd() {
		return "adminLogin/editAdminPwd";
	}

	/**
	 * 保存密码修改
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveAdminPwd", produces = "text/html;charset=UTF-8")
	public void saveAdminPwd(HttpServletRequest request, HttpServletResponse response, HttpSession session, String oldPwd, String rePwd, String newPwd) {
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			if (StringUtil.isBlank(oldPwd)) {
				write(response, "-2");// 请输入旧密码
				return;
			}
			if (StringUtil.isBlank(rePwd)) {
				write(response, "-4");// 请输入确认密码
				return;
			}
			if (!newPwd.equals(rePwd)) {
				write(response, "-5");// 新密码和确认密码不一致
				return;
			}
			if (!admin.getPassword().equals(CommonUtils.getMd5(oldPwd))) {
				write(response, "-6");// 旧密码输入错误
				return;
			}
			admin.setPassword(CommonUtils.getMd5(newPwd));
			sysAdminService.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}