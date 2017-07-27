<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code="datetime.format" var="datetimeFormat"/>


<div class="wrapper wrapper-content animated fadeInRight">
	<div class="forum-container">
		<div class="form">
			<div style="border: 1px #ccc solid;">
				<strong>Envoyé par <span>${privateMessage.sender.label}</span> le <fmt:formatDate pattern="${datetimeFormat}" value="${privateMessage.date}" /></strong><br/>
				<c:url value="/secured/pmessage/${privateMessage.id}" var="messageUrl"/>
				<h3>${privateMessage.subject}</h3>
				<p>${privateMessage.body}</p>
				<br/>
			</div>
			<c:url value="/secured/pmessage/list" var="listMessageUrl"/>
			<a href="${listMessageUrl}">Retour vers la liste des messages</a>
		</div>
	</div>
</div>
