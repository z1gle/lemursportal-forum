/**
 * 
 */
package org.wcs.lemursportal.service.mail;

import java.io.IOException;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import java.util.HashMap;

/**
 * @author mikajy.hery
 *
 */
public interface MailService {
	
	/**
	 * 
	 * @param thematique
	 * @param destinataires
         * @param postDetail
	 */
	public void sendMail(Thematique thematique, List<UserInfo> destinataires,
                HashMap<String, String> postDetail);
	/**
	 * 
	 * @param question
	 * @param owner
	 */
	public void sendMail(Post question, UserInfo owner, List<UserInfo> thematiqueManager);
	
	/**
	 * 
	 * @param thematique
	 * @param destinataires
	 */
	void saveMail(Thematique thematique, List<UserInfo> destinataires, String thematiqueUrl) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
	
	/**
	 * 
	 * @param question
	 * @param owner
	 * @param thematiqueManager
	 */
	void saveMail(Post question, UserInfo owner, List<UserInfo> thematiqueManager, String questionUrl) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
	
//	public void sendMails();
	
	public void sendEmail(SimpleMailMessage passwordResetEmail);

}
