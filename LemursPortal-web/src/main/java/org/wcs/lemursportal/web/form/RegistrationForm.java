/**
 * 
 */
package org.wcs.lemursportal.web.form;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.wcs.lemursportal.dto.user.SocialProvider;
import org.wcs.lemursportal.model.post.Thematique;

/**
 * @author mikajy.hery
 *
 */
public class RegistrationForm extends FileBucket {
	
	private Integer id;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String institution;
	private String postOccupe;
	private String password;
	private String passwordConfirm;
	private String email;
	private Boolean enabled;
	private Date lastAccessDate;
	private String biographie;
	private String publication;	
	private List<Integer> userTypeIds;
	private List<Integer> dExpertises;
	private String photoProfil;
	private SocialProvider socialProvider;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public String getBiographie() {
		return biographie;
	}
	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}
	public List<Integer> getUserTypeIds() {
		return userTypeIds;
	}
	public void setUserTypeIds(List<Integer> userTypeIds) {
		this.userTypeIds = userTypeIds;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostOccupe() {
		return postOccupe;
	}
	public void setPostOccupe(String postOccupe) {
		this.postOccupe = postOccupe;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getPhotoProfil() {
		return photoProfil;
	}
	public void setPhotoProfil(String photoProfil) {
		this.photoProfil = photoProfil;
	}
	public SocialProvider getSocialProvider() {
		return socialProvider;
	}
	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public List<Integer> getdExpertises() {
		return dExpertises;
	}
	public void setdExpertises(List<Integer> dExpertises) {
		this.dExpertises = dExpertises;
	}
	
}
