package com.example.wishlistmcm.entites;

public class Wishlist {

    private int wishlistId;
    private String wishlistName;
    private int userId;

    public Wishlist(int wishlistId, String wishlistName, int userId) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.userId = userId;
    }


    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
