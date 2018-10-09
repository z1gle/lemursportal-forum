/**
 *
 */
package org.wcs.lemursportal.repository.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.post.MetadataUtilisateur;

/**
 * @author mikajy.hery
 *
 */
@Repository
@Transactional(readOnly = true)
public class MetadataUtilisateurRepositoryImpl implements MetadataUtilisateurRepository {

    @Autowired
    private BaseAssociationCrudRepository baseAssociationCrudRepository;

    @PersistenceContext(unitName = "lemursportalPUnit")
    protected EntityManager em;        

    @Override
    public Page<MetadataUtilisateur> findAll(Pageable pageable) {
        String qry = "select * from metadata_user_user order by year desc";
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        return new PageImpl<>(query.getResultList());
    }

    @Override
    public Page<MetadataUtilisateur> findAll(Pageable pageable, MetadataUtilisateur metadata) {
        String qry = "select * from metadata_user_user d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
            query.setParameter("id", metadata.getId());
            List<MetadataUtilisateur> results = query.getResultList();
            return new PageImpl<>(results);
        }
        Boolean contributor = Boolean.FALSE;
        Boolean coverage = Boolean.FALSE;
        Boolean creator = Boolean.FALSE;
        Boolean date = Boolean.FALSE;
        Boolean description = Boolean.FALSE;
        Boolean fileFormat = Boolean.FALSE;
        Boolean format = Boolean.FALSE;
        Boolean idDocument = Boolean.FALSE;
        Boolean idThematique = Boolean.FALSE;
        Boolean identifier = Boolean.FALSE;
        Boolean language = Boolean.FALSE;
        Boolean publisher = Boolean.FALSE;
        Boolean relation = Boolean.FALSE;
        Boolean rights = Boolean.FALSE;
        Boolean source = Boolean.FALSE;
        Boolean subject = Boolean.FALSE;
        Boolean title = Boolean.FALSE;
        Boolean type = Boolean.FALSE;
        Boolean url = Boolean.FALSE;
        Boolean year = Boolean.FALSE;
        Boolean bibliographicResource = Boolean.FALSE;
        Boolean idUtilisateur = Boolean.FALSE;
        if (metadata.getContributor() != null && !metadata.getContributor().isEmpty()) {
            qry += " and d.contributor ilike :contributor";
            contributor = Boolean.TRUE;
        }
        if (metadata.getCoverage() != null && !metadata.getCoverage().isEmpty()) {
            qry += " and d.coverage ilike :coverage";
            coverage = Boolean.TRUE;
        }
        if (metadata.getCreator() != null && !metadata.getCreator().isEmpty()) {
            qry += " and d.creator ilike :creator";
            creator = Boolean.TRUE;
        }
        if (metadata.getDate() != null && !metadata.getDate().isEmpty()) {
            qry += " and d.date_publication ilike :date";
            date = Boolean.TRUE;
        }
        if (metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            qry += " and d.description ilike :description";
            description = Boolean.TRUE;
        }
        if (metadata.getFileFormat() != null && !metadata.getFileFormat().isEmpty()) {
            qry += " and d.file_format ilike :fileFormat";
            fileFormat = Boolean.TRUE;
        }
        if (metadata.getFormat() != null && !metadata.getFormat().isEmpty()) {
            qry += " and d.format ilike :format";
            format = Boolean.TRUE;
        }
        if (metadata.getIdDocument() != null && metadata.getIdDocument() != 0) {
            qry += " and d.id_document = :idDocument";
            idDocument = Boolean.TRUE;
        }
        if (metadata.getIdUtilisateur() != null && metadata.getIdUtilisateur() != 0) {
            qry += " and d.id_utilisateur = :idUtilisateur";
            idUtilisateur = Boolean.TRUE;
        }
        if (metadata.getIdentifier() != null && !metadata.getIdentifier().isEmpty()) {
            qry += " and d.identifier ilike :identifier";
            identifier = Boolean.TRUE;
        }
        if (metadata.getLanguage() != null && !metadata.getLanguage().isEmpty()) {
            qry += " and d.language ilike :language";
            language = Boolean.TRUE;
        }
        if (metadata.getPublisher() != null && !metadata.getPublisher().isEmpty()) {
            qry += " and d.publisher ilike :publisher";
            publisher = Boolean.TRUE;
        }
        if (metadata.getRelation() != null && !metadata.getRelation().isEmpty()) {
            qry += " and d.relation ilike :relation";
            relation = Boolean.TRUE;
        }
        if (metadata.getRights() != null && !metadata.getRights().isEmpty()) {
            qry += " and d.rights ilike :rights";
            rights = Boolean.TRUE;
        }
        if (metadata.getSource() != null && !metadata.getSource().isEmpty()) {
            qry += " and d.source ilike :source";
            source = Boolean.TRUE;
        }
        if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            qry += " and d.subject ilike :subject";
            subject = Boolean.TRUE;
        }
        if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            qry += " and d.title ilike :title";
            title = Boolean.TRUE;
        }
        if (metadata.getType() != null && !metadata.getType().isEmpty()) {
            qry += " and d.type = :type";
            type = Boolean.TRUE;
        }
        if (metadata.getUrl() != null && !metadata.getUrl().isEmpty()) {
            qry += " and d.url ilike :url";
            url = Boolean.TRUE;
        }
        if (metadata.getYear() != null && !metadata.getYear().isEmpty()) {
            qry += " and d.year ilike:year";
            year = Boolean.TRUE;
        }
        if (metadata.getBibliographicResource() != null && !metadata.getBibliographicResource().isEmpty()) {
            qry += " and d.bibliographic_resource ilike:bibliographic_resource";
            bibliographicResource = Boolean.TRUE;
        }
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        if (contributor) {
            query.setParameter("contributor", "%" + metadata.getContributor() + "%");
        }
        if (coverage) {
            query.setParameter("coverage", "%" + metadata.getCoverage() + "%");
        }
        if (creator) {
            query.setParameter("creator", "%" + metadata.getCreator() + "%");
        }
        if (date) {
            query.setParameter("date", "%" + metadata.getDate() + "%");
        }
        if (description) {
            query.setParameter("description", "%" + metadata.getDescription() + "%");
        }
        if (fileFormat) {
            query.setParameter("fileFormat", "%" + metadata.getFileFormat() + "%");
        }
        if (format) {
            query.setParameter("format", "%" + metadata.getFormat() + "%");
        }
        if (idDocument) {
            query.setParameter("idDocument", metadata.getIdDocument());
        }
        if (identifier) {
            query.setParameter("identifier", "%" + metadata.getIdentifier() + "%");
        }
        if (language) {
            query.setParameter("language", "%" + metadata.getLanguage() + "%");
        }
        if (publisher) {
            query.setParameter("publisher", "%" + metadata.getPublisher() + "%");
        }
        if (relation) {
            query.setParameter("relation", "%" + metadata.getRelation() + "%");
        }
        if (rights) {
            query.setParameter("rights", "%" + metadata.getRights() + "%");
        }
        if (source) {
            query.setParameter("source", "%" + metadata.getSource() + "%");
        }
        if (subject) {
            query.setParameter("subject", "%" + metadata.getSubject() + "%");
        }
        if (title) {
            query.setParameter("title", "%" + metadata.getTitle() + "%");
        }
        if (type) {
            query.setParameter("type", metadata.getType());
        }
        if (url) {
            query.setParameter("url", "%" + metadata.getUrl() + "%");
        }
        if (year) {
            query.setParameter("year", "%" + metadata.getYear() + "%");
        }
        if (bibliographicResource) {
            query.setParameter("bibliographic_resource", "%" + metadata.getBibliographicResource() + "%");
        }
        if (idUtilisateur) {
            query.setParameter("idUtilisateur", metadata.getIdUtilisateur());
        }
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<MetadataUtilisateur> findAllExact(Pageable pageable, MetadataUtilisateur metadata) {
        String qry = "select * from metadata_user_user d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
            query.setParameter("id", metadata.getId());
            List<MetadataUtilisateur> results = query.getResultList();
            return new PageImpl<>(results);
        }
        Boolean contributor = Boolean.FALSE;
        Boolean coverage = Boolean.FALSE;
        Boolean creator = Boolean.FALSE;
        Boolean date = Boolean.FALSE;
        Boolean description = Boolean.FALSE;
        Boolean fileFormat = Boolean.FALSE;
        Boolean format = Boolean.FALSE;
        Boolean idDocument = Boolean.FALSE;
        Boolean idThematique = Boolean.FALSE;
        Boolean identifier = Boolean.FALSE;
        Boolean language = Boolean.FALSE;
        Boolean publisher = Boolean.FALSE;
        Boolean relation = Boolean.FALSE;
        Boolean rights = Boolean.FALSE;
        Boolean source = Boolean.FALSE;
        Boolean subject = Boolean.FALSE;
        Boolean title = Boolean.FALSE;
        Boolean type = Boolean.FALSE;
        Boolean url = Boolean.FALSE;
        Boolean year = Boolean.FALSE;
        Boolean bibliographicResource = Boolean.FALSE;
        Boolean idUtilisateur = Boolean.FALSE;
        if (metadata.getContributor() != null && !metadata.getContributor().isEmpty()) {
            qry += " and d.contributor = :contributor";
            contributor = Boolean.TRUE;
        }
        if (metadata.getCoverage() != null && !metadata.getCoverage().isEmpty()) {
            qry += " and d.coverage = :coverage";
            coverage = Boolean.TRUE;
        }
        if (metadata.getCreator() != null && !metadata.getCreator().isEmpty()) {
            qry += " and d.creator = :creator";
            creator = Boolean.TRUE;
        }
        if (metadata.getDate() != null && !metadata.getDate().isEmpty()) {
            qry += " and d.date_publication = :date";
            date = Boolean.TRUE;
        }
        if (metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            qry += " and d.description = :description";
            description = Boolean.TRUE;
        }
        if (metadata.getFileFormat() != null && !metadata.getFileFormat().isEmpty()) {
            qry += " and d.file_format = :fileFormat";
            fileFormat = Boolean.TRUE;
        }
        if (metadata.getFormat() != null && !metadata.getFormat().isEmpty()) {
            qry += " and d.format = :format";
            format = Boolean.TRUE;
        }
        if (metadata.getIdDocument() != null && metadata.getIdDocument() != 0) {
            qry += " and d.id_document = :idDocument";
            idDocument = Boolean.TRUE;
        }
        if (metadata.getIdUtilisateur() != null && metadata.getIdUtilisateur() != 0) {
            qry += " and d.id_utilisateur = :idUtilisateur";
            idUtilisateur = Boolean.TRUE;
        }
        if (metadata.getIdentifier() != null && !metadata.getIdentifier().isEmpty()) {
            qry += " and d.identifier = :identifier";
            identifier = Boolean.TRUE;
        }
        if (metadata.getLanguage() != null && !metadata.getLanguage().isEmpty()) {
            qry += " and d.language = :language";
            language = Boolean.TRUE;
        }
        if (metadata.getPublisher() != null && !metadata.getPublisher().isEmpty()) {
            qry += " and d.publisher = :publisher";
            publisher = Boolean.TRUE;
        }
        if (metadata.getRelation() != null && !metadata.getRelation().isEmpty()) {
            qry += " and d.relation = :relation";
            relation = Boolean.TRUE;
        }
        if (metadata.getRights() != null && !metadata.getRights().isEmpty()) {
            qry += " and d.rights = :rights";
            rights = Boolean.TRUE;
        }
        if (metadata.getSource() != null && !metadata.getSource().isEmpty()) {
            qry += " and d.source = :source";
            source = Boolean.TRUE;
        }
        if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            qry += " and d.subject = :subject";
            subject = Boolean.TRUE;
        }
        if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            qry += " and d.title = :title";
            title = Boolean.TRUE;
        }
        if (metadata.getType() != null && !metadata.getType().isEmpty()) {
            qry += " and d.type = :type";
            type = Boolean.TRUE;
        }
        if (metadata.getUrl() != null && !metadata.getUrl().isEmpty()) {
            qry += " and d.url = :url";
            url = Boolean.TRUE;
        }
        if (metadata.getYear() != null && !metadata.getYear().isEmpty()) {
            qry += " and d.year =:year";
            year = Boolean.TRUE;
        }
        if (metadata.getBibliographicResource() != null && !metadata.getBibliographicResource().isEmpty()) {
            qry += " and d.bibliographic_resource =:bibliographic_resource";
            bibliographicResource = Boolean.TRUE;
        }
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        if (contributor) {
            query.setParameter("contributor", metadata.getContributor());
        }
        if (coverage) {
            query.setParameter("coverage", metadata.getCoverage());
        }
        if (creator) {
            query.setParameter("creator", metadata.getCreator());
        }
        if (date) {
            query.setParameter("date", metadata.getDate());
        }
        if (description) {
            query.setParameter("description", metadata.getDescription());
        }
        if (fileFormat) {
            query.setParameter("fileFormat", metadata.getFileFormat());
        }
        if (format) {
            query.setParameter("format", metadata.getFormat());
        }
        if (idDocument) {
            query.setParameter("idDocument", metadata.getIdDocument());
        }
        if (identifier) {
            query.setParameter("identifier", metadata.getIdentifier());
        }
        if (language) {
            query.setParameter("language", metadata.getLanguage());
        }
        if (publisher) {
            query.setParameter("publisher", metadata.getPublisher());
        }
        if (relation) {
            query.setParameter("relation", metadata.getRelation());
        }
        if (rights) {
            query.setParameter("rights", metadata.getRights());
        }
        if (source) {
            query.setParameter("source", metadata.getSource());
        }
        if (subject) {
            query.setParameter("subject", metadata.getSubject());
        }
        if (title) {
            query.setParameter("title", metadata.getTitle());
        }
        if (type) {
            query.setParameter("type", metadata.getType());
        }
        if (url) {
            query.setParameter("url", metadata.getUrl());
        }
        if (year) {
            query.setParameter("year", metadata.getYear());
        }
        if (bibliographicResource) {
            query.setParameter("bibliographic_resource", metadata.getBibliographicResource());
        }
        if (idUtilisateur) {
            query.setParameter("idUtilisateur", metadata.getIdUtilisateur());
        }
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<MetadataUtilisateur> findAll(Pageable pageable, String type, Integer idThematique) {
        String qry = "select distinct d.* from metadata_user d join association_metadata_topic a on d.id = a.id_metadata where a.id_topic = :idTopic and d.type = :type";
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Long conter(String type, Integer idThematique) {
        if (idThematique == null) {
            String qry = "select count(c.*) from (select distinct d.* from metadata_user d join association_metadata_topic a on d.id = a.id_metadata where d.type = :type) as c";
            Query query = em.createNativeQuery(qry);
            query.setParameter("type", type);
            java.math.BigInteger val = (java.math.BigInteger) query.getSingleResult();
            return val.longValue();
        }
        String qry = "select count(c.*) from (select distinct d.* from metadata_user d join association_metadata_topic a on d.id = a.id_metadata where d.type = :type and a.id_topic = :idTopic) as c";
        Query query = em.createNativeQuery(qry);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        java.math.BigInteger val = (java.math.BigInteger) query.getSingleResult();
        return val.longValue();
    }

    @Override
    public Page<MetadataUtilisateur> findAll(Pageable pageable, MetadataUtilisateur metadata, int orderByYear) {
        String qry = "select * from metadata_user d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
            query.setParameter("id", metadata.getId());
            List<MetadataUtilisateur> results = query.getResultList();
            return new PageImpl<>(results);
        }
        Boolean contributor = Boolean.FALSE;
        Boolean coverage = Boolean.FALSE;
        Boolean creator = Boolean.FALSE;
        Boolean date = Boolean.FALSE;
        Boolean description = Boolean.FALSE;
        Boolean fileFormat = Boolean.FALSE;
        Boolean format = Boolean.FALSE;
        Boolean idDocument = Boolean.FALSE;
        Boolean idThematique = Boolean.FALSE;
        Boolean identifier = Boolean.FALSE;
        Boolean language = Boolean.FALSE;
        Boolean publisher = Boolean.FALSE;
        Boolean relation = Boolean.FALSE;
        Boolean rights = Boolean.FALSE;
        Boolean source = Boolean.FALSE;
        Boolean subject = Boolean.FALSE;
        Boolean title = Boolean.FALSE;
        Boolean type = Boolean.FALSE;
        Boolean url = Boolean.FALSE;
        Boolean year = Boolean.FALSE;
        Boolean bibliographicResource = Boolean.FALSE;
        Boolean idUtilisateur = Boolean.FALSE;
        if (metadata.getContributor() != null && !metadata.getContributor().isEmpty()) {
            qry += " and d.contributor ilike :contributor";
            contributor = Boolean.TRUE;
        }
        if (metadata.getCoverage() != null && !metadata.getCoverage().isEmpty()) {
            qry += " and d.coverage ilike :coverage";
            coverage = Boolean.TRUE;
        }
        if (metadata.getCreator() != null && !metadata.getCreator().isEmpty()) {
            qry += " and d.creator ilike :creator";
            creator = Boolean.TRUE;
        }
        if (metadata.getDate() != null && !metadata.getDate().isEmpty()) {
            qry += " and d.date_publication ilike :date";
            date = Boolean.TRUE;
        }
        if (metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            qry += " and d.description ilike :description";
            description = Boolean.TRUE;
        }
        if (metadata.getFileFormat() != null && !metadata.getFileFormat().isEmpty()) {
            qry += " and d.file_format ilike :fileFormat";
            fileFormat = Boolean.TRUE;
        }
        if (metadata.getFormat() != null && !metadata.getFormat().isEmpty()) {
            qry += " and d.format ilike :format";
            format = Boolean.TRUE;
        }
        if (metadata.getIdDocument() != null && metadata.getIdDocument() != 0) {
            qry += " and d.id_document = :idDocument";
            idDocument = Boolean.TRUE;
        }
        if (metadata.getIdUtilisateur() != null && metadata.getIdUtilisateur() != 0) {
            qry += " and d.id_utilisateur = :idUtilisateur";
            idUtilisateur = Boolean.TRUE;
        }
        if (metadata.getIdentifier() != null && !metadata.getIdentifier().isEmpty()) {
            qry += " and d.identifier ilike :identifier";
            identifier = Boolean.TRUE;
        }
        if (metadata.getLanguage() != null && !metadata.getLanguage().isEmpty()) {
            qry += " and d.language ilike :language";
            language = Boolean.TRUE;
        }
        if (metadata.getPublisher() != null && !metadata.getPublisher().isEmpty()) {
            qry += " and d.publisher ilike :publisher";
            publisher = Boolean.TRUE;
        }
        if (metadata.getRelation() != null && !metadata.getRelation().isEmpty()) {
            qry += " and d.relation ilike :relation";
            relation = Boolean.TRUE;
        }
        if (metadata.getRights() != null && !metadata.getRights().isEmpty()) {
            qry += " and d.rights ilike :rights";
            rights = Boolean.TRUE;
        }
        if (metadata.getSource() != null && !metadata.getSource().isEmpty()) {
            qry += " and d.source ilike :source";
            source = Boolean.TRUE;
        }
        if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            qry += " and d.subject ilike :subject";
            subject = Boolean.TRUE;
        }
        if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            qry += " and d.title ilike :title";
            title = Boolean.TRUE;
        }
        if (metadata.getType() != null && !metadata.getType().isEmpty()) {
            qry += " and d.type = :type";
            type = Boolean.TRUE;
        }
        if (metadata.getUrl() != null && !metadata.getUrl().isEmpty()) {
            qry += " and d.url ilike :url";
            url = Boolean.TRUE;
        }
        if (metadata.getYear() != null && !metadata.getYear().isEmpty()) {
            qry += " and d.year ilike:year";
            year = Boolean.TRUE;
        }
        if (metadata.getBibliographicResource() != null && !metadata.getBibliographicResource().isEmpty()) {
            qry += " and d.bibliographic_resource ilike:bibliographic_resource";
            bibliographicResource = Boolean.TRUE;
        }
        if (orderByYear > 0) {
            qry += " order by d.year asc";
        } else if (orderByYear < 0) {
            qry += " order by d.year desc";
        }
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        if (contributor) {
            query.setParameter("contributor", "%" + metadata.getContributor() + "%");
        }
        if (coverage) {
            query.setParameter("coverage", "%" + metadata.getCoverage() + "%");
        }
        if (creator) {
            query.setParameter("creator", "%" + metadata.getCreator() + "%");
        }
        if (date) {
            query.setParameter("date", "%" + metadata.getDate() + "%");
        }
        if (description) {
            query.setParameter("description", "%" + metadata.getDescription() + "%");
        }
        if (fileFormat) {
            query.setParameter("fileFormat", "%" + metadata.getFileFormat() + "%");
        }
        if (format) {
            query.setParameter("format", "%" + metadata.getFormat() + "%");
        }
        if (idDocument) {
            query.setParameter("idDocument", metadata.getIdDocument());
        }
        if (identifier) {
            query.setParameter("identifier", "%" + metadata.getIdentifier() + "%");
        }
        if (language) {
            query.setParameter("language", "%" + metadata.getLanguage() + "%");
        }
        if (publisher) {
            query.setParameter("publisher", "%" + metadata.getPublisher() + "%");
        }
        if (relation) {
            query.setParameter("relation", "%" + metadata.getRelation() + "%");
        }
        if (rights) {
            query.setParameter("rights", "%" + metadata.getRights() + "%");
        }
        if (source) {
            query.setParameter("source", "%" + metadata.getSource() + "%");
        }
        if (subject) {
            query.setParameter("subject", "%" + metadata.getSubject() + "%");
        }
        if (title) {
            query.setParameter("title", "%" + metadata.getTitle() + "%");
        }
        if (type) {
            query.setParameter("type", metadata.getType());
        }
        if (url) {
            query.setParameter("url", "%" + metadata.getUrl() + "%");
        }
        if (year) {
            query.setParameter("year", "%" + metadata.getYear() + "%");
        }
        if (bibliographicResource) {
            query.setParameter("bibliographic_resource", "%" + metadata.getBibliographicResource() + "%");
        }
        if (idUtilisateur) {
            query.setParameter("idUtilisateur", metadata.getIdUtilisateur());
        }
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<MetadataUtilisateur> findAll(Pageable pageable, String type, Integer idThematique, int orderByYear) {
        String qry = "select distinct d.* from metadata_user d join association_metadata_topic a on d.id = a.id_metadata where a.id_topic = :idTopic and d.type = :type order by d.year desc";
        if (orderByYear > 0) {
            qry += " order by d.year asc";
        } else if (orderByYear < 0) {
            qry += " order by d.year desc";
        }
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }    

    @Override
    public Page<MetadataUtilisateur> findGlobal(Pageable pageable, String search) {
        String qry = "select distinct m.* from "
                + "(select m.*, tax.scientificname as species, t.libelle as topic "
                + "from metadata_user m "
                + "left join association_metadata_taxonomi amtax on amtax.id_metadata = m.id "
                + "left join association_metadata_topic amt on amt.id_metadata = m.id "
                + "left join thematique t on t.id = amt.id_topic "
                + "left join taxonomi_base tax on tax.idtaxonomibase = amtax.id_taxonomi) as test "
                + "left join metadata_user m on test.id = m.id "
                + "where rtrim(ltrim(replace(test.*\\:\\:text, ','\\:\\:text, ''\\:\\:text), '('\\:\\:text), ')'\\:\\:text) ilike :search order by m.year desc";
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        query.setParameter("search", "%" + search + "%");
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<MetadataUtilisateur> findAllNew(String type, int nbr) {
        String qry = "select * from metadata_user where type = :type order by id desc limit :nbr";
        Query query = em.createNativeQuery(qry, MetadataUtilisateur.class);
        query.setParameter("type", type);
        query.setParameter("nbr", nbr);
        List<MetadataUtilisateur> results = query.getResultList();
        return new PageImpl<>(results);
    }

    public BaseAssociationCrudRepository getBaseAssociationCrudRepository() {
        return baseAssociationCrudRepository;
    }

    public void setBaseAssociationCrudRepository(BaseAssociationCrudRepository baseAssociationCrudRepository) {
        this.baseAssociationCrudRepository = baseAssociationCrudRepository;
    }

}
