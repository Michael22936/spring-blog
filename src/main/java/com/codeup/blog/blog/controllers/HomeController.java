package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // this is the spring mapping for the landing page
    @GetMapping("/")
    public String landingPage(){
        return "posts/home";
    }
}
