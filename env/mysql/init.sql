CREATE database `jdbc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use jdbc;
CREATE TABLE `user`
(
    `id`    int PRIMARY KEY AUTO_INCREMENT,
    `uname` varchar(256),
    `upwd`  varchar(256),
    `major` varchar(256),
    `admin` BOOLEAN
);
CREATE USER 'user'@'%' IDENTIFIED BY 'b134ce403d14f75df399f0fc54ff2155';
GRANT ALL PRIVILEGES ON jdbc.* TO 'user'@'%';
FLUSH PRIVILEGES;