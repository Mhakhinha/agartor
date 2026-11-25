CREATE DATABASE springapp CHARACTER SET utf8mb4;

CREATE USER 'springappuser'@'%' IDENTIFIED BY 'pspringappuser';
CREATE USER 'springappuser'@'localhost' IDENTIFIED BY 'pspringappuser';
GRANT ALL ON springapp.* TO 'springappuser'@'%';
GRANT ALL ON springapp.* TO 'springappuser'@'localhost';

USE springapp;

CREATE TABLE products (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255),
  price DECIMAL(15,2)
);
CREATE INDEX products_description ON products(description);