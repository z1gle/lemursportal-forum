package org.wcs.lemursportal.model.post;

import java.io.Serializable;

/**
 * Classe representatif des thématiques avec leurs nombre de message respectifs
 * 
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class TopThematique implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1202980249240053758L;
	private Thematique thematique;
	private Long nombreMessage;
	private Long nombreThreads;
	private Post latestPost;
	
	public TopThematique(){
		
	}
	
	public TopThematique(Thematique thematique, Long nombreMessage, Long nbThreads, Post latestPost) {
		super();
		this.thematique = thematique;
		this.nombreMessage = nombreMessage;
		this.nombreThreads = nbThreads;
		this.latestPost = latestPost;
	}
	
	public Thematique getThematique() {
		return thematique;
	}
	public void setThematique(Thematique thematique) {
		this.thematique = thematique;
	}

	public Long getNombreMessage() {
		return nombreMessage;
	}

	public void setNombreMessage(Long nombreMessage) {
		this.nombreMessage = nombreMessage;
	}

	public Long getNombreThreads() {
		return nombreThreads;
	}

	public void setNombreThreads(Long nombreThreads) {
		this.nombreThreads = nombreThreads;
	}

	public Post getLatestPost() {
		return latestPost;
	}

	public void setLatestPost(Post latestPost) {
		this.latestPost = latestPost;
	}

	
	
	
	
	
	
}
