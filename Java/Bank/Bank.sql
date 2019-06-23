/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : Bank

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2018-01-22 15:03:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for balance
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance` (
  `idcard` varchar(40) NOT NULL,
  `type` varchar(10) NOT NULL,
  `endcash` double NOT NULL,
  PRIMARY KEY (`idcard`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of balance
-- ----------------------------
INSERT INTO `balance` VALUES ('10001', '人民币', '10000');
INSERT INTO `balance` VALUES ('10002', '人民币', '1000');
INSERT INTO `balance` VALUES ('10003', '人民币', '8000');
INSERT INTO `balance` VALUES ('10004', '人民币', '5000');
INSERT INTO `balance` VALUES ('10005', '人民币', '4500');
INSERT INTO `balance` VALUES ('10006', '人民币', '5500');
INSERT INTO `balance` VALUES ('10007', '人民币', '7000');
INSERT INTO `balance` VALUES ('10008', '人民币', '9600');
INSERT INTO `balance` VALUES ('10009', '人民币', '6500');
INSERT INTO `balance` VALUES ('10010', '人民币', '7500');
INSERT INTO `balance` VALUES ('123456789', '人民币', '10000');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `idcard` varchar(40) NOT NULL,
  `password` varchar(10) NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `id` varchar(30) NOT NULL,
  `age` varchar(5) NOT NULL DEFAULT '18',
  `sex` varchar(4) NOT NULL DEFAULT '男',
  `address` varchar(80) NOT NULL DEFAULT '未知',
  `tel` varchar(20) NOT NULL,
  `user_type` varchar(3) NOT NULL,
  `state` varchar(3) NOT NULL,
  PRIMARY KEY (`idcard`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('10000', '123456', '11', '510105199801013333', '18', '男', '阿萨德', '18055641123', '0', '1');
INSERT INTO `user_info` VALUES ('10001', '123456', '阿狸', '510105199801012222', '18', '女', '王者峡谷', '18055685263', '1', '2');
INSERT INTO `user_info` VALUES ('10002', '123456', '亚索', '510105199801017129', '18', '男', '王者峡谷', '18055696423', '1', '2');
INSERT INTO `user_info` VALUES ('10003', '123456', '莫甘娜', '510105199801011452', '18', '女', '王者峡谷', '18055652143', '1', '2');
INSERT INTO `user_info` VALUES ('10004', '123456', '盖伦', '510105199801019871', '18', '男', '王者峡谷', '18055674256', '1', '2');
INSERT INTO `user_info` VALUES ('10005', '123456', '貂蝉', '510105199801013459', '18', '女', '王者峡谷', '18055614523', '1', '2');
INSERT INTO `user_info` VALUES ('10006', '123456', '赵信', '510105199801010111', '18', '男', '王者峡谷', '18055695632', '1', '2');
INSERT INTO `user_info` VALUES ('10007', '123456', '吕布', '510105199801010102', '18', '男', '王者峡谷', '18055641452', '1', '2');
INSERT INTO `user_info` VALUES ('10008', '123456', '马可波罗', '510105199801010453', '18', '男', '王者峡谷', '18055647842', '1', '2');
INSERT INTO `user_info` VALUES ('10009', '123456', '张良', '510105199801010756', '18', '男', '王者峡谷', '18055641789', '1', '2');
INSERT INTO `user_info` VALUES ('10010', '123456', '公孙离', '510105199801010638', '18', '女', '王者峡谷', '18055641239', '1', '2');
INSERT INTO `user_info` VALUES ('10011', '123456', '花木兰', '510105199801012212', '18', '女', '峡谷之巅', '18055697465', '0', '1');
INSERT INTO `user_info` VALUES ('10012', '123456', '赵云', '510105199801015213', '18', '男', '王者峡谷', '18055664124', '2', '1');
INSERT INTO `user_info` VALUES ('10013', '123456', '劫', '510105199801016666', '18', '男', '王者峡谷', '18055632145', '2', '1');
INSERT INTO `user_info` VALUES ('10014', '000000', '王昭君', '510105199801019999', '18', '女', '王者峡谷', '18055645687', '2', '1');
INSERT INTO `user_info` VALUES ('10015', '123456', '奕星', '510105199801018887', '18', '男', '王者峡谷', '18055612365', '2', '0');
INSERT INTO `user_info` VALUES ('123400', '123456', 'a', '5132121231312', '20', '女', 'aaaa', '14175724254', '0', '0');
