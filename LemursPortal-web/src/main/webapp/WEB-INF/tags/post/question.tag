<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="post" tagdir="/WEB-INF/tags/post" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="datetime.format" var="datetimeFormat" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url value="/resources" var="resourcesPath" />

<%@ attribute name="topQuestion" required="true" rtexprvalue="true"
              type="org.wcs.lemursportal.model.post.TopQuestion" description="L'url de page page courante"%>
<%-- <%@ attribute name="showLastResponseInfo" required="false" rtexprvalue="true" --%>
<!-- 	type="java.lang.Boolean" description="Un flag pour afficher ou pas les information sur la dernière reponse" %> -->

<div class="forum-item">
    <div class="row">

        <sec:authorize access="isAuthenticated()" var="isLoggedInUser"/>
        <%--    <sec:authentication property="principal" /> --%>
        <sec:authentication property="name" var="currentUserLogin"/>
        <sec:authorize access="hasAnyRole('ADMIN', 'MODERATEUR')" var="isAdminOrModerateur"/>
        <c:if test="${isLoggedInUser && (currentUserLogin eq topQuestion.question.owner.email || isAdminOrModerateur)}">
            <!-- 		<a href="#">Modifier</a> -->
            <c:url value="/post/del/${topQuestion.question.id}" var="delUrl"/>
            <spring:message var="postDeletionConfirm" code="post.confirm.deletion"/>
            <a href="javascript:confirmDeletion('${postDeletionConfirm}', '${delUrl}');"><i class="fa fa-trash-o"></i></a><a href="javascript:openEdit(${topQuestion.question.id}, ${topQuestion.question.thematique.id}, '${topQuestion.question.title.replace("'", "\\'")}', '${topQuestion.question.body.replace("'", "\\'")}', '${topQuestion.question.uriYoutube}');"><i class="fa fa-edit" style="margin-left: 2px;"></i></a>
            </c:if>
        <div class="col-md-8">
            <user:forum-profil userInfo="${topQuestion.question.owner}"/>
            <c:url value="/post/show/${topQuestion.question.id}" var="questionPageUrl"/>
            <a href="${questionPageUrl}" class="forum-item-title"><c:out value="${topQuestion.question.title}" /></a>            
            <div class="forum-sub-title">
                <p class="show-read-more">
                    <c:out value="${topQuestion.question.body}" />
                </p>
                <p class="forum-date">
                    <fmt:formatDate pattern="${datetimeFormat}"
                                    value="${topQuestion.question.creationDate}" />
                </p>
            </div>
        </div>
        <div class="col-md-1 forum-info">
            <span class="views-number"> <c:out
                    value="${topQuestion.nbReponse}" />
            </span>
            <div class="vue">
                <small>
                    commentaires
                </small>
            </div>
        </div>
        <div class="col-md-3 forum-user-info">
            <a href="#"><user:profilImage src="${topQuestion.derniereReponse.owner.photoProfil}" cssClass="img-circle"/></a>
            <div class="reponse-user">
                <a href="#"> <c:out
                        value="${topQuestion.derniereReponse.owner.nom}" /> <c:out
                        value="${topQuestion.derniereReponse.owner.prenom}" />
                </a><br />
                <fmt:formatDate pattern="${datetimeFormat}"
                                value="${topQuestion.derniereReponse.creationDate}" />
                <br />
                <c:set var="idM" value="0" />
                <c:set var="libelle" value =""/> 
                <c:forEach items="${topQuestion.derniereReponse.owner.roles}"
                           var="role">
                    <c:if test="${role.id < 10001}"> 
                        <c:set var="idR" value="${role.id}" />
                    </c:if> 
                    <c:if test="${idR > idM }">
                        <c:set var="idM" value="${idR}" />
                        <c:set var="libelle" value="${role.libelle}" />
                    </c:if>
                </c:forEach>
                <a class="btn-${fn:toLowerCase(libelle)} btn-xs" style="font-size: 10px;"><c:out value="${libelle}" /></a>
                <br />
            </div>
        </div>
        <c:if test="${topQuestion.question.documents != null}">
            <c:choose>
                <c:when test="${topQuestion.question.documents.size() == 1}">
                    <div class="col-md-12 forum-user-info img-post-parent-${topQuestion.question.id}" style="padding-left: 0px;">                        
                        <c:if test="${topQuestion.question.documents.get(0).typeId == 1}">
                            <a href="${topQuestion.question.documents.get(0).url.substring(1)}"><img class="img-post" src="${topQuestion.question.documents.get(0).url.substring(1)}" alt="${topQuestion.question.documents.get(0).filename}"></a>
                            <input type="hidden" class="list-photo-${topQuestion.question.id}" value="${topQuestion.question.documents.get(0).url.substring(1)}x_x${topQuestion.question.documents.get(0).id}">
                            </c:if>
                            <%--<c:url var="videoPageUrl" value="/files/${document.id}"/>--%>
                            <!--<a  href="${videoPageUrl}">-->
                                <!--<img style="max-width: 125px; max-height: 60px; float: left; display: none;" src="${resourcesPath}/upload/${document.filename}" alt=""></a>-->
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-12 forum-user-info img-post-parent-${topQuestion.question.id}" style="padding-left: 0px;">
                        <c:forEach items="${topQuestion.question.documents}" var="document">
                            <c:if test="${document.typeId == 1}">
                                <a href="${document.url.substring(1)}"><img class="img-post-sup-3" src="${document.url.substring(1)}" alt="${document.filename}"></a>
                                <input type="hidden" class="list-photo-${topQuestion.question.id}" value="${document.url.substring(1)}x_x${document.id}">
                                </c:if>
                            </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${not empty topQuestion.question.uriYoutube }">
            <div class="col-md-offset-3 col-md-9 forum-user-info" style="padding-left: 0px;">
                <c:url var="publicationPageUrl" value="${topQuestion.question.uriYoutube}"/>
                <a  href="${topQuestion.question.uriYoutube}"  target="_blank">
                    <img src="${resourcesPath}/images/icon-video-document.png" alt=""></a>
            </div>
        </c:if>        
    </div>
</div>
<script>
    $(document).ready(function () {
        $('.img-post-parent-${topQuestion.question.id}').magnificPopup({
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