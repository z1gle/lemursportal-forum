<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<c:set var="req" value="${pageContext.request}" />

<!-- Navigation -->
<nav id="navigation" class="navbar navbar-inverse navbar-fixed-top animated-header" role="navigation">
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
            <a class="navbar-brand"  href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive" src="${resourcesPath}/images/logo-lemursportal.png" alt="Lemurs Portal"/></a>
            <div class="dropdown-menu" role="menu" style="top: 0px; left: -35px; height: 96px; background-color: #ffffff00;">
                <style>                    
                    .spec {
                        color: #a38000!important;
                    }
                    .spec:hover {
                        color: gray!important;
                    }                    
                </style>
                <a style="position: absolute;left: 165px;top: 0px;border-style: solid;border-bottom-color: rgba(0, 0, 0, 0.8);width: 50px;background-color: rgba(0, 0, 0, 0.9);border-radius: 5px!important;" class="btn spec btn-default" href="/"><img style="max-height: 34px;margin-top: -5px;" src="${resourcesPath}/images/logo-lemursportal.png" alt="Lemurs Portal"/></a>
                <a style="position: absolute;left: 220px;top: 0px;border-style: solid;border-bottom-color: rgba(0, 0, 0, 0.8);width: 50px;background-color: rgba(0, 0, 0, 0.9);border-radius: 5px!important;" class="btn spec" href="https://www.lemursportal.org/species"><i class="fa fa-database fa-2x"></i></a>                            
            </div>
        </div>
        <!-- F menu mobile  -->

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav top-nav navbar-right">
                <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
                <c:choose>
                    <c:when test="${isLoggedInUser}">
                        <li>
                            <c:url var="viewProfilUrl" value="/user/profil"></c:url>
                            <a href="${viewProfilUrl}">
                                <user:profilImage src="${currentUser.photoProfil}" cssClass="img-circle"/>
                                <%-- 		                        <spring:message code="home.monprofile"/> --%>
                                <sec:authentication property="principal.username" />
                            </a>
                        </li>
                        <li class="dropdown">
                            <c:url value="/secured/notification/list" var="notificationListUrl"/>
                            <a href="${notificationListUrl}" class="notif"><spring:message code="home.notification"/><span class="rond">
                                    <c:out value="${nombreNotification}"/></span></a>
                                    <%-- 		                        <a href="${notificationListUrl}" class="notif dropdown-toggle" data-toggle="dropdown"><spring:message code="home.notification"/><span class="rond"> --%>
                                    <%-- 		                        <c:out value="${nombreNotification}"></c:out></span></a> --%>
                            <!-- 		                        <ul class="dropdown-menu" role="menu"> -->
                            <!-- 		                        	<li>Vous avez 1 réponse(s)</li> -->
                            <!-- 		                            <li>Vous êtes maintenant devenu modérateur</li> -->
                            <!-- 		                            <li>3 lecture(s) de votre question</li> -->
                            <!-- 		                            <li>Votre question a été validée</li> -->
                            <!-- 		                            <li>Vous avez 36 réponse(s)</li> -->
                            <!-- 		                            <li>Vous êtes maintenant devenu modérateur</li> -->
                            <!-- 		                            <li>8 lecture(s) de votre question</li> -->
                            <!-- 		                            <li>Votre question a été validée</li> -->
                            <!-- 		                            <li>Vous avez 11 réponse(s)</li> -->
                            <!-- 		                            <li>Vous êtes maintenant devenu modérateur</li> -->
                            <!-- 		                            <li>45 lecture(s) de votre question</li> -->
                            <!-- 		                            <li>Votre question a été validée</li> -->
                            <!-- 		                        </ul> -->
                        </li>

                        <li class="dropdown">
                            <c:url value="/secured/pmessage/list" var="listMessagePriveUrl"/>
                            <a class="notif" href="${listMessagePriveUrl}"><spring:message code="home.messages"/><span class="rond"><c:out value="${nombrePrivateMessage}"/></span></a>
                                <%-- 		                        <a class="notif dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="home.messages"/><span class="rond"><c:out value="${nombrePrivateMessage}"/></span></a> --%>
                            <!-- 		                        <ul class="dropdown-menu" role="menu"> -->
                            <!-- 		                        	<li><a href="#">Bonjour, je suis...</a></li> -->
                            <!-- 		                            <li><a href="#">Veuillez marquer votre sujet...</a></li> -->
                            <!-- 		                            <li><a href="#">Madame, suite à votre visite...</a></li> -->
                            <!-- 		                        </ul> -->
                        </li>

                        <li>
                            <span style="display:none;">
                                <c:url value="/logout" var="logoutUrl" />
                                <form action="${logoutUrl}" method="post" id="logoutForm">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}" />
                                </form>
                                <!--                                 verification dev mode -->
                                <c:set var="port" value="" />
                                <c:if test="${req.getServerPort() != '80' || req.getServerPort() != '443'}">
                                    <c:set var="port" value=":${req.getServerPort()}" />
                                </c:if>
                                <script>
                                    function formSubmit() {
                                        //logout also species databases by Zacharie
                                        $.ajax({
                                            type: 'post',
                                            url: '${req.getScheme()}://${req.getServerName()}${port}/species/logout',
                                            success: function (json) {
                                                document.getElementById("logoutForm").submit();
                                            }
                                        });
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
                    <c:url value="/" var="baseUrl"/>
                    <page:lang/>
                    <!-- 						<div class="lang"> -->
                    <!-- 						    <select onchange="javascript:alert(this.value);"> -->
                    <%-- 						        <option value="fr"><spring:message code="global.lang.french"/></option> --%>
                    <%-- 						        <option value="en"><spring:message code="global.lang.english"/></option> --%>
                    <%-- 						        <option value="mg"><spring:message code="global.lang.malagasy"/></option> --%>
                    <!-- 						    </select> -->
                    <!-- 						</div> -->
                </li>
            </ul>

            <div class="clear"></div>

            <ul class="nav navbar-nav navbar-left">
                <li>
                    <a href="${homePage}"><spring:message code="home.menu.questions"/></a>
                </li>
                <li>
                    <c:url value="/documents" var="documentsUrl"/>
                    <a href="${documentsUrl}"><spring:message code="home.menu.documents"/></a>
                </li>
                <li>
                    <c:url value="/experts" var="expertsUrl"/>
                    <a href="${expertsUrl}"><spring:message code="home.menu.experts"/></a>
                </li>
                <li>
                    <c:url value="/formation/" var="formationsUrl"/>
                    <a href="${formationsUrl}"><spring:message code="home.menu.formations"/></a>
                </li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li>
                        <c:url value="/admin/user/list" var="userAdminitrationUrl"/>
                        <a href="${userAdminitrationUrl}">Roles des Utilisateurs</a>
                    </li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <c:url value="documents" var="formAction"></c:url>
                    <form  class="create-quest-form"  action="${formAction}"  method="GET"   >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="input-group">
                            <spring:message code="home.search.placeholder" var="searchPlaceholder"/>
                            <input type="text" name="search" class="form-control" placeholder="${searchPlaceholder}"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><spring:message code="home.search.btn" /></button>
                            </span>
                        </div>
                    </form>
                </li>
            </ul>

        </div>
    </div>
</nav>
