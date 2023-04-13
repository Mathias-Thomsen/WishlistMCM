package com.example.wishlistmcm.controllers;
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

    private int getUserId(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        return userId;
    }


    private boolean hasAccessToWish(int userId, int wishId) {
        Wish wish = repository.getWishFromId(wishId);
        int wishlistId = wish.getWishlistId();
        return hasAccessToWishlist(userId, wishlistId);
    }

    private boolean hasAccessToWishlist(int userId, int wishlistId) {
        List<Wishlist> wishlists = repository.getUserWishlists(wishlistId);
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }


    @GetMapping(value = {"/userFrontend"})
    public String index(HttpServletRequest request) {
        if (getUserId(request) != 0){
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
        int userId = getUserId(request);

        Wishlist wishlist = repository.createWishlist(list, userId);
        return "userFrontend";
    }

    @GetMapping(value = {"/showAllWishlists"})
    public String showAllWishlists(HttpServletRequest request, Model model) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }

        List<Wishlist> list = repository.getUserWishlists(userId);
        model.addAttribute("wishlists", list);
        return "showAllWishlists";
    }

    @GetMapping(value = {"/deleteWishlist/{id}"})
    public String deleteWishlist(HttpServletRequest request, @PathVariable("id") int id){
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        if(!hasAccessToWishlist(userId, id)){
            return "error/accessDenied";
        }else {
            repository.deleteWishlist(id);
            return "redirect:/showAllWishlists";
        }
    }

    @GetMapping(value = {"/wishes/{id}"})
    public String showWishesFromWishlistId(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        if(!hasAccessToWishlist(userId, id)){
            return "error/accessDenied";
        }else {
            List<Wish> wishToList = repository.getWishesByWishlistId(id);
            model.addAttribute("id", id);
            model.addAttribute("wishes", wishToList);
            return "wishes";
        }

    }


    @GetMapping(value = {"/addWish/{id}"})
    public String showAddWish(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        if(!hasAccessToWish(userId, id)){
            return "error/accessDenied";
        } else {
            model.addAttribute("id", id);
            model.addAttribute("newWish", new Wish());
            return "addWish";
        }
    }


    @PostMapping(value = {"/addWish/{id}"})
    public String addWish(HttpServletRequest request, @PathVariable("id") int id, @ModelAttribute Wish wish, Model model) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        Wish wish1 = repository.createWish(wish, id);
        return "redirect:/wishes/" + id;
    }



    @GetMapping(value = {"/editWish/{id}"})
    public String showEditWish(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        if(!hasAccessToWish(userId, id)){
            return "error/accessDenied";
        }else {
            model.addAttribute("id", id);
            model.addAttribute("wish", repository.getWishFromId(id));
            return "editWish";
        }

    }


    @PostMapping(value = {"/editWish/{id}"})
    public String editWish(HttpServletRequest request, @ModelAttribute Wish wish, @PathVariable int id) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        repository.editWish(wish);
        int wishlistId = repository.findWishlistId(id);
        return "redirect:/wishes/" + wishlistId;

    }

    @GetMapping(value = {"/deleteWish/{id}"})
    public String deleteWish(HttpServletRequest request, @PathVariable("id") int id) {
        int userId = getUserId(request);
        if (userId == 0) {
            return "login";
        }
        if(!hasAccessToWish(userId, id)){
            return "error/accessDenied";
        }else {
            int wishlistId = repository.findWishlistId(id);
            repository.deleteWish(id);
            return "redirect:/wishes/" + wishlistId;
        }

    }

}
