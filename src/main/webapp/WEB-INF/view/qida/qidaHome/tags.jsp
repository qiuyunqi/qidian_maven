<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-标签</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.targets{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.targets a{
			color: #2db1e1 !important;
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
			<%@ include file="/WEB-INF/view/qida/common/expertChart.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 标签树 -->
			<div class="tagBody">
				<h4>标签树<span>这里列出了常用的标签，你可以选择喜欢的标签进行关注</span></h4>
				<div class="tag-container" id="tagTr">
					
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>	
	<!-- footer end -->
	<script type="text/javascript">
	$(function(){
		$.post("${ctx}/web/qida/treeTag.html", null, function(data){
			var jsonData=eval("("+data+")");
			var result = '';
			var arrText = [];
			for(var i=0; i<jsonData.array.length; i++){
				arrText.push("<div class='tag-bk'><h5><a href='${ctx}/web/qida/tagsQs/"+jsonData.array[i].tagPid+".html'>"+jsonData.array[i].tagPname+"</a></h5><ul class='tag-ul'>");
				for(var j=0; j<jsonData.array[i].tagChild.length; j++){
					arrText.push("<li><a href='${ctx}/web/qida/tagsQs/"+jsonData.array[i].tagChild[j].tagId+".html'>"+jsonData.array[i].tagChild[j].tagName+"</a></li>");
				}
				arrText.push("</ul></div>");
			}
           	result +=  arrText.join('');
           	$("#tagTr").append(result);
		});
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