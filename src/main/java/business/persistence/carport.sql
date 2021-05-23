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
INSERT INTO `cladding` VALUES (1,1041);
/*!40000 ALTER TABLE `cladding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
                            `material_id` int NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1001,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',14.1,1,100,2100,19),(1002,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',16.11,1,100,2400,19),(1003,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',18.12,1,100,2700,19),(1004,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',20.14,1,100,3000,19),(1005,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',22.15,1,100,3300,19),(1006,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',24.17,1,100,3600,19),(1007,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',26.18,1,100,3900,19),(1008,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',28.19,1,100,4200,19),(1009,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',30.21,1,100,4500,19),(1010,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',32.22,1,100,4800,19),(1011,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',34.23,1,100,5100,19),(1012,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',36.25,1,100,5400,19),(1013,'19x100 mm. trykimp. brædt','Vandbrædt på stern i siderne',38.26,1,100,5700,19),(1021,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',14.1,1,100,2100,19),(1022,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',16.11,1,100,2400,19),(1023,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',18.12,1,100,2700,19),(1024,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',20.14,1,100,3000,19),(1025,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',22.15,1,100,3300,19),(1026,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',24.17,1,100,3600,19),(1027,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',26.18,1,100,3900,19),(1028,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',28.19,1,100,4200,19),(1029,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',30.21,1,100,4500,19),(1030,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',32.22,1,100,4800,19),(1031,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',34.23,1,100,5100,19),(1032,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',36.25,1,100,5400,19),(1033,'19x100 mm. trykimp. brædt','Vandbrædt på stern i forende',38.26,1,100,5700,19),(1041,'19x100 mm. trykimp. brædt','Til beklædning af skur',14.1,1,100,2100,19),(1042,'19x100 mm. trykimp. brædt','Til beklædning af skur',16.11,1,100,2400,19),(1043,'19x100 mm. trykimp. brædt','Til beklædning af skur',18.12,1,100,2700,19),(1044,'19x100 mm. trykimp. brædt','Til beklædning af skur',20.14,1,100,3000,19),(1045,'19x100 mm. trykimp. brædt','Til beklædning af skur',22.15,1,100,3300,19),(1046,'19x100 mm. trykimp. brædt','Til beklædning af skur',24.17,1,100,3600,19),(1047,'19x100 mm. trykimp. brædt','Til beklædning af skur',26.18,1,100,3900,19),(1048,'19x100 mm. trykimp. brædt','Til beklædning af skur',28.19,1,100,4200,19),(1049,'19x100 mm. trykimp. brædt','Til beklædning af skur',30.21,1,100,4500,19),(1050,'19x100 mm. trykimp. brædt','Til beklædning af skur',32.22,1,100,4800,19),(1051,'19x100 mm. trykimp. brædt','Til beklædning af skur',34.23,1,100,5100,19),(1052,'19x100 mm. trykimp. brædt','Til beklædning af skur',36.25,1,100,5400,19),(1053,'19x100 mm. trykimp. brædt','Til beklædning af skur',38.26,1,100,5700,19),(1101,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',41.66,1,125,2100,25),(1102,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',47.61,1,125,2400,25),(1103,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',53.56,1,125,2700,25),(1104,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',65.46,1,125,3000,25),(1105,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',71.42,1,125,3300,25),(1106,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',77.37,1,125,3600,25),(1107,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',83.32,1,125,3900,25),(1108,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',89.27,1,125,4200,25),(1109,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',95.22,1,125,4500,25),(1110,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',101.17,1,125,4800,25),(1111,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',107.12,1,125,5100,25),(1112,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',113.07,1,125,5400,25),(1113,'25x125 mm. trykimp. brædt','Oversternbrædder til forende',119.03,1,125,5700,25),(1121,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',41.66,1,125,2100,25),(1122,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',47.61,1,125,2400,25),(1123,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',53.56,1,125,2700,25),(1124,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',65.46,1,125,3000,25),(1125,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',71.42,1,125,3300,25),(1126,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',77.37,1,125,3600,25),(1127,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',83.32,1,125,3900,25),(1128,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',89.27,1,125,4200,25),(1129,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',95.22,1,125,4500,25),(1130,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',101.17,1,125,4800,25),(1131,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',107.12,1,125,5100,25),(1132,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',113.07,1,125,5400,25),(1133,'25x125 mm. trykimp. brædt','Oversternbrædder til siderne',119.03,1,125,5700,25),(1201,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',102.3,1,200,2100,25),(1202,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',116.91,1,200,2400,25),(1203,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',131.52,1,200,2700,25),(1204,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',146.14,1,200,3000,25),(1205,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',160.75,1,200,3300,25),(1206,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',175.37,1,200,3600,25),(1207,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',189.98,1,200,3900,25),(1208,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',204.59,1,200,4200,25),(1209,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',219.21,1,200,4500,25),(1210,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',233.82,1,200,4800,25),(1211,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',248.43,1,200,5100,25),(1212,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',263.05,1,200,5400,25),(1213,'25x200 mm. trykimp. brædt','Understernbrædder til siderne',277.66,1,200,5700,25),(1221,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',102.3,1,200,2100,25),(1222,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',116.91,1,200,2400,25),(1223,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',131.52,1,200,2700,25),(1224,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',146.14,1,200,3000,25),(1225,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',160.75,1,200,3300,25),(1226,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',175.37,1,200,3600,25),(1227,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',189.98,1,200,3900,25),(1228,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',204.59,1,200,4200,25),(1229,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',219.21,1,200,4500,25),(1230,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',233.82,1,200,4800,25),(1231,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',248.43,1,200,5100,25),(1232,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',263.05,1,200,5400,25),(1233,'25x200 mm. trykimp. brædt','Understernbrædder til for- & bagende',277.66,1,200,5700,25),(1308,'38x73 mm. lægte ubh.','Til Z på bagside af dør ',39.38,1,73,4200,38),(1402,'45x95 mm. reglar ubh.','Løsholter til skur gavle',37.64,1,95,2400,45),(1403,'45x95 mm. reglar ubh.','Løsholter til skur gavle',43.02,1,95,2700,45),(1404,'45x95 mm. reglar ubh.','Løsholter til skur gavle',48.4,1,95,3000,45),(1405,'45x95 mm. reglar ubh.','Løsholter til skur gavle',53.78,1,95,3300,45),(1406,'45x95 mm. reglar ubh.','Løsholter til skur gavle',59.15,1,95,3600,45),(1407,'45x95 mm. reglar ubh.','Løsholter til skur gavle',64.53,1,95,3900,45),(1408,'45x95 mm. reglar ubh.','Løsholter til skur gavle',75.29,1,95,4200,45),(1409,'45x95 mm. reglar ubh.','Løsholter til skur gavle',80.66,1,95,4500,45),(1410,'45x95 mm. reglar ubh.','Løsholter til skur gavle',86.04,1,95,4800,45),(1411,'45x95 mm. reglar ubh.','Løsholter til skur gavle',91.42,1,95,5100,45),(1422,'45x95 mm. reglar ubh.','Løsholter til skur sider',37.64,1,95,2400,45),(1423,'45x95 mm. reglar ubh.','Løsholter til skur sider',43.02,1,95,2700,45),(1424,'45x95 mm. reglar ubh.','Løsholter til skur sider',48.4,1,95,3000,45),(1425,'45x95 mm. reglar ubh.','Løsholter til skur sider',53.78,1,95,3300,45),(1426,'45x95 mm. reglar ubh.','Løsholter til skur sider',59.15,1,95,3600,45),(1427,'45x95 mm. reglar ubh.','Løsholter til skur sider',64.53,1,95,3900,45),(1428,'45x95 mm. reglar ubh.','Løsholter til skur sider',75.29,1,95,4200,45),(1429,'45x95 mm. reglar ubh.','Løsholter til skur sider',80.66,1,95,4500,45),(1430,'45x95 mm. reglar ubh.','Løsholter til skur sider',86.04,1,95,4800,45),(1431,'45x95 mm. reglar ubh.','Løsholter til skur sider',91.42,1,95,5100,45),(1506,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',189,1,195,3600,45),(1507,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',204.75,1,195,3900,45),(1508,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',220.5,1,195,4200,45),(1509,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',236.25,1,195,4500,45),(1510,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',252,1,195,4800,45),(1511,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',267.75,1,195,5100,45),(1512,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',283.5,1,195,5400,45),(1513,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',299.25,1,195,5700,45),(1514,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper',315,1,195,6000,45),(1526,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',189,1,195,3600,45),(1527,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',204.75,1,195,3900,45),(1528,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',220.5,1,195,4200,45),(1529,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',236.25,1,195,4500,45),(1530,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',252,1,195,4800,45),(1531,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',267.75,1,195,5100,45),(1532,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',283.5,1,195,5400,45),(1533,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',299.25,1,195,5700,45),(1534,'45x195 mm. spærtræ ubh.','Spær, monteres på rem',315,1,195,6000,45),(1546,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',189,1,195,3600,45),(1547,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',204.75,1,195,3900,45),(1548,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',220.5,1,195,4200,45),(1549,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',236.25,1,195,4500,45),(1550,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',252,1,195,4800,45),(1551,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',267.75,1,195,5100,45),(1552,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',283.5,1,195,5400,45),(1553,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',299.25,1,195,5700,45),(1554,'45x195 mm. spærtræ ubh.','Remme i sider, sadles ned i stolper (skur del, deles)',315,1,195,6000,45),(1601,'97x97 mm. trykimp. stolpe','Stolper nedgraves 90cm. i jord',112.5,1,97,3000,97),(2001,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',63.71,1,1090,2400,NULL),(2003,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',97.5,1,1090,3600,NULL),(2004,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',112.5,1,1090,4200,NULL),(2005,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',127.5,1,1090,4800,NULL),(2006,'Plastmo Ecolite blåtonet','Tagplader monteres på spær',187.5,1,1090,6000,NULL),(3001,'Vinkelbeslag 35','Til montering af løsholter i skur',5.21,1,NULL,NULL,NULL),(3011,'Universal 190 mm venstre ','Til montering af spær på rem',35.21,1,NULL,NULL,NULL),(3012,'Universal 190 mm højre','Til montering af spær på rem',34.46,1,NULL,NULL,NULL),(3021,'T-hængsel 390 mm','Til skurdør',90.71,1,NULL,NULL,NULL),(3022,'Stalddørsgreb 50x75','Til lås på dør i skur',105,4,NULL,NULL,NULL),(3051,'4,5x50 mm. skruer 300 stk.','Til montering af inderste beklædning',60,2,NULL,NULL,NULL),(3056,'4,5x60 mm. skruer 200 stk.','Til montering af stern- & vandbrædt',37.46,2,NULL,NULL,NULL),(3061,'4,5x70 mm. skruer 400 stk.','Til montering af yderste beklædning',105,2,NULL,NULL,NULL),(3071,'Firkantskiver 40x40x11 mm.','Til montering af rem på stolper',6,1,NULL,NULL,NULL),(3076,'4,0x50 mm. beslagskruer 250 stk.','Til montering af universalbeslag + hulbånd',74.25,2,NULL,NULL,NULL),(3081,'Bræddebolt 10x120 mm.','Til montering af rem på stolper',11.25,1,NULL,NULL,NULL),(3101,'Hulbånd 1x20 mm. 10 mtr.','Til vindkryds på spær',150,3,NULL,NULL,NULL),(3201,'Plastmo bundskruer 200 stk. ','Skruer til tagplader',306.75,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `order_id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int NOT NULL,
                         `status_id` int NOT NULL DEFAULT '1',
                         `price` double NOT NULL DEFAULT '0',
                         `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `time_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `carport_length` int NOT NULL,
                         `carport_width` int NOT NULL,
                         `cladding_id` int DEFAULT NULL,
                         `roofing_id` int DEFAULT NULL,
                         `shed_width` int DEFAULT NULL,
                         `shed_length` int DEFAULT NULL,
                         `svg` varchar(12000) DEFAULT NULL,
                         `bom` varchar(1500) DEFAULT NULL,
                         PRIMARY KEY (`order_id`),
                         KEY `fk_order_cladding1_idx` (`cladding_id`),
                         KEY `fk_order_roofing1_idx` (`roofing_id`),
                         KEY `fk_order_status1_idx` (`status_id`),
                         KEY `fk_order_user1_idx` (`user_id`),
                         CONSTRAINT `fk_order_cladding1` FOREIGN KEY (`cladding_id`) REFERENCES `cladding` (`cladding_id`),
                         CONSTRAINT `fk_order_roofing1` FOREIGN KEY (`roofing_id`) REFERENCES `roofing` (`roof_id`),
                         CONSTRAINT `fk_order_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
                         CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


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
INSERT INTO `roofing` VALUES (1,2001);
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
INSERT INTO `status` VALUES (1,'Forespørgsel'),(2,'Tilbud'),(3,'Tilbud accepteret'),(4,'Faktura'),(5,'Betalt'),(99,'Annulleret');
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
                        `phone_no` varchar(20) NOT NULL,
                        `email` varchar(45) NOT NULL UNIQUE,
                        `password` varchar(45) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        KEY `fk_user_role1_idx` (`role_id`),
                        KEY `fk_user_postal_code1_idx` (`postal_code`),
                        CONSTRAINT `fk_user_postal_code1` FOREIGN KEY (`postal_code`) REFERENCES `postal_code` (`postal_code`),
                        CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'Ansat','Adminstratorgade 1',3700,'12345678','a@a.dk','a'),(2,1,'Kunde','Kundegade 1',3720,'10203040','q@q.dk','q');
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

-- Dump completed on 2021-05-19 16:20:35
