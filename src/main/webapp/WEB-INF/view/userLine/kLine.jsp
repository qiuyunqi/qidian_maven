<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/view/common/include_front.jsp" %>
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
<title>${name}${code}</title>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/line.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/web/css/jquery-ui.css"></link>
<style>
html{width: 100%;height:auto;}
body {font: 13px Tahoma;margin: 0px auto;width:100%;background: #eaeaea;height:auto; }
input{border:none;background: #fff;-webkit-appearance: none;}
input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
}
.hrline{height:4px;width:100%;background: #eaeaea;}
.ui-autocomplete {max-height: 100px;overflow-y: auto;overflow-x: hidden;}
* html .ui-autocomplete {height: 100px;}
.ui-autocomplete-category {font-weight: bold;padding: .2em .4em;margin: .8em 0 .2em;line-height: 1.5;}
.fgt{float:right;}
.ui-menu .ui-menu-item a{height: 41px;line-height: 41px;}
.ui-menu .ui-menu-item a:hover{background: #dadada;}
input::-webkit-input-placeholder{
color: #fff;
}
input:-moz-placeholder {
color: #fff;
}
input::-moz-placeholder {
color: #fff;
}
input:-ms-input-placeholder{
color: #fff;
} 
@media screen and (max-width:240px){
	.cenSize{font-size:0.9rem;}
	.progVal{font-size: 0.5rem;;}
	.discussLs{width: 70%;padding:0;}
	.soSmalSize{font-size: 0.4rem;}
	.progress{width: 55%;}
	.smallSize {font-size: 0.65rem;}
	.headRgt{font-size: 10px;}
	.careTit{font-size: 10px;}
	.margBom{margin-bottom:0;}
	.fourSize{font-size: 9px;}
	.plBody i {height: 11px;width: 10px;}
	.plBody{font-size: 9px;}
	.fabuBody input[type="submit"]{width:50px;}
	.disBd{width: 65%;}
	.lefgin{padding:0}
}
@media screen and (min-width:768px){
	.care img{padding:0 2px;width: 3%;}
	.soSmalSize{font-size: 1.3rem;}
}
</style>
</head>
<body>
	<div class="kline-wrapper content">
		<div class="searchKline">
	         <input id="stockCode" class="sercInputKil bsiz"  type="search" list="mydata" placeholder="股票搜索" value="${code}" autocomplete="off">
	         <div class="sercImgKil" onclick="serachEvent()"></div>
	    </div>
		<div class="headTit" id="headTit">
			<%-- <c:if test="${nowPri >= yestodEndPri && traNumber != 0}">
				<div class="headLft textCen"><span class="red bigSize fontWed">${nowPri}</span>
			    	<div class="headLftNum">
			    		<span class="red smallSize paddBot"></span>
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
		    </c:if> --%>
		</div>
		<div class="hrline clear"></div>
		<div class="shareLine">
			<table  class="list fontWed borBom borTop" cellpadding="0" cellspacing="0" width="100%"  >
				<tr>
			        <th class="textCen fontWed"><div><span class="zangColor" id="todayStartPri">今开</span></div></th>
			        <th class="textCen fontWed"><div><span class="zangColor" id="yestodEndPri">昨收</span></div></th>
			        <th class="textCen fontWed"><div><span class="zangColor" id="todayMax">最高</span></div></th>
			        <th class="textCen fontWed"><div><span class="zangColor" id="todayMin">最低</span></div></th>
			    <tr>
			</table>
			<div class="hrline borBom"></div>
			<ul class="lineMenu">
			  <li class="lineActiv">分时<i class="jtActiv"></i></li>
			  <!-- <li class="lines">5日<i ></i></li> -->
			  <li class="lines">日K<i></i></li>
			  <li class="lines">周K<i></i></li>
			  <li class="lines">月K<i></i></li>
			</ul>
			<div class="clear"></div>
			 <div class="linLists">
				<!-- 分时 -->
				<div class="lineInfo">
					<div class="container" id="container">
						<%-- <img src="http://image.sinajs.cn/newchart/min/n/${code}.gif"> --%>
					</div>
				</div>
				<!-- 分时 end-->
				<!-- 5日 -->
				<!-- <div class="lineInfo" style="display: none;">
					<div class="container" id="fiveDays"></div>
				</div> -->
				<!-- 5日 end-->
				<!-- 日K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="DaysK">
						<%-- <img src="http://image.sinajs.cn/newchart/daily/n/${code}.gif"> --%>
					</div>
				</div>
				<!-- 日K end-->
				<!-- 周K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="weekDays">
						<%-- <img src="http://image.sinajs.cn/newchart/weekly/n/${code}.gif"> --%>
					</div>
				</div>
				<!-- 周K end-->
				<!-- 月K -->
				<div class="lineInfo" style="display: none;">
					<div class="container" id="monthDays">
						<%-- <img src="http://image.sinajs.cn/newchart/monthly/n/${code}.gif"> --%>
					</div>
				</div>
				<!-- 月K end-->
			</div> 
		</div>
		<div class="hrline borBom"></div>
		<!-- 评价和简介 -->
		<div class="discussAll borTop clear">
			<ul class="discussList textCen disLis"  id="navNor">
				<li class="disAct zangColor fourSize disListAct">累计评论（${totalCount}）</li>
				<li class=" discussLi  textCen disLiss"><div id="smil" class="discussLs"><span class="plBody"><i class="smil"></i>好评</span><br/>${goodCount}</div></li>
				<li class="discussLi textCen disLiss"><div id="cry" class="discussLs"><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
				<li class="discussLi textCen disLiss"><div id="bigCry" class="discussLs"><span class="plBody"><i class="bigCry"></i>差评</span><br/>${badCount}</div></li>
			</ul>
			<div class="hidBody" id="navFixed" style="display:none;">
				<ul class="discussListA textCen disLisA"  >
					<li class=" disListAct disActA zangColor fourSize">累计评论（${totalCount}）</li>
					<li class=" discussLi textCen disLiss"><div id="smilA"><span class="plBody"><i class="smil"></i>好评</span><br/>${goodCount}</div></li>
					<li class="discussLi textCen disLiss"><div id="cryA" ><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
					<li class="discussLi textCen disLiss"><div id="bigCryA" ><span class="plBody"><i class="bigCry"></i>差评</span><br/>${badCount}</div></li>
				</ul>
			</div>
			<!-- 为了测试，先注释 -->
			<%-- <c:if test="${empty goodCommentList && empty middleCommentList && empty badCommentList}">
		  	    <div class="noBody">
					<a><img src="${ctx}/web/images_line/ganta.png"/></a>
					<span>暂无评论，快来评价吧~</span>
				</div>
		  	</c:if>
			<c:if test="${!empty goodCommentList || !empty middleCommentList || !empty badCommentList}"> --%>
		  	    <div class="allContent" >
				<!-- 评价内容 -->
				<div class="allBody">
					<div class="hrline clear" ></div>
					<div class="disContent">
						<!-- 所有评论begin -->
						<div class="disBody actIon" id="allBody" >
							<c:if test="${empty allCommentList}">
						  	    <div class="noBody" id="noBod">
									<a><img src="${ctx}/web/images_line/ganta.png"/></a>
									<span>暂无评价，快来评价吧~</span>
								</div>
						  	</c:if>
						  	<c:if test="${!empty allCommentList}">
						  	    <div class="disGall">
						  	    <div class="disGpdLis" id="allDis" >
								<!-- 每条评论 -->
									<div class="goodList" >
							    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
											   <c:forEach items="${allCommentList}" var="comment" varStatus="row">
											   	   <!-- 单条微博评论开始 -->
											   	   <c:if test="${comment.commentType != 1}">
											   	       	<tr> 
												          <td>
												              <div class="gLis userName">
													          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
													          	<div class="gLisName yeloColor">${comment.name}</div>
													          	<div class="date lisCol">${comment.createTime}</div>
													          </div>
													          <div class="clear"></div>
													          <div class="gLis teacherName">
													          	<div class="gLisInfo">${comment.content}</div>
													          </div>
													          <div class="praise">
													 		 	<div class="lefgin lisCol">${comment.typeStr}</div>
													 		 	<div class="cid" style="display: none;">${comment.id}</div>
													 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
													 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
														        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
														      </div>
													      </td>
											   			</tr>
											   	  </c:if>
											   	  <!-- 单条微博评论结束 -->
											   	  <!-- 单条用户评论开始 -->
											      <c:if test="${comment.commentType == 1 && empty comment.parents}">
											          <tr> 
												          <td>
												          	<table  class="replyOne" cellpadding="0" cellspacing="0"  border="0">
												          		<tr>
												          			<td>
												          				<div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          </div>
																           <div class="clear"></div>
																          <div class="gLis teacherName">
																          	<div class="gLisInfoRelpy">${comment.content}</div>
																          </div>	
																          <div class="praise">
																 		 	<div class="cid" style="display: none;">${comment.id}</div>
																 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																	        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																	     </div>
												          			</td>
												          		</tr>
												          	</table>
								                    	  </td>
												  	</tr>	
											      </c:if>
											      <!-- 单条用户评论结束 -->
											      <!-- 回复评论开始  -->
										          <c:if test="${comment.commentType == 1 && !empty comment.parents}">
										             	<tr> 
													          <td>
													          	<table  class="replyMore" cellpadding="0" cellspacing="0"  border="0" width="100%">
													          		<tr>
													          			<td>
															              <div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          	<div class="date lisCol">${comment.createTime}</div>
																          </div>
													          			</td>
													          		</tr>
														          		<tr>
														          			<td>
														          				<div class="replyFloors">
																          			<table class="tableFloor" cellpadding="0" cellspacing="0"  border="0">
																		          		<c:forEach items="${comment.parents}" var="parent" varStatus="row">
																					      	<tr> 
																					          <td>
																					              <div class="gLis userName">
																						          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																						          	<div class="gLisName yeloColor">${parent.name}</div>
																						          </div>
																						           <div class="clear"></div>
																						          <div class="gLis teacherName">
																						          	<div class="gLisInfoRelpy">${parent.content}</div>
																						          </div>	
																						          <div class="praise">
																						 		 	<c:if test="${parent.commentType != 1}"><div class="lefgin lisCol">${parent.typeStr}</div></c:if>
																						 		 	<div class="cid" style="display: none;">${parent.id}</div>
																						 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																						 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${parent.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																							        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${parent.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							     </div>
																						      </td>
																						   </tr>
																		          		</c:forEach>
																					</table>
																          		</div> 
														          			</td>
														          		</tr>
													          		<tr>
													          			<td>
													          				 <div class="clear"></div>
																	          <div class="gLis teacherName">
																	          	<div class="gLisInfo">${comment.content}</div>
																	          </div>	
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
													          				<div class="praise">
																	 		 	<div class="cid" style="display: none;">${comment.id}</div>
																	 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																	 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																		        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																		     </div>
													          			</td>
													          		</tr>
													          	</table>
									                    	  </td>
														  </tr>	
										   		  </c:if>
											      <!-- 回复评论结束  --> 
												</c:forEach>
									    </table>
									</div>
								</div>
							</div>
					  		</c:if>
					    </div> 
						<!-- 所有评论end -->
						<!-- 好评 -->
						<div class="disBody " id="goodBody" style="display:none;" >
						  	<c:if test="${empty goodCommentList}">
						  	    <div class="noBody" id="noGood">
									<a><img src="${ctx}/web/images_line/ganta.png"/></a>
									<span>暂无好评，快来评价吧~</span>
								</div>
						  	</c:if>
						  	<c:if test="${!empty goodCommentList}">
						  	    <div class="disGall">
						  	    <div class="disGpdLis" id="goodDis">
								<!-- 每条评论 -->
									<div class="goodList" >
							    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
											   <c:forEach items="${goodCommentList}" var="comment" varStatus="row">
											   	   <!-- 单条微博评论开始 -->
											   	   <c:if test="${comment.commentType != 1}">
											   	       	<tr> 
												          <td>
												              <div class="gLis userName">
													          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
													          	<div class="gLisName yeloColor">${comment.name}</div>
													          	<div class="date lisCol">${comment.createTime}</div>
													          </div>
													          <div class="clear"></div>
													          <div class="gLis teacherName">
													          	<div class="gLisInfo">${comment.content}</div>
													          </div>
													          <div class="praise">
													 		 	<div class="lefgin lisCol">${comment.typeStr}</div>
													 		 	<div class="cid" style="display: none;">${comment.id}</div>
													 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
													 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
														        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
														      </div>
													      </td>
											   			</tr>
											   	  </c:if>
											   	  <!-- 单条微博评论结束 -->
											   	  <!-- 单条用户评论开始 -->
											      <c:if test="${comment.commentType == 1 && empty comment.parents}">
											          <tr> 
												          <td>
												          	<table  class="replyOne" cellpadding="0" cellspacing="0"  border="0">
												          		<tr>
												          			<td>
												          				<div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          </div>
																           <div class="clear"></div>
																          <div class="gLis teacherName">
																          	<div class="gLisInfoRelpy">${comment.content}</div>
																          </div>	
																          <div class="praise">
																 		 	<div class="cid" style="display: none;">${comment.id}</div>
																 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																	        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																	     </div>
												          			</td>
												          		</tr>
												          	</table>
								                    	  </td>
												  	</tr>	
											      </c:if>
											      <!-- 单条用户评论结束 -->
											      <!-- 回复评论开始  -->
										          <c:if test="${comment.commentType == 1 && !empty comment.parents}">
										             	<tr> 
													          <td>
													          	<table  class="replyMore" cellpadding="0" cellspacing="0"  border="0" width="100%">
													          		<tr>
													          			<td>
															              <div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          	<div class="date lisCol">${comment.createTime}</div>
																          </div>
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
														          			<!-- 新样式begin -->
														          			<%-- <div class="replyFloors">
															          			<!-- 回复最里层即回复微博 -->
															          			<div class="commentBox">
															          				<!-- 回复最里层即回复微博 -->
															          					<div class="commentBox">
																          					<!-- 最里边一层，即新浪微博 -->
																          					<div class="commentBox">
																          						<div class="gLis userName">
																								     <img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																								     <div class="gLisName yeloColor">hahah</div>
																								</div>
																								<div class="clear"></div>
																								<div class="gLis teacherName">
																								      <div class="gLisInfoRelpy">抵制美货抵制日货1</div>
																								</div>	
																								<div class="praise">
																									 <c:if test="${parent.commentType == 0}"><div class="lefgin lisCol">来自：新浪微博</div></c:if>
																									 <div class="cid" style="display: none;">1</div>
																									 <div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																									 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">0</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																									<div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">0</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																								 </div>
																          					</div>
																          					<!-- 最里一层end -->
																          					<div class="gLis userName">
																								  <img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																								 <div class="gLisName yeloColor">hahah</div>
																							</div>
																							<div class="clear"></div>
																							<div class="gLis teacherName">
																								 <div class="gLisInfoRelpy">抵制美货抵制日货2</div>
																							</div>	
																							<div class="praise">
																								<c:if test="${parent.commentType == 0}"><div class="lefgin lisCol">来自：新浪微博</div></c:if>
																								<div class="cid" style="display: none;">1</div>
																								<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																								<div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">0</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																								<div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">0</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							</div>
																          				</div>	
															          				<!--回复 最里一层end -->
															          						<div class="gLis userName">
																								<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																								<div class="gLisName yeloColor">hahah</div>
																							</div>
																							<div class="clear"></div>
																							<div class="gLis teacherName">
																								  <div class="gLisInfoRelpy">抵制美货抵制日货3</div>
																							</div>	
																							<div class="praise">
																								<c:if test="${parent.commentType == 0}"><div class="lefgin lisCol">来自：新浪微博</div></c:if>
																								<div class="cid" style="display: none;">1</div>
																								<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																								<div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">0</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																								<div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">0</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							</div>
															          				</div>
															          				<!--回复回复end -->
														          			</div> --%>
														          			<!-- 新样式结束 -->
														          			<div class="replyFloors">
																          			<table class="tableFloor" cellpadding="0" cellspacing="0"  border="0">
																		          		<c:forEach items="${comment.parents}" var="parent" varStatus="row">
																					      	<tr> 
																					          <td>
																					              <div class="gLis userName">
																						          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																						          	<div class="gLisName yeloColor">${parent.name}</div>
																						          </div>
																						           <div class="clear"></div>
																						          <div class="gLis teacherName">
																						          	<div class="gLisInfoRelpy">${parent.content}</div>
																						          </div>	
																						          <div class="praise">
																						 		 	<c:if test="${parent.commentType != 1}"><div class="lefgin lisCol">${parent.typeStr}</div></c:if>
																						 		 	<div class="cid" style="display: none;">${parent.id}</div>
																						 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																						 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${parent.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																							        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${parent.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							     </div>
																						      </td>
																						   </tr>
																		          		</c:forEach>
																					</table>
																          	</div>
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
													          				 <div class="clear"></div>
																	          <div class="gLis teacherName">
																	          	<div class="gLisInfo">${comment.content}</div>
																	          </div>	
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
													          				<div class="praise">
																	 		 	<div class="cid" style="display: none;">${comment.id}</div>
																	 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																	 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																		        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																		     </div>
													          			</td>
													          		</tr>
													          	</table>
									                    	  </td>
														  </tr>	
										   		  </c:if>
											      <!-- 回复评论结束  --> 
												</c:forEach>
									    </table>
									</div>
								</div>
							</div>
					  		</c:if>
						</div>
						<!-- 好评 结束-->
						<!-- 中评 -->
						<div class="disBody" id="midBody" style="display:none;">
							<c:if test="${empty middleCommentList}">
						  	    <div class="noBody" id="noMid">
									<a><img src="${ctx}/web/images_line/ganta.png"/></a>
									<span>暂无好评，快来评价吧~</span>
								</div>
						  	</c:if>
						  	<c:if test="${!empty middleCommentList}">
						  	    <div class="disGall">
						  	    <div class="disGpdLis" id="midDis">
								<!-- 每条评论 -->
									<div class="goodList" >
							    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
											   <c:forEach items="${middleCommentList}" var="comment" varStatus="row">
											   	   <!-- 单条微博评论开始 -->
											   	   <c:if test="${comment.commentType != 1}">
											   	       	<tr> 
												          <td>
												              <div class="gLis userName">
													          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
													          	<div class="gLisName yeloColor">${comment.name}</div>
													          	<div class="date lisCol">${comment.createTime}</div>
													          </div>
													          <div class="clear"></div>
													          <div class="gLis teacherName">
													          	<div class="gLisInfo">${comment.content}</div>
													          </div>
													          <div class="praise">
													 		 	<div class="lefgin lisCol">${comment.typeStr}</div>
													 		 	<div class="cid" style="display: none;">${comment.id}</div>
													 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
													 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
														        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
														      </div>
													      </td>
											   			</tr>
											   	  </c:if>
											   	  <!-- 单条微博评论结束 -->
											   	  <!-- 单条用户评论开始 -->
											      <c:if test="${comment.commentType == 1 && empty comment.parents}">
											          <tr> 
												          <td>
												          	<table  class="replyOne" cellpadding="0" cellspacing="0"  border="0">
												          		<tr>
												          			<td>
												          				<div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          </div>
																           <div class="clear"></div>
																          <div class="gLis teacherName">
																          	<div class="gLisInfoRelpy">${comment.content}</div>
																          </div>	
																          <div class="praise">
																 		 	<div class="cid" style="display: none;">${comment.id}</div>
																 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																	        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																	     </div>
												          			</td>
												          		</tr>
												          	</table>
								                    	  </td>
												  	</tr>	
											      </c:if>
											      <!-- 单条用户评论结束 -->
											      <!-- 回复评论开始  -->
										          <c:if test="${comment.commentType == 1 && !empty comment.parents}">
										             	<tr> 
													          <td>
													          	<table  class="replyMore" cellpadding="0" cellspacing="0"  border="0" width="100%">
													          		<tr>
													          			<td>
															              <div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          	<div class="date lisCol">${comment.createTime}</div>
																          </div>
													          			</td>
													          		</tr>
														          		<tr>
														          			<td>
														          				<div class="replyFloors">
																          			<table class="tableFloor" cellpadding="0" cellspacing="0"  border="0">
																		          		<c:forEach items="${comment.parents}" var="parent" varStatus="row">
																					      	<tr> 
																					          <td>
																					              <div class="gLis userName">
																						          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																						          	<div class="gLisName yeloColor">${parent.name}</div>
																						          </div>
																						           <div class="clear"></div>
																						          <div class="gLis teacherName">
																						          	<div class="gLisInfoRelpy">${parent.content}</div>
																						          </div>	
																						          <div class="praise">
																						 		 	<c:if test="${parent.commentType != 1}"><div class="lefgin lisCol">${parent.typeStr}</div></c:if>
																						 		 	<div class="cid" style="display: none;">${parent.id}</div>
																						 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																						 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${parent.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																							        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${parent.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							     </div>
																						      </td>
																						   </tr>
																		          		</c:forEach>
																					</table>
																          		</div>
														          			</td>
														          		</tr>
													          		<tr>
													          			<td>
													          				 <div class="clear"></div>
																	          <div class="gLis teacherName">
																	          	<div class="gLisInfo">${comment.content}</div>
																	          </div>	
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
													          				<div class="praise">
																	 		 	<div class="cid" style="display: none;">${comment.id}</div>
																	 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																	 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																		        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																		     </div>
													          			</td>
													          		</tr>
													          	</table>
									                    	  </td>
														  </tr>	
										   		  </c:if>
											      <!-- 回复评论结束  --> 
												</c:forEach>
									    </table>
									</div>
								</div>
							</div>
					  		</c:if>
						</div>
						<!-- 中评 结束-->
						<!-- 差评 -->
						<div class="disBody" id="badBody" style="display:none;">
							<c:if test="${empty badCommentList}">
						  	    <div class="noBody" id="noBad">
									<a><img src="${ctx}/web/images_line/ganta.png"/></a>
									<span>暂无好评，快来评价吧~</span>
								</div>
						  	</c:if>
						  	<c:if test="${!empty badCommentList}">
						  	    <div class="disGall">
						  	    <div class="disGpdLis" id="badDis">
								<!-- 每条评论 -->
									<div class="goodList" >
							    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
											   <c:forEach items="${badCommentList}" var="comment" varStatus="row">
											   	   <!-- 单条微博评论开始 -->
											   	   <c:if test="${comment.commentType != 1}">
											   	       	<tr> 
												          <td>
												              <div class="gLis userName">
													          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
													          	<div class="gLisName yeloColor">${comment.name}</div>
													          	<div class="date lisCol">${comment.createTime}</div>
													          </div>
													          <div class="clear"></div>
													          <div class="gLis teacherName">
													          	<div class="gLisInfo">${comment.content}</div>
													          </div>
													          <div class="praise">
													 		 	<div class="lefgin lisCol">${comment.typeStr}</div>
													 		 	<div class="cid" style="display: none;">${comment.id}</div>
													 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
													 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
														        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
														      </div>
													      </td>
											   			</tr>
											   	  </c:if>
											   	  <!-- 单条微博评论结束 -->
											   	  <!-- 单条用户评论开始 -->
											      <c:if test="${comment.commentType == 1 && empty comment.parents}">
											          <tr> 
												          <td>
												          	<table  class="replyOne" cellpadding="0" cellspacing="0"  border="0">
												          		<tr>
												          			<td>
												          				<div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          </div>
																           <div class="clear"></div>
																          <div class="gLis teacherName">
																          	<div class="gLisInfoRelpy">${comment.content}</div>
																          </div>	
																          <div class="praise">
																 		 	<div class="cid" style="display: none;">${comment.id}</div>
																 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																	        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																	     </div>
												          			</td>
												          		</tr>
												          	</table>
								                    	  </td>
												  	</tr>	
											      </c:if>
											      <!-- 单条用户评论结束 -->
											      <!-- 回复评论开始  -->
										          <c:if test="${comment.commentType == 1 && !empty comment.parents}">
										             	<tr> 
													          <td>
													          	<table  class="replyMore" cellpadding="0" cellspacing="0"  border="0" width="100%">
													          		<tr>
													          			<td>
															              <div class="gLis userName">
																          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																          	<div class="gLisName yeloColor">${comment.name}</div>
																          	<div class="date lisCol">${comment.createTime}</div>
																          </div>
													          			</td>
													          		</tr>
														          		<tr>
														          			<td>
														          				<div class="replyFloors">
																          			<table class="tableFloor" cellpadding="0" cellspacing="0"  border="0">
																		          		<c:forEach items="${comment.parents}" var="parent" varStatus="row">
																					      	<tr> 
																					          <td>
																					              <div class="gLis userName">
																						          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
																						          	<div class="gLisName yeloColor">${parent.name}</div>
																						          </div>
																						           <div class="clear"></div>
																						          <div class="gLis teacherName">
																						          	<div class="gLisInfoRelpy">${parent.content}</div>
																						          </div>	
																						          <div class="praise">
																						 		 	<c:if test="${parent.commentType != 1}"><div class="lefgin lisCol">${parent.typeStr}</div></c:if>
																						 		 	<div class="cid" style="display: none;">${parent.id}</div>
																						 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																						 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${parent.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																							        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${parent.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																							     </div>
																						      </td>
																						   </tr>
																		          		</c:forEach>
																					</table>
																          		</div>
														          			</td>
														          		</tr>
													          		<tr>
													          			<td>
													          				 <div class="clear"></div>
																	          <div class="gLis teacherName">
																	          	<div class="gLisInfo">${comment.content}</div>
																	          </div>	
													          			</td>
													          		</tr>
													          		<tr>
													          			<td>
													          				<div class="praise">
																	 		 	<div class="cid" style="display: none;">${comment.id}</div>
																	 		 	<div class="origin lisCol repyh"><a class="replyBtn">回复</a><input type="hidden" value="0" name="num"/></div>
																	 		 	 <div class="downPrai colorGray"><i class="downZan"></i><span class="downZanNum">${comment.noLike}</span><input name="downPrai" class="downPraiIn" value="0" type="hidden"/></div>
																		        <div class="upPrai colorGray"><i class="upZan"></i><span class="upZanNum">${comment.like}</span><input name="upPrai" class="upPraiIn" value="0" type="hidden"/></div>
																		     </div>
													          			</td>
													          		</tr>
													          	</table>
									                    	  </td>
														  </tr>	
										   		  </c:if>
											      <!-- 回复评论结束  --> 
												</c:forEach>
									    </table>
									</div>
								</div>
							</div>
					  		</c:if>
						</div>
						<!-- 差评 结束-->
					</div>
				</div>
				<!-- 评价内容  结束-->
		  <%-- 	</c:if> --%>
			</div>
		</div>
		<!-- 评价和简介 结束-->
	</div> 
	<%-- <div class="kline-wrapper content">
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
		    	<div class="todayLine textLft" style="margin-left:10px;padding:10px 0;font-weight: 600;">${name} (${code})</div>
		       <!-- <div class="todayLine" style="visibility: hidden;">
		           <div class="upPrai margBom" style="margin-top: 5px;"><i class="upZanA"></i>看涨<span class="upZanNumA">10</span><input name="upPrai" class="upPraiInA" value="0" type="hidden"/></div>
				   <div class="downPrai margBom" style="margin-top: 5px;"><i class="downZanA"></i>看跌<span class="downZanNumA">10 </span><input name="downPrai" class="downPraiInA" value="0" type="hidden"/></div>
		       </div> -->
		       <div class="weekLine">
		          <div class="textLft lineTit clear" style="margin-left:10px">评论次数：${comment_num}</div>
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
				<!-- 5日 -->
				<!-- <div class="lineInfo" style="display: none;">
					<div class="container" id="fiveDays"></div>
				</div> -->
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
						<li class="disListAct discussLi  textCen "><div id="smil" class="discussLs"><span class="plBody"><i class="smile"></i>好评</span><br/>${goodCount}</div></li>
						<li class="discussLi textCen disLiss"><div id="cry" class="discussLs" onclick="kLineMid('${id}')"><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
						<li class="discussLi textCen disLiss"><div id="bigCry" class="discussLs" onclick="kLineBad('${id}')"><span class="plBody"><i class="bigCry"></i>差评</span><br/>${badCount}</div></li>
					</ul>
					<ul class="discussListA textCen"  id="navFixed" style="display:none;">
						<li class="disListAct discussLi textCen borRgt"><div id="smilA"><span class="plBody"><i class="smile"></i>好评</span><br/>${goodCount}</div></li>
						<li class="discussLi textCen borRgt disLiss"><div id="cryA" onclick="kLineMid('${id}')"><span class="plBody"><i class="cry"></i>中评</span><br/>${middleCount}</div></li>
						<li class="discussLi textCen borRgt disLiss"><div id="bigCryA" onclick="kLineBad('${id}')"><span class="plBody"><i class="bigCry"></i>差评</span><br/>${badCount}</div></li>
					</ul>
					<div class="hrline clear" ></div>
					<div class="disContent">
						<!-- 好评 -->
					<div class="disBody" id="godBody" >
						<c:if test="${empty goodCommentList}">
					  	    <div class="noBody">
								<a><img src="${ctx}/web/images_line/ganta.png"/></a>
								<span>暂无好评，快来评价吧~</span>
							</div>
					  	</c:if>
					  	<c:if test="${!empty goodCommentList}">
					  	<div class="disGall">
					  	    <div class="disGpdLis" id="goodDis">
							<!-- 每条好评 -->
								 <div class="goodList" >
						    		<table class="table" cellpadding="0" cellspacing="0" width="100%" border="0">
								      <c:forEach items="${goodCommentList}" var="comment" varStatus="row">
									      <tr> 
									          <td>
									              <div class="gLis userName">
										          	<img class="headImg" src="${ctx}/web/images_line/lineIco.png"/>
										          	<div class="gLisName yeloColor">${comment.name}</div>
										          	<div class="date lisCol"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
										          </div>
										          <div class="clear"></div>
										          <div class="gLis teacherName" onclick="reviewInfo('${comment.id}')">
										          	<div class="gLisInfo">${comment.content}</div>
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
							</div>
						</div>
						<!-- 好评 结束-->
					  	</c:if>
					</div>
					</div>
				</div>
				<!-- 评价内容  结束-->
		  	</c:if>
			</div>
		</div>
		<!-- 评价和简介 结束-->
	</div> --%>
</body>
</html>
<script src="${ctx}/web/js/jquery.js"></script>
<script src="${ctx}/web/js/jquery-ui.min.js"></script>
<script src="${ctx}/web/js/dropload.min.js"></script>
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
/* //判断内容，超过用...表示
$(".gLisInfo").each(function(){
	var obString=$(this).text();
	var length=$(this).text().length;
	var sl=$(this).text().substring(0,90)+"...";
	if(length>90){
	   		$(this).text(sl);
	   }else{
	   		$(this).text(obString);
	   }; 
}); */
$(function(){
   
   //评论点赞(只能点一次)
  	
    $(".disContent").on("touchstart",".upPrai",function(){
	 		   var upThis = $(this);
			   var val=$(this).find(".upPraiIn");
			   var donval=$(this).prev(".downPrai").find(".downPraiIn");
			   var cid=$(this).prev().prev().prev().text();
			   $.ajax({
			        type: 'POST',
			        url: '${ctx}/web/stock/saveStockCommentApproval',
			        async: false,
			        data:{id:cid,type:0},
			        dataType: 'text',
			        success: function(data){
			        	if(data=="-1"){
				        	location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=tokLine_${id}#wechat_redirect";
				        	return false;
				        }
			        	if(data == "hasUp"){
							return false;
						}else if(data == "hasDown"){
							return false;
						}else{
							if(val.val()==0 && donval.val()==0){
								var upNum=upThis.find(".upZanNum").text();
								upThis.find("i").attr("class","uzanAct");
								upThis.find(".upZanNum").css("color","#ef412c");
								upThis.find(".upZanNum").text(parseInt(upNum)+1);
						   		val.val(1);
					   		}
						}
			        },//success结束
			        error: function(xhr, type){
			        	alert("up error");
			        }  
			   });
			   
		   		/* else if(val.val()==1 && donval.val()==0){//再次点击取消
					var upNum=$(this).find(".upZanNum").text();
			   		$(this).find("i").attr("class","upZan");
			   		$(this).find(".upZanNum").css("color","#8a9a9f");
			   		$(this).find(".upZanNum").text(parseInt(upNum)-1);
			   		val.val(0);
			   		var likeNum = parseInt(upNum)-1;
			   		$.post("${ctx}/web/stock/saveStockCommentApproval?id="+cid+"&type=0","",function(d){
						
					});
				} */
	   });
   
    $(".disContent").on("touchstart",".downPrai",function(){
	 		  var downThis = $(this);
		  	   var dval=$(this).find(".downPraiIn");
			   var upval=$(this).next().find(".upPraiIn");
			   var cid=$(this).prev().prev().text();
			   $.ajax({
			        type: 'POST',
			        url: '${ctx}/web/stock/saveStockCommentApproval',
			        async: false,
			        data:{id:cid,type:1},
			        dataType: 'text',
			        success: function(data){
			        	if(data=="-1"){
				        	location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=tokLine_${id}#wechat_redirect";
				        	return false;
				        }
			        	if(data == "hasUp"){
							return false;
						}else if(data == "hasDown"){
							return false;
						}else{
							if(upval.val()==0 && dval.val()==0){
								var downNum=downThis.find(".downZanNum").text();
								downThis.find("i").attr("class","dzanAct");	
								downThis.find(".downZanNum").css("color","#ef412c");
								downThis.find(".downZanNum").text(parseInt(downNum)+1);
						   		dval.val(1);
					   		}
						}
			        },//success结束
			        error: function(xhr, type){
			        	alert("error");
			        }  
			   });	
			   
		   		/* else if(upval.val()==0 && dval.val()==1){//再次点击取消
					var upNum=$(this).find(".downZanNum").text();
			   		$(this).find("i").attr("class","downZan");
			   		$(this).find(".downZanNum").css("color","#8a9a9f");
			   		$(this).find(".downZanNum").text(parseInt(upNum)-1);
			   		dval.val(0);
			   		var noLikeNum = parseInt(downNum)-1;
			   		$.post("${ctx}/web/stock/saveStockCommentApproval?id="+cid+"&type=1","",function(d){
						
					});
				} */
	 	   
   });  
   //tab标签切换
   		//k线图
   		var $liK = $('.lineMenu li');
		var $ulK = $('.linLists .lineInfo');
   		$liK.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$liK.removeClass();
				$liK.find("i").attr("class","");
				$this.attr("class","lineActiv");
				$this.find("i").attr("class","jtActiv");
				$ulK.css('display','none');
				$ulK.eq($t).css('display','block');
	});
	
	
	//好评，中评，差评切换
   			var $disLi = $('.disLis li');
   			var $disLiA = $('.disLisA li');
   			var $disUl = $('.disContent .disBody');
   		$disLi.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$disLi.removeClass("disListAct");
				$disLiA.removeClass("disListAct");
				$disLi.addClass("disLiss");
				$disLiA.addClass("disLiss");
				$this.addClass("disListAct");
				$disLiA.eq($t).addClass("disListAct");
				$disUl.css('display','none');
				$disUl.removeClass("actIon");
				$disUl.eq($t).css('display','block');
				$disUl.eq($t).addClass("actIon");
				
	});
		$disLiA.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$disLi.removeClass("disListAct");
				$disLiA.removeClass("disListAct");
				$disLi.addClass("disLiss");
				$disLiA.addClass("disLiss");
				$this.addClass("disListAct");
				$disLi.eq($t).addClass("disListAct");
				$disUl.css('display','none');
				$disUl.removeClass("actIon");
				$disUl.eq($t).css('display','block');
				$disUl.eq($t).addClass("actIon");
	});
	
	
	//评价切换图片变化
	$(".disAct").click(function(){
		$(this).next().find("i").attr("class","smil");
		$(this).next().next().find("div").attr("class","disLiss discussLs");
		$(this).next().find("div").attr("class","disLiss discussLs");
		$(this).next().next().next().find("div").attr("class","disLiss discussLs");
		$(this).next().next().find("i").attr("class","cry");
   		$(this).next().next().next().find("i").attr("class","bigCry");
   		$("#smilA").find("i").attr("class","smil");
   		$("#smilA").parent().next().find("i").attr("class","cry");
   		$("#smilA").parent().next().next().find("i").attr("class","bigCry");
   		$("#smilA").attr("class","disLiss ");
   		$("#smilA").parent().next().find("div").attr("class","disLiss ");
   		$("#smilA").parent().next().next().find("div").attr("class","disLiss ");
	});
	
	$(".disActA").click(function(){
		$(this).next().find("i").attr("class","smil");
		$(this).next().next().find("div").attr("class","disLiss discussLs");
		$(this).next().find("div").attr("class","disLiss discussLs");
		$(this).next().next().next().find("div").attr("class","disLiss discussLs");
		$(this).next().next().find("i").attr("class","cry");
   		$(this).next().next().next().find("i").attr("class","bigCry");
   		$("#smil").find("i").attr("class","smil");
   		$("#smil").parent().next().find("i").attr("class","cry");
   		$("#smil").parent().next().next().find("i").attr("class","bigCry");
   		$("#smil").attr("class","disLiss ");
   		$("#smil").parent().next().find("div").attr("class","disLiss ");
   		$("#smil").parent().next().next().find("div").attr("class","disLiss ");
	});
	
   $("#smil").click(function(){
   		$(this).find("i").attr("class","smile");
   		$(this).attr("class","disListAct discussLs");
   		$(this).parent().next().find("div").attr("class","disLiss discussLs");
   		$(this).parent().next().next().find("div").attr("class","disLiss discussLs");
   		$(this).parent().next().find("i").attr("class","cry");
   		$(this).parent().next().next().find("i").attr("class","bigCry");
   		$("#smilA").parent().next().find("i").attr("class","cry");
   		$("#smilA").parent().next().find("div").attr("class","disLiss ");
   		$("#smilA").parent().next().next().find("div").attr("class","disLiss ");
   		$("#smilA").find("i").attr("class","smile");
   		$("#smilA").attr("class","disListAct ");
   		$("#smilA").parent().next().next().find("i").attr("class","bigCry");
   });
   $("#cry").click(function(){
   		$(this).find("i").attr("class","cryAct");
   		$(this).attr("class","disListMid discussLs");
   		$(this).parent().next().find("div").attr("class","disLiss discussLs");
   		$(this).parent().prev().find("div").attr("class","disLiss discussLs");
   		$(this).parent().prev().find("i").attr("class","smil");
   		$(this).parent().next().find("i").attr("class","bigCry");
   		$("#cryA").find("i").attr("class","cryAct");
   		$("#cryA").attr("class","disListMid ");
   		$("#cryA").parent().next().find("div").attr("class","disLiss ");
   		$("#cryA").parent().prev().find("div").attr("class","disLiss ");
   		$("#cryA").parent().prev().find("i").attr("class","smil");
   		$("#cryA").parent().next().find("i").attr("class","bigCry");
   });
   $("#bigCry").click(function(){
   		$(this).find("i").attr("class","bigCryAct");
   		$(this).parent().prev().find("i").attr("class","cry");
   		$(this).parent().prev().prev().find("i").attr("class","smil");
   		$(this).attr("class","disListBad discussLs");
   		$(this).parent().prev().prev().find("div").attr("class","disLiss discussLs");
   		$(this).parent().prev().find("div").attr("class","disLiss discussLs");
   		$("#bigCryA").find("i").attr("class","bigCryAct");
   		$("#bigCryA").parent().prev().find("i").attr("class","cry");
   		$("#bigCryA").parent().prev().prev().find("i").attr("class","smil");
   		$("#bigCryA").attr("class","disListBad ");
   		$("#bigCryA").parent().prev().prev().find("div").attr("class","disLiss ");
   		$("#bigCryA").parent().prev().find("div").attr("class","disLiss ");
   });
   
   $("#smilA").click(function(){
   		$(this).find("i").attr("class","smile");
   		$(this).attr("class","disListAct ");
   		$(this).parent().next().find("div").attr("class","disLiss ");
   		$(this).parent().next().next().find("div").attr("class","disLiss ");
   		$(this).parent().next().find("i").attr("class","cry");
   		$(this).parent().next().next().find("i").attr("class","bigCry");
   		$("#smil").parent().next().find("i").attr("class","cry");
   		$("#smil").find("i").attr("class","smile");
   		$("#smil").parent().next().next().find("i").attr("class","bigCry");
   		$("#smil").attr("class","disListAct discussLs");
   		$("#smil").parent().next().find("div").attr("class","disLiss discussLs");
   		$("#smil").parent().next().next().find("div").attr("class","disLiss discussLs");
   });
   
   
   $("#cryA").click(function(){
   		$(this).find("i").attr("class","cryAct");
   		$(this).parent().prev().find("i").attr("class","smil");
   		$(this).parent().next().find("i").attr("class","bigCry");
   		$(this).attr("class","disListMid ");
   		$(this).parent().next().find("div").attr("class","disLiss ");
   		$(this).parent().prev().find("div").attr("class","disLiss ");
   		$("#cry").find("i").attr("class","cryAct");
   		$("#cry").parent().prev().find("i").attr("class","smil");
   		$("#cry").parent().next().find("i").attr("class","bigCry");
   		$("#cry").attr("class","disListMid discussLs");
   		$("#cry").parent().next().find("div").attr("class","disLiss discussLs");
   		$("#cry").parent().prev().find("div").attr("class","disLiss discussLs");
   });
    $("#bigCryA").click(function(){
   		$(this).find("i").attr("class","bigCryAct");
   		$(this).parent().prev().find("i").attr("class","cry");
   		$(this).parent().prev().prev().find("i").attr("class","smil");
   		$(this).attr("class","disListBad ");
   		$(this).parent().prev().prev().find("div").attr("class","disLiss ");
   		$(this).parent().prev().find("div").attr("class","disLiss ");
   		$("#bigCry").find("i").attr("class","bigCryAct");
   		$("#bigCry").parent().prev().find("i").attr("class","cry");
   		$("#bigCry").parent().prev().prev().find("i").attr("class","smil");
   		$("#bigCry").attr("class","disListBad discussLs");
   		$("#bigCry").parent().prev().prev().find("div").attr("class","disLiss discussLs");
   		$("#bigCry").parent().prev().find("div").attr("class","disLiss discussLs");
   });
   
   //点击回复发布
  var htmFB="<div class='fabuBody'>"+
		"<form action='' method='post'>"+
			"<textarea rows='3' name='message' placeholder='请输入评论，限70个字' ></textarea>"+
			"<ul class='disBd'>"+
				"<li class='godDisu'>"+
					"<div class='radio-btn reBlg checkedRadiore'>"+
						"<i>"+
							"<input type='radio'  name='radio-1-set' class='regular-radio' checked='' value='0'/>"+
						"</i>"+
					"</div>"+
					"<img class='bqIcon' src='${ctx}/web/images_line/hpIco.png'/>"+
				"</li>"+
				"<li class='midDisu'>"+
					"<div class='radio-btn greBlg'>"+
						"<i>"+
							"<input type='radio'  name='radio-1-set' class='regular-radio' value='1'/>"+
						"</i>"+
					"</div>"+
					"<img class='bqIcon' src='${ctx}/web/images_line/mdIco.png'/>"+
				"</li>"+
				"<li class='badDisu'>"+
					"<div class='radio-btn graBlg'>"+
						"<i>"+
							"<input type='radio'  name='radio-1-set' class='regular-radio' value='2'/>"+
						"</i>"+
					"</div>"+
					"<img class='bqIcon' src='${ctx}/web/images_line/bdIco.png'/>"+
				"</li>"+
			"</ul>"+
			"<input class='fabu' type='submit' value='发布'/>"+
		"</form>"+
	"</div>";
   $(".disContent").on("touchstart",".replyBtn",function(){
   		if($(this).next().val()==0){
   		
   			$(this).parent().parent().after(htmFB);
   			$(this).next().val(1);
   		}else{
   			$(this).parent().parent().next(".fabuBody").remove();
   			$(this).next().val(0);
   		}
   			
   });
   //点击发布
   $(".disContent").on("touchstart",".fabu",function(){
		   var cid=$(this).parent().parent().prev().find(".cid").text();
		   var content=$(this).parent().find("textarea").val();
		   var type=$("input:radio[name='radio-1-set']:checked").val();
		   /* var type=$("input:radio[name='radio-1-set']:checked").val();
		   if(typeof(type) == "undefined"){
			   alert("请选择评论分类！");
			   return false;
		   } */
		   if(content == null || content == ""){
			   alert("请输入评论内容！");
			   return false;
		   }
		   if(content.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>140){
			   alert("评论内容限70个汉字！");
			   return false;
		   }
		   $.ajax({
		        type: 'POST',
		        url: '${ctx}/web/stock/saveCreateStockComment',
		        data:{id:cid,stockId:${id},content:content,type:type},
		        dataType: 'text',
		        success: function(data){
			        if(data=="-1"){
			        	location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=tokLine_${id}#wechat_redirect";
			        	return false;
			        }
		        	location.href = location.href;
		        },
		        error: function(xhr, type){
		        	alert("error");
		        }  
		   });
   });
});
</script>
<script>
	//模拟选中和取消
	$(".disContent").on("touchstart ",".radio-btn", function () {
	
    var _this = $(this),
    block = _this.parent().parent();
    block.find("input:radio").attr("checked", false);
    if(_this.hasClass("greBlg")){
	    block.find(".radio-btn").removeClass("checkedRadiore");
	    block.find(".radio-btn").removeClass("checkedRadiogra");
	    _this.addClass("checkedRadiogre");
	    _this.find("input:radio").attr("checked", true);
    }else if(_this.hasClass("reBlg")){
    	 block.find(".radio-btn").removeClass("checkedRadiogre");
	    block.find(".radio-btn").removeClass("checkedRadiogra");
	    _this.addClass("checkedRadiore");
	    _this.find("input:radio").attr("checked", true);
    }else if(_this.hasClass("graBlg")){
    	 block.find(".radio-btn").removeClass("checkedRadiore");
	    block.find(".radio-btn").removeClass("checkedRadiogre");
	    _this.addClass("checkedRadiogra");
	    _this.find("input:radio").attr("checked", true);
    }
    
   
});
	
</script>
<script>
//搜索
     $(function() {
    var projects =${items};
 
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
        
        /* window.location = "${ctx}/web/stock/stockIndex?code="+ui.item.category;  */
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
//聚合
$(function() {
	$.ajax({
        type: 'GET',
        url: '${ctx}/web/stock/getJuheStockInfo',
        data:{id:${id}},
        dataType: 'json',
        success: function(data){
        	var nowPri = data.nowPri;
        	var yestodEndPri = data.yestodEndPri;
        	var traNumber = data.traNumber;
        	var change = data.change;
        	var todayStartPri = data.todayStartPri;
        	var todayMax = data.todayMax;
        	var todayMin = data.todayMin;
        	var difference = data.difference;
        	var arrText = [];
        	var result = '';
        	if(nowPri >= yestodEndPri && traNumber != 0){
        		arrText.push("<div class='headLft textCen'><a class='red bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${baseCode}.html'>"+nowPri+"</a>");
        		arrText.push("<div class='headLftNum'><span class='red smallSize paddBot'>+"+difference+"</span>");
        		arrText.push("<span class='red smallSize paddBot'>+"+change+"%</span></div></div>");
        	}
        	if(nowPri < yestodEndPri && traNumber != 0){
        		arrText.push("<div class='headLft textCen'><a class='green bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${baseCode}.html'>"+nowPri+"</a>");
        		arrText.push("<div class='headLftNum'><span class='green smallSize paddBot'>"+difference+"</span>");
        		arrText.push("<span class='green smallSize paddBot'>"+change+"%</span></div></div>");
        	}
        	if(traNumber == 0){
        		arrText.push("<div class='headLft textCen'><a class='dieColor bigSize fontWed' href='http://lxjzb.qianlong.com.cn/stockf10/cpbd/${baseCode}.html'>"+yestodEndPri+"</a>");
        		arrText.push("<div class='headLftNum'><span class='dieColor smallSize paddBot'>停牌</span></div></div>");
        	}
        	result +=  arrText.join('');
        	$("#headTit").append(result);
        	$("#todayStartPri").append("<br/>"+data.todayStartPri); 
        	$("#yestodEndPri").append("<br/>"+data.yestodEndPri); 
        	$("#todayMax").append("<br/>"+data.todayMax); 
        	$("#todayMin").append("<br/>"+data.todayMin); 
        	$("#container").append("<img src='http://image.sinajs.cn/newchart/min/n/${code}.gif'>");
        	$("#DaysK").append("<img src='http://image.sinajs.cn/newchart/daily/n/${code}.gif'>");
        	$("#weekDays").append("<img src='http://image.sinajs.cn/newchart/weekly/n/${code}.gif'>");
        	$("#monthDays").append("<img src='http://image.sinajs.cn/newchart/monthly/n/${code}.gif'>");
        	
        	
        },//success结束
        error: function(xhr, type){
        	var result = '';
        	result +=  arrText.join('');
        	$("#headTit").append(result);
        	$("#todayStartPri").append("<br/>"+data.todayStartPri); 
        	$("#yestodEndPri").append("<br/>"+data.yestodEndPri); 
        	$("#todayMax").append("<br/>"+data.todayMax); 
        	$("#todayMin").append("<br/>"+data.todayMin); 
        	$("#container").append("<img src='${ctx}/web/images_line/default.png'>");
        	$("#DaysK").append("<img src='${ctx}/web/images_line/default.png'>");
        	$("#weekDays").append("<img src='${ctx}/web/images_line/default.png'>");
        	$("#monthDays").append("<img src='${ctx}/web/images_line/default.png'>");
        }  
    });//ajax结束
    
    
});  
</script>
   
<script>
function downLoad(event,id,cType,structCount){
    var counter = 1;
    // 每页展示5个
    var num = 5;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $(id).dropload({
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
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
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
                url: '${ctx}/web/stock/moreStockCommentList',
                data:{id:${id}, page:counter, type:cType},
                dataType: 'json',
                success: function(data){
                 var length=data.lists.length;
                 //判断是否有数据
			        if(length==0){
			        	//$("#godBody").show();
			        	$(id).show();
			        	$(".dropload-down").text("暂无更多评论");
			        
			        }/* else if(length<=5){
			        	$(".dropload-down").hide();
			        } */else{
			        $(".dropload-load").show();
			        var result = '';
                    counter++;
                    pageEnd = num * counter;
                    pageStart = pageEnd - num;

                    var arrText = [];
                    arrText.push("<div class='goodList' >");
			        arrText.push("<table class='table' cellpadding='0' cellspacing='0' width='100%' border='0'>");
                    for(var i = 0; i < length; i++){
                      /*单条微博评论 */
                      if(data.lists[i].commentType != 1){
                    	  arrText.push("<tr><td><div class='gLis userName'><img class='headImg' src='${ctx}/web/images_line/lineIco.png'/><div class='gLisName yeloColor'>"+data.lists[i].name+"</div><div class='date lisCol'>"+data.lists[i].createTime+"</div></div>");
                          arrText.push("<div class='clear'></div>");
                          arrText.push("<div class='gLis teacherName'><div class='gLisInfo'>"+data.lists[i].content+"</div></div>");
                          arrText.push("<div class='praise'><div class='lefgin lisCol'>"+data.lists[i].typeStr+"</div><div class='cid' style='display: none;'>"+data.lists[i].id+"</div><div class='origin lisCol repyh'><a class='replyBtn'>回复</a><input type='hidden' value='0' name='num'/></div>");
                          arrText.push("<div class='downPrai colorGray'><i class='downZan'></i><span class='downZanNum'>"+data.lists[i].noLike+"</span><input name='downPrai' class='downPraiIn' value='0' type='hidden'/></div>");
                          arrText.push("<div class='upPrai colorGray'><i class='upZan'></i><span class='upZanNum'>"+data.lists[i].like+"</span><input name='upPrai' class='upPraiIn' value='0' type='hidden'/></div></div></td></tr>");
                      }	
                      /*单条用户评论 */
                      if(data.lists[i].commentType == 1 && data.lists[i].parents.length==0){
                    	  arrText.push("<tr><td><table  class='replyOne' cellpadding='0' cellspacing='0'  border='0'>");
                    	  arrText.push("<tr><td><div class='gLis userName'><img class='headImg' src='${ctx}/web/images_line/lineIco.png'/><div class='gLisName yeloColor'>"+data.lists[i].name+"</div></div>");
                    	  arrText.push("<div class='clear'></div><div class='gLis teacherName'><div class='gLisInfoRelpy'>"+data.lists[i].content+"</div></div>");
                    	  arrText.push("<div class='praise'><div class='cid' style='display: none;'>1</div><div class='origin lisCol repyh'><a class='replyBtn'>回复</a><input type='hidden' value='0' name='num'/></div>");
                    	  arrText.push("<div class='downPrai colorGray'><i class='downZan'></i><span class='downZanNum'>"+data.lists[i].noLike+"</span><input name='downPrai' class='downPraiIn' value='0' type='hidden'/></div>");
                    	  arrText.push("<div class='upPrai colorGray'><i class='upZan'></i><span class='upZanNum'>"+data.lists[i].like+"</span><input name='upPrai' class='upPraiIn' value='0' type='hidden'/></div></div></td></tr></table></td></tr>");
                      }	
                      /*回复评论 */
                      if(data.lists[i].commentType == 1 && data.lists[i].parents.length!=0){
                    	  arrText.push("<tr><td><table  class='replyMore' cellpadding='0' cellspacing='0'  border='0' width='100%'>");
                    	  arrText.push("<tr><td><div class='gLis userName'><img class='headImg' src='${ctx}/web/images_line/lineIco.png'/><div class='gLisName yeloColor'>"+data.lists[i].name+"</div><div class='date lisCol'>"+data.lists[i].createTime+"</div></div></td></tr>");
                    	  arrText.push("<tr><td><div class='replyFloors'><table class='tableFloor' cellpadding='0' cellspacing='0'  border='0'>");
                    	  //迭代parents
                    	  for(var j=0; j<data.lists[i].parents.length; j++){
                        	  arrText.push("<tr><td><div class='gLis userName'><img class='headImg' src='${ctx}/web/images_line/lineIco.png'/><div class='gLisName yeloColor'>"+data.lists[i].parents[j].name+"</div></div>");
                        	  arrText.push("<div class='clear'></div><div class='gLis teacherName'><div class='gLisInfoRelpy'>"+data.lists[i].parents[j].content+"</div></div>");
                        	  arrText.push("<div class='praise'>");
                        	  if(data.lists[i].parents[j].commentType != 1){
                        		  arrText.push("<div class='lefgin lisCol'>"+data.lists[i].typeStr+"</div>");
                        	  } 
                        	  arrText.push("<div class='cid' style='display: none;'>"+data.lists[i].parents[j].id+"</div>")
                        	  arrText.push("<div class='origin lisCol repyh'><a class='replyBtn'>回复</a><input type='hidden' value='0' name='num'/></div>");
                        	  arrText.push("<div class='downPrai colorGray'><i class='downZan'></i><span class='downZanNum'>"+data.lists[i].parents[j].noLike+"</span><input name='downPrai' class='downPraiIn' value='0' type='hidden'/></div>");
                        	  arrText.push("<div class='upPrai colorGray'><i class='upZan'></i><span class='upZanNum'>"+data.lists[i].parents[j].like+"</span><input name='upPrai' class='upPraiIn' value='0' type='hidden'/></div></div></td></tr>");
                    	  }
                    	  arrText.push("</table></div></td></tr>");
                    	  arrText.push("<tr><td><div class='clear'></div><div class='gLis teacherName'><div class='gLisInfo'>"+data.lists[i].content+"</div></div></td></tr>");
                          arrText.push("<tr><td><div class='praise'><div class='cid' style='display: none;'>"+data.lists[i].id+"</div><div class='origin lisCol repyh'><a class='replyBtn'>回复</a><input type='hidden' value='0' name='num'/></div>");
                          arrText.push("<div class='downPrai colorGray'><i class='downZan'></i><span class='downZanNum'>"+data.lists[i].noLike+"</span><input name='downPrai' class='downPraiIn' value='0' type='hidden'/></div>");
                          arrText.push("<div class='upPrai colorGray'><i class='upZan'></i><span class='upZanNum'>"+data.lists[i].like+"</span><input name='upPrai' class='upPraiIn' value='0' type='hidden'/></div></div></td></tr></table></td></tr>"); 
                      } 
                      if((i + 1) >= structCount){
                          // 锁定
                          me.lock();
                          // 无数据
                          me.noData();
                          break;
                      }
                    }
                    arrText.push("</table>");
                    result +=  arrText.join('');
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        //$('#goodDis').append(result);
                       // $('#allDis').append(result);
                       $(event).append(result);
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
			      };//if end
                },//success结束
                error: function(xhr, type){
                    alert('Ajax error下拉!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }  
            });//ajax结束
        },//上拉结束
        threshold : 50
        
    });//drop结束
};


var allClass = $("#allBody").attr("class");
var godClass = $("#goodBody").attr("class");
var midClass = $("#midBody").attr("class");
var badClass = $("#badBody").attr("class");

	$("#allBody").on("touchstart",function(){
	if($("#allBody").hasClass("disBody actIon")){
	var txt=$(this).find(".dropload-noData").text();
	var txta=$(this).has(".dropload-down").length;
	
		if((txt!="暂无更多评论")&&(txta==0)){ 
	     var allSize = ${allSize};
		 downLoad("#allDis","#allBody",3, allSize);
		 }
		 	};
		 });


	$("#goodBody").on("touchstart",function(){
		if($("#goodBody").hasClass("disBody actIon")){
			var txt=$(this).find(".dropload-noData").text();
			var txta=$(this).has(".dropload-down").length;
			if((txt!="暂无更多评论")&&(txta==0)){ 
				var goodSize = ${goodSize};
				downLoad("#goodDis","#goodBody",0, goodSize);
			} 
		}
	}); 
	 
	$("#midBody").on("touchstart",function(){
		if($("#midBody").hasClass("disBody actIon")){
			var txt=$(this).find(".dropload-noData").text();
			var txta=$(this).has(".dropload-down").length;
			if((txt!="暂无更多评论")&&(txta==0)){ 
				var midSize = ${midSize};
				downLoad("#midDis","#midBody",1, midSize);
			} 
		 	}
		 }); 
	
	$("#badBody").on("touchstart",function(){
		if($("#badBody").hasClass("disBody actIon")){
			var txt=$(this).find(".dropload-noData").text();
			var txta=$(this).has(".dropload-down").length;
			if((txt!="暂无更多评论")&&(txta==0)){ 
				var badSize = ${badSize};
				downLoad("#badDis","#badBody",2, badSize);
			} 
		 	}
		 }); 
</script>
