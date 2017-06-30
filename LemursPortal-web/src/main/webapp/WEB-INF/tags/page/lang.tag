<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<!-- inclure le fichier scripts.js avec ce tag -->
<c:set var="selectedFr" value=""/>
<c:set var="selectedMg" value=""/>
<c:set var="selectedEn" value=""/>
<c:choose>
	<c:when test="${pageContext.response.locale.language == 'en'}">
		<c:set var="selectedFr" value=""/>
		<c:set var="selectedMg" value=""/>
		<c:set var="selectedEn" value="selected=\"selected\""/>
	</c:when>
	<c:when test="${pageContext.response.locale.language == 'fr'}">
		<c:set var="selectedFr" value="selected=\"selected\""/>
		<c:set var="selectedMg" value=""/>
		<c:set var="selectedEn" value=""/>
	</c:when>
	<c:when test="${pageContext.response.locale.language == 'mg'}">
		<c:set var="selectedFr" value=""/>
		<c:set var="selectedMg" value="selected=\"selected\""/>
		<c:set var="selectedEn" value=""/>
	</c:when>
</c:choose>
<div class="lang lang-chooser">

    <select id="lang-select">
        <option value="fr" ${selectedFr}><spring:message code="global.lang.french"/></option>
        <option value="en" ${selectedEn}><spring:message code="global.lang.english"/></option>
        <option value="mg" ${selectedMg}><spring:message code="global.lang.malagasy"/></option>
    </select>
</div>