INSERT INTO empresag.rol (id, nombre) VALUES (1, 'Administrador');
INSERT INTO empresag.rol (id, nombre) VALUES (2, 'Analista');
INSERT INTO empresag.rol (id, nombre) VALUES (3, 'Cliente');
INSERT INTO empresag.rol (id, nombre) VALUES (4, 'Encuestado');

INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (1, 'hyperschnell11@outlook.sk', '123456789', 1, null, 1);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (2, 'eternalwaltz@gmail.ca', '123456789a!', 1, null, 3);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (3, 'iamnopsycho@protonmail.com', '123456789a!', 1, null, 2);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (4, 'skthereaper@gmail.ca', '123456789a!', 1, 1, 4);
INSERT INTO empresag.usuario (id, email, password, estado, fk_persona, fk_rol) VALUES (5, 'bumpsindanight@gmail.ca', '123456789a!', 1, 2, 4);

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

INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (1, 1, 1);
INSERT INTO empresag.persona_dispositivo (id, fk_persona, fk_dispositivo) VALUES (2, 2, 4);

INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (1, 1, 3);
INSERT INTO empresag.persona_nvlacademico (id, fk_persona, fk_nivel_academico) VALUES (2, 2, 4);

INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (1, 1, 5);
INSERT INTO empresag.persona_ocupacion (id, fk_persona, fk_ocupacion) VALUES (2, 2, 4);

INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (1, '3243243242', 1);
INSERT INTO empresag.telefono (id, numero, fk_persona) VALUES (2, '516516465', 2);