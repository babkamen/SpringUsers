package org.geymer.users.controller;

import org.apache.log4j.Logger;
import org.geymer.users.entity.User;
import org.geymer.users.entity.UserGroup;
import org.geymer.users.service.UserGroupService;
import org.geymer.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/groups")
public class UserGroupController {
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    UserService userService;
    private static final Logger logger = Logger.getLogger(UserGroupController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String userGroups(ModelMap model,HttpServletResponse response) {
        response.setContentType("image/jpeg");
        model.addAttribute("userGroups", userGroupService.findAll());
        return "groups";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("q") String name, Model model) {
        model.addAttribute("userGroups", userGroupService.findByNameContaining(name));
        return "groups";
    }
    @RequestMapping(value = "/getlogo/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] helloWorld(@PathVariable int groupId)  {
        return userGroupService.findOne(groupId).getLogo();
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        //TODO:FIX WYSIWYG editor and exceptions
        model.addAttribute("userGroup", new UserGroup());
        model.addAttribute("users", userService.findUsersNotInGroup());
        return "groups/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("userGroup") UserGroup userGroup, BindingResult result,
                      @RequestParam(value = "logo1", required = false) CommonsMultipartFile logo, @RequestParam(value = "userIds", required = false) Integer[] userIds, Model model) {

        if (result.hasErrors()) {
            logger.info(result.toString());
            return "groups/add";
        }
        if (userIds != null && userIds.length > 0) {
            List<User> users = new ArrayList<User>();
            for (Integer id : userIds) {
                users.add(userService.findOne(id));
            }
            userGroup.setUsers(users);
        }

        if(logo!=null){
                userGroup.setLogo(logo.getBytes());

        }


        userGroupService.save(userGroup);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/delete")
    public String addUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("idError", "");
            return "forward:/groups";
        }
        userGroupService.delete(id);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            model.addAttribute("idError", "");

            return "forward:/groups";
        }
        model.addAttribute("userGroup", userGroupService.findOne(id));
        model.addAttribute("users", userService.findAll());

        return "groups/edit";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("userGroup") UserGroup userGroup, BindingResult result,
                           @RequestParam(value = "logo", required = false) MultipartFile logo, @RequestParam(value = "userIds") Integer[] userIds, Model model) {

        if (result.hasErrors()) {
            return "groups/edit";
        }
        if (userIds != null && userIds.length > 0) {
            List<User> users = new ArrayList<User>();
            for (Integer id : userIds) {
                users.add(userService.findOne(id));
            }
            userGroup.setUsers(users);
        }

        if(logo!=null){
            try {
                userGroup.setLogo(logo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        userGroupService.save(userGroup);
        return "redirect:/groups";
    }

}
