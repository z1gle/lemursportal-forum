package org.wcs.lemursportal.service.notification;

import org.wcs.lemursportal.model.post.Post;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface NotificationService {
	/**
	 * 
	 * @param post
	 */
	void savePostNotification(Post post);
	/**
	 * 
	 * @param question
	 */
	void saveNotificationNouvelleQuestion(Post question);
	/**
	 * 
	 * @param commentaire
	 */
	void saveNotificationNouveauCommentaire(Post commentaire);
}
