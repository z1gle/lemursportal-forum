package org.wcs.lemursportal.model.post;

import java.io.Serializable;

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class TopQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082207346095545042L;
	
	private UserInfo responsable;
	private Post question;
	private Post derniereReponse;
	private Long nbVue;
	private Long nbReponse;
	
	public UserInfo getResponsable() {
		return responsable;
	}
	public void setResponsable(UserInfo responsable) {
		this.responsable = responsable;
	}
	public Post getQuestion() {
		return question;
	}
	public void setQuestion(Post question) {
		this.question = question;
	}
	public Long getNbVue() {
		return nbVue;
	}
	public void setNbVue(Long nbVue) {
		this.nbVue = nbVue;
	}
	public Long getNbReponse() {
		return nbReponse;
	}
	public void setNbReponse(Long nbReponse) {
		this.nbReponse = nbReponse;
	}
	public Post getDerniereReponse() {
		return derniereReponse;
	}
	public void setDerniereReponse(Post derniereReponse) {
		this.derniereReponse = derniereReponse;
	}
}
