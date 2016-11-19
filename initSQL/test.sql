# Host: localhost  (Version: 5.6.21)
# Date: 2016-11-19 17:02:46
# Generator: MySQL-Front 5.3  (Build 4.175)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "test_admin"
#

DROP TABLE IF EXISTS `test_admin`;
CREATE TABLE `test_admin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL DEFAULT '',
  `role_name` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "test_admin"
#

INSERT INTO `test_admin` VALUES (1,'admin','teacher','admin');

#
# Structure for table "test_course"
#

DROP TABLE IF EXISTS `test_course`;
CREATE TABLE `test_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) DEFAULT NULL,
  `CLASSROOM` varchar(30) DEFAULT NULL,
  `TEACHTIME` date DEFAULT NULL,
  `CREDIT` int(6) DEFAULT NULL,
  `TEACHER` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "test_course"
#

INSERT INTO `test_course` VALUES (4,'HTML','主楼','2015-12-09',6,'杜老师'),(11,'C','主楼','2015-12-09',6,'杜老师'),(13,'JS','主楼','2015-12-09',8,'杜老师'),(15,'C++','主楼','2013-12-13',6,'杜老师'),(16,'JAVA','主楼','2008-08-08',4,'周老师'),(17,'JSP','主楼','2006-06-06',8,'高老师'),(18,'JDBC','主楼','2012-02-02',8,'周老师'),(19,'Servlet','主楼','2015-12-10',4,'高老师');

#
# Structure for table "test_laboratory"
#

DROP TABLE IF EXISTS `test_laboratory`;
CREATE TABLE `test_laboratory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `laboratory_address` varchar(255) DEFAULT NULL,
  `laboratory_name` varchar(255) DEFAULT NULL,
  `max_number` int(11) DEFAULT NULL,
  `min_number` int(11) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `class_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "test_laboratory"
#

INSERT INTO `test_laboratory` VALUES (2,'主楼六层01618','嵌入式实验室',3,2,'2016-11-10 08:00:00','第一大节'),(4,'院楼一层05106','单片机实验室',18,9,'2016-11-06 08:00:00','第五大节'),(5,'德怀楼一层07101','电机拖动实验室',23,12,'2016-11-08 08:00:00','第一大节'),(6,'院楼一层05106','单片机实验室',19,10,'2016-11-01 08:00:00','第一大节'),(7,'主楼六层01618','电机拖动实验室',32,14,'2016-11-02 08:00:00','第一大节'),(8,'德怀楼三层07302','单片机实验室',18,6,'2016-11-03 08:00:00','第二大节'),(9,'院楼二层05202','电机拖动实验室',26,16,'2016-11-04 08:00:00','第一大节'),(10,'院楼三层05331','单片机实验室',20,9,'2016-11-05 08:00:00','第三大节'),(11,'主楼七层01723','电机拖动实验室',30,11,'2016-11-09 08:00:00','第四大节'),(12,'德怀楼三层07302','单片机实验室',44,7,'2016-11-11 08:00:00','第一大节'),(13,'主楼七层01723','电机拖动实验室',54,12,'2016-11-11 08:00:00','第五大节'),(14,'德怀楼三层07302','单片机实验室',35,11,'2016-11-14 08:00:00','第一大节');

#
# Structure for table "test_laboratoryname"
#

DROP TABLE IF EXISTS `test_laboratoryname`;
CREATE TABLE `test_laboratoryname` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `laboratory_name` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "test_laboratoryname"
#

INSERT INTO `test_laboratoryname` VALUES (2,'单片机实验室'),(3,'嵌入式实验室'),(4,'电机拖动实验室');

#
# Structure for table "test_notebook"
#

DROP TABLE IF EXISTS `test_notebook`;
CREATE TABLE `test_notebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_text` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "test_notebook"
#

INSERT INTO `test_notebook` VALUES (14,'老师您好，请问我收到此系统发的邮件，内容显示我需要退选状态为“已预约”的实验室，说所选实验室因为实验室人数不够再预约其它时间段，请问该邮件所述的详细情况？','2016-06-06 22:12:31','收到邮件“需要退选再改约其它时间段实验室”的问题','学生甲');

#
# Structure for table "test_notebook_response"
#

DROP TABLE IF EXISTS `test_notebook_response`;
CREATE TABLE `test_notebook_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context_text` varchar(255) DEFAULT NULL,
  `notebook_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "test_notebook_response"
#

INSERT INTO `test_notebook_response` VALUES (4,'同学您好，因为你预约的实验室人数已满，所以请您按照收到邮件的指示进行操作，感谢你使用中北大学实验室预约系统！',14);

#
# Structure for table "test_student"
#

DROP TABLE IF EXISTS `test_student`;
CREATE TABLE `test_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "test_student"
#

INSERT INTO `test_student` VALUES (1,'请填写否则无法接收系统邮件','男','1号','0000000001','admin','student'),(2,'请填写否则无法接收系统邮件','男','2号','0000000002','admin','student'),(3,'请填写否则无法接收系统邮件','男','3号','0000000003','admin','student'),(4,'请填写否则无法接收系统邮件','男','4号','0000000004','admin','student'),(5,'请填写否则无法接收系统邮件','男','5号','0000000005','admin','student');

#
# Structure for table "test_student_laboratory_relation"
#

DROP TABLE IF EXISTS `test_student_laboratory_relation`;
CREATE TABLE `test_student_laboratory_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `laboratory_id` int(11) DEFAULT NULL,
  `laboratoryOrderStatus` int(11) DEFAULT NULL,
  `ordered_time` datetime DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "test_student_laboratory_relation"
#

INSERT INTO `test_student_laboratory_relation` VALUES (1,14,0,'2016-06-14 09:15:14',6),(2,2,2,'2016-06-14 09:16:42',2),(4,2,2,'2016-06-14 09:17:31',3),(5,2,2,'2016-06-14 09:18:04',4),(6,2,1,'2016-06-14 09:18:25',5);
