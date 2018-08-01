<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no" name="viewport"/>
<%-- <%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%> --%>
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/qd.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/jquery-ui.css"></link>
<style>
.qdHot a{ color: #fff !important;font-size: 18px !important;}
.qdHot a>i{visibility: visible;}
</style>
</head>
<body>
	<%@ include file="topQd.jsp" %>
	<%@ include file="top.jsp" %>
	 <div class="hot-wrapper">
	 <table  class="hotList" cellpadding="0" cellspacing="0" width="100%" border="0">
	 	<tr>
	 		<!-- 左边自选股 -->
	 		<%@ include file="recentCommon.jsp" %>
	 		<!-- 中间间隔 -->
	 		<td align="left" valign="top" style="background: #28b2e3;">
	 			<div class="hotCent"></div>
	 		</td>
	 		<!-- 右边股票 -->
	 		<td align="left" valign="top">
	 			<div class="hotBody">
			    	<img src="${ctx}/web/images_line/rgtTop.png"/>
			    	<table  class="hotTab" cellpadding="0" cellspacing="0" width="100%" border="0">
			    		<c:forEach items="${codeList}" var="code" varStatus="row">
			    			<tr>
				    			<td>
				    				<div class="hotImg">
				    					<div class="hot-tit">${code[2]}<span>（${code[1]}）</span></div>
						    			<img src="http://image.sinajs.cn/newchart/min/n/${code[1]}.gif" onclick="jumpLine('${code[0]}')">
						    			<hr class="hr"/>
						    			<a class="qdBtn" data-index = "${code[0]}">看涨</a>
					    			</div>
				    			</td>
				    			<td>
				    				<div class="hotImg">
				    					<div class="hot-tit">${code[5]}<span>（${code[4]}）</span></div>
						    			<img src="http://image.sinajs.cn/newchart/min/n/${code[4]}.gif" onclick="jumpLine('${code[3]}')">
						    			<hr class="hr"/>
						    			<a class="qdBtn" data-index = "${code[3]}">看涨</a>
					    			</div>
				    			</td>
				    		</tr>
						</c:forEach>
			    	</table>
			    </div>
	 		</td>
	 	</tr>
	 </table>
</div>
    <%@ include file="footer.jsp" %>
    
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>

<script type = "text/javascript">
$(function() {
    var codeArr = "${codeArr}";
    $.ajax({
        type: 'GET',
        url: '${ctx}/web/stock/getPriceAndRange',
        data:{codeArr:codeArr},
        dataType: 'json',
        success: function(data){
        	for(var j=0; j<data.length; j++){
        		var changeText = [], changeResult = '';
        		var priceText = [], priceResult = '';
        		if(data[j].change.indexOf("-") != -1){
        			priceText.push("<span class='qlColr'>"+data[j].nowPri+"</span>");
        			changeText.push("<span class='qlColr'>"+data[j].change+"</span>");
        		}else{
        			priceText.push("<span class='redColr'>"+data[j].nowPri+"</span>");
        			changeText.push("<span class='redColr'>"+data[j].change+"</span>");
        		}
        		changeResult += changeText.join('');
            	$("#"+data[j].id).append(changeResult);
            	priceResult += priceText.join('');
            	$("#"+data[j].code).append(priceResult);
        	}
        },
        error: function(xhr, type){
            alert('Ajax error!');
        }  
    });
}); 
</script>
<script type="text/javascript">
var hml="<div class='qdHidden'>"+
			"<div class='hidBody'>"+
				"<div class='qdHidBtn'></div>"+
				"<div id='hidTxt' class='hidTxt'>"+
					"<img src='${ctx}/web/images_line/tan.png'>"+
					"<div class='hidText'>有<span id='ztCount' class='yrlColr'></span>的人赞同你的观点哦！</div>"+
				"</div>"+
			    "<div class='vsjind'>"+
			    	"<div class='redbar'></div>"+
			    "</div>"+
			"</div>"+
		"</div>";
				
//点击弹出框
var qdBtns=$(".qdBtn");
for(var i=0;i<qdBtns.length;i++){
	qdBtns[i].onclick=function(){
		qdBtns.parent().find(".qdHidden").remove();
		$(this).parent().append(hml);
		var stockId = $(this).data("index");
		$.ajax({
	        type: 'POST',
	        url: '${ctx}/web/ai/saveStockBullish',
	        async: false,
	        data:{id:stockId},
	        dataType: 'text',
	        success: function(data){
	        	if(data=="-1"){
		        	location.href = "${ctx}/web/ai/qdHot";
		        	return false;
		        }else{
		        	$("#ztCount").text(data);
		        	var cztLft=$("#ztCount").text();
		        	$(".redbar").show();
		        	$(".redbar").width(cztLft);
		        	$(".qdHidBtn").click(function(){
		        		$(".qdHidden").remove();
		        	});
		        }
	        },
	        error: function(xhr, type){
	        	alert("ajax error");
	        }  
	   });
	};
}	

function jumpLine(id)  
{  
   	window.location = "${ctx}/web/ai/qdKline?id="+id;
}  
</script>
</body>

</html>
  
   