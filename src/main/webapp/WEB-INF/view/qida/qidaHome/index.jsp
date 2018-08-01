<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}首页</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<script src="${ctx}/web/js_qiDa/slider.js"></script>
	<style>
		input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
		color: #004e99;
		}
		input:-moz-placeholder, textarea:-moz-placeholder {
		color: #004e99;
		}
		input::-moz-placeholder, textarea::-moz-placeholder {
		color: #004e99;
		}
		input:-ms-input-placeholder, textarea:-ms-input-placeholder {
		color: #004e99;
		} 
		.indexHom{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		    box-sizing: border-box;
    		height: 82px;
		}
		.indexHom a{
			color: #2db1e1 !important;
		}
		.navLf2{
			position: fixed;
			top:0px;
			background: #28b2e3 none repeat scroll 0 0;
		    height: 68px;
		    min-width: 1000px;
		    z-index:999;
		    width:100%;
		}
		.question-stock{
			margin-top:10px;
		}
	</style>
  </head>
  <body>
  <%@ include file="/WEB-INF/view/qida/common/qqConsult_hhr.jsp" %>
 <div class="bodyHget">
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 导航 -->
    <%@ include file="/WEB-INF/view/qida/common/topIndex.jsp" %>
	<!-- 登录/注册 -->
	<div class="banBody">
		<div class="container banBody-container">
  	  		<img src="${ctx}/web/images_qiDa/xiaohe.png" alt="奇答问股"/>
  	  		<div class="btnBody-text"><img src="${ctx}/web/images_qiDa/indexbackg.png" /></div>
  	  		<div class="btnBody">
  	  			<c:if test="${empty fuUser}">
	  		 	<a class="btn logBtn" onclick="login()" title="立即登录" href="javascript:void(0)"><i></i><span>立即登录</span></a>
	  		 	<a class="btn regBtn" onclick="login()" title="注册账号" href="javascript:void(0)"><i></i><span>注册账号</span></a>
	  		 	</c:if>
	  		</div>
  		</div>
	</div>
	<!-- 浏览社区/更多问题 -->
	<div class="lookBody" id="navLf">
		<div class="container">
  	  		<div class="lookBody-btn">
	  		 	<a class="btn lookBtn" href="${ctx}/web/qida/community.html" title="浏览社区"><i></i><span>浏览社区</span></a>
	  		 	<a class="btn quseBtn"  href="${ctx}/web/qida/questionAll.html" title="更多问题"><i></i><span>更多问题</span></a>
	  		</div>
  		</div>
	</div>
	<!-- 正文内容 -->
	<div class="container">
	<!-- 左边 -->
		<div class="indexLft">
			<ul class="contLists">
			</ul>
			<!-- 查看更多 -->
			<div class="lookMoreBody">
				<a class="lookMore" onclick="lookMore()" title="查看更多">查看更多<span class="glyphicon glyphicon-chevron-down"></span></a>
			</div>
		</div>
		<!-- 右边 -->
		<div class="indexRgt">
			<!-- 播报 -->
			<div class="txtScroll-top">
				<h4><i class="txtScroll-titIm"></i><a href="${ctx}/web/qida/broadcast.html">实时播报</a></h4>
				<div class="baocont">
					<ul class="infoList">
					</ul>
				</div>
			</div>
		<!-- 播报end -->
			<div class="clear"></div>
			<div class="indexRgt-lookMore">
				<a class="indexRgt-lookBtn"  title="查看更多" onclick="lookBroad()">查看更多</a>
			</div>
		</div>
		
	</div>
 </div>
 <%@ include file="/WEB-INF/view/qida/common/userInfo.jsp" %>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footer.jsp" %>
  </body>
</html>
<script type="text/javascript">
//浏览社区，更多问题随滚动条移动
		window.onscroll = function(){
			var scrolltop = document.documentElement.scrollTop+document.body.scrollTop;
			if(scrolltop<524){
				$("#navLf").attr("class","lookBody");
			}else{
				$("#navLf").attr("class","navLf2");
			}
		};

		
		//导航样式
		$(".hmenuul li a").each(function(){
			var indexTxt = $(this).text();
			if(indexTxt=="奇答"){
				$(this).attr("class","hmenuula");
			}
		});
		
		//超过字数省略号表示
					$(".infoList-p a").each(function(){
					var obString=$(this).text();
					var length=$(this).text().length;
					var sl=$(this).text().substring(0,80)+"...";
					if(length>80){
					   		$(this).text(sl);
					   }else{
					   		$(this).text(obString);
					   }; 
				}); //超过字数省略号表示end
				
				//播报文字向上滚动
				//DOM结构绘制完毕后就执行
			$(document).ready(function(){
			gundong(".baocont")//实时播报向上滚动
			});
		
		function login(){
			location.href = "${ctx}/web/ai/meindex.html";
			return false;
		}

		$(function(){
			$(document).on("mouseover",".tips",function(){
				$(".tips").tipsy({gravity: 's'}); 
			})
   		});
   		
   		var counter = 0;
   		lookMore();
    
		//查看更多
		function lookMore(){
		   // 每次加载5个
		   var num = 10;
		   var pageStart = 0,pageEnd = 0;
           $.ajax({
               	type: 'POST',
               	url: '${ctx}/web/qida/indexData.html',
               	dataType: 'json',
               	success: function(data){
		                var length=data.array.length;
			           	var result = '';
	                   	counter++;
	                   	pageEnd = num * counter;
	                   	pageStart = pageEnd - num;
	                   	if(length>0){
		                   	for(var i = pageStart; i < pageEnd; i++){
		                    	var arrText = [];
			                 	arrText.push("<li class='contLists-li'><div class='tx' onmouseover='mousove($(this),80,60,"+data.array[i].userId+")'  onmouseout='mousout($(this))'><a class='txIcon'><i class='"+data.array[i].rank+"'></i><img src='"+data.array[i].userAvatar+"'/></a></div>");
								arrText.push("<div class='lfContainer'>");
								arrText.push("<div class='userNm'>"+data.array[i].nickName+" 提问了");
								if(data.array[i].hhDiffer>2 && data.array[i].replyNum==0 && data.array[i].answerUserId>0){
									arrText.push("<span class='zjInfo'>超时未回答，可以抢答<span>");
								}
								if(data.array[i].hhDiffer<=2 && data.array[i].answerUserId>0){
									arrText.push("<span class='zjInfo'>已邀请"+data.array[i].answerNickName+"<span>");
								}
								arrText.push("</div><div class='infoBody'><div class='zhidao-question'>");
								arrText.push("<a class='red tips' original-title='访问付费的问题需要支付1积分' onclick='payQuestion("+data.array[i].id+")' target='_blank'>"+data.array[i].title+"</a>");
								if(data.array[i].replyNum==0){
									arrText.push("<a class='money'><i class='mony'></i>"+data.array[i].reward+"</a>");
								}
								if(data.array[i].replyNum>0){
									arrText.push("<a class='money'><i class='moneyAcv'></i>"+data.array[i].reward+"</a>");
								}
								arrText.push("</div>");
								arrText.push("</div><div class='qs-tags'><ul>");
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
								arrText.push("<li><span class='glyphicon glyphicon-comment'></span>"+data.array[i].replyNum+"回答</li>");
								arrText.push("<li><span class='glyphicon glyphicon-eye-open'></span>"+data.array[i].pageViews+"浏览</li>");
								arrText.push("<li><span class='glyphicon glyphicon-time'></span> ");
								if(data.array[i].hhDiffer<1){
									arrText.push(data.array[i].mmDiffer+"分钟前");
								}
								if(data.array[i].hhDiffer<24 && data.array[i].hhDiffer>=1){
									arrText.push(data.array[i].hhDiffer+"小时前");
								}
								if(data.array[i].hhDiffer>=24){
									arrText.push(data.array[i].updateTime);
								}
								arrText.push("</li></ul></div></div></div></li>");		
		                       	result +=  arrText.join('');
		                       	if((i + 1) >= data.array.length){
		                           break;
		                       	}
		                   }
	                   }
	                   setTimeout(function(){
	                       	$('.contLists').append(result);
	                       	if(length < (num*counter) || length <= 10){
	                   			$(".lookMore").hide();
	                   		}else{
	                   			$(".lookMore").show();
	                   		}
	                   },1000);
               	},//success结束
               	error: function(xhr, type){
               	}  
           	});//ajax结束
	}
	
	var count=0;
	lookBroad();
	function lookBroad(){
		// 每次加载5个
		   var num = 10;
		   var pageStart = 0,pageEnd = 0;
           $.ajax({
               	type: 'POST',
               	url: '${ctx}/web/qida/broadData.html',
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
			                 	arrText.push("<li><span class='date'>"+data.array[i].createTime+"</span><p class='infoList-p'><a href='javascript:void(0)'>"+data.array[i].content+"</a></p></li>");
		                       	result +=  arrText.join('');
		                       	if((i + 1) >= data.array.length){
		                           break;
		                       	}
		                   }
	                   }
	                   setTimeout(function(){
	                       	$('.infoList').append(result);
	                       	if(length < (num*count) || length <= 10){
	                   			$(".indexRgt-lookBtn").hide();
	                   		}else{
	                   			$(".indexRgt-lookBtn").show();
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