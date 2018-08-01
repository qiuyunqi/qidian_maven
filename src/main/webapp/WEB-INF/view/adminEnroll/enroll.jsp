<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－报名详情</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="报名详情" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/toEnrollData.html" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
                <th field="userName" width="80">名称</th>
                <th field="phone" width="80">电话号码</th>
                <th field="createTime" width="80">报名时间</th>
            </tr>
        </thead>
    </table>
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">报名信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>名称:</td>
                    <td><input name="userName" class="easyui-validatebox" data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td><input name="phone" class="easyui-datetimebox" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>报名时间:</td>
                    <td><input name="createTime" class="easyui-datetimebox" data-options="required:true"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>