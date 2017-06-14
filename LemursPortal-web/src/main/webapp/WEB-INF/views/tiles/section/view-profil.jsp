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
<div class="wrapper wrapper-content animated fadeInRight">
<!-- D Profil -->
<div class="forum-container page-profil">
        <div class="row">
        	<div class="page-title">
        		<h2 class="profil"><spring:message code="profil.label.profil"/></h2>
            </div>
            <div class="col-xs-12 col-md-4 col-lg-3">
                <div class="profil-info">
                
                	<!-- D Envoi message Modal -->
                    <div id="envoi-msg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Envoyer un message</h4>
                        </div>
                        <div class="modal-body">
                          <div class="form">
                            <form class="send-msg-form">
                                <div class="row">
                                      <label>Objet du message</label>
                                      <input type="text" />
                                      
                                      <label>Votre message<sup>*</sup></label>
                                      <textarea></textarea>
                                      
                                      <button class="right">Envoyer</button>
                                </div>
                            </form>
                            </div>
                        </div>
                        </div>
                        </div>
                    </div>
                    <!-- D Envoi message Modal -->
                    
                
                    <div class="image text-center">
                    	<user:profilImage src="${userInfo.photoProfil}" cssClass="img-responsive img-circle"/>
                        <!-- D S'affiche si un autre utilisateur visualise son profil -->
                        <a href="#envoi-msg" class="send" role="button" class="btn btn-custom" data-toggle="modal" title="Envoyer un message">
                                <i class="fa fa-envelope"></i>
                        </a>
                        <!-- F S'affiche si un autre utilisateur visualise son profil -->
                    </div>
                    <div class="box">
                        <div class="name"><strong><c:out value="${userInfo.prenom}"/> <c:out value="${userInfo.nom}"/></strong></div>
                        <div class="info">
                            <!-- D S'affiche si l'utilisateur lui-même est connecté -->
                            <c:url value="/user/profil/edit" var="userProfilUrl"/>
                            <a href="${userProfilUrl}" class="btn left"><spring:message code="profil.label.modifiermonprofil"/></a>
                            <!-- F S'affiche si l'utilisateur lui-même est connecté -->
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="col-xs-12 col-md-8 col-lg-9 profil-info">
                
                
                <div class="box">
                    <!-- D Tab -->
                    <ul class="nav nav-tabs userProfileTabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true">Informations</a></li>
                        <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false">Biographie</a></li>
                    </ul>
<!-- F Tab -->
                    
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade active in" id="tab-item-1">
                            <div class="txt-content">
                                <div class="info">
                                	<div class="col-md-6">
                                        <p><span><span><spring:message code="profil.role"/>:</span><br />
                                        	<!-- Roles display -->
											<sec:authentication property="authorities" var="roles" scope="page" />
											    <c:forEach var="role" items="${roles}">
											    	${role} 
											    </c:forEach>
                                        </span></p>
                                        <p><span><span><spring:message code="profil.edit.institution"/>:</span><br /><c:out value="${userInfo.institution}"/></span></p>
                                        <p><span><span><spring:message code="profil.edit.postoccupe"/>:</span><br /><c:out value="${userInfo.postOccupe}"/></span></p>
                                        <p><span><span><spring:message code="signup.email.placeholder"/>:</span><br /><a href="#" title="#"><c:out value="${userInfo.email}"/></a></span></p>
                                    </div>
                                    
                                    <div class="col-md-6">
                                    
                                        <p><span><span><spring:message code="profil.label.inscritle"/>:</span><br /><fmt:formatDate pattern="${dateFormat}" value="${userInfo.dateInscription}" /></span></p>
                                        <p><span><span><spring:message code="profil.label.derniereactivite"/>:</span><br /><fmt:formatDate pattern="${dateFormat}" value="${userInfo.lastAccessDate}" /></span></p>
<!--                                         <p><span><span>Nombre de questions :</span><br />18</span></p> -->
<!--                                         <p><span><span>Nombre de réponses :</span><br />45</span></p> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                
                        <div role="tabpanel" class="tab-pane fade" id="tab-item-2">
                            <div class="txt-content">
                            <p><c:out value="${userInfo.biographie}"/> </p>
                            </div>
                        </div>
                    </div>
                </div>
                
                
            </div>
        </div>
</div>
<!-- F Profil -->
    </div>