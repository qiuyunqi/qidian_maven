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
import com.xiaohe.qd.model.QidaScore;
import com.xiaohe.qd.service.QidaScoreService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class ScoreController extends BaseController {
	@Resource
	private QidaScoreService qidaScoreService;

	/**
	 * 积分列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/scoreList", produces = "text/html;charset=UTF-8")
	public String answerList() {
		return "qida/qidaManage/scoreList";
	}

	/**
	 * 积分数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/scoreData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String scoreData(String nickName, String comment, Integer isIncome, Integer page, Integer rows, String sort, String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", comment);
			}
			if (isIncome != null) {
				map.put("isIncome", isIncome);
			}
			if (!StringUtil.isBlank(sort)) {
				map.put("sort", sort);
			}
			if (!StringUtil.isBlank(order)) {
				map.put("order", order);
			}
			Integer total = qidaScoreService.getCount(map);
			List<QidaScore> list = qidaScoreService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaScore score : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", score.getId());
					jsonObject.put("nickName", score.getFuUser().getNickName() == null ? "佚名" : score.getFuUser().getNickName());
					jsonObject.put("score", score.getScore());
					jsonObject.put("money", score.getMoney());
					jsonObject.put("totalScore", score.getTotalScore());
					jsonObject.put("orderNum", score.getOrderNum());
					jsonObject.put("comment", score.getComment());
					jsonObject.put("isIncome", score.getIsIncome() == 1 ? "收入" : "支出");
					jsonObject.put("createTime", score.getCreateTime() == null ? "" : DateUtil.getStrFromDate(score.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 删除积分明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delScore", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delScore(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			qidaScoreService.delete(id);
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
