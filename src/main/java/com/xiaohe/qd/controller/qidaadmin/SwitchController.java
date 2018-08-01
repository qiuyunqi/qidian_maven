package com.xiaohe.qd.controller.qidaadmin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.QidaSwitch;
import com.xiaohe.qd.service.QidaSwitchService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class SwitchController extends BaseController {
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 回答列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/switchManager", produces = "text/html;charset=UTF-8")
	public String switchManager(ModelMap model) {
		model.put("questionSwitch", qidaSwitchService.get(1L).getQuestionSwitch());// 问题审核开关，0开启，1关闭
		model.put("answerSwitch", qidaSwitchService.get(1L).getAnswerSwitch());// 问题审核开关，0开启，1关闭
		model.put("qcSwitch", qidaSwitchService.get(1L).getQuestionCommentSwitch());// 问题审核开关，0开启，1关闭
		model.put("acSwitch", qidaSwitchService.get(1L).getAnswerCommentSwitch());// 问题审核开关，0开启，1关闭
		return "qida/qidaManage/switchManager";
	}

	/**
	 * 开启问题审核
	 * 
	 */
	@RequestMapping(value = "/openQuestionSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openQuestionSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setQuestionSwitch(0);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "开启问题审核成功！");
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
	 * 关闭问题审核
	 * 
	 */
	@RequestMapping(value = "/closeQuestionSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeQuestionSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setQuestionSwitch(1);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "关闭问题审核成功！");
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
	 * 开启答案审核
	 * 
	 */
	@RequestMapping(value = "/openAnswerSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openAnswerSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setAnswerSwitch(0);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "开启答案审核成功！");
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
	 * 关闭答案审核
	 * 
	 */
	@RequestMapping(value = "/closeAnswerSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeAnswerSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setAnswerSwitch(1);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "关闭答案审核成功！");
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
	 * 开启问题评论审核
	 * 
	 */
	@RequestMapping(value = "/openQuestionCommentSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openQuestionCommentSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setQuestionCommentSwitch(0);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "开启问题评论审核成功！");
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
	 * 关闭问题评论审核
	 * 
	 */
	@RequestMapping(value = "/closeQuestionCommentSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeQuestionCommentSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setQuestionCommentSwitch(1);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "关闭问题评论审核成功！");
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
	 * 开启答案评论审核
	 * 
	 */
	@RequestMapping(value = "/openAnswerCommentSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openAnswerCommentSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setAnswerCommentSwitch(0);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "开启答案评论审核成功！");
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
	 * 关闭答案评论审核
	 * 
	 */
	@RequestMapping(value = "/closeAnswerCommentSwitch", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeAnswerCommentSwitch() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaSwitch qidaSwitch = qidaSwitchService.get(1L);
			qidaSwitch.setAnswerCommentSwitch(1);
			qidaSwitchService.save(qidaSwitch);

			result.put("success", true);
			result.put("msg", "关闭答案评论审核成功！");
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
}
