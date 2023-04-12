package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.DTO.UserAllWishListsDTO;
import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
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
    public String index(HttpServletRequest request) {
        int user1 = (int) request.getSession().getAttribute("userId");
        if (user1 != 0 ){
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

    @GetMapping(value = {"/showAllWishlists"})
    public String showAllWishlists(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }
        int user1 = (int) request.getSession().getAttribute("userId");
        List<Wishlist> list = repository.getUserWishlists(user1);
        model.addAttribute("wishlists", list);
        return "showAllWishlists";
    }

    @GetMapping(value = {"/deleteWishlist/{id}"})
    public String deleteWishlist(@PathVariable("id") int id){
        repository.deleteWishlist(id);
        return "redirect:/showAllWishlists";
    }

    @GetMapping(value = {"/wishes/{id}"})
    public String showWishesFromWishlistId(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }
        List<Wish> wishToList = repository.getWishesByWishlistId(id);
        model.addAttribute("id", id);
        model.addAttribute("wishes", wishToList);
        return "wishes";

    }

    @GetMapping(value = {"/addWish/{id}"})
    public String showAddWish(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }
        model.addAttribute("id", id);
        model.addAttribute("newWish", new Wish());
        return "addWish";

    }


    @PostMapping(value = {"/addWish/{id}"})
    public String addWish(@PathVariable("id") int id, @ModelAttribute Wish wish, Model model) {
        Wish wish1 = repository.createWish(wish, id);

        return "redirect:/wishes/" + id;
    }



    @GetMapping(value = {"/editWish/{id}"})
    public String showEditWish(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }
        model.addAttribute("id", id);
        model.addAttribute("wish", repository.getWishFromId(id));
        return "editWish";

    }


    @PostMapping(value = {"/editWish/{id}"})
    public String editWish(@ModelAttribute Wish wish, @PathVariable int id) {
        repository.editWish(wish);
        int wishlistId = repository.findWishlistId(id);
        return "redirect:/wishes/" + wishlistId;
    }

    @GetMapping(value = {"/deleteWish/{id}"})
    public String deleteWish( @PathVariable("id") int id) {
        int wishlistId = repository.findWishlistId(id);
        repository.deleteWish(id);
        return "redirect:/wishes/" + wishlistId;

    }

}
