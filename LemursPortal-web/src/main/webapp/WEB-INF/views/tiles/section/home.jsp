<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="forum-container">

			<div class="forum-title">
				<div class="pull-right forum-desc">
					<a class="add-quest" href="secured/post/create"><spring:message code="home.ask.question"/></a>
				</div>
				<h2>
					<spring:message code="home.topquestions" />
				</h2>
			</div>
			<!-- D Sujet -->
			<c:forEach items="${topQuestionsPage.content}" var="topQuestion">
				<div class="forum-item">
					<div class="row">
						<div class="col-md-8">
							<div class="forum-profil">
								<a href="#"><img class="img-circle"
									src="${resourcesPath}/images/user1.png" alt="" /></a>
								<div class="reponse-user">
									<a href="#"><c:out
											value="${topQuestion.question.owner.nom}" /> <c:out
											value="${topQuestion.question.owner.prenom}" /></a><br />
									<i><c:out value="${question.role}" /></i>
								</div>
							</div>
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
								<small><c:out value="${topQuestion.nbVue}" /> <spring:message
										code="home.topquestions.vues" /></small>
							</div>
						</div>
						<div class="col-md-3 forum-user-info">
							<a href="#"><img class="img-circle"
								src="${resourcesPath}/images/user2.png" alt="" /></a>
							<div class="reponse-user">
								<a href="#"> <c:out
										value="${topQuestion.derniereReponse.owner.nom}" /> <c:out
										value="${topQuestion.derniereReponse.owner.prenom}" />
								</a><br />
								<fmt:formatDate pattern="${datetimeFormat}"
									value="${topQuestion.derniereReponse.creationDate}" />
								<br />
								<c:forEach items="${topQuestion.derniereReponse.owner.roles}"
									var="role">
									<i><c:out value="${role.libelle}" /></i>
									<br />
								</c:forEach>
							</div>
						</div>

					</div>
				</div>
			</c:forEach>
			<!-- F Sujet -->

<!-- 			D Pagination -->
			<c:url var="firstUrl" value="/?page=1" />
			<c:url var="lastUrl" value="/?page=${topQuestionsPage.totalPages}" />
			<c:url var="prevUrl" value="/?page=${paginationCurrent - 1}" />
			<c:url var="nextUrl" value="/?page=${paginationCurrent + 1}" />
			
			    <ul class="pagination">
			        <c:choose>
			            <c:when test="${paginationCurrent == 1}">
			                <li class="disabled"><a href="#">&lt;&lt;</a></li>
			                <li class="disabled"><a href="#">&lt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="${firstUrl}">&lt;&lt;</a></li>
			                <li><a href="${prevUrl}">&lt;</a></li>
			            </c:otherwise>
			        </c:choose>
			        <c:forEach var="i" begin="${paginationBegin}" end="${paginationEnd}">
			            <c:url var="pageUrl" value="/?page=${i}" />
			            <c:choose>
			                <c:when test="${i == paginationCurrent}">
			                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:when>
			                <c:otherwise>
			                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			        <c:choose>
			            <c:when test="${paginationCurrent == topQuestionsPage.totalPages}">
			                <li class="disabled"><a href="#">&gt;</a></li>
			                <li class="disabled"><a href="#">&gt;&gt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="${nextUrl}">&gt;</a></li>
			                <li><a href="${lastUrl}">&gt;&gt;</a></li>
			            </c:otherwise>
			        </c:choose>
			    </ul>
<!-- 			                    <ul class="pagination"> -->
<!-- 			                        <li class="disabled"><a href="#">&laquo;</a></li> -->
<!-- 			                        <li class="active"><a href="#">1</a></li> -->
<!-- 			                        <li><a href="#">2</a></li> -->
<!-- 			                        <li><a href="#">3</a></li> -->
<!-- 			                        <li><a href="#">4</a></li> -->
<!-- 			                        <li><a href="#">5</a></li> -->
<!-- 			                        <li><a href="#">&raquo;</a></li> -->
<!-- 			                    </ul> -->
<!-- 			F Pagination -->

		</div>
	</div>


