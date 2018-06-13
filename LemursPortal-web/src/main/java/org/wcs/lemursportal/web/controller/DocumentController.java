package org.wcs.lemursportal.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.DocumentRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.service.post.DocumentService;
import org.wcs.lemursportal.service.post.PostServiceImpl.DOCTYPE;
import static org.wcs.lemursportal.web.controller.PostController.URL_YOUTUBE_PATTERN;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
@Transactional
public class DocumentController extends BaseController {

    @Autowired
    ServletContext context;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentService documentService;
    @Autowired
    PostRepository postRepository;

    private static final int BUFFER_SIZE = 4096;

    @GetMapping(value = {"/documents"})
    public String list(@RequestParam(required = false, defaultValue = "0") Integer page, Model model) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        model.addAttribute("docAUDIO", listDocs(page, model, DOCTYPE.AUDIO));
        model.addAttribute("docVIDEO", listDocs(page, model, DOCTYPE.VIDEO));
        model.addAttribute("docIMAGE", listDocs(page, model, DOCTYPE.PHOTO));
        model.addAttribute("docAUTRES", listDocs(page, model, DOCTYPE.PUBLICATION));
        model.addAttribute("metadata", new Metadata());
        return "document-list";
    }

    public List<Document> listDocs(Integer page, Model model, DOCTYPE docType) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<Document> pageDocuments = documentRepository.findDocumentsbyType(docType.getValue(), pageable);
        if (null != pageDocuments) {
            return pageDocuments.getContent();
        }
        return null;
    }

    @ModelAttribute("youtubeFiles")
    public List<Post> listYoutubeFile(Integer page, Model model, DOCTYPE docType) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<Post> pageYoutubes = postRepository.getYoutubeVideo(pageable);
        if (null != pageYoutubes) {
            return pageYoutubes.getContent();
        }
        return null;
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)

    public void getFile(@PathVariable("id") Integer idDocument, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (idDocument == null || idDocument <= 0) {
            return;
        }
        Document doc = documentService.findById(idDocument);
        if (null == doc) {
            return;
        }
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
        String fileUploadLocation = context.getRealPath("resources") + "\\" + "upload" + "\\";
        File file = new File(fileUploadLocation + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("file with path: " + fileUploadLocation + fileName + " was not found.");
        }
        return file;
    }

    @PostMapping(value = "/secured/document/post")
    public String submit(Authentication authentication, @RequestParam("bibliographicResource") String bibliographicResource, @RequestParam("url") String url, @RequestParam("date") String date, @RequestParam("idThematique") Integer idThematique, @RequestParam("coverage") String coverage, @RequestParam("description") String description, @RequestParam("language") String language, @RequestParam("relation") String relation, @RequestParam("source") String source, @RequestParam("subject") String subject, @RequestParam("title") String title, @RequestParam("format") String format, @RequestParam("fileFormat") String fileFormat, @RequestParam("identifier") String identifier, @RequestParam("type") String type, @RequestParam("contributor") String contributor, @RequestParam("creator") String creator, @RequestParam("publisher") String publisher, @RequestParam("rights") String rights, @RequestParam("year") String year, @RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request) {
        if (authentication == null) {
            return "redirect:/login";
        }
        Metadata post = new Metadata();
        post.setBibliographicResource(date);
        post.setIdThematique(idThematique);
        post.setDate(date);
        post.setCoverage(coverage);
        post.setDescription(description);
        post.setLanguage(language);
        post.setRelation(relation);
        post.setSource(source);
        post.setSubject(subject);
        post.setTitle(title);
        post.setFormat(format);
        post.setFileFormat(fileFormat);
        post.setIdentifier(identifier);
        post.setContributor(contributor);
        post.setCreator(creator);
        post.setPublisher(publisher);
        post.setRights(rights);
        post.setYear(year);
        post.setType(type);
        post.setUrl(url);

        //ValidationUtils.rejectIfEmptyOrWhitespace(results, "libelle", "validation.mandatory");
//        ValidationUtils.rejectIfEmptyOrWhitespace(results, "thematiqueId", "validation.mandatory");
//        ValidationUtils.rejectIfEmptyOrWhitespace(results, "body", "validation.mandatory");
//        ValidationUtils.rejectIfEmptyOrWhitespace(results, "title", "validation.mandatory");
//        System.out.println("gffghgfh");
        //validate url youtube video
//        if (null != post.getUrl() && !post.getUrl().isEmpty()) {
//            Pattern pattern = Pattern.compile(URL_YOUTUBE_PATTERN);
//            Matcher matcher = pattern.matcher(post.getUriYoutube().trim());
//            if (!matcher.matches()) {
//                model.addAttribute("error_youtube", "invalid url youtube");
//                List<Thematique> listethematique = thematiqueService.findAll();
//                model.addAttribute("listeThematique", listethematique);
//                model.addAttribute(post);
//                return "getFormPost";
//            }
//        }
//        if (results.hasErrors()) {
//
//            List<String> errors = new ArrayList<>();
//            for (FieldError fieldError : results.getFieldErrors()) {
//                errors.add(fieldError.getCode());
//
//            }
//            List<Thematique> listethematique = thematiqueService.findAll();
//            model.addAttribute("listeThematique", listethematique);
//            model.addAttribute(post);
//            model.addAttribute("errors", errors);
//            return "getFormPost";
//        }
        //thematiqueService.saveOrUpdate(authentication.getName(), thematique);
        UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
        System.out.println("mail");
        Date now = Calendar.getInstance().getTime();

        //handle file upload
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename().replaceAll("\\s+", "");
            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
            filename = filename.replaceAll("[^\\p{ASCII}]", "");
//			System.out.println("filename " + filename);
            String path = context.getRealPath("/") + File.separator + "resources" + File.separator + "upload" + File.separator + filename;
            // Add the url path 
            if (null == post.getUrl() || post.getUrl().isEmpty()) {
                post.setUrl(path);
            }
            if (!Files.exists(Paths.get(context.getRealPath("/"), File.separator, "resources", File.separator, "upload"), LinkOption.NOFOLLOW_LINKS)) {
                try {
                    Files.createDirectories(Paths.get(context.getRealPath("/"), File.separator, "resources", File.separator, "upload"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                Document doc = new Document();
                doc.setAuthor(currentUser);
                doc.setCreationDate(now);
                // doc.setUploadDate(now);
                doc.setFilename(filename);
                doc.setUrl("/" + "resources" + "/" + "upload" + "/" + filename);
                doc.setAuthorId(currentUser.getId());
                doc.setTypeId(4);
                post.setDocument(doc);
                // System.out.println("filefile : " + context.getRealPath("/")+ File.separator +filename);
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            System.out.println("filefile empty");
        }

        post.setIdUtilisateur(currentUser.getId());
        documentService.addDocument(post);
        return "success";
    }

}
