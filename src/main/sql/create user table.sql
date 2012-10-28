/*
 Navicat Premium Data Transfer

 Source Server         : SA-bigsale
 Source Server Type    : MySQL
 Source Server Version : 50525
 Source Host           : localhost
 Source Database       : bigsale

 Target Server Type    : MySQL
 Target Server Version : 50525
 File Encoding         : utf-8

 Date: 10/19/2012 22:21:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ADDRESS`
-- ----------------------------
DROP TABLE IF EXISTS `ADDRESS`;
CREATE TABLE `ADDRESS` (
  `ADDR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDR_STREET` varchar(40) NOT NULL,
  `ADDR_CITY` varchar(20) NOT NULL,
  `ADDR_STATE` varchar(3) NOT NULL,
  `ADDR_ZIP_CODE` varchar(4) NOT NULL,
  PRIMARY KEY (`ADDR_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADDRESS`
-- ----------------------------

-- ----------------------------
--  Table structure for `ADMIN`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN`;
CREATE TABLE `ADMIN` (
  `ADMIN_ID` varchar(20) NOT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADMIN`
-- ----------------------------
BEGIN;
INSERT INTO `ADMIN` VALUES ('admin', '0DPiKuNIrrVmD8IUCuw1hQxNqZc=', 'otto185@naver.com');
COMMIT;

-- ----------------------------
--  Table structure for `ITEM`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM`;
CREATE TABLE `ITEM` (
  `ITEM_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(20) NOT NULL,
  `STOCK_QUANTITY` int(10) NOT NULL,
  `PRICE` int(10) NOT NULL,
  `DISCOUNT_RATE` int(2) DEFAULT '0',
  `DESCRIPTION` text,
  `PICTURE_PATH` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM`
-- ----------------------------


-- ----------------------------
--  Table structure for `ITEM_ORDER`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM_ORDER`;
CREATE TABLE `ITEM_ORDER` (
  `ITEM_ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `DELIVERY_STATUS` int(1) NOT NULL,
  `ORDER_QUANTITY` int(5) NOT NULL,
  PRIMARY KEY (`ITEM_ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM_ORDER`
-- ----------------------------

-- ----------------------------
--  Table structure for `ORDERS`
-- ----------------------------
DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS` (
  `ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ORDER_QUANTITY` int(4) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `SELLER`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER`;
CREATE TABLE `SELLER` (
  `SELLER_ID` varchar(20) NOT NULL,
  `SELLER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` date DEFAULT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  PRIMARY KEY (`SELLER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER`
-- ----------------------------

-- ----------------------------
--  Table structure for `SELLER_ITEM_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER_ITEM_JOIN`;
CREATE TABLE `SELLER_ITEM_JOIN` (
  `SELLER_ID` varchar(20) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  PRIMARY KEY (`SELLER_ID`,`ITEM_ID`),
  KEY `FK_MEETING` (`SELLER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER_ITEM_JOIN`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `USER_ID` varchar(20) NOT NULL,
  `USER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  `ADDR_ID` int(11) NOT NULL,
  `USER_IDX` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `ADDR_ID` (`ADDR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `USER`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER_ORDER_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ORDER_JOIN`;
CREATE TABLE `USER_ORDER_JOIN` (
  `USER_ID` varchar(20) NOT NULL,
  `ORDER_ID` int(10) NOT NULL,
  KEY `USER_ID` (`USER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
/*
 Navicat Premium Data Transfer

 Source Server         : SA-bigsale
 Source Server Type    : MySQL
 Source Server Version : 50525
 Source Host           : localhost
 Source Database       : bigsale

 Target Server Type    : MySQL
 Target Server Version : 50525
 File Encoding         : utf-8

 Date: 10/19/2012 22:21:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ADDRESS`
-- ----------------------------
DROP TABLE IF EXISTS `ADDRESS`;
CREATE TABLE `ADDRESS` (
  `ADDR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDR_STREET` varchar(40) NOT NULL,
  `ADDR_CITY` varchar(20) NOT NULL,
  `ADDR_STATE` varchar(3) NOT NULL,
  `ADDR_ZIP_CODE` varchar(4) NOT NULL,
  PRIMARY KEY (`ADDR_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADDRESS`
-- ----------------------------

-- ----------------------------
--  Table structure for `ADMIN`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN`;
CREATE TABLE `ADMIN` (
  `ADMIN_ID` varchar(20) NOT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADMIN`
-- ----------------------------
BEGIN;
INSERT INTO `ADMIN` VALUES ('admin', '0DPiKuNIrrVmD8IUCuw1hQxNqZc=', 'otto185@naver.com');
COMMIT;

-- ----------------------------
--  Table structure for `ITEM`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM`;
CREATE TABLE `ITEM` (
  `ITEM_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(20) NOT NULL,
  `STOCK_QUANTITY` int(10) NOT NULL,
  `PRICE` int(10) NOT NULL,
  `DISCOUNT_RATE` int(2) DEFAULT '0',
  `DESCRIPTION` text,
  `PICTURE_PATH` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM`
-- ----------------------------


-- ----------------------------
--  Table structure for `ITEM_ORDER`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM_ORDER`;
CREATE TABLE `ITEM_ORDER` (
  `ITEM_ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `DELIVERY_STATUS` int(1) NOT NULL,
  `ORDER_QUANTITY` int(5) NOT NULL,
  PRIMARY KEY (`ITEM_ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM_ORDER`
-- ----------------------------

-- ----------------------------
--  Table structure for `ORDERS`
-- ----------------------------
DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS` (
  `ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ORDER_QUANTITY` int(4) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `SELLER`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER`;
CREATE TABLE `SELLER` (
  `SELLER_ID` varchar(20) NOT NULL,
  `SELLER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` date DEFAULT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  PRIMARY KEY (`SELLER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER`
-- ----------------------------

-- ----------------------------
--  Table structure for `SELLER_ITEM_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER_ITEM_JOIN`;
CREATE TABLE `SELLER_ITEM_JOIN` (
  `SELLER_ID` varchar(20) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  PRIMARY KEY (`SELLER_ID`,`ITEM_ID`),
  KEY `FK_MEETING` (`SELLER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER_ITEM_JOIN`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `USER_ID` varchar(20) NOT NULL,
  `USER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  `ADDR_ID` int(11) NOT NULL,
  `USER_IDX` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `ADDR_ID` (`ADDR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `USER`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER_ORDER_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ORDER_JOIN`;
CREATE TABLE `USER_ORDER_JOIN` (
  `USER_ID` varchar(20) NOT NULL,
  `ORDER_ID` int(10) NOT NULL,
  KEY `USER_ID` (`USER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
/*
 Navicat Premium Data Transfer

 Source Server         : SA-bigsale
 Source Server Type    : MySQL
 Source Server Version : 50525
 Source Host           : localhost
 Source Database       : bigsale

 Target Server Type    : MySQL
 Target Server Version : 50525
 File Encoding         : utf-8

 Date: 10/19/2012 22:21:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ADDRESS`
-- ----------------------------
DROP TABLE IF EXISTS `ADDRESS`;
CREATE TABLE `ADDRESS` (
  `ADDR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDR_STREET` varchar(40) NOT NULL,
  `ADDR_CITY` varchar(20) NOT NULL,
  `ADDR_STATE` varchar(3) NOT NULL,
  `ADDR_ZIP_CODE` varchar(4) NOT NULL,
  PRIMARY KEY (`ADDR_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADDRESS`
-- ----------------------------

-- ----------------------------
--  Table structure for `ADMIN`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN`;
CREATE TABLE `ADMIN` (
  `ADMIN_ID` varchar(20) NOT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ADMIN`
-- ----------------------------
BEGIN;
INSERT INTO `ADMIN` VALUES ('admin', '0DPiKuNIrrVmD8IUCuw1hQxNqZc=', 'otto185@naver.com');
COMMIT;

-- ----------------------------
--  Table structure for `ITEM`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM`;
CREATE TABLE `ITEM` (
  `ITEM_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(20) NOT NULL,
  `STOCK_QUANTITY` int(10) NOT NULL,
  `PRICE` int(10) NOT NULL,
  `DISCOUNT_RATE` int(2) DEFAULT '0',
  `DESCRIPTION` text,
  `PICTURE_PATH` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM`
-- ----------------------------


-- ----------------------------
--  Table structure for `ITEM_ORDER`
-- ----------------------------
DROP TABLE IF EXISTS `ITEM_ORDER`;
CREATE TABLE `ITEM_ORDER` (
  `ITEM_ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `DELIVERY_STATUS` int(1) NOT NULL,
  `ORDER_QUANTITY` int(5) NOT NULL,
  PRIMARY KEY (`ITEM_ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ITEM_ORDER`
-- ----------------------------

-- ----------------------------
--  Table structure for `ORDERS`
-- ----------------------------
DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS` (
  `ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ORDER_QUANTITY` int(4) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `SELLER`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER`;
CREATE TABLE `SELLER` (
  `SELLER_ID` varchar(20) NOT NULL,
  `SELLER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` date DEFAULT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  PRIMARY KEY (`SELLER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER`
-- ----------------------------

-- ----------------------------
--  Table structure for `SELLER_ITEM_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `SELLER_ITEM_JOIN`;
CREATE TABLE `SELLER_ITEM_JOIN` (
  `SELLER_ID` varchar(20) NOT NULL,
  `ITEM_ID` int(10) NOT NULL,
  PRIMARY KEY (`SELLER_ID`,`ITEM_ID`),
  KEY `FK_MEETING` (`SELLER_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SELLER_ITEM_JOIN`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER`
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `USER_ID` varchar(20) NOT NULL,
  `USER_LEVEL` int(11) NOT NULL,
  `LOG_IN_COUNT` int(11) NOT NULL,
  `LAST_LOGIN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PASSWORD` varchar(256) NOT NULL,
  `FULL_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `DATE_CREATED` date DEFAULT NULL,
  `ADDR_ID` int(11) NOT NULL,
  `USER_IDX` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `ADDR_ID` (`ADDR_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `USER`
-- ----------------------------

-- ----------------------------
--  Table structure for `USER_ORDER_JOIN`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ORDER_JOIN`;
CREATE TABLE `USER_ORDER_JOIN` (
  `USER_ID` varchar(20) NOT NULL,
  `ORDER_ID` int(10) NOT NULL,
  KEY `USER_ID` (`USER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

