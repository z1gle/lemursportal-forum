package org.wcs.lemursportal.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.lemursportal.model.Formation;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author z
 *
 */
public interface FormationRepository {
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	Page<Formation> getLastestFormations(Pageable pageable);
	
}
