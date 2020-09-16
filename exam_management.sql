/*
 Navicat Premium Data Transfer

 Source Server         : Yang
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : exam_management

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 11/09/2020 11:02:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('010120100001', '123456', 1);
INSERT INTO `account` VALUES ('010120100002', '123456', 1);
INSERT INTO `account` VALUES ('010120100003', '123456', 0);
INSERT INTO `account` VALUES ('010120100004', '123456', 0);

-- ----------------------------
-- Table structure for const_variable
-- ----------------------------
DROP TABLE IF EXISTS `const_variable`;
CREATE TABLE `const_variable`  (
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of const_variable
-- ----------------------------
INSERT INTO `const_variable` VALUES ('cur_exam_num', '考次2');
INSERT INTO `const_variable` VALUES ('province_code', '001');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_english_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('0001', 'java', 'java');
INSERT INTO `course` VALUES ('0002', 'python', 'python');
INSERT INTO `course` VALUES ('0003', 'c++', 'c++');
INSERT INTO `course` VALUES ('0004', '通信原理', 'tongxingyuanli');

-- ----------------------------
-- Table structure for course_replace_apply
-- ----------------------------
DROP TABLE IF EXISTS `course_replace_apply`;
CREATE TABLE `course_replace_apply`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `course_replace_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_replace_apply
-- ----------------------------

-- ----------------------------
-- Table structure for cra_new_courses
-- ----------------------------
DROP TABLE IF EXISTS `cra_new_courses`;
CREATE TABLE `cra_new_courses`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`apply_id`, `course_code`) USING BTREE,
  INDEX `course_code`(`course_code`) USING BTREE,
  CONSTRAINT `cra_new_courses_ibfk_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cra_new_courses
-- ----------------------------

-- ----------------------------
-- Table structure for cra_old_courses
-- ----------------------------
DROP TABLE IF EXISTS `cra_old_courses`;
CREATE TABLE `cra_old_courses`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`apply_id`, `course_code`) USING BTREE,
  INDEX `course_code`(`course_code`) USING BTREE,
  CONSTRAINT `cra_old_courses_ibfk_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cra_old_courses
-- ----------------------------

-- ----------------------------
-- Table structure for ecn_change_log
-- ----------------------------
DROP TABLE IF EXISTS `ecn_change_log`;
CREATE TABLE `ecn_change_log`  (
  `id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `old_ECN` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_ECN` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `change_state` int(0) NULL DEFAULT NULL,
  `city_opinion` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `root_opinion` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `handle_time` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result_state` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `old_ECN`(`old_ECN`) USING BTREE,
  CONSTRAINT `ecn_change_log_ibfk_1` FOREIGN KEY (`old_ECN`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ecn_change_log
-- ----------------------------

-- ----------------------------
-- Table structure for exam_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_info`;
CREATE TABLE `exam_info`  (
  `exam_num` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_date` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exam_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_info
-- ----------------------------
INSERT INTO `exam_info` VALUES ('202010', '20201015', '20201017');
INSERT INTO `exam_info` VALUES ('202012', '20201215', '20201217');
INSERT INTO `exam_info` VALUES ('考次1', '2020-09-03', '2020-09-18');
INSERT INTO `exam_info` VALUES ('考次2', '2020-09-03', '2020-09-11');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_type` int(0) NULL DEFAULT NULL,
  `valid` int(0) NULL DEFAULT NULL,
  `putin_state` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`exam_card_num`, `course_code`) USING BTREE,
  INDEX `course_code`(`course_code`) USING BTREE,
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('010120100001', '0001', '100', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100001', '0002', '100', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100001', '0003', '100', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100001', '0004', '100', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100002', '0001', '80', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100002', '0002', '80', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100002', '0003', '80', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100002', '0004', '80', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100003', '0001', '90', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100003', '0002', '90', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100003', '0003', '90', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100003', '0004', '90', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100004', '0001', '85', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100004', '0002', '85', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100004', '0003', '85', 6, 1, 1);
INSERT INTO `grade` VALUES ('010120100004', '0004', '85', 6, 1, 1);

-- ----------------------------
-- Table structure for graduate_apply
-- ----------------------------
DROP TABLE IF EXISTS `graduate_apply`;
CREATE TABLE `graduate_apply`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `graduate_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of graduate_apply
-- ----------------------------
INSERT INTO `graduate_apply` VALUES (1, '010120100001', 1, 2, '20200912', NULL);
INSERT INTO `graduate_apply` VALUES (2, '010120100002', 0, 1, '20200911', NULL);
INSERT INTO `graduate_apply` VALUES (3, '010120100003', 0, 1, '20200919', NULL);

-- ----------------------------
-- Table structure for graduate_cert
-- ----------------------------
DROP TABLE IF EXISTS `graduate_cert`;
CREATE TABLE `graduate_cert`  (
  `graduate_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `graduate_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`graduate_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `graduate_cert_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of graduate_cert
-- ----------------------------
INSERT INTO `graduate_cert` VALUES ('100020200903', '010120100001', '20190904');
INSERT INTO `graduate_cert` VALUES ('100020200904', '010120100002', '20180904');
INSERT INTO `graduate_cert` VALUES ('100020200905', '010120100004', '20170904');

-- ----------------------------
-- Table structure for graduate_pre_apply
-- ----------------------------
DROP TABLE IF EXISTS `graduate_pre_apply`;
CREATE TABLE `graduate_pre_apply`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `graduate_pre_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of graduate_pre_apply
-- ----------------------------
INSERT INTO `graduate_pre_apply` VALUES (1, '010120100001', 0, 1, '20200909', NULL);

-- ----------------------------
-- Table structure for info_change_apply
-- ----------------------------
DROP TABLE IF EXISTS `info_change_apply`;
CREATE TABLE `info_change_apply`  (
  `id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `field_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `old_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `change_reason` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `change_state` int(0) NULL DEFAULT NULL,
  `city_opinion` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `root_opinion` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `handle_time` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result_state` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `info_change_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_change_apply
-- ----------------------------

-- ----------------------------
-- Table structure for info_change_log
-- ----------------------------
DROP TABLE IF EXISTS `info_change_log`;
CREATE TABLE `info_change_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `change_content` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `change_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `info_change_log_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_change_log
-- ----------------------------
INSERT INTO `info_change_log` VALUES (1, '010120100001', '1', '644658199908046570', '修改准考证号', '20200912');
INSERT INTO `info_change_log` VALUES (2, '010120100001', '1', '644658199908046570', '修改名字', '20200912');
INSERT INTO `info_change_log` VALUES (3, '010120100002', '1', '644658199908046570', '修改准考证号', '20200912');
INSERT INTO `info_change_log` VALUES (4, '010120100002', '1', '644658199908046570', '修改名字', '20200912');
INSERT INTO `info_change_log` VALUES (5, '010120100003', '1', '644658199908046570', '修改准考证号', '20200912');
INSERT INTO `info_change_log` VALUES (6, '010120100004', '1', '644658199908046570', '修改名字', '20200912');
INSERT INTO `info_change_log` VALUES (7, '010120100004', '1', '644658199908046570', '修改准考证号', '20200912');
INSERT INTO `info_change_log` VALUES (8, '010120100001', '1', '644658199908046570', '修改性别', '20200912');

-- ----------------------------
-- Table structure for info_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `info_operate_log`;
CREATE TABLE `info_operate_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `operator_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operate_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operate_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info_operate_log
-- ----------------------------
INSERT INTO `info_operate_log` VALUES (1, 'wang', '通过李四毕业申请', '20200910');
INSERT INTO `info_operate_log` VALUES (2, 'li', '驳回李四毕业申请', '20200909');
INSERT INTO `info_operate_log` VALUES (3, 'wang', '审核李四关键信息修改', '20200910');
INSERT INTO `info_operate_log` VALUES (4, 'li', '审核李四照片修改', '20200909');
INSERT INTO `info_operate_log` VALUES (5, 'wang', '通过李四前置毕业申请', '20200910');
INSERT INTO `info_operate_log` VALUES (6, 'li', '驳回李四前置毕业申请', '20200909');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `major_num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major_level` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major_dir` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`major_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('10001', '计算机', '本科', '61', '软件开发');
INSERT INTO `major` VALUES ('10002', '通信工程', '本科', '62', '数字通信');
INSERT INTO `major` VALUES ('10003', '软件工程', '本科', '51', 'web工程');
INSERT INTO `major` VALUES ('10004', '机械工程', '本科', '52', '电子机械');

-- ----------------------------
-- Table structure for major_course
-- ----------------------------
DROP TABLE IF EXISTS `major_course`;
CREATE TABLE `major_course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major_num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_num`(`major_num`) USING BTREE,
  INDEX `course_code`(`course_code`) USING BTREE,
  CONSTRAINT `major_course_ibfk_1` FOREIGN KEY (`major_num`) REFERENCES `major` (`major_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `major_course_ibfk_2` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major_course
-- ----------------------------
INSERT INTO `major_course` VALUES (1, '10001', '0001');
INSERT INTO `major_course` VALUES (2, '10002', '0002');
INSERT INTO `major_course` VALUES (3, '10003', '0003');
INSERT INTO `major_course` VALUES (4, '10004', '0004');
INSERT INTO `major_course` VALUES (9, '10001', '0002');
INSERT INTO `major_course` VALUES (10, '10002', '0003');
INSERT INTO `major_course` VALUES (11, '10003', '0004');
INSERT INTO `major_course` VALUES (12, '10004', '0001');
INSERT INTO `major_course` VALUES (13, '10001', '0003');
INSERT INTO `major_course` VALUES (14, '10002', '0004');
INSERT INTO `major_course` VALUES (15, '10003', '0001');
INSERT INTO `major_course` VALUES (16, '10004', '0002');
INSERT INTO `major_course` VALUES (17, '10001', '0004');
INSERT INTO `major_course` VALUES (18, '10002', '0001');
INSERT INTO `major_course` VALUES (19, '10003', '0002');
INSERT INTO `major_course` VALUES (20, '10004', '0003');

-- ----------------------------
-- Table structure for one_class_apply
-- ----------------------------
DROP TABLE IF EXISTS `one_class_apply`;
CREATE TABLE `one_class_apply`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `old_ecn` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_ecn` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `old_course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `old_ecn`(`old_ecn`) USING BTREE,
  INDEX `old_course_code`(`old_course_code`) USING BTREE,
  INDEX `new_course_code`(`new_course_code`) USING BTREE,
  CONSTRAINT `one_class_apply_ibfk_1` FOREIGN KEY (`old_ecn`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `one_class_apply_ibfk_2` FOREIGN KEY (`old_course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `one_class_apply_ibfk_3` FOREIGN KEY (`new_course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of one_class_apply
-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `paper_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `paper_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pub_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `paper_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------

-- ----------------------------
-- Table structure for roll_in_apply
-- ----------------------------
DROP TABLE IF EXISTS `roll_in_apply`;
CREATE TABLE `roll_in_apply`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roll_in_time` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roll_in_major_num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roll_out_reason` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `check_person` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `roll_in_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roll_in_apply
-- ----------------------------

-- ----------------------------
-- Table structure for roll_out_apply
-- ----------------------------
DROP TABLE IF EXISTS `roll_out_apply`;
CREATE TABLE `roll_out_apply`  (
  `apply_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roll_out_time` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roll_out_reason` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `check_person` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  CONSTRAINT `roll_out_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roll_out_apply
-- ----------------------------

-- ----------------------------
-- Table structure for sign_up_info
-- ----------------------------
DROP TABLE IF EXISTS `sign_up_info`;
CREATE TABLE `sign_up_info`  (
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `student_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exam_num` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major_num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sign_up_school` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sign_up_type` int(0) NULL DEFAULT NULL,
  `sign_up_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exam_card_num`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `exam_num`(`exam_num`) USING BTREE,
  INDEX `major_num`(`major_num`) USING BTREE,
  CONSTRAINT `sign_up_info_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_info` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sign_up_info_ibfk_2` FOREIGN KEY (`exam_num`) REFERENCES `exam_info` (`exam_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sign_up_info_ibfk_3` FOREIGN KEY (`major_num`) REFERENCES `major` (`major_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign_up_info
-- ----------------------------
INSERT INTO `sign_up_info` VALUES ('010120100001', '20200001', '考次2', '10001', '0001', '0001', '西电', 0, '20190912');
INSERT INTO `sign_up_info` VALUES ('010120100002', '20200002', '202012', '10002', '0002', '0002', '北大', 0, '20190912');
INSERT INTO `sign_up_info` VALUES ('010120100003', '20200003', '202010', '10003', '0003', '0003', '西工大', 1, '20190901');
INSERT INTO `sign_up_info` VALUES ('010120100004', '20200004', '202012', '10004', '0004', '0004', '陕师大', 1, '20190911');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `student_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `info_state` int(0) NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL,
  `nation` int(0) NULL DEFAULT NULL,
  `political` int(0) NULL DEFAULT NULL,
  `job` int(0) NULL DEFAULT NULL,
  `degree` int(0) NULL DEFAULT NULL,
  `health` int(0) NULL DEFAULT NULL,
  `census_place` int(0) NULL DEFAULT NULL,
  `student_type` int(0) NULL DEFAULT NULL,
  `id_card_type` int(0) NULL DEFAULT NULL,
  `id_card_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `post_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_place` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `photo_path` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `english_name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('20200001', 0, 1, 1, 2, 1, 1, 1, 1111, 1, 1, '644658199908046570', '李四', '20000116', '12389764560', '北京', '1234566', '西安人事所', NULL, 'xix', '789456123@qq.com');
INSERT INTO `student_info` VALUES ('20200002', 0, 2, 1, 2, 2, 2, 1, 1112, 1, 1, '644658199908046571', '张三', '20000117', '12389764560', '北京', '1234566', '西安人事所', NULL, 'xix', '789456123@qq.com');
INSERT INTO `student_info` VALUES ('20200003', 0, 1, 1, 2, 3, 3, 1, 1113, 2, 1, '644658199908046572', '小明', '20000116', '12389764560', '北京', '1234566', '西安人事所', NULL, 'xix', '789456123@qq.com');
INSERT INTO `student_info` VALUES ('20200004', 0, 1, 1, 2, 4, 4, 1, 1114, 3, 1, '644658199908046573', '王五', '20000116', '12389764560', '北京', '1234566', '西安人事所', NULL, 'xix', '789456123@qq.com');

-- ----------------------------
-- Table structure for three_class_apply
-- ----------------------------
DROP TABLE IF EXISTS `three_class_apply`;
CREATE TABLE `three_class_apply`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `card_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `card_type` int(0) NULL DEFAULT NULL,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  INDEX `new_course_code`(`new_course_code`) USING BTREE,
  CONSTRAINT `three_class_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `three_class_apply_ibfk_2` FOREIGN KEY (`new_course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of three_class_apply
-- ----------------------------

-- ----------------------------
-- Table structure for time_manage
-- ----------------------------
DROP TABLE IF EXISTS `time_manage`;
CREATE TABLE `time_manage`  (
  `user_id` int(0) NULL DEFAULT NULL COMMENT '时间修改对象:0在线申请，1：县区，2：市区3：考试院，4：入库时间',
  `begin_time` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '办理开始时间',
  `end_time` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '办理结束时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of time_manage
-- ----------------------------
INSERT INTO `time_manage` VALUES (0, '2020-08-01', '2020-08-02');
INSERT INTO `time_manage` VALUES (1, '2020-08-06', '2020-08-06');
INSERT INTO `time_manage` VALUES (2, '2020-08-14', '2020-08-16');
INSERT INTO `time_manage` VALUES (3, '2020-08-17', '2020-08-20');
INSERT INTO `time_manage` VALUES (4, '2020-08-22', '2020-08-25');
INSERT INTO `time_manage` VALUES (NULL, NULL, NULL);

-- ----------------------------
-- Table structure for two_class_apply
-- ----------------------------
DROP TABLE IF EXISTS `two_class_apply`;
CREATE TABLE `two_class_apply`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `graduate_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exam_card_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `old_course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_course_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apply_state` int(0) NULL DEFAULT NULL,
  `result` int(0) NULL DEFAULT NULL,
  `start_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `end_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_card_num`(`exam_card_num`) USING BTREE,
  INDEX `old_course_code`(`old_course_code`) USING BTREE,
  INDEX `new_course_code`(`new_course_code`) USING BTREE,
  CONSTRAINT `two_class_apply_ibfk_1` FOREIGN KEY (`exam_card_num`) REFERENCES `sign_up_info` (`exam_card_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `two_class_apply_ibfk_2` FOREIGN KEY (`old_course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `two_class_apply_ibfk_3` FOREIGN KEY (`new_course_code`) REFERENCES `course` (`course_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of two_class_apply
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
