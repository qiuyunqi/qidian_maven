<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="hhead">
		<div class="hhead container">
		   <span class="top-headTxt">客服热线：010-53320537&nbsp;&nbsp;服务时间：9:00 - 18:00</span> 
			<ul class="top-navind">
				  <c:if test="${!empty fuUser}"> 
				  <li class="top-navli"><a href="javascript:void(0)" onclick="logout();" style="color: #f96900;">退出</a></li>
				   <li class="top-navli allbd" id="allbd">
				  	 <span class="nh">欢迎您，</span>
				  	 <a id="nickName" class="pptv" href="${HHR_PREFIX}/user_center/centerIndex.htm">${empty fuUser.nickName?'佚名':fuUser.nickName}</a>
				  	 <!-- 弹窗 -->
				  	 <div class="topTc">
				  	 	<div class="informationTop meTipac">
							<div class="inforBod">
								<div class="inforBody-gz">
									<div class="information-tox">
										<c:if test="${empty fuUser}">
										<img src="${ctx}/web/images_qiDa/defTx.jpg" />
										</c:if>
										<c:if test="${not empty fuUser}">
										<img src="${empty fuUser.userAvatar?'../images_qiDa/tx.png':fuUser.userAvatar}" />
										</c:if>
									</div>
								</div>
							<div class="inforBody-text">
								<div class="chartNa defaultCol"><a href="${ctx}/web/ai/meindex.html">${empty fuUser.nickName?'佚名':fuUser.nickName }</a></div>
								<div class="chartName-jifen smalSize">余额：
								<c:if test="${!empty fuUser.accountBalance && fuUser.accountBalance>1000000}">
									<a title="${empty fuUser.accountBalance?0:fuUser.accountBalance}" href="${HHR_PREFIX}/user_center/centerIndex.htm">
										<fmt:formatNumber value="${fuUser.accountBalance/1000000+0.0001}" pattern="#,###,##0.00"/>百万
									</a>	
								</c:if>	
								<c:if test="${empty fuUser.accountBalance || fuUser.accountBalance<1000000}">
									<a href="${HHR_PREFIX}/user_center/centerIndex.htm">
										<fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/>元 
									</a>	
								</c:if>	
								&nbsp;&nbsp;<a class="timony" href="${HHR_PREFIX}/user_draw_money/drawMoney.htm" target="_blank">提现</a></div>
								<div class="chartName-jifen smalSize">积分：<a href="${QIDIAN_PREFIX}/web/ai/exchange.html"><fmt:formatNumber value="${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral}" pattern="#,###,##0.00"/></a></div>
							</div>
							<i class="card-top"></i>
						</div>
					</div>
					<!-- 弹窗end -->
					</li>
					<li class="top-navli"><a href="${HHR_PREFIX}/user_center/centerIndex.htm">个人中心</a></li>
				</c:if>
				  <c:if test="${empty fuUser}">
					  <li class="top-navli"></li>
				  </c:if> 
				  <li class="top-navli"><a href="${HHR_PREFIX}/index_guide/intrCompany.htm">关于我们</a></li>
				  <li class="top-navli"><a href="${ZHIBO_PREFIX}/live/index">直播</a></li>
				  <li class="top-navli"><a href="${QIDIAN_PREFIX}/web/qida/index.html">奇答问股</a></li>
				  <li class="top-navli"><a href="${QIDIAN_PREFIX}/web/ai/qdIndex">奇点算股</a></li>
				</ul>
		</div>
	 </div>
	 <div class="clear"></div>
<script type="text/javascript" src="${ctx}/web/js/jquery.tipsy.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/web/css/tipsy.css"/>	 
<script type="text/javascript">
//以下这些是直接复制奇点的topQd页面的js，不知道是否有用
function logout(){
	window.setTimeout(function(){
		location.href="${ctx}/web/ai/logout";
		}, 1000);
}
    
function login(){
	location.href = "${ctx}/web/ai/qdHot";
	return false;
}
    
function toBbs(){
    window.open('http://bbs.hhr360.com');
}

//鼠标经过头像效果
$(document).ready(function(){
	$("#nickName").mouseover(function(){
		$(".topTc").show();
	});
	$("#nickName").mouseout(function(){
		$(".topTc").hide();
	});
	$(".topTc").mouseover(function(){
		$(this).show();
	});
	$(".topTc").mouseout(function(){
		$(this).hide();
	});
});
</script>