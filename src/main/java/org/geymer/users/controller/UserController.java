package org.geymer.users.controller;

import org.geymer.users.entity.User;
import org.geymer.users.service.UserGroupService;
import org.geymer.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    @Autowired
    UserService userService;
    @Autowired
    UserGroupService userGroupService;

    @RequestMapping(method = RequestMethod.GET)
    public String Users(ModelMap model) {
        model.addAttribute("users",userService.findAll());
        return "users";
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("user",new User());
        model.addAttribute("submitButton","Register");
        model.addAttribute("groups",userGroupService.findAll());
        model.addAttribute("title","Add new user");
        return "userAdd";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("user") User user,BindingResult result,@RequestParam("group")Integer groupId) {
        if(result.hasErrors()){
            return "userAdd";
        }
        if(groupId!=-1)user.setUserGroup(userGroupService.findOne(groupId));
        userService.save(user);
        return "redirect:/users";
    }


    @RequestMapping(value = "/delete")
    public String addUser(@RequestParam("id") Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editUser(Model model,@RequestParam("id") Integer id) {
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("submitButton","Edit");
        model.addAttribute("title","Edit user");
        model.addAttribute("groups",userGroupService.findAll());

        return "userAdd";
    }

}
