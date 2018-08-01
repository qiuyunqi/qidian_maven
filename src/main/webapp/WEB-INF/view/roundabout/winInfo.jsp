<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－中奖人信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="中奖人详情" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/winInfoData.html" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
                <th field="id" width="50">中奖人ID</th>
                <th field="userName" width="50">中奖人名称</th>
                <th field="phone" width="50">中奖人手机号码</th>
                <th field="lotteryName" width="50">抽奖礼品</th>
                <th field="isWin" width="50">是否中奖</th>
                <th field="spendIntegral" width="50">花费积分</th>
                <th field="status" width="50">是否是选中用户</th>
                <th field="isReceive" width="50">是否领取奖品</th>
                <th field="drawTime" width="50">抽奖时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/editLottery.html')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="addLottery()">设置专有用户</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editLottery()">设置奖品领取</a>
                    </c:if>
				</td>
				<!-- 查询 -->
				<!-- <td style="text-align:right">
					<span>股票名称:</span>
					<input id="name" style="border:1px solid #ccc"/>
                	<span>股票代码:</span>
					<input id="code" style="border:1px solid #ccc"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td> -->
			</tr>
		</table>
    </div>
    
    <!-- 编辑是否领取奖品 -->
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">中奖人基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>是否领取奖品:</td>
                    <td><select name="isReceive">
                		<option value="0">未领取</option>
                		<option value="1">领取</option>
                		</select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="editInfo()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    <!-- 设置是否是专用用户 -->
    <div id="dgl" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">设置专用用户</div>
        <form id="sf" method="post" novalidate>
            <table border="0">
               <tr>
                    <td>奖品名称:</td>
                    <td>
                    	<select id="state" class="easyui-combobox" name="lotteryId" style="width:200px;">
                    	<c:forEach items="${jackpotList}" var="jackpot">
							<option value="${jackpot.id }">${jackpot.name}</option>
                    	</c:forEach>
						</select>
                    </td>
                </tr>
                <tr>
                    <td>中奖用户:</td>
                    <td><input id="user_id" name="userName" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                    <input name="userId" type="hidden"/>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveLottery()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dgl').dialog('close')">取消</a>
    </div>
    
    <!-- 查询用户 -->
    <div id="findUser" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">查询用户信息</div>
        <form id="fu" method="post" novalidate>
            <table border="0">
               <tr>
                    <td>手机号码:</td>
                    <td><input name="userPhone" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="searchUser()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#findUser').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addLottery() {
        	$('#dgl').dialog('open').dialog('setTitle', '设置选中用户');
	        url = '${ctx}/web/admin/saveLottery.html';
        }
        function editLottery() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑领取奖品');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/editLottery.html';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        
        $("#user_id").focus(function() {
        	$('#findUser').dialog('open').dialog('setTitle', '查询用户信息');
        	url = '${ctx}/web/admin/searchUser.html';
        });
        
        function editInfo() {
            $('#fm').form('submit', {
                url: '${ctx}/web/admin/editLottery.html',
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
        
        function searchUser() {
        	$('#fu').form('submit', {
                url: '${ctx}/web/admin/searchUser.html',
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({
                            title: '错误！',
                            msg: result.msg
                        });
                    } else {
                    	$("input[name='userId']").val(result.userId);
                    	$("input[name='userPhone']").val("");
                    	$("#user_id").val(result.userName);
                        $('#findUser').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                },
                error: function(data) {
                	alert(data)
                }
            });
        }
        
        
        
        function saveLottery() {
        	$('#sf').form('submit', {
                url: '${ctx}/web/admin/saveLottery.html',
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (!result.success) {
                        $.messager.show({
                            title: '错误！',
                            msg: result.msg
                        });
                    } else {
                    	$.messager.show({
                            title: '提示！',
                            msg: result.msg
                        });
                        $('#dgl').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                },
                error: function(data) {
                	alert(data)
                }
            });
        }
    </script>
</body>
</html>