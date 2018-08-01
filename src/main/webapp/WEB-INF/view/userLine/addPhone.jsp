<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<meta name="google" content="notranslate">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="yes">
<meta name="x5-fullscreen" content="true">
<meta name="browsermode" content="application">
<meta name="x5-page-mode" content="app">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<title>${title}-绑定手机号</title>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
input[type=checkbox] {
	visibility: hidden;
}
#send{border:0;border-radius:0;}
/* input[type='text']:focus, input[type='password']:focus {
    border: 0;background: #fff;box-sizing: border-box;border-radius: 0px;
	-webkit-appearance: textfield;-webkit-rtl-ordering: logical;
    -webkit-user-select: text;cursor: auto;    -webkit-writing-mode: horizontal-tb;
	-webkit-tap-highlight-color:rgba(255,255,255,0);
} */
input[type='text'], input[type='password']{
-webkit-tap-highlight-color:rgba(255,255,255,0);-webkit-appearance:none;
}
input[type=”button”], input[type=”submit”], input[type=”reset”] {
-webkit-appearance: none;
}
input ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #646473;
		}
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #646473;
		}
input::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #646473;
		}
input:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #646473;
}
.addMyBtn {background: #999; color: #000; pointer-events: none;}
*:focus {outline:0 none;} 
.busBumInpt{height:30px;line-height: 30px;margin:10px 0 0;}
.busBum{width:90% !important;}
@media screen and (max-width:240px){
.adphoneYqb{width:50px !important; font-size: 10px;}
.smallSize {font-size: 8px !important;}
.adPhNem .adPnSend{ width: 50px !important;font-size: 8px !important;}
.busBumInpt{font-size: 8px;}
}

</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
			<form>
				<table class="busBum adPon" cellpadding="0" cellspacing="0" width="90%" border="0">
		       		<tr>
		       			<td>
				       		<img src="../images/phone.png"/>
		       			</td>
		       			<td class="blueCol smallSize adphoneYqb">手机号码：</td>
		       			<td>
		       				<div class="adPhNem">
		       					<input class="busBumInpt" type="text" name="phone" />
		       					<!-- <input id="send">发送</a> -->
		       					<input id="send" class="adPnSend" type="button" value="发送"/>
		       				</div>
		       			</td>
		       		</tr>
		       		<tr >
		       			<td class="jge"></td>
		       			<td class="jge"></td>
		       			<td class="jge"></td>
		       		</tr>
		       		<tr>
		       			<td>
				       		<img src="../images/phoneCode.png"/>
		       			</td>
		       			<td class="blueCol smallSize adphoneYqb">短信验证码：</td>
		       			<td>
		       				<div class="adPhNem"><input class="busBumInpt" type="text" name="sendCode" /></div>
		       			</td>
		       		</tr>
		       	</table>
		       	<span id="saveUser" class="sveBt">保存</span>
			</form>
			</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var check = function() {
		var phone = $("input[name='phone']").val();
		var sendCode = $("input[name='sendCode']").val();
		if("" == phone || null == phone) {
			layer.open({
			    content: '请填写手机号码',
			    btn: ['确定']
			});
			return null;
		}
		if (phone.length != 11) {
			layer.open({
			    content: '请填写正确的手机号码',
			    btn: ['确定']
			});
			return null;
		}
		if("" == sendCode || null == sendCode) {
			layer.open({
			    content: '请填写验证码',
			    btn: ['确定']
			});
			return null;
		}
		if (sendCode.length != 4) {
			layer.open({
			    content: '请填写正确的验证码',
			    btn: ['确定']
			});
			return null;
		}
		return true;
	}
	
	var times = 60;
	function timeCount() {
		times = times-1;
		$("#send").val("剩余"+times+"s");
		$("#send").addClass("addMyBtn");
		$("#send").css({"background" : "#999", "color" : "#000"});
		if(times == 0) {
			clearTimeout(t);
			$("#send").val("重新获取");
			times = 60;
			$("#send").removeClass("addMyBtn");
			$("#send").css({"background" : "#ffebb7 none repeat scroll 0 0", "color" : "#ff7616"});
		}else {
			t = setTimeout("timeCount()",1000);
		}
	}
	
	$("#send").click(function() {
		var phone = $("input[name='phone']").val();
		if("" == phone || null == phone) {
			layer.open({
			    content: '请填写手机号码',
			    btn: ['确定']
			});
			return null;
		}
		if (phone.length != 11) {
			layer.open({
			    content: '请填写正确的手机号码',
			    btn: ['确定']
			});
			return null;
		}

		timeCount();
		// 发送验证码
		$.post("${ctx}/web/stock/sendCode", {"phone": phone}, function(data) {
			if(data == 1) {
				layer.open({
				    content: '请联系管理员解冻你的封印',
				    btn: ['确定']
				});
				return ;
			}
			if (data == 2) {
				layer.open({
				    content: '您点击过于频繁, 请稍后再试',
				    btn: ['确定']
				});
				return ;
			}
		});
	});
	
	
	// 保存手机号码
	$("#saveUser").click(function() {
		var phone = $("input[name='phone']").val();
		var sendCode = $("input[name='sendCode']").val();
		if("" == phone || null == phone) {
			layer.open({
			    content: '请填写手机号码',
			    btn: ['确定']
			});
			return null;
		}
		if (check()) {
			$.post("${ctx}/web/stock/bindPhone", {"phone": phone, "phoneCode": sendCode}, function(data) {
				if(data == 1) {
					layer.open({
					    content: '请填写验证码',
					    btn: ['确定']
					});
					return ;
				}
				if (data == 2) {
					layer.open({
					    content: '请重新发送验证码',
					    btn: ['确定']
					});
					return ;
				}
				if (data == 3) {
					layer.open({
					    content: '验证码输入错误',
					    btn: ['确定']
					});
					return ;
				}
				if (data == 4) {
					location.href="${ctx}${redirectUrl}";
					return ;
				}
				if (data == 5) {
					layer.open({
					    content: '非法路径, 请联系管理员',
					    btn: ['确定']
					});
					return ;
				}
			});
		}
	});
</script>
</html>