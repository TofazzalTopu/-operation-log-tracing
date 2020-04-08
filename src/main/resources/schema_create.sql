CREATE DATABASE  IF NOT EXISTS `lin_lout` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `lin_lout`;
-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lin_lout
-- ------------------------------------------------------
-- Server version	5.7.26


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

insert  into `casbin_rule`(`ptype`,`v0`,`v1`,`v2`,`v3`,`v4`,`v5`) values ('p','anonymousUser','/login','GET',NULL,NULL,NULL),('g','mijanurman750@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','mijanurman@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','mijanurman7500a@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('p','Customer','/error','GET',NULL,NULL,NULL),('p','Customer','/error','POST',NULL,NULL,NULL),('p','Customer','/','GET',NULL,NULL,NULL),('g','rifatsarker@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('p','anonymousUser','/pms/**','GET',NULL,NULL,NULL),('p','anonymousUser','/pms/**','POST',NULL,NULL,NULL),('p','anonymousUser','/static/**','GET',NULL,NULL,NULL),('p','anonymousUser','/','GET',NULL,NULL,NULL),('p','anonymousUser','/','POST',NULL,NULL,NULL),('g','ashid8bd@gmail.com','ADMIN',NULL,NULL,NULL,NULL),('g','ashid8bd@gmail.com','Customer',NULL,NULL,NULL,NULL),('g','asif8bd@gmail.com','Customer',NULL,NULL,NULL,NULL),('p','ADMIN','/adminpanel/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/**','POST',NULL,NULL,NULL),('p','ADMIN','/','GET',NULL,NULL,NULL),('p','ADMIN','/','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/api','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/api','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasterlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasterlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasteredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/stockmasteredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/updatestockmaster/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/updatestockmaster/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/savestockmaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/savestockmaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/addstockmaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/stockmaster/addstockmaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasterlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasterlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasteredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/ordermasteredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/addordermaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/addordermaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/updateordermaster/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/updateordermaster/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/saveordermaster','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/ordermaster/saveordermaster','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/addcustomerreview','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/addcustomerreview','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/customerreviewlist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/customerreview/customerreviewlist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/branchesedit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/branchesedit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/addbranches','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/addbranches','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/updatebranches/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/updatebranches/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/savebranches','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/savebranches','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/brancheslist','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/branches/brancheslist','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinupdate','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinupdate','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/editcasbin/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/editcasbin/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinlists','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/casbinlists','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/addcasbin','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/addcasbin','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/save','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/casbin/save','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userlists','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userlists','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userdelete/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userdelete/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userupdate/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/userupdate/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/useredit/**','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/useredit/**','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/usersave','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/usersave','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/adduser','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/user/adduser','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/dashboard','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/dashboard','POST',NULL,NULL,NULL),('p','ADMIN','/adminpanel/lock/lockmysession','GET',NULL,NULL,NULL),('p','ADMIN','/adminpanel/lock/lockmysession','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/getRack/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/getRack/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/create','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/create','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/search/**','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/search/**','POST',NULL,NULL,NULL),('p','ADMIN','/drugs/update','GET',NULL,NULL,NULL),('p','ADMIN','/drugs/update','POST',NULL,NULL,NULL),('p','ADMIN','/orders/search','GET',NULL,NULL,NULL),('p','ADMIN','/orders/search','POST',NULL,NULL,NULL),('p','ADMIN','/orders/list','GET',NULL,NULL,NULL),('p','ADMIN','/orders/list','POST',NULL,NULL,NULL),('p','ADMIN','/orders/create','GET',NULL,NULL,NULL),('p','ADMIN','/orders/create','POST',NULL,NULL,NULL),('p','ADMIN','/orders/show/**','GET',NULL,NULL,NULL),('p','ADMIN','/orders/show/**','POST',NULL,NULL,NULL),('p','ADMIN','/orders/customerSearch','GET',NULL,NULL,NULL),('p','ADMIN','/orders/customerSearch','POST',NULL,NULL,NULL),('p','ADMIN','/orders/updateOrder/**','GET',NULL,NULL,NULL),('p','ADMIN','/orders/updateOrder/**','POST',NULL,NULL,NULL),('p','ADMIN','/logout','GET',NULL,NULL,NULL),('p','ADMIN','/logout','POST',NULL,NULL,NULL),('p','ADMIN','/login','GET',NULL,NULL,NULL),('p','ADMIN','/login','POST',NULL,NULL,NULL),('p','ADMIN','/error','GET',NULL,NULL,NULL),('p','ADMIN','/error','POST',NULL,NULL,NULL);


/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679),(679);

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `roles` */
DROP TABLE IF EXISTS `roles`;
insert  into `roles`(`id`,`name`)
values (1,'ADMIN'),(111,'Customer'),(112,'USER');


/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `user_roles` */

insert  into `user_roles`(`user_id`,`role_id`)
values (110,1),(124,1),(125,1),(126,1),(127,1),(128,1),(129,1),(130,1),(131,1),(132,1),(147,1),(677,1),(677,111),(678,111);

--
-- Table structure for table `session_op_log`
--

DROP TABLE IF EXISTS `session_op_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_op_log` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `work_session_id` bigint(20) NOT NULL,
                                  `lat` float NOT NULL,
                                  `lan` float NOT NULL,
                                  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `type` enum('OPEN','CLOSE') COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `fk_op_lig_work_session_id_idx` (`work_session_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `id` varchar(255) CHARACTER SET latin1 NOT NULL,
                         `password` varchar(100) CHARACTER SET latin1 NOT NULL,
                         `active` bit(1) NOT NULL,
                         `first_name` varchar(20) CHARACTER SET latin1 NOT NULL,
                         `last_name` varchar(20) CHARACTER SET latin1 NOT NULL,
                         `mobile_number` varchar(255) CHARACTER SET latin1 NOT NULL,
                         `photo` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
                         `authority` enum('ADMIN','USER') NOT NULL DEFAULT 'USER',
                         `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `modified` timestamp NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `work_session`
--

DROP TABLE IF EXISTS `work_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_session` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `user_id` varchar(255) CHARACTER SET latin1 NOT NULL,
                                `active` bit(1) NOT NULL DEFAULT b'0',
                                `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `modified` timestamp NOT NULL,
                                PRIMARY KEY (`id`),
                                KEY `index_ws_user_id` (`user_id`),
                                CONSTRAINT `cons_fk_ws_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

grant select,insert,update,delete on lin_lout.* to 'lin_lout_api_user'@'%' identified by 'lin_lout_api_user';

/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-20  8:58:23