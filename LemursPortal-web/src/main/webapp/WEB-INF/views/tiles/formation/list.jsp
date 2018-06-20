<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="${resourcesPath}tinymce/jquery.tinymce.min.js"></script>
<script type="text/javascript" src="${resourcesPath}tinymce/tinymce.min.js"></script>

<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
	
            <div class="wrapper wrapper-content animated fadeInRight">
    			<!-- D formation -->
                <div class="forum-container liste-generale">
    
                    <div class="page-title">
                        <div class="pull-right forum-desc">
                        	<c:url value="/formation/new" var="newFormation"></c:url>
                        	<sec:authorize access="hasAnyRole('EXPERT', 'ADMIN', 'MODERATEUR')">
                            <a class="btn btn-primary btn-xs pull-right" href="${newFormation}">Ajouter une opportunité</a>
                            </sec:authorize>
                        </div>
                        <h2 class="formation">Liste des formations</h2>
                    </div>
    				
                    <c:forEach items="${formations}" var="formations">
    					<div class="forum-item">
	                        <div class="row">
		                            <div class="col-md-7">
			                            <c:url value="/formation/${formations.id}" var="viewFormationUrl" />
		                                <a href="${viewFormationUrl}" class="forum-item-title"><c:out value="${formations.title}" /></a>
		                                <div class="desc">
		                                	<c:out value="${formations.description}" escapeXml="true" />
		                                </div>
		                            </div>
		                            <div class="col-md-2">
		                                 <small><c:out value="${formations.viewCount}" escapeXml="true" /> lectures</small>
		                            </div>
		                            <div class="col-md-3 forum-user-info">
		                               <div class="reponse-user">Publiée par :<br />
		                               	<a href="#"><c:out value="${formations.owner.prenom}" escapeXml="true" /></a><br/>
		                               	<fmt:formatDate pattern="${datetimeFormat}" value="${formations.creationDate}"/></div>
		                            </div>
		                            
		                    </div>
	                            
	                        </div>
    				</c:forEach>
                    
                    
                    
                </div>
                
                <!-- D Pagination -->
				<!--
                <ul class="pagination">
                    <li class="disabled"><a href="#">&laquo;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
				-->
                <!-- F Pagination -->
              <!-- F formation -->  
                
            </div>