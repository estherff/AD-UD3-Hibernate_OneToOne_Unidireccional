
CREATE DATABASE IF NOT EXISTS empresa DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE empresa;
CREATE TABLE empleados (  
 id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,  
 emNombre varchar(50) NOT NULL,  
 emApellidos varchar(50) NOT NULL,  
 emSueldo decimal(10,0) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE direcciones (
 id int(11) NOT NULL,  
 direccion varchar(100) NOT NULL,  
 ciudad varchar(75) NOT NULL,  
 idEmpleado int(11) NOT NULL,
 FOREIGN KEY (idEmpleado) REFERENCES empleados (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;