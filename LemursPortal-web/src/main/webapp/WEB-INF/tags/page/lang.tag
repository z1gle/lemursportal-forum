<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@ attribute name="pageBaseUrl" required="true" rtexprvalue="true"
	type="java.lang.String" description="L'url de page page courante"%>
<%
	String baseUrl = pageBaseUrl.indexOf("?") == -1 ? pageBaseUrl + "?" : pageBaseUrl + "&";
%>
<c:set var="baseUrl" value="<%=baseUrl%>"/>
<div class="lang lang-chooser">
    <select id="lang-select">
        <option value="fr"><spring:message code="global.lang.french"/></option>
        <option value="en"><spring:message code="global.lang.english"/></option>
        <option value="mg"><spring:message code="global.lang.malagasy"/></option>
    </select>
</div>