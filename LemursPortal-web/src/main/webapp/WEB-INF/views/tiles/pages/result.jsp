<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:url value="/resources" var="resourcesPath"/>


<div class="container lemurs-page">
    <div class="row">
        <div class="col-md-9">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container liste-quest">
    
                    <div class="forum-title">
                        <div class="pull-right forum-desc">
                            <a class="add-quest" href="secured/post/create"><spring:message code="home.ask.question"/></a>
                        </div>
                        
                    </div>
    				
                    <!-- D Sujet -->
                   <c:forEach items="${posts.content}" var="post">
    					<div class="forum-item">
	                        <div class="row">
	                            <div class="col-md-8">
	                                <div class="forum-profil">
	                                  <a href="#"><img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/></a>
	                                  <div class="reponse-user"><a href="#"><c:out value="${post.owner.nom}"/> <c:out value="${post.owner.prenom}"/></a><br/><i></i></div>
	                                </div>
	                                <a href="/lemursPortal/post/show/${post.id}" class="forum-item-title"><c:out value="${post.title}" /></a>
	                                <div class="forum-sub-title">
	                                	<c:out value="${post.body}" escapeXml="true" />
	                                    <p class="forum-date"><fmt:formatDate pattern="${datetimeFormat}" value="${post.creationDate}"/></p>
	                                </div>
	                            </div>
	                            <div class="col-md-1 forum-info">
	                                
	                            </div>
	                            <div class="col-md-3 forum-user-info">
	                              
	                            </div>
	                            
	                        </div>
	                    </div>
    				</c:forEach>
                    <!-- F Sujet -->
                    
                    <!-- D Pagination -->
                    <ul class="pagination">
                        <li class="disabled"><a href="#">&laquo;</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                    <!-- F Pagination -->
                    
                </div>
            </div>
        </div>
        
        
        
    </div>
</div>

