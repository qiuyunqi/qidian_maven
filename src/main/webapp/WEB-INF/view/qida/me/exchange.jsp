<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-积分兑换</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<style>
		.txIcon {padding: 0 !important;}
		.lfContainer {
		    margin-left: 124px !important;
		}
	</style>
  </head>
  <body>
  <!-- 客服热线 -->
  	<%@ include file="/WEB-INF/view/qida/common/top.jsp" %>
  <!-- 页头 -->
  	<%@ include file="../common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<div class="kongge"></div>
		<!-- 出款记录/收入记录 -->
		<div class="communit-nav">
			<ul>
				<%-- <li><a href="${ctx}/web/ai/payrd.html" title="出款记录">出款记录</a></li>
				<li><a href="${ctx}/web/ai/incomerd.html" title="收入记录">收入记录</a></li> --%>
				<li class="communit-active"><a href="javascript:void(0)" title="积分兑换">积分兑换</a></li>
			</ul>
		</div>
		<!-- 兑换 -->
		<div class="me-change">
			<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
					<tr>
	  					<td width="23%">
							您可兑换金额为：<fmt:formatNumber value='${empty fuUser.accountBalance?0:fuUser.accountBalance+0.0001-0.5}' pattern='#,###,###'/>
							<a class="blueCol tips" original-title="1:${ConvertScorePercent}兑换积分"  target="_blank"> &nbsp;&nbsp;&nbsp;?</a>
						</td>
						<td></td>
						<td width="23%">
							您可兑换积分为：<fmt:formatNumber value='${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral>0.5?fuUser.qidaIntegral+0.0001-0.5:0}' pattern='#,###,##0'/>
							<a class="blueCol tips" original-title="1:${ConvertMoneyPercent}兑换余额"  target="_blank"> &nbsp;&nbsp;&nbsp;?</a>
						</td>
						<td></td>
						<td></td>
				</tr>
				<tr>
					<td width="23%"><input id="money" type="text" class="change-input" onkeyup="changeMatch(this.value)" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入金额"></td>
					<td><a class="change-btn" onclick="exchangeScore()" title="积分兑换">兑换积分</a></td>
					<td width="23%" ><input id="score" type="text" class="change-input moneyChan " onkeyup="changeBlance(this.value)" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="请输入积分"></td>
					<td ><a class="change-btn moneyChan-btn " onclick="exchangeBlance()">积分兑换余额</a></td>
					<td ><a class="change-btn moneyChan-btn flatRgt" href="${HHR_PREFIX}/user_draw_money/drawMoney.htm" target="_blank">提现</a></td>
				</tr>
			</table>
		</div>
		<!-- 出款记录 -->
		<table class="table table-striped table-bordered table-hover setTab" cellpadding="0" cellspacing="0" width="100%" border="0">
			<tr>
				<th class="blueCol">序号</th>
				<th class="blueCol">时间</th>
				<th class="blueCol">详情</th>
				<th style="text-align:right;">积分</th>
				<th style="text-align:right;">金额</th>
				<th style="text-align:right;">总积分</th>
			</tr>
			<c:forEach items="${moneyDetailList}" var="detail" varStatus="stat">
			<tr>
				<td>${stat.index+1}</td>
				<td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${detail.comment}</td>
				<td style="text-align:right;">${detail.isIncome==1?'+':'-'}${detail.score}</td>
				<td style="text-align:right;">
				<c:if test="${detail.fuDictionary.id==50}"><!-- 兑换积分 -->
				-<fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/>
				</c:if>
				<c:if test="${detail.fuDictionary.id==52}"><!-- 兑换余额 -->
				+<fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/>
				</c:if>
				</td>
				<td style="text-align:right;"><fmt:formatNumber value="${detail.totalScore}" pattern="#,###,##0.00"/></td>
			</tr>
			</c:forEach>
		</table>
		
		<!-- 分页 -->
		<div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/web/ai/exchange.html"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
			</domi:pagination>
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="../common/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$(document).on("mouseover",".tips",function(){
		$(".tips").tipsy({gravity: 's'}); 
	})
});

//兑换积分的输入限制
function changeMatch(money){
	if(isNaN(money)){
		globalTip("输入的金额只能是正整数！");
		$("#money").val(1);
		$("#money").focus();
		return false;
	}
	if(money>${empty fuUser.accountBalance?0:fuUser.accountBalance+0.0001-0.5}){
		$("#money").val(Math.round(${empty fuUser.accountBalance?0:fuUser.accountBalance+0.0001-0.5}));
		return false;
	}
	if(money!=null && money!='' && money<1){
		$("#money").val(1);
		return false;
	}
	if(money.indexOf(".")>-1){
		$("#money").val(Math.round(money));
	}
}

//兑换余额的输入限制
function changeBlance(score){
	if(isNaN(score)){
		globalTip("输入的积分只能是正整数！");
		$("#score").val(1);
		$("#score").focus();
		return false;
	}
	if(score>${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral+0.0001-0.5}){
		$("#score").val(Math.round(${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral+0.0001-0.5}));
		return false;
	}
	if(score!=null && score!='' && score<1){
		$("#score").val(1);
		return false;
	}
	if(score.indexOf(".")>-1){
		$("#score").val(Math.round(score));
	}
}
	
	//兑换积分
	function exchangeScore(){
		if(!$("#money").val()){
			layer.open({
			    content: "请输入金额！",
			    btn: ["确定"],
			    shadeClose: false,
			    yes: function(){
			        layer.closeAll();
			        $("#money").focus();
			    }
			});
			return false;
		}
		$.post("${ctx}/web/ai/exchangeScore.html",{money : $("#money").val()},function(data){
			if(data.success == 0){
				globalTip("兑换失败!");
				return false;
			}
			if (data.success == 1) {
				globalTip("兑换成功!");
				location.href=location.href;
			}
		}, "json");
	}
	
	//兑换余额
	function exchangeBlance(){
		if(!$("#score").val()){
			layer.open({
			    content: "请输入积分！",
			    btn: ["确定"],
			    shadeClose: false,
			    yes: function(){
			        layer.closeAll();
			        $("#score").focus();
			    }
			});
			return false;
		}
		$.post("${ctx}/web/ai/exchangeBlance.html",{score : $("#score").val()},function(data){
			if(data.success == 0){
				globalTip("兑换失败!");
				return false;
			}
			if (data.success == 1) {
				globalTip("兑换成功!");
				location.href=location.href;
			}
		}, "json");
	}
	
	</script>
  </body>
</html>