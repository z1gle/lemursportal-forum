package org.wcs.lemursportal.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
	 
	 /**
	  * 
	  * @return
	  */
	 Long countQuestions();
	 
	 void insert(Post post);
	 
	 Page<TopQuestion> getPostByThematique (Pageable pageable, Integer idThematique);

	Page<Post> search(Pageable pageable, String pattern);
}
