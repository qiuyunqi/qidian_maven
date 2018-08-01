//操作页面的脚本控制
$(function(){
	//聚焦和改变时的函数
	jQuery.fn.focusChangeFun=function(objName,onFocusText,onSuccessText,nullText,returnFlag){
		if($("#" + objName).val()=="" || (nullText!="" && $("#" + objName).val()==nullText)){
			$("#" + objName + "Tip").attr("class","onFocus");
			$("#" + objName + "Tip").html(onFocusText);
			if(returnFlag==true){
				return 1;
			}
		}else if($("#" + objName).val()!="" || (nullText!="" && $("#" + objName).val()!=nullText)){
			$("#" + objName + "Tip").attr("class","onSuccess");
			$("#" + objName + "Tip").html(onSuccessText);
			if(returnFlag==true){
				return 0;
			}
		}
	}
	//失去焦点的函数
	jQuery.fn.blurClickFun=function(objName,onErrorText,onSuccessText,nullText,returnFlag){
		if($("#" + objName).val()=="" || (nullText!="" && $("#" + objName).val()==nullText)){
			$("#" + objName + "Tip").attr("class","onError");
			$("#" + objName + "Tip").html(onErrorText);
			if(returnFlag==true){
				return 1;
			}
		}else if($("#" + objName).val()!="" || (nullText!="" && $("#" + objName).val()!=nullText)){
			$("#" + objName + "Tip").attr("class","onSuccess");
			$("#" + objName + "Tip").html(onSuccessText);
			if(returnFlag==true){
				return 0;
			}
		}
	}
	
	//控件验证描述(文本框)
	jQuery.fn.validateTextFun=function(objName,onShowText,onFocusText,onErrorText,onSuccessText){
		$("#" + objName + "Tip").attr("class","onShow");
		$("#" + objName + "Tip").html(onShowText);
		$("#" + objName).focus(function(){$(this).focusChangeFun(objName,onFocusText,onSuccessText,"",false)});
		$("#" + objName).blur(function(){$(this).blurClickFun(objName,onErrorText,onSuccessText,"",false)});
	}
	
	//控件验证描述(下拉菜单)
	jQuery.fn.validateSelectFun=function(objName,onShowText,onFocusText,onErrorText,onSuccessText,nullText){
		$("#" + objName + "Tip").attr("class","onShow");
		$("#" + objName + "Tip").html(onShowText);
		$("#" + objName).change(function(){$(this).focusChangeFun(objName,onFocusText,onSuccessText,nullText,true)});
		$("#" + objName).blur(function(){$(this).blurClickFun(objName,onErrorText,onSuccessText,nullText,true)});
	}
	
	//点击时验证文本框或下拉菜单
	jQuery.fn.validateTextSelectFunByClick=function(objName,onErrorText,onSuccessText){
		return $(this).blurClickFun(objName,onErrorText,onSuccessText,"",true);
	}
	
	//验证下拉菜单类型级数
	jQuery.fn.validateSelectLevelFunByClick=function(objName,onErrorText,onSuccessText,checkText){
		if($("#" + objName).find("option:selected").text().indexOf(checkText)!=-1){
			$("#" + objName + "Tip").attr("class","onError");
			$("#" + objName + "Tip").html(onErrorText);
			return 1;
		}else{
			return 0;
		}
	}
	
	//所有按钮手形样式
	$("input[type='button']").attr("style","cursor:hand");
	//在btnInnerReset按钮后面加上返回按钮
	$("#btnReset").after("&nbsp;&nbsp;&nbsp;<input type=\"button\" onclick='history.go(-1);' name=\"btnReturn\" id=\"btnReturn\" value=\"返回\" class=\"button_b\"/>");
});