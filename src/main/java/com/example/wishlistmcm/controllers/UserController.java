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

   @GetMapping(value = {"/login"})
        public String login(){
            return "login";
       }

       @GetMapping(value = {"/signup"})
       public String create(){
        return "signup";
       }

       @GetMapping(value = {"/wishlists"})
    public String wishlists(){
        return "wishlists";
       }

       @GetMapping(value = {"/wishes"})
    public String wishes(){
        return "wishes";
       }

}
