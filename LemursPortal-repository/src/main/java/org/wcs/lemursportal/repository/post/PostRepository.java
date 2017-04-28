package org.wcs.lemursportal.repository.post;

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
	 * @return
	 */
	 Page<TopQuestion> getMostViewedPosts(Pageable pageable);
}
