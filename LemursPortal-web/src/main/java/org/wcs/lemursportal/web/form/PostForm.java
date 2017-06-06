package org.wcs.lemursportal.web.form;

import java.util.List;


/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class PostForm extends FileBucket {
	
	private Integer id;
	
	private String title;
	
	private String body;
	
	private Integer parentId;
	
	private Integer thematiqueId;
	
	private List<String> attachments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public Integer getThematiqueId() {
		return thematiqueId;
	}

	public void setThematiqueId(Integer thematiqueId) {
		this.thematiqueId = thematiqueId;
	}
	
	
}
