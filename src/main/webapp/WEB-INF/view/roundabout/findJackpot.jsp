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
            url="${ctx}/web/admin/jackpotData.html" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="false" >
        <thead>
            <tr>
            		<th data-options="checkbox:true"></th>
                <th field="id" width="50">奖品ID</th>
                <th field="name" width="50">奖品名称</th>
                <th field="changes" width="50">中奖概率</th>
                <th field="minAngle" width="50">奖品最小角度</th>
                <th field="maxAngle" width="50">奖品最大角度</th>
                <th field="grade" width="50">奖品等级</th>
                <th field="type" width="50">奖品类型</th>
                <th field="score" width="50">虚拟积分</th>
                <th field="isDel" width="50">启用状态</th>
                <th field="periods" width="50">期数</th>
                <th field="createTime" width="50">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addJackpot.html')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addJackpot()">添加奖品</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editJackpot()">编辑奖品</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delJackpot.html')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delJackpot()">不启用奖品</a>
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
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>中奖概率:</td>
                    <td><input name="changes" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品最小角度:</td>
                    <td><input name="minAngle" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品最大角度:</td>
                    <td><input name="maxAngle" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品等级:</td>
                    <td><input name="grade" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品类型:</td>
                    <td><select name="type">
                    	<option value="0">虚拟</option>
                		<option value="1">实物</option>
                		</select></td>
                </tr>
                <tr>
                    <td>虚拟积分:</td>
                    <td><input name="score" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="editsJackpot()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    <!-- add dialog -->
    <div id="dlgl" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">奖品基本信息</div>
        <form id="fml" method="post" novalidate>
            <table border="0">
                <tr>
                    <td>奖品名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>中奖概率:</td>
                    <td><input name="changes" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品最小角度:</td>
                    <td><input name="minAngle" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品最大角度:</td>
                    <td><input name="maxAngle" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品等级:</td>
                    <td><input name="grade" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>奖品类型:</td>
                    <td><select name="type">
                    	<option value="0">虚拟</option>
                		<option value="1">实物</option>
                		</select></td>
                </tr>
                <tr>
                    <td>虚拟积分:</td>
                    <td><input name="score" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>期数:</td>
                    <td><input name="periods" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="addsJackpot()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlgl').dialog('close')">取消</a>
    </div>
    
    <script type="text/javascript">
        var url;
        function addJackpot() {
        		$('#dlgl').dialog('open').dialog('setTitle', '添加奖品');
        		$('#fml').form("clear");
	        url = '${ctx}/web/admin/addjackpot.html';
        }
        function editJackpot() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑奖品');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addjackpot.html';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function editsJackpot() {
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
        function addsJackpot() {
            $('#fml').form('submit', {
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
                        $('#dlgl').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function delJackpot() {
            var row = $('#dg').datagrid('getSelections');
            var url = '${ctx}/web/admin/delJackpot.html';
            if (row.length > 0) {
             	var ids = new Array();
	           	for(var i=0;i<row.length;i++){
	           		var $id = row[i].id; 
	           		ids.push($id);
	           	}
                $.messager.confirm('确认提示', '您确定要删除此项吗?', function (r) {
                    if (r) {
                        $.post(url, {ids: ids}, function (result) {
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