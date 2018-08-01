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
import com.xiaohe.qd.model.StockPraiseList;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.StockPraiseListService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class StockPraiseController extends BaseController {
	@Resource
	private StockPraiseListService stockPraiseListService;
	@Resource
	private StockInfoService stockInfoService;

	/**
	 * 股票点赞列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockPraise", produces = "text/html;charset=UTF-8")
	public String stockInfo() {
		return "stockManage/stockPraise";
	}

	/**
	 * 股票点赞数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockPraiseData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stockPraiseData(String name, String code, Integer page, Integer rows, String sort, String order) {
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
			map.put("isDelete", 0);
			Integer total = stockPraiseListService.getCount(map);
			List<StockPraiseList> list = stockPraiseListService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockPraiseList stockPraise : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", stockPraise.getId());
					jsonObject.put("stockInfoId", stockPraise.getStockInfo().getId());
					jsonObject.put("name", stockPraise.getName());
					jsonObject.put("code", stockPraise.getCode());
					jsonObject.put("transactionPrice", stockPraise.getTransactionPrice());
					jsonObject.put("riseAndFall", stockPraise.getRiseAndFall());
					jsonObject.put("goodNum", stockPraise.getGoodNum());
					jsonObject.put("pyCode", stockPraise.getPyCode());
					jsonObject.put("stockSort", stockPraise.getStockSort());
					
					jsonObject.put("isLock", stockPraise.getIsLock() == null ? "" : stockPraise.getIsLock() == 0 ? "解锁" : "锁定");
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
	 * 保存点赞记录
	 * 
	 */
	@RequestMapping(value = "/addStcokPraise", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addStcokPraise(StockPraiseList _stockPraise, Long stockInfoId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			StockPraiseList praiseList = new StockPraiseList();
			if (_stockPraise.getId() != null) {// 修改子菜单
				praiseList = stockPraiseListService.get(_stockPraise.getId());
			}
			praiseList.setName(_stockPraise.getName());
			praiseList.setCode(_stockPraise.getCode());
			praiseList.setTransactionPrice(_stockPraise.getTransactionPrice());
			praiseList.setRiseAndFall(_stockPraise.getRiseAndFall());
			praiseList.setGoodNum(_stockPraise.getGoodNum());
			praiseList.setStockInfo(stockInfoService.get(stockInfoId));
			praiseList.setPyCode(_stockPraise.getPyCode());
			praiseList.setStockSort(_stockPraise.getStockSort());
			praiseList.setIsLock(_stockPraise.getIsLock());
			stockPraiseListService.save(praiseList);

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
	 * 删除点赞记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStockPraise", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delStockPraise(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			stockPraiseListService.delete(id);
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

	@RequestMapping(value = "/historyPraise", produces = "text/html;charset=UTF-8")
	public String historyPraise() {
		return "stockManage/historyPraise";
	}
	
	
	/**
	 * 获取历史股票排行 
	 * @return
	 */
	@RequestMapping(value = "/historyPraiseData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String historyPraiseData(String name, String code, Integer page, Integer rows, String sort, String order) {
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
			map.put("isDelete", 1);
			Integer total = stockPraiseListService.getCount(map);
			List<StockPraiseList> list = stockPraiseListService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockPraiseList stockPraise : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", stockPraise.getId());
					jsonObject.put("stockInfoId", stockPraise.getStockInfo().getId());
					jsonObject.put("name", stockPraise.getName());
					jsonObject.put("code", stockPraise.getCode());
					jsonObject.put("transactionPrice", stockPraise.getTransactionPrice());
					jsonObject.put("riseAndFall", stockPraise.getRiseAndFall());
					jsonObject.put("goodNum", stockPraise.getGoodNum());
					jsonObject.put("pyCode", stockPraise.getPyCode());
					jsonObject.put("stockSort", stockPraise.getStockSort());
					jsonObject.put("createTime", DateUtil.getStrFromDate(stockPraise.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("isLock", stockPraise.getIsLock() == null ? "" : stockPraise.getIsLock() == 0 ? "解锁" : "锁定");
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
	
}
