package org.wcs.lemursportal.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.repository.post.DocumentRepository;
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
	
	
	@GetMapping(value={"/documents"})
	public String list(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
		model.addAttribute("docAUTRES", "");
		model.addAttribute("docAUDIO", "");
		model.addAttribute("docVIDEO", "");
		model.addAttribute("docIMAGE", "");
		return "document-list";
	}
	
	@ModelAttribute("docAUTRES")
	public List<Document> listDocAutres(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
		Page<Document> pageDocuments = documentRepository.findDocumentsbyType(DOCTYPE.PUBLICATION.getValue(), pageable);
		if(null != pageDocuments) return pageDocuments.getContent();
		return null;
	}
	
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("file_name") String fileName) throws FileNotFoundException {
	    return new FileSystemResource(getFileFor(fileName)); 
	}
	
	private File getFileFor(String fileName) throws FileNotFoundException {
		String fileUploadLocation = context.getRealPath("/resources/") + "upload/"+fileName;
        File file = new File(fileUploadLocation+fileName);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + fileUploadLocation+fileName + " was not found.");
        }
        return file;
    }

	
	
}
