<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="forum-container">
		<div class="forum-title">
				<h2>
					<span>Mes notifications</span>
				</h2>
			</div>
		<c:forEach items="${notifications}" var="notification">
			<!-- D Sujet -->
			<div class="forum-item">
				<div class="row">
					<c:url value="/post/show/${notification.questionId}" var="questionUrl"/>
					<c:url value="/postsParThematique/${notification.thematiqueId}" var="thematiqueUrl"/>
					<a href="${questionUrl}">Une nouvelle question</a> a été ajouté dans le Thematique <a href="${thematiqueUrl}">${notification.thematique.libelle}</a> 
				</div>
			</div>
			<!-- F Sujet -->
	</c:forEach>
</div>
</div>
