<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:url value="/resources" var="resourcesPath"/>


<div class="container lemurs-page">
    <div class="row">
        <!-- Début Ajouter Question -->
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="forum-container create-quest">

                    <div class="forum-title">
                        <h2><spring:message code="post.question.newQuestion"/></h2>
                    </div>
                    <!-- D Poser quest -->
                    <div class="cadre">
                        <c:url value="/secured/post/" var="formAction"></c:url>
                            <div class="form">
                            <form:form  class="create-quest-form" modelAttribute="post" action="${formAction}"  method="POST"  enctype="multipart/form-data"  >
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                                <div class="row">

                                    <label><spring:message code="post.question.thematique"/> <sup>*</sup></label>
                                    <form:select htmlEscape="required" path="thematiqueId">  
                                        <option disabled selected value> -- select an option -- </option>
                                        <form:options items="${listeThematique}" itemLabel="libelle" itemValue="id"/> 
                                    </form:select>
                                    <form:errors path="thematiqueId"/>
                                    <br/>
                                    <label><spring:message code="post.question.title"/> <sup>*</sup></label><span class="rouge"><form:errors path="title"/></span>
                                    <form:input path="title"/>

                                    <label><spring:message code="post.question.photo"/></label>
                                    <input type="file" name="file" class="fisie" />

                                    <label>Url youtube  <span class="rouge"><c:out value="${error_youtube}" /></span></label>  
                                        <form:input path="uriYoutube"/>

                                    <label><spring:message code="post.question.question"/> <sup>*</sup></label><span class="rouge"><form:errors path="body"/>  </span>                                
                                    <form:textarea path="body"/>
                                    <form:button value="save"><spring:message code="post.question.post"/></form:button>
                                    </div>
                            </form:form> 
                        </div>
                    </div>
                    <!-- F Poser quest -->

                </div>
            </div>
        </div>    
        <!-- Fin Ajouter Question -->           
        <script type="text/javascript">
            $(function () {
                $('input[type=file]').change(function () {
                    var val = $(this).val().toLowerCase(),
                            regex = new RegExp("(.*?)\.(jpg|jpeg|png|gif)$");

                    if (!(regex.test(val))) {
                        $(this).val('');
                        alert('Please select correct file format');
                    }
                });
            });
        </script>
    </div>
</div>

