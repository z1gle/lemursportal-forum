<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/resources" var="resourcesPath"/>

<%-- <script type="text/javascript" src="${resourcesPath}/tinymce/jquery.tinymce.min.js"></script> --%>
<%-- <script type="text/javascript" src="${resourcesPath}/tinymce/tinymce.min.js"></script> --%>

<link rel="stylesheet" href="${resourcesPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${resourcesPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${resourcesPath}/css/summernote.css">

<script type="text/javascript" src="${resourcesPath}/js/summernote.min.js"></script>
<script type="text/javascript" src="${resourcesPath}/js/bootstrap.min.js"></script>

<!--  script>
<!-- 
// // Editor
// tinymce.init({
//   selector: 'textarea.editor',
//   height: 200,
//   menubar: false,
//   plugins: [
//     'autolink lists link image print anchor',
//     'visualblocks code',
//     'insertdatetime media table contextmenu paste code'
//   ],
//   toolbar: 'insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
// });part4.png

</script-->

