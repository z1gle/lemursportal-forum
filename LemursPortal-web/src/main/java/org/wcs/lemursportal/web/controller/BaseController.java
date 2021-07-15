package org.wcs.lemursportal.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.model.post.Post;
import org.wcs.lemursportal.model.post.TopQuestion;
import org.wcs.lemursportal.model.post.TopThematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.notification.NotificationRepository;
import org.wcs.lemursportal.repository.notification.PrivateMessageRepository;
import org.wcs.lemursportal.repository.post.DocumentRepository;
import org.wcs.lemursportal.repository.post.PostRepository;
import org.wcs.lemursportal.repository.post.ThematiqueRepository;
import org.wcs.lemursportal.service.post.PhotoService;
import org.wcs.lemursportal.service.post.PostService;
import org.wcs.lemursportal.service.post.ThematiqueService;
import org.wcs.lemursportal.service.post.PostServiceImpl.DOCTYPE;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    ThematiqueService thematiqueService;

    @Autowired
    ThematiqueRepository thematiqueRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;
    @Autowired
    PhotoService photoService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    protected SessionRegistry sessionRegistry;
    @Autowired
    protected NotificationRepository notificationRepository;

    @Autowired
    PrivateMessageRepository privateMessageRepository;

    public static final int TOP_QUESTIONS_PAGE_SIZE = 20;
    public static final int TOP_THEMATIQUES_PAGE_SIZE = 20;
    public static final int TOP_DOCUMENT_PAGE_SIZE = 20;
    public static final int TOP_PHOTO_PAGE_SIZE = 21;
    public static final int TOP_DOCUMENT_ACCUEIL_SIZE = 6;
    public static final int DERNIERES_QUESTIONS_PAGE_SIZE = 20;
    
    public static final int PHOTOS_PAGE_SIZE = 40;

//	public static final String USER_PROFIL_IMAGE_RESOURCE_PATH = "/resources/profil/";
    public static final String USER_PROFIL_IMAGE_RESOURCE_PATH = "/profil/";
//	public static final String FILE_UPLOAD_LOCATION="G:/Rebioma/lemursPortal/workspaces/LemursPortal/LemursPortal-web/src/main/webapp/resources/" + USER_PROFIL_IMAGE_RESOURCE_PATH ;//TODO: à externaliser !

    protected HashMap<String, Object> resultat = new HashMap();

    @ModelAttribute("allLogedUsers")
    public List<String> getUsersFromSessionRegistry() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<String> usersNamesList = new ArrayList<String>();

        for (Object principal : principals) {
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getUsername());
            } else {
                usersNamesList.add((String) principal);
            }
        }
        return usersNamesList;
    }

    @ModelAttribute("topQuestionsPage")
    public Page<TopQuestion> getTopQuestions(@RequestParam(required = false, defaultValue = "0") Integer page, Model model) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Page<TopQuestion> pageResult = postService.getTopQuestions(new PageRequest(page, TOP_QUESTIONS_PAGE_SIZE));
        return pageResult;
    }

    @ModelAttribute("topThematiques")
    public List<TopThematique> getTopThematiques() {
//		Page<TopThematique> page = thematiqueRepository.findTopThematique(new PageRequest(0, TOP_THEMATIQUES_PAGE_SIZE));
        Page<TopThematique> page = thematiqueRepository.findTopThematique((PageRequest) null);
        return page.getContent();
    }

    @ModelAttribute("topDocuments")
    public List<Document> getTopDocument(Integer page, Model model, DOCTYPE docType) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(0, TOP_DOCUMENT_ACCUEIL_SIZE);
        Page<Document> pageDocuments = documentRepository.findTopDocuments(pageable);
        if (null != pageDocuments) {
            return pageDocuments.getContent();
        }
        return null;
    }
    
    @ModelAttribute("topPhotos")
    public List<Photo> getTopPhotos(Integer page, Model model, DOCTYPE docType) {
        if (page == null || page < 1) {
            page = 0;
        } else {
            page = page - 1; //Le numéro de page commence toujours par 1 du coté de l'utilisateur final
        }
        Pageable pageable = new PageRequest(0, TOP_DOCUMENT_ACCUEIL_SIZE);
        Page<Photo> pageDocuments = photoService.findAll(pageable);
        if (null != pageDocuments) {
            return pageDocuments.getContent();
        }
        return null;
    }

    @ModelAttribute("lastestPosts")
    public List<Post> getLastestPosts() {
        Page<Post> page = postRepository.getLastestPosts(new PageRequest(0, DERNIERES_QUESTIONS_PAGE_SIZE));//On ne prendra que les DERNIERES_QUESTIONS_PAGE_SIZE premiers
        return page.getContent();
    }

    @ModelAttribute("currentUser")
    public UserInfo getCurrentUser(Authentication authentication) {
        UserInfo userInfo = new UserInfo();
        if (authentication != null) {
            String email = authentication.getName();
            userInfo = userInfoService.getByEmail(email);
        }
        return userInfo;
    }

    @ModelAttribute("nombreNotification")
    public Long getNombreNotification(Authentication authentication) {
        Long nbNotification = 0L;
        if (authentication != null) {
            String email = authentication.getName();
            UserInfo userInfo = userInfoService.getByEmail(email);
            nbNotification = notificationRepository.countByUser(userInfo.getId());
        }
        return nbNotification;
    }

    @ModelAttribute("nombrePrivateMessage")
    public Long getNombrePrivate(Authentication authentication) {
        Long nbPrivateMessage = 0L;
        if (authentication != null) {
            String email = authentication.getName();
            UserInfo userInfo = userInfoService.getByEmail(email);
            nbPrivateMessage = privateMessageRepository.countByDestinataire(userInfo.getId());
        }
        return nbPrivateMessage;
    }
    
    public HashMap<String, Object> returnResultat() {
        return this.resultat;
    }

    public HashMap<String, Object> getResultat() {
        return resultat;
    }

    public void setResultat(HashMap<String, Object> resultat) {
        this.resultat = resultat;
    }        
    public void setResultat(Boolean etat, String message) {
        this.resultat.put("etat", etat);
        this.resultat.put("message", message);        
    }        
}
