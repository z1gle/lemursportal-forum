<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="home.title"/></title>
<link href="${resourcesPath}/css/styles.css" rel="stylesheet"/>
<link href="${resourcesPath}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<script src="${resourcesPath}/js/jquery-1.12.4.min.js"></script>
<script src="${resourcesPath}/js/script.js"></script>
<script src="${resourcesPath}/bootstrap/js/bootstrap.js"></script>
<script src="${resourcesPath}/js/jquery.showmore.js"></script>
</head>

<body class="int">
<header>

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
                <a class="navbar-brand" href="#"><img class="img-responsive" src="${resourcesPath}/images/logo-lemurs.png" alt="Lemurs Portal"/></a>
            </div>
            <!-- F menu mobile  -->
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav top-nav navbar-right">
                	<c:if test="${isAuthenticated}">
                		<li>
	                        <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png"/><spring:message code="home.monprofile"/></a>
	                    </li>
                	</c:if>
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
                    <c:choose>
                    	<c:when test="${isAuthenticated}">
                    		<a href="#"><spring:message code="home.logout"/></a>
                    	</c:when>
                    	<c:otherwise>
                    		<c:url value="/login" var="loginUrl"/>
                    		<a href="${loginUrl}"><spring:message code="home.login"/></a>
                    	</c:otherwise>
                    </c:choose>
                    </li>
                    <li>
    					<jsp:include page="inc/lang-chooser.jsp"/>
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
</header>
<div class="slider-top">
	
</div>
<div class="container lemurs-page">
    <div class="row">
        <div class="col-md-9">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container">
    
                    <div class="forum-title">
                        <div class="pull-right forum-desc">
                            <a class="add-quest"><spring:message code="home.ask.question"/></a>
                        </div>
                        <h2><spring:message code="home.topquestions"/></h2>
                    </div>
    				<!-- D Sujet -->
    				<c:forEach items="${topQuestions}" var="topQuestion">
    					<div class="forum-item">
	                        <div class="row">
	                            <div class="col-md-8">
	                                <div class="forum-profil">
	                                  <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/></a>
	                                  <div class="reponse-user"><a href="#"><c:out value="${topQuestion.question.owner.nom}"/> <c:out value="${topQuestion.question.owner.prenom}"/></a><br/><i><c:out value="${question.role}"/></i></div>
	                                </div>
	                                <a href="forum_post.html" class="forum-item-title"><c:out value="${topQuestion.question.title}" /></a>
	                                <div class="forum-sub-title">
	                                	<c:out value="${topQuestion.question.body}" escapeXml="true" />
	                                    <p class="forum-date"><fmt:formatDate pattern="${datetimeFormat}" value="${topQuestion.question.creationDate}"/></p>
	                                </div>
	                            </div>
	                            <div class="col-md-1 forum-info">
	                                <span class="views-number">
	                                    <c:out value="${topQuestion.nbReponse}"/>
	                                </span>
	                                <div class="vue">
	                                    <small><c:out value="${topQuestion.nbVue}"/> <spring:message code="home.topquestions.vues"/></small>
	                                </div>
	                            </div>
	                            <div class="col-md-3 forum-user-info">
	                               <a href="#"><img class="img-circle" src="${resourcesPath}/images/user2.png" alt=""/></a>
	                               <div class="reponse-user"><a href="#">
	                               <c:out value="${topQuestion.derniereReponse.owner.nom}"/> <c:out value="${topQuestion.derniereReponse.owner.prenom}"/>
	                               </a><br/><fmt:formatDate pattern="${datetimeFormat}" value="${topQuestion.derniereReponse.creationDate}"/><br/>
	                               	<c:forEach items="${topQuestion.derniereReponse.owner.roles}" var="role">    
									    <i><c:out value="${role.libelle}"/></i><br/>
									</c:forEach>
	                               </div>
	                            </div>
	                            
	                        </div>
	                    </div>
    				</c:forEach>
                    <!-- F Sujet -->
                    
                    <!-- D Pagination -->
                    <ul class="pagination">
                        <li class="disabled"><a href="#">&laquo;</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                    <!-- F Pagination -->
                    
                </div>
            </div>
        </div>
        
        <div class="col-md-3">
        
            <!-- D Thematiques -->
            <div class="sidebar-title">
                <h2 class="thema"><spring:message code="home.thematiques"/></h2>
            </div>
            <div class="list-group people-group thematique">
            	<c:forEach items="${topThematiques}" var="topThematique">
            		<a href="#" class="list-group-item">
	                    <div class="media">
	                        <div class="pull-left">
	                            <c:out value="${topThematique.thematique.libelle}"/>
	                        </div>
	                        <div class="pull-right">
	                            <c:out value="${topThematique.nombreMessage}"/>
	                        </div>
	                    </div>
	                </a>
            	</c:forEach>
            </div>
            <!-- F Thematiques -->
            
            <!-- D Questions -->
            <div class="sidebar-title">
                <h2 class="last-quest"><spring:message code="home.latestquestions"/></h2>
            </div>
            <div class="list-group people-group thematique">
            	<c:forEach items="${lastestPosts}" var="post">
            		<a href="#" class="list-group-item">
	                    <div class="media">
	                        <div class="pull-left">
	                            <c:out value="${post.title}"></c:out>
	                        </div>
	                        <div class="pull-right">
	                            &gt;
	                        </div>
	                    </div>
	                </a>
            	</c:forEach>
            </div>
            <!-- F Questions  -->
			
            <!-- D Membres -->
            <div class="sidebar-title">
                <h2 class="users-connect"><spring:message code="home.membreconnectes"/></h2>
            </div>
            <div class="membres-connectes">

            <div class="morea list-group people-group">
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/>
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user2.png" alt=""/>
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/>
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br/>Visiteur
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br>Visiteur
                        </div>
                    </div>
                </a>
                            
            </div>
            </div>
            <!-- F Membres -->
            
            <!-- D Video -->
            <div class="sidebar-title">
                <h2 class="vidaka"><spring:message code="home.photovideos.title"/></h2>
            </div>
            <div class="list-group people-group photos-videos">
                <div class="popup-gallery">
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                    <a href="#"><div class="video"></div><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                    <a href="#"><div class="video"></div><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""/></a>
                </div>
            </div>
            <!-- F Video -->
            
        </div>
        
        
    </div>
</div>
<footer>
<div class="container">
    <div class="row">
        <div class="col-md-6">
        	<div class="row">
                <div class="col-xs-2">
                    <img src="${resourcesPath}/images/logo-footer.png" alt="">
                </div>
                <div class="col-xs-5">
                    <ul>
                    	<li><a href="#"><spring:message code="home.menu.accueil"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.questions"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.documents"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.experts"/></a></li>
                    </ul>
                </div>
                <div class="col-xs-5">
                    <ul>
                    	<li><a href="#"><spring:message code="home.menu.formations"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.aide"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.mentionslegales"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.contact"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
        	<div class="row">
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part1.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img  src="${resourcesPath}/images/part2.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img  src="${resourcesPath}/images/part3.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part4.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part5.png" alt=""/></a>
                </div>
            </div>
        </div>
 	</div>
</div> 
<div class="copy">Copyright - Lemurs Portal 2017</div>  
</footer>
<script src="${resourcesPath}/bootstrap/js/bootstrap.min.js"></script>
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
</body>
</html>
