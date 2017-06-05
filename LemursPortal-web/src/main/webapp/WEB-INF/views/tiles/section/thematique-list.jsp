<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="forum-container">

		<div class="forum-title">
			<div class="pull-right forum-desc">
				<a class="add-quest"><spring:message code="home.ask.question" /></a>
			</div>
		</div>
		<div class="forum-title">
				<h2>
					<span>Thématiques :</span>
				</h2>
			</div>
		<c:forEach items="${topThematiquePage.content}" var="parThematique">
			<!-- D Sujet -->

			<div class="forum-item">
				<div class="row">
					<div class="col-md-8">
						<div class="forum-profil">
							<a href="#"><img class="img-circle"
								src="${resourcesPath}/images/user1.png" alt="" /></a>
							<div class="reponse-user">
								<a href="#"><c:out value="" /> <c:out value="" /></a><br />
								<i><c:out value="" /></i>
							</div>
						</div>
						<c:url var="thematiquePageUrl" value="/postsParThematique/${parThematique.thematique.id}"/>
						<a href="${thematiquePageUrl}" class="forum-item-title"><c:out
								value="${parThematique.thematique.libelle}" /></a>
						<div class="forum-sub-title">
							<c:out value="${parThematique.thematique.description}"
								escapeXml="true" />
							<p class="forum-date">
								<fmt:formatDate pattern="${datetimeFormat}"
									value="${parThematique.thematique.creationDate}" />
							</p>
						</div>
					</div>
					<div class="col-md-1 forum-info">
						<span class="views-number"> <c:out value="${parThematique.nombreQuestion}" /></span>
						<div class="vue">
							<small> <spring:message
									code="thematique.questions" /></small>
						</div>
					</div>
				</div>
			</div>
			<!-- F Sujet -->
	</c:forEach>
	<!-- D Pagination -->
	<c:url var="pageBaseUrl" value="/thematique/list/"/>
		<page:pagination currentPage="${topThematiquePage.number + 1}" totalPages="${topThematiquePage.totalPages}" pageBaseUrl="${pageBaseUrl}"/>
			<!-- F Pagination -->
</div>
</div>
