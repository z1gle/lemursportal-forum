package org.wcs.lemursportal.service.post;

import java.util.List;
import org.springframework.stereotype.Service;
import org.wcs.lemursportal.model.post.parents.MetadataParent;

@Service
public interface MetadataParentService {
    public void findById(MetadataParent metadataParent);
    public void addOrUpdate(MetadataParent metadataParent);
    public void delete(MetadataParent metadataParent);
    public List<MetadataParent> findAll(MetadataParent metadataParent);
    public List<MetadataParent> findAll();
}
