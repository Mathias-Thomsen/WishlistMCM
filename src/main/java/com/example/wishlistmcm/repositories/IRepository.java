package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;

public interface IRepository {

    User login(String email, String password);

    User createUser(User user);
}
