<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－系统参数管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="系统参数管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/configData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">ID</th>
                <th field="code" width="50">编号</th>
                <th field="name" width="100">名称</th>
                <th field="value" width="100">值</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addConfig')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addConfig()">添加参数</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editConfig()">编辑参数</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delConfig')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delConfig()">删除参数</a>
                    </c:if>
				</td>
				<td style="text-align:right">
                	<span>编号:</span>
					<input id="code" style="border:1px solid #ccc"></input>
					<span>名称:</span>
					<input id="name" style="border:1px solid #ccc"></input>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:700px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">参数基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>编号:</td>
                    <td><input name="code" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr> 
                <tr>
                    <td>名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr>
                <tr>
                    <td>值:</td>
                    <td><input name="value" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveConfig()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addConfig() {
            $('#dlg').dialog('open').dialog('setTitle', '添加参数');
            $('fm').form('clear');
            $("#id").val(null);
            url = '${ctx}/web/admin/addConfig';
        }
        function editConfig() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改参数信息');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addConfig';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveConfig() {
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
        function delConfig() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delConfig';
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
                code: $('#code').val(),
                name: $('#name').val()
            });
           
        }
    </script>
</body>
</html>