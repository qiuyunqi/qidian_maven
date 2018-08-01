<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<%-- <%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%> --%>
<title>${title}－正文</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/line.css"></link>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/highcharts.js"></script>
<%-- <script src="${ctx}/web/js/highcharts-more.js"></script> --%>
<style>
html{width: 100%;height:auto;}
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fff;height:auto; }
input{border:none;background: #fff;-webkit-appearance: none;}
input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
}
.hrline{height:4px;width:100%;background: #ededed;}
.gLisInfo a{color:#000;}
@media screen and (max-width:240px){
	.care img{padding:4px 0;width:4%;}
	.cenSize{font-size:0.9rem;}
	.progVal{font-size: 0.5rem;;}
	.discussLs{width: 70%;padding:0;}
	.soSmalSize{font-size: 0.4rem;}
	.progress{width: 55%;}
	.smallSize {font-size: 0.65rem;}
}
@media screen and (min-width:768px){
	.care img{padding:0 2px;width: 3%;}
	.soSmalSize{font-size: 1.3rem;}
}
</style>
</head>
<body>
	<div class="kline-wrapper">
		<div class="discussAll borTop clear">
			<div class="disBody">
				<div class="goodList">
					 <table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
						<tr>
							<td>
							    <div class="gLis userName">
							         <img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
							         <div class="gLisName yeloColor">鲁***0</div>
							          <div class="date lisCol">2016-04-28 11:25:28</div>
							    </div>
							    <div class="clear"></div>
							    <div class="gLis teacherName">
							        <div class="gLisInfo"><a href="javascript:void(0);">巴菲特说，他每天摄取总热量的四分之一来自于可口可乐公司的饮料及相关产品，是否超量摄入人体所需热量是每一个人的自主决定，“不是可乐的错”。他说，喝可乐、吃花生让他感觉人生很快乐，而且自己现在８５岁仍很健康。</a></div>
							    </div>
							 </td>
						</tr>
					</table>
					<div class="praise">
						<div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">100 </span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
						<div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">100 </span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
					    <div class="origin lisCol">来自：新浪微博</div>
					</div>
				</div>
				<div class="hrline clear marg borTop"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
//点赞(只能点一次)
   var upNum=$(".upZanNum").text();
   var downNum=$(".downZanNum").text();
  $(".upZan").each(function(){
	   $(".upZan").click(function(){
	   var val=$(this).parent().find(".upPraiIn");
	   var donval=$(this).parent().next(".downPrai").find(".downPraiIn");
	   		if(val.val()==0 && donval.val()==0){
	   		$(this).attr("class","uzanAct");
	   		$(this).next(".upZanNum").css("color","#ef412c");
	   		$(this).next(".upZanNum").text(parseInt(upNum)+1);
	   		val.val(1);
	   		}
	   });
   });
   
   $(".downZan").each(function(){
	   $(".downZan").click(function(){
	  	   var dval=$(this).parent().find(".downPraiIn");
		   var upval=$(this).parent().prev().find(".upPraiIn");
	   		if(upval.val()==0 && dval.val()==0){
	   		$(this).attr("class","dzanAct");	
	   		$(this).next(".downZanNum").css("color","#ef412c");
	   		$(this).next(".downZanNum").text(parseInt(downNum)+1);
	   		dval.val(1);
	   		}
	   });
   });
</script>

