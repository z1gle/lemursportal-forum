package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;
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
			topThematiques.add(new TopThematique(thematique, postCount,(long) 0,null));
		}
		return topThematiques;
	}

	@Override
	public List<TopThematique> findAllThematique() {
		StringBuilder qlBuild = new StringBuilder("SELECT DISTINCT f,   p, count(v), count(q) ");
		qlBuild.append("FROM   Thematique             f ");
		qlBuild.append("left join Post v  on v.thematique.id = f.id ");
		qlBuild.append("left join Post q  on q.thematique.id = f.id ");
		qlBuild.append("LEFT   JOIN Post   p ON p.thematique.id = f.id ");
		//qlBuild.append("inner join fetch p.owner o");
		qlBuild.append(" where q.parentId is null ");
		qlBuild.append(" group by f, f.id, p.id ");
		qlBuild.append("ORDER  BY  f.id, p.creationDate DESC");
		
		Query query = em.createQuery(qlBuild.toString());
		
		
		final List<Object[]> results = query.getResultList();
		List<TopThematique> topThematiques = new ArrayList<>();
		for(Object[] result: results){
			Thematique thematique = (Thematique)result[0];
			Post latestPost = (Post) result[1];
			Long postCount = (Long)result[2];
			Long threadsCount = (Long)result[3];
			topThematiques.add(new TopThematique(thematique, postCount, threadsCount,latestPost));
		}
		return topThematiques;
	}

}
