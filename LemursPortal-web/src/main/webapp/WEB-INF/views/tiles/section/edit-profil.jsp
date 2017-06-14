<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!-- Début Ajouter Question -->
<div class="container lemurs-page">
	 <div class="row">
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container page-profil">
    
                    <div class="page-title">
                        <h2 class="profil"><spring:message code="profil.edit.title"/></h2>
                    </div>
    				<!-- D Poser quest -->
                    <div class="cadre">
                   	  <div class="form">
                   	  		<c:url value="/user/profil/edit" var="userFormAction"/>
                            <form:form modelAttribute="registrationForm" action="${userFormAction}" enctype="multipart/form-data" method="POST" cssClass="edit-profil-form">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div class="row">
                            	<div class="col-md-4">
                                  <div class="image text-center">
                                  <user:profilImage src="${registrationForm.photoProfil}" cssClass="img-responsive img-circle"/>
                                  </div>
                                
                                  <label><spring:message code="profil.edit.change.photo"/></label>
                                  <form:input type="file" path="file" class="pdp"/>
                                  <form:errors path="file" class="error"/>
                                  
                                </div>
                                	<div class="col-md-4">
	                                	<form:hidden path="id"/>
	                                  	<label><spring:message code="signup.name.placeholder"/><sup>*</sup></label>
	                                  	<form:input path="nom"/><form:errors path="nom"/>
	                                  	<label><spring:message code="signup.surname.placeholder"/><sup>*</sup></label>
	                                  	<form:input path="prenom"/><form:errors path="prenom"/>
	                                  	<label><spring:message code="signup.dateofbirth.placeholder"/> (<c:out value="${dateFormat}"/>)</label>
	                                  	<form:input path="dateNaissance"/><form:errors path="dateNaissance"/>
	                                  	<label><spring:message code="signup.email.placeholder"/><sup>*</sup></label>
	                                  	<form:input path="email"/><form:errors path="email"/>
                                  	</div>
                                  	<div class="col-md-4">
                                  		<label><spring:message code="profil.role"/><sup>*</sup></label>
                                  		<sec:authentication property="authorities" var="roles" scope="page" />
										<c:forEach var="role" items="${roles}">
										  <c:out value="${role}"/> 
										</c:forEach><br/>
                                  		<label><spring:message code="profil.edit.institution"/></label>
                                  		<form:input path="institution"/><form:errors path="institution"/>
                                  		<label><spring:message code="profil.edit.postoccupe"/></label>
                                  		<form:input path="postOccupe"/><form:errors path="postOccupe"/>
                                  		<label><spring:message code="profil.edit.biographie"/><sup>*</sup></label>
                                  		<form:textarea path="biographie" rows="9"/><form:errors path="biographie"/>
                                  		<form:button value="Mettre à jour" class="right"><spring:message code="profil.edit.maj.btn"/></form:button>
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