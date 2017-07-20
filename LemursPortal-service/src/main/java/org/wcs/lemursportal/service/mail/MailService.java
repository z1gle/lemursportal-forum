/**
 * 
 */
package org.wcs.lemursportal.service.mail;

import java.io.IOException;
import java.util.List;

import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * @author mikajy.hery
 *
 */
public interface MailService {
	
	/**
	 * 
	 * @param thematique
	 * @param destinataires
	 */
	public void sendMail(Thematique thematique, List<UserInfo> destinataires);
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
	void saveMail(Thematique thematique, List<UserInfo> destinataires) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
	
	/**
	 * 
	 * @param question
	 * @param owner
	 * @param thematiqueManager
	 */
	void saveMail(Post question, UserInfo owner, List<UserInfo> thematiqueManager) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;

}
