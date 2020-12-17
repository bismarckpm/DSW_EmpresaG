INSERT INTO empresag.rol (id, nombre) VALUES (1, 'Administrador');
INSERT INTO empresag.rol (id, nombre) VALUES (2, 'Analista');
INSERT INTO empresag.rol (id, nombre) VALUES (3, 'Cliente');
INSERT INTO empresag.rol (id, nombre) VALUES (4, 'Encuestado');

INSERT INTO empresag.genero (id, nombre) VALUES (1, 'Masculino');
INSERT INTO empresag.genero (id, nombre) VALUES (2, 'Femenino');
INSERT INTO empresag.genero (id, nombre) VALUES (3, 'Otro');

INSERT INTO empresag.dispositivo (id, nombre) VALUES (1, 'Computadora de Escritorio (PC)');
INSERT INTO empresag.dispositivo (id, nombre) VALUES (2, 'Laptop');
INSERT INTO empresag.dispositivo (id, nombre) VALUES (3, 'Tablet');
INSERT INTO empresag.dispositivo (id, nombre) VALUES (4, 'Teléfono');

INSERT INTO empresag.edo_civil (id, nombre) VALUES (1, 'Soltero');
INSERT INTO empresag.edo_civil (id, nombre) VALUES (2, 'Casado');
INSERT INTO empresag.edo_civil (id, nombre) VALUES (3, 'Divorciado');
INSERT INTO empresag.edo_civil (id, nombre) VALUES (4, 'Unión libre o Concubinato');
INSERT INTO empresag.edo_civil (id, nombre) VALUES (5, 'Separado');
INSERT INTO empresag.edo_civil (id, nombre) VALUES (6, 'Viudo');

INSERT INTO empresag.ocupacion VALUES (1,'Maestro'),(2,'Albanil'),(3,'Carpintero'),(4,'Doctor'),(5,'Abogado');
INSERT INTO empresag.disponibilidad VALUES (1,'00:00:00'),(2,'01:00:00'),(3,'02:00:00'),(4,'03:00:00'),(5,'04:00:00'),(6,'05:00:00'),(7,'06:00:00'),(8,'07:00:00'),(9,'08:00:00'),(10,'09:00:00'),(11,'10:00:00'),(12,'11:00:00'),(13,'12:00:00'),(14,'13:00:00'),(15,'14:00:00'),(16,'15:00:00'),(17,'16:00:00'),(18,'17:00:00'),(19,'18:00:00'),(20,'19:00:00'),(21,'20:00:00'),(22,'21:00:00'),(23,'22:00:00'),(24,'23:00:00');

INSERT INTO empresag.nivel_academico (id, nombre) VALUES (1, 'Ninguno');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (2, 'Primaria');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (3, 'Secundaria');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (4, 'Técnico Superior');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (5, 'Universitario');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (6, 'Maestría');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (7, 'Doctorado');

INSERT INTO empresag.persona (id, documento_identidad, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, fk_genero, fk_edo_civil, fk_persona, fk_lugar, numero_personas_encasa, fk_disponibilidad_inicial, fk_disponibilidad_final) VALUES (1, 'DSSFDE3535', 'Peter', null, 'Nopsycho', null, '1989-01-05', 1, 1, null, 163, 1, 1, 5);
INSERT INTO empresag.persona (id, documento_identidad, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, fk_genero, fk_edo_civil, fk_persona, fk_lugar, numero_personas_encasa, fk_disponibilidad_inicial, fk_disponibilidad_final) VALUES (2, 'DSEU55353A', 'Rick', null, 'Derringer', null, '1982-04-02', 1, 5, null, 163, 1, 5, 18);
INSERT INTO empresag.persona (id, documento_identidad, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, fk_genero, fk_edo_civil, fk_persona, fk_lugar, numero_personas_encasa, fk_disponibilidad_inicial, fk_disponibilidad_final) VALUES (3, 'DVFD5R65G4', 'Nick', null, 'Kromaneyer', null, '1981-12-10', 1, 1, null, 163, 2, 1, 7);
INSERT INTO empresag.persona (id, documento_identidad, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, fk_genero, fk_edo_civil, fk_persona, fk_lugar, numero_personas_encasa, fk_disponibilidad_inicial, fk_disponibilidad_final) VALUES (4, null, 'Lukas', null, 'Kromaneyer', null, '2016-12-09', 1, null, 3, null, 0, null, null);
INSERT INTO empresag.persona (id, documento_identidad, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, fk_genero, fk_edo_civil, fk_persona, fk_lugar, numero_personas_encasa, fk_disponibilidad_inicial, fk_disponibilidad_final) VALUES (5, 'GLYQFVUZA6', 'Angel', null, 'Castillo', null, '1999-11-10', 1, 1, null, 163, 2, 1, 11);

INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (1, 1, 1);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (2, 2, 4);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (3, 3, 2);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (4, 3, 3);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (5, 5, 2);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (6, 5, 4);

INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (1, 1, 3);
INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (2, 2, 4);
INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (3, 3, 3);
INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (4, 5, 3);

INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (1, 1, 5);
INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (2, 2, 4);
INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (3, 3, 1);
INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (4, 5, 2);

INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (1, '3243243242', 1);
INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (2, '516516465', 2);
INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (3, '005804241174935', 3);
INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (4, '005804124459535', 5);


INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (1, 'hyperschnell11@outlook.sk', '123456789', 1, null, 1);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (2, 'eternalwaltz@gmail.ca', '123456789a!', 1, null, 3);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (3, 'iamnopsycho@protonmail.com', '123456789a!', 1, null, 2);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (4, 'skthereaper@gmail.ca', '123456789a!', 1, 1, 4);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (5, 'bumpsindanight@gmail.ca', '123456789a!', 1, 2, 4);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (6, 'itsmeyerz@gmail.com', '123456789a!', 1, 3, 4);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (7, 'angel99castillo@gmail.com', '123456789a!', 0, 5, 4);


INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (1, 'Ropa', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (2, 'Entretenimiento', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (3, 'Tecnología', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (4, 'Estudio', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (5, 'Hogar', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (6, 'Perfumes', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (7, 'Oficina', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (8, 'Música', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (9, 'Belleza', '');
INSERT INTO empresag.categoria (id, nombre, descripcion) VALUES (10, 'Transporte', '');

INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (1, 'Sportswear', 'Ropa deportiva');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (2, 'Videojuegos', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (3, 'Computadoras', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (4, 'Útiles escolares', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (5, 'Artículos de limpieza', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (6, 'Instrumentos', 'Instrumentos musicales');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (7, 'Cosméticos', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (8, 'Automóviles', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (9, 'Motocicletas', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (10, 'TV', '');
INSERT INTO empresag.subcategoria (id, nombre, descripcion) VALUES (11, 'Música electrónica', 'Artículos para productores');

INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (1, 1, 1);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (2, 2, 2);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (3, 3, 3);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (4, 4, 4);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (5, 5, 5);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (7, 9, 7);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (8, 10, 8);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (9, 10, 9);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (10, 2, 10);
INSERT INTO empresag.categoria_subcategoria (id, fk_categoria, fk_subcategoria) VALUES (13, 8, 11);

INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (1, 'Nike', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (2, 'Adidas', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (3, 'Puma', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (4, 'Under Armour', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (5, 'Nintendo', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (6, 'Sony', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (7, 'Asus', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (8, 'HP', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (9, 'Lenovo', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (10, 'Norma', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (11, 'Clorox', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (12, 'L''Oreal', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (13, 'Toyota', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (14, 'Honda', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (15, 'Kawasaki', '');
INSERT INTO empresag.marca (id, nombre, descripcion) VALUES (16, 'Yamaha', '');

INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (1, 1, 1);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (2, 1, 2);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (3, 1, 3);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (4, 1, 4);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (5, 2, 5);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (6, 2, 6);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (7, 3, 7);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (8, 3, 8);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (9, 3, 9);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (10, 4, 10);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (11, 5, 11);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (12, 7, 12);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (13, 8, 13);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (14, 8, 14);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (15, 9, 15);
INSERT INTO empresag.subcategoria_marca (id, fk_subcategoria, fk_marca) VALUES (16, 9, 16);

INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (1, 'Rain Jacket', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (2, 'Cushion Socks', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (3, 'Running Shoes', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (4, 'Pullover', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (5, 'Training Pants', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (6, 'Court Sneaker', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (7, 'Slide Sandal', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (8, 'Carina Sneaker', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (9, 'Basket Classic Lfs', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (10, 'Tech Shirt', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (11, 'Storm Beanie', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (12, 'Consola', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (13, 'Headsets', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (14, 'Laptop', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (15, 'Notebook', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (16, 'Cloro', '');
INSERT INTO empresag.tipo (id, nombre, descripcion) VALUES (17, 'Tinte para cabello', '');

INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (1, 1, 1);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (2, 1, 2);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (3, 1, 3);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (4, 1, 4);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (5, 2, 5);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (6, 2, 6);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (7, 2, 7);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (8, 3, 8);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (9, 3, 9);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (10, 4, 10);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (11, 4, 11);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (12, 5, 12);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (13, 6, 13);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (14, 7, 14);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (15, 10, 15);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (16, 11, 16);
INSERT INTO empresag.marca_tipo (id, fk_marca, fk_tipo) VALUES (17, 12, 17);

INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (1, 'Royal Blue', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (2, 'Black/White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (3, 'Black', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (4, 'Low cut', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (5, 'Island Green', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (6, 'Ember Glow', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (7, 'Tropical Pink', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (8, 'Bleached', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (9, 'Dark Beetroot/White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (10, 'Glory Red', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (11, 'Ink/White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (12, 'Tech Indigo', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (13, 'White/Trace Blue/Red', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (14, 'Black/White/White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (15, 'Scarlet/White/Scarlet', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (16, 'Puma White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (17, 'Black/White', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (18, 'Short Sleeve', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (19, 'Halo Gray', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (20, 'Nintendo Wii', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (21, 'Noise Cancelling WHCH710N', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (22, 'Asus X512DA', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (23, 'Espiral', '');
INSERT INTO empresag.presentacion (id, nombre, descripcion) VALUES (24, 'Tinte Rojo', '');

INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (1, 1, 1);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (2, 1, 2);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (4, 2, 3);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (5, 2, 4);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (6, 3, 5);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (7, 3, 6);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (8, 3, 7);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (9, 4, 8);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (10, 4, 9);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (11, 5, 10);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (12, 5, 11);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (13, 6, 12);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (14, 6, 13);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (15, 7, 14);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (16, 7, 15);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (17, 8, 16);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (18, 9, 17);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (19, 10, 18);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (20, 11, 19);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (21, 12, 20);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (22, 13, 21);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (23, 14, 22);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (24, 15, 23);
INSERT INTO empresag.tipo_presentacion (id, fk_tipo, fk_presentacion) VALUES (25, 17, 24);