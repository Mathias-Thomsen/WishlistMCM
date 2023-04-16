package com.example.wishlistmcm.repositories;

import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.utility.LoginException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository("stubRepository")
public class StubRepository implements IRepository {



    //Test data USERS
    private User user1 = new User(1, "John Doe", "johndoe@example.com", "password");
    private User user2 = new User(2, "Jane Doe", "janedoe@example.com", "password");
    private User user3 = new User(3, "Bob Smith", "bobsmith@example.com", "password");


    private List<User> users = new ArrayList<>(Arrays.asList(user1,user2,user3));

    //Test data wishlists
    Wishlist wishlist1 = new Wishlist(1, "Christmas List", 1);
    Wishlist wishlist2 = new Wishlist(2, "Birthday List", 2);
    Wishlist wishlist3 = new Wishlist(3, "Wedding List", 3);

    private List<Wishlist> wishlists = new ArrayList<>(Arrays.asList(wishlist1,wishlist2,wishlist3));

    //Test data wishes
    Wish wish1 = new Wish(1, "Headphones", "https://www.amazon.com/dp/B08NW7X9YH", "Noise-cancelling headphones", 199.99, 1);
    Wish wish2 = new Wish(2, "Smartwatch", "https://www.amazon.com/dp/B07W6RJ99N", "Fitness tracker smartwatch", 149.99, 2);
    Wish wish3 = new Wish(3, "Coffee maker", "https://www.amazon.com/dp/B07Z2M72F7", "Automatic coffee maker", 89.99, 3);


    private List<Wish> whises = new ArrayList<>(Arrays.asList(wish1,wish2,wish3));




    @Override
    public User login(String email, String password){

        for (User user : users) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;

    }

    @Override
    public User createUser(User user){
        User newUser = new User(user.getEmail(), user.getFullName(), user.getPassword());
        users.add(newUser);
        return newUser;
    }

    @Override
    public void editUser(User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == user.getUserId()) {
                users.set(i, user);
                return;
            }
        }

    }

    @Override
    public User getUserFromId(int id) {
        for (User user : users) {
            if(user.getUserId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(int userId) {
        // Find the user with the specified ID
        for (User user : users) {
            if(user.getUserId() == userId){
                users.remove(user);
            }
        }
    }



    @Override
    public Wishlist createWishlist(Wishlist list, int userId) {
        Wishlist newWishlist = new Wishlist(getNextIdWishlist(), list.getWishlistName(), userId);
        wishlists.add(newWishlist);
        return newWishlist;
    }

    // Helper method to get the next available ID for the wishlist
    private int getNextIdWishlist() {
        int maxId = 0;
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getWishlistId() > maxId) {
                maxId = wishlist.getWishlistId();
            }
        }
        return maxId + 1;
    }

    @Override
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        List<Wish> result = new ArrayList<>();
        for (Wish wish : whises) {
            if (wish.getWishlistId() == wishlistId) {
                result.add(wish);
            }
        }
        return result;
    }


    @Override
    public Wish createWish(Wish wish, int wishlistId) {
        for (Wishlist w : wishlists) {
            if (w.getWishlistId() == wishlistId) {
                wish.setWishId(whises.size() + 1);
                wish.setWishlistId(wishlistId);
                whises.add(wish);
                return wish;
            }
        }
        return null; // wishlist not found
    }







    @Override
    public List<Wishlist> getUserWishlists(int userId) {
        List<Wishlist> userWishlists = new ArrayList<>();
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getUserId() == userId) {
                userWishlists.add(wishlist);
            }
        }
        return userWishlists;
    }


    @Override
    public void deleteWishlist(int id) {
        for(Wishlist wishlist : wishlists) {
            if(wishlist.getWishlistId() == id){
                wishlists.remove(wishlist);
            }
        }
    }


    @Override
    public Wish getWishFromId(int id) {

        for (Wish wish : whises) {
            if(wish.getWishlistId() == id){
                return wish;
            }
        }
        return null;

    }


    @Override
    public void editWish(Wish wish) {
        // Find the wish with the same ID as the input wish
        for (Wish w : whises) {
            if (w.getWishId() == wish.getWishId()) {
                // Update the wish with the new values
                w.setWishName(wish.getWishName());
                w.setWishLink(wish.getWishLink());
                w.setWishDescription(wish.getWishDescription());
                w.setPrice(wish.getPrice());
                w.setWishlistId(wish.getWishlistId());
                return;
            }
        }

    }



    @Override
    public void deleteWish(int id) {
        for (Wish wish : whises) {
            if (wish.getWishId() == id) {
                whises.remove(wish);
                break;
            }
        }
    }

    @Override
    public int findWishlistId(int id) {
        return 0;
    }
}