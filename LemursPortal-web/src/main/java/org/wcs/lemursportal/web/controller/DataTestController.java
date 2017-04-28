package org.wcs.lemursportal.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.PostView;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.PostCrudRepository;
import org.wcs.lemursportal.repository.post.PostViewCrudRepository;
import org.wcs.lemursportal.repository.post.ThematiqueCrudRepository;
import org.wcs.lemursportal.repository.user.UserRepository;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class DataTestController {
	
	private static final int NB_THEMATIQUE_MAX = 20;
	private static final int NB_POST_MAX = 80;
	private static final int NB_POST_VIEW_MAX = 100;
	private final Random random = new Random();
	@Autowired UserInfoService userInfoService;
	@Autowired UserRepository userRepository;
	@Autowired ThematiqueCrudRepository thematiqueCrudRepository;
	@Autowired PostCrudRepository postCrudRepository; 
	@Autowired PostViewCrudRepository postViewCrudRepository;
	
	@GetMapping(value="/admin/generate_topicsandpost")
	public @ResponseBody String generateDataForTest(Authentication authentication, HttpServletRequest request){
		UserInfo currentUser = userInfoService.getByLogin(authentication.getName());
		List<Thematique> thematiques = generateThematique(currentUser);
		StringBuilder sb = new StringBuilder("<h2>Recap : </h2>");
		sb.append(thematiques.size() + " générés dont : <ul>");
		for(Thematique thematique: thematiques){
			List<Post> thematiquePosts = generatePosts(thematique, currentUser);
			for(Post post: thematiquePosts){
				List<PostView> postViews = simulatePostView(thematique, post, currentUser);
				postViewCrudRepository.save(postViews);
			}
			sb.append("<li>" + thematique.getLibelle() + "=> " + thematiquePosts.size() +" posts </li>");
			
		}
		sb.append("</ul>");
		return sb.toString();
	}
	
	private List<Thematique> generateThematique(UserInfo currentUser){
		List<Thematique> thematiques = new ArrayList<>();
		int nbThematique = random.nextInt(NB_THEMATIQUE_MAX);
		while(nbThematique < 0){
			nbThematique = random.nextInt(NB_THEMATIQUE_MAX);
		}
		for(int i=0;i< nbThematique;i++){
			Thematique t = new Thematique();
			t.setCreatedBy(currentUser);
			t.setCreationDate(Calendar.getInstance().getTime());
			t.setDescription("Description du thematique_" + i);
			t.setLibelle("Thematique_" + i);
			thematiques.add(t);
		}
		thematiqueCrudRepository.save(thematiques);
		return thematiques;
	}
	
	private List<Post> generatePosts(Thematique thematique, UserInfo user){
		List<Post> posts = new ArrayList<>();
		//Test: création de post pour le nouveau thématique
		int nbMessage = -1;
		boolean randomBoolean = false;
		while(nbMessage < 0){
			nbMessage = random.nextInt(NB_POST_MAX);
		}
		
		
		Calendar calendar = Calendar.getInstance();
		int maxMonth = calendar.get(Calendar.MONTH);
		
		for(int i=0, calendarMonth = -1; i< nbMessage; i++ ){
			Post message = new Post();
			while(calendarMonth <  0 ){
				calendarMonth = random.nextInt(maxMonth);
			}
			calendar.set(Calendar.MONTH, calendarMonth);
			Date creationDate = calendar.getTime();
			message.setCreationDate(creationDate);
			message.setTitle("Message numéro " + i + " de " + thematique.getLibelle());
			message.setBody("Description lavalava ihany ho an'i Message numéro " + i + " La Juve, le pire tirage pour Monaco ? Un derby madrilène explosif !");
			message.setOwner(user);
			if(randomBoolean && posts.size() > 1){
				int index = random.nextInt(posts.size() - 1);
				if(index > 0){
					message.setParent(posts.get(index));
				}
			}
			randomBoolean = random.nextBoolean();
			message.setThematique(thematique);
			posts.add(message);
		}
		postCrudRepository.save(posts);
		return posts;
	}
	
	private List<PostView> simulatePostView(Thematique thematique, Post post, UserInfo user){
		List<PostView> postViews = new ArrayList<>();
		int nbView = random.nextInt(NB_POST_VIEW_MAX);
		while(nbView < 0){
			nbView = random.nextInt(NB_POST_VIEW_MAX);
		}
		for(int i=0;i< nbView; i++){
			PostView postView = new PostView();
			postView.setPostId(post.getId());
			postView.setThematiqueId(thematique.getId());
			postView.setViewBy(user.getLogin());
			postView.setViewDate(Calendar.getInstance().getTime());
			postViews.add(postView);
		}
		return postViews;
	}
	
}
