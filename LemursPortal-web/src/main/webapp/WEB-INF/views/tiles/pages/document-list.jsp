 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                                       
														</br>
                                                   <div class="row">
	                                                   <c:set var="isa" value="1"/>
	                                                    <c:forEach items="${docIMAGE}" var="pic">
	                                                    	 <div class="column">
	    														<img src="${resourcesPath}/upload/${pic.filename}" style="width:100%" onclick="openModal();currentSlide(${isa})" class="hover-shadow cursor">
	  														</div>
	  														<c:set var="isa" value="${isa+1}"/>
	                                                     </c:forEach>
                                                     </div>
                                                     <div id="myModal" class="modal">
  														<span class="close cursor" onclick="closeModal()">&times;</span>
  														<div class="modal-content">
  														 <c:set var="isa1" value="1"/>
  														 <c:forEach items="${docIMAGE}" var="pic">
  															<div class="mySlides">
														      <div class="numbertext"><c:out value="${isa1 }"/> /<c:out value="${isa }"/></div>
														      <img src="${resourcesPath}/upload/${pic.filename}" style="width:100%">
														      <c:set var="isa1" value="${isa1+1}"/>
														    </div>
														  </c:forEach>
														  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    														<a class="next" onclick="plusSlides(1)">&#10095;</a>
  														</div>
  													</div>	
  													
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
             
                                