<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/common/meta.jsp" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${qidatitle}－审核开关管理</title>
<link href="${ctx}/web/css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">
	<div style="padding:5px 0;">
		<ur>
			<li>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/openQuestionSwitch') && questionSwitch==1}">
				<a href="javascript:void(0)" onclick="openQuestionSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-add',toggle:true">开启问题审核</a>
				</c:if>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/closeQuestionSwitch') && questionSwitch==0}">
				<a href="javascript:void(0)" onclick="closeQuestionSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',toggle:true">关闭问题审核</a>
				</c:if>
			</li>
			<br/>
			<li>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/openAnswerSwitch') && answerSwitch==1}">
				<a href="javascript:void(0)" onclick="openAnswerSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-add',toggle:true">开启答案审核</a>
				</c:if>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/closeAnswerSwitch') && answerSwitch==0}">
				<a href="javascript:void(0)" onclick="closeAnswerSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',toggle:true">关闭答案审核</a>
				</c:if>
			</li>
			<br/>
			<li>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/openQuestionCommentSwitch') && qcSwitch==1}">
				<a href="javascript:void(0)" onclick="openQuestionCommentSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-add',toggle:true">开启问题评论审核</a>
				</c:if>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/closeQuestionCommentSwitch') && qcSwitch==0}">
				<a href="javascript:void(0)" onclick="closeQuestionCommentSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',toggle:true">关闭问题评论审核</a>
				</c:if>
			</li>
			<br/>
			<li>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/openAnswerCommentSwitch') && acSwitch==1}">
				<a href="javascript:void(0)" onclick="openAnswerCommentSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-add',toggle:true">开启答案评论审核</a>
				</c:if>
				<c:if test="${admin.hasPrivilegeByUrl('/web/admin/closeAnswerCommentSwitch') && acSwitch==0}">
				<a href="javascript:void(0)" onclick="closeAnswerCommentSwitch()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',toggle:true">关闭答案评论审核</a>
				</c:if>
			</li>
		</ur>
	</div>	
</body>
</html>
<script type="text/javascript">
	function openQuestionSwitch(){
        	$.messager.confirm('确认提示', '您确定要开启问题审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/openQuestionSwitch", null, function (result) {
                          if (result.success) {
                          	  $.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function closeQuestionSwitch(){
        	$.messager.confirm('确认提示', '您确定要关闭问题审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/closeQuestionSwitch", null, function (result) {
                          if (result.success) {
                          	$.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href; 
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function openAnswerSwitch(){
        	$.messager.confirm('确认提示', '您确定要开启答案审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/openAnswerSwitch", null, function (result) {
                          if (result.success) {
                          	  $.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function closeAnswerSwitch(){
        	$.messager.confirm('确认提示', '您确定要关闭答案审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/closeAnswerSwitch", null, function (result) {
                          if (result.success) {
                          	$.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function openQuestionCommentSwitch(){
        	$.messager.confirm('确认提示', '您确定要开启问题评论审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/openQuestionCommentSwitch", null, function (result) {
                          if (result.success) {
                          	  $.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href; 
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function closeQuestionCommentSwitch(){
        	$.messager.confirm('确认提示', '您确定要关闭问题评论审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/closeQuestionCommentSwitch", null, function (result) {
                          if (result.success) {
                          	$.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;   
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function openAnswerCommentSwitch(){
        	$.messager.confirm('确认提示', '您确定要开启答案评论审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/openAnswerCommentSwitch", null, function (result) {
                          if (result.success) {
                          	  $.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;  
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
        
        function closeAnswerCommentSwitch(){
        	$.messager.confirm('确认提示', '您确定要关闭答案评论审核吗?', function (r) {
                  if (r) {
                      $.post("${ctx}/web/admin/closeAnswerCommentSwitch", null, function (result) {
                          if (result.success) {
                          	$.messager.show({    
                                  title: '确认',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          } else {
                              $.messager.show({    
                                  title: '错误',
                                  msg: result.msg
                              });
                              location.href=location.href;
                          }
                      }, 'json');
                  }
             });
        }
    </script>