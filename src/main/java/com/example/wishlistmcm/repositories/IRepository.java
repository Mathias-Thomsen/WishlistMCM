package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.utility.LoginException;

public interface IRepository {

    User login(String email, String password) throws LoginException;

    User createUser(User user) throws LoginException;

    Wishlist createWishlist(Wishlist list, int user1);
}
