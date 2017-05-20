-- MySQL dump 10.13  Distrib 5.7.18, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: catalog
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inscris`
--

DROP TABLE IF EXISTS `inscris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscris` (
  `id_student` int(11) NOT NULL,
  `id_materie` varchar(255) NOT NULL,
  PRIMARY KEY (`id_student`,`id_materie`),
  KEY `fk_id_materie_INSCRIS_idx` (`id_materie`),
  CONSTRAINT `fk_id_materie_INSCRIS` FOREIGN KEY (`id_materie`) REFERENCES `materie` (`id_materie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_student_INSCRIS` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscris`
--

LOCK TABLES `inscris` WRITE;
/*!40000 ALTER TABLE `inscris` DISABLE KEYS */;
INSERT INTO `inscris` VALUES (1,'ASD'),(2,'ASD'),(3,'ASD'),(4,'ASD'),(1,'LOGICA'),(2,'LOGICA'),(3,'LOGICA'),(5,'LOGICA'),(1,'POO'),(2,'POO'),(3,'POO'),(4,'POO');
/*!40000 ALTER TABLE `inscris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materie`
--

DROP TABLE IF EXISTS `materie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materie` (
  `id_materie` varchar(255) NOT NULL,
  `nr_credite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_materie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materie`
--

LOCK TABLES `materie` WRITE;
/*!40000 ALTER TABLE `materie` DISABLE KEYS */;
INSERT INTO `materie` VALUES ('ASD',6),('LOGICA',6),('POO',3);
/*!40000 ALTER TABLE `materie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id_student` int(11) NOT NULL,
  `id_materie` varchar(255) NOT NULL,
  `nota` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_student`,`id_materie`),
  CONSTRAINT `fk_id_student_materie_NOTE` FOREIGN KEY (`id_student`, `id_materie`) REFERENCES `inscris` (`id_student`, `id_materie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'ASD',8),(1,'LOGICA',9),(1,'POO',10),(2,'ASD',10),(2,'LOGICA',9),(2,'POO',4),(3,'ASD',10),(3,'LOGICA',9),(3,'POO',8),(4,'ASD',3),(4,'POO',5),(5,'LOGICA',5);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id_student` int(11) NOT NULL,
  `prenume` varchar(255) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `grupa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Toma','Radu-Petrescu',242),(2,'Andrei','Angelescu',242),(3,'Alexandru','Radu',242),(4,'Manole','Sandolache',243),(5,'Silviu','Stan',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-20 13:20:52
