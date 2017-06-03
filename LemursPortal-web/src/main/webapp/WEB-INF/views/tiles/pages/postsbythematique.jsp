<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
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
    				 <c:forEach items="${postsBythematiquePage.content}" var="QuestionParTheme">
    					<div class="forum-item">
	                        <div class="row">
	                            <div class="col-md-8">
	                                <div class="forum-profil">
	                                  <a href="#">
	                                  	<user:profilImage src="${QuestionParTheme.question.owner.photoProfil}" cssClass="img-circle"/>
	                                  </a>
	                                  <div class="reponse-user"><a href="#"><c:out value="${QuestionParTheme.question.owner.nom}"/> <c:out value="${QuestionParTheme.question.owner.prenom}"/></a><br/><i><c:out value="${question.role}"/></i></div>
	                                </div>
	                                <c:url value="/post/show/${QuestionParTheme.question.id}" var="questionPageUrl"/>
	                                <a href="${questionPageUrl}" class="forum-item-title"><c:out value="${QuestionParTheme.question.title}" /></a>
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
	                               <a href="#">
	                               <user:profilImage src="${QuestionParTheme.derniereReponse.owner.photoProfil}" cssClass="img-circle"/>
	                               </a>
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
                    <!-- 			D Pagination -->
                <c:url var="currentBaseUrl" value="/postsParThematique/${thematique.id}"/>
                <c:set var="firstUrl" value="${currentBaseUrl}?page=1"/>
                <c:set var="lastUrl" value="${currentBaseUrl}?page=${postsBythematiquePage.totalPages}"/>
                <c:set var="prevUrl" value="${currentBaseUrl}?page=${paginationCurrent - 1}"/>
                <c:set var="nextUrl" value="${currentBaseUrl}?page=${paginationCurrent + 1}"/>
				    <ul class="pagination">
				        <c:choose>
				            <c:when test="${paginationCurrent == 1}">
				                <li class="disabled"><a href="#">&lt;&lt;</a></li>
				                <li class="disabled"><a href="#">&lt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${firstUrl}">&lt;&lt;</a></li>
				                <li><a href="${prevUrl}">&lt;</a></li>
				            </c:otherwise>
				        </c:choose>
				        <c:forEach var="i" begin="${paginationBegin}" end="${paginationEnd}">
				            <c:set var="pageUrl" value="${currentBaseUrl}?page=${i}"/>
				            <c:choose>
				                <c:when test="${i == paginationCurrent}">
				                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:when>
				                <c:otherwise>
				                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				        <c:choose>
				            <c:when test="${paginationCurrent == postsBythematiquePage.totalPages}">
				                <li class="disabled"><a href="#">&gt;</a></li>
				                <li class="disabled"><a href="#">&gt;&gt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${nextUrl}">&gt;</a></li>
				                <li><a href="${lastUrl}">&gt;&gt;</a></li>
				            </c:otherwise>
				        </c:choose>
				    </ul>
                    
                </div>
            </div>