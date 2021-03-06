package org.wcs.lemursportal.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.service.post.MetadataService;

/**
 * @author Zacharie
 *
 */
@Controller
@Transactional
public class MetadataController extends BaseController {

    @Autowired
    ServletContext context;
    @Autowired
    MetadataService metadataService;
    @Autowired
    MetadataRepository metadataRepository;

    private static final int BUFFER_SIZE = 4096;

    /**
     *
     * @param element
     * @param valeur
     * @return
     */
//    @GetMapping(value = {"/metadata/list/{element}/{value}"}, headers = "Accept=application/json")
//    public @ResponseBody List<HashMap<String, String>> list(@PathVariable("element") String element, @PathVariable("value") String valeur) {
//        List<HashMap<String, String>> valiny = new ArrayList<>();
//        try {
//            Metadata metadata = new Metadata();
//            metadata.getClass().getMethod("set" + element, String.class).invoke(metadata, valeur);
//            List<String> liste = metadataService.findOneElementOfMetadata(metadata);
//            for (int i = 0; i < liste.size(); i++) {
//                HashMap<String, String> temp = new HashMap<>();
//                temp.put("value", liste.get(i));
//                temp.put("data", Integer.toString(i));
//                valiny.add(temp);
//            }
//        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            HashMap<String, String> temp = new HashMap<>();
//            temp.put("value", ex.getMessage());
//            temp.put("data", "ErrorMessage");
//            valiny.add(temp);
//            ex.printStackTrace();
//        }
//        return valiny;
//    }        
    @GetMapping(value = {"/metadata/list/{element}"}, headers = "Accept=application/json")
    public @ResponseBody
    List<HashMap<String, String>> list(@PathVariable("element") String element, @RequestParam("query") String valeur) {
        List<HashMap<String, String>> valiny = new ArrayList<>();
        try {
            Metadata metadata = new Metadata();
            metadata.getClass().getMethod("set" + element, String.class).invoke(metadata, valeur);
            List<String> liste = metadataService.findOneElementOfMetadata(metadata);
            for (int i = 0; i < liste.size(); i++) {
                HashMap<String, String> temp = new HashMap<>();
                temp.put("value", liste.get(i));
                temp.put("data", Integer.toString(i));
                valiny.add(temp);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            HashMap<String, String> temp = new HashMap<>();
            temp.put("value", ex.getMessage());
            temp.put("data", "ErrorMessage");
            valiny.add(temp);
            ex.printStackTrace();
        }
        if (valiny.isEmpty()) {
            HashMap<String, String> temp = new HashMap<>();
            temp.put("value", "");
            temp.put("data", "");
            valiny.add(temp);
        }
        return valiny;
    }

    @GetMapping(value = {"/metadata/{id}"}, headers = "Accept=application/json")
    public @ResponseBody
    List<HashMap<String, Object>> list(@PathVariable("id") Integer id) {
        List<HashMap<String, Object>> valiny = new ArrayList<>();
        try {
            Metadata metadata = new Metadata();
            metadata.setId(id);
            metadata = metadataService.findById(metadata.getId());
            metadata.setPhotos(null);
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("etat", Boolean.TRUE);
            temp.put("value", metadata);
            valiny.add(temp);
        } catch (Exception ex) {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("etat", Boolean.FALSE);
            temp.put("value", ex.getMessage());
            valiny.add(temp);
            //ex.printStackTrace();
        }
        return valiny;
    }

    @PostMapping(value = {"/metadata/delete/{id}"})
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
            Metadata metadata = metadataService.findById(id);
            if (!Objects.equals(userInfo.getId(), metadata.getIdUtilisateur()) && !request.isUserInRole("ROLE_ADMIN")) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            metadataService.deleteMetadata(metadata);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping(value = {"/metadata/list"}, headers = "Accept=application/json")
//    public @ResponseBody List<HashMap<String, Object>> lister(@RequestParam("element") String element, @RequestParam("valeur") String valeur) {
//        List<HashMap<String, Object>> valiny = new ArrayList<>();
//        try {
//            Metadata metadata = new Metadata();
//            metadata.getClass().getMethod("set" + element, String.class).invoke(metadata, valeur);            
//            metadata = metadataService.findById(metadata.getId());
//            HashMap<String, Object> temp = new HashMap<>();
//            temp.put("etat", Boolean.TRUE);
//            temp.put("value", metadata);
//            valiny.add(temp);
//        } catch (Exception ex) {
//            HashMap<String, Object> temp = new HashMap<>();
//            temp.put("etat", Boolean.FALSE);
//            temp.put("value", ex.getMessage());
//            valiny.add(temp);
//            //ex.printStackTrace();
//        }        
//        return valiny;
//    }
}
