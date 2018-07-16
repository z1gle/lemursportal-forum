package org.wcs.lemursportal.service.post;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.wcs.lemursportal.model.post.parents.MetadataParent;
import org.wcs.lemursportal.repository.post.MetadataParentCrudRepository;

public class MetadataParentServiceImpl implements MetadataParentService {

    @Autowired
    private MetadataParentCrudRepository metadataParentCrudRepository;
    
    @Override
    public void findById(MetadataParent metadataParent) {
        metadataParent = metadataParentCrudRepository.findOne(metadataParent.getId());
    }

    @Override
    public void addOrUpdate(MetadataParent metadataParent) {
        metadataParentCrudRepository.saveAndFlush(metadataParent);
    }

    @Override
    public void delete(MetadataParent metadataParent) {
        
    }

    @Override
    public List<MetadataParent> findAll(MetadataParent metadataParent) {
        Example<MetadataParent> example = Example.of(metadataParent);
        return metadataParentCrudRepository.findAll(example);
    }

    @Override
    public List<MetadataParent> findAll() {
        return metadataParentCrudRepository.findAll();
    }

    public MetadataParentCrudRepository getMetadataParentCrudRepository() {
        return metadataParentCrudRepository;
    }

    public void setMetadataParentCrudRepository(MetadataParentCrudRepository metadataParentCrudRepository) {
        this.metadataParentCrudRepository = metadataParentCrudRepository;
    }        
}
