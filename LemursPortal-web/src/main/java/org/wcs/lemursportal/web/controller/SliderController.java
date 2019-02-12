package org.wcs.lemursportal.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.model.Slider;
import org.wcs.lemursportal.model.post.Photo;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.post.SliderService;

/**
 * @author Zacharie
 *
 */
@Controller
@Transactional
public class SliderController extends BaseController {

    @Autowired
    ServletContext context;
    @Autowired
    SliderService sliderService;
    
    @PreAuthorize("hasAnyRole('MODERATEUR', 'ADMIN')")
    @GetMapping(value = "/manageSlider")
    public String showListSlider(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login";
        }
        List<Slider> sliders = sliderService.findAll();
        for(Slider s : sliders) {
            sliderService.loadPhoto(s);
        }
        model.addAttribute("sliders", sliders);
        return "manageSlider";
    }

//    private static final int BUFFER_SIZE = 4096;
    @PreAuthorize("hasAnyRole('MODERATEUR', 'ADMIN')")
    @PostMapping(value = "/secured/slider", headers = "Accept=application/json")
    public @ResponseBody
    Object submit(Authentication authentication,
            @RequestParam Integer activated,
            @RequestParam String text,
            @RequestParam String title,
            @RequestParam("file") MultipartFile file) {
        if (authentication == null) {
            return Boolean.FALSE;
        }
//        Check user
        UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
//        Prepare slider
        Slider slider = new Slider();
        slider.setActivated(activated);
        slider.setIdUser(currentUser.getId());
        slider.setText(text);
        slider.setTitle(title);
        //handle file upload
        if (!file.isEmpty()) {
            String filename = Calendar.getInstance().getTimeInMillis()
                    + file.getOriginalFilename().replaceAll("\\s+", "");
            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
            filename = filename.replaceAll("[^\\p{ASCII}]", "");
            String path = context.getRealPath("/") + File.separator
                    + "resources" + File.separator + "upload"
                    + File.separator + "slider" + File.separator + filename;
            if (!Files.exists(Paths.get(context.getRealPath("/")
                    + File.separator
                    + "resources" + File.separator + "upload"
                    + File.separator + "slider"),
                    LinkOption.NOFOLLOW_LINKS)) {
                try {
                    Files.createDirectories(Paths.get(
                            context.getRealPath("/") + File.separator
                            + "resources" + File.separator + "upload"
                            + File.separator + "slider"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                Photo p = new Photo();
                p.setName(filename);
                p.setPath(path);
                p.setIdUser(currentUser.getId());
                slider.setPhoto(p);
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            System.out.println("file empty");
        }
        //
        try {
            slider.setIdUser(currentUser.getId());
            sliderService.save(slider);
        } catch (IOException ex) {
            Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @GetMapping(value = {"/sliders"}, headers = "Accept=application/json")
    public @ResponseBody
    List<Slider> lists(@RequestParam("page") Integer page, @RequestParam(
            value = "activated", required = false) Integer activated,
            @RequestParam Integer idPhoto, @RequestParam Integer idUser,
            @RequestParam String text) {
        List<Slider> valiny = new ArrayList<>();
        try {
            Slider slider = new Slider();
            if (activated != null) {
                slider.setActivated(activated);
            }
            if (idPhoto != null) {
                slider.setIdPhoto(idPhoto);
            }
            if (idUser != null) {
                slider.setIdUser(idUser);
            }
            if (text != null) {
                slider.setText(text);
            }
            if (null == page) {
                valiny = sliderService.findAll(slider);
            } else {
                Pageable pageable = new PageRequest(page, PHOTOS_PAGE_SIZE);
                valiny = sliderService.findAll(slider, pageable).getContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valiny;
    }

    @GetMapping(value = {"/slider/{id}"}, headers = "Accept=application/json")
    public @ResponseBody
    Slider getSlider(@PathVariable("id") Integer id) {
        try {
            return sliderService.findById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @PreAuthorize("hasAnyRole('MODERATEUR', 'ADMIN')")
    @PostMapping(value = "/secured/slider/{id}", headers = "Accept=application/json")
    public @ResponseBody
    Object submitUpdate(Authentication authentication,
            @PathVariable Integer id,
            @RequestParam Integer activated,
            @RequestParam String text,
            @RequestParam String title,
            @RequestParam(name = "file", required = false) MultipartFile file) {
        if (authentication == null) {
            return Boolean.FALSE;
        }
//        Check user
        UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
//        Prepare slider
        Slider slider = sliderService.findById(id);
        slider.setActivated(activated);
        slider.setIdUser(currentUser.getId());
        slider.setText(text);
        slider.setTitle(title);
        //handle file upload
        if (file != null && !file.isEmpty()) {
            String filename = Calendar.getInstance().getTimeInMillis()
                    + file.getOriginalFilename().replaceAll("\\s+", "");
            filename = Normalizer.normalize(filename, Normalizer.Form.NFD);
            filename = filename.replaceAll("[^\\p{ASCII}]", "");
            String path = context.getRealPath("/") + File.separator
                    + "resources" + File.separator + "upload"
                    + File.separator + "slider" + File.separator + filename;
            if (!Files.exists(Paths.get(context.getRealPath("/")
                    + File.separator
                    + "resources" + File.separator + "upload"
                    + File.separator + "slider"),
                    LinkOption.NOFOLLOW_LINKS)) {
                try {
                    Files.createDirectories(Paths.get(
                            context.getRealPath("/") + File.separator
                            + "resources" + File.separator + "upload"
                            + File.separator + "slider"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                Photo p = new Photo();
                p.setName(filename);
                p.setPath(path);
                p.setIdUser(currentUser.getId());
                slider.setPhoto(p);
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            System.out.println("file empty");
        }
        //
        try {
            slider.setIdUser(currentUser.getId());
            sliderService.save(slider);
        } catch (IOException ex) {
            Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @PreAuthorize("hasAnyRole('MODERATEUR', 'ADMIN')")
    @PostMapping(value = "/secured/slider/activation/{id}", headers = "Accept=application/json")
    public @ResponseBody
    Object activate(Authentication authentication, @PathVariable Integer id) {
        if (authentication == null) {
            return Boolean.FALSE;
        }
//        Check user
        UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
//        Prepare slider
        Slider slider = sliderService.findById(id);
        if (slider.getActivated() == 0) {
            slider.setActivated(1);
        } else {
            slider.setActivated(0);
        }
        try {
            slider.setIdUser(currentUser.getId());
            sliderService.save(slider);
        } catch (IOException ex) {
            Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return Boolean.FALSE;
        }
        return slider.getActivated();
    }

    @PreAuthorize("hasAnyRole('MODERATEUR', 'ADMIN')")
    @PostMapping(value = {"/secured/slider/delete/{id}"})
    public ResponseEntity<Boolean> deleteMetadata(@PathVariable("id") Integer id,
            Authentication authentication, HttpServletRequest request) {
        UserInfo userInfo = null;
        if (authentication != null) {
            String email = authentication.getName();
            userInfo = userInfoService.getByEmail(email);
        }
        try {
            if (userInfo == null) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
            Slider slider = sliderService.findById(id);
            sliderService.delete(slider);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }
}
