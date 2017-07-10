package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.model.post.TopThematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.repository.user.UserTypeRepository;
import org.wcs.lemursportal.service.post.ThematiqueService;
import org.wcs.lemursportal.web.validator.ThematiqueValidator;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class ThematiqueController extends BaseController{
	
	@Autowired
	private ThematiqueService thematiqueService;
	
	@Autowired private PostRepository postRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	ThematiqueValidator thematiqueValidator;
	
	@Autowired
	ThematiqueRepository thematiqueRepository;
	
//	private UserInfoService userInfoService;
	
	@GetMapping(value="/secured/thematique/create")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String create(Authentication authentication, Model model){
		Thematique thematique = new Thematique();	
		model.addAttribute(thematique);
		return "thematique-form";
	}
	
	@GetMapping(value="/secured/thematique/{idThematique}")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String edit(@PathVariable(name="idThematique", required=false) Integer idThematique, Model model){
		if(idThematique == null || idThematique <= 0){
			return "redirect:/secured/thematique/create";
		}
		Thematique thematique = thematiqueRepository.findByIdAndFetchManagers(idThematique);
//		Thematique thematique = thematiqueService.findById(idThematique);
		model.addAttribute(thematique);
		return "thematique-form";
	}
	
	@GetMapping(value="/secured/thematique/archive-{idThematique}")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String delete(@PathVariable(name="idThematique", required=false) Integer idThematique, Authentication authentication, Model model){
		if(idThematique == null || idThematique <= 0){
			return "redirect:/thematique/list";
		}
		thematiqueService.delete(idThematique, authentication.getName());
//		Thematique thematique = thematiqueService.findById(idThematique);
		return "redirect:/thematique/list";
	}
	
	@GetMapping(value={"/thematique/list"})
	public String list(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_THEMATIQUES_PAGE_SIZE);
		Page<TopThematique> topThematiquePage = thematiqueRepository.findTopThematique(pageable);
		//Page<Thematique> page = thematiqueService.findAll(pageable);
		model.addAttribute("topThematiquePage", topThematiquePage);
		return "thematique-list";
	}

	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	@PostMapping(value="/secured/thematique")
	public String submit(Authentication authentication, Model model, 
			@ModelAttribute Thematique thematique, 
			BindingResult results)
	{
		thematiqueValidator.validate(thematique, results);
		if(results.hasErrors()){
			return "forward:thematique-form";
		}
		Thematique savedThematique = null;
		try{
			savedThematique = thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		}catch(RegistrationException e){
			if(e.getCode() == RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION){
				results.rejectValue("login", "validation.thematique.exist");
				return "forward:thematique-form";
			}else{
				return "forward:thematique-form";
			}
		}
		
		return "redirect:/postsParThematique/" + savedThematique.getId();
	}
	
	@RequestMapping(value="/postsParThematique/{idThematique}",method=RequestMethod.GET)
	public String listPostsByThematique(
			@PathVariable(name="idThematique", required=false) Integer idThematique, 
			@RequestParam(required=false, defaultValue="0") Integer page, 
			Model model
	){
		if(idThematique == null){
			return "redirect:/thematique/list";
		}
		Thematique thematique = thematiqueService.findById(idThematique);
		if (thematique == null){
			return "redirect:/thematique/list";
		}
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Page<TopQuestion> questionPage = postRepository.getPostByThematique(new PageRequest(page, BaseController.TOP_QUESTIONS_PAGE_SIZE), idThematique);
		model.addAttribute(thematique);
//		model.addAttribute("postsBythematique", questionPage.getContent());
		model.addAttribute("postsBythematiquePage", questionPage);
		return "postsbythematique";
	}
	
	@ModelAttribute("experts")
	public List<UserInfo> getListExperts(){
		List<UserInfo> experts = userTypeRepository.findUsers(UserRole.EXPERT);
		return experts;
	}
}
