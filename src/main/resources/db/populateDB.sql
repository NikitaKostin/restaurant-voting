DELETE FROM user_vote;
DELETE FROM user_role;
DELETE FROM users;
DELETE FROM restaurant_menu;
DELETE FROM restaurant;

INSERT INTO users (id, name, email, password)
VALUES (1, 'User', 'user@yandex.ru', 'password'),
       (2, 'Admin', 'admin@gmail.com', 'admin'),
       (3, 'Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2);

INSERT INTO restaurant (id, name)
VALUES (1, 'Burger cafe'),
       (2, 'Pizza cafe');

INSERT INTO restaurant_menu (id, dish_name, price, restaurant_id, create_date)
VALUES (1, 'Burger', 35, 1, '2020-04-04'),
       (2, 'Cheese Burger', 40, 1, '2020-04-05'),
       (3, 'Cola', 8, 1, '2020-04-04'),
       (4, 'Tea', 7, 1, '2020-04-05'),
       (5, 'Margarita', 25, 2, '2020-04-04'),
       (6, 'Chicago pizza', 35, 2, '2020-04-05'),
       (7, 'Coffee', 8, 2, '2020-04-05'),
       (8, 'Dish of the day', 30, 1, now());

INSERT INTO user_vote (id, user_id, restaurant_id, vote_date_time)
VALUES (1, 1, 1, '2020-04-04 10:00:00'),
       (2, 1, 2, '2020-04-04 11:00:00'),
       (3, 3, 2, '2020-04-04 10:00:00'),
       (4, 1, 2, '2020-04-04 12:00:00'),
       (5, 1, 1, '2020-04-05 10:00:00'),
       (6, 3, 2, '2020-04-05 10:00:00');