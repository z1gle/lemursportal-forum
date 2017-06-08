<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
                        <h2>Nouvelle Thematique</h2>
                    </div>
    				<!-- D Poser quest -->
                    <div class="cadre">
                   	  <div class="form">
                            <div class="row">
                            	<div class="forum-container create-quest">
									<c:url value="/secured/thematique" var="formAction"></c:url>
									<form:form modelAttribute="thematique" action="${formAction}"
										method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}">
										<form:hidden path="id" />
										<div>
											Libelle:
											<form:input path="libelle" />
											<form:errors path="libelle" />
										</div>
										<div>
											Description:
											<form:textarea path="description" />
											<form:errors path="description" />
										</div>
										<div>
											<form:button value="save">Enregistrer</form:button>
										</div>
									</form:form>
								</div>
                            </div>
                        </div>
                    </div>
                    <!-- F Poser quest -->
                   
                </div>
            </div>
        </div>    
    <!-- Fin Ajouter Question -->           
    </div>
</div>


