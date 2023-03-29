package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.repositories.DbManager;
import com.example.wishlistmcm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({""})
public class UserController {

    UserService userService;

    @GetMapping(value = {"/"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/signup"})
    public String create() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("email") String email,
                                @RequestParam("fullname") String fullname,
                                @RequestParam("password") String password) {
        return "redirect:/";
    }


    @GetMapping(value = {"/wishlists"})
    public String wishlists() {
        return "wishlists";
    }

    @GetMapping(value = {"/wishes"})
    public String wishes() {
        return "wishes";
    }

}
