DROP DATABASE IF EXISTS product;
CREATE DATABASE product;
USE product;





CREATE TABLE product_list (
product_id INT NOT NULL,
product_name VARCHAR(50),
sku INT,
price INT,
PRIMARY KEY (product_id)
);

CREATE TABLE order_list (
order_id INT AUTO_INCREMENT,
order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (order_id)
);

CREATE TABLE order_details (
order_detail_id INT AUTO_INCREMENT,
orderid INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (order_detail_id),
FOREIGN KEY (orderid) REFERENCES order_list (order_id),
FOREIGN KEY (product_id) REFERENCES product_list (product_id)
);



INSERT INTO product_list (product_id, product_name, sku, price)
VALUES
(1, 'Televison', '557895', '200'),
(2, 'Phone', '788124', '800'),
(3, 'Microwave', '147965', '149'),
(4, 'Refridgerator', '651366','89'),
(5, 'Dishwasher','984651','187'),
(6, 'Coffee Maker', '871454', '99'),
(7, 'Air Frier', '651974', '139');

INSERT INTO order_list (order_id, order_date)
VALUES
(1, NOW());

INSERT INTO order_details (orderid, product_id, quantity)
VALUES
(1, 1, 2);
