<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<style type="text/css">
a.read-more {
	color: black /*#74ac00*/;
	cursor: pointer;
}

a.read-more:hover {
	text-decoration: underline;
	color: #000;
}

.show-read-more .more-text, .home-bio p .more-text {
	display: none;
}
</style>
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="forum-container">
			<div class="forum-title">
			<sec:authorize access="isAuthenticated()">
				<div class="pull-right forum-desc">
					<c:url value="/secured/post/create" var="addQuestionUrl"/>
					<a class="add-quest" href="${addQuestionUrl}"><spring:message code="home.ask.question"/></a>
				</div>
			</sec:authorize>
				<h2>
					<spring:message code="home.topquestions" />
				</h2>
			</div>
			
			<!-- D Sujet -->
			<c:forEach items="${topQuestionsPage.content}" var="topQuestion">
				<post:question topQuestion="${topQuestion}"/>
			</c:forEach>
			<!-- F Sujet -->

<!-- 			D Pagination -->
				<c:url var="pageBaseUrl" value="/"/>
				<page:pagination currentPage="${topQuestionsPage.number + 1}" page="page" totalPages="${topQuestionsPage.totalPages}" pageBaseUrl="${pageBaseUrl}"/>
		</div>
	</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var maxLength = 320;
// 						$('.show-read-more').contents().unwrap();
						$(".show-read-more")
								.each(
										function() {
											var myStr = $(this).text();
											if ($.trim(myStr).length > maxLength) {
												var newStr = myStr.substring(0,
														maxLength);
												var removedStr = myStr
														.substring(
																maxLength,
																$.trim(myStr).length);
												$(this).empty().html(
														newStr);
												$(this)
														.append(
																' <a class="read-more">... read more</a>');
												$(this)
														.append(
																'<span class="more-text">'
																		+ removedStr
																		+ '</span>');
											}
										});
						$(".read-more").click(function(){
							$(this).siblings(".more-text").contents().unwrap();
							$(this).remove();
						});
					});
</script>
