CREATE DATABASE  IF NOT EXISTS `carport` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `cladding`
--

DROP TABLE IF EXISTS `cladding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cladding` (
  `cladding_id` int NOT NULL,
  `material_id` int NOT NULL,
  PRIMARY KEY (`cladding_id`),
  KEY `fk_cladding_material1_idx` (`material_id`),
  CONSTRAINT `fk_cladding_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cladding`
--

LOCK TABLES `cladding` WRITE;
/*!40000 ALTER TABLE `cladding` DISABLE KEYS */;
INSERT INTO `cladding` VALUES (1,1067);
/*!40000 ALTER TABLE `cladding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(90) NOT NULL,
  `price` double DEFAULT NULL,
  `unit_id` int NOT NULL,
  `width` int DEFAULT NULL,
  `length` int DEFAULT NULL,
  `height` int DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  KEY `fk_material_unit_idx` (`unit_id`),
  CONSTRAINT `fk_material_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1085 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1056,'25x200 mm. trykimp. brædt','Understernbrædder til for & bag ende',0,1,200,3600,25),(1057,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',0,1,200,5400,25),(1058,'25x125 mm. trykimp. brædt','Oversternbrædder til forenden',0,1,125,3600,25),(1059,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',0,1,125,5400,25),(1060,'38x73 mm. lægte ubh.','Til Z på bagside af dør ',0,1,73,4200,38),(1061,'45x95 mm. reglar ubh.','Løsholter til skur gavle',0,1,95,2700,45),(1062,'45x95 mm. reglar ubh.','Løsholter til skur sider',0,1,95,2400,45),(1063,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',0,1,195,6000,45),(1064,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',0,1,195,4800,45),(1065,'45x195 mm. spærtræ ubh.','Spær, monteres på rem        ',0,1,195,6000,45),(1066,'97x97 mm. trykimp. stolpe','Stolper nedgraves 90cm. i jord',0,1,97,3000,97),(1067,'19x100 mm. trykimp. brædt                ','Til beklædning af skur 1 på 2',0,1,100,2100,19),(1068,'19x100 mm. trykimp. brædt                ','Vandbrædt på stern i sider',0,1,100,5400,19),(1069,'19x100 mm. trykimp. brædt                ','Vandbrædt på stern i forende',0,1,100,3600,19),(1070,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',0,1,NULL,NULL,NULL),(1071,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',0,1,NULL,NULL,NULL),(1072,'Plastmo bundskruer 200 stk. ','Skruer til tagplader',0,2,NULL,NULL,NULL),(1073,'Hulbånd 1x20 mm. 10 mtr.','Til vindkryds på spær',0,3,NULL,NULL,NULL),(1074,'Universal 190 mm højre','Til montering af spær på rem',0,1,NULL,NULL,NULL),(1075,'Universal 190 mm venstre ','Til montering af spær på rem',0,1,NULL,NULL,NULL),(1076,'4,5x60 mm. skruer 200 stk.','Til montering af stern&vandbrædt',0,2,NULL,NULL,NULL),(1077,'4,0x50 mm. beslagskruer 250 stk.','Til montering af universalbeslag + hulbånd',0,2,NULL,NULL,NULL),(1078,'Bræddebolt 10x120 mm.','Til montering af rem på stolper',0,1,NULL,NULL,NULL),(1079,'Firkantskiver 40x40x11 mm.','Til montering af rem på stolper',0,1,NULL,NULL,NULL),(1080,'4,5x70 mm. skruer 400 stk.','Til montering af yderste beklædning',0,2,NULL,NULL,NULL),(1081,'4,5x50 mm. skruer 300 stk.','Til montering af inderste beklædning',0,2,NULL,NULL,NULL),(1082,'Stalddørsgreb 50x75','Til lås på dør i skur',0,4,NULL,NULL,NULL),(1083,'T-hængsel 390 mm','Til skurdør',0,1,NULL,NULL,NULL),(1084,'Vinkelbeslag 35','Til montering af løsholter i skur',0,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  `price` double NOT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_updated` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `carport_length` int NOT NULL,
  `carport_width` int NOT NULL,
  `cladding_id` int NOT NULL,
  `roofing_id` int NOT NULL,
  `shed_width` int DEFAULT NULL,
  `shed_length` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_cladding1_idx` (`cladding_id`),
  KEY `fk_order_roofing1_idx` (`roofing_id`),
  KEY `fk_order_status1_idx` (`status_id`),
  KEY `fk_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_order_cladding1` FOREIGN KEY (`cladding_id`) REFERENCES `cladding` (`cladding_id`),
  CONSTRAINT `fk_order_roofing1` FOREIGN KEY (`roofing_id`) REFERENCES `roofing` (`roof_id`),
  CONSTRAINT `fk_order_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postal_code`
--

DROP TABLE IF EXISTS `postal_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postal_code` (
  `postal_code` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`postal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postal_code`
--

LOCK TABLES `postal_code` WRITE;
/*!40000 ALTER TABLE `postal_code` DISABLE KEYS */;
INSERT INTO `postal_code` VALUES (3700,'Rønne'),(3720,'Aakirkeby'),(3730,'Nexø'),(3740,'Svaneke'),(3760,'Gudhjem'),(3770,'Allinge'),(3790,'Hasle');
/*!40000 ALTER TABLE `postal_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predefined_carport`
--

DROP TABLE IF EXISTS `predefined_carport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predefined_carport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` int DEFAULT NULL,
  `length` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predefined_carport`
--

LOCK TABLES `predefined_carport` WRITE;
/*!40000 ALTER TABLE `predefined_carport` DISABLE KEYS */;
INSERT INTO `predefined_carport` VALUES (1,240,240),(2,270,270),(3,300,300),(4,330,330),(5,360,360),(6,390,390),(7,420,420),(8,450,450),(9,480,480),(10,510,510),(11,540,540),(12,570,570),(13,600,600),(14,630,630),(15,660,660),(16,690,690),(17,720,720),(18,750,750),(19,NULL,780);
/*!40000 ALTER TABLE `predefined_carport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predefined_shed`
--

DROP TABLE IF EXISTS `predefined_shed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predefined_shed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` int DEFAULT NULL,
  `length` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predefined_shed`
--

LOCK TABLES `predefined_shed` WRITE;
/*!40000 ALTER TABLE `predefined_shed` DISABLE KEYS */;
INSERT INTO `predefined_shed` VALUES (37,NULL,150),(38,NULL,180),(39,210,210),(40,240,240),(41,270,270),(42,300,300),(43,330,330),(44,360,360),(45,390,390),(46,420,420),(47,450,450),(48,480,480),(49,510,510),(50,540,540),(51,570,570),(52,600,600),(53,630,630),(54,660,660),(55,690,690),(56,720,NULL);
/*!40000 ALTER TABLE `predefined_shed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'customer'),(2,'salesperson');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roofing`
--

DROP TABLE IF EXISTS `roofing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roofing` (
  `roof_id` int NOT NULL,
  `material_id` int NOT NULL,
  PRIMARY KEY (`roof_id`),
  KEY `fk_roofing_material1_idx` (`material_id`),
  CONSTRAINT `fk_roofing_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roofing`
--

LOCK TABLES `roofing` WRITE;
/*!40000 ALTER TABLE `roofing` DISABLE KEYS */;
INSERT INTO `roofing` VALUES (1,1070),(2,1071);
/*!40000 ALTER TABLE `roofing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'request'),(2,'offer'),(3,'order'),(4,'completed'),(5,'deleted');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `unit_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Stk.'),(2,'Pk.'),(3,'Rl.'),(4,'Sæt');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL DEFAULT '1',
  `name` varchar(90) NOT NULL,
  `address` varchar(90) NOT NULL,
  `postal_code` int NOT NULL,
  `city` varchar(45) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'Ansat','Adminstratorgade 1',3700,'Rønne','12345678','a@a.dk','a'),(2,1,'Kunde','Kundegade 1',3720,'Aakrikeby','10203040','q@q.dk','q');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-06 20:50:03
