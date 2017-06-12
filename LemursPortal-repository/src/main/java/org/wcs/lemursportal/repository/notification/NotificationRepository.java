package org.wcs.lemursportal.repository.notification;

import java.util.List;

import org.wcs.lemursportal.model.notification.Notification;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface NotificationRepository {
	/**
	 * 
	 * @param notification
	 * @return
	 */
	public void save(Notification notification);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Notification> findAllByUser(Integer userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Long countByUser(Integer userId);
	
	/**
	 * 
	 * @param userId
	 */
	public int deleteByUser(Integer userId);
}
