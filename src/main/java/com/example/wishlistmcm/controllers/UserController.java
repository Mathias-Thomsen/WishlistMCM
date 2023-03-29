package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.repositories.IRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({""})
public class UserController {

    IRepository repository;
    public UserController(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IRepository) context.getBean(impl);
    }

    @GetMapping(value = {"/"})
    public String index() {
        return "index";
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
