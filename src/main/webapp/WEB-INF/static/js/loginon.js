<!--
/*
 *登陆验证
 */
document.write("<SCRIPT LANGUAGE=javascript src='js/comm.js'></SCRIPT>")
document.write("<SCRIPT LANGUAGE=javascript src='js/publicjs.js'></SCRIPT>")

function checkCookie()
{
	checkCookies()
	var username = getCookie("username");
	if(username == "" || username == null)
	{
		return;
	}
	document.getElementById("username").value = username;
	var psd = getCookie("password");
	if(psd == "" || psd == null)
	{
		return;
	}
	document.getElementById("password").value = username;
	document.getElementById("chk_savepsd").checked = true;
	
}
var usernames = new Array();
var passwords = new Array();
function checkCookies()
{
	usernames.length = 0;
	passwords.length = 0;
	document.getElementById("userlist").innerHTML = "";
	var userlist = getCookie("userlist");
	if(userlist == null || userlist == "") return;
	var users = userlist.split("|");
	for(var i=0; i< users.length; i++)
	{
			var usd = users[i].split("+");
			usernames[i] = usd[0];
			passwords[i] = usd[1];
	}
	
	var htmls = "";
	for(var i =0; i<users.length; i++)
	{
		htmls += "<div onmouseover='mOver(this)' onmouseout='mOut(this)' onclick='chooseUser(this)' id='user_"+i+"'><span>"+usernames[i]+"</span></div>";
	}
	document.getElementById("userlist").innerHTML = htmls;
}
function addUserCookie(usr, psd)
{
	for(var i=0; i<usernames.length; i++)
	{
			if(usernames[i] == usr)
			{
				usernames.RemoveAt(i);
				passwords.RemoveAt(i);
				break;
			}
	}
	usernames.InsertAt(0,usr);
	passwords.InsertAt(0,psd);
	var saveUsrString = "";
	for(var i=0; i<usernames.length; i++)
	{
			saveUsrString += (usernames[i]+"+"+passwords[i]);
			if(i != usernames.length-1)
				saveUsrString += "|";
	}
	setCookie("userlist", saveUsrString, 1000);
}
function mOver(obj)
{
	obj.style.background = '#0099FF';
}
function mOut(obj)
{
	obj.style.background = 'transparent';
}
function showUserDiv()
{
	if(usernames.length == 0) return;
	document.getElementById("userlist").style.display ="block";	
}
function hideUserDiv()
{
	document.getElementById("userlist").style.display ="none";	
}
function refreshUserList()
{
		var tname = document.getElementById("username").value;
		var htmls = "";
		for(var i =0; i<usernames.length; i++)
		{
			if(usernames[i].indexOf(tname) == 0)
				htmls += "<div onmouseover='mOver(this)' onmouseout='mOut(this)' onclick='chooseUser(this)' id='user_"+i+"'><span>"+usernames[i]+"</span></div>";
		}
		if(htmls == "") document.getElementById("userlist").style.display ="none"
		else document.getElementById("userlist").style.display ="block"
		document.getElementById("userlist").innerHTML = htmls;
}

function chooseUser(obj)
{
	var oid = obj.id.split("_");	
	var oindex = parseInt(oid[1]);
	document.getElementById("username").value = usernames[oindex];
	if(passwords[oindex] != "")
	{
		document.getElementById("password").value = passwords[oindex];
		document.getElementById("chk_savepsd").checked = true;
	}
	else
	{
			document.getElementById("chk_savepsd").checked = false;
			document.getElementById("password").value = "";
	}
	document.getElementById("userlist").style.display ="none";	
}
var loginonphp="Login.php?";
var mxhr;
function loginOn(usrname, passwd)
{
	 var tosend = loginonphp+"user="+usrname+"&"+"passwd="+passwd;
	 mxhr = sender(tosend, loginOnCallBack);
}
function loginOnCallBack()
{
		if(!mxhr) return;
 		if(mxhr.readyState == 4)
 		{
 					var returnValue = mxhr.responseText;
          if(returnValue != null && returnValue.length > 0)
          {
              // document.getElementById("debug").innerText = returnValue;
            //   alert(returnValue);
               if(returnValue == "ok")
               		loginOnReturn(0); 
               else if(returnValue == "error user")
               {
               			loginOnReturn(1); 
               }
               else loginOnReturn(2);
          }
          else
          {
             // document.getElementById("results").innerHTML = "<p><font color=red>结果为空！！！</font></p>";
          }
 		}
}
function loginOnReturn(ret)
{
	var obj = document.getElementById("chk_savepsd");
	switch(parseInt(ret))
	{
		case 0:
			if(obj.checked)
			{
				addUserCookie(document.getElementById("username").value, document.getElementById("password").value);
			}
			else
		  {
		 		addUserCookie(document.getElementById("username").value, "");
		 	}
			location.replace("home.html");
			break;
		case 1:
			document.getElementById("checkinfo").innerText = "用户不存在" ;
			document.getElementById("username").select();
			break;
		case 2:
			addUserCookie(document.getElementById("username").value, "");
			document.getElementById("checkinfo").innerText = "密码错误" ;
			document.getElementById("password").select() ;
			break;
		default:break;
	}
}

//-->