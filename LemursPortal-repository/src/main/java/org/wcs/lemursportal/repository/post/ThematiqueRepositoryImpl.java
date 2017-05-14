package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopThematique;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional(readOnly=true)
public class ThematiqueRepositoryImpl implements ThematiqueRepository {
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.ThematiqueRepository#findTopThematique(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TopThematique> findTopThematique(Integer size) {
		StringBuilder qlBuild = new StringBuilder("select t, count(p) as nbPost from Post as p ");
		qlBuild.append("inner join p.thematique t ");
		//qlBuild.append("group by t.id order by nbPost desc ");
		qlBuild.append("group by t.id order by t.creationDate desc ");
		Query query = em.createQuery(qlBuild.toString());
		if(size != null && size > 0){
			query.setMaxResults(size);
		}
		final List<Object[]> results = query.getResultList();
		List<TopThematique> topThematiques = new ArrayList<>();
		for(Object[] result: results){
			Thematique thematique = (Thematique)result[0];
			Long postCount = (Long)result[1];
			topThematiques.add(new TopThematique(thematique, postCount));
		}
		return topThematiques;
	}

}
