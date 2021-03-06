﻿/*
Navicat MySQL Data Transfer

Source Server         : hack
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : bluestreet

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2016-09-10 11:01:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for g_goodsinfo 创建表bluestreet 然后use bulestreet; 在运行此表才能不报错
-- ----------------------------
DROP TABLE IF EXISTS `g_goodsinfo`;
CREATE TABLE `g_goodsinfo` (
  `Picid` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `Pictype` int(5) unsigned NOT NULL,
  `Pictitle` varchar(20) NOT NULL,
  `Picurl` varchar(200) NOT NULL,
  PRIMARY KEY (`Picid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of g_goodsinfo
-- ----------------------------
INSERT INTO `g_goodsinfo` VALUES ('1', '1', '夏季清凉', 'http://192.168.31.108:8080/toppic/top_one.png');
INSERT INTO `g_goodsinfo` VALUES ('2', '1', '冰冰甄珍', 'http://192.168.31.108:8080/toppic/top_two.png');
INSERT INTO `g_goodsinfo` VALUES ('3', '1', '货火货火', 'http://192.168.31.108:8080/toppic/top_three.png');
INSERT INTO `g_goodsinfo` VALUES ('4', '1', '新款上市', 'http://192.168.31.108:8080/toppic/top_four.png');

-- ----------------------------
-- Table structure for i_infoschool
-- ----------------------------
DROP TABLE IF EXISTS `i_infoschool`;
CREATE TABLE `i_infoschool` (
  `iid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `depid` int(10) unsigned NOT NULL,
  `infotitle` varchar(200) DEFAULT NULL,
  `infobody` varchar(400) NOT NULL,
  `imgsrc` varchar(200) DEFAULT NULL,
  `price` varchar(50) NOT NULL,
  `titme` varchar(500) NOT NULL,
  `infourl` varchar(50) NOT NULL,
  PRIMARY KEY (`iid`),
  KEY `depid` (`depid`),
  CONSTRAINT `i_infoschool_ibfk_1` FOREIGN KEY (`depid`) REFERENCES `s_school` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of i_infoschool
-- ----------------------------
INSERT INTO `i_infoschool` VALUES ('1', '3', '漂亮裙子', '销售漂亮连衣裙非诚勿扰，样式多样请看详情信息需要的朋友可及时联系我哦', '11', '111', '2016', 'http://192.168.31.108:8080/infoshow/show.html');
INSERT INTO `i_infoschool` VALUES ('2', '4', '票亮的帽子', '销售漂亮的帽子，帽子全新，多种多样需要的美少女帽子请及时联系我哦', '22', '222', '2016', 'http://192.168.31.108:8080/infoshow/show.html');
INSERT INTO `i_infoschool` VALUES ('3', '5', '小彩虹专卖店', '销售电动车电动车8成新价格可以面议有需要的朋友可联系我', '55', '22', '2016', 'http://192.168.31.108:8080/infoshow/show.html');
INSERT INTO `i_infoschool` VALUES ('4', '5', '转让手机平板', '手机平板9成新联系我时，请说一条街看到的信息', '88', '88', '7', 'http://192.168.31.108:8080/infoshow/show.html');
INSERT INTO `i_infoschool` VALUES ('5', '5', '85年的红酒', '这瓶红酒85年的历史悠远价格便宜只要998就可以抱回家赶快拿起手中的电话吧机不可失时不再来', '89', '89', '88', 'http://192.168.31.108:8080/infoshow/show.html');

-- ----------------------------
-- Table structure for p_category
-- ----------------------------
DROP TABLE IF EXISTS `p_category`;
CREATE TABLE `p_category` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_category
-- ----------------------------
INSERT INTO `p_category` VALUES ('1', '北京');
INSERT INTO `p_category` VALUES ('2', '上海');
INSERT INTO `p_category` VALUES ('3', '广东');
INSERT INTO `p_category` VALUES ('4', '济南');
INSERT INTO `p_category` VALUES ('5', '重庆');
INSERT INTO `p_category` VALUES ('6', '天津');
INSERT INTO `p_category` VALUES ('7', '四川');
INSERT INTO `p_category` VALUES ('8', '山东');
INSERT INTO `p_category` VALUES ('9', '浙江');
INSERT INTO `p_category` VALUES ('10', '陕西');
INSERT INTO `p_category` VALUES ('11', '云南');
INSERT INTO `p_category` VALUES ('12', '湖南');
INSERT INTO `p_category` VALUES ('13', '宁夏');
INSERT INTO `p_category` VALUES ('14', '贵州');
INSERT INTO `p_category` VALUES ('15', '福建');
INSERT INTO `p_category` VALUES ('16', '辽宁');
INSERT INTO `p_category` VALUES ('17', '安徽');
INSERT INTO `p_category` VALUES ('18', '海南');
INSERT INTO `p_category` VALUES ('19', '台湾');

-- ----------------------------
-- Table structure for s_school
-- ----------------------------
DROP TABLE IF EXISTS `s_school`;
CREATE TABLE `s_school` (
  `sid` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `schoolname` varchar(100) NOT NULL,
  `pid` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `emp_fk_dep` (`pid`),
  CONSTRAINT `emp_fk_dep` FOREIGN KEY (`pid`) REFERENCES `p_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_school
-- ----------------------------
INSERT INTO `s_school` VALUES ('3', '广东大学', '3');
INSERT INTO `s_school` VALUES ('4', '济南大学', '4');
INSERT INTO `s_school` VALUES ('5', '北京音乐学院', '1');
INSERT INTO `s_school` VALUES ('6', '北京美术学院', '1');
INSERT INTO `s_school` VALUES ('7', '北京中戏学院', '1');
INSERT INTO `s_school` VALUES ('8', '北京财经学院', '1');
INSERT INTO `s_school` VALUES ('9', '清华大学', '1');
INSERT INTO `s_school` VALUES ('10', '中国人民大学', '1');
INSERT INTO `s_school` VALUES ('11', ' 北京航空航天大学', '1');
INSERT INTO `s_school` VALUES ('12', ' 首都师范大学', '1');
INSERT INTO `s_school` VALUES ('13', ' 北京服装学院', '1');
INSERT INTO `s_school` VALUES ('14', ' 北京外国语大学', '1');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `userid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `u_user` VALUES ('2', 'a', 'a');
INSERT INTO `u_user` VALUES ('3', '张三', '123');
INSERT INTO `u_user` VALUES ('4', '1', '1');
