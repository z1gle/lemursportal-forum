package org.wcs.lemursportal.model.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.wcs.lemursportal.model.authentication.IUserInfo;
import org.wcs.lemursportal.model.post.Thematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name="utilisateur")
public class UserInfo implements IUserInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7606421457192166667L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nom;
	
	@Column(name="prenoms")
	private String prenom;
	
	@Column(name="date_naissance")
	private Date dateNaissance;
	
	@Column(name="date_inscription")
	private Date dateInscription;
	
	private String institution;
	
	@Column(name="post_occupe")
	private String postOccupe;
	
	@Column(name = "login", nullable=false, unique=true)
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "last_access_date")
	private Date lastAccessDate;
	
	private String email;
	
	@Column(name = "biographie", columnDefinition = "text")
	private String biographie;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="utilisateur_typeuser", 
		joinColumns = {@JoinColumn(name="idutilisateur", referencedColumnName = "id")},
		inverseJoinColumns= {@JoinColumn(name = "idtypeuser", referencedColumnName = "id")}
	)
	private Set<UserType> roles;
	
	@ManyToMany(mappedBy="managers")
	private Set<Thematique> managedThematiques;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getBiographie() {
		return biographie;
	}
	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}
	public Set<UserType> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserType> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Thematique> getManagedThematiques() {
		return managedThematiques;
	}
	public void setManagedThematiques(Set<Thematique> managedThematiques) {
		this.managedThematiques = managedThematiques;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getPostOccupe() {
		return postOccupe;
	}
	public void setPostOccupe(String postOccupe) {
		this.postOccupe = postOccupe;
	}
	
}
