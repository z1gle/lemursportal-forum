/**
 * 
 */
package org.wcs.lemursportal.service.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
@Transactional(propagation=Propagation.REQUIRES_NEW)
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
			notification.setThematiqueId(question.getThematiqueId());
			notification.setThematique(question.getThematique());
			notification.setUserId(user.getId());
			notificationRepository.save(notification);
//			notifications.add(notification);
		}

	}

}
