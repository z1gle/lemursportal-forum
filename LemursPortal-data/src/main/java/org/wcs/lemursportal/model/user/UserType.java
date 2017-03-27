/**
 * 
 */
package org.wcs.lemursportal.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mikajy.hery
 *
 */
@Entity
@Table(name="typeuser")
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6813530063889432936L;
	
	@Id
	private Integer id;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="description")
	private String description;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
