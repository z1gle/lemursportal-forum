/**
 * 
 */
package org.wcs.lemursportal.model.mail;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * @author mikajy.hery
 *
 */
public class MailQueueMimeMessagePreparator implements MimeMessagePreparator {
	
	private MailQueue mailQueue;

	public MailQueueMimeMessagePreparator(MailQueue mailQueue) {
		super();
		this.mailQueue = mailQueue;
	}

	/* (non-Javadoc)
	 * @see org.springframework.mail.javamail.MimeMessagePreparator#prepare(javax.mail.internet.MimeMessage)
	 */
	@Override
	public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		EmailValidator emailValidator = EmailValidator.getInstance();
		helper.setSubject(mailQueue.getSubject());
//		helper.setFrom(mailFrom);
		//helper.setTo("mikajy401@gmail.com");
		String dest = mailQueue.getDestinataires();
		String[] destItems = StringUtils.split(dest, ';');
		Set<String> tos = new HashSet<>();
		for(String destItem: destItems){
			if(emailValidator.isValid(destItem)){
				tos.add(destItem);
			}
		}
		helper.setTo(tos.toArray(new String[0]));
		String body = mailQueue.getBody();
		helper.setText(body, true);

		// Additionally, let's add a resource as an attachment as well.
		// helper.addAttachment("cutie.png", new
		// ClassPathResource("linux-icon.png"));

	}

}
