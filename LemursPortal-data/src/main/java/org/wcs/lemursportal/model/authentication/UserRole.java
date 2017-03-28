/**
 * 
 */
package org.wcs.lemursportal.model.authentication;

import org.wcs.lemursportal.model.user.UserType;

/**
 * Enumération de la liste des roles utilisés par l'application.
 * Cette énumération doit refleter le contenu de l'entity (table) {@link UserType}
 * 
 * @author mikajy.hery
 *
 */
public enum UserRole {
	ADMINISTRATEUR(10001, "ROLE_ADMIN"), 
	MODERATEUR(1001, "ROLE_MODERATEUR"), 
	EXPERT(101, "ROLE_EXPERT"), 
	SIMPLE_USER(10, "ROLE_USER");
	
	private int id;
	private String role;
	
	UserRole(int id, String role){
		this.id = id;
		this.role = role;
	}
	
	public int getId(){
		return id;
	}
	
	public String getRole(){
		return role;
	}
	
	public static UserRole toEnum(String role){
		for(UserRole e : values()){
			if(e.getRole().equalsIgnoreCase(role)){
				return e;
			}
		}
		return null;
	}
	
	public static UserRole toEnum(int id){
		for(UserRole e : values()){
			if(e.id == id){
				return e;
			}
		}
		return null;
	}
}
