package org.wcs.lemursportal.web.form;

import java.util.Date;

/**
 * 
 * @author z
 *
 */
public class FormationForm {
	
	private Long id;
	private Integer owner;
	private String content;
	private Date creationDate;
	private String description;
	private String title;
	private Date lastEdit;
	private Boolean draft;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the lastEdit
	 */
	public Date getLastEdit() {
		return lastEdit;
	}
	/**
	 * @param lastEdit the lastEdit to set
	 */
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}
	public Boolean getDraft() {
		return draft;
	}
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}
	
}
