<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="wrapper wrapper-content animated fadeInRight">
    			<!-- D Profil -->
                <div class="forum-container page-profil">
                        <div class="row">
                        	<div class="page-title">
                        		<h2 class="profil">Profil</h2>
                            </div>
                            <div class="col-xs-12 col-md-4 col-lg-3">
                                <div class="profil-info">
                                
                                	<!-- D Envoi message Modal -->
                                    <div id="envoi-msg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                        <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">Envoyer un message</h4>
                                        </div>
                                        <div class="modal-body">
                                          <div class="form">
                                            <form class="send-msg-form">
                                                <div class="row">
                                                      <label>Objet du message</label>
                                                      <input type="text" />
                                                      
                                                      <label>Votre message<sup>*</sup></label>
                                                      <textarea></textarea>
                                                      
                                                      <button class="right">Envoyer</button>
                                                </div>
                                            </form>
                                            </div>
                                        </div>
                                        </div>
                                        </div>
                                    </div>
                                    <!-- D Envoi message Modal -->
                                    
                                
                                    <div class="image text-center">
                                        <img src="images/user-profil.png" alt="#" class="img-responsive img-circle">
                                        <!-- D S'affiche si un autre utilisateur visualise son profil -->
                                        <a href="#envoi-msg" class="send" role="button" class="btn btn-custom" data-toggle="modal" title="Envoyer un message">
                                                <i class="fa fa-envelope"></i>
                                        </a>
                                        <!-- F S'affiche si un autre utilisateur visualise son profil -->
                                    </div>
                                    <div class="box">
                                        <div class="name"><strong>Vero Ramanantsoa</strong></div>
                                        <div class="info">
                                            <span><span>Rôle:</span><br />Expert</span>
                                            <span><span>Institution:</span><br />UNICEF</span>
                                            <span><span>Poste occupé:</span><br />Technicien</span>
                                            <span><span>Email:</span><br /><a href="#" title="#">vero.ram@gmail.com</a></span>
                                            <span><span>Inscrit(e) le:</span><br />03.05.2017</span>
                                            <!-- D S'affiche si l'utilisateur lui-même est connecté -->
                                            <a href="#" class="btn left">Modifier mon profil</a>
                                            <!-- F S'affiche si l'utilisateur lui-même est connecté -->
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                    
                            <div class="col-xs-12 col-md-8 col-lg-9">
                                <div class="box">
                                    <h2 class="boxTitle">Biographie</h2>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel sapien at risus commodo varius vel ut sapien. Aenean sodales non ex et venenatis. In hac habitasse platea dictumst. Donec vitae tellus non erat dapibus hendrerit. Class aptent taciti bold text lorem ipsum per conubia nostra, per inceptos himenaeos. Sed ornare vestibulum aliquet.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel sapien at risus commodo varius vel ut sapien. Aenean sodales non ex et venenatis. In hac habitasse platea dictumst. Donec vitae tellus non erat dapibus hendrerit. Class aptent taciti bold text lorem ipsum per conubia nostra, per inceptos himenaeos. Sed ornare vestibulum aliquet.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel sapien at risus commodo varius vel ut sapien. Aenean sodales non ex et venenatis. In hac habitasse platea dictumst. Donec vitae tellus non erat dapibus hendrerit. Class aptent taciti bold text lorem ipsum per conubia nostra, per inceptos himenaeos. Sed ornare vestibulum aliquet.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel sapien at risus commodo varius vel ut sapien. Aenean sodales non ex et venenatis. In hac habitasse platea dictumst. Donec vitae tellus non erat dapibus hendrerit. Class aptent taciti bold text lorem ipsum per conubia nostra, per inceptos himenaeos. Sed ornare vestibulum aliquet.</p>
                                </div>
                                
                            </div>
                        </div>
                </div>
                <!-- F Profil -->
            </div>