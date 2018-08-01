package com.xiaohe.qd.controller.qidianadmin;

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
import com.xiaohe.qd.model.StockRecentOptional;
import com.xiaohe.qd.service.StockRecentOptionalService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class StockRecentOptionalController extends BaseController {
	@Resource
	private StockRecentOptionalService stockRecentOptionalService;

	/**
	 * 自选股票列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockRecentOptional", produces = "text/html;charset=UTF-8")
	public String stockRecentOptional() {
		return "stockManage/stockRecentOptional";
	}

	/**
	 * 自选股票数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockOptinalData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stockOptinalData(String stockName, String userName, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(stockName)) {
				map.put("stockName", stockName);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			Integer total = stockRecentOptionalService.getCount(map);
			List<StockRecentOptional> list = stockRecentOptionalService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockRecentOptional recentOptional : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", recentOptional.getId());
					jsonObject.put("stockName", recentOptional.getStockInfo() == null ? "" : recentOptional.getStockInfo().getName());
					jsonObject.put("userName", recentOptional.getFuUser() == null ? "" : recentOptional.getFuUser().getUserName());
					jsonObject.put("createTime", DateUtil.getStrFromDate(recentOptional.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 删除自选股票
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStockOptinal", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delStockOptinal(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			stockRecentOptionalService.delete(id);
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
