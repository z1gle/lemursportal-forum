<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="RedirectUrl" value="${url}"></c:set>
<meta http-equiv="refresh" content="2; url=${RedirectUrl}" />

<div class="alert alert-success">
	<h2>${messageProperty}</h2>
</div>
