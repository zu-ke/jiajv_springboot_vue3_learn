/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : springboot_vue3_project

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 11/05/2024 09:58:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for furn
-- ----------------------------
DROP TABLE IF EXISTS `furn`;
CREATE TABLE `furn`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `maker` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `sales` int UNSIGNED NOT NULL,
  `stock` int UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 307 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of furn
-- ----------------------------
INSERT INTO `furn` VALUES (5, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (6, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (7, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (8, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (9, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (10, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (11, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (12, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (13, '小凳子', 'JD', 180.00, 666, 7);
INSERT INTO `furn` VALUES (200, '杯子1', 'zk厂', 9.99, 10, 100);
INSERT INTO `furn` VALUES (300, '杯子1', 'zk厂', 9.99, 10, 100);
INSERT INTO `furn` VALUES (301, '杯子1', 'zk厂', 9.99, 10, 100);
INSERT INTO `furn` VALUES (302, '杯子1', 'zk厂', 9.99, 10, 100);
INSERT INTO `furn` VALUES (303, '1', '1', 1.00, 1, 1);
INSERT INTO `furn` VALUES (304, '1', '1', 1.00, 1, 1);
INSERT INTO `furn` VALUES (305, '2', '2', 2.00, 2, 2);
INSERT INTO `furn` VALUES (306, '1', '1', 1.00, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
