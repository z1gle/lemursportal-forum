<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="lang">
    <select onchange="javascript:alert(this.value);">
        <option value="fr"><spring:message code="global.lang.french"/></option>
        <option value="en"><spring:message code="global.lang.english"/></option>
        <option value="mg"><spring:message code="global.lang.malagasy"/></option>
    </select>
</div>