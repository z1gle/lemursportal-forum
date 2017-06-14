/**
 * 
 */
package org.wcs.lemursportal.service.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.notification.Notification;
import org.wcs.lemursportal.model.notification.NotificationType;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.notification.NotificationRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;

/**
 * @author mikajy.hery
 *
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired NotificationRepository notificationRepository;
	@Autowired ThematiqueRepository thematiqueRepository; 
	@Autowired PostRepository postRepository; 

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.NotificationService#notifierNouvelleQuestion(org.wcs.lemursportal.model.post.Post)
	 */
	@Override
	public void saveNotificationNouvelleQuestion(Post question) {
		Thematique thematique = thematiqueRepository.findByIdAndFetchManagers(question.getThematiqueId());
//		List<Notification> notifications = new ArrayList<>();
		for(UserInfo user: thematique.getManagers()){
			Notification notification = new Notification();
			notification.setNotificationType(NotificationType.NOUVELLE_QUESTION);
			notification.setDate(new Date());
			notification.setQuestion(question);
			notification.setQuestionId(question.getId());
			notification.setThematiqueId(question.getThematiqueId());
			notification.setThematique(question.getThematique());
			notification.setUserId(user.getId());
			notification.setHasBeenRead(false);
			notificationRepository.save(notification);
//			notifications.add(notification);
		}
		

	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.NotificationService#notifierNouveauCommentaire(org.wcs.lemursportal.model.post.Post)
	 */
	@Override
	public void saveNotificationNouveauCommentaire(Post commentaire) {
		Post question = postRepository.getPostsAndFetchOwner(commentaire.getParentId());
		Thematique thematique = thematiqueRepository.findByIdAndFetchManagers(question.getThematiqueId());
		List<UserInfo> users = new ArrayList<>(thematique.getManagers());
		users.add(question.getOwner());
		for(UserInfo user: users){
			Notification notification = new Notification();
			notification.setNotificationType(NotificationType.NOUVEAU_COMMENTAIRE);
			notification.setDate(new Date());
			notification.setQuestion(question);
			notification.setQuestionId(question.getId());
			notification.setHasBeenRead(false);
			notification.setThematiqueId(question.getThematiqueId());
			notification.setThematique(question.getThematique());
			notification.setUserId(user.getId());
			notificationRepository.save(notification);
//			notifications.add(notification);
		}

	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.NotificationService#savePostNotification(org.wcs.lemursportal.model.post.Post)
	 */
	@Override
	public void savePostNotification(Post post) {
		if(post == null) return;
		if(post.getParentId() == null){
			saveNotificationNouvelleQuestion(post);
		}else{
			saveNotificationNouveauCommentaire(post);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.NotificationService#saveThematiqueNotification(org.wcs.lemursportal.model.post.Thematique)
	 */
	@Override
	public void saveThematiqueNotification(Thematique thematique) {
		if(thematique != null){
			for(UserInfo manager: thematique.getManagers()){
				Notification notification = new Notification();
				notification.setNotificationType(NotificationType.NOUVELLE_THEMATIQUE);
				notification.setDate(new Date());
				notification.setQuestion(null);
				notification.setQuestionId(null);
				notification.setHasBeenRead(false);
				notification.setThematiqueId(thematique.getId());
				notification.setThematique(thematique);
				notification.setUserId(manager.getId());
				notificationRepository.save(notification);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.NotificationService#getUserNotifications(java.lang.Integer)
	 */
	@Override
	public List<Notification> getUserNotifications(Integer userId) {
		List<Notification> notifications = notificationRepository.findAllByUser(userId);
		for(Notification n: notifications){
			n.setHasBeenRead(true);
		}
		return notifications;
	}

}
