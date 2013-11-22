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

import javax.servlet.http.HttpServletRequest;
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
    public String addUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("groups",userGroupService.findAll());
        return "users/add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("user") User user,BindingResult result,HttpServletRequest request,Model model) {

        if(result.hasErrors()){
            model.addAttribute("groups",userGroupService.findAll());
            return "users/add";
        }
        String groupId=request.getParameter("group");
         if(groupId!=null&&!groupId.equals("-1")){
             user.setUserGroup(userGroupService.findOne(Integer.parseInt(groupId)));
         }
        userService.save(user);
        return "redirect:/users";
    }


    @RequestMapping(value = "/delete")
    public String addUser(HttpServletRequest request,Model model) {
        String id;
        if ((id = request.getParameter("id")) == null) {
            model.addAttribute("idError","");
                                            return "forward:/users/";
        }

        userService.delete(Integer.parseInt(id));
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editUser(Model model,HttpServletRequest request) {
        String id;
        if ((id = request.getParameter("id")) == null) {
            model.addAttribute("idError","");
            return "forward:/users/";
        }

        model.addAttribute("user",userService.findOne(Integer.parseInt(id)));
        model.addAttribute("groups",userGroupService.findAll());

        return "users/edit";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("user") User user,BindingResult result,HttpServletRequest request,Model model) {

        if(result.hasErrors()){
            model.addAttribute("groups",userGroupService.findAll());
            return "users/edit";
        }
        String groupId=request.getParameter("group");
        if(groupId!=null&&!groupId.equals("-1")){
            user.setUserGroup(userGroupService.findOne(Integer.parseInt(groupId)));
        }
        userService.save(user);
        return "redirect:/users";
    }



}
