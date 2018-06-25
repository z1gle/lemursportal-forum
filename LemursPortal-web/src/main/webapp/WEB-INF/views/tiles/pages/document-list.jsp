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
<c:url value="/" var="basePath"/>
<style>	
<!--	
.project-wrapper {	
    margin: 0;	
    padding: 0;	
    list-style: none;	
    text-align: center;	
}	
	
.project-wrapper li {	
    display: inline-block;	
}	
	
.species-item {	
    cursor: pointer;	
    margin: 0 1% 1% 0;	
    overflow: hidden;	
    position: relative;	
    width: 32%;	
}	
	
p {	
    line-height: 25px;	
}	
figure, p, address {	
    margin: 0;	
}	
	
ul.external {	
    list-style: outside none none;	
    margin: 0;	
    padding: 0;	
    position: absolute;	
    right: 0;	
    top: -47px;	
    -webkit-transition: all 0.4s ease 0s;	
    -moz-transition: all 0.4s ease 0s;	
    -ms-transition: all 0.4s ease 0s;	
    -o-transition: all 0.4s ease 0s;	
    transition: all 0.4s ease 0s;	
}	
.species-item img {	
	background-size: cover;	
	background-position: 50% 25%;	
}	
	
figcaption.mask {	
    /* background-color: rgba(54, 55, 50, 0.79); */	
/* 	background-image: url('${resourcesPath}/images/gradient.png');  */	
    background: linear-gradient(to top,rgba(0,0,0,0.65) 0%,transparent 100%);	
    bottom: 0;	
    color: #fff;	
    padding: 25px;	
    position: absolute;	
    width: 100%;	
    text-align: left;	
    -webkit-transition: all 0.4s ease 0s;	
    -moz-transition: all 0.4s ease 0s;	
    -ms-transition: all 0.4s ease 0s;	
    -o-transition: all 0.4s ease 0s;	
    transition: all 0.4s ease 0s;	
}	
	
.species-item:hover figcaption.mask {	
    bottom: -126px
}	
	
figcaption.mask h3, figcaption.mask p {	
    margin: 0;	
    color: #fff;	
    font-size: 12px;	
    text-overflow: ellipsis;	
    overflow: hidden;	
}	
.species-item img:hover {	
	opacity:1;	
}	
.species-item img:hover, .species-item img, .species-item iframe {	
	border: 1px solid #d7d7d7;	
}	
-->	
</style>
<div class="forum-container page-document">
    <div class="row">
        <div class="page-title">
            <h2 class="doc">Liste des documents</h2>
        </div>
        <div class="col-xs-12 col-md-12">
            <div class="box">
                <!-- D Tab -->
                <ul class="nav nav-tabs userProfileTabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-4" role="tab" data-toggle="tab" aria-expanded="false">Documents</a></li>
                    <li role="presentation" class=""><a href="#tab-item-4" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true">Photos</a></li>
                    <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false">Videos</a></li>
                    <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false">Audios</a></li>                    
                    <li role="presentation" class=""><a href="#tab-item-5" aria-controls="tab-item-5" role="tab" data-toggle="tab" aria-expanded="false">Youtube</a></li>
                        <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
                        <c:choose>
                            <c:when test="${isLoggedInUser}">
                            <li style="float: right;"><button style="color: white;" class="btn" aria-controls="tab-item-5" role="tab" aria-expanded="false" onclick="openModal('modal-ajout-document')">Ajouter</button></li>
                            </c:when>
                            <c:otherwise>                            
                            </c:otherwise>
                        </c:choose>                    
                </ul>
                <!-- F Tab -->

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active fade in" id="tab-item-1">
                        <div class="txt-content">
                            <div class="col-md-offset custyle">
                      <table style="border: 0px;" class="table table-striped custab">
                                    <c:set var="years" value="${0}"/>
                                    <c:forEach items="${docAUTRES}" var="publication">      
                                        <c:choose>
                                            <c:when test="${years == 0}">
                                                <c:set var="years" value="${publication.year}"/>
                                                <tr style="background-color: white;">
                                                    <th style="color:  dodgerblue;font-size: 15px; border-color: white; padding-left: 20px;">${publication.year}</th>                                                                                                        
                                                    <th style="color:  dodgerblue;font-size: 15px; border-color: white;"></th>                                                                                                        
                                                    <th style="color:  dodgerblue;font-size: 15px; border-color: white;"></th>                                                                                                        
                                                    <th style="color:  dodgerblue;font-size: 15px; border-color: white;"></th>                                                                                                        
                                                </tr>
                                            </c:when>                                            
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${years != publication.year}">
                                                        <c:set var="years" value="${publication.year}"/>
                                                        <tr style="background-color: white;">
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; padding-left: 20px; border-top-color: #dddddd;">${publication.year}</th>                                                                                                        
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; border-top-color: #dddddd;"></th>                                                                                                        
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; border-top-color: #dddddd;"></th>                                                                                                        
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; border-top-color: #dddddd;"></th>                                                                                                        
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise></c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                        <tr style="border:1px solid #ccc;">                                                                                        
                                            <td><a href="#" onclick="showDetail(${publication.id})"><c:out	value="${publication.title}" /></a></td>
                                            <td><c:out	value="${publication.coverage}" /></td>
                                            <td><c:out	value="${publication.creator}" /></td>
                                            <td class="text-center">
                                                <%--<c:url var="publicationPageUrl" value="/files/${publication.idDocument}"/>--%>
                                                <c:url var="publicationPageUrl" value="${publication.url}"/>
                                                <!--<a class='btn btn-info btn-xs' href="${publicationPageUrl}" download="${publication.title}.pdf"><span class="glyphicont"></span>Télécharger</a></td>-->
                                                <a href="${publicationPageUrl}"><span class="glyphicont"></span><img src="${resourcesPath}/images/icon-thema.png" alt=""></a></td>
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

                                </br>
                                <div class="row">
                                    <c:set var="isa" value="1"/>
                          <%--          <c:forEach items="${docIMAGE}" var="pic">
                                        <div class="column col-lg-2 col-md-2 col-sm-4 col-xs-12">
                                            <a href="#" onclick="showPhoto('${pic.title}', '${basePath}${pic.url}')"><img src="${basePath}${pic.url}" style="width:100%" onclick="openModal();currentSlide(${isa})" class="hover-shadow cursor"></a>
                                        </div>
                                        <c:set var="isa" value="${isa+1}"/>
                                    </c:forEach> --%>
                                    <ul class="project-wrapper animated fadeInUp" style="text-align: left !important">
                                    <c:forEach items="${docIMAGE}" var="pic">
                                    	<li class="species-item">
                                    		<a href="#" onclick="showPhoto('${pic.filename}')">
                                    		<img src="${resourcesPath}/images/l-blank.png" style="background-image: url('${resourcesPath}/upload/${pic.filename}'); " class="img-responsive" 
                                    				onclick="openModal();currentSlide(${isa})" alt="--">
                                    		</a>
                                    		<figcaption class="mask">
                                    			<p><i>${pic.filename}</i></p>
                                    			<p>--</p>
                                    		</figcaption>
                                    	</li>
                                        <c:set var="isa" value="${isa+1}"/>
                                    </c:forEach>
                                    </ul>
                                </div>
                                <div id="myModal" class="modal">
                                    <span class="close cursor" onclick="closeModal()">&times;</span>
                                    <div class="modal-content">
                                        <c:set var="isa1" value="1"/>
                                        <c:forEach items="${docIMAGE}" var="pic">
                                            <div class="mySlides">
                                                <div class="numbertext"><c:out value="${isa1 }"/> /<c:out value="${isa }"/></div>
                                                <!--<img src="${pic.url}" style="width:100%">-->
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
                    <div role="tabpanel" class="tab-pane fade" id="tab-item-5">
                        <div class="txt-content">
                            <div class="col-md-offset custyle">
                                </br>
                                <div class="row">
								<ul class="project-wrapper animated fadeInUp" style="text-align: left !important">
									<c:forEach items="${youtubeFiles}" var="youtubFile">
										<li class="species-item">
											<iframe class="img-responsive" height="250"
                                                    src="${youtubFile.uriYoutube}?showinfo=0"/>
                                            </iframe>
											<figcaption class="mask">
												<p>
													<i>${youtubFile.title}</i>
												</p>
											</figcaption></li>
									</c:forEach>
								</ul>
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
                </div>
            </div>
        </div>

        <style>            
            .autocomplete {                
                position: relative;
                display: inline-block;
            }            
            .autocomplete-items {
                position: absolute;
                border: 1px solid #d4d4d4;
                border-radius: 4px;
                border-color: #584419;
                border-top: none;
                z-index: 99;                
                top: 100%;
                left: 0;
                right: 0;
            }
            .autocomplete-items div {
                padding: 10px;
                cursor: pointer;
                background-color: #f9efc8fa;                
                border-bottom: 1px solid #d4d4d4;                 
            }
            .autocomplete-items div:hover {
                /*when hovering an item:*/
                background-color: #e9e9e9; 
            }
            .autocomplete-active {
                /*when navigating through the items using the arrow keys:*/
                background-color: #584419 !important; 
                color: #ffffff; 
            }
            .dropdown-menu {
                background-color: #f9efc8fa;
            }
            .multiselect-container>li.active>a {
                background-color: #584419!important;
            }        
            .multiselect-selected-text {
                float: left;
            }
            .caret {
                float: right;
                margin: 8px;
            }            
        </style>

        <div id="modal-ajout-document" class="modal edit-profil-form">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-ajout-document')">&times;</button>
                        <h4 class="modal-title">Ajouter un document</h4>
                    </div>
                                        <form action="javascript:sendAddDocument();" autocomplete="off">
                        <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                            <div id="errorMdp"></div>
                            <spring:message code="metadata.topics"/><sup>*</sup><br>
                            <select required multiple="" id="id_thematique" class="form-control">
                                <option value="797277">Behavior </option>
                                <option value="797278">Threats /conservation issues </option>
                                <option value="797279">Vocalization </option>
                                <option value="797280">Ecology </option>
                                <option value="797281">Genetics </option>
                                <option value="797282">Locomotion </option>                            
                                <option value="797283">Taxonomy</option>
                                <option value="797284">Conservation Status</option>
                                <option value="797285">Subfossiles</option>
                                <option value="797286">Lemur conservation and research adminsistrative </option>
                                <option value="797287">Environmental Education</option>
                                <option value="797288">Lemur in captivity</option>
                                <option value="797289">Lemur Medicine/Biomedical assessement</option>
                                <option value="797290">Species disstribution and occurences</option>
                                <option value="797291">Nocturnal species</option>
                                <option value="797292">Diurnal species</option>
                                <option value="797293">Reintroduction and translocation</option>
                                <option value="797294">Lemur conservation success</option>
                                <option value="797295">Nutrition</option>
                                <option value="797296">Forest fragment</option>
                                <option value="797297">Parasites</option>
                                <option value="797298">Others</option>
                            </select>                            
                            <spring:message code="metadata.type"/><sup>*</sup>
                            <select class="form-control" id="type" style="width: 100%!important;">
                                <option style="background-color: #f9efc9;" value="4">Document</option>
                                <option style="background-color: #f9efc9;" value="1">Photo</option>
                                <option style="background-color: #f9efc9;" value="2">Video</option>
                                <option style="background-color: #f9efc9;" value="3">Audio</option>
                            </select>                            
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.year"/><sup>*</sup>                        
                                <input required type="text" class="form-control" id="year">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.title"/><sup>*</sup>
                                <input required type="text" class="form-control" id="title">
                            </div>
                            <spring:message code="global.label.file"/>
                            <input type="file" class="form-control" id="document">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.url"/>
                                <input type="text" class="form-control" id="url">
                            </div>
                            <div class="" style="width: 100%;">
                                <spring:message code="metadata.species"/>
                                <select multiple class="form-control" id="species" style="width: 100%!important;"></select>
                                <!--<input type="text" class="form-control" id="bibliographic_resource">-->
                            </div>
                            <spring:message code="metadata.date"/>
                            <input type="date" class="form-control" id="datePublication">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.bibliographicResource"/>
                                <select class="form-control" id="bibliographic_resource" style="width: 100%!important;">
                                    <option style="background-color: #f9efc9;" value="">none</option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.paper"/>"><spring:message code="metadata.bibliographicResource.paper"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.journal"/>"><spring:message code="metadata.bibliographicResource.journal"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.book"/>"><spring:message code="metadata.bibliographicResource.book"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.report"/>"><spring:message code="metadata.bibliographicResource.report"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.memory"/>"><spring:message code="metadata.bibliographicResource.memory"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.thesis"/>"><spring:message code="metadata.bibliographicResource.thesis"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.map"/>"><spring:message code="metadata.bibliographicResource.map"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.poster"/>"><spring:message code="metadata.bibliographicResource.poster"/></option>                                    
                                    <option style="background-color: #f9efc9;" value="<spring:message code="metadata.bibliographicResource.others"/>"><spring:message code="metadata.bibliographicResource.others"/></option>                                    
                                </select>
                                <!--<input type="text" class="form-control" id="bibliographic_resource">-->
                            </div>                                                
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.coverage"/>
                                <input type="text" class="form-control" id="coverage">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.description"/>
                                <input type="text" class="form-control" id="description">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.language"/>
                                <input type="text" class="form-control" id="language">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.relation"/>
                                <input type="text" class="form-control" id="relation">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.source"/>
                                <input type="text" class="form-control" id="source">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.subject"/>
                                <input type="text" class="form-control" id="subject">
                            </div>                        
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.format"/>
                                <input type="text" class="form-control" id="format">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.fileFormat"/>
                                <input type="text" class="form-control" id="fileFormat">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.identifier"/>
                                <input type="text" class="form-control" id="identifier">
                            </div>                        
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.contributor"/>
                                <input type="text" class="form-control" id="contributor">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.creator"/>
                                <input type="text" class="form-control" id="creator">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.publisher"/>
                                <input type="text" class="form-control" id="publisher">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.rights"/>
                                <input type="text" class="form-control" id="rights">                        
                            </div>                            
                        </div>
                        <div class="modal-footer">
                            <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-ajout-document')">Annuler</button>
                            <button style="float: right;" type="submit" class="btn btn-default" data-dismiss="modal">Enregistrer</button>
                        </div>
                    </form>
                </div>
            </div>                        
        </div>
        <!--modal for detail-->
        <div id="modal-detail" class="modal edit-profil-form">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-detail')">&times;</button>
                        <h4 class="modal-title" id="title-modal-details"></h4>
                    </div>
                    <div id="modal-detail-body" class="modal-body" style="overflow-y: auto;max-height:  500px;"></div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-detail')">Fermer</button>                        
                    </div>
                </div>
            </div>                        
        </div>
    </div>        
</div>
<script>
    function showPhoto(title, url) {
        $('#modal-detail-body').html('<img class="col-md-12" src="' + url + '">');
        $('#title-modal-details').html(title);
        openModal('modal-detail');
    }
    function showDetail(id) {
        $('.removable-row').remove();
        $('#modal-detail-body').html('<table id="table-detail-metadata" class="table"></table>');
        openModal('modal-detail');
        $.get("metadata/" + id, function (data) {
            drawTable(data[0].value, 'table-detail-metadata');
            $('#title-modal-details').html(data[0].value.title);
        }).done(function () {
        });
    }
    function drawTable(data, table) {
        $.each(data, function (key, value) {
            if (key !== 'id' && key !== 'idDocument' && key !== 'idUtilisateur' && key !== 'document' && key !== 'listeAssociationMetadataTopic') {
                if (key === 'url') {
                    if (value.indexOf("/resources/upload/") === 0) {
                        value = value.substring(1);
                    }
                    value = '<a href="' + value + '">' + value + '</a>';
                }
                drawRow(key + ":", value, table);
            }
        });
    }

    function drawRow(key, value, table) {
        var row = $("<tr class='removable-row' />");
        $("#" + table).append(row);
        row.append($("<td>" + key + "</td>"));
        row.append($("<th>" + value + "</th>"));
    }
</script>
<script>
    function sendAddDocument() {
        var formData = new FormData();
        formData.append('file', $('#document').get(0).files[0]);
        formData.append('bibliographicResource', $('#bibliographic_resource').val());
        formData.append('date', $('#datePublication').val());
        formData.append('idThematique', $('#id_thematique').val());
        formData.append('coverage', $('#coverage').val());
        formData.append('description', $('#description').val());
        formData.append('language', $('#language').val());
        formData.append('relation', $('#relation').val());
        formData.append('source', $('#source').val());
        formData.append('subject', $('#subject').val());
        formData.append('title', $('#title').val());
        formData.append('format', $('#format').val());
        formData.append('fileFormat', $('#fileFormat').val());
        formData.append('identifier', $('#identifier').val());
        formData.append('type', $('#type').val());
        formData.append('contributor', $('#contributor').val());
        formData.append('creator', $('#creator').val());
        formData.append('publisher', $('#publisher').val());
        formData.append('rights', $('#rights').val());
        formData.append('year', $('#year').val());
        formData.append('url', $('#url').val());
        formData.append('species', $('#species').val());
        $.ajax({
            method: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            url: 'secured/document/post',
            success: function (json) {
                $('#bibliographic_resource').val('');
                $('#datePublication').val('');
                $('#id_thematique').val('');
                $('#coverage').val('');
                $('#description').val('');
                $('#language').val('');
                $('#relation').val('');
                $('#source').val('');
                $('#subject').val('');
                $('#title').val('');
                $('#format').val('');
                $('#fileFormat').val('');
                $('#identifier').val('');
                $('#contributor').val('');
                $('#creator').val('');
                $('#publisher').val('');
                $('#rights').val('');
                $('#year').val('');
                $('#url').val('');
                $('#species').val('');
                $('#errorMdp').html("<p style='color: red;'> " + "</p>");
                closeModal('modal-ajout-document');
            },
            error: function (json) {
                $('#errorMdp').html("<p style='color: red;'> " + "Le téléchargement du document est un échec. Veuiller réessayer.</p>");
            }
        });
    }
</script>
<script src="${resourcesPath}/js/autocompleteForLemurs.min.js"></script>
<!--Appel de la fonction-->
<script>
    autocomplete(document.getElementById("year"), 'year');
    autocomplete(document.getElementById("datePublication"), 'date');
    autocomplete(document.getElementById("bibliographic_resource"), 'bibliographicResource');
    autocomplete(document.getElementById("id_thematique"), 'idThematique');
    autocomplete(document.getElementById("url"), 'url');
    autocomplete(document.getElementById("coverage"), 'coverage');
    autocomplete(document.getElementById("description"), 'description');
    autocomplete(document.getElementById("language"), 'language');
    autocomplete(document.getElementById("relation"), 'relation');
    autocomplete(document.getElementById("source"), 'source');
    autocomplete(document.getElementById("subject"), 'subject');
    autocomplete(document.getElementById("title"), 'title');
    autocomplete(document.getElementById("format"), 'format');
    autocomplete(document.getElementById("fileFormat"), 'fileFormat');
    autocomplete(document.getElementById("identifier"), 'identifier');
    autocomplete(document.getElementById("type"), 'type');
    autocomplete(document.getElementById("contributor"), 'contributor');
    autocomplete(document.getElementById("creator"), 'creator');
    autocomplete(document.getElementById("publisher"), 'publisher');
    autocomplete(document.getElementById("rights"), 'rights');
</script>
<link rel="stylesheet" href="${resourcesPath}/css/bootstrap-multiselect.css" type="text/css">
<script type="text/javascript" src="${resourcesPath}/js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
    function populate() {
//        $.getJSON('http://localhost:8084/lemurs/getallTaxo', {}, function (data, textStatus) {
        $.getJSON('https://www.lemursportal.org/species/getallTaxo', {}, function (data, textStatus) {
            var el = $('select#species');
            el.html('');  // empty the select
            $.each(data, function (idx, jsonData) {
                el.append($('<option style="background-color: #f9efc9;"></option>').val(jsonData.id).html(jsonData.scientificname));
            });
            $('#species').multiselect({                
                maxHeight: 316,
                enableFiltering: true,
                enableCaseInsensitiveFiltering: true,
                buttonWidth: '100%'
            });
        });
    }

    $(document).ready(function () {
        $('#id_thematique').multiselect({
            maxHeight: 158,
            buttonWidth: '100%'
        });
        populate();
    });
</script>