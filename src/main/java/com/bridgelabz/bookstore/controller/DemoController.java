package com.bridgelabz.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class DemoController {

    @GetMapping("/get")
    public String getBookName(){
        return "ReactJs";
    }
}
