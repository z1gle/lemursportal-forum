package org.wcs.lemursportal.web.controller.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wcs.lemursportal.model.notification.Notification;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.service.notification.NotificationService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.controller.BaseController;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@Controller
public class NotificationController extends BaseController{
	
	@Autowired UserInfoService userInfoService;
//	@Autowired NotificationRepository notificationRepository;
	@Autowired NotificationService notificationService;
	
	@GetMapping(value="/secured/notification/list")
	public String list(Model model, Authentication authentication){
		UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
		List<Notification> notifications = 	notificationService.getUserNotifications(currentUser.getId());
		model.addAttribute("notifications", notifications);
//		int nbDeleted = notificationRepository.deleteByUser(currentUser.getId());
		return "notification-list";
	}
	
	@GetMapping(value="/secured/notification/confirm")
	public String confirm(Authentication authentication, Model model){
		UserInfo currentUser = userInfoService.getByEmail(authentication.getName());
		int nbDeleted = notificationRepository.deleteByUser(currentUser.getId());
		return "redirect:/";
	}
}
