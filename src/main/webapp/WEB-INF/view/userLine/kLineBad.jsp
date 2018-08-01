<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/view/common/include_front.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>${name}${code}</title>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/line.css"></link>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/dropload.min.js"></script>
<script src="${ctx}/web/js/highstock.js"></script>
<style>
html{width: 100%;height:auto;}
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #eaeaea;height:auto; }
input{border:none;background: #fff;-webkit-appearance: none;}
input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
}
.hrline{height:4px;width:100%;background: #eaeaea;}
@media screen and (max-width:240px){
	.care img{padding:4px 0;width:4%;}
	.cenSize{font-size:0.9rem;}
	.progVal{font-size: 0.5rem;;}
	.discussLs{width: 70%;padding:0;}
	.soSmalSize{font-size: 0.4rem;}
	.progress{width: 55%;}
	.smallSize {font-size: 0.65rem;}
	.headRgt{font-size: 10px;}
	.careTit{font-size: 10px;}
	.margBom{margin-bottom:0;}
}
@media screen and (min-width:768px){
	.care img{padding:0 2px;width: 3%;}
	.soSmalSize{font-size: 1.3rem;}
}
</style>
</head>
<body>
	<div class="kline-wrapper content">
		<div class="headTit">
			<c:if test="${nowPri >= yestodEndPri && traNumber != 0}">
				<div class="headLft textCen"><span class="red bigSize fontWed">${nowPri}</span>
			    	<div class="headLftNum">
			    		<span class="red smallSize paddBot">+${nowPri - yestodEndPri}</span>
			    		<span class="red smallSize paddBot">+${change}%</span>
			    	</div>
			    </div>
		    </c:if>
			<c:if test="${nowPri < yestodEndPri && traNumber != 0}">
				<div class="headLft textCen"><span class="green bigSize fontWed">${nowPri}</span>
			    	<div class="headLftNum">
			    		<span class="green smallSize paddBot">${nowPri - yestodEndPri}</span>
			    		<span class="green smallSize paddBot">${change}%</span>
			    	</div>
			    </div>
		    </c:if>
		    <c:if test="${traNumber == 0}">
				<div class="headLft textCen"><span class="dieColor bigSize fontWed">${yestodEndPri}</span>
			    	<div class="headLftNum">
			    		<span class="dieColor smallSize paddBot">停牌</span>
			    	</div>
			    </div>
		    </c:if>
		    <div class="headRgt ">
		      <div class="todayLine paddBot">
		          <div class="textLft lineTit">今日 </div>
		          <div class="jindu">
		            <progress class="progress" value="${dayGoodRatio}" max="100">
			        </progress>
			        <span class="progVal">好评(${dayGoodRatio}%)</span>
		          </div>
		       </div>
		       <div class="weekLine">
		          <div class="textLft lineTit">本周</div>
			         <div class="jindu">
			            <progress class="progress" value="${weekGoodRatio}" max="100">
				        </progress>
				        <span class="progVal">好评(${weekGoodRatio}%)</span>
			          </div>
		       </div>
		    </div>
		    <div class="care">
			    <span class="careTit" >关注指数：</span>
			    <div class="starImg">
			    	<c:if test="${watch_num == 0}">
				    	<img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/>
				   	</c:if>
				   	<c:if test="${watch_num == 1}">
				    	<img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/>
				   	</c:if>
				   	<c:if test="${watch_num == 2}">
				    	<img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/>
				   	</c:if>
				   	<c:if test="${watch_num == 3}">
				    	<img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/starGra.png"/><img src="${ctx}/web/images_line/starGra.png"/>
				   	</c:if>
				   	<c:if test="${watch_num == 4}">
				    	<img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/starGra.png"/>
				   	</c:if>
				   	<c:if test="${watch_num == 5}">
				   		<img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/><img src="${ctx}/web/images_line/star.png"/>
				   	</c:if>
			    </div>
		    </div>
		</div>
		<div class="hrline clear"></div>
		<div class="shareLine">
			<table  class="list fontWed borBom borTop" cellpadding="0" cellspacing="0" width="100%"  >
				<tr>
			        <th class="textCen fontWed"><div><span class="zangColor">今开</span><br/>${todayStartPri}</div></th>
			        <th class="textCen fontWed"><div  class="borLft"><span class="zangColor">昨收</span><br/>${yestodEndPri}</div></th>
			        <th class="textCen fontWed"><div  class="borLft"><span class="zangColor">最高</span><br/>${todayMax}</div></th>
			        <th class="textCen fontWed"><div  class="borLft"><span class="zangColor">最低</span><br/>${todayMin}</div></th>
			    <tr>
			</table>
			<div class="hrline borBom"></div>
			<ul class="lineMenu">
			  <li class="lineActiv">分时</li>
			  <!-- <li class="lines">5日</li> -->
			  <li class="lines">日K</li>
			  <li class="lines">周K</li>
			  <li class="lines">月K</li>
			</ul>
			<div class="clear"></div>
			<div class="linLists">
				<!-- 分时 -->
				<div class="lineInfo">
					<div class="container" id="container">
						<img src="http://image.sinajs.cn/newchart/min/n/${code}.gif">
					</div>
					
				</div>
				<!-- 分时 end-->
				<!-- 5日 end-->
				<!-- 日K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="DaysK">
						<img src="http://image.sinajs.cn/newchart/daily/n/${code}.gif">
					</div>
				</div>
				<!-- 日K end-->
				<!-- 周K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="weekDays">
						<img src="http://image.sinajs.cn/newchart/weekly/n/${code}.gif">
					</div>
				</div>
				<!-- 周K end-->
				<!-- 月K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="monthDays">
						<img src="http://image.sinajs.cn/newchart/monthly/n/${code}.gif">
					</div>
				</div>
				<!-- 月K end-->
			</div>
			 <!-- <div class="linLists">
				分时
				<div class="lineInfo">
					<div class="container" id="container"></div>
				</div>
				分时 end
				5日
				<div class="lineInfo" style="display: none;">
					<div class="container" id="fiveDays"></div>
				</div>
				5日 end
				日K
				<div class="lineInfo" style="display: none;">
					<div class="container" id="DaysK"></div>
				</div>
				日K end
				周K
				<div class="lineInfo" style="display: none;">
					<div class="container" id="weekDays"></div>
				</div>
				周K end
				月K
				<div class="lineInfo" style="display: none;">
					<div class="container" id="monthDays"></div>
				</div>
				月K end
			</div>  -->
		</div>
		<!-- 评价和简介 -->
		<div class="discussAll borTop clear">
			<ul class="discussMenu textCen">
				<li class="disAct">累计评论<!-- <i class="arrow-upAct"></i> --></li>	
			</ul>
			<div class="hrline clear"></div>
			<c:if test="${empty goodCommentList && empty middleCommentList && empty badCommentList}">
		  	    <div class="noBody">
					<a><img src="${ctx}/web/images_line/ganta.png"/></a>
					<span>暂无评论，快来评价吧~</span>
				</div>
		  	</c:if>
			<c:if test="${!empty goodCommentList || !empty middleCommentList || !empty badCommentList}">
		  	    <div class="allContent" >
				<!-- 评价内容 -->
				<div class="allBody">
					<ul class="discussList textCen" id="navNor">
						<li class=" discussLi  textCen disLiss"><div id="smil" class="discussLs" onclick="kLine('${id}')"><span class="plBody"><i class="smil"></i>好评</span><br/>${goodCount}</div></li>
						<li class="discussLi textCen disLiss"><div id="cry" class="discussLs" onclick="kLineMid('${id}')"><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
						<li class="discussLi textCen disListAct"><div id="bigCry" class="discussLs" ><span class="plBody"><i class="bigCryAct"></i>差评</span><br/>${badCount}</div></li>
					</ul>
					<ul class="discussListA textCen"  id="navFixed" style="display:none;">
						<li class="discussLi textCen borRgt disLiss"><div id="smilA" onclick="kLine('${id}')"><span class="plBody"><i class="smil"></i>好评</span><br/>${goodCount}</div></li>
						<li class="discussLi textCen borRgt disLiss"><div id="cryA" onclick="kLineMid('${id}')"><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
						<li class="discussLi textCen borRgt disListAct"><div id="bigCryA" ><span class="plBody"><i class="bigCryAct"></i>差评</span><br/>${badCount}</div></li>
					</ul>
					<div class="hrline clear" ></div>
					<div class="disContent">
						<!-- 差评 -->
						<div class="disBody" id="badBody">
						<c:if test="${empty badCommentList}">
					  	    <div class="noBody" id="noBod">
								<a><img src="${ctx}/web/images_line/ganta.png"/></a>
								<span>暂无差评，快来评价吧~</span>
							</div>
					  	</c:if>
					  	<c:if test="${!empty badCommentList}">
					  	<div class="disGall">	
							<div class="disGpdLis" id="badDis">
								<!-- 每条差评 -->
								<div class="goodList" >
						    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
								      <c:forEach items="${badCommentList}" var="comment" varStatus="row">
									      <tr> 
									          <td>
									              <div class="gLis userName">
										          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
										          	<div class="gLisName yeloColor">${comment.name}</div>
										          	<div class="date lisCol"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
										          </div>
										          <div class="clear"></div>
										          <div class="gLis teacherName" onclick="reviewInfo('${comment.id}')">
										          	<div class="gLisInfo">${comment.content}</a></div>
										          </div>	
					                    	  </td>
										  </tr>
										  <tr>
										 	<td>
										 		 <div class="praise">
										 		 	<div class="cid" style="display: none;">${comment.id}</div>
											        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.likeNum}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
											        <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLikeNum}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
											        <div class="origin lisCol">来自：新浪微博</div>
											     </div>
										 	</td>
										  </tr>
										  <tr>
										  	<td><div class="hrline clear marg borTop"></div></td>
										  </tr>
									  </c:forEach>
								    </table>
								</div>
								<!-- 每条差评 结束-->
							</div>
						</div>
						</c:if> 
						</div>
						<!-- 差评 结束-->
					</div>
				</div>
				<!-- 评价内容  结束-->
		  	</c:if>
			</div>
		</div>
	</div>
</body>
</html>

<script>
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?577cde65ff9c5c260eeca3db486575c2";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();

window.onload=function(){
//滚动后小菜单在顶部
//评价
    var navNor=document.getElementById('navNor');
	var navFixed=document.getElementById('navFixed');
	var fenge=document.getElementById('fenge');
	var introMen=document.getElementById('introMen');
	var introFixed=document.getElementById('introFixed');
	var feng=document.getElementById('feng');
window.onscroll=function(){
	var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
		if(scrollTop>navNor.offsetTop){
			navFixed.style.display='block';
		}else if(scrollTop<=navNor.offsetTop){
			navFixed.style.display='none';
		}
	}
};
</script>

<script>
//判断内容，超过用...表示
$(".gLisInfo").each(function(){
	var obString=$(this).text();
	var length=$(this).text().length;
	var sl=$(this).text().substring(0,90)+"...";
	if(length>90){
	   		$(this).text(sl);
	   }else{
	   		$(this).text(obString);
	   }; 
});
   
   //跳到详情页
    function reviewInfo(id)  
    {  
        //window.location = "${ctx}/web/stock/reviewInfo?id="+id;  
    }  ;
    
    function newsInfo()  
    {  
        window.location = "${ctx}/web/stock/newsInfo"  
    }  ; 
     function kLine(id)  
    {  
         window.location = "${ctx}/web/stock/kLine?id="+id+"&type=0";  
    }  ;
    function kLineMid(id) {
   		  window.location = "${ctx}/web/stock/kLine?id="+id+"&type=1";  
    }

$(function(){
		$(".discussListA").hide();
   
 //名字中间用**
   $(".gLisName").each(function(){
   var userName=$(this).text();
   if(userName.length > 2) {
			var subStr = userName.substring(1, userName.length-1);
			//alert(subStr);
			var reStr = userName.replace(subStr, "****");
			$(this).text(reStr);
		} else if (userName.length == 2) {
			var subStr = userName.substring(0, 1);
			// 张
			var reStr = userName.replace(subStr, subStr+"****");
			$(this).text(reStr);	
		} else {
			var reStr = userName.replace(userName, userName+"****"+userName);
			$(this).text(reStr);

		}
   });  
   //评论点赞(只能点一次)
  
    $(".upZan").each(function(){
	   $(".upZan").click(function(){
		   var val=$(this).parent().find(".upPraiIn");
		   var donval=$(this).parent().next(".downPrai").find(".downPraiIn");
		   var cid=$(this).parent().prev().html();
		   		if(val.val()==0 && donval.val()==0){
			   		var upNum=$(this).next(".upZanNum").text();
			   		$(this).attr("class","uzanAct");
			   		$(this).next(".upZanNum").css("color","#ef412c");
			   		$(this).next(".upZanNum").text(parseInt(upNum)+1);
			   		val.val(1);
			   		var likeNum = parseInt(upNum)+1;
			   		$.post("${ctx}/web/stock/saveStockLikeNum?id="+cid+"&likeNum="+likeNum,"",function(d){
						
					});
		   		}
		   });
	   });
   
    $(".downZan").each(function(){
	   $(".downZan").click(function(){
	  	   var dval=$(this).parent().find(".downPraiIn");
		   var upval=$(this).parent().prev().find(".upPraiIn");
		   var cid=$(this).parent().prev().prev().html();
	   		if(upval.val()==0 && dval.val()==0){
   			var downNum=$(this).next(".downZanNum").text();
	   		$(this).attr("class","dzanAct");	
	   		$(this).next(".downZanNum").css("color","#ef412c");
	   		$(this).next(".downZanNum").text(parseInt(downNum)+1);
	   		dval.val(1);
	   		var noLikeNum = parseInt(downNum)+1;
	   		$.post("${ctx}/web/stock/saveStockNoLikeNum?id="+cid+"&noLikeNum="+noLikeNum,"",function(d){
				
			});
	   		
	   		}
	   });
   }); 
   //tab标签切换
   		//k线图
   		var $liK = $('.lineMenu li');
		var $ulK = $('.linLists .lineInfo');
   		$liK.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$liK.removeClass();
				$this.attr("class","lineActiv");
				$ulK.css('display','none');
				$ulK.eq($t).css('display','block');
	});
});
</script>

<script>
$(function(){
    var counter = 1;
    // 每页展示5个
    var num = 5;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $('.content').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh" >↓下拉刷新</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load" ><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">暂无更多评论</div>'
        },
        loadUpFn : function(me){
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
        },
        loadDownFn : function(me){
            $.ajax({
                type: 'GET',
                url: '${ctx}/web/stock/stockCommentList',
                data:{id:${id}, type:2},
                dataType: 'json',
                success: function(data){
                	var lengt=data.lists.length;
                	if(lengt==0){
                		$("#noBod").show();
                		$(".dropload-down").hide();
                	}else if(length<=5){
			        	$(".dropload-down").hide();
			        }else{
                		$(".dropload-load").show();
                		var result = '';
                        counter++;
                        pageEnd = num * counter;
                        pageStart = pageEnd - num;

                        for(var i = pageStart; i < pageEnd; i++){
                         var arrText = [];
    			          arrText.push("<div class='goodList' >");
    			          arrText.push("<table class='table' cellpadding='0' cellspacing='0' width='100%' border='0'>");
    			          arrText.push("<div class='gLis userName'><img class='headImg' src='${ctx}/web/images_line/lineIco.png'/><div class='gLisName yeloColor'>"+data.lists[i].name+"</div>");
    			          arrText.push("<div class='date lisCol'>"+data.lists[i].createTime+"</div></div><div class='clear'></div>");
    			          arrText.push("<div class='gLis teacherName' onclick='reviewInfo( "+data.lists[i].id+")'><div class='gLisInfo'>"+data.lists[i].content+"</div></div></td></tr><td>");
    			         arrText.push("<div class='praise'><div class='cid' style='display: none;'>"+data.lists[i].id+"</div>");
    			         arrText.push("<div class='upPrai colorGray'><i class='upZan'></i><span class='upZanNum'>"+data.lists[i].likeNum+"</span><input name='upPrai' class='upPraiIn' value='0' type='hidden'/></div>");
    			         arrText.push("<div class='downPrai colorGray'><i class='downZan'></i><span class='downZanNum'>"+data.lists[i].noLikeNum+"</span><input name='downPrai' class='downPraiIn' value='0' type='hidden'/></div>")
    			           arrText.push("<div class='origin lisCol'>来自：新浪微博</div></div></td></tr> <tr><td><div class='hrline clear marg borTop'></div></td></tr></table></div>");
                            result +=  arrText.join('');
                            if((i + 1) >= data.lists.length){
                                // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                                break;
                            }
                        }
                        // 为了测试，延迟1秒加载
                        setTimeout(function(){
                            $('#badDis').append(result);
                              //名字中间用**
    						  /*  $(".gLisName").each(function(){
    						   var userName=$(this).text();
    						   if(userName.length > 2) {
    									var subStr = userName.substring(1, userName.length-1);
    									//alert(subStr);
    									var reStr = userName.replace(subStr, "****");
    									$(this).text(reStr);
    								} else if (userName.length == 2) {
    									var subStr = userName.substring(0, 1);
    									// 张
    									var reStr = userName.replace(subStr, subStr+"****");
    									$(this).text(reStr);	
    								} else {
    									var reStr = userName.replace(userName, userName+"****"+userName);
    									$(this).text(reStr);
    						
    								}
    						   });  */
                            // 每次数据加载完，必须重置
                            me.resetload();
                        },1000);
                       };//if end
                    },
                    error: function(xhr, type){
                        alert('Ajax error!');
                        // 即使加载出错，也得重置
                        me.resetload();
                   }  
            });//ajax结束
        },//上拉结束
        threshold : 50
    });
    
});
</script>
<!-- <script>
  //修改colum条的颜色（重写了源码方法）  
    var originalDrawPoints = Highcharts.seriesTypes.column.prototype.drawPoints;  
        Highcharts.seriesTypes.column.prototype.drawPoints = function () {  
            var merge  = Highcharts.merge,  
                series = this,  
                chart  = this.chart,  
                points = series.points,  
                i      = points.length;  
              
            while (i--) {  
                var candlePoint = chart.series[0].points[i];  
                if(candlePoint.open != undefined && candlePoint.close !=  undefined){  //如果是K线图 改变矩形条颜色，否则不变  
                    var color = (candlePoint.open < candlePoint.close) ? '#DD2200' : '#33AA11';  
                    var seriesPointAttr = merge(series.pointAttr);  
                    seriesPointAttr[''].fill = color;  
                    seriesPointAttr.hover.fill = Highcharts.Color(color).brighten(0.3).get();  
                    seriesPointAttr.select.fill = color;  
                }else{  
                    var seriesPointAttr = merge(series.pointAttr);  
                }  
                  
                points[i].pointAttr = seriesPointAttr;  
            }  
      
            originalDrawPoints.call(this);  
        }  
//分时
$(function () {
 $.getJSON('http://www.hcharts.cn/datas/jsonp.php?filename=usdeur.json&callback=?', function (data) {

        //分时
         $('#container').highcharts('StockChart', {


            rangeSelector : {
                enabled: false
            },

            title : {
                text : ''
            },
			scrollbar:{
            	enabled: false
            }, 
            credits:{
            	enabled: false
            },
            yAxis : {
                title : {
                    text : ''
                },
                plotLines : [{
                    value : 0.6738,
                    color : 'green',
                    dashStyle : 'shortdash',
                    width : 2,
                    label : {
                        text : 'Last quarter minimum'
                    }
                }, {
                    value : 0.7419,
                    color : 'red',
                    dashStyle : 'shortdash',
                    width : 2,
                    label : {
                        text : 'Last quarter maximum'
                    }
                }]
            },

            series : [{
                name : 'USD to EUR',
                data : data,
                tooltip : {
                    valueDecimals : 4
                }
            }]
        }); 
   

    //5日
   /*  $('#fiveDays').highcharts('StockChart', {


            rangeSelector : {
                enabled: false
            },

            title : {
                text : ''
            },
			scrollbar:{
            	enabled: false
            }, 
            credits:{
            	enabled: false
            },
            yAxis : {
                title : {
                    text : ''
                },
                plotLines : [{
                    value : 0.6738,
                    color : 'green',
                    dashStyle : 'shortdash',
                    width : 2,
                    label : {
                        text : 'Last quarter minimum'
                    }
                }, {
                    value : 0.7419,
                    color : 'red',
                    dashStyle : 'shortdash',
                    width : 2,
                    label : {
                        text : 'Last quarter maximum'
                    }
                }]
            },

            series : [{
                name : 'USD to EUR',
                data : data,
                tooltip : {
                    valueDecimals : 4
                }
            }]
        }); */
    });
});
</script>
<script>
//日k,周k，月k
 $(function () {
    $.getJSON('http://www.hcharts.cn/datas/jsonp.php?filename=aapl-ohlcv.json&callback=?', function (data) {

        // split the data set into ohlc and volume
        var ohlc = [],
            volume = [],
            dataLength = data.length,
            // set the allowed units for data grouping
            groupingUnits = [[
                'week',                         // unit name
                [1]                             // allowed multiples
            ], [
                'month',
                [1, 2, 3, 4, 6]
            ]],

            i = 0;

        for (i; i < dataLength; i += 1) {
            ohlc.push([
                data[i][0], // the date
                data[i][1], // open
                data[i][2], // high
                data[i][3], // low
                data[i][4] // close
            ]);

            volume.push([
                data[i][0], // the date
                data[i][5] // the volume
            ]);
        }

 //日k
     $('#DaysK').highcharts('StockChart', {

            rangeSelector: {
               enabled: false
            },

            title: {
                text: ''
            },
			scrollbar:{
            	enabled: false
            }, 
            navigator:{
            	enabled: false
            }, 
            credits:{
            	enabled: false
            },
             plotOptions:{
            	candlestick:{
                	color:'#33AA11',
                    upColor:'#DD2200',
 					lineColor:'#33AA11',                 
                	upLineColor: '#DD2200', 
                    maker:{  
                    states:{  
                        hover:{  
                            enabled:false,  
                        }  
                    }  
                }  
                },
            },
            yAxis: [{
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: ''
                },
                height: '60%',
                lineWidth: 2
            }, {
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: '成交量'
                },
                top: '65%',
                height: '35%',
                offset: 0,
                lineWidth: 2
            }],

            series: [{
                type: 'candlestick',
                name: 'AAPL',
                data: ohlc,
                dataGrouping: {
                    units: groupingUnits
                }
            }, {
                type: 'column',
                name: 'Volume',
                data: volume,
                yAxis: 1,
                dataGrouping: {
                    units: groupingUnits
                }
            }]
        });
    
    //周k
     $('#weekDays').highcharts('StockChart', {

            rangeSelector: {
               enabled: false
            },

            title: {
                text: ''
            },
			scrollbar:{
            	enabled: false
            }, 
            navigator:{
            	enabled: false
            }, 
            credits:{
            	enabled: false
            },
             plotOptions:{
            	candlestick:{
                	color:'#33AA11',
                    upColor:'#DD2200',
 					lineColor:'#33AA11',                 
               	    upLineColor: '#DD2200', 
                    maker:{  
                    states:{  
                        hover:{  
                            enabled:false,  
                        }  
                    }  
                }  
                },
            },
            yAxis: [{
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: ''
                },
                height: '60%',
                lineWidth: 2
            }, {
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: '成交量'
                },
                top: '65%',
                height: '35%',
                offset: 0,
                lineWidth: 2
            }],

            series: [{
                type: 'candlestick',
                name: 'AAPL',
                data: ohlc,
                dataGrouping: {
                    units: groupingUnits
                }
            }, {
                type: 'column',
                name: 'Volume',
                data: volume,
                yAxis: 1,
                dataGrouping: {
                    units: groupingUnits
                }
            }]
        });
    
    //月k
     $('#monthDays').highcharts('StockChart', {

            rangeSelector: {
               enabled: false
            },

            title: {
                text: ''
            },
			scrollbar:{
            	enabled: false
            }, 
            navigator:{
            	enabled: false
            }, 
            credits:{
            	enabled: false
            },
             plotOptions:{
            	candlestick:{
                	color:'#33AA11',
                    upColor:'#DD2200',
  				    lineColor:'#33AA11',                 
                	upLineColor: '#DD2200', 
                    maker:{  
                    states:{  
                        hover:{  
                            enabled:false,  
                        }  
                    }  
                }  
                },
            },
            yAxis: [{
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: ''
                },
                height: '60%',
                lineWidth: 2
            }, {
                labels: {
                    align: 'right',
                    x: -3
                },
                title: {
                    text: '成交量'
                },
                top: '65%',
                height: '35%',
                offset: 0,
                lineWidth: 2
            }],

            series: [{
                type: 'candlestick',
                name: 'AAPL',
                data: ohlc,
                dataGrouping: {
                    units: groupingUnits
                }
            }, {
                type: 'column',
                name: 'Volume',
                data: volume,
                yAxis: 1,
                dataGrouping: {
                    units: groupingUnits
                }
            }]
        });
     });
});
</script> -->