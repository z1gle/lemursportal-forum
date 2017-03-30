/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.factory.UserInfoFactory;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.RegistrationForm;
import org.wcs.lemursportal.web.validator.UserInfoFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private UserInfoFormValidator userInfoFormValidator;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * Affichage du formulaire d'inscription (nouvel utilisateur)
	 * 
	 * @return
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signupForm(Model model){
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registrationForm", registrationForm);
		return "signup/registration-form";
	}
	
	/**
	 * Validation du formulaire d'inscription de l'utilisateur
	 * Si le formulaire est valide, on appelle de service d'inscription
	 * Si l'inscription a reussi, l'utilisateur sera redirigé vers la page d'accueil tout en etant connecté automatiquement.
	 * Si le formulaire n'est pas valide, on retourne à la page d'inscription avec le formulaire pre-rempli et les message d'erreur
	 * 
	 * @param userInfoForm
	 * @param results
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupSubmit(HttpServletRequest request, Model model, 
			@ModelAttribute RegistrationForm registrationForm, 
			BindingResult results)
	{
		userInfoFormValidator.validate(registrationForm, results);
		if(results.hasErrors()){
			return "signup/registration-form";
		}
		try{
			UserInfo user = UserInfoFactory.toEntity(registrationForm);
			userInfoService.save(user);
			LOGGER.info("INSCRIPTION REUSSI - AUTOLOGIN...");
			authenticationService.autoLogin(user.getLogin(), registrationForm.getPassword(), request);
		}catch(RegistrationException e){
			if(e.getCode() == RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION){
				results.rejectValue("login", "validation.login.exist");
			}else{
				throw e;//Erreur inconnu, on laisse passer en attendant d'identifier l'erreur
			}
		}
		return "redirect:/";
	}
}
