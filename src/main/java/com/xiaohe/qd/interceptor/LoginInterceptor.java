package com.xiaohe.qd.interceptor;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.StockRecentOptional;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.StockRecentOptionalService;
import com.xiaohe.qd.util.Property;
import com.xiaohe.qd.util.WebUtil;

public class LoginInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Resource
	private FuUserService fuUserService;
	@Resource
	private StockInfoService stockInfoService;
	@Resource
	private StockRecentOptionalService stockRecentOptionalService;

	public static String COOKIE_NAME = "token_name";
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
		String path = request.getContextPath();
		String privUrl = requestUrl.substring(contextPath.length());
		if (excludeUrls != null && excludeUrls.contains(privUrl)) {
			String items = stockInfoService.queryStockItems();
			request.setAttribute("items", items);
			return true;
		}

		String gotoURL = request.getParameter("gotoURL");
		if (requestUrl.equals(path + "/web/ai/logout")) {
			gotoURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/web/ai/qdIndex";
		}
		if (requestUrl.equals(path + "/web/ai/logout.html")) {
			gotoURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/web/qida/index.html";
		}
		if (gotoURL == null) {
			gotoURL = request.getRequestURL().toString();
		}

		String URL = Property.getProperty("SSO_SERVICE") + "?action=preLogin&setCookieURL=" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/web/ai/setCookie&gotoURL=" + gotoURL;

		Cookie ticket = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_NAME)) {
					ticket = cookie;
					break;
				}
			}

		if (requestUrl.equals(path + "/web/ai/logout")) {
			request.getSession().removeAttribute("fuUser");
			WebUtil.addCookie(response, COOKIE_NAME, "", 0);
			return doLogout(request, response, ticket, URL);
		}
		if (requestUrl.equals(path + "/web/ai/logout.html")) {
			request.getSession().removeAttribute("fuUser");
			WebUtil.addCookie(response, COOKIE_NAME, "", 0);
			return doLogout(request, response, ticket, URL);
		}

		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (fuUser != null) {
			String items = stockInfoService.queryStockItems();
			// 查询近期自选股
			List<StockRecentOptional> optionalList = null;
			if (null != fuUser) {
				// 查询近期选股数据
				optionalList = stockRecentOptionalService.findListByUser(0, 11, fuUser.getId());
			}
			request.setAttribute("items", items);
			request.setAttribute("optionalList", optionalList);
			return true;
		}

		if (requestUrl.equals(path + "/web/ai/setCookie")) {
			return true;
		} else if (ticket != null) {
			return authCookie(request, response, ticket, URL);
		} else {
			response.sendRedirect(URL);
			return false;
		}
	}

	private boolean doLogout(HttpServletRequest request, HttpServletResponse response, Cookie ticket, String url) throws JSONException, IOException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "logout");
		params[1] = new NameValuePair(COOKIE_NAME, ticket.getValue());
		try {
			post(request, response, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect(url);
		}
		return false;
	}

	private boolean authCookie(HttpServletRequest request, HttpServletResponse response, Cookie ticket, String url) throws IOException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "authTicket");
		params[1] = new NameValuePair(COOKIE_NAME, ticket.getValue());
		try {
			JSONObject result = post(request, response, params);
			if (result.getBoolean("error")) {
				response.sendRedirect(url);
				return false;
			} else {
				logger.info(result);
				FuUser fuUser = fuUserService.get((long) result.getInt("userId"));
				String items = stockInfoService.queryStockItems();
				// 查询近期自选股
				List<StockRecentOptional> optionalList = null;
				if (null != fuUser) {
					// 查询近期选股数据
					optionalList = stockRecentOptionalService.findListByUser(0, 11, fuUser.getId());
				}
				request.setAttribute("items", items);
				request.setAttribute("optionalList", optionalList);
				request.getSession().setAttribute("fuUser", fuUser);
				return true;
			}
		} catch (JSONException e) {
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
			throw new RuntimeException(e);
		}

	}

	private JSONObject post(HttpServletRequest request, HttpServletResponse response, NameValuePair[] params) throws HttpException, IOException, JSONException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(Property.getProperty("SSO_SERVICE"));
		postMethod.addParameters(params);
		switch (httpClient.executeMethod(postMethod)) {
		case HttpStatus.SC_OK:
			return new JSONObject(postMethod.getResponseBodyAsString());
		default:
			// 其它处理
			return null;
		}
	}

}
