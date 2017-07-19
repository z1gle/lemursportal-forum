<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="signup.title"></spring:message></title>
<link href="${resourcesPath}/css/styles.css" rel="stylesheet"/>
<link href="${resourcesPath}/bootstrap/bootstrap.css" rel="stylesheet"/>
<script src="${resourcesPath}/js/script.js"></script>
<script src="${resourcesPath}/bootstrap/bootstrap.js"></script>
</head>

<body>
    <div class="container">
        <div class="clear"></div>
    	<div class="row">
            <div class="inscript-page" align="center">
              <img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" border="0"/>
              <p class="connexion-rs"><spring:message code="login.connect.with"/> :</p>
              <a href="${pageContext.request.contextPath}/auth/facebook?scope=email,user_about_me,profile"><img src="${resourcesPath}/images/icon-fb.png" border="0"></a>
              <a href="${pageContext.request.contextPath}/auth/twitter?scope=email,user_about_me,profile"><img src="${resourcesPath}/images/icon-tw.png" border="0"></a>
              <form action="${pageContext.request.contextPath}/auth/google" method="POST" style="display: inline;">
				<button type="submit" style="background: none; padding: 0px; margin: 0px;">
					<img src="${resourcesPath}/images/icon-gplus.png" border="0">
				</button>
					<input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/plus.login" />
					<input type="hidden" name="access_type" value="offline"/>
			  </form>
<%--               <a href="${pageContext.request.contextPath}/auth/google?scope=https://www.googleapis.com/auth/userinfo.email,https://www.googleapis.com/auth/userinfo.profile"><img src="${resourcesPath}/images/icon-gplus.png" border="0"></a> --%>
			  <form action="${pageContext.request.contextPath}/auth/yahoo" method="POST" style="display: inline;">
				<button type="submit" style="background: none; padding: 0px; margin: 0px;">
					<img src="${resourcesPath}/images/icon-yahoo.png" border="0">
				</button>
					<input type="hidden" name="scope" value="email" />
			  </form>
<%--               <a href="${pageContext.request.contextPath}/auth/yahoo"><img src="${resourcesPath}/images/icon-yahoo.png" border="0"></a> --%>
              <div class="form">
              <c:url var="userInfoFormAction" value="/register"/>
				<form:form modelAttribute="registrationForm" cssClass="register-form" method="POST" action="${userInfoFormAction}">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<c:if test="${registrationForm.socialProvider != null}">
                        <form:hidden path="socialProvider"/>
                        <form:hidden path="photoProfil"/>
                    </c:if>
					<form:hidden path="id"/>
						<spring:message code="signup.name.placeholder" var="namePlaceholder"/>
	              		<form:input path="nom" placeholder="${namePlaceholder}" cssErrorClass="error"/><form:errors path="nom"/>
	              		<spring:message code="signup.surname.placeholder" var="surnamePlaceholder"/>
	              		<form:input path="prenom" placeholder="${surnamePlaceholder}" cssErrorClass="error"/><form:errors path="prenom"/>
	              		<spring:message code="signup.email.placeholder" var="emailPlaceholder"/>
	              		<form:input path="email" placeholder="${emailPlaceholder}" cssErrorClass="error"/><form:errors path="email"/>
	              		<c:if test="${registrationForm.socialProvider == null}">
	              		<spring:message code="signup.dateofbirth.placeholder" var="dobPlaceholder"/>
	              		<form:input path="dateNaissance" placeholder="${dobPlaceholder} (${dateFormat})" cssErrorClass="error"/><form:errors path="dateNaissance"/>
<%-- 	              		<spring:message code="signup.login.placeholder" var="loginPlaceholder"/> --%>
<%-- 	              		<form:input path="login" placeholder="${loginPlaceholder}" cssErrorClass="error"/><form:errors path="login"/> --%>
	              		<spring:message code="signup.password.placeholder" var="passwordPlaceholder"/>
	              		<form:password path="password"  placeholder="${passwordPlaceholder}" cssErrorClass="error"/><form:errors path="password"/>
	              		<spring:message code="signup.confirmpassword.placeholder" var="confirmPwdPlaceholder"/>
	              		<form:password path="passwordConfirm"  placeholder="${confirmPwdPlaceholder}" cssErrorClass="error"/><form:errors path="passwordConfirm"/>
	              		</c:if>
	              		<button type="submit"><spring:message code="signup.btn.signup" /></button>
              	</form:form>
              </div>
            </div>
        </div>
    </div>
</body>
</html>
