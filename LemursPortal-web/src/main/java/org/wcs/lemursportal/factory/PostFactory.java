package org.wcs.lemursportal.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.web.form.PostForm;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public abstract class PostFactory {
	
	/**
	 * 
	 * @param post
	 * @return
	 */
	public static PostForm toForm(Post post){
		PostForm form = null;
		if(post != null){
			form = new PostForm();
			form.setBody(post.getBody());
			form.setId(post.getId());
			form.setParentId(post.getParentId());
			form.setTitle(post.getTitle());
//			form.set
//			if(post.getDocuments() != null){
//				List<String> attachments = new ArrayList<>();
//				for(Document doc: post.getDocuments()){
//					attachments.add(doc.getUrl());
//				}
//				form.setAttachments(attachments);
//			}
		}
		return form;
	}
	
	public static Post toEntity(PostForm form/*, Thematique thematique, UserInfo currentUser*/){
		Post entity = null;
		if(form != null){
			entity = new Post();
			entity.setBody(form.getBody());
			entity.setTitle(form.getTitle());
			entity.setParentId(form.getParentId());
			entity.setId(form.getId());
//			entity.setThematique(thematique);
			if(form.getAttachments() != null){
				List<Document> docs = new ArrayList<>();
				for(String url: form.getAttachments()){
					Document doc = new Document();
					doc.setUrl(url);
					//doc.setDateUpload(new Date());
//					doc.setOwnerId(currentUser.getId());
//					doc.setOwner(currentUser);
//					if(form.getFile() != null && form.getFile().getContentType())
//					doc.setType(type);
					docs.add(doc);
				}
				//entity.setDocuments(docs);
			}
		}
		return entity;
	}
}
