<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    	<jsp:include page="inc/lang-chooser.jsp"/>
        <div class="clear"></div>
    	<div class="row">
            <div class="login-page" align="center">
              <img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" border="0"/>
              <p class="connexion-rs"><spring:message code="login.connect.with"/> :</p>
              <a href="#"><img src="${resourcesPath}/images/icon-fb.png" border="0"></a>
              <a href="#"><img src="${resourcesPath}/images/icon-tw.png" border="0"></a>
              <a href="#"><img src="${resourcesPath}/images/icon-gplus.png" border="0"></a>
              <a href="#"><img src="${resourcesPath}/images/icon-yahoo.png" border="0"></a>
              <div class="form">
                <form class="login-form" name="loginForm" action="<c:url value='/authenticate' />" method="POST">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  <spring:message code="login.login.placeholder" var="loginPlaceholder"/>
                  <spring:message code="login.password.placeholder" var="pwdPlaceholder"/>
                  <input class="email" type="text" name="login" placeholder="${loginPlaceholder}"/>
                  <input class="pwd" type="password" name="password" placeholder="${pwdPlaceholder}"/>
                  <button type="submit"><spring:message code="login.btn.connect"/></button>
                  <p class="message">
                  <c:url value="/signup" var="signupUrl"/>
                  <a href="${signupUrl}" class="left"><spring:message code="login.signup"/> ?</a> 
                  <a href="#" class="right"><spring:message code="login.forgot.password"/> ?</a></p>
                </form>
              </div>
            </div>
        </div>
    </div>
</body>
</html>
