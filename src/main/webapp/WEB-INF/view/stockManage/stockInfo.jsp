<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－股票详情</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="股票详情" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/stockInfoData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
                <th field="id" width="50">股票ID</th>
                <th field="name" width="50">股票名称</th>
                <th field="code" width="50">股票代码</th>
                <th field="pyCode" width="50">股票拼音</th>
                <th field="goodDay" width="50">今日好评</th>
                <th field="goodWeek" width="50">本周好评</th>
                <th field="watchNum" width="50">关注指数</th>
                <th field="goodCommentNum" width="50">好评数</th>
                <th field="mediumCommentNum" width="50">中评数</th>
                <th field="badCommentNum" width="50">差评数</th>
                <th field="commentNum" width="50" sortable="true">累计评论数</th>
                <th field="commentNumToday" width="50" sortable="true">当日评论数</th>
                <th field="stkType" width="50">交易所</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addStcok')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addStcok()">添加股票</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editStock()">编辑股票</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delStock')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delStock()">删除股票</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addPraise')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="addPraise()">添加排行</a>
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
        <div class="ftitle">股票基本信息</div>
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
                    <td>股票拼音:</td>
                    <td><input name="pyCode" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>今日好评:</td>
                    <td><input name="goodDay" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>本周好评:</td>
                    <td><input name="goodWeek" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>关注指数:</td>
                    <td><input name="watchNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>好评数:</td>
                    <td><input name="goodCommentNum" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>中评数:</td>
                    <td><input name="mediumCommentNum" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>差评数:</td>
                    <td><input name="badCommentNum" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>累计评论数:</td>
                    <td><input name="commentNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>当日评论数:</td>
                    <td><input name="commentNumToday" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>交易所:</td>
                    <td><input name="stkType" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveStockInfo()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    
    
    <div id="praise" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#praise-buttons">
        <div class="ftitle">排行基本信息</div>
        <form id="praiseForm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
            	<tr>
                    <td>股票名称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>股票代码:</td>
                    <td><input name="code" class="easyui-validatebox" data-options="required:true,validType:''" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>股票拼音:</td>
                    <td><input name="pyCode" class="easyui-validatebox" data-options="required:true,validType:''" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>好评数:</td>
                    <td><input name="goodCommentNum" class="easyui-validatebox" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>排序:</td>
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
    <div id="praise-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveStockPraise()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#praise').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addStcok() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加股票');
	        $("#id").val(null);
	        url = '${ctx}/web/admin/addStcok';
        }
        function editStock() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑股票');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addStcok';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveStockInfo() {
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
        function delStock() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delStock';
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
        
        function addPraise() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#praise').dialog('open').dialog('setTitle', '添加排行');
                $('#praiseForm').form('load', row);
                url = '${ctx}/web/admin/addPraise';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveStockPraise() {
            $('#praiseForm').form('submit', {
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
                    	$.messager.show({
                            title: '提示！',
                            msg: result.msg
                        });
                        $('#praise').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
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