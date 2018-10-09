/**
 *
 */
package org.wcs.lemursportal.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wcs.lemursportal.model.post.Thematique;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.user.UserTypeRepository;
import org.wcs.lemursportal.service.post.ThematiqueService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.DExpertiseEditForm;
import org.wcs.lemursportal.web.form.UserRoleEditForm;
import org.wcs.lemursportal.web.validator.DExpertiseEditFormValidator;
import org.wcs.lemursportal.web.validator.UserRoleEditFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class AdminUserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    UserRoleEditFormValidator userRoleEditFormValidator;
    @Autowired
    DExpertiseEditFormValidator dExpertiseEditFormValidator;
    @Autowired
    ThematiqueService thematiqueService;

    @GetMapping(value = "/admin/user/list")
    @PreAuthorize("hasRole('ADMIN')")
    public String list(HttpServletRequest request, Model model) {
        Pageable pageable = new PageRequest(0, 20);
        List<UserRoleEditForm> userRoleEditForms = new ArrayList<>();
        Page<UserInfo> page = userInfoService.findByPagination(pageable);
        for (UserInfo user : page.getContent()) {
            UserRoleEditForm form = new UserRoleEditForm(user);
            model.addAttribute("userRoleEditForm_" + user.getId(), form);
            userRoleEditForms.add(form);
        }
        model.addAttribute("page", page);
        model.addAttribute("userRoleEditForms", userRoleEditForms);
        return "admin.user.roles";
    }

//    @GetMapping(value = {"/admin/user/{id}"}, headers = "Accept=application/json")
//    @PreAuthorize("hasRole('ADMIN')")
//    public @ResponseBody UserInfo getUser(@PathVariable Integer id) {
//        return userInfoService.getById(id);
//    }

    @GetMapping(value = "/admin/roles/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserRoles(
            @PathVariable(name = "userId", required = false) Integer userId, Model model) {
        if (userId == null) {
            return "redirect:/admin/user/list";
        }
        UserInfo user = userInfoService.getById(userId);
        UserRoleEditForm userRoleEditForm = new UserRoleEditForm(user);
        model.addAttribute("user", user);
        model.addAttribute("userRoleEditForm", userRoleEditForm);
        return "user/role-edit-form";
    }

    @PostMapping(value = "/admin/de/expert")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserDESubmit(Model model, @ModelAttribute DExpertiseEditForm dExpertiseEditForm, BindingResult results) {
        //Validation
        dExpertiseEditFormValidator.validate(dExpertiseEditForm, results);

        if (results.hasErrors()) {
            UserInfo user = userInfoService.getById(dExpertiseEditForm.getUserId());
            model.addAttribute("user", user);
            model.addAttribute("userId", user.getId());//juste pour declencher le chargement de l'objet proxy
            return "redirect:/experts/";
        }
        Set<Thematique> dEToSave = new HashSet<>();
        List<Thematique> dEs = getAllThematiques();
        for (Thematique thematique : dEs) {
            if (dExpertiseEditForm.getdExpertise().contains(thematique.getId())) {
                dEToSave.add(thematique);
            }
        }
        userInfoService.updateDExpertise(dExpertiseEditForm.getUserId(), dEToSave, dExpertiseEditForm.getTitle());
        return "redirect:/experts/" + dExpertiseEditForm.getUserId();
    }

    @PostMapping(value = "/admin/roles/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserRolesSubmit(Model model, @ModelAttribute UserRoleEditForm userRoleEditForm, BindingResult results) {
        //Validation
        userRoleEditFormValidator.validate(userRoleEditForm, results);
//		UserInfo user = userInfoService.getById(userRoleEditForm.getUserId());
//		if(user == null || user.getId() == null){
//			results.rejectValue("userId", "validation.user.identifiant.incorrect");
//		}
        if (results.hasErrors()) {
            UserInfo user = userInfoService.getById(userRoleEditForm.getUserId());
            model.addAttribute("user", user);
            model.addAttribute("userId", user.getId());//juste pour declencher le chargement de l'objet proxy
            return "user/role-edit-form";
        }
        Set<UserType> userRolesToSave = new HashSet<>();
        List<UserType> userTypes = getAllUserTypes();
        for (UserType userType : userTypes) {
            if (userRoleEditForm.getRoles().contains(userType.getId())) {
                userRolesToSave.add(userType);
            }
        }
        userInfoService.updateUserRoles(userRoleEditForm.getUserId(), userRolesToSave);
        return "redirect:/admin/user/list";
    }

    @ModelAttribute("userTypes")
    public List<UserType> getAllUserTypes() {
        List<UserType> userTypes = userTypeRepository.findAll();
        return userTypes;
    }

    @ModelAttribute("thematiques")
    public List<Thematique> getAllThematiques() {
        List<Thematique> listethematique = thematiqueService.findAll();
        return listethematique;
    }
}
