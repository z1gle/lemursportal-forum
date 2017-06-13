package org.wcs.lemursportal.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;

@Controller
@Transactional
public class PostController extends BaseController{

	@Autowired
	ServletContext context; 
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ThematiqueService thematiqueService;
	
	
	@GetMapping(value={"/secured/post/create", "/secured/thematique-{thematiqueId}/post/create"})
	public String create(@PathVariable(required=false) Integer thematiqueId, Authentication authentication, Model model){
		List<Thematique> listethematique = thematiqueService.findAll();
		model.addAttribute("listeThematique", listethematique);
		Post post = new Post();
		if(thematiqueId != null){
			post.setThematiqueId(thematiqueId);
		}
		model.addAttribute(post);
		return "getFormPost";
	}
	
	@PostMapping(value="/secured/post")
	public String submit(Authentication authentication, Model model, 
			@ModelAttribute Post post, @RequestParam("file") MultipartFile file,
			BindingResult results){		
		if(authentication == null) return "redirect:/login";
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "thematiqueId", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:getFormPost";
		}
		//thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		UserInfo currentUser = userInfoService.getByLogin(authentication.getName());
		Date now = Calendar.getInstance().getTime();
		
		//handle file upload
		
		 if (!file.isEmpty()) {
			 String filename = file.getOriginalFilename();
//			System.out.println("filename " + filename);
			 String path = context.getRealPath("/")+ File.separator +  "resources" + File.separator + "upload" + File.separator +  filename;
			 if(!Files.exists(  Paths.get(context.getRealPath("/"), File.separator ,  "resources" , File.separator , "upload"), LinkOption.NOFOLLOW_LINKS)  ){
				 try {
					Files.createDirectories(Paths.get(context.getRealPath("/"), File.separator ,  "resources" , File.separator , "upload"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(path)));
	                stream.write(bytes);
	                stream.close();
	                Document doc  = new Document();
	                doc.setAuthor(currentUser);
	                doc.setCreationDate(now);
	                doc.setFilename(filename);
	                doc.setUrl("/" + "resources" + "/" + "upload"+ "/" + filename);
	                doc.setAuthorId(currentUser.getId());
	                post.setDocument(doc);
	               // System.out.println("filefile : " + context.getRealPath("/")+ File.separator +filename);
	            } catch (Exception e) {
	                return "You failed to upload " + filename + " => " + e.getMessage();
	            }
	        } else {
	            System.out.println("filefile empty");
	        }
		
		//
		
		post.setCreationDate(now);
		post.setOwnerId(currentUser.getId());
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
			postService.incrementerNbVue(p, authentication == null ? null : authentication.getName());
		}

		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1;
		}
		Page<Post> responsesPage = postService.getQuestionResponses(idPost, new PageRequest(page, BaseController.DERNIERES_QUESTIONS_PAGE_SIZE));
		model.addAttribute("post", p);
//		model.addAttribute(new PostForm());
		model.addAttribute("responsesPage", responsesPage);
		return "showPost";
	}
	 
	
	@PostMapping(value="/secured/post/reponse")
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	public String submitReponse(Authentication authentication, Model model, 
			@ModelAttribute Post post, 
			BindingResult results){		
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:getFormPost";
		}
		Integer id = post.getId();
		post.setParentId(id);
		post.setTitle("");
		postService.insert(post, authentication.getName());
		return "redirect:/post/show/"+id;
	}
}
