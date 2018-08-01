<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－账户管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="账户管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/accountData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
            	<input field="password" type="hidden"/>
            	<input field="roleId" type="hidden"/>
            	<input field="email" type="hidden"/>
            	<input field="phone" type="hidden"/>
                <th field="account" width="50">账号</th>
                <th field="name" width="50">姓名</th>
                <th field="type" width="50">类型</th>
                <th field="jobDesc" width="50">职位</th>
                <th field="roleName" width="50">角色</th>
                <th field="createTime" width="50">注册时间</th>
                <th field="updateLoginTime" width="50">最后登录</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addAdmin')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addAdmin()">添加账户</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editAdmin()">编辑账户</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delAdmin')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delAdmin()">删除账户</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>账号:</span>
					<input id="account" style="border:1px solid #ccc"/>
                	<span>真实姓名:</span>
					<input id="Name" style="border:1px solid #ccc"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="ftitle">账户基本信息</div>
        <form id="fm" method="post" novalidate>
            <table border="0">
                <tr>
                    <td>账号:</td>
                    <td><input name="account" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input name="password" class="easyui-validatebox" type="password" data-options="required:true,validType:'length[6,32]'"/></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input name="confirmPassword" class="easyui-validatebox" type="password" required="true"/></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>职位:</td>
                    <td><input name="jobDesc" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td>
                    <input required="true" class="easyui-combobox" name="roleId"
							data-options="
									url:'${ctx}/web/admin/allRoleData',
									method:'post',
									valueField:'roleId',
									textField:'roleName',
									editable:false,
									panelHeight:'auto'
									">
                    </td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input name="email" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
                </tr>
                <tr>
                    <td>手机号:</td>
                    <td><input name="phone" class="easyui-validatebox" data-options="required:true,validType:'Mobile'"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveAddAdmin()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    <div id="update" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="ftitle">账户基本信息</div>
        <form id="updatefm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>账号:</td>
                    <td><input name="account" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input name="password" class="easyui-validatebox" type="password" data-options="required:true,validType:'length[6,32]'"/></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input name="confirmPassword" id="rePwd" class="easyui-validatebox" type="password"/></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>职位:</td>
                    <td><input name="jobDesc" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td>
                    <input required="true" class="easyui-combobox" name="roleId"
							data-options="
									url:'${ctx}/web/admin/allRoleData',
									method:'post',
									valueField:'roleId',
									textField:'roleName',
									editable:false,
									panelHeight:'auto'
									">
                    </td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input name="email" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
                </tr>
                <tr>
                    <td>手机号:</td>
                    <td><input name="phone" class="easyui-validatebox" data-options="required:true,validType:'Mobile'"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveUpdateAdmin()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#update').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        function addAdmin() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加账户');
        }
        function saveAddAdmin() {
            $('#fm').form('submit', {
                url: '${ctx}/web/admin/addAdmin',
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
        
        function editAdmin() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#update').dialog('open').dialog('setTitle', '编辑账户');
                $('#updatefm').form('load', row);
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        
        function saveUpdateAdmin() {
            $('#updatefm').form('submit', {
                url: '${ctx}/web/admin/addAdmin',
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
                        $('#update').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        
        function delAdmin() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delAdmin';
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
            	account:$("#account").val(),
                name: $("#Name").val()
            });
        }
    </script>
</body>
</html>