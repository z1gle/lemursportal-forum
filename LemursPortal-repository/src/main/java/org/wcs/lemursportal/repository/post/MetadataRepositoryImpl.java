/**
 *
 */
package org.wcs.lemursportal.repository.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.association.AssociationMetadataTaxonomi;
import org.wcs.lemursportal.model.association.AssociationMetadataTopic;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;

/**
 * @author mikajy.hery
 *
 */
@Repository
@Transactional(readOnly = true)
public class MetadataRepositoryImpl implements MetadataRepository {

    @Autowired
    private BaseAssociationCrudRepository baseAssociationCrudRepository;

    @PersistenceContext(unitName = "lemursportalPUnit")
    protected EntityManager em;

//    @Override
//    @Transactional(readOnly = true)
//    public List<Document> findAllDocuments() {
//        Query query = em.createQuery("from Document");
//        List<Document> results = query.getResultList();
//        return results;
//    }
    @Override
    public Page<Document> findDocuments(Metadata metadata, Pageable pageable) {
        String qry = "select s.* from document s join metadata d where 1=1";
        if (metadata.getIdDocument() != null) {
            qry += " and d.id = :idDoc";
            Query query = em.createNativeQuery(qry, Document.class);
            query.setParameter("idDoc", metadata.getIdDocument());
            if (pageable != null) {
                query.setFirstResult(pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Document> results = query.getResultList();
            System.out.println("firy ny isany : " + results.size());
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
            qry += " and d.type ilike :type";
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
        Query query = em.createNativeQuery(qry, Document.class);
        if (contributor) {
            query.setParameter("contributor", metadata.getContributor());
        }
        if (coverage) {
            query.setParameter("coverage", metadata.getContributor());
        }
        if (creator) {
            query.setParameter("creator", metadata.getContributor());
        }
        if (date) {
            query.setParameter("date", metadata.getContributor());
        }
        if (description) {
            query.setParameter("description", metadata.getContributor());
        }
        if (fileFormat) {
            query.setParameter("fileFormat", metadata.getContributor());
        }
        if (format) {
            query.setParameter("format", metadata.getContributor());
        }
        if (idDocument) {
            query.setParameter("idDocument", metadata.getContributor());
        }
        if (idThematique) {
            query.setParameter("idThematique", metadata.getContributor());
        }
        if (identifier) {
            query.setParameter("identifier", metadata.getContributor());
        }
        if (language) {
            query.setParameter("language", metadata.getContributor());
        }
        if (publisher) {
            query.setParameter("publisher", metadata.getContributor());
        }
        if (relation) {
            query.setParameter("relation", metadata.getContributor());
        }
        if (rights) {
            query.setParameter("rights", metadata.getContributor());
        }
        if (source) {
            query.setParameter("source", metadata.getContributor());
        }
        if (subject) {
            query.setParameter("subject", metadata.getContributor());
        }
        if (title) {
            query.setParameter("title", metadata.getContributor());
        }
        if (type) {
            query.setParameter("type", metadata.getContributor());
        }
        if (url) {
            query.setParameter("url", metadata.getContributor());
        }
        if (year) {
            query.setParameter("year", metadata.getContributor());
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
        List<Document> results = query.getResultList();
        System.out.println("firy ny isany : " + results.size());
        return new PageImpl<>(results);
    }

//    @Override
//    public Page<Document> findTopDocuments(Pageable pageable) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void insert(Metadata metadata) {
        Document document = metadata.getDocument();
        em.persist(document);
        metadata.setIdDocument(document.getId());
        em.persist(metadata);
        List<AssociationMetadataTopic> listeAMT = metadata.getListeAssociationMetadataTopic();
        for (AssociationMetadataTopic amt : listeAMT) {
            amt.setId1(metadata.getId());
            em.persist(amt);
        }
        List<AssociationMetadataTaxonomi> listeAMTax = metadata.getListeAssociationMetadataTaxonomi();
        try {
            for (AssociationMetadataTaxonomi amt : listeAMTax) {
                amt.setId1(metadata.getId());
                em.persist(amt);
            }
        } catch (NullPointerException npe) {
            System.out.println("Il n'y a pas d'éspèces séléctionnée");
        }
    }

    @Override
    public List<String> findOneElementOfMetadata(Metadata metadata) {
        Query query = null;
        if (metadata.getContributor() != null && !metadata.getContributor().isEmpty()) {
            String qry = "select distinct s.contributor from metadata s where s.contributor ilike :contributor";
            query = em.createNativeQuery(qry);
            query.setParameter("contributor", metadata.getContributor() + "%");
        } else if (metadata.getCoverage() != null && !metadata.getCoverage().isEmpty()) {
            String qry = "select distinct s.coverage from metadata s where s.coverage ilike :coverage";
            query = em.createNativeQuery(qry);
            query.setParameter("coverage", metadata.getCoverage() + "%");
        } else if (metadata.getCreator() != null && !metadata.getCreator().isEmpty()) {
            String qry = "select distinct s.creator from metadata s where s.creator ilike :creator";
            query = em.createNativeQuery(qry);
            query.setParameter("creator", metadata.getCreator() + "%");
        } else if (metadata.getDate() != null && !metadata.getDate().isEmpty()) {
            String qry = "select distinct s.date_publication from metadata s where s.date_publication ilike :date_publication";
            query = em.createNativeQuery(qry);
            query.setParameter("date_publication", metadata.getDate() + "%");
        } else if (metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            String qry = "select distinct s.description from metadata s where s.description ilike :description";
            query = em.createNativeQuery(qry);
            query.setParameter("description", metadata.getDescription() + "%");
        } else if (metadata.getFileFormat() != null && !metadata.getFileFormat().isEmpty()) {
            String qry = "select distinct s.file_format from metadata s where s.file_format ilike :file_format";
            query = em.createNativeQuery(qry);
            query.setParameter("file_format", metadata.getFileFormat() + "%");
        } else if (metadata.getFormat() != null && !metadata.getFormat().isEmpty()) {
            String qry = "select distinct s.format from metadata s where s.format ilike :format";
            query = em.createNativeQuery(qry);
            query.setParameter("format", metadata.getFormat() + "%");
        } else if (metadata.getIdDocument() != null && metadata.getIdDocument() != 0) {
            String qry = "select distinct s.id_document from metadata s where s.id_document ilike :id_document";
            query = em.createNativeQuery(qry);
            query.setParameter("id_document", metadata.getIdDocument() + "%");
        } else if (metadata.getIdUtilisateur() != null && metadata.getIdUtilisateur() != 0) {
            String qry = "select distinct s.id_utilisateur from metadata s where s.id_utilisateur ilike :id_utilisateur";
            query = em.createNativeQuery(qry);
            query.setParameter("id_utilisateur", metadata.getIdUtilisateur() + "%");
        } else if (metadata.getIdentifier() != null && !metadata.getIdentifier().isEmpty()) {
            String qry = "select distinct s.identifier from metadata s where s.identifier ilike :identifier";
            query = em.createNativeQuery(qry);
            query.setParameter("identifier", metadata.getIdentifier() + "%");
        } else if (metadata.getLanguage() != null && !metadata.getLanguage().isEmpty()) {
            String qry = "select distinct s.language from metadata s where s.language ilike :language";
            query = em.createNativeQuery(qry);
            query.setParameter("language", metadata.getLanguage() + "%");
        } else if (metadata.getPublisher() != null && !metadata.getPublisher().isEmpty()) {
            String qry = "select distinct s.publisher from metadata s where s.publisher ilike :publisher";
            query = em.createNativeQuery(qry);
            query.setParameter("publisher", metadata.getPublisher() + "%");
        } else if (metadata.getRelation() != null && !metadata.getRelation().isEmpty()) {
            String qry = "select distinct s.relation from metadata s where s.relation ilike :relation";
            query = em.createNativeQuery(qry);
            query.setParameter("relation", metadata.getRelation() + "%");
        } else if (metadata.getRights() != null && !metadata.getRights().isEmpty()) {
            String qry = "select distinct s.rights from metadata s where s.rights ilike :rights";
            query = em.createNativeQuery(qry);
            query.setParameter("rights", metadata.getRights() + "%");
        } else if (metadata.getSource() != null && !metadata.getSource().isEmpty()) {
            String qry = "select distinct s.source from metadata s where s.source ilike :source";
            query = em.createNativeQuery(qry);
            query.setParameter("source", metadata.getSource() + "%");
        } else if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            String qry = "select distinct s.subject from metadata s where s.subject ilike :subject";
            query = em.createNativeQuery(qry);
            query.setParameter("subject", metadata.getSubject() + "%");
        } else if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            String qry = "select distinct s.title from metadata s where s.title ilike :title";
            query = em.createNativeQuery(qry);
            query.setParameter("title", metadata.getTitle() + "%");
        } else if (metadata.getType() != null && !metadata.getType().isEmpty()) {
            String qry = "select distinct s.type from metadata s where s.type ilike :type";
            query = em.createNativeQuery(qry);
            query.setParameter("type", metadata.getType() + "%");
        } else if (metadata.getUrl() != null && !metadata.getUrl().isEmpty()) {
            String qry = "select distinct s.url from metadata s where s.url ilike :url";
            query = em.createNativeQuery(qry);
            query.setParameter("url", metadata.getUrl() + "%");
        } else if (metadata.getYear() != null && !metadata.getYear().isEmpty()) {
            String qry = "select distinct s.year from metadata s where s.year like :year";
            query = em.createNativeQuery(qry);
            query.setParameter("year", metadata.getYear() + "%");
        } else if (metadata.getBibliographicResource() != null && !metadata.getBibliographicResource().isEmpty()) {
            String qry = "select distinct s.bibliographic_resource from metadata s where s.bibliographic_resource ilike :bibliographic_resource";
            query = em.createNativeQuery(qry);
            query.setParameter("bibliographic_resource", "%" + metadata.getBibliographicResource());
        }
        if (query != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    public Page<Metadata> findAll(Pageable pageable) {
        String qry = "select * from metadata order by year desc";
        Query query = em.createNativeQuery(qry, Metadata.class);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        return new PageImpl<>(query.getResultList());
    }

    @Override
    public Page<Metadata> findAll(Pageable pageable, Metadata metadata) {
        String qry = "select * from metadata d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, Metadata.class);
            query.setParameter("id", metadata.getId());
            List<Metadata> results = query.getResultList();
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
        Query query = em.createNativeQuery(qry, Metadata.class);
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
        List<Metadata> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<Metadata> findAllExact(Pageable pageable, Metadata metadata) {
        String qry = "select * from metadata d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, Metadata.class);
            query.setParameter("id", metadata.getId());
            List<Metadata> results = query.getResultList();
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
        Query query = em.createNativeQuery(qry, Metadata.class);
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
        List<Metadata> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<Metadata> findAll(Pageable pageable, String type, Integer idThematique) {
        String qry = "select d.* from metadata d join association_metadata_topic a on d.id = a.id_metadata where a.id_topic = :idTopic and d.type = :type";
        Query query = em.createNativeQuery(qry, Metadata.class);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<Metadata> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Long conter(String type, Integer idThematique) {
        if (idThematique == null) {
            String qry = "select count(d.*) from metadata d join association_metadata_topic a on d.id = a.id_metadata where d.type = :type";
            Query query = em.createNativeQuery(qry);
            query.setParameter("type", type);
            java.math.BigInteger val = (java.math.BigInteger) query.getSingleResult();
            return val.longValue();
        }
        String qry = "select count(d.*) from metadata d join association_metadata_topic a on d.id = a.id_metadata where a.id_topic = :idTopic and d.type = :type";
        Query query = em.createNativeQuery(qry);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        java.math.BigInteger val = (java.math.BigInteger) query.getSingleResult();
        return val.longValue();
    }

    @Override
    public Page<Metadata> findAll(Pageable pageable, Metadata metadata, int orderByYear) {
        String qry = "select * from metadata d where 1=1";
        if (metadata.getId() != null) {
            qry += " and d.id = :id";
            Query query = em.createNativeQuery(qry, Metadata.class);
            query.setParameter("id", metadata.getId());
            List<Metadata> results = query.getResultList();
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
        Query query = em.createNativeQuery(qry, Metadata.class);
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
        List<Metadata> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public Page<Metadata> findAll(Pageable pageable, String type, Integer idThematique, int orderByYear) {
        String qry = "select d.* from metadata d join association_metadata_topic a on d.id = a.id_metadata where a.id_topic = :idTopic and d.type = :type";
        if (orderByYear > 0) {
            qry += " order by d.year asc";
        } else if (orderByYear < 0) {
            qry += " order by d.year desc";
        }
        Query query = em.createNativeQuery(qry, Metadata.class);
        query.setParameter("idTopic", idThematique);
        query.setParameter("type", type);
        if (pageable != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        List<Metadata> results = query.getResultList();
        return new PageImpl<>(results);
    }

    @Override
    public void update(Metadata metadata) {
        Integer idDocument = em.find(Metadata.class, metadata.getId()).getIdDocument();
        metadata.setIdDocument(idDocument);
        em.merge(metadata);
        //Get all associationTopic
        AssociationMetadataTopic amtForSearch = new AssociationMetadataTopic();
        amtForSearch.setId1(metadata.getId());
        Example<AssociationMetadataTopic> example = Example.of(amtForSearch);
        List<AssociationMetadataTopic> listeAmt = baseAssociationCrudRepository.findAll(example);
        //Get all associationTaxonomi
        AssociationMetadataTaxonomi amtaxForSearch = new AssociationMetadataTaxonomi();
        amtaxForSearch.setId1(metadata.getId());
        Example<AssociationMetadataTaxonomi> example2 = Example.of(amtaxForSearch);
        List<AssociationMetadataTaxonomi> listeAmtax = baseAssociationCrudRepository.findAll(example2);
        //Delete all Association in relation with this metadata
        if (listeAmt.size() > 0) {
            for (AssociationMetadataTopic a : listeAmt) {
                baseAssociationCrudRepository.delete(a);
                System.out.println("1 topic deleted");
            }
        }
        if (listeAmtax.size() > 0) {
            for (AssociationMetadataTaxonomi a : listeAmtax) {
                baseAssociationCrudRepository.delete(a);
                System.out.println("1 taxonomi deleted");
            }
        }
        //Add the new association
        List<AssociationMetadataTopic> listeAMT = metadata.getListeAssociationMetadataTopic();
        for (AssociationMetadataTopic amt : listeAMT) {
            amt.setId1(metadata.getId());
            em.persist(amt);
            System.out.println("1 topic added");
        }
        List<AssociationMetadataTaxonomi> listeAMTax = metadata.getListeAssociationMetadataTaxonomi();
        try {
            for (AssociationMetadataTaxonomi amt : listeAMTax) {
                amt.setId1(metadata.getId());
                em.persist(amt);
                System.out.println("1 taxonomi added");
            }
        } catch (NullPointerException npe) {
            System.out.println("Il n'y a pas d'éspèces séléctionnée");
        }
    }

    @Override
    @Transactional
    public void delete(Metadata metadata) {
        Metadata toRemove = em.find(Metadata.class, metadata.getId());
        System.out.println("Got metadata");
        Document d = em.find(Document.class, toRemove.getIdDocument());
        System.out.println("Got service");
        em.remove(toRemove);
        em.remove(d);
    }

    public BaseAssociationCrudRepository getBaseAssociationCrudRepository() {
        return baseAssociationCrudRepository;
    }

    public void setBaseAssociationCrudRepository(BaseAssociationCrudRepository baseAssociationCrudRepository) {
        this.baseAssociationCrudRepository = baseAssociationCrudRepository;
    }

}
