package org.wcs.lemursportal.web.controller;

import java.util.HashMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wcs.lemursportal.model.user.UserInfo;

/**
 * @author Zacharie
 *
 */
@Controller
@Transactional
public class UserController extends BaseController {

    @GetMapping(value = {"/user/{id}"}, headers = "Accept=application/json")
    public @ResponseBody
    HashMap<String, String> getUser(@PathVariable Integer id) {
        UserInfo temp = userInfoService.getById(id);
        HashMap<String, String> valiny = new HashMap<>();
        valiny.put("img", temp.getPhotoProfil());
        valiny.put("name", temp.getNom());
        valiny.put("firstname", temp.getPrenom());
        return valiny;
    }
}
