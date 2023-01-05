CREATE DATABASE IF NOT EXISTS `restnewsdb`;

USE `restnewsdb`;

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `content`     varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`title`)
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `email`     varchar(255) NOT NULL,
    `text_body` varchar(255) NOT NULL,
    `user_name` varchar(255) NOT NULL,
    `post_id`   bigint       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `post_id` (`post_id`),
    CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
);

INSERT INTO `post`
VALUES (1, 'My first content', 'My first description', 'My first title'),
       (2, 'My second content', 'My second description', 'My second title'),
       (3, 'My third content', 'My third description', 'My third title'),
       (4, 'content aaa', 'description 1', 'title lll'),
       (5, 'content bbb', 'description 2', 'title kkk'),
       (6, 'content ccc', 'description 3', 'title jjj'),
       (7, 'content ddd', 'description 4', 'title iii'),
       (8, 'content eee', 'description 5', 'title hhh'),
       (9, 'content fff', 'description a', 'title ggg'),
       (10, 'content ggg', 'description b', 'title fff'),
       (11, 'content hhh', 'description c', 'title eee'),
       (12, 'content iii', 'description d', 'title ddd'),
       (13, 'content jjj', 'description e', 'title ccc'),
       (14, 'content kkk', 'description f', 'title bbb'),
       (15, 'content lll', 'description g', 'title aaa');

INSERT INTO `comment`
VALUES (1, 'aaa@mail.com', 'My first text', 'AAA name', 1),
       (2, 'bbb@mail.com', 'My second text', 'BBB name', 1),
       (3, 'ccc@mail.com', 'My third text', 'CCC name', 1);



CREATE TABLE `user`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `email`     varchar(255) NOT NULL,
    `full_name` varchar(255) NOT NULL,
    `password`  varchar(255) NOT NULL,
    `username`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`email`),
    UNIQUE KEY (`username`)
);


CREATE TABLE `role`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY (`role_id`),
    CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);


INSERT INTO `restnewsdb`.`user`
VALUES ('1', 'yuriy@mail.ru', 'yuriy', '$2a$10$SR8eHOrMIQqY.Ko9XTOpROvNoxkQrJndF4wuh/W6xkpmlZ5y.ywnO', 'yuriy'),
       ('2', 'admin@mail.ru', 'admin', '$2a$10$nxXIjXm19N2KkYXfjHHm6e8qG2xpL1qqFEiiS42RYprx8Y3GdnB5e', 'admin');


INSERT INTO `restnewsdb`.`role`
VALUES ('1', 'ROLE_USER'),
       ('2', 'ROLE_ADMIN');


INSERT INTO `restnewsdb`.`users_roles`
VALUES ('1', '1'),
       ('2', '2');


