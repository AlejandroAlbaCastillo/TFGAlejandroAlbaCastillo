-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 24-04-2024 a las 18:10:26
-- Versión del servidor: 5.7.35-0ubuntu0.18.04.2
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tfg`
--
CREATE DATABASE IF NOT EXISTS `tfg` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tfg`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `dni_usuario` varchar(9) NOT NULL,
  `sueldo` decimal(10,0) NOT NULL,
  `idSucursal` int(11) NOT NULL,
  PRIMARY KEY (`dni_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantienen`
--

DROP TABLE IF EXISTS `mantienen`;
CREATE TABLE IF NOT EXISTS `mantienen` (
  `dni` varchar(9) NOT NULL,
  `idPista` int(10) NOT NULL,
  `tipoTrabajo` varchar(50) NOT NULL,
  PRIMARY KEY (`dni`,`idPista`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `telefono` int(9) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

DROP TABLE IF EXISTS `pistas`;
CREATE TABLE IF NOT EXISTS `pistas` (
  `id_pista` int(11) NOT NULL AUTO_INCREMENT,
  `precioHora` decimal(10,0) NOT NULL,
  `actividad` varchar(50) NOT NULL,
  `idSucursal` int(11) NOT NULL,
  PRIMARY KEY (`id_pista`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE IF NOT EXISTS `reservas` (
  `idPista` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `horaInicio` date NOT NULL,
  `duracion` int(10) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idPista`,`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
CREATE TABLE IF NOT EXISTS `sucursal` (
  `idSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(50) NOT NULL,
  `codigoPostal` int(11) NOT NULL,
  `direccion` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  PRIMARY KEY (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `dni_usuario` varchar(9) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`dni_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
