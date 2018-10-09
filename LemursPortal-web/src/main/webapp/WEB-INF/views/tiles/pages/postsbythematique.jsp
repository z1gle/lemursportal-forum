<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>

<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="forum-container">

        <div class="forum-title">
            <sec:authorize access="isAuthenticated()">
                <div class="pull-right forum-desc">
                    <c:url value="/secured/thematique-${thematique.id}/post/create" var="addQuestionUrl"/>
                    <c:url value="/secured/post/" var="upadateLink"/>
                    <a href="${addQuestionUrl}" class="add-quest"><spring:message code="home.ask.question"/></a>
                </div>
            </sec:authorize>
            <h2><span><spring:message code="home.thematiques"/> :</span><spring:message code="document.thematique.id.${thematique.id}"/></h2>
        </div>
        <!-- D Sujet -->
        <c:forEach items="${postsBythematiquePage.content}" var="QuestionParTheme">
            <post:question topQuestion="${QuestionParTheme}"/>
        </c:forEach>
        <!-- F Sujet -->
        <!-- 			D Pagination -->
        <c:url var="currentBaseUrl" value="/postsParThematique/${thematique.id}"/>
        <page:pagination currentPage="${postsBythematiquePage.number + 1}" totalPages="${postsBythematiquePage.totalPages}" pageBaseUrl="${currentBaseUrl}"/>
    </div>
    <script type="text/javascript">
        function updateEdit() {
            $("#form-update").ajaxSubmit({url: '${upadateLink}' + $('#id_id_edit').val(), type: 'post', success:function(data){window.location.reload();}});
        }
        function openEdit(id, thematique, title, post, url) {
            $('.remove-content').remove();
            var body = '<form id="form-update" enctype="multipart/form-data">';
            body += '<input type="hidden" id="id_id_edit" value = "' + id + '">';
            body += '<div class="row remove-content">';
            body += '<div class="col-md-offset-1 col-md-10">';
            body += '<label>Topic</label>';
            body += '<select name="idTopic" id="topic-edit-post-modal" class="form-control">';
        <c:forEach items="${topThematiques}" var="topThematique">
            body += '<option value="${topThematique.thematique.id}">';
            body += '<spring:message code="document.thematique.id.${topThematique.thematique.id}"/>';
            body += '</option>';
        </c:forEach>
            body += '</select><br>';
            body += '<label>Title</label>';
            body += '<input name="title" type="text" class="form-control" value = "' + title + '"><br>';
            body += '<label>Url youtube</label>';
            body += '<input name="uri" type="text" class="form-control" value = "' + url + '"><br>';
            body += '<label>Post</label>';
            body += '<textarea name="post" id="txt-edit-modal" class="form-control"></textarea><br>';
            body += '<div id="photos-edit-modal"></div><br>';
            body += '<label>Add photos</label><br>';
            body += '<input name = "file" type="file" class="form-control" multiple><br>';
            body += '</div>';
            body += '</form></div>';
            $('#modal-edit-post-content').append(body);
            $('#topic-edit-post-modal').val(thematique);
            openModal('modal-edit');
            var photos = $('.list-photo-' + id).map(function () {
                return $(this).val();
            }).get();
            if (photos.length > 0) {
                var image = '<label>Photos</label><br><div style="height: 100px;">';
                for (var i = 0; i < photos.length; i++) {
                    image += '<img class="img-post-sup-3" src="' + photos[i] + '">';
                }
                image += '</div>';
                $('#photos-edit-modal').append(image);
            }
            $('#txt-edit-modal').append(post);
        }
    </script>
</div>