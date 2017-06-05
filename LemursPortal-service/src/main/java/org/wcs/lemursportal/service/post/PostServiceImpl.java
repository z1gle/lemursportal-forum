package org.wcs.lemursportal.service.post;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.PostView;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.PostCrudRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.PostViewCrudRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Service
@Transactional(readOnly=true)
public class PostServiceImpl implements PostService {

	@Autowired 
	private PostRepository postRepository;
	
	@Autowired PostCrudRepository postCrudRepository;
	
	@Autowired 
	PostViewCrudRepository postViewCrudRepository;
	
	@Autowired ThematiqueRepository thematiqueRepository;
	
	@Autowired UserInfoService userInfoService; 

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.post.ThematiqueService#getTopQuestions(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<TopQuestion> getTopQuestions(Pageable pageable) {
		Page<TopQuestion> page = postRepository.getTopQuestions(pageable);
		return page;
	}
	
	@Override
	public Page<Post> getQuestionResponses(Integer questionId, Pageable pageable) {
		Page<Post> page = postRepository.getQuestionResponses(questionId, pageable);
		return page;
	}

	@Override
	@Transactional(readOnly=false)
	public void insert(Post post, String authorLogin) {
		UserInfo currentUser = userInfoService.getByLogin(authorLogin);
		post.setOwnerId(currentUser.getId());
		post.setOwner(currentUser);
		post.setCreationDate(new Date());
		//System.out.println(post.toString());
		postRepository.insert(post);
	}

	@Override
	public Page<Post> search(Pageable pageable, String pattern) {
		return postRepository.search(pageable, pattern);
	}

	@Override
	public Post findPostById(Integer id) {
		Post post = postRepository.getPostsAndFetchOwner(id);
		return post;
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.post.PostService#incrementerNbVue(org.wcs.lemursportal.model.post.Post, java.lang.String)
	 */
	@Transactional(readOnly=false)
	@Override
	public PostView incrementerNbVue(Integer questionId, String user) {
		PostView postView = new PostView();
		postView.setPostId(questionId);
//		Thematique thematique = thematiqueRepository.findByQuestionId(questionId);
//		postView.setThematiqueId(thematique.getId());
		postView.setViewBy(user);
		postView.setViewDate(Calendar.getInstance().getTime());
		postViewCrudRepository.save(postView);
		return postView;
	}

}
