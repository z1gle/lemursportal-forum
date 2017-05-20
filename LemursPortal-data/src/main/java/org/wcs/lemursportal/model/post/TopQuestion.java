package org.wcs.lemursportal.model.post;

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class TopQuestion extends TopQuestionLight {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082207346095545042L;
	
	private UserInfo responsable;
	private Post question;
	private Post derniereReponse;
	
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
	public Post getDerniereReponse() {
		return derniereReponse;
	}
	public void setDerniereReponse(Post derniereReponse) {
		this.derniereReponse = derniereReponse;
	}
	
}
