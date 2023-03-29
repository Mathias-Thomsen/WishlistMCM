package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.repositories.IRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({""})
public class LoginController {

    IRepository repository;
    public LoginController(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IRepository) context.getBean(impl);
    }
    @PostMapping("login")
    public String loginUser(HttpServletRequest request, @ModelAttribute User user) {
        User user1 = repository.login(user.getEmail(), user.getPassword());

        if(user1 != null) {
            request.getSession().setAttribute("userID", user.getUserId());

            return "wishlists";
        }else {
            return "redirect:/";
        }

    }

    @GetMapping("signUp")
    public String showSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("signUp")
    public String signUp(HttpServletRequest request, @ModelAttribute User user) {
        User user1 = repository.createUser(user);
        return "login";
    }
}
