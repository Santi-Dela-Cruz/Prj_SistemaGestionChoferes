-- database: ../DataBase/GestionChoferes.sqlite

DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS chofer;
DROP TABLE IF EXISTS huella_digital;
DROP TABLE IF EXISTS modificacion;
DROP TABLE IF EXISTS penalizacion;
DROP TABLE IF EXISTS registro_estado;
DROP TABLE IF EXISTS ruta;
DROP TABLE IF EXISTS vehiculo;

CREATE TABLE administrador (
    admin_id INTEGER PRIMARY KEY AUTOINCREMENT,
    admin_usuario VARCHAR(50) NOT NULL,
    admin_nombre VARCHAR(50) NOT NULL,
    admin_apellido VARCHAR(50) NOT NULL,
    admin_correo VARCHAR(50) NOT NULL,
    admin_contrasena VARCHAR(50) NOT NULL,
    admin_fecha_ingreso DATE NOT NULL,
    admin_hora_ingreso TIME NOT NULL,
    admin_cargo VARCHAR(50) NOT NULL,
    admin_telefono VARCHAR(50) NOT NULL,
    admin_direccion VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    UNIQUE(admin_correo, admin_contrasena, admin_usuario, admin_telefono),
    CONSTRAINT chk_admin_estado CHECK (estado IN ('A', 'X'))
); 

CREATE TABLE chofer (
    chofer_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_cedula VARCHAR(10) NOT NULL,
    chofer_nombre VARCHAR(100) NOT NULL,
    chofer_apellido VARCHAR(100) NOT NULL,
    chofer_telefono VARCHAR(10) NOT NULL,
    chofer_direccion VARCHAR(50) NOT NULL,
    chofer_correo VARCHAR(50) NOT NULL,
    chofer_categoria_licencia VARCHAR(50) NOT NULL,
    chofer_fecha_vencimiento_licencia DATE NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    UNIQUE(chofer_cedula, chofer_telefono, chofer_correo),
    CONSTRAINT chk_chofer_estado CHECK (estado IN ('A', 'X'))
); 

CREATE TABLE huella_digital (
    huella_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_id INTEGER NOT NULL,
    huella_codigo VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    UNIQUE(huella_codigo),
    CONSTRAINT fk_huella_chofer FOREIGN KEY (chofer_id) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_huella_estado CHECK (estado IN ('A', 'X'))
);

CREATE TABLE modificacion (
    modificacion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    admin_id INTEGER NOT NULL,
    chofer_id_modificado INTEGER NOT NULL,
    modificacion_fecha VARCHAR(50) NOT NULL,
    modificacion_hora VARCHAR(50) NOT NULL,
    modificacion_accion VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    CONSTRAINT fk_modificacion_admin FOREIGN KEY (admin_id) REFERENCES administrador (admin_id),
    CONSTRAINT fk_modificacion_chofer FOREIGN KEY (chofer_id_modificado) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_modificacion_estado CHECK (estado IN ('A', 'X'))
);

CREATE TABLE penalizacion (
    penalizacion_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_id INTEGER NOT NULL,
    infracciones INTEGER NOT NULL,
    penalizacion_detalle VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    CONSTRAINT fk_penalizacion_chofer FOREIGN KEY (chofer_id) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_penalizacion_estado CHECK (estado IN ('A', 'X'))
);

CREATE TABLE registro_estado (
    registro_estado_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_id INTEGER NOT NULL,
    estado_chofer VARCHAR(50) DEFAULT NULL,
    autorizacion INTEGER NOT NULL,
    registro_fecha DATE NOT NULL,
    registro_hora TIME NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    CONSTRAINT fk_registro_estado_chofer FOREIGN KEY (chofer_id) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_registro_estado CHECK (estado IN ('A', 'X'))
);

CREATE TABLE ruta (
    ruta_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_id INTEGER NOT NULL,
    ruta_nombre VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    UNIQUE(ruta_nombre),
    CONSTRAINT fk_ruta_chofer FOREIGN KEY (chofer_id) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_ruta_estado CHECK (estado IN ('A', 'X'))
);

CREATE TABLE vehiculo (
    vehiculo_id INTEGER PRIMARY KEY AUTOINCREMENT,
    chofer_id INTEGER NOT NULL,
    vehiculo_placa VARCHAR(8) NOT NULL,
    vehiculo_tipo VARCHAR(50) NOT NULL,
    vehiculo_marca VARCHAR(50) NOT NULL,
    vehiculo_modelo VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT 'A' CHECK (estado IN ('A', 'X')),
    UNIQUE(vehiculo_placa),
    CONSTRAINT fk_vehiculo_chofer FOREIGN KEY (chofer_id) REFERENCES chofer (chofer_id),
    CONSTRAINT chk_vehiculo_estado CHECK (estado IN ('A', 'X'))
);
