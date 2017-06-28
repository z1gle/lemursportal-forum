package org.wcs.lemursportal.repository.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.notification.PrivateMessage;

public interface PrivateMessageRepository {
	/**
	 * 
	 */
	void save(PrivateMessage message);
	/**
	 * 
	 */
	void update(PrivateMessage message);
	/**
	 * 
	 * @param id
	 * @return
	 */
	PrivateMessage findById(Integer id);
	/**
	 * 
	 * @param destinataireId
	 * @param pageable
	 * @return
	 */
	Page<PrivateMessage> findByDestinataire(Integer destinataireId, Pageable pageable);
	/**
	 * 
	 * @param destinataireId
	 * @return
	 */
	Long countByDestinataire(Integer destinataireId);
}
