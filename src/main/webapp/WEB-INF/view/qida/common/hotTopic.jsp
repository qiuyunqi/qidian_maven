<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 热门话题 -->
<div class="sidebar-left">
	<div class="chartBody">
		<h4>热门话题</h4>
		<div class="zhidao-tags clearfix">
			<ul class="hot-ul">
				<c:forEach items="${qidaTags}" var="tag">
					<li>
						<a href="${ctx}/web/qida/tagsQs/${tag.id}.html" target="_blank">${tag.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
