<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no" name="viewport"/>
<%-- <%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%> --%>
<title>${title}</title>

<link type="text/css" rel="stylesheet" href="${ctx}/web/css/qd.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/jquery-ui.css"></link>
<style type="text/css">
	body{width:100%;overflow-x:hidden;}
	.qdArea a{ color: #fff !important;font-size: 18px !important;}
	.qdArea a>i{visibility: visible;}
</style>
</head>
<body>
	<%@ include file="topQd.jsp" %>
	<%@ include file="top.jsp" %>
	 <div class="concept-wrapper">
	 <table  class="hotList" cellpadding="0" cellspacing="0" width="100%" border="0">
	 	<tr>
	 		<!-- 左边自选股 -->
	 		<%@ include file="recentCommon.jsp" %>
	 		<!-- 中间间隔 -->
	 		<td align="left" valign="top" style="background: #28b2e3;" width="2">
	 			<div class="hotCent"></div>
	 		</td>
	 		<!-- 右边股票 -->
	 		<td align="left" valign="top">
	 			<div class="hotBody">
			    	<img src="${ctx}/web/images_line/rgtTop.png"/>
			    	<div id="myMetro" class="myMetro"></div>
			    </div>
	 		</td>
	 	</tr>
	 </table>
</div>
    <%@ include file="footer.jsp" %>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script src="${ctx}/web/js/waterFall.js"></script>
<script type="text/javascript">
var myMetro = $id("myMetro");

function createTestData(dataList){
	var spanWrap = document.createElement("span"),
	content = "";
	var data = dataList.stockList;
	for(var i = 0; i < data.length; i++) {
		var data1 = data[i]['name'];
		var data2 = data[i]['liftRate'];
		color = data[i]['color'];
		if (undefined != data[i]['infoId'] && "" != data[i]['infoId']) {
			urlPath = "${ctx}/web/ai/qdKline?id="+data[i]['infoId'];
		} else {
			urlPath = "${ctx}/web/ai/qdArea?id="+data[i]['id'];
		}
		if(!(Math.random()*3 >> 0)){
			height = Math.floor(Math.random()*10 + 100);
			width = Math.floor(Math.random()*10 + 100);
			content += '<div class="MBox"><div class="widgetBox bigBox" style="background:#'+color+'"><div style="width:' + width +'px;height:' + height +'px;margin:0 auto;" class="innerBox"><div class="fallxs"><a href="'+ urlPath +'" target="_blank">'+data1+'<br>'+ data2 +'</a></div></div></div></div>';
		 }else{ 
			content += '<div class="MBox"><div class="widgetBox" style="background:#'+color+'"><div class="fallx"><a href="'+ urlPath +'" target="_blank">'+data1+'<br>'+data2+'</a></div></div></div>';
		} 
	}
	spanWrap.innerHTML = content;
	myMetro.appendChild(spanWrap);
	return spanWrap;
}
window.onload = function(){
	createTestData(${stockList});
	metro.init(myMetro);
};
window.onresize = function(){
	metro.resort(myMetro);
};

// 1分钟访问一次
setInterval(index, 60000);
$id = "${id}";
function index() {
	$.post("${ctx}/web/ai/ajaxQdArea", {"id" : $id}, function(data){
		$("#myMetro").html("");
		createTestData(data);
		metro.init(myMetro);
	});
}
</script>
</body>
</html>