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
import com.xiaohe.qd.model.GuessRecord;
import com.xiaohe.qd.service.GuessRecordService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class GuessRecordController extends BaseController {
	@Resource
	private GuessRecordService guessRecordService;

	/**
	 * 竞猜列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/guessRecordList", produces = "text/html;charset=UTF-8")
	public String guessRecordList() {
		return "qida/qidaManage/guessRecordList";
	}

	/**
	 * 竞猜数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/guessRecordData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String guessRecordData(String userName, String marketName, Integer guessType, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (!StringUtil.isBlank(marketName)) {
				map.put("marketName", marketName);
			}
			if (guessType != null) {
				map.put("guessType", guessType);
			}
			Integer total = guessRecordService.getCount(map);
			List<GuessRecord> list = guessRecordService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (GuessRecord record : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", record.getId());
					jsonObject.put("userName", record.getFuUser().getUserName());
					jsonObject.put("tradeNo", record.getTradeNo());
					jsonObject.put("score", record.getScore());
					jsonObject.put("orderIncome", record.getOrderIncome());
					jsonObject.put("guessType", record.getGuessType() == 1 ? "涨（奇数）" : record.getGuessType() == 2 ? "涨（偶数）" : record.getGuessType() == 3 ? "跌（奇数）" : "跌（偶数）");
					jsonObject.put("marketName", record.getGuessMarket().getName());
					jsonObject.put("guessTime", record.getGuessTime() == null ? "" : DateUtil.getStrFromDate(record.getGuessTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 修改竞猜
	 * 
	 */
	@RequestMapping(value = "/updateGuessRecord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateGuessRecord(Long id, GuessRecord guessRecord, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			GuessRecord record = new GuessRecord();
			if (id != null) {
				record = guessRecordService.get(id);
			}
			record.setGuessType(guessRecord.getGuessType());
			record.setOrderIncome(guessRecord.getOrderIncome());
			record.setScore(guessRecord.getScore());
			record.setTradeNo(guessRecord.getTradeNo());
			guessRecordService.save(record);

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
	 * 删除竞猜记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delGuessRecord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delGuessRecord(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			guessRecordService.delete(id);
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
