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
	 			<div class="stcockQd">
	 				<img src="${ctx}/web/images_line/rgtTop.png"/>
	 				<div class="qdLine">
	 					<div class="lineTit" id="lineTit">
	 						
						</div>	 
						<div class="qdLineImg">
							<table  class="list fontWed borBom borTop" cellpadding="0" cellspacing="0" width="100%"  >
								<tr>
							        <th class="textCen fontWed"><div><span id="todayStartPri"></span></div></th>
							        <th class="textCen fontWed"><div><span id="yestodEndPri"></span></div></th>
							        <th class="textCen fontWed"><div><span id="todayMax"></span></div></th>
							        <th class="textCen fontWed"><div><span id="todayMin"></span></div></th>
							    <tr>
							</table>
							<ul class="lineMenu">
							  <li class="lineActiv">分时</li>
							  <!-- <li class="lines">5日<i ></i></li> -->
							  <li class="lines">日K</li>
							  <li class="lines">周K</li>
							  <li class="lines">月K</li>
							</ul>
							<div class="clear"></div>
							 <div class="linLists">
									<!-- 分时 -->
									<div class="lineInfo">
										<div class="container" id="container">
											<%-- <img class="lineImg" src="${ctx}/web/images_line/qdK.jpg"/> --%>
										</div>
									</div>
									<!-- 分时 end-->
									<!-- 5日 end-->
									<!-- 日K -->
									<div class="lineInfo" style="display: none;">
										<div class="container" id="DaysK">
											<%-- <img class="lineImg" src="${ctx}/web/images_line/qdK.jpg"/> --%>
										</div>
									</div>
									<!-- 日K end-->
									<!-- 周K -->
									<div class="lineInfo" style="display: none;">
										<div class="container" id="weekDays">
											<%-- <img class="lineImg" src="${ctx}/web/images_line/qdK.jpg"/> --%>
										</div>
									</div>
									<!-- 周K end-->
									<!-- 月K -->
									<div class="lineInfo" style="display: none;">
										<div class="container" id="monthDays">
											<%-- <img class="lineImg" src="${ctx}/web/images_line/qdK.jpg"/> --%>
										</div>
									</div>
									<!-- 月K end-->
								</div> 
							</div> 
						</div>   
	 				</div>
	 			</div>
	 		</td>
	 		<td align="left" valign="top" style="background: #28b2e3;" width="2">
	 			<div class="hotCent"></div>
	 		</td>
	 		<!-- 评论 -->
	 		<%@ include file="recentReview.jsp" %>
	 	</tr>
	 </table>
</div>
    <%@ include file="footer.jsp" %>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	//tab标签切换
   		//k线图
	var $liK = $('.lineMenu li');
		var $ulK = $('.linLists .lineInfo');
   		$liK.click(function(){
   			var $this = $(this);
			var $t = $this.index();
			$liK.removeClass();
			$this.attr("class","lineActiv");
			$ulK.css('display','none');
			$ulK.eq($t).css('display','block');
   		});
</script>

<script>
//聚合
$(function() {
	$.ajax({
        type: 'GET',
        url: '${ctx}/web/stock/getJuheStockInfo',
        data:{id:${id}},
        dataType: 'json',
        success: function(data){
        	var nowPri = data.nowPri;
        	var yestodEndPri = data.yestodEndPri;
        	var traNumber = data.traNumber;
        	var change = data.change;
        	var todayStartPri = data.todayStartPri;
        	var todayMax = data.todayMax;
        	var todayMin = data.todayMin;
        	var difference = data.difference;
        	var arrText = [];
        	var result = '';
        	
        	if(nowPri >= yestodEndPri && traNumber != 0){
        		arrText.push("<div class='headLft textCen'><span class='red bigSize fontWed'>${stockInfo.name}</span><br/>");
        		arrText.push("<a class='red bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${stockInfo.code}.html'>"+nowPri+"</a></div>");
        		arrText.push("<div class='headLftNum textCen'><span class='red smallSize paddBot'>"+difference+"&nbsp;</span><span class='red smallSize paddBot'>"+change+"%</span></div>");
        	}
        	if(nowPri < yestodEndPri && traNumber != 0){
        		arrText.push("<div class='headLft textCen'><span class='green bigSize fontWed'>${stockInfo.name}</span><br/>");
        		arrText.push("<a class='green bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${stockInfo.code}.html'>"+nowPri+"</a></div>");
        		arrText.push("<div class='headLftNum textCen'><span class='green smallSize paddBot'>"+difference+"&nbsp;</span><span class='green smallSize paddBot'>"+change+"%</span></div>");
        	}
        	if(traNumber == 0){
           		arrText.push("<div class='headLft textCen'><span class='dieColor bigSize fontWed'>${stockInfo.name}</span><br/>");
        		arrText.push("<a class='dieColor bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${stockInfo.code}.html'>"+yestodEndPri+"</a></div>");
           		arrText.push("<div class='headLftNum textCen'><span class='dieColor smallSize paddBot'>停牌</span></div>");
        	}
        	result +=  arrText.join('');
        	$("#lineTit").append(result);
        	$("#todayStartPri").append(data.todayStartPri); 
        	$("#yestodEndPri").append(data.yestodEndPri); 
        	$("#todayMax").append(data.todayMax); 
        	$("#todayMin").append(data.todayMin);
        	$("#container").append("<img class='lineImg' src='http://image.sinajs.cn/newchart/min/n/${code}.gif'>");
        	$("#DaysK").append("<img class='lineImg' src='http://image.sinajs.cn/newchart/daily/n/${code}.gif'>");
        	$("#weekDays").append("<img class='lineImg' src='http://image.sinajs.cn/newchart/weekly/n/${code}.gif'>");
        	$("#monthDays").append("<img class='lineImg' src='http://image.sinajs.cn/newchart/monthly/n/${code}.gif'>");
        },//success结束
        error: function(xhr, type){
           // alert('Ajax error!');
        }  
    });//ajax结束
    
    
});  
</script>

</body>
</html>
