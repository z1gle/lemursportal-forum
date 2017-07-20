/**
 * 
 */
package org.wcs.lemursportal.repository.mail;

import java.util.List;

import org.wcs.lemursportal.model.mail.MailQueue;

/**
 * @author mikajy.hery
 *
 */
public interface MailQueueRepository {
	/**
	 * 
	 * @param queue
	 */
	void save(MailQueue queue);
	
	/**
	 * 
	 * @param queue
	 */
	void update(MailQueue queue);
	
	/**
	 * 
	 * @return
	 */
	List<MailQueue> findAllNotSent();
}
