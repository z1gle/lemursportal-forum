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
              	<p>${successMessage}</p>
              	<div style="color: red">${errorMessage}</div>
                <br>
                <form class="login-form" action="<c:url value='/reset' />" method="POST">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  	<c:if test = "${empty errorMessage}">
                  	<spring:message code="signup.password.placeholder" var="passwordPlaceholder"/>
                  	<input class="password" style="border: 1px solid #ccc/*#a38000*/;" type="password" name="password" placeholder="${passwordPlaceholder}"/>
                  
                  	<spring:message code="signup.confirmpassword.placeholder" var="confirmPwdPlaceholder"/>
                  	<input class="passwordConfirm" style="border: 1px solid #ccc/*#a38000*/;" type="password" name="passwordConfirm" placeholder="${confirmPwdPlaceholder}"/>
                  
                  	<input type="hidden" name="token" value="${token}"/>
                  	
                  	<button type="submit" style="margin-top: 5px; margin-bottom: 5px"><spring:message code="login.btn.submit"/></button>
                  	</c:if>
                </form>
              </div>
            </div>
        </div>
    </div>
</body>
</html>
