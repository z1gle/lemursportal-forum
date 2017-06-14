package org.wcs.lemursportal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author z
 *
 */
@Entity
@Table(name="formation")
public class Formation implements Serializable {
	
	private static final long serialVersionUID = -1722178560929257653L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="contenu", nullable=true)
	@Type(type="text")
	private String body;
	
	@Column(name="date_creation", nullable=false)
	private Date creationDate;
	
	@Column(name="date_edition", nullable=true)
	private Date lastEdit;
	
	@Column(name="owner_id", insertable=true, updatable=true)
	private Integer ownerId;
	
	@Column(name="parent_id", insertable=true, updatable=true, nullable=true)
	private Integer parentId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="owner_id", insertable=false, updatable=false)
	private UserInfo owner;
	
	@Column(name="censored", nullable=true)
	private Boolean censored;
	
	@Column(name="draft", nullable=true)
	private Boolean draft;
	
	@Column(name="censored_date", nullable=true)
	private Date censoredDate;
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name = "view_count", insertable = false, columnDefinition = "INT DEFAULT 0")
	private int viewCount;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(columnDefinition="integer", name="censored_by", nullable=true)
	private UserInfo censoredBy;//l'utilisateur(moderateur) qui a bloqué ce POST

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UserInfo getOwner() {
		return owner;
	}

	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}

	public Boolean getCensored() {
		return censored;
	}

	public void setCensored(Boolean censored) {
		this.censored = censored;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCensoredDate() {
		return censoredDate;
	}

	public void setCensoredDate(Date censoredDate) {
		this.censoredDate = censoredDate;
	}

	public UserInfo getCensoredBy() {
		return censoredBy;
	}

	public void setCensoredBy(UserInfo censoredBy) {
		this.censoredBy = censoredBy;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
}
