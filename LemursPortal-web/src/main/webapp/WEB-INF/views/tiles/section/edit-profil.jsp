<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Début Ajouter Question -->
        <div class="full-width">
            <div class="wrapper wrapper-content animated fadeInRight">
    
                <div class="forum-container page-profil">
    
                    <div class="page-title">
                        <h2 class="profil">Edition Profil</h2>
                    </div>
    				<!-- D Poser quest -->
                    <div class="cadre">
                   	  <div class="form">
                        	<form class="edit-profil-form">
                            <div class="row">
                            	<div class="col-md-4">
                                  <div class="image text-center">
                                    <img src="images/user-profil.png" alt="#" class="img-responsive img-circle">
                                  </div>
                                
                                  <label>Changer mon photo de profil</label>
                                  <input type="file" class="pdp" />
                                  
                                </div>
                                
                                <div class="col-md-4">
                                  <label>Nom<sup>*</sup></label>
                                  <input type="text" />
                                  
                                  <label>Prénoms<sup>*</sup></label>
                                  <input type="text" />
                                  
                                  <label>Date de naissance (jj/mm/aaaa)</label>
                                  <input type="text" />
                                  
                                  <label>Adresse email<sup>*</sup></label>
                                  <input type="email" />
                                  
                                  <label>Ancien mot de passe</label>
                                  <input type="password" />
                                  
                                  <label>Nouveau mot de passe</label>
                                  <input type="password" />
                                  
                                </div>
                                
                                <div class="col-md-4">
                                  <label>Rôle<sup>*</sup></label>
                                  <select disabled="disabled">
                                    <option>Rôle</option>
                                  	<option>Expert</option>
                                    <option>Modérateur</option>
                                    <option>Visiteur</option>
                                  </select>
                                  <label>Institution</label>
                                  <input type="text" />
                                  
	                              <label>Poste occupé</label>
                                  <input type="text" />
                                  
                                  <label>Biographie<sup>*</sup></label>
                                  <textarea rows="9"></textarea>
                                  
                                  <button class="right">Mettre à jour</button>
                               </div>
                            </div>
                            </form>
                        </div>
                    </div>
                    <!-- F Poser quest -->
                   
                </div>
            </div>
        </div>    
    <!-- Fin Ajouter Question --> 