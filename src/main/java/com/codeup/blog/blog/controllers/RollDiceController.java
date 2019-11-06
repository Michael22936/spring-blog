package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String roll(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String dice(@PathVariable int number, Model model){
        model.addAttribute("number", number);
        Random random = new Random();
        int randomNum = random.nextInt(6);
        int[] ranNum = {1,2,3,4,5,6};
        model.addAttribute("guess", ranNum[randomNum]);
        return "roll-dice";
    }

}
