-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: veilog_db
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `tipo_pessoa` enum('Física','Jurídica') NOT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `rua` varchar(200) DEFAULT NULL,
  `numero_local` int DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Nestle','12.345.6789/10','Jurídica','07148-000','Rua Palmeiras',1879,'Vila Olímpia','São Paulo','SP','(11) 98787-8787','nestle@gmail.com'),(2,'Chocolandia','77.545.20154587/87','Jurídica','05487-741','Gaivotas',8788,'Macedo','Osasco','SP','(11) 98784-5155','chocolandia@oficial.com'),(3,'Empreender SENAI','43.844.2730006/32','Física','07142-000','Estrada do Elenco',5645,'Maia','Guarulhos','SP','(11) 96177-8343','senai@empreender.com'),(4,'Hershey\'s','43.844.2730006/31','Jurídica','07142-000','Golfinho',8787,'Jardim Dona Méri','Guarulhos','SP','(11) 98987-5212','hersheys@chocolates.com'),(5,'Aurora','31.231.23213123/21','Jurídica','70714-287','Jiboia',222,'Limeiras','Viçosa','MG','(11) 98787-8454','aurora@aur.com'),(6,'Poppeyes','34.243.24324243/22','Jurídica','34234-234','Avenidade Rio de Janeiro',33,'Ouverinho','Gramado','RS','(32) 93243-2432','poppeyes@gmail.com'),(7,'AMA','21.212.12121212/12','Jurídica','56564-545','Flor de Lis',12312,'Ouro Preto','Ouro Preto','MG','(43) 53453-4534','ama@gmail.com'),(8,'Amazon','87.878.78787878/78','Jurídica','56565-656','Via Láctea',15454,'Reis','Holambra','SP','(11) 98878-5454','amazon@gmail.com'),(9,'Loreal Paris','54.845.45212112/15','Jurídica','07145110','Rua Caranguejo',1698,'Parque Primavera','Guarulhos','SP','(11) 98523-6254','lorealparis@gmail.com'),(10,'Poppeyes','56.565.65665656/56','Jurídica','07145200','Rua Meteorito',150,'Parque Primavera','Guarulhos','SP','(11) 98745-2563','poppeyes@gmail.com');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-17 16:11:54
