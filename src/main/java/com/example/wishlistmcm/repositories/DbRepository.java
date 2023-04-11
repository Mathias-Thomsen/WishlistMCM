package com.example.wishlistmcm.repositories;


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
import java.util.List;

@Repository("dbRepository")
public class DbRepository implements IRepository {


    @Override
    public User login(String email, String password) throws LoginException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM user WHERE email = ? AND USER_password = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("USER_id");
                User user = new User(email, password);
                user.setUserId(id);
                return user;
            } else {
                throw new LoginException("Wrong email or password. Try again");
            }
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    @Override
    public User createUser(User user) throws LoginException {
        try {
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
        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }


    public void deleteUser(int userId) throws LoginException {
        try (Connection con = DBManager.getConnection()) {
            String SQL = "DELETE FROM USER WHERE USER_ID = ?;";
            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
        }


    }

    @Override
    public Wishlist createWishlist(Wishlist list, int userId) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Wishlist> getUserWishlists(int userId) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT WISHLIST_ID, WISHLIST_NAME, USER_ID FROM WISHLIST WHERE USER_ID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Wishlist> list = new ArrayList<>();
            while (rs.next()) {
                int wishlistId = rs.getInt("WISHLIST_ID");
                String wishlistName = rs.getString("WISHLIST_NAME");
                int userID = rs.getInt("USER_ID");
                Wishlist wishlist = new Wishlist(wishlistId, wishlistName, userId);
                list.add(wishlist);
            }

            return list;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT WISH_ID, WISH_NAME, LINK_TO_WISH, WISH_DESCRIPTION, WISH_PRICE, WISHLIST_ID \n" +
                    "FROM WISH\n" +
                    "WHERE WISHLIST_ID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, wishlistId);
            ResultSet rs = ps.executeQuery();


            List<Wish> list = new ArrayList<>();
            while (rs.next()) {
                int wishId = rs.getInt("WISH_ID");
                String wishName = rs.getString("WISH_NAME");
                String wishLink = rs.getString("LINK_TO_WISH");
                String wishDescription = rs.getString("WISH_DESCRIPTION");
                double wishPrice = rs.getDouble("WISH_PRICE");
                Wish wishlist = new Wish(wishId, wishName, wishLink, wishDescription, wishPrice, wishlistId);
                list.add(wishlist);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Wish createWish(Wish wish, int wishlistId) {
        try {
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
        } catch (SQLException ex) {
            return null;
        }
    }


    public void deleteWishlist(int id) {
        try (Connection con = DBManager.getConnection()) {
            String SQL = "DELETE FROM wishlist WHERE WISHLIST_ID = ?;";
            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public Wish getWishFromId(int id) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM WISH WHERE WISH_ID = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Wish wish1 = null;
            if (rs.next()) {
                int wishId = rs.getInt("WISH_ID");
                String wishName = rs.getString("WISH_NAME");
                String wishLink = rs.getString("LINK_TO_WISH");
                String wishDescription = rs.getString("WISH_DESCRIPTION");
                double wishPrice = rs.getDouble("WISH_PRICE");
                wish1 = new Wish(wishId, wishName, wishLink, wishDescription, wishPrice, id);

            }

            return wish1;

        } catch (SQLException ex) {
            return null;
        }
    }

    public void editWish(Wish wish) {
        try (Connection con = DBManager.getConnection()) {
            String SQL = "UPDATE WISH SET WISH_NAME = ?, LINK_TO_WISH = ?, WISH_DESCRIPTION = ?, WISH_PRICE = ? WHERE WISH_ID = ?;";
            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, wish.getWishName());
                stmt.setString(2, wish.getWishLink());
                stmt.setString(3, wish.getWishDescription());
                stmt.setString(4, String.valueOf(wish.getPrice()));
                stmt.setInt(5, wish.getWishId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
