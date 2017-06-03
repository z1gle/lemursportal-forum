<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ attribute name="userInfo" required="true" rtexprvalue="true" type="org.wcs.lemursportal.model.user.UserInfo" description="L'Utilisateur"%>
<c:url value="/resources" var="resourcesPath"/>
<div class="forum-profil">
	<a href="#"><user:profilImage src="${userInfo.photoProfil}" cssClass="img-circle"/></a>
	<div class="reponse-user">
		<a href="#"><c:out
				value="${userInfo.nom}" /> <c:out
				value="${userInfo.prenom}" /></a><br />
		<c:forEach items="${userInfo.roles}" var="role">
			<i>${role.libelle}</i>
		</c:forEach>
	</div>
</div>
