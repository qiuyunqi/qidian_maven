<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-我的答案</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.all.min.js"></script>
	<style>
		.question{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.question a{
			color: #2db1e1 !important;
		}
		.lfContainer {
		    margin-left: 124px !important;
		}
		.txIcon{
			padding:0 !important;
		}
		.editorBody{
			display: none;
		}
		.collection{
			float:right;
			color: #727171;
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
		<!-- 左边部分 -->
		<div class="sideLft">
			<div class="kongge"></div>
			<%@ include file="../common/answerLft.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 查看 -->
			<div class="question-informat">
				<p>
					<c:if test="${question.isDelete==1}">
						<s>${question.title}</s><span class="redCol">(已删除)</span>
					</c:if>
					<c:if test="${question.isDelete!=1}">
						${question.title}
					</c:if>
				</p>
				<div class="question-daSh">
					<!-- 时间 -->
					<div class="question-date">
						<span class="glyphicon glyphicon-time"></span>
						<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) < 1}">
							<fmt:formatNumber value="${(nowDate.time-question.updateTime.time)/1000/60}" pattern="#0"/>分钟前
						</c:if>
						<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) < 24 && ((nowDate.time-question.updateTime.time)/1000/60/60) >= 1}">
							<fmt:formatNumber value="${(nowDate.time-question.updateTime.time)/1000/60/60}" pattern="#0"/>小时前
						</c:if>
						<c:if test="${((nowDate.time-question.updateTime.time)/1000/60/60) >= 24}">
							<fmt:formatDate value="${question.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
						</c:if>
					</div>
					<a class="collection" onclick="collectQuestion($(this),${question.id})"><span class="${isCollection==1?'glyphicon glyphicon-star':'glyphicon glyphicon-star-empty'}"></span> 收藏</a>
				</div>
				<!-- 标签 -->
				<div class="question-tag">
					<ul>
						<c:forEach items="${question.tagQuestions}" var="tagQuestion">
						<li>
							<a href="${ctx}/web/qida/tagsQs/${tagQuestion.qidaTags.id}.html" target="_blank">${tagQuestion.qidaTags.name}</a>
						</li>
						</c:forEach>
					</ul>
				</div>
				<!-- 具体问题 -->
				<div class="short-content">
					${question.detail}
					<c:if test="${not empty question.imgUrl}">
					<div class="short-question-img">
						<img src="${question.imgUrl}" alt="问题" />
					</div>
					</c:if>
				</div>
			</div>
			<div class="kongge"></div>
			<div class="answer-lists">
				<h4 class="questionAs">${question.replyNum}个回答</h4>
				<!-- 全部回答 -->
				<div class="dtAll commiInfo">
					<ul class="answerLists-ul">
					<!-- 第一条内容-->
					<c:forEach items="${qidaAnswers}" var="answer">
						<li>
							<div class="tx">
								<a class="txImg" href="javascript:void(0)" title="头像"><img src="${empty answer.fuUser.userAvatar?'../../images_qiDa/tx.png':answer.fuUser.userAvatar}" alt="头像" /></a>
							</div>
							<div class="discussner">
								<div class="userNm smalBlu">${empty answer.fuUser.nickName?'佚名':answer.fuUser.nickName}
								<span class="flatRgt grayCol">
									<c:if test="${((nowDate.time-answer.createTime.time)/1000/60/60) < 24}">
										<fmt:formatNumber value="${(nowDate.time-answer.createTime.time)/1000/60/60}" pattern="#0"/>小时前
									</c:if>
									<c:if test="${((nowDate.time-answer.createTime.time)/1000/60/60) >= 24}">
										<fmt:formatDate value="${answer.createTime}" pattern="yyyy/MM/dd"/>
									</c:if>
									</span></div>
								<div class="infoBody">
									<!-- 回答 -->
									<div class="short-content">
										<div class="replayCont">
											<c:if test="${answer.isDelete==1}">
												<s>${answer.content}</s><span class="redCol">(已删除)</span>
											</c:if>
											<c:if test="${answer.isDelete!=1}">
												${answer.content}
											</c:if>
										</div>
										<div class="replayChan"></div>
									</div>
								</div>
								<!-- 赞同/反对/评论/收藏 -->
								<div class="answCon">
									<!-- 赞同/反对/评论/收藏按钮 -->
									<div class="answerBody">
										<a onclick="savaAgree($(this),${answer.id},0)" title="赞同">赞同<span class="agreeNum">${answer.realSupportNum}</span></a>
										<a onclick="savaAgree($(this),${answer.id},1)" title="反对">反对<span class="disagreeNum">${answer.realNoSupportNum}</span></a>
										<c:if test="${answer.fuUser.id==fuUser.id}">
										<a class="editAnswer" onclick="editAnswer(${answer.id})" title="编辑">编辑</a>
										<input class="editHidden" type="hidden" value="0"/>
										</c:if>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>	
					<!-- 分页 -->
					<div class="page">
						<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/web/ai/answerInfo/${answer.id}.html"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
						</domi:pagination>
					</div>
				</div>	<!-- 全部回答结束 -->
			</div><!-- 回答end -->
			<!-- 我要回答 -->
				<!-- editor -->
				<c:if test="${!empty fuUser && question.isComment==0 && fuUser.id!=question.fuUser.id}">
				<div class="editorBody">
					<p>我要回答：</p>
					<div class="editor">
					    <script id="editor" type="text/plain" style="width:700px;height:300px;"></script>
					</div>
					<!-- 提交 -->
					<div class="answerAdd">
						 <label>
						  </label>
						  <input id="answerId" type="hidden" value=""/>
						  <a class="answerAddBtn" onclick="saveEditAnswer()" title="提交">提交</a>
					</div>
					<div class="kongge"></div>
				</div>
				</c:if>
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>	
	<!-- footer end -->
	
	<script type="text/javascript">
	//展开收起
   	/* $(".replayCont").each(function(){
		var obString=$(this).text();
		var length=obString.length;
		var sl=obString.substring(0,100)+"...";
		if(length>100){
			$(this).hide();
		   	$(this).next().html("<span class='rpyChan'>"+sl+"</span>"+"<a class='openAll'><展开全部></a>");
		   }else{
		   	$(this).show();
		   	$(this).text(obString);
		   	$(this).next().html();
		   }; 
	});
	
	$(".openAll").on("click",function(){
		var obString=$(this).parent().prev().text();
		var length=obString.length;
		var sl=obString.substring(0,100)+"...";
		var opentxt=$(this).text();
		if(opentxt=="<展开全部>"){
			$(this).prev().text(obString);
			$(this).text("<收起>");
		}else{
			$(this).prev().text(sl);
			$(this).text("<展开全部>");
		}
	}); */
	//展开收起end
	
	
		//点击编辑出现编辑框
		function editAnswer(answerId){
			if($(".editHidden").val()==0){
				$(".editorBody").show();
				$(".editHidden").val(1);
				$("#answerId").val(answerId);
			}else{
				$(".editorBody").hide();
				$(".editHidden").val(0);
			}
		}
		
		//赞同或反对此回答
		function savaAgree(thisA,id,type){
			$.post("${ctx}/web/qida/savaAgree/"+id+"/"+type+".html",null,function(data){
				if(data=="-1"){
					layer.open({
		 			    content: "未登录",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
	 				});
	 				location.href="${ctx}/web/ai/meindex.html";
					return false;
				}
				if(data=="hasUp"){
					layer.open({
		 			    content: "您已赞同",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
	 				});
					return false;
				}else if(data=="hasDown"){
					layer.open({
		 			    content: "您已反对",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			        layer.closeAll();
		 			    }
	 				});
					return false;
				}else{
					if(type==0){
						var agreeNum=thisA.find("span").text();
						thisA.find("span").text(parseInt(agreeNum)+1);
					}else{
						var disagreeNum=thisA.find("span").text();
						thisA.find("span").text(parseInt(disagreeNum)+1);
					}
				}
			})
		}
		
		//收藏此问题
		function collectQuestion(thisA,id){
			var childCla = thisA.find("span").attr("class");
			if(childCla == "glyphicon glyphicon-star-empty"){
				$.post("${ctx}/web/qida/collectQuestion/"+id+".html",null,function(data){
					if(data=="-1"){
						layer.open({
			 			    content: "未登录",
			 			    btn: ["确定"],
			 			    shadeClose: false,
			 			    yes: function(){
			 			        layer.closeAll();
			 			    }
		 				});
		 				location.href="${ctx}/web/ai/meindex.html";
						return false;
					}
					layer.open({
		 			    content: "收藏成功！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			    	thisA.find("span").attr("class","glyphicon glyphicon-star");
		 			        layer.closeAll();
		 			    }
		 			});
				})
			}else{
				$.post("${ctx}/web/qida/cancelCollectQuestion/"+id+".html",null,function(data){
					if(data=="-1"){
						layer.open({
			 			    content: "未登录",
			 			    btn: ["确定"],
			 			    shadeClose: false,
			 			    yes: function(){
			 			        layer.closeAll();
			 			    }
		 				});
		 				location.href="${ctx}/web/ai/meindex.html";
						return false;
					}
					layer.open({
		 			    content: "取消收藏成功！",
		 			    btn: ["确定"],
		 			    shadeClose: false,
		 			    yes: function(){
		 			    	thisA.find("span").attr("class","glyphicon glyphicon-star-empty");
		 			        layer.closeAll();
		 			    }
		 			});
				})
			}
		}
		
		//保存编辑的回答
		function saveEditAnswer(){
			if(!UE.getEditor('editor').getContentTxt()){
				layer.open({
	 			    content: "回复内容不能为空！",
	 			    btn: ["确定"],
	 			    shadeClose: false,
	 			    yes: function(){
	 			        layer.closeAll();
	 			    }
	 			});
	 			return false;
			}
			$.post("${ctx}/web/ai/saveEditAnswer/"+$("#answerId").val()+".html",{content:UE.getEditor('editor').getContentTxt()},function(){
				layer.open({
	 			    content: "回复成功！",
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
	
	<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor', {
	    toolbars: [
	        ['italic', 'underline', 'bold', 'undo']
	    ],
	    autoHeightEnabled: true,
	    autoFloatEnabled: true,
	    elementPathEnabled:false
	});

    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
    
    //判断footer是否在底部
	$(document).ready(function(){
	var topHgt = $(".navbar-static-top").height();
	var contHgt = $(".bodyHget").height();
	var sunHgt = topHgt + contHgt;
	var bodyHgt = $(window).height();
		if(sunHgt <bodyHgt-392){
			$(".conterAll").css("position", "");
			$(".bodyHget").height("600px");
		}else{
			$(".conterAll").css("position", "");
		}
	});
</script>
  </body>
</html>