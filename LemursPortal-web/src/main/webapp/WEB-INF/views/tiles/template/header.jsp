<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>

<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- D menu mobile  -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"><spring:message code="home.togglenavigation"/></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <c:url value="/" var="homePage"/>
                <a class="navbar-brand" href="${homePage}"><img class="img-responsive" src="${resourcesPath}/images/logo-lemurs.png" alt="Lemurs Portal"/></a>
            </div>
            <!-- F menu mobile  -->
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav top-nav navbar-right">
                    <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
                    <c:choose>
                    	<c:when test="${isLoggedInUser}">
	                    	<li>
	                    		<c:url var="viewProfilUrl" value="/user/profil"></c:url>
		                        <a href="${viewProfilUrl}"><img class="img-circle" src="${resourcesPath}/images/user1.png"/><spring:message code="home.monprofile"/></a>
		                    </li>
                    		<li class="dropdown">
		                        <a href="#" class="notif dropdown-toggle" data-toggle="dropdown"><spring:message code="home.notification"/><span class="rond">12</span></a>
		                        <ul class="dropdown-menu" role="menu">
		                        	<li>Vous avez 1 réponse(s)</li>
		                            <li>Vous êtes maintenant devenu modérateur</li>
		                            <li>3 lecture(s) de votre question</li>
		                            <li>Votre question a été validée</li>
		                            <li>Vous avez 36 réponse(s)</li>
		                            <li>Vous êtes maintenant devenu modérateur</li>
		                            <li>8 lecture(s) de votre question</li>
		                            <li>Votre question a été validée</li>
		                            <li>Vous avez 11 réponse(s)</li>
		                            <li>Vous êtes maintenant devenu modérateur</li>
		                            <li>45 lecture(s) de votre question</li>
		                            <li>Votre question a été validée</li>
		                        </ul>
		                    </li>
		                    <li class="dropdown">
		                        <a class="notif dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="home.messages"/><span class="rond">3</span></a>
		                        <ul class="dropdown-menu" role="menu">
		                        	<li><a href="#">Bonjour, je suis...</a></li>
		                            <li><a href="#">Veuillez marquer votre sujet...</a></li>
		                            <li><a href="#">Madame, suite à votre visite...</a></li>
		                        </ul>
		                    </li>
                    		<li>
                    			<span style="display:none;">
                    			<c:url value="/logout" var="logoutUrl" />
								<form action="${logoutUrl}" method="post" id="logoutForm">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
								<script>
									function formSubmit() {
										document.getElementById("logoutForm").submit();
									}
								</script>
								</span>
                    			<a href="javascript:formSubmit();"><spring:message code="home.logout"/></a>
                    		</li>
                    	</c:when>
                    	<c:otherwise>
                    		<c:url value="/signup" var="signupUrl"/>
                    		<li><a href="${signupUrl}"><spring:message code="login.signup"/></a></li>
                    		<c:url value="/login" var="loginUrl"/>
                    		<li><a href="${loginUrl}"><spring:message code="home.login"/></a></li>
                    	</c:otherwise>
                    </c:choose>
                    <li>
						<div class="lang">
						    <select onchange="javascript:alert(this.value);">
						        <option value="fr"><spring:message code="global.lang.french"/></option>
						        <option value="en"><spring:message code="global.lang.english"/></option>
						        <option value="mg"><spring:message code="global.lang.malagasy"/></option>
						    </select>
						</div>
                    </li>
                </ul>
                
                <div class="clear"></div>
                
                <ul class="nav navbar-nav navbar-left">
                	<li>
                        <a href="#"><spring:message code="home.menu.questions"/></a>
                    </li>
                    <li>
                        <a href="#"><spring:message code="home.menu.documents"/></a>
                    </li>
                    <li>
                        <a href="#"><spring:message code="home.menu.experts"/></a>
                    </li>
                    <li>
                        <a href="#"><spring:message code="home.menu.formations"/></a>
                    </li>
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
                	<li>
                        <div class="input-group">
                        <spring:message code="home.search.placeholder" var="searchPlaceholder"/>
                          <input type="text" class="form-control" placeholder="${searchPlaceholder}"/>
                          	<span class="input-group-btn">
                            <button class="btn btn-default" type="button"><spring:message code="home.search.btn" /></button>
                          </span>
                        </div>
                    </li>
                </ul>
               
            </div>
        </div>
    </nav>