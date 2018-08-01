package com.xiaohe.qd.controller.qidianadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.model.SysUserRole;
import com.xiaohe.qd.service.SysAdminService;
import com.xiaohe.qd.service.SysRoleService;
import com.xiaohe.qd.service.SysUserRoleService;
import com.xiaohe.qd.util.CommonUtils;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysAdminController extends BaseController {
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserRoleService sysUserRoleService;

	/**
	 * 后台账号列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountList", produces = "text/html;charset=UTF-8")
	public String accountList() {
		return "adminManage/accountList";
	}

	/**
	 * 管理员列表数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String accountData(ModelMap model, String account, String name, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(account)) {
				map.put("account", account);
			}
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			Integer total = sysAdminService.getCount(map);
			List<Object[]> adminList = sysAdminService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (adminList != null && adminList.size() > 0) {
				for (Object[] obj : adminList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", obj[0]);
					jsonObject.put("account", obj[1]);
					jsonObject.put("name", obj[2]);
					jsonObject.put("type", obj[3].toString().equals("0") ? "管理员" : "超级管理员");
					jsonObject.put("jobDesc", obj[4]);
					jsonObject.put("createTime", obj[5]);
					jsonObject.put("updateLoginTime", obj[6]);
					jsonObject.put("roleName", obj[7]);
					jsonObject.put("password", obj[8]);
					jsonObject.put("email", obj[9]);
					jsonObject.put("phone", obj[10]);
					jsonArray.add(jsonObject);
				}
			}
			result.put("total", total);
			result.put("rows", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 保存用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addAdmin", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdmin(HttpServletResponse response, Long roleId, SysAdmin admin, String confirmPassword) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysAdmin fuAdmin = new SysAdmin();
			if (admin.getId() != null) {// 修改
				fuAdmin = sysAdminService.get(admin.getId());
			}
			if (admin.getId() == null) {// 新增
				fuAdmin.setState(1);
				fuAdmin.setCreateTime(new Date());
				fuAdmin.setType(0);// 新增时默认是普通管理员
				fuAdmin.setLoginErrorTimes(0);
			}
			if (StringUtil.isBlank(admin.getAccount())) {
				result.put("success", false);
				result.put("msg", "账号不能为空！");
				return JSONObject.toJSONString(result);
			} else if (admin.getId() == null && this.sysAdminService.findAdminByAccount(admin.getAccount()) != null) {
				result.put("success", false);
				result.put("msg", "账号已存在！");
				return JSONObject.toJSONString(result);
			}
			if (StringUtil.isBlank(admin.getAccount())) {
				result.put("success", false);
				result.put("msg", "密码不能为空！");
				return JSONObject.toJSONString(result);
			} else {
				if (admin.getId() == null || (admin.getId() != null && !admin.getPassword().equals(fuAdmin.getPassword()))) {// 修改密码或者新增密码
					if (null == confirmPassword || confirmPassword == "") {
						result.put("success", false);
						result.put("msg", "确认密码不能为空！");
						return JSONObject.toJSONString(result);
					}
					if (!admin.getPassword().equals(confirmPassword)) {
						result.put("success", false);
						result.put("msg", "密码不一致！");
						return JSONObject.toJSONString(result);
					}
					fuAdmin.setPassword(CommonUtils.getMd5(admin.getPassword()));
				} else {// 修改的时候密码未变，那么里面的密码是加密过的
					fuAdmin.setPassword(admin.getPassword());
				}
			}
			if (StringUtil.isBlank(admin.getName())) {
				result.put("success", false);
				result.put("msg", "姓名不能为空！");
				return JSONObject.toJSONString(result);
			}
			if (StringUtil.isBlank(admin.getJobDesc())) {
				result.put("success", false);
				result.put("msg", "职位不能为空！");
				return JSONObject.toJSONString(result);
			}
			if (StringUtil.isBlank(admin.getEmail())) {
				result.put("success", false);
				result.put("msg", "邮箱不能为空！");
				return JSONObject.toJSONString(result);
			}
			if (StringUtil.isBlank(admin.getPhone())) {
				result.put("success", false);
				result.put("msg", "手机号不能为空！");
				return JSONObject.toJSONString(result);
			}
			fuAdmin.setAccount(admin.getAccount());
			fuAdmin.setName(admin.getName());
			fuAdmin.setJobDesc(admin.getJobDesc());
			fuAdmin.setEmail(admin.getEmail());
			fuAdmin.setPhone(admin.getPhone());
			sysAdminService.save(fuAdmin);
			if (admin.getId() == null) {
				SysUserRole userRole = new SysUserRole();
				userRole.setSysRole(sysRoleService.get(roleId));
				userRole.setSysAdmin(fuAdmin);
				sysUserRoleService.save(userRole);
			} else {
				List<SysUserRole> userRoles = sysUserRoleService.findUserRole(admin.getId());// 根据管理员id查询用户角色中间表
				if (null != userRoles && userRoles.size() > 0) {
					SysUserRole userRole = userRoles.get(0);
					userRole.setSysRole(sysRoleService.get(roleId));
					sysUserRoleService.save(userRole);
				} else {
					SysUserRole userRole = new SysUserRole();
					userRole.setSysAdmin(fuAdmin);
					userRole.setSysRole(sysRoleService.get(roleId));
					sysUserRoleService.save(userRole);
				}
			}
			result.put("success", true);
			result.put("msg", "管理员保存成功！");
			return JSONObject.toJSONString(result);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delAdmin", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delAdmin(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			sysAdminService.delete(id);
			result.put("success", true);
			result.put("msg", "删除管理员成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 设为管理员
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setAdmin", produces = "text/html;charset=UTF-8")
	public void setAdmin(Long id, Integer type) {
		try {
			if (id != null) {
				SysAdmin fuAdmin = sysAdminService.get(id);
				fuAdmin.setType(type);
				sysAdminService.save(fuAdmin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
