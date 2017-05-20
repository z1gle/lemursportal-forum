<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container">
    
                    <div class="forum-title">
                        <div class="pull-right forum-desc">
                            <a class="add-quest"><spring:message code="home.ask.question"/></a>
                        </div>
                        <h2><span>Thématique :</span><c:out value="${thematique.libelle}"/></h2>
                    </div>
    				<!-- D Sujet -->
    				 <c:forEach items="${postsBythematique}" var="QuestionParTheme">
    					<div class="forum-item">
	                        <div class="row">
	                            <div class="col-md-8">
	                                <div class="forum-profil">
	                                  <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/></a>
	                                  <div class="reponse-user"><a href="#"><c:out value="${QuestionParTheme.question.owner.nom}"/> <c:out value="${QuestionParTheme.question.owner.prenom}"/></a><br/><i><c:out value="${question.role}"/></i></div>
	                                </div>
	                                <a href="forum_post.html" class="forum-item-title"><c:out value="${QuestionParTheme.question.title}" /></a>
	                                <div class="forum-sub-title">
	                                	<c:out value="${QuestionParTheme.question.body}" escapeXml="true" />
	                                    <p class="forum-date"><fmt:formatDate pattern="${datetimeFormat}" value="${QuestionParTheme.question.creationDate}"/></p>
	                                </div>
	                            </div>
	                            <div class="col-md-1 forum-info">
	                                <span class="views-number">
	                                    <c:out value="${QuestionParTheme.nbReponse}"/>
	                                </span>
	                                <div class="vue">
	                                    <small><c:out value="${QuestionParTheme.nbVue}"/> <spring:message code="home.topquestions.vues"/></small>
	                                </div>
	                            </div>
	                            <div class="col-md-3 forum-user-info">
	                               <a href="#"><img class="img-circle" src="${resourcesPath}/images/user2.png" alt=""/></a>
	                               <div class="reponse-user"><a href="#">
	                               <c:out value="${QuestionParTheme.derniereReponse.owner.nom}"/> <c:out value="${QuestionParTheme.derniereReponse.owner.prenom}"/>
	                               </a><br/><fmt:formatDate pattern="${datetimeFormat}" value="${QuestionParTheme.derniereReponse.creationDate}"/><br/>
	                               	<c:forEach items="${QuestionParTheme.derniereReponse.owner.roles}" var="role">    
									    <i><c:out value="${role.libelle}"/></i><br/>
									</c:forEach>
	                               </div>
	                            </div>
	                            
	                        </div>
	                    </div>
    				</c:forEach>
                    <!-- F Sujet -->
                    
                    <!-- D Pagination -->
<!--                     <ul class="pagination"> -->
<!--                         <li class="disabled"><a href="#">&laquo;</a></li> -->
<!--                         <li class="active"><a href="#">1</a></li> -->
<!--                         <li><a href="#">2</a></li> -->
<!--                         <li><a href="#">3</a></li> -->
<!--                         <li><a href="#">4</a></li> -->
<!--                         <li><a href="#">5</a></li> -->
<!--                         <li><a href="#">&raquo;</a></li> -->
<!--                     </ul> -->
                    <!-- F Pagination -->
                    
                </div>
            </div>