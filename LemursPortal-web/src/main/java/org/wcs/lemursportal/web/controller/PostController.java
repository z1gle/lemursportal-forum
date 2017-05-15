package org.wcs.lemursportal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;

@Controller
@Transactional
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private ThematiqueService thematiqueService;
	
	
	@GetMapping(value="/secured/post/create")
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public String create(Authentication authentication, Model model){
		List<Thematique> listethematique = thematiqueService.findAll();
		model.addAttribute("listeThematique", listethematique);
		return "post/post-form";
	}
	
}
