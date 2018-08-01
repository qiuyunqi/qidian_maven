<!--
//self-define color of enable or disable

Array.prototype.InsertAt=function(index,obj){ 
this.splice(index,0,obj); 
} 
Array.prototype.RemoveAt=function(index){ 
this.splice(index,1); 
} 
Array.prototype.Remove = function(val)
{
	for(var i=0; i<this.length; i++)
	{
		if(this[i] === val)	
			this.splice(i,1);
	}	
}

function disableObj(objname)
{
	var tempobj = document.getElementById(objname);
	tempobj.style.background = "#E0E0E0";
	tempobj.disabled = true;
}
var checkboxbg;
function disableCheckBox(objname)
{
	var tempobj = document.getElementById(objname);
	checkboxbg = tempobj.style.background;
	tempobj.style.background = "transparent";
	tempobj.disabled = true;
}
function enableCheckBox(objname)
{
	var tempobj = document.getElementById(objname);
	if(tempobj.disabled == false) return;
	tempobj.style.background = checkboxbg;
	tempobj.disabled = false;
}
function enableSelectObj(objname)
{
	var tempobj = document.getElementById(objname);
	tempobj.style.background = "#FFFFFF";
	tempobj.disabled = false;
}
function enableInputObj(objname)
{
	var tempobj = document.getElementById(objname);
	tempobj.style.background = "#FFFFFF";
	tempobj.disabled = false;
	tempobj.focus();
}

function insertToSelect(selectobj, selectoption, optionValue)
{
	if(selectobj == null) return;
	if (navigator.userAgent.indexOf('Firefox') >= 0)
	{
		var item = document.createElement("option");
		item.text = selectoption;
		item.value = optionValue;
		selectobj.appendChild(item);
	}
	else
	{
		var item = new Option(selectoption, optionValue);
		selectobj.add(item);
	}
}
function clearSelect(selectobj)
{
	selectobj.length = 0;
}
function publicGetTodayDate()
{
	var day = new Date();
	var year;
	var month;
	var day;
	var currentDate = "";
	year = day.getFullYear();
	month = day.getMonth()+1;
	day = day.getDate();
	currentDate += year + "-";
	if(month >= 10)
	{
		currentDate += month +"-";
	}
	else
	{
		currentDate += "0" + month +"-";
	}
	if(day >= 10)
	{
		currentDate += day;
	}
	else
	{
		currentDate += "0" + day;
	}
	
	return currentDate;
}

function getCookie(CKname)
{  
    var arrCookie = document.cookie.match(new RegExp("(^| )"+CKname+"=([^;]*)(;|$)"));  
    if(arrCookie!=null)
    {  
        return unescape(arrCookie[2]);  
    }
    else
    {  
        return null;  
    };  
}; 
function setCookie(CKname,CKvalue,CKexpireHour){  

    document.cookie = CKname+"="+escape(CKvalue); 
    var str =  CKname+"="+escape(CKvalue); 
    if(CKexpireHour > 0)
    {
    	var date = new Date();
    	var ms = CKexpireHour*3600*1000;
    	date.setTime(date.getTime()+ms);
    	str += (";expires=" + date.toGMTString());
    }
    document.cookie = str;
};
function deleteCookie(CKname)
{  
   document.cookie = CKname+"=;expires="+(new Date(0)).toGMTString();  
};
 //-->