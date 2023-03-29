package com.example.wishlistmcm.service;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.repositories.IRepository;

public class LoginService {


    IRepository iRepository;




    public User loginUser(String email, String password){
        return iRepository.login(email, password);
    }

    public User createUser(String email, String fullName, String password) {
        User user = new User(email, fullName, password);
        return iRepository.createUser(user);
    }
}
