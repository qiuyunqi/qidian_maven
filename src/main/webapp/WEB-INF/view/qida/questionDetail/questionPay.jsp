<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-付费问题</title>
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
		.money .mony {
		    background: rgba(0, 0, 0, 0) url("../../images_qiDa/money.png") no-repeat scroll 0 0 / 70% auto;
		    display: block;
		    float: left;
		    height: 24px;
		    margin: 0 0 0 5px;
		    width: 34px;
		}
		.money .moneyAcv {
		    background: rgba(0, 0, 0, 0) url("../../images_qiDa/moneyAcv.png") no-repeat scroll 0 0 / 70% auto;
		    display: block;
		    float: left;
		    height: 24px;
		    margin: 0 0 0 5px;
		    width: 34px;
		}
		.money {
		    color: red;
		    display: inline-block;
		    font-size: 14px;
		    font-weight: normal;
		    position: relative;
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
  	<%@ include file="/WEB-INF/view/qida/common/topQiDa.jsp" %>
	<!-- 正文内容 -->
	<div class="container bodyHget">
		<!-- 左边部分 -->
		<div class="sideLft">
			<div class="kongge"></div>
			<%@ include file="/WEB-INF/view/qida/common/questionLft.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 问题 -->
			<div class="question-informat">
				<p class="payP">
					${question.title}
					<a class="money" href="javascript:void(0)" title="付费">
						<i class="${question.replyNum==0?'mony':'moneyAcv'}"></i>${question.reward }
					</a>
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
					<c:if test="${not empty question.answerUser && question.replyNum==0 && timeDiffer>2}"><div class="zjInfo">超时未回答，可以抢答</div></c:if>
					<c:if test="${not empty question.answerUser && timeDiffer<=2}"><div class="zjInfo">已邀请${question.answerUser.nickName==null?'佚名':question.answerUser.nickName}</div></c:if>
					<a class="collection" onclick="collectQuestion($(this),${question.id})"><span class="${isCollection==1?'glyphicon glyphicon-star':'glyphicon glyphicon-star-empty'}"></span> 收藏</a>
				</div>
				<!-- 标签 -->
				<div class="question-tag">
					<ul>
						<c:forEach items="${question.tagQuestions}" var="tagQuestions">
							<li><a href="${ctx}/web/qida/tagsQs/${tagQuestions.qidaTags.id}.html" target="_blank">${tagQuestions.qidaTags.name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- 股票代码 -->
				<div class="question-stock">
					<ul>
						<c:forEach items="${question.stockQuestions}" var="stockQuestion">
							<li><a href="${ctx}/web/qida/find/${stockQuestion.stockInfo.id}.html" target="_blank">${stockQuestion.stockInfo.name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- 具体问题 -->
				<div class="short-content">
					${question.detail }
					<div class="short-question-img">
						<c:if test="${!empty question.imgUrl}">
							<img src="${question.imgUrl}" alt="问题"/>
						</c:if>
					</div>
				</div>
				<!-- 评论 -->
				<div class="discussBody">
					<div class="discussBtnBod">
						<c:if test="${fuUser.id==question.fuUser.id}">
						<a class="invite" href="javascript:void(0)" title="邀请专家"><i class="inviteImg"></i>邀请专家<input class="inviteHid" type="hidden" value="0"/></a>
						</c:if>
						<a class="discussBtn" href="javascript:void(0)" title="评论">评论${size}<input class="discussHid" type="hidden" value="0"/></a>
					</div>
					<div class="zjList">
						<ul class="zjList-ul">
						</ul>
						<nav class="fenye">
							<ul class="pager">
							   <li><a href="javascript:void(0)" onclick="lastPage()" title="上一页">上一页</a></li>
							   <li><a href="javascript:void(0)" onclick="nextPage()" title="下一页">下一页</a></li>
							 </ul>
						</nav>
					</div>
					<!-- 评论列表 -->
					<div class="discussList">
						<!-- 评论内容 -->
						<ul class="discussList-ul">
						<c:forEach items="${question.questionComments}" var="comment" varStatus="status">
							<c:if test="${comment.state == 1 && comment.isDelete == 0}">
							<li>
								<div class="tx">
									<a class="txImg"  href="javascript:void(0)" title="头像">
										<c:if test="${empty comment.fuUser.userAvatar}">
										<img src="${ctx}/web/images_qiDa/tx.png" alt="头像"/>
										</c:if>
										
										<c:if test="${not empty comment.fuUser.userAvatar}">
										<img src="${comment.fuUser.userAvatar}" alt="头像"/>
										</c:if>
									</a>
								</div>
								<div class="discussner">
									<div class="userNm smalBlu">${empty comment.fuUser.nickName ? '佚名' : comment.fuUser.nickName}
									<span class="paydate grayCol">
									<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) < 1}">
										<fmt:formatNumber value="${(nowDate.time-comment.createTime.time)/1000/60}" pattern="#0"/>分钟前
									</c:if>
									<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) < 24 && ((nowDate.time-comment.createTime.time)/1000/60/60) >= 1}">
										<fmt:formatNumber value="${(nowDate.time-comment.createTime.time)/1000/60/60}" pattern="#0"/>小时前
									</c:if>
									<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) >= 24}">
										<fmt:formatDate value="${comment.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
									</c:if>
									</span>
									<span class="floorNum">${status.index+1}楼</span>
									</div>
									<div class="infoBody">
										<!-- 回答 -->
										<div class="short-content">
											${comment.content}
										</div>
									</div>
								</div>
							</li>
							</c:if>
						</c:forEach>
							
						</ul>
						<!-- 提交评论 -->
						<div class="addDisgus">
							<form method="post">
								<textarea id="questionText" class="addTexa" rows="3" placeholder="请在这里写下评论..."></textarea>
								<a class="addTexaBtn" href="javascript:void(0)" onclick="saveQuestionComment(${question.id}, 0);" title="提交">提交</a>
							</form>
						</div>
					</div><!-- 评论列表end -->
				</div><!-- 评论end -->
			</div>
			<div class="kongge"></div>
			<!-- 回答 -->
			<div class="answer-lists">
				<h4 class="questionAs">${question.replyNum}个回答</h4>
				<!-- 全部回答 -->
				<div class="dtAll commiInfo">
					<ul class="answerLists-ul">
						<c:forEach items="${question.qidaAnswers}" var="answer">
							<c:if test="${answer.state==1 && answer.isDelete==0}">
							<li>
								<div class="tx">
									<a class="txImg" href="javascript:void(0)" title="头像"><img src="${empty answer.fuUser.userAvatar?'${ctx}/web/images_qiDa/tx.png':answer.fuUser.userAvatar}" alt="头像"/></a>
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
											${answer.content}
										</div>
									</div>
									<!-- 赞同/反对/评论/收藏 -->
									<div class="answCon">
										<!-- 赞同/反对/评论/收藏按钮 -->
										<div class="answerBody">
											<a onclick="savaAgree($(this),${answer.id},0)" href="javascript:void(0)" title="赞同">赞同<span class="agreeNum">${answer.realSupportNum}</span></a>
											<a onclick="savaAgree($(this),${answer.id},1)" href="javascript:void(0)" title="反对">反对<span class="disagreeNum">${answer.realNoSupportNum}</span></a>
											<a class="answDis-btn" href="javascript:void(0)" title="评论">评论${fn:length(answer.answerComments)}</a>
										</div>
										<!-- 评论列表 -->
										<div class="answDis-info">
											<!-- 评论内容 -->
											<ul class="discussList-ul">
												<c:forEach items="${answer.answerComments}" var="comment" varStatus="status" >
													<c:if test="${comment.state == 1 && comment.isDelete == 0}">
													<li>
														<div class="tx">
															<a class="txImg"  href="javascript:void(0)" title="头像">
																<c:if test="${empty comment.fuUser.userAvatar}">
																<img src="${ctx}/web/images_qiDa/tx.png" alt="头像"/>
																</c:if>
																
																<c:if test="${not empty comment.fuUser.userAvatar}">
																<img src="${comment.fuUser.userAvatar}" alt="头像"/>
																</c:if>
															</a>
														</div>
														<div class="discussner">
															<div class="userNm smalBlu">${empty comment.fuUser.nickName ? '佚名' : comment.fuUser.nickName}
															<span class="paydate grayCol">
															<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) < 1}">
																<fmt:formatNumber value="${(nowDate.time-comment.createTime.time)/1000/60}" pattern="#0"/>分钟前
															</c:if>
															<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) < 24 && ((nowDate.time-comment.createTime.time)/1000/60/60) >= 1}">
																<fmt:formatNumber value="${(nowDate.time-comment.createTime.time)/1000/60/60}" pattern="#0"/>小时前
															</c:if>
															<c:if test="${((nowDate.time-comment.createTime.time)/1000/60/60) >= 24}">
																<fmt:formatDate value="${comment.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
															</c:if>
															</span>
															<span class="floorNum">${status.index+1}楼</span>
															</div>
															<div class="infoBody">
																<!-- 回答 -->
																<div class="short-content">
																	${comment.content}
																</div>
															</div>
														</div>
													</li>
													</c:if>
												</c:forEach>
											</ul>
											<!-- 提交评论 -->
											<div class="addDisgus">
												<form action="" method="post">
													<textarea id="addAnswer" class="addTexa" rows="3" placeholder="请在这里写下评论..."></textarea>
													<a class="addTexaBtn" href="javascript:void(0)" onclick="saveAnswerComment(${answer.id}, 1);" title="提交">提交</a>
												</form>
											</div>
										</div><!-- 评论列表结束 -->
									</div>
								</div>
							</li>
							</c:if>
						</c:forEach>
					</ul>	
				</div>	<!-- 全部回答结束 -->
			</div><!-- 回答end -->
			<!-- 我要回答 -->
			<c:if test="${!empty fuUser && fuUser.qidaRank==1 && question.isComment==0 && fn:length(question.qidaAnswers)==0 && fuUser.id!=question.fuUser.id}"><!--当用户不为空必须是专家而且不能是提问者，问题评论是开启，问题还没有人回答  -->
				<c:if test="${empty question.answerUser}"><!-- 问题没有邀请专家回答,所有专家都可以回答 -->
					<div class="editorBody">
						<p>我要回答：</p>
						<div class="editor">
						    <script id="editor" type="text/plain" style="width:700px;height:300px;"></script>
						</div>
						<!-- 提交 -->
						<div class="answerAdd">
							 <label>
							  </label>
							  <a class="answerAddBtn" onclick="saveAnswerByPayQuestion(${question.id})" title="提交">提交</a>
						</div>
						<div class="kongge"></div>
					</div>
				</c:if>
				<c:if test="${not empty question.answerUser}"><!-- 问题邀请专家回答了 -->
					<c:choose>
					<c:when test="${fuUser.id==question.answerUser.id && timeDiffer<=2}"><!-- 当前用户id等于邀请回答专家的id，并且当前访问时间和问题更新时间差小于等于2小时 -->
						<div class="editorBody">
							<p>我要回答：</p>
							<div class="editor">
							    <script id="editor" type="text/plain" style="width:700px;height:300px;"></script>
							</div>
							<!-- 提交 -->
							<div class="answerAdd">
								 <label>
								  </label>
								  <a class="answerAddBtn" onclick="saveAnswerByPayQuestion(${question.id})" title="提交">提交</a>
							</div>
							<div class="kongge"></div>
						</div>
					</c:when>
					<c:when test="${timeDiffer>2}"><!-- 当前访问时间和问题更新时间差大于2小时,所有专家都可以回答 -->
						<div class="editorBody">
							<p>我要回答：</p>
							<div class="editor">
							    <script id="editor" type="text/plain" style="width:700px;height:300px;"></script>
							</div>
							<!-- 提交 -->
							<div class="answerAdd">
								 <label>
								  </label>
								  <a class="answerAddBtn" onclick="saveAnswerByPayQuestion(${question.id})" title="提交">提交</a>
							</div>
							<div class="kongge"></div>
						</div>
					</c:when>
					</c:choose>
				</c:if>
			</c:if>
		</div>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/view/qida/common/footerMes.jsp" %>
	<!-- footer end -->
<script type="text/javascript">
// 添加评论
function saveQuestionComment(questionId, flag){
	$content = $("#questionText").val();
	if (check($content)) {
		$.post("${ctx}/web/ai/addComment.html", {'questionId': questionId, 'content': $content, 'flag': flag}, function(data){
			if (data.success == 0) {
				globalTip(data.message);
				return false;
			}
			if (data.success == 1) {
				globalTip("已提交问题评论，信息审核中，请稍等!");
				location.href=window.location.href;
			}
		}, "json");
	}
}
function saveAnswerComment(answerId, flag){
	$content = $("#addAnswer").val();
	if (check($content)) {
		$.post("${ctx}/web/ai/addComment.html", {'questionId': answerId, 'content': $content, 'flag': flag}, function(data){
			if (data.success == 0) {
				globalTip(data.message);
				return false;
			}
			if (data.success == 1) {
				globalTip("已提交答案评论，信息审核中，请稍等!");
				location.href=location.href;
			}
		}, "json");
	}
}
function check($content) {
	if (null == $content || "" == $content) {
		globalTip("请输入评论内容");
		return false;
	}
	
	if ($content.length <5 || $content.length > 200) {
		globalTip("评论内容长度5-200");
		return false;
	}
	return true;
}
</script>
<script type="text/javascript">
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor', {
	    toolbars: [
	        ['italic', 'underline', 'bold','undo']
	    ],
	    autoHeightEnabled: true,
	    autoFloatEnabled: true,
	    elementPathEnabled:false
	});
	
	
		//邀请专家
		$(".invite").click(function(){
			if($(".inviteHid").val()==0){
				$(".zjList").show();
				$(".inviteHid").val(1);
			}else{
				$(".zjList").hide();
				$(".inviteHid").val(0);
			}
		});
		//评论
		$(".discussBtn").click(function(){
			if($(".discussHid").val()==0){
				$(".addDisgus").show();
				$(".discussHid").val(1);
			}else{
				$(".addDisgus").hide();
				$(".discussHid").val(0);
			}
		});
		//回答中的评论
		$(".answDis-btn").click(function(){
			var ansAct = $(this).parent().attr("class");
			if(ansAct != "answerBody actv"){
				$(this).parent().attr("class","answerBody actv");
				$(this).parent().next().find(".addDisgus").show();
			}else{
				 $(this).parent().attr("class","answerBody");
				$(this).parent().next().find(".addDisgus").hide();
			}
		});
		
		var totalCount=${totalCount};
		var pageNo=1;
		var pageSize=4;
		var totalPage=(totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
		searchExpert(pageNo,pageSize);
		
		//分页查询邀请的专家
		function searchExpert(pageNo,pageSize){
			$.post("${ctx}/web/qida/questionExpert.html",{pageNo:pageNo,pageSize:pageSize},function(data){
				var data=eval("("+data+")");
				$(".zjList-ul").find("li").remove(); 
				var length=data.array.length;
				var result = '';
				for(var i=0;i<length;i++){
					var arrText = [];
					arrText.push("<li><div class='tx'><a class='txImg'><img src='"+data.array[i].userAvatar+"' /></a></div>");
					arrText.push("<div class='zjList-infos'><div class='zjList-inf userNm grayCol'><span class='smalBlu'>"+data.array[i].nickName+"</span><br></div>");
					if(${question.answerUser==null}){
						arrText.push("<a class='zjList-btn' onclick='InvitationAnswer("+data.array[i].id+",${question.id})'>邀请回答</a>");
					}
					if(data.array[i].id==${question.answerUser==null?0:question.answerUser.id}){
						arrText.push("<a class='zjList-btnac'>已邀请</a>");
					}
					arrText.push("</div></li>");
					result +=  arrText.join('');
				}
				$(".zjList-ul").append(result);
			});
		}
		
		//上一页
		function lastPage(){
			pageNo -= 1;
			pageNo=pageNo>=0?pageNo:0;
			searchExpert(pageNo,pageSize);
		}
		
		//下一页
		function nextPage(){
			if(pageNo+1<=totalPage){
				pageNo += 1;
			}
			searchExpert(pageNo,pageSize);
		}
		
		//邀请专家解答
		function InvitationAnswer(expertId,questionId){
			$.post("${ctx}/web/qida/InvitationAnswer/"+expertId+"/"+questionId+".html",null,function(){
				layer.open({
	 			    content: "邀请成功！",
	 			    btn: ["确定"],
	 			    shadeClose: false,
	 			    yes: function(){
	 			        layer.closeAll();
	 			        location.href=location.href;
	 			    }
 				});
			})
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
		
		//保存答案
		function saveAnswerByPayQuestion(id){
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
			$.post("${ctx}/web/qida/saveAnswerByPayQuestion.html",{id:id, content:ue.getContent()},function(data){
				if(data=="-1"){
					globalTip("回复失败，该问题评论已关闭!");
					return false;
				}
				if(data=="-2"){
					globalTip("回复失败，该问题已经有新的答案!");
					return false;
				}
				if(data=="-3"){
					globalTip("付费问题只允许专家回答!");
					return false;
				}
				if(data=="-4"){
					globalTip("两小时之后邀请的专家没有答案，您才能提交答案!");
					return false;
				}
				globalTip("已提交答案，信息审核中，请稍等!");
	 			location.href=location.href;
			})
		}
	</script>
	
	<script type="text/javascript">
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
</script>
<script type="text/javascript">
//判断footer是否在底部
window.onload = function() { 
		var contHgt = $(".bodyHget").height()+20;
		var bodyHgt = $(window).height();
		if(contHgt <bodyHgt-445){
			$(".conterAll").css("position", "");
			$(".bodyHget").height("700px");
		}else{
			$(".conterAll").css("position", "");
		} ;
	};
</script>
  </body>
</html>