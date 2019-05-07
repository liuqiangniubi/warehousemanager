/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.21-log : Database - masscommercialcity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`masscommercialcity` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `masscommercialcity`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `cname` varchar(20) NOT NULL COMMENT '分类名字',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `itemid` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单项id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `subtotal` int(20) DEFAULT NULL COMMENT '用户ID',
  `pid` int(20) DEFAULT NULL COMMENT '状态',
  `oid` int(20) NOT NULL COMMENT '订单id',
  PRIMARY KEY (`itemid`),
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oid` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `ordertime` datetime NOT NULL COMMENT '订单时间',
  `total` double NOT NULL COMMENT '总金额',
  `state` int(11) DEFAULT NULL COMMENT '订单状态',
  `address` varchar(30) NOT NULL COMMENT '地址',
  `name` varchar(20) NOT NULL COMMENT '用户真名',
  `telepho` varchar(20) NOT NULL COMMENT '电话',
  `uid` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `pname` varchar(50) NOT NULL COMMENT '商品名字',
  `market_price` double NOT NULL COMMENT '市场价',
  `Shop_price` double NOT NULL COMMENT '售价',
  `pimage` varchar(200) NOT NULL COMMENT '商品照片',
  `pdate` date NOT NULL COMMENT '商品日期',
  `Is_hot` int(11) NOT NULL COMMENT '是否热门',
  `pdesc` varchar(255) NOT NULL COMMENT '商品描述',
  `pflag` int(11) NOT NULL COMMENT '是否下架',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(20) NOT NULL COMMENT '用户ID',
  `username` varchar(20) NOT NULL COMMENT '昵称',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '真实姓名',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(20) NOT NULL COMMENT '电话',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` int(1) DEFAULT '1' COMMENT '性别',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `code` varchar(20) DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
