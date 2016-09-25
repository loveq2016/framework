/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2016-09-25 10:09:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_no` varchar(30) NOT NULL,
  `group_name` varchar(30) NOT NULL,
  `item_no` varchar(30) NOT NULL,
  `item_key` varchar(30) DEFAULT NULL,
  `item_value` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `is_use` varchar(2) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT 'N',
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', 'gender', '性别', 'male', 'male', '男', null, null, null, '2016-08-13 14:26:48', null, null, null, null, 'N', null, null, null);
INSERT INTO `code` VALUES ('2', 'gender', '性别', 'madam', 'madam', '女', null, '', '', '2016-08-13 14:29:20', null, null, null, '', 'N', '', '', '');
INSERT INTO `code` VALUES ('3', 'resources_type', '资源类型', 'module', 'module', '模块', null, '', '', '2016-08-18 15:36:35', null, null, null, '', 'N', '', '', '');
INSERT INTO `code` VALUES ('4', 'resources_type', '资源类型', 'method', 'method', '方法', null, '', '', '2016-08-18 15:36:46', null, null, null, '', 'N', '', '', '');
INSERT INTO `code` VALUES ('5', 'yes_no', '是否', 'Y', 'Y', '是', null, '', '', '2016-08-18 15:36:35', null, null, null, '', 'N', '', '', '');
INSERT INTO `code` VALUES ('6', 'yes_no', '是否', 'N', 'N', '否', null, 'N', '', '2016-08-30 10:09:14', null, '2016-08-30 10:09:14', '101', '', 'N', '', '', '');

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `parameter_key` varchar(30) NOT NULL,
  `parameter_value` varchar(30) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT 'N',
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES ('1', '1', '1', '1', '1', null, '2016-08-30 10:18:02', '101', null, null, null, null, null, null, null);
INSERT INTO `parameter` VALUES ('2', '1', '3', '1', '1', null, '2016-08-30 10:18:24', '101', '2016-08-30 10:18:24', '101', null, 'N', null, null, null);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `code` varchar(30) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT NULL,
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '基础系统', 'framework', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for project_user
-- ----------------------------
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user` (
  `project_code` varchar(30) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_user
-- ----------------------------
INSERT INTO `project_user` VALUES (null, '2');
INSERT INTO `project_user` VALUES (null, '3');
INSERT INTO `project_user` VALUES (null, '4');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `link_url` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `project_code` varchar(30) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT 'N',
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '系统管理', '', '1', '0', null, 'system', 'module', 'framework', null, '2016-08-18 15:37:33', null, null, null, null, 'N', null, null, null);
INSERT INTO `resources` VALUES ('2', '用户管理', 'user/toFind', '1', '1', null, 'user', 'module', 'framework', null, '2016-08-18 15:37:36', null, null, null, null, 'N', null, null, null);
INSERT INTO `resources` VALUES ('3', '用户添加', 'user/toAdd', '1', '2', null, 'user_add', 'method', 'framework', null, '2016-08-18 15:38:00', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('4', '用户删除', 'user/delete', '1', '2', null, 'user_delete', 'method', 'framework', null, '2016-08-18 15:38:00', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('5', '用户更新', 'user/toUpdate', '1', '2', null, 'user_update', 'method', 'framework', null, '2016-08-18 15:37:59', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('6', '用户查询', 'user/find', '1', '2', null, 'user_find', 'method', 'framework', null, '2016-08-18 15:37:59', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('7', '用户分配角色', 'role/toAssignRole', '1', '2', null, 'user_assign_role', 'module', 'framework', null, '2016-08-18 15:37:39', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('8', '角色管理', 'role/toFind', '1', '1', null, 'role', 'module', 'framework', null, '2016-08-18 15:37:41', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('9', '角色添加', 'role/toAdd', '1', '8', null, 'role_add', 'method', 'framework', null, '2016-08-18 15:37:58', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('10', '角色删除', 'role/delete', '1', '8', null, 'role_delete', 'method', 'framework', null, '2016-08-18 15:37:57', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('11', '角色更新', 'role/toUpdate', '1', '8', null, 'role_update', 'method', 'framework', null, '2016-08-18 15:37:57', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('12', '角色查询', 'role/find', '1', '8', null, 'role_find', 'method', 'framework', null, '2016-08-18 15:37:56', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('13', '分配访问资源', 'resources/toAssignResources', '1', '8', null, 'role_assign_resources', 'method', 'framework', null, '2016-08-18 15:37:56', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('14', '资源管理', 'resources/toFind', '', '1', null, 'resources', 'module', 'framework', '', '2016-08-30 09:16:09', null, null, '101', null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('15', '资源添加', 'resources/toAdd', '1', '14', null, 'resources_add', 'method', 'framework', '', '2016-08-30 09:16:54', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('16', '资源更新', 'resources/toUpdate', '1', '14', null, 'resources_update', 'method', 'framework', '', '2016-08-20 11:41:23', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('17', '资源删除', 'resources/delete', '1', '14', null, 'resources_delete', 'method', 'framework', '', '2016-08-20 11:41:25', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('18', '资源查询', 'resources/find', '1', '14', null, 'resources_find', 'method', 'framework', '', '2016-08-20 11:41:28', null, null, null, null, 'N', '', '', '');
INSERT INTO `resources` VALUES ('19', '状态码管理', 'code/toFind', '', '1', null, 'code', 'module', 'framework', '', '2016-08-30 09:18:38', null, null, '101', '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('20', '状态码添加', 'code/toAdd', '1', '19', null, 'code_add', 'method', 'framework', '', '2016-08-30 09:16:55', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('21', '状态码更新', 'code/toUpdate', '1', '19', null, 'code_update', 'method', 'framework', '', '2016-08-30 09:16:36', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('22', '状态码删除', 'code/delete', '1', '19', null, 'code_delete', 'method', 'framework', '', '2016-08-30 09:16:38', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('23', '状态码查询', 'code/find', '1', '19', null, 'code_find', 'method', 'framework', '', '2016-08-30 09:16:39', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('24', '参数管理', 'parameter/toFind', '', '1', null, 'parameter', 'module', 'framework', '', '2016-08-30 09:18:38', null, null, '101', '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('25', '参数查询', 'parameter/find', '1', '24', null, 'parameter_find', 'method', 'framework', '', '2016-08-30 10:15:32', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('26', '参数删除', 'parameter/delete', '1', '24', null, 'parameter_delete', 'method', 'framework', '', '2016-08-30 10:15:31', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('27', '参数更新', 'parameter/toUpdate', '1', '24', null, 'parameter_update', 'method', 'framework', '', '2016-08-30 10:15:29', null, null, null, '', 'N', '', '', '');
INSERT INTO `resources` VALUES ('28', '参数添加', 'parameter/toAdd', '1', '24', null, 'parameter_add', 'method', 'framework', '', '2016-08-30 10:15:26', null, null, null, '', 'N', '', '', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `project_code` varchar(30) DEFAULT NULL,
  `description` varchar(30) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT 'N',
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('6', '财务人员', 'framework', '', null, '2016-08-18 14:05:54', '101', null, null, null, 'N', null, null, null);

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `role_id` bigint(20) DEFAULT NULL,
  `resources_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('6', '1');
INSERT INTO `role_resources` VALUES ('6', '2');
INSERT INTO `role_resources` VALUES ('6', '6');
INSERT INTO `role_resources` VALUES ('6', '10');
INSERT INTO `role_resources` VALUES ('6', '8');
INSERT INTO `role_resources` VALUES ('6', '13');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  `country` int(11) DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `credentials_expired` varchar(30) DEFAULT NULL,
  `full_name` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `user_type` varchar(2) DEFAULT NULL,
  `notify_mode` varchar(2) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `project_code` varchar(30) DEFAULT NULL,
  `lock_version` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `is_locked` varchar(2) DEFAULT NULL,
  `is_delete` varchar(2) DEFAULT 'N',
  `rsv1` varchar(50) DEFAULT NULL,
  `rsv2` varchar(50) DEFAULT NULL,
  `rsv3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'c0037f72b24b21c5fe297eab8f7e1755', '1', null, '1', '1', 'male', '18', '1', '1', '', '1', '1', '1', '', '1', 'framework', '1', '2016-08-18 14:05:01', null, '2016-08-16 15:42:59', '101', null, 'N', null, null, null);
INSERT INTO `user` VALUES ('4', 'willenfoo', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, '', '', null, null, null, '', null, null, null, '', null, 'framework', null, '2016-08-18 14:05:02', '101', null, null, null, 'N', null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4', '6');
