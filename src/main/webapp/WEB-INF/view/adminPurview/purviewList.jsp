<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－菜单管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="权限管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/purviewData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">菜单ID</th>
                <th field="name" width="50">权限名称</th>
                <th field="ParentName" width="30">父ID</th>
                <th field="url" width="70">菜单路径</th>
                <th field="remark" width="70">备注</th>
                <th field="CreatePersonStr" width="50">创建人</th>
                <th field="CreateTimeStr" width="50">创建时间</th>
                <th field="UpdatePersonStr" width="50">修改人</th>
                <th field="UpdateTimeStr" width="50">修改时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addPurview')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="newSysPurview()">添加权限信息</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editSysPurview()">编辑权限信息</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delPurview')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="destroySysPurview()">删除权限信息</a>
                    </c:if>
				</td>
				<td style="text-align:right">
                	<span>权限名称:</span>
					<input id="Name" style="border:1px solid #ccc"></input>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">权限基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name = "id" type="hidden"/>
            <input id="parentId" name="parentId" type="hidden" value="0"/>
            <table border="0">
                <tr>
                    <td>名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr>
                <tr>
                    <td>Url:</td>
                    <td><input name="url" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><textarea name="remark" style="height:60px;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveSysPurview()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newSysPurview() {
        	var row = $('#dg').datagrid('getSelected');
        	if(row){//选中一项，添加的是子菜单
	            $('#dlg').dialog('open').dialog('setTitle', '添加子权限');
	            $("#id").val(null);
	            $("#parentId").val(row.id);
	            url = '${ctx}/web/admin/addPurview';
            }else{//未选中，添加的是顶级菜单
            	$('#dlg').dialog('open').dialog('setTitle', '添加顶级权限');
	            $("#parentId").val(0);
	            url = '${ctx}/web/admin/addPurview';
            }
        }
        function editSysPurview() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改权限信息');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addPurview';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveSysPurview() {
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
        function destroySysPurview() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delPurview';
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
                name: $('#Name').val()
            });
           
        }
    </script>
</body>
</html>