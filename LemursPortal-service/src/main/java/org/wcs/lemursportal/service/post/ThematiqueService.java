package org.wcs.lemursportal.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.service.common.GenericCRUDService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
public interface ThematiqueService extends GenericCRUDService<Thematique, Integer> {
	/**
	 * 
	 * @param currentLogin
	 * @param thematique
	 * @return
	 */
	public Thematique saveOrUpdate(String currentLogin, Thematique thematique) ;
	
	
}
