/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcs.lemursportal.helper.pagination.PaginationRequest;
import org.wcs.lemursportal.helper.pagination.PaginationResponse;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.model.user.UserType;
import org.wcs.lemursportal.repository.user.UserTypeRepository;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.form.UserRoleEditForm;
import org.wcs.lemursportal.web.validator.UserRoleEditFormValidator;

/**
 * @author mikajy.hery
 *
 */
@Transactional
@Controller
public class UserController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	UserRoleEditFormValidator userRoleEditFormValidator;
	
	@GetMapping(value="/admin/user/list")
	public String list(HttpServletRequest request, Model model){
		PaginationRequest<UserInfo> paginationRequest = new PaginationRequest<>(1, 50);
		PaginationResponse<UserInfo> paginationResponse = userInfoService.findByPagination(paginationRequest);
		model.addAttribute("paginationResponse", paginationResponse);
		return "user/list";
	}
	
	@GetMapping(value="/admin/roles/user/{userId}")
	public String editUserRoles(
				@PathVariable(name="userId", required=false) Integer userId, Model model)
	{
		if(userId == null){
			return "redirect:/admin/user/list";
		}
		UserInfo user = userInfoService.getById(userId);
		UserRoleEditForm userRoleEditForm = new UserRoleEditForm(user);
		model.addAttribute("user", user);
		model.addAttribute("userRoleEditForm", userRoleEditForm);
		return "user/role-edit-form";
	}
	
	@PostMapping(value="/admin/roles/user")
	public String editUserRolesSubmit(Model model, @ModelAttribute UserRoleEditForm userRoleEditForm, BindingResult results){
		//Validation
		userRoleEditFormValidator.validate(userRoleEditForm, results);
//		UserInfo user = userInfoService.getById(userRoleEditForm.getUserId());
//		if(user == null || user.getId() == null){
//			results.rejectValue("userId", "validation.user.identifiant.incorrect");
//		}
		if(results.hasErrors()){
			UserInfo user = userInfoService.getById(userRoleEditForm.getUserId());
			model.addAttribute("user", user);
			model.addAttribute("userId", user.getId());//juste pour declencher le chargement de l'objet proxy
			return "user/role-edit-form";
		}
		Set<UserType> userRolesToSave = new HashSet<>();
		List<UserType> userTypes = getAllUserTypes();
		for(UserType userType : userTypes){
			if(userRoleEditForm.getRoles().contains(userType.getId())){
				userRolesToSave.add(userType);
			}
		}
		userInfoService.updateUserRoles(userRoleEditForm.getUserId(), userRolesToSave);
		return "redirect:/admin/user/list";
	}
	
	@ModelAttribute("userTypes")
	public List<UserType> getAllUserTypes(){
		List<UserType> userTypes = userTypeRepository.findAll();
		return userTypes;
	}
}
