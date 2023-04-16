package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.repositories.IRepository;
import com.example.wishlistmcm.utility.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "")
public class LoginController {

    IRepository repository;
    public LoginController(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        repository = (IRepository) context.getBean(impl);
    }




    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("userId") != null) {
            return "redirect:/userFrontend";
        } else {
            model.addAttribute("user", new User());
            return "login";
        }
    }



    @PostMapping(path = "/login")
    public String loginUser(HttpServletRequest request, @ModelAttribute("user") User user, Model model) {
        try {
            User user1 = repository.login(user.getEmail(), user.getPassword());
            if (user1 != null) {
                request.getSession().setAttribute("userId", user1.getUserId());
                return "redirect:/userFrontend";
            } else {
                throw new LoginException("Invalid email or password");
            }
        } catch (LoginException e) {
            model.addAttribute("errorLogin", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(HttpServletRequest request, @ModelAttribute User user, Model model) throws LoginException {

        if (!user.getPassword().equals(request.getParameter("passwordConfirm"))) {
            model.addAttribute("errorSignup", "Passwords do not match");
            return "signup";
        }
        User user1 = repository.createUser(user);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
