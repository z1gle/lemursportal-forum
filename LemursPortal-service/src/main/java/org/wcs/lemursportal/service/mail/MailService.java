/**
 * 
 */
package org.wcs.lemursportal.service.mail;

/**
 * @author mikajy.hery
 *
 */
public interface MailService {
	
	
	public void sendMail(String to, String subject, String text);
	
	public void sendMail(String[] tos, String subject, String text);
	
}
