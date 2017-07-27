<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="slider" />
<div class="container lemurs-page">
	 <div class="row">
	 	<div class="col-md-9">
			<tiles:insertAttribute name="left" />
		</div>
		<div class="col-md-3">
			<tiles:insertAttribute name="right" />
		</div> 
	 </div>
</div>