package org.wcs.lemursportal.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.PostView;
import org.wcs.lemursportal.model.post.TopQuestion;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface PostService {
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<TopQuestion> getTopQuestions(Pageable pageable);
	
	public void insert(Post post, String authorLogin, String postUrl);
	
	public void update(Post post, String authorLogin, String postUrl);
	
	Page<Post> search(Pageable pageable, String pattern);
	
	Post findPostById(Integer id);

	/**
	 * @param questionId
	 * @param pageable
	 * @return
	 */
	Page<Post> getQuestionResponses(Integer questionId, Pageable pageable);
	
	/**
	 * 
	 * @param question
	 * @return
	 */
	PostView incrementerNbVue(Post question, String user);
	
	@PostAuthorize ("returnObject.owner.email == authentication.name or hasRole('ADMIN')")
	Post deletepost(Integer postId, String currentLogin);
}
