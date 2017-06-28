/**
 * 
 */
package org.wcs.lemursportal.service.notification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.notification.PrivateMessage;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.notification.PrivateMessageRepository;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author mikajy.hery
 *
 */
@Service
@Transactional
public class PrivateMessageServiceImpl implements PrivateMessageService {
	
	@Autowired PrivateMessageRepository privateMessageRepository;
	@Autowired UserInfoService userInfoService;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.notification.PrivateMessageService#update(org.wcs.lemursportal.model.notification.PrivateMessage)
	 */
	@Override
	public void setAsRead(Integer privateMessageId, Boolean read) {
		PrivateMessage entity = privateMessageRepository.findById(privateMessageId);
		entity.setReadByDestinataire(read);
	}

	@Override
	public void setAsRead(Integer privateMessageId) {
		setAsRead(privateMessageId, true);
	}

	@Override
	public void save(UserInfo currentUser, PrivateMessage message) {
		message.setDate(new Date());
		message.setReadByDestinataire(false);
		message.setSender(currentUser);
		message.setSenderId(currentUser.getId());
		privateMessageRepository.save(message);
	}

}
