<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-社区</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.community{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.community a{
			color: #2db1e1 !important;
		}
		.lfContainer {
		    margin-left: 124px !important;
		}
		.txIcon{
			padding:0 !important;
		}
		.contLists>li {
		    padding: 10px 0;
		}
		.question-stock{
			margin-top:10px;
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
		<!-- banner -->
		<div class="community-ban">
			<img src="${ctx}/web/images_qiDa/banner.png" alt="奇答问股"/>
			<a href="javascript:void(0)" title="奇答问股">奇答问股是什么？</a> - <a href="javascript:void(0)" title="付费问题">付费问题</a> - <a href="javascript:void(0)" title="联系站点管理员">联系站点管理员</a>
		</div>
		<!-- 左边部分 -->
		<div class="sideLft">
			<%@ include file="/WEB-INF/view/qida/common/expertChart.jsp" %>
			<%@ include file="/WEB-INF/view/qida/common/hotTopic.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<!-- 精彩问答 -->
			<div class="hotQues">
				<h4>精彩问答<a href="${ctx}/web/qida/questionAll.html" title="更多">more>></a></h4>
				<ul class="hotQues-list">
					<c:forEach items="${jcwd}" var="question">
						<li><a href="${ctx}/web/qida/question/${question[1]}.html" title="问题">${question[2]}</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 全部动态/我的圈子 -->
			<div class="communit-nav">
				<ul>
					<li class="communit-active" id="dtCom"><a href="javascript:void(0)" title="全部动态">全部动态</a></li>
					<li id="circles"><a href="javascript:void(0)" title="我的圈子">我的圈子</a></li>
				</ul>
			</div>
			<!-- 全部动态/我的圈子 的内容 -->
			<div class="communit-lists">
				<!-- 全部动态 -->
				<div class="dtAll commiInfo">
					<ul class="contLists" id="allDynamic">
					</ul>
					<!-- 查看更多 -->
					<a class="lookMore" id="lookMore1" onclick="lookMore1()">查看更多<span class="glyphicon glyphicon-chevron-down"></span></a>
				</div>	<!-- 全部动态结束 -->
				<!-- 我的圈子 -->
				<div class="cirel commiInfo">
					<ul class="contLists" id="myGroup">
					</ul>
					<!-- 查看更多 -->
					<a class="lookMore" id="lookMore2" onclick="lookMore2()">查看更多<span class="glyphicon glyphicon-chevron-down"></span></a>
				</div>	<!-- 我的圈子结束 -->
			</div><!-- 所有内容end -->
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/footer.jsp" %>
	</body>
</html>
<script type="text/javascript">
		var count = 0;
		var counte = 0;
		
		$(function(){
			$(document).on("mouseover",".tips",function(){
				$(".tips").tipsy({gravity: 's'}); 
			})
			$("#lookMore1").hide();
			$("#lookMore2").hide();
			$(".chartBtn").hide();
			lookMore1();
   		});
		//tab标签切换
		$("#dtCom").click(function(){
			$(this).parent().find("li").attr("class","")
			$(this).attr("class","communit-active");
			$(".dtAll").show();
			$(".cirel").hide();
			count=0;
			$("#lookMore1").hide();
			$("#allDynamic").find("li").remove(); 
			lookMore1();
		});
		$("#circles").click(function(){
			$(this).parent().find("li").attr("class","")
			$(this).attr("class","communit-active");
			$(".dtAll").hide();
			$(".cirel").show();
			counte=0;
			$("#lookMore2").hide();
			$("#myGroup").find("li").remove(); 
			lookMore2();
		});
		
   		
   		
   		//全部动态
		function lookMore1(){
		   var num = 10;
		   var pageStart = 0,pageEnd = 0;
           $.ajax({
               	type: 'POST',
               	url: '${ctx}/web/qida/dynamicData.html',
               	dataType: 'json',
               	success: function(data){
		                var length=data.array.length;
			           	var result = '';
	                   	count++;
	                   	pageEnd = num * count;
	                   	pageStart = pageEnd - num;
	                   	if(length>0){
		                   	for(var i = pageStart; i < pageEnd; i++){
		                    	var arrText = [];
			                 	arrText.push("<li><div class='tx' onmouseover='mousove($(this),80,30,"+data.array[i].userId+")'  onmouseout='mousout($(this))'><a class='txIcon'><img src='"+data.array[i].userAvatar+"'/></a></div>");
								arrText.push("<div class='lfContainer'>");
								arrText.push("<div class='userNm'>"+data.array[i].nickName+" "+data.array[i].type+"了</div>");//<span class='zjInfo'>已邀请XX<span>
								arrText.push("<div class='infoBody'><div class='zhidao-question'>");
								arrText.push("<a class='red tips' original-title='访问付费的问题需要支付1积分' onclick='payQuestion("+data.array[i].id+")' target='_blank'>"+data.array[i].title+"</a>");
								if(data.array[i].replyNum==0){
									arrText.push("<a class='money'><i class='mony'></i>"+data.array[i].reward+"</a>");
								}
								if(data.array[i].replyNum>0){
									arrText.push("<a class='money'><i class='moneyAcv'></i>"+data.array[i].reward+"</a>");
								}
								arrText.push("</div><div class='zhidao-question-unit-count3'><ul><li><span class='glyphicon glyphicon-comment'></span>"+data.array[i].replyNum+"回答</li>");
								arrText.push("<li><span class='glyphicon glyphicon-eye-open'></span>"+data.array[i].pageViews+"浏览</li></ul></div>");
								arrText.push("<div class='qs-tags'><ul>");
								for(var j=0; j<data.array[i].tagQuestions.length; j++){
									arrText.push("<li><a href='${ctx}/web/qida/tagsQs/"+data.array[i].tagQuestions[j].tagId+".html' target='_blank'>"+data.array[i].tagQuestions[j].tagName+"</a></li>");
								}
								arrText.push("</ul></div>");
								//股票代码
								arrText.push("<div class='question-stock'>");
								arrText.push("<ul>");
								for(var j=0; j<data.array[i].stockQuestions.length; j++){
									arrText.push("<li><a href='${ctx}/web/qida/find/"+data.array[i].stockQuestions[j].stockId+".html' target='_blank'>"+data.array[i].stockQuestions[j].stockName+"</a></li>");		
								}
								arrText.push("</ul></div>");
								
								arrText.push("<div class='zhidao-question-unit-count2'><ul>");
								arrText.push("<li><span class='glyphicon glyphicon-time'></span> ");
								if(data.array[i].hhDiffer<1){
									arrText.push(data.array[i].mmDiffer+"分钟前");
								}
								if(data.array[i].hhDiffer<24 && data.array[i].hhDiffer>=1){
									arrText.push(data.array[i].hhDiffer+"小时前");
								}
								if(data.array[i].hhDiffer>=24){
									arrText.push(data.array[i].createTime);
								}
								arrText.push("</li></ul></div></div></div></li>");		
		                       	result +=  arrText.join('');
		                       	if((i + 1) >= data.array.length){
		                           break;
		                       	}
		                   }
	                   }
	                   setTimeout(function(){
	                       	$("#allDynamic").append(result);
	                       	if(length < (num*count) || length <= 10){
	                   			$("#lookMore1").hide();
	                   		}else{
	                   			$("#lookMore1").show();
	                   		}
	                   },1000);
               	},//success结束
               	error: function(xhr, type){
               	}  
           	});//ajax结束
		}
		
		
		//我的圈子
		function lookMore2(){
		   // 每次加载5个
		   var num = 10;
		   var pageStart = 0,pageEnd = 0;
           $.ajax({
               	type: 'POST',
               	url: '${ctx}/web/qida/myGroupData.html',
               	dataType: 'json',
               	success: function(data){
		                var length=data.array.length;
			           	var result = '';
	                   	counte++;
	                   	pageEnd = num * counte;
	                   	pageStart = pageEnd - num;
	                   	if(length>0){
		                   	for(var i = pageStart; i < pageEnd; i++){
		                    	var arrText = [];
			                 	arrText.push("<li><div class='tx' onmouseover='mousove($(this),80,30,"+data.array[i].userId+")'  onmouseout='mousout($(this))'><a class='txIcon'><img src='"+data.array[i].userAvatar+"'/></a></div>");
								arrText.push("<div class='lfContainer'>");
								arrText.push("<div class='userNm'>"+data.array[i].nickName+" "+data.array[i].type+"了</div>");//<span class='zjInfo'>已邀请XX<span>
								arrText.push("<div class='infoBody'><div class='zhidao-question'>");
								arrText.push("<a class='red tips' original-title='访问付费的问题需要支付1积分' onclick='payQuestion("+data.array[i].id+")' target='_blank'>"+data.array[i].title+"</a>");
								if(data.array[i].replyNum==0){
									arrText.push("<a class='money'><i class='mony'></i>"+data.array[i].reward+"</a>");
								}
								if(data.array[i].replyNum>0){
									arrText.push("<a class='money'><i class='moneyAcv'></i>"+data.array[i].reward+"</a>");
								}
								arrText.push("</div><div class='zhidao-question-unit-count3'><ul><li><span class='glyphicon glyphicon-comment'></span>"+data.array[i].replyNum+"回答</li>");
								arrText.push("<li><span class='glyphicon glyphicon-eye-open'></span>"+data.array[i].pageViews+"浏览</li></ul></div>");
								arrText.push("<div class='qs-tags'><ul>");
								for(var j=0; j<data.array[i].tagQuestions.length; j++){
									arrText.push("<li><a href='${ctx}/web/qida/tagsQs/"+data.array[i].tagQuestions[j].tagId+".html' target='_blank'>"+data.array[i].tagQuestions[j].tagName+"</a></li>");
								}
								arrText.push("</ul></div>");
								//股票代码
								arrText.push("<div class='question-stock'>");
								arrText.push("<ul>");
								arrText.push("<li><a href='javascript:void(0)' target='_blank'>sh600810</a></li>");		
								arrText.push("</ul></div>");
											
								arrText.push("<div class='zhidao-question-unit-count2'><ul>");
								arrText.push("<li><span class='glyphicon glyphicon-time'></span> ");
								if(data.array[i].timeInterval<24){
									arrText.push(data.array[i].timeInterval+"小时前");
								}
								if(data.array[i].timeInterval>=24){
									arrText.push(data.array[i].createTime);
								}
								arrText.push("</li></ul></div></div></div></li>");		
		                       	result +=  arrText.join('');
		                       	if((i + 1) >= data.array.length){
		                           break;
		                       	}
		                   }
	                   }
	                   setTimeout(function(){
	                       	$("#myGroup").append(result);
	                       	if(length < (num*counte) || length <= 10){
	                   			$("#lookMore2").hide();
	                   		}else{
	                   			$("#lookMore2").show();
	                   		}
	                   },1000);
               	},//success结束
               	error: function(xhr, type){
               	}  
           	});//ajax结束
		}
		
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

