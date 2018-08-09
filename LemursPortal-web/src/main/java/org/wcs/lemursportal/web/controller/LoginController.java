package org.wcs.lemursportal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model)
	{
		if(error != null){
			model.addAttribute("error", "Erreur d'authentification!");
		}/*else if(logout != null){
			model.addAttribute("message", "Vous vous êtes deconnecté");
		}*/
		return "login";
	}
        
        @RequestMapping(value="/join_us", method=RequestMethod.GET)
	public String joinUs(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model)
	{
		if(error != null){
			model.addAttribute("error", "Erreur d'authentification!");
		}/*else if(logout != null){
			model.addAttribute("message", "Vous vous êtes deconnecté");
		}*/
		return "join_us";
	}
}
