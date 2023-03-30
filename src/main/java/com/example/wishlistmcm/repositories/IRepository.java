package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.DTO.UserAllWishListsDTO;
import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.utility.LoginException;

import java.util.List;

public interface IRepository {

    User login(String email, String password) throws LoginException;

    User createUser(User user) throws LoginException;

    Wishlist createWishlist(Wishlist list, int user1);

    Wish createWish(Wish wish, int wishlistId);

    List<UserAllWishListsDTO> getUserWishlists(int user1);
}
