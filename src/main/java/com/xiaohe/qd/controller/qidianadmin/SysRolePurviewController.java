package com.xiaohe.qd.controller.qidianadmin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.service.SysRolePurviewService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysRolePurviewController extends BaseController {
	@Resource
	private SysRolePurviewService sysRolePurviewService;

	/**
	 * 权限树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/treedata", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String treedata(Long roleId) {
		String jsonStr = "";
		try {
			jsonStr = sysRolePurviewService.findPurviewList(roleId);
			if (jsonStr == null) {
				jsonStr = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 保存角色的菜单权限
	 */
	@RequestMapping(value = "/saveRolePurview", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveRolePurview(Long roleId, String purviewArray) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			sysRolePurviewService.saveRolePurview(roleId, purviewArray);
			result.put("success", true);
			result.put("msg", "权限设置成功！");
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
