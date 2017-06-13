package org.wcs.lemursportal.web.controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class DocumentController extends BaseController{
	
	@Autowired ServletContext context;
	
	
	@GetMapping(value={"/documents"})
	public String list(@RequestParam(required=false, defaultValue="0") Integer page, Model model){
		if(page == null || page < 1){
			page = 0;
		}else{
			page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
		}
		Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
		model.addAttribute("documentAUTRES", "");
		model.addAttribute("documentAUDIO", "");
		model.addAttribute("documentVIDEO", "");
		model.addAttribute("documentIMAGE", "");
		return "document-list";
	}
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("file_name") String fileName) throws FileNotFoundException {
	    return new FileSystemResource(getFileFor(fileName)); 
	}
	
	private File getFileFor(String fileName) throws FileNotFoundException {
		String fileUploadLocation = context.getRealPath("/resources/") + USER_PROFIL_IMAGE_RESOURCE_PATH;
        File file = new File(fileUploadLocation+fileName);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + fileUploadLocation+fileName + " was not found.");
        }
        return file;
    }

	
	
}
