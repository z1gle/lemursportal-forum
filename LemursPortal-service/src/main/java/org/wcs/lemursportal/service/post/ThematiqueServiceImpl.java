package org.wcs.lemursportal.service.post;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.exception.RegistrationException;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueCrudRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;
import org.wcs.lemursportal.service.mail.MailService;
import org.wcs.lemursportal.service.notification.NotificationService;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Service
@Transactional
public class ThematiqueServiceImpl extends
		GenericCRUDServiceImpl<Thematique, Integer> implements
		ThematiqueService {
	
	@Autowired
	private ThematiqueCrudRepository thematiqueCrudRepository;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired 
	NotificationService notificationService;
	@Autowired PostRepository postRepository;
	
	@Autowired MailService mailService;
	
	@Autowired ThematiqueRepository thematiqueRepository;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.common.GenericCRUDServiceImpl#getJpaRepository()
	 */
	@Override
	protected JpaRepository<Thematique, Integer> getJpaRepository() {
		return thematiqueCrudRepository;
	}

	@Override
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public Thematique saveOrUpdate(Thematique thematique) {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.post.ThematiqueService#saveOrUpdate(java.lang.String, org.wcs.lemursportal.model.post.Thematique)
	 */
	@Override
	@PreAuthorize("hasAnyRole('EXPERT','MODERATEUR', 'ADMIN')")
	public Thematique saveOrUpdate(String currentLogin, Thematique thematique) {
		UserInfo currentUser = userInfoService.getByEmail(currentLogin);
		Date now = Calendar.getInstance().getTime();
		if(thematique.getId() != null){
			//Edition
			Thematique persistentEntity = this.findById(thematique.getId());
			persistentEntity.setEditDate(now);
			persistentEntity.setModifiedBy(currentUser);
			persistentEntity.setLibelle(thematique.getLibelle());
			persistentEntity.setDescription(thematique.getDescription());
			persistentEntity.setManagers(thematique.getManagers());
			this.thematiqueCrudRepository.save(persistentEntity);
//			notificationService.saveThematiqueNotification(thematique);
			return persistentEntity;
		}else{
			//Création
			thematique.setCreatedBy(currentUser);
			thematique.setCreationDate(now);
			thematique.setDeleted(false);
			Thematique thematiqueExample = new Thematique();
			thematiqueExample.setLibelle(thematique.getLibelle());
			boolean thematiqueExist = thematiqueCrudRepository.exists(Example.of(thematiqueExample));
			if(thematiqueExist){
				throw new RegistrationException(RegistrationException.LOGIN_ALREADY_EXIST_EXCEPTION);
			}
			thematiqueCrudRepository.save(thematique);
			notificationService.saveThematiqueNotification(thematique);
			return thematique;
		}
	}

	@Override
	public void delete(Integer thematiqueId, String currentLogin) {
		UserInfo currentUser = userInfoService.getByEmail(currentLogin);
		if(currentUser != null){
			Thematique thematique = thematiqueCrudRepository.findOne(thematiqueId);
			thematique.setDeleted(true);
			thematique.setDeletedBy(currentUser.getId());
			thematique.setDeletedDate(new Date());
			postRepository.deletePostByThematique(thematiqueId, currentUser.getId());
		}
	}

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.common.GenericCRUDServiceImpl#findAll()
	 */
	@Override
	public List<Thematique> findAll() {
		return thematiqueRepository.findAll();
	}
}
