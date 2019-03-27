DROP TABLE IF EXISTS category;
CREATE TABLE category (
  category_id   INT PRIMARY KEY,
  category_name VARCHAR (50) NOT NULL,
  description   VARCHAR (100)
);
DROP TABLE IF EXISTS product;
CREATE TABLE product (
  product_id    INT PRIMARY KEY auto_increment,
  category_id   INT NOT NULL,
  product_name  VARCHAR (50) NOT NULL,
  price         DECIMAL
);
INSERT INTO category (category_id, category_name, description) VALUES
  (1, 'Beverages', 'test'),
  (2, 'Condiments', 'test'),
  (3, 'Oil', 'test');
  DELETE FROM product;
INSERT INTO product (product_id, category_id, product_name, price) VALUES
  (1, 1, 'Northwind Traders Chai', 18.0000),
  (2, 2, 'Northwind Traders Syrup', 7.5000),
  (3, 2, 'Northwind Traders Cajun Seasoning', 16.5000),
  (4, 3, 'Northwind Traders Olive Oil', 16.5000),
  (5, 3, 'Northwind Traders Olive Oil2', 16.5000);