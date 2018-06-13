package org.wcs.lemursportal.service.post;

import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.service.common.GenericCRUDService;

public interface DocumentService extends GenericCRUDService<Document, Integer> {
	public void downloadFile(String filename);        
        public void addDocument(Metadata metadata);
}
