package com.example.wishlistmcm.repositories;


import com.example.wishlistmcm.DTO.UserAllWishListsDTO;
import com.example.wishlistmcm.entites.User;
import com.example.wishlistmcm.entites.Wish;
import com.example.wishlistmcm.entites.Wishlist;
import com.example.wishlistmcm.utility.DBManager;
import com.example.wishlistmcm.utility.LoginException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("dbRepository")
public class DbRepository implements IRepository {


    @Override
    public User login(String email, String password) throws LoginException {
        try{
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM user WHERE email = ? AND USER_password = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt("USER_id");
                User user = new User(email, password);
                user.setUserId(id);
                return user;
            } else {
                throw new LoginException("Wrong email or password. Try again");
            }
        } catch(SQLException ex){
            throw new LoginException(ex.getMessage());
        }
    }

    @Override
    public User createUser(User user) throws LoginException {
        try{
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO user (email, user_password, fullname) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            User user1 = new User(user.getEmail(), user.getPassword());
            user.setUserId(id);
            return user1;
        } catch(SQLException ex){
            throw new LoginException(ex.getMessage());
        }
    }

    @Override
    public Wishlist createWishlist(Wishlist list, int userId) {
        try{
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO wishlist (wishlist_name, user_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, list.getWishlistName());
            ps.setInt(2, userId);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            Wishlist wishlist = new Wishlist(list.getWishlistName(), list.getUserId());
            wishlist.setUserId(id);
            return list;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Wish createWish(Wish wish, int wishlistId) {
        try{
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO WISH (WISH_NAME, LINK_TO_WISH, WISH_DESCRIPTION, WISH_PRICE, WISHLIST_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);


            ps.setString(1, wish.getWishName());
            ps.setString(2, wish.getWishLink());
            ps.setString(3, wish.getWishDescription());
            ps.setDouble(4, wish.getPrice());
            ps.setLong(5, wishlistId);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);


            Wish wish1 = new Wish();

            wish.setWishName(wish.getWishName());
            wish.setWishLink(wish.getWishLink());
            wish.setPrice(wish.getPrice());
            wish.setWishDescription(wish.getWishDescription());
            wish.setWishId(id);

            return wish1;
        } catch(SQLException ex){
            return null;
        }
    }

    @Override
    public List<UserAllWishListsDTO> getUserWishlists(int userId) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT w.WISHLIST_NAME, ws.WISH_NAME, ws.LINK_TO_WISH, ws.WISH_PRICE, ws.WISH_DESCRIPTION " +
                    "FROM WISHLIST w " +
                    "INNER JOIN WISH ws ON w.WISHLIST_ID = ws.WISHLIST_ID " +
                    "WHERE w.USER_ID = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            Map<String, List<Wish>> wishlists = new HashMap<>();
            while (rs.next()) {
                String wishlistName = rs.getString("WISHLIST_NAME");
                String wishName = rs.getString("WISH_NAME");
                String link = rs.getString("LINK_TO_WISH");
                double price = rs.getDouble("WISH_PRICE");
                String description = rs.getString("WISH_DESCRIPTION");

                Wish wish = new Wish(wishName, link, price, description);
                if (!wishlists.containsKey(wishlistName)) {
                    wishlists.put(wishlistName, new ArrayList<>());
                }
                wishlists.get(wishlistName).add(wish);
            }

            List<UserAllWishListsDTO> result = new ArrayList<>();
            for (Map.Entry<String, List<Wish>> entry : wishlists.entrySet()) {
                String wishlistName = entry.getKey();
                List<Wish> wishes = entry.getValue();
                UserAllWishListsDTO dto = new UserAllWishListsDTO(wishlistName, wishes);
                result.add(dto);
            }

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
