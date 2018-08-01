package com.xiaohe.qd.controller.qidapc;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaConcern;
import com.xiaohe.qd.model.QidaPayQuestion;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaStockQuestion;
import com.xiaohe.qd.model.QidaTagQuestion;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaConcernService;
import com.xiaohe.qd.service.QidaPayQuestionService;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.util.DateUtil;

/**
 * 奇答首页
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qida")
@Scope("prototype")
public class QidaHomeContorller extends BaseController {
	@Resource
	private QidaQuestionService qidaQuestionService;
	@Resource
	private QidaPayQuestionService qidaPayQuestionService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private QidaConcernService qidaConcernService;
	@Resource
	private QidaAnswerService qidaAnswerService;

	/**
	 * 奇答首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index.html", produces = "text/html;charset=UTF-8")
	public String index(ModelMap model, HttpServletRequest request) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (fuUser != null) {
			FuUser fuUser2 = fuUserService.get(fuUser.getId());
			request.getSession().setAttribute("fuUser", fuUser2);
		}
		return "qida/qidaHome/index";
	}

	/**
	 * 首页数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/indexData.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String indexData() {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isDelete", 0);
			List<QidaQuestion> questions = qidaQuestionService.findList(0, Integer.MAX_VALUE, map);
			JSONArray array = new JSONArray();
			if (questions != null && questions.size() > 0) {
				for (QidaQuestion question : questions) {
					JSONObject obj = new JSONObject();
					obj.put("id", question.getId());
					obj.put("userId", question.getFuUser().getId());
					if (question.getFuUser().getQidaIntegral() == null) {
						obj.put("rank", "erro");
					} else if (question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(0)) == 1 && question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(10)) == -1) {
						obj.put("rank", "topFour");// 进士
					} else if (question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(9)) == 1 && question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(30)) == -1) {
						obj.put("rank", "topThree");// 探花
					} else if (question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(29)) == 1 && question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(70)) == -1) {
						obj.put("rank", "topTwo");// 榜眼
					} else if (question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(69)) == 1 && question.getFuUser().getQidaIntegral().compareTo(new BigDecimal(100)) == -1) {
						obj.put("rank", "topOne");// 状元
					} else {
						obj.put("rank", "rank");
					}
					obj.put("userAvatar", question.getFuUser().getUserAvatar() == null ? "../images_qiDa/tx.png" : question.getFuUser().getUserAvatar());
					obj.put("nickName", question.getFuUser().getNickName() == null ? "佚名" : question.getFuUser().getNickName());
					obj.put("title", question.getTitle());
					obj.put("answerUserId", question.getAnswerUser() == null ? 0 : question.getAnswerUser().getId());
					obj.put("answerNickName", question.getAnswerUser() == null ? "佚名" : question.getAnswerUser().getNickName() == null ? "佚名" : question.getAnswerUser().getNickName());
					obj.put("replyNum", question.getReplyNum());
					obj.put("reward", question.getReward());
					obj.put("detail", question.getDetail());
					obj.put("imgUrl", question.getImgUrl());
					Set<QidaTagQuestion> tagQuestions = question.getTagQuestions();
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
					Set<QidaStockQuestion> stockQuestions = question.getStockQuestions();
					for (QidaStockQuestion stockQuestion : stockQuestions) {
						JSONObject obj3 = new JSONObject();
						obj3.put("stockId", stockQuestion.getStockInfo().getId());
						obj3.put("stockName", stockQuestion.getStockInfo().getName());
						array3.add(obj3);
					}
					json3.put("array3", array3);// 股票Json集合
					obj.put("stockQuestions", array3);

					obj.put("replyNum", question.getReplyNum() == null ? "0" : question.getReplyNum());
					obj.put("pageViews", question.getPageViews() == null ? "0" : question.getPageViews());
					long nowDate = new Date().getTime();
					long questionDate = question.getUpdateTime().getTime();
					int hhDiffer = (int) ((nowDate - questionDate) / 1000 / 60 / 60);
					obj.put("hhDiffer", hhDiffer);
					int mmDiffer = (int) ((nowDate - questionDate) / 1000 / 60);
					obj.put("mmDiffer", mmDiffer);
					obj.put("updateTime", DateUtil.getStrFromDate(question.getUpdateTime(), "yyyy/MM/dd HH:mm:ss"));
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
	 * 查看付费问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/payQuestion/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payQuestion(@PathVariable Long id, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				return "-1";// 未登录
			}
			QidaQuestion question = qidaQuestionService.get(id);
			int payQuestionCount = qidaPayQuestionService.findByMySelf(fuUser.getId(), id);
			if (payQuestionCount > 0 || fuUser.getId().equals(question.getFuUser().getId()) || (fuUser.getQidaRank() != null && fuUser.getQidaRank() == 1 && question.getReplyNum() == 0)
					|| (question.getAnswerUser() != null && question.getAnswerUser().getId().equals(fuUser.getId()))) {
				return "1";// 已支付这个问题或者作者自己或者被邀请的专家或者专家在没有答案的时候查看
			}
			if (fuUser.getQidaIntegral() == null || fuUser.getQidaIntegral().compareTo(new BigDecimal(1)) == -1) {
				return "-2";// 积分不足
			}
			fuUser.setQidaIntegral(fuUser.getQidaIntegral().subtract(new BigDecimal(1)));
			fuUserService.save(fuUser);
			// 保存查看付费问题产生的明细
			qidaScoreUtil.saveScoreByQd(fuUser, 48, new BigDecimal(1), BigDecimal.ZERO, fuUser.getQidaIntegral(), 0);

			FuUser createUser = question.getFuUser();
			createUser.setQidaIntegral((createUser.getQidaIntegral() == null ? BigDecimal.ZERO : createUser.getQidaIntegral()).add(new BigDecimal(0.5)));
			fuUserService.save(createUser);
			// 保存查看付费问题分成的明细
			qidaScoreUtil.saveScoreByQd(createUser, 49, new BigDecimal(0.5), BigDecimal.ZERO, createUser.getQidaIntegral(), 1);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", question.getId());
			List<QidaAnswer> list = qidaAnswerService.findList(0, Integer.MAX_VALUE, map);
			if (list != null && list.size() > 0) {
				QidaAnswer answer = list.get(0);
				FuUser answerUser = answer.getFuUser();
				answerUser.setQidaIntegral((answerUser.getQidaIntegral() == null ? BigDecimal.ZERO : answerUser.getQidaIntegral()).add(new BigDecimal(0.5)));
				fuUserService.save(answerUser);
				// 保存查看付费问题分成的明细
				qidaScoreUtil.saveScoreByQd(answerUser, 49, new BigDecimal(0.5), BigDecimal.ZERO, answerUser.getQidaIntegral(), 1);
			}

			// 保存支付问题的记录，以便下次查看
			QidaPayQuestion payQuestion = new QidaPayQuestion();
			payQuestion.setFuUser(fuUser);
			payQuestion.setQidaQuestion(question);
			payQuestion.setCreateTime(new Date());
			qidaPayQuestionService.save(payQuestion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userInfo.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String userInfo(Long id, HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser user = fuUserService.get(id);
			obj.put("userAvatar", user.getUserAvatar());
			obj.put("nickName", user.getNickName() == null ? "佚名" : user.getNickName());
			obj.put("integral", user.getQidaIntegral() == null ? 0.00 : user.getQidaIntegral());
			obj.put("qidaRank", user.getQidaRank() == null ? "会员" : user.getQidaRank() == 0 ? "会员" : "专家");
			List<QidaConcern> concerns = qidaConcernService.findByMySelf(user.getId());// 被我关注的人
			List<QidaConcern> concerns2 = qidaConcernService.findByWatchMe(user.getId());// 关注我的人
			List<QidaAnswer> answers = qidaAnswerService.findByMySelf(user.getId(), 0, Integer.MAX_VALUE);// 我回答的答案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("answerUserId", user.getId());
			map.put("isDelete", 0);
			List<QidaQuestion> questions = qidaQuestionService.findList(0, Integer.MAX_VALUE, map);// 邀请我回答的问题
			obj.put("conNum", concerns.size());
			obj.put("beconNum", concerns2.size());
			obj.put("answerNum", answers.size());
			obj.put("questionNum", questions.size());
			obj.put("introduction", user.getIntroduction() == null ? "什么也没有说.." : user.getIntroduction());
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			int res = qidaConcernService.findIsWatch(fuUser, user);
			obj.put("res", res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
}
