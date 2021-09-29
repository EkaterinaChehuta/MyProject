INSERT INTO indicator (id, name, viewName)
VALUES (1, 'грамм', 'гр'), (2, 'миллилитр', 'мл'), (3, 'штука', 'шт');

INSERT INTO product(id, name, indicator_id)
VALUES(1, 'молоко', 2), (2, 'хлеб', 3), (3, 'сахар', 1), (4, 'рис', 1), (5, 'перец', 3);