<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>

<!-- D Thematiques -->
<div class="sidebar-title">
    <c:url value="/thematique/list" var="listThematiqueUrl"/>
<!--<h2 class="thema"><a href="${listThematiqueUrl}"><spring:message code="home.thematiques"/></a></h2>-->
    <h2 class="thema"><a href="#"><spring:message code="home.thematiques"/></a></h2>
</div>
<div class="list-group people-group thematique">
<<<<<<< HEAD
    <c:forEach items="${topThematiques}" var="topThematique">
        <c:url var="postsParThematique" value="/postsParThematique/${topThematique.thematique.id}"></c:url>
        <a href="${postsParThematique}" class="list-group-item">
            <div class="media">
                <div class="pull-left" style="max-width:200px">
                    <spring:message code="document.thematique.id.${topThematique.thematique.id}"/>
                    <%--<c:out value="${topThematique.thematique.id}"/>--%>
                </div>
                <div class="pull-right">
                    <c:out value="${topThematique.nombreQuestion}"/>
                </div>
            </div>
        </a>
    </c:forEach>
=======
	<c:forEach items="${topThematiques}" var="topThematique">
	<c:url var="postsParThematique" value="/postsParThematique/${topThematique.thematique.id}"></c:url>
		<a href="${postsParThematique}" class="list-group-item">
         <div class="media">
             <div class="pull-left" style="max-width:180px">
                 <spring:message code="document.thematique.id.${topThematique.thematique.id}"/>
                 <%--<c:out value="${topThematique.thematique.id}"/>--%>
             </div>
             <div class="pull-right">
                 <c:out value="${topThematique.nombreQuestion}"/>
             </div>
         </div>
     </a>
	</c:forEach>
>>>>>>> c8a0b42aef870c449ae1414199f6d6e8244a54e2
</div>
<!-- F Thematiques -->

<!-- D Questions -->
<div class="sidebar-title">
    <h2 class="last-quest"><spring:message code="home.latestquestions"/></h2>
</div>
<div class="list-group people-group thematique">
<<<<<<< HEAD
    <c:forEach items="${lastestPosts}" var="post">
        <c:url value="/post/show/${post.id}" var="questionPageUrl"/>
        <a href="${questionPageUrl}" class="list-group-item">
            <div class="media">
                <div class="pull-left">
                    <c:out value="${post.title}"/>
                </div>
                <div class="pull-right">
                    &gt;
                </div>
            </div>
        </a>
    </c:forEach>
=======
	<c:forEach items="${lastestPosts}" var="post">
		<c:url value="/post/show/${post.id}" var="questionPageUrl"/>
		<a href="${questionPageUrl}" class="list-group-item">
         <div class="media">
             <div class="pull-left" style="max-width:180px">
                 <c:out value="${post.title}"/>
             </div>
             <div class="pull-right">
                 &gt;
             </div>
         </div>
     </a>
	</c:forEach>
>>>>>>> c8a0b42aef870c449ae1414199f6d6e8244a54e2
</div>
<!-- F Questions  -->

<!-- D Membres -->
<%--
<div class="sidebar-title">
    <h2 class="users-connect"><spring:message code="home.membreconnectes"/></h2>
</div>
<div class="membres-connectes">

<div class="morea list-group people-group">
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/>
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user2.png" alt=""/>
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user1.png" alt=""/>
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user1.png" alt="">
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br/>Visiteur
            </div>
        </div>
    </a>
    
    <a href="#" class="list-group-item">
        <div class="media">
            <div class="pull-left">
                <img class="img-circle" src="${resourcesPath}/images/user2.png" alt="">
                <div class="notif-connect"></div>
            </div>
            <div class="media-body reponse-user">
                <span>Lucas Artigo</span>
                <br>Visiteur
            </div>
        </div>
    </a>
                
</div>
</div>
<!-- F Membres -->
--%>
<!-- D Video -->
<div class="sidebar-title">
    <h2 class="vidaka"><spring:message code="home.photovideos.title"/></h2>
</div>
<div class="list-group people-group photos-videos">
    <div class="popup-gallery">
        <c:forEach items="${topPhotos}" var="topDocument">
            <a href="#">
                <%--<c:if test="${topDocument.typeId == 2}">--%>
                <%--<c:url var="videoPageUrl" value="/documents#tab-item-2"/>--%>
         <!--			<a  href="${videoPageUrl}">
                         <div class="video"></div>
                         <img src="${resourcesPath}/images/lem.png" alt=""/></a>-->
                <%--</c:if>--%>
                <c:url var="videoPageUrl" value="/documents#tab-item-1"/>
                <a  href="${videoPageUrl}">
                    <img src="${resourcesPath}/profil/blanc.png" style="background-image: url('${topDocument.breakpoints[topDocument.breakpoints.size()-2].link}'); background-size: cover;background-position: 50% 25%;" alt=""/>
                </a>
            </a>

        </c:forEach>
    </div>
</div>
<!-- F Video -->
