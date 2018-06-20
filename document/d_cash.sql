/*
Navicat MySQL Data Transfer

Source Server         : sa
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : d_cash

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-20 16:26:49
*/
-- 建数据库
-- CREATE DATABASE IF NOT EXISTS d_cash DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
-- use d_cash;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `pwd` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', '13500000000', 'SuperAdmin', '41EA4112960ADD9614CA22968A104D34', 'SuperAdmin', 'Y', '2018-05-24 15:28:05');

-- ----------------------------
-- Table structure for `t_aid_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_aid_card`;
CREATE TABLE `t_aid_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(36) NOT NULL,
  `vip_id` int(11) NOT NULL,
  `money` decimal(10,0) NOT NULL,
  `reserve_money` decimal(10,0) DEFAULT '0' COMMENT '预留金额',
  `status` varchar(2) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_aid_card
-- ----------------------------

-- ----------------------------
-- Table structure for `t_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(30) NOT NULL COMMENT '权限内容',
  `store_id` int(11) DEFAULT NULL COMMENT '门店id',
  `descript` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', 'root', null, '超级管理员权限');
INSERT INTO `t_authority` VALUES ('2', 'general', '3', '总店权限');

-- ----------------------------
-- Table structure for `t_check`
-- ----------------------------
DROP TABLE IF EXISTS `t_check`;
CREATE TABLE `t_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_no` varchar(36) NOT NULL COMMENT '盘点编号',
  `raw_id` int(11) NOT NULL COMMENT '所盘原料id',
  `stock_count` float NOT NULL COMMENT '库存数',
  `real_count` float DEFAULT NULL COMMENT '实盘数',
  `gap_count` float DEFAULT NULL COMMENT '差距数',
  `check_emp` varchar(36) DEFAULT NULL COMMENT '盘点员工',
  `check_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '盘点时间',
  `status` varchar(10) DEFAULT NULL,
  `descript` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_check
-- ----------------------------

-- ----------------------------
-- Table structure for `t_employee`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('1', '张三', '13500000000', '123456', '100011@qq.com', '3', 'Y', '2018-05-30 09:38:28');
INSERT INTO `t_employee` VALUES ('3', '李四', '13511111111', '41EA4112960ADD9614CA22968A104D34', '100001@qq.com', '3', 'Y', '2018-06-08 14:47:17');

-- ----------------------------
-- Table structure for `t_emp_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp_role`;
CREATE TABLE `t_emp_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp_role
-- ----------------------------
INSERT INTO `t_emp_role` VALUES ('1', null, '1', '1', null);
INSERT INTO `t_emp_role` VALUES ('2', null, '2', null, '3');

-- ----------------------------
-- Table structure for `t_formula`
-- ----------------------------
DROP TABLE IF EXISTS `t_formula`;
CREATE TABLE `t_formula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raw_id` int(11) NOT NULL COMMENT '配方名',
  `pro_id` int(11) NOT NULL COMMENT '商品id',
  `count` float NOT NULL,
  `descript` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_formula
-- ----------------------------
INSERT INTO `t_formula` VALUES ('2', '16', '7', '1', '生抽', 'Y', '2018-06-05 15:01:38');

-- ----------------------------
-- Table structure for `t_gift`
-- ----------------------------
DROP TABLE IF EXISTS `t_gift`;
CREATE TABLE `t_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(10,0) NOT NULL COMMENT '套餐价格',
  `descript` varchar(500) DEFAULT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gift
-- ----------------------------
INSERT INTO `t_gift` VALUES ('3', '3', '小龙虾套餐', '999', '', 'Y', '2018-06-05 10:15:43');
INSERT INTO `t_gift` VALUES ('4', '3', '冰饮套餐', '20', '', 'Y', '2018-06-05 10:16:27');
INSERT INTO `t_gift` VALUES ('5', '3', '早餐套餐', '16', '', 'Y', '2018-06-05 17:19:08');
INSERT INTO `t_gift` VALUES ('6', '3', '主食套餐', '8888', '', 'Y', '2018-06-05 17:21:31');
INSERT INTO `t_gift` VALUES ('7', '3', '海鲜套餐', '9999', '', 'Y', '2018-06-11 08:47:12');

-- ----------------------------
-- Table structure for `t_grade`
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(30) NOT NULL COMMENT '牌号名称',
  `seat` int(11) NOT NULL COMMENT '座位数',
  `store_id` int(11) NOT NULL COMMENT '门店id',
  `status` varchar(10) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('1', 'A001', '6', '3', 'Y', '2018-05-29 14:50:46');
INSERT INTO `t_grade` VALUES ('3', 'A002', '4', '3', 'Y', '2018-05-29 15:19:25');
INSERT INTO `t_grade` VALUES ('4', 'A003', '4', '3', 'Y', '2018-05-29 15:37:16');
INSERT INTO `t_grade` VALUES ('5', 'A004', '9', '3', 'Y', '2018-05-29 15:38:56');
INSERT INTO `t_grade` VALUES ('6', 'A1001', '4', '4', 'Y', '2018-05-29 17:27:28');

-- ----------------------------
-- Table structure for `t_half_pro`
-- ----------------------------
DROP TABLE IF EXISTS `t_half_pro`;
CREATE TABLE `t_half_pro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '半成品名',
  `count` float NOT NULL COMMENT '数量',
  `store_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL COMMENT '单位',
  `status` varchar(10) DEFAULT NULL COMMENT '处理状态',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_half_pro
-- ----------------------------
INSERT INTO `t_half_pro` VALUES ('2', '腌鸡腿', '5', '3', '8', '0', '2018-06-14 15:23:54');

-- ----------------------------
-- Table structure for `t_industry`
-- ----------------------------
DROP TABLE IF EXISTS `t_industry`;
CREATE TABLE `t_industry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '行业名称',
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_industry
-- ----------------------------
INSERT INTO `t_industry` VALUES ('1', '餐饮行业', 'Y', '2018-05-24 17:08:45');
INSERT INTO `t_industry` VALUES ('2', '母婴行业', 'Y', '2018-05-24 17:18:28');
INSERT INTO `t_industry` VALUES ('3', '烘焙行业', 'Y', '2018-05-24 17:19:32');
INSERT INTO `t_industry` VALUES ('4', '宠物行业', 'Y', '2018-05-24 17:24:10');
INSERT INTO `t_industry` VALUES ('5', '琴行行业', 'Y', '2018-05-24 17:25:17');
INSERT INTO `t_industry` VALUES ('6', '服装鞋帽', 'Y', '2018-05-24 17:29:04');
INSERT INTO `t_industry` VALUES ('7', '生活服务', 'Y', '2018-05-24 17:30:53');
INSERT INTO `t_industry` VALUES ('8', '美业休闲', 'Y', '2018-05-24 17:31:16');
INSERT INTO `t_industry` VALUES ('12', '其他', 'Y', '2018-05-25 11:22:57');

-- ----------------------------
-- Table structure for `t_log_money`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_money`;
CREATE TABLE `t_log_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL,
  `way_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `money` decimal(10,0) NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `descript` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log_money
-- ----------------------------
INSERT INTO `t_log_money` VALUES ('1', '1', '1', '7', '1000000', '2018-06-12 11:24:10', '开店预算');

-- ----------------------------
-- Table structure for `t_log_traffic`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_traffic`;
CREATE TABLE `t_log_traffic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL COMMENT '销售类型',
  `sale_count` int(11) NOT NULL COMMENT '销售额',
  `sale_price` decimal(10,0) NOT NULL COMMENT '售价',
  `original_price` decimal(10,0) NOT NULL COMMENT '原价',
  `profit` decimal(10,0) NOT NULL COMMENT '利润',
  `pro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log_traffic
-- ----------------------------

-- ----------------------------
-- Table structure for `t_log_transfer`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_transfer`;
CREATE TABLE `t_log_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `emp_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `cash_count` decimal(10,0) DEFAULT NULL COMMENT '收银总额',
  `cash_pay` decimal(10,0) DEFAULT NULL COMMENT '现金支付',
  `wechat_pay` decimal(10,0) DEFAULT NULL COMMENT '微信支付',
  `ali_pay` decimal(10,0) DEFAULT NULL COMMENT '支付宝支付',
  `bankcard_pay` decimal(10,0) DEFAULT NULL COMMENT '银联支付',
  `vip_card_pay` decimal(10,0) DEFAULT NULL COMMENT '会员卡支付',
  `aid_card_pay` decimal(10,0) DEFAULT NULL COMMENT '次卡支付',
  `other_pay` decimal(10,0) DEFAULT NULL COMMENT '其他',
  `cash_remaining` decimal(10,0) DEFAULT NULL COMMENT '现金结余',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log_transfer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_lose`
-- ----------------------------
DROP TABLE IF EXISTS `t_lose`;
CREATE TABLE `t_lose` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_id` int(11) DEFAULT NULL,
  `raw_id` int(11) DEFAULT NULL,
  `count` float NOT NULL COMMENT '损失数量',
  `reason` varchar(500) DEFAULT NULL COMMENT '原因',
  `status` varchar(10) DEFAULT '已处理' COMMENT '处理状态',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lose
-- ----------------------------
INSERT INTO `t_lose` VALUES ('1', '1', null, '3', '过期', '已处理', '2018-06-15 09:19:34');
INSERT INTO `t_lose` VALUES ('5', null, '16', '1.5', '过期', '已处理', '2018-06-19 09:46:13');
INSERT INTO `t_lose` VALUES ('6', null, '18', '5', '过期，已处理', '已处理', '2018-06-20 09:47:59');
INSERT INTO `t_lose` VALUES ('7', null, '18', '5', '过期', '已处理', '2018-06-20 10:26:13');

-- ----------------------------
-- Table structure for `t_money`
-- ----------------------------
DROP TABLE IF EXISTS `t_money`;
CREATE TABLE `t_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(10,0) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_money
-- ----------------------------
INSERT INTO `t_money` VALUES ('1', '1000000', '7');

-- ----------------------------
-- Table structure for `t_orders`
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NOT NULL,
  `supplie_id` int(11) NOT NULL COMMENT '供应商id',
  `list_name` text COMMENT '订单清单',
  `status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pay_way`
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_way`;
CREATE TABLE `t_pay_way` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_way` varchar(30) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pay_way
-- ----------------------------
INSERT INTO `t_pay_way` VALUES ('1', '现金', 'Y');
INSERT INTO `t_pay_way` VALUES ('2', '支付宝', 'Y');
INSERT INTO `t_pay_way` VALUES ('3', '微信', 'Y');
INSERT INTO `t_pay_way` VALUES ('4', '银行卡', 'Y');
INSERT INTO `t_pay_way` VALUES ('5', '云闪付', 'Y');
INSERT INTO `t_pay_way` VALUES ('6', '会员卡', 'Y');
INSERT INTO `t_pay_way` VALUES ('7', 'PayPal', 'Y');
INSERT INTO `t_pay_way` VALUES ('8', '其他', 'Y');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `store_id` int(11) NOT NULL,
  `taste_id` int(11) DEFAULT NULL COMMENT '口味id',
  `type_id` int(11) DEFAULT NULL COMMENT '商品类型id',
  `price` decimal(10,0) NOT NULL COMMENT '售价',
  `unit_id` int(11) NOT NULL,
  `pro_img1` varchar(500) DEFAULT NULL,
  `pro_img2` varchar(500) DEFAULT NULL,
  `pro_img3` varchar(500) DEFAULT NULL,
  `pro_img4` varchar(500) DEFAULT NULL,
  `priority` int(11) DEFAULT '99' COMMENT '菜单显示优先级',
  `status` varchar(255) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '小龙虾', '3', '2', '1', '666', '1', 'static/uploads/55128217d1bd25af448b1e9cbf56e910.gif', 'static/uploads/餐饮收银系统功能分析.png', '', '', '99', 'Y', '2018-06-02 17:54:50');
INSERT INTO `t_product` VALUES ('2', '冰淇淋', '3', '2', '4', '5', '1', 'static/uploads/55128217d1bd25af448b1e9cbf56e910.gif', 'static/uploads/55128217d1bd25af448b1e9cbf56e910.gif', 'static/uploads/55128217d1bd25af448b1e9cbf56e910.gif', null, '99', 'Y', '2018-06-04 16:43:28');
INSERT INTO `t_product` VALUES ('3', '酸梅汤', '3', '1', '1', '9', '4', null, null, null, null, '99', 'Y', '2018-06-04 16:44:31');
INSERT INTO `t_product` VALUES ('7', '酱油包', '3', '2', '4', '3', '2', null, null, null, null, '99', 'Y', '2018-06-05 15:01:38');

-- ----------------------------
-- Table structure for `t_pro_gift`
-- ----------------------------
DROP TABLE IF EXISTS `t_pro_gift`;
CREATE TABLE `t_pro_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_id` int(11) NOT NULL,
  `gift_id` int(11) NOT NULL,
  `count` float NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pro_gift
-- ----------------------------
INSERT INTO `t_pro_gift` VALUES ('4', '3', '3', '1');
INSERT INTO `t_pro_gift` VALUES ('5', '2', '3', '2');
INSERT INTO `t_pro_gift` VALUES ('6', '1', '3', '1');
INSERT INTO `t_pro_gift` VALUES ('7', '3', '4', '1');
INSERT INTO `t_pro_gift` VALUES ('8', '2', '4', '1');
INSERT INTO `t_pro_gift` VALUES ('9', '7', '5', '1');
INSERT INTO `t_pro_gift` VALUES ('10', '3', '5', '1');
INSERT INTO `t_pro_gift` VALUES ('11', '2', '5', '1');
INSERT INTO `t_pro_gift` VALUES ('12', '7', '6', '1');
INSERT INTO `t_pro_gift` VALUES ('13', '3', '6', '1');
INSERT INTO `t_pro_gift` VALUES ('14', '1', '6', '1');
INSERT INTO `t_pro_gift` VALUES ('15', '7', '7', '2');
INSERT INTO `t_pro_gift` VALUES ('16', '3', '7', '2');
INSERT INTO `t_pro_gift` VALUES ('17', '2', '7', '2');
INSERT INTO `t_pro_gift` VALUES ('18', '1', '7', '1');

-- ----------------------------
-- Table structure for `t_pro_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_pro_type`;
CREATE TABLE `t_pro_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '商品分类',
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pro_type
-- ----------------------------
INSERT INTO `t_pro_type` VALUES ('1', '快餐', '3', 'Y', '2018-05-30 14:41:54');
INSERT INTO `t_pro_type` VALUES ('3', '自助餐', '3', 'Y', '2018-05-30 14:55:55');
INSERT INTO `t_pro_type` VALUES ('4', '家常菜', '3', 'Y', '2018-05-30 14:56:12');

-- ----------------------------
-- Table structure for `t_raw_materials`
-- ----------------------------
DROP TABLE IF EXISTS `t_raw_materials`;
CREATE TABLE `t_raw_materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '商品名',
  `amount` float(11,2) NOT NULL COMMENT '数量',
  `price` decimal(10,0) NOT NULL COMMENT '进价',
  `unit_id` int(11) NOT NULL COMMENT '单位',
  `supplier_id` int(11) NOT NULL COMMENT '供应商id',
  `birth_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生产日期',
  `shelf_time` varchar(20) NOT NULL COMMENT '保质期',
  `dead_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '过期时间',
  `min_stock` int(11) NOT NULL,
  `max_stock` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_raw_materials
-- ----------------------------
INSERT INTO `t_raw_materials` VALUES ('16', '酱油', '7.50', '5', '3', '1', '2018-06-02 09:22:28', '150天', '2018-10-30 09:22:28', '1', '30', '3', 'Y', '2018-06-02 09:23:02');
INSERT INTO `t_raw_materials` VALUES ('17', '面粉', '11.90', '6', '1', '1', '2018-06-02 14:16:50', '12月', '2019-06-02 14:16:50', '1', '30', '3', 'Y', '2018-06-02 14:17:25');
INSERT INTO `t_raw_materials` VALUES ('18', '辣椒', '2.00', '7', '1', '1', '2018-06-04 10:36:19', '9天', '2018-06-13 10:36:19', '10', '30', '3', 'Y', '2018-06-19 10:37:28');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) NOT NULL,
  `store_id` int(11) DEFAULT NULL COMMENT '门店id',
  `descript` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'root', null, '超级管理员角色');
INSERT INTO `t_role` VALUES ('2', 'general', '3', '总店');

-- ----------------------------
-- Table structure for `t_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------
INSERT INTO `t_role_authority` VALUES ('1', '1', '1');
INSERT INTO `t_role_authority` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for `t_stock`
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raw_id` int(11) NOT NULL COMMENT '原料id',
  `type` varchar(36) DEFAULT NULL COMMENT '类别',
  `count` float NOT NULL COMMENT '库存量',
  `unit_id` int(11) NOT NULL COMMENT '单位',
  `price` decimal(10,0) NOT NULL COMMENT '入库价',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------
INSERT INTO `t_stock` VALUES ('1', '16', null, '7.5', '3', '5', '2018-06-02 09:23:12', '2018-06-19 09:46:19');
INSERT INTO `t_stock` VALUES ('2', '17', null, '11.9', '1', '6', '2018-06-02 14:17:25', '2018-06-08 09:48:15');
INSERT INTO `t_stock` VALUES ('3', '18', null, '2', '1', '7', '2018-06-19 10:37:28', '2018-06-20 10:26:14');

-- ----------------------------
-- Table structure for `t_stock_operating`
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_operating`;
CREATE TABLE `t_stock_operating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raw_id` int(11) NOT NULL COMMENT '原料id',
  `in_stock_count` float DEFAULT NULL COMMENT '入库数量',
  `out_stock_count` float DEFAULT NULL COMMENT '出库数量',
  `unit_id` int(11) DEFAULT NULL COMMENT '单位',
  `emp_name` varchar(30) NOT NULL COMMENT '出入库员工',
  `operating_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `descript` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock_operating
-- ----------------------------
INSERT INTO `t_stock_operating` VALUES ('1', '16', '10', null, '3', '店长', '2018-06-02 09:23:31', null);
INSERT INTO `t_stock_operating` VALUES ('2', '17', '12', null, '1', '店长', '2018-06-02 14:17:25', null);
INSERT INTO `t_stock_operating` VALUES ('4', '16', null, '1', '3', '张三', '2018-06-08 09:48:05', '营业原料领用');
INSERT INTO `t_stock_operating` VALUES ('5', '17', null, '0.1', '1', '张三', '2018-06-08 09:48:14', '营业原料领用');
INSERT INTO `t_stock_operating` VALUES ('6', '16', null, '1.5', '3', '店长', '2018-06-19 09:47:57', null);
INSERT INTO `t_stock_operating` VALUES ('7', '18', '12', null, '1', '店长', '2018-06-19 10:37:28', null);
INSERT INTO `t_stock_operating` VALUES ('8', '18', null, '5', '1', '店长', '2018-06-20 09:48:00', '过期，已处理');
INSERT INTO `t_stock_operating` VALUES ('9', '18', null, '5', '1', '店长', '2018-06-20 10:26:14', '过期');

-- ----------------------------
-- Table structure for `t_store`
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` varchar(50) NOT NULL COMMENT '门店账号',
  `name` varchar(100) NOT NULL,
  `manager` varchar(50) NOT NULL COMMENT '负责人',
  `phone` varchar(20) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `email` varchar(50) NOT NULL,
  `province` varchar(30) NOT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `county` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(160) NOT NULL COMMENT '详细地址',
  `license_img` varchar(500) DEFAULT NULL COMMENT '营业执照图片',
  `industry_id` int(11) NOT NULL COMMENT '行业id',
  `general_id` int(11) DEFAULT NULL COMMENT '总部id',
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_store
-- ----------------------------
INSERT INTO `t_store` VALUES ('3', '10001', '真功夫餐饮店', '张三', '13500000000', '41EA4112960ADD9614CA22968A104D34', '10010@qq.com', '广东省-440000', '东莞-441900', '市辖区-441901', '宏图路33号', '', '1', null, 'Y', '2018-05-28 15:05:33');
INSERT INTO `t_store` VALUES ('4', '10002', '真功夫分店1', '张三', '13511111111', '41EA4112960ADD9614CA22968A104D34', '10020@qq.com', '广东省-440000', '东莞-441900', '莞城区-441902', '33号', '', '1', '3', 'Y', '2018-05-28 15:05:28');
INSERT INTO `t_store` VALUES ('5', '10004', '真功夫分店2', '李四', '13522222222', '41EA4112960ADD9614CA22968A104D34', '10002@qq.com', '广东省-440000', '深圳-440300', '南山区-440305', '33号', '', '1', '3', 'Y', '2018-05-29 10:04:36');
INSERT INTO `t_store` VALUES ('6', '10003', '真功夫分店3', '王五', '13533333333', '41EA4112960ADD9614CA22968A104D34', '10002@qq.com', '广东省-440000', '深圳-440300', '龙岗区-440307', '1001路33号', '', '1', '3', 'Y', '2018-05-30 11:13:16');
INSERT INTO `t_store` VALUES ('7', '10056', '真功夫3', '王武', '13544444444', '41EA4112960ADD9614CA22968A104D34', '1000023@qq.com', '广东省-440000', '东莞-441900', '莞城区-441902', '10023号', '', '1', '3', 'Y', '2018-06-12 11:23:47');

-- ----------------------------
-- Table structure for `t_store_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_order`;
CREATE TABLE `t_store_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(36) NOT NULL COMMENT '订单编号',
  `grade_id` int(11) NOT NULL COMMENT '桌牌号',
  `emp_id` int(11) NOT NULL COMMENT '负责员工',
  `people_count` int(11) NOT NULL COMMENT '客人个数',
  `total_money` decimal(10,0) DEFAULT NULL COMMENT '总支付金额',
  `status` varchar(10) DEFAULT 'N' COMMENT '默认未支付',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_store_order
-- ----------------------------
INSERT INTO `t_store_order` VALUES ('1', 'SC20180620162035312', '1', '1', '6', '689', 'N', '2018-06-20 16:20:53');

-- ----------------------------
-- Table structure for `t_store_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_store_order_detail`;
CREATE TABLE `t_store_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `pro_id` int(11) DEFAULT NULL,
  `gift_id` int(11) DEFAULT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  `total_money` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_store_order_detail
-- ----------------------------
INSERT INTO `t_store_order_detail` VALUES ('1', '1', '1', null, '1', '666');
INSERT INTO `t_store_order_detail` VALUES ('2', '1', '3', null, '2', '18');
INSERT INTO `t_store_order_detail` VALUES ('3', '1', '2', null, '1', '5');

-- ----------------------------
-- Table structure for `t_supplier`
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL COMMENT '供应商电话',
  `address` varchar(200) NOT NULL,
  `defaults` varchar(2) DEFAULT 'N' COMMENT '是否为默认供应商',
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO `t_supplier` VALUES ('1', '3', '张三', '13500000000', '广东省东莞市', 'Y', 'Y', '2018-05-30 17:30:47');
INSERT INTO `t_supplier` VALUES ('2', '3', '李四', '13511111111', '东莞市', 'N', 'Y', '2018-05-31 08:44:18');

-- ----------------------------
-- Table structure for `t_task_plan`
-- ----------------------------
DROP TABLE IF EXISTS `t_task_plan`;
CREATE TABLE `t_task_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(50) NOT NULL,
  `job_name` varchar(100) NOT NULL COMMENT '任务名称',
  `cron_expression` varchar(30) NOT NULL COMMENT 'cron表达式',
  `is_start` varchar(2) DEFAULT 'Y' COMMENT '是否开始运行',
  `descript` varchar(300) DEFAULT NULL COMMENT '任务描述',
  `status` varchar(2) DEFAULT 'Y' COMMENT '是否生效',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task_plan
-- ----------------------------
INSERT INTO `t_task_plan` VALUES ('5', 'com.sucheng.quartz.CronJob', 'cronJobDetail', '0 0 0 1/1 * ? ', 'Y', '每天0点执行', 'Y', '2018-06-12 14:57:55', '2018-06-11 16:36:38');

-- ----------------------------
-- Table structure for `t_taste`
-- ----------------------------
DROP TABLE IF EXISTS `t_taste`;
CREATE TABLE `t_taste` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taste` varchar(20) NOT NULL,
  `descript` varchar(500) DEFAULT NULL,
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_taste
-- ----------------------------
INSERT INTO `t_taste` VALUES ('1', '无', '无', '3', 'Y', '2018-05-30 15:34:23');
INSERT INTO `t_taste` VALUES ('2', '小辣', '小辣', '3', 'Y', '2018-05-30 16:09:43');

-- ----------------------------
-- Table structure for `t_unit`
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit` varchar(20) NOT NULL COMMENT '单位',
  `descript` varchar(20) DEFAULT NULL COMMENT '简介',
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT 'Y',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_unit
-- ----------------------------
INSERT INTO `t_unit` VALUES ('1', 'kg', '千克', '3', 'Y', '2018-06-01 10:21:16');
INSERT INTO `t_unit` VALUES ('2', 'g', '克', '3', 'Y', '2018-06-01 10:21:53');
INSERT INTO `t_unit` VALUES ('3', 'L', '升', '3', 'Y', '2018-06-01 10:22:22');
INSERT INTO `t_unit` VALUES ('4', 'ml', '毫升', '3', 'Y', '2018-06-01 10:22:38');
INSERT INTO `t_unit` VALUES ('7', 'kg', '千克', '4', 'Y', '2018-06-06 08:48:09');
INSERT INTO `t_unit` VALUES ('8', '份', '1份', '3', 'Y', '2018-06-11 08:45:53');

-- ----------------------------
-- Table structure for `t_vip`
-- ----------------------------
DROP TABLE IF EXISTS `t_vip`;
CREATE TABLE `t_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '会员名',
  `card_no` varchar(30) NOT NULL COMMENT '会员卡号',
  `phone` varchar(20) NOT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `money` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '余额',
  `reserve_money` decimal(10,0) DEFAULT '0' COMMENT '预留金额',
  `store_id` int(11) NOT NULL COMMENT '开卡门店',
  `level_id` int(11) NOT NULL COMMENT '等级制度id',
  `over_time` timestamp NULL DEFAULT NULL COMMENT '截止时间',
  `status` varchar(2) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL COMMENT '开卡时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip
-- ----------------------------

-- ----------------------------
-- Table structure for `t_vip_level`
-- ----------------------------
DROP TABLE IF EXISTS `t_vip_level`;
CREATE TABLE `t_vip_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL COMMENT '会员等级',
  `discount` float(4,2) NOT NULL COMMENT '折扣',
  `use_integral` varchar(2) NOT NULL COMMENT '是否使用积分',
  `auto_levelup` int(11) NOT NULL COMMENT '自动升等级积分',
  `store_id` int(11) NOT NULL COMMENT '门店id',
  `descript` varchar(100) DEFAULT '暂无',
  `status` varchar(2) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip_level
-- ----------------------------

-- ----------------------------
-- Table structure for `t_vip_manage`
-- ----------------------------
DROP TABLE IF EXISTS `t_vip_manage`;
CREATE TABLE `t_vip_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vip_day` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `discount_way` varchar(20) DEFAULT NULL,
  `discount` float(4,2) DEFAULT NULL,
  `integral` int(11) DEFAULT NULL COMMENT '积分活动',
  `default_integral` int(11) NOT NULL COMMENT '会员初始积分',
  `store_id` int(11) NOT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip_manage
-- ----------------------------
