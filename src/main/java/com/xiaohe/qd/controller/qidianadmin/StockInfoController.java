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
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.model.StockPraiseList;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.StockPraiseListService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class StockInfoController extends BaseController {
	@Resource
	private StockInfoService stockInfoService;
	@Resource
	private StockPraiseListService stockPraiseListService;

	/**
	 * 股票详情列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockInfo", produces = "text/html;charset=UTF-8")
	public String stockInfo() {
		return "stockManage/stockInfo";
	}

	/**
	 * 股票详情数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockInfoData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stockInfoData(String name, String code, Integer page, Integer rows, String sort, String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (!StringUtil.isBlank(code)) {
				map.put("code", code);
			}
			if (!StringUtil.isBlank(sort)) {
				map.put("sort", sort);
			}
			if (!StringUtil.isBlank(order)) {
				map.put("order", order);
			}
			Integer total = stockInfoService.getCount(map);
			List<StockInfo> list = stockInfoService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockInfo stockInfo : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", stockInfo.getId());
					jsonObject.put("name", stockInfo.getName());
					jsonObject.put("code", stockInfo.getCode());
					jsonObject.put("pyCode", stockInfo.getPyCode());
					jsonObject.put("goodDay", stockInfo.getGoodDay());
					jsonObject.put("goodWeek", stockInfo.getGoodWeek());
					jsonObject.put("watchNum", stockInfo.getWatchNum());
					jsonObject.put("goodCommentNum", stockInfo.getGoodCommentNum());
					jsonObject.put("mediumCommentNum", stockInfo.getMediumCommentNum());
					jsonObject.put("badCommentNum", stockInfo.getBadCommentNum());
					jsonObject.put("commentNum", stockInfo.getCommentNum());
					jsonObject.put("commentNumToday", stockInfo.getCommentNumToday());
					jsonObject.put("stkType", stockInfo.getStkType());
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
	 * 保存股票
	 * 
	 */
	@RequestMapping(value = "/addStcok", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addStcok(StockInfo _stockInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			StockInfo info = new StockInfo();
			if (_stockInfo.getId() != null) {// 修改子菜单
				info = stockInfoService.get(_stockInfo.getId());
			}
			info.setName(_stockInfo.getName());
			info.setCode(_stockInfo.getCode());
			info.setPyCode(_stockInfo.getPyCode());
			info.setGoodDay(_stockInfo.getGoodDay() == null ? "0" : _stockInfo.getGoodDay() == "" ? "0" : _stockInfo.getGoodDay());
			info.setGoodWeek(_stockInfo.getGoodWeek() == null ? "0" : _stockInfo.getGoodDay() == "" ? "0" : _stockInfo.getGoodDay());
			info.setWatchNum(_stockInfo.getWatchNum() == null ? "0" : _stockInfo.getGoodDay() == "" ? "0" : _stockInfo.getGoodDay());
			info.setGoodCommentNum(_stockInfo.getGoodCommentNum() == null ? 0 : _stockInfo.getGoodCommentNum());
			info.setMediumCommentNum(_stockInfo.getMediumCommentNum() == null ? 0 : _stockInfo.getMediumCommentNum());
			info.setBadCommentNum(_stockInfo.getBadCommentNum() == null ? 0 : _stockInfo.getBadCommentNum());
			info.setCommentNum(_stockInfo.getCommentNum() == null ? 0 : _stockInfo.getCommentNum());
			info.setCommentNumToday(_stockInfo.getCommentNumToday() == null ? 0 : _stockInfo.getCommentNumToday());
			info.setStkType(_stockInfo.getStkType());
			stockInfoService.save(info);

			result.put("success", true);
			result.put("msg", "保存股票成功！");
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
	 * 删除菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStock", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delStock(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			stockInfoService.delete(id);
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

	/**
	 * 添加股票排行
	 * 
	 */
	@RequestMapping(value = "/addPraise", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addPraise(Long id, Integer stockSort, Integer isLock) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("stockInfoId", id);
			Integer count = stockPraiseListService.getCount(map);
			if (count >= 1) {
				result.put("success", false);
				result.put("msg", "当前股票已排行！");
				return JSONObject.toJSONString(result);
			}
			StockInfo info = stockInfoService.get(id);
			StockPraiseList praiseList = new StockPraiseList();
			praiseList.setStockInfo(info);
			praiseList.setName(info.getName());
			praiseList.setCode(info.getStkType() + info.getCode());
			praiseList.setGoodNum(info.getGoodCommentNum());
			praiseList.setPyCode(info.getPyCode());
			praiseList.setStockSort(stockSort);
			praiseList.setIsLock(isLock);
			stockPraiseListService.save(praiseList);

			result.put("success", true);
			result.put("msg", "添加成功，请至股票排行查看！");
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
}
