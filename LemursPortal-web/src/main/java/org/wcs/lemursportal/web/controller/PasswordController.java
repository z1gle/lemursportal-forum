package org.wcs.lemursportal.web.controller;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.mail.MailService;
import org.wcs.lemursportal.service.user.UserInfoService;

@Controller
public class PasswordController {

  @Autowired
  private UserInfoService userService;

  @Autowired
  private MailService mailService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
	
  // Display forgotPassword page
  @RequestMapping(value = "/forgot", method = RequestMethod.GET)
  public ModelAndView displayForgotPasswordPage() {
    return new ModelAndView("forgot-passwd");
  }
    
  // Process form submission from forgotPassword page
  @RequestMapping(value = "/forgot", method = RequestMethod.POST)
  public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

    // Lookup user in database by e-mail
    UserInfo user = userService.getByEmail(userEmail);

    if (user != null) {
    	// Generate random 36-character string token for reset password 
        user.setResetToken(UUID.randomUUID().toString());

        // Save token to database
        userService.update(user);

        String appUrl = request.getScheme() + "://" + request.getServerName();
        
        // Email message
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("adb.login@gmail.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
          + "/forum/reset?token=" + user.getResetToken());
  			
        System.out.println(passwordResetEmail.toString());
        mailService.sendEmail(passwordResetEmail);

        // Add success message to view
        modelAndView.addObject("successTitle", "EMAIL SENT");
        modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        modelAndView.setViewName("forgot-passwdconf");
    } else {	
    	modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
    	modelAndView.addObject("errorStyle", "border-color: red ! important;");
    	modelAndView.setViewName("forgot-passwd");
    }
  
    return modelAndView;
  }

  // Display form to reset password
  @RequestMapping(value = "/reset", method = RequestMethod.GET)
  public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
		
	UserInfo user = userService.findUserByResetToken(token);

    if (user == null) { // Token not found in DB
    	modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
    } else { // Token found in DB
    	modelAndView.addObject("successMessage", "Enter New Password for " + user.getEmail());
    	modelAndView.addObject("emailId");
    	modelAndView.addObject("token", token);
    }

    modelAndView.setViewName("reset-passwd");
    return modelAndView;
  }

  // Process reset password form
  @RequestMapping(value = "/reset", method = RequestMethod.POST)
  public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

    // Find the user associated with the reset token
    UserInfo resetUser = userService.findUserByResetToken(requestParams.get("token"));

    if (resetUser != null) {
    	
      // Set new password	   passwordConfirm        
      resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

      // Set the reset token to null so it cannot be used again
      resetUser.setResetToken(null);

      // Save user
      userService.updatePass(resetUser);

      // In order to set a model attribute on a redirect, we must use RedirectAttributes
//      redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

      modelAndView.addObject("successMessage", "You have successfully reset your password.  You may now login.");
      modelAndView.setViewName("forgot-passwdconf");	
//      modelAndView.setViewName("redirect:login");
      return modelAndView;
    } else {
      modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
      modelAndView.setViewName("reset-passwd");	
    }
		
    return modelAndView;
  }
   
  // Going to reset page without a token redirects to login page
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
    return new ModelAndView("redirect:login");
  }
}