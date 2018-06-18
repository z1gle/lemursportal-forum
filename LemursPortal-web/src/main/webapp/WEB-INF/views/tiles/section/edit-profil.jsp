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
<!-- Début Ajouter Question -->
<div class="container lemurs-page">
	<div class="row">
		<div class="full-width">
			<div class="wrapper wrapper-content animated fadeInRight">

				<div class="forum-container page-profil">

					<div class="page-title">
						<h2 class="profil"><spring:message code="profil.edit.title" /></h2>
					</div>
					<!-- D Poser quest -->
					<div class="cadre">
						<div class="form">
							<c:url value="/user/profil/edit" var="userFormAction" />
							<form:form modelAttribute="registrationForm" action="${userFormAction}" enctype="multipart/form-data" method="POST" cssClass="edit-profil-form">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="row">
									<div class="col-md-4">
										<div class="image text-center">
											<user:profilImage src="${registrationForm.photoProfil}" cssClass="img-responsive img-circle" />
										</div>

										<label><spring:message code="profil.edit.change.photo" /></label>
										<form:input type="file" path="file" class="pdp" />
										<form:errors path="file" class="error" />
										<div style="margin-bottom: 15px; text-align: left !important">
											<b><spring:message code="profil.role" /></b>
											<sec:authentication property="authorities" var="roles"
												scope="page" />
											<span
												style="font-weight: normal; padding-left: 20px; margin-bottom: 15px">
												<c:forEach var="role" items="${roles}">
													<c:out value="${fn:replace(role, 'ROLE_', '')}/ " />
												</c:forEach>
											</span>
										</div>
										
											<script type="text/javascript">
												var room = 1;
												function de_fields() {

													room++;
													var objTo = document
															.getElementById('dm_fields')
													var divtest = document
															.createElement("div");
													divtest.setAttribute(
															"class",
															"form-group removeclass"
																	+ room);
													var rdiv = 'removeclass'
															+ room;
													divtest.innerHTML = '<div class="form-group" style="height: 40px"><div class="input-group"><select id="thematique" class="" name="thematiqueName" style=" width: 100% !important;"><option disabled="disabled" selected="selected"></option><c:forEach var="thematique" items="${listeThematique}"><option><c:out value="${thematique.libelle}" /></option></c:forEach></select><div class="input-group-btn"> <button style="margin-top: 1px;background-color: #d9534f;border-color: #d43f3a;" class="btn btn-danger" type="button" onclick="remove_de_fields('+ room +');"> <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> </button></div></div></div></div><div class="clear"></div>';

													objTo.appendChild(divtest)
												}
												function remove_de_fields(
														rid) {
													$('.removeclass' + rid)
															.remove();
												}
											</script>
<!-- 										<label>Domaine d'expertise<sup>*</sup></label> -->
<!-- 										<div style="margin-bottom: 15px; fon-size: bold; text-align: left !important"> -->
<!-- 											<b>Domaine d'expertise</b> -->
<!-- 										</div> -->
<!-- 										<div id="dm_fields"></div> -->
<!-- 										<div class="form-group" style="height: 40px"> -->
<!-- 											<div class="input-group"> -->
<!-- 												<select id="thematique" class="" name="thematiqueName" -->
<!-- 													style="width: 100% !important;"> -->
<!-- 													<option disabled="disabled" selected="selected"> -->
<!-- 														Choose a topic -->
<!-- 														spring:message code="article-edit.category" / -->
<!-- 													</option> -->
<%-- 													<c:forEach var="thematique" items="${listeThematique}"> --%>
<%-- 														<option><c:out value="${thematique.libelle}" /></option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 												<div class="input-group-btn"> -->
<!-- 													<button class="btn btn-success" type="button" -->
<!-- 														style="margin-top: 1px;" onclick="de_fields();"> -->
<!-- 														<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> -->
<!-- 													</button> -->
<!-- 												</div> -->
<!-- 											</div> -->

										<sec:authorize access="hasRole('EXPERT')">
											<label>Choisir un thématique <sup>*</sup></label>
											<div>
												<form:select path="dExpertises">
													<form:options items="${listeThematique}"
														itemLabel="libelle" itemValue="id" />
												</form:select>
											</div>
										</sec:authorize>
										<br />
										<br />
									</div>
									<div class="col-md-4">
										<form:hidden path="id" />
										<label><spring:message code="signup.name.placeholder" /><sup>*</sup></label>
										<form:input path="nom" />
										<form:errors path="nom" />
										<label><spring:message
												code="signup.surname.placeholder" /><sup>*</sup></label>
										<form:input path="prenom" />
										<form:errors path="prenom" />
										<label><spring:message code="signup.email.placeholder" /><sup>*</sup></label>
										<form:input path="email" />
										<form:errors path="email" />
									</div>
									<div class="col-md-4">
										<label><spring:message code="profil.edit.institution" /></label>
										<form:input path="institution" />
										<form:errors path="institution" />
										<label><spring:message code="profil.edit.postoccupe" /></label>
										<form:input path="postOccupe" />
										<form:errors path="postOccupe" />
										<label><spring:message
												code="signup.dateofbirth.placeholder" /> (<c:out
												value="${dateFormat}" />)</label>
										<form:input path="dateNaissance" />
										<form:errors path="dateNaissance" />
									</div>

									<div class="col-md-8">
										<div class="forma-style">
											<label style="float: none !important; display: block"><spring:message
													code="profil.edit.biographie" /><sup>*</sup></label>
											<form:textarea path="biographie" rows="5" />
											<form:errors path="biographie" />
											
											<sec:authorize access="hasRole('EXPERT')">
												<label style="float: none !important; display: block"><spring:message
														code="profil.edit.publication" /><sup>*</sup></label>
												<form:textarea path="publication" rows="5" />
												<form:errors path="publication" />
											</sec:authorize>
											
										</div>
										<form:button value="Mettre à jour" class="right">
											<spring:message code="profil.edit.maj.btn" />
										</form:button>
										<button type="button" class="btn-info" style="float: right; margin-right: 1px; background-color: #a36000;" onclick="openModal('myModal')"> <spring:message code="profil.btn.changePassword"/> </button>
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
                <button type="button" class="close" onclick="closeModal('myModal')">&times;</button>
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
                <button style="/*float: right;*/" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal('myModal')">Annuler</button>
                <button style="/*float: right;*/" type="button" class="btn btn-default" data-dismiss="modal" onclick="sendChangePasswordForm()">Enregistrer</button>
            </div>
        </div>
    </div>
</div>

<!-- Fin Ajouter Question -->
<script type="text/javascript"
	src="<c:url value="/resources/js/app/form-edit.js"/>"></script>
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
                        closeModal('myModal');
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
