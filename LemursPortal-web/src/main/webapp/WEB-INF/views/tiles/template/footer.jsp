<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>

<div class="container">
    <div class="row">
        <div class="col-md-6">
        	<div class="row">
                <div class="col-xs-2">
                    <img src="${resourcesPath}/images/logo-lemurs-blanc.png" style="width: 48px" alt="">
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
        
        <div id="partenaire" class="col-md-6">
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
