/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : Shop

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2018-08-31 22:00:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` varchar(30) NOT NULL,
  `name` varchar(30) default NULL,
  `price` double(30,1) default NULL,
  `stock` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1000', '可乐', '10.0', '97');
INSERT INTO `goods_info` VALUES ('1001', '辣条', '4.0', '200');
INSERT INTO `goods_info` VALUES ('1002', '饼干', '3.5', '80');
INSERT INTO `goods_info` VALUES ('1003', '恒大冰泉', '8.0', '98');
INSERT INTO `goods_info` VALUES ('1004', '碧螺春', '100.0', '20');
INSERT INTO `goods_info` VALUES ('1005', '雪碧', '4.0', '100');
INSERT INTO `goods_info` VALUES ('1006', '茶杯', '39.5', '20');
INSERT INTO `goods_info` VALUES ('1007', 'JAVA从入门到精通', '58.8', '18');
INSERT INTO `goods_info` VALUES ('1008', 'PHP从入门到精通', '40.0', '10');
INSERT INTO `goods_info` VALUES ('1009', '冰红茶', '4.0', '100');
INSERT INTO `goods_info` VALUES ('1010', '网络技术', '50.0', '40');
INSERT INTO `goods_info` VALUES ('1011', '牛肉干', '20.0', '1000');
INSERT INTO `goods_info` VALUES ('1012', '小米充电宝', '49.5', '10');
INSERT INTO `goods_info` VALUES ('1013', '小米充电器', '24.0', '46');
INSERT INTO `goods_info` VALUES ('1014', '拖鞋', '19.0', '20');
INSERT INTO `goods_info` VALUES ('1015', '小米超智能手机', '10000.0', '1');
INSERT INTO `goods_info` VALUES ('1017', '华硕笔记本电脑', '6499.0', '5');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) default NULL,
  `sex` varchar(10) default NULL,
  `tel` varchar(30) default NULL,
  `address` varchar(30) default NULL,
  `state` varchar(10) default NULL,
  `type` varchar(10) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('a', 'a', '阿尔瓦', '女', '12345678911', '峡谷', '1', '0');
INSERT INTO `user_info` VALUES ('admin', 'admin', 'me', '男 ', '123456789', '王者峡谷', '1', '1');
INSERT INTO `user_info` VALUES ('my', '123456789', 'tg', '男', '1234578944', '南充', '1', '0');
INSERT INTO `user_info` VALUES ('myuser', '000000', '阿萨德', '女', '12345678911', '王泽z', '1', '0');

-- ----------------------------
-- Table structure for `ushop_info`
-- ----------------------------
DROP TABLE IF EXISTS `ushop_info`;
CREATE TABLE `ushop_info` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(30) default NULL,
  `goodsid` varchar(30) NOT NULL,
  `name` varchar(30) default NULL,
  `price` double(30,1) default NULL,
  `number` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ushop_info
-- ----------------------------
INSERT INTO `ushop_info` VALUES ('6', 'a', '1009', '冰红茶', '4.0', '11');
INSERT INTO `ushop_info` VALUES ('7', 'a', '1005', '雪碧', '4.0', '1');
INSERT INTO `ushop_info` VALUES ('8', 'a', '1003', '恒大冰泉', '8.0', '1');
INSERT INTO `ushop_info` VALUES ('9', 'a', '1001', '辣条', '4.0', '1');
