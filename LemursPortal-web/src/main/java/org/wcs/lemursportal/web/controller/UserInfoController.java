/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.factory.UserInfoFactory;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.UserInfoForm;
import org.wcs.lemursportal.web.validator.UserInfoFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class UserInfoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	
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
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String signupSubmit(@ModelAttribute @Valid UserInfoForm userInfoForm, 
			HttpServletRequest request, BindingResult results, Model model)
	{
		userInfoFormValidator.validate(userInfoForm, results);
		if(results.hasErrors()){
			return "signup/user-form";
		}
		userInfoForm.setEnabled(true);//Par defaut c'est true
		userInfoForm.setUserTypeIds(new ArrayList<Integer>((Arrays.asList(UserRole.SIMPLE_USER.getId()))));
		UserInfo user = UserInfoFactory.toEntity(userInfoForm);
		userInfoService.save(user);
		LOGGER.info("INSCRIPTION REUSSI - AUTOLOGIN...");
		authenticationService.autoLogin(user.getLogin(),userInfoForm.getPassword(), request);
		return "redirect:/";
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
	}
}
