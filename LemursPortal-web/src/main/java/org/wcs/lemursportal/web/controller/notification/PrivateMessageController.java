/**
 * 
 */
package org.wcs.lemursportal.web.controller.notification;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcs.lemursportal.model.notification.PrivateMessage;
import org.wcs.lemursportal.model.user.UserInfo;
import org.wcs.lemursportal.repository.notification.PrivateMessageRepository;
import org.wcs.lemursportal.service.notification.PrivateMessageService;
import org.wcs.lemursportal.service.user.UserInfoService;
import org.wcs.lemursportal.web.controller.BaseController;

/**
 * @author mikajy.hery
 *
 */
@Controller
public class PrivateMessageController extends BaseController {
	
	@Autowired UserInfoService userInfoService;
	@Autowired PrivateMessageRepository privateMessageRepository;
	@Autowired PrivateMessageService privateMessageService;
	
	@GetMapping(value="/secured/pmessage/form/{destinataireId}")
	public String getNombreMessage(@PathVariable(name="destinataireId", required=false) Integer destinataireId, 
			Model model, Authentication authentication
	){
		UserInfo destinataire = userInfoService.getById(destinataireId);
		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setDestinataireId(destinataireId);
		privateMessage.setDestinataire(destinataire);
		model.addAttribute("privateMessage", privateMessage);
		return "privatemessage-form";
	}
	
	@PostMapping(value="/secured/pmessage")
	public String signupSubmit(HttpServletRequest request, Model model, Authentication authentication,
			@ModelAttribute PrivateMessage privateMessage, 
			BindingResult results)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "subject", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "body", "validation.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(results, "destinataireId", "validation.mandatory");
		if(results.hasErrors()){
			return "privatemessage-form";
		}
		String email = authentication.getName();
		UserInfo currentUser = userInfoService.getByEmail(email);
		privateMessageService.save(currentUser, privateMessage);
		return "redirect:/secured/pmessage/list";
	}
	
	@GetMapping(value="/secured/pmessage/list")
	public String getListMessage(Model model, Authentication authentication){
		String email = authentication.getName();
		UserInfo userInfo = userInfoService.getByEmail(email);
		Page<PrivateMessage> messagesPage = privateMessageRepository.findByDestinataire(userInfo.getId(), new PageRequest(0, 100));
		model.addAttribute("messagesPage", messagesPage);
		return "privatemessage-list";
	}
	
	@GetMapping(value="/secured/pmessage/{messageId}")
	public String getMessage(@PathVariable(name="messageId", required=false) Integer messageId, Model model, Authentication authentication){
		String email = authentication.getName();
		UserInfo userInfo = userInfoService.getByEmail(email);
		PrivateMessage privateMessage = privateMessageService.findById(messageId, userInfo.getId());
		model.addAttribute("privateMessage", privateMessage);
		return "privatemessage-view";
	}
}
