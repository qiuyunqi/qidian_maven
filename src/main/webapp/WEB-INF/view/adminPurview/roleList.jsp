<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}－角色管理</title>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">

	<table id="dg" title="角色管理" class="easyui-datagrid" width="100%" style="height:auto"
            url="${ctx}/web/admin/roleData" 
            toolbar="#toolbar" pagination="true" pageSize="20"
			rownumbers="true" fitColumns="true" singleSelect="true" >
        <thead>
            <tr>
            	<input field="id" type="hidden"/>
                <th field="roleName" width="50">角色名称</th>
                <th field="roleCode" width="50">角色编号</th>
                <th field="roleDesc" width="50">角色描述</th>
                <th field="createAdmin" width="50">创建人</th>
                <th field="createTime" width="50">创建时间</th>
                <th field="updateAdmin" width="50">更新人</th>
                <th field="updateTime" width="50">更新时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/addRole')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addRole()">添加角色</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editRole()">编辑角色</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/delRole')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="delRole()">删除角色</a>
                    </c:if>
                    <c:if test="${admin.hasPrivilegeByUrl('/web/admin/treedata')}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="setPurview()">设置权限</a>
                    </c:if>
				</td>
			</tr>
		</table>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:500px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">角色基本信息</div>
        <form id="fm" method="post" novalidate>
            <input id="id" name="id" type="hidden"/>
            <table border="0">
            	<tr>
                    <td>角色名称:</td>
                    <td><input name="roleName" class="easyui-validatebox" data-options="required:true,validType:''"/></td>
                </tr>
                <tr>
                    <td>角色编号:</td>
                    <td><input name="roleCode" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>角色描述:</td>
                    <td><input name="roleDesc" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td>上级角色:</td>
                    <td><input required="true" class="easyui-combobox" name="parentId"
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
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveSysRole()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    
    
    
    <div id="setPurview" class="easyui-dialog" style="width:600px;height:auto;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <input id="roleId" type="hidden"/>
            <!-- 权限窗口 -->  
		    <div id="menuDiv" title="配置权限" style="width:350px;padding:10px">  
		        <div id="tree" class="ztree" style="padding: 10px 20px;"></div>  
		    </div>  
    </div>
    <div id="dlg-buttons">
    	<c:if test="${admin.hasPrivilegeByUrl('/web/admin/saveRolePurview')}">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onClick="saveSysRolePurview()">保存</a>
        </c:if>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="javascript:$('#setPurview').dialog('close')">取消</a>
    </div>
<script type="text/javascript">
        var url;
        function addRole() {
        	$('#dlg').dialog('open').dialog('setTitle', '添加角色');
	        $("#id").val(null);
	        url = '${ctx}/web/admin/addRole';
        }
        function editRole() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#dlg').dialog('open').dialog('setTitle', '编辑角色');
                $('#fm').form('load', row);
                url = '${ctx}/web/admin/addRole';
            } else {
                $.messager.show({    // show error message
                    title: '错误',
                    msg: '请选择一项!'
                });
            }
        }
        function saveSysRole() {
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
        function delRole() {
            var row = $('#dg').datagrid('getSelected');
            var url = '${ctx}/web/admin/delRole';
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
</script>
    


   
<script type="text/javascript">
function setPurview(){
	var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#setPurview').dialog('open').dialog('setTitle', '编辑权限');
        $("#roleId").val(row.id);
        tree();
    } else {
        $.messager.show({    // show error message
            title: '错误',
            msg: '请选择一项!'
        });
    }
}



function tree(){
	var globalId = null;
	var setting = {
		async : {  
	        enable : true, 
	        url : "${ctx}/web/admin/treedata?roleId="+$("#roleId").val(),  
	        autoParam : ["id", "name"]                    
	    }, 
		check : {
			chkboxType:{"Y":"ps","N":"ps"},//勾选checkbox对于父子节点的关联关系,取消勾选时不关联父
			chkStyle:"checkbox",
			enable : true	//是否复选框
		},
		//数据
		data : {
			simpleData : {
				enable : true,
				idKey : "id",     
	            pIdKey : "pId",
	            rootPId: 0
			}
		},
		callback : {  
	            onClick : function(event, treeId, treeNode, clickFlag) {  
	                if(true) {
	                	globalId = treeNode.id;
	                }  
	            },  
	            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数  
	            onAsyncSuccess : function(event, treeId, treeNode, msg){  
	            	
	            }
	        }  
	};



	$.fn.zTree.init($("#tree"), setting);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandAll(true);
}

function saveSysRolePurview(){
	 var row = $('#dg').datagrid('getSelected');
          
     var treeObj=$.fn.zTree.getZTreeObj("tree");
     var nodes=treeObj.getCheckedNodes(true);
     var purviewArray = "";
     for(var i=0;i<nodes.length;i++){
    	//获取选中节点的值
    	 purviewArray = purviewArray + nodes[i].id + ",";
     }
     purviewArray=purviewArray.substring(0,purviewArray.length-1);
     
     if (row) {
        $.messager.confirm('确认提示', '您确定要修改此角色权限吗?', function (r) {
            if (r) {
                $.post("${ctx}/web/admin/saveRolePurview", {"roleId":row.id,"purviewArray":purviewArray}, function (result) {
                    if (result.success) {
                    	$.messager.alert('确定',result.msg);
                    	$('#setPurview').dialog('close');
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