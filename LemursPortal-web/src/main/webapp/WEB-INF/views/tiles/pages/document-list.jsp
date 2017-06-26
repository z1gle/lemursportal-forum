 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<div class="forum-container page-document">
                        <div class="row">
                        	<div class="page-title">
                        		<h2 class="doc">Liste des documents</h2>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="box">
                                    <!-- D Tab -->
                                    <ul class="nav nav-tabs userProfileTabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true">Photos</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false">Videos</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false">Audios</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-4" aria-controls="tab-item-4" role="tab" data-toggle="tab" aria-expanded="false">Publications</a></li>
                                         <li role="presentation" class=""><a href="#tab-item-5" aria-controls="tab-item-5" role="tab" data-toggle="tab" aria-expanded="false">Youtube</a></li>
                                    </ul>
									<!-- F Tab -->
                                    
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade active in" id="tab-item-1">
                                            <div class="txt-content">
                                               
                                               <div class="col-md-offset custyle">
                                               		<!-- The Modal -->
														<div id="myModal" class="modal">
														
														  <!-- The Close Button -->
														  <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
														
														  <!-- Modal Content (The Image) -->
														  <img class="modal-content" id="img01">
														
														  <!-- Modal Caption (Image Text) -->
														  <div id="caption"></div>
														</div>
														</br>
                                                    <table class="table table-striped custab">
                                                    <c:forEach items="${docIMAGE}" var="pic">
                                                    	 <div class="col-md-3">
                                							
	                                    						
	                                    							<img src="${resourcesPath}/upload/${pic.filename}" id="myImg" class="img-responsive" alt="${pic.filename}"/>
	                                   							 
                                							
                            							</div>
                                                     </c:forEach>
                                                    </table>
                                                    <!-- D Pagination -->
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#">&laquo;</a></li>
                                                        <li class="active"><a href="#">1</a></li>
                                              
                                                    </ul>
                                                    <!-- F Pagination -->
                                                </div>
                                               
                                            </div>
                                        </div>
                                
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-2">
                                            <div class="txt-content">
                                            
                                            <div class="col-md-offset custyle">
                                                    <table class="table table-striped custab">
                                                    <c:forEach items="${docVIDEO}" var="video">
                                            		 
                                                        <tr>
                                                        	<td><img src="${resourcesPath}/images/icon-video-document.png" alt=""></td>
                                                            <td><c:out	value="${video.filename}" /></td>
                                                            <td class="text-center">
                                                            <c:url var="videoPageUrl" value="/files/${video.id}"/>
                                                            <a class='btn btn-info btn-xs' href="${videoPageUrl}"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        </c:forEach>
                                                    </table>
                                                    
                                                    <!-- D Pagination -->
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#">&laquo;</a></li>
                                                        <li class="active"><a href="#">1</a></li>
                                                    </ul>
                                                    <!-- F Pagination -->
                                                </div>
                                            
                                            </div>
                                        </div>
                                        
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-3">
                                            <div class="txt-content">
                                               <div class="col-md-offset custyle">
                                                	<table class="table table-striped custab">
                                                	 <c:forEach items="${docAUDIO}" var="audio">
                                            		 
                                                        <tr>
                                                        	<td><img src="${resourcesPath}/images/icon-audio.png" alt=""></td>
                                                            <td><c:out	value="${audio.filename}" /></td>
                                                            <td class="text-center">
                                                            <c:url var="audioPageUrl" value="/files/${audio.id}"/>
                                                            <a class='btn btn-info btn-xs' href="${audioPageUrl}"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        </c:forEach>
                                                        
                                                    </table>
                                                    
                                                    <!-- D Pagination -->
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#">&laquo;</a></li>
                                                        <li class="active"><a href="#">1</a></li>
                                                    </ul>
                                                    <!-- F Pagination -->
                                                </div>
                                               
                                            </div>
                                        </div>
                                
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-4">
                                            <div class="txt-content">
                                    			<div class="col-md-offset custyle">
                                            		<table class="table table-striped custab">
                                            		 <c:forEach items="${docAUTRES}" var="publication">
                                            		 
                                                        <tr>
                                                        	<td><img src="${resourcesPath}/images/icon-thema.png" alt=""></td>
                                                            <td><c:out	value="${publication.filename}" /></td>
                                                            <td class="text-center">
                                                            <c:url var="publicationPageUrl" value="/files/${publication.id}"/>
                                                            <a class='btn btn-info btn-xs' href="${publicationPageUrl}"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        </c:forEach>
                                                    </table>
                                                    
                                                    <!-- D Pagination -->
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#">&laquo;</a></li>
                                                        <li class="active"><a href="#">1</a></li>
                                                    </ul>
                                                    <!-- F Pagination -->
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-5">
                                            <div class="txt-content">
                                    			<div class="col-md-offset custyle">
                                            		<table class="table table-striped custab">
                                            		 <c:forEach items="${youtubeFiles}" var="youtubFile">
                                            		 
                                                        <tr>
                                                        	<td><img src="${resourcesPath}/images/youtube-icon.png" alt=""></td>
                                                            <td><c:out	value="${youtubFile.title}" /></td>
                                                            <td class="text-center">
                                                            <c:url var="publicationPageUrl" value="${youtubFile.uriYoutube}"/>
                                                            <a class='btn btn-info btn-xs' href="${publicationPageUrl}"  target="_blank"><span class="glyphicont"></span>Voir</a></td>
                                                        </tr>
                                                        </c:forEach>
                                                    </table>
                                                    
                                                    <!-- D Pagination -->
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#">&laquo;</a></li>
                                                        <li class="active"><a href="#">1</a></li>
                                                    </ul>
                                                    <!-- F Pagination -->
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                         </div>
                   </div>
                   </div>
             
                                