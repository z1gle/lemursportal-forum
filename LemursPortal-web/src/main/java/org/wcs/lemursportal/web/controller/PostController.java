package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.wcs.lemursportal.factory.PostFactory;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;
import org.wcs.lemursportal.web.form.FileBucket;
import org.wcs.lemursportal.web.form.PostForm;

@Controller
@Transactional
public class PostController extends BaseController{

	@Autowired
	private PostService postService;
	
	@Autowired
	private ThematiqueService thematiqueService;
	
	
	@GetMapping(value={"/secured/post/create", "/secured/thematique-{thematiqueId}/post/create"})
	public String create(@PathVariable(required=false) Integer thematiqueId, Authentication authentication, Model model){
		List<Thematique> listethematique = thematiqueService.findAll();
		model.addAttribute("listeThematique", listethematique);
		PostForm postForm = new PostForm();
		if(thematiqueId != null){
			postForm.setThematiqueId(thematiqueId);
		}
		model.addAttribute(postForm);
		return "getFormPost";
	}
	
	@PostMapping(value="/secured/post")
	public String submit(Authentication authentication, Model model, 
			@ModelAttribute PostForm postForm, 
			BindingResult results){		
		if(authentication == null) return "redirect:/login";
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:getFormPost";
		}
		//thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		//post.set
		Post post = PostFactory.toEntity(postForm);
		postService.insert(post, authentication.getName());
		return "redirect:/post/show/" + post.getId();
	}
	
	
	@PostMapping(value="/post/search")	
	public String search(@RequestParam(name="pattern", required=false) String pattern, Model model){
		model.addAttribute("posts", postService.search(new PageRequest(0, 10), pattern));
		//System.out.println("*************** " + pattern + "  " +p.getSize());
		return "results";
	}
	
	@GetMapping(value="/post/show/{idPost}")	
	public String showPost(@PathVariable(name="idPost", required=true) Integer idPost, 
			@RequestParam(required=false, defaultValue="0") Integer page, 
			Authentication authentication, 
			Model model
	){
		Post p = postService.findPostById(idPost);
		if(p != null){
			postService.incrementerNbVue(idPost, authentication == null ? "anonymous" : authentication.getName());
		}

		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1;
		}
		Page<Post> responsesPage = postService.getQuestionResponses(idPost, new PageRequest(page, BaseController.DERNIERES_QUESTIONS_PAGE_SIZE));
		model.addAttribute("post", p);
		model.addAttribute(new PostForm());
		model.addAttribute("responsesPage", responsesPage);
		return "showPost";
	}
	 
	
	@PostMapping(value="/secured/post/reponse")
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	public String submitReponse(Authentication authentication, Model model, 
			@ModelAttribute PostForm postForm, 
			BindingResult results){		
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:getFormPost";
		}
		
		Post post = PostFactory.toEntity(postForm);
		postService.insert(post, authentication.getName());
		return "redirect:/post/show/"+post.getId();
	}
}
