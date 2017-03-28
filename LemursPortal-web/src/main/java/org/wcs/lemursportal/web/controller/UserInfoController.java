/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.factory.UserInfoFactory;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.UserInfoForm;
import org.wcs.lemursportal.web.validator.UserInfoFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Controller
public class UserInfoController {
	
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
		UserInfoForm userInfoForm = new UserInfoForm();
		model.addAttribute("userInfoForm", userInfoForm);
		return "signup/user-form";
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
	public String signupSubmit(@ModelAttribute @Valid UserInfoForm userInfoForm, 
			BindingResult results, Model model)
	{
		userInfoFormValidator.validate(userInfoForm, results);
		if(results.hasErrors()){
			return "signup/user-form";
		}
		UserInfo user = UserInfoFactory.toEntity(userInfoForm);
		userInfoService.save(user);
		authenticationService.doLogin(user.getLogin(), user.getPassword());
		return "";
	}
}
