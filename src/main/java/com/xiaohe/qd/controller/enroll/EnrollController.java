package com.xiaohe.qd.controller.enroll;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.service.FuEnrollService;

/**
 * 一个简单的报名页面
 * @author han
 * @date 2016-12-13
 */
@Controller
@Scope("prototype")
public class EnrollController extends BaseController {

	@Resource
	private FuEnrollService fuEnrollService;
	/**
	 * 进入报名页面
	 * @return
	 */
	@RequestMapping("/enroll.html")
	public String enroll() {
		return "enroll/enroll";
	}
	
	/**
	 * 检测手机号是否报名
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/checkPhone.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkPhone(String phone) {
		JSONObject obj = new JSONObject();
		phone = phone.trim();
		if (null == phone || "".equals(phone)) {
			obj.put("success", 0);
			obj.put("message", "请输入手机号");
			return obj.toString();
		}
		if (phone.length() != 11) {
			obj.put("success", 0);
			obj.put("message", "请输入正确的手机号");
			return obj.toString();
		}
		int result = fuEnrollService.findByPhone(phone);
		if (result == 0) {
			obj.put("success", 1);
			obj.put("message", "这个电话号码可以有");
			return obj.toString();
		} else {
			obj.put("success", 0);
			obj.put("message", "此号已被注册");
			return obj.toString();
		}
	}
	/**
	 * 存储报名信息
	 * @param request
	 * @param userName
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/saveEnroll.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveEnroll(HttpServletRequest request, String userName, String phone) {
		userName = userName.trim();
		phone = phone.trim();
		JSONObject obj = new JSONObject();
		if (null == userName || "".equals(userName)) {
			obj.put("success", 0);
			obj.put("message", "请输入您的名称");
			return obj.toString();
		}
		if (null == phone || "".equals(phone)) {
			obj.put("success", 0);
			obj.put("message", "请输入手机号");
			return obj.toString();
		}
		if (phone.length() != 11) {
			obj.put("success", 0);
			obj.put("message", "请输入正确的手机号");
			return obj.toString();
		}
		int result = fuEnrollService.findByPhone(phone);
		if (result == 1) {
			obj.put("success", 0);
			obj.put("message", "此号已被注册");
			return obj.toString();
		}
		int num = fuEnrollService.save(userName, phone);
		if (num == 0) {
			obj.put("success", 0);
			obj.put("message", "报名失败, 请联系管理员");
			return obj.toString();
		} else {
			obj.put("success", 1);
			obj.put("message", "恭喜您, 报名成功");
			return obj.toString();
		}
	}
}
