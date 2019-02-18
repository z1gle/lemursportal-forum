package org.wcs.lemursportal.model.post;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name="thematique")
public class Thematique implements Serializable {

	private static final long serialVersionUID = -7731302369156672050L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="libelle", unique=true, nullable=false)
	private String libelle;
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name="date_creation", nullable=false)
	private Date creationDate;
	
	@Column(name="date_modif", nullable=true)
	private Date editDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(columnDefinition="integer", name="created_by", nullable=false)
	private UserInfo createdBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(columnDefinition="integer", name="modified_by", nullable=true)
	private UserInfo modifiedBy;
	
	@OneToMany(mappedBy="thematique")
	private List<Post> questions;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="domaine_expertise", 
		joinColumns = {@JoinColumn(name="idthematique", referencedColumnName = "id")},
		inverseJoinColumns= {@JoinColumn(name = "iduser", referencedColumnName = "id")}
	)
	private Set<UserInfo> managers;
	
	private Boolean deleted= false;
	
	@Column(name="deleted_by", nullable=true)
	private Integer deletedBy;
	
	@Column(name="deleted_date", nullable=true)
	private Date deletedDate;
	
	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<UserInfo> getManagers() {
		return managers;
	}
	public void setManagers(Set<UserInfo> managers) {
		this.managers = managers;
	}
	public UserInfo getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserInfo createdBy) {
		this.createdBy = createdBy;
	}
	public UserInfo getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(UserInfo modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
//	public List<Post> getPosts() {
//		return posts;
//	}
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}
	 @Override
	    public boolean equals(Object o) {

	        if (o == this) return true;
	        if (!(o instanceof Thematique)) {
	            return false;
	        }

	        Thematique thematique = (Thematique) o;
	        return Objects.equals(id, thematique.id);
	       
	    }
	 
	 @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	public List<Post> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Post> questions) {
		this.questions = questions;
	}
	/**
	 * @return the deletedBy
	 */
	public Integer getDeletedBy() {
		return deletedBy;
	}
	/**
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}
	/**
	 * @param deletedDate the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
}
