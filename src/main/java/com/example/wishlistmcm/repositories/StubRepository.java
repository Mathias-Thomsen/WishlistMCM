package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;
import org.springframework.stereotype.Repository;


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
}