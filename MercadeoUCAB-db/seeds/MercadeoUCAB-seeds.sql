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

INSERT INTO empresag.tipo_pregunta (id, nombre, descripcion)
VALUES (1, 'Texto Abierto', 'Respuesta libre');

INSERT INTO empresag.tipo_pregunta (id, nombre, descripcion)
VALUES (2, 'Selección Simple', 'Sólo se puede seleccioanr una opción');

INSERT INTO empresag.tipo_pregunta (id, nombre, descripcion)
VALUES (3, 'Selección Múltiple', null);

INSERT INTO empresag.tipo_pregunta (id, nombre, descripcion)
VALUES (4, 'Verdadero y Falso', null);

INSERT INTO empresag.tipo_pregunta (id, nombre, descripcion)
VALUES (5, 'Rango', 'Rango de valores numéricos');

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (1, '¿Qué talla de zapatos suele usar usted?', 1, 1);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (2, '¿Cuál es tu categoría favorita de videojuegos?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (3, '¿Cuál es tu género favorito de anime?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (4, '¿Prefieres los headsets o los earbuds?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (5, '¿Prefieres Intel o NVIDIA?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (6, '¿Cuál es tu velocidad de internet? (Mb/s)', 1, 5);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (7, '¿Qué opinas del servicio de internet de tu país?', 1, 1);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (8, '¿Utilizas automóvil eléctrico o mecánico?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (9, 'Si sabe qué es el Net Neutrality, ¿Qué opinas de él?', 1, 1);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (10, 'Seleccione el rango de precio mínimo y máximo por el cuál usted pagaría por un carro ($)', 1, 5);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (11, 'Seleccione qué animes de los siguientes ha visto', 1, 3);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (12, '¿Está usted familiarizado con el lenguaje de programación Python?', 1, 4);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (13, '¿De qué precio fue la pieza más cara que ha comprado? ($)', 1, 1);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (14, '¿Prefiere usted FPS o TPS?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (15, '¿Qué procesador tiene?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (16, '¿Maneja automóvil automático o sincrónico?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (17, '¿Cuál es tu arma favorita?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (18, '¿De cuántas líneas de código ha sido el proyecto más largo que ha escrito?', 1, 1);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (19, '¿Tiene un grupo de amigos para jugar en línea?', 1, 4);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (20, '¿Qué tan seguido se le daña el carro?', 1, 2);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (21, '¿Alguna vez ha comprado en una tienda underground?', 1, 4);

INSERT INTO empresag.pregunta (id, pregunta, status, fk_tipo)
VALUES (22, '¿Se considera usted un buen jugador?', 1, 4);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (1, 1, 1);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (2, 2, null);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (3, 4, 4);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (4, 3, 5);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (5, 3, 5);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (6, 3, 5);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (7, 3, 5);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (8, 5, 6);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (9, 3, null);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (10, 5, 6);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (11, 4, 4);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (12, 3, 3);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (13, 5, 6);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (14, 2, 2);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (15, 3, 5);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (16, 5, 6);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (17, 2, 2);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (18, 3, 3);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (19, 2, 2);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (20, 5, 6);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (21, 1, 1);

INSERT INTO empresag.pregunta_cat_subcat (fk_pregunta, fk_categoria, fk_subcategoria)
VALUES (22, 2, 2);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (1, 'Shooter', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (2, 'Deportes', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (3, 'Estrategia', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (4, 'RPG', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (5, 'Shonen', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (6, 'Seinen', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (7, 'Shojo', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (8, 'Isekai', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (9, 'Ecchi', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (10, 'Headsets', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (11, 'Earbuds', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (12, 'Intel', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (13, 'NVIDIA', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (14, null, 1, 100);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (15, 'Mecánico', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (16, 'Eléctrico', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (17, null, 500, 100000);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (18, 'Naruto', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (19, 'Dragon Ball', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (20, 'Full Metal Alchemist', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (21, 'Death Note', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (22, 'Shingeki No Kyojin', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (23, 'Sword Art Online', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (24, 'Verdadero', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (25, 'Falso', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (26, 'FPS', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (27, 'TPS', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (28, 'Intel', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (29, 'AMD', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (30, 'Otro', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (31, 'Automático', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (32, 'Sincrónico', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (33, 'Sniper', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (34, 'AK-47', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (35, 'AR-15', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (36, 'Glock', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (37, 'Escopeta', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (38, 'Verdadero', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (39, 'Falso', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (40, 'Una vez al mes o más seguido', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (41, 'Cada dos-cinco meses', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (42, 'Cada seis-nueve meses', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (43, 'Una vez al año', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (44, 'Menos de una vez al año', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (45, 'Verdadero', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (46, 'Falso', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (47, 'Verdadero', null, null);

INSERT INTO empresag.opcion (id, valor, rango_inicial, rango_final)
VALUES (48, 'Falso', null, null);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (2, 1);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (2, 2);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (2, 3);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (2, 4);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (3, 5);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (3, 6);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (3, 7);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (3, 8);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (3, 9);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (4, 10);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (4, 11);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (5, 12);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (5, 13);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (6, 14);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (8, 15);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (8, 16);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (10, 17);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 18);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 19);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 20);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 21);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 22);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (11, 23);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (12, 24);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (12, 25);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (14, 26);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (14, 27);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (15, 28);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (15, 29);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (15, 30);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (16, 31);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (16, 32);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (17, 33);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (17, 34);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (17, 35);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (17, 36);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (17, 37);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (19, 38);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (19, 39);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (20, 40);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (20, 41);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (20, 42);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (20, 43);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (20, 44);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (21, 45);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (21, 46);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (22, 47);

INSERT INTO empresag.posible_respuesta (fk_pregunta, fk_opcion)
VALUES (22, 48);

INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (1, 'Estados Unidos', 1, null, null);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (2, 'Louisiana', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (3, 'California', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (4, 'Florida', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (5, 'Alabama', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (6, 'Arkansas', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (7, 'Ohio', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (8, 'Alaska', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (9, 'Texas', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (10, 'Washington', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (11, 'Oregon', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (12, 'Virginia', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (13, 'West Virginia', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (14, 'South Dakota', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (15, 'North Dakota', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (16, 'North Carolina', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (17, 'South Carolina', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (18, 'Kansas', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (19, 'Nebraska', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (20, 'Delaware', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (21, 'Vermont', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (22, 'Distrct of Columbia', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (23, 'Tennessee', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (24, 'Massachusetts', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (25, 'Hawaii', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (26, 'Minnesotta', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (27, 'Maryland', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (28, 'Michigan', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (29, 'Iowa', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (30, 'Idaho', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (31, 'Ohio', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (32, 'Pennsylvania', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (33, 'Montana', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (34, 'Rhode Island', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (35, 'Maine', 2, null, 1);
INSERT INTO empresag.lugar (id, nombre, tipo, fk_nivel_socioeconomico, fk_lugar) VALUES (36, 'Nevada', 2, null, 1);

INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (1, 'Alto');
INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (2, 'Medio-Alto');
INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (3, 'Medio');
INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (4, 'Medio-Bajo');
INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (5, 'Bajo');
INSERT INTO empresag.nivel_socioeconomico (id, nombre) VALUES (6, 'Extrema Pobreza');

INSERT INTO empresag.nivel_academico (id, nombre) VALUES (1, 'Ninguno');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (2, 'Primaria');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (3, 'Secundaria');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (4, 'Técnico Superior');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (5, 'Universitario');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (6, 'Maestría');
INSERT INTO empresag.nivel_academico (id, nombre) VALUES (7, 'Doctorado');

INSERT INTO empresag.genero (id, nombre) VALUES (1, 'Masculino');
INSERT INTO empresag.genero (id, nombre) VALUES (2, 'Femenino');
INSERT INTO empresag.genero (id, nombre) VALUES (3, 'Otro');

INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (1, '2020-12-04', null, 1, null);
INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (2, '2020-07-02', null, 1, null);
INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (3, '2020-07-03', null, 1, null);
INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (4, '2018-03-02', null, 1, null);
INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (5, '2017-03-03', null, 1, null);
INSERT INTO empresag.estudio (id, fecha_realizacion, fecha_culminacion, estado, fk_analisis) VALUES (6, '2020-06-05', null, 1, null);

INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (1, 1, 1, 15, 45, 3, null, 2, 1, null, 1, 1, 1);
INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (2, 2, 1, null, null, 3, null, 2, 1, null, 1, 2, 2);
INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (3, 3, 2, 14, 28, 3, null, 2, null, null, 2, 3, 3);
INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (4, 4, 2, 14, 28, 3, null, null, null, null, 3, 5, 6);
INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (5, 5, null, null, null, 3, null, 1, null, null, null, 4, 4);
INSERT INTO empresag.filtro (id, fk_estudio, tipo_filtro_lugar, edad_minima, edad_maxima, fk_nivel_socioeconomico, fk_edo_civil, fk_nivel_academico, fk_genero, fk_grupo_familiar, fk_lugar, fk_categoria, fk_subcategoria) VALUES (6, 6, 1, 1, 100, 3, 1, 2, 1, null, 1, 5, 6);