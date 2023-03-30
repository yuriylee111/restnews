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

INSERT INTO `post` (`content`, `description`,`title`)
VALUES ('My first content', 'My first description', 'My first title'),
       ('My second content', 'My second description', 'My second title'),
       ('My third content', 'My third description', 'My third title'),
       ('content aaa', 'description 1', 'title lll'),
       ('content bbb', 'description 2', 'title kkk'),
       ('content ccc', 'description 3', 'title jjj'),
       ('content ddd', 'description 4', 'title iii'),
       ('content eee', 'description 5', 'title hhh'),
       ('content fff', 'description a', 'title ggg'),
       ('content ggg', 'description b', 'title fff'),
       ('content hhh', 'description c', 'title eee'),
       ('content iii', 'description d', 'title ddd'),
       ('content jjj', 'description e', 'title ccc'),
       ('content kkk', 'description f', 'title bbb'),
       ('content lll', 'description g', 'title aaa');

INSERT INTO `comment` (`email`,`text_body`,`user_name`,`post_id`)
VALUES ('aaa@mail.com', 'My first text', 'AAA name', 1),
       ('bbb@mail.com', 'My second text', 'BBB name', 1),
       ('ccc@mail.com', 'My third text', 'CCC name', 1);



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


INSERT INTO `restnewsdb`.`user` (`email`,`full_name`,`password`,`username`)
VALUES ('yuriy@mail.ru', 'yuriy', '$2a$10$SR8eHOrMIQqY.Ko9XTOpROvNoxkQrJndF4wuh/W6xkpmlZ5y.ywnO', 'yuriy'),
       ('admin@mail.ru', 'admin', '$2a$10$nxXIjXm19N2KkYXfjHHm6e8qG2xpL1qqFEiiS42RYprx8Y3GdnB5e', 'admin');


INSERT INTO `restnewsdb`.`role` (`name`)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');


INSERT INTO `restnewsdb`.`users_roles` (`user_id`,`role_id`)
VALUES ('1', '1'),
       ('2', '2');


