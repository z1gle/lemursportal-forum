 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<style>
.expert-sidebar {
    padding: 0px;
    background: #f7f5f5 none repeat scroll 0 0;
    border: 0px solid #d7d7d7; */
    margin-bottom: 50px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 1px 3px #9e9e9e;*/
    display: inline-block;
    flex-grow: 1;
    /*margin: 8px 4px 0;*/
    position: relative;
    overflow: hidden;
    vertical-align: top;
    /* width: 96px; */
}
.expert-userpic {
    position: relative;
    margin: 20px;
}
.expert-usertitle {
    text-align: center;
    margin: 10px;
}
.expert-usertitle-name {
    color: #333;
    font-size: 14px;
    font-weight: bold;
    /* margin-bottom: 7px; */
    height: 44px;
    text-overflow: ellipsis;
    overflow: hidden;
}
.expert-usertitle-role {
    font-size: 10px;
    margin-bottom: 15px;
    height: 12px;
}
.expert-userbuttons .expert-usertitle-role {
	height: 24px;
	text-align: left;
	overflow: hidden;
    text-overflow: ellipsis;
}
.expert-userbuttons {
    text-align: center;
    margin-top: 10px;
    border-top: 1px solid #CCC;
    color: #777777;
    /* margin: 30px 0 0; */
    padding: 10px;
    /* text-align: left; */
    background-color: #EEE;
}
.expert-userbuttons a.btn {
    margin-bottom: 9px;
}
</style>
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
									<c:choose>
										<c:when test="${empty expert.photoProfil}">
											<img src="${resourcesPath}/profil/blanc.png" alt="<c:out value="${expert.prenom}" /> " style="background-position: center; margin: auto;/* width: 100%; */height: 100%; background-image: url('${resourcesPath}/profil/default.png'); background-repeat: no-repeat; background-size: auto 100%;" class="img-responsive"/>
										</c:when>
										<c:otherwise>
											<img src="${resourcesPath}/profil/blanc.png" alt="<c:out value="${expert.prenom}" /> " style="background-position: center; margin: auto;/* width: 100%; */height: 100%; background-image: url('${resourcesPath}<c:out value="${expert.photoProfil}" />'); background-repeat: no-repeat; background-size: auto 100%;" class="img-responsive"/>
										</c:otherwise>
									</c:choose>
                                    
									
                                        <a href="#envoi-msg" class="send" role="button" class="btn btn-custom" data-toggle="modal" title="Envoyer un message">
                                            <i class="fa fa-envelope"></i>
                                        </a>
                                    </div>
                                    
                                    <div class="expert-usertitle">
                                        <div class="expert-usertitle-name">
                                        <c:out value="${expert.title}" /> <c:out value="${expert.prenom}" /> <c:out
											value="${expert.nom}" />
                                            
                                        </div>
                                        <div class="expert-usertitle-role">
                                            <u>Expert</u>&nbsp;
                                            <i style="color: #777;">
                                            	<c:choose>
												    <c:when test="${empty expert.lastAccessDate}">(Actif: ...)</c:when>
												    <c:otherwise>
												    	<jsp:useBean id="today" class="java.util.Date" />
														<fmt:parseNumber
														    value="${(today.time - expert.lastAccessDate.time) / (1000*60*60*24) }"
														    integerOnly="true" var="active"/>
														<c:choose>
														    <c:when test="${active < 0}">(Actif: now)</c:when>
														    <c:when test="${active eq 0}">(Actif: today)</c:when>
														    <c:when test="${active eq 1}">(Actif: yesterday)</c:when>
														    <c:when test="${active < 7}">(Actif: ${active} days ago)</c:when>
														    <c:when test="${active < 30}">(Actif: <fmt:parseNumber value="${active/7}" integerOnly="true"/> weeks ago)</c:when>
														    <c:when test="${active < 365}">(Actif: <fmt:parseNumber value="${active/30}" integerOnly="true"/> month ago)</c:when>
														    <c:otherwise>(Actif: <fmt:parseNumber value="${active/365}" integerOnly="true"/> year ago)</c:otherwise>
														</c:choose>
												    </c:otherwise>
												</c:choose>
                                            
                                            </i>
                                            <br />
                                        </div>
                                    </div>
                                    <div class="expert-userbuttons" align="center">
                                        <div class="expert-usertitle-role">
                                            <span
												style="font-weight: normal; margin-bottom: 15px"><b>Domaine d'expertise: </b>
												<c:forEach var="dExpertise" items="${expert.dExpertise}">
													<c:out value="${dExpertise.libelle}/ " />
												</c:forEach>
											</span>
									
                                    </div>
                                    <c:url var="expertDetailUrl" value="/experts/${expert.id}"></c:url>
                                        <a href="${expertDetailUrl}" class="btn"><spring:message code="user.seeprofil" /></a>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                          

                            
                        </div>
                </div>
             </div>   
                
