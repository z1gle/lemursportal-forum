package org.wcs.lemursportal.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Post;
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
	
	public void insert(Post post);
}
