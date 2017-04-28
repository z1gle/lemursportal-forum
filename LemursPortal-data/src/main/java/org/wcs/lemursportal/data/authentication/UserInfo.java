package org.wcs.lemursportal.data.authentication;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Entity
@Table(name="user_info")
public class UserInfo implements IUserInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7606421457192166667L;
	
	@Id
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "last_access_date")
	private Date lastAccessDate;
	
	@Column(name = "role")
	private String role;
//	private HashSet<Authority> authorities;
	
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
//	public HashSet<Authority> getAuthorities() {
//		return authorities;
//	}
//	public void setAuthorities(HashSet<Authority> authorities) {
//		this.authorities = authorities;
//	}
	public Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
