<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><tiles:getAsString name="title" /></title>
	<link href="${resourcesPath}/css/styles.css" rel="stylesheet"/>
	<link href="${resourcesPath}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<!--link href="${resourcesPath}/css/img_modal.css" rel="stylesheet"/-->
	<script src="${resourcesPath}/js/js_modal.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
	<script src="${resourcesPath}/js/script.js"></script>
	<script src="${resourcesPath}/bootstrap/js/bootstrap.js"></script>
	<script src="${resourcesPath}/js/jquery.showmore.js"></script>
	
	<tiles:insertAttribute name="head" />
	
</head>
 
<body class="int">
	<header id="header">
		<tiles:insertAttribute name="header" />
	</header>
	<tiles:insertAttribute name="body" />
	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
	
	<tiles:insertAttribute name="body.end" />
</body>
<!--script src="${resourcesPath}/bootstrap/js/bootstrap.min.js"></script-->
<script>
jQuery(document).ready(function(){
				
	jQuery(".morea").showmore({
		
		childElement:"a",
		visible : 5,
		showMoreText : "<span class='more'>...</span>",
	   showLessText : "<span class='more'>-</span>",
		
	});
	
});
</script>
</html>