<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<spring:message code="date.format" var="dateFormat" />
<c:url value="/resources" var="resourcesPath" />

<style>
    .slider-img {
        height: 13vh;
    }
</style>

<div class="container lemurs-page">
    <div class="row">
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="forum-container page-profil">
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-sm-10">
                            <div class="page-title">
                                <h2 class="doc">Sliders</h2>
                            </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <button onclick="openModal('modal-edit')" class="btn btn-custom" style="margin-top: 20px; 
                                    color: white;">
                                <spring:message code="document.add"/></button>
                        </div>
                    </div>
                    <style>
                        .ajust {
                            width: 40px;
                        }
                        .delete {
                            width: 39px;
                        }
                        .red {
                            color: palevioletred;
                        }
                    </style>
                    <c:forEach items="${sliders}" var="slider">
                        <div class="cadre row">
                            <div class="bg-img col-sm-4 col-md-4 col-lg-4 slider-img" style="
                                 background-image: url(${slider.photo.breakpoints.get(slider.photo.breakpoints.size()-2)
                                                         .link});">&nbsp;</div>
                            <div class="col-sm-7 col-md-7 col-lg-7">
                                <a href="javascript:openModalEdit(${slider.id}, '${slider.title.replace("'", "\\'")}', 
                                   '${slider.text.replace("'", "\\'")}')"><h4>${slider.title}</h4>
                                </a>
                                <p>${slider.text}</p>
                            </div>
                            <div class="col-sm-1 col-md-1 col-lg-1">
                                <button class="btn btn-default ajust">
                                    <c:choose>
                                        <c:when test="${slider.activated == 1}">
                                            <i id="share-icon-${slider.id}" onclick="activation(${slider.id})" class="fa fa-times-circle-o red"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i id="share-icon-${slider.id}" onclick="activation(${slider.id})" class="fa fa-share"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                                <button class="btn btn-default" onclick="openModalEdit(${slider.id},
                                                '${slider.title.replace("'", "\\'")}', '${slider.text.replace("'", "\\'")}')">
                                    <i class="fa fa-edit"></i>
                                </button>
                                <button class="btn btn-danger delete" onclick="openDelete('modal-delete', ${slider.id})">
                                    <i class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<!--modal for edit-->
<div id="modal-edit" style="background-color: #0000004d;" class="modal edit-profil-form">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closeModal('modal-edit')">&times;</button>
                <h4 class="modal-title" id="title-modal-edits"><spring:message code="document.add"/> slider</h4>
            </div>
            <div id="modal-edit-body" class="modal-body" style="overflow-y: auto;max-height:  500px;">
                <div id="errorMdp"></div>
                <input type="hidden" id="slider-id" value="-1"> 
                <label>Title</label>
                <input class="form-control" type="text" id="slider-title"><br>
                <label>Text</label>
                <textarea class="form-control" id="slider-text"></textarea><br>
                <label>Photo</label>
                <input class="form-control" type="file" id="slider-img">
            </div>
            <input type="hidden" id="id_comment">
            <div class="modal-footer">
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('modal-edit')"><spring:message code="global.btn.cancel"/></button>
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="send()"><spring:message code="global.btn.save"/></button>                        
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
            <input type="hidden" id="id_slider_to_delete">
            <div class="modal-footer">
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDeleteModal()"><spring:message code="global.btn.cancel"/></button>
                <button style="float: right;" type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteComment()"><spring:message code="delete.modal.button"/></button>
            </div>
        </div>
    </div>                        
</div>
<script type="text/javascript"
src="<c:url value="/resources/js/app/form-edit.js"/>"></script>
<script type="text/javascript" src="${resourcesPath}/js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
                    $(document).ready(function () {
                        $('#dExpertises').multiselect({
                            maxHeight: 158,
                            buttonWidth: '100%'
                        });
                    });

                    function openDelete(modalName, id) {
                        $('#id_slider_to_delete').val(id);
                        openModal(modalName);
                    }

                    function openModalEdit(id, title, text) {
                        $('#slider-id').val(id);
                        $('#slider-title').val(title);
                        $('#slider-text').val(text);
                        openModal('modal-edit');
                    }

                    function activation(id) {
                        $('#slider-id').val(id);
                        $.ajax({
                            method: 'POST',
                            url: 'secured/slider/activation/' + id,
                            success: function (json) {
                                if (json == 1) {
                                    $('#share-icon-' + id).removeClass();
                                    $('#share-icon-' + id).addClass('fa fa-times-circle-o red');
                                } else if (json == 0) {
                                    $('#share-icon-' + id).removeClass();
                                    $('#share-icon-' + id).addClass('fa fa-share');
                                }
                            }
                        });
                    }

                    function send() {
                        var formData = new FormData();
                        formData.append('file', $('#slider-img').get(0).files[0]);
                        formData.append('text', $('#slider-text').val());
                        formData.append('title', $('#slider-title').val());
                        formData.append('activated', 0);
                        var url = '';
                        var id = $('#slider-id').val();
                        if (id == -1) {
                            url = 'secured/slider';
                        } else {
                            url = 'secured/slider/' + id;
                        }
                        $.ajax({
                            method: 'POST',
                            data: formData,
                            contentType: false,
                            processData: false,
                            url: url,
                            success: function (json) {
                                location.reload();
                            },
                            error: function (json) {
                                $('#errorMdp').html("<p style='color: red;'> " + "Le téléchargement du document est un échec. Veuiller réessayer.</p>");
                                $('#slider-id').val(-1);
                            }
                        });
                    }

                    function closeDeleteModal() {
                        $('#supression_sentence').html('');
                        closeModal('modal-delete');
                    }

                    function deleteComment() {
                        $.ajax({
                            method: 'POST',
                            url: 'secured/slider/delete/' + $('#id_slider_to_delete').val(),
                            success: function () {
                                location.reload();
                            },
                            error: function (json) {
                                $('#supression_sentence').html("<p style='color: red;'> " + "Le téléchargement du document est un échec. Veuiller réessayer.</p>");
                            }
                        });
                    }
</script>
