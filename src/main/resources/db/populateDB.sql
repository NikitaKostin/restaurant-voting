DELETE
FROM user_vote;
DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM dish;
DELETE
FROM menu;
DELETE
FROM restaurant;


INSERT INTO users (id, name, email, password)
VALUES (1, 'User', 'user@yandex.ru', 'password'),
       (2, 'Admin', 'admin@gmail.com', 'admin'),
       (3, 'Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurant (id, name)
VALUES (1, 'Чебуречная'),
       (2, 'Пельменная');

INSERT INTO menu (id, name, restaurant_id, create_date_time)
VALUES (1, 'Меню номер 1 чебуречной', 1, '2020-04-04 10:00:00'),
       (2, 'Меню номер 2 чебуречной', 1, '2020-04-05 10:00:00'),
       (3, 'Меню номер 1 пельменной', 2, '2020-04-04 10:00:00'),
       (4, 'Меню номер 2 пельменной', 2, '2020-04-05 10:00:00');

INSERT INTO dish (id, name, price, menu_id)
VALUES (1, 'Чебурек с мясом', 130, 1),
       (2, 'Чебурек с мясом', 130, 2),
       (3, 'Пельмени', 110, 3),
       (4, 'Чай', 30, 3),
       (5, 'Варенники с картошкой', 110, 4),
       (6, 'Кофе', 110, 4);

INSERT INTO user_vote (id, user_id, restaurant_id, vote_date_time)
VALUES (1, 1, 2, '2020-04-04 10:10:00'),
       (2, 1, 1, '2020-04-04 13:10:00'),
       (3, 3, 2, '2020-04-04 13:10:00'),
       (4, 1, 2, '2020-04-05 10:10:00'),
       (5, 1, 1, '2020-04-05 13:10:00'),
       (6, 3, 2, '2020-04-05 13:10:00');