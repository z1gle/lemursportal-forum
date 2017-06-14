package org.wcs.lemursportal.model.post;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Compteur de vue pour les messages
 * 
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name="message_view_counter")
public class PostView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3713043049600304167L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="post_id", nullable=false, insertable=true, updatable=true)
	private Integer postId;
	
	@Column(name="thematique_id", nullable=false)
	private Integer thematiqueId;

	@Column(name="view_date", nullable=false)
	private Date viewDate;
	
	@Column(name="view_by", nullable=true)
	private Integer viewBy;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="post_id", nullable=false, insertable=false, updatable=false)
	private Post post;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="thematique_id", insertable=false, updatable=false)
	private Thematique thematique;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getThematiqueId() {
		return thematiqueId;
	}
	public void setThematiqueId(Integer thematiqueId) {
		this.thematiqueId = thematiqueId;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	public Integer getViewBy() {
		return viewBy;
	}
	public void setViewBy(Integer viewBy) {
		this.viewBy = viewBy;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Thematique getThematique() {
		return thematique;
	}
	public void setThematique(Thematique thematique) {
		this.thematique = thematique;
	}
	
}
