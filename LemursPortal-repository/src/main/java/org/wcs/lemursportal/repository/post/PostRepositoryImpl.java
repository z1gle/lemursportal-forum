package org.wcs.lemursportal.repository.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
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
		Page<TopQuestion> page = getTopQuestions(idThematique, pageable);
		return page;
	}


	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#countQuestion()
	 */
	private Long countQuestions(Integer idThematique) {
		StringBuilder jpql = new StringBuilder("select count(p.id) from Post p where p.parentId is null and (p.censored is null or p.censored != :censored) ");
		if(idThematique != null){
			jpql.append("and p.thematique.id=:thematiqueId ");
		}
		Query query = em.createQuery(jpql.toString(), Long.class);
		query.setParameter("censored", Boolean.TRUE);
		if(idThematique != null){
			query.setParameter("thematiqueId", idThematique);
		}
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getMostRespondedPosts(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<TopQuestion> getTopQuestions(Pageable pageable) {
		return getTopQuestions(null, pageable);
	}


	private Page<TopQuestion> getTopQuestions(Integer idThematique, Pageable pageable) {
		Long total = countQuestions(idThematique);
		StringBuilder jpql = new StringBuilder("select max(p.id) as lastResponseId, count(p.id) as nbResponse, p.parentId as questionId ")
				.append(" from Post p where p.parentId is not null and (p.censored is null or p.censored != :censored) ");
		if(idThematique != null){
			jpql.append("and p.thematique.id=:thematiqueId ");
		}
		jpql.append(" group by p.parentId order by nbResponse desc ");
		
		TypedQuery<Tuple> typedQuery = em.createQuery(jpql.toString(), Tuple.class);
		typedQuery.setParameter("censored", Boolean.TRUE);
		if(idThematique != null){
			typedQuery.setParameter("thematiqueId", idThematique);
		}
		if(pageable != null){
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
		}
		List<TopQuestion> topQuestions = new ArrayList<>();//pour garder l'ordre des resultats
		Map<Integer, TopQuestion> topQuestionMap = new TreeMap<>();
		Map<Integer, TopQuestion> lastResponseMap = new TreeMap<>();
		List<Tuple> results = typedQuery.getResultList();
		for(Tuple tuple: results){
			Integer lastResponseId = (Integer)tuple.get(0);
			Long nbResponse = (Long)tuple.get(1);
			Integer questionId = (Integer)tuple.get(2);
			TopQuestion topQuestion = new TopQuestion();
			topQuestion.setIdDerniereReponse(lastResponseId);
			topQuestion.setIdQuestion(questionId);
			topQuestion.setNbReponse(nbResponse);
			topQuestionMap.put(questionId, topQuestion);
			lastResponseMap.put(lastResponseId, topQuestion);
			topQuestions.add(topQuestion);
		}
		//On recupère les nombre de vue de chaque reponse
		populateNombreDeVue(topQuestionMap);
		//On recupère les informations sur les question et sur leurs dernieres reponse respective
		Set<Integer> questionAndResponseIds = new java.util.HashSet<>(topQuestionMap.keySet());
		questionAndResponseIds.addAll(lastResponseMap.keySet());
		List<Post> lastResponsePosts = getPostsAndFetchOwner(questionAndResponseIds);
		for(Post post: lastResponsePosts){
			if(lastResponseMap.containsKey(post.getId())){
				lastResponseMap.get(post.getId()).setDerniereReponse(post);
			}else{
				topQuestionMap.get(post.getId()).setQuestion(post);
				topQuestionMap.get(post.getId()).setResponsable(post.getOwner());
			}
			
		}
		return new PageImpl<>(topQuestions, pageable, total);
	}
	
	public List<Post> getPostsAndFetchOwner(Set<Integer> postIds){
		List<Post> posts = new ArrayList<>();
		if(postIds != null && !postIds.isEmpty()){
			StringBuilder jpql = new StringBuilder("select p from Post p ")
				.append("inner join fetch p.owner ")
				.append(" where p.id in (:ids)");
			TypedQuery<Post> query = em.createQuery(jpql.toString(), Post.class);
			query.setParameter("ids", postIds);
			posts = query.getResultList();
		}
		return posts;
	} 
	
	
	public List<Post> getResponsesAndFetchOwner(Integer parentId){
		List<Post> posts = new ArrayList<>();
		
		StringBuilder jpql = new StringBuilder("select p from Post p ")
			.append("left join fetch p.owner ")
			.append(" where p.parentId = :id)");
		TypedQuery<Post> query = em.createQuery(jpql.toString(), Post.class);
		query.setParameter("id", parentId);
		posts = query.getResultList();
		System.out.println("zanany : " +posts.size());
		return posts;
	} 
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getMostViewedPost(org.springframework.data.domain.Pageable)
	 */
	private void populateNombreDeVue(Map<Integer, TopQuestion> topQuestionMap) {
		if(topQuestionMap == null || topQuestionMap.isEmpty()) return;
		StringBuilder jpql = new StringBuilder("select v.postId, count(v.id) as nbVue from PostView as v ")
//		.append(" inner join v.post as p ")
//		.append(" inner join fetch p.owner u ")
		.append("where v.postId in (:questions) ")
		.append(" group by v.postId ");
		TypedQuery<Tuple> typedQuery = em.createQuery(jpql.toString(), Tuple.class);
		typedQuery.setParameter("questions", topQuestionMap.keySet());
		final List<Tuple> results = typedQuery.getResultList();
		final Map<Integer, TopQuestion> mostViewedPostMap = new HashMap<>();
		List<TopQuestion> mostViewedPost = new ArrayList<>();
		for(Tuple tuple: results){
			Integer questionId = (Integer)tuple.get(0);
			Long nbVue = (Long)tuple.get(1);
			TopQuestion topQuestion = topQuestionMap.get(questionId);
			topQuestion.setNbVue(nbVue);
			mostViewedPost.add(topQuestion);
			mostViewedPostMap.put(questionId, topQuestion);
		}
	}
	
	
	public void insert(Post p){
		em.persist(p);
	}
	
	/* (non-Javadoc)
	 * @see org.wcs.lemursportal.repository.post.PostRepository#getMostViewedPost(org.springframework.data.domain.Pageable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Post> search(Pageable pageable,String pattern) {
		StringBuilder jpql = new StringBuilder("select p ")
		.append(" from Post p  inner join fetch p.owner u ")
		.append(" where ( p.body LIKE '%"+ pattern +"%' "
				+ " OR p.title LIKE '%" +pattern+"%'  " 
				+ " OR p.thematique.description LIKE '%" +pattern+"%'  " 
				+ "    ) ");
		//System.out.println(jpql.toString());
		TypedQuery<Post> typedQuery = em.createQuery(jpql.toString(), Post.class).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize());
		return new PageImpl<>( typedQuery.getResultList());
	}
	

}
