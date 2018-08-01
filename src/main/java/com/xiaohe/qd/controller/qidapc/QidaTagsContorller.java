package com.xiaohe.qd.controller.qidapc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.model.StockPraiseList;
import com.xiaohe.qd.model.StockType;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaTagsService;
import com.xiaohe.qd.service.StockPraiseListService;
import com.xiaohe.qd.service.StockTypeService;
/**
 * 标签
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qida")
@Scope("prototype")
public class QidaTagsContorller extends BaseController {
	@Resource
	private QidaTagsService qidaTagsService;
	@Resource
	private QidaQuestionService questionService;
	@Resource
	private StockTypeService stockTypeService;
	@Resource
	private StockPraiseListService praiseListService;
	/**
	 * 标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tags.html", produces = "text/html;charset=UTF-8")
	public String tags(ModelMap model) {
		return "qida/qidaHome/tags";
	}

	/**
	 * 标签树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/treeTag.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String treeTag() {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", 0L);
			List<QidaTags> tags = qidaTagsService.findList(0, Integer.MAX_VALUE, map);// 顶级标签
			JSONArray array = new JSONArray();
			if (tags != null && tags.size() > 0) {
				for (QidaTags tag : tags) {
					JSONObject obj = new JSONObject();
					obj.put("tagPid", tag.getId());
					obj.put("tagPname", tag.getName());
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("parentId", tag.getId());
					List<QidaTags> tags2 = qidaTagsService.findList(0, Integer.MAX_VALUE, map2);// 二级标签
					JSONObject json2 = new JSONObject();
					JSONArray array2 = new JSONArray();
					for (QidaTags tag2 : tags2) {
						JSONObject obj2 = new JSONObject();
						obj2.put("tagId", tag2.getId());
						obj2.put("tagName", tag2.getName());
						array2.add(obj2);
					}
					json2.put("array2", array2);// 二级标签Json集合
					obj.put("tagChild", array2);
					array.add(obj);
				}
				json.put("array", array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 进入标签详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tagsQs/{tagId}.html", produces = "text/html;charset=UTF-8")
	public String tagsQs(HttpServletRequest request, @PathVariable Long tagId, Integer pageNo) {
		if (pageNo == null) {
			pageNo = 1;
		}
		List<QidaQuestion> questionlist = questionService.findByTagid((pageNo-1) * this.getPageSize(), this.getPageSize(), tagId);
		Integer totalCount = questionService.getTagCount(tagId);
		request.setAttribute("questionList", questionlist);
		request.setAttribute("tagId", tagId);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", this.getPageSize());
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowDate", new Date());
		return "qida/qidaHome/tagsQs";
	}
	
	/**
	 * 删除标签
	 * @return
	 */
	@RequestMapping(value = "/delete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(Long id) {
		JSONObject obj = new JSONObject();
		if (null == id) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请不要这样做");
			return obj.toString();
		}
		QidaTags qidaTags = qidaTagsService.get(id);
		if (null == qidaTags) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请不要这样做");
			return obj.toString();
		}
		int result = qidaTagsService.delTag(qidaTags);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("message", "删除标签失败, 请联系管理员");
			return obj.toString();
		} else {
			obj.put("success", 1);
			obj.put("message", "删除创建标签成功");
			return obj.toString();
		}
	}
	
	/**
	 * 获取概念模块数据
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/stock.html", produces="text/html;charset=UTF-8")
	public String stock(HttpServletRequest request, Long id) {
		List<StockType> typeList = stockTypeService.getStockInfoByPid(2L);
		List<StockPraiseList> stockList = praiseListService.findList(0, 5, new HashMap<String, Object>());
		request.setAttribute("typeList", typeList);
		request.setAttribute("stockList", stockList);
		return "qida/qidaHome/stock";
	}
	
	/**
	 * 获取某一个板块下的数据
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/stock/{id}.html", produces="text/html;charset=UTF-8")
	public String stockInfo(HttpServletRequest request, @PathVariable Long id) {
		StockType stockType  = null;
		List<StockType> typeList = stockTypeService.getStockInfoById(id);
		List<StockPraiseList> stockList = praiseListService.findList(0, 5, new HashMap<String, Object>());
		if (null != typeList && typeList.size() > 0) {
			stockType = typeList.get(0);
		}
		request.setAttribute("stockType", stockType);
		request.setAttribute("stockList", stockList);
		return "qida/qidaHome/stockInfo";
	}
}
