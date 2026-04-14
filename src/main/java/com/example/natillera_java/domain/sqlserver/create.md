CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Genera un ID numérico automático
    name VARCHAR(100) NOT NULL,       -- Nombre del usuario (obligatorio)
    email VARCHAR(150) NOT NULL UNIQUE, -- Email único para evitar duplicados
    password VARCHAR(255) NOT NULL,   -- Espacio suficiente para contraseñas encriptadas
    created_at DATETIME DEFAULT GETDATE() -- Fecha de registro automática (opcional)
);

CREATE TABLE accounts (
    id INT IDENTITY(1,1) PRIMARY KEY,     -- ID único de la cuenta
    bank VARCHAR(100) NOT NULL,           -- Nombre del banco (asumiendo que 'back' era 'bank')
    number VARCHAR(20) NOT NULL UNIQUE,   -- Número de cuenta único
    user_id INT NOT NULL,                 -- Relación con el usuario
    
    -- Definimos la relación entre tablas
    CONSTRAINT FK_UserAccount FOREIGN KEY (user_id) 
    REFERENCES users(id) 
    ON DELETE CASCADE                     -- Si se borra el usuario, se borran sus cuentas
);

