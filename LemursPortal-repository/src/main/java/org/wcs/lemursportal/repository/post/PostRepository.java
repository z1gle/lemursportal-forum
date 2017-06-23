package org.wcs.lemursportal.repository.post;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopQuestion;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface PostRepository {
	
	/**
	 * 
	 * @param limit
	 * @return
	 */
	Page<Post> getLastestPosts(Pageable pageable);
	
	 /**
	  * 
	  * @param pageable
	  * @param idThematique
	  * @return
	  */
	 Page<TopQuestion> getPostByThematique (Pageable pageable, Integer idThematique);
	 
	 /**
	  * 
	  * @param pageable
	  * @return
	  */
	 Page<TopQuestion> getTopQuestions(Pageable pageable);
	 
	 
	 void insert(Post post);
	 
	 public List<Post> getPostsAndFetchOwner(Set<Integer> postIds);
	 
	 public Post getPostsAndFetchOwner(Integer postId);

	 public List<Post> getResponsesAndFetchOwner(Integer id);
	 
	Page<Post> search(Pageable pageable, String pattern);
	
	Page<Post> getYoutubeVideo(Pageable pageable);

	/**
	 * @param questionId
	 * @param pageable
	 * @return
	 */
	Page<Post> getQuestionResponses(Integer questionId, Pageable pageable);
}
