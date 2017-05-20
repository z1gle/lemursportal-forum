<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!-- Début Ajouter Question -->
<div class="container lemurs-page">
	 <div class="row">
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container page-profil">
    
                    <div class="page-title">
                        <h2 class="profil">Edition Profil</h2>
                    </div>
    				<!-- D Poser quest -->
                    <div class="cadre">
                   	  <div class="form">
                   	  		<c:url value="/user/profil/edit" var="userFormAction"/>
                            <form:form modelAttribute="registrationForm" action="${userFormAction}" method="POST" cssClass="edit-profil-form">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div class="row">
                            	<div class="col-md-4">
                                  <div class="image text-center">
                                    <img src="${resourcesPath}/images/user-profil.png" alt="#" class="img-responsive img-circle">
                                  </div>
                                
<!--                                   <label>Changer mon photo de profil</label> -->
<!--                                   <input type="file" class="pdp" /> -->
                                  
                                </div>
                                	<div class="col-md-4">
	                                	<form:hidden path="id"/>
	                                  	<label>Nom<sup>*</sup></label>
	                                  	<form:input path="nom"/><form:errors path="nom"/>
	                                  	<label>Prénoms<sup>*</sup></label>
	                                  	<form:input path="prenom"/><form:errors path="prenom"/>
	                                  	<label>Date de naissance (<c:out value="${dateFormat}"/>)</label>
	                                  	<form:input path="dateNaissance"/><form:errors path="dateNaissance"/>
	                                  	<label>Adresse email<sup>*</sup></label>
	                                  	<form:input path="email"/><form:errors path="email"/>
                                  	</div>
                                  	<div class="col-md-4">
                                  		<label>Rôle<sup>*</sup></label>
                                  		<sec:authentication property="authorities" var="roles" scope="page" />
										<c:forEach var="role" items="${roles}">
										  <c:out value="${role}"/> 
										</c:forEach><br/>
                                  		<label>Institution</label>
                                  		<form:input path="institution"/><form:errors path="institution"/>
                                  		<label>Poste occupé</label>
                                  		<form:input path="postOccupe"/><form:errors path="postOccupe"/>
                                  		<label>Biographie<sup>*</sup></label>
                                  		<form:textarea path="biographie" rows="9"/><form:errors path="biographie"/>
                                  		<form:button value="Mettre à jour" class="right">Mettre à jour</form:button>
                                  	</div>
                            </div>
                           </form:form>
                        </div>
                    </div>
                    <!-- F Poser quest -->
                   
                </div>
            </div>
        </div> 
       </div>
      </div>   
    <!-- Fin Ajouter Question --> 