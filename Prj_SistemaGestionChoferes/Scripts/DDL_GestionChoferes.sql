-- database: ../DataBase/GestionChoferes.sqlite

DROP TABLE IF EXISTS Huella_Digital;
DROP TABLE IF EXISTS Modificacion;
DROP TABLE IF EXISTS Penalizacion;
DROP TABLE IF EXISTS Registro_Estado_Chofer;
DROP TABLE IF EXISTS Ruta;
DROP TABLE IF EXISTS Vehiculo;
DROP TABLE IF EXISTS Chofer;
DROP TABLE IF EXISTS Administrador;

CREATE TABLE Administrador (
     IdAdministrador INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT
    ,Nombre          VARCHAR(50) NOT NULL
    ,Apellido        VARCHAR(50) NOT NULL
    ,Usuario         VARCHAR(50) NOT NULL UNIQUE
    ,Correo          VARCHAR(50) NOT NULL UNIQUE
    ,Clave           VARCHAR(50) NOT NULL UNIQUE
    ,Cargo           VARCHAR(50) NOT NULL 
    ,Telefono        VARCHAR(50) NOT NULL UNIQUE
    ,Direccion       VARCHAR(50) NOT NULL
    ,FechaIngreso    DATE        
    ,HoraIngreso     TIME        
    ,Estado          VARCHAR(1)  NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT      chk_admin_Estado CHECK (Estado IN ('A', 'X'))
); 

CREATE TABLE Chofer (
     IdChofer           INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT
    ,Cedula             VARCHAR(10)     NOT NULL UNIQUE
    ,Nombre             VARCHAR(100)    NOT NULL
    ,Apellido           VARCHAR(100)    NOT NULL
    ,Telefono           VARCHAR(10)     NOT NULL UNIQUE
    ,Direccion          VARCHAR(50)     NOT NULL
    ,Correo             VARCHAR(50)     NOT NULL UNIQUE
    ,CategoriaLicencia  VARCHAR(50)     NOT NULL
    ,FechaVenceLicencia DATE            NOT NULL
    ,Estado             VARCHAR(1)      NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT         chk_Chofer_Estado CHECK (Estado IN ('A', 'X'))
); 

CREATE TABLE Huella_Digital (
     IdHuella    INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdChofer    INTEGER        NOT NULL
    ,Codigo      VARCHAR(50)    NOT NULL UNIQUE
    ,Estado      VARCHAR(1)     NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT  fk_huella_Chofer FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT  chk_huella_Estado CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Vehiculo (
     IdVehiculo  INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdChofer    INTEGER        NOT NULL
    ,Placa       VARCHAR(8)     NOT NULL UNIQUE
    ,Tipo        VARCHAR(50)    NOT NULL
    ,Marca       VARCHAR(50)    NOT NULL
    ,Modelo      VARCHAR(50)    NOT NULL
    ,Estado      VARCHAR(1)     NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT  fk_Vehiculo_Chofer FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT  chk_Vehiculo_Estado CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Modificacion (
     IdModificacion     INTEGER          NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdAdministrador    INTEGER          NOT NULL
    ,IdChofer           INTEGER          NOT NULL
    ,Accion             VARCHAR(50)      NOT NULL
    ,Fecha              VARCHAR(50)      NOT NULL
    ,Hora               VARCHAR(50)      NOT NULL
    ,Estado             VARCHAR(1)       NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT fk_Modificacion_admin    FOREIGN KEY (IdAdministrador) REFERENCES Administrador (IdAdministrador)
    ,CONSTRAINT fk_Modificacion_Chofer   FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT chk_Modificacion_Estado  CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Penalizacion (
     IdPenalizacion  INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdChofer        INTEGER         NOT NULL
    ,Infracciones    INTEGER         NOT NULL
    ,Detalle         VARCHAR(50)     NOT NULL
    ,Estado          VARCHAR(1)      NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT      fk_Penalizacion_Chofer FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT      chk_Penalizacion_Estado CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Registro_Estado_Chofer (
     IdEstadoChofer  INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdChofer        INTEGER         NOT NULL
    ,EstadoChofer    VARCHAR(50)     DEFAULT NULL
    ,Autorizacion    INTEGER         NOT NULL
    ,Fecha           DATE            
    ,Hora            TIME            
    ,Estado          VARCHAR(1)      NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT      fk_Registro_Estado_Chofer_Chofer FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT      chk_Registro_Estado_Chofer CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Ruta (
     IdRuta      INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdChofer    INTEGER     NOT NULL
    ,Nombre      VARCHAR(50) NOT NULL UNIQUE
    ,Estado      VARCHAR(1)  NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,CONSTRAINT  fk_Ruta_Chofer FOREIGN KEY (IdChofer) REFERENCES Chofer (IdChofer)
    ,CONSTRAINT  chk_Ruta_Estado CHECK (Estado IN ('A', 'X'))
);
