CREATE DATABASE IF NOT EXISTS DB_registros_futbol;
CREATE USER IF NOT EXISTS 'jgnarvaez'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON DB_registros_futbol.* TO 'jgnarvaez'@'%';
FLUSH PRIVILEGES;