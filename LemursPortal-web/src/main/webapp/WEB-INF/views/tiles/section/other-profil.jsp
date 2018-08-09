<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<style>
<!--
.radio, .checkbox {
	margin-left: 15px;
}
.dropdown-menu > .active > a {
    background-color: #a38000 !important;
}
-->
</style>
<div class="wrapper wrapper-content animated fadeInRight">
<!-- D Profil -->
<div class="forum-container page-profil">
        <div class="row">
        	<div class="page-title">
        		<h2 class="profil">Profil</h2>
            </div>
            <div class="col-xs-12 col-md-4 col-lg-3">
                <div class="profil-info">
                
                	<!-- D Envoi message Modal -->
                    <!--div id="envoi-msg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    </div-->
                    <!-- D Envoi message Modal -->
                    
                
                    <div class="image text-center">
                    <user:profilImage src="${userInfo.photoProfil}" cssClass="img-responsive img-circle"/>
                        <!-- D S'affiche si un autre utilisateur visualise son profil -->
                    <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
                        <c:choose>
                            <c:when test="${isLoggedInUser}">
                                <a href="#envoi-msg" class="send" role="button" class="btn btn-custom" data-toggle="modal" title="Envoyer un message">
                                        <i class="fa fa-envelope"></i>
                                </a>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                        <!-- F S'affiche si un autre utilisateur visualise son profil -->
                    </div>
                    <div class="box">
                        <div class="name"><strong><c:out value="${userInfo.prenom}"/> <c:out value="${userInfo.nom}"/></strong></div>
                        
                    </div>
                    
                    <sec:authorize access="hasRole('ADMIN')">
                    	<c:url value="/admin/de/expert" var="userFormAction" />
                    	<form:form modelAttribute="dExpertiseForm"
								action="${userFormAction}" name="dExpertiseEditForm" 
								method="POST" cssClass="edit-profil-form">
						<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						<label>Titre<sup>*</sup> </label>
						<form:select path="title">
							<form:option value="" disabled="true"> --SELECT--</form:option>
							<form:option value="Mr.">Mr.</form:option>
							<form:option value="Mlle.">Mlle.</form:option>
							<form:option value="Mme." >Mme.</form:option>
							<form:option value="Dr.">Dr.</form:option>
							<form:option value="Pr.">Pr.</form:option>
						</form:select><br><br>
						<div style="text-align: center !important;margin-bottom: 10px;">Editer domaine d'expertise</div>
						<div>
							<form:hidden path="userId"/>
							<form:select path="dExpertise" id="dExpertise" style="display:none">
								<form:options items="${listeThematique}"
									itemLabel="libelle" itemValue="id" />
							</form:select>
						</div>
						<div class="form">
							<form:button value="Mettre à jour">
								<spring:message code="profil.edit.maj.btn" />
							</form:button>
						</div>
						</form:form>
					</sec:authorize>
                </div>
            </div>
    
            <div class="col-xs-12 col-md-8 col-lg-9 profil-info">
                
                
                <div class="box">
                    <!-- D Tab -->
                    <ul class="nav nav-tabs userProfileTabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true">Informations</a></li>
             			<li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="profil.edit.biographie"/></a></li>
                        <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="profil.edit.publication"/></a></li>
                    </ul>
<!-- F Tab -->
                    
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade active in" id="tab-item-1">
                            <div class="txt-content">
                                <div class="info">
                                	<div class="col-md-6">
                                        <p><span>Rôle:</span><br>
                                        <!-- Roles display -->
                                        <c:forEach var="role" items="${userInfo.roles}">
                                        	<c:set value="${fn:replace(role.libelle, 'ROLE_', '')}" var="role" />
											<a class="btn-${fn:toLowerCase(role)} btn-xs">${role}</a>
										</c:forEach>
										</p>		
                                        <p><span><span>Institution:</span><br /><c:out value="${userInfo.institution}"/></span></p>
                                        <p><span><span>Poste occupé:</span><br /><c:out value="${userInfo.postOccupe}"/></span></p>
                                        <p><span><span>Email:</span><br /><a href="#" title="#"><c:out value="${userInfo.email}"/></a></span></p>
                                    </div>
                                    
                                    <div class="col-md-6">
                                    
                                        <p><span><span>Inscrit(e) le:</span><br /><fmt:formatDate pattern="${dateFormat}" value="${userInfo.dateInscription}" /></span></p>
                                        <p><span><span>Dernière activité le:</span><br /><fmt:formatDate pattern="${dateFormat}" value="${userInfo.lastAccessDate}" />&nbsp;</span></p>
<!--                                         <p><span><span>Nombre de questions :</span><br />18</span></p> -->
<!--                                         <p><span><span>Nombre de réponses :</span><br />45</span></p> -->
										<p><span><span>Domaine d'expertise :</span><br />
												<c:forEach var="dExp" items="${userInfo.dExpertise}">
													<a><c:out value="${dExp.libelle}" /></a>/&nbsp; 
												</c:forEach>
											</span>
										</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                
                        <div role="tabpanel" class="tab-pane fade" id="tab-item-2">
                            <div class="txt-content">
                            <p><c:out value="${userInfo.biographie}"  escapeXml="false" /> </p>
                            </div>
                        </div>
                
                        <div role="tabpanel" class="tab-pane fade" id="tab-item-3">
                            <div class="txt-content">
                            <p><c:out value="${userInfo.publication}"  escapeXml="false" /> </p>
                            </div>
                        </div>
                    </div>
                </div>
                
                
            </div>
        </div>
</div>
<!-- F Profil -->
    </div>
<script type="text/javascript" src="${resourcesPath}/js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#dExpertise').multiselect({
            maxHeight: 158,
            buttonWidth: '100%'
        });
    });
</script>
    