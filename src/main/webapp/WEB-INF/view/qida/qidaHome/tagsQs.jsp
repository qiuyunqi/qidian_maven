<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-标签详情</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.question{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.question a{
			color: #2db1e1 !important;
		}
		.txIcon {padding: 0 !important;}
		.lfContainer{margin-left:0 !important;}
		.zhidao-question-unit-count2{padding:0 !important;}
		.zhidao-question-tags ul, .zhidao-question-unit-count2 ul{margin:0 !important;}
		.zhidao-question {margin: 0 !important;}
		.short-content{margin-bottom: 5px;}
		.contLists > li { padding: 10px 0;}
		.conterAll{border-top: 30px solid #fff;}
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
			<div class="tagsTitle"><span class="tagsTitle-txt">问题</span></div>
			<!-- 全部问题 -->
			<div class="communit-lists">
				<!-- 最新 -->
				<div class="commiInfo new">
					<ul class="contLists">
						<c:forEach items="${questionList}" var="question">
						<li>
							<div class="lfContainer">
								<div class="infoBody">
									<!-- 问题 -->
									<div class="zhidao-question">
											<a class="red tips" original-title="访问付费的问题需要支付1积分" onclick="payQuestion(${question.id})" target="_blank">${question.title}</a>
											<a class="money"><i class="${question.replyNum==0?'mony':'moneyAcv'}"></i>${question.reward}</a>
									</div>
									<!-- 回答/浏览/时间 -->
									<div class="zhidao-question-unit-count2">
										<ul>
											<li><span class="glyphicon glyphicon-comment"></span> ${question.replyNum}回答</li>
											<li><span class="glyphicon glyphicon-eye-open"></span> ${question.pageViews}浏览</li>
										</ul>
									</div>
									<!-- 回答 -->
									<%-- <div class="short-content">
										<div class="replayCont">${question.detail}</div>
										<div class="replayChan"></div>
										<c:if test="${not empty question.imgUrl}">
										<div class="short-content-img">
											<img src="${question.imgUrl}">
										</div>
										</c:if>
									</div> --%>
									<!-- 标签 -->
									<div class="zhid-tags">
										<ul>
											<c:forEach items="${question.tagQuestions}" var="tagQuestions">
												<li><a href="${ctx}/web/qida/tagsQs/${tagQuestions.qidaTags.id}.html" target="_blank">${tagQuestions.qidaTags.name}</a></li>
											</c:forEach>
										</ul>
									</div>
									<!-- 时间 -->
									<div class="questDates">
										<span class="glyphicon glyphicon-time"></span> 
										<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) < 1}">
											<fmt:formatNumber value="${(nowDate.time-question.updateTime.time)/1000/60}" pattern="#0"/>分钟前
										</c:if>
										<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) < 24 && ((nowDate.time-question.updateTime.time)/1000/60/60) >= 1}">
											<fmt:formatNumber value="${(nowDate.time-question.updateTime.time)/1000/60/60}" pattern="#0"/>小时前
										</c:if>
										<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) >= 24}">
											<fmt:formatDate value="${question.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
										</c:if>
									</div>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
					<!-- 分页 -->
					<div class="page">
					  	<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/web/qida/tagsQs/${tagId}.html"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
						</domi:pagination>
					</div>
				</div>	<!-- 最新结束 -->
			</div><!-- 所有内容end -->
		</div>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>
	<!-- footer end -->
	</body>
</html>
<script type="text/javascript">
   		//展开收起
   	/* $(".replayCont").each(function(){
		var obString=$(this).text();
		var length=obString.length;
		var sl=obString.substring(0,100)+"...";
		if(length>100){
			$(this).hide();
		   	$(this).next().html("<span class='rpyChan'>"+sl+"</span>"+"<a class='openAll'><展开全部></a>");
		   }else{
		   	$(this).show();
		   	$(this).text(obString);
		   	$(this).next().html();
		   }; 
	});
	
	$(".openAll").on("click",function(){
		var obString=$(this).parent().prev().text();
		var length=obString.length;
		var sl=obString.substring(0,100)+"...";
		var opentxt=$(this).text();
		if(opentxt=="<展开全部>"){
			$(this).prev().text(obString);
			$(this).text("<收起>");
		}else{
			$(this).prev().text(sl);
			$(this).text("<展开全部>");
		}
	}); */
	//展开收起end
   		
		$(function(){
			$(document).on("mouseover",".tips",function(){
				$(".tips").tipsy({gravity: 's'}); 
			})
   		});
		
	//查看付费问题
	function payQuestion(id){
		layer.open({
	    content: "确认查看问题？",
	    btn: ["确定","取消"],
	    shadeClose: false,
	    yes: function(){
		  	$.post("${ctx}/web/qida/payQuestion/"+id+".html",null,function(data){
		  		if(data=="-1"){
					layer.open({
		 			    content: "未登录",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
	 				});
	 				location.href="${ctx}/web/ai/meindex.html";
					return false;
				}
				if(data=="-2"){
					layer.open({
		 			    content: "您的积分不足！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
					return false;
				}
				if(data=="1"){
					location.href="${ctx}/web/qida/questionPay/"+id+".html";
					return false;
				}
				layer.open({
	 			    content: "支付成功，可以查看问题！",
	 			    btn: ["确定"],
	 			    shadeClose: false,
	 			    yes: function(){
	 			        layer.closeAll();
	 			    }
	 			});
	 			location.href="${ctx}/web/qida/questionPay/"+id+".html";
		 	})
	 	}
	 	})
	};
	
	
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
