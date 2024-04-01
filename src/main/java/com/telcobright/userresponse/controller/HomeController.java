package com.telcobright.userresponse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/rest/auth/pp")
public class HomeController {
    @ResponseBody
    @RequestMapping(value = "/log",method = RequestMethod.GET)
    public String hello(){
        return "Successfully Token verified1!!";
    }
}