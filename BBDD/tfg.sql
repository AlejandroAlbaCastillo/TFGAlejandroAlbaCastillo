DROP DATABASE IF EXISTS tfg2;
CREATE DATABASE tfg2 COLLATE utf8mb4_spanish2_ci;
USE tfg2;

CREATE TABLE sucursal (
  id_sucursal INT UNSIGNED AUTO_INCREMENT,
  ciudad VARCHAR(50) NOT NULL,
  codigo_postal INT UNSIGNED NOT NULL,
  direccion VARCHAR(50) UNSIGNED NOT NULL,
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
  permisos VARCHAR(4),
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
  tipo_pista VARCHAR(50) NOT NULL,
  fecha_ini DATE NOT NULL,
  duracion DECIMAL(4,2) UNSIGNED NOT NULL,
  PRIMARY KEY (id_mantenimiento),
  FOREIGN KEY (dni) REFERENCES empleados (dni_empleado) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pista) REFERENCES pistas (id_pista) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reservas (
  id_pista INT UNSIGNED,
  dni VARCHAR(9),
  hora_inicio DATE,
  duracion DECIMAL(4,2) UNSIGNED NOT NULL,
  fecha DATE,
  PRIMARY KEY (id_pista,dni,hora_inicio,fecha),
  FOREIGN KEY (dni) REFERENCES usuarios (dni_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_pista) REFERENCES pistas (id_pista) ON DELETE CASCADE ON UPDATE CASCADE
);

