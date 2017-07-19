package org.wcs.lemursportal.data.authentication;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class Authority {
	private String email;
	private String authority;
	public String getEmail() {
		return email;
	}
	public void setLogin(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
