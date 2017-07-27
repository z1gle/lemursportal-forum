<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="slider-top">
	
    
</div>

<div class="container lemurs-page">
    <div class="row">
    <!-- Début Ajouter Formation -->
        <div class="col-md-9">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container page-formation">
    
                    <div class="page-title">
                        <h2 class="formation">Ajout formation</h2>
                    </div>
                    <div class="cadre">
                   	  <div class="form">
							<form:form modelAttribute="formation" cssClass="formation-form" method="POST">
                        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<form:hidden path="id"/>
            	                <div class="row">
                                
                                <div class="">
                                  <label><!--  spring:message code="title"/-->Titre<sup>*</sup></label>
                                  	<input name="title" type="text" value="${formation.title}"/>
                                  
                                  <label><!-- spring:message code="formation.description"/-->Déscription<sup>*</sup></label>
                                  <textarea rows="5" name="description">${formation.description}</textarea>
                                  
<!--                                   <label>Ajouter un fichier</label> -->
<!--                                   <input type="file" class="fichier-formation" /> -->
<!--                                   <label>Contenu de la formation<sup>*</sup></label><br /> -->
                                </div>
                                
                                <div class="forma-style">
                                  
                                  
                                  <textarea class="editor" name="body" rows="9">${formation.body}</textarea>
                                  </div><div>
                                  <button type="submit" class="right">Enregistrer</button>
                               </div>
                            </div>
		              	</form:form>
                        </div>
                    </div>
                   
                </div>
            </div>
        </div> 
        <script type="text/javascript" src="<c:url value="/resources/js/app/form-edit.js"/>"></script>
        <script>

			$('textarea.editor').summernote({
				height: 400,
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
    <!-- Fin Ajouter Formation -->           
    </div>
</div>
