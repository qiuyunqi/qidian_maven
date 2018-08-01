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
import com.xiaohe.qd.model.QidaAnswerComment;
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.service.QidaMessageService;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AnswerCommentController extends BaseController {
	@Resource
	private QidaCommentService qidaCommentService;
	@Resource
	private QidaMessageService messageService;
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 评论列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerCommentList", produces = "text/html;charset=UTF-8")
	public String answerCommentList(ModelMap model) {
		return "qida/qidaManage/answerCommentList";
	}

	/**
	 * 评论数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerCommentData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String answerCommentData(String nickName, String content, Integer state, Integer page, Integer rows) {
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
			Integer total = qidaCommentService.getCountAnswerComment(map);
			List<QidaAnswerComment> list = qidaCommentService.findListAnswerComment((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaAnswerComment comment : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", comment.getId());
					jsonObject.put("title", comment.getQidaAnswer().getContent());
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
	 * 修改评论
	 * 
	 */
	@RequestMapping(value = "/editAnswerComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editAnswerComment(QidaAnswerComment comment) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaAnswerComment answerComment = new QidaAnswerComment();
			if (comment.getId() != null) {
				answerComment = qidaCommentService.getAc(comment.getId());
			}
			answerComment.setContent(comment.getContent() == null ? answerComment.getContent() : comment.getContent());
			// answerComment.setIsClose(comment.getIsClose());
			// answerComment.setIsDelete(comment.getIsDelete());
			qidaCommentService.saveAc(answerComment);

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
	 * 开启评论
	 * 
	 */
	@RequestMapping(value = "/openAnswerComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openAnswerComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaAnswerComment comment = qidaCommentService.getAc(Long.valueOf(array[i]));
				if (comment.getState() == 0) {
					QidaMessage message = new QidaMessage();
					message.setMessage((null == comment.getFuUser().getNickName()) ? "佚名" : comment.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
							+ comment.getQidaAnswer().getQidaQuestion().getId() + ".html>" + comment.getQidaAnswer().getQidaQuestion().getTitle() + "</a>问题的答案");
					message.setSendFuUser(comment.getFuUser());
					message.setReceiveFuUser(comment.getQidaAnswer().getFuUser());
					message.setHttpCookie(UUID.randomUUID().toString());
					message.setIsRead(0);
					message.setIsDelete(0);
					message.setCreateTime(new Date());
					messageService.save(message);
					message.setMessageId(message.getId());
					messageService.save(message);

					comment.setState(1);
					qidaCommentService.saveAc(comment);
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
	 * 关闭评论
	 * 
	 */
	@RequestMapping(value = "/closeAnswerComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeAnswerComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaAnswerComment comment = qidaCommentService.getAc(Long.valueOf(array[i]));
				if (comment.getState() == 1) {
					comment.setState(0);
					qidaCommentService.saveAc(comment);
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
	 * 删除评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delAnswerComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delAnswerComment(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] array = request.getParameterValues("array[]");
			for (int i = 0; i < array.length; i++) {
				QidaAnswerComment comment = qidaCommentService.getAc(Long.valueOf(array[i]));
				comment.setIsDelete(1);
				qidaCommentService.saveAc(comment);
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
	 * 待审核评论列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerCommentAudit", produces = "text/html;charset=UTF-8")
	public String answerCommentAudit(ModelMap model) {
		model.put("acSwitch", qidaSwitchService.get(1L).getAnswerCommentSwitch());//问题审核开关，0开启，1关闭
		return "qida/qidaManage/answerCommentAudit";
	}

	/**
	 * 待审核评论数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/answerCommentAuditData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String answerCommentAuditData(String nickName, String content, Integer page, Integer rows) {
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
			Integer total = qidaCommentService.getCountAnswerComment(map);
			List<QidaAnswerComment> list = qidaCommentService.findListAnswerComment((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaAnswerComment comment : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", comment.getId());
					jsonObject.put("title", comment.getQidaAnswer().getContent());
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
