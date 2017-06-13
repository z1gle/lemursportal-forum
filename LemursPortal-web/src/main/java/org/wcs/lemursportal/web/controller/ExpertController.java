package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wcs.lemursportal.model.authentication.UserRole;
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
public class ExpertController extends BaseController {
	
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
}
