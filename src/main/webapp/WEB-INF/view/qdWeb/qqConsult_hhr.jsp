<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/commonQd.css"></link>
<div class="hzxzx">
<a class="erweima zax" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2211247320&site=qq&menu=yes"><i class="zaixxianzx11"></i><span>在线咨询</span></a>
</div>
<div class="hzxzx1"><div class="wxcode"><img src="${ctx}/web/images_common/borwx.png" /></div><a href="javascript:void(0)" id="img1" class="erweima weiwei"><i class="weixincd1"></i><span>微信公众号</span></a></div>
<script>
$(".wxcode").hide(); 
$(document).ready(function(){
	$("#img1").mouseover(function(){
		$(".wxcode").show();
	})
	$("#img1").mouseout(function(){
		$(".wxcode").hide();
	})

});  
</script>