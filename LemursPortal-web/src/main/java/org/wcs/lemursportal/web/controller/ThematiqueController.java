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
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.model.post.TopThematique;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class ThematiqueController {
	
	@Autowired
	private ThematiqueService thematiqueService;
	@Autowired private PostRepository postRepository;
	@Autowired private PostService postService;
	

	@Autowired
	ThematiqueRepository thematiqueRepository;
	
//	private UserInfoService userInfoService;
	
	@GetMapping(value="/secured/thematique/create")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String create(Authentication authentication, Model model){
		Thematique thematique = new Thematique();	
		model.addAttribute(thematique);
		return "post/thematique-form";
	}
	
	@GetMapping(value="/secured/thematique/{idThematique}")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String edit(@PathVariable(name="idThematique", required=false) Integer idThematique, Model model){
		if(idThematique == null || idThematique <= 0){
			return "redirect:/secured/thematique/create";
		}
		Thematique thematique = thematiqueService.findById(idThematique);
		model.addAttribute(thematique);
		return "post/thematique-form";
	}
	
	@GetMapping(value={"/thematique/list"})
	public String list(Model model){
		List<TopThematique>  listThematiques = thematiqueRepository.findAllThematique();
		//Page<Thematique> page = thematiqueService.findAll(pageable);
		model.addAttribute("allThematiques", listThematiques);
		return "post/thematique-list";
	}
	
	@PostMapping(value="/secured/thematique")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String submit(Authentication authentication, Model model, 
			@ModelAttribute Thematique thematique, 
			BindingResult results){
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:post/thematique-form";
		}
		try{
			thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		}catch(RegistrationException e){
			if(e.getCode() == RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION){
				results.rejectValue("login", "validation.thematique.exist");
				return "forward:post/thematique-form";
			}else{
				return "forward:post/thematique-form";
			}
		}
		
		return "redirect:/secured/thematique/list";
	}
	
	@RequestMapping(value="/postsParThematique/{idThematique}",method=RequestMethod.GET)
	public String listPostsByThematique(@PathVariable(name="idThematique", required=false) Integer idThematique, Model model){
		if(idThematique == null){
			return "redirect:/thematique/list";
		}
		Thematique thematique = thematiqueService.findById(idThematique);
		if (null == thematique){
			return "redirect:/thematique/list";
		}
		model.addAttribute(thematique);
		model.addAttribute("postsBythematique", postRepository.getPostByThematique(new PageRequest(0, 10),idThematique).getContent());
		return "postsbythematique";
	}
	
	@ModelAttribute("topQuestions")
	public List<TopQuestion> getTopQuestions(){
		Page<TopQuestion> page = postService.getTopQuestions(new PageRequest(0, 10));
		return page.getContent();
	}
	
	@ModelAttribute("topThematiques")
	public List<TopThematique> getTopThematiques(){
		List<TopThematique> t = thematiqueRepository.findTopThematique(10);
		return t;
	}
	
	@ModelAttribute("lastestPosts")
	public List<Post> getLastestPosts(){
		Page<Post> page = postRepository.getLastestPosts(new PageRequest(0, 10));//On ne prendra que les 10 premiers
		return page.getContent();
	}
	
	
}
