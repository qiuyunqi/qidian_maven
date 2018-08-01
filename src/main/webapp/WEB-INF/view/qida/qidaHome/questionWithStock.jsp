<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-全部问题</title>
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
		.zhidao-question-unit-count2{padding:0 !important;}
		.zhidao-question-tags ul, .zhidao-question-unit-count2 ul{margin:0 !important;}
		.zhidao-question {margin: 0 !important;}
		.short-content{margin-bottom: 5px;}
		.contLists > li { padding: 10px 0;}
		.question-nav ul li{margin: 0 !important;}
		.question-nav{height:31px;}
		.question-nav ul {height: 31px;margin: 10px 0;}
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
			<!-- 全部/待回答 -->
			<div class="communit-nav">
				<ul>
					<li class="communit-active"><a href="${ctx}/web/qida/questionAll.html" title="全部">全部</a></li>
					<li><a href="${ctx}/web/qida/questionNo.html" title="待回答">待回答</a></li>
				</ul>
			</div>
			<!-- 最新/最热/加精 -->
			<!-- <div class="question-nav">
				<ul>
					<li class="question-active"><a href="javascript:void(0)" title="最新">最新</a></li>
					<li><a href="javascript:void(0)" title="最热">最热</a></li>
				</ul>
			</div> -->
			<!-- 最新/最热/加精 的内容 -->
			<div class="communit-lists">
				<!-- 最新 -->
				<div class="commiInfo new">
					<ul class="contLists">
						<c:forEach items="${questions}" var="question">
						<li>
							<div class="tx"  onmouseover='mousove($(this),80,30,${question.fuUser.id})'  onmouseout='mousout($(this))'><a class="txIcon" href="javascript:void(0)" title="头像"><img src="${empty question.fuUser.userAvatar?'../images_qiDa/tx.png':question.fuUser.userAvatar}" alt="头像"/></a></div>
							<div class="lfContainer">
								<div class="infoBody">
									<!-- 问题 -->
									<div class="zhidao-question">
										<c:if test="${question.isReward==0}">
											<a class="blue" href="${ctx}/web/qida/question/${question.id}.html" target="_blank">${question.title}</a>
										</c:if>
										<c:if test="${question.isReward==1}">
											<a class="red tips" original-title="访问付费的问题需要支付1积分" onclick="payQuestion(${question.id})" target="_blank">${question.title}</a>
											<a class="money" href="javascript:void(0)" title="付费"><i></i>${question.reward}</a>
										</c:if>
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
									<!-- 实现展开收起效果添加了两个div -->
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
									<!-- 股票代码 -->
									<div class="question-stock">
										<ul>
											<c:forEach items="${question.stockQuestions}" var="stockQuestion">
												<li><a href="${ctx}/web/qida/find/${stockQuestion.stockInfo.id}.html" target="_blank">${stockQuestion.stockInfo.name}</a></li>
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
					<%-- <div class="page">
					  	<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/web/qida/questionAll.html"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
								<domi:paramTag name="keyword" value="${keyword}"/>
						</domi:pagination>
					</div> --%>
				</div>	<!-- 最新结束 -->
				<!-- 最热 -->
				<%-- <div class="hotques commiInfo">
					<ul class="contLists ">
						<c:forEach items="${questionsHot}" var="hot">
						<li>
							<div class="tx"  onmouseover='mousove($(this),80,30,${hot.fuUser.id})'  onmouseout='mousout($(this))'><a class="txIcon" href="javascript:void(0)" title="头像"><img src="${empty hot.fuUser.userAvatar?'../images_qiDa/tx.png':hot.fuUser.userAvatar}" alt="头像"/></a></div>
							<div class="lfContainer">
								<div class="infoBody">
									<!-- 问题 -->
									<div class="zhidao-question">
										<c:if test="${hot.isReward==0}">
											<a class="blue" href="${ctx}/web/qida/question/${hot.id}.html" target="_blank">${hot.title}</a>
										</c:if>
										<c:if test="${hot.isReward==1}">
											<a class="red tips" original-title="访问付费的问题需要支付1积分" onclick="payQuestion(${hot.id})" target="_blank">${hot.title}</a>
											<a class="money" href="javascript:void(0)" title="付费"><i></i>${hot.reward}</a>
										</c:if>
									</div>
									<!-- 回答/浏览/时间 -->
									<div class="zhidao-question-unit-count2">
										<ul>
											<li><span class="glyphicon glyphicon-comment"></span> ${hot.replyNum}回答</li>
											<li><span class="glyphicon glyphicon-eye-open"></span> ${hot.pageViews}浏览</li>
										</ul>
									</div>
									<!-- 回答 -->
									<div class="short-content">
										<div class="replayCont">${hot.detail}</div>
										<div class="replayChan"></div>
										<c:if test="${not empty hot.imgUrl}">
										<div class="short-content-img">
											<img src="${hot.imgUrl}">
										</div>
										</c:if>
									</div>
									<!-- 标签 -->
									<div class="zhid-tags">
										<ul>
											<c:forEach items="${hot.tagQuestions}" var="tagQuestions">
												<li><a href="${ctx}/web/qida/tagsQs/${tagQuestions.qidaTags.id}.html" target="_blank">${tagQuestions.qidaTags.name}</a></li>
											</c:forEach>
										</ul>
									</div>
									<!-- 时间 -->
									<div class="questDates">
										<span class="glyphicon glyphicon-time"></span> 
										<c:if test="${((nowDate.time-hot.updateTime.time)/1000/60/60) < 1}">
											<fmt:formatNumber value="${(nowDate.time-hot.updateTime.time)/1000/60}" pattern="#0"/>分钟前
										</c:if>
										<c:if test="${((nowDate.time-hot.updateTime.time)/1000/60/60) < 24 && ((nowDate.time-hot.updateTime.time)/1000/60/60) >= 1}">
											<fmt:formatNumber value="${(nowDate.time-hot.updateTime.time)/1000/60/60}" pattern="#0"/>小时前
										</c:if>
										<c:if test="${((nowDate.time-hot.updateTime.time)/1000/60/60) >= 24}">
											<fmt:formatDate value="${hot.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
										</c:if>
									</div>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</div> --%>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/view/qida/common/userInfo.jsp" %>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/footer.jsp" %>
	</body>
</html>
<script type="text/javascript">
	//最新最热加精切换
		var $liK = $('.question-nav ul li');
		var $ulK = $('.communit-lists .commiInfo');
   		$liK.click(function(){
   			var $this = $(this);
			var $t = $this.index();
			$liK.removeClass();
			$this.attr("class","question-active");
			$ulK.css('display','none');
			$ulK.eq($t).css('display','block');
   		}); 
   		
		$(function(){
			$(document).on("mouseover",".tips",function(){
				$(".tips").tipsy({gravity: 's'}); 
			})
   		});
   		
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
	}
</script>
