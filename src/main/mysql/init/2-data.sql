USE wishList;

INSERT INTO USER (FULLNAME, EMAIL, USER_PASSWORD)
VALUES
('John Doe', 'johndoe@example.com', '1234'),
('Jane Smith', 'janesmith@example.com', '1234'),
('Mike Brown', 'mikebrown@example.com', '1234');


INSERT INTO WISHLIST (WISHLIST_NAME, USER_ID)
VALUES
('John Doe Wishlist', 1),
('Jane Smith Wishlist', 2),
('Mike Brown Wishlist', 3);

INSERT INTO WISH (WISH_ID, WISH_NAME, LINK_TO_WISH, WISH_DESCRIPTION, WISH_PRICE, WISHLIST_ID)
VALUES
(1, 'iPhone 13', 'https://www.apple.com/shop/buy-iphone/iphone-13', 'The latest iPhone from Apple', 1099.00, 1),
(2, 'Nintendo Switch', 'https://www.nintendo.com/switch/', 'A popular gaming console from Nintendo', 299.00, 1),
(3, 'Amazon Gift Card', 'https://www.amazon.com/gift-cards', 'A gift card for online shopping', 50.00, 3 );

