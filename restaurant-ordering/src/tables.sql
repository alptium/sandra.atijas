CREATE TABLE `ORDER` (
   `id_Order` INT NOT NULL AUTO_INCREMENT,
   `order` VARCHAR(50) COLLATE utf8_unicode_ci default NULL,
   `isServed`  BOOLEAN default NULL,
   `isPaid`     BOOLEAN  default NULL,
   PRIMARY KEY (`id_Order`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `INGREDIENT` (
   `id_ingredient` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(50) COLLATE utf8_unicode_ci default NULL,
   `quantity`  DOUBLE default NULL,
   PRIMARY KEY (`id_ingredient`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `WAITER` (
   `id_waiter` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) COLLATE utf8_unicode_ci default NULL,
   `isFree`  BOOLEAN default NULL,
   PRIMARY KEY (`id_waiter`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `TABLE` (
   `id_table` INT NOT NULL AUTO_INCREMENT,
   `isOccupied`  BOOLEAN default NULL,
   PRIMARY KEY (`id_table`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;