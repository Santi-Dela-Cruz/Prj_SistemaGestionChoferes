DROP TABLE IF EXISTS `administrador`;
DROP TABLE IF EXISTS `choferes`;
DROP TABLE IF EXISTS `huella`;
DROP TABLE IF EXISTS `modificaciones`;
DROP TABLE IF EXISTS `registro_estados`;
DROP TABLE IF EXISTS `registro_penalizaciones`;
DROP TABLE IF EXISTS `rutas`;
DROP TABLE IF EXISTS `vehiculo`;

CREATE TABLE `administrador` (
    `id` INTEGER PRIMARY KEY AUTOINCREMENT,
    `usuarioAdmin` VARCHAR(50) NOT NULL,
    `nombreAdmin` VARCHAR(50) NOT NULL,
    `apellidoAdmin` VARCHAR(50) NOT NULL,
    `correoAdmin` VARCHAR(50) NOT NULL,
    `contrasenaAdmin` VARCHAR(50) NOT NULL,
    `fechaIngreso` DATE NOT NULL,
    `horaIngreso` TIME NOT NULL,
    `estadoAdmin` VARCHAR(50) NOT NULL,
    `cargoAdmin` VARCHAR(50) NOT NULL,
    `telefono` VARCHAR(50) NOT NULL,
    `direccion` VARCHAR(50) NOT NULL,
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    UNIQUE (`correoAdmin`, `contrasenaAdmin`, `usuarioAdmin`, `telefono`),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `choferes` (
    `id_Chofer` INTEGER PRIMARY KEY AUTOINCREMENT,
    `id_Cedula` VARCHAR(10) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `apellido` VARCHAR(100) NOT NULL,
    `telefono` VARCHAR(10) NOT NULL,
    `direccion` VARCHAR(50) NOT NULL,
    `correo` VARCHAR(50) NOT NULL,
    `categoriaLicencia` VARCHAR(50) NOT NULL,
    `fechaVenciminetoLicencia` DATE NOT NULL,
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    UNIQUE (`id_Cedula`, `telefono`, `correo`),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `huella` (
    `id_Huella` INTEGER PRIMARY KEY AUTOINCREMENT,
    `id_Codigo_Huella` VARCHAR(50) NOT NULL,
    `fecha_RegHuella` DATE NOT NULL,
    `id_Chofer` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    UNIQUE (`id_Codigo_Huella`),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `modificaciones` (
    `id` INTEGER PRIMARY KEY AUTOINCREMENT,
    `id_Administrador` INTEGER NOT NULL REFERENCES `administrador`(`id`),
    `id_ChoferModificacion` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `fechaModificacion` VARCHAR(50) NOT NULL,
    `horaModificacion` VARCHAR(50) NOT NULL,
    `accionAdmin` VARCHAR(50) NOT NULL,
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `registro_estados` (
    `id_RegEst` INTEGER PRIMARY KEY AUTOINCREMENT,
    `fecha_Ingreso` DATE NOT NULL,
    `hora_Ingreso` TIME NOT NULL,
    `estado_Chofer` VARCHAR(20) NOT NULL,
    `autorizacion_Chofer` TINYINT(1) NOT NULL,
    `id_Chofer` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `registro_penalizaciones` (
    `id_RegPen` INTEGER PRIMARY KEY AUTOINCREMENT,
    `n_Infracciones` INTEGER NOT NULL,
    `penalizacion_Chofer` VARCHAR(50) NOT NULL,
    `id_Chofer` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `rutas` (
    `id_Ruta` INTEGER PRIMARY KEY AUTOINCREMENT,
    `nombre_Ruta` VARCHAR(50) NOT NULL,
    `id_Chofer` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    UNIQUE (`nombre_Ruta`),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `vehiculo` (
    `id_Vehiculo` INTEGER PRIMARY KEY AUTOINCREMENT,
    `id_Placa` VARCHAR(8) NOT NULL,
    `tipo_Vehiculo` VARCHAR(50) NOT NULL,
    `id_Chofer` INTEGER NOT NULL REFERENCES `choferes`(`id_Chofer`),
    `marcaVehiculo` VARCHAR(50) NOT NULL,
    `modeloVehiculo` VARCHAR(50) NOT NULL,
    `estado` CHAR(1) DEFAULT 'A' CHECK (`estado` IN ('A', 'X')),
    UNIQUE (`id_Placa`),
    `FECHACREA` DATETIME DEFAULT CURRENT_TIMESTAMP
);
