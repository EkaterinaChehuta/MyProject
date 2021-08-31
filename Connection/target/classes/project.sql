CREATE TABLE indicator
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	viewName VARCHAR(255) NOT NULL
);

CREATE TABLE product
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	indicator_id INT NOT NULL,
    FOREIGN KEY(indicator_id) REFERENCES indicator(id)
 );

CREATE TABLE ingredients_name
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE ingredients
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    ingredients_name_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY(ingredients_name_id) REFERENCES ingredients_name(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);

CREATE TABLE recipe
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ingredients_name_id INT NOT NULL,
    preparation VARCHAR(8000) NOT NULL,
    FOREIGN KEY(ingredients_name_id) REFERENCES ingredients_name(id)
);

 CREATE TABLE shopping_list
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	product_id INT NOT NULL,
    quantity INT,
    is_purchased boolean,
    FOREIGN KEY(product_id) REFERENCES product(id)
 );