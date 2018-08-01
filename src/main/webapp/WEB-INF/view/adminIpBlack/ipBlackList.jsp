<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－IP黑名单管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="IP黑名单列表" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/ipBlackData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
                <th field="ip" width="50">IP</th>
                <th field="isBlack" width="50">状态</th>
                <th field="createAdmin" width="50">创建人</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="updateAdmin" width="50">更新人</th>
                <th field="updateTime" width="50">更新时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addIpBlack')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addIpBlack()">添加黑名单</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editIpBlack()">编辑黑名单</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delIpBlack')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="delIpBlack()">删除黑名单</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>IP:</span>
					<input id="ip" style="border:1px solid #ccc"/>
                	<span>状态:</span>
                	<select id="isBlack">
                		<option value=" ">全部</option>
                		<option value="0">白名单</option>
                		<option value="1">黑名单</option>
                	</select>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">权限基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>IP:</td>
                    <td><input name="ip" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td><select name="isBlack">
                		<option value="0">白名单</option>
                		<option value="1">黑名单</option>
                		</select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveIpBlack()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addIpBlack() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加黑名单');
	        $("#id").val(null);
	        url = '${ctx}/web/admin/addIpBlack';
        }
        function editIpBlack() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑黑名单');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addIpBlack';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveIpBlack() {
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
        
        function delIpBlack() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delIpBlack';
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
            	ip:$("#ip").val(),
                isBlack: $("#isBlack").val()
            });
        }
    </script>
</body>
</html>