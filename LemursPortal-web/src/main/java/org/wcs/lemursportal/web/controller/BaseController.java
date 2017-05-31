package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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
public class BaseController {
	
	@Autowired
	ThematiqueService thematiqueService;
	
	@Autowired ThematiqueRepository thematiqueRepository;
	@Autowired PostRepository postRepository;
	@Autowired PostService postService;
	
	@ModelAttribute("topQuestionsPage")
	public Page<TopQuestion> getTopQuestions(@RequestParam(required=false) Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Page<TopQuestion> pageResult = postService.getTopQuestions(new PageRequest(page, 20));
		int current = pageResult.getNumber() + 1;
	    int begin = Math.max(1, current - 3);
	    int end = Math.min(begin + 6, pageResult.getTotalPages());

		model.addAttribute("paginationCurrent", current);
		model.addAttribute("paginationBegin", begin);
		model.addAttribute("paginationEnd", end);
		return pageResult;
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
