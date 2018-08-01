<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored ="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String titleChar = "&nbsp;&nbsp;\\&nbsp;&nbsp;";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="小合智能" />