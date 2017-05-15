package org.wcs.lemursportal.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.repository.post.PostRepository;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Service
@Transactional(readOnly=true)
public class PostServiceImpl implements PostService {

	@Autowired 
	private PostRepository postRepository;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.post.ThematiqueService#getTopQuestions(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<TopQuestion> getTopQuestions(Pageable pageable) {
		Page<TopQuestion> page = postRepository.getMostViewedPosts(pageable);
		//TODO recuperer le nombre de reponse de chaque post (=> remplir les informations de chaque TopQuestion)
		return page;
	}

	@Override
	public void insert(Post post) {
		postRepository.insert(post);
	}

}
