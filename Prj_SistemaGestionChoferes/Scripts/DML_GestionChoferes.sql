-- database: ../DataBase/GestionChoferes.sqlite

INSERT INTO Administrador 
(Usuario   ,Nombre  ,Apellido   ,Correo             ,Clave          ,Cargo          ,Telefono       ,Direccion) VALUES
('admin1', 'Admin', 'Uno',      'admin1@gmail.com', 'password1',    'Supervisor',   '0927654321',   'Dirección Admin 1'),
('admin2', 'Admin', 'Dos',      'admin2@gmail.com', 'password2',    'Supervisor',   '0987654321',   'Dirección Admin 2'),
('admin3', 'Admin', 'Tres',     'admin3@gmail.com', 'password3',    'Supervisor',   '0937654321',   'Dirección Admin 3'),
('admin4', 'Admin', 'Cuatro',   'admin4@gmail.com', 'password4',    'Supervisor',   '0917654321',   'Dirección Admin 4'),
('admin5', 'Admin', 'Cinco',    'admin5@gmail.com', 'password5',    'Supervisor',   '0987954321',   'Dirección Admin 5');

INSERT INTO Chofer 
(Cedula        ,Nombre              ,Apellido           ,Telefono     ,Direccion     ,Correo                      ,CategoriaLicencia ,FechaVenceLicencia ) VALUES
('0102030405', 'Ana Maria',         'Garcia Cevallos',  '0998765432', 'Dirección 1', 'ana.garcia@gmail.com',      'Tipo B',          '2025-12-31'),
('0203040506', 'Luis Eduardo',      'Mendoza Vargas',   '0997654321', 'Dirección 2', 'luis.mendoza@gmail.com',    'Tipo B',          '2025-12-31'),
('0304050607', 'Maria Jose',        'Perez Gomez',      '0996543210', 'Dirección 3', 'maria.perez@gmail.com',     'Tipo B',          '2025-12-31'),
('0405060708', 'Juan Carlos',       'Lopez Zambrano',   '0995432109', 'Dirección 4', 'juan.lopez@gmail.com',      'Tipo B',          '2025-12-31'),
('0506070809', 'Paola Lucia',       'Herrera Torres',   '0994321098', 'Dirección 5', 'paola.fernandez@gmail.com', 'Tipo B',          '2025-12-31'),
('0607080910', 'Andres Felipe',     'Torres Espinosa',  '0993210987', 'Dirección 6', 'andres.torres@gmail.com',   'Tipo B',          '2025-12-31'),
('0708090101', 'Valeria Fernanda',  'Alvarez Alvarado', '0992109876', 'Dirección 7', 'valeria.sanchez@gmail.com', 'Tipo B',          '2025-12-31'),
('0809100202', 'Diego Alejandro',   'Martinez Lozano',  '0991098765', 'Dirección 8', 'diego.martinez@gmail.com',  'Tipo B',          '2025-12-31'),
('0910110303', 'Gabriela Luisa',    'Chavez Chavez',    '0990987654', 'Dirección 9', 'gabriela.morales@gmail.com','Tipo B',          '2025-12-31'),
('1011120404', 'Ricardo Javier',    'Jimenez Guzman',   '0990876543', 'Dirección 10', 'ricardo.jimenez@gmail.com','Tipo B',          '2025-12-31');

INSERT INTO Huella_Digital 
(IdChofer   ,Codigo) VALUES
( 1,        '3A6F2B9C8D7E1F0G5H4I'),
( 2,        '4B7E1G2F9A6D3C0H8I5'),
( 3,        '5C8D3H0A6E1F2G7B9I4'),
( 4,        '6D9A4E2F7B1G3C8H5I0'),
( 5,        '7E0B5F3G8C1D2A6H9I4'),
( 6,        '8F1C6G4H9B2A7D0E3I5'),
( 7,        '9G2D7H5A0C3E8B1F6I4'),
( 8,        '0H3E8I6B1D4F9A2G7C5'),
( 9,        '1I4F9C2E7A5G0D3H8B6'),
( 10,       '2A5G0H8D3F1C6B4I9E7');

INSERT INTO Vehiculo 
(IdChofer   ,Placa      ,Tipo       ,Marca      ,Modelo) VALUES
( 1,        'ABC-1234', 'Tipo 1',   'Marca 1',  'Modelo 1'),
( 2,        'DEF-5678', 'Tipo 2',   'Marca 2',  'Modelo 2'),
( 3,        'GHI-9012', 'Tipo 3',   'Marca 3',  'Modelo 3'),
( 4,        'JKL-3456', 'Tipo 4',   'Marca 4',  'Modelo 4'),
( 5,        'MNO-7890', 'Tipo 5',   'Marca 5',  'Modelo 5'),
( 6,        'PQR-1234', 'Tipo 6',   'Marca 6',  'Modelo 6'),
( 7,        'STU-5678', 'Tipo 7',   'Marca 7',  'Modelo 7'),
( 8,        'VWX-9012', 'Tipo 8',   'Marca 8',  'Modelo 8'),
( 9,        'YZA-3456', 'Tipo 9',   'Marca 9',  'Modelo 9'),
( 10,       'BCD-7890', 'Tipo 10',  'Marca 10', 'Modelo 10');

INSERT INTO Ruta 
(IdChofer   ,Nombre) VALUES
( 1,        'Ruta Quito 1'),
( 2,        'Ruta Quito 2'),
( 3,        'Ruta Quito 3'),
( 4,        'Ruta Quito 4'),
( 5,        'Ruta Quito 5'),
( 6,        'Ruta Quito 6'),
( 7,        'Ruta Quito 7'),
( 8,        'Ruta Quito 8'),
( 9,        'Ruta Quito 9'),
( 10,       'Ruta Quito 10');

INSERT INTO Registro_Estado_Chofer 
(IdChofer   ,EstadoChofer   ,Autorizacion) VALUES
( 1,        'Sobrio',        1),
( 2,        'Sobrio',        1),
( 3,        'Sobrio',        1),
( 4,        'Sobrio',        1),
( 5,        'Sobrio',        1),
( 6,        'Sobrio',        1),
( 7,        'Sobrio',        1),
( 8,        'Sobrio',        1),
( 9,        'Sobrio',        1),
( 10,       'Sobrio',        1);

INSERT INTO Modificacion 
(IdAdministrador, IdChofer  ,Fecha        ,Hora       ,Accion) VALUES
( 1,                1,      '2023-01-01', '09:00:00', 'Actualización de datos'),
( 2,                2,      '2023-01-02', '10:00:00', 'Actualización de datos'),
( 3,                3,      '2023-01-03', '11:00:00', 'Actualización de datos'),
( 4,                4,      '2023-01-04', '12:00:00', 'Actualización de datos'),
( 5,                5,      '2023-01-05', '13:00:00', 'Actualización de datos'),
( 1,                6,      '2023-01-06', '14:00:00', 'Actualización de datos'),
( 2,                7,      '2023-01-07', '15:00:00', 'Actualización de datos'),
( 3,                8,      '2023-01-08', '16:00:00', 'Actualización de datos'),
( 4,                9,      '2023-01-09', '17:00:00', 'Actualización de datos'),
( 5,                10,     '2023-01-10', '18:00:00', 'Actualización de datos');

INSERT INTO Penalizacion 
(IdChofer   ,Infracciones  ,Detalle) VALUES
( 1,         0,            'Ninguna'),
( 2,         0,            'Ninguna'),
( 3,         0,            'Ninguna'),
( 4,         0,            'Ninguna'),
( 5,         0,            'Ninguna'),
( 6,         0,            'Ninguna'),
( 7,         0,            'Ninguna'),
( 8,         0,            'Ninguna'),
( 9,         0,            'Ninguna'),
( 10,        0,            'Ninguna');