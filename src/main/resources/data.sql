insert into cliente(nombre,telefono) values ('Sergio',684232323);
insert into cliente(nombre,telefono) values ('Jorge',684232324);
insert into cliente(nombre,telefono) values ('Luis',684232325);
insert into cliente(nombre,telefono) values ('Pablo',684232326);


insert into pedido(hora_realizado,hora_reserva,FK_CLIENTE) values ('oven','forge',2);
insert into pedido(hora_realizado,hora_reserva,FK_CLIENTE) values ('Hola','guay',3);
insert into pedido(hora_realizado,hora_reserva,FK_CLIENTE) values ('Mieres','La Felguera',4);
insert into pedido(hora_realizado,hora_reserva,FK_CLIENTE) values ('Marcos','Xuanxe',1);


insert into plato(nombre,descripcion,foto) values 
('Cocido mierense','Muy rico','a');
insert into plato(nombre,descripcion,foto) values 
('Cocido Felguerino','Muy rico','a');
insert into plato(nombre,descripcion,foto) values 
('Cocido Karatraba','Muy rico','a');
insert into plato(nombre,descripcion,foto) values 
('Cocido Madrileño','Muy rico','a');

insert into contiene(pedido_id,plato_id,cantidad) values (1,1,3);
insert into contiene(pedido_id,plato_id,cantidad) values (1,2,1);
insert into contiene(pedido_id,plato_id,cantidad) values (1,3,2);
insert into contiene(pedido_id,plato_id,cantidad) values (3,4,1);
insert into contiene(pedido_id,plato_id,cantidad) values (2,2,2);
insert into contiene(pedido_id,plato_id,cantidad) values (4,1,3);
insert into contiene(pedido_id,plato_id,cantidad) values (4,2,1);