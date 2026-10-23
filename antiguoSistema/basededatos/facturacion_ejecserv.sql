create schema facturacion;

create table facturacion.empresas(
    idempresa serial not null primary key,
    nit bigint,
    razonsocial varchar,
    codigosistema varchar,
    token varchar,
    fechahora timestamp,
    ambiente integer,
    modalidad integer,
    estado BOOLEAN
);

create table facturacion.sucursales (
    idsucursal serial not null primary key,
    idempresa integer,
    sucursal integer,
    nombresucursal varchar,
    Foreign Key (idempresa) REFERENCES facturacion.empresas (idempresa)
);

create table facturacion.puntosventas(
    idpuntoventa serial not null primary key,
    idsucursal integer,
    puntoventa integer,
    nombrepuntoventa varchar,
    Foreign Key (idsucursal) REFERENCES facturacion.sucursales (idsucursal)
);

create table facturacion.cuis(
    idcui serial not null primary key,
    idpuntoventa integer,
    cui varchar,
    fechavigencia TIMESTAMP,
    estado boolean,
    foreign Key (idpuntoventa) REFERENCES facturacion.puntosventas (idpuntoventa)
);

insert into facturacion.empresas(nit, razonsocial, codigosistema, token, fechahora, ambiente, modalidad, estado) values
(120987022, 'SFIDA INVERSIONES S.A.', '776DAEB0ABAD6B822DBD056', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTRklEQUlTQSIsImNvZGlnb1Npc3RlbWEiOiI3NzZEQUVCMEFCQUQ2QjgyMkRCRDA1NiIsIm5pdCI6Ikg0c0lBQUFBQUFBQUFETTBNckMwTURjd01nSUFOclRjYmdrQUFBQT0iLCJpZCI6MTQzODM2LCJleHAiOjE3MDEzMDI0MDAsImlhdCI6MTY5NjgxNDkxMiwibml0RGVsZWdhZG8iOjEyMDk4NzAyMiwic3Vic2lzdGVtYSI6IlNGRSJ9.S0uZYwKeAKtih-gFjdSM_tt2JvLa1hS312zdVZbOm9C4NZgIhpe4X066OsRYuy8I0SBsP7BZbTRLe05SVCn7xg', now(), 2, 1, true);

insert into facturacion.sucursales(idempresa, sucursal, nombresucursal) values
(1, 0, 'Sucursal Central');

insert into facturacion.puntosventas(idsucursal, puntoventa, nombrepuntoventa) values
(1, 0, 'Punto de venta 0'),
(1, 1, 'Punto de venta 1');

CREATE TABLE facturacion.unidadesmedidas(
    idunidadmedida serial not null primary key,
    codigosin integer,
    unidadmedida varchar,
    sigla varchar
);

insert into facturacion.unidadesmedidas(sigla, unidadmedida, codigosin) values
('CJ', 'CAJA',6),
('BL', 'BOLSA',4),
('PQ', 'PAQUETE',42),
('UNIDAD', 'UNIDAD (BIENES)',57),
('UN', 'UNIDAD (SERVICIOS)',58);

CREATE TABLE facturacion.grupos(
    idgrupo serial not null primary key,
    grupo varchar,
    sigla varchar
);

insert into facturacion.grupos(sigla, grupo) values
('GAL', 'GALLETAS'),
('FID', 'FIDEOS'),
('BAL', 'BALANCEADOS'),
('ALQ', 'ALQUILERES'),
('ACT', 'ACTIVOS');

CREATE TABLE facturacion.descuentos(
    iddescuento serial not null primary key,
    descuento varchar,
    fecha date,
    cantidad integer,
    porcentaje float
);

insert into facturacion.descuentos(fecha, cantidad, porcentaje) values
('2022-02-01', 100, 1.00),
('2022-02-01', 500, 2.00),
('2022-02-01', 1000, 6.00),
('2022-02-01', 100, 1.00);

CREATE TABLE facturacion.productos(
    idproducto serial not null primary key,
    codigosin integer,
    idgrupo integer references facturacion.grupos(idgrupo),
    idunidadmedida integer references facturacion.unidadesmedidas(idunidadmedida),
    iddescuento integer references facturacion.descuentos(iddescuento),
    producto varchar,
    cantidadcaja integer,
    preciounitario numeric(15,2),
    enlaceproducto varchar,
    habilitado boolean default true
);

insert into facturacion.productos(codigosin, producto, cantidadcaja, preciounitario, idunidadmedida, idgrupo, iddescuento) values
(23410, 'INTEGRAL 220 GRS',45, 6.50, 3, 1, 1),
(23410, 'INTEGRAL 1 KG',9, 29.00, 3, 1, 1),
(23410, 'AGUA 1 KG',7, 29.00, 3, 1, 1),
(23410, 'CRACKERS  1 KG',7, 29.00, 3, 1, 1),
(23410, 'CRACKERS 100 GRS',80, 3.50, 3, 1, 1),
(23410, 'CRACKERS 200 GRS',41, 6.50, 3, 1, 1),
(23410, 'CRACKERS QUINUA 200 GRS',41, 6.50, 3, 1, 1),
(23410, 'MINI CRACKERS 35 GRS',80, 0.60, 3, 1, 1),
(23410, 'SOCIALES 35 GRS',80, 0.60, 3, 1, 1),
(23410, 'TOSTITAS 100 GRS',76, 2.00, 3, 1, 1),
(23410, 'ABECEDARIO 880 GRS',8, 26.50, 3, 1, 1),
(23410, 'ANIMALITO 880 GRS',7, 26.50, 3, 1, 1),
(23410, 'ANIMALITO 35 GRS',80, 0.60, 3, 1, 1),
(23410, 'ABECEDARIO 35 GRS',80, 0.60, 3, 1, 1),
(23410, 'LECHE 170 GRS',56, 3.70, 3, 1, 1),
(23410, 'MARIA 1 KG',8, 27.50, 3, 1, 1),
(23410, 'MARIA 200 GRS',40, 5.70, 3, 1, 1),
(23410, 'LECHE 80 GRS',144, 1.70, 3, 1, 1),
(23410, 'CHOCOLIN 190 GRS',79, 6.20, 3, 1, 1),
(23410, 'COQUETA 190 GRS',64, 6.20, 3, 1, 1),
(23410, 'MELITAS 270 GRS',16, 4.50, 3, 1, 1),
(23410, 'MERENGUITAS 50 GRS',84, 3.00, 3, 1, 1),
(23410, 'MERENGUITAS 100 GRS',40, 6.00, 3, 1, 1),
(23410, 'TENTACION FRUTILLA 45 GRS',156, 0.70, 3, 1, 1),
(23410, 'TENTACION CHOCOLATE 45 GRS',156, 0.70, 3, 1, 1),
(23410, 'RESERVADAS 90 GRS',66, 5.00, 3, 1, 1),
(23410, 'WAFERLIN NARANJA 30 GRS',168, 0.70, 3, 1, 1),
(23410, 'WAFERLIN FRUTILLA 30 GRS',168, 0.70, 3, 1, 1),
(23410, 'TUBBY MINI WAFER 30 GRS',100, 0.70, 3, 1, 1),
(23410, 'PRIMAVERA 35 GRS',70, 0.70, 3, 1, 1),
(23410, 'SURTIDA NAVIDEÑA 700 GRS',6, 30.00, 3, 1, 4),
(23410, 'CHAMPAGNE 170 GRS',22, 8.50, 3, 1, 1),
(23410, 'FETUCCINI 400 GRS. #14',30, 11.50, 3, 2, 2),
(23410, 'SPAGHETTI 400 GRS',30, 11.50, 3, 2, 2),
(23410, 'LASAGÑA 200 GRS',36, 10.50, 3, 2, 2),
(23410, 'CABELLO DE ANGEL 200 GRS',28, 10.50, 3, 2, 2),
(23410, 'TALLARIN ESPECIAL # 14 400 GR',20, 8.00, 3, 2, 2),
(23410, 'SPAGHETTI ESPECIAL 400 GRS',20, 8.00, 3, 2, 2),
(23410, 'MARIPOSA 400 GRS',20, 3.50, 3, 2, 2),
(23410, 'MARIPOSA 1 KG',1, 8.50, 3, 2, 2),
(23410, 'MARIPOSA 5 KG',1, 40.50, 3, 2, 2),
(23410, 'API AMARILLO 1KG',10, 12.00, 3, 3, 3),
(23410, 'API MORADO 1 KG',10, 12.00, 3, 3, 3),
(23410, 'CRACKER H3 * 23 GRS',325, 0.45, 3, 1, 1),
(23410, 'SEMOLA GLUTINA 500 GRS',16, 8.00, 3, 2, 2),
(23410, 'CANITO 20 KG',1, 120.00, 3, 3, 3),
(23410, 'CANITO DE 40 KG',1, 220.00, 3, 3, 3),
(23410, 'LASAGÑA HOTELERA',1, 355.00, 3, 2, 2),
(23410, 'SPAGUETTI CORRIENTE 1 KG',1, 10.00, 3, 2, 2),
(23410, 'CRACKER INTEGRAL H3 * 23 GRS',500, 0.45, 3, 1, 1),
(23410, 'LECHE H3 * 18 GRS',405, 0.45, 3, 1, 1),
(23410, 'ROEDORES LABORATORIO',1, 350.00, 2, 3, 3),
(72112, 'ALQUILER DE UNA HABITACIÓN DE 2.25 MTS., EN EL FRONTIS DEL INMUEBLE UBICADO EN LA AV. 6 DE OCTUBRE N°5049, AROMA Y RODRIGUEZ',1, 2088.00, 4, 4, 3),
(72112, 'ALQUILER DE UN AMBIENTE DE 4 MTS. PARA CAJERO ATM UBICADO EN LA AV. 6 DE OCTUBRE N°5049, AROMA Y RODRIGUEZ',1, 2436.00, 4, 4, 3),
(99100, 'CAMION MERCEDES BENZ PLACA 116-DCG MODELO 1976 CHASIS N°37830212001393',1, 3480.00, 4, 4, 3),
(23410, 'FETUCCINI 400 GRS. #15',30, 11.50, 3, 2, 2),
(23410, 'FETUCCINI 400 GRS. #16',30, 11.50, 3, 2, 2),
(23410, 'TALLARIN ESPECIAL # 15 400 GR',20, 8.00, 3, 2, 2),
(23410, 'TALLARIN ESPECIAL # 16 400 GR',20, 8.00, 3, 2, 2);

CREATE TABLE facturacion.vendedores(
    idvendedores serial not null primary key,
    descuento varchar,
    fecha date,
    cantidad integer,
    porcentaje float
);

alter table facturacion.sucursales add municipio varchar default 'Oruro';
alter table facturacion.sucursales add telefono varchar default '2-5288006';
alter table facturacion.sucursales add direccion varchar default '6 de Agosto entre Junin y Ayacucho';
alter table facturacion.puntosventas add numerofactura integer default 1;
alter table facturacion.productos add idproductosin integer default 1;
alter table facturacion.productos drop codigosin;

create table facturacion.cufds(
    idcufd serial not null primary key,
    idpuntoventa integer REFERENCES facturacion.puntosventas (idpuntoventa),
    cufd varchar,
    cufdcontrol varchar,
    fechainicio TIMESTAMP,
    fechavigencia TIMESTAMP,
    estado boolean
);

create table facturacion.clientes(
    idcliente serial not null primary key,
    cliente varchar,
    tipodocumento integer,
    numerodocumento varchar,
    complemento varchar,
    correoelectronico varchar
);

create table facturacion.facturas(
    idfactura serial not null primary key,
    idusuario integer references seguridad.usuarios(idusuario),
    idcliente integer references facturacion.clientes(idcliente),
    numerofactura integer,
    cuf varchar,
    cufd varchar,
    total numeric(15,2) default 0,
    descuento numeric(15,2) default 0,
    efectivo numeric(15,2) default 0,
    cambio numeric(15,2) default 0,
    fechaemision timestamp,
    codigo integer,
    estado varchar
);

create table facturacion.detalles(
    iddetalle serial not null primary key,
    idfactura integer references facturacion.facturas(idfactura),
    idproducto integer references facturacion.productos(idproducto),
    cantidad integer,
    preciounitario numeric(15,2)
);

--Ejemplo

INSERT INTO facturacion.clientes
(cliente, tipodocumento, numerodocumento, complemento, correoelectronico) VALUES
('VINCENTY FUNES CESAR', 1, '3517657', '', 'cesarnvf.profesional.bo@gmail.com');

INSERT INTO facturacion.facturas(idusuario, idcliente, total, descuento, efectivo, cambio, numerofactura) VALUES
(26, 1, 65, 0, 65, 0, 1),
(26, 1, 65, 0, 65, 0, 2);

INSERT INTO facturacion.detalles
(idfactura, idproducto, cantidad, preciounitario) VALUES
(1, 1, 10, 6.5),
(2, 1, 10, 6.5);

create table facturacion.productossin(
    idproductosin serial not null primary key,
    codigosin integer,
    codigoactividadsin integer,
    productosin varchar
);

create table facturacion.actividadessin(
    idactividadsin serial not null primary key,
    codigosin integer,
    actividadsin varchar
);

create table facturacion.leyendassin(
    idleyendasin serial not null primary key,
    codigosin integer,
    leyendasin varchar
);

create table facturacion.eventossignificativossin(
    ideventosignificativosin serial not null primary key,
    codigosin integer,
    eventosignificativosin varchar
);

create table facturacion.documentosidentidadessin(
    iddocumentoidentidadsin serial not null primary key,
    codigosin integer,
    documentoidentidadsin varchar
);

create table facturacion.eventossignificativos(
    ideventosignificativo serial not null primary key,
    codigosin integer,
    fechasolicitud date,
    codigorecepcion integer,
    eventosignificativo varchar
);

create table facturacion.eventossignificativosdetalles(
    ideventosignificativodetalle serial not null primary key,
    ideventosignificativo integer references facturacion.eventossignificativos (ideventosignificativo),
    cuf varchar
);

drop table if exists facturacion.detalles;
drop table if exists facturacion.facturas;
drop table if exists facturacion.clientes;

create table facturacion.clientes(
    idcliente serial not null primary key,
    iddocumentoidentidadsin integer references facturacion.documentosidentidadessin(iddocumentoidentidadsin),
    cliente varchar,
    numerodocumento varchar,
    complemento varchar
);

create table facturacion.facturas(
    idfactura serial not null primary key,
    idusuario integer references seguridad.usuarios(idusuario),
    idcliente integer references facturacion.clientes(idcliente),
    numerofactura integer,
    cuf varchar,
    cufd varchar,
    total numeric(15,2) default 0,
    descuento numeric(15,2) default 0,
    efectivo numeric(15,2) default 0,
    cambio numeric(15,2) default 0,
    fechaemision timestamp,
    codigo integer,
    estado varchar
);

create table facturacion.detalles(
    iddetalle serial not null primary key,
    idfactura integer references facturacion.facturas(idfactura),
    idproducto integer references facturacion.productos(idproducto),
    cantidad integer,
    preciounitario numeric(15,2)
);

INSERT INTO facturacion.clientes
(cliente, iddocumentoidentidadsin, numerodocumento, complemento) VALUES
('VINCENTY FUNES CESAR', 1, '3517657', '');

INSERT INTO facturacion.facturas(idusuario, idcliente, total, descuento, efectivo, cambio, numerofactura) VALUES
(26, 1, 65, 0, 65, 0, 1),
(26, 1, 65, 0, 65, 0, 2);

INSERT INTO facturacion.detalles
(idfactura, idproducto, cantidad, preciounitario) VALUES
(1, 1, 10, 6.5),
(2, 1, 10, 6.5);

alter table facturacion.grupos add column tipo integer;
alter table facturacion.productos add column codigocorto integer default 1;
alter table facturacion.clientes add column correoelectronico varchar default '';
alter table facturacion.clientes add column celular varchar default '';
alter table facturacion.facturas add column idmetodopagosin integer;

create table facturacion.metodospagossin(
    idmetodopagosin serial not null primary key,
    codigosin integer,
    metodopagosin varchar
);

insert into facturacion.clientes(cliente, numerodocumento, complemento, iddocumentoidentidadsin)
(select razonsocial, nit, complemento, 1 from ventas.clientefactura where clientefactura.razonsocial <>'') ;

alter table facturacion.puntosventas add column idusuario integer;

alter table facturacion.descuentos add column idgrupo integer;
alter table facturacion.descuentos add column idproducto integer;
alter table facturacion.descuentos add column opcion integer;
alter table facturacion.descuentos drop column cantidad;
alter table facturacion.descuentos add column cantidadinicial numeric(15,2);
alter table facturacion.descuentos add column cantidadfinal numeric(15,2);
alter table facturacion.detalles add column idgrupo integer;

create table facturacion.puntosventasdescuentos(
    idpuntoventadescuento serial not null primary key,
    idpuntoventa integer,
    iddescuento integer,
    foreign key (idpuntoventa) references facturacion.puntosventas (idpuntoventa),
    foreign key (iddescuento) references facturacion.descuentos (iddescuento)
);

alter table facturacion.facturas add column idpuntoventa integer;
alter table facturacion.productos drop column iddescuento;

delete from facturacion.detalles;
delete from facturacion.facturas;

alter table facturacion.descuentos add column idcliente integer references facturacion.clientes (idcliente);

update facturacion.descuentos set idcliente=31245;

alter table facturacion.detalles add column descuento numeric(15,2) default 0;

alter table facturacion.facturas add column leyenda varchar;

alter table facturacion.empresas add column ruta varchar default 'https://https://pilotosiatservicios.impuestos.gob.bo/v2/ServicioFacturacionElectronica';

update facturacion.clientes  set complemento  ='' where  complemento  is null;

alter table facturacion.empresas add column cafc varchar default '10154DD6D7A5E';

alter table facturacion.clientes add codigoexcepcion integer default 0;
alter table facturacion.puntosventas add estadosistema integer default 1;
alter table facturacion.puntosventas add cafc varchar default '';
alter table facturacion.puntosventas add idcufd integer;

create table facturacion.anulacionessin(
    idanulacionsin serial not null primary key,
    codigosin integer,
    anulacionsin varchar
);

insert into facturacion.anulacionessin(codigosin, anulacionsin) values
(1, 'FACTURA MAL EMITIDA'),
(2, 'NOTA DE CREDITO-DEBITO MAL EMITIDA'),
(3, 'DATOS DE EMISION INCORRECTOS'),
(4, 'FACTURA O NOTA DE CREDITO-DEBITO DEVUELTA');

insert into seguridad.enlacesroles(idenlace, idrol, usuariocreacion, usuariomodificacion) VALUES
(347, 20, 1, 1),
(349, 20, 1, 1),
(350, 20, 1, 1);

select * from facturacion.detalles;

delete from facturacion.detalles;
delete from facturacion.facturas;

update facturacion.productos set enlaceproducto=replace(enlaceproducto,'.jpeg','.webp');

select * from seguridad.enlaces order by idenlace desc;
select * from seguridad.enlacesroles  order by idenlace desc;

INSERT INTO seguridad.enlaces (idpagina, idrol, usuariocreacion, usuariomodificacion, idenlacepadre, enlace, ruta, iconoenlace, orden)
VALUES (0, 0, 0, 0, 432, 'Notas de entrega', '/notasentrega', 'fa fa-files-o', 1210);

INSERT INTO seguridad.enlacesroles (idenlace, idrol, usuariocreacion, usuariomodificacion)
VALUES (465, 21, 0, 0);

alter table facturacion.facturas add column idpedido integer default 0;
insert into seguridad.roles(rol) values ('ROL_VENDEDORMAYORISTAS');
