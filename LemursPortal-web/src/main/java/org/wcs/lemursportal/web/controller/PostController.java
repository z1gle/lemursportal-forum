package org.wcs.lemursportal.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	
	public static final String URL_YOUTUBE_PATTERN = "^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$";
	
	
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
			@ModelAttribute Post post, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			BindingResult results){		
		if(authentication == null) return "redirect:/login";
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "thematiqueId", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "body", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "title", "validation.mandatory");
		 System.out.println("gffghgfh");
		//validate url youtube video
		if(null!=post.getUriYoutube()&& !post.getUriYoutube().isEmpty()){
			Pattern pattern =  Pattern.compile(URL_YOUTUBE_PATTERN);  
			Matcher matcher = pattern.matcher(post.getUriYoutube().trim());  
			if (!matcher.matches()) {  
				model.addAttribute("error_youtube","invalid url youtube");
				List<Thematique> listethematique = thematiqueService.findAll();
				model.addAttribute("listeThematique", listethematique);
				model.addAttribute(post);
				return "getFormPost";
			}
		}
		if(results.hasErrors()){
			 
			List<String> errors = new ArrayList<>();
			for(FieldError fieldError:results.getFieldErrors()){
				errors.add(fieldError.getCode());
				
			}
			List<Thematique> listethematique = thematiqueService.findAll();
			model.addAttribute("listeThematique", listethematique);
			model.addAttribute(post);
			model.addAttribute("errors",errors);
			return "getFormPost";
		}
		//thematiqueService.saveOrUpdate(authentication.getName(), thematique);
		UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
		System.out.println("mail");
		Date now = Calendar.getInstance().getTime();
		
		if(null!= post.getUriYoutube()){
			String urIYoutube = post.getUriYoutube().trim();
			if(StringUtils.isEmpty(urIYoutube))post.setUriYoutube(null);
			else{
				if (!urIYoutube.toLowerCase().matches("^\\w+://.*")) {
					urIYoutube = "http://" + urIYoutube;
					post.setUriYoutube(urIYoutube);
				}
			}
				
		}
		
		//handle file upload
		
		 if (!file.isEmpty()) {
			 String filename = file.getOriginalFilename().replaceAll("\\s+","");
			 filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
			 filename = filename.replaceAll("[^\\p{ASCII}]", "");
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
	               // doc.setUploadDate(now);
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
		postService.insert(post, authentication.getName(), getPostUrl(post, request));
		return "redirect:/post/show/" + post.getId();
	}
	
	private String getPostUrl(Post post, HttpServletRequest request){
		StringBuilder postUrl = new StringBuilder(); 
		String requestUrl = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		String path = requestUrl.substring(0, requestUrl.indexOf(contextPath) + contextPath.length());
		postUrl.append(path).append("/post/show/");
		System.out.println(postUrl);
		
		return postUrl.toString();
		
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
	
	@GetMapping(value="/post/del/{idPost}")
	public String deletePost(@PathVariable(name="idPost", required=true) Integer idPost, Authentication authentication, 
			Model model){
		if(authentication != null){
			Post post = postService.deletepost(idPost, authentication.getName());
		}
		return "redirect:/";
	}
	 
	
	@PostMapping(value="/secured/post/reponse")
	@PreAuthorize("hasAnyRole('USER', 'EXPERT','MODERATEUR', 'ADMIN')")
	public String submitReponse(Authentication authentication, Model model, 
			@ModelAttribute Post post, HttpServletRequest request,
			BindingResult results){		
		//ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
		if(results.hasErrors()){
			return "forward:getFormPost";
		}
		Integer id = post.getId();
		post.setParentId(id);
		post.setTitle("");
		postService.insert(post, authentication.getName(), getPostUrl(post, request));
		return "redirect:/post/show/"+id;
	}
}
