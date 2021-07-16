CREATE DATABASE  IF NOT EXISTS `bd_pescadeportiva` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_pescadeportiva`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_pescadeportiva
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acotado`
--

DROP TABLE IF EXISTS `acotado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acotado` (
  `AcoCod` int NOT NULL,
  `AcoDes` varchar(40) DEFAULT NULL,
  `AcoEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`AcoCod`),
  KEY `Relationship45` (`AcoEstReg`),
  CONSTRAINT `Relationship45` FOREIGN KEY (`AcoEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acotado`
--

LOCK TABLES `acotado` WRITE;
/*!40000 ALTER TABLE `acotado` DISABLE KEYS */;
INSERT INTO `acotado` VALUES (0,'No acotado','A'),(1,'Acotado','A');
/*!40000 ALTER TABLE `acotado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `afi_lic`
--

DROP TABLE IF EXISTS `afi_lic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `afi_lic` (
  `AfiLicCod` int NOT NULL,
  `AfiLicAfiCod` int NOT NULL,
  `AfiLicLicCod` int NOT NULL,
  `AfiLicEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`AfiLicCod`),
  KEY `Relationship37` (`AfiLicAfiCod`),
  KEY `Relationship38` (`AfiLicLicCod`),
  KEY `Relationship62` (`AfiLicEstReg`),
  CONSTRAINT `Relationship37` FOREIGN KEY (`AfiLicAfiCod`) REFERENCES `afiliados` (`AfiCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship38` FOREIGN KEY (`AfiLicLicCod`) REFERENCES `licencias` (`LicCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship62` FOREIGN KEY (`AfiLicEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afi_lic`
--

LOCK TABLES `afi_lic` WRITE;
/*!40000 ALTER TABLE `afi_lic` DISABLE KEYS */;
INSERT INTO `afi_lic` VALUES (1,2,1,'A'),(2,3,2,'A');
/*!40000 ALTER TABLE `afi_lic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `afiliados`
--

DROP TABLE IF EXISTS `afiliados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `afiliados` (
  `AfiCod` int NOT NULL,
  `AfiNom` varchar(40) DEFAULT NULL,
  `AfiApePat` varchar(40) DEFAULT NULL,
  `AfiApeMat` varchar(40) DEFAULT NULL,
  `AfiFecInsAnio` int DEFAULT NULL,
  `AfiFecInsMes` int DEFAULT NULL,
  `AfiFecInsDia` int DEFAULT NULL,
  `AfiPaiCod` int NOT NULL,
  `AfiEstCivCod` int NOT NULL DEFAULT '1',
  `AfiLicCod` int NOT NULL,
  `AfiEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`AfiCod`),
  KEY `Relationship23` (`AfiPaiCod`),
  KEY `Relationship24` (`AfiEstCivCod`),
  KEY `Relationship25` (`AfiLicCod`),
  KEY `Relationship54` (`AfiEstReg`),
  CONSTRAINT `Relationship23` FOREIGN KEY (`AfiPaiCod`) REFERENCES `pais` (`PaiCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship24` FOREIGN KEY (`AfiEstCivCod`) REFERENCES `estado_civil` (`EstCivCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship25` FOREIGN KEY (`AfiLicCod`) REFERENCES `licencias` (`LicCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship54` FOREIGN KEY (`AfiEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afiliados`
--

LOCK TABLES `afiliados` WRITE;
/*!40000 ALTER TABLE `afiliados` DISABLE KEYS */;
INSERT INTO `afiliados` VALUES (1,'Lucho Pepe','Morales','Gonzales',2001,1,12,1,1,1,'A'),(2,'Pepe Nacho','Gonzales','Morales',2004,4,13,2,2,1,'A'),(3,'Nacho Gilbert','Quispe','Lozano',1998,6,22,2,3,1,'A'),(4,'Pepe Lucho','Tamarro','Lozano',1999,3,12,3,2,2,'A'),(5,'Gilbert Berly','Dolores','Gungry',2005,6,26,1,4,2,'A'),(6,'Frank Berly','Conolly','Terrones',2005,12,11,1,3,2,'A');
/*!40000 ALTER TABLE `afiliados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `captura`
--

DROP TABLE IF EXISTS `captura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `captura` (
  `CapCod` int NOT NULL,
  `CapTipCod` int NOT NULL,
  `CapEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CapCod`),
  KEY `Relationship27` (`CapTipCod`),
  KEY `Relationship59` (`CapEstReg`),
  CONSTRAINT `Relationship27` FOREIGN KEY (`CapTipCod`) REFERENCES `captura_tip` (`CapTipCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship59` FOREIGN KEY (`CapEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `captura`
--

LOCK TABLES `captura` WRITE;
/*!40000 ALTER TABLE `captura` DISABLE KEYS */;
INSERT INTO `captura` VALUES (1,1,'A'),(2,1,'A');
/*!40000 ALTER TABLE `captura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `captura_tip`
--

DROP TABLE IF EXISTS `captura_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `captura_tip` (
  `CapTipCod` int NOT NULL,
  `CapTipDes` varchar(40) DEFAULT NULL,
  `CapEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CapTipCod`),
  KEY `Relationship57` (`CapEstReg`),
  CONSTRAINT `Relationship57` FOREIGN KEY (`CapEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `captura_tip`
--

LOCK TABLES `captura_tip` WRITE;
/*!40000 ALTER TABLE `captura_tip` DISABLE KEYS */;
INSERT INTO `captura_tip` VALUES (0,'Captura solo','A'),(1,'Captura competencia','A');
/*!40000 ALTER TABLE `captura_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capturas_eve`
--

DROP TABLE IF EXISTS `capturas_eve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capturas_eve` (
  `CapEveCod` int NOT NULL,
  `CapEveCapCod` int NOT NULL,
  `CapEveEveCod` int NOT NULL,
  `CapEveFecAnio` int DEFAULT NULL,
  `CapEveFecMes` int DEFAULT NULL,
  `CapEveFecDia` int DEFAULT NULL,
  `CapEveHor` int DEFAULT NULL,
  `CapEveMin` int DEFAULT NULL,
  `CapEveEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CapEveCod`),
  KEY `Relationship32` (`CapEveCapCod`),
  KEY `Relationship33` (`CapEveEveCod`),
  KEY `Relationship65` (`CapEveEstReg`),
  CONSTRAINT `Relationship32` FOREIGN KEY (`CapEveCapCod`) REFERENCES `captura` (`CapCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship33` FOREIGN KEY (`CapEveEveCod`) REFERENCES `eventos` (`EveCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship65` FOREIGN KEY (`CapEveEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capturas_eve`
--

LOCK TABLES `capturas_eve` WRITE;
/*!40000 ALTER TABLE `capturas_eve` DISABLE KEYS */;
INSERT INTO `capturas_eve` VALUES (1,2,1,2005,1,12,3,2,'A'),(2,2,2,2020,2,20,12,1,'A');
/*!40000 ALTER TABLE `capturas_eve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capturas_solos`
--

DROP TABLE IF EXISTS `capturas_solos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capturas_solos` (
  `CapSolCod` int NOT NULL,
  `CapCapCod` int NOT NULL,
  `CapLugCod` int NOT NULL,
  `CapSolAfi1` int NOT NULL,
  `CapSolAfi2` int NOT NULL,
  `CapSolFecAnio` int DEFAULT NULL,
  `CapSolFecMes` int DEFAULT NULL,
  `CapSolFecDia` int DEFAULT NULL,
  `CapSolEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CapSolCod`),
  KEY `Relationship30` (`CapSolAfi1`),
  KEY `Relationship31` (`CapSolAfi2`),
  KEY `Relationship34` (`CapLugCod`),
  KEY `Relationship35` (`CapCapCod`),
  KEY `Relationship64` (`CapSolEstReg`),
  CONSTRAINT `Relationship30` FOREIGN KEY (`CapSolAfi1`) REFERENCES `afiliados` (`AfiCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship31` FOREIGN KEY (`CapSolAfi2`) REFERENCES `afiliados` (`AfiCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship34` FOREIGN KEY (`CapLugCod`) REFERENCES `lugares` (`LugCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship35` FOREIGN KEY (`CapCapCod`) REFERENCES `captura` (`CapCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship64` FOREIGN KEY (`CapSolEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capturas_solos`
--

LOCK TABLES `capturas_solos` WRITE;
/*!40000 ALTER TABLE `capturas_solos` DISABLE KEYS */;
INSERT INTO `capturas_solos` VALUES (1,1,3,4,6,2005,1,12,'A'),(2,1,5,2,3,2020,2,20,'A');
/*!40000 ALTER TABLE `capturas_solos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cauces`
--

DROP TABLE IF EXISTS `cauces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cauces` (
  `CauCod` int NOT NULL,
  `CauNom` varchar(40) DEFAULT NULL,
  `CauDes` varchar(40) DEFAULT NULL,
  `CauEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`CauCod`),
  KEY `Relationship43` (`CauEstReg`),
  CONSTRAINT `Relationship43` FOREIGN KEY (`CauEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cauces`
--

LOCK TABLES `cauces` WRITE;
/*!40000 ALTER TABLE `cauces` DISABLE KEYS */;
INSERT INTO `cauces` VALUES (1,'Cauce 1','Lugar Bonito','A'),(2,'Cauce 2','Lugar Bonito','A'),(3,'Cauce 3','Lugar Pantanoso','A'),(4,'Cauce 4','Lugar Frondoso','A'),(5,'Cauce 5','Lugar Abundante en peces','A'),(6,'Cauce 6','Lugar Pantanoso','A'),(7,'Cauce 7','Lugar Abundante en peces','A'),(8,'Cauce 8','Lugar Bonito','A');
/*!40000 ALTER TABLE `cauces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_civil`
--

DROP TABLE IF EXISTS `estado_civil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_civil` (
  `EstCivCod` int NOT NULL DEFAULT '1',
  `EstCivDes` varchar(40) DEFAULT NULL,
  `EstCivEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`EstCivCod`),
  KEY `Relationship50` (`EstCivEstReg`),
  CONSTRAINT `Relationship50` FOREIGN KEY (`EstCivEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_civil`
--

LOCK TABLES `estado_civil` WRITE;
/*!40000 ALTER TABLE `estado_civil` DISABLE KEYS */;
INSERT INTO `estado_civil` VALUES (1,'Soltero','A'),(2,'Casado','A'),(3,'Viudo','A'),(4,'Divorciado','A'),(5,'Conviviente','A'),(6,'No_Conviviente','A');
/*!40000 ALTER TABLE `estado_civil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_registro`
--

DROP TABLE IF EXISTS `estado_registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_registro` (
  `EstRegCod` char(1) NOT NULL DEFAULT 'A',
  `EstRegDes` char(1) DEFAULT NULL,
  `FK_EstRegCod` char(1) DEFAULT 'A',
  PRIMARY KEY (`EstRegCod`),
  KEY `IX_Relationship66` (`FK_EstRegCod`),
  CONSTRAINT `Relationship66` FOREIGN KEY (`FK_EstRegCod`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_registro`
--

LOCK TABLES `estado_registro` WRITE;
/*!40000 ALTER TABLE `estado_registro` DISABLE KEYS */;
INSERT INTO `estado_registro` VALUES ('A','A','A'),('E','E','A'),('I','I','A');
/*!40000 ALTER TABLE `estado_registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `EveCod` int NOT NULL,
  `EveNom` varchar(40) DEFAULT NULL,
  `EveFecAnio` int DEFAULT NULL,
  `EveFecMes` int DEFAULT NULL,
  `EveFecDia` int DEFAULT NULL,
  `EvePre` decimal(4,2) NOT NULL DEFAULT '0.00',
  `EveNroPar` int DEFAULT NULL,
  `EveCar` varchar(15) DEFAULT NULL,
  `EveLugCod` int NOT NULL,
  `EveEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`EveCod`),
  KEY `Relationship26` (`EveLugCod`),
  KEY `Relationship60` (`EveEstReg`),
  CONSTRAINT `Relationship26` FOREIGN KEY (`EveLugCod`) REFERENCES `lugares` (`LugCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship60` FOREIGN KEY (`EveEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (1,'Pesca y Gana',2001,12,12,55.00,500,'Nacional',1,'A'),(2,'Cuve List',2004,2,1,20.00,100,'Internacional',10,'I');
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos_afi`
--

DROP TABLE IF EXISTS `eventos_afi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos_afi` (
  `EveAfiCod` int NOT NULL,
  `EveAfiPos` int DEFAULT NULL,
  `EveAfiEveCod` int NOT NULL,
  `AveAfiAfiCod` int NOT NULL,
  `EveAfiTroCod` int NOT NULL,
  `EveAfiEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`EveAfiCod`),
  KEY `Relationship39` (`EveAfiEveCod`),
  KEY `Relationship40` (`AveAfiAfiCod`),
  KEY `Relationship41` (`EveAfiTroCod`),
  KEY `Relationship63` (`EveAfiEstReg`),
  CONSTRAINT `Relationship39` FOREIGN KEY (`EveAfiEveCod`) REFERENCES `eventos` (`EveCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship40` FOREIGN KEY (`AveAfiAfiCod`) REFERENCES `afiliados` (`AfiCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship41` FOREIGN KEY (`EveAfiTroCod`) REFERENCES `trofeos` (`TroCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship63` FOREIGN KEY (`EveAfiEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos_afi`
--

LOCK TABLES `eventos_afi` WRITE;
/*!40000 ALTER TABLE `eventos_afi` DISABLE KEYS */;
INSERT INTO `eventos_afi` VALUES (1,1,1,1,1,'A'),(2,2,1,2,2,'A');
/*!40000 ALTER TABLE `eventos_afi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licencias`
--

DROP TABLE IF EXISTS `licencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licencias` (
  `LicCod` int NOT NULL,
  `LicNom` varchar(40) DEFAULT NULL,
  `LicEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`LicCod`),
  KEY `Relationship56` (`LicEstReg`),
  CONSTRAINT `Relationship56` FOREIGN KEY (`LicEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencias`
--

LOCK TABLES `licencias` WRITE;
/*!40000 ALTER TABLE `licencias` DISABLE KEYS */;
INSERT INTO `licencias` VALUES (1,'General','A'),(2,'Especifica','A');
/*!40000 ALTER TABLE `licencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar_peces`
--

DROP TABLE IF EXISTS `lugar_peces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar_peces` (
  `LugPecCod` int NOT NULL,
  `LucPecPecCod` int NOT NULL,
  `LugPecLugCod` int NOT NULL,
  `EstRegCod` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`LugPecCod`),
  KEY `Relationship19` (`LugPecLugCod`),
  KEY `Relationship22` (`LucPecPecCod`),
  KEY `Relationship58` (`EstRegCod`),
  CONSTRAINT `Relationship19` FOREIGN KEY (`LugPecLugCod`) REFERENCES `lugares` (`LugCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship22` FOREIGN KEY (`LucPecPecCod`) REFERENCES `peces` (`PecCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship58` FOREIGN KEY (`EstRegCod`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar_peces`
--

LOCK TABLES `lugar_peces` WRITE;
/*!40000 ALTER TABLE `lugar_peces` DISABLE KEYS */;
INSERT INTO `lugar_peces` VALUES (1,1,2,'A'),(2,2,1,'A'),(3,1,3,'A'),(4,2,4,'A'),(5,3,2,'A'),(6,4,3,'A'),(7,2,1,'A'),(8,5,4,'A'),(9,2,5,'A'),(10,3,2,'A'),(11,4,4,'A');
/*!40000 ALTER TABLE `lugar_peces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugares`
--

DROP TABLE IF EXISTS `lugares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugares` (
  `LugCod` int NOT NULL,
  `LugNom` varchar(40) DEFAULT NULL,
  `LugCom` varchar(40) DEFAULT NULL,
  `LugCau` int NOT NULL,
  `LugTip` int NOT NULL,
  `LugAco` int NOT NULL,
  `LugVedCod` int NOT NULL,
  `LugEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`LugCod`),
  KEY `Relationship11` (`LugCau`),
  KEY `Relationship12` (`LugTip`),
  KEY `Relationship13` (`LugAco`),
  KEY `Relationship36` (`LugVedCod`),
  KEY `Relationship53` (`LugEstReg`),
  CONSTRAINT `Relationship11` FOREIGN KEY (`LugCau`) REFERENCES `cauces` (`CauCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship12` FOREIGN KEY (`LugTip`) REFERENCES `lugares_tip` (`LugTipCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship13` FOREIGN KEY (`LugAco`) REFERENCES `acotado` (`AcoCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship36` FOREIGN KEY (`LugVedCod`) REFERENCES `veda` (`VedCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship53` FOREIGN KEY (`LugEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugares`
--

LOCK TABLES `lugares` WRITE;
/*!40000 ALTER TABLE `lugares` DISABLE KEYS */;
INSERT INTO `lugares` VALUES (1,'Lugar 1','Comunidad 1',1,1,0,5,'A'),(2,'Lugar 2','Comunidad 2',2,1,1,4,'A'),(3,'Lugar 3','Comunidad 3',3,2,0,2,'A'),(4,'Lugar 4','Comunidad 4',4,1,1,1,'A'),(5,'Lugar 5','Comunidad 5',3,1,0,3,'A'),(6,'Lugar 6','Comunidad 6',2,2,0,2,'A'),(7,'Lugar 7','Comunidad 7',5,1,0,1,'A'),(8,'Lugar 8','Comunidad 8',1,2,0,2,'A'),(9,'Lugar 9','Comunidad 9',2,3,0,5,'A'),(10,'Lugar 10','Comunidad 10',3,1,0,4,'A'),(11,'Lugar 11','Comunidad 11',5,1,1,3,'A');
/*!40000 ALTER TABLE `lugares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugares_tip`
--

DROP TABLE IF EXISTS `lugares_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugares_tip` (
  `LugTipCod` int NOT NULL,
  `LugTipDes` varchar(40) DEFAULT NULL,
  `LugTipEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`LugTipCod`),
  KEY `Relationship44` (`LugTipEstReg`),
  CONSTRAINT `Relationship44` FOREIGN KEY (`LugTipEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugares_tip`
--

LOCK TABLES `lugares_tip` WRITE;
/*!40000 ALTER TABLE `lugares_tip` DISABLE KEYS */;
INSERT INTO `lugares_tip` VALUES (1,'Es Bonito','A'),(2,'Es Peligroso','A'),(3,'Es Desafiante','A'),(4,'Es Frondoso','A'),(5,'Es Bonito','A'),(6,'Es Desafiante','A'),(7,'Es Reluciente','A');
/*!40000 ALTER TABLE `lugares_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `PaiCod` int NOT NULL,
  `PaiDes` varbinary(40) DEFAULT NULL,
  `PaiEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`PaiCod`),
  KEY `Relationship48` (`PaiEstReg`),
  CONSTRAINT `Relationship48` FOREIGN KEY (`PaiEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,_binary 'Peru','A'),(2,_binary 'Chile','A'),(3,_binary 'Rumania','A'),(4,_binary 'Brasil','E'),(5,_binary 'Noruega','A'),(6,_binary 'Estocolmo','A');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peces`
--

DROP TABLE IF EXISTS `peces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peces` (
  `PecCod` int NOT NULL,
  `PecNom` varchar(40) DEFAULT NULL,
  `PecPueCap` int NOT NULL,
  `PecDes` varchar(40) DEFAULT NULL,
  `PecEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`PecCod`),
  KEY `Relationship14` (`PecPueCap`),
  KEY `Relationship52` (`PecEstReg`),
  CONSTRAINT `Relationship14` FOREIGN KEY (`PecPueCap`) REFERENCES `puede_cap` (`PueCapCod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Relationship52` FOREIGN KEY (`PecEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peces`
--

LOCK TABLES `peces` WRITE;
/*!40000 ALTER TABLE `peces` DISABLE KEYS */;
INSERT INTO `peces` VALUES (1,'Bonito',0,'Un pez interesante','A'),(2,'Trucha',1,'Un pez no tan interesante','A'),(3,'Sonriente',0,'Un pez del mal','A'),(4,'Atun',1,'Un pez','A'),(5,'Piraña',1,'Un pez peligroso','A'),(6,'Pez Espada',1,'Muy raro','A'),(7,'El dorado',1,'Muy dorado','A'),(8,'El tiburon',1,'Es muy peligroso','A'),(9,'Diamante',1,'Brilla como el Rubi','A'),(10,'Lienzo',0,'Un pez inexistente','A');
/*!40000 ALTER TABLE `peces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puede_cap`
--

DROP TABLE IF EXISTS `puede_cap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puede_cap` (
  `PueCapCod` int NOT NULL,
  `PueCapDes` varchar(40) DEFAULT NULL,
  `PueCapEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`PueCapCod`),
  KEY `Relationship42` (`PueCapEstReg`),
  CONSTRAINT `Relationship42` FOREIGN KEY (`PueCapEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puede_cap`
--

LOCK TABLES `puede_cap` WRITE;
/*!40000 ALTER TABLE `puede_cap` DISABLE KEYS */;
INSERT INTO `puede_cap` VALUES (0,'No Capturar','A'),(1,'Puede Capturar','A');
/*!40000 ALTER TABLE `puede_cap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trofeos`
--

DROP TABLE IF EXISTS `trofeos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trofeos` (
  `TroCod` int NOT NULL,
  `TroDes` varchar(40) DEFAULT NULL,
  `TroEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`TroCod`),
  KEY `Relationship61` (`TroEstReg`),
  CONSTRAINT `Relationship61` FOREIGN KEY (`TroEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trofeos`
--

LOCK TABLES `trofeos` WRITE;
/*!40000 ALTER TABLE `trofeos` DISABLE KEYS */;
INSERT INTO `trofeos` VALUES (1,'Oro','A'),(2,'Plata','A'),(3,'Bronce','A'),(4,'Participo','A');
/*!40000 ALTER TABLE `trofeos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `UsuCod` int NOT NULL,
  `UsuUsu` varchar(40) DEFAULT NULL,
  `UsuCon` varchar(40) DEFAULT NULL,
  `UsuTip` char(1) DEFAULT 'N',
  `UsuEstReg` char(1) DEFAULT 'A',
  PRIMARY KEY (`UsuCod`),
  KEY `IX_Relationship68` (`UsuEstReg`),
  CONSTRAINT `Relationship68` FOREIGN KEY (`UsuEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'The','123','M','A');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veda`
--

DROP TABLE IF EXISTS `veda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veda` (
  `VedCod` int NOT NULL,
  `VedPecCod` int DEFAULT NULL,
  `VecTalMax` decimal(4,2) DEFAULT NULL,
  `VecTalMin` decimal(4,2) DEFAULT NULL,
  `VecPesMax` decimal(4,2) DEFAULT NULL,
  `VecPesMin` decimal(4,2) DEFAULT NULL,
  `VedNumMax` int DEFAULT NULL,
  `VedNumMin` int DEFAULT NULL,
  `VedInf` varchar(40) DEFAULT NULL,
  `VedEstReg` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`VedCod`),
  KEY `Relationship46` (`VedEstReg`),
  CONSTRAINT `Relationship46` FOREIGN KEY (`VedEstReg`) REFERENCES `estado_registro` (`EstRegCod`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veda`
--

LOCK TABLES `veda` WRITE;
/*!40000 ALTER TABLE `veda` DISABLE KEYS */;
INSERT INTO `veda` VALUES (1,1,12.15,8.12,11.00,8.00,100,50,'esta en peligro de extincion','A'),(2,2,15.12,8.12,11.00,8.00,100,50,'No se ve muy amenudo','A'),(3,3,8.80,4.15,11.00,8.00,100,50,'Muchos los usan para comer','A'),(4,4,7.12,4.80,11.00,8.00,100,50,'Estos son venenosos','A'),(5,5,22.70,11.15,11.00,8.00,100,50,'En peligro de extincion','A');
/*!40000 ALTER TABLE `veda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-16  1:41:35
