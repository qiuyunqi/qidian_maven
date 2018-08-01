<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no" name="viewport"/>
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
<%-- <%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%> --%>
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/line.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/jquery-ui.css"></link>
<style>
        html{width: 100%;height:auto;}
        body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fff;height:auto; }
        input{border:none;background: #fff;-webkit-appearance: none;}
        .ui-autocomplete {max-height: 100px;overflow-y: auto;overflow-x: hidden;}
		* html .ui-autocomplete {height: 100px;}
		 .ui-autocomplete-category {font-weight: bold;padding: .2em .4em;margin: .8em 0 .2em;line-height: 1.5;}
        input[type="button"], input[type="submit"], input[type="reset"] {
            -webkit-appearance: none;
        }
         .fgt{float:right;}
         .ui-menu .ui-menu-item a{height: 48px;line-height: 48px;}
         .ui-menu .ui-menu-item a:hover{background: #dadada;}
        @media screen and (max-width:240px){
			.cenSize{font-size: 0.8rem;}
		}
		.index_time {
			width: 100%;
			height: 30px;
			color: #fff;
			margin: 0px auto;
		}
		.index_time span {
		    display: block;
			margin: 8px auto;
		    width: 160px;
		    height: 18px;
		    font-size: 16px;
		}
    </style>
</head>
<body>
	 <div class="index-wrapper">
	 	<div class="indexHead">
	        <div class="indexLogo"> <img src="${ctx}/web/images_line/indexLogo.png" /></div>
	        <div class="search">
	            <input id="stockCode" class="sercInput bsiz"  type="search" list="mydata" placeholder="股票搜索" value="${code}" autocomplete="off">
	            <div class="sercImg" onclick="serachEvent()"></div>
	            <div class="indexShadow"></div>
	        </div>
	        <div class="indexBan"><span>每天09：15刷新</span></div>
	    </div>
	    <div class="indexList">
            <table class="stockTable textCen" cellpadding="0" cellspacing="0" width="100%" border="0">
            		<c:forEach items="${praiseList}" var="praise" varStatus="row">
					    <tr class="stockTable-hover" onclick="jumpLine('${praise.stockInfo.id}')"> 
					    	<td><span class="firstTd bsiz">${praise.name}</span></br><span class="ssize">${praise.code}</span></td>
					    	<c:if test="${!empty praise.stockInfo.liftRate}">
								<c:if test="${praise.stockInfo.liftRate >= 0}">
									<td><span class="zangColor bsiz">${praise.stockInfo.currPrice}</span></br><span class="zangColor ssize">${praise.stockInfo.liftRate}%</span></td>
								</c:if>
								<c:if test="${praise.stockInfo.liftRate < 0}">
									<td><span class="green bsiz">${praise.stockInfo.currPrice}</span></br><span class="green ssize">${praise.stockInfo.liftRate}%</span></td>
								</c:if>
							</c:if>
							<c:if test="${empty praise.stockInfo.liftRate}">
								<td><span class="zangColor bsiz"></span></br><span class="zangColor ssize"></span></td>
							</c:if>
						    <td><a href="javascript:void(0)" onclick="jumpLine('${praise.stockInfo.id}')"><img src="${ctx}/web/images_line/moreBtn.png"/></a></td>
						</tr>
					</c:forEach>
            </table>
        </div>
        <div class="stockThank">特别感谢：</br>阿金；晦聪禅师；★WYLYF☆；骑士八律；在开发测试时做出的贡献。</div>
        <form id="searchForm" action="${ctx}/web/stock/stockIndex" method="post"></form>
    </div>
</body>
</html>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?577cde65ff9c5c260eeca3db486575c2";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();

	function jumpLine(id)  
	{  
	   	window.location = "${ctx}/web/stock/kLine?id="+id;
	}  
  
    function serachEvent()  
    {  
    	var code = $("#stockCode").val();
    	if(code == null || code == ""){
    		$('#searchForm').submit();
    	}else{
    		window.location = "${ctx}/web/stock/stockIndex?code="+code;  
    	}
    }    
</script>   
<script>
    $(function() {
	    var projects = ${items};
	    $( "#stockCode" ).autocomplete({
	      minLength: 0,
	      source: projects,
	      focus: function( event, ui ) {
	        $( "#stockCode" ).val( ui.item.category );
	        return false;
	      },
	      select: function( event, ui ) {
	        $( "#stockCode" ).val( ui.item.category );
	        window.location = "${ctx}/web/stock/kLine?id="+ui.item.id;
	        return false;
	      }
	    })
	    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append(  "<a>" + "<span>"+ item.category + "</span>" +  "<span class='fgt'>"+ item.code+ "</span>" + "</a>")
	        .appendTo( ul );
	    };
    });
</script> 

<script>
/* $(function() {
    var codeArr = "${codeArr}";
    $.ajax({
        type: 'GET',
        url: '${ctx}/web/stock/getPriceAndRange',
        data:{codeArr:codeArr},
        dataType: 'json',
        success: function(data){
        	for(var j=0; j<5; j++){
        		var arrText = [];
            	var result = '';
        		if(data[j].change.indexOf("-") != -1){
	        		arrText.push("<td><span class='green bsiz'>"+data[j].nowPri+"</span></br><span class='green ssize'>"+data[j].change+"</span></td>");
        		}else{
	        		arrText.push("<td><span class='zangColor bsiz'>"+data[j].nowPri+"</span></br><span class='zangColor ssize'>"+data[j].change+"</span></td>");
        		}
        		result += arrText.join('');
            	$("#"+data[j].code).append(result);
        	}
        },
        error: function(xhr, type){
            alert('Ajax error!');
        }  
    });
});  */
</script> 
