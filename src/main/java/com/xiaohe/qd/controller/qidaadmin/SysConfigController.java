package com.xiaohe.qd.controller.qidaadmin;

import java.math.BigDecimal;
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
import com.xiaohe.qd.model.SysConfig;
import com.xiaohe.qd.service.SysConfigService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SysConfigController extends BaseController {
	@Resource
	private SysConfigService sysConfigService;

	/**
	 * 系统参数列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysConfigList", produces = "text/html;charset=UTF-8")
	public String sysConfigList() {
		return "qida/qidaManage/sysConfigList";
	}

	/**
	 * 系统数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/configData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String configData(String code, String name, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(code)) {
				map.put("code", code);
			}
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			Integer total = sysConfigService.getCount(map);
			List<SysConfig> list = sysConfigService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (SysConfig config : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", config.getId());
					jsonObject.put("code", config.getCode());
					jsonObject.put("name", config.getName());
					jsonObject.put("value", config.getValue());
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
	 * 添加或修改参数
	 * 
	 */
	@RequestMapping(value = "/addConfig", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addConfig(Long id, String code, String name, BigDecimal value, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysConfig config = new SysConfig();
			if (id != null) {
				config = sysConfigService.get(id);
			}
			config.setCode(code);
			config.setName(name);
			config.setValue(value);
			sysConfigService.save(config);

			result.put("success", true);
			result.put("msg", "保存成功！");
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
	 * 删除参数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delConfig", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delConfig(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			sysConfigService.delete(id);
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
