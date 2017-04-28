/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.factory.UserInfoFactory;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.RegistrationForm;
import org.wcs.lemursportal.web.validator.RegistrationFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private RegistrationFormValidator registrationFormValidator;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Affichage du formulaire d'inscription (nouvel utilisateur)
	 * 
	 * @return
	 */
	@RequestMapping(value={"/signup"}, method=RequestMethod.GET)
	public String signupForm(Model model){
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registrationForm", registrationForm);
		return "user/registration-form";
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
		registrationFormValidator.validate(registrationForm, results);
		if(results.hasErrors()){
			return "user/registration-form";
		}
		UserInfo user = UserInfoFactory.toEntity(registrationForm);
		try{
			userInfoService.save(user);
			LOGGER.info("INSCRIPTION REUSSI - AUTOLOGIN...");
			authenticationService.autoLogin(user.getLogin(), registrationForm.getPassword(), request);
		}catch(RegistrationException e){
			if(e.getCode() == RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION){
				results.rejectValue("login", "validation.login.exist");
				return "user/registration-form";
			}else{
				throw e;//Erreur inconnu, on laisse passer en attendant d'identifier l'erreur
			}
		}
		return "redirect:/";
	}
	/**
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user/edit")
	@PreAuthorize("hasRole('USER')")
	public String edit(Authentication authentication, Model model){
		String login = authentication.getName();
		UserInfo userInfo = userInfoService.getByLogin(login);
		RegistrationForm registrationForm = UserInfoFactory.toForm(userInfo);
		model.addAttribute("registrationForm", registrationForm);
		return "user/edit-form";
	}
	
	@PostMapping(value="/user/edit")
	public String editSubmit(Locale locale, Model model, 
			@ModelAttribute RegistrationForm registrationForm, 
			BindingResult results)
	{
		registrationFormValidator.validate(registrationForm, results);
		if(results.hasErrors()){
			return "user/registration-form";
		}
		UserInfo user = UserInfoFactory.toEntity(registrationForm);
		userInfoService.update(user);
		model.addAttribute("successMessage", messageSource.getMessage("message.edit.successMessage",new Object[]{} ,locale));
		return "redirect:/";
	}

}
