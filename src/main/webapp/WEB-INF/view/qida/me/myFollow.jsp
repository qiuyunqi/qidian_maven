<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-我的关注</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/webuploader.css"></link>
	<script type="text/javascript" src="${ctx}/web/js_qiDa/webuploader.js"></script>
	<style>
		.txIcon {padding: 0 !important;}
		.lfContainer {
		    margin-left: 124px !important;
		}
		#uploader-demo{text-align: center;}
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
			<%@ include file="../common/meLft.jsp" %>
		</div>
		<!-- 右边部分 -->
		<div class="sidebar-right">
			<div class="kongge"></div>
			<!-- 头像等信息 -->
			<div class="userBody">
				<div class="userBody-toux">
					<div class="user-tx">
						<c:if test="${fuUser.userAvatar == null}">
							<img src = "${ctx}/web/images_qiDa/defTx.jpg" alt="头像" />
						</c:if>
						<c:if test="${fuUser.userAvatar != null}">
						<img src="${fuUser.userAvatar}" alt="头像" />
						</c:if>
					</div>
					<div class="userBody-tc">编辑头像</div>
				</div>
				<div class="userBody-name">${fuUser.nickName}</div>
				<div class="userBody-jifen">用户积分：<span>${fuUser.qidaIntegral==null?'0':fuUser.qidaIntegral}</span></div>
				<div class="userBody-jibie">用户级别：${fuUser.qidaRank==null?'会员':fuUser.qidaRank==0?'会员':'专家'}</div>
			</div>
			<!-- 答案/问题/关注/收藏 -->
			<div class="communit-nav">
				<ul>
					<li><a href="${ctx}/web/ai/meindex.html" title="答案">答案(${answerNum})</a></li>
					<li><a href="${ctx}/web/ai/questionAll.html" title="问题">问题(${questionNum})</a></li>
					<li class="communit-active"><a href="${ctx}/web/ai/concernAll.html" title="关注">关注(${concernNum})</a></li>
					<li><a href="${ctx}/web/ai/collectionAll.html" title="收藏">收藏(${collectionNum})</a></li>
				</ul>
			</div>
			<!-- 答案/问题/关注/收藏 的内容 -->
			<div class="communit-lists">
				<!-- 关注 -->
				<div class="commiInfo myFollow">
					<c:if test="${concernNum == 0}">
						您还没有关注的用户
					</c:if>
					<c:if test="${concernNum != 0}">
					<ul>
						<!-- 第一条内容 -->
						<c:forEach items="${concernList}" var="concern">
						<li class="caredList-info" onmouseover='mousove($(this),70,30,${concern.beConcernUser.id})'  onmouseout='mousout($(this))'>
							<a href="javascript:void(0)" title="关注用户">
								<img src="${empty concern.beConcernUser.userAvatar?'../images_qiDa/tx.png':concern.beConcernUser.userAvatar}" alt="头像" />
								<span>${concern.beConcernUser.nickName}</span>
							</a>
						</li>
						</c:forEach>
					</ul>	
					</c:if>
				</div>	<!-- 关注结束 -->
			</div><!-- 所有内容end -->
		</div>
	</div>
	<!-- footer -->
	<div class="kongge"></div>
	<%@ include file="/WEB-INF/view/qida/common/userInfo.jsp" %>
	<%@ include file="../common/footer.jsp" %>
	
	<!-- 上传头像弹窗 -->
	<div  class="txFc">
		<div id="uploader-demo">
			<a class="uploader-clos" href="javascript:void(0)" title="关闭">X</a>
		    <!--用来存放item-->
		    <div id="fileList" class="uploader-list"></div>
		    <input type="file" name="imgFile" id="filePicker" value="选择图片"/>
		    <div class="uploader-tip">格式：gif，jpg，jpeg，bmp，png </div>
		    <input type="hidden" name="userAvatar" id="userAvatar" value=""/>
		</div>
	</div>
<script type="text/javascript">
		//设置向导弹窗高度
		var bdhgt = $(".bodyHget").height();
		var sumGt = bdhgt + 476;
		$(".guideTip").height(sumGt);
	
		
		//鼠标经过头像出现编辑头像字样
		$(".user-tx").mouseover(function(){
			$(this).parent().find(".userBody-tc").show();
		});
		$(".userBody-tc").mouseover(function(){
			$(this).show();
		});
		$(".userBody-tc").mouseout(function(){
			$(this).hide();
		});
		$(".user-tx").mouseout(function(){
		$(this).parent().find(".userBody-tc").hide();
		});
		
   		
   		 //点击编辑头像
   		$(".userBody-tc").click(function(){
   			$(".txFc").css("left","45%");
   		});
   		
   		$(".uploader-clos").click(function(){
   			$(".txFc").css("left","-999px");
   		});  
</script>
<link href="${ctx}/web/js_qiDa/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/web/js_qiDa/uploadify-v2.1.4/swfobject.js"></script>
<script	type="text/javascript" src="${ctx}/web/js_qiDa/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript">
jQuery("#filePicker").uploadify({
	'uploader' : '${ctx}/web/js_qiDa/uploadify-v2.1.4/uploadify.swf?random=' + (new Date()).getTime(),
	'script' : '${ctx}/web/upload/img.html',//请求地址
	'cancelImg' : '${ctx}/web/js_qiDa/uploadify-v2.1.4/cancel.png',
	'fileDataName' : 'imgFile', //相当于  file 控件的 name
	'auto' : true,
	'multi' : false,
	'buttonImg' : '${ctx}/web/images_qiDa/fileBtn.png',
	'height' : '31',
	'width' : '86',
	'fileDesc' : '能上传的图片类型:jpg,gif,bmp,jpeg,png', //出现在上传对话框中的文件类型描述
	'fileExt' : '*.jpg;*.gif;*.bmp;*.jpeg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	'sizeLimit' : 3*1024*1024,
	onComplete:function(event,queueID,fileObj,response,data){
			var jsondata = eval("("+response+")");
			if(jsondata.error==1){
				Dialog.alert(jsondata.message);
				return false;
			}
			$(".txFc").hide();
			$(".user-tx img").attr("src", jsondata.saveDir);
			$("#userAvatar").val(jsondata.saveDir);//图片的地址
			$("#span1").html("已上传文件："+jsondata.fileName);
			$.post("${ctx}/web/ai/editUserAvatar.html",{userAvatar:$("#userAvatar").val()},function(data){
				if (data.success == 1) {
					globalTip("编辑图片成功!");
				}
			}, "json")
		},
		'onSelect' : function(event,queueID, fileObj) {
			if (fileObj.size > 5*1024*1024) {
				Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
				jQuery("#filePicker").uploadifyClearQueue();
			}
		}
});
</script>
 </body>
</html>