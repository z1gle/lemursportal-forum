<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<c:url value="/resources" var="resourcesPath" />
<c:url value="/secured/photo/delete/" var="deletePath" />
<style type="text/css">
    a.read-more {
        color: black /*#74ac00*/;
        cursor: pointer;
    }

    a.read-more:hover {
        text-decoration: underline;
        color: #000;
    }

    .show-read-more .more-text, .home-bio p .more-text {
        display: none;
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
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="forum-container">
        <div class="forum-title">
            <sec:authorize access="isAuthenticated()">
                <div class="pull-right forum-desc">
                    <c:url value="/secured/post/create" var="addQuestionUrl"/>
                    <a class="add-quest" href="${addQuestionUrl}"><spring:message code="home.ask.question"/></a>
                </div>
            </sec:authorize>
            <h2>
                <spring:message code="home.topquestions" />
            </h2>
        </div>

        <!-- D Sujet -->
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

        <c:forEach items="${topQuestionsPage.content}" var="topQuestion">
            <post:question topQuestion="${topQuestion}"/>
        </c:forEach>
        <!-- F Sujet -->

        <!-- 			D Pagination -->
        <c:url var="pageBaseUrl" value="/"/>
        <page:pagination currentPage="${topQuestionsPage.number + 1}" totalPages="${topQuestionsPage.totalPages}" pageBaseUrl="${pageBaseUrl}"/>
    </div>
</div>
<script type="text/javascript">
    var toDelete = [];

//    function resetWarningButton(id) {
//        $('#warning1-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=1\', ' + id + ')" style="float: right; color: gainsboro;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
//        $('#warning2-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=2\', ' + id + ')" style="float: right; color: gainsboro;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
//        $('#warning3-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=3\', ' + id + ')" style="float: right; color: gainsboro;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
////        $('.toGainsboro').css('color', 'gainsboro');
//    }
//
//    function putAsAlert(link, id) {
//        var lk = link.substring(0, link.lastIndexOf('?'));
//        var alrt = link.substring(link.lastIndexOf('?') + 7);
//        console.log('link:' + lk);
//        console.log('alerte:' + alrt);
//        $.post(lk, {alert: alrt}, function (data, status) {
//            if (data != null) {
//                if (data == true) {
//                    if (alrt == '1') {
//                        resetWarningButton(id);
//                        $('#warning1-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=0\', ' + id + ')" style="float: right; color: red;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
//                    } else if (alrt == '2') {
//                        resetWarningButton(id);
//                        $('#warning2-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=0\', ' + id + ')" style="float: right; color: yellow;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
//                    } else if (alrt == '3') {
//                        resetWarningButton(id);
//                        $('#warning3-' + id).html('<a href="javascript:putAsAlert(\'post/' + id + '?alert=0\', ' + id + ')" style="float: right; color: green;"><i id="waring-' + id + '" class="fa fa-warning toGainsboro"></i></a>');
//                    } else {
//                        resetWarningButton(id);
//                    }
//                }
//            }
//        }
//        );
//    }

    function putAsAlert(link, id) {
        var lk = link.substring(0, link.lastIndexOf('?'));
        var alrt = link.substring(link.lastIndexOf('?') + 7);
        console.log('link:' + lk);
        console.log('alerte:' + alrt);
        $.post(lk, {alert: alrt}, function (data, status) {
            if (data != null) {
                if (data == true) {
                    if (alrt == '1') {
                        $('#warning-' + id).html('<a href="javascript:putAsAlert(\'post/'
                                + id + '?alert=0\', ' + id 
                                + ')" style="float: right; color: red;"><i id="waring-' 
                                + id + '" class="fa fa-warning"></i></a>');
                    } else {
                        $('#warning-' + id).html('<a href="javascript:putAsAlert(\'post/' 
                                + id + '?alert=1\', ' 
                                + id + ')" style="float: right; color: gainsboro;"><i id="waring-' 
                                + id + '" class="fa fa-warning"></i></a>');
                    }
                }
            }
        }
        );
    }

    function closeModalEdit(id) {
        toDelete.length = 0;
        closeModal(id);
    }
    function updateEdit() {
        if (toDelete.length > 0) {
            deleteToDelete(0);
        }
        $("#form-update").ajaxSubmit({url: 'secured/post/' + $('#id_id_edit').val(), type: 'post', success: function (data) {
                window.location.reload();
            }});
    }
    function deleteEdit(id) {
        toDelete.push(id);
        $('#pht-' + id).remove();
        console.log(toDelete);
    }

    function deleteToDelete(i) {
        if (i < toDelete.length) {
            $.ajax({url: '${deletePath}' + toDelete[i], type: 'post', success: function () {
                    i++;
                    deleteToDelete(i);
                }});
        }
//        window.location.reload();
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
                image += '<div id="pht-' + photos[i].split('x_x')[1] + '"><img class="img-post-sup-3" src="' + photos[i].split('x_x')[0] + '"><a href="javascript:void(0);" onclick="deleteEdit(' + photos[i].split('x_x')[1] + ')" style="position: absolute; margin-left: -15px;"><i class="fa fa-trash" style="color: #cacaca;"></i></a></div>';
            }
            image += '</div>';
            $('#photos-edit-modal').append(image);
        }
        $('#txt-edit-modal').append(post);
    }
</script>
<script type="text/javascript">
    $(document)
            .ready(
                    function () {
                        var maxLength = 320;
// 						$('.show-read-more').contents().unwrap();
                        $(".show-read-more")
                                .each(
                                        function () {
                                            var myStr = $(this).text();
                                            if ($.trim(myStr).length > maxLength) {
                                                var newStr = myStr.substring(0,
                                                        maxLength);
                                                var removedStr = myStr
                                                        .substring(
                                                                maxLength,
                                                                $.trim(myStr).length);
                                                $(this).empty().html(
                                                        newStr);
                                                $(this)
                                                        .append(
                                                                ' <a class="read-more">... read more</a>');
                                                $(this)
                                                        .append(
                                                                '<span class="more-text">'
                                                                + removedStr
                                                                + '</span>');
                                            }
                                        });
                        $(".read-more").click(function () {
                            $(this).siblings(".more-text").contents().unwrap();
                            $(this).remove();
                        });
                    });
</script>
