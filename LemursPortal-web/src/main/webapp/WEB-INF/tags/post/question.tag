<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />

<%@ attribute name="topQuestion" required="true" rtexprvalue="true"
	type="org.wcs.lemursportal.model.post.TopQuestion" description="L'url de page page courante"%>
<%-- <%@ attribute name="showLastResponseInfo" required="false" rtexprvalue="true" --%>
<!-- 	type="java.lang.Boolean" description="Un flag pour afficher ou pas les information sur la dernière reponse" %> -->
<div class="forum-item">
	<div class="row">
	
        <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
<%--    <sec:authentication property="principal" /> --%>
        <sec:authentication property="name" var="currentUserLogin"/>
        <sec:authorize access="hasAnyRole('ADMIN', 'MODERATEUR')" var="isAdminOrModerateur"/>
		<c:if test="${isLoggedInUser && (currentUserLogin eq topQuestion.question.owner.login || isAdminOrModerateur)}">
<!-- 		<a href="#">Modifier</a> -->
			<c:url value="/post/del/${topQuestion.question.id}" var="delUrl"/>
			<a href="${delUrl}">Supprimer</a>		
		</c:if>
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
				<small>
					commentaires
				</small>
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
				<c:set var="idM" value="0" />
				<c:set var="libelle" value =""/> 
				<c:forEach items="${topQuestion.derniereReponse.owner.roles}"
					var="role">
					<c:if test="${role.id < 10001}"> 
					 <c:set var="idR" value="${role.id}" />
					</c:if> 
					<c:if test="${idR > idM }">
						<c:set var="idM" value="${idR}" />
						<c:set var="libelle" value="${role.libelle}" />
					</c:if>
				</c:forEach>
				<i><c:out value="${libelle}" /></i>
					<br />
			</div>
		</div>

	</div>
</div>