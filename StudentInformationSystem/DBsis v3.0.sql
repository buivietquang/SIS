-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: db_sis
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `idaccount` int NOT NULL AUTO_INCREMENT,
  `idPermission` int NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idaccount`,`idPermission`),
  KEY `account_ibfk_1_idx` (`idPermission`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`idPermission`) REFERENCES `permission` (`idpermission`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'21010614','889a45a9ed50510b9349873eedba7fc12e3ebaa1','9cf95dacd226dcf43da376cdb6cbba7035218921'),(2,1,'3333333','889a45a9ed50510b9349873eedba7fc12e3ebaa1','9cf95dacd226dcf43da376cdb6cbba7035218921'),(3,1,'2222222','889a45a9ed50510b9349873eedba7fc12e3ebaa1','9cf95dacd226dcf43da376cdb6cbba7035218921'),(22,2,'admin','889a45a9ed50510b9349873eedba7fc12e3ebaa1','9cf95dacd226dcf43da376cdb6cbba7035218921'),(23,1,'1111111','889a45a9ed50510b9349873eedba7fc12e3ebaa1','9cf95dacd226dcf43da376cdb6cbba7035218921'),(45,1,'21000022','6086b58b9c02da365b83d24f82822ba22a025452','9bc31423-59e4-4f16-b7c0-3e8bb5a90111'),(46,1,'21000023','c0dca2c7b3c65abaecba65bfb9d4cf75e5ecd7ff','899b9c41-d838-463f-92d4-15c28ba6b26e'),(47,1,'21000024','f27e143e088319d7b7c35b8b602527ba3eaa759b','0ccf24a7-deaf-4ed9-a6b3-0c5770b0d0a5'),(48,1,'21000025','080a58b74a27cee3a3c018f4e769f2cf0a6c0b05','75917fb3-3f40-4ec8-a061-923f82b0d5c5'),(49,1,'21000026','310978ee3700af2599fa559015f5eabec2670cfc','7a3474f8-2d6b-4edd-b292-075635e47454'),(50,1,'21000027','866b0e588180bc5265aa5242f97b459fa53adf2a','a011de68-ab75-4050-97d3-78b20a2634f8'),(51,1,'21000028','3ee5cc9fd2499579ab71301005a0030be6e9d2e7','3ee77444-aa1c-4839-bd9f-1afcf272034a'),(52,1,'21000029','5103e5935823a6711d760adcc0e73e8685816956','09c9e982-34a2-447e-ae7f-4a00549c747c'),(53,1,'21000030','3f79c5b6cb0a1d43ce4dda3ad5d03d83532956ad','4c87bd25-826c-4d6c-9dd5-47833ae90efb');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailregistersubject`
--

DROP TABLE IF EXISTS `detailregistersubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailregistersubject` (
  `idDetailRegister` int NOT NULL AUTO_INCREMENT,
  `idRegister` int NOT NULL,
  `idSubject` int DEFAULT NULL,
  `middleGrade` float DEFAULT NULL,
  `finalGrade` float DEFAULT NULL,
  PRIMARY KEY (`idDetailRegister`,`idRegister`),
  KEY `detailregistersubject_ibfk_1_idx` (`idRegister`),
  KEY `detailregistersubject_ibfk_2_idx` (`idSubject`),
  CONSTRAINT `detailregistersubject_ibfk_1` FOREIGN KEY (`idRegister`) REFERENCES `registersubject` (`idregister`) ON UPDATE CASCADE,
  CONSTRAINT `detailregistersubject_ibfk_2` FOREIGN KEY (`idSubject`) REFERENCES `subject` (`idsubject`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailregistersubject`
--

LOCK TABLES `detailregistersubject` WRITE;
/*!40000 ALTER TABLE `detailregistersubject` DISABLE KEYS */;
INSERT INTO `detailregistersubject` VALUES (1,1,1,8,7),(2,1,2,10,8.5),(3,1,4,8.5,9.5),(4,1,3,8,2),(5,2,5,8.5,10),(6,2,6,10,4),(7,2,7,9,8),(9,4,1,NULL,NULL),(10,4,3,NULL,NULL),(11,5,1,3,8),(12,5,2,9,9),(13,5,3,9,6),(14,6,9,NULL,NULL),(15,6,19,NULL,NULL),(30,7,8,9,10),(31,7,11,6,6),(32,7,12,7,8),(33,7,15,8,9),(34,7,16,5.5,0),(41,3,11,NULL,NULL),(42,3,1,NULL,NULL),(43,3,8,NULL,NULL),(45,3,7,NULL,NULL),(46,3,17,NULL,NULL),(47,6,27,NULL,NULL);
/*!40000 ALTER TABLE `detailregistersubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailsubject`
--

DROP TABLE IF EXISTS `detailsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailsubject` (
  `idDetailSubject` int NOT NULL AUTO_INCREMENT,
  `idSubject` int NOT NULL,
  `semester` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `room` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idDetailSubject`,`idSubject`),
  KEY `detailsubject_ibfk_1_idx` (`idSubject`),
  CONSTRAINT `detailsubject_ibfk_1` FOREIGN KEY (`idSubject`) REFERENCES `subject` (`idsubject`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailsubject`
--

LOCK TABLES `detailsubject` WRITE;
/*!40000 ALTER TABLE `detailsubject` DISABLE KEYS */;
/*!40000 ALTER TABLE `detailsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `idfee` int NOT NULL AUTO_INCREMENT,
  `feeName` varchar(40) DEFAULT NULL,
  `money` int DEFAULT NULL,
  PRIMARY KEY (`idfee`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (1,'normalFee',500000),(2,'specialFee',600000);
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `grade_view`
--

DROP TABLE IF EXISTS `grade_view`;
/*!50001 DROP VIEW IF EXISTS `grade_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `grade_view` AS SELECT 
 1 AS `username`,
 1 AS `semester`,
 1 AS `subjectCode`,
 1 AS `name`,
 1 AS `creditSubject`,
 1 AS `middleGrade`,
 1 AS `finalGrade`,
 1 AS `weight`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `institute`
--

DROP TABLE IF EXISTS `institute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institute` (
  `idInstitute` int NOT NULL AUTO_INCREMENT,
  `instituteName` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phoneSupport` int DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idInstitute`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institute`
--

LOCK TABLES `institute` WRITE;
/*!40000 ALTER TABLE `institute` DISABLE KEYS */;
INSERT INTO `institute` VALUES (1,'Khoa Kinh tế và Kinh doanh',2147483647,'304 - A2'),(2,'Khoa Du Lịch',383958869,'Tầng 6 - A3'),(3,'Khoa Khoa học và Kỹ thuật Vật liệu',342592093,'504 - A5'),(4,'Khoa Điện - Điện tử',NULL,'608 - A4'),(5,'Khoa Cơ khí - Cơ điện tử',982900248,'Tầng 4 và 5 - A7'),(6,'Khoa Kỹ thuật Ô tô và Năng lượng',985814118,'Tầng 6 - A7'),(7,'Khoa Công nghệ thông tin',2147483647,'A6'),(8,'Khoa Công nghệ Sinh học, Hóa học và Kỹ thuật ',NULL,NULL),(9,'Khoa Y',979547418,'Tầng 23 - A9'),(10,'Khoa Dược',904736575,'Tầng 21 - A9'),(11,'Khoa Điều dưỡng',2147483647,'Tòa A'),(12,'Khoa Kỹ thuật Y học',946511010,NULL),(13,'Khoa Tiếng Anh',NULL,'Tầng 4 - A2'),(14,'Khoa Tiếng Hàn Quốc',NULL,'506 - A3'),(15,'Khoa Tiếng Trung Quốc',NULL,'702 - A3'),(16,'Khoa Tiếng Nhật',NULL,'502 - A3'),(17,'Khoa Khoa Học Cơ Bản',24366569,NULL),(18,'Khoa test',11111111,'KTX'),(19,'khoa test 2',1208401804,'ktx');
/*!40000 ALTER TABLE `institute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `idManager` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idManager`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`idManager`) REFERENCES `account` (`idaccount`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (22,'Bùi Việt Quang','Thanh Hóa','012345678','2003-10-28','vietquang2810@gmail.com',NULL);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `idnotice` int NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`idnotice`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (6,'let see what happend','nothing to seeasdasdasd','2023-05-05'),(35,'test','test','2023-05-05'),(37,'Đăng kí 2023','jfbwbf','2023-05-05'),(39,'Tôm','Toom dz vcl','2023-05-09'),(40,'test thông báo','ngày 08 -05 -2023','2023-05-08'),(42,'5455','gdsgd','2023-05-09');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `idPermission` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPermission`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'user','just to view User Interface'),(2,'admin','can see Admin Interface');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registersubject`
--

DROP TABLE IF EXISTS `registersubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registersubject` (
  `idRegister` int NOT NULL AUTO_INCREMENT,
  `idStudent` int NOT NULL,
  `semester` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idRegister`,`idStudent`),
  KEY `registerSubject_ibfk_1_idx` (`idStudent`),
  CONSTRAINT `registerSubject_ibfk_1` FOREIGN KEY (`idStudent`) REFERENCES `student` (`idstudent`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registersubject`
--

LOCK TABLES `registersubject` WRITE;
/*!40000 ALTER TABLE `registersubject` DISABLE KEYS */;
INSERT INTO `registersubject` VALUES (1,1,'20231'),(2,2,'20232'),(3,1,'20232'),(4,2,'20232'),(5,23,'20232'),(6,23,'20232'),(7,1,'20231');
/*!40000 ALTER TABLE `registersubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `registersubject_view`
--

DROP TABLE IF EXISTS `registersubject_view`;
/*!50001 DROP VIEW IF EXISTS `registersubject_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `registersubject_view` AS SELECT 
 1 AS `idRegister`,
 1 AS `idStudent`,
 1 AS `semester`,
 1 AS `subjectCode`,
 1 AS `middleGrade`,
 1 AS `finalGrade`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `requiredsubject`
--

DROP TABLE IF EXISTS `requiredsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requiredsubject` (
  `idrequired` int NOT NULL AUTO_INCREMENT,
  `idSubject` int DEFAULT NULL,
  `idRequiredSubject` int DEFAULT NULL,
  PRIMARY KEY (`idrequired`),
  KEY `requiredSubject_idx` (`idSubject`),
  KEY `req_sub_idx` (`idRequiredSubject`,`idSubject`),
  CONSTRAINT `Subject` FOREIGN KEY (`idSubject`) REFERENCES `subject` (`idsubject`) ON UPDATE CASCADE,
  CONSTRAINT `SubjectMustBeLearnFirst` FOREIGN KEY (`idRequiredSubject`) REFERENCES `subject` (`idsubject`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requiredsubject`
--

LOCK TABLES `requiredsubject` WRITE;
/*!40000 ALTER TABLE `requiredsubject` DISABLE KEYS */;
INSERT INTO `requiredsubject` VALUES (11,8,1),(17,16,1),(18,22,4),(39,135,4),(20,23,9),(38,135,9),(15,12,11),(16,14,12),(34,124,15),(35,124,16),(21,24,19),(23,25,19),(22,32,19),(37,135,19),(26,25,20),(27,30,20),(28,30,21),(19,26,22),(24,29,24),(25,29,26),(36,125,124);
/*!40000 ALTER TABLE `requiredsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `idStudent` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `class` varchar(50) DEFAULT NULL,
  `course` varchar(5) DEFAULT NULL,
  `majors` varchar(255) DEFAULT NULL,
  `identityCardNumber` varchar(50) DEFAULT NULL,
  `dateIssue` varchar(50) DEFAULT NULL,
  `issuePlace` varchar(50) DEFAULT NULL,
  `idInstitute` int DEFAULT NULL,
  PRIMARY KEY (`idStudent`),
  KEY `idInstitute` (`idInstitute`),
  CONSTRAINT `idInstitute` FOREIGN KEY (`idInstitute`) REFERENCES `institute` (`idInstitute`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`idStudent`) REFERENCES `account` (`idaccount`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Bùi Việt Quang','Thanh Hóa','0856908456','2003-10-28','vietquang2810@gmail.com',NULL,'Male','CNTT1','K15','Công nghệ thông tin','2106564564','21010614','Thanh Hóa',1),(2,'Phí Khánh Huyền','Hòa Bình','0929392393','2003-09-09','phihuyen9@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(3,'Bùi Văn Quang','Nam Định','7977898484','2003-05-09','lalala@gmail.com',NULL,'Mail','CNTT2','K13','Công nghệ thông tin','5644464','30/11/2021','Nam Định',7),(23,'Bùi Ngọc Anh','Hà Nội','0355687035','2003-09-23','ngocanh@gmail.com',NULL,'Female','CNTT2','K15','Công nghệ phần mềm','123456','123456','Thanh Hóa',1),(45,'quang1','Thanh Hóa','1663503879','2000-09-15','quang1@gmail.com','sinh vien','Male','CNTT2-2','K15','Hệ thống thông tin','123456','26/03/2021','Thanh Hóa',1),(46,'quang2','Thanh Hóa','1663503879','2001-09-15','quang2@gmail.com','null','Female','CNTT2-2','k15','Kĩ thuật máy tính','123456','26/03/2021','Thanh Hóa',2),(47,'quang3','Thanh Hóa','1663503879','2002-09-15','quang3@gmail.com','null','Male','CNTT2-2','K15','An toàn thông tin','123456','26/03/2021','Thanh Hóa',3),(48,'quang4','Thanh Hóa','1663503879','2003-09-15','quang4@gmail.com','null','Female','CNTT2-2','K15','Hệ thống thông tin','123456','26/03/2021','Thanh Hóa',4),(49,'quang5','Hà Nội','1663503879','2004-09-15','quang5@gmail.com','sunh vien','Male','CNTT2-3','K15','Kĩ thuật máy tính','123456','26/03/2021','Thanh Hóa',5),(50,'quang6','Hà Nội','1663503879','2005-09-15','quang6@gmail.com','null','Female','CNTT2-3','K15','An toàn thông tin','123456','26/03/2021','Thanh Hóa',6),(51,'quang7','Hà Nội','1663503879','2006-09-15','quang7@gmail.com','null','Male','CNTT2-3','K15','Hệ thống thông tin','123456','26/03/2021','Thanh Hóa',1),(52,'quang8','Hà Nội','1663503879','2007-09-15','quang8@gmail.com','null','Female','CNTT2-3','K15','Kĩ thuật máy tính','123456','26/03/2021','Thanh Hóa',2),(53,'quang9','Hà Nội','1663503879','2008-09-15','quang9@gmail.com','sinh vien','Male','CNTT2-4','K15','An toàn thông tin','123456','26/03/2021','Thanh Hóa',3);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `idSubject` int NOT NULL AUTO_INCREMENT,
  `subjectCode` varchar(10) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creditSubject` int DEFAULT NULL,
  `creditTuition` int DEFAULT NULL,
  `idInstitute` int DEFAULT NULL,
  `weight` float DEFAULT NULL,
  PRIMARY KEY (`idSubject`),
  KEY `institute_idx` (`idInstitute`),
  CONSTRAINT `institute` FOREIGN KEY (`idInstitute`) REFERENCES `institute` (`idInstitute`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'BS1100','Giải tích ',4,6,17,0.7),(2,'BS1101','Đại số tuyến tính',4,5,17,0.7),(3,'BS1102','Vật lý cho CNTT',3,4,17,0.7),(4,'IT1101','Nhập môn CNTT',4,7,17,0.7),(5,'IT1100','Ngôn ngữ lập trình C',3,5,17,0.7),(6,'EEE1002','Kỹ thuật số	',2,3,7,0.7),(7,'BS1103','Toán rời rạc',3,4,7,0.7),(8,'IT1102','Cơ sở dữ liệu',3,4,7,0.7),(9,'IT1103','Nhập môn Khoa học dữ liệu và Trí tuệ nhân tạo',2,5,17,0.7),(10,'BS1104','Xác suất thống kê',3,6,17,0.7),(11,'BS1105','Triết học Mác-Lênin ',2,4,17,0.7),(12,'BS1106','Kinh tế chính trị Mác-Lênin ',3,5,17,0.6),(13,'BL1100','Tư tưởng Hồ Chí Minh',2,4,17,0.7),(14,'IT1104','Mạng máy tính',3,5,7,0.6),(15,'IT1105','Hệ điều hành',3,6,7,0.7),(16,'IT1106','Cấu trúc dữ liệu và giải thuật',3,6,7,0.7),(17,'FL1100','Tiếng anh I',3,6,13,0.7),(18,'FL1101','Tiếng anh II',3,5,13,0.7),(19,'FL1102','Tiếng anh III',3,5,13,0.7),(20,'IT1107','Kiến trúc máy tính',3,5,7,0.7),(21,'IT1108','Kỹ thuật phần mềm',3,5,17,0.7),(22,'IT1109','Lập trình hướng đối tượng',2,4,7,0.7),(23,'IT1110','Xây dựng ứng dụng web',3,5,17,0.7),(24,'IT1300','Phân tích và thiết kế phần mềm',3,5,17,0.7),(25,'IT1301','An toàn và bảo mật thông tin',3,5,7,0.7),(26,'IT1302','Lập trình cho thiết bị di động',2,4,7,0.6),(27,'IT1303','Chương trình dịch',2,4,7,0.7),(28,'IT1304','Thị giác máy tính',3,6,7,0.7),(136,'IT4009','Điện toán đám mây',3,2,7,0.7),(137,'BS1300','Lịch sử Đảng Cộng sản Việt Nam',3,2,17,0.4),(138,'IT20009','TEST',2,3,2,0.6),(139,'BS11761','Test 2',4,2,18,0.5),(140,'IT14002','Giao diện người máy',2,3,5,0.4);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `subject_view`
--

DROP TABLE IF EXISTS `subject_view`;
/*!50001 DROP VIEW IF EXISTS `subject_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `subject_view` AS SELECT 
 1 AS `idSubject`,
 1 AS `subjectCode`,
 1 AS `name`,
 1 AS `creditSubject`,
 1 AS `creditTuition`,
 1 AS `weight`,
 1 AS `instituteName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `test`
--

DROP TABLE IF EXISTS `test`;
/*!50001 DROP VIEW IF EXISTS `test`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `test` AS SELECT 
 1 AS `idSubject`,
 1 AS `subjectCode`,
 1 AS `name`,
 1 AS `creditSubject`,
 1 AS `creditTuition`,
 1 AS `idInstitute`,
 1 AS `weight`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `grade_view`
--

/*!50001 DROP VIEW IF EXISTS `grade_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `grade_view` AS select `account`.`username` AS `username`,`registersubject`.`semester` AS `semester`,`subject`.`subjectCode` AS `subjectCode`,`subject`.`name` AS `name`,`subject`.`creditSubject` AS `creditSubject`,`detailregistersubject`.`middleGrade` AS `middleGrade`,`detailregistersubject`.`finalGrade` AS `finalGrade`,`subject`.`weight` AS `weight` from (((`registersubject` join `detailregistersubject`) join `subject`) join `account`) where ((`registersubject`.`idRegister` = `detailregistersubject`.`idRegister`) and (`detailregistersubject`.`idSubject` = `subject`.`idSubject`) and (`registersubject`.`idStudent` = `account`.`idaccount`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `registersubject_view`
--

/*!50001 DROP VIEW IF EXISTS `registersubject_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `registersubject_view` AS select `registersubject`.`idRegister` AS `idRegister`,`registersubject`.`idStudent` AS `idStudent`,`registersubject`.`semester` AS `semester`,`subject`.`subjectCode` AS `subjectCode`,`detailregistersubject`.`middleGrade` AS `middleGrade`,`detailregistersubject`.`finalGrade` AS `finalGrade` from ((`registersubject` join `detailregistersubject`) join `subject`) where ((`registersubject`.`idRegister` = `detailregistersubject`.`idRegister`) and (`detailregistersubject`.`idSubject` = `subject`.`idSubject`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `subject_view`
--

/*!50001 DROP VIEW IF EXISTS `subject_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `subject_view` AS select `subject`.`idSubject` AS `idSubject`,`subject`.`subjectCode` AS `subjectCode`,`subject`.`name` AS `name`,`subject`.`creditSubject` AS `creditSubject`,`subject`.`creditTuition` AS `creditTuition`,`subject`.`weight` AS `weight`,`institute`.`instituteName` AS `instituteName` from (`subject` join `institute`) where (`subject`.`idInstitute` = `institute`.`idInstitute`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `test`
--

/*!50001 DROP VIEW IF EXISTS `test`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `test` AS select `subject`.`idSubject` AS `idSubject`,`subject`.`subjectCode` AS `subjectCode`,`subject`.`name` AS `name`,`subject`.`creditSubject` AS `creditSubject`,`subject`.`creditTuition` AS `creditTuition`,`subject`.`idInstitute` AS `idInstitute`,`subject`.`weight` AS `weight` from `subject` where (`subject`.`creditSubject` = 3) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 16:52:07
