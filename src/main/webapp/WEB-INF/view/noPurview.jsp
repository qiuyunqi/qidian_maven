<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta name="viewport" content="width=device-width">
<title>奇点后台管理系统</title>
<link href="${ctx}/web/css/css.css" rel="stylesheet" type="text/css">
<style>
body{width:100%;height:100%;overflow: hidden;background: #f5f5f5;}
</style>
</head>
<body id="theme-default" class="full_block">
<div id="error_404" >
	<div class="error_container" >
		<div class="txtCen"><img src="${ctx}/web/images_line/errorA.png"></div>
		<div class="error_content">
			<h2>很抱歉 您没有权限访问指定的网页...</h2>
			<div class="home_link">
				<div>您可以尝试：</div>
				<div>1.请重新关闭后登录</div>
				<div>2.联系系统管理员</div>
				<div class="txtCen errorA"><a href="${ctx}/web/admin/index" original-title="GO TO HOME">返回首页</a></div>
			</div>
			<span class="clear"></span>
		</div>
		<div class="txtCen"><img src="${ctx}/web/images_line/errorB.png"></div>
	</div>
</div>

</body>
</html>