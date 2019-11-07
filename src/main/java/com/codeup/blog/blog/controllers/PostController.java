package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.Post;
import com.codeup.blog.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }
    @GetMapping("/posts")
    public String index(Model model){

        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){

        model.addAttribute("post", postDao.getOne(id));

        return  "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(body);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/delete/posts")
    public String deletePostBy(@RequestParam("id")  String id){
        long deleteId = Long.parseLong(id);
        postDao.deleteById(deleteId);
        return "redirect:/posts";
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
