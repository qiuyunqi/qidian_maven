<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-发送消息</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/web/js_qiDa/bootstrap.js"></script>
	<style>
		.conterAll{position: fixed;bottom:0;}
	</style>
  </head>
  <body>
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 页头 -->
  	<%@ include file="../common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<!-- 左边 -->
		<div class="sidebar-left flatLft">
			<div class="kongge"></div>
			<a class="message-send" href="${ctx}/web/messages.html" title="返回我的消息列表">返回我的消息列表</a>
		</div>
		<!-- 右边 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<div class="send-name">
				<a class="send-tx" href="javascript:void(0)" title="头像">
				<c:if test="${empty receiveUser.userAvatar}">
					<img src="${ctx}/web/images_qiDa/tx.png" alt="头像"/>
				</c:if>
				<c:if test="${not empty receiveUser.userAvatar}">
					<img src="${receiveUser.userAvatar}" alt="头像"/>
				</c:if>
				</a>
				<input type="hidden" name="id" value="${receiveUser.id}">
				<span class="send-text">给 <a href="javascript:void(0)" title="用户">${empty receiveUser.nickName ? '佚名' : receiveUser.nickName}</a> 写消息：</span>
			</div>
			<textarea class="send-textarea"  rows="5"  placeholder="请输入您要发送的内容，限制200字以内"></textarea>
			<div class="send-btnBody">
				<a href="javascript:void(0);" class="send-btn send-sure" id="sendSub" title="发送">发送</a>
				<a href="javascript:void(0);" class="send-btn send-qx" id="sendCan" title="取消">取消</a>
			</div>
		</div>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>
	<!-- footer end -->
	
	<script type="text/javascript">
	// 提交
	$("#sendSub").click(function() {
		var $content = $(".send-textarea").val();
		$.ajax({
			type: 'POST',
			url: "${ctx}/web/ai/send.html",
			data: {'content': $content, 'receiveUserId': ${receiveUser.id}},
			success: function(data){
				layer.closeAll();
				if (data.success == 0) {
					globalTip(data.message);
				}  else {
					globalTip(data.message);
					location.href="${ctx}/web/messages.html";
				}
			},
			beforeSend:function() {
				layer.open({type: 2, content: '加载中'});
			},
			dataType: "json"
		});
	});
	
	$("#sendCan").click(function() {
		$(".send-textarea").val("");
		return false;
	});
	
	
		//判断footer是否在底部
		$(document).ready(function(){
		var topHgt = $(".navbar-static-top").height();
		var contHgt = $(".bodyHget").height();
		var sunHgt = topHgt + contHgt;
		var bodyHgt = $(window).height();
			if(sunHgt <bodyHgt-392){
				$(".conterAll").css("position", "");
				$(".bodyHget").height("600px");
			}else{
				$(".conterAll").css("position", "");
			}
		});
	</script>
  </body>
</html>