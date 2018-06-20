package org.wcs.lemursportal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.wcs.lemursportal.model.Formation;
import org.wcs.lemursportal.repository.post.FormationRepository;
import org.wcs.lemursportal.service.post.FormationService;
import org.wcs.lemursportal.web.constants.URL;
import org.wcs.lemursportal.web.constants.View;


/**
 * @author z
 *
 */
@Controller
@RequestMapping("/formation")
public class FormationController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormationController.class);
	
	@Autowired 
	private FormationRepository formationRepository;
	
	@Autowired 
	private FormationService formationService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String formation(Model model, Authentication authentication){
		Boolean isAuthenticated = false;
		String userName = null;
		if(authentication != null){
			isAuthenticated = authentication.isAuthenticated();
			userName = authentication.getName();
		}
		model.addAttribute("isAuthenticated", isAuthenticated);
		model.addAttribute("userName", userName);
		
		Page<Formation> page = formationRepository.getLastestFormations(new PageRequest(0, 10));
		model.addAttribute("formations", page.getContent());
		
		return "formation";
	}
	
	/**
	 * 
	 */
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = URL.EDIT_FORMATION)
	public String editFormationPage(Model model, @PathVariable("id") Long formationId,
			HttpSession session, HttpServletRequest request) {
		
		Formation formationToEdit = formationService.getFormation(formationId);
		model.addAttribute("formation", formationToEdit);
		return View.EDIT_FORMATION;
	}
	
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = URL.EDIT_FORMATION)
	public String editFormationSubmit(Authentication authentication, Model model, @Valid @ModelAttribute("formation") Formation formation,
			BindingResult result, HttpSession session, SessionStatus status) {

//		if (result.hasErrors()) {
//			return View.EDIT_FORMATION;
//		}
		LOGGER.debug("dfjdmqkjdlmkjmfqk0" + formation.getBody());
		formationService.update(formation, authentication.getName());

		return "redirect:/formation/" + formation.getId();
	}
	
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = URL.ADD_FORMATION)
	public String addFormationSubmit(Authentication authentication, Model model, @Valid @ModelAttribute("formation") Formation formation,
			BindingResult result, HttpSession session, SessionStatus status) {
//		if(result.hasErrors()){
//			return "forward:" + View.VIEW_FORMATION + View.NEW_FORMATION;
//		}
		formationService.save(formation, authentication.getName());
		return "redirect:/formation/" + formation.getId();
	}
	
	//	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = URL.SHOW_FORMATION)
	public String viewFormationPage(Authentication authentication, Model model, @PathVariable("id") Long formationId,
			HttpSession session, HttpServletRequest request, HttpServletResponse resp) {
		String email = authentication==null?null:authentication.getName();
		
		Formation currentFormation = formationService.getFormation(formationId, email);
		
		model.addAttribute("formation", currentFormation);

		return View.VIEW_FORMATION;
	}
	
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = URL.ADD_FORMATION)
	public String addFormationPage(Model model) {
		model.addAttribute("formation", populateFormation());
		
		return View.EDIT_FORMATION;
	}
	
	/**
	 * Delete formation
	 */
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = URL.DELETE_FORMATION)
	public ModelAndView deleteFormation(Authentication authentication, @PathVariable("id") Long id,
			HttpSession session, SessionStatus status, HttpServletRequest request) {

		formationService.deleteById(id, authentication.getName());

		return new ModelAndView(View.SUCCESS)
				.addObject("messageProperty", "Supprimée")
				.addObject("url", request.getServletContext().getContextPath() + "/formation/");
	}
	
	@ModelAttribute("formation")
	public Formation populateFormation() {
		return new Formation();
	}
}
