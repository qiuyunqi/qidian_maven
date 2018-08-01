<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-选择好友</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/web/js_qiDa/bootstrap.js"></script>
  </head>
  <body>
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 页头 -->
  	<%@ include file="../common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<!-- 好友 -->
			<div class="kongge"></div>
			<ul class="choose-ul">
				<c:forEach items="${concernList}" var="concern">
				<li class="choose-li">
					<a href="${ctx}/web/ai/new/${concern.beConcernUser.id}.html" title="头像">
					<c:if test="${empty concern.beConcernUser.userAvatar}">
						<img src="${ctx}/web/images_qiDa/defTx.jpg" alt="头像"/>
					</c:if>
					<c:if test="${not empty concern.beConcernUser.userAvatar}">
						<img src="${concern.beConcernUser.userAvatar}" alt="头像"/>
					</c:if>		
					<span class="choose-name">${empty concern.beConcernUser.nickName ? '佚名' : concern.beConcernUser.nickName}</span>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>
	<!-- footer end -->
	
	<script type="text/javascript">
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