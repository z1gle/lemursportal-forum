/**
 * 
 */
package org.wcs.lemursportal.model.mail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author mikajy.hery
 *
 */
@Entity
@Table(name="mailqueue")
public class MailQueue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -913995174990028780L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	private String subject;
	@Column(name="body")
	private String body;
	private String destinataires;
	@Column(name="creation_date", nullable=false)
	private Date creationDate;
	private Boolean sent;
	@Column(name="sent_date", nullable=true)
	private Date sentDate;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the sent
	 */
	public Boolean getSent() {
		return sent;
	}
	/**
	 * @param sent the sent to set
	 */
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}
	/**
	 * @param sentDate the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	/**
	 * @return the destinataires
	 */
	public String getDestinataires() {
		return destinataires;
	}
	/**
	 * @param destinataires the destinataires to set
	 */
	public void setDestinataires(String destinataires) {
		this.destinataires = destinataires;
	}
	
	

}
