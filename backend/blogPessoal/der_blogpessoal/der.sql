-- MySQL Script generated by MySQL Workbench
-- Mon May  2 14:21:13 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_blogpessoal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_blogpessoal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_blogpessoal` DEFAULT CHARACTER SET utf8mb4 ;
USE `db_blogpessoal` ;

-- -----------------------------------------------------
-- Table `db_blogpessoal`.`tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_blogpessoal`.`tb_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(55) NOT NULL,
  `email` VARCHAR(175) NOT NULL,
  `photo` VARCHAR(25) NULL,
  `password` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_blogpessoal`.`tb_themes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_blogpessoal`.`tb_themes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_blogpessoal`.`tb_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_blogpessoal`.`tb_post` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `text` VARCHAR(1240) NOT NULL,
  `date` DATE NULL,
  `tb_themes_id` BIGINT NOT NULL,
  `tb_user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_post_tb_themes_idx` (`tb_themes_id` ASC) VISIBLE,
  INDEX `fk_tb_post_tb_user1_idx` (`tb_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_post_tb_themes`
    FOREIGN KEY (`tb_themes_id`)
    REFERENCES `db_blogpessoal`.`tb_themes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_post_tb_user1`
    FOREIGN KEY (`tb_user_id`)
    REFERENCES `db_blogpessoal`.`tb_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
