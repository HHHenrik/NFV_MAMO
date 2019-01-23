/*
 Navicat Premium Data Transfer

 Source Server         : 我的Mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : nfv_mano

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 20/01/2019 16:29:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for algorithm
-- ----------------------------
DROP TABLE IF EXISTS `algorithm`;
CREATE TABLE `algorithm` (
  `algName` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(500) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `className` varchar(500) DEFAULT NULL,
  `function` varchar(500) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of algorithm
-- ----------------------------
BEGIN;
INSERT INTO `algorithm` VALUES ('sfcAlg', 'working', 1, 'deploy', 'nfv_mano/Alg/sfcAlg.jar', 'algOrchestration.AlgDeploy', 'deploy', NULL);
INSERT INTO `algorithm` VALUES ('12', 'stateless', 2, '12', 'D://Test//CrossDomainEmbed.jar', '12', 'deploy', '2018-05-17 10:47:49');
COMMIT;

-- ----------------------------
-- Table structure for controllersfc
-- ----------------------------
DROP TABLE IF EXISTS `controllersfc`;
CREATE TABLE `controllersfc` (
  `sfcId` int(11) DEFAULT NULL,
  `controllerSfcId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`controllerSfcId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of controllersfc
-- ----------------------------
BEGIN;
INSERT INTO `controllersfc` VALUES (2, 1);
COMMIT;

-- ----------------------------
-- Table structure for forwarding_node
-- ----------------------------
DROP TABLE IF EXISTS `forwarding_node`;
CREATE TABLE `forwarding_node` (
  `id` varchar(20) NOT NULL,
  `capacity` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of forwarding_node
-- ----------------------------
BEGIN;
INSERT INTO `forwarding_node` VALUES ('switch1', 10);
INSERT INTO `forwarding_node` VALUES ('switch10', 10);
INSERT INTO `forwarding_node` VALUES ('switch11', 10);
INSERT INTO `forwarding_node` VALUES ('switch12', 10);
INSERT INTO `forwarding_node` VALUES ('switch13', 10);
INSERT INTO `forwarding_node` VALUES ('switch14', 10);
INSERT INTO `forwarding_node` VALUES ('switch2', 10);
INSERT INTO `forwarding_node` VALUES ('switch3', 10);
INSERT INTO `forwarding_node` VALUES ('switch4', 10);
INSERT INTO `forwarding_node` VALUES ('switch5', 10);
INSERT INTO `forwarding_node` VALUES ('switch6', 10);
INSERT INTO `forwarding_node` VALUES ('switch7', 10);
INSERT INTO `forwarding_node` VALUES ('switch8', 10);
INSERT INTO `forwarding_node` VALUES ('switch9', 10);
COMMIT;

-- ----------------------------
-- Table structure for functionality_node
-- ----------------------------
DROP TABLE IF EXISTS `functionality_node`;
CREATE TABLE `functionality_node` (
  `nodeId` varchar(20) NOT NULL,
  `totalCpu` int(10) DEFAULT NULL,
  `totalMemory` int(10) DEFAULT NULL,
  `totalStorage` int(10) DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `avaCpu` int(10) DEFAULT NULL,
  `avaMemory` int(10) DEFAULT NULL,
  `avaStorage` int(10) DEFAULT NULL,
  PRIMARY KEY (`nodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of functionality_node
-- ----------------------------
BEGIN;
INSERT INTO `functionality_node` VALUES ('server1', 100, 500, 1000, 'bupt', 50, 300, 800);
INSERT INTO `functionality_node` VALUES ('server10', 100, 500, 1000, 'bupt', 90, 400, 700);
INSERT INTO `functionality_node` VALUES ('server11', 100, 500, 1000, 'bupt', 70, 300, 600);
INSERT INTO `functionality_node` VALUES ('server12', 100, 500, 1000, 'bupt', 60, 300, 800);
INSERT INTO `functionality_node` VALUES ('server13', 100, 500, 1000, 'bupt', 80, 200, 900);
INSERT INTO `functionality_node` VALUES ('server14', 100, 500, 1000, 'bupt', 50, 300, 800);
INSERT INTO `functionality_node` VALUES ('server2', 100, 500, 1000, 'bupt', 60, 200, 700);
INSERT INTO `functionality_node` VALUES ('server3', 100, 500, 1000, 'bupt', 70, 300, 600);
INSERT INTO `functionality_node` VALUES ('server4', 100, 500, 1000, 'bupt', 80, 400, 500);
INSERT INTO `functionality_node` VALUES ('server5', 100, 500, 1000, 'bupt', 90, 300, 800);
INSERT INTO `functionality_node` VALUES ('server6', 100, 500, 1000, 'bupt', 80, 300, 900);
INSERT INTO `functionality_node` VALUES ('server7', 100, 500, 1000, 'bupt', 70, 100, 800);
INSERT INTO `functionality_node` VALUES ('server8', 100, 500, 1000, 'bupt', 60, 300, 700);
INSERT INTO `functionality_node` VALUES ('server9', 100, 500, 1000, 'bupt', 50, 200, 800);
COMMIT;

-- ----------------------------
-- Table structure for phy_link_monitor
-- ----------------------------
DROP TABLE IF EXISTS `phy_link_monitor`;
CREATE TABLE `phy_link_monitor` (
  `from` varchar(30) DEFAULT NULL,
  `to` varchar(30) DEFAULT NULL,
  `bw_util_rate` float DEFAULT NULL,
  `bw_thre_up` float DEFAULT NULL,
  `bw_thre_down` float DEFAULT NULL,
  `delay` float DEFAULT NULL,
  `delay_threshold` float DEFAULT NULL,
  `current_time` datetime DEFAULT NULL,
  `alarm_level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of phy_link_monitor
-- ----------------------------
BEGIN;
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 0, 85, 15, 5, 10, '2018-06-14 09:39:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 09:40:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 09:40:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 09:41:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 09:41:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 09:42:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 09:42:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 09:43:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 09:43:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 09:44:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 09:44:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 09:45:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 09:45:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 09:46:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 09:46:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 09:47:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 09:47:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 09:48:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 09:48:30', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 09:49:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 09:49:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 09:50:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 09:50:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 09:51:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 09:51:30', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 09:52:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 09:52:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 09:53:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 30, 85, 15, 5, 10, '2018-06-14 09:53:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 09:54:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 09:54:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 09:55:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 09:55:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 09:56:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 09:56:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 09:57:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 09:57:30', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 09:58:00', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 09:58:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 09:59:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 09:59:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 10:00:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:00:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:01:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:01:30', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:02:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:02:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:03:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:03:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:04:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 10:04:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 10:05:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 10:05:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 10:06:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:06:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 10:07:00', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:07:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 10:08:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:08:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:09:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:09:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 30, 85, 15, 5, 10, '2018-06-14 10:10:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:10:30', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:11:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 0, 85, 15, 5, 10, '2018-06-14 10:11:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:12:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 10:12:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 10:13:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:13:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:14:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 10:14:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:15:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 10:15:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 10:16:00', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 10:16:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 10:17:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 0, 85, 15, 5, 10, '2018-06-14 10:17:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:18:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:18:30', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:19:00', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:19:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:20:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 10:20:30', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 10:21:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 10:21:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 10:22:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 10:22:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:23:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:23:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:24:00', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:24:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:25:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 10:25:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 10:26:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:26:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:27:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 30, 85, 15, 5, 10, '2018-06-14 10:27:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:28:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 10:28:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 10:29:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:29:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 10:32:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 10:32:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:33:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 10:33:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:34:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:34:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:35:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:35:30', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:36:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:36:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 30, 85, 15, 5, 10, '2018-06-14 10:37:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 10:37:30', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 10:38:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:38:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 10:39:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 10:39:30', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:40:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 10:40:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 0, 85, 15, 5, 10, '2018-06-14 10:41:00', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:41:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:42:00', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:42:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 10:43:00', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:43:30', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 5, 85, 15, 5, 10, '2018-06-14 10:44:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:44:31', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 10:45:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:45:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 10:46:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:46:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:47:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:47:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:48:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 10:48:31', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 10:49:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 10:49:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:50:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:50:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 10:51:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 10:51:31', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:52:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 10:52:31', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 10:53:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 10:53:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 10:54:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 10:54:31', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:55:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 10:55:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:56:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:56:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 10:57:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 10:57:31', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 70, 85, 15, 5, 10, '2018-06-14 10:58:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 10:58:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 30, 85, 15, 5, 10, '2018-06-14 10:59:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 10:59:31', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 11:00:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 11:00:31', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 11:01:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 11:01:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 11:02:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 0, 85, 15, 5, 10, '2018-06-14 11:02:31', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 11:03:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 11:03:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 11:04:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 11:04:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 11:05:01', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 11:05:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 90, 85, 15, 5, 10, '2018-06-14 11:06:01', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 11:06:31', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 11:07:01', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 15:05:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 15:06:02', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 15:06:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 15:07:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 40, 85, 15, 5, 10, '2018-06-14 15:05:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 15:06:02', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 15:06:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 15:07:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 15:07:32', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:08:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 15:08:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 15:09:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:09:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 15:10:02', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 15:10:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 15:11:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:11:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 15:12:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 10, 85, 15, 5, 10, '2018-06-14 15:07:32', 2);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:08:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 25, 85, 15, 5, 10, '2018-06-14 15:08:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 35, 85, 15, 5, 10, '2018-06-14 15:09:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:09:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 15:10:02', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 15:10:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 15:11:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:11:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 15:12:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:12:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 15:13:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 15:13:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 15:14:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 15:14:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 15:15:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 15:15:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:16:02', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:16:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 15:17:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:12:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 15:13:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 85, 85, 15, 5, 10, '2018-06-14 15:13:32', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 55, 85, 15, 5, 10, '2018-06-14 15:14:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 75, 85, 15, 5, 10, '2018-06-14 15:14:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 45, 85, 15, 5, 10, '2018-06-14 15:15:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 20, 85, 15, 5, 10, '2018-06-14 15:15:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 15, 85, 15, 5, 10, '2018-06-14 15:16:02', 1);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:16:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 50, 85, 15, 5, 10, '2018-06-14 15:17:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 95, 85, 15, 5, 10, '2018-06-14 15:17:32', 3);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 15:18:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 15:18:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 80, 85, 15, 5, 10, '2018-06-14 15:19:02', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 60, 85, 15, 5, 10, '2018-06-14 15:19:32', 0);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 100, 85, 15, 5, 10, '2018-06-14 15:20:02', 4);
INSERT INTO `phy_link_monitor` VALUES ('switch1', 'server1', 65, 85, 15, 5, 10, '2018-06-14 15:20:32', 0);
COMMIT;

-- ----------------------------
-- Table structure for phy_node_monitor
-- ----------------------------
DROP TABLE IF EXISTS `phy_node_monitor`;
CREATE TABLE `phy_node_monitor` (
  `nodeId` varchar(30) DEFAULT NULL,
  `current_time` datetime DEFAULT NULL,
  `alarm_level` int(11) DEFAULT NULL,
  `cpu_util_rate` float DEFAULT NULL,
  `memory_util_rate` float DEFAULT NULL,
  `storage_util_rate` float DEFAULT NULL,
  `cpu_thre_up` float DEFAULT NULL,
  `cpu_thre_down` float DEFAULT NULL,
  `memory_thre_up` float DEFAULT NULL,
  `memory_thre_down` float DEFAULT NULL,
  `storage_thre_up` float DEFAULT NULL,
  `storage_thre_down` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of phy_node_monitor
-- ----------------------------
BEGIN;
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:39:30', 3, 0, 30, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:40:00', 0, 40, 35, 5, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:40:30', 3, 20, 40, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:41:00', 0, 60, 100, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:41:30', 3, 0, 10, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:42:00', 2, 95, 85, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:42:30', 0, 20, 65, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:43:00', 3, 20, 15, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:43:30', 0, 65, 80, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:44:00', 3, 15, 30, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:44:30', 1, 10, 20, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:45:00', 0, 75, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:45:30', 3, 25, 15, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:46:00', 0, 30, 5, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:46:30', 3, 55, 60, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:47:00', 0, 75, 70, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:47:30', 2, 5, 40, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:48:00', 0, 70, 90, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:48:30', 3, 80, 70, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:49:00', 0, 45, 5, 0, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:49:30', 3, 100, 95, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:50:00', 0, 70, 95, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:50:30', 2, 5, 45, 70, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:51:00', 0, 100, 80, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:51:30', 3, 80, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:52:00', 0, 85, 95, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:52:30', 3, 75, 70, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:53:00', 0, 75, 95, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:53:30', 3, 35, 35, 60, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:54:00', 0, 30, 15, 5, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:54:30', 3, 80, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:55:00', 0, 45, 55, 35, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:55:30', 3, 75, 75, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:56:00', 2, 95, 95, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:56:30', 1, 10, 25, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:57:00', 0, 25, 50, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:57:30', 2, 5, 15, 10, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:58:00', 0, 85, 85, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:58:30', 3, 55, 5, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:59:00', 1, 10, 50, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 09:59:30', 0, 35, 20, 35, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:00:00', 1, 10, 70, 70, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:00:30', 0, 50, 40, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:01:00', 3, 100, 85, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:01:30', 0, 70, 70, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:02:00', 3, 20, 70, 35, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:02:30', 0, 60, 10, 20, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:03:00', 3, 80, 70, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:03:30', 0, 20, 40, 0, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:04:00', 3, 85, 95, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:04:30', 0, 0, 60, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:05:00', 3, 25, 30, 5, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:05:30', 1, 90, 85, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:06:00', 2, 95, 100, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:06:30', 1, 90, 90, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:07:00', 0, 35, 30, 10, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:07:30', 3, 45, 35, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:08:00', 0, 65, 75, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:08:30', 3, 40, 15, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:09:00', 0, 30, 30, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:09:30', 3, 60, 70, 50, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:10:00', 0, 35, 25, 0, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:10:30', 1, 10, 50, 50, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:11:00', 0, 75, 100, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:11:31', 1, 90, 80, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:12:01', 0, 35, 70, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:12:31', 3, 0, 60, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:13:01', 0, 85, 70, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:13:31', 3, 75, 90, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:14:01', 0, 85, 75, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:14:31', 2, 95, 90, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:15:01', 2, 5, 10, 35, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:15:31', 1, 10, 55, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:16:01', 0, 100, 75, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:16:31', 2, 95, 70, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:17:01', 0, 75, 90, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:17:31', 3, 70, 70, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:18:01', 0, 25, 35, 5, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:18:31', 3, 70, 70, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:19:01', 0, 40, 50, 10, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:19:31', 3, 25, 40, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:20:01', 0, 75, 90, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:20:31', 3, 55, 40, 60, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:21:01', 0, 75, 70, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:21:31', 3, 60, 90, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:22:01', 2, 5, 15, 50, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:22:31', 0, 20, 40, 70, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:23:01', 3, 75, 90, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:23:31', 0, 75, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:24:01', 3, 40, 5, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:24:31', 0, 65, 90, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:25:01', 3, 40, 10, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:25:31', 0, 25, 0, 10, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:26:01', 3, 60, 85, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:26:31', 0, 80, 85, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:27:01', 3, 15, 55, 15, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:27:31', 0, 40, 55, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:28:01', 3, 45, 30, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:28:31', 0, 15, 40, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:29:01', 3, 70, 70, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:29:31', 2, 5, 10, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:30:01', 0, 100, 95, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:30:31', 3, 85, 100, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:31:01', 0, 75, 95, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:31:31', 3, 85, 70, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:32:01', 0, 60, 0, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:32:31', 3, 60, 40, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:33:01', 0, 70, 80, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:33:31', 3, 20, 40, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:34:01', 1, 90, 90, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:34:31', 0, 75, 100, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:35:01', 3, 80, 80, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:35:31', 0, 60, 25, 60, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:36:01', 3, 55, 30, 60, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:36:31', 0, 45, 35, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:37:01', 3, 0, 70, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:37:31', 0, 35, 55, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:38:01', 3, 100, 95, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:38:31', 2, 95, 85, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:39:01', 0, 60, 70, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:39:31', 3, 100, 90, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:40:01', 2, 95, 95, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:40:31', 0, 35, 0, 10, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:41:01', 3, 50, 70, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:41:31', 0, 50, 60, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:42:01', 1, 90, 90, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:42:31', 2, 5, 35, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:43:01', 2, 5, 65, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:43:31', 0, 65, 75, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:44:01', 1, 90, 85, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:44:31', 1, 90, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:45:01', 2, 95, 95, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:45:31', 0, 55, 45, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:46:01', 3, 20, 65, 20, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:46:31', 0, 60, 75, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:47:01', 3, 60, 65, 70, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:47:31', 0, 75, 75, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:48:01', 1, 90, 95, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:48:31', 0, 0, 45, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:49:01', 3, 50, 10, 20, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:49:31', 0, 50, 30, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:50:01', 3, 50, 30, 70, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:50:31', 0, 80, 70, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:51:01', 3, 65, 90, 85, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:51:31', 1, 90, 80, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:52:01', 0, 60, 50, 50, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:52:31', 3, 20, 40, 55, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:53:01', 0, 25, 0, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:53:31', 3, 60, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:54:01', 0, 0, 50, 65, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:54:31', 1, 90, 75, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 10:55:01', 2, 5, 20, 75, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:05:32', 2, 95, 70, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:06:02', 0, 0, 60, 15, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:06:32', 2, 95, 80, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:07:02', 0, 75, 100, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:07:32', 2, 5, 35, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:08:02', 0, 30, 40, 5, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:08:32', 3, 100, 80, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:09:02', 0, 45, 50, 45, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:09:32', 2, 95, 85, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:10:02', 2, 95, 100, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:10:32', 0, 85, 70, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:11:02', 3, 85, 100, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:11:32', 1, 10, 60, 30, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:12:02', 0, 35, 60, 20, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:12:32', 3, 60, 5, 0, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:13:02', 2, 95, 80, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:13:32', 0, 0, 20, 20, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:14:02', 3, 0, 0, 60, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:14:32', 0, 35, 60, 25, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:15:02', 2, 95, 95, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:15:32', 0, 85, 85, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:16:02', 2, 95, 80, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:16:32', 2, 95, 75, 90, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:17:02', 0, 55, 65, 35, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:17:32', 3, 20, 25, 40, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:18:02', 2, 95, 90, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:18:32', 0, 85, 100, 95, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:19:02', 3, 100, 75, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:19:32', 0, 35, 70, 80, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:20:02', 3, 65, 90, 100, 90, 10, 85, 15, 90, 5);
INSERT INTO `phy_node_monitor` VALUES ('server1', '2018-06-14 15:20:32', 1, 90, 90, 100, 90, 10, 85, 15, 90, 5);
COMMIT;

-- ----------------------------
-- Table structure for server_util
-- ----------------------------
DROP TABLE IF EXISTS `server_util`;
CREATE TABLE `server_util` (
  `nodeId` varchar(30) DEFAULT NULL,
  `cpuRate` int(10) DEFAULT NULL,
  `memoryRate` int(10) DEFAULT NULL,
  `storageRate` int(10) DEFAULT NULL,
  `recordTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server_util
-- ----------------------------
BEGIN;
INSERT INTO `server_util` VALUES ('server8', 10, 20, 30, '2018-04-17 10:00:00');
INSERT INTO `server_util` VALUES ('server8', 30, 40, 30, '2018-04-17 10:05:00');
INSERT INTO `server_util` VALUES ('server8', 66, 35, 70, '2018-04-17 10:10:00');
INSERT INTO `server_util` VALUES ('server8', 86, 50, 40, '2018-04-17 10:15:00');
INSERT INTO `server_util` VALUES ('server8', 60, 47, 39, '2018-04-17 10:20:00');
INSERT INTO `server_util` VALUES ('server8', 20, 70, 46, '2018-04-17 10:25:00');
INSERT INTO `server_util` VALUES ('server8', 20, 70, 46, '2018-04-17 10:30:00');
INSERT INTO `server_util` VALUES ('server8', 57, 57, 7, '2018-04-17 10:35:00');
INSERT INTO `server_util` VALUES ('server8', 40, 27, 72, '2018-04-17 10:40:00');
COMMIT;

-- ----------------------------
-- Table structure for sfc
-- ----------------------------
DROP TABLE IF EXISTS `sfc`;
CREATE TABLE `sfc` (
  `sfcId` int(11) NOT NULL AUTO_INCREMENT,
  `sfcName` varchar(30) DEFAULT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`sfcId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc
-- ----------------------------
BEGIN;
INSERT INTO `sfc` VALUES (1, 'test', 'admin', 'stateless', '2018-06-14 10:18:46');
INSERT INTO `sfc` VALUES (2, 'sfc1', 'admin', 'working', '2018-09-06 09:57:17');
COMMIT;

-- ----------------------------
-- Table structure for sfc_link
-- ----------------------------
DROP TABLE IF EXISTS `sfc_link`;
CREATE TABLE `sfc_link` (
  `sfcId` int(11) NOT NULL,
  `from` int(5) DEFAULT NULL,
  `to` int(5) DEFAULT NULL,
  `bandwidth` int(5) DEFAULT NULL,
  `delay` int(5) DEFAULT NULL,
  `flag` int(1) DEFAULT NULL,
  `fromVnf` varchar(30) DEFAULT NULL,
  `toVnf` varchar(30) DEFAULT NULL,
  `linkId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_link
-- ----------------------------
BEGIN;
INSERT INTO `sfc_link` VALUES (2, 0, 1, 10, 10, 1, 'vnf2', 'vnf1', 0);
INSERT INTO `sfc_link` VALUES (2, 1, 2, 10, 10, 1, 'vnf1', 'vnf1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sfc_link_deploy
-- ----------------------------
DROP TABLE IF EXISTS `sfc_link_deploy`;
CREATE TABLE `sfc_link_deploy` (
  `sfcId` int(11) DEFAULT NULL,
  `results` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_link_deploy
-- ----------------------------
BEGIN;
INSERT INTO `sfc_link_deploy` VALUES (2, 'server8,switch8,switch11,server11,switch11,switch10,server10');
COMMIT;

-- ----------------------------
-- Table structure for sfc_link_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sfc_link_monitor`;
CREATE TABLE `sfc_link_monitor` (
  `sfcId` int(11) DEFAULT NULL,
  `bw_util_rate` float DEFAULT NULL,
  `delay` float DEFAULT NULL,
  `bw_threshold_down` float DEFAULT NULL,
  `delay_threshold` float DEFAULT NULL,
  `alarm_level` int(11) DEFAULT NULL,
  `current_time` datetime DEFAULT NULL,
  `linkId` int(11) DEFAULT NULL,
  `bw_threshold_up` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_link_monitor
-- ----------------------------
BEGIN;
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 09:39:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 09:40:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 0, '2018-06-14 09:40:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 09:41:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 09:41:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 09:42:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 09:42:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 3, '2018-06-14 09:43:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 09:43:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 25, 5, 15, 10, 0, '2018-06-14 09:44:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 09:44:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 25, 5, 15, 10, 0, '2018-06-14 09:45:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 09:45:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 09:46:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 65, 5, 15, 10, 0, '2018-06-14 09:46:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 09:47:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 09:47:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 09:48:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 35, 5, 15, 10, 0, '2018-06-14 09:48:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 60, 5, 15, 10, 0, '2018-06-14 09:49:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 09:49:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 09:50:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 30, 5, 15, 10, 0, '2018-06-14 09:50:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 09:51:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 09:51:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 40, 5, 15, 10, 0, '2018-06-14 09:52:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 09:52:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 09:53:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 15, 5, 15, 10, 0, '2018-06-14 09:53:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 09:54:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 30, 5, 15, 10, 0, '2018-06-14 09:54:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 09:55:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 09:55:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 09:56:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 55, 5, 15, 10, 0, '2018-06-14 09:56:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 09:57:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 0, '2018-06-14 09:57:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 55, 5, 15, 10, 0, '2018-06-14 09:58:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 09:58:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 15, 5, 15, 10, 0, '2018-06-14 09:59:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 09:59:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 15, 5, 15, 10, 0, '2018-06-14 10:00:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:04:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 10:04:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 4, '2018-06-14 10:05:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:05:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 0, '2018-06-14 10:06:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 65, 5, 15, 10, 0, '2018-06-14 10:06:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:07:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:07:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:08:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:08:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 10:09:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 2, '2018-06-14 10:09:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:10:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:10:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:11:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:11:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 45, 5, 15, 10, 0, '2018-06-14 10:12:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:12:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 60, 5, 15, 10, 0, '2018-06-14 10:13:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:13:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:14:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:14:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:15:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:15:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 45, 5, 15, 10, 0, '2018-06-14 10:16:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 40, 5, 15, 10, 0, '2018-06-14 10:16:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 0, '2018-06-14 10:17:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:17:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 10:18:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:18:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:19:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:19:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 20, 5, 15, 10, 0, '2018-06-14 10:20:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:20:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 10:21:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:21:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 15, 5, 15, 10, 0, '2018-06-14 10:22:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 55, 5, 15, 10, 0, '2018-06-14 10:22:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 25, 5, 15, 10, 0, '2018-06-14 10:23:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:23:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:24:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:24:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 55, 5, 15, 10, 0, '2018-06-14 10:25:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 45, 5, 15, 10, 0, '2018-06-14 10:25:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 60, 5, 15, 10, 0, '2018-06-14 10:26:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:26:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 10:27:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 10:27:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 2, '2018-06-14 10:28:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 65, 5, 15, 10, 0, '2018-06-14 10:28:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:29:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 10:29:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:30:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 45, 5, 15, 10, 0, '2018-06-14 10:30:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:31:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:31:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:32:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:32:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 10:33:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:33:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 40, 5, 15, 10, 0, '2018-06-14 10:34:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 10:34:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:35:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:35:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:36:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:36:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:37:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:37:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 10:38:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 10:38:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:39:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:39:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:40:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:40:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 20, 5, 15, 10, 0, '2018-06-14 10:41:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:41:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:42:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 45, 5, 15, 10, 0, '2018-06-14 10:42:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 0, '2018-06-14 10:43:00', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 10:43:30', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:44:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:44:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 10:45:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:45:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 65, 5, 15, 10, 0, '2018-06-14 10:46:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:46:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 10:47:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:47:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 30, 5, 15, 10, 0, '2018-06-14 10:48:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 70, 5, 15, 10, 0, '2018-06-14 10:48:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 65, 5, 15, 10, 0, '2018-06-14 10:49:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 30, 5, 15, 10, 0, '2018-06-14 10:49:31', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 10:50:01', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 15, 5, 15, 10, 0, '2018-06-14 15:05:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 15:06:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 15:06:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 20, 5, 15, 10, 0, '2018-06-14 15:07:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 15:07:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 40, 5, 15, 10, 0, '2018-06-14 15:08:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 15:08:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 50, 5, 15, 10, 0, '2018-06-14 15:09:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 80, 5, 15, 10, 0, '2018-06-14 15:09:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 55, 5, 15, 10, 0, '2018-06-14 15:10:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 85, 5, 15, 10, 1, '2018-06-14 15:10:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 15:11:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 20, 5, 15, 10, 0, '2018-06-14 15:11:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 75, 5, 15, 10, 0, '2018-06-14 15:12:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 15:12:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 3, '2018-06-14 15:13:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 15:13:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 0, 5, 15, 10, 4, '2018-06-14 15:14:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 5, 5, 15, 10, 4, '2018-06-14 15:14:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 100, 5, 15, 10, 4, '2018-06-14 15:15:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 10, 5, 15, 10, 2, '2018-06-14 15:15:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 15:16:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 90, 5, 15, 10, 2, '2018-06-14 15:16:32', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 95, 5, 15, 10, 3, '2018-06-14 15:17:02', 0, 85);
INSERT INTO `sfc_link_monitor` VALUES (1, 25, 5, 15, 10, 0, '2018-06-14 15:17:32', 0, 85);
COMMIT;

-- ----------------------------
-- Table structure for sfc_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sfc_monitor`;
CREATE TABLE `sfc_monitor` (
  `sfcId` int(11) DEFAULT NULL,
  `current_time` datetime DEFAULT NULL,
  `package_receive` float DEFAULT NULL,
  `package_loss` float DEFAULT NULL,
  `package_deal` float DEFAULT NULL,
  `throughput` float DEFAULT NULL,
  `alarm_level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_monitor
-- ----------------------------
BEGIN;
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:39:30', 11, 3, 8, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:40:00', 23, 5, 18, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:40:30', 28, 13, 15, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:41:00', 127, 25, 102, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:41:30', 13, 10, 3, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:42:00', 119, 20, 99, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:42:30', 32, 4, 28, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:43:00', 109, 19, 90, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:43:30', 108, 16, 92, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:44:00', 118, 7, 111, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:44:30', 32, 26, 6, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:45:00', 20, 3, 17, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:45:30', 107, 10, 97, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:46:00', 100, 7, 93, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:46:30', 113, 3, 110, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:47:00', 22, 10, 12, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:47:30', 22, 2, 20, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:48:00', 28, 11, 17, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:48:30', 101, 7, 94, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:49:00', 105, 0, 105, 1, 0);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:49:30', 16, 29, -13, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:50:00', 39, 20, 19, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:50:30', 15, 17, -2, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:51:00', 36, 28, 8, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:51:30', 102, 5, 97, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:52:00', 112, 28, 84, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:52:30', 30, 22, 8, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:53:00', 121, 10, 111, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:53:30', 112, 27, 85, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:54:00', 11, 4, 7, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:54:30', 27, 1, 26, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:55:00', 105, 4, 101, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:55:30', 29, 11, 18, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:56:00', 34, 9, 25, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:56:30', 21, 28, -7, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:57:00', 11, 27, -16, -1, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:57:30', 104, 8, 96, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:58:00', 37, 11, 26, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:58:30', 125, 22, 103, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:59:00', 22, 7, 15, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 09:59:30', 35, 27, 8, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:00:00', 34, 24, 10, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:00:30', 37, 23, 14, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:01:00', 29, 25, 4, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:01:30', 24, 17, 7, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:02:00', 102, 10, 92, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:02:30', 16, 3, 13, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:03:00', 32, 17, 15, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:03:30', 114, 27, 87, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:04:00', 103, 3, 100, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:04:30', 106, 9, 97, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:05:00', 120, 28, 92, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:05:30', 127, 18, 109, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:06:00', 120, 22, 98, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:06:30', 40, 15, 25, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:07:00', 28, 19, 9, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:07:30', 20, 21, -1, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:08:00', 31, 2, 29, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:08:30', 129, 9, 120, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:09:00', 100, 21, 79, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:09:30', 38, 19, 19, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:10:00', 34, 18, 16, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:10:30', 39, 13, 26, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:11:00', 35, 15, 20, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:11:30', 119, 18, 101, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:12:00', 30, 14, 16, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:12:30', 103, 5, 98, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:13:00', 14, 1, 13, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:13:30', 21, 15, 6, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:14:00', 114, 17, 97, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:14:30', 117, 5, 112, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:15:00', 14, 15, -1, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:15:30', 118, 28, 90, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:16:00', 24, 19, 5, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:16:30', 113, 8, 105, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:17:00', 26, 11, 15, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:17:30', 104, 29, 75, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:18:00', 121, 2, 119, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:18:30', 38, 16, 22, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:19:00', 119, 29, 90, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:19:30', 37, 8, 29, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:20:00', 115, 28, 87, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:20:30', 14, 2, 12, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:21:00', 123, 27, 96, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:21:30', 128, 2, 126, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:22:00', 125, 21, 104, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:22:30', 26, 21, 5, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:23:00', 104, 7, 97, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:23:30', 39, 0, 39, 1, 0);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:24:00', 100, 21, 79, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:24:30', 23, 8, 15, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:25:00', 127, 21, 106, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:25:30', 112, 13, 99, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:26:00', 18, 20, -2, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:26:30', 14, 18, -4, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:27:00', 31, 29, 2, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:27:30', 17, 23, -6, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:28:00', 116, 8, 108, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:28:30', 101, 5, 96, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:29:00', 109, 29, 80, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:34:30', 105, 1, 104, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:35:00', 113, 26, 87, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:35:30', 102, 13, 89, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:36:00', 123, 21, 102, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:36:30', 39, 18, 21, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:37:00', 108, 5, 103, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:37:30', 107, 3, 104, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:38:00', 26, 8, 18, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:38:30', 114, 1, 113, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:39:00', 101, 28, 73, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:39:30', 17, 1, 16, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:40:00', 33, 22, 11, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:40:30', 36, 11, 25, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:41:00', 40, 8, 32, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:41:30', 124, 13, 111, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:42:00', 33, 16, 17, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:42:30', 115, 14, 101, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:43:01', 125, 25, 100, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:43:31', 20, 25, -5, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:44:01', 20, 4, 16, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:44:31', 124, 23, 101, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:45:01', 40, 24, 16, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:45:31', 35, 18, 17, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:46:01', 30, 20, 10, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:46:31', 106, 15, 91, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:47:01', 105, 5, 100, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:47:31', 103, 5, 98, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:48:01', 12, 25, -13, -1, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:48:31', 21, 18, 3, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:49:01', 112, 21, 91, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:49:31', 31, 4, 27, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 10:50:01', 19, 5, 14, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:05:32', 108, 21, 87, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:06:02', 15, 28, -13, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:06:32', 40, 8, 32, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:07:02', 33, 7, 26, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:07:32', 25, 9, 16, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:08:02', 28, 29, -1, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:08:32', 116, 8, 108, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:09:02', 122, 17, 105, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:09:32', 37, 28, 9, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:10:02', 103, 24, 79, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:10:32', 119, 5, 114, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:11:02', 32, 19, 13, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:11:32', 114, 6, 108, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:12:02', 17, 8, 9, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:12:32', 109, 12, 97, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:13:02', 25, 19, 6, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:13:32', 22, 29, -7, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:14:02', 112, 25, 87, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:14:32', 119, 26, 93, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:15:02', 36, 29, 7, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:15:32', 120, 16, 104, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:16:02', 11, 13, -2, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:16:32', 16, 22, -6, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:17:02', 35, 2, 33, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:17:32', 32, 6, 26, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:18:02', 24, 20, 4, 0, 3);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:18:32', 15, 18, -3, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:19:02', 103, 7, 96, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:19:32', 12, 12, 0, 0, 2);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:20:02', 118, 4, 114, 0, 1);
INSERT INTO `sfc_monitor` VALUES (1, '2018-06-14 15:20:32', 13, 6, 7, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for sfc_node
-- ----------------------------
DROP TABLE IF EXISTS `sfc_node`;
CREATE TABLE `sfc_node` (
  `sfcId` int(11) DEFAULT NULL,
  `vnfd` varchar(50) DEFAULT NULL,
  `vnfdId` int(5) DEFAULT NULL,
  `vnfName` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_node
-- ----------------------------
BEGIN;
INSERT INTO `sfc_node` VALUES (2, '0ade0d5f34468fc49687c033a699625d', 0, 'vnf2');
INSERT INTO `sfc_node` VALUES (2, '1349e31797dddc809922d167749822fc', 1, 'vnf1');
INSERT INTO `sfc_node` VALUES (2, '1349e31797dddc809922d167749822fc', 2, 'vnf1');
COMMIT;

-- ----------------------------
-- Table structure for sfc_node_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sfc_node_monitor`;
CREATE TABLE `sfc_node_monitor` (
  `sfcId` int(11) DEFAULT NULL,
  `vnfId` int(11) DEFAULT NULL,
  `current_time` datetime DEFAULT NULL,
  `vnfd` varchar(50) DEFAULT NULL,
  `cpu_util_rate` float DEFAULT NULL,
  `memory_util_rate` float DEFAULT NULL,
  `storage_util_rate` float DEFAULT NULL,
  `package_receive` float DEFAULT NULL,
  `package_deal` float DEFAULT NULL,
  `cpu_threshold_up` float DEFAULT NULL,
  `memory_threshold_up` float DEFAULT NULL,
  `storage_threshold_up` float DEFAULT NULL,
  `alarm_level` int(11) DEFAULT NULL,
  `cpu_threshold_down` float DEFAULT NULL,
  `memory_threshold_down` float DEFAULT NULL,
  `storage_threshold_down` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sfc_node_monitor
-- ----------------------------
BEGIN;
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:39:30', '3bb03f33cb3797c4ab123b055e1359c5', 70, 80, 90, 108, 99, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:40:00', '3bb03f33cb3797c4ab123b055e1359c5', 65, 85, 100, 106, 97, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:40:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 70, 15, 38, 36, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:41:00', '3bb03f33cb3797c4ab123b055e1359c5', 0, 50, 50, 19, 10, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:41:30', '3bb03f33cb3797c4ab123b055e1359c5', 50, 10, 20, 38, 33, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:42:00', '3bb03f33cb3797c4ab123b055e1359c5', 90, 85, 100, 103, 97, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:42:30', '3bb03f33cb3797c4ab123b055e1359c5', 70, 85, 80, 112, 108, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:43:00', '3bb03f33cb3797c4ab123b055e1359c5', 65, 100, 80, 127, 126, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:43:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 90, 85, 108, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:44:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 80, 90, 129, 125, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:44:30', '3bb03f33cb3797c4ab123b055e1359c5', 40, 40, 75, 21, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:45:00', '3bb03f33cb3797c4ab123b055e1359c5', 90, 95, 90, 117, 112, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:45:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 70, 80, 110, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:46:00', '3bb03f33cb3797c4ab123b055e1359c5', 60, 5, 50, 28, 24, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:46:30', '3bb03f33cb3797c4ab123b055e1359c5', 45, 70, 20, 23, 18, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:47:00', '3bb03f33cb3797c4ab123b055e1359c5', 60, 70, 85, 126, 124, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:47:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 80, 90, 126, 122, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:48:00', '3bb03f33cb3797c4ab123b055e1359c5', 25, 50, 50, 26, 22, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:48:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 95, 85, 101, 93, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:49:00', '3bb03f33cb3797c4ab123b055e1359c5', 25, 25, 10, 27, 25, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:49:30', '3bb03f33cb3797c4ab123b055e1359c5', 45, 20, 70, 16, 9, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:50:00', '3bb03f33cb3797c4ab123b055e1359c5', 95, 80, 100, 129, 126, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:50:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 100, 95, 119, 110, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:51:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 90, 95, 105, 96, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:51:30', '3bb03f33cb3797c4ab123b055e1359c5', 25, 30, 60, 37, 28, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:52:00', '3bb03f33cb3797c4ab123b055e1359c5', 60, 80, 100, 129, 121, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:52:30', '3bb03f33cb3797c4ab123b055e1359c5', 20, 55, 50, 23, 21, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:53:00', '3bb03f33cb3797c4ab123b055e1359c5', 95, 90, 80, 129, 127, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:53:30', '3bb03f33cb3797c4ab123b055e1359c5', 85, 95, 90, 102, 93, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:54:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 70, 95, 105, 100, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:54:30', '3bb03f33cb3797c4ab123b055e1359c5', 80, 100, 80, 129, 121, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:55:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 80, 90, 121, 112, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:55:30', '3bb03f33cb3797c4ab123b055e1359c5', 100, 85, 100, 117, 113, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:56:00', '3bb03f33cb3797c4ab123b055e1359c5', 65, 95, 85, 117, 111, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:56:30', '3bb03f33cb3797c4ab123b055e1359c5', 45, 10, 80, 30, 25, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:57:00', '3bb03f33cb3797c4ab123b055e1359c5', 95, 70, 95, 127, 119, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:57:30', '3bb03f33cb3797c4ab123b055e1359c5', 5, 55, 5, 21, 17, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:58:00', '3bb03f33cb3797c4ab123b055e1359c5', 15, 5, 40, 39, 37, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:58:30', '3bb03f33cb3797c4ab123b055e1359c5', 85, 85, 100, 125, 120, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:59:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 95, 95, 129, 122, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 09:59:30', '3bb03f33cb3797c4ab123b055e1359c5', 35, 25, 80, 25, 22, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:00:00', '3bb03f33cb3797c4ab123b055e1359c5', 80, 80, 80, 107, 103, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:00:30', '3bb03f33cb3797c4ab123b055e1359c5', 50, 40, 5, 22, 17, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:01:00', '3bb03f33cb3797c4ab123b055e1359c5', 55, 70, 15, 35, 35, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:01:30', '3bb03f33cb3797c4ab123b055e1359c5', 90, 100, 85, 125, 120, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:02:00', '3bb03f33cb3797c4ab123b055e1359c5', 50, 65, 45, 28, 23, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:02:30', '3bb03f33cb3797c4ab123b055e1359c5', 85, 95, 90, 129, 122, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:03:00', '3bb03f33cb3797c4ab123b055e1359c5', 40, 60, 5, 12, 3, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:03:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 90, 90, 105, 97, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:04:00', '3bb03f33cb3797c4ab123b055e1359c5', 5, 35, 20, 16, 7, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:04:30', '3bb03f33cb3797c4ab123b055e1359c5', 70, 95, 100, 115, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:05:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 85, 80, 119, 111, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:05:30', '3bb03f33cb3797c4ab123b055e1359c5', 70, 90, 95, 106, 104, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:06:00', '3bb03f33cb3797c4ab123b055e1359c5', 60, 75, 95, 129, 127, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:06:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 95, 90, 108, 107, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:07:00', '3bb03f33cb3797c4ab123b055e1359c5', 30, 0, 70, 39, 37, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:07:30', '3bb03f33cb3797c4ab123b055e1359c5', 20, 25, 25, 38, 31, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:08:00', '3bb03f33cb3797c4ab123b055e1359c5', 0, 20, 50, 29, 26, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:08:30', '3bb03f33cb3797c4ab123b055e1359c5', 10, 70, 35, 29, 29, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:09:00', '3bb03f33cb3797c4ab123b055e1359c5', 30, 70, 20, 17, 11, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:09:30', '3bb03f33cb3797c4ab123b055e1359c5', 55, 60, 45, 22, 22, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:10:00', '3bb03f33cb3797c4ab123b055e1359c5', 55, 40, 50, 29, 27, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:10:30', '3bb03f33cb3797c4ab123b055e1359c5', 10, 40, 80, 29, 29, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:11:00', '3bb03f33cb3797c4ab123b055e1359c5', 10, 35, 40, 39, 37, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:11:30', '3bb03f33cb3797c4ab123b055e1359c5', 65, 85, 80, 119, 116, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:12:00', '3bb03f33cb3797c4ab123b055e1359c5', 45, 0, 5, 26, 17, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:12:30', '3bb03f33cb3797c4ab123b055e1359c5', 65, 85, 80, 129, 122, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:13:00', '3bb03f33cb3797c4ab123b055e1359c5', 15, 55, 35, 40, 33, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:13:30', '3bb03f33cb3797c4ab123b055e1359c5', 15, 60, 80, 30, 29, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:14:00', '3bb03f33cb3797c4ab123b055e1359c5', 10, 35, 5, 24, 23, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:14:30', '3bb03f33cb3797c4ab123b055e1359c5', 25, 25, 0, 27, 25, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:15:00', '3bb03f33cb3797c4ab123b055e1359c5', 20, 55, 15, 24, 23, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:15:30', '3bb03f33cb3797c4ab123b055e1359c5', 70, 75, 85, 128, 126, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:16:00', '3bb03f33cb3797c4ab123b055e1359c5', 20, 30, 75, 25, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:16:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 45, 5, 26, 17, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:17:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 90, 90, 118, 118, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:17:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 75, 100, 100, 94, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:18:00', '3bb03f33cb3797c4ab123b055e1359c5', 45, 30, 0, 16, 14, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:18:30', '3bb03f33cb3797c4ab123b055e1359c5', 55, 50, 10, 28, 21, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:19:00', '3bb03f33cb3797c4ab123b055e1359c5', 80, 80, 90, 110, 105, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:19:30', '3bb03f33cb3797c4ab123b055e1359c5', 80, 100, 85, 117, 113, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:20:00', '3bb03f33cb3797c4ab123b055e1359c5', 70, 85, 95, 110, 102, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:20:30', '3bb03f33cb3797c4ab123b055e1359c5', 100, 100, 90, 113, 111, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:21:00', '3bb03f33cb3797c4ab123b055e1359c5', 30, 50, 10, 27, 22, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:21:30', '3bb03f33cb3797c4ab123b055e1359c5', 35, 15, 5, 39, 32, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:22:00', '3bb03f33cb3797c4ab123b055e1359c5', 45, 60, 55, 22, 21, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:22:30', '3bb03f33cb3797c4ab123b055e1359c5', 40, 65, 30, 22, 16, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:23:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 80, 100, 123, 122, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:23:30', '3bb03f33cb3797c4ab123b055e1359c5', 100, 100, 95, 108, 104, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:24:00', '3bb03f33cb3797c4ab123b055e1359c5', 40, 25, 55, 38, 33, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:24:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 95, 90, 116, 109, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:25:00', '3bb03f33cb3797c4ab123b055e1359c5', 10, 20, 55, 25, 19, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:25:30', '3bb03f33cb3797c4ab123b055e1359c5', 65, 70, 90, 103, 97, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:26:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 75, 80, 124, 119, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:26:30', '3bb03f33cb3797c4ab123b055e1359c5', 75, 90, 80, 118, 116, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:27:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 90, 80, 105, 96, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:27:30', '3bb03f33cb3797c4ab123b055e1359c5', 0, 40, 5, 17, 17, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:28:00', '3bb03f33cb3797c4ab123b055e1359c5', 85, 100, 80, 117, 114, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:28:30', '3bb03f33cb3797c4ab123b055e1359c5', 10, 15, 25, 26, 17, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:29:00', '3bb03f33cb3797c4ab123b055e1359c5', 30, 25, 65, 19, 12, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:29:30', '3bb03f33cb3797c4ab123b055e1359c5', 75, 90, 90, 121, 113, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:30:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 80, 100, 126, 125, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:30:30', '3bb03f33cb3797c4ab123b055e1359c5', 60, 100, 95, 102, 98, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:31:00', '3bb03f33cb3797c4ab123b055e1359c5', 80, 85, 90, 104, 104, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:31:30', '3bb03f33cb3797c4ab123b055e1359c5', 15, 55, 5, 32, 25, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:32:00', '3bb03f33cb3797c4ab123b055e1359c5', 55, 60, 35, 27, 23, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:32:30', '3bb03f33cb3797c4ab123b055e1359c5', 45, 5, 40, 26, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:33:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 100, 95, 114, 113, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:33:30', '3bb03f33cb3797c4ab123b055e1359c5', 95, 70, 90, 109, 101, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:34:00', '3bb03f33cb3797c4ab123b055e1359c5', 90, 75, 90, 102, 95, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:34:30', '3bb03f33cb3797c4ab123b055e1359c5', 90, 100, 95, 119, 113, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:35:00', '3bb03f33cb3797c4ab123b055e1359c5', 65, 95, 100, 122, 121, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:35:30', '3bb03f33cb3797c4ab123b055e1359c5', 20, 65, 5, 18, 9, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:36:00', '3bb03f33cb3797c4ab123b055e1359c5', 75, 70, 85, 100, 91, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:36:30', '3bb03f33cb3797c4ab123b055e1359c5', 80, 95, 95, 109, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:37:00', '3bb03f33cb3797c4ab123b055e1359c5', 60, 90, 100, 110, 101, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:37:30', '3bb03f33cb3797c4ab123b055e1359c5', 10, 35, 10, 38, 37, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:38:00', '3bb03f33cb3797c4ab123b055e1359c5', 15, 35, 5, 14, 14, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:38:30', '3bb03f33cb3797c4ab123b055e1359c5', 30, 70, 10, 23, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:39:00', '3bb03f33cb3797c4ab123b055e1359c5', 10, 20, 15, 13, 6, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:39:30', '3bb03f33cb3797c4ab123b055e1359c5', 15, 50, 5, 11, 7, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:40:00', '3bb03f33cb3797c4ab123b055e1359c5', 15, 0, 60, 38, 30, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:40:30', '3bb03f33cb3797c4ab123b055e1359c5', 90, 80, 80, 116, 115, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:41:00', '3bb03f33cb3797c4ab123b055e1359c5', 70, 95, 95, 107, 105, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:41:30', '3bb03f33cb3797c4ab123b055e1359c5', 15, 55, 55, 36, 30, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:42:00', '3bb03f33cb3797c4ab123b055e1359c5', 20, 0, 0, 23, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:42:30', '3bb03f33cb3797c4ab123b055e1359c5', 0, 20, 45, 33, 28, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:43:00', '3bb03f33cb3797c4ab123b055e1359c5', 100, 75, 90, 109, 104, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:43:30', '3bb03f33cb3797c4ab123b055e1359c5', 10, 30, 10, 22, 21, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:44:00', '3bb03f33cb3797c4ab123b055e1359c5', 90, 95, 85, 122, 121, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:44:31', '3bb03f33cb3797c4ab123b055e1359c5', 95, 95, 95, 121, 113, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:45:01', '3bb03f33cb3797c4ab123b055e1359c5', 60, 70, 90, 103, 99, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:45:31', '3bb03f33cb3797c4ab123b055e1359c5', 25, 0, 40, 36, 28, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:46:01', '3bb03f33cb3797c4ab123b055e1359c5', 30, 50, 20, 30, 26, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:46:31', '3bb03f33cb3797c4ab123b055e1359c5', 55, 35, 10, 21, 21, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:47:01', '3bb03f33cb3797c4ab123b055e1359c5', 45, 0, 30, 26, 18, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:47:31', '3bb03f33cb3797c4ab123b055e1359c5', 70, 85, 100, 107, 99, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:48:01', '3bb03f33cb3797c4ab123b055e1359c5', 60, 90, 100, 110, 104, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:48:31', '3bb03f33cb3797c4ab123b055e1359c5', 35, 55, 45, 27, 27, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:49:01', '3bb03f33cb3797c4ab123b055e1359c5', 65, 85, 95, 127, 121, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:49:31', '3bb03f33cb3797c4ab123b055e1359c5', 35, 50, 55, 12, 9, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 10:50:01', '3bb03f33cb3797c4ab123b055e1359c5', 85, 70, 95, 109, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:05:32', '3bb03f33cb3797c4ab123b055e1359c5', 70, 80, 85, 110, 103, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:06:02', '3bb03f33cb3797c4ab123b055e1359c5', 55, 45, 0, 19, 14, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:06:32', '3bb03f33cb3797c4ab123b055e1359c5', 85, 70, 100, 116, 107, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:07:02', '3bb03f33cb3797c4ab123b055e1359c5', 70, 100, 80, 106, 100, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:07:32', '3bb03f33cb3797c4ab123b055e1359c5', 25, 65, 65, 29, 22, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:08:02', '3bb03f33cb3797c4ab123b055e1359c5', 85, 90, 95, 110, 109, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:08:32', '3bb03f33cb3797c4ab123b055e1359c5', 10, 0, 75, 26, 19, 90, 85, 90, 1, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:09:02', '3bb03f33cb3797c4ab123b055e1359c5', 30, 65, 10, 32, 28, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:09:32', '3bb03f33cb3797c4ab123b055e1359c5', 25, 20, 5, 26, 26, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:10:02', '3bb03f33cb3797c4ab123b055e1359c5', 95, 85, 95, 126, 122, 90, 85, 90, 2, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:10:32', '3bb03f33cb3797c4ab123b055e1359c5', 15, 35, 25, 26, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:11:02', '3bb03f33cb3797c4ab123b055e1359c5', 15, 70, 10, 32, 31, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:11:32', '3bb03f33cb3797c4ab123b055e1359c5', 60, 80, 95, 104, 103, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:12:02', '3bb03f33cb3797c4ab123b055e1359c5', 65, 100, 85, 122, 122, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:12:32', '3bb03f33cb3797c4ab123b055e1359c5', 25, 40, 25, 18, 10, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:13:02', '3bb03f33cb3797c4ab123b055e1359c5', 80, 90, 80, 113, 111, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:13:32', '3bb03f33cb3797c4ab123b055e1359c5', 80, 90, 90, 117, 115, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:14:02', '3bb03f33cb3797c4ab123b055e1359c5', 75, 80, 80, 122, 115, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:14:32', '3bb03f33cb3797c4ab123b055e1359c5', 0, 10, 60, 19, 12, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:15:02', '3bb03f33cb3797c4ab123b055e1359c5', 35, 15, 70, 33, 30, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:15:32', '3bb03f33cb3797c4ab123b055e1359c5', 40, 25, 5, 21, 17, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:16:02', '3bb03f33cb3797c4ab123b055e1359c5', 30, 25, 30, 15, 12, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:16:32', '3bb03f33cb3797c4ab123b055e1359c5', 60, 70, 25, 26, 19, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:17:02', '3bb03f33cb3797c4ab123b055e1359c5', 15, 55, 35, 31, 27, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:17:32', '3bb03f33cb3797c4ab123b055e1359c5', 85, 90, 100, 124, 117, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:18:02', '3bb03f33cb3797c4ab123b055e1359c5', 70, 90, 95, 119, 112, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:18:32', '3bb03f33cb3797c4ab123b055e1359c5', 20, 0, 15, 36, 31, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:19:02', '3bb03f33cb3797c4ab123b055e1359c5', 80, 70, 85, 100, 98, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:19:32', '3bb03f33cb3797c4ab123b055e1359c5', 75, 95, 90, 115, 108, 90, 85, 90, 0, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:20:02', '3bb03f33cb3797c4ab123b055e1359c5', 0, 35, 45, 11, 8, 90, 85, 90, 3, 10, 15, 5);
INSERT INTO `sfc_node_monitor` VALUES (1, 0, '2018-06-14 15:20:32', '3bb03f33cb3797c4ab123b055e1359c5', 60, 25, 50, 23, 20, 90, 85, 90, 0, 10, 15, 5);
COMMIT;

-- ----------------------------
-- Table structure for sub_link
-- ----------------------------
DROP TABLE IF EXISTS `sub_link`;
CREATE TABLE `sub_link` (
  `from` varchar(20) DEFAULT NULL,
  `to` varchar(20) DEFAULT NULL,
  `fromDpId` varchar(50) DEFAULT NULL,
  `toDpId` varchar(50) DEFAULT NULL,
  `bandwidth` int(10) DEFAULT NULL,
  `delay` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sub_link
-- ----------------------------
BEGIN;
INSERT INTO `sub_link` VALUES ('switch1', 'switch2', 'switch1-dp1', 'switch2-dp1', 80, 3);
INSERT INTO `sub_link` VALUES ('switch1', 'switch3', 'switch1-dp2', 'switch3-dp1', 60, 3);
INSERT INTO `sub_link` VALUES ('switch2', 'switch4', 'switch2-dp2', 'switch4-dp1', 70, 3);
INSERT INTO `sub_link` VALUES ('switch2', 'switch5', 'switch2-dp3', 'switch5-dp1', 90, 3);
INSERT INTO `sub_link` VALUES ('switch3', 'switch5', 'switch3-dp2', 'switch5-dp2', 30, 3);
INSERT INTO `sub_link` VALUES ('switch4', 'switch6', 'switch4-dp2', 'switch6-dp1', 50, 3);
INSERT INTO `sub_link` VALUES ('switch4', 'switch7', 'switch4-dp3', 'switch7-dp1', 100, 3);
INSERT INTO `sub_link` VALUES ('switch3', 'switch8', 'switch3-dp3', 'switch8-dp1', 20, 3);
INSERT INTO `sub_link` VALUES ('switch5', 'switch7', 'switch5-dp3', 'switch7-dp2', 70, 3);
INSERT INTO `sub_link` VALUES ('switch6', 'switch9', 'switch6-dp2', 'switch9-dp1', 20, 3);
INSERT INTO `sub_link` VALUES ('switch7', 'switch9', 'switch7-dp3', 'switch9-dp2', 90, 3);
INSERT INTO `sub_link` VALUES ('switch8', 'switch10', 'switch8-dp3', 'switch10-dp1', 50, 3);
INSERT INTO `sub_link` VALUES ('switch8', 'switch11', 'switch8-dp4', 'switch11-dp1', 40, 3);
INSERT INTO `sub_link` VALUES ('switch9', 'switch10', 'switch9-dp3', 'switch10-dp2', 30, 3);
INSERT INTO `sub_link` VALUES ('switch10', 'switch11', 'switch10-dp3', 'switch11-dp2', 70, 3);
INSERT INTO `sub_link` VALUES ('switch11', 'switch12', 'switch11-dp3', 'switch12-dp1', 50, 3);
INSERT INTO `sub_link` VALUES ('switch10', 'switch13', 'switch10-dp4', 'switch13-dp1', 60, 3);
INSERT INTO `sub_link` VALUES ('switch11', 'switch13', 'switch11-dp4', 'switch13-dp2', 80, 3);
INSERT INTO `sub_link` VALUES ('switch12', 'switch14', 'switch12-dp2', 'switch14-dp1', 70, 3);
INSERT INTO `sub_link` VALUES ('switch13', 'switch14', 'switch13-dp3', 'switch14-dp2', 50, 3);
INSERT INTO `sub_link` VALUES ('switch1', 'server1', 'switch1-dp3', 'server1-dp1', 90, 3);
INSERT INTO `sub_link` VALUES ('switch2', 'server2', 'switch2-dp4', 'server2-dp1', 50, 3);
INSERT INTO `sub_link` VALUES ('switch3', 'server3', 'switch3-dp4', 'server3-dp1', 80, 3);
INSERT INTO `sub_link` VALUES ('switch4', 'server4', 'switch4-dp4', 'server4-dp1', 70, 3);
INSERT INTO `sub_link` VALUES ('switch5', 'server5', 'switch5-dp5', 'server5-dp1', 60, 3);
INSERT INTO `sub_link` VALUES ('switch6', 'server6', 'switch6-dp3', 'server6-dp1', 20, 3);
INSERT INTO `sub_link` VALUES ('switch7', 'server7', 'switch7-dp4', 'server7-dp1', 50, 3);
INSERT INTO `sub_link` VALUES ('switch8', 'server8', 'switch8-dp5', 'server8-dp1', 20, 3);
INSERT INTO `sub_link` VALUES ('switch9', 'server9', 'switch9-dp4', 'server9-dp1', 40, 3);
INSERT INTO `sub_link` VALUES ('switch10', 'server10', 'switch10-dp5', 'server10-dp1', 30, 3);
INSERT INTO `sub_link` VALUES ('switch11', 'server11', 'switch11-dp5', 'server11-dp1', 10, 3);
INSERT INTO `sub_link` VALUES ('switch12', 'server12', 'switch12-dp3', 'server12-dp1', 90, 3);
INSERT INTO `sub_link` VALUES ('switch13', 'server13', 'switch13-dp4', 'server13-dp1', 80, 3);
INSERT INTO `sub_link` VALUES ('switch14', 'server14', 'switch14-dp3', 'server14-dp1', 30, 3);
COMMIT;

-- ----------------------------
-- Table structure for temp_vnfd
-- ----------------------------
DROP TABLE IF EXISTS `temp_vnfd`;
CREATE TABLE `temp_vnfd` (
  `vnfd` varchar(50) NOT NULL,
  `numVirtualCpu` int(3) DEFAULT NULL,
  `virtualMemSize` int(5) DEFAULT NULL,
  `sizeOfStorage` int(5) DEFAULT NULL,
  `swImageDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vnfd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '123456');
COMMIT;

-- ----------------------------
-- Table structure for vim
-- ----------------------------
DROP TABLE IF EXISTS `vim`;
CREATE TABLE `vim` (
  `serverId` varchar(20) NOT NULL,
  `cpu` int(11) DEFAULT NULL,
  `memory` float(255,0) DEFAULT NULL,
  `storage` float DEFAULT NULL,
  `vimId` varchar(20) DEFAULT NULL,
  `virtualEnvironment` varchar(50) DEFAULT NULL,
  `vimIp` varchar(20) DEFAULT NULL,
  `vimPort` int(10) DEFAULT NULL,
  `typeOfStorage` varchar(20) DEFAULT NULL,
  `cpuArchiecture` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`serverId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vim
-- ----------------------------
BEGIN;
INSERT INTO `vim` VALUES ('server1', 32, 200, 500, 'vim1', 've-a', '10.108.128.49', 8089, 't-a', 'x86');
INSERT INTO `vim` VALUES ('server2', 32, 200, 500, 'vim1', 've-b', '10.108.128.49', 8089, 't-b', 'x64');
INSERT INTO `vim` VALUES ('server3', 32, 200, 500, 'vim1', 've-c', '10.108.128.49', 8089, 't-c', 'x64');
INSERT INTO `vim` VALUES ('server4', 32, 200, 500, 'vim1', 've-d', '10.108.128.49', 8089, 't-d', 'x86');
INSERT INTO `vim` VALUES ('server5', 32, 200, 500, 'vim1', 've-e', '10.108.128.49', 8089, 't-e', 'x64');
COMMIT;

-- ----------------------------
-- Table structure for vnf
-- ----------------------------
DROP TABLE IF EXISTS `vnf`;
CREATE TABLE `vnf` (
  `vnfd` varchar(50) DEFAULT NULL,
  `vnfName` varchar(30) NOT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `vnfProductName` varchar(30) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL,
  `version` varchar(30) DEFAULT NULL,
  `numVirtualCpu` int(3) DEFAULT NULL,
  `sizeOfStorage` int(5) DEFAULT NULL,
  `virtualMemSize` int(5) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`vnfName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vnf
-- ----------------------------
BEGIN;
INSERT INTO `vnf` VALUES ('1349e31797dddc809922d167749822fc', 'vnf1', 'admin', 'proxy', 'B', '3.2.1', 2, 40, 30, '2018-06-14 11:18:00');
INSERT INTO `vnf` VALUES ('0ade0d5f34468fc49687c033a699625d', 'vnf2', 'admin', 'dpi', 'C', '2.1.3', 4, 20, 20, '2018-09-06 09:56:02');
COMMIT;

-- ----------------------------
-- Table structure for vnf_deploy
-- ----------------------------
DROP TABLE IF EXISTS `vnf_deploy`;
CREATE TABLE `vnf_deploy` (
  `vnfId` int(11) DEFAULT NULL,
  `sfcId` int(11) DEFAULT NULL,
  `vnfd` varchar(50) DEFAULT NULL,
  `nodeId` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vnf_deploy
-- ----------------------------
BEGIN;
INSERT INTO `vnf_deploy` VALUES (0, 2, '0ade0d5f34468fc49687c033a699625d', 'server8');
INSERT INTO `vnf_deploy` VALUES (1, 2, '1349e31797dddc809922d167749822fc', 'server11');
INSERT INTO `vnf_deploy` VALUES (2, 2, '1349e31797dddc809922d167749822fc', 'server10');
COMMIT;

-- ----------------------------
-- Table structure for vnfd
-- ----------------------------
DROP TABLE IF EXISTS `vnfd`;
CREATE TABLE `vnfd` (
  `vnfd` varchar(50) NOT NULL,
  `version` varchar(10) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `vnfProductName` varchar(20) DEFAULT NULL,
  `virtualMemSize` int(5) DEFAULT NULL,
  `cpuArchiecture` varchar(10) DEFAULT NULL,
  `numVirtualCpu` int(3) DEFAULT NULL,
  `virtualCpuClock` float DEFAULT NULL,
  `typeOfStorage` varchar(10) DEFAULT NULL,
  `sizeOfStorage` int(5) DEFAULT NULL,
  `vnfSoftwareVersion` varchar(10) DEFAULT NULL,
  `swImageDesc` varchar(50) DEFAULT NULL,
  `cpd` varchar(100) DEFAULT NULL,
  `virtualEnviroment` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`vnfd`),
  KEY `companyIndex` (`company`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vnfd
-- ----------------------------
BEGIN;
INSERT INTO `vnfd` VALUES ('0ade0d5f34468fc49687c033a699625d', '2.1.3', 'C', 'dpi', 20, 'x64', 4, 2.2, 'object', 20, '2.2.2', '/local', 'cp-x', 've-c', NULL, NULL);
INSERT INTO `vnfd` VALUES ('1349e31797dddc809922d167749822fc', '3.2.1', 'B', 'proxy', 30, 'x86', 2, 2.8, 'object', 40, '2.1.1', '/local', 'cp-x', 've-b', NULL, NULL);
INSERT INTO `vnfd` VALUES ('554abbb96074260fe6ea882172ade19b', '2.1.1', 'C', 'firewall', 50, 'x64', 2, 2.4, 'object', 30, '1.4.1', '/local', 'cp-x', 've-l', NULL, NULL);
INSERT INTO `vnfd` VALUES ('91ef58cb2be74ae1c86b7f8764e39478', '2.2.1', 'D', 'firewall', 30, 'x86', 8, 2.2, 'object', 30, '3.1.1', '/local', 'cp-x', 've-a', NULL, NULL);
INSERT INTO `vnfd` VALUES ('935bf2977162b149b92e1e6dc1f874ed', '6.2.4', 'B', 'firewall', 25, 'x86', 4, 2.5, 'object', 15, '5.2.1', '/local', 'cp-x', 've-c', NULL, NULL);
INSERT INTO `vnfd` VALUES ('a624aa963dfd75a0c1964ca5a3c8d6e3', '1.2.1', 'E', 'proxy', 40, 'x64', 2, 2.5, 'object', 20, '2.3.2', '/local', 'cp-x', 've-d', NULL, NULL);
INSERT INTO `vnfd` VALUES ('bca36d5f1945d0f9e66703293aa90846', '3.1.3', 'F', 'dpi', 10, 'x86', 4, 2.2, 'object', 20, '2.3.4', '/local', 'cp-x', 've-a', NULL, NULL);
INSERT INTO `vnfd` VALUES ('d24d6bb0f6938d1594053fdaf52f895f', '2.3.4', 'G', 'firewall', 20, 'x86', 4, 2.5, 't-a', 20, '1.1.3', 'D://fileUpload//vnfd.zip', NULL, 've-a', NULL, NULL);
INSERT INTO `vnfd` VALUES ('fecfe7baf31161776279f5174cfb1ed7', '3.1.3', 'D', 'dpi', 15, 'x64', 4, 2.2, 'object', 20, '2.9.2', '/local', 'cp-x', 've-f', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for vnfd_relevance
-- ----------------------------
DROP TABLE IF EXISTS `vnfd_relevance`;
CREATE TABLE `vnfd_relevance` (
  `vnfd` varchar(50) NOT NULL,
  `num` int(5) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`vnfd`),
  KEY `vnfdIndex` (`vnfd`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vnfd_relevance
-- ----------------------------
BEGIN;
INSERT INTO `vnfd_relevance` VALUES ('0ade0d5f34468fc49687c033a699625d', 1, 'working');
INSERT INTO `vnfd_relevance` VALUES ('1349e31797dddc809922d167749822fc', 1, 'working');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
