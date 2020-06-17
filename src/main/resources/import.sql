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



                                  ///////////////////////////BORRAR A PARTIR DE AQUI///////////////////////////
                                    
                                    

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

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('Velasco','Sevilla','sara@gmail.com',1,'sara.jpg','Sara','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',657979871,'Sara');
INSERT INTO `authorities` (user_id, authority) VALUES (5,'ROLE_USER');

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Aparador de los años 30, en casi perfecto estado.','aparador.jpg','aparador2.jpg','Sevilla','Aparador de los años 30',225,3,5,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Cabecero gris, muy muy viejo','cabecero.jpg','cabecero2.jpg','Sevilla','Cabecero',99,3,5,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Una comoda algo vieja pero muy espaciosa, con 4 cajones grandes','comoda.jpg','comoda2.jpg','Sevilla','Comoda',75,3,5,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Una comoda de cerezo muy bien cuidada con bastantes cajones muy espaciosos','comodacerezo.jpg','comodacerezo2.jpg','Sevilla','Comoda de cerezo',89,3,5,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Una comoda con un dibujo muy bonito, rustica, Con una algo mas pequeña pero muy bonita','dormitorio.jpg','dormitorio2.jpg','Sevilla','Conjunto de dormitorio',125,3,5,false);

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('De las peñas','Humanes','david@gmail.com',1,'david.jpg','David','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',659979854,'David');
INSERT INTO `authorities` (user_id, authority) VALUES (6,'ROLE_USER');

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Esta en perfecto estado me costo 115€ y la vendo por 60€','maquina.webp','maquina2.webp','Humanes','Maquina de ejercicio en perfecto estado',60,8,6,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Maquina de remo solo usada una vez y en perfecto estado','remo.webp','remo2.webp','Humanes','Maquina de remo',170,8,6,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Maquina multiuso, la vendo porque no usarla','gimnasia.webp','gimnasia.webp','Humanes','Maquina multiuso',60,8,6,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Maquina de musculacion, que tiene varias estacion de 2m de altura, 1,5m de ancho y 1,75m de largo','musculacion.webp','musculacion.webp','Humanes','Maquina de musculacion multiestacion',160,8,6,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Maquina de caminar sin moverse, con varios tipos de resistencia','pedal.webp','pedal2.webp','Humanes','Maquina de caminar',35,8,6,false);

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('Herrera','Leganes','laura@gmail.com',1,'laura.jpg','Laura','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',655747216,'Laura');
INSERT INTO `authorities` (user_id, authority) VALUES (7,'ROLE_USER');

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Unos botines con poco uso de la talla 39','botin.webp','botin2.webp','Leganes','Botin de piel',10,12,7,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Vendo camiseta seminueva, talla M','camiseta.webp','camiseta.webp','Leganes','Camiseta del gatito de piolin',4,12,7,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Leggins nuevos del Zara de talla S','leggin.webp','leggin2.webp','Leganes','Leggins del zara',3,12,7,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Gabardina Blanca, casi sin uso de talla M/38/10','gabardina.webp','gabardina2.webp','Leganes','Gabardina blanca',10,12,7,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Lote de ropa de 3 piezas, tallas M-L','lote.webp','lote2.webp','Leganes','Lote de ropa',20,12,7,false);

INSERT INTO `users` (apellido, direccion, email, enabled, foto_perfil, nombre, password, telefono, username) VALUES ('Herrera','Leganes','laura@gmail.com',1,'javier.jpg','Javier','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',787543598,'Javier');
INSERT INTO `authorities` (user_id, authority) VALUES (8,'ROLE_USER');

INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Sillon de director sin abrir','sillon.webp','sillon2.webp','Leganes','Sillon de director',18,11,8,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Libro de verónica Roth de la saga que se llevó al cine con el mismo título','divergente.webp','divergente2.webp','Leganes','Libro de divergente',5,9,8,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Coleccion de peliculas para jovenes','pelis.webp','pelis.webp','Leganes','Colección de peliculas',12,9,8,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Spoilers juego para jugar entre 2 a 10 jugadores sobtre peliculas','spoilers.webp','spoilers.webp','Leganes','Juego de mesa',5,9,8,false);
INSERT INTO producto (descripcion,foto_principal,fotos,localizacion,nombre,precio,categoria_producto_categoria_id,usuario_id,vendido) VALUES ('Blu Ray Variados, 10 Euros cada unidad bluray cine','lote3.webp','lote4.webp','Leganes','Blu Ray variados',10,9,8,false);
INSERT INTO `uwantit`.`puntuacion` (`id_puntuacion`, `id_producto`, `puntos`, `puntuado_id`, `puntuador_id`) VALUES ('1', '1', '5', '2', '2');
INSERT INTO `uwantit`.`puntuacion` (`id_puntuacion`, `id_producto`, `puntos`, `puntuado_id`, `puntuador_id`) VALUES ('2', '2', '5', '2', '2');
INSERT INTO `uwantit`.`puntuacion` (`id_puntuacion`, `id_producto`, `puntos`, `puntuado_id`, `puntuador_id`) VALUES ('3', '3', '6', '2', '2');


INSERT INTO `uwantit`.`comunicacion` (`id_mensaje`, `mensaje`, `envia_id`, `recibe_id`) VALUES ('1', 'Hola  buenas estaria interesado', '3', '2');
INSERT INTO `uwantit`.`comunicacion` (`id_mensaje`, `mensaje`, `envia_id`, `recibe_id`) VALUES ('2', 'Hola  buenas está en buenas condiciones?', '4', '2');
INSERT INTO `uwantit`.`comunicacion` (`id_mensaje`, `mensaje`, `envia_id`, `recibe_id`) VALUES ('3', 'Hola, aceptas 20€ por el reloj?', '5', '2');


