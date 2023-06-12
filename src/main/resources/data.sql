INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User 2', 'user2@yandex.ru', '{noop}password2');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

INSERT INTO restaurant (id, name)
VALUES (1, 'Burger cafe'),
       (2, 'Pizza cafe');

INSERT INTO dish (id, name, price)
VALUES (1, 'Burger', 350),
       (2, 'Cheese Burger', 400),
       (3, 'Cola', 80),
       (4, 'Tea', 70),
       (5, 'Margarita', 250),
       (6, 'Chicago pizza', 350),
       (7, 'Coffee', 80),
       (8, 'Dish of the day', 300),
       (9, 'Drink of the day', 50),
       (10, 'New ice coffee', 50);

INSERT INTO menu (id, dish_id, restaurant_id, create_date)
VALUES (1, 1, 1, '2023-04-04'),
       (2, 2, 1, '2023-04-05'),
       (3, 3, 1, '2023-04-04'),
       (4, 4, 1, '2023-04-05'),
       (5, 5, 2, '2023-04-04'),
       (6, 6, 2, '2023-04-05'),
       (7, 7, 2, '2023-04-05'),
       (8, 8, 1, now()),
       (9, 9, 1, now());

INSERT INTO user_vote (id, user_id, restaurant_id, vote_date)
VALUES (1, 1, 1, '2023-04-04'),
       (2, 3, 2, '2023-04-04'),
       (3, 1, 1, '2023-04-05'),
       (4, 3, 2, '2023-04-05');
