package org.geymer.users.controller;

import org.apache.log4j.Logger;
import org.geymer.users.entity.User;
import org.geymer.users.service.UserGroupService;
import org.geymer.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    UserGroupService userGroupService;

    @RequestMapping(method = RequestMethod.GET)
    public String Users(ModelMap model) {
        logger.debug("userController");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("groups", userGroupService.findAll());
        return "users/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(value = "group", required = false) Integer groupId, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("groups", userGroupService.findAll());
            return "users/add";
        }
        //TODO:replace request with @RequiredParam
        if (groupId != null && groupId != -1) {
            user.setUserGroup(userGroupService.findOne(groupId));
        }
        userService.save(user);
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = u.getName();
        logger.info("User " + name + " added user " + user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete")
    public String addUser(@RequestParam(value = "id", required = false) Integer id, Model model) {

        if (id == null) {
            model.addAttribute("idError", "");
            return "forward:/users/";
        }
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = u.getName();
        logger.info("User " + name + " added user " + userService.findOne(id));
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(value = "id", required = false) Integer id) {

        if (id == null) {
            model.addAttribute("idError", "");
            return "forward:/users/";
        }

        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("groups", userGroupService.findAll());

        return "users/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("user") User user, BindingResult result,
                       @RequestParam(value = "groupId", required = false) Integer groupId, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("groups", userGroupService.findAll());
            return "users/edit";
        }
        if (groupId != null && groupId != -1) {
            user.setUserGroup(userGroupService.findOne(groupId));
        }
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/json")
    @ResponseBody
    List<User> json() {
        return userService.findAll();
    }


}
