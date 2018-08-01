package com.xiaohe.qd.controller.qidianwap;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuSmsLog;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.service.FuSmsLogService;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.util.CommonUtil;
import com.xiaohe.qd.util.HttpClientUtil;
import com.xiaohe.qd.util.IP4;
import com.xiaohe.qd.util.Property;

@Controller
@RequestMapping("/stock")
@Scope("prototype")
public class WeiXinController extends BaseController {
	private static final Log logger = LogFactory.getLog(WeiXinController.class);
	@Resource
	private FuUserService fuUserService;
	@Resource
	private FuSmsLogService fuSmsLogService;

	@RequestMapping(value = "/wxLogin", produces = "text/html;charset=UTF-8")
	public String wxLogin(HttpServletRequest request, HttpServletResponse response, ModelMap map, HttpSession session, String state) {
		String wxCode = request.getParameter("code");
		String[] split = state.split("_");
		Long id = null;
		if ("tokLine".equals(split[0])) {
			id = Long.parseLong(split[1]);
		}
		try {
			FuUser fuUser = null;
			fuUser = (FuUser) session.getAttribute("fuUser");
			if (null == fuUser) {
				// 获取token和openid
				String appid = Property.getProperty("WEIXIN_APPID");
				String secret = Property.getProperty("WEIXIN_APP_SECRET");

				String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + wxCode + "&grant_type=authorization_code";

				logger.info("getTokenUrl===========>" + url);

				JSONObject result1 = HttpClientUtil.httpRequest(url, "GET", null);
				// 解析json数据
				String openId = (String) result1.get("openid");
				String token = (String) result1.get("access_token");
				logger.info("openId===========>" + openId);
				// 获取用户信息
				String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openId + "&lang=zh_CN";
				JSONObject result2 = HttpClientUtil.httpRequest(userUrl, "GET", null);

				String nickName = (String) result2.get("nickname");
				String avatar = (String) result2.get("headimgurl");
				logger.info("nickName===========>" + nickName);
				// nickName openid
				fuUser = fuUserService.findUserByWeixinCode(openId);
				logger.info("wxLogin_fuUser=====>" + fuUser);
				session.setAttribute("fuUser", fuUser);
				session.setAttribute("openId", openId);
				if (null == fuUser && null != openId) {
					// 新用户
					session.setAttribute("nickName", nickName);
					session.setAttribute("avatar", avatar);
					String redirectUrl = "";
					if ("tokLine".equals(split[0])) {
						redirectUrl = "/web/stock/kLine?id=" + id;
					} else {
						redirectUrl = "/web/stock/stockIndex";
					}
					map.put("redirectUrl", redirectUrl);
					return "redirect:/web/stock/addPhone";
				} else if (null != fuUser && null == fuUser.getPhone()) {
					String redirectUrl = "";
					if ("tokLine".equals(split[0])) {
						redirectUrl = "/web/stock/kLine?id=" + id;
					} else {
						redirectUrl = "/web/stock/stockIndex";
					}
					map.put("redirectUrl", redirectUrl);
					return "redirect:/web/stock/addPhone";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("tokLine".equals(split[0])) {
			return "redirect:/web/stock/kLine?id=" + id;
		} else {
			return "redirect:/web/stock/" + split[0];
		}
	}

	/**
	 * 绑定手机
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPhone", produces = "text/html;charset=UTF-8")
	public String addPhone(HttpServletRequest request, String redirectUrl) {
		request.setAttribute("redirectUrl", redirectUrl);
		return "userLine/addPhone";
	}

	/**
	 * 发送验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendCode", produces = "text/html;charset=UTF-8")
	public void sendCode(HttpServletRequest request, HttpServletResponse response, String phone) {
		try {
			String ip = IP4.getIP4(request);
			int flag = fuSmsLogService.examin(phone, ip);
			if (flag == 0) { // false
				write(response, "1");// 请联系管理员解冻你的封印
				return;
			} else if (flag == 2) {
				write(response, "2");// 您点击过于频繁, 请稍后再试
				return;
			}
			DecimalFormat format = new DecimalFormat("0000");
			String code = format.format(Math.random() * 9999);
			String message = URLDecoder.decode("您的手机验证码是:" + code + "，请填入界面完成绑定手机号码。", "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setContent(message);
			log.setPrio(1);
			log.setReason("发送验证码");
			log.setDestination(phone);
			log.setPlanTime(new Date());
			log.setType(1);
			log.setRegCode(code);
			log.setState(0);
			fuSmsLogService.save(log);
			write(response, "3");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return;
	}

	/**
	 * 绑定手机
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bindPhone", produces = "text/html;charset=UTF-8")
	public void bindPhone(HttpServletRequest request, HttpServletResponse response, String phone, String phoneCode, String registerCode) {
		try {
			if (null == phoneCode || "".equals(phoneCode)) {
				write(response, "1"); // 没有获取到验证码
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("destination", phone);
			map.put("type", 1);
			FuSmsLog fs = fuSmsLogService.findLogByMap(map);// 根据目标手机号得到最新一条短信对象
			if (null == fs) {
				write(response, "2"); // 请重新发送验证码
				return;
			}
			if (null == fs.getRegCode() || !phoneCode.equals(fs.getRegCode())) {
				write(response, "3"); // 验证码输入错误
				return;
			}
			String openId = (String) request.getSession().getAttribute("openId");
			if (null == openId) {
				write(response, "5"); // 非法路径
				return;
			}
			FuUser fuUser = fuUserService.findUserByAccount(phone);
			String nickName = (String) request.getSession().getAttribute("nickName");
			String avatar = (String) request.getSession().getAttribute("avatar");
			String code = (String) request.getSession().getAttribute("registerCode");
			if (null != code && !"".equals(code)) {
				registerCode = code;
			}
			if (null == fuUser) {
				DecimalFormat format = new DecimalFormat("000000");
				String password = format.format(Math.random() * 9999);
				password = CommonUtil.getMd5(password);
				fuUserService.saveWeiXinUser(request, response, phone, password, nickName, null, null == registerCode ? "364122458502" : registerCode, 4, avatar, openId);
				fuUser = fuUserService.findUserByWeixinCode(openId);
			} else {
				fuUser.setWeixinCode(openId);
				fuUserService.save(fuUser);
			}
			// 销毁Session
			request.getSession().invalidate();
			// 保存用户Session
			request.getSession().setAttribute("fuUser", fuUser);
			write(response, "4");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("bindPhone exception" + e);
		}
		return;
	}
}