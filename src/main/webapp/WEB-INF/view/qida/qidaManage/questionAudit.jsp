<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－待审核问题列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="待审核问题列表" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/questionAuditData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="false" >
        <thead>
            <tr>
            	<th data-options="checkbox:true"></th>
                <th field="id" width="50">ID</th>
                <th field="nickName" width="50">用户昵称</th>
                <th field="title" width="100">标题</th>
                <th field="detail" width="100">详情</th>
                <th field="reward" width="50" sortable="true">悬赏积分</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="updateTime" width="50">修改时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
					<c:if test="${admin.hasPrivilegeByUrl('/web/admin/openQuestion') && questionSwitch==0}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="openQuestion()">审核通过</a>
                    </c:if>
                    <%-- <c:if test="${admin.hasPrivilegeByUrl('/web/admin/closeQuestion')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="closeQuestion()">关闭问题</a>
                    </c:if> --%>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/editQuestion')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editQuestion()">编辑问题</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delQuestion')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delQuestion()">删除问题</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>用户昵称:</span>
					<input id="nickName" style="border:1px solid #ccc"/>
					<span>标题:</span>
					<input id="title" style="border:1px solid #ccc"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">问题基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>标题:</td>
                    <td><input name="title" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>详情:</td>
                    <td><textarea name="detail" class="easyui-validatebox" data-options="required:true,validType:''"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveQuestion()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function editQuestion() {
        	var rows = $('#dg').datagrid('getSelections');
            var row = $('#dg').datagrid('getSelected');
            if (rows.length==1) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑问题');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/editQuestion';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择且仅选择一项!'
                });
            }
        }
        function saveQuestion() {
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
        function openQuestion() {
            var row = $('#dg').datagrid('getSelections');
            var url = '${ctx}/web/admin/openQuestion';
            if (row.length>0) {
            	var ids=[];
	           	for(var i=0;i<row.length;i++){
	           		var id=row[i].id; 
	           		ids.push(id);
	           	}
                $.messager.confirm('确认提示', '您确定要开启选中项吗?', function (r) {
                    if (r) {
                        $.post(url, {"array[]":ids}, function (result) {
                            if (result.success) {
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '错误',
                                    msg: result.msg
                                });
                                $('#dg').datagrid('reload');
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
        
        function closeQuestion() {
            var row = $('#dg').datagrid('getSelections');
            var url = '${ctx}/web/admin/closeQuestion';
            if (row.length>0) {
            	var ids=[];
	           	for(var i=0;i<row.length;i++){
	           		var id=row[i].id; 
	           		ids.push(id);
	           	}
                $.messager.confirm('确认提示', '您确定要关闭选中项吗?', function (r) {
                    if (r) {
                        $.post(url, {"array[]":ids}, function (result) {
                            if (result.success) {
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '错误',
                                    msg: result.msg
                                });
                                $('#dg').datagrid('reload');
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
        function delQuestion() {
            var row = $('#dg').datagrid('getSelections');
            var url = '${ctx}/web/admin/delQuestion';
            if (row.length>0) {
            	var ids=[];
	           	for(var i=0;i<row.length;i++){
	           		var id=row[i].id; 
	           		ids.push(id);
	           	}
                $.messager.confirm('确认提示', '您确定要删除选中项吗?', function (r) {
                    if (r) {
                        $.post(url, {"array[]":ids}, function (result) {
                            if (result.success) {
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: '错误',
                                    msg: result.msg
                                });
                                $('#dg').datagrid('reload');
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
            	title: $("#title").val()
            });
        }
    </script>
</body>
</html>