package com.xiaohe.qd.controller.qidaadmin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaAnswerComment;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaQuestionComment;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaCollectionService;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.service.QidaPayQuestionService;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.service.QidaStockQuestionService;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.service.QidaTagQuestionService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class QuestionController extends BaseController {
	@Resource
	private QidaQuestionService qidaQuestionService;
	@Resource
	private QidaTagQuestionService qidaTagQuestionService;
	@Resource
	private QidaStockQuestionService qidaStockQuestionService;
	@Resource
	private QidaAnswerService qidaAnswerService;
	@Resource
	private QidaCollectionService qidaCollectionService;
	@Resource
	private QidaPayQuestionService qidaPayQuestionService;
	@Resource
	private QidaCommentService qidaCommentService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 问题列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionList", produces = "text/html;charset=UTF-8")
	public String questionList(ModelMap model) {
		return "qida/qidaManage/questionList";
	}

	/**
	 * 问题数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String questionData(String nickName, String title, Integer state, Integer isDelete, Integer page, Integer rows, String sort, String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(title)) {
				map.put("title", title);
			}
			if (state != null) {
				map.put("state", state);
			}
			if (isDelete != null) {
				map.put("isDelete", isDelete);
			}
			if (!StringUtil.isBlank(sort)) {
				map.put("sort", sort);
			}
			if (!StringUtil.isBlank(order)) {
				map.put("order", order);
			}
			Integer total = qidaQuestionService.getCountAfter(map);
			List<QidaQuestion> list = qidaQuestionService.findListAfter((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaQuestion qidaQuestion : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", qidaQuestion.getId());
					jsonObject.put("nickName", qidaQuestion.getFuUser().getNickName() == null ? "佚名" : qidaQuestion.getFuUser().getNickName());
					jsonObject.put("title", qidaQuestion.getTitle());
					jsonObject.put("detail", qidaQuestion.getDetail());
					jsonObject.put("replyNum", qidaQuestion.getReplyNum() == null ? 0 : qidaQuestion.getReplyNum());
					jsonObject.put("pageViews", qidaQuestion.getPageViews() == null ? 0 : qidaQuestion.getPageViews());
					jsonObject.put("isMessage", qidaQuestion.getIsMessage() == 1 ? "是" : "否");
					// jsonObject.put("isReward", qidaQuestion.getIsReward()==1?"是":"否");
					jsonObject.put("isComment", qidaQuestion.getIsComment() == 1 ? "是" : "否");
					jsonObject.put("state", qidaQuestion.getState() == 1 ? "通过" : "待审核");
					jsonObject.put("isDelete", qidaQuestion.getIsDelete() == 1 ? "是" : "否");
					jsonObject.put("reward", qidaQuestion.getReward());
					jsonObject.put("createTime", qidaQuestion.getCreateTime() == null ? "" : DateUtil.getStrFromDate(qidaQuestion.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("updateTime", qidaQuestion.getUpdateTime() == null ? "" : DateUtil.getStrFromDate(qidaQuestion.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 修改问题
	 * 
	 */
	@RequestMapping(value = "/editQuestion", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editQuestion(QidaQuestion question) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaQuestion qidaQuestion = new QidaQuestion();
			if (question.getId() != null) {// 修改子菜单
				qidaQuestion = qidaQuestionService.get(question.getId());
			}
			qidaQuestion.setTitle(question.getTitle() == null ? qidaQuestion.getTitle() : question.getTitle());
			qidaQuestion.setDetail(question.getDetail() == null ? qidaQuestion.getDetail() : question.getDetail());
			qidaQuestion.setReplyNum(question.getReplyNum() == null ? qidaQuestion.getReplyNum() : question.getReplyNum());
			qidaQuestion.setPageViews(question.getPageViews() == null ? qidaQuestion.getPageViews() : question.getPageViews());
			// qidaQuestion.setIsMessage(question.getIsMessage());
			// qidaQuestion.setIsComment(question.getIsComment());
			// qidaQuestion.setIsClose(question.getIsClose());
			// qidaQuestion.setReward(question.getReward());
			qidaQuestion.setUpdateTime(new Date());
			qidaQuestionService.save(qidaQuestion);

			result.put("success", true);
			result.put("msg", "保存问题成功！");
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
	 * 开启问题
	 * 
	 */
	@RequestMapping(value = "/openQuestion", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openQuestion(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaQuestion qidaQuestion = qidaQuestionService.get(Long.valueOf(array[i]));
				if (qidaQuestion.getState() == 0) {
					qidaQuestion.setState(1);
					qidaQuestion.setUpdateTime(new Date());
					qidaQuestionService.save(qidaQuestion);
				}
			}
			result.put("success", true);
			result.put("msg", "开启问题成功！");
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
	 * 关闭问题
	 * 
	 */
	@RequestMapping(value = "/closeQuestion", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeQuestion(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaQuestion qidaQuestion = qidaQuestionService.get(Long.valueOf(array[i]));
				if (qidaQuestion.getState() == 1) {
					qidaQuestion.setState(0);
					qidaQuestion.setUpdateTime(new Date());
					qidaQuestionService.save(qidaQuestion);
				}
			}
			result.put("success", true);
			result.put("msg", "关闭问题成功！");
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
	 * 删除问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delQuestion", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delQuestion(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("questionId", Long.valueOf(array[i]));
				List<QidaAnswer> list1 = qidaAnswerService.findList(0, Integer.MAX_VALUE, map);
				if (list1 != null && list1.size() > 0) {
					result.put("success", false);
					result.put("msg", "问题" + array[i] + "已有回答，请勿删除！");
					return JSONObject.toJSONString(result);
				}
				// List<QidaTagQuestion> list2 = qidaTagQuestionService.findList(0, Integer.MAX_VALUE, map);
				// if (list2 != null && list2.size() > 0) {
				// for (QidaTagQuestion qidaTagQuestion : list2) {
				// qidaTagQuestionService.delete(qidaTagQuestion.getId());
				// }
				// }
				// List<QidaStockQuestion> list3 = qidaStockQuestionService.findList(0, Integer.MAX_VALUE, map);
				// if (list3 != null && list3.size() > 0) {
				// for (QidaStockQuestion qidaStockQuestion : list3) {
				// qidaStockQuestionService.delete(qidaStockQuestion.getId());
				// }
				// }
				// List<QidaCollection> list4 = qidaCollectionService.findList(0, Integer.MAX_VALUE, map);
				// if (list4 != null && list4.size() > 0) {
				// for (QidaCollection qidaCollection : list4) {
				// qidaCollectionService.delete(qidaCollection.getId());
				// }
				// }
				// List<QidaPayQuestion> list5 = qidaPayQuestionService.findList(0, Integer.MAX_VALUE, map);
				// if (list5 != null && list5.size() > 0) {
				// for (QidaPayQuestion qidaPayQuestion : list5) {
				// qidaPayQuestionService.delete(qidaPayQuestion.getId());
				// }
				// }
				List<QidaQuestionComment> list6 = qidaCommentService.findListQuestionComment(0, Integer.MAX_VALUE, map);
				if (list6 != null && list6.size() > 0) {
					for (QidaQuestionComment questionComment : list6) {
						questionComment.setIsDelete(1);
						qidaCommentService.saveQc(questionComment);
					}
				}
				List<QidaAnswerComment> list7 = qidaCommentService.findListAnswerComment(0, Integer.MAX_VALUE, map);
				if (list7 != null && list7.size() > 0) {
					for (QidaAnswerComment answerComment : list7) {
						answerComment.setIsDelete(1);
						qidaCommentService.saveAc(answerComment);
					}
				}
				QidaQuestion question = qidaQuestionService.get(Long.valueOf(array[i]));
				FuUser fuUser = question.getFuUser();
				fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).add(new BigDecimal(question.getReward())));
				fuUserService.save(fuUser);
				// 删除问题返还积分
				qidaScoreUtil.saveScoreByQd(fuUser, 53, new BigDecimal(question.getReward()), BigDecimal.ZERO, fuUser.getQidaIntegral(), 1);

				question.setIsDelete(1);
				qidaQuestionService.save(question);
			}
			result.put("success", true);
			result.put("msg", "删除成功！");
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
	 * 待审核问题列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionAudit", produces = "text/html;charset=UTF-8")
	public String questionAudit(ModelMap model) {
		model.put("questionSwitch", qidaSwitchService.get(1L).getQuestionSwitch());//问题审核开关，0开启，1关闭
		return "qida/qidaManage/questionAudit";
	}

	/**
	 * 待审核问题数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionAuditData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String questionAuditData(String nickName, String title, Integer page, Integer rows, String sort, String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 0);
			map.put("isDelete", 0);
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(title)) {
				map.put("title", title);
			}
			if (!StringUtil.isBlank(sort)) {
				map.put("sort", sort);
			}
			if (!StringUtil.isBlank(order)) {
				map.put("order", order);
			}
			Integer total = qidaQuestionService.getCountAfter(map);
			List<QidaQuestion> list = qidaQuestionService.findListAfter((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaQuestion qidaQuestion : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", qidaQuestion.getId());
					jsonObject.put("nickName", qidaQuestion.getFuUser().getNickName() == null ? "佚名" : qidaQuestion.getFuUser().getNickName());
					jsonObject.put("title", qidaQuestion.getTitle());
					jsonObject.put("detail", qidaQuestion.getDetail());
					jsonObject.put("reward", qidaQuestion.getReward());
					jsonObject.put("createTime", qidaQuestion.getCreateTime() == null ? "" : DateUtil.getStrFromDate(qidaQuestion.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("updateTime", qidaQuestion.getUpdateTime() == null ? "" : DateUtil.getStrFromDate(qidaQuestion.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
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
