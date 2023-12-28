-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: Dextho
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asistencias`
--

USE Dextho;

DROP TABLE IF EXISTS `asistencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `id_ciudadano` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencias`
--

LOCK TABLES `asistencias` WRITE;
/*!40000 ALTER TABLE `asistencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ciudadanos_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnwj3ltbosx1scl8vwsfabnj2` (`ciudadanos_id`),
  CONSTRAINT `FKnwj3ltbosx1scl8vwsfabnj2` FOREIGN KEY (`ciudadanos_id`) REFERENCES `ciudadanos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'Descripcion del cargoo','2018-01-01','Secretarioo',1),(2,'Tesorero de Delegacion','2017-01-01','Tesorero',1),(14,'gdsfgfdsgdf','2023-01-08','Cargo',32);
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudadanos`
--

DROP TABLE IF EXISTS `ciudadanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudadanos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido_m` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `apellido_p` varchar(255) NOT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `grupo` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `representante` varchar(255) NOT NULL,
  `vive_pueblo` varchar(255) NOT NULL,
  `id_status` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6b726kydqytg79wrb3shg1rq0` (`id_status`),
  CONSTRAINT `FK6b726kydqytg79wrb3shg1rq0` FOREIGN KEY (`id_status`) REFERENCES `estatus_ciudadanos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudadanos`
--

LOCK TABLES `ciudadanos` WRITE;
/*!40000 ALTER TABLE `ciudadanos` DISABLE KEYS */;
INSERT INTO `ciudadanos` VALUES (1,'Perez','Chavez','2023-07-31','2023-07-31',3,'Abraham','Carmen Perez Mendoza','No',1),(21,'Chavez','Guerrero',NULL,NULL,1,'Adan','No','Si',1),(22,'Lorenzo','Godinez',NULL,NULL,1,'Alejandra','No','Si',1),(23,'Perez','Godinez',NULL,NULL,1,'Alfonso','No','Si',1),(24,'Perez','Godinez',NULL,NULL,1,'Anacleto','No','Si',1),(25,'Pastor','Godinez',NULL,NULL,1,'Antonio','No','Si',1),(26,'Resendiz','Godinez',NULL,NULL,1,'Antonio','No','Si',1),(27,'Lorenzo','Godinez',NULL,NULL,1,'Cecilia','No','Si',1),(28,'Tomas','Godinez',NULL,NULL,1,'Cirilo','No','Si',1),(29,'Godinez','Alejandro',NULL,NULL,1,'David','No','Si',1),(30,'Chavez','Guerrero',NULL,NULL,1,'Eduardo','No','Si',1),(31,'Tomas','Mendoza',NULL,NULL,1,'Emiliano','No','Si',1),(32,'Rodriguez','Godinez',NULL,NULL,1,'Edshael','No','Si',1),(33,'Tomas','Godinez',NULL,NULL,1,'Faustino','No','Si',1),(34,'Perez','Godinez',NULL,NULL,1,'Gabriela','No','Si',1),(35,'Godinez','Perez',NULL,NULL,1,'Gerardo','No','Si',1),(36,'Lorenzo','Godinez',NULL,NULL,1,'Jose','No','Si',1),(37,'Perez','Godinez',NULL,NULL,1,'Lucas','No','Si',1),(38,'Godinez','Cruz',NULL,NULL,1,'Lucio','No','Si',1),(39,'Paulin','Godinez',NULL,NULL,1,'Mateo','No','Si',1),(40,'Paulin','Godinez',NULL,NULL,1,'Meliton','No','Si',1),(41,'Charrez','Mendoza',NULL,NULL,1,'Miguel Angel','No','Si',1),(42,'Martinez','Godinez',NULL,NULL,1,'Misael','No','Si',1),(43,'Lorenzo','Godinez',NULL,NULL,1,'Rosalio','No','Si',1),(44,'Guerrero','Ventura',NULL,NULL,1,'Sandra','No','Si',1),(45,'Jimenez','Guerrero',NULL,NULL,1,'Severo','No','Si',1),(46,'Godinez','Perez',NULL,NULL,1,'Vicente','No','Si',1),(47,'Hernandez','Perez',NULL,NULL,1,'Juan Vicente','No','Si',1),(48,'Godinez','Cruz',NULL,NULL,1,'Zenaida','No','Si',1),(49,'Guerrero','Godinez',NULL,NULL,1,'Omar','No','Si',1),(50,'Mendoza','Mendoza',NULL,NULL,1,'Claudia','No','Si',1),(51,'Godinez','Godinez',NULL,NULL,1,'Hotilia','No','Si',1),(52,'Mendoza','Mendoza',NULL,NULL,1,'Rodolfo','No','No',1),(53,'Mendoza','Mendoza',NULL,NULL,1,'German','No','No',1),(54,'Godinez','Godinez',NULL,NULL,1,'Mauricio','No','No',1),(55,'Godinez','Godinez',NULL,NULL,1,'Andres','No','No',1),(56,'Godinez','Godinez',NULL,NULL,1,'Genaro','No','No',1),(57,'Godinez','Godinez',NULL,NULL,1,'Roberto','No','No',1),(58,'Guerrero','Ventura',NULL,NULL,1,'Carlos','No','Si',1),(59,'Espiritu','Godinez',NULL,NULL,1,'Jahir','No','Si',1),(60,'Pastor','Godinez',NULL,NULL,1,'Santiago','No','Si',1),(61,'Rodriguez','Godinez',NULL,NULL,1,'Noe','No','Si',1),(62,'Guerrero','Godinez',NULL,NULL,1,'Leonel','No','No',1),(63,'Godinez','Perez',NULL,NULL,1,'Lucario','No','No',1),(64,'Godinez','Guerrero',NULL,NULL,1,'Sergio','No','No',1),(65,'Godinez','Guerrero',NULL,NULL,1,'Ulises','No','No',1),(66,'Gutierrez','Godinez',NULL,NULL,1,'Fernando','No','No',1),(67,'Godinez','Cruz',NULL,NULL,1,'Abel','No','No',1),(68,'Godinez','Cruz',NULL,NULL,1,'Isidro','No','No',1),(69,'Mendoza','Godinez',NULL,NULL,1,'Diego','No','No',1),(70,'Guerrero','Godinez',NULL,NULL,1,'Eleazar','No','No',1),(71,'Lorenzo','Godinez',NULL,NULL,1,'Joan','No','No',1),(72,'Godinez','Escobilla',NULL,NULL,1,'Hilario','No','No',1),(73,'Hernandez','Perez',NULL,NULL,1,'Saul','No','No',1),(74,'Mendoza','Godinez',NULL,NULL,1,'Jose Manuel','No','No',1),(75,'Mendoza','Tomas',NULL,NULL,2,'Abelardo','No','Si',1),(76,'Lorenzo','Mendoza',NULL,NULL,2,'Andres','No','Si',1),(77,'Mendoza','Bothi',NULL,NULL,2,'Camilo','No','Si',1),(78,'Hernandez','Godinez',NULL,NULL,2,'Eusiquio','No','Si',1),(79,'Lorenzo','Mendoza',NULL,NULL,2,'Felipe','No','Si',1),(80,'Mendoza','Tomas',NULL,NULL,2,'Fidencio','No','Si',1),(81,'Taxtho','Tomas',NULL,NULL,2,'Fidel','No','Si',1),(82,'Mendoza','Dias',NULL,NULL,2,'Francisco','No','Si',1),(83,'Lorenzo','Mendoza',NULL,NULL,2,'Jorge Luis','No','Si',1),(84,'Pastor','Godinez',NULL,NULL,2,'Jose Luis','No','Si',1),(85,'Perez','Godinez',NULL,NULL,2,'Jose Luis','No','Si',1),(86,'Mendoza','Bothi',NULL,NULL,2,'Juan','No','Si',1),(87,'Mendoza','Bothi',NULL,NULL,2,'Juan Carlos','No','Si',1),(88,'Pastor','Godinez',NULL,NULL,2,'Leydi','No','Si',1),(89,'Hernandez','Godinez',NULL,NULL,2,'Marcelino','No','Si',1),(90,'Mendoza','Tomas',NULL,NULL,2,'Margarito','No','Si',1),(91,'Hernandez','Godinez',NULL,NULL,2,'Mario','No','Si',1),(92,'Taxtho','Tomas',NULL,NULL,2,'Melissa','No','Si',1),(93,'Lorenzo','Mendoza',NULL,NULL,2,'Pedro','No','Si',1),(94,'Hernandez','Cruz',NULL,NULL,2,'Roberto','No','Si',1),(95,'Rafael','Mendoza',NULL,NULL,2,'Ruben','No','Si',1),(96,'Hernandez','Cruz',NULL,NULL,2,'Severino','No','Si',1),(97,'C.','Mendoza',NULL,NULL,2,'Eusebio','No','Si',1),(98,'Tomas','Anahi',NULL,NULL,2,'Dulce','No','Si',1),(99,'Cruz','Angel',NULL,NULL,2,'Jose','No','Si',1),(100,'Cruz','Godinez',NULL,NULL,2,'Eusiquio','No','Si',1),(101,'Charrez','Mendoza',NULL,NULL,2,'Mario','No','Si',1),(102,'Tomas','Cerro',NULL,NULL,2,'Emilia','No','Si',1),(103,'Gonzales','Cruz',NULL,NULL,2,'Omar','No','Si',1),(104,'Cyzu','Tomas',NULL,NULL,2,'Jose','No','Si',1),(105,'Taxtho','Tomas',NULL,NULL,2,'Aide','No','No',1),(106,'Rafael','Mendoza',NULL,NULL,2,'Andres','No','No',1),(107,'Mendoza','Lopez',NULL,NULL,2,'Juan','No','No',1),(108,'Pastor','Godinez',NULL,NULL,2,'Lorenzo','No','No',1),(109,'L.','Mendoza',NULL,NULL,2,'Rigoberto','No','No',1),(110,'Mendoza','Lopez',NULL,NULL,2,'Heriberto','No','No',1),(111,'Mendoza','Lopez',NULL,NULL,2,'Enrique','No','No',1),(112,'Hernandez','Godinez',NULL,NULL,2,'Jose','No','No',1),(113,'Perez','Godinez',NULL,NULL,2,'Ermenegildo','No','No',1),(114,'Pastor','Godinez',NULL,NULL,2,'Amando','No','No',1),(115,'Pastor','Godinez',NULL,NULL,2,'German','No','No',1),(116,'Lorenzo','Mendoza',NULL,NULL,2,'Victor','No','No',1),(117,'M.','Tomas',NULL,NULL,2,'Fermin','No','No',1),(118,'T.','Tomas',NULL,NULL,2,'Sergio','No','No',1),(119,'Ramon','Cruz',NULL,NULL,2,'Agustin','No','No',1),(120,'Ramon','Cruz',NULL,NULL,2,'Geronimo','No','No',1),(121,'R.','Cruz',NULL,NULL,2,'Eliseo','No','No',1),(122,'R.','Cruz',NULL,NULL,2,'Adrian','No','No',1),(123,NULL,'Godinez',NULL,NULL,2,'Jonathan','No','No',1),(124,'Godinez','Torres',NULL,NULL,3,'Alberto','No','Si',1),(125,'Cruz','Godinez',NULL,NULL,3,'Albino','No','Si',1),(126,'Mendoza','Chavez',NULL,NULL,3,'Aldo Uriel','No','Si',1),(127,'Ramon','Godinez',NULL,NULL,3,'Alejandra','No','Si',1),(128,'Godinez','Chavez',NULL,NULL,3,'Catarino','No','Si',1),(129,'Perez','Chavez',NULL,NULL,3,'Erasmo','No','Si',1),(130,'Mendoza','Godinez',NULL,NULL,3,'Jose Jesus','No','Si',1),(131,'Godinez','Villeda',NULL,NULL,3,'Jose','No','Si',1),(132,'Villeda','Palacios',NULL,NULL,3,'Daniel','No','Si',1),(133,'Velazquez','Palacios',NULL,NULL,3,'Jose Ignacio','No','Si',1),(134,'Cruz','Perez',NULL,NULL,3,'Juan','No','Si',1),(135,'Mendoza','Godinez',NULL,NULL,3,'Julio Cesar','No','Si',1),(136,'Paz','Rafael',NULL,NULL,3,'Luis Francisco','No','Si',1),(137,'Chavez','Paz',NULL,NULL,3,'Luz Maria','No','Si',1),(138,'Perez','Cruz',NULL,NULL,3,'Mario','No','Si',1),(139,'Perez','Chavez',NULL,NULL,3,'Mauricia','No','Si',1),(140,'Godinez','Chavez',NULL,NULL,3,'Paciano','No','Si',1),(141,'Godinez','Mendoza',NULL,NULL,3,'Paul','No','Si',1),(142,'Chavez','Alaniz',NULL,NULL,3,'Rodrigo','No','Si',1),(143,'Mendoza','Godinez',NULL,NULL,3,'Leonor','No','Si',1),(144,'Huapilla','Godinez',NULL,NULL,3,'Teresa','No','Si',1),(145,'Perez','Chavez',NULL,NULL,3,'Fernanda','No','Si',1),(146,'Perez','Chavez',NULL,NULL,3,'Alvaro','No','Si',1),(147,'Godinez','Cruz',NULL,NULL,3,'Jorge Luis','No','Si',1),(148,'Sanchez','Godinez',NULL,NULL,3,'Hector','No','No',1),(149,'Perez','Chavez',NULL,NULL,3,'Albina','No','No',1),(150,'Cruz','Godinez',NULL,NULL,3,'Agustina','No','No',1),(151,'Perez','Chavez',NULL,NULL,3,'Efrain','No','No',1),(152,'Anastasio','Godinez',NULL,NULL,3,'Eleazar','No','No',1),(153,'Cruz','Perez',NULL,NULL,3,'Isidro','No','No',1),(154,'Perez','Chavez',NULL,NULL,3,'Ruben','No','No',1),(155,'C.','Godinez',NULL,NULL,3,'Martiniano','No','No',1),(156,'P.','Godinez',NULL,NULL,3,'Rogelio','No','No',1),(157,'Martinez','Godinez',NULL,NULL,3,'Gabriel','No','No',1),(158,'Godinez','Mendoza',NULL,NULL,3,'Simon','No','No',1),(159,'G.','Mendoza',NULL,NULL,3,'Juan Carlos','No','No',1),(160,'Anastasio','Godinez',NULL,NULL,3,'Artemio','No','No',1),(161,'Anastasio','Godinez',NULL,NULL,3,'Hilario','No','No',1),(162,'Mendoza','Godinez',NULL,NULL,3,'Ancelmo','No','No',1),(163,'Guerrero','Godinez',NULL,NULL,3,'Keny','No','No',1),(164,NULL,'Godinez',NULL,NULL,3,'David','No','No',1),(165,NULL,'Trejo',NULL,NULL,3,'Pedro','No','Si',1),(166,NULL,'Godinez',NULL,NULL,3,'Mariano','No','No',1),(167,NULL,'Godinez',NULL,NULL,3,'Martin','No','Si',1),(168,'Cruz','Hernandez',NULL,NULL,4,'Angela','No','Si',1),(169,'Cruz','Godinez',NULL,NULL,4,'Brigida','No','Si',1),(170,'Cruz','Rubio',NULL,NULL,4,'Brigido','No','Si',1),(171,'Cruz','Godinez',NULL,NULL,4,'Cirilo','No','Si',1),(172,'Bothi','Perez',NULL,NULL,4,'Clara','No','Si',1),(173,'Chavez','Perez',NULL,NULL,4,'Crescenciano','No','Si',1),(174,'Mendoza','Perez',NULL,NULL,4,'Daniel','No','Si',1),(175,'Garcia','Perez',NULL,NULL,4,'Delfino','No','Si',1),(176,'Mendoza','Perez',NULL,NULL,4,'Diego','No','Si',1),(177,'Godinez','Bothi',NULL,NULL,4,'Esteban','No','Si',1),(178,'Bothi','Perez',NULL,NULL,4,'Felipe','No','Si',1),(179,'Pastor','Perez',NULL,NULL,4,'Florencio','No','Si',1),(180,'Cruz','Bothi',NULL,NULL,4,'Jacinto','No','Si',1),(181,'Bothi','Perez',NULL,NULL,4,'Jesus','No','Si',1),(182,'Chavez','Bothi',NULL,NULL,4,'Jose Guadalupe','No','Si',1),(183,'Perez','Sanchez',NULL,NULL,4,'Karla','No','Si',1),(184,'Medina','Godinez',NULL,NULL,4,'Marcos','No','Si',1),(185,'Escalante','Medina',NULL,NULL,4,'Mauro','No','Si',1),(186,'Garcia','Perez',NULL,NULL,4,'Meliton','No','Si',1),(187,'Medina','Godinez',NULL,NULL,4,'Rosalio','No','Si',1),(188,'Mendoza','Perez',NULL,NULL,4,'Salvador','No','Si',1),(189,'Chavez','Perez',NULL,NULL,4,'Sebastian','No','Si',1),(190,'Angeles','Perez',NULL,NULL,4,'Taurino','No','Si',1),(191,'Garcia','Perez',NULL,NULL,4,'Tomas','No','Si',1),(192,'Bothi','Fernando',NULL,NULL,4,'Julio','No','Si',1),(193,'Chavez','Perez',NULL,NULL,4,'Angela','No','Si',1),(194,'Angeles','Perez',NULL,NULL,4,'Socorro','No','Si',1),(195,'Pastor','Perez',NULL,NULL,4,'Hilda','No','Si',1),(196,'Bothi','Perez',NULL,NULL,4,'Natalia','No','No',1),(197,'Cruz','Perez',NULL,NULL,4,'Adrian','No','No',1),(198,'Mendoza','Perez',NULL,NULL,4,'Juan','No','No',1),(199,'Medina','Godinez',NULL,NULL,4,'Alfonso','No','No',1),(200,'Medina','Godinez',NULL,NULL,4,'Ana Margarita','No','No',1),(201,'Rubio','Luis',NULL,NULL,4,'Jose','No','No',1),(202,'Perez','Antonio',NULL,NULL,4,'Marco','No','No',1),(203,'Bothi','Manuel',NULL,NULL,4,'Victor','No','No',1),(204,NULL,'Perez',NULL,NULL,4,'Andres','No','No',1),(205,NULL,'Perez',NULL,NULL,4,'Victor','No','No',1),(206,NULL,'Perez',NULL,NULL,4,'Filemon','No','No',1),(207,NULL,'Godinez',NULL,NULL,4,'Ignacio','No','No',1),(208,'Perez','Perez',NULL,NULL,5,'Alfredo','No','Si',1),(209,'Garcia','Pastor',NULL,NULL,5,'Andres','No','Si',1),(210,'Cruz','Guerrero',NULL,NULL,5,'Angelina','No','Si',1),(211,'Tomas','Mendoza',NULL,NULL,5,'Camilo','No','Si',1),(212,'Mendoza','Gutierrez',NULL,NULL,5,'Clemente','No','Si',1),(213,'Garcia','Pastor',NULL,NULL,5,'German','No','Si',1),(214,'Pastor','Guerrero',NULL,NULL,5,'Gumercindo','No','Si',1),(215,'Cerro','Mendoza',NULL,NULL,5,'Isaac','No','Si',1),(216,'Hernandez','Mendoza',NULL,NULL,5,'Isaac','No','Si',1),(217,'Tepetate','Godinez',NULL,NULL,5,'Jeus','No','Si',1),(218,'Hernandez','Mendoza',NULL,NULL,5,'Jose','No','Si',1),(219,'Bothi','Carlos',NULL,NULL,5,'Juan','No','Si',1),(220,'Lopez','Lopez',NULL,NULL,5,'Lucio','No','Si',1),(221,'Godinez','Mendoza',NULL,NULL,5,'Marco Antonio','No','Si',1),(222,'Tomas','Mendoza',NULL,NULL,5,'Marcos','No','Si',1),(223,'Ramirez','Pastor',NULL,NULL,5,'Martin','No','Si',1),(224,'Godinez','Pastor',NULL,NULL,5,'Michael','No','Si',1),(225,'Cruz','Pastor',NULL,NULL,5,'Moises','No','Si',1),(226,'Perez','Mendoza',NULL,NULL,5,'Narciso','No','Si',1),(227,'Godinez','Pastor',NULL,NULL,5,'Omar','No','Si',1),(228,'Tepetate','Godinez',NULL,NULL,5,'Pablo','No','Si',1),(229,'Mendoza','Gutierrez',NULL,NULL,5,'Rene','No','Si',1),(230,'Trejo','Pastor',NULL,NULL,5,'Ricky','No','Si',1),(231,'Mendoza','Pastor',NULL,NULL,5,'Uriel','No','Si',1),(232,'Cruz','Pastor',NULL,NULL,5,'Victor','No','Si',1),(233,'H.','Gutierrez',NULL,NULL,5,'Emilio','No','Si',1),(234,'Hernandez','Godinez',NULL,NULL,5,'Pablo','No','Si',1),(235,'Perez','Mendoza',NULL,NULL,5,'Juan','No','Si',1),(236,'Perez','Gutierrez',NULL,NULL,5,'Clemente','No','Si',1),(237,'Cruz','Pastor',NULL,NULL,5,'Aurelio','No','No',1),(238,'Gonzales','Mendoza',NULL,NULL,5,'Adrian','No','No',1),(239,'Guerrero','Ventura',NULL,NULL,5,'Eduardo','No','No',1),(240,'Gutierrez','Eduardo',NULL,NULL,5,'Carlos','No','No',1),(241,'Martinez','Mendoza',NULL,NULL,5,'Vicente','No','No',1),(242,'Godinez','Mendoza',NULL,NULL,5,'Jessica','No','No',1),(243,'Mendoza','Pastor',NULL,NULL,5,'Aurelio','No','No',1),(244,'Mendoza','Pastor',NULL,NULL,5,'Ezequiel','No','No',1),(245,'Cerro','Mendoza',NULL,NULL,5,'Graciela','No','No',1),(246,'Godinez','Perez',NULL,NULL,5,'Ismael','No','No',1),(247,'Hernandez','Pastor',NULL,NULL,5,'Leydi','No','No',1),(248,'Mendoza','Pastor',NULL,NULL,5,'Mariano','No','No',1),(249,'Bothi','Gutierrez',NULL,NULL,5,'Leonel','No','No',1),(250,'Godinez','Perez',NULL,NULL,5,'Galdino','No','No',1),(251,'Perez','Pastor',NULL,NULL,5,'Presciliano','No','No',1),(252,'Perez','Pastor',NULL,NULL,5,'Felipe','No','No',1),(253,'Perez','Pastor',NULL,NULL,5,'Sabino','No','No',1),(254,'Perez','Pastor',NULL,NULL,5,'Mauricio','No','No',1),(255,'Ramirez','Pastor',NULL,NULL,5,'Efren','No','No',1),(256,'Ramirez','Pastor',NULL,NULL,5,'Gadiel','No','No',1),(257,'Hernandez','Gutierrez',NULL,NULL,5,'Tomas','No','No',1),(258,'Hernandez','Gutierrez',NULL,NULL,5,'Marcos','No','No',1),(259,'Hernandez','Gutierrez',NULL,NULL,5,'Daniel','No','No',1),(260,'Cruz','Guerrero',NULL,NULL,5,'Jose Luis','No','No',1),(261,'Cruz','Guerrero',NULL,NULL,5,'Luis Enrique','No','No',1),(262,'Pastor','Perez',NULL,NULL,5,'Angelica M.','No','No',1),(263,'Mendoza','Daniel',NULL,NULL,5,'Juan','No','No',1),(264,'Bothi','Gutierrez',NULL,NULL,5,'Marco Antonio','No','No',1),(265,'Platon','Pastor',NULL,NULL,5,'Charly','No','No',1),(266,'Pastor','Perez',NULL,NULL,5,'Nicolas','No','No',1),(267,'Rosales','Mendoza',NULL,NULL,5,'Eliut','No','No',1),(268,'Martinez','Mendoza',NULL,NULL,5,'Jonathan','No','No',1),(269,NULL,'Gutierrez',NULL,NULL,5,'Armando','No','Si',1),(270,NULL,'Gutierrez',NULL,NULL,5,'Hugo','No','Si',1),(271,NULL,'Pastor',NULL,NULL,5,'Porfirio','No','Si',1),(272,NULL,'Guerrero',NULL,NULL,5,'Edgar','No','No',1),(273,'Cruz','Mendoza',NULL,NULL,6,'Joel','No','Si',1),(274,'Godinez','Mendoza',NULL,NULL,6,'Luis','No','Si',1),(275,'Ramirez','Mendoza',NULL,NULL,6,'Luis Antonio','No','Si',1),(276,'Godinez','Bothi',NULL,NULL,6,'Agustin','No','Si',1),(277,'Rafael','Bothi',NULL,NULL,6,'Agustin','No','Si',1),(278,'Bothi','Gutierrez',NULL,NULL,6,'Rafael','No','Si',1),(279,'Mendoza','Bothi',NULL,NULL,6,'Armando','No','Si',1),(280,'Ramon','Luis',NULL,NULL,6,'Jose','No','Si',1),(281,'Pastor','Guerrero',NULL,NULL,6,'Lucio','No','Si',1),(282,'Perez','Mendoza',NULL,NULL,6,'Juliana','No','Si',1),(283,'Perez','Mendoza',NULL,NULL,6,'Emilio','No','Si',1),(284,'Mendoza','Bothi',NULL,NULL,6,'Matilde','No','Si',1),(285,'Perez','Mendoza',NULL,NULL,6,'Olga Yesenia','No','Si',1),(286,'Mendoza','Bothi',NULL,NULL,6,'Alejandro','No','Si',1),(287,'Bothi','Carlos',NULL,NULL,6,'Juan','No','Si',1),(288,'Bothi','Karina',NULL,NULL,6,'Lizeth','No','Si',1),(289,'Mendoza','Bothi',NULL,NULL,6,'Maximo','No','Si',1),(290,'Bothi','Giovani',NULL,NULL,6,'Max','No','Si',1),(291,'Chavez','Bothi',NULL,NULL,6,'Placido','No','Si',1),(292,'Chavez','Bothi',NULL,NULL,6,'Alejandro','No','Si',1),(293,'Pioquinto','Mendoza',NULL,NULL,6,'Jose Juan','No','Si',1),(294,'Mendoza','Manelic',NULL,NULL,6,'Lazaro','No','Si',1),(295,'Pastor','Pedraza',NULL,NULL,6,'Javier','No','Si',1),(296,'Chavez','Rodriguez',NULL,NULL,6,'Francisco','No','Si',1),(297,'Perez','Ramon',NULL,NULL,6,'Marcelino','No','Si',1),(298,'Perez','Mendoza',NULL,NULL,6,'Juliana','No','Si',1),(299,'Rafael','Bothi',NULL,NULL,6,'Abel','No','No',1),(300,'Bothi','Fernando',NULL,NULL,6,'Luis','No','No',1),(301,'de la Cruz','Bothi',NULL,NULL,6,'Lidia','No','No',1),(302,'de la Cruz','Bothi',NULL,NULL,6,'David','No','No',1),(303,'Chavez','Bothi',NULL,NULL,6,'Crescenciano','No','No',1),(304,'Cruz','Mendoza',NULL,NULL,6,'Pablo','No','No',1),(305,'Marcos','Mendoza',NULL,NULL,6,'Jonathan','No','No',1),(306,'Marcos','Mendoza',NULL,NULL,6,'Juan P.','No','No',1),(307,'Pioquinto','Mendoza',NULL,NULL,6,'Leonid','No','No',1),(308,'Ramon','Mendoza',NULL,NULL,6,'Hilda','No','No',1),(309,NULL,'Godinez',NULL,NULL,6,'Claudio','No','Si',1),(310,NULL,'Ramon',NULL,NULL,6,'Anabel','No','Si',1),(311,NULL,'Guerrero',NULL,NULL,6,'Ivan','No','Si',1),(312,NULL,'Bothi',NULL,NULL,6,'Keyli','No','Si',1),(313,NULL,'Bothi',NULL,NULL,6,'Roberto','No','Si',1),(314,NULL,'Cruz',NULL,NULL,6,'Celestino','No','No',1),(315,NULL,'Mendoza',NULL,NULL,6,'Gilberto','No','No',1),(316,NULL,'Mendoza',NULL,NULL,6,'Florencio','No','No',1),(317,NULL,'Hernandez',NULL,NULL,6,'Efrain','No','No',1),(318,NULL,'Bothi',NULL,NULL,6,'Joaquin','No','No',1),(319,NULL,'Pedraza',NULL,NULL,6,'Luciano','No','No',1),(320,'Godinez','Antonio',NULL,NULL,7,'Alejandro','No','Si',1),(321,'Chavez','Mendoza',NULL,NULL,7,'Alfredo','No','Si',1),(322,'Hernandez','Guerrero',NULL,NULL,7,'Alma','No','Si',1),(323,'Ramon','Mendoza',NULL,NULL,7,'Angelina','No','Si',1),(324,'Rivera','Mendoza',NULL,NULL,7,'Carlos','No','Si',1),(325,'Cruz','Hernandez',NULL,NULL,7,'Carolina','No','Si',1),(326,'Mendoza','Hernandez',NULL,NULL,7,'Catarino','No','Si',1),(327,'Mendoza','Hernandez',NULL,NULL,7,'Fidencio','No','Si',1),(328,'Perez','Mendoza',NULL,NULL,7,'Guadalupe','No','Si',1),(329,'Godinez','Bothi',NULL,NULL,7,'Ignacio','No','Si',1),(330,'Martinez','Mendoza',NULL,NULL,7,'Jose Carlos','No','Si',1),(331,'Mendoza','Hernandez',NULL,NULL,7,'Leonardo','No','Si',1),(332,'Godinez','Bothi',NULL,NULL,7,'Luis Enrique','No','Si',1),(333,'Cruz','Mendoza',NULL,NULL,7,'Pablo','No','Si',1),(334,'Martinez','Mendoza',NULL,NULL,7,'Sara','No','Si',1),(335,'Martinez','Guerrero',NULL,NULL,7,'Tomas','No','Si',1),(336,'Godinez','Antonio',NULL,NULL,7,'Saturnino','No','Si',1),(337,'Godinez','Mendoza',NULL,NULL,7,'Hipolito','No','Si',1),(338,'Bothi','Mendoza',NULL,NULL,7,'Jose Guadalupe','No','Si',1),(339,'Godiez','Mendoza',NULL,NULL,7,'Sara','No','Si',1),(340,'Ramon','Mendoza',NULL,NULL,7,'Marina','No','Si',1),(341,'Martinez','Mendoza',NULL,NULL,7,'Alfredo','No','Si',1),(342,'Rebolledo','Hernandez',NULL,NULL,7,'Jayson','No','Si',1),(343,'Godinez','Antonio',NULL,NULL,7,'Anastacio','No','No',1),(344,'Mendoza','Hernandez',NULL,NULL,7,'Celso','No','No',1),(345,'Hernandez','Guerrero',NULL,NULL,7,'David','No','No',1),(346,'Godinez','Mendoza',NULL,NULL,7,'Emiliano','No','No',1),(347,'Cruz','Rafael',NULL,NULL,7,'Felipe','No','No',1),(348,'Guerrero','Rafael',NULL,NULL,7,'Felipe','No','No',1),(349,'Martinez','Guerrero',NULL,NULL,7,'Genaro','No','No',1),(350,'Mendoza','Mendoza',NULL,NULL,7,'Gerardo','No','No',1),(351,'Godinez','Mendoza',NULL,NULL,7,'Jesus','No','No',1),(352,'Cruz','Hernandez',NULL,NULL,7,'Sergio','No','No',1),(353,'Mendoza','Hernandez',NULL,NULL,7,'Juvencio','No','No',1),(354,'Cruz','Cruz',NULL,NULL,7,'Leobardo','No','No',1),(355,'Cruz','Mendoza',NULL,NULL,7,'Marcelo','No','No',1),(356,'Martinez','Guerrero',NULL,NULL,7,'Nemecio','No','No',1),(357,'Mendoza','Antonio',NULL,NULL,7,'Patricia','No','No',1),(358,'Martinez','Mendoza',NULL,NULL,7,'Raul','No','No',1),(359,'Mendoza','Hernandez',NULL,NULL,7,'Severo','No','No',1),(360,'Godinez','Antonio',NULL,NULL,7,'Wenseslao','No','No',1),(361,'Godinez','Antonio',NULL,NULL,7,'Francisco','No','No',1),(362,'Cruz','Hernandez',NULL,NULL,7,'Catarino','No','No',1),(363,'Mendoza','Cruz',NULL,NULL,8,'Adela','No','Si',1),(364,'Antonio','Ramirez',NULL,NULL,8,'Alejandro','No','Si',1),(365,'Huapilla','Cruz',NULL,NULL,8,'Andres','No','Si',1),(366,'Mendoza','Perez',NULL,NULL,8,'Armando','No','Si',1),(367,'Cruz','Cruz',NULL,NULL,8,'Aurelio','No','Si',1),(368,'Hernandez','Perez',NULL,NULL,8,'Cecilia','No','Si',1),(369,'Mendoza','Cruz',NULL,NULL,8,'Cecilio','No','Si',1),(370,'Medina','Cruz',NULL,NULL,8,'Heriberto','No','Si',1),(371,'Perez','Ramirez',NULL,NULL,8,'Isidro','No','Si',1),(372,'Rafael','Perez',NULL,NULL,8,'Jose Manuel','No','Si',1),(373,'Mendoza','Perez',NULL,NULL,8,'Jose','No','Si',1),(374,'Perez','Ramirez',NULL,NULL,8,'Margarita','No','Si',1),(375,'Godinez','Ramirez',NULL,NULL,8,'Mariela','No','Si',1),(376,'Luna','Perez',NULL,NULL,8,'Oliver','No','Si',1),(377,'Godinez','Ramirez',NULL,NULL,8,'Pedro Juan','No','Si',1),(378,'Mendoza','Perez',NULL,NULL,8,'Valentin','No','Si',1),(379,'Mendoza','Perez',NULL,NULL,8,'Heriberto','No','Si',1),(380,'Hernandez','Gutierrez',NULL,NULL,8,'Guillermina','No','Si',1),(381,'Tendo','Cruz',NULL,NULL,8,'Alvaro','No','Si',1),(382,'Perez','Alberto',NULL,NULL,8,'Jose','No','Si',1),(383,'Luna','Perez',NULL,NULL,8,'Oscar','No','Si',1),(384,'Perez','Sanchez',NULL,NULL,8,'Aleida','No','Si',1),(385,'Perez','Rodriguez',NULL,NULL,8,'Kareli','No','Si',1),(386,'Rafael','Perez',NULL,NULL,8,'Armando','No','No',1),(387,'Bothi','Cruz',NULL,NULL,8,'Adelaido','No','No',1),(388,'Godinez','Ramirez',NULL,NULL,8,'Alejandro','No','No',1),(389,'Hernandez','Perez',NULL,NULL,8,'Clemente','No','No',1),(390,'Bothi','Cruz',NULL,NULL,8,'Jorge','No','No',1),(391,'Huapilla','Cruz',NULL,NULL,8,'Juan Carlos','No','No',1),(392,'Cruz','Mendoza',NULL,NULL,8,'Rafael','No','No',1),(393,'Medina','Cruz',NULL,NULL,8,'Ignacio','No','No',1),(394,'Mendoza','Cruz',NULL,NULL,8,'Jaime','No','No',1),(395,'Pastor','Cruz',NULL,NULL,8,'Jose','No','No',1),(396,'Chavez','Cruz',NULL,NULL,8,'Mateo','No','No',1),(397,'Mendoza','Cruz',NULL,NULL,8,'Mateo','No','No',1),(398,'Medina','Cruz',NULL,NULL,8,'Guillermo','No','No',1),(399,'Medina','Cruz',NULL,NULL,8,'Ernesto','No','No',1),(400,'Medina','Cruz',NULL,NULL,8,'Gabino','No','No',1),(401,'M.','Cruz',NULL,NULL,8,'Luis Enrique','No','No',1),(402,'M.','Perez',NULL,NULL,8,'Rosalio','No','No',1),(403,'Cruz','Godinez',NULL,NULL,8,'Pedro','No','No',1),(404,'Ramirez','Sandovar',NULL,NULL,8,'Pablo','No','No',1),(405,'Perez','Ramirez',NULL,NULL,8,'Oscar','No','No',1),(406,'Mendoza','Cruz',NULL,NULL,8,'Eduardo','No','No',1),(407,'Bothi','Cruz',NULL,NULL,8,'Juan','No','No',1),(408,'Huapilla','Cruz',NULL,NULL,8,'Luis David','No','No',1),(409,'Godinez','Ramirez',NULL,NULL,8,'Miguel Angel','No','No',1),(410,NULL,'Cruz',NULL,NULL,8,'Gregorio','No','No',1),(411,NULL,'Ramirez',NULL,NULL,8,'Manuel','No','No',1);
/*!40000 ALTER TABLE `ciudadanos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estatus_ciudadanos`
--

DROP TABLE IF EXISTS `estatus_ciudadanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatus_ciudadanos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_emitida` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estatus_ciudadanos`
--

LOCK TABLES `estatus_ciudadanos` WRITE;
/*!40000 ALTER TABLE `estatus_ciudadanos` DISABLE KEYS */;
INSERT INTO `estatus_ciudadanos` VALUES (1,'El ciudadano se encuentra cooperando e ingresado a un grupo. ','2023-05-03','Activo'),(2,'Es una persona inactiva que no esta integrado en un grupo y no coopera.','2023-05-03','Inactivo'),(3,'El ciudadano se ha cumplido con los años de participacion en la comunidad.','2023-08-14','Jubilado'),(4,'El ciudadano se dio de baja en buenos terminos sin tener adeudos.','2023-08-26','Baja'),(5,'El ciudadano se dio de baja pero quedo con adeudos.','2023-08-26','Baja Adeudos');
/*!40000 ALTER TABLE `estatus_ciudadanos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multas`
--

DROP TABLE IF EXISTS `multas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_emitida` date NOT NULL,
  `fecha_limite` date NOT NULL,
  `fecha_pagada` date DEFAULT NULL,
  `monto` decimal(38,2) NOT NULL,
  `pagado` bit(1) NOT NULL,
  `ciudadanos_id` bigint DEFAULT NULL,
  `activo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp5caim5h2swrurx8ky4ggiynj` (`ciudadanos_id`),
  CONSTRAINT `FKp5caim5h2swrurx8ky4ggiynj` FOREIGN KEY (`ciudadanos_id`) REFERENCES `ciudadanos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multas`
--

LOCK TABLES `multas` WRITE;
/*!40000 ALTER TABLE `multas` DISABLE KEYS */;
INSERT INTO `multas` VALUES (1,'Multa por exceso de velocidaddd','2023-05-02','2023-05-31','2023-08-07',50050.00,_binary '',1,_binary '\0'),(14,'Multa por pelea en via publica','2023-08-01','2023-08-31',NULL,5000.00,_binary '\0',1,_binary ''),(15,'Pelea en via public','2023-08-02','2023-09-02',NULL,5000.00,_binary '\0',141,_binary '');
/*!40000 ALTER TABLE `multas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `estatus` varchar(255) NOT NULL,
  `fecha_creado` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `prioridad` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (17,_binary '','Se van a citar a Manuel, Juan y Ruben por pelea en via publica.','Pendiente','2023-08-16','Citar a tales personas','Media');
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roles_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKdbv8tdyltxa1qjmfnj9oboxse` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (66,1,1),(81,1,23),(82,2,23);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '','$2a$10$XA/peummgTxzswGITDPKBOS4u2a4FpHiFD4QdAszz108bGpiq7cAy','Delegado'),(23,_binary '','$2a$10$.dXcX0Lj79EDzpYRCrnFrusfI9TFmJkHG90TEG2CLpLcNQIiX5ira','Secretario');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_documentos`
--

DROP TABLE IF EXISTS `usuarios_documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_documentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_emitida` date NOT NULL,
  `ruta` varchar(255) NOT NULL,
  `ciudadanos_id` bigint DEFAULT NULL,
  `nombre_delegado` varchar(255) NOT NULL,
  `nombre_documento` varchar(255) NOT NULL,
  `tipo_documento` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkygqmvklu9whgqjqlueppvlic` (`ciudadanos_id`),
  CONSTRAINT `FKkygqmvklu9whgqjqlueppvlic` FOREIGN KEY (`ciudadanos_id`) REFERENCES `ciudadanos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_documentos`
--

LOCK TABLES `usuarios_documentos` WRITE;
/*!40000 ALTER TABLE `usuarios_documentos` DISABLE KEYS */;
INSERT INTO `usuarios_documentos` VALUES (38,'Acta que se realizo por la falta administrativa que tubo el C. Abraham','2022-04-06','/home/abe-dev/Documents/DocDextho/1/Acta/ABRAHAM CHAVEZ.pdf',1,'Marco Godinez','ABRAHAM CHAVEZ.pdf','Acta'),(39,'Descripcion de prueba','2023-08-26','/home/abe-dev/Documents/DocDextho/32/Contrato/ABRAHAM CHAVEZ Spanish.pdf',32,'Edsael Godinez','ABRAHAM CHAVEZ Spanish.pdf','Contrato');
/*!40000 ALTER TABLE `usuarios_documentos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-27 11:10:23
