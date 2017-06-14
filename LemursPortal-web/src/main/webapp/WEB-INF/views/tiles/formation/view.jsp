<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="${resourcesPath}tinymce/jquery.tinymce.min.js"></script>
<script type="text/javascript" src="${resourcesPath}tinymce/tinymce.min.js"></script>

<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<c:url value="/formation/${formation.id}/edit" var="editFormationUrl" />
<c:url value="/formation/${formation.id}/delete" var="deleteFormationUrl" />
<c:url value="/formation/new" var="newFormation"></c:url>
            <div class="wrapper wrapper-content animated fadeInRight">
    			<!-- D formation -->
                <div class="forum-container liste-generale">
    
                    <div class="page-title">
                        <div class="pull-right forum-desc">
                        	<c:set var="uLogin" value="" />
							<sec:authorize access="hasRole('EXPERT')">
								<a class="btn btn-primary btn-xs pull-right" href="${newFormation}">Ajouter une formation</a>
								<c:set var="uLogin">
									<sec:authentication property="principal.username" /> 
								</c:set>
							</sec:authorize>
                            
                        </div>
                        <h2 class="formation"><c:out value="${formation.title}" escapeXml="true" /></h2>
						
						<!-- user is admin -->
						<c:set var="isAdmin" value="false" />
						<sec:authorize access="hasRole('MODERATEUR')">
						    <c:set var="isAdmin" value="true" />
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
						    <c:set var="isAdmin" value="true" />
						</sec:authorize>
						
						<!-- user is author -->
						<c:set var="isAuthor" value="false" />
						<sec:authorize access="hasRole('EXPERT')">
							<c:if test="${uLogin == formation.owner.login}">
								<c:set var="isAuthor" value="true" />
							</c:if>
						</sec:authorize>

						<sec:authorize access="isAuthenticated()">
							<c:if test="${isAuthor || isAdmin}">
								<a href="${editFormationUrl}" class="btn-info btn-xs" type="button">
									<i class="glyphicon glyphicon-edit"></i>
									Edit	
								</a>&nbsp;
								<a href="${deleteFormationUrl}" class="btn-danger btn-xs" type="button" id="delete-formation">
									<i class="glyphicon glyphicon-exclamation-sign"></i>
									Delete
								</a><br />
							</c:if>
						</sec:authorize>
						
						<br />
						<div class="">
						<small><a href="#">
							<strong><c:out value="${formation.owner.prenom}" escapeXml="true" /></strong>
						</a>&nbsp; <fmt:formatDate pattern="${datetimeFormat}" value="${formation.creationDate}"/>
						</small>&nbsp;|&nbsp;
						<small class="text-muted">
							<i class="glyphicon glyphicon-check"></i>Vues: <c:out value="${formation.viewCount}" escapeXml="true" />
						</small>
						</div>
                    </div>
    				
                    <div class="forum-item">
	                        <div class="row">
		                            <c:out value="${formation.body}" escapeXml="false" />
		                    </div>
	                            
	                        </div>
                    
                </div>
                
                
              <!-- F formation -->  
           <script>
			$("#delete-formation").click(function() {
				if (confirm("Etes-vous sûr?")){
					location.href="${deleteFormationUrl}";
				};
				return false;
			});
		</script>
            </div>