package org.wcs.lemursportal.web.controller;

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
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
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
	

	@Autowired
	ThematiqueRepository thematiqueRepository;
	
	@Autowired private PostRepository postRepository;
	
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
	
	@GetMapping(value={"/secured/thematique", "/secured/thematique/list"})
	public String list(Model model, Pageable pageable){
		Page<Thematique> page = thematiqueService.findAll(pageable);
		model.addAttribute("page", page);
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
		thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		return "redirect:/secured/thematique/list";
	}
	
	@RequestMapping(value="/postsParThematique/{idThematique}",method=RequestMethod.GET)
	public String listPostsByThematique(@PathVariable(name="idThematique", required=false) Integer idThematique, Model model){
		if(idThematique == null){
			return "redirect:post/thematique-list";
		}
		Thematique thematique = thematiqueService.findById(idThematique);
		model.addAttribute(thematique);
		model.addAttribute("postsBythematique", postRepository.getPostByThematique(new PageRequest(0, 10),idThematique).getContent());
		return "post/posts-thematique";
	}
	
}
