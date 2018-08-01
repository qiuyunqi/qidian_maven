<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－竞猜管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="竞猜管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/guessRecordData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true"
             >
        <thead>
            <tr>
                <th field="id" width="30">ID</th>
                <th field="userName" width="50">姓名</th>
                <th field="tradeNo" width="50">订单号</th>
                <th field="score" width="50">消费积分</th>
                <th field="orderIncome" width="50">订单收益</th>
                <th field="guessType" width="50">竞猜类型</th>
                <th field="marketName" width="50">竞猜项目</th>
                <th field="guessTime" width="50">竞猜时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/updateGuessRecord')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editGuessRecord()">编辑竞猜</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delGuessRecord')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delGuessRecord()">删除竞猜</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>姓名:</span>
					<input id="userName" style="border:1px solid #ccc"></input>
					<span>竞猜项目:</span>
					<input id="marketName" style="border:1px solid #ccc"></input>
                	<span>类型:</span>
                	<select id="guessType">
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
        <div class="ftitle">竞猜基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>消费积分:</td>
                    <td><input name="score" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr> 
                <tr>
                    <td>订单号:</td>
                    <td><input name="tradeNo" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr> 
                <tr>
                    <td>订单收益:</td>
                    <td><input name="orderIncome" class="easyui-validatebox" data-options="required:true,validType:'Number'"></input></td>
                </tr> 
                <tr>
                    <td>竞猜类型:</td>
                    <td><select name="guessType">
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveGuessRecord()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function editGuessRecord() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '修改竞猜');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/updateGuessRecord';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveGuessRecord() {
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
        function delGuessRecord() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delGuessRecord';
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
                userName: $('#userName').val(),
                marketName: $('#marketName').val(),
                guessType: $('#guessType').val()
            });
           
        }
    </script>
</body>
</html>