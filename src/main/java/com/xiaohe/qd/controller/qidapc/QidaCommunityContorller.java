package com.xiaohe.qd.controller.qidapc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaStockQuestion;
import com.xiaohe.qd.model.QidaTagQuestion;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaTagsService;
import com.xiaohe.qd.util.DateUtil;

/**
 * 奇答社区（我）
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qida")
@Scope("prototype")
public class QidaCommunityContorller extends BaseController {
	@Resource
	private QidaAnswerService qidaAnswerService;
	@Resource
	private QidaTagsService qidaTagsService;
	@Resource
	private FuUserService fuUserService;

	/**
	 * 奇答社区
	 * 
	 * @return
	 */
	@RequestMapping(value = "/community.html", produces = "text/html;charset=UTF-8")
	public String community(ModelMap model) {
		try {
			List<QidaTags> qidaTags = qidaTagsService.findList(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			List<Object[]> jcwd = qidaAnswerService.findJcwd();// 精彩问答
			model.put("jcwd", jcwd);
			model.put("qidaTags", qidaTags);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isDelete", 0);
			map.put("state", 1);
			List<QidaAnswer> answers = qidaAnswerService.findList(0, 6, map);
			model.put("answers", answers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/qidaHome/community";
	}

	/**
	 * 专家排行
	 * 
	 * @return
	 */
	@RequestMapping(value = "/expertData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String expertData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qidaRank", 1);
			map.put("qidaIntegral", "积分");
			List<FuUser> users = fuUserService.findList(0, Integer.MAX_VALUE, map);
			JSONArray array = new JSONArray();
			if (users != null && users.size() > 0) {
				for (FuUser fuUser : users) {
					JSONObject obj = new JSONObject();
					obj.put("userId", fuUser.getId());
					obj.put("userAvatar", fuUser.getUserAvatar() == null ? request.getContextPath() + "/web/images_qiDa/tx.png" : fuUser.getUserAvatar());
					obj.put("nickName", fuUser.getNickName() == null ? "佚名" : fuUser.getNickName());
					obj.put("qidaIntegral", fuUser.getQidaIntegral());
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
	 * 全部动态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dynamicData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String dynamicData() {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isDelete", 0);
			map.put("state", 1);
			List<QidaAnswer> answers = qidaAnswerService.findList(0, Integer.MAX_VALUE, map);
			JSONArray array = new JSONArray();
			if (answers != null && answers.size() > 0) {
				for (QidaAnswer answer : answers) {
					JSONObject obj = new JSONObject();
					obj.put("id", answer.getId());
					obj.put("userId", answer.getFuUser().getId());
					obj.put("userAvatar", answer.getFuUser().getUserAvatar() == null ? "../images_qiDa/tx.png" : answer.getFuUser().getUserAvatar());
					obj.put("nickName", answer.getFuUser().getNickName() == null ? "佚名" : answer.getFuUser().getNickName());
					obj.put("type", answer.getType() == 0 ? "回答" : "评论");
					obj.put("replyNum", answer.getQidaQuestion().getReplyNum());
					obj.put("questionId", answer.getQidaQuestion().getId());
					obj.put("title", answer.getQidaQuestion().getTitle());
					obj.put("reward", answer.getQidaQuestion().getReward() == null ? "0" : answer.getQidaQuestion().getReward());
					obj.put("content", answer.getContent());
					Set<QidaTagQuestion> tagQuestions = answer.getQidaQuestion().getTagQuestions();
					JSONObject json2 = new JSONObject();
					JSONArray array2 = new JSONArray();
					for (QidaTagQuestion tagQuestion : tagQuestions) {
						JSONObject obj2 = new JSONObject();
						obj2.put("tagId", tagQuestion.getQidaTags().getId());
						obj2.put("tagName", tagQuestion.getQidaTags().getName());
						array2.add(obj2);
					}
					json2.put("array2", array2);// 标签Json集合
					obj.put("tagQuestions", array2);

					JSONObject json3 = new JSONObject();
					JSONArray array3 = new JSONArray();
					Set<QidaStockQuestion> stockQuestions = answer.getQidaQuestion().getStockQuestions();
					for (QidaStockQuestion stockQuestion : stockQuestions) {
						JSONObject obj3 = new JSONObject();
						obj3.put("stockId", stockQuestion.getStockInfo().getId());
						obj3.put("stockName", stockQuestion.getStockInfo().getName());
						array3.add(obj3);
					}
					json3.put("array3", array3);// 股票Json集合
					obj.put("stockQuestions", array3);
					obj.put("replyNum", answer.getQidaQuestion().getReplyNum() == null ? "0" : answer.getQidaQuestion().getReplyNum());
					obj.put("pageViews", answer.getQidaQuestion().getPageViews());
					long nowDate = new Date().getTime();
					long answerDate = answer.getCreateTime().getTime();
					int hhDiffer = (int) ((nowDate - answerDate) / 1000 / 60 / 60);
					obj.put("hhDiffer", hhDiffer);
					int mmDiffer = (int) ((nowDate - answerDate) / 1000 / 60);
					obj.put("mmDiffer", mmDiffer);
					obj.put("createTime", DateUtil.getStrFromDate(answer.getCreateTime(), "yyyy/MM/dd HH:mm:ss"));
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
	 * 我的圈子（查询我关注的用户的动态）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myGroupData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myGroupData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("concernUserId", fuUser.getId());// 关注者为自己
				map.put("isDelete", 0);
				map.put("state", 1);
				List<QidaAnswer> answers = qidaAnswerService.findList(0, Integer.MAX_VALUE, map);
				JSONArray array = new JSONArray();
				if (answers != null && answers.size() > 0) {
					for (QidaAnswer answer : answers) {
						JSONObject obj = new JSONObject();
						obj.put("id", answer.getId());
						obj.put("userId", answer.getFuUser().getId());
						obj.put("userAvatar", answer.getFuUser().getUserAvatar() == null ? "../images_qiDa/tx.png" : answer.getFuUser().getUserAvatar());
						obj.put("nickName", answer.getFuUser().getNickName() == null ? "佚名" : answer.getFuUser().getNickName());
						obj.put("type", answer.getType() == 0 ? "回答" : "评论");
						obj.put("replyNum", answer.getQidaQuestion().getReplyNum());
						obj.put("questionId", answer.getQidaQuestion().getId());
						obj.put("title", answer.getQidaQuestion().getTitle());
						obj.put("reward", answer.getQidaQuestion().getReward() == null ? "0" : answer.getQidaQuestion().getReward());
						obj.put("content", answer.getContent());
						Set<QidaTagQuestion> tagQuestions = answer.getQidaQuestion().getTagQuestions();
						JSONObject json2 = new JSONObject();
						JSONArray array2 = new JSONArray();
						for (QidaTagQuestion tagQuestion : tagQuestions) {
							JSONObject obj2 = new JSONObject();
							obj2.put("tagId", tagQuestion.getQidaTags().getId());
							obj2.put("tagName", tagQuestion.getQidaTags().getName());
							array2.add(obj2);
						}
						json2.put("array2", array2);// 标签Json集合
						obj.put("tagQuestions", array2);
						obj.put("replyNum", answer.getQidaQuestion().getReplyNum() == null ? "0" : answer.getQidaQuestion().getReplyNum());
						obj.put("pageViews", answer.getQidaQuestion().getPageViews());
						long nowDate = new Date().getTime();
						long answerDate = answer.getCreateTime().getTime();
						int timeInterval = (int) ((nowDate - answerDate) / 1000 / 60 / 60);
						obj.put("timeInterval", timeInterval);
						obj.put("createTime", DateUtil.getStrFromDate(answer.getCreateTime(), "yyyy/MM/dd HH:mm:ss"));
						array.add(obj);
					}
					json.put("array", array);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
