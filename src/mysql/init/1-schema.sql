DROP DATABASE IF EXISTS wishlist;
CREATE DATABASE wishlist;
USE wishlist; 

CREATE TABLE user (
    user_id INT AUTO_INCREMENT,
    fullname VARCHAR(255),
    email VARCHAR(255),
    user_password VARCHAR(255),
    PRIMARY KEY(user_id)
);

CREATE TABLE wishlist (
    wishlist_id INT AUTO_INCREMENT,
    wishlist_name VARCHAR(255),
    user_id INT,
    PRIMARY KEY(wishlist_id),
    FOREIGN KEY(user_id) REFERENCES user(user_id)
);

CREATE TABLE wish (
    wish_id INT AUTO_INCREMENT,
    wish_name VARCHAR(255),
    link_to_wish VARCHAR(2000),
    wish_description VARCHAR(6000),
    wish_price DECIMAL,
    wishlist_id INT,
    PRIMARY KEY(wish_id),
    FOREIGN KEY(wishlist_id) REFERENCES wishlist(wishlist_id)
);
