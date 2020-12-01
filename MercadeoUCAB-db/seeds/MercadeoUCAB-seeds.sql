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