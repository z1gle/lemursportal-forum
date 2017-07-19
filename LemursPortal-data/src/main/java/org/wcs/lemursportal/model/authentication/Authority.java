package org.wcs.lemursportal.model.authentication;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class Authority {
	private String email;
	private String authority;
	public String getLogin() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
