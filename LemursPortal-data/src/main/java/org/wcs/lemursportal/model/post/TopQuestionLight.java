package org.wcs.lemursportal.model.post;

import java.io.Serializable;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class TopQuestionLight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304043543980495831L;
	
	private Integer idQuestion;
	private Integer idDerniereReponse;
	private Long nbReponse;
	private Long nbVue;
	
	public Integer getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Integer getIdDerniereReponse() {
		return idDerniereReponse;
	}
	public void setIdDerniereReponse(Integer idDerniereReponse) {
		this.idDerniereReponse = idDerniereReponse;
	}
	public Long getNbReponse() {
		return nbReponse;
	}
	public void setNbReponse(Long nbReponse) {
		this.nbReponse = nbReponse;
	}
	public Long getNbVue() {
		return nbVue;
	}
	public void setNbVue(Long nbVue) {
		this.nbVue = nbVue;
	}

}
