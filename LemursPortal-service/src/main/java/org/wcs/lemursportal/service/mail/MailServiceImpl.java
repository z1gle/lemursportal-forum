/**
 * 
 */
package org.wcs.lemursportal.service.mail;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author mikajy.hery
 *
 */
@Service
public class MailServiceImpl implements MailService {
	
	@Resource(name="mailSender")
	private JavaMailSender javaMailSender;
	
	
	private String mailFrom = "no-reply@lemursportal-wcs.org";

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.mail.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(mailFrom);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		javaMailSender.send(mailMessage);
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.mail.MailService#sendMail(java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String[] tos, String subject, String message) {
		// TODO Auto-generated method stub

	}

}
