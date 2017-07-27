<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code="date.format" var="dateFormat"/>
<div class="container lemurs-page">
    <div class="row">
<!-- F Assigantion role -->
        <div class="page-title">
            <h2 class="profil">Tableau de board: Membres</h2>
        </div>
        <div class="col-md-offset custyle">
            <table class="table table-striped custab">
            	<thead>
<!--            		<a href="#" class="btn btn-primary btn-xs pull-right">Ajouter un membre</a> -->
                <tr>
                    <th>Nom & Prénoms</th>
                    <th>Nbr questions</th>
                    <th>Nbr réponses</th>
                    <th>Membre depuis</th>
                    <th>Nouveau rôle</th>
                    <th class="text-center">Action</th>
                </tr>
            	</thead>
            	<tbody>
            	<c:forEach items="${page.content}" var="user">
            		
                        	<c:url value="/admin/roles/user" var="formActionUrl"/>
                        	<form:form modelAttribute="userRoleEditForm_${user.id}" name="userRoleEditForm" action="${formActionUrl}" method="post">
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            		<tr>
                        <td>
                        <c:out value="${user.prenom}"/> <c:out value="${user.nom}"/><br />
                        	<i>
                        		<c:forEach items="${user.roles}" var="role">
                        			<c:out value="${role.libelle}"/> 
                        		</c:forEach>
                        	</i></td>
                        <td></td>
                        <td></td>
                        <td><fmt:formatDate pattern="${dateFormat}" value="${user.dateInscription}" /></td>
                        <td>
                        		<form:hidden path="userId"/>
                        		<form:select path="roles">
                        			<form:options items="${userTypes}" itemValue="id" itemLabel="libelle"/>
                        		</form:select>	
                        </td>
                        <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span><form:button value="valider">Valider</form:button></a> 
                        <!-- <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a> --></td>
                    </tr>
                    
                        	</form:form>
            	</c:forEach>
            	</tbody>
                    
<!--                    	<tr> -->
<!--                         <td>Lucien Randria<br /><i>Expert</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Faraniaina Vavisoa<br /><i>Visiteur</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!-- 					<tr> -->
<!--                         <td>Johan Andriamanantsoa<br /><i>Expert</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Jean-Aimé Rakotonirainy<br /><i>Expert</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Tahiana Mamelasoa<br /><i>Expert</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Ginette Ravelonasoa<br /><i>Moderateur</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Vero Ramanantsoa<br /><i>Visiteur</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>Lucas Artigo<br /><i>Moderateur</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr>                     -->
<!--                     <tr> -->
<!--                         <td>Lucas Artigo<br /><i>Moderateur</i></td> -->
<!--                         <td>1564</td> -->
<!--                         <td>8975</td> -->
<!--                         <td>03.05.2016</td> -->
<!--                         <td> -->
<%--                         <form> --%>
<!--                             <select> -->
<!--                                 <option>Expert</option> -->
<!--                                 <option>Modérateur</option> -->
<!--                                 <option>Visiteur</option> -->
<!--                             </select> -->
<%--                         </form> --%>
<!--                         </td> -->
<!--                         <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span class="glyphicont"></span>Valider</a> <a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Supprimer ce membre</a></td> -->
<!--                     </tr>  -->
            </table>
            
            <!-- D Pagination -->
            <ul class="pagination">
                <li class="disabled"><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
            <!-- F Pagination -->
        </div>
        <!-- D Assigantion role -->
       </div>
      </div>