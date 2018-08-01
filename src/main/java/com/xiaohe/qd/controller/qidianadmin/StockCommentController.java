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
import com.xiaohe.qd.model.StockComment;
import com.xiaohe.qd.service.StockCommentService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class StockCommentController extends BaseController {
	@Resource
	private StockCommentService stockCommentService;
	@Resource
	private StockInfoService stockInfoService;

	/**
	 * 股票评论列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockComment", produces = "text/html;charset=UTF-8")
	public String stockComment() {
		return "stockManage/stockComment";
	}

	/**
	 * 股票评论数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stockCommentData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stockCommentData(String name, Integer type, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (type != null) {
				map.put("type", type);
			}
			Integer total = stockCommentService.getCount(map);
			List<StockComment> list = stockCommentService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (StockComment comment : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", comment.getId());
					jsonObject.put("stockInfoId", comment.getStockInfo().getId());
					jsonObject.put("name", comment.getName());
					jsonObject.put("createTime", DateUtil.getStrFromDate(comment.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("content", comment.getContent());
					jsonObject.put("type", comment.getType() == 0 ? "好评" : comment.getType() == 1 ? "中评" : "差评");
					jsonObject.put("likeNum", comment.getLikeNum());
					jsonObject.put("noLikeNum", comment.getNoLikeNum());
					jsonObject.put("realLikeNum", comment.getRealLikeNum());
					jsonObject.put("realNoLikeNum", comment.getRealNoLikeNum());
					jsonObject.put("wbUrl", comment.getWbUrl());
					jsonObject.put("wbMid", comment.getWbMid());
					jsonObject.put("wbReposts", comment.getWbReposts());
					jsonObject.put("wbComments", comment.getWbComments());
					jsonObject.put("wbAttitudes", comment.getWbAttitudes());
					jsonObject.put("wbHdl", comment.getWbHdl());
					jsonObject.put("wbUid", comment.getWbUid());
					jsonObject.put("wbFensi", comment.getWbFensi());
					jsonObject.put("wbWibo", comment.getWbWibo());
					jsonObject.put("wbGuanzhu", comment.getWbGuanzhu());
					jsonObject.put("wbvtype", comment.getWbvtype());
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
	 * 保存股票评论
	 * 
	 */
	@RequestMapping(value = "/addStcokComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addStcokComment(Long id, Long stockInfoId, String name, String createTime, String content, Integer type, Integer likeNum, Integer noLikeNum, Integer realLikeNum, Integer realNoLikeNum, String wbUrl, String wbMid, Integer wbReposts, Integer wbComments, Integer wbAttitudes, Integer wbHdl, String wbUid, Integer wbFensi, Integer wbWibo, Integer wbGuanzhu, String wbvtype) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			StockComment comment = new StockComment();
			if (id != null) {// 修改子菜单
				comment = stockCommentService.get(id);
			}
			comment.setStockInfo(stockInfoService.get(stockInfoId));
			comment.setName(name);
			comment.setCreateTime(DateUtil.getDateFromString(createTime, "yyyy-MM-dd HH:mm:ss"));
			comment.setContent(content);
			comment.setType(type);
			comment.setLikeNum(likeNum);
			comment.setNoLikeNum(noLikeNum);
			comment.setRealLikeNum(realLikeNum);
			comment.setRealNoLikeNum(realNoLikeNum);
			comment.setWbUrl(wbUrl);
			comment.setWbMid(wbMid);
			comment.setWbReposts(wbReposts);
			comment.setWbComments(wbComments);
			comment.setWbAttitudes(wbAttitudes);
			comment.setWbHdl(wbHdl);
			comment.setWbUid(wbUid);
			comment.setWbFensi(wbFensi);
			comment.setWbWibo(wbWibo);
			comment.setWbGuanzhu(wbGuanzhu);
			comment.setWbvtype(wbvtype);
			stockCommentService.save(comment);

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
	 * 删除评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStockComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delStockComment(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			stockCommentService.delete(id);
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
