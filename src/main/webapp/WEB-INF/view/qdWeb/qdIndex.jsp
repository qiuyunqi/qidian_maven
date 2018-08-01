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
	<%@ include file="qqConsult_hhr.jsp" %>
	 <div class="index-wrapper">
	    <div class="indexTop"></div>
	    <div class="indexBody">
	    	<table  class="indexTab" cellpadding="0" cellspacing="0" width="100%" border="0">
	    		<c:forEach items="${codeList}" var="code" varStatus="row">
	    			<tr>
		    			<td>
		    				<div class="tabImg">
				    			<img src="${code[1]}" onclick="jumpLine('${code[0]}')">
				    			<hr class="hr"/>
				    			<a class="qdBtn" data-index = "${code[0]}">看涨</a>
			    			</div>
		    			</td>
		    			<td>
		    				<div class="tabImg">
				    			<img src="${code[3]}" onclick="jumpLine('${code[2]}')">
				    			<hr class="hr"/>
				    			<a class="qdBtn" data-index = "${code[2]}">看涨</a>
			    			</div>
		    			</td>
		    		</tr>
				</c:forEach>
	    	</table>
	    </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script>
//导航样式
$(".hmenuul li a").each(function(){
	var indexTxt = $(this).text();
	if(indexTxt=="奇点算股"){
		$(this).attr("class","hmenuula");
	}
});

/* 
/*点击聊天室弹出*/
  /*  html="<div class='talkBg'>"+
   			"<div class='talkCon'>"+
   				"<div class='talkClos'><img class='talkClosBtn' src='${ctx}/web/images_line/talkClos.png'></div>"+
   				"<div class='talkBody'>"+
   					"<div class='talkTxt'>"+
   						"<div class='talkHead'></div>"+
   						"<div class='talkInfo'>"+
   							"<p>小合智能是一个基于大数据筛选与人工智能学习的智能辨识市场的系统。</p>"+
   							"<p>通过初期对数据的精确筛选与辨识获得最初的结果，后端使用经验模型以及市场成熟模型对结果做多维度多对比的匹配，将最终结果以排列形式直观展现出来。</p>"+
   						"</div>"+
   					"</div>"+
   				"</div>"+
   			"</div>"+
   		"</div>"; */
   		
   		
	/*弹窗高度*/
	/* window.onload = function() {
		$("body").append(html);
		var hgt = $(document.body).height();
		$(".talkBg").height(hgt);
		//关闭窗口
		$(".talkBg").on("click", ".talkClosBtn", function() {
			$(".talkBg").remove();
		}); */

		//点击弹窗其他地方跳转
		/* $(".talkBody").click(function() {
			location.href = "http://zhibo.hhr360.com";
		});
	}; */

	var _hmt = _hmt || [];
	(function() {
	var hm = document.createElement("script");
	hm.src = "https://hm.baidu.com/hm.js?577cde65ff9c5c260eeca3db486575c2";
	var s = document.getElementsByTagName("script")[0]; 
	s.parentNode.insertBefore(hm, s);
	})();

	function jumpLine(id) {
		window.location = "${ctx}/web/ai/qdKline?id=" + id;
	}

	// 看涨弹窗
	var htm = "<div class='qdHidden'>"
			+ "<div class='hidBody'>"
			+ "<div class='qdHidBtn'></div>"
			+ "<div id='hidTxt' class='hidTxt'>"
			+ "<img src='${ctx}/web/images_line/tan.png'>"
			+ "<div class='hidText'>有<span id='ztCount' class='yrlColr'></span>的人赞同你的观点哦！</div>"
			+ "</div>" + "<div class='vsjind'>" + "<div class='redbar'></div>"
			+ "</div>" + "</div>" + "</div>";

	var qdBtns = $(".qdBtn");
	for (var i = 0; i < qdBtns.length; i++) {
		qdBtns[i].onclick = function() {
			var stockId = $(this).data("index");
			$.ajax({
				type : 'POST',
				url : '${ctx}/web/ai/saveStockBullish',
				async : false,
				data : {
					id : stockId
				},
				dataType : 'text',
				success : function(data) {
					if (data == "-1") {
						location.href = "${ctx}/web/ai/qdHot";
						return false;
					} else {
						qdBtns.parent().find(".qdHidden").remove();
						$(this).parent().append(htm);
						$("#ztCount").text(data);
						var cztLft = $("#ztCount").text();
						$(".redbar").show();
						$(".redbar").width(cztLft);
						$(".qdHidBtn").click(function() {
							$(".qdHidden").remove();
						});
					}
				},
				error : function(xhr, type) {
					alert("ajax error");
				}
			});
		};
	}
</script>  
