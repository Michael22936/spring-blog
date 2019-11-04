package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number}/and/{number1}")
    @ResponseBody
    public String add(@PathVariable int number, @PathVariable int number1){
        return number + " + " + number1 + " = " + (number + number1);
    }

    @GetMapping("/subtract/{number}/from/{number1}")
    @ResponseBody
    public String subtract(@PathVariable int number, @PathVariable int number1){
        return number + " - " + number1 + " = " + (number - number1);
    }

    @GetMapping("/multiply/{number}/and/{number1}")
    @ResponseBody
    public String multiply(@PathVariable int number, @PathVariable int number1){
        return number + " * " + number1 + " = " + (number * number1);
    }

    @GetMapping("/divide/{number}/by/{number1}")
    @ResponseBody
    public String divide(@PathVariable double number, @PathVariable double number1){
        return number + " / " + number1 + " = " + (number / number1);
    }

}
