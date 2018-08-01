package com.xiaohe.qd.controller.qidaadmin;

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
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.model.QidaQuestionComment;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.service.QidaMessageService;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class QuestionCommentController extends BaseController {
	@Resource
	private QidaCommentService qidaCommentService;
	@Resource
	private QidaMessageService messageService;
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 问题列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionCommentList", produces = "text/html;charset=UTF-8")
	public String questionCommentList(ModelMap model) {
		return "qida/qidaManage/questionCommentList";
	}

	/**
	 * 问题数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionCommentData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String questionCommentData(String nickName, String content, Integer state, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			if (state != null) {
				map.put("state", state);
			}
			Integer total = qidaCommentService.getCountQuestionComment(map);
			List<QidaQuestionComment> list = qidaCommentService.findListQuestionComment((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaQuestionComment comment : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", comment.getId());
					jsonObject.put("title", comment.getQidaQuestion().getTitle());
					jsonObject.put("nickName", comment.getFuUser().getNickName() == null ? "佚名" : comment.getFuUser().getNickName());
					jsonObject.put("content", comment.getContent());
					jsonObject.put("state", comment.getState() == 1 ? "通过" : "待审核");
					jsonObject.put("isDelete", comment.getIsDelete() == 1 ? "是" : "否");
					jsonObject.put("createTime", comment.getCreateTime() == null ? "" : DateUtil.getStrFromDate(comment.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
	@RequestMapping(value = "/editQuestionComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editQuestionComment(QidaQuestionComment comment) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaQuestionComment questionComment = new QidaQuestionComment();
			if (comment.getId() != null) {
				questionComment = qidaCommentService.getQc(comment.getId());
			}
			questionComment.setContent(comment.getContent() == null ? questionComment.getContent() : comment.getContent());
			// questionComment.setIsClose(comment.getIsClose());
			// questionComment.setIsDelete(comment.getIsDelete());
			qidaCommentService.saveQc(questionComment);

			result.put("success", true);
			result.put("msg", "保存评论成功！");
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
	@RequestMapping(value = "/openQuestionComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openQuestionComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaQuestionComment comment = qidaCommentService.getQc(Long.valueOf(array[i]));
				if (comment.getState() == 0) {
					QidaMessage message = new QidaMessage();
					message.setMessage((null == comment.getFuUser().getNickName()) ? "佚名" : comment.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
							+ comment.getQidaQuestion().getId() + ".html>" + comment.getQidaQuestion().getTitle() + "</a>问题");
					message.setSendFuUser(comment.getFuUser());
					message.setReceiveFuUser(comment.getQidaQuestion().getFuUser());
					message.setHttpCookie(UUID.randomUUID().toString());
					message.setIsRead(0);
					message.setIsDelete(0);
					message.setCreateTime(new Date());
					messageService.save(message);
					message.setMessageId(message.getId());
					messageService.save(message);

					comment.setState(1);
					qidaCommentService.saveQc(comment);
				}
			}
			result.put("success", true);
			result.put("msg", "开启评论成功！");
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
	@RequestMapping(value = "/closeQuestionComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeQuestionComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaQuestionComment comment = qidaCommentService.getQc(Long.valueOf(array[i]));
				if (comment.getState() == 1) {
					comment.setState(0);
					qidaCommentService.saveQc(comment);
				}
			}
			result.put("success", true);
			result.put("msg", "关闭评论成功！");
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
	@RequestMapping(value = "/delQuestionComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delQuestionComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaQuestionComment comment = qidaCommentService.getQc(Long.valueOf(array[i]));
				comment.setIsDelete(1);
				qidaCommentService.saveQc(comment);
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
	 * 待审核问题评论列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionCommentAudit", produces = "text/html;charset=UTF-8")
	public String questionCommentAudit(ModelMap model) {
		model.put("qcSwitch", qidaSwitchService.get(1L).getQuestionCommentSwitch());//问题审核开关，0开启，1关闭
		return "qida/qidaManage/questionCommentAudit";
	}

	/**
	 * 待审核问题评论数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionCommentAuditData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String questionCommentAuditData(String nickName, String content, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 0);
			map.put("isDelete", 0);
			if (!StringUtil.isBlank(nickName)) {
				map.put("nickName", nickName);
			}
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			Integer total = qidaCommentService.getCountQuestionComment(map);
			List<QidaQuestionComment> list = qidaCommentService.findListQuestionComment((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaQuestionComment comment : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", comment.getId());
					jsonObject.put("title", comment.getQidaQuestion().getTitle());
					jsonObject.put("nickName", comment.getFuUser().getNickName() == null ? "佚名" : comment.getFuUser().getNickName());
					jsonObject.put("content", comment.getContent());
					jsonObject.put("createTime", comment.getCreateTime() == null ? "" : DateUtil.getStrFromDate(comment.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
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
