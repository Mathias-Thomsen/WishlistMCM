package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.utility.LoginException;

import java.util.List;

public interface IRepository {

    User login(String email, String password) throws LoginException;

    User createUser(User user) throws LoginException;


    void editUser(User user) throws LoginException;

    User getUserFromId(int id);

    Wishlist createWishlist(Wishlist list, int user1);
    List<Wish> getWishesByWishlistId(int wishlistId);

    Wish createWish(Wish wish, int wishlistId);

    List<Wishlist> getUserWishlists(int user1);

    void deleteWishlist(int id);

    Wish getWishFromId(int id);

    void editWish(Wish wish);

    void deleteWish(int id);

    int findWishlistId(int id);
}
