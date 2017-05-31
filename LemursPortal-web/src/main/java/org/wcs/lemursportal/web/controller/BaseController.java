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
	
	public static final int TOP_QUESTIONS_PAGE_SIZE = 20;
	public static final int TOP_THEMATIQUES_PAGE_SIZE = 20;
	public static final int DERNIERES_QUESTIONS_PAGE_SIZE = 20;
	
	public void setPagination(Integer page, Page<?> pageable, Model model){
		int current = pageable.getNumber() + 1;
	    int begin = Math.max(1, current - 3);
	    int end = Math.min(begin + 6, pageable.getTotalPages());

		model.addAttribute("paginationCurrent", current);
		model.addAttribute("paginationBegin", begin);
		model.addAttribute("paginationEnd", end);
	}
	
	@ModelAttribute("topQuestionsPage")
	public Page<TopQuestion> getTopQuestions(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Page<TopQuestion> pageResult = postService.getTopQuestions(new PageRequest(page, TOP_QUESTIONS_PAGE_SIZE));
		setPagination(page, pageResult, model);
		return pageResult;
	}
	
	@ModelAttribute("topThematiques")
	public List<TopThematique> getTopThematiques(){
		List<TopThematique> t = thematiqueRepository.findTopThematique(TOP_THEMATIQUES_PAGE_SIZE);
		return t;
	}
	
	@ModelAttribute("lastestPosts")
	public List<Post> getLastestPosts(){
		Page<Post> page = postRepository.getLastestPosts(new PageRequest(0, DERNIERES_QUESTIONS_PAGE_SIZE));//On ne prendra que les 10 premiers
		return page.getContent();
	}
}
