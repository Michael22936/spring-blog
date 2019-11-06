package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    ArrayList<Post> adsList;

    public PostController() {
        adsList = new ArrayList<>();

        adsList.add(new Post(1,"first post","first ad"));
        adsList.add(new Post(2,"second post","second ad"));
    }


    @GetMapping("/posts")
    public String index(Model model){

        model.addAttribute("posts", adsList);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){

        model.addAttribute("post", adsList.get( (int) id - 1 ));

        return  "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showForm(){
        return  "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create(@RequestParam String title, @RequestParam String body){
        System.out.println("title = " + title);
        System.out.println("body = " + body);
        return "create new post";
    }

}
