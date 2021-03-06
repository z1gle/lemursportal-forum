<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<c:url value="/" var="basePath"/>
<style>	
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
</style>
<style>
    /**Modal photo style**/

    /* The Modal (background) */
    .modalP {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        padding-top: 100px; /* Location of the box */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
    }

    /* Modal Content (Image) */
    .modalP-content {
        margin: auto;
        display: block;
        max-height: 70vh;
        max-width: 100%;
    }    

    /* Add Animation - Zoom in the Modal */
    .modalP-content, #caption { 
        animation-name: zoom;
        animation-duration: 0.6s;
    }

    @keyframes zoom {
        from {transform:scale(0)} 
        to {transform:scale(1)}
    }

    /* The Close Button */
    .closeP {
        /*position: absolute;*/
        margin-top: 30px;
        margin-right: 45px;
        color: #f1f1f1;
        font-size: 40px;
        font-weight: bold;
        transition: 0.3s;
        float: right;
    }

    .closeP:hover,
    .closeP:focus {
        color: #bbb;
        text-decoration: none;
        cursor: pointer;
    }

    .cliquable:hover,
    .cliquable:focus {
        cursor: pointer;
    }
    .modalP-content:hover {
        opacity: 1;
    }
    /* 100% Image Width on Smaller Screens */
    @media only screen and (max-width: 700px){
        .modalP-content {
            width: 100%;
        }
    }
    @media (max-width: 990px) {
        #caption {
            margin-left: 25px;
        }
    }
</style>
<div class="forum-container page-document">
    <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>

    <div class="row">
        <div class="page-title">
            <h2 class="doc"><spring:message code="document.title"/> <small><c:choose><c:when test="${isLoggedInUser && nbrDocument != null && nbrDocument!= 0}">( <a style="color: #9e9e9e;" href="?nouveau=${nbrDocument}">${nbrDocument} new documents</a>)</c:when></c:choose></small></h2>
                </div>
                <div class="col-xs-12 col-md-12">
                    <div class="box">
                        <!-- D Tab -->
                        <ul class="nav nav-tabs userProfileTabs" role="tablist">
                            <li role="presentation" class="active"><a href="#tab-item-1" aria-controls="tab-item-4" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.documents"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-4" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true"><spring:message code="document.header.pictures"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.videos"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.audios"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-5" aria-controls="tab-item-5" role="tab" data-toggle="tab" aria-expanded="false">Youtube</a></li>                        
                        <c:choose>
                            <c:when test="${isLoggedInUser}">
                            <li style="float: right;"><button style="color: white;" class="btn" aria-controls="tab-item-5" role="tab" aria-expanded="false" onclick="operModifAddModal()"><spring:message code="document.add"/></button></li>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                </ul>
                <!-- F Tab -->
                <style>
                    .user-detail {
                        font-size: 8px;
                        font-weight: 500;
                        color: darkolivegreen;
                    }
                </style>
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
                                                    <th style="font-size: 15px; border-color: white; text-align: right;">Page: ${pagination.pageDocument.pageDocumentCurrent + 1}/${pagination.pageDocument.pageDocumentFin} | Total: </th>                                                                                                        
                                                    <th style="font-size: 15px; border-color: white; text-align: right;">${pagination.pageDocument.pageDocumentTotalElement}</th>                                                                                                        
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
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; border-top-color: #dddddd;"></th>                                                                                                        
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise></c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                        <tr style="border:1px solid #ccc;">                                                                                        
                                            <td>
                                                <a href="#" onclick="showDetailNew(${publication.id})"><c:out	value="${publication.title}" /></a><br>
                                                <c:choose>
                                                    <c:when test="${isLoggedInUser}">
                                                        <a onclick="openDetail(${publication.idUtilisateur}, '${publication.userName}', '${publication.userFirstname}', '${publication.userEmail}', '${publication.userPhoto}');" href="#"><span class="user-detail"><c:out value="${publication.userFirstname}" /> <c:out value="${publication.userName}" /></span></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="user-detail"><c:out value="${publication.userName}" /> <c:out value="${publication.userFirstname}" /></span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><c:out	value="${publication.coverage}" /></td>
                                            <td><c:out	value="${publication.creator}" /></td>
                                            <td class="text-center">
                                                <%--<c:url var="publicationPageUrl" value="/files/${publication.idDocument}"/>--%>
                                                <c:url var="publicationPageUrl" value="${publication.url}"/>
                                                <!--<a class='btn btn-info btn-xs' href="${publicationPageUrl}" download="${publication.title}.pdf"><span class="glyphicont"></span>Télécharger</a></td>-->
                                                <a target="_blank" rel="noopener noreferrer" title="view document" href="${publicationPageUrl}"><span class="glyphicont"></span><img src="${resourcesPath}/images/icon-thema.png" alt=""></a>
                                            </td>
                                            <c:choose>
                                                <c:when test="${isLoggedInUser && currentUser.id == publication.idUtilisateur}">
                                                    <td class="text-center">
                                                        <a title="edit or remove" href="#" onclick="showDetailForModification(${publication.id})"><span class="glyphicont"></span><i style="margin-top: 3px;" class="fa fa-edit fa-2x"></i></a>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="text-center">
                                                        <sec:authorize access="hasRole('ADMIN')">                                                        
                                                            <a title="edit or remove" href="#" onclick="showDetailForModification(${publication.id})"><span class="glyphicont"></span><i style="margin-top: 3px;" class="fa fa-edit fa-2x"></i></a>                                                        
                                                                </sec:authorize>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>                                            
                                        </tr>
                                    </c:forEach>
                                </table>

                                <!-- D Pagination -->
                                <c:choose>
                                    <c:when test="${topic != 0}">
                                        <c:url var="pageBaseUrl" value="/documents?topic=${topic}"/>
                                        <page:paginationDocument currentPage="${pagination.pageDocument.pageDocumentCurrent + 1}" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${!search.equals(Integer.toString(0))}">
                                                <c:url var="pageBaseUrl" value="/documents?search=${search}"/>
                                                <page:paginationDocument currentPage="${pagination.pageDocument.pageDocumentCurrent + 1}" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url var="pageBaseUrl" value="/documents"/>
                                                <page:paginationDocument currentPage="${pagination.pageDocument.pageDocumentCurrent + 1}" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                            </c:otherwise>
                                        </c:choose>                                        
                                    </c:otherwise>
                                </c:choose>
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
                                    <style>
                                        .delete-photo-document {
                                            position: absolute;
                                            right: 0px;
                                            background-color: #3330!important;
                                            color: grey!important;
                                        }

                                        a.delete-photo-document:hover {
                                            background-color: #bbb!important;
                                            color: white!important;
                                        }
                                    </style>
                                    <ul class="project-wrapper animated fadeInUp" style="text-align: left !important">
                                        <c:forEach items="${docIMAGE}" var="pic">
                                            <li class="species-item">
                                                <c:choose>
                                                    <c:when test="${isLoggedInUser && currentUser.id == publication.idUtilisateur}">
                                                        <a href="#" onclick="openDeleteModal(${pic.id}, '${pic.title.replace("'", "\\'")}')" class="btn delete-photo-document"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <sec:authorize access="hasRole('ADMIN')">
                                                            <a href="#" onclick="openDeleteModal(${pic.id}, '${pic.title.replace("'", "\\'")}')" class="btn delete-photo-document"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                                            </sec:authorize>
                                                        </c:otherwise>
                                                    </c:choose>
                                                <a href="#" onclick="showPhoto('${pic.id}', '${pic.photo.breakpoints[2].link}')">
                                                    <img src="${resourcesPath}/images/l-blank.png" style="background-image: url('${pic.photo.breakpoints[pic.photo.breakpoints.size()-4].link}'); " class="img-responsive" 
                                                         onclick="showPhoto('${pic.id}', '${pic.photo.breakpoints[2].link}')" class="hover-shadow cursor" alt="--">
                                                </a>
                                            <figcaption class="mask">
                                                <p><i>${pic.title}</i></p>
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
                                                <%--                                                 <img src="${resourcesPath}/upload/${pic.url}" style="width:100%"> --%>
                                                <img src="${pic.url}" style="width:100%">
                                                <c:set var="isa1" value="${isa1+1}"/>
                                            </div>
                                        </c:forEach>
                                        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                                        <a class="next" onclick="plusSlides(1)">&#10095;</a>
                                    </div>
                                </div>

                                <!-- Modal detail photo -->
                                <div id="modalDetailPhoto" class="modalP">
                                    <div class="row">
                                        <!-- The Close Button -->
                                        <div class="row">
                                            <span class="closeP" onclick="closeModal('modalDetailPhoto');">&times;</span>
                                            <c:choose>
                                                <c:when test="${isLoggedInUser && currentUser.id == publication.idUtilisateur}">
                                                    <!--<button class="btn btn-danger" onclick="openDeleteModal(${video.id}, '${video.title}')">Delete</button>-->
                                                </c:when>
                                                <c:otherwise>
                                                    <sec:authorize access="hasRole('ADMIN')">                                                        
                                                        <!--<button class="btn btn-danger">Delete</button>-->
                                                    </sec:authorize>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="row">
                                            <!-- Modal Content (The Image) -->
                                            <div class="col-md-8">
                                                <div class="modalP-content" id="img01"></div>
                                            </div>
                                            <div class="col-md-4" id="caption" style="background-color:  black;border-radius:  5px;border-style: solid;border-color: #9d5b00;">
                                                <table id="photoTable" class="" style="width: 100%; font-size: 18px; color: white; margin-top: 10px; margin-bottom: 10px;">
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.title"/> :</td>
                                                        <td id="photoSpecies"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.date"/> :</td>
                                                        <td id="photoDate"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.location"/> :</td>
                                                        <td id="photoLocalisation"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.source"/> :</td>
                                                        <td id="photoSource"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.author"/> :</td>
                                                        <td id="photoAuteur"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.right"/> :</td>
                                                        <td id="photoRight"></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- Modal Caption (Image Text) -->
                                        <!--<div id="caption"></div>-->
                                    </div>                                    
                                </div>                                
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
                                            <!--                                            <td>
                                                                                            <img src="${resourcesPath}/images/icon-video-document.png" alt="">
                                                                                            <video style="max-width: 320px;" controls="controls">
                                                                                                <source src="${video.url.substring(1)}" type="video/mp4">
                                                                                                <source src="${video.url.substring(1)}" type="video/ogg">
                                                                                                Your browser does not support the video tag.
                                                                                            </video>
                                                                                        </td>-->
                                            <td><c:out	value="${video.title}" /></td>
                                            <td class="text-center">
                                                <c:url var="videoPageUrl" value="${video.url}"/>
                                                <a class='btn btn-info btn-xs' href="${videoPageUrl}"><span class="glyphicont"></span><spring:message code="document.video.show"/></a></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${isLoggedInUser && currentUser.id == publication.idUtilisateur}">
                                                        <span class="cliquable" style="float: left; margin-left: 35px;"><i onclick="openDeleteModal(${video.id}, '${video.title}')" class="fa fa-trash-o"></i></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <sec:authorize access="hasRole('ADMIN')">                                                            
                                                            <span class="cliquable" style="float: left; margin-left: 35px;"><i onclick="openDeleteModal(${video.id}, '${video.title}')" class="fa fa-trash-o"></i></span>
                                                            </sec:authorize>
                                                        </c:otherwise>
                                                    </c:choose>
                                            </td>
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
                    <style>
                        .sound-title {
                            text-align: center;
                            padding-top: 25px!important;
                        }
                    </style>
                    <div role="tabpanel" class="tab-pane fade" id="tab-item-3">
                        <div class="txt-content">
                            <div class="col-md-offset custyle">
                                <table class="table table-striped custab">
                                    <c:forEach items="${docAUDIO}" var="audio">

                                        <tr>
                                            <!--<td><img src="${resourcesPath}/images/icon-audio.png" alt=""></td>-->
                                            <td class="sound-title"><c:out	value="${audio.title}" /></td>
                                            <td>
                                                <audio controls>
                                                    <source src="${audio.url.substring(1)}" type="audio/ogg">
                                                    <source src="${audio.url.substring(1)}" type="audio/mpeg">
                                                    <c:url var="audioPageUrl" value="${audio.url}"/>
                                                    <a class='btn btn-info btn-xs' href="${audioPageUrl}"><span class="glyphicont"></span></a>
                                                </audio>
                                            </td>                                            
                                            <!--                                            <td class="text-center">
                                            <c:url var="audioPageUrl" value="${audio.url}"/>
                                            <a class='btn btn-info btn-xs' href="${audioPageUrl}"><span class="glyphicont"></span>Télécharger</a></td>-->
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
                        <button type="button" class="close" onclick="closeModifAddModal()">&times;</button>
                        <h4 class="modal-title"><spring:message code="document.add_document.title"/></h4>
                    </div>
                    <form action="javascript:sendAddDocument();" autocomplete="off">
                        <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                            <div style=" font-size: 10px; color: #999;"><span style="color: red;">NB</span><spring:message code="document.add_document.nb"/></div>
                            <div id="errorMdp"></div>
                            <input type="hidden" id="id">
                            <spring:message code="metadata.topics"/><sup>*</sup><br>
                            <select title="<spring:message code="metadata.popup.bubble.topics"/>" required multiple="" id="id_thematique" class="form-control">
                                <c:forEach items="${topThematiques}" var="topThematique">
                                    <option value="${topThematique.thematique.id}"><spring:message code="document.thematique.id.${topThematique.thematique.id}"/></option>
                                </c:forEach>                                
                            </select>                            
                            <spring:message code="metadata.type"/><sup>*</sup>
                            <select required title="<spring:message code="metadata.popup.bubble.type"/>" class="form-control" id="type" style="width: 100%!important;">
                                <option disabled selected value> -- select an option -- </option>
                                <option style="background-color: #f9efc9;" value="4">Document</option>
                                <option style="background-color: #f9efc9;" value="1">Photo</option>
                                <option style="background-color: #f9efc9;" value="2">Video</option>
                                <option style="background-color: #f9efc9;" value="3">Audio</option>
                            </select>                            
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.year"/><sup>*</sup>                        
                                <input title="<spring:message code="metadata.popup.bubble.year"/>" required type="text" class="form-control" id="year">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.title"/><sup>*</sup>
                                <input title="<spring:message code="metadata.popup.bubble.title"/>" required type="text" class="form-control" id="title">
                            </div>
                            <spring:message code="global.label.file"/>
                            <input type="file" class="form-control" id="document">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.url"/>
                                <input title="<spring:message code="metadata.popup.bubble.url"/>" placeholder="ex: https://www.lemursportal.org/forum/resources/upload/1530798561545Ziheng&Yoder2003.pdf" type="text" class="form-control" id="url">
                            </div>
                            <div class="" style="width: 100%;">
                                <spring:message code="metadata.species"/>
                                <select title="<spring:message code="metadata.popup.bubble.species"/>" multiple class="form-control" id="species" style="width: 100%!important;"></select>
                            </div>
                            <spring:message code="metadata.date"/>
                            <input title="<spring:message code="metadata.popup.bubble.date"/>" type="date" class="form-control" id="datePublication">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.bibliographicResource"/>
                                <select title="<spring:message code="metadata.popup.bubble.bibliographicResource"/>" class="form-control" id="bibliographic_resource" style="width: 100%!important;">
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
                            </div>                                                
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.coverage"/>
                                <input title="<spring:message code="metadata.popup.bubble.coverage"/>" placeholder="ex: SAINTE LUCE | FORT-DAUPHIN | MADAGASCAR" type="text" class="form-control" id="coverage">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.description"/>
                                <textarea title="<spring:message code="metadata.popup.bubble.description"/>" class="form-control" id="description">
                                    
                                </textarea>
                            </div>
                            <spring:message code="metadata.language"/>
                            <select title="<spring:message code="metadata.popup.bubble.language"/>" class="form-control" id="language" style="width: 100%!important;">
                                <option style="background-color: #f9efc9;" selected="" value="MLG">MG</option>
                                <option style="background-color: #f9efc9;" value="EN">EN</option>
                                <option style="background-color: #f9efc9;" value="FR">FR</option>
                                <option style="background-color: #f9efc9;" value="FR">OTHERS</option>
                            </select>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.relation"/>
                                <input title="<spring:message code="metadata.popup.bubble.relation"/>" placeholder="ex: Relation" type="text" class="form-control" id="relation">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.source"/>
                                <input title="<spring:message code="metadata.popup.bubble.source"/>" placeholder="ex: Syst. Biol. 52(5):705–716, 2003" type="text" class="form-control" id="source">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.subject"/>
                                <input title="<spring:message code="metadata.popup.bubble.subject"/>" placeholder="ex: Mouse lemur Species | Bayesian Methods" type="text" class="form-control" id="subject">
                            </div>                        
                            <!--<div class="autocomplete" style="width: 100%;">-->
                            <%--<spring:message code="metadata.format"/>--%>
                            <input title="<spring:message code="metadata.popup.bubble.format"/>" placeholder="ex: text" type="hidden" class="form-control" id="format">
                            <!--</div>-->
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.fileFormat"/>
                                <input title="<spring:message code="metadata.popup.bubble.fileFormat"/>" placeholder="ex: PDF" type="text" class="form-control" id="fileFormat">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.identifier"/>
                                <input title="<spring:message code="metadata.popup.bubble.identifier"/>" placeholder="ex :ISSN: 1063-5157 print / 1076-836X online | DOI: 10.1080/10635150390235557" type="text" class="form-control" id="identifier">
                            </div>                        
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.contributor"/>
                                <input title="<spring:message code="metadata.popup.bubble.contributor"/>" placeholder="ex: Department of Biology, University College London | Department of Ecology and Evolutionary Biology, Yale University" type="text" class="form-control" id="contributor">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.creator"/>
                                <input title="<spring:message code="metadata.popup.bubble.creator"/>" placeholder="ex: ZIHENG YANG | ANNE D. YODER" type="text" class="form-control" id="creator">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.publisher"/>
                                <input title="<spring:message code="metadata.popup.bubble.publisher"/>" placeholder="ex: Society of Systematic Biologists" type="text" class="form-control" id="publisher">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.rights"/>
                                <input title="<spring:message code="metadata.popup.bubble.rights"/>" placeholder="ex: Syst. Biol. 52(5):705–716, 2003 | Copyright(c) Society of Systematic Biologists" type="text" class="form-control" id="rights">                        
                            </div>                            
                        </div>
                        <div class="modal-footer">
                            <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModifAddModal()"><spring:message code="global.btn.cancel"/></button>
                            <button style="float: right;" type="submit" class="btn btn-default" data-dismiss="modal"><spring:message code="global.btn.save"/></button>
                            <div id="delete"></div>
                        </div>
                    </form>
                </div>
            </div>                        
        </div>        
        <div id="modal-detail-document" class="modal">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeDetailModal()">&times;</button>
                        <h4 id="titleDetail" class="modal-title"></h4>
                    </div>
                    <div style="overflow-y: auto;max-height:  500px;">
                        <table class="table">
                            <tr>
                                <td><spring:message code="metadata.topics"/></td>
                                <td>
                                    <select disabled title="<spring:message code="metadata.popup.bubble.topics"/>" required multiple="" id="idThematiqueDetail" class="form-control">
                                        <c:forEach items="${topThematiques}" var="topThematique">
                                            <option value="${topThematique.thematique.id}"><spring:message code="document.thematique.id.${topThematique.thematique.id}"/></option>
                                        </c:forEach>                                
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.type"/></td>
                                <td>
                                    <select disabled required title="<spring:message code="metadata.popup.bubble.type"/>" class="form-control" id="typeDetail" style="width: 100%!important;">                                    
                                        <option style="background-color: #f9efc9;" value="4">Document</option>
                                        <option style="background-color: #f9efc9;" value="1">Photo</option>
                                        <option style="background-color: #f9efc9;" value="2">Video</option>
                                        <option style="background-color: #f9efc9;" value="3">Audio</option>
                                    </select>                            
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.year"/></td>
                                <td>
                                    <p id="yearDetail"></p>
                                </td>
                            </tr>                        
                            <tr>
                                <td><spring:message code="metadata.url"/></td>
                                <td>
                                    <p id="urlDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.species"/></td>
                                <td>
                                    <select disabled multiple class="form-control" id="speciesDetail" style="width: 100%!important;"></select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.date"/></td>
                                <td>
                                    <p id="datePublicationDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.bibliographicResource"/></td>
                                <td>
                                    <p id="bibliographicResourceDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.coverage"/></td>
                                <td>
                                    <p id="coverageDetail"></p>
                                </td>
                            </tr>
                            <tr><!---->
                                <td><spring:message code="metadata.description"/></td>
                                <td style="text-align: justify;text-justify: inter-word;">
                                    <p id="descriptionDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.language"/></td>
                                <td>
                                    <select disabled title="<spring:message code="metadata.popup.bubble.language"/>" class="form-control" id="languageDetail" style="width: 100%!important;">
                                        <option style="background-color: #f9efc9;" selected="" value="MLG">MG</option>
                                        <option style="background-color: #f9efc9;" value="EN">EN</option>
                                        <option style="background-color: #f9efc9;" value="FR">FR</option>
                                        <option style="background-color: #f9efc9;" value="FR">OTHERS</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.relation"/></td>
                                <td>
                                    <p id="relationDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.source"/></td>
                                <td>
                                    <p id="sourceDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.subject"/></td>
                                <td>
                                    <p id="subjectDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.fileFormat"/></td>
                                <td>
                                    <p id="fileFormatDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.identifier"/></td>
                                <td>
                                    <p id="identifierDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.contributor"/></td>
                                <td>
                                    <p id="contributorDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.creator"/></td>
                                <td>
                                    <p id="creatorDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.publisher"/></td>
                                <td>
                                    <p id="publisherDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.rights"/></td>
                                <td>
                                    <p id="rightsDetail"></p>
                                </td>
                            </tr>
                        </table>                    
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDetailModal()">OK</button>                                                
                    </div>                    
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
        <!--modal confirmation delete-->
        <div id="modal-delete" class="modal edit-profil-form">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-delete')">&times;</button>
                        <h4 class="modal-title">Suppression</h4>
                    </div>
                    <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                        <span>Cette action est irreversible.</span>
                        <span id="supression_sentence"></span>
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-delete')">Annuler</button>                        
                        <div id="performe_delete"></div>
                    </div>
                </div>
            </div>                        
        </div>
        <!--modal detail user-->
        <div id="modal-detail-user" class="modal edit-profil-form">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-detail-user')">&times;</button>
                        <h4 class="modal-title">Detail</h4>
                    </div>
                    <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                        <div class="row">
                            <div id="detail-user-img" class="col-md-2">                                
                            </div>
                            <div id="detail-user-body" class="col-md-10">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-detail-user')">OK</button>                                                
                    </div>
                </div>
            </div>                        
        </div>
    </div>        
</div>
<script>
    function showPhoto(id, url) {
        $('.removable-row').remove();
        $('#photoDate').text('');
        $('#photoLocalisation').text('');
        $('#photoSource').text('');
        $('#photoAuteur').text('');
        $('#photoRight').text('');
        $.get("metadata/" + id, {}, function (data) {
            console.log("the data :");
            console.log(data);
            $('#photoDate').text(data[0].value.date);
            $('#photoLocalisation').text(data[0].value.coverage);
            $('#photoSource').text(data[0].value.source);
            $('#photoAuteur').text(data[0].value.creator);
            $('#photoRight').text(data[0].value.rights);
            $('#photoSpecies').text(data[0].value.title);
//            $('#deletePhoto').on('click', openDeleteModal(id, data[0].title));
            document.getElementById('deletePhoto').html = '<span id="deletePhoto" style="float: left; margin-left: 35px;" class="closeP"><i onclick="openDeleteModal(' + id + ', \'' + data[0].value.title + '\')" class="fa fa-trash-o"></i></span>'
        }).done(function () {
            $.getJSON('metadata/' + id + '/taxonomis', {}, function (data, textStatus) {
                if (data.length > 0) {
                    var species = '';
                    for (var v = 0; v < data.length; v++) {
                        species += data[v].value.scientificname + '<br>';
                    }
                    drawRow('Species :', species, 'photoTable');
                }
            });
        });
        document.getElementById("img01").innerHTML = '<img class="modalP-content" src="' + url + '">';
        openModal('modalDetailPhoto');
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
        var id = -1;
        $.each(data, function (key, value) {
            if (key !== 'id' && key !== 'idDocument' && key !== 'idUtilisateur' && key !== 'document' && key !== 'listeAssociationMetadataTopic') {
                if (key === 'url') {
                    if (value.indexOf("/resources/upload/") === 0) {
                        value = value.substring(1);
                    }
                    value = '<a href="' + value + '">' + value + '</a>';
                }
                drawRow(key + ":", value, table);
            } else if (key === 'id') {
                id = value;
            }
        });
        $.getJSON('metadata/' + id + '/taxonomis', {}, function (data, textStatus) {
            if (data.length > 0) {
                var species = '<ul>';
                for (var v = 0; v < data.length; v++) {
                    species += '<li>' + data[v].scientificname + '</li>';
                }
                species += '</ul>';
                drawRow('species', species, table);
            }
        }).done(function () {
            $.getJSON('metadata/' + id + '/topics', {}, function (data, textStatus) {
                if (data.length > 0) {
                    var species = '<ul>';
                    for (var v = 0; v < data.length; v++) {
                        species += '<li>' + data[v].libelle + '</li>';
                    }
                    species += '</ul>';
                    drawRow('Topics', species, table);
                }
            }).done(function () {
                $.getJSON('metadata/' + id + '/topics', {}, function (data, textStatus) {
                    if (data.length > 0) {
                        var species = '<ul>';
                        for (var v = 0; v < data.length; v++) {
                            species += '<li>' + data[v].libelle + '</li>';
                        }
                        species += '</ul>';
                        drawRow('Topics', species, table);
                    }
                });
            });
        });
    }

    function drawRow(key, value, table) {
        var row = $("<tr class='removable-row' />");
        $("#" + table).append(row);
        row.append($("<td>" + key + "</td>"));
        row.append($("<th>" + value + "</th>"));
    }

    function operModifAddModal() {
        populate();
        resetModalModifAjout();
        openModal('modal-ajout-document');
    }
    function openDeleteModal(id, title) {
        openModal('modal-delete');
        $('#supression_sentence').html("voulez-vous vraiment supprimer " + title + " ?");
        $('#performe_delete').html('<button style="float: right;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteDocument(' + id + ')">Supprimer</button>');
    }

    function closeModifAddModal() {
        $("#modal-ajout-document").load(location.href + " #modal-ajout-document>*", "");
        closeModal('modal-ajout-document');
    }
    function closeDetailModal() {
        $("#modal-detail-document").load(location.href + " #modal-detail-document>*", "");
        closeModal('modal-detail-document');
    }

    function showDetailForModification(id) {
        var taxx = [];
//        $("#modal-ajout-document").load(location.href + " #modal-ajout-document>*", "");
        $.get("metadata/" + id + "/all", function (data) {
            $('#year').val(data.metadata.year);
            $('#title').val(data.metadata.title);
            $('#type').val(data.metadata.type);
            $('#url').val(data.metadata.url);
            $('#datePublication').val(data.metadata.date);
//            $('#date').value = '2012-01-01';
            $('#coverage').val(data.metadata.coverage);
            $('#description').val(data.metadata.description);
            $('#language').val(data.metadata.language);
            $('#relation').val(data.metadata.relation);
            $('#source').val(data.metadata.source);
            $('#subject').val(data.metadata.subject);
            $('#format').val(data.metadata.format);
            $('#fileFormat').val(data.metadata.fileFormat);
            $('#identifier').val(data.metadata.identifier);
            $('#contributor').val(data.metadata.contributor);
            $('#creator').val(data.metadata.creator);
            $('#publisher').val(data.metadata.publisher);
            $('#rights').val(data.metadata.rights);
            $('#id').val(id);
            if (data.topics.length > 0) {
                for (var v = 0; v < data.topics.length; v++) {
                    $('#id_thematique option[value=' + data.topics[v].id + ']').attr('selected', true);
                }
            }
            taxx = data.taxonomi;
            $("#delete").html('<button style="float: left;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="openDeleteModal(' + id + ', \'' + data.metadata.title + '\')">Supprimer</button>');
        }).done(function () {
            openModal('modal-ajout-document');
            $('#id_thematique').multiselect({
                maxHeight: 158,
                buttonWidth: '100%'
            });
            //get all taxo begin
            $.getJSON('https://www.lemursportal.org/species/getallTaxo', {}, function (data, textStatus) {
                var el = $('select#species');
                el.html('');  // empty the select
                $.each(data, function (idx, jsonData) {
                    el.append($('<option style="background-color: #f9efc9;"></option>').val(jsonData.id).html(jsonData.scientificname));
                });
            }).done(function () {
                if (taxx.length > 0) {
                    for (var v = 0; v < taxx.length; v++) {
                        $('#species option[value=' + taxx[v].id + ']').attr('selected', 'true');
                    }
                }
                $('#species').multiselect({
                    maxHeight: 316,
                    enableFiltering: true,
                    enableCaseInsensitiveFiltering: true,
                    buttonWidth: '100%'
                });
            });
            //get all taxo end                                
        });
    }
    function showDetailNew(id) {
        var taxx = [];
        $.get("metadata/" + id + "/all", function (data) {
            $('#yearDetail').text(data.metadata.year);
            $('#titleDetail').text(data.metadata.title);
            $('#typeDetail').val(data.metadata.type);
            if (data.metadata.url.charAt(0) === '/') {
                data.metadata.url = 'https://www.lemursportal.org/forum' + data.metadata.url;
            } else if (!data.metadata.url.includes('http')) {
                data.metadata.url = 'https://' + data.metadata.url;
            }
            $('#urlDetail').html('<a href="' + data.metadata.url + '">' + data.metadata.url + '</a>');
            $('#datePublicationDetail').text(data.metadata.date);
            $('#coverageDetail').text(data.metadata.coverage);
            $('#descriptionDetail').text(data.metadata.description);
            $('#languageDetail').val(data.metadata.language);
            $('#relationDetail').text(data.metadata.relation);
            $('#sourceDetail').text(data.metadata.source);
            $('#subjectDetail').text(data.metadata.subject);
            $('#fileFormatDetail').text(data.metadata.fileFormat);
            $('#identifierDetail').text(data.metadata.identifier);
            $('#contributorDetail').text(data.metadata.contributor);
            $('#creatorDetail').text(data.metadata.creator);
            $('#publisherDetail').text(data.metadata.publisher);
            $('#rightsDetail').text(data.metadata.rights);
            $('#bibliographicResourceDetail').text(data.metadata.bibliographicResource);
            if (data.topics.length > 0) {
                for (var v = 0; v < data.topics.length; v++) {
                    $('#idThematiqueDetail option[value=' + data.topics[v].id + ']').attr('selected', true);
                }
            }
            taxx = data.taxonomi;
        }).done(function () {
            openModal('modal-detail-document');
            $('#idThematiqueDetail').multiselect({
                maxHeight: 158,
                buttonWidth: '100%'
            });
            //get all taxo begin
            $.getJSON('https://www.lemursportal.org/species/getallTaxo', {}, function (data, textStatus) {
                var el = $('select#speciesDetail');
                el.html('');  // empty the select
                $.each(data, function (idx, jsonData) {
                    el.append($('<option style="background-color: #f9efc9;"></option>').val(jsonData.id).html(jsonData.scientificname));
                });
            }).done(function () {
                if (taxx.length > 0) {
                    for (var v = 0; v < taxx.length; v++) {
                        $('#speciesDetail option[value=' + taxx[v].id + ']').attr('selected', 'true');
                    }
                }
                $('#speciesDetail').multiselect({
                    maxHeight: 316,
                    enableFiltering: true,
                    enableCaseInsensitiveFiltering: true,
                    buttonWidth: '100%'
                });
            });
            //get all taxo end                                
        });
    }
    function resetModalModifAjout() {
        $('#year').val('');
        $('#title').val('');
        $('#url').val('');
        $('#date').val('');
        $('#coverage').val('');
        $('#description').val('');
        $('#language').val('');
        $('#relation').val('');
        $('#source').val('');
        $('#subject').val('');
        $('#format').val('');
        $('#fileFormat').val('');
        $('#identifier').val('');
        $('#contributor').val('');
        $('#creator').val('');
        $('#publisher').val('');
        $('#rights').val('');
        $('#id').val('');
    }
    function deleteDocument(id) {
        $.post('metadata/delete/' + id, {}, function (data, textStatus) {
            if (data === true) {
                location.reload();
            } else
                alert(data);
        }).error(function (data, textStatus) {
            closeModal('modal-delete');
            closeModifAddModal();
            alert("La supression a rencontré une erreur. Veuiller réessayer ultérieurement");
        });
    }
    function openDetail(id, name, firstname, email, photo) {
        var body = '';
        body += '<div class="remove-body">';
        body += '<h4>' + firstname + '<small>' + name + '</small>' + '</h4>';
    <sec:authorize access="hasRole('ADMIN')">
        body += '<span>' + email + '</span>';
    </sec:authorize>
        body += '</div>';
        $(".remove-body").remove();
        $('#detail-user-body').append(body);
        $('#detail-user-img').append('<img style="max-width: 100%; max-height: 60px;" src="${resourcesPath}' + photo + '" class="remove-body img-circle">');
        openModal('modal-detail-user');
    }
</script>
<script>
    function sendAddDocument() {
        var formData = new FormData();
        var id = $('#id').val();
        if (id !== 0 && id !== undefined && id !== null) {
            formData.append('id', id);
        }
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
                $('#id').val('');
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
                location.reload();
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
        $('#id_thematique').multiselect({
            maxHeight: 158,
            buttonWidth: '100%'
        });
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
    });
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<c:url value="/" var="basePath"/>
<%
	String pDActive = (request.getParameter("pageDocument") != null)?"active":"";
	String pPActive= (request.getParameter("pP")!= null)?"active":"";
	if(pPActive.equals(""))pDActive="active";
%>
<c:set var="pDActive" value="<%=pDActive%>"/>
<c:set var="pPActive" value="<%=pPActive%>"/>
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
<style>
    /**Modal photo style**/

    /* The Modal (background) */
    .modalP {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        padding-top: 100px; /* Location of the box */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
    }

    /* Modal Content (Image) */
    .modalP-content {
        margin: auto;
        display: block;
        max-height: 70vh;
        max-width: 100%;
    }    

    /* Add Animation - Zoom in the Modal */
    .modalP-content, #caption { 
        animation-name: zoom;
        animation-duration: 0.6s;
    }

    @keyframes zoom {
        from {transform:scale(0)} 
        to {transform:scale(1)}
    }

    /* The Close Button */
    .closeP {
        /*position: absolute;*/
        margin-top: 30px;
        margin-right: 45px;
        color: #f1f1f1;
        font-size: 40px;
        font-weight: bold;
        transition: 0.3s;
        float: right;
    }

    .closeP:hover,
    .closeP:focus {
        color: #bbb;
        text-decoration: none;
        cursor: pointer;
    }
    .modalP-content:hover {
        opacity: 1;
    }
    /* 100% Image Width on Smaller Screens */
    @media only screen and (max-width: 700px){
        .modalP-content {
            width: 100%;
        }
    }
    @media (max-width: 990px) {
        #caption {
            margin-left: 25px;
        }
    }
</style>
<div class="forum-container page-document">
    <div class="row">
        <div class="page-title">
            <h2 class="doc"><spring:message code="document.title"/></h2>
        </div>
        <div class="col-xs-12 col-md-12">
            <div class="box">
                <!-- D Tab -->
                <ul class="nav nav-tabs userProfileTabs" role="tablist">
                    <li role="presentation" class="${pDActive}"><a href="#tab-item-1" aria-controls="tab-item-4" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.documents"/></a></li>
                    <li role="presentation" class="${pPActive}"><a href="#tab-item-4" aria-controls="tab-item-1" role="tab" data-toggle="tab" aria-expanded="true"><spring:message code="document.header.pictures"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-2" aria-controls="tab-item-2" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.videos"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-3" aria-controls="tab-item-3" role="tab" data-toggle="tab" aria-expanded="false"><spring:message code="document.header.audios"/></a></li>
                    <li role="presentation" class=""><a href="#tab-item-5" aria-controls="tab-item-5" role="tab" data-toggle="tab" aria-expanded="false">Youtube</a></li>
                        <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
                        <c:choose>
                            <c:when test="${isLoggedInUser}">
                            <li style="float: right;"><button style="color: white;" class="btn" aria-controls="tab-item-5" role="tab" aria-expanded="false" onclick="operModifAddModal()"><spring:message code="document.add"/></button></li>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                </ul>
                <!-- F Tab -->

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane ${pDActive} fade in" id="tab-item-1">
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
                                                    <th style="font-size: 15px; border-color: white; text-align: right;">Page: ${pagination.pageDocument.pageDocumentCurrent+1}/${pagination.pageDocument.pageDocumentFin} | Total: </th>                                                                                                        
                                                    <th style="font-size: 15px; border-color: white; text-align: right;">${pagination.pageDocument.pageDocumentTotalElement}</th>                                                                                                        
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
                                                            <th style="color:  dodgerblue;font-size: 15px; border-color: white; border-top-color: #dddddd;"></th>                                                                                                        
                                                        </tr>
                                                    </c:when>
                                                    <c:otherwise></c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                        <tr style="border:1px solid #ccc;">                                                                                        
                                            <td><a href="#" onclick="showDetailNew(${publication.id})"><c:out	value="${publication.title}" /></a></td>
                                            <td><c:out	value="${publication.coverage}" /></td>
                                            <td><c:out	value="${publication.creator}" /></td>
                                            <td class="text-center">
                                                <%--<c:url var="publicationPageUrl" value="/files/${publication.idDocument}"/>--%>
                                                <c:url var="publicationPageUrl" value="${publication.url}"/>
                                                <!--<a class='btn btn-info btn-xs' href="${publicationPageUrl}" download="${publication.title}.pdf"><span class="glyphicont"></span>Télécharger</a></td>-->
                                                <a target="_blank" rel="noopener noreferrer" title="view document" href="${publicationPageUrl}"><span class="glyphicont"></span><img src="${resourcesPath}/images/icon-thema.png" alt=""></a>
                                            </td>
                                            <c:choose>
                                                <c:when test="${isLoggedInUser && currentUser.id == publication.idUtilisateur}">
                                                    <td class="text-center">
                                                        <a title="edit or remove" href="#" onclick="showDetailForModification(${publication.id})"><span class="glyphicont"></span><i style="margin-top: 3px;" class="fa fa-edit fa-2x"></i></a>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="text-center"></td>
                                                </c:otherwise>
                                            </c:choose>                                            
                                        </tr>
                                    </c:forEach>
                                </table>

                                <!-- D Pagination -->
                                <c:choose>
                                    <c:when test="${topic != 0}">
                                        <c:url var="pageBaseUrl" value="/documents?topic=${topic}"/>
                                        <page:pagination currentPage="${pagination.pageDocument.pageDocumentCurrent+1}" page="pageDocument" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${!search.equals(Integer.toString(0))}">
                                                <c:url var="pageBaseUrl" value="/documents?search=${search}"/>
                                                <page:pagination currentPage="${pagination.pageDocument.pageDocumentCurrent+1}" page="pageDocument" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url var="pageBaseUrl" value="/documents"/>
                                                <page:pagination currentPage="${pagination.pageDocument.pageDocumentCurrent+1}" page="pageDocument" totalPages="${pagination.pageDocument.pageDocumentFin}" pageBaseUrl="${pageBaseUrl}"/>
                                            </c:otherwise>
                                        </c:choose>                                        
                                    </c:otherwise>
                                </c:choose>
                                <!-- F Pagination -->
                            </div>
                        </div>
                    </div>   
                    <div role="tabpanel" class="tab-pane ${pPActive} fade in" id="tab-item-4">
                        <div class="txt-content">

                            <div class="col-md-offset custyle">
								<!-- D Pagination Photo -->
								<div align="left">
									<c:choose>
										<c:when test="${!search.equals(Integer.toString(0))}">
											<c:url var="pageBaseUrl" value="/documents?search=${search}" />
											<page:pagination
												currentPage="${pagination.pagePhoto.pagePhotoCurrent+1}"
												totalPages="${pagination.pagePhoto.pagePhotoFin}"
												page="pP"
												pageBaseUrl="${pageBaseUrl}" />
										</c:when>
										<c:otherwise>
											<c:url var="pageBaseUrl" value="/documents" />
											<page:pagination
												currentPage="${pagination.pagePhoto.pagePhotoCurrent+1}"
												totalPages="${pagination.pagePhoto.pagePhotoFin}"
												page="pP"
												pageBaseUrl="${pageBaseUrl}" />
										</c:otherwise>
									</c:choose>
								</div>
								<!-- F Pagination --> 
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
                                                <a href="#" onclick="showPhoto('${pic.title}', '${basePath}${pic.url}');">
                                                    <img src="${resourcesPath}/images/l-blank.png" style="background-image: url('${basePath}${pic.url}'); " class="img-responsive" 
                                                         onclick="showPhoto('${pic.id}', '${basePath}${pic.url}');" class="hover-shadow cursor" alt="--">
                                                </a>
                                            <figcaption class="mask">
                                                <p><i>${pic.title}</i></p>
                                                <p>--</p>
                                            </figcaption>
                                            </li>
                                            <c:set var="isa" value="${isa+1}"/>
                                        </c:forEach>
                                    </ul>
                                </div>
                                
                                <div align="left">
									<c:choose>
										<c:when test="${!search.equals(Integer.toString(0))}">
											<c:url var="pageBaseUrl" value="/documents?search=${search}" />
											<page:pagination
												currentPage="${pagination.pagePhoto.pagePhotoCurrent+1}"
												totalPages="${pagination.pagePhoto.pagePhotoFin}"
												page="pP"
												pageBaseUrl="${pageBaseUrl}" />
										</c:when>
										<c:otherwise>
											<c:url var="pageBaseUrl" value="/documents" />
											<page:pagination
												currentPage="${pagination.pagePhoto.pagePhotoCurrent+1}"
												totalPages="${pagination.pagePhoto.pagePhotoFin}"
												page="pP"
												pageBaseUrl="${pageBaseUrl}" />
										</c:otherwise>
									</c:choose>
								</div> 
                                
                                <div id="myModal" class="modal">
                                    <span class="close cursor" onclick="closeModal()">&times;</span>
                                    <div class="modal-content">
                                        <c:set var="isa1" value="1"/>
                                        <c:forEach items="${docIMAGE}" var="pic">
                                            <div class="mySlides">
                                                <div class="numbertext"><c:out value="${isa1 }"/> /<c:out value="${isa }"/></div>
                                                <!--<img src="${pic.url}" style="width:100%">-->
                                                <%--                                                 <img src="${resourcesPath}/upload/${pic.url}" style="width:100%"> --%>
                                                <img src="${pic.url}" style="width:100%">
                                                <c:set var="isa1" value="${isa1+1}"/>
                                            </div>
                                        </c:forEach>
                                        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                                        <a class="next" onclick="plusSlides(1)">&#10095;</a>
                                    </div>
                                </div>	

                                <!-- Modal detail photo -->
                                <div id="modalDetailPhoto" class="modalP">
                                    <div class="row">
                                        <!-- The Close Button -->
                                        <div class="row">
                                            <span class="closeP" onclick="closeModal('modalDetailPhoto');">&times;</span>
                                        </div>
                                        <div class="row">
                                            <!-- Modal Content (The Image) -->
                                            <div class="col-md-8">
                                                <div class="modalP-content" id="img01"></div>
                                            </div>
                                            <div class="col-md-4" id="caption" style="background-color:  black;border-radius:  5px;border-style: solid;border-color: #9d5b00;">
                                                <table id="photoTable" class="" style="width: 100%; font-size: 18px; color: white; margin-top: 10px; margin-bottom: 10px;">
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.title"/> :</td>
                                                        <td id="photoSpecies"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.date"/> :</td>
                                                        <td id="photoDate"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.location"/> :</td>
                                                        <td id="photoLocalisation"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.source"/> :</td>
                                                        <td id="photoSource"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.author"/> :</td>
                                                        <td id="photoAuteur"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><spring:message code="metadata.photo.right"/> :</td>
                                                        <td id="photoRight"></td>
                                                    </tr>
                                                </table>
                                            </div>                                        
                                        </div>
                                        <!-- Modal Caption (Image Text) -->
                                        <!--<div id="caption"></div>-->
                                    </div>                                    
                                </div>                               
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
                                            <td><c:out	value="${video.title}" /></td>
                                            <td class="text-center">
                                                <c:url var="videoPageUrl" value="${video.url}"/>
                                                <a class='btn btn-info btn-xs' href="${videoPageUrl}"><span class="glyphicont"></span>Visionner</a></td>
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
                                            <td><c:out	value="${audio.title}" /></td>
                                            <td class="text-center">
                                                <c:url var="audioPageUrl" value="${audio.url}"/>
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
                        <button type="button" class="close" onclick="closeModifAddModal()">&times;</button>
                        <h4 class="modal-title"><spring:message code="document.add_document.title"/></h4>
                    </div>
                    <form action="javascript:sendAddDocument();" autocomplete="off">
                        <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                            <div style=" font-size: 10px; color: #999;"><span style="color: red;">NB</span><spring:message code="document.add_document.nb"/></div>
                            <div id="errorMdp"></div>
                            <input type="hidden" id="id">
                            <spring:message code="metadata.topics"/><sup>*</sup><br>
                            <select title="<spring:message code="metadata.popup.bubble.topics"/>" required multiple="" id="id_thematique" class="form-control">
                                <c:forEach items="${topThematiques}" var="topThematique">
                                    <option value="${topThematique.thematique.id}"><spring:message code="document.thematique.id.${topThematique.thematique.id}"/></option>
                                </c:forEach>                                
                            </select>                            
                            <spring:message code="metadata.type"/><sup>*</sup>
                            <select required title="<spring:message code="metadata.popup.bubble.type"/>" class="form-control" id="type" style="width: 100%!important;">
                                <option disabled selected value> -- select an option -- </option>
                                <option style="background-color: #f9efc9;" value="4">Document</option>
                                <option style="background-color: #f9efc9;" value="1">Photo</option>
                                <option style="background-color: #f9efc9;" value="2">Video</option>
                                <option style="background-color: #f9efc9;" value="3">Audio</option>
                            </select>                            
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.year"/><sup>*</sup>                        
                                <input title="<spring:message code="metadata.popup.bubble.year"/>" required type="text" class="form-control" id="year">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.title"/><sup>*</sup>
                                <input title="<spring:message code="metadata.popup.bubble.title"/>" required type="text" class="form-control" id="title">
                            </div>
                            <spring:message code="global.label.file"/>
                            <input type="file" class="form-control" id="document">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.url"/>
                                <input title="<spring:message code="metadata.popup.bubble.url"/>" placeholder="ex: https://www.lemursportal.org/forum/resources/upload/1530798561545Ziheng&Yoder2003.pdf" type="text" class="form-control" id="url">
                            </div>
                            <div class="" style="width: 100%;">
                                <spring:message code="metadata.species"/>
                                <select title="<spring:message code="metadata.popup.bubble.species"/>" multiple class="form-control" id="species" style="width: 100%!important;"></select>
                            </div>
                            <spring:message code="metadata.date"/>
                            <input title="<spring:message code="metadata.popup.bubble.date"/>" type="date" class="form-control" id="datePublication">
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.bibliographicResource"/>
                                <select title="<spring:message code="metadata.popup.bubble.bibliographicResource"/>" class="form-control" id="bibliographic_resource" style="width: 100%!important;">
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
                            </div>                                                
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.coverage"/>
                                <input title="<spring:message code="metadata.popup.bubble.coverage"/>" placeholder="ex: SAINTE LUCE | FORT-DAUPHIN | MADAGASCAR" type="text" class="form-control" id="coverage">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.description"/>
                                <textarea title="<spring:message code="metadata.popup.bubble.description"/>" class="form-control" id="description">
                                    
                                </textarea>
                            </div>
                            <spring:message code="metadata.language"/>
                            <select title="<spring:message code="metadata.popup.bubble.language"/>" class="form-control" id="language" style="width: 100%!important;">
                                <option style="background-color: #f9efc9;" selected="" value="MLG">MG</option>
                                <option style="background-color: #f9efc9;" value="EN">EN</option>
                                <option style="background-color: #f9efc9;" value="FR">FR</option>
                                <option style="background-color: #f9efc9;" value="FR">OTHERS</option>
                            </select>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.relation"/>
                                <input title="<spring:message code="metadata.popup.bubble.relation"/>" placeholder="ex: Relation" type="text" class="form-control" id="relation">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.source"/>
                                <input title="<spring:message code="metadata.popup.bubble.source"/>" placeholder="ex: Syst. Biol. 52(5):705–716, 2003" type="text" class="form-control" id="source">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.subject"/>
                                <input title="<spring:message code="metadata.popup.bubble.subject"/>" placeholder="ex: Mouse lemur Species | Bayesian Methods" type="text" class="form-control" id="subject">
                            </div>                        
                            <!--<div class="autocomplete" style="width: 100%;">-->
                            <%--<spring:message code="metadata.format"/>--%>
                            <input title="<spring:message code="metadata.popup.bubble.format"/>" placeholder="ex: text" type="hidden" class="form-control" id="format">
                            <!--</div>-->
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.fileFormat"/>
                                <input title="<spring:message code="metadata.popup.bubble.fileFormat"/>" placeholder="ex: PDF" type="text" class="form-control" id="fileFormat">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.identifier"/>
                                <input title="<spring:message code="metadata.popup.bubble.identifier"/>" placeholder="ex :ISSN: 1063-5157 print / 1076-836X online | DOI: 10.1080/10635150390235557" type="text" class="form-control" id="identifier">
                            </div>                        
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.contributor"/>
                                <input title="<spring:message code="metadata.popup.bubble.contributor"/>" placeholder="ex: Department of Biology, University College London | Department of Ecology and Evolutionary Biology, Yale University" type="text" class="form-control" id="contributor">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.creator"/>
                                <input title="<spring:message code="metadata.popup.bubble.creator"/>" placeholder="ex: ZIHENG YANG | ANNE D. YODER" type="text" class="form-control" id="creator">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.publisher"/>
                                <input title="<spring:message code="metadata.popup.bubble.publisher"/>" placeholder="ex: Society of Systematic Biologists" type="text" class="form-control" id="publisher">
                            </div>
                            <div class="autocomplete" style="width: 100%;">
                                <spring:message code="metadata.rights"/>
                                <input title="<spring:message code="metadata.popup.bubble.rights"/>" placeholder="ex: Syst. Biol. 52(5):705–716, 2003 | Copyright(c) Society of Systematic Biologists" type="text" class="form-control" id="rights">                        
                            </div>                            
                        </div>
                        <div class="modal-footer">
                            <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModifAddModal()"><spring:message code="global.btn.cancel"/></button>
                            <button style="float: right;" type="submit" class="btn btn-default" data-dismiss="modal"><spring:message code="global.btn.save"/></button>
                            <div id="delete"></div>
                        </div>
                    </form>
                </div>
            </div>                        
        </div>        
        <div id="modal-detail-document" class="modal">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeDetailModal()">&times;</button>
                        <h4 id="titleDetail" class="modal-title"></h4>
                    </div>
                    <div style="overflow-y: auto;max-height:  500px;">
                        <table class="table">
                            <tr>
                                <td><spring:message code="metadata.topics"/></td>
                                <td>
                                    <select disabled title="<spring:message code="metadata.popup.bubble.topics"/>" required multiple="" id="idThematiqueDetail" class="form-control">
                                        <c:forEach items="${topThematiques}" var="topThematique">
                                            <option value="${topThematique.thematique.id}"><spring:message code="document.thematique.id.${topThematique.thematique.id}"/></option>
                                        </c:forEach>                                
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.type"/></td>
                                <td>
                                    <select disabled required title="<spring:message code="metadata.popup.bubble.type"/>" class="form-control" id="typeDetail" style="width: 100%!important;">                                    
                                        <option style="background-color: #f9efc9;" value="4">Document</option>
                                        <option style="background-color: #f9efc9;" value="1">Photo</option>
                                        <option style="background-color: #f9efc9;" value="2">Video</option>
                                        <option style="background-color: #f9efc9;" value="3">Audio</option>
                                    </select>                            
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.year"/></td>
                                <td>
                                    <p id="yearDetail"></p>
                                </td>
                            </tr>                        
                            <tr>
                                <td><spring:message code="metadata.url"/></td>
                                <td>
                                    <p id="urlDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.species"/></td>
                                <td>
                                    <select disabled multiple class="form-control" id="speciesDetail" style="width: 100%!important;"></select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.date"/></td>
                                <td>
                                    <p id="datePublicationDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.bibliographicResource"/></td>
                                <td>
                                    <select disabled title="<spring:message code="metadata.popup.bubble.bibliographicResource"/>" class="form-control" id="bibliographicResourceDetail" style="width: 100%!important;">
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
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.coverage"/></td>
                                <td>
                                    <p id="coverageDetail"></p>
                                </td>
                            </tr>
                            <tr><!---->
                                <td><spring:message code="metadata.description"/></td>
                                <td style="text-align: justify;text-justify: inter-word;">
                                    <p id="descriptionDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.language"/></td>
                                <td>
                                    <select disabled title="<spring:message code="metadata.popup.bubble.language"/>" class="form-control" id="languageDetail" style="width: 100%!important;">
                                        <option style="background-color: #f9efc9;" selected="" value="MLG">MG</option>
                                        <option style="background-color: #f9efc9;" value="EN">EN</option>
                                        <option style="background-color: #f9efc9;" value="FR">FR</option>
                                        <option style="background-color: #f9efc9;" value="FR">OTHERS</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.relation"/></td>
                                <td>
                                    <p id="relationDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.source"/></td>
                                <td>
                                    <p id="sourceDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.subject"/></td>
                                <td>
                                    <p id="subjectDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.fileFormat"/></td>
                                <td>
                                    <p id="fileFormatDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.identifier"/></td>
                                <td>
                                    <p id="identifierDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.contributor"/></td>
                                <td>
                                    <p id="contributorDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.creator"/></td>
                                <td>
                                    <p id="creatorDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.publisher"/></td>
                                <td>
                                    <p id="publisherDetail"></p>
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="metadata.rights"/></td>
                                <td>
                                    <p id="rightsDetail"></p>
                                </td>
                            </tr>
                        </table>                    
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDetailModal()">OK</button>                                                
                    </div>                    
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
        <!--modal confirmation delete-->
        <div id="modal-delete" class="modal edit-profil-form">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModal('modal-delete')">&times;</button>
                        <h4 class="modal-title">Suppression</h4>
                    </div>
                    <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                        <span>Cette action est irreversible.</span>
                        <span id="supression_sentence"></span>
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-detail')">Annuler</button>                        
                        <div id="performe_delete"></div>
                    </div>
                </div>
            </div>                        
        </div>
    </div>        
</div>
<script>
    function showPhoto(id, url) {
        $('.removable-row').remove();
        $('#photoDate').text('');
        $('#photoLocalisation').text('');
        $('#photoSource').text('');
        $('#photoAuteur').text('');
        $('#photoRight').text('');
        $.get("metadata/" + id, {}, function (data) {
            $('#photoDate').text(data[0].value.date);
            $('#photoLocalisation').text(data[0].value.coverage);
            $('#photoSource').text(data[0].value.source);
            $('#photoAuteur').text(data[0].value.creator);
            $('#photoRight').text(data[0].value.rights);
        }).done(function () {
            $.getJSON('metadata/' + id + '/taxonomis', {}, function (data, textStatus) {
                if (data.length > 0) {
                    var species = '';
                    for (var v = 0; v < data.length; v++) {
                        species += data[v].scientificname + '<br>';
                    }
                    drawRow('Species :', species, 'photoTable');
                }
            });
        });
        document.getElementById("img01").innerHTML = '<img class="modalP-content" src="' + url + '">';
        openModal('modalDetailPhoto');
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
        var id = -1;
        $.each(data, function (key, value) {
            if (key !== 'id' && key !== 'idDocument' && key !== 'idUtilisateur' && key !== 'document' && key !== 'listeAssociationMetadataTopic') {
                if (key === 'url') {
                    if (value.indexOf("/resources/upload/") === 0) {
                        value = value.substring(1);
                    }
                    value = '<a href="' + value + '">' + value + '</a>';
                }
                drawRow(key + ":", value, table);
            } else if (key === 'id') {
                id = value;
            }
        });
        $.getJSON('metadata/' + id + '/taxonomis', {}, function (data, textStatus) {
            if (data.length > 0) {
                var species = '<ul>';
                for (var v = 0; v < data.length; v++) {
                    species += '<li>' + data[v].scientificname + '</li>';
                }
                species += '</ul>';
                drawRow('species', species, table);
            }
        }).done(function () {
            $.getJSON('metadata/' + id + '/topics', {}, function (data, textStatus) {
                if (data.length > 0) {
                    var species = '<ul>';
                    for (var v = 0; v < data.length; v++) {
                        species += '<li>' + data[v].libelle + '</li>';
                    }
                    species += '</ul>';
                    drawRow('Topics', species, table);
                }
            });
        });
    }

    function drawRow(key, value, table) {
        var row = $("<tr class='removable-row' />");
        $("#" + table).append(row);
        row.append($("<td>" + key + "</td>"));
        row.append($("<th>" + value + "</th>"));
    }

    function operModifAddModal() {
        populate();
        resetModalModifAjout();
        openModal('modal-ajout-document');
    }
    function openDeleteModal(id, title) {
        openModal('modal-delete');
        $('#supression_sentence').html("voulez-vous vraiment supprimer " + title + " ?");
        $('#performe_delete').html('<button style="float: right;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteDocument(' + id + ')">Supprimer</button>');
    }

    function closeModifAddModal() {
        $("#modal-ajout-document").load(location.href + " #modal-ajout-document>*", "");
        closeModal('modal-ajout-document');
    }
    function closeDetailModal() {
        $("#modal-detail-document").load(location.href + " #modal-detail-document>*", "");
        closeModal('modal-detail-document');
    }

    function showDetailForModification(id) {
        var taxx = [];
//        $("#modal-ajout-document").load(location.href + " #modal-ajout-document>*", "");
        $.get("metadata/" + id + "/all", function (data) {
            $('#year').val(data.metadata.year);
            $('#title').val(data.metadata.title);
            $('#type').val(data.metadata.type);
            $('#url').val(data.metadata.url);
            $('#datePublication').val(data.metadata.date);
//            $('#date').value = '2012-01-01';
            $('#coverage').val(data.metadata.coverage);
            $('#description').val(data.metadata.description);
            $('#language').val(data.metadata.language);
            $('#relation').val(data.metadata.relation);
            $('#source').val(data.metadata.source);
            $('#subject').val(data.metadata.subject);
            $('#format').val(data.metadata.format);
            $('#fileFormat').val(data.metadata.fileFormat);
            $('#identifier').val(data.metadata.identifier);
            $('#contributor').val(data.metadata.contributor);
            $('#creator').val(data.metadata.creator);
            $('#publisher').val(data.metadata.publisher);
            $('#rights').val(data.metadata.rights);
            $('#id').val(id);
            if (data.topics.length > 0) {
                for (var v = 0; v < data.topics.length; v++) {
                    $('#id_thematique option[value=' + data.topics[v].id + ']').attr('selected', true);
                }
            }
            taxx = data.taxonomi;
            $("#delete").html('<button style="float: left;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="openDeleteModal(' + id + ', \'' + data.metadata.title + '\')">Supprimer</button>');
        }).done(function () {
            openModal('modal-ajout-document');
            $('#id_thematique').multiselect({
                maxHeight: 158,
                buttonWidth: '100%'
            });
            //get all taxo begin
            $.getJSON('https://www.lemursportal.org/species/getallTaxo', {}, function (data, textStatus) {
                var el = $('select#species');
                el.html('');  // empty the select
                $.each(data, function (idx, jsonData) {
                    el.append($('<option style="background-color: #f9efc9;"></option>').val(jsonData.id).html(jsonData.scientificname));
                });
            }).done(function () {
                if (taxx.length > 0) {
                    for (var v = 0; v < taxx.length; v++) {
                        $('#species option[value=' + taxx[v].id + ']').attr('selected', 'true');
                    }
                }
                $('#species').multiselect({
                    maxHeight: 316,
                    enableFiltering: true,
                    enableCaseInsensitiveFiltering: true,
                    buttonWidth: '100%'
                });
            });
            //get all taxo end                                
        });
    }
    function showDetailNew(id) {
        var taxx = [];
        $.get("metadata/" + id + "/all", function (data) {
            $('#yearDetail').text(data.metadata.year);
            $('#titleDetail').text(data.metadata.title);
            $('#typeDetail').val(data.metadata.type);
            if (data.metadata.url.charAt(0) === '/') {
                data.metadata.url = 'https://www.lemursportal.org/forum' + data.metadata.url;
            } else if (!data.metadata.url.includes('http')) {
                data.metadata.url = 'https://' + data.metadata.url;
            }
            $('#urlDetail').html('<a href="' + data.metadata.url + '">' + data.metadata.url + '</a>');
            $('#datePublicationDetail').text(data.metadata.date);
            $('#coverageDetail').text(data.metadata.coverage);
            $('#descriptionDetail').text(data.metadata.description);
            $('#languageDetail').val(data.metadata.language);
            $('#relationDetail').text(data.metadata.relation);
            $('#sourceDetail').text(data.metadata.source);
            $('#subjectDetail').text(data.metadata.subject);
            $('#fileFormatDetail').text(data.metadata.fileFormat);
            $('#identifierDetail').text(data.metadata.identifier);
            $('#contributorDetail').text(data.metadata.contributor);
            $('#creatorDetail').text(data.metadata.creator);
            $('#publisherDetail').text(data.metadata.publisher);
            $('#rightsDetail').text(data.metadata.rights);
            if (data.topics.length > 0) {
                for (var v = 0; v < data.topics.length; v++) {
                    $('#idThematiqueDetail option[value=' + data.topics[v].id + ']').attr('selected', true);
                }
            }
            taxx = data.taxonomi;
        }).done(function () {
            openModal('modal-detail-document');
            $('#idThematiqueDetail').multiselect({
                maxHeight: 158,
                buttonWidth: '100%'
            });
            //get all taxo begin
            $.getJSON('https://www.lemursportal.org/species/getallTaxo', {}, function (data, textStatus) {
                var el = $('select#speciesDetail');
                el.html('');  // empty the select
                $.each(data, function (idx, jsonData) {
                    el.append($('<option style="background-color: #f9efc9;"></option>').val(jsonData.id).html(jsonData.scientificname));
                });
            }).done(function () {
                if (taxx.length > 0) {
                    for (var v = 0; v < taxx.length; v++) {
                        $('#speciesDetail option[value=' + taxx[v].id + ']').attr('selected', 'true');
                    }
                }
                $('#speciesDetail').multiselect({
                    maxHeight: 316,
                    enableFiltering: true,
                    enableCaseInsensitiveFiltering: true,
                    buttonWidth: '100%'
                });
            });
            //get all taxo end                                
        });
    }
    function resetModalModifAjout() {
        $('#year').val('');
        $('#title').val('');
        $('#url').val('');
        $('#date').val('');
        $('#coverage').val('');
        $('#description').val('');
        $('#language').val('');
        $('#relation').val('');
        $('#source').val('');
        $('#subject').val('');
        $('#format').val('');
        $('#fileFormat').val('');
        $('#identifier').val('');
        $('#contributor').val('');
        $('#creator').val('');
        $('#publisher').val('');
        $('#rights').val('');
        $('#id').val('');
    }
    function deleteDocument(id) {
        $.post('metadata/delete/' + id, {}, function (data, textStatus) {
            if (data === true) {
                location.reload();
            } else
                alert(data);
        }).error(function (data, textStatus) {
            closeModal('modal-delete');
            closeModifAddModal();
            alert("La supression a rencontré une erreur. Veuiller réessayer ultérieurement");
        });
    }
</script>
<script>
    function sendAddDocument() {
        var formData = new FormData();
        var id = $('#id').val();
        if (id !== 0 && id !== undefined && id !== null) {
            formData.append('id', id);
        }
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
                $('#id').val('');
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
                location.reload();
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
        $('#id_thematique').multiselect({
            maxHeight: 158,
            buttonWidth: '100%'
        });
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

>>>>>>> c8a0b42aef870c449ae1414199f6d6e8244a54e2
</script>