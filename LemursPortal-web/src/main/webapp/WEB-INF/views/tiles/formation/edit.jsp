<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
                                  
                                  <label>Ajouter un fichier</label>
                                  <input type="file" class="fichier-formation" />
                                  
                                </div>
                                
                                <div class="forma-style">
                                  
<!--                                   <label>Contenu de la formation<sup>*</sup></label> -->
                                  <textarea class="editor" name="body" rows="9">${formation.body}</textarea>
                                  </div><div>
                                  <button type="submit" class="right">Ajouter</button>
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
				onImageUpload: function (files, editor, welEditable) {
					sendFile(files[0], editor, welEditable);
				}
			});
			
</script>   
    <!-- Fin Ajouter Formation -->           
    </div>
</div>
