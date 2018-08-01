<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
color: #3dc0ee;
}
input:-moz-placeholder, textarea:-moz-placeholder {
color: #3dc0ee;
}
input::-moz-placeholder, textarea::-moz-placeholder {
color: #3dc0ee;
}
input:-ms-input-placeholder, textarea:-ms-input-placeholder {
color: #3dc0ee;
} 
</style>
<div class="qdBan">
	<div class="indexHead">
		<div class="indexLogo"> <img src="${ctx}/web/images_line/qdLogo.png" /></div>
	      <div class="search">
	         <input id="stockCode" class="sercInput bsiz"  type="search" list="mydata" placeholder="股票搜索" value="${code}" autocomplete="off">
   	         <div class="sercImg" onclick="serachEvent()"></div>
	         <div class="indexShadow"></div>
	      </div>
		<div class="indexBan"><span>每天09：15刷新</span></div>
	  	<%-- <c:if test="${empty fuUser}">
	  	    <div class="qdLog">
				<a href="javascript:void(0)" onclick="login();">登录</a> / <a href="javascript:void(0)" onclick="login();">注册</a>
			</div> 
	  	</c:if> --%>
	  	<%-- <c:if test="${!empty fuUser}">
	  	    <div class="qdLogins smalSze">
				<p>欢迎您，<span>${fuUser.nickName}</span></p>
				<a href="javascript:void(0)" onclick="logout();">退出</a>
			</div>
	  	</c:if> --%>
	</div>
</div>
<div class="nav">
	<ul>
		<c:if test="${!empty fuUser}">
	  	    <li class="qdHot"><a href="${ctx}/web/ai/qdHot">热度板块<br><i class="qdIco"></i></a></li>
	  	</c:if>
	  	<c:if test="${empty fuUser}">
	  	    <li class="qdHot"><a href="${ctx}/web/ai/qdIndex">热度板块<br><i class="qdIco"></i></a></li>
	  	</c:if>
		<li class="qdGn"><a href="${ctx}/web/ai/qdConcept">概念板块<br><i class="qdIco"></i></a></li>
		<li class="qdArea"><a href="${ctx}/web/ai/qdArea">地域板块<br><i class="qdIco"></i></a></li>
		<%-- <li class="qdTalk"><img class="hot" src="${ctx}/web/images_line/hotIco.png"/><a class="talkBtn" href="http://live.hhr360.com/"  target="_blank">聊天室<br><i class="qdIco"></i></a></li> --%>
	</ul>
</div>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
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
	        window.location = "${ctx}/web/ai/qdKline?id="+ui.item.id;
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