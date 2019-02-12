<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/resources" var="resourcesPath"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <style>
        .img-post {
            max-height: 100px;
            float: left;
        }
        .img-post-sup-3 {
            float: left;
            max-width: 33.33%;
            max-height: 100px;
            min-height: 100px;
            margin-right: 1px;
            margin-bottom: 1px;
        }
    </style>
    <!-- D Question/Reponse -->
    <div class="forum-container reponse-quest">

        <div class="page-title">
            <h2 class="formation">Question</h2>
        </div>

        <div class="forum-item">
            <div class="row">
                <div class="col-md-11">
                    <user:forum-profil userInfo="${post.owner}"/>
                    <a href="#" class="forum-item-title"><c:out value="${post.title}" /></a>
                    <div class="forum-sub-title">
                        <c:out value="${post.body}" />
                        <p class="forum-date"><fmt:formatDate pattern="${datetimeFormat}" value="${post.creationDate}"/></p>
                    </div>
                </div>

                <div class="col-md-1 forum-info">
                    <span class="views-number">
                        ${responsesPage.totalElements }
                    </span>
                    <div class="vue">
                        <small>Commentaires</small>
                    </div>
                </div>
                <div class="col-md-12 forum-info">
                    <c:if test="${post.documents != null}">
                        <c:choose>
                            <c:when test="${post.documents.size() == 1}">
                                <div class="col-md-12 forum-user-info img-post-parent-${post.id}" style="padding-left: 0px;">                        
                                    <c:if test="${post.documents.get(0).typeId == 1}">
                                        <a href="${resourcesPath}${post.documents.get(0).url.substring(10)}"><img class="img-post" src="${resourcesPath}${post.documents.get(0).url.substring(10)}" alt="${post.documents.get(0).filename}"></a>
                                        </c:if>                        
                                        <%--<c:url var="videoPageUrl" value="/files/${document.id}"/>--%>
                                        <!--<a  href="${videoPageUrl}">-->
                                            <!--<img style="max-width: 125px; max-height: 60px; float: left; display: none;" src="${resourcesPath}/upload/${document.filename}" alt=""></a>-->
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-12 forum-user-info img-post-parent-${post.id}" style="padding-left: 0px;">
                                    <c:forEach items="${post.documents}" var="document">
                                        <c:if test="${document.typeId == 1}">
                                            <a href="${resourcesPath}${document.url.substring(10)}"><img class="img-post-sup-3" src="${resourcesPath}${document.url.substring(10)}" alt="${document.filename}"></a>
                                            </c:if>
                                        </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <script>
                            $(document).ready(function () {
                                $('.img-post-parent-${post.id}').magnificPopup({
                                    delegate: 'a', // child items selector, by clicking on it popup will open
                                    type: 'image',
                                    tLoading: 'Loading image #%curr%...',
                                    mainClass: 'mfp-img-mobile',
                                    gallery: {
                                        enabled: true,
                                        navigateByImgClick: true,
                                        preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
                                    }
                                });
                            });
                        </script>
                    </c:if>
                    <c:if test="${post.photos != null}">
                        <c:choose>
                            <c:when test="${post.photos.size() == 1}">
                                <div class="col-md-12 forum-user-info img-post-parent-${post.id}" style="padding-left: 0px;">                        
                                    <a href="${post.photos.get(0).breakpoints.get(0).link}">
                                        <img class="img-post" src="${topQuestion.question.photos.get(0).link}" 
                                             srcset="${post.photos.get(0).getBreakpointsAsStringForSrcset()}"
                                             sizes="20vw"
                                             alt="${post.photos.get(0).name}"></a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-12 forum-user-info img-post-parent-${post.id}" style="padding-left: 0px;">
                                    <c:forEach items="${post.photos}" var="photo">
                                        <a href="${photo.breakpoints.get(0).link}">
                                            <img class="img-post-sup-3" src="${photo.link}" 
                                                 srcset="${photo.getBreakpointsAsStringForSrcset()}"
                                                 sizes="20vw"
                                                 alt="${photo.name}"></a>
                                        </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <script>
                            $(document).ready(function () {
                                $('.img-post-parent-${post.id}').magnificPopup({
                                    delegate: 'a', // child items selector, by clicking on it popup will open
                                    type: 'image',
                                    tLoading: 'Loading image #%curr%...',
                                    mainClass: 'mfp-img-mobile',
                                    gallery: {
                                        enabled: true,
                                        navigateByImgClick: true,
                                        preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
                                    }
                                });
                            });
                        </script>
                    </c:if>
                    <!--///////////////////////////-->
                    <%--<c:if test="${post.documentId > 0}">--%>
                    <%--<c:url var="videoPageUrl" value="/files/${post.documentId}"/>--%>
                    <!--<a  href="${videoPageUrl}">-->
                        <!--<img src="${resourcesPath}/images/icon-audio.png" alt=""></a>-->
                    <%--</c:if>--%>
                    <%--<c:if test="${not empty post.uriYoutube }">--%>
                    <%--<c:url var="publicationPageUrl" value="${post.uriYoutube}"/>--%>
                <!--<a  href="${publicationPageUrl}"  target="_blank">-->
                    <!--<img src="${resourcesPath}/images/icon-video-document.png" alt=""></a>-->
                    <%--</c:if>--%>
                </div>
            </div>
        </div>

        <div class="post-comments">
            <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
            <c:if test="${isLoggedInUser}">
                <div class="comment-meta">
                    <span>
                        <a class="btn" role="button" data-toggle="collapse" href="#replyCommentT" aria-expanded="true" aria-controls="collapseExample"><spring:message code="post.comment.answer"/></a>
                    </span>
                    <div class="collapse" id="replyCommentT">
                        <c:url value="/secured/post/reponse" var="formAction"></c:url>
                        <form:form  class="create-quest-form" modelAttribute="post" action="${formAction}"  method="POST"   >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <form:input type="hidden" path="id" value="${post.id}"/> <form:input type="hidden" path="thematiqueId" value="${post.thematiqueId}"/>
                            <div class="form-group">                                
                                <form:textarea path="body" class="editor form-control" rows="3"/>
                            </div>                             
                            <form:button value="save"  class="btn">Envoyer</form:button>
                        </form:form>
                    </div>
                </div>
            </c:if>	

            <div class="row">
                <c:forEach items="${responsesPage.content}" var="child">

                    <div class="media">
                        <div class="media-heading col-md-3 forum-user-info">
                            <a href="#" class="al-left"><user:profilImage src="${child.owner.photoProfil}" cssClass="img-circle"/></a>
                            <div class="reponse-user"><a href="#"><c:out value="${child.owner.nom}"/> <c:out value="${child.owner.prenom}"/>
                                </a><br/>
                                <c:set var="idM" value="0" />
                                <c:set var="libelle" value =""/> 
                                <c:forEach items="${child.owner.roles}"
                                           var="role">
                                    <c:if test="${role.id < 10001}"> 
                                        <c:set var="idR" value="${role.id}" />
                                    </c:if> 
                                    <c:if test="${idR > idM }">
                                        <c:set var="idM" value="${idR}" />
                                        <c:set var="libelle" value="${role.libelle}" />
                                    </c:if>
                                </c:forEach>
                                <i class="btn-success btn-xs" style="font-size: 10px;"><c:out value="${libelle}" /></i>                            
                                <br/><br/><fmt:formatDate pattern="${datetimeFormat}" value="${child.creationDate}"/></div>
                        </div>

                        <div class="panel-collapse collapse in col-md-8" id="collapseOne">
                            <div class="media-body" id="comm_${child.id}">
                                <c:out value="${child.body}" escapeXml="true" />
                            </div>
                        </div>
                        <div class="panel-collapse collapse in col-md-1" id="collapseOne">
                            <c:choose>
                                <c:when test="${isLoggedInUser && currentUser.id == child.owner.id}">
                                    <i onclick="openDelete('modal-delete', ${child.id})" class="fa fa-trash-o"></i>
                                    <i onclick="openComment('modal-edit', '${child.body.replace("'", "\\'").replace(System.getProperty("line.separator"), "")}', ${child.id})" class="fa fa-edit" style="margin-left: 2px;"></i>
                                </c:when>
                                <c:otherwise>
                                    <sec:authorize access="hasAnyRole('ADMIN, MODERATEUR')">
                                        <i onclick="openDelete('modal-delete', ${child.id})" class="fa fa-trash-o"></i>
                                    </sec:authorize>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                </c:forEach> 


            </div>


        </div>
        <!-- 			D Pagination -->
        <c:url var="currentBaseUrl" value="/post/show/${post.id}"/>
        <page:pagination currentPage="${responsesPage.number + 1}" totalPages="${responsesPage.totalPages}" pageBaseUrl="${currentBaseUrl}"/>
    </div>
</div>
<!--modal for edit-->
<div id="modal-edit" style="background-color: #0000004d;" class="modal edit-profil-form">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closeModal('modal-edit')">&times;</button>
                <h4 class="modal-title" id="title-modal-edits"><spring:message code="details-post.modal-comment.title"/></h4>
            </div>
            <div id="modal-edit-body" class="modal-body" style="overflow-y: auto;max-height:  500px;">
                <textarea class="form-control" id="modalTextArea"></textarea>
            </div>
            <input type="hidden" id="id_comment">
            <div class="modal-footer">
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-edit')"><spring:message code="global.btn.cancel"/></button>
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="updateComment()"><spring:message code="global.btn.save"/></button>                        
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
                <h4 class="modal-title"><spring:message code="delete.modal.title"/></h4>
            </div>
            <div class="modal-body" style="overflow-y: auto;max-height:  500px;">
                <span><spring:message code="delete.modal.message"/></span>
                <span id="supression_sentence"></span>
            </div>
            <input type="hidden" id="id_comm_to_delete">
            <div class="modal-footer">
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-delete')"><spring:message code="global.btn.cancel"/></button>
                <button style="float: right;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteComment()"><spring:message code="delete.modal.button"/></button>
            </div>
        </div>
    </div>                        
</div>
<script>
    function openComment(modalName, text, id) {
        $('#modalTextArea').text(text);
        $('#id_comment').val(id);
        openModal(modalName);
    }
    function openDelete(modalName, id) {
        $('#id_comm_to_delete').val(id);
        openModal(modalName);
    }
    function updateComment() {
        $.post("http://localhost:8088/LemursPortal-web/secured/comment/" + $('#id_comment').val(),
                {post: $('#modalTextArea').val()},
                function (response) {
                    console.log(response);
                    if (response) {
                        $('#comm_' + $('#id_comment').val()).html($('#modalTextArea').val());
                        $('#modalTextArea').remove();
                        $('#modal-edit-body').append('<textarea class="form-control" id="modalTextArea"></textarea>');
                    }
                    closeModal('modal-edit');
                }).fail(function () {
            closeModal('modal-edit');
        });
    }
    function deleteComment() {
        $.get("http://localhost:8088/LemursPortal-web/post/del/" + $('#id_comm_to_delete').val(),
                function (response) {
                    window.location.reload();
                }).fail(function () {
            closeModal('modal-delete');
        });
    }
</script>
