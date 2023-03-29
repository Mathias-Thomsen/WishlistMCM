package com.example.wishlistmcm.controllers;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.repositories.IRepository;
import com.example.wishlistmcm.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class LoginController {

    LoginService loginService;

    public LoginController(ApplicationContext context, @Value("${wishlist.repository.impl}") String impl) {
        loginService = (LoginService) context.getBean(impl);
    }

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // delegate work + data to login controller
        User user = loginService.loginUser(email, pwd);

        // Set user and role in session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);


        return "login";
    }

    @PostMapping("/register")
    public String createUser(WebRequest request) {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to login service
        if (password1.equals(password2)) {
            User user = loginService.createUser(email, fullName, password1);
            request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        } else { // If passwords don't match, an exception is thrown
            System.out.println("Password dont match");

        }
        return "index";

    }

}
