package com.example.wishlistmcm.repositories;


import com.example.wishlistmcm.entites.User;
import org.springframework.stereotype.Repository;

@Repository("dbRepository")
public class DbRepository implements IRepository {

    public User login(String email, String password){
        return null;
    }

    public User createUser(User user){
        return null;
    }

}
