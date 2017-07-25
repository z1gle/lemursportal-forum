<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lemurs Portal - Poser Question</title>
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
                        <div class="input-group">
                          <input type="text" class="form-control" placeholder="Rechercher...">
                          	<span class="input-group-btn">
                            <button class="btn btn-default" type="button">GO</button>
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
    <!-- Début Ajouter Question -->
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container create-quest">
    
                    <div class="forum-title">
                        <h2>Nouvelle question</h2>
                    </div>
    				<!-- D Poser quest -->
                    <div class="cadre">
                    <c:url value="/secured/post/" var="formAction"></c:url>
                   	  <div class="form">
                        	<form:form  class="create-quest-form" modelAttribute="post" action="${formAction}"  method="POST"   >
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	
                            <div class="row">
                            	<!-- D Ne S'affiche uniquement que pour le moderateur -->
                            	<!--
                                <div class="col-md-6" style="display:block;">
                                  <label>Titre du thématique</label>                                 
                                  <form:input path="title"/>
                                  <label>Description du thématique</label>
                                  <textarea></textarea>
                                </div>
                                -->
                                <!-- F Ne S'affiche uniquement que pour le moderateur -->
                                
                                <div class="col-md-6">
                                  <label>Choisir un thématique <sup>*</sup></label>
                                  <form:select path="thematique">                                  	
                                    <c:forEach items="${listeThematique}" var="item">
							         <option value="<c:out value = "${item.id}"/>"><c:out value = "${item.libelle}"/></option>
							      </c:forEach>
                                  </form:select>
                                  
                                  
                                  <label>Titre de la question <sup>*</sup></label>
                                  <form:input path="title"/>
                                  
                                  <label>Ajouter un fichier (photos, documents, videos, audios...)</label>
                                  <input type="file" class="fisie" />
                                  
                                  <label>Votre question <sup>*</sup></label>                                  
                                  <form:textarea path="body"/>
                                  <form:button value="save">Poster</form:button>
                               </div>
                            </div>
                            </form:form> 
                        </div>
                    </div>
                    <!-- F Poser quest -->
                   
                </div>
            </div>
        </div>    
    <!-- Fin Ajouter Question -->           
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
<!--script src="bootstrap/js/bootstrap.min.js"></script-->
<script>
jQuery(document).ready(function(){
				
	jQuery(".morea").showmore({
		
		childElement:"a",
		visible : 5,
		showMoreText : "<span class='more'>...</span>",
	   showLessText : "<span class='more'>-</span>",
		
	});
	
});
var sprytextarea1 = new Spry.Widget.ValidationTextarea("sprytextarea1");
</script>
</body>
</html>
