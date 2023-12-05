-- MySQL Script generated by MySQL Workbench
-- Tue Nov 14 17:28:13 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema calculadora
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema calculadora
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `calculadora` DEFAULT CHARACTER SET utf8 ;
USE `calculadora` ;

-- -----------------------------------------------------
-- Table `calculadora`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculadora`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `calculadora`.`despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculadora`.`despesa` (
  `id_despesa` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_despesa`),
  INDEX `id_usuario_idx` (`id_usuario` ASC),
  CONSTRAINT `id_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `calculadora`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `calculadora`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculadora`.`categoria` (
  `idcategoria` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `calculadora`.`despesa_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculadora`.`despesa_categoria` (
  `idDespesa` INT NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idDespesa`, `idCategoria`),
  INDEX `fk_despesa_has_categoria_categoria1_idx` (`idCategoria` ASC),
  INDEX `fk_despesa_has_categoria_despesa_idx` (`idDespesa` ASC),
  CONSTRAINT `idDespesa`
    FOREIGN KEY (`idDespesa`)
    REFERENCES `calculadora`.`despesa` (`id_despesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idCategoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `calculadora`.`categoria` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
SHOW GRANTS FOR 'root'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
