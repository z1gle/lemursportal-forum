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
                                <a href="#" class="btn btn-primary btn-xs pull-right">Ajouter un media</a>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="box">
                                    <!-- D Tab -->
                                    <ul class="nav nav-tabs userProfileTabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true">Photos</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false">Videos</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false">Audios</a></li>
                                        <li role="presentation" class=""><a href="#tab-item-4" aria-controls="tab-item-4" role="tab" data-toggle="tab" aria-expanded="false">Publications</a></li>
                                    </ul>
									<!-- F Tab -->
                                    
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade active in" id="tab-item-1">
                                            <div class="txt-content">
                                               
                                               <div class="col-md-offset custyle">
                                                    <table class="table table-striped custab">
                                                        <tr>
                                                            <td><a href="#"><img src="images/lem.png" alt=""></a></td>
                                                            <td>Sifaka</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="#"><img src="images/lem.png" alt=""></a></td>
                                                            <td>Lemuriens</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="#"><img src="images/lem.png" alt=""></a></td>
                                                            <td>Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="#"><img src="images/lem.png" alt=""></a></td>
                                                            <td>Lorem ipsum dolor not</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="#"><img src="images/lem.png" alt=""></a></td>
                                                            <td>Plage de Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                    </table>
                                                    
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
                                
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-2">
                                            <div class="txt-content">
                                            
                                            <div class="col-md-offset custyle">
                                                    <table class="table table-striped custab">
                                                        <tr>
                                                        	<td><img src="images/icon-video-document.png" width="40" alt=""></td>
                                                            <td>Sifaka</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Voir la video</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-video-document.png" width="40" alt=""></td>
                                                            <td>Lemuriens</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Voir la video</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-video-document.png" width="40" alt=""></td>
                                                            <td>Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Voir la video</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-video-document.png" width="40" alt=""></td>
                                                            <td>Lorem ipsum dolor not</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Voir la video</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-video-document.png" width="40" alt=""></td>
                                                            <td>Plage de Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Voir la video</a></td>
                                                        </tr>
                                                    </table>
                                                    
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
                                        
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-3">
                                            <div class="txt-content">
                                               <div class="col-md-offset custyle">
                                                	<table class="table table-striped custab">
                                                        <tr>
                                                        	<td><img src="images/icon-audio.png" alt=""></td>
                                                            <td>Sifaka</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-audio.png" alt=""></td>
                                                            <td>Lemuriens</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-audio.png" alt=""></td>
                                                            <td>Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-audio.png" alt=""></td>
                                                            <td>Lorem ipsum dolor not</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td><img src="images/icon-audio.png" alt=""></td>
                                                            <td>Plage de Madagascar</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                    </table>
                                                    
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
                                
                                        <div role="tabpanel" class="tab-pane fade" id="tab-item-4">
                                            <div class="txt-content">
                                    			<div class="col-md-offset custyle">
                                            		<table class="table table-striped custab">
                                                        <tr>
                                                            <td>Sifaka-de-Madagascar.pdf</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Tout-savoir-sur-les-Lemuriens.docx</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Madagascariens-tome-3.docx</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Statistique-lemuriens.xls</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Plage-de-Madagascar2017-avril-05.pdf</td>
                                                            <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Télécharger</a></td>
                                                        </tr>
                                                    </table>
                                                    
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
                         </div>
                   </div>
             
                                