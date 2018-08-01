<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored ="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="domi" uri="/WEB-INF/tld/domi.tld"%>
<%
	String path = request.getContextPath();
	String titleChar = "&nbsp;&nbsp;\\&nbsp;&nbsp;";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="奇点算股" />
<c:set var="qidatitle" value="奇答问股" />

<link rel="stylesheet" type="text/css" href="${ctx}/web/js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/web/js/easyui/themes/icon.css"/>
<script type="text/javascript" src="${ctx}/web/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/web/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/web/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/web/js/easyui/easyui-extend.js"></script>


<link href="${ctx}/web/js/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/web/js/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${ctx}/web/js/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/web/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/web/js/zTree/js/jquery.ztree.exedit-3.5.js"></script> 

<script type="text/javascript" src="${ctx}/web/js/layer/layer.js"></script>

<script type="text/javascript" src="${ctx}/web/js/dropload.min.js"></script>

<script type="text/javascript" src="${ctx}/web/js/jquery.tipsy.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/web/css/tipsy.css"/>
<!-- 自定义弹窗 -->
<script type="text/javascript" src="${ctx}/web/js_qiDa/globalTip.js"></script>
<!-- 日历函数 -->
<script type="text/javascript" src="${ctx}/web/js/DatePicker/WdatePicker.js"></script>

 