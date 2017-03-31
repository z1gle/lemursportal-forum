/**
 * 
 */
package org.wcs.lemursportal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcs.lemursportal.helper.pagination.PaginationRequest;
import org.wcs.lemursportal.helper.pagination.PaginationResponse;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.user.UserInfoService;

/**
 * @author mikajy.hery
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/admin/user/list")
	public String list(HttpServletRequest request, Model model){
		PaginationRequest<UserInfo> paginationRequest = new PaginationRequest<>(1, 50);
		PaginationResponse<UserInfo> paginationResponse = userInfoService.findByPagination(paginationRequest);
		model.addAttribute("paginationResponse", paginationResponse);
		return "admin/user-list";
	}
}
