/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.dto.user.UserRegistrationForm;
import org.wcs.lemursportal.factory.UserInfoFactory;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.authentication.AuthenticationService;
import org.wcs.lemursportal.service.exception.UserAlreadyExistAuthenticationException;
import org.wcs.lemursportal.service.util.SecurityUtil;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.RegistrationForm;
import org.wcs.lemursportal.web.validator.RegistrationFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class RegistrationController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private RegistrationFormValidator registrationFormValidator;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired ServletContext context; 
	
	@Autowired
	private MessageSource messageSource;
	
//	@Autowired
//    private UserInfoService userService;

	/*
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "User Registration Form");
        model.setViewName("registration");
        return model;
    }*/
	
//    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public @ResponseBody String registerUser(@RequestBody UserRegistrationForm registrationForm) throws UserAlreadyExistAuthenticationException {
//
//        if (registrationForm.getUserId() == null) {
//            registrationForm.setUserId(registrationForm.getUserId());
//        }
//
//        LocalUser localUser = (LocalUser) userService.registerNewUser(registrationForm);
//
//       return "success";
//
//    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        LOGGER.debug("Redirecting request to registration page.");

        return "redirect:/register";
    }
    
    /**
     * Renders the registration page.
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        LOGGER.debug("Rendering registration page.");

        Connection<?> connection = ProviderSignInUtils.getConnection(request);

        RegistrationForm registration = createRegistrationDTO(connection);
        LOGGER.debug("Rendering registration form with information: {}", registration);

        model.addAttribute("registrationForm", registration);

        return "registration";
    }
    
    /**
     * Creates the form object used in the registration form.
     * @param connection
     * @return  If a user is signing in by using a social provider, this method returns a form
     *          object populated by the values given by the provider. Otherwise this method returns
     *          an empty form object (normal form registration).
     */
    private RegistrationForm createRegistrationDTO(Connection<?> connection) {
        RegistrationForm dto = new RegistrationForm();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setEmail(socialMediaProfile.getEmail());
            dto.setNom(socialMediaProfile.getFirstName());
            dto.setPrenom(socialMediaProfile.getLastName());
            dto.setPhotoProfil(connection.getImageUrl());
            
            dto.setSocialProvider(SecurityUtil.toSocialProvider(connection.getKey().getProviderId().toUpperCase()));
        }

        return dto;
    }
    
    /**
     * Processes the form submissions of the registration form.
     */
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("registrationForm") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws UserAlreadyExistAuthenticationException {
        LOGGER.debug("Registering user account with information: {}", userAccountData);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
            return "registration";
        }

        LOGGER.debug("No validation errors found. Continuing registration process.");
        UserRegistrationForm userForm = UserRegistrationForm.getBuilder()
                .addUserId(userAccountData.getEmail())
                .addNom(userAccountData.getNom())
                .addDateNaissance(userAccountData.getDateNaissance())
                .addPrenom(userAccountData.getPrenom())
                .addEmail(userAccountData.getEmail())
                .addPassword(userAccountData.getPassword())
                .addSocialProvider(userAccountData.getSocialProvider())
                .addImageUrl(userAccountData.getPhotoProfil())
        .build();

        LOGGER.debug("#### info : {} + {} + {}", userForm.getEmail(), userForm.getUserId(), userForm.getPrenom());
        
        UserInfo registered = createUserAccount(userForm, result);

        //If email address was already found from the database, render the form view.
        if (registered == null) {
            LOGGER.debug("An email address was found from the database. Rendering form view.");
            return "registration";
        }

        LOGGER.debug("Registered user account with information: {}", registered);

        //Logs the user in.
        SecurityUtil.authenticateUser(registered);
        LOGGER.debug("User {} has been signed in", registered);
        
        //stores the connection to the UserConnection table
        ProviderSignInUtils.handlePostSignUp(userForm.getEmail(), request);

        return "redirect:/";
    }
    
    /**
     * creatuion d'un compte utilisateur.
     * verification si l'addresse email est deja utilisee
     */
    private UserInfo createUserAccount(UserRegistrationForm userAccountData, BindingResult result) {
        LOGGER.debug("Creating user account with information: {}", userAccountData);
        UserInfo registered = null;

        try {
            registered = userInfoService.registerNewUser(userAccountData);
        }
        catch (Exception ex) {
        	ex.printStackTrace();
            LOGGER.debug("An email address: {} exists.", userAccountData.getEmail());
            addFieldError(
                    "registrationForm",
                    "email",
                    userAccountData.getEmail(),
                    "The email address you have used is already registered",
                    result);
        }

        return registered;
    }
	
//	/**
//	 * Affichage du formulaire d'inscription (nouvel utilisateur)
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value={"/signup"}, method=RequestMethod.GET)
//	public String signupForm(Model model){
//		RegistrationForm registrationForm = new RegistrationForm();
//		model.addAttribute("registrationForm", registrationForm);
//		return "registration";
//	}	
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
//	@RequestMapping(value="/signup", method=RequestMethod.POST)
//	public String signupSubmit(HttpServletRequest request, Model model, 
//			@ModelAttribute RegistrationForm registrationForm, 
//			BindingResult results)
//	{
//		registrationFormValidator.validate(registrationForm, results);
//		if(results.hasErrors()){
//			return "registration";
//		}
//		UserInfo user = UserInfoFactory.toEntity(registrationForm);
//		try{
//			userInfoService.save(user);
//			LOGGER.info("INSCRIPTION REUSSI - AUTOLOGIN...");
//			authenticationService.autoLogin(user.getLogin(), registrationForm.getPassword(), request);
//		}catch(RegistrationException e){
//			if(e.getCode() == RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION){
//				results.rejectValue("login", "validation.login.exist");
//				return "registration";
//			}else{
//				throw e;//Erreur inconnu, on laisse passer en attendant d'identifier l'erreur
//			}
//		}
//		return "redirect:/";
//	}
	/**
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user/profil")
	public String viewProfil(Authentication authentication, Model model){
		String email = authentication.getName();
		UserInfo userInfo = userInfoService.getByEmail(email);
		model.addAttribute("userInfo", userInfo);
		return "profil.view.page";
	}
	/**
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user/profil/edit")
	public String editProfil(Authentication authentication, Model model){
		String email = authentication.getName();
		UserInfo userInfo = userInfoService.getByEmail(email);
		RegistrationForm registrationForm = UserInfoFactory.toForm(userInfo);
		model.addAttribute("registrationForm", registrationForm);
		return "profil.edit.page";
	}
	
	@PostMapping(value="/user/profil/edit")
	public String editSubmit(Locale locale, Model model, 
			@ModelAttribute RegistrationForm registrationForm, 
			BindingResult results) throws IOException 
	{
		registrationFormValidator.validate(registrationForm, results);
		if(results.hasErrors()){
			return "profil.edit.page";
		}
		MultipartFile multipartFile = registrationForm.getFile();
		if(multipartFile != null && !registrationForm.getFile().getOriginalFilename().isEmpty()){

			String fileUploadLocation = context.getRealPath("/resources/") + USER_PROFIL_IMAGE_RESOURCE_PATH;
			FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUploadLocation + multipartFile.getOriginalFilename()));
			String imagePath = USER_PROFIL_IMAGE_RESOURCE_PATH.endsWith("/") ? USER_PROFIL_IMAGE_RESOURCE_PATH : USER_PROFIL_IMAGE_RESOURCE_PATH +"/"; 
			registrationForm.setPhotoProfil(imagePath + multipartFile.getOriginalFilename());
//            String fileName = multipartFile.getOriginalFilename();
//            model.addAttribute("fileName", fileName);
		}
		UserInfo user = UserInfoFactory.toEntity(registrationForm);
		userInfoService.update(user);
		model.addAttribute("successMessage", messageSource.getMessage("message.edit.successMessage",new Object[]{} ,locale));
		return "redirect:/user/profil";
	}
	
	private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        LOGGER.debug(
                "Field error: {} field: {} with error code: {}",
                objectName,
                fieldName,
                errorCode
        );
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
        LOGGER.debug("Field error: {} to binding result: {}", error, result);
    }

}
