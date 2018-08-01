<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－股票点赞排行</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="股票点赞排行" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/stockPraiseData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
            	<input field="stockInfoId" type="hidden"/>
                <th field="name" width="50">股票名称</th>
                <th field="code" width="50">股票代码</th>
                <th field="transactionPrice" width="50">成交价格</th>
                <th field="riseAndFall" width="50">涨跌幅</th>
                <th field="goodNum" width="50" sortable="true">当日评论数</th>
                <th field="pyCode" width="50">股票拼音</th>
                <th field="stockSort" width="50" sortable="true">股票排序</th>
                <th field="isLock" width="50">是否锁定</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addStcokPraise')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addStcokPraise()">添加点赞</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editStcokPraise()">编辑点赞</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delStockPraise')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delStockPraise()">删除点赞</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>股票名称:</span>
					<input id="name" style="border:1px solid #ccc"/>
                	<span>股票代码:</span>
					<input id="code" style="border:1px solid #ccc"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">股票点赞信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>股票名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>股票代码:</td>
                    <td><input name="code" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>成交价格:</td>
                    <td><input name="transactionPrice" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>涨跌幅:</td>
                    <td><input name="riseAndFall" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>当日评论数:</td>
                    <td><input name="goodNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>股票拼音:</td>
                    <td><input name="pyCode" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>股票详情ID:</td>
                    <td><input name="stockInfoId" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>股票排序:</td>
                    <td><input name="stockSort" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>是否锁定:</td>
                    <td><select name="isLock">
                    	<option value="0">解锁</option>
                		<option value="1">锁定</option>
                		</select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveStockPriase()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addStcokPraise() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加股票排序');
        	$('fm').form('clear');
	        url = '${ctx}/web/admin/addStcokPraise';
        }
        function editStcokPraise() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑股票排序');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addStcokPraise';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveStockPriase() {
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
        function delStockPraise() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delStockPraise';
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
            	name: $("#name").val(),
                code: $("#code").val()
            });
        }
    </script>
</body>
</html>