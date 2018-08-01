package com.xiaohe.qd.controller.qidianadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.xiaohe.qd.model.SysPurview;
import com.xiaohe.qd.service.SysPurviewService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysPurviewController extends BaseController {
	@Resource
	private SysPurviewService sysPurviewService;

	/**
	 * 菜单管理
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/purviewList", produces = "text/html;charset=UTF-8")
	public String purviewList() {
		return "adminPurview/purviewList";
	}

	/**
	 * 加载菜单JSON数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/purviewData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String purviewData(ModelMap model, Integer page, Integer rows, String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			Integer count = sysPurviewService.getCount(map);
			List<SysPurview> purviewList = sysPurviewService.findPurviewList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (purviewList != null && purviewList.size() > 0) {
				for (SysPurview sysPurview : purviewList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", sysPurview.getId());
					jsonObject.put("name", sysPurview.getName());
					jsonObject.put("ParentName", sysPurview.getParentId());
					jsonObject.put("url", sysPurview.getUrl());
					jsonObject.put("remark", sysPurview.getRemark());
					if (null != sysPurview.getCreateAdmin()) {
						jsonObject.put("CreatePersonStr", sysPurview.getCreateAdmin().getName());
					} else {
						jsonObject.put("CreatePersonStr", "");
					}
					if (null != sysPurview.getCreateTime() && !"".equals(sysPurview.getCreateTime())) {
						jsonObject.put("CreateTimeStr", DateUtil.getStrFromDate(sysPurview.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						jsonObject.put("CreateTimeStr", "");
					}
					if (null != sysPurview.getUpdateAdmin()) {
						jsonObject.put("UpdatePersonStr", sysPurview.getUpdateAdmin().getName());
					} else {
						jsonObject.put("UpdatePersonStr", "");
					}
					if (null != sysPurview.getUpdateTime() && !"".equals(sysPurview.getUpdateTime())) {
						jsonObject.put("UpdateTimeStr", DateUtil.getStrFromDate(sysPurview.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						jsonObject.put("UpdateTimeStr", "");
					}

					jsonArray.add(jsonObject);
				}
			}
			result.put("total", count);
			result.put("rows", jsonArray);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 保存菜单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param _sysPurview
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPurview", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addPurview(HttpServletRequest request, HttpServletResponse response, HttpSession session, SysPurview _sysPurview) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			if (StringUtil.isBlank(_sysPurview.getName())) {
				result.put("success", false);
				result.put("msg", "名称不能为空！");
				return JSONObject.toJSONString(result);
			}
			if (StringUtil.isBlank(_sysPurview.getUrl())) {
				result.put("success", false);
				result.put("msg", "URL不能为空！");
				return JSONObject.toJSONString(result);
			}
			if (_sysPurview.getId() != null) {// 修改子菜单
				SysPurview sysPurview = sysPurviewService.get(_sysPurview.getId());
				sysPurview.setName(_sysPurview.getName());
				sysPurview.setUrl(_sysPurview.getUrl());
				sysPurview.setRemark(_sysPurview.getRemark());
				sysPurview.setUpdateAdmin(admin);
				sysPurview.setUpdateTime(new Date());
				sysPurviewService.save(sysPurview);

				result.put("success", true);
				result.put("msg", "修改菜单成功！");
				return JSONObject.toJSONString(result);
			}
			if (_sysPurview.getParentId() != null) {// 添加菜单
				SysPurview purview = new SysPurview();
				purview.setParentId(_sysPurview.getParentId());
				purview.setName(_sysPurview.getName());
				purview.setUrl(_sysPurview.getUrl());
				purview.setRemark(_sysPurview.getRemark());
				purview.setCreateAdmin(admin);
				purview.setCreateTime(new Date());
				sysPurviewService.save(purview);

				result.put("success", true);
				result.put("msg", "添加菜单成功！");
				return JSONObject.toJSONString(result);
			}
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
	 * 删除菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delPurview", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delPurview(Long id, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("parentId", id);
			List<SysPurview> list = sysPurviewService.findPurviewList(0, Integer.MAX_VALUE, map);// 查询当前菜单是否有下级菜单
			if (list != null && list.size() > 0) {
				result.put("success", false);
				result.put("msg", "当前菜单有下级菜单，请先删除下级子菜单！");
				return JSONObject.toJSONString(result);
			}
			sysPurviewService.deletePurview(id);

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
