package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.PostImage;
import com.codeup.blog.blog.models.Tag;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.PostImageRepository;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.TagRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import com.codeup.blog.blog.services.TextServices;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final PostImageRepository postImageDao;
    private final TagRepository tagDao;
    private final UserRepository userDao;
    private final TextServices  textServices;
    @Autowired
    EmailService emailService;

    public PostController(PostRepository postDao, PostImageRepository postImageDao, TagRepository tagDao, UserRepository userDao, TextServices textServices) {
        this.postDao = postDao;
        this.postImageDao = postImageDao;
        this.tagDao = tagDao;
        this.userDao= userDao;
        this.textServices = textServices;
    }

    //returns on the post on one page
    @GetMapping("/posts")
    public String index(Model model){

        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

    //returns one post with a specific id
    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){

        model.addAttribute("post", postDao.getOne(id));

        return  "posts/show";
    }

    //returns a edit page to a user
    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    //saves the changes to a post with the same id
    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(body);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    //deletes a specific post
    @PostMapping("/posts/delete")
    public String deletePostBy(@RequestParam("id")  String id){
        long deleteId = Long.parseLong(id);
        postDao.deleteById(deleteId);
        return "redirect:/posts";
    }

    //return a page to make a post
    @GetMapping("/posts/create")
    public String showForm(Model model){
        model.addAttribute("posts", new Post());
        return  "posts/create";
    }

    //adds the new post to the database
    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post createdNewPost){
        createdNewPost.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Post post = postDao.save(createdNewPost);
        emailService.prepareAndSend(post, "Ad created", "An ad has been created with this title " + post.getTitle());
        System.out.println();
        return "redirect:/posts";
    }

    //returns the history of that specif post
    @GetMapping("/posts/history/{id}")
    public String testView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/test";
    }

    //returns a page to added a picture to a post
    @GetMapping("/posts/{id}/add-image")
    public String catView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/test";
    }

    //saves the new picture to that specific post
    @PostMapping("/posts/{id}/add-image")
    public String addImage(
            @PathVariable long id,
            @RequestParam String title,
            @RequestParam String url) {

        PostImage postImage = new PostImage(title, url);
        postImage.setPost(postDao.getOne(id));
        postImageDao.save(postImage);

        return "redirect:/posts/{id}/add-image";
    }

    @GetMapping("/post-tags")
    public String getPetVets(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/test";
    }

    @PostMapping("/tags/post/{id}")
    public String assignNewTagToPost(
            @PathVariable long id,
            @RequestParam String tag) {
        Post post = postDao.getOne(id);
        tagDao.save(new Tag(tag, Arrays.asList(post)));
        return "redirect:/post-tags";
    }


}
