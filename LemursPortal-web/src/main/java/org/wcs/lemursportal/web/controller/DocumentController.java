package org.wcs.lemursportal.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.repository.post.DocumentRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.service.post.DocumentService;
import org.wcs.lemursportal.service.post.PostServiceImpl.DOCTYPE;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class DocumentController extends BaseController{
	
	@Autowired ServletContext context;
	@Autowired DocumentRepository documentRepository;
	@Autowired DocumentService documentService;
	@Autowired PostRepository postRepository;
	
	private static final int BUFFER_SIZE = 4096;
	
	@GetMapping(value={"/documents"})
	public String list(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		model.addAttribute("docAUDIO", listDocs(page, model, DOCTYPE.AUDIO));
		model.addAttribute("docVIDEO", listDocs(page, model, DOCTYPE.VIDEO));
		model.addAttribute("docIMAGE", listDocs(page, model, DOCTYPE.PHOTO));
		model.addAttribute("docAUTRES", listDocs(page, model, DOCTYPE.PUBLICATION));
		return "document-list";
	}
	

	public List<Document> listDocs(Integer page, Model model,DOCTYPE docType ){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
		Page<Document> pageDocuments = documentRepository.findDocumentsbyType(docType.getValue(), pageable);
		if(null != pageDocuments) return pageDocuments.getContent();
		return null;
	}
	
	@ModelAttribute("youtubeFiles")
	public List<Post> listYoutubeFile(Integer page, Model model,DOCTYPE docType ){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
		Page<Post> pageYoutubes = postRepository.getYoutubeVideo(pageable);
		if(null != pageYoutubes) return pageYoutubes.getContent();
		return null;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
	
	public void getFile(@PathVariable("id") Integer idDocument, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(idDocument == null || idDocument <= 0){
			return ;
		}
		Document doc = documentService.findById(idDocument);
		if(null == doc)return ;
		File file = getFileFor(doc.getFilename());
		FileInputStream inputStream = new FileInputStream(file);
		 String mimeType = context.getMimeType(doc.getFilename());
		    if (mimeType == null) {
		        mimeType = MediaType.APPLICATION_OCTET_STREAM.getType();
		    }
		    response.setContentType(mimeType);
		    response.setContentLength((int) file.length());
		    String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
		    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, headerValue);
		    OutputStream outStream = response.getOutputStream();
		    byte[] buffer = new byte[BUFFER_SIZE];
		    int bytesRead = -1;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outStream.write(buffer, 0, bytesRead);
		    }
		    inputStream.close();
		    outStream.close();
	}
	
	private File getFileFor(String fileName) throws FileNotFoundException {
		String fileUploadLocation = context.getRealPath("resources") +"\\"+ "upload"+"\\";
        File file = new File(fileUploadLocation+fileName);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + fileUploadLocation+fileName + " was not found.");
        }
        return file;
    }

	
	
}
