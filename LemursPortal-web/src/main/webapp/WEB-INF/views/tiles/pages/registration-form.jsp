<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
                width: 100%;
                /*padding: 20px !important;*/
                box-sizing: border-box;
                font-size: 14px;
                border-radius: 0px;
            }
            .has-error input {
                /*border-color: #a94442*/;
                -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
                box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
                border: 1px solid red;                
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="clear"></div>
            <div class="row">
                <div class="inscript-page" align="center">
                    <a href="//www.lemursportal.org"><img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" border="0"/></a>
                    <p class="connexion-rs"><spring:message code="login.connect.with"/> :</p>
                    <a href="${pageContext.request.contextPath}/auth/facebook?scope=email,user_about_me,profile"><img src="${resourcesPath}/images/icon-fb.png" border="0"></a>
                    <a href="${pageContext.request.contextPath}/auth/twitter?scope=email,user_about_me,profile"><img src="${resourcesPath}/images/icon-tw.png" border="0"></a>
                    <form action="${pageContext.request.contextPath}/auth/google" method="post" style="display: inline;">
                        <button type="submit" style="background: none; padding: 0px; margin: 0px;">
                            <img src="${resourcesPath}/images/icon-gplus.png" border="0">
                        </button>
                        <input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/plus.login" />
                        <input type="hidden" name="access_type" value="offline"/>
                    </form>
                    <%--               <a href="${pageContext.request.contextPath}/auth/google?scope=https://www.googleapis.com/auth/userinfo.email,https://www.googleapis.com/auth/userinfo.profile"><img src="${resourcesPath}/images/icon-gplus.png" border="0"></a> --%>
                    <form action="${pageContext.request.contextPath}/auth/yahoo" method="post" style="display: inline;">
                        <button type="submit" style="background: none; padding: 0px; margin: 0px;">
                            <img src="${resourcesPath}/images/icon-yahoo.png" border="0">
                        </button>
                        <input type="hidden" name="scope" value="email" />
                    </form>
                    <%--               <a href="${pageContext.request.contextPath}/auth/yahoo"><img src="${resourcesPath}/images/icon-yahoo.png" border="0"></a> --%>
                    <div class="form">
                        <c:url var="userInfoFormAction" value="/register"/>
                        <form:form modelAttribute="registrationForm" cssClass="register-form" method="POST" action="${userInfoFormAction}" data-toggle="validator">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <c:if test="${registrationForm.socialProvider != null}">
                                <form:hidden path="socialProvider"/>
                                <form:hidden path="photoProfil"/>
                            </c:if>
                            <form:hidden path="id"/>
                            <!-- nom -->
                            <div class="form-group">
                                <form:errors path="email" class="label label-danger"></form:errors>
                                    <div id="nom-err" class="help-block with-errors"></div>
                                <spring:bind path="nom">
                                    <input id="nom" type="nom" name="nom" class="form-control" maxlength="50"
                                           placeholder="<spring:message code="signup.name.placeholder" />" 
                                           required data-error="<spring:message code="validation.mandatory" />">
                                    </spring:bind>
                            </div>

                            <div class="form-group">
                                <spring:bind path="prenom">
                                    <input id="prenom" type="prenom" name="prenom" class="form-control" maxlength="50"
                                           placeholder="<spring:message code="signup.surname.placeholder" />" />
                                </spring:bind>
                            </div>

                            <!-- email -->
                            <div class="form-group">
                                <form:errors path="email" class="label label-danger"></form:errors>
                                    <div id="email-err" class="help-block with-errors"></div>
                                <spring:bind path="email">
                                    <input id="email" type="email" name="email" class="form-control" maxlength="50"
                                           placeholder="<spring:message code="signup.email.placeholder" />" 
                                           required data-error="<spring:message code="validation.email.format.invalid" />">
                                    </spring:bind>
                            </div>

                            <c:if test="${registrationForm.socialProvider == null}">
                                <%-- 	              		<spring:message code="signup.dateofbirth.placeholder" var="dobPlaceholder"/> --%>
                                <%-- 	              		<form:input path="dateNaissance" placeholder="${dobPlaceholder} (${dateFormat})" cssErrorClass="error"/><form:errors path="dateNaissance"/> --%>
                                <%-- 	              		<spring:message code="signup.login.placeholder" var="loginPlaceholder"/> --%>
                                <%-- 	              		<form:input path="login" placeholder="${loginPlaceholder}" cssErrorClass="error"/><form:errors path="login"/> --%>
                                <!-- Password -->
                                <div class="form-group">
                                    <h4><form:errors path="password" class="label label-danger"></form:errors></h4>
                                        <div class="help-block with-errors"></div>
                                    <spring:bind path="password">
                                        <input type="password" name="password" id="password" class="form-control"
                                               data-minlength="7" maxlength="60"
                                               placeholder="<spring:message code="signup.password.placeholder" />" 
                                               data-error="<spring:message code="validation.diff.passwordconfirm" />">
                                        </spring:bind>
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
                            </c:if>
                                <button id="submit" type="submit"><spring:message code="signup.btn.signup" /></button><br>
                            <p class="message" style="float:left">
                                <c:url value="/signup" var="signupUrl"/>                        
                                <spring:message code="global.text.alreadySignedUp" /><a href="join_us"> <spring:message code="login.signin" /></a>
                            </p>
                        </form:form>
                    </div>                    
                </div>
            </div>

            <%--         <script type="text/javascript" src="<c:url value="/resources/js/app/inputs.js"/>"></script> --%>
            <script>
                // 		    $("#email").keyup(function() {
                // 		    	var isEmailValid = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(String(email).toLowerCase());
                // 				if(!isEmailValid) {
                // 					$("#email-err").text("e-mail invalid").css("color", "red").css("margin-bottom", "5px").css("font-family","montserratlight").css("font-size", "13px").css("text-align","left").show();
                // 					$("#submit").attr("disabled", "disabled");
                // 				} else {
                // 					$("#email-err").text("");	
                // 				}
                // 		    });
                $("#email").blur(function () {
                    email = $("#email").val();
                    var isEmailValid = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(String(email).toLowerCase());
                    if (isEmailValid) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/check-email?email=" + email,
                            // 						cache:  false,
                            success: function (response) {
                                if (response == "false") {
                                    $("#email-err").text("User with this e-mail already exists").css("color", "red").css("margin-bottom", "5px").css("font-family", "montserratlight").css("font-size", "13px").css("text-align", "left").show();
                                    // 								$("#submit").attr("disabled", "disabled");
                                } else if (response == "true") {
                                    $("#email-err").hide();
                                    // 								$("#submit").attr("disabled", "enable");
                                }
                            }
                        });
                    }
                });
            </script>

        </div>
    </body>
</html>
