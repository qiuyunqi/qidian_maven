package com.xiaohe.qd.controller.qidapc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.util.ReplaceUtil;

/**
 * 评论系统
 * @author han
 */

@Controller
@Scope("prototype")
public class CommentController extends BaseController {
	@Resource
	private QidaCommentService commentService;
	/**
	 * 添加评论
	 * @param quetsionId  flag == 0  questionId 代表问题id  == 1 代表答案id
	 * @param content  评论内容
	 * @param flag
	 * @return
	 */
	@RequestMapping(value = "/ai/addComment.html", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addComment(HttpServletRequest request, Long questionId, String content, Integer flag) {
		JSONObject obj = new JSONObject();
		if (null == questionId) {
			obj.put("success", 0);
			obj.put("message", "非法请求");
			return obj.toString();
		}
		
		if (null == flag) {
			obj.put("success", 0);
			obj.put("message", "非法请求");
			return obj.toString();
		}
		
		if (null == content || "".equals(content)) {
			obj.put("success", 0);
			obj.put("message", "请输入评论内容");
			return obj.toString();
		}
		
		if (content.length() < 5 || content.length() > 200) {
			obj.put("success", 0);
			obj.put("message", "评论内容长度5-200字");
			return obj.toString();
		}
		FuUser fuUser = (FuUser)request.getSession().getAttribute("fuUser");
		// 过滤关键词
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
        String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");  
		content = ReplaceUtil.replaceCheck(keys, content);
		
		int result = commentService.saveComment(fuUser,questionId, content, flag, request);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("message", "保存评论失败, 联系管理员");
		} else if (2 == result) {
			obj.put("success", 0);
			obj.put("message", "非法请求");
		} else {
			obj.put("success", 1);
			obj.put("message", "保存评论内容成功");
		}
		return obj.toString();
	}

}
