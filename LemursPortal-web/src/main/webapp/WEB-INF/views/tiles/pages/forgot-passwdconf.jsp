<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="/resources" var="resourcesPath"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="login.title"/></title>
<link href="${resourcesPath}/css/styles.css" rel="stylesheet">
<link href="${resourcesPath}/bootstrap/bootstrap.css" rel="stylesheet">
<script src="${resourcesPath}/js/script.js"></script>
<script src="${resourcesPath}/bootstrap/bootstrap.js"></script>
</head>

<body>
    <div class="container">
        <div class="clear"></div>
    	<div class="row">
            <div class="login-page" align="center">
              <img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" border="0"/>
<%--               <a href="${pageContext.request.contextPath}/auth/yahoo"><img src="${resourcesPath}/images/icon-yahoo.png" border="0"></a> --%>
              <div class="form" style="background: white; margin-top: 25px;">
              	<h1>${successTitle}</h1>
                <p>${successMessage}</p>
                <br>
                  <c:url value="/login" var="loginUrl"/>
                  <a href="${loginUrl}"><spring:message code="login.goto"/></a><br><br><br> 
              </div>
            </div>
        </div>
    </div>
</body>
</html>
