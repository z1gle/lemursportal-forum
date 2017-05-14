package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.post.TopQuestion;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Repository
@Transactional(readOnly=true)
public class PostRepositoryImpl implements PostRepository {
	
	@PersistenceContext(unitName="lemursportalPUnit")
	protected EntityManager em;
	
	@Autowired private PostCrudRepository postCrudRepository;

	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getLastOrphanPosts(java.lang.Integer)
	 */
	@Override
	public Page<Post> getLastestPosts(Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Post> select = builder.createQuery(Post.class);
		Root<Post> from = select.from(Post.class);
		select.select(from);
		select.where(from.get("parent").isNull());
		select.orderBy(builder.desc(from.get("creationDate")));
		TypedQuery<Post> typedQuery = em.createQuery(select);
		if(pageable != null){
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
		}
		List<Post> posts = typedQuery.getResultList();
		return new PageImpl<Post>(posts);
	}


	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getMostViewedPost(org.springframework.data.domain.Pageable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<TopQuestion> getMostViewedPosts(Pageable pageable) {
		StringBuilder jpql = new StringBuilder("select p, count(v.id) as nbVue from PostView as v");
		jpql.append(" inner join v.post as p ");
		jpql.append(" inner join fetch p.owner u ");
		jpql.append(" group by p.id, u.id");
		jpql.append(" order by nbVue desc");
		Query query = em.createQuery(jpql.toString());
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		final List<Object[]> results = query.getResultList();
		final Map<Integer, TopQuestion> mostViewedPostMap = new HashMap<>();
		List<TopQuestion> mostViewedPost = new ArrayList<>();
		for(Object[] array: results){
			Post p = (Post)array[0];
			//p.getOwner().getNom();//juste pour s'assurer qu'on a bien l'objet owner
			Long nbVue = (Long)array[1];
			TopQuestion topQuestion = new TopQuestion();
			topQuestion.setQuestion(p);
			topQuestion.setNbVue(nbVue);
			topQuestion.setResponsable(p.getOwner());
			mostViewedPost.add(topQuestion);
			mostViewedPostMap.put(p.getId(), topQuestion);
		}
		populateNbResponseAndLastResponse(mostViewedPostMap);
		return new PageImpl<>(mostViewedPost);
	}


	private void populateNbResponseAndLastResponse(Map<Integer, TopQuestion> topQuestionMap) {
		StringBuilder jpql = new StringBuilder("select p.parentId, count(p.id) as nbResponse, lastResponse ")
				.append(" from Post p, Post lastResponse inner join fetch lastResponse.owner o ")
				.append(" where p.parentId is not null and p.parentId in (:parentIds) ")
				.append(" group by p.parentId, lastResponse.id, o.id having lastResponse.id=max(p.id) ");
		TypedQuery<Object[]> typedQuery = em.createQuery(jpql.toString(), Object[].class);
		typedQuery.setParameter("parentIds", topQuestionMap.keySet());
		List<Object[]> results = typedQuery.getResultList();
		for(Object[] array: results){
			Integer parentId = (Integer)array[0];
			Long nbResponse = (Long)array[1];
			Post lastResponse = (Post)array[2];
			topQuestionMap.get(parentId).setDerniereReponse(lastResponse);
			topQuestionMap.get(parentId).setNbReponse(nbResponse);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<TopQuestion> getPostByThematique(Pageable pageable,Integer idThematique) {
		StringBuilder jpql = new StringBuilder("select p, count(v.id) as nbVue from PostView as v");
		jpql.append(" inner join v.post as p ");
		jpql.append(" inner join fetch p.owner u ");
		jpql.append(" inner join fetch p.thematique t ");
		jpql.append(" where t.id = "+idThematique);
		jpql.append(" group by p.id , t.id , u.id");
		jpql.append(" order by nbVue desc");
		Query query = em.createQuery(jpql.toString());
		if(pageable != null){
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		final List<Object[]> results = query.getResultList();
		final Map<Integer, TopQuestion> mostViewedPostMap = new HashMap<>();
		List<TopQuestion> mostViewedPost = new ArrayList<>();
		for(Object[] array: results){
			Post p = (Post)array[0];
			//p.getOwner().getNom();//juste pour s'assurer qu'on a bien l'objet owner
			Long nbVue = (Long)array[1];
			TopQuestion topQuestion = new TopQuestion();
			topQuestion.setQuestion(p);
			topQuestion.setNbVue(nbVue);
			topQuestion.setResponsable(p.getOwner());
			mostViewedPost.add(topQuestion);
			mostViewedPostMap.put(p.getId(), topQuestion);
		}
		populateNbResponseAndLastResponse(mostViewedPostMap);
		return new PageImpl<>(mostViewedPost);
	}
	
	

}
