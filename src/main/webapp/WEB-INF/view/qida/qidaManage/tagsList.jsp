<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－标签管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="标签管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/tagsData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">ID</th>
                <th field="name" width="50">名称</th>
                <th field="parentId" width="50">父ID</th>
                <th field="isHot" width="50">标签类型</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addTag')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addTag()">添加标签</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editTag()">编辑标签</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delTag')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delTag()">删除标签</a>
                    </c:if>
				</td>
				<td style="text-align:right">
                	<span>标签名称:</span>
					<input id="Name" style="border:1px solid #ccc"></input>
					<span>标签类型:</span>
					<select id="isHot">
                		<option value=" ">全部</option>
                		<option value="0">普通标签</option>
                		<option value="1">热门标签</option>
                	</select>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">标签基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr>
                <tr>
                    <td>上级标签:</td>
                    <td><input required="true" class="easyui-combobox" name="parentId"
							data-options="
									url:'${ctx}/web/admin/topTagData',
									method:'post',
									valueField:'tagId',
									textField:'tagName',
									editable:false,
									panelHeight:'auto'
									">
                    </td>
                </tr>
                <tr>
                    <td>标签类型:</td>
                    <td>
                    <select name="isHot">
                		<option value="0">普通标签</option>
                		<option value="1">热门标签</option>
                	</select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveTag()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addTag() {
            $('#dlg').dialog('open').dialog('setTitle', '添加标签');
            $("#id").val(null);
            url = '${ctx}/web/admin/addTag';
        }
        function editTag() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改标签信息');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addTag';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveTag() {
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
        function delTag() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delTag';
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
                name: $('#Name').val(),
                isHot: $('#isHot').val()
            });
           
        }
    </script>
</body>
</html>