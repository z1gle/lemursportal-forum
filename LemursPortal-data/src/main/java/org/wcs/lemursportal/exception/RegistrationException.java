/**
 * 
 */
package org.wcs.lemursportal.exception;

/**
 * @author mikajy.hery
 *
 */
public class RegistrationException extends RuntimeException {
	public static final int LOGIN_ALREADY_EXIST_EXCEPTION=1;
	public static final int DATABASE_NOT_READY_EXCEPTION=2;
	public static final int THEMATIQUE_ALREADY_EXIST_EXCEPTION=3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code = 0;
	

	public RegistrationException(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
