<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-股票树</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.stock{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.stock a{
			color: #2db1e1 !important;
		}
		.tag-bk h5 a{
			color:#fff;
			display: block;
		}
		.tag-container .tag-bk {
		    float: left;
		    height: 85px;
		    padding: 0 25px 15px 0;
		    width: 25%;
		}
		.tag-ul li {
		    display: inline-block;
		    float: left;
		    font-size: 13px;
		    width: 33%;
		    margin-right:0;
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
			<!-- 热点股票 -->
			<div class="stock-body">
				<h4>热点股票</h4>
				<ul class="stock-ul">
					<c:forEach items="${stockList}" var="stockInfo">
					<li><span class="stock-name">${stockInfo.name}</span><span class="stock-number">${stockInfo.code}</span></li>
					</c:forEach>
				</ul>
			</div>
			<div class="kongge"></div>
			<%@ include file="/WEB-INF/view/qida/common/expertChart.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 股票树 -->
			<div class="tagBody">
				<h4>股票树<span>这里列出了热门的股票，你可以选择喜欢的股票进行关注</span></h4>
				<div class="tag-container" id="tagTr">
					<!-- 板块 -->
					<c:forEach items="${typeList}" var="type">
					<div class='tag-bk'>
						<h5><a href="${ctx}/web/qida/stock/${type.id}.html">${type.name}</a></h5>
						<ul class='tag-ul'>
							<c:forEach items="${type.stockInfos}" var="stockInfo" begin="0" end="2">
							<li><a href="${ctx}/web/qida/find/${stockInfo.id}.html">${stockInfo.name}</a></li>
							</c:forEach>
						</ul>
					</div>
					</c:forEach>
					<!-- 板块结束 -->	
				</div>
			</div>
		</div>
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