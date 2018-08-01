<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-全部消息</title>
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
		<!-- 左边 -->
		<div class="sidebar-left flatLft">
			<div class="kongge"></div>
			<a class="message-send" href="${ctx}/web/ai/choose.html" title="给关注的人发消息">给关注的人发消息</a>
		</div>
		<!-- 右边 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<div class="message-tit">
				<a class="message-txt" href="javascript:void(0)" title="私信">私信（${unReadCount}未读）</a>
				<div class="checkbox message-box">
				    <label>
				      <input type="checkbox">
				    </label>
				</div>
				<a class="message-del btnMaee" href="javascript:void(0)" id="delMsg" title="删除">删除</a>
				<a class="message-yid btnMaee" href="javascript:void(0)" id="readMsg" title="标为已读">标为已读</a>
				<div class="message-gl btnMaee">
					<a class="message-create" href="javascript:void(0)" title="筛选"><span>全部</span> <i class="glyphicon glyphicon-chevron-down"></i></a>
					<!-- 下拉弹窗 -->
					 <ul class="message-xial">
					 	<li><a href="${ctx}/web/messages.html" title="全部">全部</a></li>
					 	<li><a href="${ctx}/web/read.html" title="已读">已读</a></li>
					 	<li><a href="${ctx}/web/unRead.html" title="未读">未读</a></li>
					 </ul>
				</div>
			</div>
			<!-- 列表 -->
			<ul class="table table-striped table-bordered table-hover messageTab">
			<!-- 一条数据 -->
			<c:forEach items="${messageList}" var="message">
				<li>
					<div class="messageTab-div">
						<div class="message-toux">
							<c:if test="${empty message.sendFuUser.userAvatar}">
								<img src="${ctx}/web/images_qiDa/defTx.jpg" alt="头像"/>
							</c:if>
							<c:if test="${not empty message.sendFuUser.userAvatar}">
								<img src="${message.sendFuUser.userAvatar}" alt="头像"/>
							</c:if>
						</div>
						<div class="message-container">
							<div class="message-up">
								<a class="message-name" href="javascript:void(0)" title="用户名">${empty message.sendFuUser.nickName ? '佚名' : message.sendFuUser.nickName}</a>
								<span class="message-time">
									<c:if test="${((nowDate.time-message.createTime.time)/1000/60/60) < 1}">
										<fmt:formatNumber value="${(nowDate.time-message.createTime.time)/1000/60}" pattern="#0"/>分钟前
									</c:if>
									<c:if test="${((nowDate.time-message.createTime.time)/1000/60/60) < 24 && ((nowDate.time-message.createTime.time)/1000/60/60) >= 1}">
										<fmt:formatNumber value="${(nowDate.time-message.createTime.time)/1000/60/60}" pattern="#0"/>小时前
									</c:if>
									<c:if test="${((nowDate.time-message.createTime.time)/1000/60/60) >= 24}">
										<fmt:formatDate value="${message.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
									</c:if>
								</span>
							</div>
							<div class="message-down">
								<div class="message-info"><a href="${ctx}/web/views/${message.id}.html" title="消息">${message.message}</a></div>
								<div class="checkbox lichebox">
								    <label>
								      <input type="checkbox" name="msgFlag" value="${message.id}">
								    </label>
								    <c:if test="${message.isRead == 0}">
								    <img class="mesages-detial" src="${ctx}/web/images_qiDa/noread.png" alt="未读消息"/>
								    </c:if>
								    <c:if test="${message.isRead == 1}">
								    <img class="mesages-detial" src="${ctx}/web/images_qiDa/read.png" alt="已读消息"/>
								    </c:if>
								</div>
							</div>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
			<!-- 分页 -->
			<div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
					url="${ctx}/web/messages.html"
					totalNum="${totalCount}" curPageNum="${pageNo}"
					formId="pageForm">
				</domi:pagination>
			</div>
		</div>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>
	<!-- footer end -->
	
	<script type="text/javascript">
	// 删除
	$("#delMsg").click(function() {
		var $str = "";
		$('input[name="msgFlag"]:checked').each(function(){ 
			$str+=$(this).val()+","; 
		}); 
		$.ajax({
			type: 'POST',
			url: "${ctx}/web/delete.html",
			data: {'ids': $str},
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
	
	// 标记已读
	$("#readMsg").click(function() {
		var $str = "";
		$('input[name="msgFlag"]:checked').each(function(){ 
			$str+=$(this).val()+","; 
		}); 
		$.ajax({
			type: 'POST',
			url: "${ctx}/web/setIsRead.html",
			data: {'messageIds': $str},
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
		//下拉菜单
		$(".message-create").click(function(){
		var patCla = $(this).find("i").attr("class");
			if(patCla != "glyphicon glyphicon-chevron-up"){
				$(".message-xial").show();
				$(this).find("i").attr("class","glyphicon glyphicon-chevron-up");
			}else{
				$(".message-xial").hide();
				$(this).find("i").attr("class","glyphicon glyphicon-chevron-down");
			}
		});
		
		$(".message-xial li").on("click",function(){
			var creat = $(this).find("a").text();
			$(".message-create").find("span").text(creat);
			$(".message-xial").hide();
			$(".message-create").find("i").attr("class","glyphicon glyphicon-chevron-down");
		});
		
		//全选
		$(".message-box").click(function(){
		var checked = $(this).attr("class");
			if(checked != "checkbox message-box chec"){
				$(this).attr("class","checkbox message-box chec");
				$(this).find("input").attr("checked","checked");
				$(".lichebox").find("input").attr("checked","checked");
			}else{
				$(this).attr("class","checkbox message-box");
				$(this).find("input").attr("checked",false);
				$(".lichebox").find("input").attr("checked",false);
			}
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