<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/web/js_qiDa/globalTip.js"></script>
<!-- 问题详细页左边部分 -->
<div class="sidebar-left">
	<!-- 设置 -->
	<div class="leftContain">
		<c:if test="${res == 0}">
			<a class="cas" href="javascript:void(0);" onclick="guanzhu($(this),${question.fuUser.id})">
				<i class="glyphicon glyphicon-plus"></i> <span>关注</span>
			</a>
			</c:if>
			<c:if test="${res == 1}">
			<a class="cas" href="javascript:void(0);" onclick="qxgz($(this),${question.fuUser.id})">
				<i class="glyphicon glyphicon-minus"></i> <span>已关注</span>
			</a>	
		</c:if>
		<div class="lftSet">
			<a class="setBtn"><span class="glyphicon glyphicon-cog"></span> 设置</a>
			<c:if test="${fuUser.id == question.fuUser.id}">
			<ul class="setNav">
					<li><a onclick="delQuestion(${question.id})">删除问题</a></li>
					<li><a href="${ctx}/web/ai/editQuestion/${question.id}.html">编辑问题</a></li>
					<c:if test="${question.isComment==0}">
					<li><a onclick="closeComment(${question.id})">关闭评论</a></li>
					</c:if>
					<c:if test="${question.isComment==1}">
					<li><a onclick="openComment(${question.id})">开启评论</a></li>
					</c:if>
			</ul>
			</c:if>
		</div>
	</div>
	<!-- 该问题作者 -->
	<div class="author">
		<p>该问题作者：<span class="smalBlu">${question.fuUser.nickName==null?'佚名':question.fuUser.nickName}</span></p>
		<p>问题更新于：<fmt:formatDate value="${question.updateTime}" pattern="yyyy-MM-dd"/> </p>
		<p>浏览数：${question.pageViews==null?0:question.pageViews}</p>
	</div>
	<!-- 关注的用户 -->
	<div class="care">
		<p>关注的用户</p>
		<c:if test="${empty concernList}">
		<div class="careUsers">还没有关注的用户</div>
		</c:if>
		<c:if test="${not empty concernList}">
		<ul class="careUl">
			<c:forEach items="${concernList}" var="concern">
			<li><a><img src="${concern.beConcernUser.userAvatar==null?'../../images_qiDa/tx.png':concern.beConcernUser.userAvatar}" /></a></li>
			</c:forEach>
		</ul>
		</c:if>
	</div>
	<!-- 分享 -->
	<!-- <div class="clear answer-share">
		<a title="QQ空间">
			<img src="/qidian_maven/web/images_qiDa/qq.png">
		</a>
		<a title="新浪微博">
			<img src="/qidian_maven/web/images_qiDa/sina.png">
		</a>
		<a title="腾讯微博">
			<img src="/qidian_maven/web/images_qiDa/tengx.png">
		</a>	
	</div> -->
</div>

<script>
//鼠标滑过出现设置菜单
	$(".setBtn").mouseover(function(){
		$(".setNav").show();
	});
	$(".setBtn").mouseout(function(){
		$(".setNav").hide();
	});
	$(".setNav").mouseover(function(){
		$(".setNav").show();
	});
	$(".setNav").mouseout(function(){
		$(".setNav").hide();
	});
	
	
	//关注提问用户
	function guanzhu(thisA,id){
		$.post("${ctx}/web/ai/watch/1.html", {userId: id}, function(data){
				var data=eval("("+data+")");
				if (data.is_message == -1) {
					 layer.open({
		 			    content: "未选定用户！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
		 			return false;
				}
				if (data.is_message == -2) {
					 layer.open({
		 			    content: "不能选定自己！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
		 			return false;
				}
				if (data.is_message == 1) {
					layer.open({
		 			    content: "关注成功！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
					thisA.find("i").attr("class","glyphicon glyphicon-minus");
					thisA.find("span").text("已关注");
					thisA.width("100px");
					location.href=location.href;
				}
		});
	}
	
	//取消关注提问用户
	function qxgz(thisA,id){
		$.post("${ctx}/web/ai/watch/2.html", {userId: id}, function(data){
				var data=eval("("+data+")");
				if (data.is_message == -1) {
					 layer.open({
		 			    content: "未选定用户！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
		 			return false;
				}
				if (data.is_message == -2) {
					 layer.open({
		 			    content: "不能选定自己！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
		 			return false;
				}
				if (data.is_message == 2) {
					layer.open({
		 			    content: "取消关注成功！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
		 			});
					thisA.find("i").attr("class","glyphicon glyphicon-plus");
					thisA.find("span").text("关注");
					thisA.width("90px");
					location.href=location.href;
				}
		});
	}
	
	//删除问题
	function delQuestion(id){
		$.post("${ctx}/web/qida/delQuestion/"+id+".html",null,function(data){
			if(data=="-1"){
				globalTip("该问题已有答案，请勿删除!");
				return false;
			}
			layer.open({
 			    content: "删除问题成功！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			        location.href="${ctx}/web/qida/questionAll.html";
 			    }
 			});
		},"json")
	}
	//关闭评论
	function closeComment(id){
		$.post("${ctx}/web/qida/closeComment/"+id+".html",null,function(){
			layer.open({
 			    content: "关闭评论成功！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			        location.href=location.href;
 			    }
 			});
		})
	}
	//开启评论
	function openComment(id){
		$.post("${ctx}/web/qida/openComment/"+id+".html",null,function(){
			layer.open({
 			    content: "开启评论成功！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			        location.href=location.href;
 			    }
 			});
		})
	}
</script>