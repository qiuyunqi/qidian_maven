<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
 <style>
.leftContain{height:48px;}
</style>
<!-- 问题详细页左边部分 -->
<div class="sidebar-left">
	<!-- 设置 -->
	<div class="leftContain">
		<a class="guide"><span class="glyphicon glyphicon-pushpin"></span> 向导</a>
	</div>
	<!-- 签名档 -->
	<div class="qianm">
		<p>签名档</p>
		<div class="qianInfo">${empty fuUser.introduction?'什么也没说..':fuUser.introduction}</div>
		<a class="qianm-btn"><span class="glyphicon glyphicon-pencil"></span></a>
	</div>
	<!-- TA的粉丝 -->
	<div class="fens">
		<p>我的粉丝</p>
		<c:if test = "${empty fansList}">
		<div class="noFens">还没有关注的粉丝</div>
		</c:if>
		<!-- 无粉丝时end -->
		<!-- 有粉丝时 -->
		<ul class="fensUl">
		<c:if test = "${fansList != null}">
			<c:forEach items = "${fansList}" var = "fans"> 
			<li onmouseover='mousove($(this),60,15,${fans.concernUser.id})'  onmouseout='mousout($(this))'><a><img src="${fans.concernUser.userAvatar==null?'../images_qiDa/tx.png':fans.concernUser.userAvatar}" /></a></li>
			</c:forEach>
		</c:if>
		</ul>
		<!-- 有粉丝时end -->
	</div>
	<!-- 关注的用户 -->
	<div class="care clear">
		<p>关注的用户</p>
		<!-- 无用户时 -->
		<c:if test = "${empty concernList}">
		<div class="careUsers">还没有关注的用户</div>
		</c:if>
		<!-- 无用户时end -->
		<!-- 有用户时 -->
		<ul class="careUl">
		<c:if test = "${concernList != null}">
			<c:forEach items = "${concernList}" var = "concern"> 
			<li onmouseover='mousove($(this),50,20,${concern.beConcernUser.id})'  onmouseout='mousout($(this))'><a><img src="${concern.beConcernUser.userAvatar==null?'../images_qiDa/tx.png':concern.beConcernUser.userAvatar}" /></a></li>
			</c:forEach>
		</c:if>
		</ul>
		<!-- 有用户时 end-->
	</div>
	<!-- 关注的问题 -->
	<%-- <div class="aboutQs">
		<p>关注的问题</p>
		<!-- 无关注的问题时 -->
		<c:if test = "${empty collectionList}">
		<div class="aboutNoqs">还没有关注的问题</div>
		</c:if>
		<!-- 无关注的问题时end -->
		<!-- 有关注的问题时 -->
		<ul class="aboutQsInf">
		<c:if test = "${collectionList != null}">
			<c:forEach items = "${collectionList}" var="collection"> 
			<li class="aboutQs-info"><a>${collection.qidaQuestion.title}</a><span> - ${collection.qidaQuestion.replyNum}回答</span></li>
			</c:forEach>
			</c:if>
		</ul>
		<!-- 有关注的问题时end -->
	</div> --%>
</div>

<!-- 向导弹窗 -->
<div class="guideTip">
	<div class="container guideConts">
			<!-- 头像弹窗 -->
		<div class="lead-info szTx">
			<div class="lead-infoRw">
				<div class="jqiarrow jqiarrowtl"></div>
			</div>
			<div class="leadTit">设置向导-头像<span class="lead-close">X</span> </div>
			<div class="leadContent">鼠标移到这里，你就会发现惊喜！</div>
			<div class="leadBtn">
				<a class="txNex" href="#">Next</a>
			</div>
		</div>
		<!-- 个人信息展示 -->
		<div class="lead-info infoSow">
			<div class="lead-infoRw">
				<div class="jqiarrow jqiarrowtl"></div>
			</div>
			<div class="leadTit">个人信息展示<span class="lead-close">X</span> </div>
			<div class="leadContent">你的个人资料会显示在下方。</div>
			<div class="leadBtn">
				<a class="infoPre" href="#">Pre</a>
				<a class="infoNex" href="#">Next</a>
			</div>
		</div>
		<!-- 签名档弹窗 -->
		<div class="lead-info qmtc">
			<div class="lead-infoRw">
				<div class="jqiarrow jqiarrowtl"></div>
			</div>
			<div class="leadTit">个人签名<span class="lead-close">X</span> </div>
			<div class="leadContent">个人签名始终会显示在你的回答旁边，是时候展示自己啦!</div>
			<div class="leadBtn">
				<a class="qmPre" href="#">Pre</a>
				<a class="done" href="#">done</a>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/qida/common/userInfo.jsp" %>
<script type="text/javascript">
	
	//输入签名
	var tear = "<div class='textAre'>"+
					"<textarea class='qianText' rows='2'></textarea>"+
					"<a href='javascript:void(0);' onclick = 'updateIntro();' class='qianText-btn'>提交</a>"+
				"</div>";
	$(".qianm-btn").click(function(){
		var qianm = $(this).parent().attr("class");
		if(qianm == "qianm"){
			$(this).parent().attr("class","qianm acv");
			$(this).parent().append(tear);
		}else{
			$(this).parent().attr("class","qianm");
			$(this).next().remove();
		}
	});
	
	// 提交输入的签名
	function updateIntro() {
		$introduction = $(".qianText").val();
		$.post("${ctx}/web/ai/upIntroduction.html" , {introduction : $introduction}, function(data){
			if (data.num == 1) {
				$(".textAre").remove();
			} else if (data.num == 2) {
				$(".textAre").remove();
				$(".qianInfo").text("");
				$(".qianInfo").text(data.content);
			}
		}, "json");
	}
	

	//向导
	$(".guide").click(function(){
		$(".guideTip").show();
		$(".qmtc").hide();
		$(".infoSow").hide();
		$(".szTx").show();
	});
	$(".txNex").click(function(){
		$(".guideTip").show();
		$(".szTx").hide();
		$(".infoSow").show();
	});
	$(".infoPre").click(function(){
		$(".guideTip").show();
		$(".infoSow").hide();
		$(".szTx").show();
	});
	$(".infoNex").click(function(){
		$(".guideTip").show();
		$(".infoSow").hide();
		$(".qmtc").show();
	});
	$(".qmPre").click(function(){
		$(".guideTip").show();
		$(".qmtc").hide();
		$(".infoSow").show();
	});
	$(".done").click(function(){
		$(".guideTip").hide();
	});
	$(".lead-close").click(function(){
		$(".guideTip").hide();
	});
</script>