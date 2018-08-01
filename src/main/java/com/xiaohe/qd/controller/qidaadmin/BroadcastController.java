package com.xiaohe.qd.controller.qidaadmin;

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
import com.xiaohe.qd.model.QidaBroadcast;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.service.QidaBroadcastService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class BroadcastController extends BaseController {
	@Resource
	private QidaBroadcastService qidaBroadcastService;

	/**
	 * 播报列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/broadcastList", produces = "text/html;charset=UTF-8")
	public String broadcastList() {
		return "qida/qidaManage/broadcastList";
	}
	

	/**
	 * 播报数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/broadcastData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String broadcastData(String content, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			Integer total = qidaBroadcastService.getCount(map);
			List<QidaBroadcast> list = qidaBroadcastService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaBroadcast broadcast : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", broadcast.getId());
					jsonObject.put("content", broadcast.getContent());
					jsonObject.put("createAdmin", broadcast.getSysAdmin().getName());
					jsonObject.put("createTime", broadcast.getCreateTime() == null ? "" : DateUtil.getStrFromDate(broadcast.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 添加或修改播报
	 * 
	 */
	@RequestMapping(value = "/addBroadcast", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBroadcast(Long id,String content,String createTime,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			QidaBroadcast broadcast = new QidaBroadcast();
			if (id != null) {
				broadcast = qidaBroadcastService.get(id);
			}
			broadcast.setCreateDay(DateUtil.getDateFromString(createTime, "yyyy-MM-dd"));
			broadcast.setCreateTime(DateUtil.getDateFromString(createTime, "yyyy-MM-dd HH:mm:ss"));
			broadcast.setSysAdmin(admin);
			broadcast.setContent(content);
			qidaBroadcastService.save(broadcast);

			result.put("success", true);
			result.put("msg", "保存播报成功！");
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
	 * 删除播报
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delBroadcast", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delBroadcast(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			qidaBroadcastService.delete(id);
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
