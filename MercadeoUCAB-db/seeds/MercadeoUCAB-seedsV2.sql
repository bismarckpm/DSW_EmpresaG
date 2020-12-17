INSERT INTO empresag.rol (id, nombre) VALUES (1, 'Administrador');
INSERT INTO empresag.rol (id, nombre) VALUES (2, 'Analista');
INSERT INTO empresag.rol (id, nombre) VALUES (3, 'Cliente');
INSERT INTO empresag.rol (id, nombre) VALUES (4, 'Encuestado');

INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (1, 'hyperschnell11@outlook.sk', '123456789', 1, null, 1);