package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
import com.example.wishlistmcm.entites.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("stubRepository")
public class StubRepository implements IRepository {
    @Override
    public User login(String email, String password){
        User user = new User("test@test.com", "customer");
        user.setUserId(1);
        return user;
    }

    @Override
    public User createUser(User user){
        User newUser = new User("test@test.com", "Mathias Thomsen", "1234");
        return newUser;
    }

    @Override
    public Wishlist createWishlist(Wishlist list, int user1) {
        return null;
    }

    @Override
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        return null;
    }

    @Override
    public Wish createWish(Wish wish, int wishlistId) {
        return null;
    }

    @Override
    public List<Wishlist> getUserWishlists(int userid) {
        return null;
    }

    @Override
    public void deleteWishlist(int id) {

    }

    @Override
    public Wish getWishFromId(int id) {
        return null;
    }

    @Override
    public void editWish(Wish wish) {

    }
}