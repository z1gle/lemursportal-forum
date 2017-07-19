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
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1" class=""></li>
    <li data-target="#myCarousel" data-slide-to="2" class=""></li>
    <li data-target="#myCarousel" data-slide-to="3" class=""></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active"> 
		<!--img src="/lemursPortal/resources/images/0270_ruffed_lemur.jpg" alt="Sauvons les l&eacute;muriens &agrave; travers des &eacute;changes/partages de connaissances et de comp&eacute;tences"-->
		<div class="bg-img" style="height: 430px;background-image: url(/lemursPortal/resources/images/0270_ruffed_lemur.jpg);">&nbsp;</div>
        <div class="carousel-caption">
        <p>Sauvons les l&eacute;muriens &agrave; travers des &eacute;changes/partages de connaissances et de comp&eacute;tences</p>
        </div>
    </div>
    <div class="item"> 
		<!--img src="/lemursPortal/resources/images/Baby_Propithecus_diadema_Waking_Up.jpg" data-src="" alt="" style="height: 430px"-->
		<div class="bg-img" style="height: 430px;background-image: url(/lemursPortal/resources/images/Baby_Propithecus_diadema_Waking_Up.jpg);">&nbsp;</div>
		<div class="carousel-caption">
		  <h2>Les l&eacute;muriens</h2>
          <p>Primates embl&eacute;matiques de Madagascar  dont 94 % des esp&egrave;ces sont menac&eacute;es d'extinction</p>
        </div>
    </div>
    <div class="item"> 
		<!--img src="/lemursPortal/resources/images/Bandro_Alice_Smith.jpg" data-src="" alt=""-->
		<div class="bg-img" style="height: 430px;background-image: url(/lemursPortal/resources/images/Bandro_Alice_Smith.jpg);">&nbsp;</div><div class="carousel-caption">
		  <h2>Le portail des l&eacute;muriens</h2>	
		  <p>Un appel &agrave; l'unit&eacute; pour sauver notre identit&eacute; </p>
        </div>
    </div>
    <div class="item"> 
		<!--img src="/lemursPortal/resources/images/Varikamena_Inaki_Relanzon.jpg" data-src="" alt=""-->
		<div class="bg-img" style="height: 430px;background-image: url(/lemursPortal/resources/images/Varikamena_Inaki_Relanzon.jpg);">&nbsp;</div><div class="carousel-caption">
        <div class="carousel-caption">
		<h2>Vision</h2>
		<p>Madagascar sera un pays de l'excellence ou s'instaure la protection et la conservation durable des l&eacute;muriens</p>
        </div>
    </div>
  </div>
  <a class="al-left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-menu-left"></span></a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-menu-right"></span></a>
</div>
</div>
</div>


