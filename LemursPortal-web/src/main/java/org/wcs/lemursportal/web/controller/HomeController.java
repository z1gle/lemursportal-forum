package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.model.post.Post;
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
@RequestMapping
public class HomeController {
	
	@Autowired
	private ThematiqueService thematiqueService;
	
	@Autowired private ThematiqueRepository thematiqueRepository;
	@Autowired private PostRepository postRepository;
	@Autowired private PostService postService;
	
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
		return "index";
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
		return "index";
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
