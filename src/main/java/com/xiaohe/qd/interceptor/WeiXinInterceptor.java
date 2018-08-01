package com.xiaohe.qd.interceptor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.util.HttpClientUtil;
import com.xiaohe.qd.util.Property;
import com.xiaohe.qd.util.Sign;

public class WeiXinInterceptor implements HandlerInterceptor {


	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前调用
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		String privUrl = requestUrl.substring(contextPath.length());
		
		String appid = Property.getProperty("WEIXIN_APPID");
		String wxurl = Property.getProperty("REDIRECT_URL");
		request.setAttribute("appid", appid);
		request.setAttribute("url", wxurl);
		if (excludeUrls != null && excludeUrls.contains(privUrl)) {
			return true;
		} else {
			String access_token = null;
			if (null == request.getSession().getAttribute("access_token")) {
				// 第一步获取token存入全局缓存，
				String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Property.getProperty("WEIXIN_APPID") + "&secret=" + Property.getProperty("WEIXIN_APP_SECRET"));
				JSONObject obj1 = new JSONObject(result1);
				access_token = obj1.get("access_token").toString();
				request.getSession().setAttribute("access_token", access_token);
			} else {
				access_token = request.getSession().getAttribute("access_token").toString();
			}

			String jsapi_ticket = null;
			if (null == request.getSession().getAttribute("jsapi_ticket")) {
				// 第二步根据token得到jsapi_ticket存入全局缓存
				String result2 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");
				JSONObject obj2 = new JSONObject(result2);
				jsapi_ticket = obj2.get("ticket").toString();
				request.getSession().setAttribute("jsapi_ticket", jsapi_ticket);
			} else {
				jsapi_ticket = request.getSession().getAttribute("jsapi_ticket").toString();
			}

			// 获取请求的地址
			StringBuffer url = request.getRequestURL();
			String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
			String httpUrl = contextUrl + request.getRequestURI();
			String from = request.getParameter("from");
			String isappinstalled = request.getParameter("isappinstalled");
			if (from != null && isappinstalled != null) {
				httpUrl = httpUrl + "?from=" + from + "&isappinstalled=" + isappinstalled;
			}
			// 签名算法
			Map<String, String> map = Sign.sign(jsapi_ticket, httpUrl);
			request.setAttribute("appId", Property.getProperty("WEIXIN_APPID"));
			request.setAttribute("timestamp", map.get("timestamp"));
			request.setAttribute("nonceStr", map.get("nonceStr"));
			request.setAttribute("signature", map.get("signature"));
			request.setAttribute("newDate", new Date().getTime());

			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (null == fuUser) {// 用户未登录
				String state = "";
				if (requestUrl.contains("kLine")) {
					String id = request.getParameter("id");
					state = "tokLine_" + id;
				} else {
					state = "toStockIndex";
				}
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + wxurl + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect");
				return false;
			} else {// 用户已登录
				request.getSession().setAttribute("fuUser", fuUser);
				return true;
			}
		}
	}
}
