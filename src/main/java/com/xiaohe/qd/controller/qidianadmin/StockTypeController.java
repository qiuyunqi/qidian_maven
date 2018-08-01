package com.xiaohe.qd.controller.qidianadmin;

import java.math.BigDecimal;
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
import com.xiaohe.qd.model.StockType;
import com.xiaohe.qd.service.StockTypeService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class StockTypeController extends BaseController {
	@Resource
	private StockTypeService stockTypeService;

	/**
	 * 股票类型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockType", produces = "text/html;charset=UTF-8")
	public String stockType() {
		return "stockManage/stockType";
	}

	/**
	 * 股票类型数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockTypeData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stockTypeData(Integer page, Integer rows, String name, String liftRate) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (!StringUtil.isBlank(liftRate)) {
				map.put("liftRate", liftRate);
			}
			Integer total = stockTypeService.getCount(map);
			List<StockType> list = stockTypeService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockType stockType : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", stockType.getId());
					jsonObject.put("pid", stockType.getPid());
					jsonObject.put("name", stockType.getName());
					jsonObject.put("liftRate", stockType.getLiftRate());
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
	 * 保存股票类型
	 * 
	 */
	@RequestMapping(value = "/addStcokType", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addStcokType(Long id, Long pid, String name, BigDecimal liftRate) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			StockType stockType = new StockType();
			if (id != null) {// 修改子菜单
				stockType = stockTypeService.get(id);
			}
			stockType.setPid(pid);
			stockType.setName(name);
			stockType.setLiftRate(liftRate);
			stockTypeService.save(stockType);

			result.put("success", true);
			result.put("msg", "保存类型成功！");
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
	 * 删除类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStockType", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delStockType(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			stockTypeService.delete(id);
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
