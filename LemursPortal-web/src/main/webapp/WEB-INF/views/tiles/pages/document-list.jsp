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
                    <li style="float: right;"><button style="color: white;" class="btn" aria-controls="tab-item-5" role="tab" aria-expanded="false" onclick="openModal('modal-ajout-document')">Ajouter</button></li>
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
        <div id="modal-ajout-document" class="modal">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-ajout-document')">&times;</button>
                        <h4 class="modal-title">Ajouter un document</h4>
                    </div>
                    <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                        Fichier<sup>*</sup>
                        <input type="file" class="form-control" id="document">
                        Bibliographic resource<sup>*</sup>
                        <input type="text" class="form-control" id="bibliographic_resource">
                        Date<sup>*</sup>
                        <input type="date" class="form-control" id="datePublication">
                        Topics<sup>*</sup>
                        <select id="id_thematique" class="form-control">
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
                        <!--<input type="text" class="form-control" id="id_thematique">-->
                        url<sup>*</sup>
                        <input type="text" class="form-control" id="url">
                        coverage<sup>*</sup>
                        <input type="text" class="form-control" id="coverage">
                        description<sup>*</sup>
                        <input type="text" class="form-control" id="description">
                        language<sup>*</sup>
                        <input type="text" class="form-control" id="language">
                        relation<sup>*</sup>
                        <input type="text" class="form-control" id="relation">
                        source<sup>*</sup>
                        <input type="text" class="form-control" id="source">
                        subject<sup>*</sup>
                        <input type="text" class="form-control" id="subject">
                        title<sup>*</sup>
                        <input type="text" class="form-control" id="title">
                        format<sup>*</sup>
                        <input type="text" class="form-control" id="format">
                        fileFormat<sup>*</sup>
                        <input type="text" class="form-control" id="fileFormat">
                        identifier<sup>*</sup>
                        <input type="text" class="form-control" id="identifier">
                        type<sup>*</sup>
                        <input type="text" class="form-control" id="type">
                        contributor<sup>*</sup>
                        <input type="text" class="form-control" id="contributor">
                        creator<sup>*</sup>
                        <input type="text" class="form-control" id="creator">
                        publisher<sup>*</sup>
                        <input type="text" class="form-control" id="publisher">
                        rights<sup>*</sup>
                        <input type="text" class="form-control" id="rights">
                        year<sup>*</sup>
                        <input type="text" class="form-control" id="year">
                        <div id="errorMdp"></div>
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-ajout-document')">Annuler</button>
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="sendAddDocument()">Enregistrer</button>
                    </div>
                </div>
            </div>            
            <script>
                function sendAddDocument() {
                    var formData = new FormData();
                    formData.append('file', $('#document').get(0).files[0]);
                    formData.append('bibliographicResource', parseInt($('#bibliographic_resource').val()));
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
                            $('#errorMdp').html("<p style='color: red;'> " + "</p>");
                            closeModal('modal-ajout-document');
                        },
                        error: function (json) {
                            $('#errorMdp').html("<p style='color: red;'> " + "le telechargement du document est un échec. Veuiller réessayer." + json + "</p>");
                        }
                    });
                }
            </script>
        </div>
    </div>        
</div>

