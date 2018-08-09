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
                <div class="col-md-4 forum-info">
                    <c:if test="${post.documentId > 0}">
                        <c:url var="videoPageUrl" value="/files/${post.documentId}"/>
                        <a  href="${videoPageUrl}">
                            <img src="${resourcesPath}/images/icon-audio.png" alt=""></a>
                        </c:if>
                        <c:if test="${not empty post.uriYoutube }">
                            <c:url var="publicationPageUrl" value="${post.uriYoutube}"/>
                        <a  href="${publicationPageUrl}"  target="_blank">
                            <img src="${resourcesPath}/images/icon-video-document.png" alt=""></a>
                        </c:if>
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

                        <div class="panel-collapse collapse in col-md-9" id="collapseOne">
                            <div class="media-body">
                                <c:out value="${child.body}" escapeXml="true" />
                            </div>
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

