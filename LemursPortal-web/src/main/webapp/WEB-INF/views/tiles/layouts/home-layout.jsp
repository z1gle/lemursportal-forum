<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="icon" href="${resourcesPath}/favicon.ico" type="image/x-icon">
        <title><tiles:getAsString name="title" /></title>
        <link href="${resourcesPath}/css/styles.css" rel="stylesheet"/>
        <link href="${resourcesPath}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${resourcesPath}/css/img_modal.css" rel="stylesheet"/>
        <link href="${resourcesPath}/css/magnific-popup.css" rel="stylesheet">
        <script src="${resourcesPath}/js/js_modal.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
        <script src="${resourcesPath}/js/script.js"></script>
        <script src="${resourcesPath}/bootstrap/js/bootstrap.js"></script>
        <script src="${resourcesPath}/js/jquery.showmore.js"></script>
        <script src="${resourcesPath}/js/jquery.magnific-popup.min.js"></script>
    </head>

    <body class="h">
        <!--modal edit-->
        <div id="modal-edit" class="" style="position: fixed; z-index: 1031;margin: auto;width: 100%;height: 100vh;display: none!important;background-color: #0000008f;">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="closeModalEdit('modal-edit')">&times;</button>
                        <h4 class="modal-title">Edit</h4>
                    </div>
                    <div id="modal-edit-post-content" class="modal-body" style="overflow-y: auto;max-height:  500px;">
                    </div>
                    <div class="modal-footer">
                        <button style="float: right;" type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModalEdit('modal-edit')">Annuler</button>                        
                        <div id="performe_delete"></div>
                        <button style="float: right;" type="button" class="btn btn-success" data-dismiss="modal" onclick="updateEdit()">OK</button>                        
                        <div id="performe_delete"></div>
                    </div>
                </div>
            </div>                        
        </div>
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
        <tiles:insertAttribute name="body" />
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
    </body>
    <!--script src="${resourcesPath}/bootstrap/js/bootstrap.min.js"></script-->
    <script>
        jQuery(document).ready(function () {
            jQuery(".morea").showmore({

                childElement: "a",
                visible: 5,
                showMoreText: "<span class='more'>...</span>",
                showLessText: "<span class='more'>-</span>"

            });

        });
    </script>
</html>
