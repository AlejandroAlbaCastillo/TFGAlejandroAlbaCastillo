DROP DATABASE IF EXISTS tfg;
CREATE DATABASE tfg COLLATE utf8mb4_spanish2_ci;
USE tfg;

CREATE TABLE sucursal (
  id_sucursal INT UNSIGNED AUTO_INCREMENT,
  ciudad VARCHAR(50) NOT NULL,
  codigo_postal INT UNSIGNED NOT NULL,
  direccion VARCHAR(50) NOT NULL,
  telefono INT UNSIGNED NOT NULL,
  PRIMARY KEY (id_sucursal)
);

CREATE TABLE rol (
  id_rol INT UNSIGNED AUTO_INCREMENT,
  denominacion VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_rol)
);

CREATE TABLE modulos(
  id_modulo INT UNSIGNED AUTO_INCREMENT,
  modulo VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_modulo)
);

CREATE TABLE permisos(
  id_rol INT UNSIGNED,
  id_modulo INT UNSIGNED,
  permisos VARCHAR(6),
  PRIMARY KEY (id_rol, id_modulo, permisos),
  FOREIGN KEY (id_rol) REFERENCES rol (id_rol) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_modulo) REFERENCES modulos (id_modulo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE personas(
  dni VARCHAR(9),
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  correo VARCHAR(50) NOT NULL,
  telefono int(9) NOT NULL,
  contrasena VARCHAR(50) NOT NULL,
  rol INT UNSIGNED NOT NULL,
  PRIMARY KEY (dni),
  FOREIGN KEY (rol) REFERENCES rol (id_rol) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE empleados (
  dni_empleado VARCHAR(9),
  sueldo decimal(10,0) NOT NULL,
  PRIMARY KEY (dni_empleado),
  FOREIGN KEY (dni_empleado) REFERENCES personas (dni) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE trabajan (
  dni_empleado VARCHAR(9),
  id_sucursal INT UNSIGNED,
  fecha_ini DATE NOT NULL,
  fecha_fin DATE,
  PRIMARY KEY (dni_empleado, id_sucursal,fecha_ini),
  FOREIGN KEY (dni_empleado) REFERENCES empleados (dni_empleado) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usuarios (
  dni_usuario VARCHAR(9),
  usuario VARCHAR(50) NOT NULL,
  PRIMARY KEY (dni_usuario),
  FOREIGN KEY (dni_usuario) REFERENCES personas (dni) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pistas (
  id_pista INT UNSIGNED AUTO_INCREMENT,
  precioHora decimal(10,0) NOT NULL,
  actividad VARCHAR(50) NOT NULL,
  id_sucursal INT UNSIGNED NOT NULL,
  PRIMARY KEY (id_pista),
  FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE mantienen (
  id_mantenimiento INT UNSIGNED AUTO_INCREMENT,
  dni VARCHAR(9) NOT NULL,
  id_pista INT UNSIGNED NOT NULL,
  tipo_trabajo VARCHAR(50) NOT NULL,
  fecha_ini DATE NOT NULL,
  duracion DECIMAL(10,2) UNSIGNED NOT NULL,
  PRIMARY KEY (id_mantenimiento),
  FOREIGN KEY (dni) REFERENCES empleados (dni_empleado) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pista) REFERENCES pistas (id_pista) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reservas (
  id_reserva INT UNSIGNED AUTO_INCREMENT,
  id_pista INT UNSIGNED,
  dni VARCHAR(9),
  hora_inicio VARCHAR(50),
  duracion DECIMAL(10,2) UNSIGNED NOT NULL,
  fecha DATE,
  precio_reserva DECIMAL(4,2) UNSIGNED NOT NULL,
  PRIMARY KEY (id_reserva, id_pista,dni,hora_inicio,fecha),
  FOREIGN KEY (dni) REFERENCES usuarios (dni_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pista) REFERENCES pistas (id_pista) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Inserción de Roles
INSERT INTO rol VALUES(null, "Admin");
INSERT INTO rol VALUES(null, "Director");
INSERT INTO rol VALUES(null, "Empleado");
INSERT INTO rol VALUES(null, "Usuario");

-- Inserción de Modulos
INSERT INTO modulos VALUES(null, "sucursal");
INSERT INTO modulos VALUES(null, "rol");
INSERT INTO modulos VALUES(null, "modulos");
INSERT INTO modulos VALUES(null, "permisos");
INSERT INTO modulos VALUES(null, "personas");
INSERT INTO modulos VALUES(null, "empleados");
INSERT INTO modulos VALUES(null, "trabajan");
INSERT INTO modulos VALUES(null, "usuarios");
INSERT INTO modulos VALUES(null, "pistas");
INSERT INTO modulos VALUES(null, "mantienen");
INSERT INTO modulos VALUES(null, "reservas");
-- Inserción de Permisos
INSERT INTO permisos VALUES(1, 1, "CRUD");
INSERT INTO permisos VALUES(1, 2, "CRUD");
INSERT INTO permisos VALUES(1, 3, "CRUD");
INSERT INTO permisos VALUES(1, 4, "CRUD");
INSERT INTO permisos VALUES(1, 5, "CRUD");
INSERT INTO permisos VALUES(1, 6, "CRUD");
INSERT INTO permisos VALUES(1, 7, "CRUD");
INSERT INTO permisos VALUES(1, 8, "CRUD");
INSERT INTO permisos VALUES(1, 9, "CRUD");
INSERT INTO permisos VALUES(1, 10, "CRUD");
INSERT INTO permisos VALUES(1, 11, "CRUD");
INSERT INTO permisos VALUES(4, 1, "");
INSERT INTO permisos VALUES(4, 2, "");
INSERT INTO permisos VALUES(4, 3, "");
INSERT INTO permisos VALUES(4, 4, "");
INSERT INTO permisos VALUES(4, 5, "CRUD *");
INSERT INTO permisos VALUES(4, 6, "");
INSERT INTO permisos VALUES(4, 7, "");
INSERT INTO permisos VALUES(4, 8, "CRUD *");
INSERT INTO permisos VALUES(4, 9, "");
INSERT INTO permisos VALUES(4, 10, "");
INSERT INTO permisos VALUES(4, 11, "CRUD *");

INSERT INTO personas VALUES("21027174E","Alejandro","Alba Castillo","alejandroalba02@gmail.com",628332813,"proyectoSportify",1);