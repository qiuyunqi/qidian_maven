<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－猜猜乐管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="猜猜乐管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/guessMarketData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">ID</th>
                <th field="name" width="50">名称</th>
                <th field="guessScore" width="50">竞猜积分</th>
                <th field="acceptFactor" width="50">结算指数</th>
                <th field="acceptResult" width="50">结算结果</th>
                <th field="state" width="50">状态</th>
                <th field="isClose" width="50">是否封盘</th>
                <th field="type" width="50">类型</th>
                <th field="acceptAdmin" width="50">结算者</th>
                <th field="acceptTime" width="50">结算时间</th>
                <th field="createTime" width="50">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
					<c:if test="${admin.hasPrivilegeByUrl('/web/admin/balanceMarket')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="balanceMarket()">结算猜猜乐</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addGuessMarket')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addGuessMarket()">添加猜猜乐</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editGuessMarket()">编辑猜猜乐</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delGuessMarket')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delGuessMarket()">删除猜猜乐</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>名称:</span>
					<input id="name" style="border:1px solid #ccc"></input>
                	<span>类型:</span>
                	<select id="type">
						<option value="">全部</option>
                    	<option value="1">涨（奇数）</option>
                		<option value="2">涨（偶数）</option>
                		<option value="3">跌（奇数）</option>
                		<option value="4">跌（偶数）</option>
                	</select>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">猜猜乐基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"></input></td>
                </tr> 
                <tr>
                    <td>竞猜积分:</td>
                    <td><input name="guessScore" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr>
                <tr>
                    <td>创建时间:</td>
                    <td><input name="createTime" class="easyui-validatebox" data-options="required:true,validType:''" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></input><i class="riqi"></i></td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td><select name="state">
                    	<option value="0">未结算</option>
                		<option value="1">已结算</option>
                		</select>
                    </td>
                </tr> 
                <tr>
                    <td>是否封盘:</td>
                    <td><select name="isClose">
                    	<option value="0">未封盘</option>
                		<option value="1">已封盘</option>
                		</select>
                	</td>
                </tr> 
                <tr>
                    <td>类型:</td>
                    <td><select name="type">
                    	<option value="0">上午盘</option>
                		<option value="1">下午盘</option>
                		</select>
                    </td>
                </tr> 
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveGuessMarket()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div> 
    
    
    <div id="jsg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">猜猜乐结算</div>
        <form id="js" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>结算指数:</td>
                    <td><input name="acceptFactor" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr> 
                <tr>
                    <td>结算结果:</td>
                    <td><select name="acceptResult">
                    	<option value="1">涨（奇数）</option>
                		<option value="2">涨（偶数）</option>
                		<option value="3">跌（奇数）</option>
                		<option value="4">跌（偶数）</option>
                		</select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveBalanceMarket()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#jsg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addGuessMarket() {
            $('#dlg').dialog('open').dialog('setTitle', '添加猜猜乐');
            $('fm').form('clear');
            $("#id").val(null);
            url = '${ctx}/web/admin/addGuessMarket';
        }
        function editGuessMarket() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改猜猜乐信息');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addGuessMarket';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        
        function saveGuessMarket() {
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
        function delGuessMarket() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delGuessMarket';
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
                name: $('#name').val(),
                type: $('#type').val()
            });
        }
        
        function balanceMarket(){
        	var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('确认提示', '您确定要结算此项吗?', function (r) {
                    if (r) {
                    	$('#jsg').dialog('open').dialog('setTitle', '结算猜猜乐信息');
		                $('#js').form('load', row);
		                url = '${ctx}/web/admin/balanceMarket';
                    }
                });
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一个项目!'
                });
            }
        }
        
        function saveBalanceMarket() {
            $('#js').form('submit', {
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
                        $('#jsg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
    </script>
</body>
</html>