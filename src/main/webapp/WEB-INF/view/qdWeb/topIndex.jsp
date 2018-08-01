<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="hhead">
<div class="hhead">
   <span class="headTxt">客服热线：010-53320537&nbsp;&nbsp;服务时间：9:00 - 18:00</span> 
  <ul class="navind">
  <c:if test="${!empty fuUser}">
  <li class="navli"><a href="javascript:void(0)" onclick="logout();" style="color: #f96900;">退出</a></li>
   <li class="navli allbd" id="allbd">
  	 <span class="nh">欢迎您，</span>
  	 <a id="nickName" class="pptv" href="${HHR_PREFIX}/user_center/centerIndex.htm">${empty fuUser.nickName?'佚名':fuUser.nickName}</a>
  	 <!-- 弹窗 -->
  	 <div class="topTc">
  	 	<div class="informationTop meTip">
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
			</div>
			<i class="card-top"></i>
		</div>
	</div>
		<!-- 弹窗end -->
	</li>
	<%-- <li class="navli" id="message">
  	 <a class="messa" href="javascript:void(0);" style="display:block;" onclick="toMoneyDetail()">
  		<span class="nh">消息</span><img class="bell" src="../images_line/tones.png">
     	<c:if test="${counts>0}"><span class="messageNum">${counts}</span></c:if>
     </a>
  </li> --%>
  </c:if>
  
  <c:if test="${empty fuUser}">
	  <li class="navli"></li>
  </c:if>
</ul>
</div>
</div>
<div class="clear"></div>
<div id="hmenu">
  <div class="hmenu">
    <div class="hlogo">
	    <c:if test="${empty fuUser}">
		    <a href="${QIDIAN_PREFIX}/web/index.html">
		    <img src="${ctx }/web/images_line/logo.png" title="首页"/>
		    </a>
	    </c:if>
	    <c:if test="${!empty fuUser}">
		    <a href="${HHR_PREFIX}/user_center/centerIndex.htm">
		    <img src="${ctx}/web/images_line/logo.png" title="个人中心"/>
		    </a>
	    </c:if>
    </div>
    <ul class="hmenuul">
    	<li><a href="${QIDIAN_PREFIX}/web/index.html">首页</a></li>
    	<li><a href="${QIDIAN_PREFIX}/web/ai/qdIndex">奇点算股</a></li>
    	<li><a href="${QIDIAN_PREFIX}/web/qida/index.html">奇答问股</a></li>
    	<li><a href="${ZHIBO_PREFIX}/live/index">直播</a></li>
    	<li><a href="${HHR_PREFIX}/index_guide/intrCompany.htm">关于我们</a></li>
    	<c:if test="${empty fuUser}">
	  	    <li>
				<a href="javascript:void(0)" onclick="login();">登录 /注册</a>
			</li> 
	  	</c:if>
    	<c:if test="${!empty fuUser}">
    	    <li><a href="${HHR_PREFIX}/user_center/centerIndex.htm">个人中心</a></li>
        </c:if>
    </ul>
  </div>
</div>
<script type="text/javascript">
window.onscroll=function(){
	var scrolltop = document.documentElement.scrollTop+document.body.scrollTop;
	if(scrolltop<24){
		$("#hmenu").attr("class","hmenu1");
	}else{
		$("#hmenu").attr("class","hmenu2");
	}
	
	
};

/* function lgInfo(flag){
	$.fancybox.open({
		href : '${ctx}/user_login/userLoginAjax.htm?flag='+flag,
		type : 'ajax',
		padding : 0 ,
		scrolling:'no',
	});
} */

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
		$(".topTc").show();
	});
	$(".topTc").mouseout(function(){
		$(".topTc").hide();
	});
});
//刷新资金变更消息数目
/* $(function(){
	$.post("${ctx}/user_login/fushMoneyDetail.htm",null,function(data){
	})
}) 
//跳转到个人中心账户预览，然后清零资金变更消息
function toMoneyDetail(){
	$.post("${ctx}/user_login/toMoneyDetail.htm",null,function(data){
		location.href="${ctx}/user_center/centerIndex.htm";
	})
} */
</script>