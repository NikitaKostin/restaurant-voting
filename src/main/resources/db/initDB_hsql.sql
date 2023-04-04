DROP TABLE user_vote IF EXISTS;
DROP TABLE user_role IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restaurant IF EXISTS;

CREATE TABLE restaurant
(
    id GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX restaurant_unique_name_idx
    ON restaurant (name);

CREATE TABLE menu
(
    id GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    restaurant_id    INTEGER                 NOT NULL,
    create_date_time TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX menu_unique_name_restaurant_create_date_time_idx
    ON menu (name, restaurant_id, create_date_time);

CREATE TABLE dish
(
    id GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   INTEGER      NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX dish_unique_name_price_menu_idx
    ON dish (name, price, menu_id);

CREATE TABLE users
(
    id GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE user_role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_vote
(
    id GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    user_id          INTEGER                 NOT NULL,
    restaurant_id    INTEGER                 NOT NULL,
    vote_date_time TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_vote_unique_user_restaurant_vote_date_time_idx
    ON user_vote (user_id, restaurant_id, vote_date_time)