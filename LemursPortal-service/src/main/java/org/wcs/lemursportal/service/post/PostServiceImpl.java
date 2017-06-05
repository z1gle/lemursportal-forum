package org.wcs.lemursportal.service.post;

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
		Page<TopQuestion> page = postRepository.getTopQuestions(pageable);
		return page;
	}
	
	@Override
	public Page<Post> getQuestionResponses(Integer questionId, Pageable pageable) {
		Page<Post> page = postRepository.getQuestionResponses(questionId, pageable);
		return page;
	}

	@Override
	public void insert(Post post) {
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
		Set<Integer> sets = new HashSet<Integer>();
		sets.add(id);
		List<Post> lst = postRepository.getPostsAndFetchOwner(sets);
		if(lst!=null && lst.size()==1){
			Post p = lst.get(0);
			//p.setChildren(postRepository.getResponsesAndFetchOwner(p.getId()));
			return p;
		}
		return null;
	}

}
