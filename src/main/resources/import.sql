insert into categoria_producto(categoria_id, descripcion) values(1,'Vehiculos');
insert into categoria_producto(categoria_id, descripcion) values(1,'Vehiculos');
insert into categoria_producto(categoria_id, descripcion) values(2,'Herramientas');
insert into categoria_producto(categoria_id, descripcion) values(3,'Muebles');
insert into categoria_producto(categoria_id, descripcion) values(4,'Informatica');
insert into categoria_producto(categoria_id, descripcion) values(5,'Móviles y teléfonos');
insert into categoria_producto(categoria_id, descripcion) values(6,'Televisión');
insert into categoria_producto(categoria_id, descripcion) values(7,'Audio e Imagen');
insert into categoria_producto(categoria_id, descripcion) values(8,'Deportes');
insert into categoria_producto(categoria_id, descripcion) values(9,'Cine,Libros y Música');
insert into categoria_producto(categoria_id, descripcion) values(10,'Consolas y Videojuegos');
insert into categoria_producto(categoria_id, descripcion) values(11,'Jardín');
insert into categoria_producto(categoria_id, descripcion) values(12,'Moda y Accesorios');
insert into categoria_producto(categoria_id, descripcion) values(13,'Electrodomésticos');

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('admin','admin','admin',1,'admin.jpg','admin','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',658965874,'admin');
INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_ADMIN');

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('perez','fuenlabrada','clau@gmail.com',1,'clau.jpg','clau','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',658965874,'clau');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_USER');

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('de Carlos Garcia','Loranca','jose@gmail.com',1,'jose.jpg','Jose Luis','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',657965874,'Joseldcg');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_USER');

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('Blasco','Fuenlabrada','joaquin@gmail.com',1,'joaquin.jpg','Joaquín','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',657965874,'Joaquín');
INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_USER');

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('patata artistica','patata.jpg','logo.png','Madrid','patata a la venta',900,11,2,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('reloj bolsillo desfasao','relojbolsillo.jpg','logo.png','Fuenlabrada','vendo reloj bueno',1,12,2,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('pelota de futbol','pelotafutbol.jpg','logo.png','Getafe','Pelota vieja casi sin uso',10,8,2,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Playstation 4 casi sin uso, con 2 años', 'play.jpg','logo.png', 'Fuenlabrada','PlayStation 4 segunda mano',150,10,2,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Bicicleta Montaña con plato de marchas, poco tiempo de uso','bicicleta.jpg','logo.png','Madrid','Bicicleta Montaña',90,8,3,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Monopoly año 2000 con todas las piezas', 'monopoly.jpg','logo.png', 'Madrid','Monopoly Completo', 10,10,2,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Ordenador HP con disco ssd 1T', 'ordenador.jpg','logo.png', 'Fuenlabrada', 'Ordenador HP', 190,4,4,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Camara analogica en perfecto estado','olimpus.jpeg','logo.png','Madrid','Olympus Pen EE2',95,7,3,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Guitarra electrica Gibson modelo SG','sg.jpeg','logo.png','Fuenlabrada','Gibson SG',600,7,3,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Figura Funko Slash G&R','slash.jpeg','logo.png','Leganes','Figura Slash',10,9,3,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Espejo rectangular con marco gris', 'espejo.jpg','logo.png','Madrid', 'Espejo',20,3,3,false);

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Unas aletas de un buen material y sin usar.','aletas.jpeg','logo.png','Cubas','Aletas profesionales',20,8,4,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Vendo esta estanteria porque ya no puede mantener todos los libros que compro','estanteria.jpeg','logo.png','Moraleja de Enmedio','Estanteria Wollhand',60,3,4,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Microondas en muy buen estado, aunque esta muy usado','micro.jpeg','logo.png','Serranillos del Valle','Microondas Samsung',40,13,4,false);