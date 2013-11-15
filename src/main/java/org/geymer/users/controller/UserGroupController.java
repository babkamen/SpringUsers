package org.geymer.users.controller;
import org.geymer.users.entity.User;
import org.geymer.users.entity.UserGroup;
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


    @RequestMapping(method = RequestMethod.GET)
    public String userGroups(ModelMap model) {
        model.addAttribute("userGroups",userGroupService.findAll());
        return "groups";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(@RequestParam("q") String name,Model model) {
        model.addAttribute("userGroups",userGroupService.findByNameContaining(name));
        return "groups";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addUser(ModelMap model) {
          model.addAttribute("userGroup",new UserGroup());
        model.addAttribute("submitButton","Register");
        model.addAttribute("title","Add new group");
        model.addAttribute("users",userService.findUsersNotInGroup());
        return "groupAdd";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("userGroup") UserGroup userGroup,BindingResult result,@RequestParam("userIds") List<Integer> userIds) {
        if(result.hasErrors()){
            return "groupAdd";
        }
        if(userIds.size()>0){
            List<User> users=new ArrayList<User>();
            for(int id:userIds){
                users.add(userService.findOne(id));
            }
             userGroup.setUsers(users);
        }
        userGroupService.save(userGroup);
        return "redirect:/groups";
    }


    @RequestMapping(value = "/delete")
    public String addUser(@RequestParam("id") Integer id) {
        userGroupService.delete(id);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editUser(Model model,@RequestParam("id") Integer id) {
        model.addAttribute("userGroup",userGroupService.findOne(id));
        model.addAttribute("submitButton","Edit");
        model.addAttribute("title","Edit group");
        model.addAttribute("users",userService.findAll());

        return "groupAdd";
    }






}
