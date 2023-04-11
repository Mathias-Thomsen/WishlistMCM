package com.example.wishlistmcm.DTO;

import com.example.wishlistmcm.entites.Wish;

import java.util.List;

public class UserAllWishListsDTO {
    private int wishListId;
    private String wishListName;
    private List<Wish> userwishes;


    public UserAllWishListsDTO(String wishListName, List<Wish> userwishes) {
        this.wishListName = wishListName;
        this.userwishes = userwishes;
    }

    public UserAllWishListsDTO(List<Wish> userwishes) {
        this.userwishes = userwishes;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public List<Wish> getUserwishes() {
        return userwishes;
    }

    public void setUserwishes(List<Wish> userwishes) {
        this.userwishes = userwishes;
    }
}


