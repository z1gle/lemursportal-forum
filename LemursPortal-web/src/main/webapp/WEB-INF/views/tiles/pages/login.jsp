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
                                    <form id="form-login" class="login-form" name="loginForm" action="<c:url value='/authenticate' />" method="POST">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <spring:message code="signup.email.placeholder" var="loginPlaceholder"/>
                                            <spring:message code="login.password.placeholder" var="pwdPlaceholder"/>
                                            <input id="email" class="email" style="border: 1px solid #ccc/*#a38000*/;" type="text" name="email" placeholder="${loginPlaceholder}"/>
                                            <input id="pwd" class="pwd"  style="border: 1px solid #ccc/*#a38000*/;" type="password" name="password" placeholder="${pwdPlaceholder}"/>
                                            <button type="submit" style="margin-top: 5px"><spring:message code="login.btn.connect"/></button>
                                            <p class="message">
                                                <c:url value="/signup" var="signupUrl"/>
                                                <c:url value="/forgot" var="forgotUrl"/>
                                                <a href="${signupUrl}" style="float:left"><spring:message code="login.signup"/> ?</a> 
                                                <a href="${forgotUrl}" class="right"><spring:message code="login.forgot.password"/> ?</a></p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
                    <script src="http://malsup.github.com/jquery.form.js"></script> 
                    <script>
                        // wait for the DOM to be loaded 
                        $(document).ready(function () {
                            // bind 'myForm' and provide a simple callback function 
                            $('#form-login').ajaxForm(function () {
                                $.post("http://localhost:8085/lemurs/autentification", { login: $('#email').val(), password: $('#pwd').val() }, function () {
                                    window.history.back();
                                }).fail(function () {
                                    $('.message').html("Erreur lors de l'autentification, veuiller vérifier votre e-mail et mot de passe");
                                });                                
                            });
                        });
                    </script> 
                </body>
                </html>
