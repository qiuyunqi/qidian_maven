<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 专家排行 -->
<div class="sidebar-left">
	<div class="chartBody">
		<h4>专家排行榜</h4>
		<ul class="chart_ul">
		</ul>
		<a class="chartBtn defaultCol" onclick="expand()">展开<img src="${ctx}/web/images_qiDa/more_down.png"/></a>
	</div>
</div>
<%@ include file="/WEB-INF/view/qida/common/userInfo.jsp" %>
<script type="text/javascript">
	var counter = 0;
	expand();
	function expand(){
		   // 每次加载5个
		   var num = 5;
		   var pageStart = 0,pageEnd = 0;
           $.ajax({
               	type: 'POST',
               	url: '${ctx}/web/qida/expertData.html',
               	dataType: 'json',
               	success: function(data){
		                var length=data.array.length;
			           	var result = '';
	                   	counter++;
	                   	pageEnd = num * counter;
	                   	pageStart = pageEnd - num;
	                   	
	                   	for(var i = pageStart; i < pageEnd; i++){
	                    	var arrText = [];
		                 	arrText.push("<li><div class='chartTx' onmouseover='mousove($(this),50,20,"+data.array[i].userId+")'  onmouseout='mousout($(this))'><a><i class='doctor'></i><img src='"+data.array[i].userAvatar+"'/></a></div>");
							arrText.push("<div class='infoAdd'></div>");
							arrText.push("<div class='chartInfo'><div class='chartName defaultCol'><a>"+data.array[i].nickName+"</a></div>");
							arrText.push("<div class='chartNum'>"+data.array[i].qidaIntegral+"</div></div></li>");
	                       	result +=  arrText.join('');
	                       	if((i + 1) >= data.array.length){
	                           break;
	                       	}
	                   }
	                   setTimeout(function(){
	                       	$('.chart_ul').append(result);
	                       	if(length < (num*counter) || length <= 6){
	                   			$(".chartBtn").hide();
	                   		}else{
	                   			$(".chartBtn").show();
	                   		}
	                   },1000);
               	},//success结束
               	error: function(xhr, type){
               	}  
           	});//ajax结束
	}
</script>
