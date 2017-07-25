 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<div class="wrapper wrapper-content animated fadeInRight">
 <div class="forum-container liste-expert">
                        <div class="row expert">
                        	<div class="page-title">
                        		<h2 class="profil">Liste des Experts</h2>
                            </div>
                            <div>
                              <h4 ><c:out value="${noexpert}"/></h4>
                             </div>
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
                             <c:forEach items="${experts}" var="expert">
                            <div class="col-md-3">
                                <div class="expert-sidebar">
                                    <div class="image expert-userpic">
                                    <user:profilImage src="${expert.photoProfil}" cssClass="img-responsive"/>
                          
                                        <a href="#envoi-msg" class="send" role="button" class="btn btn-custom" data-toggle="modal" title="Envoyer un message">
                                            <i class="fa fa-envelope"></i>
                                        </a>
                                    </div>
                                    
                                    <div class="expert-usertitle">
                                        <div class="expert-usertitle-name">
                                        <c:out value="${expert.prenom}" /> <c:out
											value="${expert.nom}" />
                                            
                                        </div>
                                        <div class="expert-usertitle-role">
                                            Expert<br />
                                            <span><fmt:formatDate pattern="${datetimeFormat}"
									value="${expert.lastAccessDate}" /></span>
                                        </div>
                                    </div>
                                    <div class="expert-userbuttons" align="center">
                                    <c:url var="expertDetailUrl" value="/experts/${expert.id}"></c:url>
                                        <a href="${expertDetailUrl}" class="btn"><spring:message code="user.seeprofil" /></a>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                          

                            
                        </div>
                </div>
             </div>   
                
