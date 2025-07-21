CREATE DATABASE IF NOT EXISTS montecafeWeb;
USE montecafeweb;

CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    contacto VARCHAR(100),
    correo VARCHAR (100),
    direccion VARCHAR(150)
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(50),
    correo VARCHAR(100),
    direccion VARCHAR(150)
);

CREATE TABLE materia_prima (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    cantidad INT,
    stock INT
);

CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_producto VARCHAR(20) UNIQUE,
    producto VARCHAR(100),
    categoria VARCHAR(50),
    stock INT,
    unidad VARCHAR(20),
    estado VARCHAR(50)
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(100),
    producto VARCHAR(100),
    cantidad INT,
    precio_unitario DOUBLE,
    total DOUBLE,
    fecha DATE
);

CREATE TABLE configuracion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE,
    correo VARCHAR (150),
    password VARCHAR(100),
    rol VARCHAR(20)  -- Por ejemplo: Admin, Vendedor
);