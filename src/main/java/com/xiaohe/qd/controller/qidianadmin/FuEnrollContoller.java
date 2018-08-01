package com.xiaohe.qd.controller.qidianadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuEnroll;
import com.xiaohe.qd.service.FuEnrollService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class FuEnrollContoller extends BaseController {

	@Resource
	private FuEnrollService fuEnrollService;
	
	@RequestMapping(value = "/toEnroll.html", produces = "text/html;charset=UTF-8")
	public String toEnroll() {
		return "adminEnroll/enroll";
	}
	
	@RequestMapping(value = "/toEnrollData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String toEnrollData(Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		int total = fuEnrollService.getCount();
		List<FuEnroll> enrollList = fuEnrollService.findByList((page-1)*rows, rows);
		JSONArray jsonArray = new JSONArray();
		for (FuEnroll fuEnroll : enrollList) {
			JSONObject obj = new JSONObject();
			obj.put("id", fuEnroll.getId());
			obj.put("userName", fuEnroll.getUserName());
			obj.put("phone", fuEnroll.getPhone());
			obj.put("createTime", DateUtil.showDateTime(fuEnroll.getCreateTime()));
			jsonArray.add(obj);
		}
		result.put("total", total);
		result.put("rows", jsonArray);
		return JSONObject.toJSONString(result);
	}
}
