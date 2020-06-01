/*
Navicat MySQL Data Transfer

Source Server         : 阿里云Mysql
Source Server Version : 50726
Source Host           : linaxhua.cn:33306
Source Database       : pesystem

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-22 13:28:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('pesystem', 'res', '{bcrypt}$2a$10$kLc3ItBzwujjy1POGwld5O31r8iP5XDw3FbDv6Agb164z4H2Dg8H.', 'all', 'password,refresh_token', '', null, '7200', '259200', '{\"country\":\"China\"}', 'false');

-- ----------------------------
-- Table structure for `t_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_code` varchar(20) DEFAULT NULL,
  `authority_info` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', 'admin', '管理员权限');
INSERT INTO `t_authority` VALUES ('2', 'sectionAdmin', '部门管理员权限');
INSERT INTO `t_authority` VALUES ('3', 'general', '普通用户');

-- ----------------------------
-- Table structure for `t_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_case`;
CREATE TABLE `t_case` (
  `case_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_type_id` int(11) DEFAULT NULL,
  `case_type_name` varchar(50) DEFAULT NULL,
  `case_info` varchar(500) DEFAULT NULL,
  `case_attachments_url` varchar(300) DEFAULT NULL,
  `case_update_time` datetime DEFAULT NULL,
  `case_status` varchar(15) DEFAULT NULL COMMENT '待审核：pendingReview 未通过审核：notApproved 还未处理：noTreatment 正在进行：ongoing 已完成：completed',
  `review_time` datetime DEFAULT NULL,
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='案件表';

-- ----------------------------
-- Records of t_case
-- ----------------------------
INSERT INTO `t_case` VALUES ('1', '1', '刑事', 'xxx处刑事犯罪', 'https://pesystem.oss-cn-hangzhou.aliyuncs.com/Case/1.png', '2020-04-20 00:00:00', 'ongoing', '2020-04-21 14:52:36');
INSERT INTO `t_case` VALUES ('2', '1', 'aa', 'xkb', 'https://pesystem.oss-cn-hangzhou.aliyuncs.com/Case/2/2020_4_21_1107023418.png', '2020-04-20 22:00:00', 'pendingReview', '2020-04-21 00:42:00');

-- ----------------------------
-- Table structure for `t_case_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_case_type`;
CREATE TABLE `t_case_type` (
  `case_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_type_name` varchar(50) DEFAULT NULL,
  `case_type_info` varchar(200) DEFAULT NULL,
  `estimated_score` decimal(11,3) DEFAULT NULL,
  PRIMARY KEY (`case_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='案件类型';

-- ----------------------------
-- Records of t_case_type
-- ----------------------------
INSERT INTO `t_case_type` VALUES ('1', '刑事', 'aaaaa', '100.000');

-- ----------------------------
-- Table structure for `t_completion`
-- ----------------------------
DROP TABLE IF EXISTS `t_completion`;
CREATE TABLE `t_completion` (
  `completion_id` int(11) NOT NULL AUTO_INCREMENT,
  `subtasks_id` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `completion_info` varchar(500) DEFAULT NULL,
  `completion_video_url` varchar(300) DEFAULT NULL,
  `completion_attachments_url` varchar(300) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`completion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='个人任务完成情况';

-- ----------------------------
-- Records of t_completion
-- ----------------------------
INSERT INTO `t_completion` VALUES ('1', '1', '1', '完成', null, null, '2020-04-20 00:00:00');
INSERT INTO `t_completion` VALUES ('2', '1', '1', '鬼才', 'https://pesystem.oss-cn-hangzhou.aliyuncs.com/Completion/2/Video/2020_4_21_492687968.png', 'https://pesystem.oss-cn-hangzhou.aliyuncs.com/Completion/2/2020_4_21_799369272.jpg', '2020-04-21 22:00:00');

-- ----------------------------
-- Table structure for `t_depart`
-- ----------------------------
DROP TABLE IF EXISTS `t_depart`;
CREATE TABLE `t_depart` (
  `depart_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_depart_id` int(11) DEFAULT NULL,
  `depart_name` varchar(50) DEFAULT NULL,
  `depart_info` varchar(100) DEFAULT NULL,
  `depart_address` varchar(50) DEFAULT NULL,
  `depart_admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of t_depart
-- ----------------------------
INSERT INTO `t_depart` VALUES ('1', null, '公安', '公安', '上海浦东', '2');
INSERT INTO `t_depart` VALUES ('2', '2', '情报部', 'bb', 'dd', '1');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(50) DEFAULT NULL,
  `notice_info` varchar(500) DEFAULT NULL,
  `notice_attachments_url` varchar(300) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='绩效考核通知';

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '绩效考核1', '暂时没有', '', '2020-04-18 00:00:00');
INSERT INTO `t_notice` VALUES ('2', '绩效考核1', '暂时没有', '', '2020-04-18 00:00:00');
INSERT INTO `t_notice` VALUES ('3', '考核2', '额', '', '2020-01-01 00:00:00');
INSERT INTO `t_notice` VALUES ('4', '考核3', '额', '', '2020-02-01 00:00:00');
INSERT INTO `t_notice` VALUES ('5', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('6', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('7', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('8', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('9', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('10', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('11', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('12', '考核5', '没想到', 'xxx.com', null);
INSERT INTO `t_notice` VALUES ('13', '考核5', '没想到', 'xxx.com', '2020-04-21 15:08:42');
INSERT INTO `t_notice` VALUES ('14', '考核5', '没想到', 'xxx.com', '2020-04-21 15:11:07');
INSERT INTO `t_notice` VALUES ('15', '考核5', '没想到', 'xxx.com', '2020-04-21 15:19:46');
INSERT INTO `t_notice` VALUES ('16', '123', '123', 'xxx.com', '2020-04-21 15:21:31');
INSERT INTO `t_notice` VALUES ('17', '123', '123', 'xxx.com', '2020-04-21 15:21:44');
INSERT INTO `t_notice` VALUES ('18', '123', '123', 'xxx.com', '2020-04-21 15:24:47');
INSERT INTO `t_notice` VALUES ('19', '123', '123', 'xxx.com', '2020-04-21 15:24:56');
INSERT INTO `t_notice` VALUES ('20', '123', '123', 'xxx.com', '2020-04-21 15:27:59');
INSERT INTO `t_notice` VALUES ('21', '123', '123', 'xxx.com', '2020-04-21 15:28:02');
INSERT INTO `t_notice` VALUES ('22', '123', '123', 'xxx.com', '2020-04-21 15:28:08');
INSERT INTO `t_notice` VALUES ('23', '123', '123', 'xxx.com', '2020-04-21 15:32:35');
INSERT INTO `t_notice` VALUES ('24', '123', '123', 'xxx.com', '2020-04-21 15:33:25');
INSERT INTO `t_notice` VALUES ('25', '123', '123', 'xxx.com', '2020-04-21 15:34:28');
INSERT INTO `t_notice` VALUES ('26', '123', '123', 'xxx.com', '2020-04-21 15:34:28');
INSERT INTO `t_notice` VALUES ('27', '123', '123', 'xxx.com', '2020-04-21 15:47:49');
INSERT INTO `t_notice` VALUES ('28', '123', '123', 'xxx.com', '2020-04-21 15:50:27');
INSERT INTO `t_notice` VALUES ('29', '123', '123', 'xxx.com', '2020-04-21 15:51:29');
INSERT INTO `t_notice` VALUES ('30', 'wjh', 'wjh', 'xxx.com', '2020-04-22 02:42:55');
INSERT INTO `t_notice` VALUES ('31', 'www', 'www', 'xxx.com', '2020-04-22 02:44:57');

-- ----------------------------
-- Table structure for `t_perform`
-- ----------------------------
DROP TABLE IF EXISTS `t_perform`;
CREATE TABLE `t_perform` (
  `perform_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `depart_id` int(11) DEFAULT NULL,
  `perform_score` decimal(11,3) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`perform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='个人绩效';

-- ----------------------------
-- Records of t_perform
-- ----------------------------
INSERT INTO `t_perform` VALUES ('1', '1', '2', '60.000', '2020-03-01 00:00:00');
INSERT INTO `t_perform` VALUES ('2', '1', '2', '70.000', '2020-04-01 00:00:00');
INSERT INTO `t_perform` VALUES ('3', '1', '2', '80.000', '2020-05-01 00:00:00');
INSERT INTO `t_perform` VALUES ('4', '1', '2', '65.000', '2020-06-01 00:00:00');
INSERT INTO `t_perform` VALUES ('35', '1', '2', '0.000', '2020-05-01 19:00:00');
INSERT INTO `t_perform` VALUES ('36', '2', '2', '60.000', '2020-05-01 19:00:00');
INSERT INTO `t_perform` VALUES ('37', '3', '2', '0.000', '2020-05-01 19:00:00');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) DEFAULT NULL,
  `role_info` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员身份');
INSERT INTO `t_role` VALUES ('2', 'sectionAdmin', '部门管理员身份');
INSERT INTO `t_role` VALUES ('3', 'general', '普通用户身份');

-- ----------------------------
-- Table structure for `t_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色权限\r\n';

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------
INSERT INTO `t_role_authority` VALUES ('1', '1', '1');
INSERT INTO `t_role_authority` VALUES ('2', '1', '2');
INSERT INTO `t_role_authority` VALUES ('3', '1', '3');
INSERT INTO `t_role_authority` VALUES ('4', '2', '2');
INSERT INTO `t_role_authority` VALUES ('5', '2', '3');
INSERT INTO `t_role_authority` VALUES ('6', '3', '3');

-- ----------------------------
-- Table structure for `t_subtasks`
-- ----------------------------
DROP TABLE IF EXISTS `t_subtasks`;
CREATE TABLE `t_subtasks` (
  `subtasks_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `subtasks_desciption` varchar(500) DEFAULT NULL,
  `subtasks_attachments_url` varchar(300) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `weights` decimal(6,3) DEFAULT NULL COMMENT '权重按百分比存储',
  `is_submit` tinyint(1) DEFAULT NULL,
  `is_completed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`subtasks_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='子任务';

-- ----------------------------
-- Records of t_subtasks
-- ----------------------------
INSERT INTO `t_subtasks` VALUES ('1', '2', '2', '修改版', 'http://www.baidu.com', '2020-04-01 08:00:00', '6.000', '0', '1');

-- ----------------------------
-- Table structure for `t_task_status`
-- ----------------------------
DROP TABLE IF EXISTS `t_task_status`;
CREATE TABLE `t_task_status` (
  `task_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `task_completion_time` datetime DEFAULT NULL,
  `task_completion_info` varchar(500) DEFAULT NULL,
  `task_completion_attachments_url` varchar(300) DEFAULT NULL,
  `task_score` decimal(11,3) DEFAULT NULL,
  `task_status` varchar(15) DEFAULT NULL COMMENT '未完成：undone 已完成：completed',
  `is_recruiting` tinyint(1) DEFAULT NULL COMMENT '1为ture 0为false',
  PRIMARY KEY (`task_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='任务进行情况';

-- ----------------------------
-- Records of t_task_status
-- ----------------------------
INSERT INTO `t_task_status` VALUES ('1', '1', '2', '2020-04-21 08:00:00', '修改版', 'https://www.baidu.com', '60.000', 'completed', '0');
INSERT INTO `t_task_status` VALUES ('2', '2', '2', '2000-01-01 00:00:00', null, 'https://pesystem.oss-cn-hangzhou.aliyuncs.com/TaskStatus/2/2020_4_21_1133855046.png', '50.000', 'completed', '1');

-- ----------------------------
-- Table structure for `t_team`
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `user_type` varchar(8) DEFAULT NULL COMMENT 'leader队长 member队员',
  PRIMARY KEY (`team_id`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='团队';

-- ----------------------------
-- Records of t_team
-- ----------------------------
INSERT INTO `t_team` VALUES ('1', '1', 'leader');
INSERT INTO `t_team` VALUES ('2', '1', 'leader');
INSERT INTO `t_team` VALUES ('2', '2', 'member');
INSERT INTO `t_team` VALUES ('2', '3', 'member');
INSERT INTO `t_team` VALUES ('3', '1', 'member');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `depart_id` int(11) DEFAULT NULL,
  `top_depart_id` int(11) DEFAULT NULL,
  `score` decimal(11,3) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '{bcrypt}$2a$10$Eb2crLXuneU/3usQq/VUCePC0de0yIbV80VHYCcsiY47ZSnBZdHrC', '张三', '12345678911', '2', '1', '100.000');
INSERT INTO `t_user` VALUES ('2', '{bcrypt}$2a$10$6vFedfMLK2u3hgnBJMRfa.uuoKk//yBtQb8ROFqLbCjZjJJunywRu', '李四', '12345678911', '2', '1', '470.000');
INSERT INTO `t_user` VALUES ('3', '{bcrypt}$2a$10$6vFedfMLK2u3hgnBJMRfa.uuoKk//yBtQb8ROFqLbCjZjJJunywRu', '王五', '12345678911', '2', '1', '1.000');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '3');
