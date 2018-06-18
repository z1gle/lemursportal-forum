<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="/resources" var="resourcesPath"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="login.title"/></title>
<link href="${resourcesPath}/css/styles.css" rel="stylesheet">
<link href="${resourcesPath}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<script src="${resourcesPath}/js/jquery-1.12.4.min.js"></script>
<script src="${resourcesPath}/bootstrap/js/bootstrap.js"></script>
<script src="${resourcesPath}/js/validator.min.js"></script>
<style >
.form-group, .form-group li {
	color: red !important;
    margin-bottom: 5px;
    font-family: montserratlight;
    font-size: 13px;
    text-align: left;
}
.form-group input, .form input {
	font-family: 'montserratregular';
   /* outline: 0;*/
    background-color: #fff!important;
    border: 1px solid #ccc/*#a38000*/;
    width: 100%;
    padding: 20px !important;
    box-sizing: border-box;
    font-size: 14px;
    border-radius: 0px;
}
.has-error input {
    /*border-color: #a94442*/;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    border: 1px solid red !important;
    border-
}
</style>
</head>

<body>
    <div class="container">
        <div class="clear"></div>
    	<div class="row">
            <div class="login-page" align="center">
              <img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" border="0"/>
<%--               <a href="${pageContext.request.contextPath}/auth/yahoo"><img src="${resourcesPath}/images/icon-yahoo.png" border="0"></a> --%>
              <div class="form" style="background: white; margin-top: 25px;">
              	<p style="font-size: 12px;margin-bottom: 0 !important">${successMessage}</p>
              	<div style="color: red;font-size: 12px">${errorMessage}</div>
                <br>
                <form class="login-form" action="<c:url value='/reset' />" method="POST" data-toggle="validator">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  	<c:if test = "${empty errorMessage}">
<%--                   	<spring:message code="signup.password.placeholder" var="passwordPlaceholder"/> --%>
<%--                   	<input class="password" style="border: 1px solid #ccc/*#a38000*/;" type="password" name="password" placeholder="${passwordPlaceholder}"/> --%>
                  
<%--                   	<spring:message code="signup.confirmpassword.placeholder" var="confirmPwdPlaceholder"/> --%>
<%--                   	<input class="passwordConfirm" style="border: 1px solid #ccc/*#a38000*/;" type="password" name="passwordConfirm" placeholder="${confirmPwdPlaceholder}"/> --%>
                  
                  	<div class="form-group">
							<h4><form:errors path="password" class="label label-danger"></form:errors></h4>
							<div class="help-block with-errors"></div>
								<input type="password" name="password" id="password" class="form-control"
									data-minlength="7" maxlength="60"
									placeholder="<spring:message code="signup.password.placeholder" />" 
									data-error="<spring:message code="validation.diff.passwordconfirm" />">
						</div>
						
						<!-- Confirm password -->
						<div class="form-group">
							<h4><form:errors path="password" class="label label-danger"></form:errors></h4>
							<div class="help-block with-errors"></div>
							<input type="password" class="form-control" maxlength="60" 
								placeholder="<spring:message code="signup.confirmpassword.placeholder" />"
								data-match="#password"
								required data-match-error="<spring:message code="validation.diff.passwordMatch" />">
						</div>
						
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
