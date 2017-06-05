<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:out value="${thematique.libelle}"></c:out></title>
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
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img class="img-responsive" src="${resourcesPath}/images/logo-lemurs.png" alt="Lemurs Portal"></a>
            </div>
            <!-- F menu mobile  -->
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav top-nav navbar-right">
                	<li>
                        <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png"> Mon profil</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="notif dropdown-toggle" data-toggle="dropdown">Notifications<span class="rond">12</span></a>
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
                        <a class="notif dropdown-toggle" data-toggle="dropdown" href="#">Messages<span class="rond">3</span></a>
                        <ul class="dropdown-menu" role="menu">
                        	<a href="#"><li>Bonjour, je suis...</li></a>
                            <a href="#"><li>Veuillez marquer votre sujet...</li></a>
                            <a href="#"><li>Madame, suite à votre visite...</li></a>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Se deconnecter</a>
                    </li>
                    <li>
                    	<div class="lang">
                        <form>
                            <select>
                                <option>Français</option>
                                <option>English</option>
                                <option>Malagasy</option>
                            </select>
                        </form>
                        </div>
                    </li>
                </ul>
                
                <div class="clear"></div>
                
                <ul class="nav navbar-nav navbar-left">
                	<li>
                        <a href="#">Questions</a>
                    </li>
                    <li>
                        <a href="#">Documents</a>
                    </li>
                    <li>
                        <a href="#">Experts</a>
                    </li>
                    <li>
                        <a href="#">Formations</a>
                    </li>
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
                	<li>
                         <c:url value="/post/search" var="formAction"></c:url>
                	<form  class="create-quest-form"  action="${formAction}"  method="post"   >
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="input-group">
                        <spring:message code="home.search.placeholder" var="searchPlaceholder"/>
                          <input type="text" name="pattern" class="form-control" placeholder="${searchPlaceholder}"/>
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
</header>
<div class="slider-top">
	
</div>
<div class="container lemurs-page">
    <div class="row">
        <div class="col-md-9">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container liste-quest">
    
                    <div class="forum-title">
                        <div class="pull-right forum-desc">
                            <a class="add-quest">Poser une question</a>
                        </div>
                        
                    </div>
    				
                    <!-- D Sujet -->
                   <c:forEach items="${posts.content}" var="post">
    					<div class="forum-item">
	                        <div class="row">
	                            <div class="col-md-8">
	                                <div class="forum-profil">
	                                  <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/></a>
	                                  <div class="reponse-user"><a href="#"><c:out value="${post.owner.nom}"/> <c:out value="${post.owner.prenom}"/></a><br/><i></i></div>
	                                </div>
	                                <a href="forum_post.html" class="forum-item-title"><c:out value="${post.title}" /></a>
	                                <div class="forum-sub-title">
	                                	<c:out value="${post.body}" escapeXml="true" />
	                                    <p class="forum-date"><fmt:formatDate pattern="${datetimeFormat}" value="${post.creationDate}"/></p>
	                                </div>
	                            </div>
	                            <div class="col-md-1 forum-info">
	                                
	                            </div>
	                            <div class="col-md-3 forum-user-info">
	                              
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
                <h2 class="thema">Thematiques</h2>
            </div>
            <div class="list-group people-group thematique">
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Santé
                        </div>
                        <div class="pull-right">
                            19
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Financement
                        </div>
                        <div class="pull-right">
                            4987
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Statistiques
                        </div>
                        <div class="pull-right">
                            3
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Localisation
                        </div>
                        <div class="pull-right">
                            82
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Visite
                        </div>
                        <div class="pull-right">
                            785
                        </div>
                    </div>
                </a>
                            
            </div>
            <!-- F Thematiques -->
            
            <!-- D Questions -->
            <div class="sidebar-title">
                <h2 class="last-quest">Dernières questions</h2>
            </div>
            <div class="list-group people-group thematique">
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Lorem ipsum dolor
                        </div>
                        <div class="pull-right">
                            &gt;
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Lorem ipsum dolor
                        </div>
                        <div class="pull-right">
                            &gt;
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Luas qui tenent, eruditi app...
                        </div>
                        <div class="pull-right">
                            &gt;
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Mensarum enim voragines... 
                        </div>
                        <div class="pull-right">
                            &gt;
                        </div>
                    </div>
                </a>
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            Lorem ipsum dolor not si amet
                        </div>
                        <div class="pull-right">
                            &gt;
                        </div>
                    </div>
                </a>
                
            </div>
            <!-- F Questions  -->
			
            <!-- D Membres -->
            <div class="sidebar-title">
                <h2 class="users-connect">Membres connectés</h2>
            </div>
            <div class="membres-connectes">

            <div class="morea list-group people-group">
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br>Visiteur
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
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br>Visiteur
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
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br>Visiteur
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
                
                <a href="#" class="list-group-item">
                    <div class="media">
                        <div class="pull-left">
                            <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                            <div class="notif-connect"></div>
                        </div>
                        <div class="media-body reponse-user">
                            <span>Lucas Artigo</span>
                            <br>Visiteur
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
                <h2 class="vidaka">Photos / Vidéos</h2>
            </div>
            <div class="list-group people-group photos-videos">
                <div class="popup-gallery">
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""></a>
                    <a href="#"><div class="video"></div><img src="${resourcesPath}/images/lem.png" alt=""></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""></a>
                    <a href="#"><div class="video"></div><img src="${resourcesPath}/images/lem.png" alt=""></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""></a>
                    <a href="#"><img src="${resourcesPath}/images/lem.png" alt=""></a>
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
                    	<li><a href="#">Accueil</a></li>
                        <li><a href="#">Questions</a></li>
                        <li><a href="#">Documents</a></li>
                        <li><a href="#">Experts</a></li>
                    </ul>
                </div>
                <div class="col-xs-5">
                    <ul>
                    	<li><a href="#">Formations</a></li>
                        <li><a href="#">Aide</a></li>
                        <li><a href="#">Mentions légales</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
        	<div class="row">
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part1.png" alt=""></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img  src="${resourcesPath}/images/part2.png" alt=""></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img  src="${resourcesPath}/images/part3.png" alt=""></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part4.png" alt=""></a>
                </div>
                <div class="col-xs-2">
                    <a href="#"><img src="${resourcesPath}/images/part5.png" alt=""></a>
                </div>
            </div>
        </div>
 	</div>
</div> 
<div class="copy">Copyright - Lemurs Portal 2017</div>  
</footer>
<script src="bootstrap/js/bootstrap.min.js"></script>
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
