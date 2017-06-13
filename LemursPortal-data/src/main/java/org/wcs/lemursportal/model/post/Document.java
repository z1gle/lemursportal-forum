package org.wcs.lemursportal.model.post;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wcs.lemursportal.model.user.UserInfo;

@Entity
@Table(name="document")
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="author_id", insertable=true, updatable=true)
	private Integer authorId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id", insertable=false, updatable=false)
	private UserInfo author;
	
	@Column(name="url")
	private String url; 
	
	@Column(name="filename")
	private String filename; 
	
	@Column(name="date_creation", nullable=false)
	private Date creationDate;

	@Column(name="type_id", insertable=true, updatable=true, nullable=true)
	private Integer typeId;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="type_id", insertable=false, updatable=false)
	private DocumentType type = null;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public UserInfo getAuthor() {
		return author;
	}

	public void setAuthor(UserInfo author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
