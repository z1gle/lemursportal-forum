package org.wcs.lemursportal.model.notification;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name="notification")
public class Notification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -913118027882503983L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="user_id", nullable=false)
	private Integer userId;
	
	@Column(name="thematique_id", nullable=true)
	private Integer thematiqueId;
	
	@Column(name="question_id", nullable=true)
	private Integer questionId;
	
	@Column(name="response_id", nullable=true)
	private Integer responseId;
	
	@Column(name="date", nullable=true)
	private Date date;
	
	@Column(name="has_bean_read")
	private Boolean hasBeenRead;
	
	/**
	 * 
	 */
	@Enumerated(value=EnumType.STRING)
	private NotificationType notificationType;
	
	@ManyToOne
	@JoinColumn(name="thematique_id", insertable=false, updatable=false)
	private Thematique thematique;
	
	@ManyToOne
	@JoinColumn(name="question_id", insertable=false, updatable=false)
	private Post question;
	
	@ManyToOne
	@JoinColumn(name="response_id", insertable=false, updatable=false)
	private Post response;
	
	public Notification() {
		super();
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getResponseId() {
		return responseId;
	}
	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getThematiqueId() {
		return thematiqueId;
	}
	public void setThematiqueId(Integer thematiqueId) {
		this.thematiqueId = thematiqueId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Thematique getThematique() {
		return thematique;
	}
	public void setThematique(Thematique thematique) {
		this.thematique = thematique;
	}
	public Post getQuestion() {
		return question;
	}
	public void setQuestion(Post question) {
		this.question = question;
	}
	public Post getResponse() {
		return response;
	}
	public void setResponse(Post response) {
		this.response = response;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public Boolean getHasBeenRead() {
		return hasBeenRead;
	}

	public void setHasBeenRead(Boolean hasBeenRead) {
		this.hasBeenRead = hasBeenRead;
	}
	
	
	
	
}
