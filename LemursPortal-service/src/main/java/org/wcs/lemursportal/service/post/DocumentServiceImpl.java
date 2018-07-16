package org.wcs.lemursportal.service.post;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.repository.post.DocumentCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl extends
        GenericCRUDServiceImpl<Document, Integer> implements DocumentService {

    @Autowired
    DocumentCrudRepository documentCrudRepository;
    @Autowired
    MetadataRepository metadataRepository;

    @Override
    protected JpaRepository<Document, Integer> getJpaRepository() {
        // TODO Auto-generated method stub
        return documentCrudRepository;
    }

    @Override
    public void downloadFile(String filename) {

    }

    @Override
    public Page<Document> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addDocument(Metadata metadata) {
        metadataRepository.insert(metadata);
    }

    @Override
    public void updateDocument(Metadata metadata) {
        metadataRepository.update(metadata);
    }

    @Override
    public boolean deleteDocument(Document document) throws Exception {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath().split("WEB-INF" + File.separator + "classes")[0];
        String filePath = rootPath + "resources" + File.separator + "upload" + File.separator + document.getFilename();
        try {
            File file = new File(filePath);
            return file.delete();
        } catch (Exception e) {
            throw e;
        }
    }

}
