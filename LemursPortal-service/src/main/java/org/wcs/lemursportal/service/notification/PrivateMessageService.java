/**
 * 
 */
package org.wcs.lemursportal.service.notification;

import org.wcs.lemursportal.model.notification.PrivateMessage;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author mikajy.hery
 *
 */
public interface PrivateMessageService {
	/**
	 * 
	 * @param privateMessage
	 */
	void setAsRead(Integer privateMessageId, Boolean read);
	/**
	 * 
	 * @param privateMessageId
	 * @param read
	 */
	void setAsRead(Integer privateMessageId);
	
	/**
	 * 
	 * @param message
	 */
	void save(UserInfo currentUser, PrivateMessage message);
}
