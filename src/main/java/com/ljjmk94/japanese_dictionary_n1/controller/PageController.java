package com.ljjmk94.japanese_dictionary_n1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    // CREATE
    @GetMapping("/append")
    public String append() {
        return "crud/append";
    }
    // READ
    @GetMapping("/search")
    public String search() {
        return "crud/search";
    }
    // UPDATE 
    @GetMapping("/update")
    public String update() {
        return "crud/update";
    }
    // DELETE
    @GetMapping("/delete")
    public String delete() {
        return "crud/delete";
    }
}
