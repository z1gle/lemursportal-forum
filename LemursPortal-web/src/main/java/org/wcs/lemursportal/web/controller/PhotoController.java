package org.wcs.lemursportal.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.post.PhotoService;
import static org.wcs.lemursportal.web.controller.BaseController.TOP_DOCUMENT_PAGE_SIZE;

/**
 * @author Zacharie
 *
 */
@Controller
@Transactional
public class PhotoController extends BaseController {

    @Autowired
    ServletContext context;
    @Autowired
    PhotoService photoService;

    private static final int BUFFER_SIZE = 4096;

    @GetMapping(value = {"/photos"}, headers = "Accept=application/json")
    public @ResponseBody
    List<Photo> lists(@RequestParam("page") Integer page) {
        List<Photo> valiny = new ArrayList<>();
        try {
            if (null == page) {
                valiny = photoService.findAll();
            } else switch (page) {
                case 0:
                    page = 1;
                    break;
                default:
                    Pageable pageable = new PageRequest(page, PHOTOS_PAGE_SIZE);
                    valiny = photoService.findAll(pageable).getContent();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valiny;
    }

    @GetMapping(value = {"/photo/{id}"}, headers = "Accept=application/json")
    public @ResponseBody
    Photo getPhoto(@PathVariable("id") Integer id) {
        try {
            return photoService.findById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = {"secured/photo/delete/{id}"})
    public ResponseEntity<Boolean> deleteMetadata(@PathVariable("id") Integer id, Authentication authentication, HttpServletRequest request) {
        UserInfo userInfo = null;
        if (authentication != null) {
            String email = authentication.getName();
            userInfo = userInfoService.getByEmail(email);
        }
        try {
            if (userInfo == null) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            Photo photo = photoService.findById(id);
            if (!Objects.equals(userInfo.getId(), photo.getIdUser()) && !request.isUserInRole("ROLE_ADMIN")) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            photoService.deletePhoto(photo);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

}
