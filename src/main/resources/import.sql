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

INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('patata artistica','patata.jpg','Madrid','patata a la venta',900,11,2);
INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('reloj bolsillo desfasao','relojbolsillo.jpg','Fuenlabrada','vendo reloj bueno',1,12,2);
INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('pelota de futbol','pelotafutbol.jpg','Getafe','Pelota vieja casi sin uso',10,8,2);

INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('Camara analogica en perfecto estado','olimpus.jpg','Madrid','Olympus Pen EE2',95,7,3);
INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('Guitarra electrica Gibson modelo SG','sg.jpg','Fuenlabrada','Gibson SG',600,7,3);
INSERT INTO producto (descripcion,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id) VALUES ('Figura Funko Slash G&R','slash.jpg','Leganes','Figura Slash',10,9,3);
