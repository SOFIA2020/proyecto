-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-09-2024 a las 02:20:55
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `electro_arreglos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `cedula` int(12) NOT NULL,
  `celular` int(15) NOT NULL,
  `usuarios_id` int(11) NOT NULL,
  `usuarios_email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id`, `nombre`, `cedula`, `celular`, `usuarios_id`, `usuarios_email`) VALUES
(1, 'jose gregorio jimenez', 70694521, 319657456, 9, 'jojimenez@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `celular` int(15) NOT NULL,
  `usuarios_id` int(11) NOT NULL,
  `usuarios_email` varchar(50) NOT NULL,
  `cedula` int(15) NOT NULL,
  `dirección` varchar(50) NOT NULL,
  `electrodomestico_id` int(11) NOT NULL,
  `ingreso` date NOT NULL,
  `estado` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `celular`, `usuarios_id`, `usuarios_email`, `cedula`, `dirección`, `electrodomestico_id`, `ingreso`, `estado`) VALUES
(2, 321547798, 7, 'ginnagarcia@gmail.com', 1146789510, 'carrera 20 # 55 - 85', 0, '2024-09-03', '0'),
(3, 312594420, 6, 'medranomat@gmail.com', 80954178, 'transv 12 # 33-65', 0, '2024-08-08', '0'),
(4, 30048745, 5, 'ivang@gmail.com', 70954821, 'calle 57# 33 -65', 0, '2024-09-06', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electrodomestico`
--

CREATE TABLE `electrodomestico` (
  `id` int(11) NOT NULL,
  `referencia` varchar(200) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `ingreso` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  `serial` varchar(30) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `tecnico_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `electrodomestico`
--

INSERT INTO `electrodomestico` (`id`, `referencia`, `marca`, `ingreso`, `estado`, `serial`, `cliente_id`, `tecnico_id`) VALUES
(1, 'Lavadora lg digital ', 'LG', '2024-08-15', 0, 'lg321548j544', 2, 3),
(2, 'Samsum Nevecon', 'samsum', '2024-09-25', 0, 'S25478hy655', 2, 2),
(3, 'Lavadora Samsum', 'Samsum', '2024-07-12', 0, 'S25498kj5331', 4, 3),
(4, 'Lavadora haceb de tambor', 'Haceb', '2024-09-18', 0, 'Tf58752ds56', 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `estado_reparacion` enum('En reparación','Reparado','No reparable') NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_salida` date DEFAULT NULL,
  `id_electrodomestico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id`, `estado_reparacion`, `fecha_ingreso`, `fecha_salida`, `id_electrodomestico`) VALUES
(1, 'En reparación', '2024-09-02', NULL, 4),
(2, 'Reparado', '2024-09-12', '2024-09-28', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `title`) VALUES
(2, 'administrador'),
(1, 'cliente'),
(3, 'tecnico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnico`
--

CREATE TABLE `tecnico` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cedula` int(12) NOT NULL,
  `celular` int(12) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `electrodomestico_id` int(11) NOT NULL,
  `ususarios_id` int(11) NOT NULL,
  `usuarios_email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tecnico`
--

INSERT INTO `tecnico` (`id`, `nombre`, `cedula`, `celular`, `titulo`, `electrodomestico_id`, `ususarios_id`, `usuarios_email`) VALUES
(2, 'Ricardo Antonio Donado', 19845271, 301789451, 'Tecnico en Neveras', 0, 8, 'riardonado@hotmail.com'),
(3, 'Pedro Pablo Angulo', 28456218, 315789245, 'Tecnico en Lavadoras', 0, 10, 'angulop@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `email`, `rol_id`) VALUES
(5, 'ivan gallardo', 'ivang@gmail.com', 1),
(6, 'matias medrano', 'medranomat@gmail.com', 1),
(7, 'ginna garcia', 'ginnagarcia@gmail.com', 1),
(8, 'ricardo donado', 'riardonado@hotmail.com', 3),
(9, 'jose jimenez', 'jojimenez@gmail.com', 2),
(10, 'pedro angulo', 'angulop@gmail.com', 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuarios_id` (`usuarios_id`),
  ADD UNIQUE KEY `usuarios_email` (`usuarios_email`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuarios_id` (`usuarios_id`),
  ADD UNIQUE KEY `usuarios_email` (`usuarios_email`),
  ADD KEY `fk_cliente_electrodomestico_id` (`electrodomestico_id`);

--
-- Indices de la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `serial` (`serial`),
  ADD KEY `fk_electrodomestico_cliente_id` (`cliente_id`),
  ADD KEY `fk_electrodomestico_tecnico_id` (`tecnico_id`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_estado_electrodomestico_id` (`id_electrodomestico`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indices de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tecnico_usuario_id` (`ususarios_id`),
  ADD KEY `fk_tecnico_usuario_email` (`usuarios_email`),
  ADD KEY `fk_tecnico_electrodomestico_id` (`electrodomestico_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `rol_id` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `fk_administrador_usuarios_email` FOREIGN KEY (`usuarios_email`) REFERENCES `usuarios` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_administrador_usuarios_id` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_usuario_email` FOREIGN KEY (`usuarios_email`) REFERENCES `usuarios` (`email`),
  ADD CONSTRAINT `fk_cliente_usuario_id` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  ADD CONSTRAINT `fk_electrodomestico_cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_electrodomestico_tecnico_id` FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estado`
--
ALTER TABLE `estado`
  ADD CONSTRAINT `fk_estado_electrodomestico_id` FOREIGN KEY (`id_electrodomestico`) REFERENCES `electrodomestico` (`id`);

--
-- Filtros para la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD CONSTRAINT `fk_tecnico_usuario_email` FOREIGN KEY (`usuarios_email`) REFERENCES `usuarios` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tecnico_usuario_id` FOREIGN KEY (`ususarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_rol_id` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
