package org.wcs.lemursportal.model.authentication;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public class Authority {
	private String login;
	private String authority;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
