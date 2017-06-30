/**
 * 
 */
package org.wcs.lemursportal.service.notification;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<PrivateMessage> findByDestinataire(Integer destinataireId, Pageable pageable) {
		Page<PrivateMessage> page = privateMessageRepository.findByDestinataire(destinataireId, pageable);
		//on va marquer les messages comme lu
		for(PrivateMessage pm: page.getContent()){
			pm.setReadByDestinataire(true);
			privateMessageRepository.update(pm);
		}
		return page;
	}

	@Override
	public PrivateMessage findById(Integer messageId, Integer destinataireId) {
		PrivateMessage privateMessage = privateMessageRepository.findById(messageId);
		if(privateMessage != null && privateMessage.getDestinataireId().equals(destinataireId)){
			//Seule le destinataire peut visualiser le contenu du message
			privateMessage.setReadByDestinataire(true);
			return privateMessage;
		}
		return null;
	}

}
