<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>

<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<div class="slider-top">
<div id="myCarousel" class="carousel slide" data-ride="carousel"> 
  <!-- Indicators -->
  
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" class="active"></li>
    <li data-target="#myCarousel" class=""></li>
    <li data-target="#myCarousel" class=""></li>
    <li data-target="#myCarousel" class=""></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active"> <img src="/lemursPortal/resources/images/0270_ruffed_lemur.jpg" style="width:100%" alt="">
        <div class="carousel-caption">
        <p>Sauvons les lémuriens à travers des échanges/partages de connaissances et de compétences</p>
        </div>
    </div>
    <div class="item"> <img src="/lemursPortal/resources/images/Baby_Propithecus_diadema_Waking_Up.jpg" style="width:100%" data-src="" alt="">
        <div class="carousel-caption">
          <p>Les lémuriens : primates emblématiques de Madagascar  dont 94 % des espèces sont menacées d’extinction</p>
        </div>
    </div>
    <div class="item"> <img src="/lemursPortal/resources/images/Bandro_Alice_Smith.jpg" style="width:100%" data-src="" alt="">
        <div class="carousel-caption">
          <p>Le portail des lémuriens : Un appel à l’unité pour sauver notre identité </p>
        </div>
    </div>
    <div class="item"> <img src="/lemursPortal/resources/images/Bandro_Tobias_Nowlan.jpg" style="width:100%" data-src="" alt="">
        <div class="carousel-caption">
   <p>Vision : Madagascar sera un pays de l’excellence ou s’instaure la protection et la conservation durable des lémuriens</p>
        </div>
    </div>
  </div>
  <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-menu-left"></span></a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-menu-right"></span></a>
</div>
</div>