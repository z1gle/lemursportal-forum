<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="src" required="true" rtexprvalue="true" type="java.lang.String" description="L'image à afficher"%>
<%@ attribute name="alt" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="cssClass" required="false" rtexprvalue="true" type="java.lang.String" %>
<c:url value="/resources" var="resourcesPath"/>
<c:choose>
	<c:when test="${empty src}">
		<img src="${resourcesPath}/profil/default.png" alt="${alt}" class="${cssClass}">
	</c:when>
	<c:when test = "${fn:startsWith(src, 'http')}">
        <img src="${src}" alt="${alt}" class="${cssClass}">
   	</c:when>
	<c:otherwise>
		<img src="${resourcesPath}/${src}" alt="${alt}" class="${cssClass}">
	</c:otherwise>
</c:choose>
