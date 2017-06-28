<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="wrapper wrapper-content animated fadeInRight">
	<div class="forum-container">
		<div class="form">
			<c:url value="/secured/pmessage" var="formActionUrl" />
			<form:form modelAttribute="privateMessage" method="post"
				action="${formActionUrl}" cssClass="send-msg-form">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="row">
					<form:hidden path="destinataireId" />
					<label>Objet du message</label>
					<form:input path="subject" />

					<label>Votre message<sup>*</sup></label>
					<form:textarea path="body" />

					<form:button value="Envoyer">Envoyer</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
