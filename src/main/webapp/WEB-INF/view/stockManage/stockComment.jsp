<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－股票评论</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="股票评论" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/stockCommentData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
            	<input field="stockInfoId" type="hidden"/>
                <th field="name" width="80">昵称</th>
                <th field="createTime" width="80">评论时间</th>
                <th field="content" width="80">评论内容</th>
                <th field="type" width="30">评论类型</th>
                <th field="likeNum" width="30">点赞数</th>
                <th field="noLikeNum" width="30">未点赞数</th>
                <th field="realLikeNum" width="30">真实喜欢数目</th>
                <th field="realNoLikeNum" width="30">非真实喜欢数目</th>
                <th field="wbUrl" width="80">微博地址</th>
                <th field="wbMid" width="80">微博MID</th>
                <th field="wbReposts" width="30">微博转发数</th>
                <th field="wbComments" width="30">微博评论数</th>
                <th field="wbAttitudes" width="30">微博点赞数</th>
                <th field="wbHdl" width="30">微博互动量</th>
                <th field="wbUid" width="50">微博UID</th>
                <th field="wbFensi" width="50">微博粉丝数</th>
                <th field="wbWibo" width="50">用户微博数</th>
                <th field="wbGuanzhu" width="50">微博关注数</th>
                <th field="wbvtype" width="50">微博类型</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addStcokComment')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addComment()">添加评论</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editComment()">编辑评论</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delStockComment')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="delComment()">删除评论</a>
                    </c:if>
				</td>
				<td style="text-align:right">
					<span>昵称:</span>
					<input id="name" style="border:1px solid #ccc"/>
					<span>评论类型:</span>
					<select id="type">
                		<option value=" ">全部</option>
                		<option value="0">好评</option>
                		<option value="1">中评</option>
                		<option value="2">差评</option>
                	</select>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="doSearch()">查询</a>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">股票评论信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
                <tr>
                    <td>昵称:</td>
                    <td><input name="name" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>评论时间:</td>
                    <td><input name="createTime" class="easyui-datetimebox" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>评论内容:</td>
                    <td><textarea name="content" data-options="required:true,validType:''" rows="4" cols="30"></textarea></td>
                </tr>
                <tr>
                    <td>评论类型:</td>
                    <td>
                    <select name="type">
                		<option value="0">好评</option>
                		<option value="1">中评</option>
                		<option value="2">差评</option>
                	</select>
                    </td>
                </tr>
                <tr>
                    <td>股票详情ID:</td>
                    <td><input name="stockInfoId" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>点赞数:</td>
                    <td><input name="likeNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>未点赞数:</td>
                    <td><input name="noLikeNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>真实喜欢数目:</td>
                    <td><input name="realLikeNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>非真实喜欢数目:</td>
                    <td><input name="realNoLikeNum" class="easyui-validatebox" data-options="required:true,validType:'Number'"/></td>
                </tr>
                <tr>
                    <td>微博地址:</td>
                    <td><input name="wbUrl" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>微博MID:</td>
                    <td><input name="wbMid" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博转发数:</td>
                    <td><input name="wbReposts" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博评论数:</td>
                    <td><input name="wbComments" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博点赞数:</td>
                    <td><input name="wbAttitudes" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博互动量:</td>
                    <td><input name="wbHdl" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博UID:</td>
                    <td><input name="wbUid" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博粉丝数:</td>
                    <td><input name="wbFensi" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>用户微博数:</td>
                    <td><input name="wbWibo" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博关注数:</td>
                    <td><input name="wbGuanzhu" class="easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td>微博类型:</td>
                    <td><input name="wbvtype" class="easyui-validatebox" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveComment()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function addComment() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加评论');
        	$('fm').form('clear');
	        $("#id").val(null);
	        url = '${ctx}/web/admin/addStcokComment';
        }
        function editComment() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑评论');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addStcokComment';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveComment() {
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
        function delComment() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delStockComment';
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
                type: $("#type").val()
            });
        }
    </script>
</body>
</html>