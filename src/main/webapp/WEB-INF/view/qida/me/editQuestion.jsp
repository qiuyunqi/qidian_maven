<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/WEB-INF/view/common/meta.jsp" %>
    <title>${qidatitle}-编辑问题</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/bootstrap.min.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/jquery-ui.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_qiDa/qiDa.css"></link>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/web/js_qiDa/ueditor.all.min.js"></script>
	<link href="${ctx}/web/js_qiDa/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
	<style>
		.ask{
			background: #f0f9ff none repeat scroll 0 0;
		    border-top: 2px solid #2db1e1;
		}
		.ask a{
			color: #2db1e1 !important;
		}
		.editor{
			width:100%;
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
		<div class="kongge"></div>
		<!-- 查看/编辑 -->
			<div class="communit-nav">
				<ul>
					<li>
						<c:if test="${question.isReward == 0}">
							<a class="meTits" href="${ctx}/web/qida/question/${question.id}.html">查看</a>
						</c:if>
						<c:if test="${question.isReward == 1}">
							<a class="meTits" href="${ctx}/web/qida/questionPay/${question.id}.html" >查看</a>
						</c:if>
					</li>
					<li class="communit-active"><a href="javascript:void(0)">编辑</a></li>
				</ul>
			</div>
		<!-- 查看/编辑end -->
		<form action="" method="post">
			<input type="hidden" name="id" value="${question.id}">
			<div class="askTit">
				<p>请用一句话描述你的问题，45字以内</p>
				<input type="text" class="askTit-input" value="${question.title}"/>
			</div>
			<!-- 添加股票 -->
			<div class="askStock">
				<p>股票代码</p>
				<div class="askStock-content">
					<ul class="hotTagsts" id="addStc">
					<c:forEach items="${question.stockQuestions}" var="stock">
						<li>${stock.stockInfo.name}<span class='redClor' onclick="clos($(this));">x</span>
							<input type="hidden" class='hiddren_t' value="${stock.stockInfo.id}"> 
						</li>
					</c:forEach>
					</ul>
				</div> 
				<div class="clear"></div>
				<span class="addStock">添加股票：</span>
				<input type="text" autocomplete="off" name="stockInfo" id = "stockCode" class="askStock-input sercInput" />
			</div>
			<!-- editor -->
			<div class="editorBody clear">
				<p>补充问题</p>
				<div class="editor">
				    <textarea id="editor" name="editor" style="width: 700px; height: 300px;">${question.detail}</textarea>
				</div>
			</div>
			<div>提问图片：</div>
       	  		<input type="file" name="imgFile" id="uploadImg1"/>
			<c:if test="${not empty question.imgUrl}">
            <div class="form quali_img" id="div1" style="display: block;">
	   			<span class="sctp" style="margin-left: 116px">
	   				<img style="margin-top: -15px" id="picShow1" src="${questoin.imgUrl}" width="320" height="180"></span>
	       		<input type="hidden" name="imageUrl" id="picValue1" value=""/>
	       		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
	   		</div>
	   		</c:if>
	   		<c:if test="${empty question.imgUrl}">
            <div class="form quali_img" id="div1" style="display: none;">
	   			<span class="sctp" style="margin-left: 116px">
	   				<img style="margin-top: -15px" id="picShow1" src="${questoin.imgUrl}" width="320" height="180"></span>
	       		<input type="hidden" name="imageUrl" id="picValue1" value=""/>
	       		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
	   		</div>
	   		</c:if>
			<!-- 提问服务 -->
			<div class="askService">
				<div class="askService-tit">提问服务：</div>
				<div class="askService-info">
					<div class="checkbox mesgeServ">
					  <label>
					  	<%-- <c:if test="${question.isMessage == 0}">
					  		<input type="checkbox" id="blankCheckbox1" value="0">
					  	</c:if>
					  	<c:if test="${question.isMessage == 1}">
					  		<input type="checkbox" id="blankCheckbox1" checked="checked" value="1">
					  	</c:if> --%>
					    
					    <div class="checkbox-txt">
					    	<div class="checkbox-txtOne">收到答案时短信通知<!-- <a href="http://u.hhr360.com/user_center/centerIndex.htm" class="defaultCol"> --><span>${fuUser.phone}</span><!-- [更改]</a> --></div>
					    </div>
					  </label>
					 <!--  <div class="checkbox-txtTwo">
					    <i class="mon"></i>0.1
					    弹窗提示扣费
						<span class="payTip">短信通知需每次扣费0.1积分</span>
					   </div> -->
					</div>
					<div class="checkbox xuans">
					  <label>
					 	<%--  <c:if test="${question.isReward == 0}">
					  		 <input type="checkbox" id="blankCheckbox2" value="0">
					  	</c:if>
					  	<c:if test="${question.isReward == 1}">
					  		<input type="checkbox" id="blankCheckbox2" checked="checked" value="1">
					  	</c:if> --%>
					    <div class="checkbox-txt">
					    	<div class="checkbox-txtOne">答题悬赏
					    		<div class="selectBody">
					    			<i class="mon"></i>
					    			<select class="form-control">
					    			 <%--  <option <c:if test="${question.reward == 0}">selected = "selected"</c:if> value="0">请选择悬赏的积分</option>
									  <option <c:if test="${question.reward == 10}">selected = "selected"</c:if> value="10">10</option>
									  <option <c:if test="${question.reward == 20}">selected = "selected"</c:if> value="20">20</option>
									  <option <c:if test="${question.reward == 30}">selected = "selected"</c:if> value="30">30</option>
									  <option <c:if test="${question.reward == 40}">selected = "selected"</c:if> value="40">40</option> --%>
									  <option selected = "selected" value="${question.reward}">${question.reward}</option>
									</select>
					    		</div>
							</div>
					    	<div class="checkbox-txtMony">您的财富值：<span>${fuUser.qidaIntegral}</span></div>
					    </div>
					  </label>
					</div>
				</div>
			</div>
			<!-- 问题分类 -->
			<div class="askStock">
				<p>问题分类</p>
				<div class="askStock-content">
					<ul class="hotTagsts" id="addTag">
					<c:forEach items="${question.tagQuestions}" var="tag">
						<li data-id="${tag.qidaTags.id}" data-name="${tag.qidaTags.name}">${tag.qidaTags.name}<span class="redClor" onclick="clos($(this));" >x</span></li>
					</c:forEach>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="addStocksDiv">
					<input type="text" name="tags" class="askStock-input" />
					<a href="javascript:void(0);" class="addStocks" id="createTags">创建标签</a>
				</div>
				<div class="askStock-hotTag">
					<div class="askStock-hotTagTit">
						添加热门标签：
					</div>
					<div class="askStock-hotTagList">
						<ul class="hotTagsts">
							<c:forEach items="${tagList}" var = "tag">
								<li data-id = "${tag.id}" data-name="${tag.name}" data-flag="0" onclick="addTag($(this))">${tag.name}<span class="redClor">+</span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<a href="javascript:void(0);" class="tijiao clear" id="submitQue">提交</a>
		</form>
	</div>
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(function() {
    var projects = ${items};
    $( "#stockCode" ).autocomplete({
      minLength: 0,
      source: projects,
      focus: function( event, ui ) {
        $( "#stockCode" ).val( ui.item.category );
        return false;
      },
      select: function( event, ui ) {
    	var $stockObj = $(".hiddren_t");
    	var $falg = true;
    	var i= 0;
  		$stockObj.each(function(){
  			if (ui.item.id == $(this).val()) {
  				$falg = false;
  				return false;
  			}
  			i++;
  		});
  		if (i >= 5) {
  			globalTip("最多只能添加5支股票");
  			return false;
  		}
  		if ($falg) {
	  		var html = "<li>"+
				ui.item.category+"<span class='redClor' onclick='clos($(this));'>x</span>"+
			"<input type='hidden' class='hiddren_t' value='"+ui.item.id+"'>"+ 
			"</li>";
			$("#addStc").append(html);
  		} else {
  			globalTip("不能加入重复的股票");
  		}
      	return false;
      }
    })
    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append(  "<a>" + "<span>"+ item.category + "</span>" +  "<span class='fgt'>"+ item.code+ "</span>" + "</a>")
        .appendTo( ul );
    };
});

function delTag(ths){
	var $flag = ths.data("flag");
	var $id = ths.data("id");
	ths.parent().remove();
	if ($flag == 1) { // 新创建
		$.post("${ctx}/web/qida/delete.html", {id: $id}, function(data) {
			if (data.success == 0) {
				globalTip(data.message);
			} 
		}, "json"); 
	}
}

function clos(ths){
	ths.parent().remove();
}

function addTag(tag) {
	var $id = tag.data("id");
	var $tags = $("#addTag li");
	var i = 0;
	$tags.each(function(){
		if ($id == $(this).data("id")) {
			globalTip("不能加入重复的标签");
			return false;
		}	
		i++;
	});
	if (i >= 5) {
		globalTip("最多可以添加5个标签");
		return  false;
	}
	var $tagName = tag.data("name");
	var html = "<li data-id='"+$id+"' data-name='"+$tagName+"' data-flag='"+$flag+"'>"+$tagName+"<span class='redClor' onclick='delTag($(this));' >x</span></li>";
	$("#addTag").append(html);
	$("input[name='tags'").val("");
	tag.remove();
}
</script>
<script type="text/javascript">
	/*点击金币出现弹框*/
	$(".checkbox-txtTwo").click(function(){
		var monAct = $(this).find("span").attr("class");
		if(monAct != "payTip actv"){
			$(this).find("span").attr("class","payTip actv");
			$(this).find("span").show();
		}else{
			$(this).find("span").attr("class","payTip");
			$(this).find("span").hide();
		}
	});
	
	/*创建标签*/
	$("#createTags").click(function() {
		var $tag = $("input[name='tags'").val();
		if (!$tag) {
			globalTip("请输入标签名称", $("input[name='tags'"));
			return false;
		} else if ($tag.length > 6 || $tag.length < 2) {
			globalTip("标签的长度是2-6个字", $("input[name='tags'"));
			return false;
		} else {
			$.post("${ctx}/web/ai/saveTag.html", {tagName: $tag}, function(data) {
				if (data.success == 1) {
					var html = "<li data-id='"+data.id+"' data-flag='"+data.flag+"'>"+$tag+"<span class='redClor'onclick='delTag($(this))' >x</span></li>";
					$("#addTag").append(html);
					$("input[name='tags'").val("");
				} else {
					globalTip(data.message);
				}
			}, "json");
		}
	});
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
    
	$("#submitQue").click(function() {
		var $id = $("input[name='id']").val();
		var $title = $(".askTit-input").val();
		var $stockObj = $(".hiddren_t");
		var $stockIds="";
		$stockObj.each(function(){
			$stockIds += $(this).val()+",";		
		});
		var $editor = ue.getContent();
		var $imgSrc = $("#picValue1").val();
		// var $isMsg = $("#blankCheckbox1").val();
		/* var $rewardValue = 0;
		if ($("#blankCheckbox2").is(":checked")) {
		} else {
			$rewardValue = 0;
		} */
		var $rewardValue = $(".form-control option:selected").val();
		var $isMsg = 1;
		var $tags = $("#addTag li");
		var $tagIds = "";
		$tags.each(function(){
			$tagIds += $(this).data("id")+",";		
		});
		if (isCheck($id, $title)) {
			$.ajax({
				type: 'POST',
				url: "${ctx}/web/ai/updateQuestion.html",
				data: {'id': $id, 'title': $title, 'stockIds': $stockIds, 'content': $editor, 'imgSrc':$imgSrc, 'isMsg': $isMsg, 'rewardValue': $rewardValue, 'tagIds':$tagIds},
				success: function(data){
					layer.closeAll();
					if (data.success == 0) {
						globalTip(data.message);
					}  else {
						globalTip("已提交问题，信息审核中，请稍等！");
						location.href="${ctx}/web/qida/index.html";
					}
				},
				beforeSend:function() {
					layer.open({type: 2, content: '正在修改中'});
				},
				dataType: "json"
			});
		}
	});
	
    function isCheck(id, title) {
    	if (null == id || "" == id || undefined == id) {
    		globalTip("非法请求页面, 请联系管理员");
    		return false;
    	}
    	if (null == title || "" == title || undefined == title) {
    		globalTip("请输入标题", $(".askTit-input"));
    		return false;
    	}
    	if (title.length > 45 || title.length < 3) {
    		globalTip("标题长度3-45个字", $(".askTit-input"));
    		return false;
    	}
    	$rewardValue = $(".form-control option:selected").val();
		if ($rewardValue == 0) {
			globalTip("请选择悬赏积分", $(".form-control"));
			return false;
		}
    	/*验证收到短信积分*/
		/* if($("#blankCheckbox1").is(":checked")) { // 选中
			$.post("${ctx}/web/ai/checkMsg.html", null, function(data){
				if (data.success == 0) { // 积分不足, 不能发送短信通知
					$("#blankCheckbox1").removeAttr("checked");
					globalTip("积分不足, 不能发送短信通知");
				} else {
					$("#blankCheckbox1").val(1);
				}
			}, "json");
		} */
		
		/*验证答题悬赏 */
		var $rewardValue = $(".form-control option:selected").val();
		if ($rewardValue == 0) {
			$("#blankCheckbox2").removeAttr("checked");
			globalTip("请选择悬赏积分");
			return false;
		}
		$.post("${ctx}/web/ai/checkReward.html", {isMsg: 1, rewardValue: $rewardValue}, function(data){
			if (data.success == 0) { // 积分不足, 不能发送短信通知
				globalTip("积分不足, 不能发送问题");
				return false;
			}
		}, "json");
    	return true;
    }
</script>
<script type="text/javascript" src="${ctx}/web/js_qiDa/uploadify-v2.1.4/swfobject.js"></script>
<script	type="text/javascript" src="${ctx}/web/js_qiDa/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript">
jQuery("#uploadImg1").uploadify({
	'uploader' : '${ctx}/web/js_qiDa/uploadify-v2.1.4/uploadify.swf?random=' + (new Date()).getTime(),
	'script' : '${ctx}/web/upload/img.html',
	'cancelImg' : '${ctx}/web/js_qiDa/uploadify-v2.1.4/cancel.png',
	'fileDataName' : 'imgFile', //相当于  file 控件的 name
	'auto' : true,
	'multi' : false,
	'buttonImg' : '${ctx}/web/images_qiDa/xzwj_03.gif',
	'height' : '28',
	'width' : '82',
	'fileDesc' : '能上传的图片类型:jpg,gif,bmp,jpeg,png', //出现在上传对话框中的文件类型描述
	'fileExt' : '*.jpg;*.bmp;*.jpeg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	'sizeLimit' : 1024*512,
	onComplete:function(event,queueID,fileObj,response,data){
			var jsondata = eval("("+response+")");
			if(jsondata.error==1){
				Dialog.alert(jsondata.message);
				return false;
			}
			$("#picShow1").attr("src",jsondata.saveDir);
			$("#picValue1").val(jsondata.saveDir);
			$("#div1").show();
			$("#span1").html("已上传文件："+jsondata.fileName);
		},
		'onSelect' : function(event,queueID, fileObj) {
			if (fileObj.size > 5*1024*1024) {
				Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
				jQuery("#uploadImg1").uploadifyClearQueue();
			}
		}
});
</script>
<!-- 表单的基础验证以及其他操作 -->
<script type="text/javascript">
/* 验证标题*/
$(".askTit-input").keyup(function(){
	var $contentSize = $(".askTit-input").val().length;
	if ($contentSize > 45) {
		globalTip("标题长度3-45个字", $(".askTit-input"));
		return false;
	}
});

/*验证股票代码*/
$("#addStock").click(function(){
	var $stockInfo = $("input[name='stockInfo']").val();
	if ($stockInfo) {
		$.post("${ctx}/web/qida/checkStockInfo.html", {code: $stockInfo}, function(data){
			if (data.success == 0) { // 不存在
				$("input[name='stockInfo']").val("");
				globalTip("提示您添加的股票不存在", $("input[name='stockInfo']"));
			}		
		}, "json");
	}
});
</script>
  </body>
</html>