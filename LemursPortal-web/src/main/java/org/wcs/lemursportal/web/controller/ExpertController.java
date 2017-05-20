package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.model.authentication.UserRole;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.model.post.TopThematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.repository.user.UserTypeRepository;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class ExpertController {
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private ThematiqueService thematiqueService;
	@Autowired private PostRepository postRepository;
	@Autowired private PostService postService;
	@Autowired
	ThematiqueRepository thematiqueRepository;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping(value="/experts")
	public String getAllExperts( Model model){
		List<UserInfo> listExperts = userTypeRepository.findUsers(UserRole.EXPERT);
		if(null== listExperts || listExperts.size()== 0){
			model.addAttribute("noexpert","expert.not.found");
		}
		model.addAttribute("experts",listExperts);
		return "experts";
	}
	
	@RequestMapping(value="/experts/{idExpert}",method=RequestMethod.GET)
		public String ViewUserInfoDetail(@PathVariable(name="idExpert", required=false) Integer idExpert, Model model){
		if(idExpert == null){
			return "redirect:/experts";
		}
		UserInfo userInfo = userInfoService.getById(idExpert);
		if(userInfo == null){
			return "redirect:/experts";
		}
		model.addAttribute("userInfo",userInfo);
		return "expertdetail";
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
