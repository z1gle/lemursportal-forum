package org.wcs.lemursportal.service.post;

import java.util.List;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.service.common.GenericCRUDService;

public interface MetadataService extends GenericCRUDService<Metadata, Integer> {
    public List<String> findOneElementOfMetadata(Metadata metadata);    
}
