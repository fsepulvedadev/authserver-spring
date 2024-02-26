package com.fsepulvedadev.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @GetMapping("/")
    public String helloAdminControlleString() {
        return "Admin level access";
    }
    

}
