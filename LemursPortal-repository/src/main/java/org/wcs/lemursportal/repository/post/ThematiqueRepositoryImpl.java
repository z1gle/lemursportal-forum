package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	
	@Autowired ThematiqueCrudRepository thematiqueCrudRepository;
	
	@PersistenceContext
	protected EntityManager em;
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.ThematiqueRepository#findTopThematique(int)
	 */
	@Override
	public Page<TopThematique> findTopThematique(Pageable pageable) {
//		StringBuilder qlBuild = new StringBuilder("select t, count(p) as nbPost from Post as p ");
//		qlBuild.append("inner join p.thematique t ");
//		qlBuild.append("group by t.id order by t.creationDate desc ");
		TypedQuery<Long> countQuery = em.createQuery("select count(t) from Thematique as t where t.deleted<>:deleted", Long.class);
		countQuery.setParameter("deleted", true);
		Long total = countQuery.getSingleResult();
		StringBuilder jpqlBuilder = new StringBuilder("select t, count(q) as nbQuestions from Thematique t ")
			.append("left join t.questions as q ")
			.append("where q.parentId is null and (q.censored is null or q.censored <> :censored) ")
			.append(" and (t.deleted=:notDeleted or t.deleted is null) ")
			.append("group by t.id ")
			.append("order by nbQuestions desc ");
		
		
		/*StringBuilder jpqlBuilder = new StringBuilder("select p.thematique.id, count(p.id) as nbQuestions from Post as p ")
			.append("where p.parentId is null and (p.censored is null or p.censored != :censored) ")
			.append("group by p.thematique.id ")
			.append("order by nbQuestions desc ");*/
		TypedQuery<Tuple> query = em.createQuery(jpqlBuilder.toString(), Tuple.class);
		query.setParameter("censored", true);
		query.setParameter("notDeleted", false);
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
//		Map<Integer, TopThematique> topThematiqueMap = new HashMap<>();
		final List<Tuple> tuples = query.getResultList();
		List<TopThematique> topThematiques = new ArrayList<>();
		for(Tuple tuple: tuples){
			Thematique thematique = (Thematique)tuple.get(0);
			Long postCount = (Long)tuple.get(1);
			TopThematique top = new TopThematique();
			top.setNombreQuestion(postCount);
			top.setThematique(thematique);
//			topThematiqueMap.put(thematiqueId, top);
			topThematiques.add(top);
		}
//		List<Thematique> thematiques = thematiqueCrudRepository.findAll(topThematiqueMap.keySet());
//		for(Thematique t: thematiques){
//			topThematiqueMap.get(t.getId()).setThematique(t);
//		}
		return new PageImpl<>(topThematiques, pageable, total);
	}

	@Override
	public List<TopThematique> findAllThematique() {
		StringBuilder qlBuild = new StringBuilder("SELECT DISTINCT f,   p, count(v), count(q) ");
		qlBuild.append("FROM   Thematique  f ");
		qlBuild.append("left join Post v  on v.thematique.id = f.id ");
		qlBuild.append("left join Post q  on q.thematique.id = f.id ");
		qlBuild.append("LEFT   JOIN Post   p ON p.thematique.id = f.id ");
		//qlBuild.append("inner join fetch p.owner o");
		qlBuild.append(" where (f.deleted=:notDeleted or f.deleted is null) and q.parentId is null ");
		qlBuild.append(" group by f, f.id, p.id ");
		qlBuild.append("ORDER  BY  f.id, p.creationDate DESC");
		
		Query query = em.createQuery(qlBuild.toString());
		query.setParameter("notDeleted", false);
		
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

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.ThematiqueRepository#findByQuestionId(java.lang.Integer)
	 */
	@Override
	public Thematique findByQuestionId(Integer questionId) {
		TypedQuery<Thematique> query = em.createQuery("select t from Post p inner join fetch p.thematique t where p.id=:questionId and (t.deleted=:notDeleted or t.deleted is null)", Thematique.class);
		query.setParameter("questionId", questionId);
		query.setParameter("notDeleted", false);
		Thematique thematique = query.getSingleResult();
		return thematique;
	}

	@Override
	public Thematique findByIdAndFetchManagers(Integer thematiqueId) {
		//TypedQuery<Thematique> query = em.createQuery("select t from Thematique t inner join fetch t.managers where t.id=:thematiqueId and (t.deleted=:notDeleted or t.deleted is null) ", Thematique.class);
		TypedQuery<Thematique> query = em.createQuery("select t from Thematique t where t.id=:thematiqueId and (t.deleted=:notDeleted or t.deleted is null) ", Thematique.class);
		query.setParameter("notDeleted", false);
		query.setParameter("thematiqueId", thematiqueId);
		Thematique thematique = query.getSingleResult();
		return thematique;
	}

	@Override
	public List<Thematique> findAll() {
		TypedQuery<Thematique> query = em.createQuery("select t from Thematique t where t.deleted=:notDeleted or t.deleted is null ", Thematique.class);
		query.setParameter("notDeleted", false);
		List<Thematique> thematiques = query.getResultList();
		return thematiques;
	}

}
