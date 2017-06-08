<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
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
				<div class="forum-item">
					<div class="row">
						<div class="col-md-8">
							<user:forum-profil userInfo="${topQuestion.question.owner}"/>
							<c:url value="/post/show/${topQuestion.question.id}" var="questionPageUrl"/>
							<a href="${questionPageUrl}" class="forum-item-title"><c:out value="${topQuestion.question.title}" /></a>
							<div class="forum-sub-title">
								<c:out value="${topQuestion.question.body}" escapeXml="true" />
								<p class="forum-date">
									<fmt:formatDate pattern="${datetimeFormat}"
										value="${topQuestion.question.creationDate}" />
								</p>
							</div>
						</div>
						<div class="col-md-1 forum-info">
							<span class="views-number"> <c:out
									value="${topQuestion.nbReponse}" />
							</span>
							<div class="vue">
								<small><c:out value="${topQuestion.nbVue}" /> <spring:message
										code="home.topquestions.vues" /></small>
							</div>
						</div>
						<div class="col-md-3 forum-user-info">
							<a href="#"><user:profilImage src="${topQuestion.derniereReponse.owner.photoProfil}" cssClass="img-circle"/></a>
							<div class="reponse-user">
								<a href="#"> <c:out
										value="${topQuestion.derniereReponse.owner.nom}" /> <c:out
										value="${topQuestion.derniereReponse.owner.prenom}" />
								</a><br />
								<fmt:formatDate pattern="${datetimeFormat}"
									value="${topQuestion.derniereReponse.creationDate}" />
								<br />
								<c:forEach items="${topQuestion.derniereReponse.owner.roles}"
									var="role">
									<i><c:out value="${role.libelle}" /></i>
									<br />
								</c:forEach>
							</div>
						</div>

					</div>
				</div>
			</c:forEach>
			<!-- F Sujet -->

<!-- 			D Pagination -->
				<c:url var="pageBaseUrl" value="/"/>
				<page:pagination currentPage="${topQuestionsPage.number + 1}" totalPages="${topQuestionsPage.totalPages}" pageBaseUrl="${pageBaseUrl}"/>
		</div>
	</div>


