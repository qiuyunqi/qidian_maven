package com.xiaohe.qd.controller.qidaadmin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaAgreeService;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.service.QidaMessageService;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AnswerController extends BaseController {
	@Resource
	private QidaAgreeService qidaAgreeService;
	@Resource
	private QidaAnswerService qidaAnswerService;
	@Resource
	private QidaMessageService messageService;
	@Resource
	private QidaQuestionService qidaQuestionService;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaCommentService qidaCommentService;
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 回答列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerList", produces = "text/html;charset=UTF-8")
	public String answerList(ModelMap model) {
		return "qida/qidaManage/answerList";
	}

	/**
	 * 回答数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String answerData(String nickName, String title, String content, Integer state, Integer isDelete, Integer page, Integer rows, String sort, String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(title)) {
				map.put("title", title);
			}
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
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
			Integer total = qidaAnswerService.getCount(map);
			List<QidaAnswer> list = qidaAnswerService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaAnswer answer : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", answer.getId());
					jsonObject.put("nickName", answer.getFuUser().getNickName() == null ? "佚名" : answer.getFuUser().getNickName());
					jsonObject.put("title", answer.getQidaQuestion().getTitle());
					jsonObject.put("content", answer.getContent());
					jsonObject.put("realSupportNum", answer.getRealSupportNum() == null ? 0 : answer.getRealSupportNum());
					jsonObject.put("realNoSupportNum", answer.getRealNoSupportNum() == null ? 0 : answer.getRealNoSupportNum());
					jsonObject.put("state", answer.getState() == 1 ? "通过" : "待审核");
					jsonObject.put("isDelete", answer.getIsDelete() == 1 ? "是" : "否");
					jsonObject.put("createTime", answer.getCreateTime() == null ? "" : DateUtil.getStrFromDate(answer.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	 * 修改答案
	 * 
	 */
	@RequestMapping(value = "/editAnswer", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editAnswer(QidaAnswer answer) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaAnswer qidaAnswer = new QidaAnswer();
			if (answer.getId() != null) {
				qidaAnswer = qidaAnswerService.get(answer.getId());
			}
			qidaAnswer.setContent(answer.getContent() == null ? qidaAnswer.getContent() : answer.getContent());
			qidaAnswer.setRealSupportNum(answer.getRealSupportNum() == null ? qidaAnswer.getRealSupportNum() : answer.getRealSupportNum());
			qidaAnswer.setRealNoSupportNum(answer.getRealNoSupportNum() == null ? qidaAnswer.getRealNoSupportNum() : answer.getRealNoSupportNum());
			// qidaAnswer.setIsClose(answer.getIsClose());
			qidaAnswerService.save(qidaAnswer);

			result.put("success", true);
			result.put("msg", "保存回答成功！");
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
	 * 开启
	 * 
	 */
	@RequestMapping(value = "/openAnswer", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openAnswer(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			for (int i = 0; i < array.length; i++) {
				QidaAnswer answer = qidaAnswerService.get(Long.valueOf(array[i]));
				if (answer.getState() == 0) {
					answer.setState(1);
					qidaAnswerService.save(answer);

					QidaQuestion question = answer.getQidaQuestion();
					question.setReplyNum(1);
					qidaQuestionService.save(question);

					FuUser fuUser = answer.getFuUser();
					// 如果为付费问题，回答者就要获得积分
					if (question.getReward() > 0) {
						fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral().add(new BigDecimal(question.getReward()))));
						fuUserService.save(fuUser);
						// 保存回答问题得到的积分收益明细
						qidaScoreUtil.saveScoreByQd(fuUser, 47, new BigDecimal(question.getReward()), BigDecimal.ZERO, fuUser.getQidaIntegral(), 1);
					}

					QidaMessage message = new QidaMessage();
					message.setMessage((null == answer.getFuUser().getNickName()) ? "佚名" : answer.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
							+ answer.getQidaQuestion().getId() + ".html>" + answer.getQidaQuestion().getTitle() + "</a>问题");
					message.setSendFuUser(answer.getFuUser());
					message.setReceiveFuUser(answer.getQidaQuestion().getFuUser());
					message.setHttpCookie(UUID.randomUUID().toString());
					message.setIsRead(0);
					message.setIsDelete(0);
					message.setCreateTime(new Date());
					messageService.save(message);
				}
			}
			result.put("success", true);
			result.put("msg", "开启回答成功！");
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
	 * 关闭
	 * 
	 */
	@RequestMapping(value = "/closeAnswer", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeAnswer(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaAnswer answer = qidaAnswerService.get(Long.valueOf(array[i]));
				if (answer.getState() == 1) {
					answer.setState(0);
					qidaAnswerService.save(answer);
				}
			}
			result.put("success", true);
			result.put("msg", "关闭回答成功！");
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
	 * 删除答案
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delAnswer", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delAnswer(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				// Map<String, Object> map = new HashMap<String, Object>();
				// map.put("answerId", Long.valueOf(array[i]));
				// List<QidaAgree> list = qidaAgreeService.findList(0, Integer.MAX_VALUE, map);
				// if (list != null && list.size() > 0) {
				// for (QidaAgree qidaAgree : list) {
				// qidaAgreeService.delete(qidaAgree.getId());
				// }
				// }
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("answerId", Long.valueOf(array[i]));
				List<QidaAnswerComment> list = qidaCommentService.findListAnswerComment(0, Integer.MAX_VALUE, map);
				if (list != null && list.size() > 0) {
					for (QidaAnswerComment comment : list) {
						comment.setIsDelete(1);
						qidaCommentService.saveAc(comment);
					}
				}
				QidaAnswer answer = qidaAnswerService.get(Long.valueOf(array[i]));
				answer.setIsDelete(1);
				qidaAnswerService.save(answer);
			}

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
	 * 待审核回答列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerAudit", produces = "text/html;charset=UTF-8")
	public String answerAudit(ModelMap model) {
		model.put("answerSwitch", qidaSwitchService.get(1L).getAnswerSwitch());//问题审核开关，0开启，1关闭
		return "qida/qidaManage/answerAudit";
	}

	/**
	 * 待审核回答数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerAuditData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String answerAuditData(String nickName, String title, String content, Integer page, Integer rows, String sort, String order) {
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
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			if (!StringUtil.isBlank(sort)) {
				map.put("sort", sort);
			}
			if (!StringUtil.isBlank(order)) {
				map.put("order", order);
			}
			Integer total = qidaAnswerService.getCount(map);
			List<QidaAnswer> list = qidaAnswerService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaAnswer answer : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", answer.getId());
					jsonObject.put("nickName", answer.getFuUser().getNickName() == null ? "佚名" : answer.getFuUser().getNickName());
					jsonObject.put("title", answer.getQidaQuestion().getTitle());
					jsonObject.put("content", answer.getContent());
					jsonObject.put("createTime", answer.getCreateTime() == null ? "" : DateUtil.getStrFromDate(answer.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
