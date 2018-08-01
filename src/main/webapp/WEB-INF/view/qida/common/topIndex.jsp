<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		.meA>.doctor{
			background-size:40px;
			top:9px;
			left:13px;
		}
</style>
<!-- 导航 -->
    <nav class="navbar navbar-static-top" role="navigation">
	  <div class="container navHgt">
	  	<!-- logo -->
	    <div class="navbar-head">
	      <c:if test="${empty fuUser}">
			    <a href="${QIDIAN_PREFIX}/web/index.html">
			    <img src="${ctx}/web/images_qiDa/logo.png" title="首页"/>
			    </a>
		    </c:if>
		    <c:if test="${!empty fuUser}">
			    <a href="${HHR_PREFIX}/user_center/centerIndex.htm">
			    <img src="${ctx}/web/images_qiDa/logo.png" title="个人中心"/>
			    </a>
		    </c:if>
	    </div>
	    <!-- 导航右边部分 -->
	    <div class="navbar-collap">
	    <!-- 搜索 -->
		   <form class="navbar-for navbar-right" role="search" action="${ctx}/web/qida/questionAll.html">
			     <div class="form-grou searhBody">
			        <input name="keyword" type="text" class="form-control navbar-left" placeholder="搜索问答">
				    <button class="btn searBtn" type="submit"><img src="${ctx}/web/images_qiDa/search.png" /></button>
			     </div>
		   </form>
	    <!-- 首页/问题/标签 -->
	      <ul class="navbar-na navbar-right navHight">
	        <li class="indexHom"><a href="${ctx}/web/qida/index.html">首页</a></li>
		    <li class="community"><a href="${ctx}/web/qida/community.html">社区</a></li>
		    <li class="question"><a href="${ctx}/web/qida/questionAll.html">问题</a></li>
		    <li class="targets"><a href="${ctx}/web/qida/tags.html">标签</a></li>
		    <li class="stock"><a href="${ctx}/web/qida/stock.html">股票</a></li>
		    <li class="ask"><a href="${ctx}/web/ai/questionAsk.html">提问</a></li>
		   <c:if test="${empty fuUser}">
		   	 <li>
				<a href="javascript:void(0)" > </a>
			 </li>
		  	</c:if>
	    	<c:if test="${!empty fuUser}">
	    	    <li class="message">
	    	   		  <a class="message-bell" href="javascript:void(0)"><span class="glyphicon glyphicon-bell bellTop"></span><span class="badge bellNum" id="countNum"></span></a>
	    	   		 <!-- 弹窗 （无消息） -->
					<div class="informationTop messTip">
						<div class="inforBod" id="messageTip">	
						</div>
						<i class="card-top"></i>
					</div>
	    	   	</li>
	    	    <li class="mineInfo">
	    	    	<a class="meA" href="javascript:void(0)">
	    	    			<c:if test="${empty fuUser}">
							<img class="meIcon" src="${ctx}/web/images_qiDa/tx.png" />
							</c:if>
							<c:if test="${not empty fuUser}">
							<c:if test="${fuUser.qidaRank==1}">
							<i class="doctor"></i>
							</c:if>
							<img class="meIcon" src="${empty fuUser.userAvatar?'../images_qiDa/tx.png':fuUser.userAvatar}" />
							</c:if>
	    	    	</a>
	    	    	<!-- 弹窗 -->
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
								<div class="chartName-jifen smalSize">余额：<fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/>元<a class="timony" href="${HHR_PREFIX}/user_draw_money/drawMoney.htm" target="_blank">提现</a></div>
								<div class="chartName-jifen smalSize">积分：<fmt:formatNumber value="${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral}" pattern="#,###,##0.00"/></div>
								<div class="chartName-jifen smalSize">用户级别：${fuUser.qidaRank==null?'会员':fuUser.qidaRank==0?'会员':'专家'}</div>
							</div>
						</div>
						<div class="chartName-nav">
							<ul>
								<li><a href="${ctx}/web/ai/meindex.html">我的主页</a></li>
								<li><a href="${ctx}/web/ai/meindex.html">我的回答</a></li>
								<li><a href="${ctx}/web/ai/exchange.html">积分明细</a></li>
								<li><a onclick="logout()">退出</a></li>
							</ul>
						</div>
						<i class="card-top"></i>
					</div>
	    	    </li>
	       </c:if>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
<!-- 获取站内信消息 -->
<script type="text/javascript">
	function getMessage() {
		if(${not empty fuUser}) {
			var html = "";
			$.post("${ctx}/web/ai/myMessage.html", null, function(data){
				if (data.success == 0) {
					$("#messageTip > div").remove();
					html = "<div class='inforBody-mesg'>"+data.message+"</div>"+
								"<div class='inforBody-dext'>"+
								"<a href='${ctx}/web/messages.html' class='defaultCol'>查看全部消息</a>"+
								"</div>";
					$("#messageTip").append(html);
					return false;
				} else {
					$("#messageTip > div").remove();
					var $list = data.messageList;
					$("#countNum").text(data.unReadCount);
					for (var i = 0; i < $list.length; i++) {
						html += "<div class='inforBody-hmesg'>"+
									"<div class='hmesg-toux'>"+
										"<a><img src="+$list[i]['userAvatar']+" /></a>"+
									"</div>"+
									"<div class='hmesg-info'>"+
										"<div class='hmesg-name'><a>"+$list[i]['nickName']+"</a><span>"+$list[i]['createTime']+"</span></div>"+
										"<div class='hmesg-texts'><a  class='hmesg-textsa' href='${ctx}/web/views/"+$list[i]['id']+".html'>"+$list[i]['content']+"</a></div>"+
										"<div class='hmesg-tts'><a class='hmesg-ttsa' href='${ctx}/web/views/"+$list[i]['id']+".html'></a></div>"+
									"</div>"+
									"<div class='hmesg-kg'></div>"+
								"</div>";
					}
					html +="<div class='inforBody-dext'>"+
								"<a href='${ctx}/web/messages.html' class='defaultCol'>查看全部消息</a>"+
							"</div>";
					$("#messageTip").append(html);
					
					//超过字数省略号表示
					$(".hmesg-texts a").each(function(){
					var obString=$(this).text();
					var length=$(this).text().length;
					var sl=$(this).text().substring(0,25)+"...";
					if(length>25){
					   		$(this).parent().next().find(".hmesg-ttsa").text(sl);
					   		$(this).parent().hide();
					   }else{
					   		$(this).parent().show();
					   		$(this).parent().next().hide();
					   }; 
				}); //超过字数省略号表示end
					
				}
			}, "json");
		} else {
			console.log("用户不存在")
			return false;
		}
	}

	window.setInterval(getMessage,5000);
</script>
<script type="text/javascript">
//鼠标经过头像效果
$(document).ready(function(){
	$(".message").mouseover(function(){
		$(".messTip").show();
	});
	$(".message").mouseout(function(){
		$(".messTip").hide();
	});
	$(".messTip").mouseover(function(){
		$(this).show();
	});
	$(".messTip").mouseout(function(){
		$(this).hide();
	});
	$(".mineInfo").mouseover(function(){
		$(this).find(".meTip").show();
	});
	$(".mineInfo").mouseout(function(){
		$(this).find(".meTip").hide();
	});
});

function logout(){
	location.href="${ctx}/web/ai/logout.html";
	return false;	
}

function login(){
	location.href="${ctx}/web/ai/meindex.html";
	return false;
}
</script>