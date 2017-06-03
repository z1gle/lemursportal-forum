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
<%--	
<div id="myCarousel" class="carousel slide" data-ride="carousel"> 
  <!-- Indicators -->
  
  <ol class="carousel-indicators">
    <li data-target="#slider-lemurs" class="active"></li>
    <li data-target="#slider-lemurs"></li>
    <li data-target="#slider-lemurs"></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active"> <img src="${resourcesPath}/images/slide-1.jpg" style="width:100%" alt="">
        <div class="carousel-caption">
          <h1>Lorem ipsum</h1>
          <p>Aenean a rutrum nulla. Vestibulum a arcu at nisi tristique pretium. Donec sit amet mi imperdiet mauris viverra accumsan ut at libero.</p>
        </div>
    </div>
    <div class="item"> <img src="${resourcesPath}/images/slide-2.jpg" style="width:100%" data-src="" alt="">
        <div class="carousel-caption">
          <h1>Lorem ipsum dolor not si amet</h1>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae egestas purus. Donec sit amet mi imperdiet mauris viverra accumsan ut at libero.</p>
        </div>
    </div>
    <div class="item"> <img src="${resourcesPath}/images/slide-3.jpg" style="width:100%" data-src="" alt="">
        <div class="carousel-caption">
		  <h1>Donec sit amet</h1> 
          <p>Donec sit amet mi imperdiet mauris viverra accumsan ut at libero. Donec sit amet mi imperdiet mauris viverra accumsan ut at libero.</p>
        </div>
    </div>
  </div>
  <a class="left carousel-control" href="#slider-lemurs" data-slide="prev"><span class="glyphicon glyphicon-menu-left"></span></a> <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-menu-right"></span></a> </div>
 --%>

</div>