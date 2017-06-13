package org.wcs.lemursportal.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.repository.post.DocumentCrudRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly=true)
public class DocumentServiceImpl extends
GenericCRUDServiceImpl<Document, Integer> implements DocumentService{
	@Autowired DocumentCrudRepository documentCrudRepository;
	
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

	

}
