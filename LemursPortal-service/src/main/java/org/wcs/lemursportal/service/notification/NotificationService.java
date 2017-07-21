package org.wcs.lemursportal.service.notification;

import java.util.List;

import org.wcs.lemursportal.model.notification.Notification;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface NotificationService {
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Notification> getUserNotifications(Integer userId);
	
	/**
	 * 
	 * @param thematique
	 */
	void saveThematiqueNotification(Thematique thematique);
	/**
	 * 
	 * @param post
	 */
	void savePostNotification(Post post, String postUrl);
	/**
	 * 
	 * @param question
	 */
	void saveNotificationNouvelleQuestion(Post question, String postUrl);
	/**
	 * 
	 * @param commentaire
	 */
	void saveNotificationNouveauCommentaire(Post commentaire);
}
