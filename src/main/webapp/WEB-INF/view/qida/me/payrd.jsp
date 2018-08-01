<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-设置(出款记录)</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.txIcon {padding: 0 !important;}
		.lfContainer {
		    margin-left: 124px !important;
		}
	</style>
  </head>
  <body>
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 页头 -->
  	<%@ include file="../common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<div class="kongge"></div>
		<!-- 出款记录/收入记录 -->
		<div class="communit-nav">
			<ul>
				<li class="communit-active"><a href="javascript:void(0)" title="出款记录">出款记录</a></li>
				<li><a href="${ctx}/web/ai/incomerd.html" title="收入记录">收入记录</a></li>
				<li><a href="${ctx}/web/ai/exchange.html" title="积分兑换">积分兑换</a></li>
			</ul>
		</div>
		<!-- 出款记录 -->
		<table class="table table-striped table-bordered table-hover setTab" cellpadding="0" cellspacing="0" width="100%" border="0">
			<tr>
			<!-- 	<th class="blueCol"><a class="order">订单号<span class="glyphicon glyphicon-chevron-down"></span></a></th> -->
				<th>序号</th>
				<th class="blueCol">支付时间</th>
				<th class="blueCol">消费详情</th>
				<th>支付金额</th>
				<th>支付方式</th>
			</tr>
			<c:forEach items="${findListBy}" var="moneyDetail" varStatus="index">
			<tr>
				<td>${index.index+1}</td>
				<td><fmt:formatDate value="${moneyDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${moneyDetail.comment}</td>
				<td>${moneyDetail.score}</td>
				<td>积分</td>
			</tr>
			</c:forEach>
		</table>
		
		<!-- 分页 -->
		<div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/web/ai/payrd.html"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		     <domi:paramTag name="pageSize" value="${pageSize}"/>
			</domi:pagination>
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="../common/footer.jsp" %>
	
	<script type="text/javascript">
		//点击订单号样式改变
		$(".order").click(function(){
		var patCla = $(this).parent().attr("class");
			if(patCla == "blueCol"){
				$(this).parent().attr("class","blueCol actv");
				$(this).find("span").attr("class","glyphicon glyphicon-chevron-up");
			}else{
				$(this).parent().attr("class","blueCol");
				$(this).find("span").attr("class","glyphicon glyphicon-chevron-down");
			}
		});
	</script>
  </body>
</html>