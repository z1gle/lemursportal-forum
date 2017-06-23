/**
 * 
 */
package org.wcs.lemursportal.service.mail;

import java.util.List;

import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;

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

}
