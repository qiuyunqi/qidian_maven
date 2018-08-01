<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-实时播报</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.lfContainer {
		    margin-left: 124px !important;
		}
	</style>
  </head>
  <body>
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 页头 -->
  	<%@ include file="/WEB-INF/view/qida/common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<!-- 左边部分 -->
		<div class="sideLft">
			<div class="kongge"></div>
			<%@ include file="/WEB-INF/view/qida/common/hotTopic.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 实时播报 -->
			<div class="broadcast-title">
				<div class="broadcast-tit">
					<input id="soundalertinput" type="checkbox" checked="" autocomplete="off" name="mess" onclick="changesoundalert();"/>
					<label id="showts" class="ched"  for="soundalertinput">声音提醒<em></em></label>
					<c:if test="${isVoice==1}">
					<div id="soundalert">
						<audio autoplay="autoplay" src="${ctx}/web/images_qiDa/alert.mp3"></audio>
					</div>
					</c:if>
					<div class="dropdown_box">
						 <!-- 搜索 -->
						<form class="navbar-fr " role="search" action="${ctx}/web/qida/broadcast.html">
						     <div class="form-grou searhBody">
						        <input name="createDay" type="text" class="form-control navbar-left" placeholder="输入日期搜索" value="${createDay}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><i class="riqi"></i>
							    <button class="btn searBtn" type="submit"><img src="${ctx}/web/images_qiDa/search.png" /></button>
						     </div>
						</form> 
					</div>
					<em>股票实时播报</em>
					</div>
					<div class="line">竖线ico</div>
			</div>
			<hr class="baoconHr">
			<!-- 播报列表 -->
			<div class="baocontDetail">
				<!-- 当天 -->
				<div class="baocontDetail-date">
					<ul class="infoList">
						<c:forEach items="${broadcasts}" var="cast" varStatus="stat">
						<c:if test="${stat.index==0}">
						<h4><i class="txtScroll-titIm"></i><fmt:formatDate value="${broadcasts[stat.index].createDay}" pattern="yyyy-MM-dd"/></h4>
						</c:if>
						<li>
							<span class="date"><fmt:formatDate value="${cast.createTime}" pattern="HH:mm"/></span>
							<p class="infoList-p">${cast.content}</p>
						</li>
						<c:if test="${broadcasts[stat.index].createDay != broadcasts[stat.index+1].createDay && stat.index < fn:length(broadcasts)-1}">
						<h4><i class="txtScroll-titIm"></i><fmt:formatDate value="${broadcasts[stat.index+1].createDay}" pattern="yyyy-MM-dd"/></h4>
						</c:if>
						</c:forEach>
					</ul>
				</div>
				<!-- 分页 -->
				<nav class="page">
					  	<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/web/qida/broadcast.html"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
						</domi:pagination>
					</nav>
				</div>
			<!-- 列表end -->
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/footer.jsp" %>
	<script>
			//播报声音提醒
			/* var html5 = 1;
			if (document.createElement('canvas').getContext==undefined){
				html5 = 0;
			}
			if (getcookie('aiwengu_soundalert')=='hidden'){
				document.getElementById('soundalertinput').checked = false;
				$('#showts').attr('class','chd');
			} */
			
			// 1分钟访问一次
			setInterval(index, 60000);
			function index(){
				location.href=location.href;
			}
			
			$(function(){
				if(${isVoice==1}){//如果用户设置了需要提醒
					$('#showts').attr('class','chd');
				}else{
					$('#showts').attr('class','ched');
				}
			})
			
			function changesoundalert(){
				if(document.getElementById('soundalertinput').checked == false){
					//alert('打开提醒');
					$.post("${ctx}/web/ai/setVoice.html",{"isVoice":1},function(data){
						$('#showts').attr('class','chd');
					})
				} else {
					//alert('关闭提醒');
					$.post("${ctx}/web/ai/setVoice.html",{"isVoice":0},function(data){
						$('#showts').attr('class','ched');
					})
				}
			}
	</script>
	</body>
</html>
