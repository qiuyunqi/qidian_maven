<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width; initial-scale=1.0">
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta name="mobile-web-app-capable" content="yes">
	<meta name="google" content="notranslate">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<meta name="full-screen" content="yes">
	<meta name="x5-fullscreen" content="true">
	<meta name="browsermode" content="application">
	<meta name="x5-page-mode" content="app">
    <title>报名</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/web/css_enroll/enroll.css"></link>
	<script type="text/javascript" src="${ctx}/web/js/jquery.js"></script>
  </head>
  <body>
	<body>
	<div class="enrollBody" >
		<div class="enroll-content">
			<h4>请留下您的信息</h4>
			<div class="enroll-info">
			<form>
				<div class="enroll-name">
					姓名：
					<input class="busBumInpt" type="text" name="userName" />
				</div>
				<div class="enroll-phone">
					手机：
					<input class="busBumInpt" type="text" name="phone" />
				</div>
		       	<a href="javascript:void(0);" class="sveBt">提交</a>
			</form>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${ctx}/web/js/layer/layer.js"></script>
<script type="text/javascript">
$(".sveBt").click(function(){
	var $userName = $("input[name='userName']").val();
	var $phone = $("input[name='phone']").val();
	if (!$userName) {
		layer.open({
		    content: '请输入姓名',
		    btn: ['确定']
		});
		return false;
	}
	
	if (!$phone) {
		layer.open({
		    content: '请输入您的手机号码',
		    btn: ['确定']
		});
		return false;
	}
	if(!(/^1[34578]\d{9}$/.test($phone))){ 
		layer.open({
		    content: '请输入正确的手机号码',
		    btn: ['确定']
		});
        return false; 
    } 
	/*验证手机号码是否重复*/
	$.post("${ctx}/web/checkPhone.html", {phone: $phone}, function(data){
		if (data.success == 1) {
			$.post("${ctx}/web/saveEnroll.html", {userName: $userName, phone: $phone}, function(saveData){
				if (saveData.success == 1) {
					layer.open({
					    content: saveData.message,
					    btn: ['确定'],
					    yes: function(index){
					    		$("input[name='userName']").val("");
					    		$("input[name='phone']").val("");
					    		layer.close(index);
					    }
					});
				} else {
					layer.open({
						content: saveData.message,
					    btn: ['确定']
					});
				}
			}, "json");		
		} else {
			layer.open({
				content: data.message,
				btn: ['确定']
			});
		}
	},"json");
	
});
</script>
  </body>
</html>
