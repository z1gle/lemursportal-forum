<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="date.format" var="dateFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!-- Début Ajouter Question -->
<div class="container lemurs-page">
    <div class="row">
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="forum-container page-profil">

                    <div class="page-title">
                        <h2 class="profil"><spring:message code="profil.edit.title"/></h2>
                    </div>
                    <!-- D Poser quest -->
                    <div class="cadre">
                        <div class="form">
                            <c:url value="/user/profil/edit" var="userFormAction"/>
                            <form:form modelAttribute="registrationForm" action="${userFormAction}" enctype="multipart/form-data" method="POST" cssClass="edit-profil-form">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="image text-center">
                                            <user:profilImage src="${registrationForm.photoProfil}" cssClass="img-responsive img-circle"/>
                                        </div>

                                        <label><spring:message code="profil.edit.change.photo"/></label>
                                        <form:input type="file" path="file" class="pdp"/>
                                        <form:errors path="file" class="error"/>
                                        <label><spring:message code="profil.role"/><sup>*</sup></label>
                                        <sec:authentication property="authorities" var="roles" scope="page" />
                                        <label style="font-weight: normal; padding-left: 20px;"> 
                                            <c:forEach var="role" items="${roles}">
                                                <c:out value="${role}/ "/> 
                                            </c:forEach>
                                        </label>
                                        <br/><br/>
                                    </div>
                                    <div class="col-md-4">
                                        <form:hidden path="id"/>
                                        <label><spring:message code="signup.name.placeholder"/><sup>*</sup></label>
                                        <form:input path="nom"/><form:errors path="nom"/>
                                        <label><spring:message code="signup.surname.placeholder"/><sup>*</sup></label>
                                        <form:input path="prenom"/><form:errors path="prenom"/>
                                        <label><spring:message code="signup.email.placeholder"/><sup>*</sup></label>
                                        <form:input path="email"/><form:errors path="email"/>
                                    </div>
                                    <div class="col-md-4">
                                        <label><spring:message code="profil.edit.institution"/></label>
                                        <form:input path="institution"/><form:errors path="institution"/>
                                        <label><spring:message code="profil.edit.postoccupe"/></label>
                                        <form:input path="postOccupe"/><form:errors path="postOccupe"/>
                                        <label><spring:message code="signup.dateofbirth.placeholder"/> (<c:out value="${dateFormat}"/>)</label>
                                        <form:input path="dateNaissance"/><form:errors path="dateNaissance"/>
                                    </div>

                                    <div class="col-md-8">
                                        <div class="forma-style">
                                            <label style="float: none !important; display: block"><spring:message code="profil.edit.biographie"/><sup>*</sup></label>
                                            <form:textarea path="biographie" rows="5"/><form:errors path="biographie"/>

                                            <label style="float: none !important; display: block"><spring:message code="profil.edit.publication"/><sup>*</sup></label>
                                            <form:textarea path="publication" rows="5"/><form:errors path="publication"/>
                                        </div>                                        
                                        <form:button value="Mettre à jour" class="right"><spring:message code="profil.edit.maj.btn"/></form:button>                                            
                                            <button type="button" class="btn-info" style="float: right; margin-right: 1px; background-color: #a36000;" onclick="openModal()"> <spring:message code="profil.btn.changePassword"/> </button>
                                        </div>
                                    </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- F Poser quest -->

                </div>
            </div>
        </div> 
    </div>
</div>   
<!-- Modal for changing the password-->
<div id="myModal" class="modal">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="closeModal()">&times;</button>
                <h4 class="modal-title">Modifier le mot de passe</h4>
            </div>
            <div class="modal-body">
                <form:form modelAttribute="changePasswordForm" action="/user/profil/password/edit" enctype="multipart/form-data" method="POST" cssClass="edit-profil-form">
                    <form:hidden path="id" id="modifPasswordId"/>
                    <label><spring:message code="profil.label.oldPassword"/><sup>*</sup></label>
                    <form:password path="password" id="modifPasswordOld" cssClass="form-control"/><form:errors path="password"/><br>
                    <label><spring:message code="profil.label.newPassword"/><sup>*</sup></label>
                    <form:password path="newPassword" id="modifPasswordNew" cssClass="form-control"/><form:errors path="newPassword"/><br>                  
                    <label><spring:message code="profil.label.confirmNewPassword"/><sup>*</sup></label>
                    <form:password path="passwordConfirm" id="modifPasswordConfirm" cssClass="form-control"/><form:errors path="passwordConfirm"/>                    
                </form:form>
                <div id="errorMdp"></div>
            </div>
            <div class="modal-footer">
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal()">Annuler</button>
                <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="sendChangePasswordForm()">Enregistrer</button>
            </div>
        </div>
    </div>
</div>

<!-- Fin Ajouter Question --> 
<script type="text/javascript" src="<c:url value="/resources/js/app/form-edit.js"/>"></script>
<script>

                    $('#biographie').summernote({
                        height: 200,
                        toolbar: [
                            ['style', ['style']],
                            ['font', ['bold', 'italic', 'underline', 'clear']],
                            ['font', ['fontsize']],
                            ['color', ['color']],
                            ['para', ['ul', 'ol', 'paragraph']],
                            ['height', ['height']],
                            ['table', ['table']],
                            ['insert', ['link', 'picture', 'video']],
                            ['view', ['codeview']],
                            ['help', ['help']]
                        ],
                        onImageUpload: function (files, editor, welEditable) {
                            sendFile(files[0], editor, welEditable);
                        }
                    });

                    $('#publication').summernote({
                        height: 200,
                        toolbar: [
                            ['style', ['style']],
                            ['font', ['bold', 'italic', 'underline', 'clear']],
                            ['font', ['fontsize']],
                            ['color', ['color']],
                            ['para', ['ul', 'ol', 'paragraph']],
                            ['height', ['height']],
                            ['table', ['table']],
                            ['insert', ['link', 'picture', 'video']],
                            ['view', ['codeview']],
                            ['help', ['help']]
                        ],
                        onImageUpload: function (files, editor, welEditable) {
                            sendFile(files[0], editor, welEditable);
                        }
                    });

</script> 
<script>
    function sendChangePasswordForm() {
        if ($('#modifPasswordNew').val() === $('#modifPasswordConfirm').val()) {
            var formData = {
                'id': parseInt($('#modifPasswordId').val()),
                'password': $('#modifPasswordOld').val(),
                'newPassword': $('#modifPasswordNew').val(),
                'passwordConfirm': $('#modifPasswordConfirm').val()
            };
            $.ajax({
                method: 'POST',
                data: formData,
                url: 'password/edit',
                success: function (json) {
                    if (json.etat) {
                        closeModal();
                    } else {
                        $('#errorMdp').html("<p style='color: red;'> " + json.message + "</p>");
                    }
                }
            });
        } else {
            $('#errorMdp').html("<p style='color: red;'> Les nouveaux mots de passes ne sont pas les mêmes</p>");
        }
    }
</script>