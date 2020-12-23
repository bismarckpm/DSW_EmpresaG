-- MySQL Script generated by MySQL Workbench
-- Tue Dec  8 02:18:57 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema empresag
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema empresag
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empresag` DEFAULT CHARACTER SET utf8 ;
USE `empresag` ;

-- -----------------------------------------------------
-- Table `empresag`.`NIVEL_SOCIOECONOMICO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`NIVEL_SOCIOECONOMICO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`DISPONIBILIDAD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`DISPONIBILIDAD` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`DISPOSITIVO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`DISPOSITIVO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`OCUPACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`OCUPACION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`EDO_CIVIL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`EDO_CIVIL` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`NIVEL_ACADEMICO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`NIVEL_ACADEMICO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`GENERO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`GENERO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`LUGAR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`LUGAR` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  `tipo` INT NOT NULL,
  `fk_nivel_socioeconomico` INT NULL,
  `fk_lugar` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lugar_superior_idx` (`fk_lugar` ASC) VISIBLE,
  INDEX `fk_lugar_nivelsocioeconomico_idx` (`fk_nivel_socioeconomico` ASC) VISIBLE,
  CONSTRAINT `fk_lugar_superior`
    FOREIGN KEY (`fk_lugar`)
    REFERENCES `empresag`.`LUGAR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lugar_nivelsocioeconomico`
    FOREIGN KEY (`fk_nivel_socioeconomico`)
    REFERENCES `empresag`.`NIVEL_SOCIOECONOMICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PERSONA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PERSONA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `documento_identidad` VARCHAR(45) NULL,
  `primer_nombre` VARCHAR(45) NOT NULL,
  `segundo_nombre` VARCHAR(45) NULL,
  `primer_apellido` VARCHAR(45) NOT NULL,
  `segundo_apellido` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `fk_genero` INT NOT NULL,
  `fk_edo_civil` INT NULL,
  `fk_persona` INT NULL,
  `fk_lugar` INT NULL,
  `numero_personas_encasa` INT NULL DEFAULT 0,
  `fk_disponibilidad_inicial` INT NULL DEFAULT NULL,
  `fk_disponibilidad_final` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_persona_genero_idx` (`fk_genero` ASC) VISIBLE,
  INDEX `fk_persona_edocivil_idx` (`fk_edo_civil` ASC) VISIBLE,
  INDEX `fk_persona_padre_idx` (`fk_persona` ASC) VISIBLE,
  INDEX `fk_persona_lugar_idx` (`fk_lugar` ASC) VISIBLE,
  INDEX `fk_persona_dispini_idx` (`fk_disponibilidad_inicial` ASC) VISIBLE,
  INDEX `fk_persona_dispfin_idx` (`fk_disponibilidad_final` ASC) VISIBLE,
  CONSTRAINT `fk_persona_genero`
    FOREIGN KEY (`fk_genero`)
    REFERENCES `empresag`.`GENERO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_edocivil`
    FOREIGN KEY (`fk_edo_civil`)
    REFERENCES `empresag`.`EDO_CIVIL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_padre`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_lugar`
    FOREIGN KEY (`fk_lugar`)
    REFERENCES `empresag`.`LUGAR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_disp_ini`
    FOREIGN KEY (`fk_disponibilidad_inicial`)
    REFERENCES `empresag`.`DISPONIBILIDAD` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_disp_fin`
    FOREIGN KEY (`fk_disponibilidad_final`)
    REFERENCES `empresag`.`DISPONIBILIDAD` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`TELEFONO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`TELEFONO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `fk_persona` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_telefono_persona_idx` (`fk_persona` ASC) VISIBLE,
  CONSTRAINT `fk_telefono_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`ROL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`ROL` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`USUARIO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `estado` INT NOT NULL,
  `fk_persona` INT NULL,
  `fk_rol` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_persona_idx` (`fk_persona` ASC) VISIBLE,
  INDEX `fk_usuario_rol_idx` (`fk_rol` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_rol`
    FOREIGN KEY (`fk_rol`)
    REFERENCES `empresag`.`ROL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`SOLICITUD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`SOLICITUD` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado` INT NOT NULL DEFAULT 0,
  `nombre` VARCHAR(100) NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_solicitud_usuario_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud_usuario`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `empresag`.`USUARIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`ANALISIS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`ANALISIS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `conclusiones` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`ESTUDIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`ESTUDIO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_realizacion` DATE NOT NULL,
  `fecha_culminacion` DATE NULL,
  `nombre` VARCHAR(100) NULL,
  `estado` INT NOT NULL DEFAULT 1,
  `fk_analisis` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_estudio_analisis_idx` (`fk_analisis` ASC) VISIBLE,
  CONSTRAINT `fk_estudio_analisis`
    FOREIGN KEY (`fk_analisis`)
    REFERENCES `empresag`.`ANALISIS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`SOLICITUD_ESTUDIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`SOLICITUD_ESTUDIO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_solicitud` INT NOT NULL,
  `fk_estudio` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_solest_estudio_idx` (`fk_estudio` ASC) VISIBLE,
  INDEX `fk_solest_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  CONSTRAINT `fk_solest_estudio`
    FOREIGN KEY (`fk_estudio`)
    REFERENCES `empresag`.`ESTUDIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solest_solicitud`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `empresag`.`SOLICITUD` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PERSONA_DISPOSITIVO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PERSONA_DISPOSITIVO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_persona` INT NOT NULL,
  `fk_dispositivo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_perdisposit_dispositivo_idx` (`fk_dispositivo` ASC) VISIBLE,
  CONSTRAINT `fk_perdisposit_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perdisposit_dispositivo`
    FOREIGN KEY (`fk_dispositivo`)
    REFERENCES `empresag`.`DISPOSITIVO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PERSONA_OCUPACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PERSONA_OCUPACION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_persona` INT NOT NULL,
  `fk_ocupacion` INT NOT NULL,
  INDEX `fk_perocu_ocupacion_idx` (`fk_ocupacion` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_perocu_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perocu_ocupacion`
    FOREIGN KEY (`fk_ocupacion`)
    REFERENCES `empresag`.`OCUPACION` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PERSONA_NVLACADEMICO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PERSONA_NVLACADEMICO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_persona` INT NOT NULL,
  `fk_nivel_academico` INT NOT NULL,
  INDEX `fk_pernvlaca_nivelacademico_idx` (`fk_nivel_academico` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pernvlaca_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pernvlaca_nivelacademico`
    FOREIGN KEY (`fk_nivel_academico`)
    REFERENCES `empresag`.`NIVEL_ACADEMICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`CATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`CATEGORIA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`SUBCATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`SUBCATEGORIA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`FILTRO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`FILTRO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_estudio` INT NULL,
  `tipo_filtro_lugar` INT NULL,
  `edad_minima` INT NULL,
  `edad_maxima` INT NULL,
  `fk_nivel_socioeconomico` INT NULL,
  `fk_edo_civil` INT NULL,
  `fk_nivel_academico` INT NULL,
  `fk_genero` INT NULL,
  `fk_lugar` INT NULL,
  `fk_categoria` INT NULL,
  `fk_subcategoria` INT NULL,
  `fk_solicitud` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_filtro_estudio_idx` (`fk_estudio` ASC) VISIBLE,
  INDEX `fk_filtro_nivelsocioeconomico_idx` (`fk_nivel_socioeconomico` ASC) VISIBLE,
  INDEX `fk_filtro_edocivil_idx` (`fk_edo_civil` ASC) VISIBLE,
  INDEX `fk_filtro_nivelacademico_idx` (`fk_nivel_academico` ASC) VISIBLE,
  INDEX `fk_filtro_genero_idx` (`fk_genero` ASC) VISIBLE,
  INDEX `fk_filtro_lugar_idx` (`fk_lugar` ASC) VISIBLE,
  INDEX `fk_categoria_idx` (`fk_categoria` ASC) VISIBLE,
  INDEX `fk_filtro_subcategoria_idx` (`fk_subcategoria` ASC) VISIBLE,
  INDEX `fk_filtro_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  CONSTRAINT `fk_filtro_estudio`
    FOREIGN KEY (`fk_estudio`)
    REFERENCES `empresag`.`ESTUDIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_nivelsocioeconomico`
    FOREIGN KEY (`fk_nivel_socioeconomico`)
    REFERENCES `empresag`.`NIVEL_SOCIOECONOMICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_edocivil`
    FOREIGN KEY (`fk_edo_civil`)
    REFERENCES `empresag`.`EDO_CIVIL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_nivelacademico`
    FOREIGN KEY (`fk_nivel_academico`)
    REFERENCES `empresag`.`NIVEL_ACADEMICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_genero`
    FOREIGN KEY (`fk_genero`)
    REFERENCES `empresag`.`GENERO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_lugar`
    FOREIGN KEY (`fk_lugar`)
    REFERENCES `empresag`.`LUGAR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `empresag`.`CATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_subcategoria`
    FOREIGN KEY (`fk_subcategoria`)
    REFERENCES `empresag`.`SUBCATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filtro_solicitud`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `empresag`.`SOLICITUD` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`TIPO_PREGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`TIPO_PREGUNTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PREGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PREGUNTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pregunta` VARCHAR(90) NOT NULL,
  `status` INT NOT NULL,
  `fk_tipo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pregunta_tipo_idx` (`fk_tipo` ASC) VISIBLE,
  CONSTRAINT `fk_pregunta_tipo`
    FOREIGN KEY (`fk_tipo`)
    REFERENCES `empresag`.`TIPO_PREGUNTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`OPCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`OPCION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` VARCHAR(90) NULL,
  `rango_inicial` INT NULL,
  `rango_final` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`POSIBLE_RESPUESTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`POSIBLE_RESPUESTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_pregunta` INT NOT NULL,
  `fk_opcion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posresp_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  INDEX `fk_posresp_opcion_idx` (`fk_opcion` ASC) VISIBLE,
  CONSTRAINT `fk_posresp_pregunta`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `empresag`.`PREGUNTA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_posresp_opcion`
    FOREIGN KEY (`fk_opcion`)
    REFERENCES `empresag`.`OPCION` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`ENCUESTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`ENCUESTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `respuesta_texto` TEXT NULL,
  `respuesta_rango_inicial` INT NULL,
  `respuesta_rango_final` INT NULL,
  `fk_posible_respuesta` INT NULL,
  `fk_pregunta` INT NOT NULL,
  `fk_estudio` INT NOT NULL,
  `fk_persona` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_encuesta_posiblerespuesta_idx` (`fk_posible_respuesta` ASC) VISIBLE,
  INDEX `fk_encuesta_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  INDEX `fk_encuesta_estudio_idx` (`fk_estudio` ASC) VISIBLE,
  INDEX `fk_encuesta_persona_idx` (`fk_persona` ASC) VISIBLE,
  CONSTRAINT `fk_encuesta_posiblerespuesta`
    FOREIGN KEY (`fk_posible_respuesta`)
    REFERENCES `empresag`.`POSIBLE_RESPUESTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_encuesta_estudio`
    FOREIGN KEY (`fk_estudio`)
    REFERENCES `empresag`.`ESTUDIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_encuesta_pregunta`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `empresag`.`PREGUNTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_encuesta_persona`
    FOREIGN KEY (`fk_persona`)
    REFERENCES `empresag`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'fk_posible_respuesta es para el caso de la pregunta donde se selecciona una opcion\nfk_pregunta es para el caso de la pregunta de texto abierto, puesto que no posee opcion';


-- -----------------------------------------------------
-- Table `empresag`.`PREGUNTA_ESTUDIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PREGUNTA_ESTUDIO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `requerido` TINYINT NOT NULL,
  `fk_estudio` INT NOT NULL,
  `fk_pregunta` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_preest_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  INDEX `fk_preest_estudio_idx` (`fk_estudio` ASC) VISIBLE,
  CONSTRAINT `fk_preest_pregunta`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `empresag`.`PREGUNTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_preest_estudio`
    FOREIGN KEY (`fk_estudio`)
    REFERENCES `empresag`.`ESTUDIO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`MARCA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`MARCA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`TIPO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`TIPO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PRESENTACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PRESENTACION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`PREGUNTA_CAT_SUBCAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`PREGUNTA_CAT_SUBCAT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_pregunta` INT NOT NULL,
  `fk_categoria` INT NULL,
  `fk_subcategoria` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pregcatsubcat_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  INDEX `fk_pregcatsubcat_categoria_idx` (`fk_categoria` ASC) VISIBLE,
  INDEX `fk_pregcatsubcat_subcategoria_idx` (`fk_subcategoria` ASC) VISIBLE,
  CONSTRAINT `fk_pregcatsubcat_pregunta`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `empresag`.`PREGUNTA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pregcatsubcat_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `empresag`.`CATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pregcatsubcat_subcategoria`
    FOREIGN KEY (`fk_subcategoria`)
    REFERENCES `empresag`.`SUBCATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`CATEGORIA_SUBCATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`CATEGORIA_SUBCATEGORIA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_categoria` INT NOT NULL,
  `fk_subcategoria` INT NOT NULL,
  INDEX `fk_catsubcat_subcategoria_idx` (`fk_subcategoria` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_catsubcat_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `empresag`.`CATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_catsubcat_subcategoria`
    FOREIGN KEY (`fk_subcategoria`)
    REFERENCES `empresag`.`SUBCATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`SUBCATEGORIA_MARCA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`SUBCATEGORIA_MARCA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_subcategoria` INT NOT NULL,
  `fk_marca` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subcatmarca_marca_idx` (`fk_marca` ASC) VISIBLE,
  CONSTRAINT `fk_subcatmarca_subcategoria`
    FOREIGN KEY (`fk_subcategoria`)
    REFERENCES `empresag`.`SUBCATEGORIA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subcatmarca_marca`
    FOREIGN KEY (`fk_marca`)
    REFERENCES `empresag`.`MARCA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`MARCA_TIPO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`MARCA_TIPO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_marca` INT NOT NULL,
  `fk_tipo` INT NOT NULL,
  INDEX `fk_marcatipo_tipo_idx` (`fk_tipo` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_marcatipo_marca`
    FOREIGN KEY (`fk_marca`)
    REFERENCES `empresag`.`MARCA` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marcatipo_tipo`
    FOREIGN KEY (`fk_tipo`)
    REFERENCES `empresag`.`TIPO` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empresag`.`TIPO_PRESENTACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`TIPO_PRESENTACION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_tipo` INT NOT NULL,
  `fk_presentacion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tipopres_presentacion_idx` (`fk_presentacion` ASC) VISIBLE,
  CONSTRAINT `fk_tipopres_tipo`
    FOREIGN KEY (`fk_tipo`)
    REFERENCES `empresag`.`TIPO` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipopres_presentacion`
    FOREIGN KEY (`fk_presentacion`)
    REFERENCES `empresag`.`PRESENTACION` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `empresag`.`TOKENS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empresag`.`TOKENS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token_login` VARCHAR(30) NULL,
  `token_reset` VARCHAR(30) NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_token_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_token`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `empresag`.`USUARIO` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
