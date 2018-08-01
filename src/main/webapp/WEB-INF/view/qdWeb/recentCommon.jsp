<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 自选股票 -->
<td align="left" valign="top" width="338">
	<div class="conceptLeft">
		<img src="${ctx}/web/images_line/leftTop.png" />
		<h4>最近自选股</h4>
		<table class="optional" cellpadding="0" cellspacing="0" width="94%"
			border="0">
			<tr>
				<th align="left">名称&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th align="center">当前价</th>
				<th align="right">涨幅%</th>
			</tr>
		</table>
		<table class="optional" cellpadding="0" cellspacing="0" width="94%" border="0">
			<c:forEach items="${optionalList}" var="optional" varStatus="row">
				<tr>
					<td align="left">${optional.stockInfo.name}</td>
					<c:if test="${optional.stockInfo.liftRate >= 0}">
						<td align="center"><span class="redColr">${optional.stockInfo.currPrice}</span></td>
						<td align="right"><span class="redColr">${optional.stockInfo.liftRate}%</span></td>
					</c:if>
					<c:if test="${optional.stockInfo.liftRate < 0}">
						<td align="center"><span class="qlColr">${optional.stockInfo.currPrice}</span></td>
						<td align="right"><span class="qlColr">${optional.stockInfo.liftRate}%</span></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</td>