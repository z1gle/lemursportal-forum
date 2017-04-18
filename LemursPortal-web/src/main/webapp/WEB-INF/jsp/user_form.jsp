<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="user">
		<form:hidden path="id"/>
		<div>
			<label>Nom</label>
			<span><form:input path="nom"/></span>
		</div>
		<div>
			<label>Prenom</label>
			<span><form:input path="prenom"/></span>
		</div>
		<div>
			<label>Date de naissance</label>
			<span><form:input path="date_naissance"/></span>
		</div>
		<div>
			<label>email</label>
			<span><form:input path="email"/></span>
		</div>
		<div>
			<label>Mot de passe</label>
			<span><form:password path="password"/></span>
		</div>
		<div>
			<label>Confirmer le mot de passe</label>
			<span><form:password path="password_confirm"/></span>
		</div>
		
		<div>
			<label>Fonction</label>
			<span><form:input path="fonction"/></span>
		</div>
		<div>
			<label>Institution</label>
			<span><form:input path="institution"/></span>
		</div>
		<div>
			<label>Biographie</label>
			<span><form:textarea path="biographie" /></span>
		</div>
	</form:form>
</body>
</html>