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
.newsTitle{text-align: center;font-weight: 600;font-size: 16px;padding: 15px 0;}
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
					 <div class="newsList" onclick="newsInfo()">
					    <table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
							<tr>
							   <td>
							      <div class="newsLis newsTitle">
							          	${comment.content}
							      </div>
							      <div class="clear"></div>
							      <div class="newsLis">
							         <div class="gLisInfo lisCol"><a href="javascript:void(0);">${comment.content}</a></div>
							         <div class="tim lisCol"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
							      </div>
							    </td>
							</tr>
						</table>
					</div>
			</div>
		</div>
	</div>
</body>
</html>


