package com.xiaohe.qd.controller.qidapc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaConcern;
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaConcernService;
import com.xiaohe.qd.service.QidaMessageService;
import com.xiaohe.qd.util.DateUtil;
import com.xiaohe.qd.util.ReplaceUtil;

/**
 * 站内消息
 * @author han
 *
 */
@Controller
@Scope("prototype")
public class MessageControll extends BaseController {
	private static Logger logger = Logger.getLogger(MessageControll.class);
	public static final String FILE_NAME = "qida/message/";
	@Resource
	private QidaMessageService messageService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaConcernService  concernService;
	
	/**
	 * 查询最新的消息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ai/myMessage.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myMessage(HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			List<QidaMessage> messageList = messageService.findNew(0, 5, fuUser.getId(), 0, 0);
			List<QidaMessage> unReadList = messageService.findNew(0, 10000, fuUser.getId(), 0, 0);
			if (null != messageList && messageList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (QidaMessage message : messageList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id", message.getId());
					map.put("userAvatar", null == (message.getSendFuUser().getUserAvatar()) ? 
							request.getContextPath()+"/web/images_qiDa/defTx.jpg" : 
								message.getSendFuUser().getUserAvatar());
					
					map.put("nickName", null == (message.getSendFuUser().getNickName()) ? "佚名" : message.getSendFuUser().getNickName());
					map.put("createTime", null == DateUtil.getTimeBefore(message.getCreateTime()) ? 
							DateUtil.showDate(message.getCreateTime()) : DateUtil.getTimeBefore(message.getCreateTime()));
					map.put("content", message.getMessage());
					list.add(map);
				}
				obj.put("messageList", list);
				obj.put("unReadCount", (null == unReadList || unReadList.size() <= 0) ? 0 : unReadList.size());
				obj.put("success", 1);
			} else {
				obj.put("message", "暂无最新消息");
				obj.put("success", 0);
			}
			return obj.toString();
		} catch (Exception e) {
			obj.put("message", "请联系管理员");
			obj.put("success", 0);
			logger.error(DateUtil.showDate(new Date()) + obj.toString());
			return obj.toString();
		}
		
	}
	
	/**
	 * 查询更多的消息
	 * @return
	 */
	@RequestMapping(value = "/messages.html", produces = "text/html;charset=UTF-8")
	public String moreNews(HttpServletRequest request, Integer pageNo) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null == pageNo) {
			pageNo = 1;
		}
		messageService.updateMessageId((pageNo -1)* this.getPageSize(), this.getPageSize(), fuUser.getId(), 0);
		List<QidaMessage> unReadList = messageService.findNew(0, 10000, fuUser.getId(), 0, 0);
		List<QidaMessage> messageList = messageService.findByMe((pageNo -1)* this.getPageSize(), this.getPageSize(), fuUser.getId(), 0);
		int totalCount = messageService.getCount(fuUser.getId(), 0);
		request.setAttribute("messageList", messageList);
		request.setAttribute("unReadCount", (null == unReadList || unReadList.size() <= 0) ? 0 : unReadList.size());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", this.getPageSize());
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowDate", new Date());
		return FILE_NAME+"messages";
	}
	
	/**
	 * 查询更多未读的消息
	 * @return
	 */
	@RequestMapping(value = "/unRead.html", produces = "text/html;charset=UTF-8")
	public String unRead(HttpServletRequest request, Integer pageNo) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null == pageNo) {
			pageNo = 1;
		}
		List<QidaMessage> unReadList = messageService.findNew((pageNo-1) * this.getPageSize(), this.getPageSize(), fuUser.getId(), 0, 0);
		List<QidaMessage> listCount = messageService.findNew(0, 10000, fuUser.getId(), 0, 0);
		request.setAttribute("unReadList", unReadList);
		request.setAttribute("unReadCount", (null == listCount || listCount.size() <= 0) ? 0 : listCount.size());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", this.getPageSize());
		request.setAttribute("totalCount", (null == listCount || listCount.size() <= 0) ? 0 : listCount.size());
		request.setAttribute("nowDate", new Date());
		return FILE_NAME+"unRead";
	}
	
	/**
	 * 查询更多未读的消息
	 * @return
	 */
	@RequestMapping(value = "/read.html", produces = "text/html;charset=UTF-8")
	public String read(HttpServletRequest request, Integer pageNo) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null == pageNo) {
			pageNo = 1;
		}
		// 未读消息
		List<QidaMessage> unReadList = messageService.findNew(0, 10000, fuUser.getId(), 0, 0);
		List<QidaMessage> readList = messageService.findNew((pageNo-1) * this.getPageSize(), this.getPageSize(), fuUser.getId(), 1, 0);
		List<QidaMessage> readCount = messageService.findNew(0, 10000, fuUser.getId(), 1, 0);
		request.setAttribute("readList", readList);
		request.setAttribute("unReadCount", (null == unReadList || unReadList.size() <= 0) ? 0 : unReadList.size());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", this.getPageSize());
		request.setAttribute("totalCount", (null == readCount || readCount.size() <= 0) ? 0 : readCount.size());
		request.setAttribute("nowDate", new Date());
		return FILE_NAME+"read";
	}
	
	/**
	 * 查询站内信具体内容
	 * @return
	 */
	@RequestMapping(value = "/views/{messageId}.html", produces = "text/html;charset=UTF-8")
	public String views(HttpServletRequest request, @PathVariable Long messageId){
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null == messageId) {
			return null;
		}
		QidaMessage qidaMessage = messageService.get(messageId);
		if (null == qidaMessage) {
			return null;
		}
		List<QidaMessage> messages= messageService.findByHttpCookie(qidaMessage.getHttpCookie(), fuUser.getId(), 0);
		for (QidaMessage qidaMessage2 : messages) {
			qidaMessage2.setIsRead(1); // 标识成已读
			messageService.save(qidaMessage2);
		}
		List<QidaMessage> messageList = messageService.findViews(qidaMessage, 0);
		QidaMessage message = messageService.get(messageId);
		request.setAttribute("sendUserId", message.getSendFuUser().getId());
		request.setAttribute("messageList", messageList);
		request.setAttribute("nowDate", new Date());
		return FILE_NAME+"messageInfo";
	}
	
	/**
	 * 回复站内信消息
	 * @param request
	 * @param content
	 * @param receiveUserId
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/reply.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reply(HttpServletRequest request, String content, Long receiveUserId, Long messageId) {
		JSONObject obj = new JSONObject();
		if (null == content || "".equals(content) || null == receiveUserId || null == messageId) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请联系管理员");
			logger.error(DateUtil.showDate(new Date()) + obj.toString());
			return obj.toString();
		}
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		// 过滤关键词
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
        String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");  
		content = ReplaceUtil.replaceCheck(keys, content);
		
		int result = messageService.saveRply(receiveUserId, fuUser.getId(), content, UUID.randomUUID().toString(), messageId);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("message", "发送信息失败, 请联系管理员");
			logger.error(DateUtil.showDate(new Date()) + obj.toString());
		} else {
			obj.put("success", 1);
			obj.put("message", "发送信息成功");
			logger.info(DateUtil.showDate(new Date()) + obj.toString());
		}
		return obj.toString();
	}
	
	/**
	 * 进入选择发送消息人页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ai/choose.html", produces = "text/html;chaset=UTF-8")
	public String choose(HttpServletRequest request) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		List<QidaConcern> concernList = concernService.findByMySelf(fuUser.getId());
		request.setAttribute("concernList", concernList);
		return FILE_NAME+"choose";
	}
	
	/**
	 * 删除这条站内信
	 * @param request
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/delete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(String ids) {
		JSONObject obj = new JSONObject();
		if (null == ids || "".equals(ids)) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请联系管理员");
			logger.error(DateUtil.showDateTime(new Date()) + obj.toString());
			return obj.toString();
		}
		int result = messageService.delete(ids);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("message", "消息不存在");
			logger.error(DateUtil.showDateTime(new Date()) + obj.toString());
		} else {
			obj.put("success", 1);
			obj.put("message", "删除消息成功");
			logger.info(DateUtil.showDateTime(new Date()) + obj.toString() + "===" + ids);
		}
		return obj.toString();
	}
	
	/**
	 * 设置站内信为已读信息
	 * @return
	 */
	@RequestMapping(value = "/setIsRead.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setIsRead(String messageIds) {
		JSONObject obj = new JSONObject();
		if (null == messageIds) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请联系管理员");
			logger.error(DateUtil.showDateTime(new Date()) + obj.toString());
			return obj.toString();
		}
		messageIds = messageIds.substring(0, messageIds.length() - 1);
		int result = messageService.saveIsRead(messageIds);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("message", "消息不存在");
			logger.error(DateUtil.showDateTime(new Date()) + obj.toString());
		} else {
			obj.put("success", 1);
			obj.put("message", "设置已读成功");
			logger.info(DateUtil.showDateTime(new Date()) + obj.toString() + "===" + messageIds);
		}
		return obj.toString();
	}
	
	/**
	 * 进入发送短信息页面
	 * @param request
	 * @param userId	接收人信息人的标识符
	 * @return
	 */
	@RequestMapping(value = "/ai/new/{userId}.html", produces = "text/html;charset=UTF-8")
	public String toSend(HttpServletRequest request, @PathVariable Long userId) {
		FuUser receiveUser = fuUserService.get(userId);
		request.setAttribute("receiveUser", receiveUser);
		return FILE_NAME+"sendMessage";
	}
	
	/**
	 * 发送短信息
	 * @param request
	 * @param userId	接收人信息人的标识符
	 * @return
	 */
	@RequestMapping(value = "/ai/send.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String sendMessage(HttpServletRequest request, Long receiveUserId, String content) {
		JSONObject obj = new JSONObject();
		try {
			// 过滤关键词
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
	        String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");  
			content = ReplaceUtil.replaceCheck(keys, content);
			
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			FuUser receiveUser = fuUserService.get(receiveUserId);
			QidaMessage message = new QidaMessage();
			message.setMessage(content);
			message.setSendFuUser(fuUser);
			message.setReceiveFuUser(receiveUser);
			message.setHttpCookie(UUID.randomUUID().toString());
			message.setIsRead(0);
			message.setIsDelete(0);
			message.setCreateTime(new Date());
			messageService.save(message);
			message.setMessageId(message.getId());
			messageService.save(message);
			obj.put("success", 1);
			obj.put("message", "发送短信消息成功");
		} catch (Exception e) {
			obj.put("success", 1);
			obj.put("message", "发送短信消息失败");
		}
		return obj.toString();
	}
}
