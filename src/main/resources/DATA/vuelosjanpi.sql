CREATE DATABASE  IF NOT EXISTS `railway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `railway`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: viaduct.proxy.rlwy.net    Database: railway
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe8ddci9cyv26p143tumal79j2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES (1,'American Airlines'),(5,'British Airways'),(2,'Delta Air Lines'),(6,'LATAM Airlines'),(4,'Southwest Airlines'),(3,'United Airlines');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf583ieaw0ttnwklqy222tfru0` (`city_id`),
  CONSTRAINT `FKf583ieaw0ttnwklqy222tfru0` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'Aeropuerto Internacional El Dorado',2),(2,'Aeropuerto',11),(3,'Aeropuerto',12),(4,'Aeropuerto',13),(5,'Aeropuerto',14),(6,'Aeropuerto',2),(7,'Aeropuerto',8),(8,'Aeropuerto',9),(9,'Aeropuerto',9);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport_gate`
--

DROP TABLE IF EXISTS `airport_gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport_gate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gate` varchar(255) DEFAULT NULL,
  `airport_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKocamhbt13f8mkxfc31329h57n` (`airport_id`),
  CONSTRAINT `FKocamhbt13f8mkxfc31329h57n` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport_gate`
--

LOCK TABLES `airport_gate` WRITE;
/*!40000 ALTER TABLE `airport_gate` DISABLE KEYS */;
/*!40000 ALTER TABLE `airport_gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpd7j1p7yxr784adkx4pyepba` (`country_id`),
  CONSTRAINT `FKrpd7j1p7yxr784adkx4pyepba` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Bogota',1),(2,'Medellin',1),(3,'Miami',3),(4,'Pekin',2),(5,'Mexico DF',5),(6,'Vinia Mar',7),(7,'Brasilia',8),(8,'Cali',1),(9,'Cartagena',1),(10,'Barranquilla',1),(11,'Buenos Aires',11),(12,'São Paulo',8),(13,'Santiago',12),(14,'Lima',13),(15,'Nueva York',3),(16,'Madrid',14),(17,'París',15),(18,'Londres',16),(19,'Frankfurt',17),(20,'Tokio',18);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Colombia'),(2,'China'),(3,'United States'),(4,'Venezuela'),(5,'Mexico'),(6,'Pakistan'),(7,'Chile'),(8,'Brasil'),(9,'Germany'),(10,'France'),(11,'Argentina'),(12,'Chile'),(13,'Perú'),(14,'España'),(15,'Francia'),(16,'Reino Unido'),(17,'Alemania'),(18,'Japón');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_role`
--

DROP TABLE IF EXISTS `crew_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_role`
--

LOCK TABLES `crew_role` WRITE;
/*!40000 ALTER TABLE `crew_role` DISABLE KEYS */;
INSERT INTO `crew_role` VALUES (1,'Piloto'),(2,'Copiloto '),(3,'Controlador de Tráfico Aéreo'),(4,'Administrador'),(5,'Despachador de Vuelo'),(6,'Auxiliar de Vuelo'),(7,'Jefe de Cabina'),(8,'Ingeniero de Mantenimiento');
/*!40000 ALTER TABLE `crew_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `age` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `document_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsnintmqkiuk1ue013x4eb955o` (`document_type_id`),
  CONSTRAINT `FKsnintmqkiuk1ue013x4eb955o` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type`
--

DROP TABLE IF EXISTS `document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type`
--

LOCK TABLES `document_type` WRITE;
/*!40000 ALTER TABLE `document_type` DISABLE KEYS */;
INSERT INTO `document_type` VALUES (1,'Social Security Card'),(2,'Permanent Resident Card'),(3,'Passport'),(4,'Driver\'s License'),(5,'National ID Number'),(6,'Visa');
/*!40000 ALTER TABLE `document_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` varchar(255) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `airline_id` bigint DEFAULT NULL,
  `airport_id` bigint DEFAULT NULL,
  `rol_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtmqwv9yl1irua2jj16ulg5yx` (`airline_id`),
  KEY `FKk47ustgp3236kn909gn745f08` (`airport_id`),
  KEY `FKtiix6233jauxq105q6ardl4ub` (`rol_id`),
  CONSTRAINT `FKjtmqwv9yl1irua2jj16ulg5yx` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`),
  CONSTRAINT `FKk47ustgp3236kn909gn745f08` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKtiix6233jauxq105q6ardl4ub` FOREIGN KEY (`rol_id`) REFERENCES `crew_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_connection`
--

DROP TABLE IF EXISTS `flight_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_connection` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `connection_number` varchar(255) DEFAULT NULL,
  `destination_airport_id` bigint DEFAULT NULL,
  `origin_airport_id` bigint DEFAULT NULL,
  `plane_id` bigint DEFAULT NULL,
  `trip_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpt1wr29pdcwss5tkhkdgqvouo` (`destination_airport_id`),
  KEY `FKtqb9f1fqdm4re84dfs2kwlpmu` (`origin_airport_id`),
  KEY `FKnbcv33iia5eg73xkq6asyac76` (`plane_id`),
  KEY `FK8xc7cp273agjux07vemqcv7yc` (`trip_id`),
  CONSTRAINT `FK8xc7cp273agjux07vemqcv7yc` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FKnbcv33iia5eg73xkq6asyac76` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`),
  CONSTRAINT `FKpt1wr29pdcwss5tkhkdgqvouo` FOREIGN KEY (`destination_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKtqb9f1fqdm4re84dfs2kwlpmu` FOREIGN KEY (`origin_airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_connection`
--

LOCK TABLES `flight_connection` WRITE;
/*!40000 ALTER TABLE `flight_connection` DISABLE KEYS */;
INSERT INTO `flight_connection` VALUES (1,'Bogota-Medellin',1,NULL,4,2);
/*!40000 ALTER TABLE `flight_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_fare`
--

DROP TABLE IF EXISTS `flight_fare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_fare` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_fare`
--

LOCK TABLES `flight_fare` WRITE;
/*!40000 ALTER TABLE `flight_fare` DISABLE KEYS */;
/*!40000 ALTER TABLE `flight_fare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `card_number` bigint DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `payment_method_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKby2skjf3ov608yb6nm16b49lg` (`customer_id`),
  KEY `FKjii2n6db6cje3km5ybsbgo8cl` (`payment_method_id`),
  CONSTRAINT `FKby2skjf3ov608yb6nm16b49lg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKjii2n6db6cje3km5ybsbgo8cl` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_revision_employee`
--

DROP TABLE IF EXISTS `plan_revision_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_revision_employee` (
  `employee_id` varchar(255) NOT NULL,
  `plan_revision_id` bigint NOT NULL,
  PRIMARY KEY (`employee_id`,`plan_revision_id`),
  KEY `FKcbpnnw1ijdbg8pjan2rtsyo9o` (`plan_revision_id`),
  CONSTRAINT `FKbeuu81shyc68xs0jjkcmja54x` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKcbpnnw1ijdbg8pjan2rtsyo9o` FOREIGN KEY (`plan_revision_id`) REFERENCES `plane_revision` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_revision_employee`
--

LOCK TABLES `plan_revision_employee` WRITE;
/*!40000 ALTER TABLE `plan_revision_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_revision_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int DEFAULT NULL,
  `fabrication_date` date DEFAULT NULL,
  `plate` varchar(255) DEFAULT NULL,
  `model_id` bigint DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp8t23erg8p54qxigg2imrq800` (`plate`),
  KEY `FKikld1lubimd88d2sk3kv8ekyo` (`model_id`),
  KEY `FKkymgc2clwif4m8v0507cwi857` (`status_id`),
  CONSTRAINT `FKikld1lubimd88d2sk3kv8ekyo` FOREIGN KEY (`model_id`) REFERENCES `plane_model` (`id`),
  CONSTRAINT `FKkymgc2clwif4m8v0507cwi857` FOREIGN KEY (`status_id`) REFERENCES `plane_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,250,'2023-05-12','HK-5393',1,1),(2,255,'2005-01-01','F-WWOW',1,8),(3,364,'1995-04-25','N777UA',5,6),(4,303,'2015-01-18','CC-BGA',6,1);
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane_manufacturer`
--

DROP TABLE IF EXISTS `plane_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_manufacturer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_manufacturer`
--

LOCK TABLES `plane_manufacturer` WRITE;
/*!40000 ALTER TABLE `plane_manufacturer` DISABLE KEYS */;
INSERT INTO `plane_manufacturer` VALUES (1,'Boeing'),(2,'Airbus'),(3,'Embraer'),(4,'Bombardier'),(5,'Lockheed Martin'),(6,'COMAC'),(7,'Tupolev'),(8,'Sukhoi ');
/*!40000 ALTER TABLE `plane_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane_model`
--

DROP TABLE IF EXISTS `plane_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `plane_manufacturer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9t83d0i5eb40yp5e3nhy1ylhv` (`plane_manufacturer_id`),
  CONSTRAINT `FK9t83d0i5eb40yp5e3nhy1ylhv` FOREIGN KEY (`plane_manufacturer_id`) REFERENCES `plane_manufacturer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_model`
--

LOCK TABLES `plane_model` WRITE;
/*!40000 ALTER TABLE `plane_model` DISABLE KEYS */;
INSERT INTO `plane_model` VALUES (1,'A380',2),(2,'A320',2),(3,'A350',2),(4,'737',1),(5,'777',1),(6,'787',1);
/*!40000 ALTER TABLE `plane_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane_revision`
--

DROP TABLE IF EXISTS `plane_revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_revision` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `revision_date` date DEFAULT NULL,
  `plane_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK177xbrhtvm9eborv7e7maruar` (`plane_id`),
  CONSTRAINT `FK177xbrhtvm9eborv7e7maruar` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_revision`
--

LOCK TABLES `plane_revision` WRITE;
/*!40000 ALTER TABLE `plane_revision` DISABLE KEYS */;
/*!40000 ALTER TABLE `plane_revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane_revision_employee`
--

DROP TABLE IF EXISTS `plane_revision_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_revision_employee` (
  `employee_id` varchar(255) NOT NULL,
  `plan_revision_id` bigint NOT NULL,
  PRIMARY KEY (`employee_id`,`plan_revision_id`),
  KEY `FKd906racfna74e54ngq9vma5ev` (`plan_revision_id`),
  CONSTRAINT `FK54wmwr80snx8yb4lejunxxbt5` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKd906racfna74e54ngq9vma5ev` FOREIGN KEY (`plan_revision_id`) REFERENCES `plane_revision` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_revision_employee`
--

LOCK TABLES `plane_revision_employee` WRITE;
/*!40000 ALTER TABLE `plane_revision_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `plane_revision_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane_status`
--

DROP TABLE IF EXISTS `plane_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_status`
--

LOCK TABLES `plane_status` WRITE;
/*!40000 ALTER TABLE `plane_status` DISABLE KEYS */;
INSERT INTO `plane_status` VALUES (1,'Operational '),(2,'Grounded '),(3,'In Maintenance'),(4,'Under Inspection'),(5,'On Standby'),(6,'In Flight'),(7,'Delayed'),(8,'Parked'),(9,'Under De-icing'),(10,'Awaiting Clearance');
/*!40000 ALTER TABLE `plane_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_available` bit(1) NOT NULL,
  `seat_class` varchar(255) DEFAULT NULL,
  `seat_number` varchar(255) DEFAULT NULL,
  `flight_connection_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeucu03hbglyc2b7yqimcwdg5m` (`flight_connection_id`),
  CONSTRAINT `FKeucu03hbglyc2b7yqimcwdg5m` FOREIGN KEY (`flight_connection_id`) REFERENCES `flight_connection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK74y7xiqrvp39wycn0ron4xq4h` (`username`),
  CONSTRAINT `system_user_chk_1` CHECK ((`role` between 0 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,'1234',0,'Jp'),(2,'1324',0,'Balti'),(3,'123',1,'Sebastian');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `trip_date` date DEFAULT NULL,
  `trip_price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'2024-05-19',123.2),(2,'2024-07-01',200);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_booking`
--

DROP TABLE IF EXISTS `trip_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `trip_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1trqscm247gbxaita85gcdh72` (`trip_id`),
  CONSTRAINT `FK1trqscm247gbxaita85gcdh72` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_booking`
--

LOCK TABLES `trip_booking` WRITE;
/*!40000 ALTER TABLE `trip_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_booking_detail`
--

DROP TABLE IF EXISTS `trip_booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_booking_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) DEFAULT NULL,
  `flight_fare_id` bigint DEFAULT NULL,
  `trip_booking_id` bigint DEFAULT NULL,
  `seat_number` int NOT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK56ixu47flelsw7umgl915fhtd` (`customer_id`),
  KEY `FKrooqqup8j1214lhu5srcf5xd9` (`flight_fare_id`),
  KEY `FKik2jhb7inin5tio9l5x2er246` (`trip_booking_id`),
  KEY `FK2oguc5mg4n3da3pfk2ei81e7u` (`payment_id`),
  CONSTRAINT `FK2oguc5mg4n3da3pfk2ei81e7u` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FK56ixu47flelsw7umgl915fhtd` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKik2jhb7inin5tio9l5x2er246` FOREIGN KEY (`trip_booking_id`) REFERENCES `trip_booking` (`id`),
  CONSTRAINT `FKrooqqup8j1214lhu5srcf5xd9` FOREIGN KEY (`flight_fare_id`) REFERENCES `flight_fare` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_booking_detail`
--

LOCK TABLES `trip_booking_detail` WRITE;
/*!40000 ALTER TABLE `trip_booking_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip_booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_booking_trip_booking_details`
--

DROP TABLE IF EXISTS `trip_booking_trip_booking_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_booking_trip_booking_details` (
  `trip_booking_id` bigint NOT NULL,
  `trip_booking_details_id` bigint NOT NULL,
  UNIQUE KEY `UK9bg2x4dnqvpxj9o0iv9h21o4o` (`trip_booking_details_id`),
  KEY `FK8skknyvc7e3b4it7b6bdhqm3b` (`trip_booking_id`),
  CONSTRAINT `FK8skknyvc7e3b4it7b6bdhqm3b` FOREIGN KEY (`trip_booking_id`) REFERENCES `trip_booking` (`id`),
  CONSTRAINT `FKbtk2vbwcs0ic24bn0m6ny2ade` FOREIGN KEY (`trip_booking_details_id`) REFERENCES `trip_booking_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_booking_trip_booking_details`
--

LOCK TABLES `trip_booking_trip_booking_details` WRITE;
/*!40000 ALTER TABLE `trip_booking_trip_booking_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip_booking_trip_booking_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_crew`
--

DROP TABLE IF EXISTS `trip_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_crew` (
  `employee_id` varchar(255) NOT NULL,
  `flight_connection_id` bigint NOT NULL,
  PRIMARY KEY (`employee_id`,`flight_connection_id`),
  KEY `FK5k73s8nv1944bel3wt7iq0vsq` (`flight_connection_id`),
  CONSTRAINT `FK31utfclv462g0e8oh1wye9j37` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK5k73s8nv1944bel3wt7iq0vsq` FOREIGN KEY (`flight_connection_id`) REFERENCES `flight_connection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_crew`
--

LOCK TABLES `trip_crew` WRITE;
/*!40000 ALTER TABLE `trip_crew` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'railway'
--

--
-- Dumping routines for database 'railway'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-30  8:03:00
