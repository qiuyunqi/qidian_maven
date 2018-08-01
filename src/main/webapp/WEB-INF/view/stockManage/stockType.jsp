<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－股票类型</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="股票类型" class="easyui-datagrid" width="100%" style="height:auto" 
	url="${ctx}/web/admin/stockTypeData" toolbar="#toolbar" pagination="true" 
	pageSize="20" rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
                <th field="pid" width="80">pid</th>
                <th field=name width="80">类型名称</th>
                <th field="liftRate" width="80">升降率</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addStcokType')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addStcokType()">添加类型</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editStockType()">编辑类型</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delStockType')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delStockType()">删除类型</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>类型名称:</span>
					<input id="name" style="border:1px solid #ccc"/>
					<span>升降率:</span>
					<input id="liftRate" style="border:1px solid #ccc"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">股票类型</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>pid:</td>
                    <td><input name="pid" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>类型名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>升降率:</td>
                    <td><input name="liftRate" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveType()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addStcokType() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加类型');
        	$('fm').form('clear');
	        $("#id").val(null);
	        url = '${ctx}/web/admin/addStcokType';
        }
        function editStockType() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑类型');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addStcokType';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveType() {
            $('#fm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({
                            title: '错误！',
                            msg: result.msg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function delStockType() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delStockType';
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
            	name: $("#name").val(),
                liftRate: $("#liftRate").val()
            });
        }
    </script>
</body>
</html>