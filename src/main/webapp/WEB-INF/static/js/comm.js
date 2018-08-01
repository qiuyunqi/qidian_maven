<!--
/*
 *登陆验证
 */

function createXHR() 
{
    var xhr;


    try 
    {
        xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } 
    catch (e) 
    {
        try 
        {
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch(E) 
        {
            xhr = false;
        }
    }

    if (!xhr && typeof XMLHttpRequest != 'undefined') 
    {
        xhr = new XMLHttpRequest();
    }
		
    return xhr;
}

/*
 *异步访问提交处理
 */
function sender(tosend,func) 
{
    xhr = createXHR();
		if(!xhr) alert("createXHR failed!");
    if(xhr)
    {
        xhr.open("GET", "./policeRecorder/"+tosend,true);
    		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhr.onreadystatechange=func;
        xhr.send(null);
    }
    else
    {
        alert("浏览器不支持，请更换浏览器！");
    }
    return xhr;
}
function senderHttp(tosend,func) 
{
    xhr = createXHR();alert(tosend)
		if(!xhr) alert("createXHR failed!");
    if(xhr)
    {
        xhr.open("GET", tosend,true);
    		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhr.onreadystatechange=func;
        xhr.send(null);
    }
    else
    {
        alert("浏览器不支持，请更换浏览器！");
    }
    return xhr;
}
var sessionxhr;
var sessionphp = "GetSessionState.php";
function getsession()
{
	sessionxhr = sender(sessionphp, getsessionCallBack);
}
function getsessionCallBack()
{
		if(!sessionxhr) return;
 		if(sessionxhr.readyState == 4)
 		{
 					var returnValue = sessionxhr.responseText;
          if(returnValue != null && returnValue.length > 0)
          {
              
               if(returnValue == "error")
               {
               		redirection();
               }
                
          } 
          else
          {
             // document.getElementById("results").innerHTML = "<p><font color=red>结果为空！！！</font></p>";
          }
 		}
}
function redirection()
{
	/*var obj,obj1;
	obj1 = document.element[0];
	while(obj = obj1)
	{
		obj1 = obj.parent;
	}*/
}
//-->