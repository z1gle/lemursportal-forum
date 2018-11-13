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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.model.association.AssociationMetadataTaxonomi;
import org.wcs.lemursportal.model.association.AssociationMetadataTopic;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.post.MetadataUtilisateur;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopThematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserView;
import org.wcs.lemursportal.repository.post.DocumentRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.repository.post.MetadataUtilisateurRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.user.UserViewRepository;
import org.wcs.lemursportal.service.post.DocumentService;
import org.wcs.lemursportal.service.post.PostServiceImpl.DOCTYPE;
import static org.wcs.lemursportal.web.controller.BaseController.TOP_DOCUMENT_PAGE_SIZE;

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
    MetadataRepository metadataRepository;
    @Autowired
    MetadataUtilisateurRepository metadataUtilisateurRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserViewRepository userViewRepository;

    private static final int BUFFER_SIZE = 4096;

    @GetMapping(value = {"/documents"})
    public String list(@RequestParam(value = "pageDocument", required = false, defaultValue = "1") Integer pageDocument, @RequestParam(value = "pP", required = false, defaultValue = "0") Integer pagePhoto, @RequestParam(value = "pV", required = false, defaultValue = "0") Integer pageVideo, @RequestParam(value = "pA", required = false, defaultValue = "0") Integer pageAudio, @RequestParam(required = false, value = "topic") Integer thematique, @RequestParam(required = false, value = "search") String search, @RequestParam(required = false, value = "nouveau") Integer nouveau, Model model, Authentication authentication) {
        pageDocument--;
        HashMap temp = paginate(pageDocument, pageAudio, pagePhoto, pageVideo, thematique, search);
        UserView uv = null;
        int totalDoc = new Long(((HashMap<String, Object>) temp.get("pageDocument")).get("pageDocumentTotalElement").toString()).intValue();
        if (authentication != null) {
            String email = authentication.getName();
            UserInfo userInfo = userInfoService.getByEmail(email);                        
            try {
                uv = userViewRepository.findByIdUser(userInfo.getId());
                model.addAttribute("nbrDocument", totalDoc - uv.getNbrDocument());
            } catch (NullPointerException npe) {
                if (uv == null) {
                    uv = new UserView();
                    uv.setIdUser(userInfo.getId());
                    uv.setNbrDocument(totalDoc);
                    uv = userViewRepository.save(uv);
                    model.addAttribute("nbrDocument", uv.getNbrDocument());
                } else {
                    model.addAttribute("nbrDocument", 0);
                }
            }

        }
        
        if (pageDocument == null || pageDocument < 1) {
            pageDocument = 1;
        }
        /*else {
            pageDocument = pageDocument - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }*/
        if (pageVideo == null || pageVideo < 1) {
            pageVideo = -1;
        } else {
            pageVideo = pageVideo - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        if (pagePhoto == null || pagePhoto < 1) {
            pagePhoto = -1;
        } else {
            pagePhoto = pagePhoto - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        if (pageAudio == null || pageAudio < 1) {
            pageAudio = -1;
        } else {
            pageAudio = pageAudio - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        if (authentication != null && nouveau != null) {
            model.addAttribute("docAUDIO", listMetadataNouveau("3", nouveau));
            model.addAttribute("docVIDEO", listMetadataNouveau("2", nouveau));
            model.addAttribute("docIMAGE", listMetadataNouveau("1", nouveau));
            model.addAttribute("docAUTRES", listMetadataNouveau("4", nouveau));
            model.addAttribute("metadata", new Metadata());
            ((HashMap<String, Object>) temp.get("pageDocument")).put("pageDocumentTotalElement", nouveau);
            if (uv != null) {
                uv.setNbrDocument(totalDoc);
                userViewRepository.save(uv);
            }
        } else {
            if (search == null || search.isEmpty()) {
                if (thematique != null) {
                    model.addAttribute("docAUDIO", listMetadatas(pageAudio, model, "3", thematique));
                    model.addAttribute("docVIDEO", listMetadatas(pageVideo, model, "2", thematique));
                    model.addAttribute("docIMAGE", listMetadatas(pagePhoto, model, "1", thematique));
                    model.addAttribute("docAUTRES", listMetadatas(pageDocument, model, "4", thematique));
                    model.addAttribute("metadata", new Metadata());
                } else {
                    model.addAttribute("docAUDIO", listMetadata(pageAudio, model, "3"));
                    model.addAttribute("docVIDEO", listMetadata(pageVideo, model, "2"));
                    model.addAttribute("docIMAGE", listMetadata(pagePhoto, model, "1"));
                    model.addAttribute("docAUTRES", listMetadata(pageDocument, model, "4"));
                    model.addAttribute("metadata", new Metadata());
                }
            } else {
                model.addAttribute("docAUDIO", listMetadata(null, model, "3"));
                model.addAttribute("docVIDEO", listMetadata(null, model, "2"));
                model.addAttribute("docIMAGE", listMetadata(null, model, "1"));
                model.addAttribute("docAUTRES", searchGlobal(pageDocument, model, search));
                model.addAttribute("metadata", new Metadata());
            }
        }        

        model.addAttribute("pagination", temp);
        if (thematique == null) {
            model.addAttribute("topic", 0);
        } else {
            model.addAttribute("topic", thematique);
        }
        if (search != null && !search.isEmpty()) {
            model.addAttribute("search", search);
        } else {
            model.addAttribute("search", "0");
        }
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

    public List<MetadataUtilisateur> listMetadata(Integer page, Model model) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<MetadataUtilisateur> pageMetadata = metadataUtilisateurRepository.findAll(pageable);
        if (null != pageMetadata) {
            return pageMetadata.getContent();
        }
        return null;
    }

    public List<MetadataUtilisateur> listMetadata(Integer page, Model model, String metadataType) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        MetadataUtilisateur metadata = new MetadataUtilisateur();
        metadata.setType(metadataType);
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<MetadataUtilisateur> pageMetadata = metadataUtilisateurRepository.findAll(pageable, metadata, -2);
        if (null != pageMetadata) {
            return pageMetadata.getContent();
        }
        return null;
    }

    public List<MetadataUtilisateur> listMetadataNouveau(String metadataType, int nbr) {
        Page<MetadataUtilisateur> pageMetadata = metadataUtilisateurRepository.findAllNew(metadataType, nbr);
        if (null != pageMetadata) {
            return pageMetadata.getContent();
        }
        return null;
    }

    public List<MetadataUtilisateur> searchGlobal(Integer page, Model model, String search) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<MetadataUtilisateur> pageMetadata = metadataUtilisateurRepository.findGlobal(pageable, search);
        if (null != pageMetadata) {
            return pageMetadata.getContent();
        }
        return null;
    }

    public List<MetadataUtilisateur> listMetadatas(Integer page, Model model, String metadataType, Integer thematique) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
        Page<MetadataUtilisateur> pageMetadata = metadataUtilisateurRepository.findAll(pageable, metadataType, thematique);
        if (null != pageMetadata) {
            return pageMetadata.getContent();
        }
        return null;
    }

    //Construction of pagination
    public HashMap<String, Object> paginate(Integer pD, Integer pA, Integer pP, Integer pV, Integer idThematique, String search) {
        HashMap<String, Object> valiny = new HashMap<>();
        Long totalPP = metadataUtilisateurRepository.conter("1", idThematique, search);
        Long totalPV = metadataUtilisateurRepository.conter("2", idThematique, search);
        Long totalPA = metadataUtilisateurRepository.conter("3", idThematique, search);
        Long totalPD = metadataUtilisateurRepository.conter("4", idThematique, search);
        if (pP >= 0) {
            valiny.put("pagePhoto", page(pP, totalPP, "pagePhoto"));
        }
        if (pV >= 0) {
            valiny.put("pageVideo", page(pV, totalPV, "pageVideo"));
        }
        if (pA >= 0) {
            valiny.put("pageAudio", page(pA, totalPA, "pageAudio"));
        }
        if (pD >= 0) {
            valiny.put("pageDocument", page(pD, totalPD, "pageDocument"));
        }
        return valiny;
    }

    private HashMap<String, Object> page(int p, Long total, String name) {
        HashMap<String, Object> pp = new HashMap<>();
        pp.put(name + "Debut", 1);
        pp.put(name + "Current", p);
        int totalePage = new Double(Math.ceil(total.doubleValue() / TOP_DOCUMENT_PAGE_SIZE)).intValue();
        pp.put(name + "Fin", totalePage);
        pp.put(name + "TotalElement", total);
        int debut = 1;
        int fin = 5;
        if (p >= 3) {
            debut += p - 2;
            fin += p - 2;
        }
        if (totalePage < fin) {
            fin = totalePage;
        }
        List<Integer> liste = new ArrayList<>();
        for (int i = debut; i <= fin; i++) {
            liste.add(i);
        }
        pp.put(name + "Liste", liste);
        return pp;
    }
    // Modif using MetadataParent, only work for image now
//    public List<Metadata> listMetadatas() {
//        if (page == null || page < 1) {
//            page = 0;
//        } else {
//            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
//        }
//        Pageable pageable = new PageRequest(page, TOP_DOCUMENT_PAGE_SIZE);
//        Page<Metadata> pageMetadata = metadataRepository.findAll(pageable, metadataType, thematique);
//        if (null != pageMetadata) {
//            return pageMetadata.getContent();
//        }
//        return null;
//    }

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
    public String submit(Authentication authentication, @RequestParam("bibliographicResource") String bibliographicResource, @RequestParam("url") String url, @RequestParam("date") String date, @RequestParam("idThematique") String idThematique, @RequestParam(name = "species", required = false) String species, @RequestParam(name = "id", required = false) Integer id, @RequestParam("coverage") String coverage, @RequestParam("description") String description, @RequestParam("language") String language, @RequestParam("relation") String relation, @RequestParam("source") String source, @RequestParam("subject") String subject, @RequestParam("title") String title, @RequestParam("format") String format, @RequestParam("fileFormat") String fileFormat, @RequestParam("identifier") String identifier, @RequestParam("type") String type, @RequestParam("contributor") String contributor, @RequestParam("creator") String creator, @RequestParam("publisher") String publisher, @RequestParam("rights") String rights, @RequestParam("year") String year, @RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request) {
        if (authentication == null) {
            return "redirect:/login";
        }
        Metadata post = new Metadata();
        post.setBibliographicResource(bibliographicResource);
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

        String[] idsThematique = null;
        try {
            idsThematique = idThematique.split(",");
        } catch (PatternSyntaxException nse) {
            idsThematique = new String[]{idThematique};
        }
        for (String s : idsThematique) {
            AssociationMetadataTopic amt = new AssociationMetadataTopic();
            amt.setId2(Integer.parseInt(s));
            post.addListeAssociationMetadataTopic(amt);
        }
        String[] listSpecies = null;
        try {
            listSpecies = species.split(",");
        } catch (PatternSyntaxException nse) {
            listSpecies = new String[]{species};
        } catch (NullPointerException npe) {
            // If null, we don't care, we catch it later;
        }
        for (String s : listSpecies) {
            AssociationMetadataTaxonomi amt = new AssociationMetadataTaxonomi();
            try {
                amt.setId2(Integer.parseInt(s));
            } catch (Exception e) {
                continue;
            }
            post.addListeAssociationMetadataTaxonomi(amt);
        }
        UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
        System.out.println("mail");
        Date now = Calendar.getInstance().getTime();

        //handle file upload        
        if (null != file && !file.isEmpty()) {
            String additionalName = "";
            additionalName += Calendar.getInstance().getTime().getTime();
            String filename = file.getOriginalFilename().replaceAll("\\s+", "");
            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
            filename = filename.replaceAll("[^\\p{ASCII}]", "");
            String path = context.getRealPath("/") + File.separator + "resources" + File.separator + "upload" + File.separator + additionalName + filename;
            // Add the url path 
            post.setUrl("/" + "resources" + "/" + "upload" + "/" + additionalName + filename);
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
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                Document doc = new Document();
                doc.setAuthor(currentUser);
                doc.setCreationDate(now);
                doc.setFilename(additionalName + filename);
                doc.setUrl("/" + "resources" + "/" + "upload" + "/" + additionalName + filename);
                doc.setAuthorId(currentUser.getId());
                //typeId is 4 for publication 
                doc.setTypeId(Integer.parseInt(post.getType()));
                post.setDocument(doc);
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            Document doc = new Document();
            doc.setAuthor(currentUser);
            doc.setCreationDate(now);
            String fn = post.getTitle();
            if (fn.length()>=155) {
                fn = fn.substring(0, 155);
            }
            doc.setFilename(fn);
            doc.setUrl(post.getUrl());
            doc.setAuthorId(currentUser.getId());
            //typeId is 4 for publication 
            doc.setTypeId(4);
            post.setDocument(doc);
            System.out.println("filefile empty");
        }
        if (id == null) {
            // Save the document and the metadata in BDD
            post.setIdUtilisateur(currentUser.getId());
            documentService.addDocument(post);
        } else {
            post.setId(id);
            post.setIdUtilisateur(currentUser.getId());
            documentService.updateDocument(post);
        }
        return "success";
    }
    
    @PostMapping(value = {"/secured/document/delete/{id}"})
    public ResponseEntity<Boolean> deleteMetadata(@PathVariable("id") Integer id, Authentication authentication, HttpServletRequest request) {
        UserInfo userInfo = null;
        if (authentication != null) {
            userInfo = new UserInfo();
            String email = authentication.getName();
            userInfo = userInfoService.getByEmail(email);
        }
        try {
            if (userInfo == null) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            Document document = documentService.findById(id);
            if (!Objects.equals(userInfo.getId(), document.getAuthorId()) && !request.isUserInRole("ROLE_ADMIN")) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            documentService.deleteDocumentIrreversible(document);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @return
     */
    @ModelAttribute("topThematiques")
    @Override
    public List<TopThematique> getTopThematiques() {
        Page<TopThematique> page = thematiqueRepository.findTopThematique((PageRequest) null, true);
        return page.getContent();
    }

}
