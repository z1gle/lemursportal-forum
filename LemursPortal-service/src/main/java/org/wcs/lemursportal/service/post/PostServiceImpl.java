package org.wcs.lemursportal.service.post;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.DocumentType;
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

	private enum PHOTOEXT {
		png, jpg, gif
	}
	private enum VIDEOEXT {
		mov, avi, mkv,mp4,wmv,mpg
	}
	private enum AUDIOEXT {
		wma, mp3, avi
	}
	
	public enum DOCTYPE {
		PHOTO(1), VIDEO(2), AUDIO(3), PUBLICATION(4);
		private int value;
 
		private DOCTYPE(int value) {
			this.value = value;
		}
	}
	
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
		if(post.getDocument()!=null){
			DocumentType type = new DocumentType();
			String ext = getExtension( post.getDocument().getUrl());
			type.setId(DOCTYPE.PUBLICATION.value);
			for(PHOTOEXT s : PHOTOEXT.values()){
				if(ext.equalsIgnoreCase(s.toString())){
					type.setId( DOCTYPE.PHOTO.value );
					break;
				}
			}
			for(VIDEOEXT s : VIDEOEXT.values()){
				if(ext.equalsIgnoreCase(s.toString())){
					type.setId( DOCTYPE.VIDEO.value );
					break;
				}
			}
			for(AUDIOEXT s : AUDIOEXT.values()){
				if(ext.equalsIgnoreCase(s.toString())){
					type.setId( DOCTYPE.AUDIO.value );
					break;
				}
			}
		}
		postRepository.insert(post);
	}
	
	private  String getExtension(String fileName) {		
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        else return "";
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
	public PostView incrementerNbVue(Post post, String user) {
		
		PostView postView = new PostView();
		postView.setPostId(post.getId());
		if(post.getThematique() != null){
			postView.setThematiqueId(post.getThematique().getId());
		}else{
			Thematique thematique = thematiqueRepository.findByQuestionId(post.getId());
			postView.setThematiqueId(thematique.getId());
		}
		if(user != null){
			UserInfo currentUser = null;
			currentUser = userInfoService.getByLogin(user);
			postView.setViewBy(currentUser.getId());
		}
		postView.setViewDate(Calendar.getInstance().getTime());
		postViewCrudRepository.save(postView);
		return postView;
	}

}
