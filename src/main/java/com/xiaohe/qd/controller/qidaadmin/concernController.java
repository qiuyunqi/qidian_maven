package com.xiaohe.qd.controller.qidaadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.QidaConcern;
import com.xiaohe.qd.service.QidaConcernService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class concernController extends BaseController {
	@Resource
	private QidaConcernService qidaConcernService;

	/**
	 * 关注列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/concernList", produces = "text/html;charset=UTF-8")
	public String concernList() {
		return "qida/qidaManage/concernList";
	}
	
	/**
	 * 关注数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/concernData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String concernData(String concernUser, String beConcernUser, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(concernUser)) {
				map.put("concernUser", concernUser);
			}
			if (!StringUtil.isBlank(beConcernUser)) {
				map.put("beConcernUser", beConcernUser);
			}
			Integer total = qidaConcernService.getCount(map);
			List<QidaConcern> list = qidaConcernService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaConcern concern : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", concern.getId());
					jsonObject.put("concernUser", concern.getConcernUser().getNickName());
					jsonObject.put("beConcernUser", concern.getBeConcernUser().getNickName());
					jsonObject.put("concernTime", concern.getConcernTime()==null?"":DateUtil.getStrFromDate(concern.getConcernTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 删除关注
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delConcern", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delConcern(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			qidaConcernService.delete(id);
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
