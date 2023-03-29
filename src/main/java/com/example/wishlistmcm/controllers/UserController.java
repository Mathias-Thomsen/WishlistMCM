package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.repositories.DbManager;
import com.example.wishlistmcm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"wishlistmcm"})
public class UserController {

    UserService userService;

    @GetMapping(value = {"/"})
    public String index(){
        return "index";
    }

}
