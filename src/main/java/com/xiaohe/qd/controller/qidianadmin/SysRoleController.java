package com.xiaohe.qd.controller.qidianadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.model.SysRole;
import com.xiaohe.qd.service.SysRoleService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysRoleController extends BaseController {
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 查询所有的角色
	 */
	@RequestMapping(value = "/allRoleData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String allRoleData() {
		JSONArray jsonArray = new JSONArray();
		try {
			List<SysRole> list = sysRoleService.findRoleListByMap(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			if (list != null && list.size() > 0) {
				for (SysRole sysRole : list) {
					JSONObject obj = new JSONObject();
					obj.put("roleId", sysRole.getId());
					obj.put("roleName", sysRole.getRoleName());
					jsonArray.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(jsonArray);
	}

	/**
	 * 角色列表
	 */
	@RequestMapping(value = "/roleList", produces = "text/html;charset=UTF-8")
	public String roleList() {
		return "adminPurview/roleList";
	}

	/**
	 * 根据当前后台用户查询他下面的所有角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/roleData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String roleData(HttpSession session, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			map.put("adminId", admin.getId());
			Integer total = sysRoleService.countRole(map);
			List<SysRole> roleList = sysRoleService.findRoleListByMap((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (roleList != null && roleList.size() > 0) {
				for (SysRole role : roleList) {
					JSONObject obj = new JSONObject();
					obj.put("id", role.getId());
					obj.put("roleName", role.getRoleName());
					obj.put("roleCode", role.getRoleCode());
					obj.put("roleDesc", role.getRoleDesc());
					obj.put("createAdmin", role.getCreateAdmin() == null ? "" : role.getCreateAdmin().getName());
					obj.put("createTime", role.getCreateTime() == null ? "" : DateUtil.getStrFromDate(role.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					obj.put("updateAdmin", role.getUpdateAdmin() == null ? "" : role.getUpdateAdmin().getName());
					obj.put("updateTime", role.getUpdateTime() == null ? "" : DateUtil.getStrFromDate(role.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonArray.add(obj);
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
	 * 添加或编辑角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addRole", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRole(SysRole _sysRole, HttpSession session) {
		Map<String, Object> result = new JSONObject();
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			if (StringUtil.isBlank(_sysRole.getRoleName())) {
				result.put("success", false);
				result.put("msg", "角色名称不能为空");
				return JSONObject.toJSONString(result);
			}
			if (_sysRole.getParentId() == null) {
				result.put("success", false);
				result.put("msg", "上级角色不能为空");
				return JSONObject.toJSONString(result);
			}
			SysRole role = new SysRole();
			if (_sysRole.getId() != null) {
				role = sysRoleService.get(_sysRole.getId());
			}
			role.setRoleCode(_sysRole.getRoleCode());
			role.setRoleName(_sysRole.getRoleName());
			role.setRoleDesc(_sysRole.getRoleDesc());
			role.setParentId(_sysRole.getParentId());
			role.setCreateAdmin(admin);
			role.setCreateTime(new Date());
			sysRoleService.save(role);

			result.put("success", true);
			result.put("msg", "保存角色成功！");
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
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delRole", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delRole(Long id) {
		Map<String, Object> result = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", id);
			List<SysRole> roles = sysRoleService.findRoleListByMap(0, Integer.MAX_VALUE, map);// 查询当前角色是否有下级角色
			if (roles != null && roles.size() > 0) {
				result.put("success", false);
				result.put("msg", "当前角色有下级角色，请先删除下级角色！");
				return JSONObject.toJSONString(result);
			}

			sysRoleService.deleteRole(id);
			result.put("success", true);
			result.put("msg", "删除成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

}
