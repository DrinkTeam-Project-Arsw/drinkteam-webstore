CREATE TABLE USUARIO
(nombre VARCHAR(50) NOT NULL ,
correo VARCHAR(50) NOT NULL,
passw VARCHAR(100) NOT NULL,
tipo VARCHAR(50) NOT NULL,
carnet integer NOT NULL,
celular integer NOT NULL,
PRIMARY KEY (carnet)
);

create sequence SUS_TRANSACCION_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE TRANSACCION
(id integer NOT NULL DEFAULT NEXTVAL('SUS_TRANSACCION_seq'),
valor integer NOT NULL,
fecha TIMESTAMP NOT NULL,
origen integer NOT NULL,
destino integer NOT NULL,
producto integer NOT NULL,
PRIMARY KEY (id)
);

create sequence SUS_PRODUCTO_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE PRODUCTO
(identificador integer NOT NULL DEFAULT NEXTVAL('SUS_PRODUCTO_seq'),
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(50) NOT NULL,
precio integer NOT NULL,
estado VARCHAR(50) NOT NULL,
subasta integer,
PRIMARY KEY (identificador)
);

CREATE SEQUENCE SUS_SUBASTA_seq
  start with 1
  increment by 1
  maxvalue 99999999
  minvalue 1;

CREATE TABLE SUBASTA
(id integer NOT NULL DEFAULT NEXTVAL('SUS_SUBASTA_seq'),
fecha_ini TIMESTAMP NOT NULL,
fecha_fin TIMESTAMP NOT NULL,
precio integer NOT NULL,
usuario integer NOT NULL,
PRIMARY KEY (id)
);