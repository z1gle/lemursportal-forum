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
		<c:set var="idM" value="0" />
		<c:set var="libelle" value =""/> 
		<c:forEach items="${userInfo.roles}" var="role">
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
