<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>

<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container">
    
                    <div class="forum-title">
                    	<sec:authorize access="isAuthenticated()">
                        <div class="pull-right forum-desc">
							<c:url value="/secured/thematique-${thematique.id}/post/create" var="addQuestionUrl"/>
                            <a href="${addQuestionUrl}" class="add-quest"><spring:message code="home.ask.question"/></a>
                        </div>
                        </sec:authorize>
                        <h2><span><spring:message code="home.thematiques"/> :</span><spring:message code="document.thematique.id.${thematique.id}"/></h2>
                    </div>
    				<!-- D Sujet -->
    				 <c:forEach items="${postsBythematiquePage.content}" var="QuestionParTheme">
    				 	<post:question topQuestion="${QuestionParTheme}"/>
    				</c:forEach>
                    <!-- F Sujet -->
                    <!-- 			D Pagination -->
                	<c:url var="currentBaseUrl" value="/postsParThematique/${thematique.id}"/>
                    <page:pagination currentPage="${postsBythematiquePage.number + 1}" totalPages="${postsBythematiquePage.totalPages}" pageBaseUrl="${currentBaseUrl}"/>
                </div>
            </div>