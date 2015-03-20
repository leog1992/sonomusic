SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `almacen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `almacen` ;

CREATE TABLE IF NOT EXISTS `almacen` (
  `idAlmacen` INT(11) NOT NULL AUTO_INCREMENT,
  `nom_alm` VARCHAR(145) NULL DEFAULT NULL,
  `dir_alm` VARCHAR(245) NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `ciudad` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idAlmacen`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cargo` ;

CREATE TABLE IF NOT EXISTS `cargo` (
  `idCargo` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo_cargo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idCargo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `metas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `metas` ;

CREATE TABLE IF NOT EXISTS `metas` (
  `idMetas` INT(11) NOT NULL AUTO_INCREMENT,
  `monto` DECIMAL(8,2) NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `fec_inicio` DATE NULL DEFAULT NULL,
  `fec_fin` DATE NULL DEFAULT NULL,
  `idcargo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idMetas`),
  INDEX `fk_idcargo_idx` (`idcargo` ASC),
  CONSTRAINT `fk_idcargo`
    FOREIGN KEY (`idcargo`)
    REFERENCES `cargo` (`idCargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empleados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empleados` ;

CREATE TABLE IF NOT EXISTS `empleados` (
  `dni` INT(8) NOT NULL,
  `nom_per` VARCHAR(245) NULL DEFAULT NULL,
  `dir_per` VARCHAR(245) NULL DEFAULT NULL,
  `tel_per` VARCHAR(15) NULL DEFAULT NULL,
  `tel2_per` VARCHAR(15) NULL DEFAULT NULL,
  `img_per` VARCHAR(45) NULL DEFAULT NULL,
  `est_per` CHAR(1) NULL DEFAULT NULL,
  `sueldo` DECIMAL(8,2) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `idMetas` INT(11) NOT NULL,
  `idCargo` INT(11) NOT NULL,
  PRIMARY KEY (`dni`),
  INDEX `fk_Empleados_Almacen1_idx` (`idAlmacen` ASC),
  INDEX `fk_Empleados_Metas1_idx` (`idMetas` ASC),
  INDEX `fk_Empleados_Cargo1_idx` (`idCargo` ASC),
  CONSTRAINT `fk_Empleados_Almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleados_Cargo1`
    FOREIGN KEY (`idCargo`)
    REFERENCES `cargo` (`idCargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empleados_Metas1`
    FOREIGN KEY (`idMetas`)
    REFERENCES `metas` (`idMetas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `adelantosempleados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adelantosempleados` ;

CREATE TABLE IF NOT EXISTS `adelantosempleados` (
  `idAdelantos` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `monto` DOUBLE(8,2) NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `dni` INT(8) NOT NULL,
  PRIMARY KEY (`idAdelantos`),
  INDEX `fk_AdelantosEmpleados_Empleados1_idx` (`dni` ASC),
  CONSTRAINT `fk_AdelantosEmpleados_Empleados1`
    FOREIGN KEY (`dni`)
    REFERENCES `empleados` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `clasificacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clasificacion` ;

CREATE TABLE IF NOT EXISTS `clasificacion` (
  `id_clas` INT(11) NOT NULL AUTO_INCREMENT,
  `desc_clas` VARCHAR(145) NULL DEFAULT NULL,
  PRIMARY KEY (`id_clas`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cliente` ;

CREATE TABLE IF NOT EXISTS `cliente` (
  `nro_doc` CHAR(11) NOT NULL,
  `tipo_doc` VARCHAR(3) NULL DEFAULT NULL,
  `nom_per` VARCHAR(245) NULL DEFAULT NULL,
  `dir_per` VARCHAR(245) NULL DEFAULT NULL,
  `tel_per` VARCHAR(15) NULL DEFAULT NULL,
  `tel2_per` VARCHAR(15) NULL DEFAULT NULL,
  `est_per` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`nro_doc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proveedor` ;

CREATE TABLE IF NOT EXISTS `proveedor` (
  `ruc_pro` CHAR(11) NOT NULL,
  `raz_soc_pro` VARCHAR(145) NULL DEFAULT NULL,
  `dir_pro` VARCHAR(145) NULL DEFAULT NULL,
  `tel_pro` CHAR(15) NULL DEFAULT NULL,
  `web` VARCHAR(245) NULL DEFAULT NULL,
  `contacto` VARCHAR(245) NULL,
  `tel_contacto` CHAR(10) NULL,
  `tel2_contacto` CHAR(10) NULL,
  `email_contacto` VARCHAR(45) NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ruc_pro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tipo_doc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_doc` ;

CREATE TABLE IF NOT EXISTS `tipo_doc` (
  `idtipo_doc` INT(11) NOT NULL,
  `desc_tipd` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipo_doc`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tipo_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_pago` ;

CREATE TABLE IF NOT EXISTS `tipo_pago` (
  `idTipo_pago` INT(11) NOT NULL AUTO_INCREMENT,
  `desc` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idTipo_pago`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

CREATE TABLE IF NOT EXISTS `usuario` (
  `nick` CHAR(15) NOT NULL,
  `contra` VARCHAR(45) NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `dni` INT(8) NOT NULL,
  PRIMARY KEY (`nick`),
  INDEX `fk_Usuario_Empleados1_idx` (`dni` ASC),
  CONSTRAINT `fk_Usuario_Empleados1`
    FOREIGN KEY (`dni`)
    REFERENCES `empleados` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compra` ;

CREATE TABLE IF NOT EXISTS `compra` (
  `idCompra` INT(11) NOT NULL AUTO_INCREMENT,
  `ruc_pro` CHAR(11) NOT NULL,
  `glosa` VARCHAR(245) NULL DEFAULT NULL,
  `idtipo_doc` INT(11) NOT NULL,
  `serie_doc` INT(4) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `nro_doc` INT(7) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `fecha_doc` DATE NULL DEFAULT NULL,
  `idTipo_pago` INT(11) NOT NULL,
  `fecha_pago` DATE NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `nick` CHAR(15) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `total` DECIMAL(8,2) NULL DEFAULT NULL,
  `tipo_compra` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idCompra`),
  INDEX `fk_Compra_tipo_doc1_idx` (`idtipo_doc` ASC),
  INDEX `fk_Compra_Proveedor1_idx` (`ruc_pro` ASC),
  INDEX `fk_Compra_Tipo_pago1_idx` (`idTipo_pago` ASC),
  INDEX `fk_Compra_usuario1_idx` (`nick` ASC),
  INDEX `fk_Compra_Almacen1_idx` (`idAlmacen` ASC),
  CONSTRAINT `fk_Compra_Almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Proveedor1`
    FOREIGN KEY (`ruc_pro`)
    REFERENCES `proveedor` (`ruc_pro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_tipo_doc1`
    FOREIGN KEY (`idtipo_doc`)
    REFERENCES `tipo_doc` (`idtipo_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Tipo_pago1`
    FOREIGN KEY (`idTipo_pago`)
    REFERENCES `tipo_pago` (`idTipo_pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cotizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cotizacion` ;

CREATE TABLE IF NOT EXISTS `cotizacion` (
  `idCotizacion` INT(11) NOT NULL AUTO_INCREMENT,
  `fec_cot` DATE NULL DEFAULT NULL,
  `est_cot` CHAR(1) NULL DEFAULT NULL,
  `nro_doc` CHAR(11) NOT NULL,
  `nick` CHAR(15) NOT NULL,
  PRIMARY KEY (`idCotizacion`),
  INDEX `fk_Cotizacion_Persona1_idx` (`nro_doc` ASC),
  INDEX `fk_Cotizacion_usuario1_idx` (`nick` ASC),
  CONSTRAINT `fk_Cotizacion_Persona1`
    FOREIGN KEY (`nro_doc`)
    REFERENCES `cliente` (`nro_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cotizacion_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `und_medida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `und_medida` ;

CREATE TABLE IF NOT EXISTS `und_medida` (
  `idUnd_medida` INT(2) ZEROFILL NOT NULL,
  `desc_und` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idUnd_medida`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `productos` ;

CREATE TABLE IF NOT EXISTS `productos` (
  `idProductos` INT(4) NOT NULL AUTO_INCREMENT,
  `desc_pro` VARCHAR(145) NULL DEFAULT NULL,
  `marca` VARCHAR(45) NULL DEFAULT NULL,
  `modelo` VARCHAR(45) NULL DEFAULT NULL,
  `serie` VARCHAR(45) NULL DEFAULT NULL,
  `grado` VARCHAR(20) NULL DEFAULT NULL,
  `costo_compra` DECIMAL(8,2) NULL DEFAULT NULL,
  `precio_venta` DECIMAL(8,2) NULL DEFAULT NULL,
  `id_clas` INT(11) NOT NULL,
  `idUnd_medida` INT(2) ZEROFILL NOT NULL,
  `cant_actual` DECIMAL(8,2) NULL DEFAULT NULL,
  `cant_min` DECIMAL(8,2) NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `comision` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idProductos`),
  INDEX `fk_productos_clasificacion1_idx` (`id_clas` ASC),
  INDEX `fk_productos_und_medida1_idx` (`idUnd_medida` ASC),
  CONSTRAINT `fk_productos_clasificacion1`
    FOREIGN KEY (`id_clas`)
    REFERENCES `clasificacion` (`id_clas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos_und_medida1`
    FOREIGN KEY (`idUnd_medida`)
    REFERENCES `und_medida` (`idUnd_medida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'clasificacion_id_clas';


-- -----------------------------------------------------
-- Table `detalle_compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalle_compra` ;

CREATE TABLE IF NOT EXISTS `detalle_compra` (
  `idCompra` INT(11) NOT NULL,
  `idProductos` INT(4) NOT NULL,
  `cant_compra` DECIMAL(8,2) NULL DEFAULT NULL,
  `precio_compra` DECIMAL(8,2) NULL DEFAULT NULL,
  INDEX `fk_detalle_compra_Compra1_idx` (`idCompra` ASC),
  INDEX `fk_detalle_compra_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_detalle_compra_Compra1`
    FOREIGN KEY (`idCompra`)
    REFERENCES `compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_compra_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'productos_idProductos';


-- -----------------------------------------------------
-- Table `detalle_cotizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalle_cotizacion` ;

CREATE TABLE IF NOT EXISTS `detalle_cotizacion` (
  `idCotizacion` INT(11) NOT NULL,
  `idProductos` INT(4) NOT NULL,
  `cant` DECIMAL(8,2) NOT NULL,
  `precio_cot` DECIMAL(8,2) NOT NULL,
  INDEX `fk_Detalle_Cotizacion_Cotizacion1_idx` (`idCotizacion` ASC),
  INDEX `fk_detalle_cotizacion_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Detalle_Cotizacion_Cotizacion1`
    FOREIGN KEY (`idCotizacion`)
    REFERENCES `cotizacion` (`idCotizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_cotizacion_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oferta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oferta` ;

CREATE TABLE IF NOT EXISTS `oferta` (
  `idOferta` INT(11) NOT NULL AUTO_INCREMENT,
  `idAlmacen` INT(11) NOT NULL,
  `nom_ofer` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_ofer` DATE NULL DEFAULT NULL,
  `fecha_venc` DATE NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idOferta`),
  INDEX `fk_oferta_almacen1_idx` (`idAlmacen` ASC),
  CONSTRAINT `fk_oferta_almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `detalle_oferta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalle_oferta` ;

CREATE TABLE IF NOT EXISTS `detalle_oferta` (
  `idProductos` INT(4) NOT NULL,
  `idOferta` INT(11) NOT NULL,
  `precio` DOUBLE(8,2) NULL DEFAULT NULL,
  `cantidad` DOUBLE(8,2) NULL DEFAULT NULL,
  INDEX `fk_detalle_oferta_Oferta1_idx` (`idOferta` ASC),
  INDEX `fk_detalle_oferta_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_detalle_oferta_Oferta1`
    FOREIGN KEY (`idOferta`)
    REFERENCES `oferta` (`idOferta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_oferta_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pedido` ;

CREATE TABLE IF NOT EXISTS `pedido` (
  `idPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `fec_ped` DATE NULL DEFAULT NULL,
  `fec_pago` DATE NULL DEFAULT NULL,
  `idTipo_pago` INT(11) NOT NULL,
  `descuento` DECIMAL(8,2) NULL DEFAULT NULL,
  `est_ped` CHAR(1) NULL DEFAULT NULL,
  `idtipo_doc` INT(11) NOT NULL,
  `serie_doc` INT(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `nro_doc` INT(7) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `nick` CHAR(15) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `albaran` INT(11) NULL DEFAULT NULL,
  `cli_doc` CHAR(11) NULL,
  `total` DOUBLE(8,2) NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Tipo_pago1_idx` (`idTipo_pago` ASC),
  INDEX `fk_Pedido_tipo_doc1_idx` (`idtipo_doc` ASC),
  INDEX `fk_Pedido_usuario1_idx` (`nick` ASC),
  INDEX `fk_Pedido_Almacen1_idx` (`idAlmacen` ASC),
  INDEX `fk_pedido_cliente1_idx` (`cli_doc` ASC),
  CONSTRAINT `fk_Pedido_Almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_tipo_doc1`
    FOREIGN KEY (`idtipo_doc`)
    REFERENCES `tipo_doc` (`idtipo_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Tipo_pago1`
    FOREIGN KEY (`idTipo_pago`)
    REFERENCES `tipo_pago` (`idTipo_pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cli_doc`)
    REFERENCES `cliente` (`nro_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `detalle_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalle_pedido` ;

CREATE TABLE IF NOT EXISTS `detalle_pedido` (
  `idProductos` INT(4) NOT NULL,
  `idPedido` INT(11) NOT NULL,
  `precio` DECIMAL(8,2) NULL DEFAULT NULL,
  `cantidad` DECIMAL(8,2) NULL DEFAULT NULL,
  INDEX `fk_Productos_has_Pedido_Pedido1_idx` (`idPedido` ASC),
  INDEX `fk_detalle_pedido_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Pedido_Pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_pedido_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empresa` ;

CREATE TABLE IF NOT EXISTS `empresa` (
  `ruc` CHAR(11) NOT NULL,
  `raz_soc` VARCHAR(255) NULL DEFAULT NULL,
  `dir` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ruc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tipo_movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_movimiento` ;

CREATE TABLE IF NOT EXISTS `tipo_movimiento` (
  `idtipo_movimiento` INT(11) NOT NULL,
  `nombre` VARCHAR(145) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipo_movimiento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kardex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kardex` ;

CREATE TABLE IF NOT EXISTS `kardex` (
  `idKardex` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `idProductos` INT(4) NOT NULL,
  `cant_ing` DECIMAL(8,2) NULL DEFAULT NULL,
  `pre_uni_ing` DECIMAL(8,2) NULL DEFAULT NULL,
  `cant_sal` DECIMAL(8,2) NULL DEFAULT NULL,
  `pre_uni_sal` DECIMAL(8,2) NULL DEFAULT NULL,
  `serie` INT(4) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `numero` INT(4) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `idtipo_doc` INT(11) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `nro_doc` CHAR(11) NULL DEFAULT NULL,
  `nombre` VARCHAR(150) NULL DEFAULT NULL,
  `idtipo_movimiento` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idKardex`),
  INDEX `fk_Kardex_tipo_doc1_idx` (`idtipo_doc` ASC),
  INDEX `fk_Kardex_Almacen1_idx` (`idAlmacen` ASC),
  INDEX `fk_tipo_movimiento` (`idtipo_movimiento` ASC),
  INDEX `fk_kardex_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Kardex_Almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kardex_tipo_doc1`
    FOREIGN KEY (`idtipo_doc`)
    REFERENCES `tipo_doc` (`idtipo_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_movimiento1`
    FOREIGN KEY (`idtipo_movimiento`)
    REFERENCES `tipo_movimiento` (`idtipo_movimiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_kardex_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `letras_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `letras_pedido` ;

CREATE TABLE IF NOT EXISTS `letras_pedido` (
  `idLetras_Pedido` INT(11) NOT NULL AUTO_INCREMENT,
  `monto` DECIMAL(8,2) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `idPedido` INT(11) NOT NULL,
  PRIMARY KEY (`idLetras_Pedido`),
  INDEX `fk_Letras_Pedido_Pedido1_idx` (`idPedido` ASC),
  CONSTRAINT `fk_Letras_Pedido_Pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movimiento` ;

CREATE TABLE IF NOT EXISTS `movimiento` (
  `idMovimiento` INT(11) NOT NULL AUTO_INCREMENT,
  `glosa` VARCHAR(245) NULL DEFAULT NULL,
  `fec_mov` DATE NULL DEFAULT NULL,
  `entrada` DECIMAL(8,2) NULL DEFAULT NULL,
  `salida` DECIMAL(8,2) NULL DEFAULT NULL,
  `nick` CHAR(15) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  INDEX `fk_Movimiento_usuario1_idx` (`nick` ASC),
  INDEX `fk_movimiento_almacen1_idx` (`idAlmacen` ASC),
  CONSTRAINT `fk_Movimiento_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimiento_almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `notas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notas` ;

CREATE TABLE IF NOT EXISTS `notas` (
  `idNotas` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_reg` DATE NULL DEFAULT NULL,
  `fecha_eje` DATE NULL DEFAULT NULL,
  `glosa` VARCHAR(255) NULL DEFAULT NULL,
  `nick` CHAR(15) NOT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idNotas`),
  INDEX `fk_Notas_usuario1_idx` (`nick` ASC),
  CONSTRAINT `fk_Notas_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pagoempleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pagoempleado` ;

CREATE TABLE IF NOT EXISTS `pagoempleado` (
  `idPago` INT(11) NOT NULL AUTO_INCREMENT,
  `monto` DECIMAL(8,2) NULL DEFAULT NULL,
  `dni` INT(8) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `estado` CHAR(1) NULL DEFAULT NULL,
  `Adelantos` DOUBLE(8,2) NULL DEFAULT NULL,
  `Comisiones` DOUBLE(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idPago`),
  INDEX `fk_PagoEmpleado_Empleados1_idx` (`dni` ASC),
  CONSTRAINT `fk_PagoEmpleado_Empleados1`
    FOREIGN KEY (`dni`)
    REFERENCES `empleados` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `producto_almacen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `producto_almacen` ;

CREATE TABLE IF NOT EXISTS `producto_almacen` (
  `idProductos` INT(4) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `cant` DECIMAL(8,2) NULL DEFAULT NULL,
  `precio` DECIMAL(8,2) NULL DEFAULT NULL,
  INDEX `fk_Productos_has_Almacen_Almacen1_idx` (`idAlmacen` ASC),
  INDEX `fk_producto_almacen_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Productos_has_Almacen_Almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_almacen_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `traslado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traslado` ;

CREATE TABLE IF NOT EXISTS `traslado` (
  `idTraslado` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `motivo` CHAR(40) NULL DEFAULT NULL,
  `origen` VARCHAR(255) NULL DEFAULT NULL,
  `destino` VARCHAR(255) NULL DEFAULT NULL,
  `ruc_dest` INT(8) NULL DEFAULT NULL,
  `raz_soc_dest` VARCHAR(255) NULL DEFAULT NULL,
  `nick` CHAR(15) NOT NULL,
  `nro_doc` INT(8) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `ser_doc` INT(4) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`idTraslado`),
  INDEX `fk_traslado_usuario1_idx` (`nick` ASC),
  CONSTRAINT `fk_traslado_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `productos_traslado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `productos_traslado` ;

CREATE TABLE IF NOT EXISTS `productos_traslado` (
  `idTraslado` INT(11) NOT NULL,
  `idProductos` INT(4) NOT NULL,
  `cant` DECIMAL(8,2) NULL DEFAULT NULL,
  INDEX `fk_Traslado_has_Productos_Traslado1_idx` (`idTraslado` ASC),
  INDEX `fk_productos_traslado_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_Traslado_has_Productos_Traslado1`
    FOREIGN KEY (`idTraslado`)
    REFERENCES `traslado` (`idTraslado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos_traslado_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `solicitud_articulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `solicitud_articulos` ;

CREATE TABLE IF NOT EXISTS `solicitud_articulos` (
  `idsolicitud` INT(3) NOT NULL AUTO_INCREMENT,
  `fec_sol` DATE NULL,
  `fec_env` DATE NULL,
  `plazo` INT(1) NULL,
  `id_alm_ori` INT(1) NULL,
  `id_alm_des` INT(1) NULL,
  `estado` CHAR(1) NULL,
  `nick` CHAR(15) NOT NULL,
  PRIMARY KEY (`idsolicitud`),
  INDEX `fk_solicitud_articulos_usuario1_idx` (`nick` ASC),
  CONSTRAINT `fk_solicitud_articulos_usuario1`
    FOREIGN KEY (`nick`)
    REFERENCES `usuario` (`nick`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'base con error';


-- -----------------------------------------------------
-- Table `detalle_solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `detalle_solicitud` ;

CREATE TABLE IF NOT EXISTS `detalle_solicitud` (
  `idsolicitud` INT(3) NOT NULL,
  `idProductos` INT(4) NOT NULL,
  `cant_sol` DOUBLE(8,2) NULL,
  `cant_env` DOUBLE(8,2) NULL,
  INDEX `fk_detalle_solicitud_solicitud_articulos1_idx` (`idsolicitud` ASC),
  INDEX `fk_detalle_solicitud_productos1_idx` (`idProductos` ASC),
  CONSTRAINT `fk_detalle_solicitud_solicitud_articulos1`
    FOREIGN KEY (`idsolicitud`)
    REFERENCES `solicitud_articulos` (`idsolicitud`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_solicitud_productos1`
    FOREIGN KEY (`idProductos`)
    REFERENCES `productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pago_compras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pago_compras` ;

CREATE TABLE IF NOT EXISTS `pago_compras` (
  `idpago` INT(4) NOT NULL AUTO_INCREMENT,
  `fec_pago` DATE NULL,
  `monto` DOUBLE(8,2) NULL,
  `idCompra` INT(11) NOT NULL,
  PRIMARY KEY (`idpago`),
  INDEX `fk_pago_compras_compra1_idx` (`idCompra` ASC),
  CONSTRAINT `fk_pago_compras_compra1`
    FOREIGN KEY (`idCompra`)
    REFERENCES `compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `documento_almacen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `documento_almacen` ;

CREATE TABLE IF NOT EXISTS `documento_almacen` (
  `idtipo_doc` INT(11) NOT NULL,
  `idAlmacen` INT(11) NOT NULL,
  `ser_doc` INT(4) ZEROFILL NULL,
  `nro_doc` INT(7) ZEROFILL NULL,
  PRIMARY KEY (`idtipo_doc`, `idAlmacen`),
  INDEX `fk_tipo_doc_has_almacen_almacen1_idx` (`idAlmacen` ASC),
  INDEX `fk_tipo_doc_has_almacen_tipo_doc1_idx` (`idtipo_doc` ASC),
  CONSTRAINT `fk_tipo_doc_has_almacen_tipo_doc1`
    FOREIGN KEY (`idtipo_doc`)
    REFERENCES `tipo_doc` (`idtipo_doc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_doc_has_almacen_almacen1`
    FOREIGN KEY (`idAlmacen`)
    REFERENCES `almacen` (`idAlmacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `almacen`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `almacen` (`idAlmacen`, `nom_alm`, `dir_alm`, `estado`, `ciudad`) VALUES (1, 'PRINCIPAL', 'XXXX', '1', 'ANCASH');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cargo`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `cargo` (`idCargo`, `tipo_cargo`) VALUES (1, 'GERENTE GENERAL');

COMMIT;


-- -----------------------------------------------------
-- Data for table `metas`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `metas` (`idMetas`, `monto`, `estado`, `fec_inicio`, `fec_fin`, `idcargo`) VALUES (1, 0, '1', '2015-01-01', '2016-01-01', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tipo_doc`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `tipo_doc` (`idtipo_doc`, `desc_tipd`) VALUES (01, 'NOTA DE VENTA');
INSERT INTO `tipo_doc` (`idtipo_doc`, `desc_tipd`) VALUES (02, 'BOLETA');
INSERT INTO `tipo_doc` (`idtipo_doc`, `desc_tipd`) VALUES (03, 'FACTURA');
INSERT INTO `tipo_doc` (`idtipo_doc`, `desc_tipd`) VALUES (04, 'GUIA DE REMISION');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tipo_pago`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `tipo_pago` (`idTipo_pago`, `desc`) VALUES (1, 'CONTADO');
INSERT INTO `tipo_pago` (`idTipo_pago`, `desc`) VALUES (2, 'TARJETA');
INSERT INTO `tipo_pago` (`idTipo_pago`, `desc`) VALUES (3, 'CREDITO');

COMMIT;


-- -----------------------------------------------------
-- Data for table `und_medida`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (01, 'KILOGRAMOS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (02, 'LIBRAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (03, 'TONELADAS LARGAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (04, 'TONELADAS MÉTRICAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (05, 'TONELADAS CORTAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (06, 'GRAMOS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (07, 'UNIDADES');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (08, 'LITROS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (09, 'GALONES');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (10, 'BARRILES');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (11, 'LATAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (12, 'CAJAS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (13, 'MILLARES');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (14, 'METROS CÚBICOS');
INSERT INTO `und_medida` (`idUnd_medida`, `desc_und`) VALUES (15, 'METROS');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tipo_movimiento`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (1, 'VENTA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (2, 'COMPRA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (3, 'CONSIGNACION RECIBIDA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (4, 'CONSIGNACION ENTREGADA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (5, 'DEVOLUCION RECIBIDA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (6, 'DEVOLUCION ENTREGADA');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (7, 'PRMOCION');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (8, 'PREMIO');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (9, 'DONACION');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (10, 'SALIDA A PRODUCCION');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (11, 'TRANSFERENCIA ENTRE ALMACENES');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (12, 'REITRO');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (13, 'MERMAS');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (14, 'DESMEDROS');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (15, 'DESTRUCCION');
INSERT INTO `tipo_movimiento` (`idtipo_movimiento`, `nombre`) VALUES (16, 'SALDO INICIAL');

COMMIT;

