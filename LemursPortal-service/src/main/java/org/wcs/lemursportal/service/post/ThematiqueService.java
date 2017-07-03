package org.wcs.lemursportal.service.post;

import org.wcs.lemursportal.model.post.Thematique;
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
	
	/**
	 * 
	 * @param thematiqueId
	 * @param currentLogin
	 */
	public void delete(Integer thematiqueId, String currentLogin);
	
	
}
