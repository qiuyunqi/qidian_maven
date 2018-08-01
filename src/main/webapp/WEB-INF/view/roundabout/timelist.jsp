<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－奖品池列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="奖品池列表" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/timePageData.html" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="false" >
        <thead>
            <tr>
            	<th data-options="checkbox:true"></th>
                <th field="id" width="50">ID</th>
                <th field="changes" width="50">中奖概率</th>
                <th field="startTime" width="50">开始时间</th>
                <th field="endTime" width="50">结束时间</th>
                <th field="name" width="50">奖品名称</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="isDel" width="50">是否启用</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addJackpotTime.html')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addJackpotTime()">添加奖品</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editJackpotTime()">编辑奖品</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delJackpotTime.html')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delJackpotTime(0)">不启用奖品</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delJackpotTime(1)">启用奖品</a>
                    </c:if>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">奖品基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>奖品名称:</td>
                    <td><select id="jackpotId" name="jackpotId" class="easyui-combogrid" style="width:120px;" data-options="
					    panelWidth:450,
					    value:'006',
					    idField:'jackpotId',
					    textField:'name',
					    url:'${ctx}/web/admin/getJackpotData.html',
					    columns:[[
					    {field:'jackpotId',title:'奖品池ID',width:60},
					    {field:'name',title:'奖品池名称',width:100},
					    ]]
					    "></select></td>
                </tr>
                <tr>
                    <td>中奖概率:</td>
                    <td><input id="changes" name="changes" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>开始时间:</td>
                    <td><input id="startTime" name="startTime" class="easyui-datetimebox" required="true" /></td>
                </tr>
                <tr>
                    <td>结束时间:</td>
                    <td><input id="endTime" name="endTime" type="text" class="easyui-datetimebox" required="true"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveJackpotTime()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    
    <script type="text/javascript">
        var url;
        function addJackpotTime() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加');
        	$('#fm').form("clear");
	        url = '${ctx}/web/admin/addJackpotTime.html';
        }
        function editJackpotTime() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/editJackpotTime.html';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveJackpotTime() {
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

        function delJackpotTime(isDel) {
            var row = $('#dg').datagrid('getSelections');
            var url = '${ctx}/web/admin/delJackpotTime.html';
            if (row.length > 0) {
             	var ids = new Array();
	           	for(var i=0;i<row.length;i++){
	           		var $id = row[i].id; 
	           		ids.push($id);
	           	}
	           	var msg = "";
	           	if (isDel == 1) {
	           		msg = "您确定要不启用此项吗?";
	           	} else {
	           		msg = "您确定要启用此项吗?";
	           	}
                $.messager.confirm('确认提示', msg, function (r) {
                    if (r) {
                        $.post(url, {ids: ids, isDel: isDel}, function (result) {
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
        
</script>
</body>
</html>