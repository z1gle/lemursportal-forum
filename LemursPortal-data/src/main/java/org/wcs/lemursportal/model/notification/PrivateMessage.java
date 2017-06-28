/**
 * 
 */
package org.wcs.lemursportal.model.notification;

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

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author mikajy.hery
 *
 */
@Table(name="private_message")
@Entity
public class PrivateMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2880019254988724084L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="destinataire_id", nullable=false)
	private Integer destinataireId;
	
	@Column(name="sender_id", nullable=false)
	private Integer senderId;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="destinataire_id", insertable=false, updatable=false)
	private UserInfo destinataire;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="sender_id", insertable=false, updatable=false)
	private UserInfo sender;
	
	private String subject;
	
	private String body;

	private Date date;
	
	@Column(name="is_read_by_destinaire", nullable=false)
	private Boolean readByDestinataire = false;
	
	
	public Integer getDestinataireId() {
		return destinataireId;
	}

	public void setDestinataireId(Integer destinataireId) {
		this.destinataireId = destinataireId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public UserInfo getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(UserInfo destinataire) {
		this.destinataire = destinataire;
	}

	public UserInfo getSender() {
		return sender;
	}

	public void setSender(UserInfo sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Boolean getReadByDestinataire() {
		return readByDestinataire;
	}

	public void setReadByDestinataire(Boolean readByDestinataire) {
		this.readByDestinataire = readByDestinataire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
