<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}－修改密码</title>
<link href="${ctx}/web/css/css.css" rel="stylesheet" type="text/css">
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
            <div class="form">
                <form id="passwordForm">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">原登录密码：</div>
						            <div class="fl input"><input name="oldPwd" id="oldPwd" type="password" placeholder="输入原登录密码"></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    </tr>
						    <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">新登录密码：</div>
						            <div class="fl input"><input name="newPwd" id="newPwd" type="password" placeholder="输入新登录密码"></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    </tr>
						    <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">确认登录密码：</div>
						            <div class="fl input"><input name="rePwd" id="rePwd" type="password" placeholder="确认登录密码"></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    </tr>
						    <tr>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl">
						            <c:if test="${admin.hasPrivilegeByUrl('/web/admin/saveAdminPwd')}">
						            <a href="javascript:void(0);" onclick="savePassword();" class=" sure">确认</a>
						            </c:if>
						            </div>
						            <div class="fl"></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						   </tr>
					</tbody>
					</table>
				</form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
	function savePassword(){
		var newpwd = $("#newPwd").val(); 
		var reg = /^(?=.*[A-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,16}$/;  
		var flag = reg.test(newpwd);  
		if(flag == false){  
			$.messager.alert('警告','新登录密码格式：6~16位，至少包含一个大写字母和一个特殊字符！','warning',function(){
				$("#newPwd").focus();
			});
			return false;     
		}
		var data=$("#passwordForm").serialize();
		$.post("${ctx}/web/admin/saveAdminPwd",data,function(d){
			if(d==-2){
				$.messager.alert('警告','请输入原登录密码！','warning',function(){
					$("#oldPwd").focus();
				});
				return false;
			}
			if(d==-4){
				$.messager.alert('警告','请输入确认密码！','warning',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-5){
				$.messager.alert('警告','新密码和确认密码不一致！','warning',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-6){
				$.messager.alert('警告','原登录密码输入错误！','warning',function(){
					$("#oldPwd").focus();
				});
				return false;
			}
			$.messager.alert('确定','登陆密码修改成功，请牢记新的登陆密码！','info',function(){
				location.href="${ctx}/web/admin/editAdminPwd";
			});
		});
	}
</script>
