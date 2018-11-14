<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code="datetime.format" var="datetimeFormat"/>
<c:url value="/resources" var="resourcesPath"/>

<div class="container">
    <div class="row">
        <div class="col-md-5">
        	<div class="row">
        	
                <div class="col-xs-2">
                    <img src="${resourcesPath}/images/logo-lemurs-blanc.png" style="width: 48px" alt="">
                </div>
                
                <div class="col-xs-4">
                    <ul  style="padding-left: 20px;">
                    	<c:url value="/" var="homePage"/>
                    	<li><a href="${homePage}"><spring:message code="home.menu.accueil"/></a></li>
                        <li><a href="${homePage}"><spring:message code="home.menu.questions"/></a></li>
                        <c:url value="/documents" var="documentsUrl"/>
                        <li><a href="${documentsUrl}"><spring:message code="home.menu.documents"/></a></li>
                        <c:url value="/experts" var="expertsUrl"/>
                        <li><a href="${expertsUrl}"><spring:message code="home.menu.experts"/></a></li>
                    </ul>
                </div>
                <div class="col-xs-4">
                    <ul style="padding-left: 20px;">
                    	<c:url value="/formation/" var="formationsUrl"/>
                    	<li><a href="${formationsUrl}"><spring:message code="home.menu.formations"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.aide"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.mentionslegales"/></a></li>
                        <li><a href="#"><spring:message code="home.menu.contact"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div id="partenaire" class="col-md-4">
        	<div class="row">
                <div class="col-xs-2">
                	<c:url value="http://www.fapbm.org/" var="fapbmUrl"/>
                    <a href="${fapbmUrl}" target="_blank"><img src="${resourcesPath}/images/part1.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                	<c:url value="http://www.gerp.mg" var="gerpUrl"/>
                    <a href="${gerpUrl}" target="_blank"><img  src="${resourcesPath}/images/part2.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                	<c:url value="http://www.primate-sg.org/" var="primatesUrl"/>
                    <a href="${primatesUrl}" target="_blank"><img  src="${resourcesPath}/images/part3.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                	<c:url value="http://madagascar.wcs.org" var="wcsUrl"/>
                    <a href="${wcsUrl}" target="_blank"><img src="${resourcesPath}/images/part4.png" alt=""/></a>
                </div>
                <div class="col-xs-2">
                	<c:url value="http://data.rebioma.net/" var="rebiomaUrl"/>
                    <a href="${rebiomaUrl}" target="_blank"><img src="${resourcesPath}/images/part5.png" alt=""/></a>
                </div>
            </div>
        </div>
        
				<div class="col-md-3 animated fadeInUp" data-wow-delay="0.8s">
					<a href="http://jrsbiodiversity.org/" target="_blank"><img
						src="http://jrsbiodiversity.org/wp-content/themes/zero/images/jrs-logo.svg"
						style="border-radius: 4%; margin-top: 3%;padding:2%;"></a>
				</div>
 	</div>
<!--  	<div class="row text-center" style="border-bottom: solid 1px; border-top: solid 1px;border-color: #84bc00; margin: 30px 15px; padding-bottom: 30px;"> -->
<!--  					<div class="footer-content"> --> 
<!-- 						<div style="font-size: 16px;padding: 15px;font-weight: bold;color:white">PARTNERS</div> -->
<!-- 						<div class="footer-partner"> -->
<!-- 							<ul> -->
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_ADD.jpg"></a></li> --%>
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_Aspinall.jpg"></a></li> --%>
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_DPZ.jpg"></a></li> --%>
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_DREEF-Atsinanana.jpg"></a></li> --%>
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_ISSEDD.png"></a></li> --%>
<!-- 								<li class=""><a href="#" target="_blank"><img -->
<%-- 								src="${resourcesPath}/logos/logo_Valbio.jpg"></a></li> --%>
<!-- 							</ul> -->
<!-- 						</div> -->

<!--  					</div> -->
<!-- 				</div> -->
</div> 

<div class="footer-bottom">
			<div class="container" id="fter">
				<div class="row">
					<div class="col-md-12">
						<ul class="cash-out pull-left">
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_meef-48.jpg" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/05_2016-Logo-48.jpg" alt="">	
								</a>
							</li>
							<li>
									<img src="${resourcesPath}/logos/blank.png" alt="">	
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_ADD-48.jpg" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_Aspinall-48.jpg" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_DPZ-48.jpg" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_ISSEDD-48.jpg" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="${resourcesPath}/logos/logo_Valbio-48.jpg" alt="">	
								</a>
							</li>
                                                        <li>
								<a href="#">
									<img src="https://www.lemursportal.org/resources/logos/MV_logo.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="/resources/logos/MV_logo.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="/resources/logos/PGF_logo.png" alt="">	
								</a>
							</li>
						</ul>
						<p class="copyright-text pull-right" style="color:white; font-size:12px; margin: 12px 0 10px;"> Copyright &copy; - Madagascar Lemurs Portal 2017 <!-- All Rights Reserved--></p>
					</div>	<!-- End Of /.col-md-12 -->	
				</div>	<!-- End Of /.row -->	
			</div>	<!-- End Of /.container -->	
		</div>
		
<!-- <div class="copy">Copyright &copy; - Lemurs Portal 2017</div>  -->

