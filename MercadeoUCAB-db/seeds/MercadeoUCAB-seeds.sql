INSERT INTO categoria (id, nombre) VALUES 
(1, 'Ropa'),
(2, 'Videojuegos'),
(3, 'Tecnología'),
(4, 'TV'),
(5, 'Transporte'),
(6, 'Deportes');

INSERT INTO subcategoria (id, nombre) VALUES
(1, 'Streetwear'), 
(2, 'Shooter'), 
(3, 'Programacion'),
(4, 'Anime'), 
(5, 'Hardware'), 
(6, 'Automoviles');

INSERT INTO categoria_subcategoria (fk_categoria, fk_subcategoria) VALUES
(1,1), (2,2), (3,3), (4,4), (3,5), (5,6);

INSERT INTO marca (id, nombre) VALUES
(1, 'Supreme'), (2, 'Sony'), (3, 'Vans');

INSERT INTO subcategoria_marca (fk_subcategoria, fk_marca) VALUES
(1, 1), (5, 2), (1, 3);

INSERT INTO tipo (id, nombre) VALUES
(1, 'Zapatos'), (2, 'Headsets'), (3, 'T-shirt');

INSERT INTO marca_tipo (fk_marca, fk_tipo) VALUES
(1, 1), (2, 2), (3, 3);

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (1, 'Old Skool', null);

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (2, 'Authentic', null);

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (3, 'Z1R Headphones', null);

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (4, 'MDR-Z7M2', null);

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (5, 'Box Logo', 'Tee White');

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (6, 'Futura Logo', 'Tee Black');

INSERT INTO empresag.presentacion (id, nombre, descripcion)
VALUES (7, 'Good Vibes Long Sleeve', null);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (1, 1);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (1, 2);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (2, 3);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (2, 4);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (3, 5);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (3, 6);

INSERT INTO empresag.tipo_presentacion (fk_tipo, fk_presentacion)
VALUES (3, 7);
