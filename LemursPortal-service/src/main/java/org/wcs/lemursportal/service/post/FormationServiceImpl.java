package org.wcs.lemursportal.service.post;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.Formation;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.FormationCrudRepository;
import org.wcs.lemursportal.repository.post.FormationRepository;
import org.wcs.lemursportal.repository.user.UserRepository;

/**
 * @author z
 *
 */
@Service
@Transactional(readOnly=true)
public class FormationServiceImpl implements FormationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormationServiceImpl.class);

	@Autowired 
	private FormationRepository formationRepository;
	
	@Autowired 
	private FormationCrudRepository formationCrudRepository;
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.service.post.ThematiqueService#getTopQuestions(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Formation> getFormations(Pageable pageable) {
		Page<Formation> page = formationRepository.getLastestFormations(pageable);
		LOGGER.info("========" + page.getContent().get(0).getOwner());
		//TODO recuperer le nombre de reponse de chaque post (=> remplir les informations de chaque TopQuestion)
		return page;
	}

	
	@Transactional
	@Override
	public void save(Formation formation, String email) {
		UserInfo user = userRepository.findByEmail(email);
		formation.setOwner(user);
		formation.setOwnerId(user.getId());
		Date date = new Date();
		formation.setCreationDate(date);
		formation.setLastEdit(date);
		formationCrudRepository.save(formation);
	}
	
	@Override
	public Formation getFormation(Long id) {
		return formationCrudRepository.findOne(id);
	}

	@Override
	@Transactional
	public void update(Formation formation, String email) {
		//checking owner
		UserInfo user = userRepository.findByEmail(email);
		Formation oldFormation = getFormation(formation.getId());
		if(oldFormation.getOwnerId().equals(user.getId())) {
			oldFormation.setTitle(formation.getTitle());
			oldFormation.setDescription(formation.getDescription());
			oldFormation.setBody(formation.getBody());
			oldFormation.setLastEdit(new Date());
			formationCrudRepository.save(oldFormation);
		}
	}


	@Override
	@Transactional
	public void deleteById(Long id, String email) {
		UserInfo user = userRepository.findByEmail(email);
		Formation oldFormation = getFormation(id);
		if(oldFormation.getOwnerId().equals(user.getId())) {
			formationCrudRepository.delete(id);
		}
		
	}

	@Override
	@Transactional
	public Formation getFormation(Long formationId, String email) {
		Formation formation = getFormation(formationId);
		
		if(email == null || !formation.getOwnerId().equals(userRepository.findByEmail(email).getId())) {
			int viewCount = formation.getViewCount();
			formation.setViewCount(++viewCount);
			formationCrudRepository.save(formation);
		}
		return formation;
	}
}
