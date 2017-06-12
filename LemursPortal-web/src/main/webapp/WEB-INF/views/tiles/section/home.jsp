<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="forum-container">
			<div class="forum-title">
			<sec:authorize access="isAuthenticated()">
				<div class="pull-right forum-desc">
					<c:url value="/secured/post/create" var="addQuestionUrl"/>
					<a class="add-quest" href="${addQuestionUrl}"><spring:message code="home.ask.question"/></a>
				</div>
			</sec:authorize>
				<h2>
					<spring:message code="home.topquestions" />
				</h2>
			</div>
			
			<!-- D Sujet -->
			<c:forEach items="${topQuestionsPage.content}" var="topQuestion">
				<post:question topQuestion="${topQuestion}"/>
			</c:forEach>
			<!-- F Sujet -->

<!-- 			D Pagination -->
				<c:url var="pageBaseUrl" value="/"/>
				<page:pagination currentPage="${topQuestionsPage.number + 1}" totalPages="${topQuestionsPage.totalPages}" pageBaseUrl="${pageBaseUrl}"/>
		</div>
	</div>


