package org.geymer.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 19.11.13
 */
@Controller
@RequestMapping("/")
public class FrontController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
