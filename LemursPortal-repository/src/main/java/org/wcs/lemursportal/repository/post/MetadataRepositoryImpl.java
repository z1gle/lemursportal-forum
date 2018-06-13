/**
 *
 */
package org.wcs.lemursportal.repository.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;

/**
 * @author mikajy.hery
 *
 */
@Repository
@Transactional(readOnly = true)
public class MetadataRepositoryImpl implements MetadataRepository {

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
            qry += "and d.id = :idDoc";
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
            qry += "and d.contributor ilike :contributor";
            contributor = Boolean.TRUE;
        }
        if (metadata.getCoverage() != null && !metadata.getCoverage().isEmpty()) {
            qry += "and d.coverage ilike :coverage";
            coverage = Boolean.TRUE;
        }
        if (metadata.getCreator() != null && !metadata.getCreator().isEmpty()) {
            qry += "and d.creator ilike :creator";
            creator = Boolean.TRUE;
        }
        if (metadata.getDate() != null && !metadata.getDate().isEmpty()) {
            qry += "and d.date_publication ilike :date";
            date = Boolean.TRUE;
        }
        if (metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            qry += "and d.description ilike :description";
            description = Boolean.TRUE;
        }
        if (metadata.getFileFormat() != null && !metadata.getFileFormat().isEmpty()) {
            qry += "and d.file_format ilike :fileFormat";
            fileFormat = Boolean.TRUE;
        }
        if (metadata.getFormat() != null && !metadata.getFormat().isEmpty()) {
            qry += "and d.format ilike :format";
            format = Boolean.TRUE;
        }
        if (metadata.getIdDocument() != null && metadata.getIdDocument() != 0) {
            qry += "and d.id_document = :idDocument";
            idDocument = Boolean.TRUE;
        }
        if (metadata.getIdThematique() != null && metadata.getIdThematique() != 0) {
            qry += "and d.id_thematique = :idThematique";
            idThematique = Boolean.TRUE;
        }
        if (metadata.getIdUtilisateur()!= null && metadata.getIdUtilisateur() != 0) {
            qry += "and d.id_utilisateur = :idUtilisateur";
            idUtilisateur = Boolean.TRUE;
        }
        if (metadata.getIdentifier() != null && !metadata.getIdentifier().isEmpty()) {
            qry += "and d.identifier ilike :identifier";
            identifier = Boolean.TRUE;
        }
        if (metadata.getLanguage() != null && !metadata.getLanguage().isEmpty()) {
            qry += "and d.language ilike :language";
            language = Boolean.TRUE;
        }
        if (metadata.getPublisher() != null && !metadata.getPublisher().isEmpty()) {
            qry += "and d.publisher ilike :publisher";
            publisher = Boolean.TRUE;
        }
        if (metadata.getRelation() != null && !metadata.getRelation().isEmpty()) {
            qry += "and d.relation ilike :relation";
            relation = Boolean.TRUE;
        }
        if (metadata.getRights() != null && !metadata.getRights().isEmpty()) {
            qry += "and d.rights ilike :rights";
            rights = Boolean.TRUE;
        }
        if (metadata.getSource() != null && !metadata.getSource().isEmpty()) {
            qry += "and d.source ilike :source";
            source = Boolean.TRUE;
        }
        if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            qry += "and d.subject ilike :subject";
            subject = Boolean.TRUE;
        }
        if (metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            qry += "and d.title ilike :title";
            title = Boolean.TRUE;
        }
        if (metadata.getType() != null && !metadata.getType().isEmpty()) {
            qry += "and d.type ilike :type";
            type = Boolean.TRUE;
        }
        if (metadata.getUrl() != null && !metadata.getUrl().isEmpty()) {
            qry += "and d.url ilike :url";
            url = Boolean.TRUE;
        }
        if (metadata.getYear() != null && !metadata.getYear().isEmpty()) {
            qry += "and d.year ilike:year";
            year = Boolean.TRUE;
        }
        if (metadata.getBibliographicResource()!= null && !metadata.getBibliographicResource().isEmpty()) {
            qry += "and d.bibliographic_resource ilike:bibliographic_resource";
            bibliographicResource = Boolean.TRUE;
        }
        Query query = em.createQuery(qry);
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
    }

}
