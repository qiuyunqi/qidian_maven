<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－关注管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="关注管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/concernData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">ID</th>
                <th field="concernUser" width="50">关注者</th>
                <th field="beConcernUser" width="50">被关注者</th>
                <th field="concernTime" width="100">关注时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delConcern')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delConcern()">删除关注</a>
                    </c:if>
				</td>
				<td style="text-align:right">
                	<span>关注者:</span>
					<input id="concernUser" style="border:1px solid #ccc"></input>
					<span>被关注者:</span>
					<input id="beConcernUser" style="border:1px solid #ccc"></input>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    <script type="text/javascript">
        function delConcern() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delConcern';
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
                concernUser: $('#concernUser').val(),
                beConcernUser: $('#beConcernUser').val()
            });
           
        }
    </script>
</body>
</html>