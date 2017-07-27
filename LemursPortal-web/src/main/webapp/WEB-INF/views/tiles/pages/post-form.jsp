<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:url value="/resources" var="resourcesPath"/>


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
                        	<form:form  class="create-quest-form" modelAttribute="post" action="${formAction}"  method="POST"  enctype="multipart/form-data"  >
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	
                            <div class="row">
                            	
                                  <label>Choisir un thématique <sup>*</sup></label>
                                  <form:select path="thematiqueId">  
                                  	<form:options items="${listeThematique}" itemLabel="libelle" itemValue="id"/> 
                                  </form:select>
                                  <form:errors path="thematiqueId"/>
                                  <br/>
                                  <label>Titre de la question <sup>*</sup></label>
                                  <form:input path="title"/>
                                  
                                  <label>Ajouter un fichier (photos, documents, videos, audios...)</label>
                                  <input type="file" name="file" class="fisie" />
                                  
                                   <label>Url youtube  <span class="rouge"><c:out value="${errors}" /></span></label>  
                                  <form:input path="uriYoutube"/>
                                  
                                  <label>Votre question <sup>*</sup></label>                                  
                                  <form:textarea path="body"/>
                                  <form:button value="save">Poster</form:button>
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

