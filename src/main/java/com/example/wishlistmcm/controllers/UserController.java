package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.DTO.UserAllWishListsDTO;
import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.repositories.IRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({""})
public class UserController {

    IRepository repository;
    public UserController(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IRepository) context.getBean(impl);
    }

    @GetMapping(value = {"/userFrontend"})
    public String index(Model model, HttpServletRequest request) {
        int user1 = (int) request.getSession().getAttribute("userId");

        if (user1 != 0 ){
            List<UserAllWishListsDTO> wishlist = repository.getUserWishlists(user1);
            model.addAttribute("wishlists", wishlist);
            return "userFrontend";
        }else {
            return "index";
        }

    }

    @GetMapping(value = {"/createWishlist"})
    public String showCreateWishlistForm(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        return "createWishlist";
    }

    @PostMapping(value = {"/createWishlist"})
    public String processCreateWishlist(HttpServletRequest request, @ModelAttribute Wishlist list) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }

        int user1 = (int) request.getSession().getAttribute("userId");
        Wishlist wishlist = repository.createWishlist(list, user1);
        return "userFrontend";
    }








}
