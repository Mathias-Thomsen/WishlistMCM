package com.example.wishlistmcm.entites;

public class Wish {
    private int wishId;
    private String wishName;
    private String wishLink;
    private double price;
    private String wishDescription;
    private int wishlistId;


    public Wish() {
    }

    public Wish(int wishId, String wishName, String wishLink, double price, String wishDescription) {
        this.wishId = wishId;
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.price = price;
        this.wishDescription = wishDescription;
    }


    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishLink() {
        return wishLink;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }
}
