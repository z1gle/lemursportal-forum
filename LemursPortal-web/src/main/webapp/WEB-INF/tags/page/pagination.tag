<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@ attribute name="totalPages" required="true" rtexprvalue="true"
	type="java.lang.Integer" description="Nombre total des pages"%>
<%@ attribute name="currentPage" required="true" rtexprvalue="true"
	type="java.lang.Integer" description="La page courante"%>
<%@ attribute name="pageBaseUrl" required="true" rtexprvalue="true"
	type="java.lang.String" description="L'url de page page courante"%>
<%@ attribute name="page" required="true" rtexprvalue="true"
	type="java.lang.String" description="L'onglet active"%>
<%
	int begin = Math.max(1, currentPage - 3);
	int end = Math.min(begin + 6, totalPages);
	String baseUrl = pageBaseUrl.indexOf("?") == -1 ? pageBaseUrl + "?" : pageBaseUrl + "&";
%>
<c:url value="/resources" var="resourcesPath" />
<c:set var="begin" value="<%=begin%>"/>
<c:set var="end" value="<%=end%>"/>
<c:set var="baseUrl" value="<%=baseUrl%>"/>
<!-- 			D Pagination -->
<c:set var="firstUrl"></c:set>
<c:set var="firstUrl" value="${baseUrl}${page}=1" />
<c:set var="lastUrl" value="${baseUrl}${page}=${totalPages}" />
<c:set var="prevUrl" value="${baseUrl}${page}=${currentPage - 1}" />
<c:set var="nextUrl" value="${baseUrl}${page}=${currentPage + 1}" />

<ul class="pagination">
	<c:choose>
		<c:when test="${currentPage == 1}">
			<li class="disabled"><a href="#">&lt;&lt;</a></li>
			<li class="disabled"><a href="#">&lt;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${firstUrl}">&lt;&lt;</a></li>
			<li><a href="${prevUrl}">&lt;</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${begin}" end="${end}">
		<c:set var="pageUrl" value="${baseUrl}${page}=${i}" />
		<c:choose>
			<c:when test="${i == currentPage}">
				<li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${currentPage == totalPages}">
			<li class="disabled"><a href="#">&gt;</a></li>
			<li class="disabled"><a href="#">&gt;&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${nextUrl}">&gt;</a></li>
			<li><a href="${lastUrl}">&gt;&gt;</a></li>
		</c:otherwise>
	</c:choose>
</ul>