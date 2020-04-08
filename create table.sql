/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.16 : Database - zpharma
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zpharma` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `zpharma`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apartment` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `address` */

insert  into `address`(`id`,`apartment`,`city`,`country`,`state`,`zip_code`) values (1,'','Dhaka','','','1240'),(2,'','Dhaka','','','1240'),(3,'','Gulshan','','','1200'),(4,'','gulshan','','',NULL),(5,'','Dhaka','','',NULL),(6,'','Dhaka','','',NULL),(7,'','Dhaka','','',NULL),(8,'','Badda','','',NULL),(9,'','South Badda','','',NULL),(10,'Bepari Villa, 13/4 \nSouth Badda','Gulshan','Bangladesh','Dhaka','1200'),(11,'614 b/1','dhka','Bangladesh','dhaka','1215'),(12,'Bepari Villa, 23/c South Badda','Badda','Bangladesh','Dhaka','1200'),(13,'23/B, South Badda','Gulshan','Bangladesh','Dhaka','1200'),(14,'23/c South Badda','Gulshan','Bangladesh','Dhaka','1200'),(15,'23/c South Badda','Gulshan','Bangladesh','Dhaka','1200'),(16,'23/c South Bada','Gulshan','Bangladesh','Dhaka','1200'),(17,'23/c South Bada','Gulshan','Bangladesh','Dhaka','1200'),(18,'23/c South Badda','Gulshan','Bangladesh','Dhaka','1200'),(19,'test','test','Bangladesh','test','1200'),(20,'test','test','Bangladesh','test','1200'),(21,'test','test','Bangladesh','test','1200'),(22,'test','test','Bangladesh','test','1200'),(23,'test','test','Bangladesh','test','1200'),(24,'','','Bangladesh','',NULL);

/*Table structure for table `branch` */

DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `branch` */

insert  into `branch`(`id`,`name`) values (109,'Dhaka');

/*Table structure for table `casbin_rule` */

DROP TABLE IF EXISTS `casbin_rule`;

CREATE TABLE `casbin_rule` (
  `ptype` tinytext,
  `v0` tinytext,
  `v1` tinytext,
  `v2` tinytext,
  `v3` tinytext,
  `v4` tinytext,
  `v5` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `casbin_rule` */

insert  into `casbin_rule`(`ptype`,`v0`,`v1`,`v2`,`v3`,`v4`,`v5`) values ('p','anonymousUser','/login','GET',NULL,NULL,NULL),('g','mijanurman750@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','mijanurman@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','mijanurman7500a@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('p','Customer','/error','GET',NULL,NULL,NULL),('p','Customer','/error','POST',NULL,NULL,NULL),('p','Customer','/','GET',NULL,NULL,NULL),('g','rifatsarker@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('p','anonymousUser','/lin_info/**','GET',NULL,NULL,NULL),('p','anonymousUser','/lin_info/**','POST',NULL,NULL,NULL),('p','anonymousUser','/static/**','GET',NULL,NULL,NULL),('p','anonymousUser','/','GET',NULL,NULL,NULL),('p','anonymousUser','/','POST',NULL,NULL,NULL),('g','ashid8bd@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','ashid8bd@gmail.com','Customer',NULL,NULL,NULL,NULL),('g','asif8bd@gmail.com','Customer',NULL,NULL,NULL,NULL),('p','ADMIN','/adminpanel/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/**','POST',NULL,NULL,NULL),('p','ADMIN','/','GET',NULL,NULL,NULL),('p','ADMIN','/','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/api','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/api','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasterlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasterlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasteredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasteredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/updatestockmaster/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/updatestockmaster/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/savestockmaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/savestockmaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/addstockmaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/addstockmaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasterlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasterlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasteredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasteredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/addordermaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/addordermaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/updateordermaster/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/updateordermaster/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/saveordermaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/saveordermaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/addcustomerreview','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/addcustomerreview','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/customerreviewlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/customerreviewlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/branchesedit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/branchesedit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/addbranches','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/addbranches','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/updatebranches/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/updatebranches/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/savebranches','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/savebranches','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/brancheslist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/brancheslist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinupdate','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinupdate','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/editcasbin/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/editcasbin/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinlists','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinlists','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/addcasbin','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/addcasbin','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/save','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/save','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userlists','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userlists','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userdelete/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userdelete/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userupdate/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userupdate/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/useredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/useredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/usersave','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/usersave','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/adduser','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/adduser','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/dashboard','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/dashboard','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/lock/lockmysession','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/lock/lockmysession','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/getRack/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/getRack/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/create','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/create','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/search/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/search/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/update','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/update','POST',NULL,NULL,NULL),('p','ADMIN','/orders/search','GET',NULL,NULL,NULL),('p','ADMIN','/orders/search','POST',NULL,NULL,NULL),('p','ADMIN','/orders/list','GET',NULL,NULL,NULL),('p','ADMIN','/orders/list','POST',NULL,NULL,NULL),('p','ADMIN','/orders/create','GET',NULL,NULL,NULL),('p','ADMIN','/orders/create','POST',NULL,NULL,NULL),('p','ADMIN','/orders/show/**','GET',NULL,NULL,NULL),('p','ADMIN','/orders/show/**','POST',NULL,NULL,NULL),('p','ADMIN','/orders/customerSearch','GET',NULL,NULL,NULL),('p','ADMIN','/orders/customerSearch','POST',NULL,NULL,NULL),('p','ADMIN','/orders/updateOrder/**','GET',NULL,NULL,NULL),('p','ADMIN','/orders/updateOrder/**','POST',NULL,NULL,NULL),('p','ADMIN','/logout','GET',NULL,NULL,NULL),('p','ADMIN','/logout','POST',NULL,NULL,NULL),('p','ADMIN','/login','GET',NULL,NULL,NULL),('p','ADMIN','/login','POST',NULL,NULL,NULL),('p','ADMIN','/error','GET',NULL,NULL,NULL),('p','ADMIN','/error','POST',NULL,NULL,NULL);

/*Data for the table `functions` */

insert  into `functions`(`id`,`name`,`price`) values (23,'Marriage Cremony',5000),(93,'Birthday',250),(112,'Something',2000);

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`) values (110,1),(124,1),(125,1),(126,1),(127,1),(128,1),(129,1),(130,1),(131,1),(132,1),(147,1),(677,1),(677,111),(678,111);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `branch_id` bigint(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKixo09sv3j1j6hfox3cx6d2ggg` (`branch_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`active`,`address`,`email`,`first_name`,`last_name`,`mobile_number`,`password`,`username`,`branch_id`,`photo`) values (132,'','Dhaka, Bangladesh, Rangpur','rifatsarker@gmail.com','Rifat','Sarker','01728030032','$2a$12$LtbYtEzOBdnsiD/E9Wtj2O9Y5dj8BGhbPzuSAt.40LuHhKFfcVuHC','rifatsarker@gmail.com',109,'1556011164280.jpeg'),(147,'','Dhaka, Bangladesh, Rangpur','mijanurman750@gmail.com','Mijanur','Rahaman','01728030032','$2a$12$LtbYtEzOBdnsiD/E9Wtj2O9Y5dj8BGhbPzuSAt.40LuHhKFfcVuHC','mijanurman750@gmail.com',109,'1556600187641.jpeg'),(677,'','Rampura','ashid8bd@gmail.com','Md Ahasanul','Ashid','01818096315','$2a$12$LtbYtEzOBdnsiD/E9Wtj2OVTfG.20NfrlOF4MjiWaATWVhmOLkkFG','ashid8bd@gmail.com',109,'1572776337118.jpeg'),(678,'','Rampura','asif8bd@gmail.com','Asif','Chowdhury','01818096315','$2a$12$LtbYtEzOBdnsiD/E9Wtj2O9Y5dj8BGhbPzuSAt.40LuHhKFfcVuHC','asif8bd@gmail.com',109,'1573885266260.jpeg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
