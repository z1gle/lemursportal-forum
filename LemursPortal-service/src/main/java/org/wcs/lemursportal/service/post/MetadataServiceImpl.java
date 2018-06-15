package org.wcs.lemursportal.service.post;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.repository.post.MetadataCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly = true)
public class MetadataServiceImpl extends GenericCRUDServiceImpl<Metadata, Integer> implements MetadataService {

    @Autowired
    MetadataCrudRepository metadataCrudRepository;
    @Autowired
    MetadataRepository metadataRepository;

    @Override
    protected JpaRepository<Metadata, Integer> getJpaRepository() {
        return metadataCrudRepository;
    }

    @Override
    public List<String> findOneElementOfMetadata(Metadata metadata) {
        return metadataRepository.findOneElementOfMetadata(metadata);
    }

}
