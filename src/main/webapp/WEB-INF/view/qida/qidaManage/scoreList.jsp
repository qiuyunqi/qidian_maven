<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－积分列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="积分列表" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/scoreData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
                <th field="id" width="50">ID</th>
                <th field="nickName" width="50">用户昵称</th>
                <th field="score" width="50" sortable="true">积分</th>
                <th field="money" width="50" sortable="true">金额</th>
                <th field="totalScore" width="50" sortable="true">总积分</th>
                <th field="orderNum" width="50">订单号</th>
                <th field="comment" width="100">详情</th>
                <th field="isIncome" width="50">是否收入</th>
                <th field="createTime" width="100">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delScore')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delScore()">删除明细</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>用户昵称:</span>
					<input id="nickName" style="border:1px solid #ccc"/>
					<span>详情:</span>
					<input id="comment" style="border:1px solid #ccc"/>
					<span>是否收入:</span>
					<select id="isIncome">
                		<option value=" ">全部</option>
                		<option value="0">支出</option>
                		<option value="1">收入</option>
                	</select>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    <script type="text/javascript">
        function delScore() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delScore';
            if (row) {
                $.messager.confirm('确认提示', '您确定要删除此项吗?', function (r) {
                    if (r) {
                        $.post(url, { id: row.id }, function (result) {
                            if (result.success) {
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '错误',
                                    msg: result.msg
                                });
                            }
                        }, 'json');
                    }
                });
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一个项目!'
                });
            }
        }
        
        
        function doSearch() {
            $('#dg').datagrid('load', {
            	nickName: $("#nickName").val(),
            	comment: $("#comment").val(),
            	isIncome: $("#isIncome").val()
            });
        }
    </script>
</body>
</html>