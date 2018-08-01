<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>奇点后台管理系统</title>
<link href="${ctx}/web/css/css.css" rel="stylesheet" type="text/css">
</head>
<body class="login_page">
    <div class="login_div">
    	<div class="login_main">
    		<div class="loginTit">奇点后台管理系统</div>
    		<div class="loginLogo"><img src="${ctx}/web/images_line/indexLogo.png"></div>
    		<form id="loginForm" method="post">
	        	<div class="txt">
	            <p class="margB">
	           	 	<i class=" zh"><img src="${ctx}/web/images_line/username.png"></i>
	            	<input type='text' name='account' id='account' class='login-text' value="${account}" placeholder="账号"/>
	            </p>	
	            <p>
	            	<i class=" mm"><img src="${ctx}/web/images_line/userpass.png"></i>
	            	<input type='password' name='password' id='password' class="login-text"  value="" placeholder="密码"/>
	            </p>
	            </div>
	            <div class=" dl_cont0" style="margin:15px auto;color:#fff;width: 217px;">
	        	<input id="isRemain" name="isRemain" type="checkbox" value="true"><span style="margin-right:20px;margin-left: 5px;" >记住账号</span>
	            <input id="isAuto" name="isAuto" type="checkbox" value="true"><span style="margin-left: 5px;">下次自动登录</span>
	        	</div>
	            <div id="loginClick" class="btn l-clear" onclick="login()"><span>登录</span></div>
            </form>
        </div>
    </div>
</body>
<script>
var hgt=$(".login_page").height()/2;
var hgt2=$(".login_div").height()/2;
var hgt3=hgt-hgt2;
$(".login_div").css("margin-top",hgt3);

function login(){
  	var data = $("#loginForm").serialize();
  	$.post("${ctx}/web/admin/loginAjax",data,function(d){
        if(d==-2){
        	$.messager.alert('警告','请输入账号');
            return;
        }
        if(d==-3){
        	$.messager.alert('警告','请输入密码');
            return;
        }
        if(d==-4){
        	$.messager.alert('警告','账号不存在');
            return;
        }
        if(d==-5){
        	$.messager.alert('警告','密码输入错误');
            return;
        }
        if(d==-6){
        	$.messager.alert('警告','您的IP地址在黑名单中,无法登录');
            return;
        }
        if(d==-7){
        	$.messager.alert('警告','账号未解禁,无法登录');
            return;
        }
        if(d==-8){
        	$.messager.alert('警告','密码连续错误5次,禁止登录1天');
            return;
        }
        location.href = "${ctx}/web/admin/home";
    });
}

$("#loginForm").keydown(function(e){
	 var e = e || event,
	 keycode = e.which || e.keyCode;
	 if (keycode==13) {
	  $("#loginClick").trigger("click");
	 }
});

//placeholder的兼容
 function isPlaceholder(){
	 var input = document.createElement('input');
	 return 'placeholder' in input;
}

if (!isPlaceholder()) {//不支持placeholder 用jquery来完成
 $(document).ready(function() {
     if(!isPlaceholder()){
         $("input").not("input[type='password']").each(//把input绑定事件 排除password框
             function(){
                 if($(this).val()=="" && $(this).attr("placeholder")!=""){
                     $(this).val($(this).attr("placeholder"));
                     $(this).focus(function(){
                         if($(this).val()==$(this).attr("placeholder")) $(this).val("");
                     });
                     $(this).blur(function(){
                         if($(this).val()=="") $(this).val($(this).attr("placeholder"));
                     });
                 }
         });
         //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
         var pwdField = $("input[type=password]");
         var pwdVal  = pwdField.attr('placeholder');
         pwdField.after('<input id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');
         var pwdPlaceholder = $('#pwdPlaceholder');
         pwdPlaceholder.show();
         pwdField.hide();
         
         pwdPlaceholder.focus(function(){
	          pwdPlaceholder.hide();
	          pwdField.show();
	          pwdField.focus();
         });
         
         pwdField.blur(function(){
	          if(pwdField.val() == '') {
		           pwdPlaceholder.show();
		           pwdField.hide();
	          }
        });
     }
 });
}
</script> 

</html>