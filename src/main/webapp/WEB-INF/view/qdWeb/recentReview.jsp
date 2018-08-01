<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 评论 -->
			<td width="338" valign="top" align="center">
	 			<div class="discussAll borTop clear">
					<ul class="discussList textCen disLis"  id="navNor">
						<li class="discussLi  textCen disListAct">累计评论<br/>(${totalCount})</li>
						<li class=" discussLi  textCen disLiss">好评<br/>(${goodCount})</li>
						<li class="discussLi textCen disLiss">中评<br/>(${middleCount})</li>
						<li class="discussLi textCen disLiss">差评<br/>(${badCount})</li>
					</ul>
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
											<div class="goodListAll" >
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
										
										<!-- 页码 -->
										 <div class="page">
											<input type="button" class="allTop" value="首页">
											<input type="button" class="allPre disa" value="上一页" disabled="disabled">
											<a style="display: none;">3</a>
											<span class="allPage">1</span>
											<input type="button" class="allNext" value="下一页">
										</div>
										<!-- 页码end -->
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
											<div class="goodListGood" >
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
										<!-- 页码 -->
										 <div class="page">
										 	<input type="button" class="goodTop" value="首页">
											<input type="button" class="goodPre disa" value="上一页" disabled="disabled">
											<a style="display: none;">0</a>
											<span class="goodPage">1</span>
											<input type="button" class="goodNext" value="下一页">
										</div>
										<!-- 页码end -->
										
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
											<span>暂无中评，快来评价吧~</span>
										</div>
								  	</c:if>
								  	<c:if test="${!empty middleCommentList}">
								  	    <div class="disGall">
								  	    <div class="disGpdLis" id="midDis">
										<!-- 每条评论 -->
											<div class="goodListMid" >
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
										
										<!-- 页码 -->
										 <div class="page">
										 	<input type="button" class="midTop" value="首页">
											<input type="button" class="midPre disa" value="上一页" disabled="disabled">
											<a style="display: none;">1</a>
											<span class="midPage">1</span>
											<input type="button" class="midNext" value="下一页">
										</div>
										<!-- 页码end -->
										
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
											<span>暂无差评，快来评价吧~</span>
										</div>
								  	</c:if>
								  	<c:if test="${!empty badCommentList}">
								  	    <div class="disGall">
								  	    <div class="disGpdLis" id="badDis">
										<!-- 每条评论 -->
											<div class="goodListBad" >
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
										
										<!-- 页码 -->
										 <div class="page">
										 	<input type="button" class="badTop" value="首页">
											<input type="button" class="badPre disa" value="上一页" disabled="disabled">
											<a style="display: none;">2</a>
											<span class="badPage">1</span>
											<input type="button" class="badNext" value="下一页">
										</div>
										<!-- 页码end -->
										
										</div>
									</div>
							  		</c:if>
								</div>
								<!-- 差评 结束-->
							</div>
						</div>
						<!-- 评价内容  结束-->
			</div>
		</div>
		<!-- 评价和简介 结束-->
	 </td>
	 <script type="text/javascript">
	 	//好中差评
		var $disLi = $('.disLis li');
   		var $disUl = $('.disContent .disBody');
   		$disLi.click(function(){
				var $this = $(this);
				var $t = $this.index();
				$disLi.removeClass("disListAct");
				$disLi.addClass("disLiss");
				$this.addClass("disListAct");
				$disUl.css('display','none');
				$disUl.removeClass("actIon");
				$disUl.eq($t).css('display','block');
				$disUl.eq($t).addClass("actIon");
				
	});		
	//点赞
	 //评论点赞(只能点一次)
    $(".disContent").on("click",".upPrai",function(){
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
			        		location.href = "${loginUrl}"+"/qidian_maven/web/ai/qdKline?id="+${id};
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
			   })
			 });
			 
			 $(".disContent").on("click",".downPrai",function(){
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
				        	location.href = "${loginUrl}"+"/qidian_maven/web/ai/qdKline?id="+${id};
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
   $(".disContent").on("click",".replyBtn",function(){
   		if($(this).next().val()==0){
   		
   			$(this).parent().parent().after(htmFB);
   			$(this).next().val(1);
   		}else{
   			$(this).parent().parent().next(".fabuBody").remove();
   			$(this).next().val(0);
   		}
   			
   });
   //点击发布
   $(".disContent").on("click",".fabu",function(){
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
			        	location.href = "${loginUrl}"+"/qidian_maven/web/ai/qdKline?id="+${id};
			        	return false;
			        }
		        	location.href = location.href;
		        },
		        error: function(xhr, type){
		        	alert("error");
		        }  
		   });
   });


//模拟选中和取消
	$(".disContent").on("click ",".radio-btn", function () {
	
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
	
	$(function(){
		nextValidate(3);
		nextValidate(0);
		nextValidate(1);
		nextValidate(2);
	});
	
	//全部评论首页点击事件
	$(".allTop").click(function(){
		//改变页码的值
		$(".allPage").text(1);
		//将上一页按钮禁用
		$(".allPre").addClass("disa");
		$(".allPre").attr({"disabled":"disabled"});
		//判断下一页按钮是否可用
		nextValidate(3);
		//更新数据
		downLoad(".goodListAll","#allDis","#allBody",1,3);
	});
	
	//全部评论下一页点击事件
	$(".allNext").click(function(){
		var allPage= parseInt($(".allPage").text())+1;
		//改变页码的值
		$(".allPage").text(allPage);
		//将上一页按钮设为可用
		$(".allPre").removeClass("disa");
		$(".allPre").removeAttr("disabled");
		//判断下一页按钮是否可用
		nextValidate(3);	
		//更新数据
		downLoad(".goodListAll","#allDis","#allBody",allPage,3);
	});
	
	//全部评论上一页点击事件
	$(".allPre").click(function(){
		var allPage=parseInt($(".allPage").text())-1;
		//改变页码的值
		$(".allPage").text(allPage);
		//将下一页按钮设为可用
		$(".allNext").removeClass("disa");
		$(".allNext").removeAttr("disabled");
		//判断上一页按钮是否可用
		if(allPage=="1"){
			$(this).addClass("disa");
			$(this).attr({"disabled":"disabled"});
		}else{
			$(this).removeClass("disa");
			$(this).removeAttr("disabled");
		}
		//更新数据
		downLoad(".goodListAll","#allDis","#allBody",allPage,3);
	});
	
	//好评首页点击事件
	$(".goodTop").click(function(){
		//改变页码的值
		$(".goodPage").text(1);
		//将上一页按钮禁用
		$(".goodPre").addClass("disa");
		$(".goodPre").attr({"disabled":"disabled"});
		//判断下一页按钮是否可用
		nextValidate(0);
		//更新数据
		downLoad(".goodListGood","#goodDis","#goodBody",1,0);
	});
	
	//好评下一页点击事件
	$(".goodNext").click(function(){
		var goodPage= parseInt($(".goodPage").text())+1;
		//改变页码的值
		$(".goodPage").text(goodPage);
		//将上一页按钮设为可用
		$(".goodPre").removeClass("disa");
		$(".goodPre").removeAttr("disabled");
		//判断下一页按钮是否可用
		nextValidate(0);	
		//更新数据
		downLoad(".goodListGood","#goodDis","#goodBody",goodPage,0);
	});
	
	//好评上一页点击事件
	$(".goodPre").click(function(){
		var goodPage=parseInt($(".goodPage").text())-1;
		//改变页码的值
		$(".goodPage").text(goodPage);
		//将下一页按钮设为可用
		$(".goodNext").removeClass("disa");
		$(".goodNext").removeAttr("disabled");
		//判断上一页按钮是否可用
		if(goodPage=="1"){
			$(this).addClass("disa");
			$(this).attr({"disabled":"disabled"});
		}else{
			$(this).removeClass("disa");
			$(this).removeAttr("disabled");
		}
		//更新数据
		downLoad(".goodListGood","#goodDis","#goodBody",goodPage,0);
	});
	
	//中评首页点击事件
	$(".midTop").click(function(){
		//改变页码的值
		$(".midPage").text(1);
		//将上一页按钮禁用
		$(".midPre").addClass("disa");
		$(".midPre").attr({"disabled":"disabled"});
		//判断下一页按钮是否可用
		nextValidate(1);
		//更新数据
		downLoad(".goodListMid","#midDis","#midBody",1,1);
	});
	
	//中评下一页点击事件
	$(".midNext").click(function(){
		var midPage= parseInt($(".midPage").text())+1;
		//改变页码的值
		$(".midPage").text(midPage);
		//将上一页按钮设为可用
		$(".midPre").removeClass("disa");
		$(".midPre").removeAttr("disabled");
		//判断下一页按钮是否可用
		nextValidate(1);	
		//更新数据
		downLoad(".goodListMid","#midDis","#midBody",midPage,1);
	});
	
	//中评上一页点击事件
	$(".midPre").click(function(){
		var midPage=parseInt($(".midPage").text())-1;
		//改变页码的值
		$(".midPage").text(midPage);
		//将下一页按钮设为可用
		$(".midNext").removeClass("disa");
		$(".midNext").removeAttr("disabled");
		//判断上一页按钮是否可用
		if(midPage=="1"){
			$(this).addClass("disa");
			$(this).attr({"disabled":"disabled"});
		}else{
			$(this).removeClass("disa");
			$(this).removeAttr("disabled");
		}
		//更新数据
		downLoad(".goodListMid","#midDis","#midBody",midPage,1);
	});
		
	//差评首页点击事件
	$(".badTop").click(function(){
		//改变页码的值
		$(".badPage").text(1);
		//将上一页按钮禁用
		$(".badPre").addClass("disa");
		$(".badPre").attr({"disabled":"disabled"});
		//判断下一页按钮是否可用
		nextValidate(2);
		//更新数据
		downLoad(".goodListBad","#badDis","#badBody",1,2);
	});
	
	//差评下一页点击事件
	$(".badNext").click(function(){
		var badPage= parseInt($(".badPage").text())+1;
		//改变页码的值
		$(".badPage").text(badPage);
		//将上一页按钮设为可用
		$(".badPre").removeClass("disa");
		$(".badPre").removeAttr("disabled");
		//判断下一页按钮是否可用
		nextValidate(2);	
		//更新数据
		downLoad(".goodListBad","#badDis","#badBody",badPage,2);
	});
	
	//差评上一页点击事件
	$(".badPre").click(function(){
		var badPage=parseInt($(".badPage").text())-1;
		//改变页码的值
		$(".badPage").text(badPage);
		//将下一页按钮设为可用
		$(".badNext").removeClass("disa");
		$(".badNext").removeAttr("disabled");
		//判断上一页按钮是否可用
		if(badPage=="1"){
			$(this).addClass("disa");
			$(this).attr({"disabled":"disabled"});
		}else{
			$(this).removeClass("disa");
			$(this).removeAttr("disabled");
		}
		//更新数据
		downLoad(".goodListBad","#badDis","#badBody",badPage,2);
	});
	
	//判断下一页按钮是否可用
	function nextValidate(id){
		if(id==3){
			var allSize = ${allSize};
			var allPage=$(".allPage").text();
			//当前页码*每页数量>=总数量,禁用下一页
			if(parseInt(allPage)*5>=allSize){
				$(".allNext").addClass("disa");
				$(".allNext").attr({"disabled":"disabled"});
			}else{
				$(".allNext").removeClass("disa");
				$(".allNext").removeAttr("disabled");
			}
		}
		if(id==0){
			var goodSize = ${goodSize};
			var goodPage=$(".goodPage").text();
			if(parseInt(goodPage)*5>=goodSize){
				$(".goodNext").addClass("disa");
				$(".goodNext").attr({"disabled":"disabled"});
			}else{
				$(".goodNext").removeClass("disa");
				$(".goodNext").removeAttr("disabled");
			}
		}
		if(id==1){
			var midSize = ${midSize};
			var midPage=$(".midPage").text();
			if(parseInt(midPage)*5>=midSize){
				$(".midNext").addClass("disa");
				$(".midNext").attr({"disabled":"disabled"});
			}else{
				$(".midNext").removeClass("disa");
				$(".midNext").removeAttr("disabled");
			}	
		}
		if(id==2){
			var badSize = ${badSize};
			var badPage=$(".badPage").text();
			if(parseInt(badPage)*5>=badSize){
				$(".badNext").addClass("disa");
				$(".badNext").attr({"disabled":"disabled"});
			}else{
				$(".badNext").removeClass("disa");
				$(".badNext").removeAttr("disabled");
			}	
		}
	}
	
	function downLoad(part,event,id,counter,cType){
	    $(part).remove();
	    $.ajax({
            type: 'GET',
            url: '${ctx}/web/stock/moreStockCommentList',
            data:{id:${id}, page:counter-1, type:cType},
            dataType: 'json',
            success: function(data){
             var length=data.lists.length;
             //判断是否有数据
		        if(length==0){
		        	$(id).show();
		        }else{
			        var result = '';
	                var arrText = [];
	                if(cType == 3){
	                	arrText.push("<div class='goodListAll' >");
	                }else if(cType == 0){
	                	arrText.push("<div class='goodListGood' >");
	                }else if(cType == 1){
	                	arrText.push("<div class='goodListMid' >");
	                }else if(cType == 2){
	                	arrText.push("<div class='goodListBad' >");
	                }
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
	                }
	                arrText.push("</table>");
	                result +=  arrText.join('');
	                $(event).prepend(result);
		      };//if end
            },//success结束
            error: function(xhr, type){
                alert('Ajax error下拉!');
                // 即使加载出错，也得重置
            }  
        });//ajax结束
	};

	 </script>
	 