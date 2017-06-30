package org.wcs.lemursportal.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@RequestMapping
public class HomeController extends BaseController {
	
	@RequestMapping(value="/general-error")
	public String globalError(Model model){
		return "global-error";
	}
	
	@RequestMapping(value={"/", "/index", "/home", "/accueil"}, method=RequestMethod.GET)
	public String home(Model model, Authentication authentication){
		Boolean isAuthenticated = false;
		String userName = null;
		if(authentication != null){
			isAuthenticated = authentication.isAuthenticated();
			userName = authentication.getName();
		}
		model.addAttribute("isAuthenticated", isAuthenticated);
		model.addAttribute("userName", userName);
		return "home";
	}
	
	@RequestMapping(value="/secure", method=RequestMethod.GET)
	public String secure(Model model, Authentication authentication){
		Boolean isAuthenticated = false;
		String userName = null;
		if(authentication != null){
			isAuthenticated = authentication.isAuthenticated();
			userName = authentication.getName();
		}
		model.addAttribute("isAuthenticated", isAuthenticated);
		model.addAttribute("userName", userName);
		return "home";
	}
	
}
