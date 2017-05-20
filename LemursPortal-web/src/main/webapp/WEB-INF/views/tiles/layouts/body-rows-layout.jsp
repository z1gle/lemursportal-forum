<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="container lemurs-page">
	 <div class="row">
	 	<div class="col-md-9">
			<section id="site-content">
				<tiles:insertAttribute name="body" />
			</section>
		</div>
		<div class="col-md-3">
			<section id="side-content">
				<tiles:insertAttribute name="rightside" />
			</section>
		</div> 
	 </div>
</div>