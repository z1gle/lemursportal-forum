package org.wcs.lemursportal.data.authentication;

import java.io.Serializable;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface IUserInfo extends  Serializable {
	
	public String getEmail();
	
	public String getPassword();
}
