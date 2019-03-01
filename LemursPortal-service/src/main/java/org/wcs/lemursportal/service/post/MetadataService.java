package org.wcs.lemursportal.service.post;

import java.io.OutputStream;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.service.common.GenericCRUDService;

public interface MetadataService extends GenericCRUDService<Metadata, Integer> {

    public List<String> findOneElementOfMetadata(Metadata metadata);

    public void deleteMetadata(Metadata metadata) throws Exception;
    
    public void writeMetadataCsv(List<Metadata> metadata, List<String> listeColonnes, 
            char separator, OutputStream output) throws Exception;
    
    public List<Metadata> findAll(Metadata metadata);
    
    public List<Metadata> findAll(Metadata metadata, Pageable pageable);
}
