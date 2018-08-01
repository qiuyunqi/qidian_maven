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
import com.xiaohe.qd.model.SysIpBlacklist;
import com.xiaohe.qd.service.SysIpBlacklistService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysIpBlacklistController extends BaseController {
	@Resource
	private SysIpBlacklistService sysIpBlacklistService;

	/**
	 * 黑名单列表
	 * 
	 * @param model
	 * @param ip
	 * @param isBlack
	 * @param totalCount
	 * @return
	 */
	@RequestMapping(value = "/ipBlackList", produces = "text/html;charset=UTF-8")
	public String ipBlackList() {
		return "adminIpBlack/ipBlackList";
	}

	/**
	 * IP黑名单数据
	 * 
	 * @param ip
	 * @param isBlack
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/ipBlackData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String ipBlackData(String ip, Integer isBlack, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(ip)) {
				map.put("ip", ip);
			}
			if (isBlack != null) {
				map.put("isBlack", isBlack);
			}
			Integer total = sysIpBlacklistService.countIpBlack(map);
			List<SysIpBlacklist> IpBlackList = sysIpBlacklistService.findIpBlackList((page - 1) * rows, rows, map);

			JSONArray jsonArray = new JSONArray();
			if (IpBlackList != null && IpBlackList.size() > 0) {
				for (SysIpBlacklist blacklist : IpBlackList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", blacklist.getId());
					jsonObject.put("ip", blacklist.getIp());
					jsonObject.put("isBlack", blacklist.getIsBlack() == 0 ? "白名单" : "黑名单");
					jsonObject.put("createAdmin", blacklist.getCreatAdmin() == null ? "" : blacklist.getCreatAdmin().getName());
					jsonObject.put("createTime", blacklist.getCreateTime() == null ? "" : DateUtil.getStrFromDate(blacklist.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("updateAdmin", blacklist.getUpdateAdmin() == null ? "" : blacklist.getUpdateAdmin().getName());
					jsonObject.put("updateTime", blacklist.getUpdateTime() == null ? "" : DateUtil.getStrFromDate(blacklist.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 保存黑名单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addIpBlack", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addIpBlack(SysIpBlacklist _ipBlacklist, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			if (_ipBlacklist.getId() != null) {
				SysIpBlacklist ipBlacklist = sysIpBlacklistService.get(_ipBlacklist.getId());
				ipBlacklist.setIp(_ipBlacklist.getIp());
				ipBlacklist.setIsBlack(_ipBlacklist.getIsBlack());
				ipBlacklist.setUpdateAdmin(admin);
				ipBlacklist.setUpdateTime(new Date());
				// 设为白名单时,重置登录错误次数和禁止登录时间
				if (_ipBlacklist.getIsBlack() == 0) {
					admin.setLoginErrorTimes(0);
					admin.setForbidLoginTime(null);
				}
				sysIpBlacklistService.save(ipBlacklist);
			} else {
				SysIpBlacklist ipBlacklist = new SysIpBlacklist();
				ipBlacklist.setIp(_ipBlacklist.getIp());
				ipBlacklist.setIsBlack(_ipBlacklist.getIsBlack());
				ipBlacklist.setCreatAdmin(admin);
				ipBlacklist.setCreateTime(new Date());
				sysIpBlacklistService.save(ipBlacklist);
			}
			result.put("success", true);
			result.put("msg", "保存黑名单成功");
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
	 * 删除黑名单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delIpBlack", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delIpBlack(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			sysIpBlacklistService.delete(id);
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
