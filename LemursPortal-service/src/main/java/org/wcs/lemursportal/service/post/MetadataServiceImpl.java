package org.wcs.lemursportal.service.post;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.association.AssociationMetadataTaxonomi;
import org.wcs.lemursportal.model.association.AssociationMetadataTopic;
import org.wcs.lemursportal.model.association.BaseAssociation;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.user.UserView;
import org.wcs.lemursportal.repository.post.BaseAssociationCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.repository.user.UserViewRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly = true)
public class MetadataServiceImpl extends GenericCRUDServiceImpl<Metadata, Integer> implements MetadataService {

    @Autowired
    MetadataCrudRepository metadataCrudRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    DocumentService documentService;
    @Autowired
    BaseAssociationCrudRepository baseAssociationCrudRepository;
    @Autowired
    UserViewRepository userViewRepository;

    @Override
    protected JpaRepository<Metadata, Integer> getJpaRepository() {
        return metadataCrudRepository;
    }

    @Override
    public List<String> findOneElementOfMetadata(Metadata metadata) {
        return metadataRepository.findOneElementOfMetadata(metadata);
    }
    
    /**
     *
     * @param metadata
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteMetadata(Metadata metadata) throws Exception {
        try {
            System.out.println("Delete metadata");
            List<BaseAssociation> liste = new ArrayList<>();            
            try {
                AssociationMetadataTaxonomi a = new AssociationMetadataTaxonomi();
                a.setId1(metadata.getId());
                Example<AssociationMetadataTaxonomi> example = Example.of(a);
                liste.addAll(baseAssociationCrudRepository.findAll(example));
                System.out.println("All association taxonomi got");
            } catch (NullPointerException npe) {
            }
            try {
                AssociationMetadataTopic a = new AssociationMetadataTopic();
                a.setId1(metadata.getId());
                Example<AssociationMetadataTopic> example = Example.of(a);
                liste.addAll(baseAssociationCrudRepository.findAll(example));
                System.out.println("All association topics got");
            } catch (NullPointerException npe) {
            }
            if (liste.size() > 0) {
                for (BaseAssociation b : liste) {
                    baseAssociationCrudRepository.delete(b);
                }
                System.out.println("All Association deleted");
            }
            metadata = metadataCrudRepository.findOne(metadata.getId());
            Document d = new Document();
            d.setFilename(documentService.findById(metadata.getIdDocument()).getFilename());            
            List<UserView> allUV = userViewRepository.findAll();
            for (UserView uv : allUV) {
                uv.setNbrDocument(uv.getNbrDocument()-1);
                userViewRepository.save(uv);
            }            
            metadataRepository.delete(metadata);
            System.out.println("document and metadata deleted");
            documentService.deleteDocument(d);
            System.out.println("File deleted");
        } catch (Exception e) {
            throw e;
        }
    }
}
