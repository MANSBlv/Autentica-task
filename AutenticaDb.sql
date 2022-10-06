-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: autentica
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `added_items`
--

DROP TABLE IF EXISTS `added_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `added_items` (
  `added_items_id` bigint NOT NULL,
  `ordered_date` datetime(6) DEFAULT NULL,
  `reason` varchar(255) NOT NULL,
  `status` int DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`added_items_id`),
  KEY `FKpnpiwevigxs6xga4t2224t101` (`id_user`),
  CONSTRAINT `FKpnpiwevigxs6xga4t2224t101` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `added_items`
--

LOCK TABLES `added_items` WRITE;
/*!40000 ALTER TABLE `added_items` DISABLE KEYS */;
INSERT INTO `added_items` VALUES (2,'2022-10-05 16:19:34.080000','Broken work laptot',3,3),(5,'2022-10-05 16:20:39.544000','need a mouse',1,6),(9,'2022-10-05 16:28:22.819000','need',3,3),(13,'2022-10-05 17:23:05.205000','test',3,3),(14,'2022-10-05 17:34:22.608000','test',3,3),(15,'2022-10-05 17:53:58.317000','testtt',0,3),(16,'2022-10-05 17:57:13.187000','for gaming',0,6),(19,'2022-10-06 18:54:08.632000','For zoom',1,6),(20,'2022-10-06 18:55:35.661000','For zoom',0,6),(21,'2022-10-06 18:58:35.861000','Cant see in dark',3,3),(25,'2022-10-06 20:05:54.791000','Broken chair',3,6);
/*!40000 ALTER TABLE `added_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (26);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `equipment_id` bigint NOT NULL,
  `equipment_name` varchar(255) DEFAULT NULL,
  `parameters` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (7,'Mouse','Razor'),(8,'Keyboard','With lights'),(18,'Camera','1080p'),(24,'Chair','Comfy');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_added_items`
--

DROP TABLE IF EXISTS `items_added_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_added_items` (
  `equipment_id` bigint NOT NULL,
  `added_items_id` bigint NOT NULL,
  KEY `FKsmq518rt3r68catq9b2jasqd7` (`added_items_id`),
  KEY `FK1f86tgu9053hww58rfi20api2` (`equipment_id`),
  CONSTRAINT `FK1f86tgu9053hww58rfi20api2` FOREIGN KEY (`equipment_id`) REFERENCES `items` (`equipment_id`),
  CONSTRAINT `FKsmq518rt3r68catq9b2jasqd7` FOREIGN KEY (`added_items_id`) REFERENCES `added_items` (`added_items_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_added_items`
--

LOCK TABLES `items_added_items` WRITE;
/*!40000 ALTER TABLE `items_added_items` DISABLE KEYS */;
INSERT INTO `items_added_items` VALUES (7,5),(18,19),(18,20),(8,16),(8,21),(24,25);
/*!40000 ALTER TABLE `items_added_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` bigint NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@inbox.lv','$2a$10$14Ra.9V7dMtxOazeQ/bRTeffsB5rxCvKXk/DgBFGuGm6ioSM0B5.K',0),(3,'Mort@inbox.lv','$2a$10$JQ0SF116N4ypjmJmGRwb0uSUKW8V3h17HqEHsnPjEaLmcPwGxNVCS',1),(6,'Lion@inbox.lv','$2a$10$Epj8nbJrilZAdkOZiLppH.p3dJtYynfkhTsG976tZdnKsS8WOezsS',1);
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

-- Dump completed on 2022-10-06 20:14:34
