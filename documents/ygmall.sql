/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ygmall

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-17 17:13:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` varchar(32) NOT NULL COMMENT '账号id',
  `password` varchar(32) NOT NULL COMMENT '账号密码',
  `salt` varchar(32) NOT NULL COMMENT '密码加密秘钥',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1为顾客、2为商城运营员',
  `state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0可用，1不可用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号表';

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('2d69b37b3ba649479a56a3133552d247', '7b454664ea49af5761467418bd54f18c', '3nb2p7', '1', '0', '2018-09-12 22:28:00', '2018-09-12 22:28:00');
INSERT INTO `account` VALUES ('afbcdab4bf064502a1b62dd3693f6653', 'ddbdeac9cdcef6fe9978f472788996ea', 'iawmkn', '1', '0', '2018-09-13 16:45:45', '2018-09-13 16:45:45');

-- ----------------------------
-- Table structure for `after_sale`
-- ----------------------------
DROP TABLE IF EXISTS `after_sale`;
CREATE TABLE `after_sale` (
  `id` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '售后申请id',
  `order_id` varchar(64) NOT NULL COMMENT '订单id',
  `type` tinyint(3) DEFAULT NULL COMMENT '售后类型',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `description` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `receiver_name` varchar(20) NOT NULL COMMENT '联系人姓名',
  `receiver_tel` varchar(16) NOT NULL COMMENT '联系电话',
  `status` tinyint(3) DEFAULT NULL COMMENT '售后处理状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `after_sale_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='售后申请表';

-- ----------------------------
-- Records of after_sale
-- ----------------------------

-- ----------------------------
-- Table structure for `after_sale_alter`
-- ----------------------------
DROP TABLE IF EXISTS `after_sale_alter`;
CREATE TABLE `after_sale_alter` (
  `id` varchar(32) NOT NULL COMMENT '售后变动id',
  `after_sale_id` varchar(64) NOT NULL COMMENT '售后申请id',
  `state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '变更后状态，1待审核，2已取消，0已完成。',
  `operator` varchar(32) NOT NULL COMMENT '操作人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sqsdq` (`after_sale_id`),
  CONSTRAINT `sqsdq` FOREIGN KEY (`after_sale_id`) REFERENCES `after_sale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='售后状态变更表';

-- ----------------------------
-- Records of after_sale_alter
-- ----------------------------

-- ----------------------------
-- Table structure for `attribute`
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格编号',
  `category_id` int(11) NOT NULL COMMENT '分类编号',
  `name` varchar(50) NOT NULL COMMENT '规格名称',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `attribute_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格表';

-- ----------------------------
-- Records of attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌编号',
  `name` varchar(255) NOT NULL COMMENT '品牌名称',
  `status` tinyint(255) NOT NULL COMMENT '品牌状态（1：已授权:0：未授权）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1087 DEFAULT CHARSET=utf8mb4 COMMENT='品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1000', '青竹', '1');
INSERT INTO `brand` VALUES ('1001', '玛丽', '0');
INSERT INTO `brand` VALUES ('1002', '呵呵', '0');
INSERT INTO `brand` VALUES ('1061', '集合', '0');
INSERT INTO `brand` VALUES ('1080', '个人', '0');
INSERT INTO `brand` VALUES ('1081', '发送给', '0');
INSERT INTO `brand` VALUES ('1082', '黑胡椒', '0');
INSERT INTO `brand` VALUES ('1083', '开个回', '0');
INSERT INTO `brand` VALUES ('1084', '回电话', '0');

-- ----------------------------
-- Table structure for `categories`
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `name` varchar(255) NOT NULL COMMENT '类别名',
  `parent_level` int(11) DEFAULT NULL COMMENT '父级id',
  `grand_level` int(11) DEFAULT NULL COMMENT '祖级id',
  `status` tinyint(255) NOT NULL COMMENT '分类状态（1：已分类:0：已取消）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类别表';

-- ----------------------------
-- Records of categories
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '评论id',
  `goods_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品编号',
  `order_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单详情编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `user_img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `goods_score` tinyint(5) DEFAULT NULL COMMENT '商品评分',
  `service_score` tinyint(5) DEFAULT NULL COMMENT '服务评分',
  `create_time` datetime DEFAULT NULL COMMENT '首次评论时间',
  `comment` varchar(255) DEFAULT NULL COMMENT '首次文字评论',
  `comment_img` varchar(255) DEFAULT NULL COMMENT '首次图片评论',
  `additional_comment` varchar(255) DEFAULT NULL COMMENT '文字追评',
  `additional_comment_img` varchar(255) DEFAULT NULL COMMENT '图片追评',
  `additional_comment_time` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '追评时间',
  `format_and_style` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '规格款式',
  `display` tinyint(3) DEFAULT NULL COMMENT '是否显示',
  `delete` tinyint(3) DEFAULT NULL COMMENT '是否删除过评论',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `icon` varchar(64) DEFAULT NULL COMMENT '头像路径',
  `sex` tinyint(3) DEFAULT NULL COMMENT '性别(0：男性，1：女性，-1未知)',
  `telephone` varchar(16) NOT NULL COMMENT '绑定手机',
  `email` varchar(64) NOT NULL COMMENT '绑定邮箱',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顾客信息表';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('2d69b37b3ba649479a56a3133552d247', null, null, null, '18820842091', '555@qq.com', '2018-09-12 22:28:00', '2018-09-12 22:28:00');
INSERT INTO `customer` VALUES ('afbcdab4bf064502a1b62dd3693f6653', null, null, null, '13690309138', '55@qq.com', '2018-09-13 16:45:45', '2018-09-13 16:45:45');

-- ----------------------------
-- Table structure for `customer_address`
-- ----------------------------
DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '所属顾客id',
  `receiver_name` varchar(20) NOT NULL COMMENT '联系人姓名',
  `province` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '国标收货地址第一级地址',
  `city` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '国标收货地址第二级地址',
  `county` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '县，乡',
  `detail` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '详细收货地址信息',
  `receiver_tel` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '联系电话',
  `postal_code` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮编（后期扩展用）',
  `status` tinyint(3) NOT NULL COMMENT '是否为常用地址(1：是，0：不是)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `customer_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顾客收货地址表';

-- ----------------------------
-- Records of customer_address
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '商品编号',
  `SPU_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '货品编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `inventory` int(11) NOT NULL COMMENT '库存',
  `category_id` int(11) NOT NULL COMMENT '类别编号',
  `status` tinyint(255) NOT NULL COMMENT '商品状态：0在售；1预售；2缺货；3下架；4促销',
  `cost` decimal(10,2) NOT NULL COMMENT '成本价',
  `market_price` decimal(10,2) NOT NULL COMMENT '市场价',
  `price` decimal(10,2) NOT NULL COMMENT '售价',
  `sale_price` decimal(10,2) DEFAULT NULL COMMENT '促销价',
  `unit` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '计量单位',
  `sale_count` int(11) NOT NULL DEFAULT '0' COMMENT '销售量',
  `note` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `attributes` varchar(255) NOT NULL COMMENT '规格值字符串，如“红色-XL”',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `SPU_id` (`SPU_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`SPU_id`) REFERENCES `spu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_collage`
-- ----------------------------
DROP TABLE IF EXISTS `goods_collage`;
CREATE TABLE `goods_collage` (
  `id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '商品id',
  `activity_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '拼团活动id',
  `need_number` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '拼团需要人数',
  `price` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '价格',
  `join_number` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '商品参与数量',
  `remain_number` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '商品剩余数量',
  `people_number` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '已参与人数',
  `group_number` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '已成团数量',
  `star_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `endr_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商家商品拼团活动信息表';

-- ----------------------------
-- Records of goods_collage
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_img`
-- ----------------------------
DROP TABLE IF EXISTS `goods_img`;
CREATE TABLE `goods_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品细节图编号',
  `goods_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '商品编号',
  `img_order` int(11) NOT NULL COMMENT '细节图序号',
  `img_url` varchar(255) NOT NULL COMMENT '细节图路径',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `goods_img_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品细节图片表';

-- ----------------------------
-- Records of goods_img
-- ----------------------------

-- ----------------------------
-- Table structure for `group_collage`
-- ----------------------------
DROP TABLE IF EXISTS `group_collage`;
CREATE TABLE `group_collage` (
  `group_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '拼团团号id',
  `activity_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '拼团活动id',
  `joided_number` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '已拼团人数',
  `leader_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '团长id',
  `state_judge` tinyint(3) NOT NULL COMMENT '拼团状态（0未成功/1已成功）',
  `star_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商品拼团信息表';

-- ----------------------------
-- Records of group_collage
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '订单id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `goods_total_money` decimal(10,2) DEFAULT NULL COMMENT '商品总金额',
  `carriage` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `real_total_money` decimal(10,2) DEFAULT NULL COMMENT '实际订单总金额',
  `receiver_name` varchar(20) NOT NULL COMMENT '联系人姓名',
  `receiver_tel` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '联系电话',
  `province` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '国标收货地址第一级地址',
  `city` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '国标收货地址第二级地址',
  `county` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '县，乡',
  `detail` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '详细收货地址信息',
  `order_remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '买家留言',
  `attributes` tinyint(255) DEFAULT NULL COMMENT '订单属性/来源',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `order_alter`
-- ----------------------------
DROP TABLE IF EXISTS `order_alter`;
CREATE TABLE `order_alter` (
  `id` varchar(64) NOT NULL COMMENT '订单变动id',
  `entry_id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '订单id',
  `state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '变更后状态，1待审核，2已取消，0已完成。',
  `operator` varchar(32) NOT NULL COMMENT '操作人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '变更时间',
  PRIMARY KEY (`id`),
  KEY `entry_id` (`entry_id`),
  CONSTRAINT `order_alter_ibfk_1` FOREIGN KEY (`entry_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单状态变更表';

-- ----------------------------
-- Records of order_alter
-- ----------------------------

-- ----------------------------
-- Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '订单详情id',
  `order_id` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `goods_number` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品名称',
  `format_and_style` varchar(255) DEFAULT NULL COMMENT '规格款式',
  `market_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单个商品支付金额',
  `count` int(11) DEFAULT NULL COMMENT '商品下单数量',
  `status` tinyint(255) DEFAULT NULL COMMENT '订单详情状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `goods_number` (`goods_number`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`),
  CONSTRAINT `order_detail_ibfk_3` FOREIGN KEY (`goods_number`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `refund`
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '申请退款id',
  `serial` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '需要退款的流水号',
  `step` int(1) NOT NULL COMMENT '1：退款发起，2：等待财务确认，3：退款通过,4：拒绝退款',
  `operator` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '操作人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款申请表';

-- ----------------------------
-- Records of refund
-- ----------------------------

-- ----------------------------
-- Table structure for `reply_comment`
-- ----------------------------
DROP TABLE IF EXISTS `reply_comment`;
CREATE TABLE `reply_comment` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '回复id',
  `reply_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '被回复id',
  `reply_content` varchar(255) DEFAULT NULL COMMENT '首次回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '首次回复时间',
  `content` varchar(255) DEFAULT NULL COMMENT '回复追评内容',
  `creat_time` datetime DEFAULT NULL COMMENT '回复追评时间',
  PRIMARY KEY (`id`),
  KEY `reply_id` (`reply_id`),
  CONSTRAINT `reply_comment_ibfk_1` FOREIGN KEY (`reply_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家回复表';

-- ----------------------------
-- Records of reply_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `spu`
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '货品编号',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '货品名称',
  `category_id` int(11) NOT NULL COMMENT '分类编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '详情图片编号',
  `brand_id` int(11) NOT NULL COMMENT '品牌编号',
  `createtime` datetime(6) NOT NULL COMMENT '上架时间',
  `sale_count` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评价数',
  `params` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '参数(json)',
  `attributes_name` varchar(255) NOT NULL COMMENT 'json,存放该货品对应的规格的所有值的字符串，如{“规1”:"a;b;c",“规2”:"1;2;3"}',
  `subtitle` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品副标题',
  PRIMARY KEY (`id`),
  KEY `detail_id` (`detail_id`),
  KEY `brand_id` (`brand_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `spu_ibfk_1` FOREIGN KEY (`detail_id`) REFERENCES `spu_detail` (`id`),
  CONSTRAINT `spu_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `spu_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货品表';

-- ----------------------------
-- Records of spu
-- ----------------------------

-- ----------------------------
-- Table structure for `spu_detail`
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail`;
CREATE TABLE `spu_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货品详情编号',
  `spu_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT 'SPU编号（货品编号）',
  `img_order` int(11) DEFAULT NULL COMMENT '图片序号',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`),
  KEY `SPU_id` (`spu_id`),
  CONSTRAINT `spu_detail_ibfk_1` FOREIGN KEY (`spu_id`) REFERENCES `spu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货品详情图片表';

-- ----------------------------
-- Records of spu_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `user_collage`
-- ----------------------------
DROP TABLE IF EXISTS `user_collage`;
CREATE TABLE `user_collage` (
  `id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '标识编号',
  `user_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `group_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '拼团团号id',
  `activity_id` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '拼团活动id',
  `leader_judge` tinyint(3) NOT NULL COMMENT '团员团长判断（0团员/1团长）',
  `star_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户拼团个人信息表';

-- ----------------------------
-- Records of user_collage
-- ----------------------------
