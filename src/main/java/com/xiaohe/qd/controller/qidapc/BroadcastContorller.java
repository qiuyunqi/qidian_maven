package com.xiaohe.qd.controller.qidapc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaBroadcast;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.service.QidaBroadcastService;
import com.xiaohe.qd.service.QidaTagsService;
import com.xiaohe.qd.util.DateUtil;

/**
 * 奇答首页
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qida")
@Scope("prototype")
public class BroadcastContorller extends BaseController {
	@Resource
	private QidaBroadcastService qidaBroadcastService;
	@Resource
	private QidaTagsService qidaTagsService;

	
	/**
	 * 播报主页
	 * @return
	 */
	@RequestMapping(value = "/broadcast.html", produces = "text/html;charset=UTF-8")
	public String broadcast(ModelMap model, String createDay, Integer pageNo, HttpServletRequest request){
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			List<QidaTags> qidaTags = qidaTagsService.findList(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			model.put("qidaTags", qidaTags);
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtil.isBlank(createDay)){
				map.put("createDay", createDay);
			}
			Integer totalCount = qidaBroadcastService.getCount(map);
			List<QidaBroadcast> broadcasts = qidaBroadcastService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map);
			model.put("broadcasts", broadcasts);
			model.put("createDay", createDay);
			model.put("totalCount", totalCount);
			model.put("pageNo", pageNo);
			model.put("pageSize", this.getPageSize());
			
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser != null) {
				model.put("isVoice", fuUser.getIsVoice());
			}else{
				model.put("isVoice", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/qidaHome/broadcast";
	}
	
	/**
	 * 播报数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/broadData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String broadData() {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<QidaBroadcast> broadcasts = qidaBroadcastService.findList(0, Integer.MAX_VALUE, map);
			JSONArray array = new JSONArray();
			if (broadcasts != null && broadcasts.size() > 0) {
				for (QidaBroadcast broadcast : broadcasts) {
					JSONObject obj = new JSONObject();
					obj.put("id", broadcast.getId());
					obj.put("content", broadcast.getContent());
					obj.put("createTime", DateUtil.getStrFromDate(broadcast.getCreateTime(), "HH:mm"));
					array.add(obj);
				}
				json.put("array", array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}
