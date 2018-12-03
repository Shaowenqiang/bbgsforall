/*
Navicat MySQL Data Transfer

Source Server         : 笔记本mysql56-root
Source Server Version : 50635
Source Host           : 127.0.0.1:3306
Source Database       : bbgs

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-10-24 09:06:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `ID` varchar(32) DEFAULT NULL COMMENT 'ID',
  `NAME` varchar(64) DEFAULT NULL,
  `AGE` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('123', '123', '12');

-- ----------------------------
-- Table structure for sql_archives_copy
-- ----------------------------
DROP TABLE IF EXISTS `sql_archives_copy`;
CREATE TABLE `sql_archives_copy` (
  `node` int(8) NOT NULL AUTO_INCREMENT,
  `tree_name` varchar(10) DEFAULT NULL,
  `parent_node` varchar(8) DEFAULT NULL,
  `level` int(4) DEFAULT NULL,
  `sql` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`node`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sql_archives_copy
-- ----------------------------
INSERT INTO `sql_archives_copy` VALUES ('1', '查询', null, '1', null);
INSERT INTO `sql_archives_copy` VALUES ('2', '插入', null, '1', null);
INSERT INTO `sql_archives_copy` VALUES ('3', '更新', null, '1', null);
INSERT INTO `sql_archives_copy` VALUES ('4', '删除', null, '1', null);
INSERT INTO `sql_archives_copy` VALUES ('5', 'aaa', '1', '2', null);
INSERT INTO `sql_archives_copy` VALUES ('6', 'aa', '1', '2', null);
INSERT INTO `sql_archives_copy` VALUES ('7', 'a', '1', '2', null);
INSERT INTO `sql_archives_copy` VALUES ('8', 'b', '2', '2', null);
INSERT INTO `sql_archives_copy` VALUES ('9', 'bb', '2', '2', null);
INSERT INTO `sql_archives_copy` VALUES ('10', 'bbb', '2', '2', null);

-- ----------------------------
-- Table structure for tbl_bbgs_batch
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_batch`;
CREATE TABLE `tbl_bbgs_batch` (
  `ID` varchar(128) NOT NULL COMMENT '主键',
  `BATCH_NAME` varchar(200) DEFAULT '111' COMMENT '批次名称',
  `IS_MATERIAL` varchar(128) DEFAULT NULL COMMENT '是否是物资（Y是物资 N 不是物资）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_batch
-- ----------------------------
INSERT INTO `tbl_bbgs_batch` VALUES ('dbe18881355145f1bdf0056d17cd9bb4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', 'Y');

-- ----------------------------
-- Table structure for tbl_bbgs_bid_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_bid_information`;
CREATE TABLE `tbl_bbgs_bid_information` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `BID_ABBREVIAION` varchar(200) DEFAULT NULL COMMENT '标段简称',
  `TECHNOLOGY_TEMPLATE` varchar(128) DEFAULT NULL COMMENT '技术打分模版',
  `BUSINESS_TEMPLATE` varchar(128) DEFAULT NULL COMMENT '商务打分模版',
  `TECHNOLOGY_BID_RECORD` varchar(128) DEFAULT NULL COMMENT '技术阅标记录模版',
  `BUSINESS_BID_RECORD` varchar(128) DEFAULT NULL COMMENT '商务阅标记录模版',
  `BIDDER_LIST` varchar(128) DEFAULT NULL COMMENT '投标人清单模版',
  `PRICE_SCORE_TEMPLATE` varchar(128) DEFAULT NULL COMMENT '价格计算得分模板',
  `BUSINESS_EXPERT_GROUP` varchar(128) DEFAULT NULL COMMENT '商务专家分组',
  `TECHNOLOGY_EXPERT_GROUP` varchar(128) DEFAULT NULL COMMENT '技术专家分组',
  `BUSINESS_WEIGHT` varchar(4) DEFAULT NULL COMMENT '商务权重',
  `TECHNOLOGY_WEIGHT` varchar(4) DEFAULT NULL COMMENT '技术权重',
  `PRICE_WEIGHT` varchar(4) DEFAULT NULL COMMENT '价格权重',
  `INVALID` varchar(2) DEFAULT NULL COMMENT '流标',
  `FLAG` varchar(2) DEFAULT NULL COMMENT '标志位 Y有对应模板 N无对应模板',
  `PRICE_FORMULA` varchar(100) DEFAULT NULL COMMENT '价格公式',
  `M` varchar(4) DEFAULT NULL COMMENT '物资类第一种公式m参数',
  `TECHNOLOGY_ASSESSMENT` varchar(128) DEFAULT NULL,
  `BUSINESS_ASSESSMENT` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_bid_information
-- ----------------------------
INSERT INTO `tbl_bbgs_bid_information` VALUES ('7076261c5c4f48bebcd7609f37baaadf', '001-电能表包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('576698c93ee64bb48b7aabce2d0c5c12', '008-低压电缆包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('ab5e3a6b98e442d2b49b63457dcaa984', '001-电能表包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('12dcec3557a545b5b32486ec441d21c1', '008-低压电缆包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d020390d555e486287eee06d04047323', '001-电能表包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('cc02abd58e6743da8bb538268a4d2328', '008-低压电缆包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('642a12cde2d04a4ab1b667f259a67a46', '001-电能表包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('bd589da14b6d49c08d784227e0febed1', '008-低压电缆包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('18196edd9dad46349186f01d82ad3cc7', '001-电能表包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('5602cb6b2fcf4ff3b9c9e258ca9ad49f', '001-电能表包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('befd65ac38a7496ea38b6f15cdae3f4e', '008-低压电缆包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('7ecbd4d1f3b44e60b0eb90ff20ba3136', '001-电能表包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d95ba02751d34aa6ae333cf4eebe970a', '008-低压电缆包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('7b78d3aefe1d46ab95c27c412dd707d3', '001-电能表包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_3', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('8ea0708c86d24f64b71026f2f8f4012b', '008-低压电缆包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('9c72fc48e51045d1b637a7747ad64609', '004-变压器包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('611418a00d434f3caa6b29f16ef85c77', '001-电能表包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('0ebaa24329e649fab6945397ac04d78b', '008-低压电缆包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('a55a93cec0c346bdbb30b14feb063760', '004-变压器包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('a339be367344440cbd11a05dfb0418de', '008-低压电缆包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('749c9f82f0394275bd88096e0803778b', '005-高压柜包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('c1f30ceddd7a4d968eed1966f111f5b5', '005-高压柜包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('f5e2975fed3e47d9b8d0590222875adb', '009-电缆桥架包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('ac7f3643fb484a7a8eb27c55045ec272', '009-电缆桥架包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('5fb63399818348c8a9475105c40cd1b3', '009-电缆桥架包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('1e2149f50f234048a0f84af363c00b84', '009-电缆桥架包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b3939e9a695a4e80845e72bf0b9268da', '009-电缆桥架包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('f91e4264a5604570b32c8b007529f614', '009-电缆桥架包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('153f8e9c207743abab525ec1b106f168', '009-电缆桥架包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d588b0e476bd44f19e557716a2f47be3', '009-电缆桥架包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('bc85443f32c4462a92b5116983bed74e', '009-电缆桥架包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('0b33cb2098364b0f9c1e01ca5ad7d40f', '007-高压电缆包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('e098c4307c224dbb8cdd0d553f2133f8', '007-高压电缆包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('65075030cfc04fda8a6c21af0e586d92', '006-低压柜包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('4597dd413e9c483497718bc1a5f5860b', '007-高压电缆包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('224104e00ea54a3f8efa8821f0f64555', '007-高压电缆包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('2df58ebd7cff4ca696fc2aef080de86a', '006-低压柜包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('6a64f72fa74744489d6059c18397dc1a', '006-低压柜包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('380fd8415c074ca58616f5b8878ad02b', '007-高压电缆包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('33153cb8fae243aa84a6754bfa0b905c', '006-低压柜包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d2493d66ff634e4eb1b7ad2cf60d4271', '006-低压柜包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d6cdae4269ed4314bac1da352fc8a0c1', '007-高压电缆包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b74b6a6902ee457ea92d13d531999fbd', '006-低压柜包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('0848e61ec0024f95bddd67595ed878fb', '007-高压电缆包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('47153ca09d744ec2a683e18875dae9c6', '006-低压柜包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('26c61416ca5d4a449e0ede48ef777cae', '006-低压柜包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('9809050459b34a1dabccc6af3958a889', '006-低压柜包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('82d561bacf844ba0891cde47df877ae0', '007-高压电缆包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('2406f3e4e1fe4a5695a7eda14f926a9f', '006-低压柜包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('4508918fbdab4b879d43170715930717', '007-高压电缆包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('8361b1a22cba43c886ba5a4ba7ab568a', '006-低压柜包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('c7533eb7b6cb42c3b2fb18e161b21325', '002-电能表包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('133c87f65b7946f283f70b1665efded6', '002-电能表包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('5e63eda5a10c40c1a190ebb6f565cde3', '002-电能表包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('a2d902104144464cb9963c05e8e2233e', '002-电能表包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('fff5365ba2294ab3b2f47e0573f662fb', '002-电能表包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('217ab95fb5364592b6eecf33091b726f', '002-电能表包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('c70d851f0f044101ac86ac15218f0ce2', '002-电能表包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('58e173e00d064c158fc8dadaec854383', '002-电能表包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('45f69ab408e749f883aa67f7fb948152', '002-电能表包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('93ee84ea14d5488b909981459ba29d2c', '005-高压柜包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b2d63ddcb2d742ecb154623c47f719bd', '005-高压柜包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('15b7a7abb32447e5a58c91077c16ff7c', '005-高压柜包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b4e49b469cea407cb7ad31293dca6c57', '003-开闭箱包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('392bf92a5c4f468eb9d3a109bd7619e2', '003-开闭箱包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('03a80ac9cdd142ba84ce5fd898eef195', '003-开闭箱包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('96316cc56719416d8abf920e010424f5', '003-开闭箱包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('e4cd70bd9bd94579add636d2197936b2', '003-开闭箱包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('92eb3fa6823a401a9a1d3f3931412be4', '003-开闭箱包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('3cfe9017857343978536a24c57998cae', '003-开闭箱包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('adcd5eca1ca4418fb934649898a19c89', '003-开闭箱包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('837a0d5e2f6c4725a92d6e7763e0a522', '003-开闭箱包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('856576cc5731448ba86ecae69fd90fc5', '010-DTU包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('8e51e541b20342878dea5319d597f85a', '010-DTU包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('01d0e11e5c784ae0a017d4b382b32edb', '001-电能表包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('a3d389d4f6ea41c5869cc71652969956', '010-DTU包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('302199f80a3d4fc7a76d83b401cd35a9', '001-电能表包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('c4f2894d3bbe4d8c83bc64e02e53a1a5', '002-电能表包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('6ad4a1c6b2774471a10f0d866fbe35c3', '010-DTU包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b9c1c28b69014150b663908470c342fa', '010-DTU包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('fec0c21966b84bd882d50479586f3794', '010-DTU包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('a39f3715a08d4a98966bcd9a90ef4f29', '007-高压电缆包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('976b0799f00e4974b5789268d65a096b', '010-DTU包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b4c09881cbdf4f0fbaf6ec0101b06907', '007-高压电缆包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('fa5976fc933b4d88b4826439fc86e8c7', '010-DTU包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('c6ee6df6a1524e66a39c0437cebbb650', '010-DTU包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('9613efa32ad1409d8bef07a6fe109905', '009-电缆桥架包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('f9b74717d3ab44059c4e933c2265b6ad', '009-电缆桥架包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('139cf39e60f54827a81db97e9e5a68fe', '005-高压柜包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('34bc7cd8062a41cb869743b7eff74bd9', '005-高压柜包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('ba6ee03f25774b6a9c68c672908e2d45', '005-高压柜包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('7dae99f0803d422c8af2355abb300324', '005-高压柜包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('64d4dfc1c5084a75ac75848bc3a85a9b', '005-高压柜包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('01f883c10e5540b3b2a6739197590493', '005-高压柜包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('5132dac3fd3a4ce68272c4f84e8350db', '003-开闭箱包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('3b85caa73b594459bc175bded2a911e0', '003-开闭箱包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('e6ae8245fb644faba0b924afc33728cf', '004-变压器包7', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b9ca228253ba4d639a86b80988c84b6d', '010-DTU包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('01e2ba47310f407a97916e9a0330dcda', '004-变压器包8', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('48e29bf06fd14f85b6232271c6500a8e', '004-变压器包5', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('b16a2b07004f40b9b272aa5784cf89fa', '004-变压器包6', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d86bbc0b7e65450bad7ffa459c1c3703', '010-DTU包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('4d4c82c37e514226a24746489ab72b6a', '004-变压器包3', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('0eacc770f29d47a89b99363bf7c14de7', '004-变压器包4', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('d6feaca356c044928edbe5ea4d3fe325', '004-变压器包1', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('cb050560e5e24701b202c5c141986fb4', '004-变压器包2', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('0d47767a52854056b07e965d9f35991c', '004-变压器包9', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('3984e6f6af7942dc87997c4dcb1bdaa5', '002-电能表包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('11ac161b17204ef09f1ab5bd59d2ae8a', '008-低压电缆包11', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);
INSERT INTO `tbl_bbgs_bid_information` VALUES ('3f2df6f332344460b497f36d9ae1ca04', '008-低压电缆包10', '8057b60366d3428ea408bc67ad91ca23', '5e8a6e8a828f48d18be4d1ced23c1191', '21bf1ab0f4f64685857e544454705336', '8f613509e93e49a49e5438cc11d3611f', '45d104c995d9459a938516c076977d0d', '027eb5dee91449d7b077b5e5097ec496', 'a3d15962bc5441d59fd47b998195c7e8', '2194fd14d2ce4ef8987f673701f842ea', '0.10', '0.40', '0.50', '1', 'Y', 'formulaService@materials_priceFormula_1', '0.1', null, null);

-- ----------------------------
-- Table structure for tbl_bbgs_expert
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_expert`;
CREATE TABLE `tbl_bbgs_expert` (
  `ID` varchar(128) DEFAULT NULL COMMENT '主键',
  `EXPERT_NAME` varchar(128) DEFAULT NULL COMMENT '专家姓名',
  `GROUP_ID` varchar(128) DEFAULT NULL COMMENT '专家组id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='专家';

-- ----------------------------
-- Records of tbl_bbgs_expert
-- ----------------------------
INSERT INTO `tbl_bbgs_expert` VALUES ('1d93199b987f47b6916ff15ea15a187d', '555', '2194fd14d2ce4ef8987f673701f842ea');
INSERT INTO `tbl_bbgs_expert` VALUES ('eeaf6236a29248279248740bbcb00359', '5555', 'a3d15962bc5441d59fd47b998195c7e8');
INSERT INTO `tbl_bbgs_expert` VALUES ('7e9812f741ae47d3a47467ae2f181770', '777', 'a3d15962bc5441d59fd47b998195c7e8');
INSERT INTO `tbl_bbgs_expert` VALUES ('1e90391b5d4a4e50ac6290ddeaa788ff', '55', '2194fd14d2ce4ef8987f673701f842ea');

-- ----------------------------
-- Table structure for tbl_bbgs_expert_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_expert_group`;
CREATE TABLE `tbl_bbgs_expert_group` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `EXPERT_GROUP_NAME` varchar(50) DEFAULT NULL COMMENT '专家组名称',
  `TYPE` varchar(4) DEFAULT NULL COMMENT '类型',
  `SORTID` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='专家组信息';

-- ----------------------------
-- Records of tbl_bbgs_expert_group
-- ----------------------------
INSERT INTO `tbl_bbgs_expert_group` VALUES ('2194fd14d2ce4ef8987f673701f842ea', '技术一组', '技术', '1');
INSERT INTO `tbl_bbgs_expert_group` VALUES ('a3d15962bc5441d59fd47b998195c7e8', '商务一组', '商务', '1');

-- ----------------------------
-- Table structure for tbl_bbgs_flow_state
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_flow_state`;
CREATE TABLE `tbl_bbgs_flow_state` (
  `id` varchar(8) NOT NULL,
  `content` varchar(128) DEFAULT NULL COMMENT '提示内容',
  `flow_state` varchar(2) DEFAULT NULL COMMENT '流程',
  `alert_message` varchar(128) DEFAULT NULL COMMENT '弹出内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_flow_state
-- ----------------------------
INSERT INTO `tbl_bbgs_flow_state` VALUES ('501', '招标基础数据输入【步骤⑤导入按钮】', '1', '请先在步骤 ⑤ 中导入招标基础数据');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('601', '唱标报价数据输入【步骤⑥导入唱标报价数据按钮】', '1', '请先在步骤⑥ 导入唱标报价数据');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('602', '绑定标段（包）模板、权重等信息【步骤⑥ 2、导出专家组记录保存按钮】', '1', '请先在步骤⑥ 的导出专家组记录中绑定标段（包）模板、权重等信息');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('603', '计算评标价格得分【步骤⑥ 4、评标间隔计算得分表计算评标价格得分按钮】', '1', '请先在步骤⑥ 的评标价格计算得分表中计算评标价格得分');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('701', '导入专家评分数据【步骤⑦ 导入专家评分数据按钮】', '0', '请先在步骤⑦ 中导入专家评分数据');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('801', '计算综合排序【步骤⑧ 计算综合排序按钮】', '0', '请先在步骤⑧ 中计算综合排序');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('901', '计算中标结果【步骤⑨ 计算中标结果按钮】', '0', '请先在步骤⑨ 中计算中标结果');
INSERT INTO `tbl_bbgs_flow_state` VALUES ('999', '用于区别901完成与否的记录（无实际意义但不可删）', '0', '用于区别901完成与否的记录（无实际意义但不可删）');

-- ----------------------------
-- Table structure for tbl_bbgs_formula_conditions
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_formula_conditions`;
CREATE TABLE `tbl_bbgs_formula_conditions` (
  `ID` varchar(100) DEFAULT NULL COMMENT '主键',
  `STARTVALUE` int(11) DEFAULT NULL COMMENT '起始值',
  `ENDVALUE` int(11) DEFAULT NULL COMMENT '结束值',
  `SUBMAXPRICECOUNT` int(11) DEFAULT NULL COMMENT '去掉最高价个数',
  `SUBMINPRICECOUNT` int(11) DEFAULT NULL COMMENT '去掉最低价个数',
  `CREATETIME` date DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` date DEFAULT NULL COMMENT '修改时间',
  `EXECUTEFUNID` int(11) DEFAULT NULL COMMENT '方法id',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `CONDITIONID` int(11) DEFAULT NULL COMMENT '条件(参数)id',
  `CONDITIONNAME` varchar(10) DEFAULT NULL COMMENT '条件(参数)名称',
  `EXECUTEFUN` varchar(100) DEFAULT NULL COMMENT '方法路径(名)',
  `EXECUTEFUNNAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_formula_conditions
-- ----------------------------
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('51385e14f59b443bab611fd4beedc70b', '0', '10', '0', '0', null, null, '1', null, '1', 'M', 'formulaService@materials_priceFormula_1', '【物资】区间复合平均价法');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('2535e95214404a37beae1763f8a8c44d', '10', '20', '1', '1', null, null, '1', null, '1', 'M', 'formulaService@materials_priceFormula_1', '【物资】区间复合平均价法');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('35e034f6af4749c6abbe4600421d8938', '20', '30', '2', '1', null, null, '1', null, '1', 'M', 'formulaService@materials_priceFormula_1', '【物资】区间复合平均价法');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('187ba7d11e374d12be965f399404b851', '30', '10000', '3', '2', null, null, '1', null, '1', 'M', 'formulaService@materials_priceFormula_1', '【物资】区间复合平均价法');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('cab85656ae424ca79d6149fed3f10d38', '0', '5', '0', '0', null, null, '3', null, '2', 'N', 'formulaService@notmaterials_priceFormula_1', '【服务】服务公式1');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('cd75c8b3ed9c451ab1214c522fa1dc7b', '6', '6', '1', '0', null, null, '3', null, '2', 'N', 'formulaService@notmaterials_priceFormula_1', '【服务】服务公式1');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('024458752736429db2543f62cadde963', '7', '10000', '1', '1', null, null, '3', null, '2', 'N', 'formulaService@notmaterials_priceFormula_1', '【服务】服务公式1');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('6c02039f81b74ae598f788be53d0e619', '0', '5', '0', '0', null, null, '4', null, '2', 'N', 'formulaService@notmaterials_priceFormula_2', '【服务】服务公式2');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('5d4f74235a8f442c816e9d4c53ecc509', '6', '6', '1', '0', null, null, '4', null, '2', 'N', 'formulaService@notmaterials_priceFormula_2', '【服务】服务公式2');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('1', '0', '6', '0', '0', null, null, '6', null, '1', 'M', 'formulaService@materials_priceFormula_3', '【物资】电商公式');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('2', '6', '10', '1', '1', null, null, '6', null, '1', 'M', 'formulaService@materials_priceFormula_3', '【物资】电商公式');
INSERT INTO `tbl_bbgs_formula_conditions` VALUES ('4', '10', '10000', '2', '1', null, null, '6', null, '1', 'M', 'formulaService@materials_priceFormula_3', '【物资】电商公式');

-- ----------------------------
-- Table structure for tbl_bbgs_formula_executefun
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_formula_executefun`;
CREATE TABLE `tbl_bbgs_formula_executefun` (
  `ID` int(11) NOT NULL COMMENT '主键',
  `TYPE` int(11) NOT NULL DEFAULT '0' COMMENT '类型0:物资1:非物资',
  `DESCRIPT` varchar(100) DEFAULT NULL COMMENT '公式描述',
  `CREATETIME` date DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` date DEFAULT NULL COMMENT '修改时间',
  `EXECUTEFUN` varchar(100) DEFAULT NULL COMMENT '公式执行方法路径',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注(公式详细描述)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_formula_executefun
-- ----------------------------
INSERT INTO `tbl_bbgs_formula_executefun` VALUES ('1', '0', '【物资】区间复合平均价法', '2017-10-09', '2017-10-09', 'formulaService@materials_priceFormula_1', '根据起始值结束值去掉最高价最低价等条件计算');
INSERT INTO `tbl_bbgs_formula_executefun` VALUES ('2', '0', '【物资】最低评标价法', '2017-10-14', '2017-10-14', 'formulaService@materials_priceFormula_2', '根据最低评标价计算');
INSERT INTO `tbl_bbgs_formula_executefun` VALUES ('3', '1', '【服务】服务公式1', '2017-10-14', '2017-10-14', 'formulaService@notmaterials_priceFormula_1', '价格得分=(投标报价权重-投标报价权重×n×(|投标人的评标价-基准价1|/基准价1))×100');
INSERT INTO `tbl_bbgs_formula_executefun` VALUES ('4', '1', '【服务】服务公式2', '2017-10-14', '2017-10-14', 'formulaService@notmaterials_priceFormula_2', '价格得分＝(基准价2/投标人的评标价)×100');
INSERT INTO `tbl_bbgs_formula_executefun` VALUES ('5', '0', '【物资】电商公式', null, null, 'formulaService@materials_priceFormula_3', '采用平均浮动双曲线计算报价得分');

-- ----------------------------
-- Table structure for tbl_bbgs_package_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_package_supplier`;
CREATE TABLE `tbl_bbgs_package_supplier` (
  `ID` varchar(128) DEFAULT NULL COMMENT '主键',
  `PACKAGENAME` varchar(128) DEFAULT NULL COMMENT '包名',
  `SUPPLIER` varchar(128) DEFAULT NULL COMMENT '供应商名称',
  `FLAG_BIT` varchar(128) DEFAULT NULL COMMENT '标志位(废标 无报价一次，初评一次区分开)(0为废标，1或者空为正常)',
  `OFFER` varchar(128) DEFAULT NULL COMMENT '报价',
  `MOD_OFFER` varchar(128) DEFAULT NULL COMMENT '最终金额报价',
  `FINAL_RATE` varchar(128) DEFAULT NULL COMMENT '最终折扣比例',
  `PRICE_SCORE` varchar(128) DEFAULT NULL COMMENT '报价得分（权重）',
  `TECHNOLOGY_SCORE` varchar(128) DEFAULT NULL COMMENT '技术得分（权重）',
  `BUSINESS_SCORE` varchar(128) DEFAULT NULL COMMENT '商务得分（权重）',
  `TOTAL_SCORE` varchar(128) DEFAULT NULL COMMENT '总计',
  `SORT` varchar(128) DEFAULT NULL COMMENT '排名',
  `IS_WIN` varchar(128) DEFAULT NULL COMMENT '是否中标',
  `BID_SETION` varchar(200) DEFAULT NULL COMMENT '标段全称',
  `BID_ABBREVIAION` varchar(200) DEFAULT NULL COMMENT '标段简称',
  `BID` varchar(128) DEFAULT NULL COMMENT '标名',
  `FLAG_INVALID` varchar(128) DEFAULT NULL COMMENT '标志位(流标)(0为流标，1或者空为正常)',
  `REASON_BIT` varchar(255) DEFAULT NULL,
  `FLAG_BID_INVALID` varchar(128) DEFAULT NULL COMMENT '包流标标志位(0为流标，1或者空为正常)',
  `REASON_INVALID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='标段（包）—供应商关系表';

-- ----------------------------
-- Records of tbl_bbgs_package_supplier
-- ----------------------------
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('f5c0f5431778473db2dd974b965f9382', '包1', '青岛鼎信通讯股份有限公司', '1', '200.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('79bb3c31b9a74cf4927d64c87e8387fb', '包1', '石家庄科林电气股份有限公司', '0', '201.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('e5b1f8f2f6e2441a9a1102964e080e9b', '包1', '哈尔滨市电力工贸公司', '0', '202.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('25fc6dc104ff43beb5a6eab5338b8cd4', '包1', '浙江八达电子仪表有限公司', '1', '203.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('15f30a0c900b4ae5a42ffe8c84a54275', '包1', '江苏林洋能源股份有限公司', '1', '204.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('e6bd2cdd2bab40c29e193848cef5ee98', '包1', '杭州炬华科技股份有限公司', '1', '205.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);
INSERT INTO `tbl_bbgs_package_supplier` VALUES ('60d33790e20c4c06b13239e29ae2ead5', '包1', '华立科技股份有限公司', '1', '206.000000', null, null, null, null, null, null, null, '0', null, '001-电能表包1', '001', '1', null, '1', null);

-- ----------------------------
-- Table structure for tbl_bbgs_price_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_price_information`;
CREATE TABLE `tbl_bbgs_price_information` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `PRICE` varchar(128) DEFAULT NULL COMMENT '投标价格',
  `mod_offer` varchar(128) DEFAULT NULL,
  `final_rate` varchar(128) DEFAULT NULL,
  `SUPPLIER` varchar(128) DEFAULT NULL COMMENT '供应商名称',
  `A1` varchar(128) DEFAULT NULL,
  `N` varchar(128) DEFAULT NULL,
  `A2` varchar(128) DEFAULT NULL,
  `P` varchar(128) DEFAULT NULL,
  `BASE_PRICE` varchar(128) DEFAULT NULL COMMENT '评标基准价',
  `PRICE_SCORE` varchar(128) DEFAULT NULL COMMENT '评标价格得分',
  `SORT` varchar(4) DEFAULT NULL COMMENT '排序',
  `PACKAGENAME` varchar(128) DEFAULT NULL COMMENT '包名',
  `A3` varchar(128) DEFAULT NULL,
  `A4` varchar(128) DEFAULT NULL,
  `BID` varchar(128) DEFAULT NULL COMMENT '标段名',
  `M` varchar(128) DEFAULT NULL COMMENT '物资类第一种公式其中一种情况M值用户设定',
  `BUSINESS_WEIGHT` varchar(128) DEFAULT NULL COMMENT '商务权重',
  `TECHNOLOGY_WEIGHT` varchar(128) DEFAULT NULL COMMENT '技术权重',
  `PRICE_WEIGHT` varchar(128) DEFAULT NULL COMMENT '价格权重',
  `BID_SETION` varchar(200) DEFAULT NULL COMMENT '标段全称',
  `BID_ABBREVIAION` varchar(200) DEFAULT NULL COMMENT '标段简称',
  `PRICE_FORMULA` varchar(100) DEFAULT NULL COMMENT '使用的公式',
  `EXPORT_TYPE` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='价格打分信息';

-- ----------------------------
-- Records of tbl_bbgs_price_information
-- ----------------------------
INSERT INTO `tbl_bbgs_price_information` VALUES ('8ab5f67a858d4435a85acb4aac20761d', '205.000000', null, null, '杭州炬华科技股份有限公司', '203.0000000', '4', '205.0300000', '0', '205.0300000', '40.00', '1', '包1', null, null, '001', '0.1', '0.10', '0.40', '0.50', null, '001-电能表包1', 'formulaService@materials_priceFormula_3', '0');
INSERT INTO `tbl_bbgs_price_information` VALUES ('9ffdd05b03994023a363093a1226a750', '204.000000', null, null, '江苏林洋能源股份有限公司', '203.0000000', '4', '205.0300000', '0', '205.0300000', '39.88', '2', '包1', null, null, '001', '0.1', '0.10', '0.40', '0.50', null, '001-电能表包1', 'formulaService@materials_priceFormula_3', '0');
INSERT INTO `tbl_bbgs_price_information` VALUES ('76ee7a96c2e940968aad42a678219164', '206.000000', null, null, '华立科技股份有限公司', '203.0000000', '4', '205.0300000', '0', '205.0300000', '39.81', '3', '包1', null, null, '001', '0.1', '0.10', '0.40', '0.50', null, '001-电能表包1', 'formulaService@materials_priceFormula_3', '0');
INSERT INTO `tbl_bbgs_price_information` VALUES ('9a5942190e4445488830f60c1cdb7a53', '203.000000', null, null, '浙江八达电子仪表有限公司', '203.0000000', '4', '205.0300000', '0', '205.0300000', '39.76', '4', '包1', null, null, '001', '0.1', '0.10', '0.40', '0.50', null, '001-电能表包1', 'formulaService@materials_priceFormula_3', '0');
INSERT INTO `tbl_bbgs_price_information` VALUES ('09b883d0482d4cacb6be0946e73651dd', '200.000000', null, null, '青岛鼎信通讯股份有限公司', '203.0000000', '4', '205.0300000', '0', '205.0300000', '39.41', '5', '包1', null, null, '001', '0.1', '0.10', '0.40', '0.50', null, '001-电能表包1', 'formulaService@materials_priceFormula_3', '0');

-- ----------------------------
-- Table structure for tbl_bbgs_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_supplier`;
CREATE TABLE `tbl_bbgs_supplier` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `SUPPLIER` varchar(64) DEFAULT NULL COMMENT '供应商名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_supplier
-- ----------------------------
INSERT INTO `tbl_bbgs_supplier` VALUES ('7acd8b1eaa9f11e881dd4ccc6a530a59', '扬州京隆科技有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7acd94fcaa9f11e881dd4ccc6a530a59', '黑龙江华控电气成套设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7acd9fffaa9f11e881dd4ccc6a530a59', '深圳安富电力有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7711baa9f11e881dd4ccc6a530a59', '哈尔滨市鑫盛达电控设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad785c6aa9f11e881dd4ccc6a530a59', '黑龙江泓晟电力有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad79677aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司22');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7a474aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司23');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7ae08aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司24');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7b769aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司25');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7c0fcaa9f11e881dd4ccc6a530a59', '哈尔滨德高供电设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7ca3baa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7d365aa9f11e881dd4ccc6a530a59', '远洋线缆有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7dc84aa9f11e881dd4ccc6a530a59', '石家庄科林电气股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7e5bcaa9f11e881dd4ccc6a530a59', '杭州炬华科技股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7eecdaa9f11e881dd4ccc6a530a59', '黑龙江沃尔德电缆有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad7f7b6aa9f11e881dd4ccc6a530a59', '丹东鑫源电力设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad800b3aa9f11e881dd4ccc6a530a59', '沈阳北阳电缆制造有限责任公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad80a77aa9f11e881dd4ccc6a530a59', '北京华电美仪电气科技有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad817c6aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司20');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8258faa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司21');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8335eaa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司11');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad840dbaa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司1');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad84c17aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司12');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad85653aa9f11e881dd4ccc6a530a59', '黑龙江省联兴通信器材有限责任公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad85fc8aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司2');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad869d6aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司13');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad873baaa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司3');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad87d06aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司14');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8867baa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司4');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad88f89aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司15');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8994daa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司5');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8a24caa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司16');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8ab6baa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司6');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8b47caa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司17');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8bd6aaa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司7');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8c645aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司18');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8d00caa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司8');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8d911aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司19');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8e200aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司9');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8eae3aa9f11e881dd4ccc6a530a59', '江苏博世电气有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8f3daaa9f11e881dd4ccc6a530a59', '浙江八达电子仪表有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad8fcc0aa9f11e881dd4ccc6a530a59', '华立科技股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad905a6aa9f11e881dd4ccc6a530a59', '大连启元电器制造有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad90e86aa9f11e881dd4ccc6a530a59', '白城电力镇赉变压器有限责任公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad91769aa9f11e881dd4ccc6a530a59', '黑龙江中远电气设备制造有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad922efaa9f11e881dd4ccc6a530a59', '浙江广天电力设备股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad92ee5aa9f11e881dd4ccc6a530a59', '哈尔滨龙能电气有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad938beaa9f11e881dd4ccc6a530a59', '哈尔滨市电力工贸公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad942d5aa9f11e881dd4ccc6a530a59', '许昌许继德理施尔电气有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad94de1aa9f11e881dd4ccc6a530a59', '上海振大电器成套集团有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad957a2aa9f11e881dd4ccc6a530a59', '青岛鼎信通讯股份有限公司10');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad96180aa9f11e881dd4ccc6a530a59', '黑龙江中纳输配电成套设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad96c37aa9f11e881dd4ccc6a530a59', '黑龙江哈沈电缆制造有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad975c5aa9f11e881dd4ccc6a530a59', '哈尔滨向欣电缆桥架有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9815aaa9f11e881dd4ccc6a530a59', '长园电力技术有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad98bfcaa9f11e881dd4ccc6a530a59', '江苏林洋能源股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad99750aa9f11e881dd4ccc6a530a59', '哈尔滨互成机电设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9a53baa9f11e881dd4ccc6a530a59', '哈尔滨市北胜金属结构制造有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9af33aa9f11e881dd4ccc6a530a59', '东方电子股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9b93caa9f11e881dd4ccc6a530a59', '北京聚能达电力技术有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9c5c3aa9f11e881dd4ccc6a530a59', '哈尔滨鹏健电控设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9cf4eaa9f11e881dd4ccc6a530a59', '石家庄科林电气设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9d91baa9f11e881dd4ccc6a530a59', '哈尔滨阿通电站设备有限责任公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9e351aa9f11e881dd4ccc6a530a59', '江苏腾源电气有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9ecc6aa9f11e881dd4ccc6a530a59', '哈尔滨锋云电控设备有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ad9f732aa9f11e881dd4ccc6a530a59', '哈尔滨亿汇达电气科技发展股份有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ada006baa9f11e881dd4ccc6a530a59', '美仪电气有限公司');
INSERT INTO `tbl_bbgs_supplier` VALUES ('7ada098daa9f11e881dd4ccc6a530a59', '浙江申大电力设备有限公司');

-- ----------------------------
-- Table structure for tbl_bbgs_supplier_row_price
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_supplier_row_price`;
CREATE TABLE `tbl_bbgs_supplier_row_price` (
  `ID` varchar(32) DEFAULT NULL,
  `BID_ABBREVIAION` varchar(255) DEFAULT NULL COMMENT '标段简称',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商名',
  `row_price` varchar(255) DEFAULT NULL COMMENT '行价格',
  `row_type` varchar(255) DEFAULT NULL COMMENT '行类别',
  `row_price_weight` varchar(255) DEFAULT NULL COMMENT '行价格平均值',
  `row_price_avg` varchar(255) DEFAULT NULL COMMENT '行价格平均值',
  `A1` varchar(255) DEFAULT NULL,
  `M` varchar(255) DEFAULT NULL,
  `N` varchar(255) DEFAULT NULL,
  `A2` varchar(255) DEFAULT NULL,
  `base_price` varchar(255) DEFAULT NULL COMMENT '基准价格',
  `score` varchar(255) DEFAULT NULL COMMENT '得分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_bbgs_supplier_row_price
-- ----------------------------
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('1', '001-电能表包1', '石家庄科林电气股份有限公司', '1', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '46.8777058');
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('2', '001-电能表包1', '石家庄科林电气股份有限公司', '2', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '71.0533153');
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('3', '001-电能表包1', '石家庄科林电气股份有限公司', '3', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '90.6231392');
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('4', '001-电能表包1', '石家庄科林电气股份有限公司', '4', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '88.3750000');
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('5', '001-电能表包1', '石家庄科林电气股份有限公司', '5', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '70.7000000');
INSERT INTO `tbl_bbgs_supplier_row_price` VALUES ('6', '001-电能表包1', '石家庄科林电气股份有限公司', '6', '灯泡10w', '', '', '3.5000000', '6', '4', '3.5350000', '3.5350000', '58.9166667');

-- ----------------------------
-- Table structure for tbl_bbgs_technology_business
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_technology_business`;
CREATE TABLE `tbl_bbgs_technology_business` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `EXPERT_NAME` varchar(128) DEFAULT NULL COMMENT '专家姓名',
  `BID_ABBREVIAION` varchar(200) DEFAULT NULL COMMENT '标段名称',
  `SUPPLIER` varchar(128) DEFAULT NULL COMMENT '供应商ID',
  `TYPE` varchar(128) DEFAULT NULL COMMENT '打分类型（商务/技术）',
  `PRICE` varchar(128) DEFAULT NULL COMMENT '分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_technology_business
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_bbgs_template
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_template`;
CREATE TABLE `tbl_bbgs_template` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键',
  `FILE_NAME` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `REAL_NAME` varchar(100) DEFAULT NULL COMMENT '真实文件名称',
  `FILE_PATH` varchar(400) DEFAULT NULL COMMENT '文件路径',
  `TEMPLATE_TYPE` varchar(4) DEFAULT NULL COMMENT '模版类型(0技术打分模版，1商务打分模版，2价格打分模版，3技术阅标记录模版，4商务阅标记录模版，5投标人清单)',
  `IS_DEFAULT` varchar(4) DEFAULT NULL COMMENT '是否是默认模板(每个类型只能有一个 Y是 N不是）',
  `IS_MATERIAL` varchar(4) DEFAULT NULL COMMENT '是否是物资（Y是物资 N不是物资）',
  `UPTIME` varchar(8) DEFAULT NULL COMMENT '上传模板时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_template
-- ----------------------------
INSERT INTO `tbl_bbgs_template` VALUES ('8057b60366d3428ea408bc67ad91ca23', '技术评分表', '技术评分表模板.xls', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/技术评分表模板.xls', '0', 'Y', null, '20180827');
INSERT INTO `tbl_bbgs_template` VALUES ('5e8a6e8a828f48d18be4d1ced23c1191', '商务评分表', '商务评分表模板.xls', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/商务评分表模板.xls', '1', 'Y', null, '20180827');
INSERT INTO `tbl_bbgs_template` VALUES ('027eb5dee91449d7b077b5e5097ec496', '报价评分表', '报价评分表模板.xlsx', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/报价评分表模板.xlsx', '2', null, null, '20180827');
INSERT INTO `tbl_bbgs_template` VALUES ('21bf1ab0f4f64685857e544454705336', '技术阅览记录', '技术阅览记录模板.xls', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/技术阅览记录模板.xls', '3', null, null, '20180827');
INSERT INTO `tbl_bbgs_template` VALUES ('8f613509e93e49a49e5438cc11d3611f', '商务阅览记录', '商务阅览记录模板.xls', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/商务阅览记录模板.xls', '4', null, null, '20180827');
INSERT INTO `tbl_bbgs_template` VALUES ('45d104c995d9459a938516c076977d0d', '投标人模板', '投标人清单模板.xls', 'D:\\projects\\bbgs\\bbgs-core\\target\\bbgs-core\\WEB-INF/page/bbgs/excelHtml/投标人清单模板.xls', '5', 'Y', null, '20180827');

-- ----------------------------
-- Table structure for tbl_bbgs_tenderer
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_tenderer`;
CREATE TABLE `tbl_bbgs_tenderer` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BATCH_TITLE` varchar(200) DEFAULT NULL COMMENT '批次名称',
  `BID_ABBREVIAION` varchar(200) DEFAULT NULL COMMENT '标段',
  `SUPPLIER` varchar(64) DEFAULT NULL COMMENT '供应商',
  `TEL` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `REMIT` varchar(2) DEFAULT NULL COMMENT '是否汇款',
  `SEND` varchar(2) DEFAULT NULL COMMENT '是否发送'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_tenderer
-- ----------------------------
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f05097192e1f414e8f3df81b4c0f9725', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6e90148e0bbb4a8e870803a9a4ba81c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('aee7e24238f44e4fbee6d59cfb44db95', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2030cd392c754305946429d94b7e4f5d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('848b72f8bfd24d0bbfecccddfe9770a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('46ac9dc93df34c33b499d3cf68877b7c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('220a1136d10e46998a7140a6549ad969', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('daf0099ae88842a69274ebf95d052422', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('db50e47a471d4c0e810bd4c7ffe5134c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eff80f32686d423596a9741a8e725068', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('28b087c51d1b4b049c63cdeaebc18577', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6a150274247e4d91a29c562d8a7b8bcb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4611ea1283f043d1a284588d8a7d76f7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1a76e00519384aa797c2c04668b2e5fd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包1', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('51f9f695bed5444ea1814d8630fb3c10', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('da2ced69151b445397e8fe3eeb54acc6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0d26f297a9fb4e9d86d3e23a4432aa88', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2e5d52dddbdd4739b536c5cd22acd92b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d40c9b36bf5d409e92488d109c2a0700', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dbad76f2054e4442acdcff35c0a6c2d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('196c635a325247b0ac0755230a0da0be', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ba4a02d5891c4cc095d9cbf2de97773e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2b8e0de6c1e74bec835fedab1c3bb283', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0890b8682a5748f0b43ae0863720fde4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7290aa6ce85b473db84f5087586918eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a1c7f0c15f6142958b2482b668eeb49b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0ca9853b029a4e4ca967101ac39e00a3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包1', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e9ef46b6aca4ddeb3eda50b752f9827', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('34f0490f26144dde9bdb8a2e88c86aa8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f50f3c7025a44514a971d101230db5ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dbcb732d6fba4143aeb88f773c035ce4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1372f8ad8cf543bea6d3ddcf2fb44668', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fa8b5029b8cd40b0af80f2cd2f127575', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0a01854727794b479a6ab5c3a84e6e21', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包1', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0987fd015e754da1b3f4a9b18ec3d348', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('70d915f58d0a4aa5b9eda798cf54258d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('23e65411bb594f27bbf55ce0baeed87b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('db55bd9dda804a2d9b8f805d3080fb0e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('96402c7ae1304c8f8b8cb980d81c7192', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e930fb94aaae4de58e9578127d055f8a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8de0317f550142a2a3d31704753bf695', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1d2772c2460244809a1cdc74c1b7f20e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a4b33fd2b24244a89552658e0d73c476', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包1', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c035d6aefab9445b9f85f21d44a52e94', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('043c3af751ba40e8bc49f5adf04a79cd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('36efb4075e0a4c979c8b2fcfdb20c5d2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4502277da38a4d42b585861f0f6b4810', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3f11869ac95e4e0693409b8204e82ddb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bb7d954082d94fc1ad7aa333dc66ffbc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eaf0339518c84857988cf11728be02f7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('685a94a8fdbf414c88712bd0a8302b3f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('75ee5a4fbe844876b61087792cff7b58', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('da4d635e696a45ebac05ad73d7c511a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cbcdffcf91cb4d23964a2ccc53315973', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3be03069f4d24bce8a7dc38472fb82ab', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8dddb807efd9468bae79eca38d3c3313', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e8f182a6e8147dfa3aacc7153c08cce', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e490e862db45492581e62293f29928bc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e54d0f7a242f4a8c99600644f0efcbad', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('24b65c6deed94f4f96cf93477a2ea879', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d0013c60b02b47a0ba30ea1de8f66833', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包1', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2c0ae2d9283e43c896b91c83dd73f8fb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包1', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('aad9a0782a344b42afff741a3f1b002f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包1', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3585b8f826a4419ab67ce8d8f9ec17cc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包1', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dd2783dc6ae04e3cb5894c52d6f5dd4c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包1', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0752f14930244c9eaba3695fdf0f9b42', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包1', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8708d11aeb544c27870b798bc210f971', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包1', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d24d314fe6814dfb8607ce8600e41d76', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包1', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bb161aa7a12e49a2b4241c7f47db813d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包1', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('641b30000fba44deaeb75f1279709293', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包1', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2e9e444658054d05934a5173fdcb41ff', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4fa271a3ede24999ba6f333fd3ec9e06', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('25610cc59d3b4876936af767c1aa8b03', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b94fe64dfd0a4ea8b3dbe1041d061f4d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fd45d2a0fb3e4e5e90aa6946aaeb3379', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9af7c9988a7540148ccc2c464a992f89', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包1', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('93cef194c7db4e8fb7ae153fbddeeae3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('94bef75b9c7e48bc9989caba3ac89a19', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0f6295da88d34ed396ed27f735fb2be1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('71d33acc1fce4131af9068547bf251c3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b67d69f8a90e4a5bbee422a2ecf7d3f0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c60096acdcae41b3ba2eacdcc3fb795d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包1', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3caf6aaef7b24ab69ce8e7ecd07b6865', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fd6df19d176d47c49429f0f62f9dcb19', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a0676ab2516a44f0b4a36f5723f4b64e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('92e9f643e0214e5d9799798582f7e61e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b6d1450e821c4550bc83da0db34ef7ae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5c6c374d65044acc9d79d9442f200c99', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('70ebecbbc15243fd93305bc8638a6c84', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包2', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d14348b1448a4136811f77dfd6ace76e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5acb157388e34979af12c60f425a7c76', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3bcc39f19e5e42fc80a4f9b1d09fb895', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e3f9c806887c4435a10e355e6ac889df', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('db4de87b67f64bb8958b9def8920fccc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('aa696a77c5b3419d9c89cd95ca49b552', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8724891682774978a9fd356922aa61c5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包2', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7478361de84647ef963fbbc7bd2d8434', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('235240ad5329427090a67464e09d02d5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('27304781d4ea40369b2efed8e6cfb94e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('381cc642e37d40aca882e0517a479b04', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('203e97243cd94a1db1b485576eff663f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6e64c6fd097d449e8a4b113a76a83da3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('63846236726842c58f2f56758c57b272', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d05c7de4b1b94f28b0269ee2493a3e3c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e46a0b8df7c64a528500a16843a5fbcf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2b33c04989f1460aad9b6e3e73359efc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3431f9162b4b4b21aa930cf10e409906', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a18d481c74fd404caa0e2e9c1c10b457', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('01827d94d0514e19a0d32fb38b24933d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包2', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7659ec7a086a4c66bd6d3f337be0e041', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2a0b8ed5cc45402db4129630c0f3c5a0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9a137a64ea09445897ce156da849b671', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('92ea6e44fe084eb38daa2284605309ae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ed8422015fdf45f6bdea3a1603c8a681', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('607a9a55433d46e2ab07445affb98aa9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f8e94f1d15a849f1ba74f7be32799e35', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包2', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d2ff995ff07443e096bf95171a05fede', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53e66594800a4e48a1f2c045c74a73ba', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53541159e2254f3488d437422758986b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c9710b544a5e41b495e680698001288a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0753b3f7fd3e472883978f8383ecd03e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('81e5f54b367748af9a238bf5c5e81e1a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c34ca6c9a8e4baaa611d09b9944dddb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8aacd8452bf948ae9092265e6e672fed', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e9ff2bd5850c4e05910c8f07db34d360', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包2', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('68565a11e2c847389ed68fafa363c1b8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e500362c17d245b1826fecc94d69d968', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('425230d84a01447996a96517984f45a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('088fc21606d14b02a4696a7d72ea4d3b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eb31d7bbd0a948ed9f370dfc135afde9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c7ee18318a2549849fbb876661a2689f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9e6978d070144c77a109502755cde485', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e0dca5af9cd446ed9f6e5d8c2a2298a7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b1244021143e495280c6e3416e4758d0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a4589b79ab944454803a672fa91cb319', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bba4de84e600452ea11d8005a138dda5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7b7cdbfeff1742bfa9a2be317083172e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('10f62a0c30134ff2b3635448f790417e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('25538e59a6d34608b24c0c95b77dcaf8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53a90532b630418484fc7f2d2b8ac258', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1a82853ea9bf41b39bfefa24c3a5e4b4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fb723720aef240a4b4b29411b9937668', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ebb6e6345e264dd782c07dfbab7e4ef6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包2', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fb9042e200ec4055a1013afe0c55f0ee', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包2', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('05dedef7376b4eef868859f3770ef6f1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包2', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e0d4bd7f47c74b10bd17ad84127a9f30', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包2', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53fa21feac2d42bfb9f01fb1aade24b1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包2', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c3aaa4e3cd444ed1bf7358aed0aa6e26', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包2', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4eccce09de6b4fbebe64d8218d6c3878', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包2', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0f7ec2fffd9542c68fd54dfc91665657', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包2', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9e43e2e5a1c341f7a509682b644a1bf2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包2', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('923c3aab91014bb5aa9aa2e78c8ea429', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包2', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('62e04def76a9475bbf5cb556097287e0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8d49698c121a4ed4964be18a676c8fea', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('de4926bbad8a4c778c6426deb0c7f8c2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c23a7f414d749489aa9b05a791376b7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('94f130b559b24d909800a34993635513', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('230161eb5f384655a2346aef0abdce8a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包2', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c3cad37c2fa34d56a550ff0310c96ad3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('535564a4f3274879b37cd76582715725', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('99c812a7932243e3bf9b72a0fbca2baf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5e56a349b9ad46c19f594f23b7155b32', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3b1222f473b44dd5a1cddff123f6e29c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2eba123af0bb40bfb165deaa003508a1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包2', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('140d059d0fb243d4826249aa249dff90', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0933ee450cb047cc96865b9d87099b12', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('825f7d8df36642439dd3a40c1f8d23eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b492bb981f6e45fc9b25af21e6b4e17c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5f157442e7414326bec324ad5c693981', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2cea77d64f3445d68a22f213746bf3eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a1ae8cf6913f40cdabe97e9b394963b5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包3', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('486abd5ea34348cfaf9b8ffcca8d108e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('980919e6694d48d683109d203557c735', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('38a7b3a015894d17a87c49fce75a9182', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('88385b0caca0428cb2c26c82badc8a72', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c825ef7794a844d69a263a46e6829748', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2d56b7bcbb8643a6b4eafdc61ec40b14', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('abbf05b6d35a4f419abb00f5e60310a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包3', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9897c38453b0494785b1703cff5bdcfb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('00d2ab49cccb442d8a1e11cbcb37610a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('797423d831a142f185e596b268434920', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0aa1af6fad614b6e901dc01f395e4d3e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('61b7f9e5fb5847a2b9cbb2f6bdc61179', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f8748cb31e7443aa92b010c954451340', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('62c09f1cee0646829b075d24f48316cf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('22b0571fb7344e66807288f69cb23093', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('28ad00893fc84f3b95ba6acf0bb2d696', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f1d6ccce4bcd4614985cf9df5e755997', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4d0b59c733164cfa8b6d8ca676743e93', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ee43d986dbb74b6aa0f7e6500ba09001', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f6bd6248056343078113d10753a7b12e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包3', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b6691de070bb4f5ea750b99b3e10d249', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dedb164751e94862833c34e42af252e9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0022040d575c4b6795975e8f5543c2d6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('569bbf68754448fc9965409483cc5eb8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('12c7613e9a8340d09c1523d70219a286', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2f3a66a934174f208d28ab84b2a00622', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('860b00fca2604db38200e5f7a2c554ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包3', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5d544bc67d594e459793f6f56b0c12a3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('86f5f55fd1d342cf92f6908852ea5ca4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ae48e150142b4e04ab0eb43904049843', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8c56e7c304d8415d9c373155968f76a6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('79899cd6c3a24f80973da27f050aa9cb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('557c2f821c6d42b5988fc4acfa3318e2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('170b333ca7784c64b8c8b6b84df5524f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bab2e37c17234b26bef3c7fefbc24c9c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7357d17fb1b84783b64f87575ad71f2f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包3', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1f854086776a4191bd6444d0413e8736', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('555f7392ae6542cbb3836f317c9e2d20', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('87b7bc7a05fc4076aba61fd2b5436d7a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6573b2796ad44d218768718600ee8dbd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('31725c6182c14584ab9bdd3134c615ce', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c336d13116b64ae2af41f4cd13e9b913', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f283cbc6389e4df58643b5d7d222d49d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6ec7bc6053194850a974c7bb4ebfe095', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f51e769dcd354c8096252665bc34d094', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1951217b9d0646fbad089a566fbf3143', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2f8e8cdc8d1d459e8cd4a0b4614af13f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a92f26ce624240138d549c97a965c97a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dcec6d25d88b42c1aa5deb672efaf654', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4021f4ecf46a474d9c709858545b5729', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('515a577f85a04b308868631c369d6a6e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d148142152024ba7ae6b58451a64d3c0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e17771fccb4143f9902407c979dae366', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a5c645809e9249838a6fdadba6d8b441', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包3', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('26a4b7cea36649c996d1d6312a3c7b18', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包3', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cf4244acef644a17988be75790f6d93c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包3', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ec825a3a5426441eb75188006c19f8e0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包3', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('21cd579166e74b2198afa10f50c71e30', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包3', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('85f5e6f27e914413b2a51429e779e8a2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包3', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c66121e8ad584062ae5855f83cbcdcd8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包3', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6133a215e8564cf38a65f19786dbf7e2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包3', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b964c6be71834cdf8766fab2ee24eeaf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包3', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d227efd74f3144aa94f6ab1262e92c78', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包3', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e7f7653e6987437895c11eb1fb430596', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7b6eb137d1ea44fa9371a8e836b382d0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('df7b779d725c4707ae7ed748f4c7bf82', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('708395ea216e46ce9a153241c2c3ca8a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fe43f67b4a7e4e6588ec01e59c009f98', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('249392f26ef54672a8debcc13cc79b29', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包3', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b63e87d65f18416994ae7c2e41e200d4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('043c4a71d2fb4136863864ce122d9544', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c6f8714897e424aabafa096ffed36b3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e84b498f3c624f7baf69c37f34c07103', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f961b8d1011f468b81fc1153aabc5fdf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7ff0d22fa92440a09e96758407a8d67c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包3', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5d5c26017cf14e72848450043482eae5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8992c3f410794b32b7c147201c1e540a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('77a221c7cb0b431aa61be80802af5be9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a23797bab4444ccd9fff3a46f35b8b6f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('00bc1f8d7cea478db46edb79b177175d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4887fa5b8cb04a3bba16e379f1eb88ac', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0e4e97af761c476c9eb09e18ec5f45c0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包4', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('582348fa6a234cd999bbced7e89712cf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('87eed0d214b84af9a6c0188415f257cf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('080a37cc80ec45f9b2658801ae7f9d94', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e61bfe95eb584c1facf339da5de235bd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4de29f10444644fe8ebb2735b27c022b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('650bdc4712884d67aa352d22530750d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c574138a488d42f4bc2fab73533389ef', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包4', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('613829b540be4b9198a1f7f1d016395d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e4b3e57cf0664574b9c6c0a085ba6ba9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c6915ad7930c430eb608a70746a1821a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f046708dd3d2479fb492d4e6b809ec82', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e9f222148f24840b42c1b251614a638', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('98c1a6afcdd940b59f333863f3831540', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('12949c2cab0147558cb4b8382ee54600', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c34a7d3795b840369a5f995239438123', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('10d27c7d661347a6bd5e158c41443933', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e45d0fce1eae4c3d85a1e412a86e14c9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e953a7f8ceb04d8ea311f59735630e9a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a0a692c511f040e8a091756bbdb77e75', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('46c3af0a80684b23a00c1805397270c5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包4', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fad75c4baa7c44f8932b088f6e0c20f5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4db7f3d70d514fa8b0b97854957d5d4e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2e852d9007ba4a1e88cf82359525e282', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3d4591b03ca84dafa5614d3b4ad8b5a0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('92121c0b1fd24b52839777119264134d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0e0c5ffadf40471186e8889eba139ad5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('23ce18a2702c49e69e568d835dbb2082', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包4', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e6d6b4d43e10458f9c7425db51b5fd4f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('90fce001b15f427bb73989b08915be40', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('379839fd01d7476f899fdac69370f091', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('86ff829f3f084f02b92bf10762868fa6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('10a6c40053f24117ab7d4fef2f2b7c2b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8cb9c79453144437b6f3464d943931de', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8c1bf439109b4b88a91fbe1162142bbf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('206f15ab363841439141264e0c82c754', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7db390d04da949a682e48740f117766e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包4', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4905556243de4136a3edbdca0e504b75', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('270ae55b3526493da430e59682d744c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6772aa30f35c458ca154a93a4ecf7618', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('62fb69e86e0240b6aa4589288f18aec7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('48cd21c8a8cf41eebe99a08807034274', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5baadf05377b4b8fbba07b67985517f1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ad35a07415844518b0e4321849aecba1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ffcf4af45f104730a79d90350c8204da', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c32322c0447740e59e01e4e38feb486c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('14a1999b6c704810b645511ccdc8ce52', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9bfd04245ce3486f8ec2deb062320533', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e6b54a4ef1e4f4097c3ad3703cd806b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('872d40afc4b54bd3885812c9b3046e40', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('55fe45558a87418580cd981b88503050', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e98b8d9765064ed2a682048860b5b137', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ba417a228656492084fe8778f0c5e592', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d82c4f87bce04959be704f50f084a329', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a4a1b3ffec144c1e9cd32ece68db26ce', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包4', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fc6dd7f846af41dfb83f7751c230621d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包4', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ab5e16eff8b340ce9ba454c42490b5d7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包4', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5a060011206948748d3c30c463443176', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包4', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fde046e4fef8478ca5da9e542f0e0bd0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包4', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('922f4457a7274ba08fbde9cd203e23c8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包4', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4d6e9ff2389f4a27adf39eb1b3886b7a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包4', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('95b3863c66e04fa78e51f426b8ac9979', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包4', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('93487dc90d3b452caed1fe8599a8de14', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包4', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('38d3f7757a7c47d48e6d13c39fffbb00', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包4', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a50d6286b0ae41d4a748029c6daf07dd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a31eed07666d4a9c8bac957ae974bf23', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('22fbe67058aa4430bd670ffdad9a5902', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0dae4b0b615e404197be6755d10ada5b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ebc8ba0ed7914d78b88180b0b1f171db', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('737d418119a84bbb8852335e84c4a980', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包4', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('be80ab09a6d34c129bde86845def2175', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4f54bf3d2dde4852849a58442614f7f5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cf4f68a8ab094e47bd99d90c035a1dfb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('795b039269304717a291cc0e6326a4dd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('47237c6edd2c4902846280acc706ebfa', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ec736d6eb92c4e9b88c55ef8348242fc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包4', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e9023f0be8464fe4b6578311de5cbe00', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('95ac8e12af84427fbfc40ada4ae1e5fd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8740ae112da64a089d03f6afc68d9675', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ec307ab13f904fd182eec5ec50efb52e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b890bc134c2540f08a5281bfe41f2ac4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('905bfd4b960246bc9ad86c356612c467', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('65d9a71b49254e07ad507d937b7fb280', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包5', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b9260d5c9bc44f2484b276f76d4eda46', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eaa267270d7e48fa85bd70140167e6c5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('23c8bca07dbb48be9422ad30ae7e618e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c2f40346167a46f597f1a1b3f92c98ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9df89ec1fe8643c6bf10ead788702450', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dd7ce5b4506d4d8b8d6ef29409ed72c2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('600d5b3fccf1481fa467c44a2e4e36b9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包5', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a3a950accff44f6d8ef29417bd14c001', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('276ab40f0cb64121b4e8948c64bb4563', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('efa1b10d7f6145509c24b3d676090fff', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c2a6842ea7174fa992476269ec0d7c65', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d09cc3113bd64465a692c4055a267297', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fc05f45e711b456daee4a93ff70e43dd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b7ca56316d4142598b0537c5d15fcd70', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5518b5613d3441df8dea453d2c11f957', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('412988b12c4f48e9bb97827ea79c946d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('08797df0d6674aaebddcf27ccfc102c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7b09dd734bbe402693beb3e12165674c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('359631da5c7f4653926c55c60a69d87e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('51eb3f32a6ac41099979f7764e2abda9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包5', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('32861d6f08f44ea1958ae4270a166f92', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3dd055bc1fda4ec5b0c7e62b09990870', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('baf36f430d5d438c85bb6cb61f0fd4db', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a54938179f5a4c5bb3e7d2487c4042eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('25958ca4182648bebeaf41dff2b34123', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3d995b569f84461087fe1408601eed56', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ff181a423a174a75aedd361bfb31b2ae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包5', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a3391d1791a04adcb6ed66adb8913d0e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c9e76de2f9d54902b1b54922922f8d60', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1d74e097647141d0a41eab1954821560', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8fc2e180e1cf40b9b7f797baf9dc8afe', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d3b4a7f2d84843c2a81c0dd53a6e2f3a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0d0ffbb0ef374b16afd92d54434d94d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e5a98c026d5e4ada980b456da47398b8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2a71004865864e189924ae0514fb221f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fbc9577cae4a4ee9ab38c5be7072e0f8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包5', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a7ffb290ebbd48cc8f686ff1bd0bd1c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('65d00606dbad4c0e9ff333e5fb9e38fd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2efb5b0280b345089b763cfe9c24f772', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eb091c266f2843b49bfcfe0ceebe5235', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c4d788dffac46b485203bf2fc16fa11', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ed4b7eed80894ff395eb704363343607', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('60f00d6492d540e1939289569adea39c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('19925255dee247d58aa255142f6c3d00', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e082a7d429134a8c9fe13c53ff47cf98', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('542e034513434a5f944578d970fdfe44', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e320c85daec4d799e5b0d912397d659', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4564907e98a843ea98803921dc9bc8f9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f180dac2abbd4ff3a0d4d81680aba88d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('343d23a0653c4fbcb2c84bcb6a36c586', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c460531e0cfa4824873e648efbbf2494', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('669207ac026846cf9f0cfcee418e868f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b0a9e25aa18e4cec99046588b5d1bc52', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('87d4fc32d6074132864e41f7da9def64', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包5', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4c891ad1d2c14729a11c1264a6eaaa62', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包5', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('352f187cecc94132b93c7dbdbf7c2952', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包5', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ad3c5e937c804115904435a645963e29', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包5', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('13ca992ee06943cda2b5f41a606c0830', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包5', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d402b84afa774d8ebfbcd69d5fb44689', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包5', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('860d3d54f6f449519ac143ffbff89461', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包5', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d36a34b888c943d48e680e18672cf898', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包5', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('81585eca1e8a4be6aa0c8c31ee6ef878', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包5', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0945916dc4374ca2b2b8ed28ff681f3a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包5', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ddaaf95797894c5a985447692cb04ae2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c6ac34932eee4f8eae3033f6deb1ecd8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53e99046daed4ff6bf01d75cb086738f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d4bd15c2251245559ae2fc37172e4b10', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2656a802bacd43eb862517541557d990', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f1a9c3cbc66248e9af3f4967f08ed556', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包5', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fab5a0e34b4a441b8772954f7256397d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7a1e8dfabe2c474ea3947db92bce8b50', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('74a5d05f72f64265aa2610217b6f5ce5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e5ec603a214b47c1a93e51b16161b87c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c6d03b7949d94391a55634e586b08a67', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('865d86e48eb9433793b903aedcf957e6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包5', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('442f5ea55df34b558b4fd7a060e05557', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b924ff81169f40379ac3253b720ac374', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c43cea595d6548e593aaf09d9408e7bc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4f39718ab3db4fdeb10c8f8331c81ae3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d68c2b31bac04e05ad58130f987cf740', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1a7db42f7bdb43cca0286a28c9449308', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a6c5330123a44039904cd2e92263b37d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包6', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('431794648445472dbf552a7f8b55c900', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d45bd81e8f6c4c8aa5d566e3aa1f9cf0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d5a7b06b043942a6a19a584135af432b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('56ae80803b594ff19af57c6e7ad43830', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('80636dc5c38340f598e2330114f86676', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6488d8bfe85f45bb8b065a2d5f7e0c0b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5ffb8e9b8ecc425fb8b9e62207d5807a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包6', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('21d9867270c3492988f27b2cde32a2a0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('23c73d90075542df9554d0a441fae6f8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2286343070bd4e65977cb0fdddd24c94', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cc1076a0a91c43d7a13ab6acbc89cc9e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2398565ffb754af09e9662db12e31b3a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('df4372585b514b5588b0969e57402e85', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e9244734b46648e8970778b9abe8c9cf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f6ed8c8705a04799be155e115c9d0bf6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('aabd1c0cf5d0466f931ef2ff0807875b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('248bea0fc3e643e4b22e0c57407ea9fc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('07974087ea354b009ca1fa07339791de', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('15e55499f61c4f06b719fa15c5f5e8be', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1ab87cc4e6924c9a863afd721ee7e7d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包6', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7b7cdcc8f18c41089f5340999ed9accb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('555a71f4d18c453cbbb97e035722183f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a67ebeb622dd44cab3c22745e62c98ad', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('91b8f92aac6443bf89300a8261993d05', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7f4a4517b9c148559f0fb63dd50cd080', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b7cab34dad8c4003bd870458b7bf0106', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4a7c6bc2ecac445794077b698259f264', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包6', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d85bce0a25b84a52a9de5b4437dca461', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('637b851f1cc34aa3bfba3b486546a2f1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2746bdbb18a44f809a443be4850ca9a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a852cb861a3c446eb41be92db9c5e9a6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f031303868f84479bc0e8373c36ced81', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e5ea94fa44d7471eae0ddd9b68548c6a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ddb11bed807e4b64aa4cc8c7949ff968', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('156919ccbe0b4b27bdc05f5bfe37a11b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a5b2033ac012470e99d619115a99d547', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包6', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('877eec18a3754b06a4170e190a46bae4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e3dc12a888f14cc287c308124d68d959', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ea158f6f40ed4dcb93d9878f8c52531f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('33e00ac771684d05911aa6f4cf2ad4b7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('585d8a936e8e48ca85c27e2f34c14a29', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('03fa174323d646b58ceb74fedffbc4c4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('323447ee3c204696bb4452ad2036ae09', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('632fa2795123483c89be6de282486372', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8ef521824a3a4251ac70f41e6c4504bf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e0b918023ae844489573b11c07f72a34', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('66269cb17d8e45418733cc79027440f6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('80b93ac48a2f4a41a69665fb2838bb46', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3117f99eba9143729bef94b05a53a01e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2432a9ffbaae4818857ee4d58f6d4b4f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cf9a62fd7d1c4034ba03b4bec928d52f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('22f9c7ae604c4d63be193afe8375038e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ecda6e08d5e14fdb8a5ce1eae0b49e93', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('73e7f9d829b345879598ee22d914db5d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包6', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8a18daae70134ed38198deb152f0bcaf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包6', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8008d1c5de32442d9520d35e19a169ee', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包6', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('73a15581747742f68cc983f5dd36bcc5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包6', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d4ac10c1afe346a6b062a4508037b623', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包6', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8f2bbc6e49b440d481f1ba2a2a1c1d36', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包6', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('48064c985d364912871a16ce4635e4c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包6', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9f9afed744904d438f507a5591e7f958', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包6', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('efa14bb26baf42298aeb8adf837f7d10', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包6', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8ad4c43a8c874023ad6b47beb287ff1a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包6', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('875a49a332ce41849362328a5726bf70', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ee5a577314a54d94ab75feb2f2b5977f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('22b967f8f8af4efdade702ddafd265a1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('880fd437f40c4bd1b78fe385e93c356c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('20237b01fdab462689c7709e621b3bc8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4652f8be7e9b490a9d0451f8a9e1cc9e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包6', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('914d9cd71de7453785b62f82ae65d86c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('02e0a1030fa94312bfee98ae1e055416', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1d6314de36164f4eb35aeb037d761f85', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9abdd0f7a92c48e6b7e3064a3975a562', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a8ab40b1cadd499c9dcb59f7952c2729', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('09c6d81a34be46ec805c080baca01579', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包6', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('faf6c15575a745fab2392ed690dce413', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9ea83ae38dcd441cb6a54a86e9328148', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ebcf878178de4876a09daaaf97348f0e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('724d70069f4742bebfa2389bf346c866', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e57b9a8e7a164894936895985ee11687', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('27f8902fc08944ddb3fd76df543deb2d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2e01ef08e9ce4150abfe7470703f4962', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包7', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3c8acd2d1678461981dad855b61a4a97', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2237bb00e8734dfbbe2c75e11f10af7d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('36712e282afc452992728fbf98f64a23', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f1d4bc6e15f5483f87deb6c7f85a1761', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('66afdf0cb4024b4a9365ea807612d8ff', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('265c98450ba0429bafe0b0b8d93f9682', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cef8f417a1a546f19c81e449d58df786', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包7', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bfb72b778e0d43e2b531fbf0b877bc0d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ec50447088d049bfb0f5875aa3da522a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('26e940e414664f3f89ac10d4d0f7b2ca', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d7ee5ef357dc4ad3b10701c698ff64bd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('51a406de07274646a0ae1770288271ca', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6562f09677104399b0c58cd166285c60', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ac9f2d0de45244c99149a54db4ef7d9b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('af8103e47c1b4f3ba3ffec2aaad1b71a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2d1554c7ccba4dd197b240bf6db54df9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5cb930410b0c4458a7ef6b4cc350dea6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('649ac9cbbd8947e2a651bc477e1a6daf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('84f765eac1d24ad49139bd08b83f9989', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('59845f500fe1420a97410ba79ac55134', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包7', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1936c2c182a145e2a508d41f491de920', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c03fe96791ca4e578bb7395eee29b3f5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f968af9d43e34e759995d7cf2d9dcec1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('feabed97cff844f29f81aaf9fce485ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('74d7f479d622403cb56e99b5aee9df94', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('21355db6bdd74194bd66f44be40f35e4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('28f3eabf113f4e42beb1dbcbaeb7a87a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包7', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c75d2c31facd48d58c4e4266c219372b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('02933c63157c4308a55a555dab113d81', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fc5c96bbd53643bbb6bb763161abf32f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7411355432e14d978d4dc926410a2aca', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1744099a8f4b486ca9c940ee336e534c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d7f00ee79d0c4d9a89448180ab0661ed', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6707fe33d59d482bac518247a5561484', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ed3238197af646d5937d55172a6ae749', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('034951dee46646feb6fd984af6c07552', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包7', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fc68920a8a9a45f490c7d6869a55a86e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8f2cc00b088346deb3c55cac1a9a04cb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1a53905c226b4178b1787f813ee72b03', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0d32773b667a4691951716ad201534a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('302d0474953c428abfaedbf565b44c1a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e3318582063842179612abf6380cd752', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('730158f09afc4d48830f25b56ccb3596', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b32ef7c2864f4edf9091119cafae3a17', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('36ca6c31257c4d4280e6a12dfad54f3d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('89aa602beb604d90b4164f64893dd159', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('12703aff0e5e49e2a252d102d21e5865', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ce8e3a66b7e24e34a026cb21c46a4b4f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d0a59608a22b4d338d9adb28c5eeb2e2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a3de3a62bf8a4eb6b537e1ac05c236c2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('26de24c47d174f62bb0ef156786d4e77', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('174d0a77ed2e485f9f06207e68b7a157', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e29dfd7c5f7a4f559af2ef792c1c9b5b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c88cddea12d34e49868f1f62c82208bc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包7', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('07e3935061f44fccb11abcfa2865e88c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包7', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b2a7743f243347408f50a2ee4f67cab9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包7', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7e30d358a4a64b2bbd3b007e42f90a74', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包7', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('17f29be909c34be18874029aaca9431f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包7', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c86db3d2aa84d539672566bdd65e423', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包7', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('99e5ea0abbfe40fa9aa3f4281be4a5b1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包7', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0c5cad4177a2426cbcec0d9b63745dbd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包7', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a6b597f7556942e9b3a0b50b5322f2b3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包7', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5594a4633d1546f6a16a780884488090', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包7', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('209ea2dbd16144129991d41643e8ba4a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('06bd8ec5a57d434f8d392e3b6da10b5c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('30c1bf3cf82b4ca6b7507dec3581e9c1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b86bd771c03f47088ebc1a0c6f9f1f5e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a88dde69b75d47e7a018b4710376297d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('eae5dfb66bf04c0aa176d183cb639e6d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包7', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('61de51038bc1498f90e6b27e0925f563', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e4d87c3ff966462699bf76c1aa7905a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e406cfdc2e3b4924b70a9cfad68d53a1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c3990dc1ee974adcaeb509538db42ec5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5c9d17bac9be43caad5c4baddbcc95a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d53dc2c18e9043f18eb031a2855b9c35', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包7', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a05a7099f0e8483a8ac8d005ba3efc44', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('03bc0f9f63d34d4c88c9a9cbdb96c5fe', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('340078a7c92a41e98af71f5b36c8179d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7287c530041e468d8b9156b85edb479d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('65990fbc4df04f86a17763a389750f16', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2ac215dbdcdb44248760ff9c14f22823', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包8', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d036d5b5247947d89bc91ae243bfa6e8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e4ba4f4d8b214adcae4a8f4bdbd221e0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('30f9130babb140938abf5d42fa826419', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4c2ece57bd9040dc83a8f272f4d52554', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bf0e52f65fc24e3c8100f5c685e14e28', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('675f097c1ae84a59bfc3934487137b25', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c6cf0184455a4a988670844e54b33975', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包8', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f32dc0bd898f46d7a1d409723921bbda', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('07f7981147e4451c8a7d44c3bc172177', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8d5d913ca45e49479cccfcecd88f2af4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('58e32a6944ec418d8e027870566abeab', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('894f49f8c96840b6beb09aea7b23d158', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cf7c1d67a592468086d033aa5b1d292b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('644c521dea3749ee8145a65051be3105', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8fda9818def34b418a9c1ad0254b59e8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('284cc8a2112449968c4792818d694534', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2ec55a48671f429c9047c7ad688151d9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c79e94fd1d2f4d43ad2808a71a585585', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c1a71bcaf3034e4f9506fc5e18096561', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7fee689f73404730a33f202ed73a55cc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包8', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1f07ed8116e040dab1991d9c587c7dba', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e7a4303eef5742fdb084516b26c256b6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3142f100e0c0493ca5afb2aa0ddebc21', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4174c294badd46a0915503e29b21bf22', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7d0cc52f8033467fb1f93d07076d6eb1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e54848bb682a467cbb9096eb0313fad7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('243143587e3341a8adac6eb2262b6811', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包8', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c031125d342641cf8c159dd20d4f6a81', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b5c8c1da634d46aba1a4ac60715cabde', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ebf4569f925541c79fc499f27b7c9ce2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9d7ee029d0764996ba9d4c29dd6397a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9c311d8055bf43af8f71c1de3a6d4982', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('aec37c1e732e4cc1982c8e0ac310eba3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53816ad94901416a905d0bde9781923e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('af2448c719a34620b701a53dd9719663', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4940914c255343ddb661b284b719df73', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包8', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('00bb2c8017cd46998416eb24f382602a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4e182ad762e34d27b21842f6cff52a8a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d13c523c8b8f4fab86892278f01dace8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('172a9724912d483ca1ce1e260f1739c4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d79b9d4e75834baaa25b81b61150e2c5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0ca8be5f72ad4817bde68a85570d5840', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5f54ac90789c4365a2032a773cc337b8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ecd207d8f47641cfb16ff1170d7ac065', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a5c8b4f0d1b7488196580fd7f5a6f41a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3ca2765a30c1407cb115c05d57dfe755', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('423b3a37090742d4955c66d5ae92d7e8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e63e97518283467ca685d5eff6ed4442', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a1fac1e8c13b475786956c8d05624545', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d45bfa19ca114d9883a11bfef042887e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('72d291f83e564f21be9c551269b3470c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a30bfff681634188a487e35e6a556b42', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cac13978eb1e479497f0e21cec643caa', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0ec462fa84274767a3ddb9e7b182e04d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包8', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('290431989d2546e5aa2301cc843491fa', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包8', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('60a63c7e455341908f69c292fcf34b91', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包8', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ef60acf145cd4abbac4c6798f2f33154', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包8', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8b01e006c6e44c25b3ac1ea8155b4d82', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包8', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('87caff704dd04b0c920fc02c7dcc5285', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包8', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('102c9d856151401a9072c9ec690c1ec1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包8', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1f3409aa9421489ba60e9b240a2117d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包8', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('efb5cdd7a93b4b1da8de45b6821422eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包8', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9914f267150c4ffe9c08e9322178ba18', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包8', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('44cd408007d04e0bb5e7c0da06332fd7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b1aa73ba998e4f2bae90365a0c373bcf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d0f3ac764c234a01a7966d8ad724154d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('42fa655c80e547bbb21aa1d4ccb90fd6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1dedb25469f54c15bbd7bddf4d99e42e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('454872244509485e8259e0c8533c8da5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包8', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7285d1d6a96e4db290458a02d67d409b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9cd91a5c8b6f4fc4be0d8ada00200991', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6a7ec572d09e4fd58da96dd0562e41c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('31a38ad3623c488dac89e11160963ca6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0a69bd549d04499fb73e12e73c545638', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2a0cf6d9083244eda7589a64227a650e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包8', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('81a68062718443b59499083e5f56e388', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f4d428f629ce4cbea643e1a8194d7979', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b4bde0bd180841ba86d1c41f1ff33f89', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('79f751e7c5f54897bc65e0258030c21b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('96de93a5b6d94e1a943584a0fbf3ff32', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a0940a816ffa4b35ae024bfc3ad81d44', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包9', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ef1b268bcc494eefa9dc1b420eb6bc91', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3567896687f34c4c92f8a13205a619c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('33774c7841b3471aa3905534df72736f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('caf81ab37af442a88d700b0e1dce748d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8a7a9a2c832b466e848723b9e4f623ae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('10df30a7981341eca23cb51c6b866bad', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('47f74c0f27514b98ba4d4ac44b405808', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包9', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6dc7bc095936479bb6fce3a1c1a48c9f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f55fd34c245c4085afae09c078b66bda', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2154060f203b440c8fc69600e222521b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('85371a48ede74c8e859499d8bf8fa63f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0ffbf84e6d69449aae248d0b5560c3cb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('539d319eb5624f55b675ec8d15575fc0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a71efbf6fdd04217a803e9ec03214647', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('715880c944ea499a8bb58170df1680d4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e1e43c5fb5054a898437ee219ea6fbd1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bc29242c35494f6bbebc59c37da6179c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('19229716c5b54fe88bd7611fb28e3392', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c24c2d8b50a7490092cb0417208def53', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('67b68c139be746ddb59a24a68eb5c78b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包9', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f807eeee4f864f0f92d20a6ac41c8257', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8781470d20d74475a1bc657284a1ce0f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a28c5478e74c472893a1bceaae0693cc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f948d4259e074df78957a67dad2c4cb1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a6ee64d7fc26466cb7230d8847dbb0c5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d740f631fea6446f84e23d8c61bbee26', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c87bfce20fb147f29ae24f0055921ed1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包9', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8bd0d0cadd6c4343a0a7d89e8d6756ed', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('48b7b3c3bd114dea835deaf06f8324de', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('420539422aa34543b650ae9e20920d6b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f3dd2feb61384942bb1484fe54152a9c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a95959646c544518befc9f299a216d56', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fe4f81d6d06546e0b4d56d171afac413', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5ac2805cbb6249b396e3e28c5396e8c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('91b25b37d9fd4ee2bc597f62ca3d4453', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7b973960d1b048a8a13f8a43318ff945', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包9', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a35bd77f505d49ccb1325794fd047173', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7a7404b7a5e34fd69c91d2312e33e924', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7dbe4e014b264e47a59b5c988bfa2ddd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('53653d04f0a544c3a68e78ed0f9f3706', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('283a99d77cda4f2d874ee4a59558596d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('55944de301994708988ccfd5a679a7eb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('12d740a3ec5b490ca7b1744618b0eb6f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e17fd652f2f6409dad264c7b6d5bf3e1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cc37cc6174f542cab1b055daffd2a258', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5ac00299b60a4205a1ce3aaea3d1dbe6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c2ca3112ebe2458186bf85cbfe2e76ed', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('03d75facd60f46a883bd33d1d8814059', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c1ca1156f22246168a0e87185c996be6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8ad4f41070f5410d99ba0001ecd55329', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('728232ee9baf4cb3a8af73e1aecf344d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('373f48ea795f4d56a39766ea132e0b6c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('73a1bf5336eb403eae817088b0aa0fa2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('bdbec7919d5944e5ae6921a495978f5b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包9', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a30fb7808bfd493b8f7cff94c951c330', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包9', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c6080cfc66a64631acaabe7594909622', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包9', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a0d7e559afd1407f8fd71f79dd7f2cf3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包9', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cda9dc05edc642bbb94ceb460901f323', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包9', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('270dcdeaa5674481955f91837b96618b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包9', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('85951d230d214655b60acd22791dba02', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包9', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('52f9c9be02ec4b549357d33cb7348171', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包9', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0df2ea45257a4167a01611b8bb3c6d8a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包9', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('327a8f252bb5456ab0a88d42cb67dbbe', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包9', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fb1614a6518b43509527c410728116f4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d35102230aab4634afc691e3561ecdf1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('02c360effd2248fc8fdd0accd65d9300', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('63d4407fcc3844d8920062743c1093b5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e430006c29eb41be895691bba750d8a9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1c8f489a26a2444bb4f8d2d0886293c1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包9', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d8179440850345a19b2836f0881f2b2c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a6b0bc3dcbf44755999a4b3ef3dec20e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('91c5207f78684c2781a2ad9b8d2ee94c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0faa0997955d4f13961aa7a2c9993dd0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('48c89a856b064151aff470a8a248324d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('035d2f609aac4aadaafe7fd444c1f679', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包9', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3a16ca386f5f4341864f6717e5d237da', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f770cf18ec6c4947bdb54e2022d2021c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b9f1934e8b204988ab34b128eb973c6b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c10795be67c44faeaf71cb1b7bcfa6e7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('02967abebbdf46d59a874be644da7191', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a99885e40d6a4fdda145de332b98d9fb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包10', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c5ac9cc3f933420d86ea827c48d754ed', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fe78238b42cb43e4a8c6ab77c1e10bc4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5afc189bbe82407ebf1e1069681c676b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6642ba5f8e0a4e329647e847ec410069', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('34cb374e63724f39a51134980467c7c2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('73120dc2693949f49a17d4fb410a2eba', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8eca926c0b1a47899a954d24c267902a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包10', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7958eebc14db46aab65a07dab0d1ce32', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('38073136a8744df9a336eaca6c71b205', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0b601e4de23142db93a7f612a2918bf3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e634e3676667478caad03f87c8a24d4c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('faa540f9e3264a86a6102b2bf0e3ef81', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('367fd4ac50c845d1ac1a447ca17f0066', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0a916b23cd214cf1a64fa1851f476624', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('72aac78e5e0c4903bb8809902583c4a1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('049f6cecd43d41248809797cc81f15a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('01798ff05a0f4d5a80c907cefe712ae0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0a88e6f02c9d4af3b0fc30c92637535d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0b431dc361224d3c81dfa6a482fca164', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b5e81d78d13f46538c590f9f7215169f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包10', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('64d67c1301bc4f9689cab9130bf9c87d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('92aa3849d18243e2b738875ae5ce8ade', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('481152a24b95461983060dc920f90ba0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a77d827adeea4fbfaefe7160b7a7c84b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2a57d3d2de4c4fed80d3c71f8c7c77ae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1875effbbad34cca9fb874e7df437d41', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('39de15687bd94aa3838c1d65c79434c6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包10', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e52e6ae9c3ad467f9221f0c809081a35', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f2626ef5d8a145219b7b6953eafb8d9b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6f758aba9e23410f93bfb14771e4e226', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('48b8977a0b3847ab8faf31cef8751a3d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0fc3ed7c81914a288f49f1df7d67db5a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('be90063bbcb545329256a2591033c8c4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fb3c6e7677e749d1846f4386c7018036', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('25f9a0c6c4244880879baff31e577184', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('446ed589e2c54cbb945f04577d89206a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包10', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ea9ef85b294447678f559fb44154882a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('65ae501e503c4570866fbe0d287570e0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3a18dde86e224d37b3d262378066d1de', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8a176f8f18994e1ab8f217780e39946a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cb29e31a4cdd4845af2156f0b9c408d8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8540f42b631e4aa5bea9dcbc4990668f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9456982ae4f24ca4a917c457fac18c23', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a18eb1aa2c0b4ac5867a54a9a7edf721', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9344bc6cadbb4bcf831e40198f6a325d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2750eef7244843f79a30c29c7a2809f0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b6d1825ac6e144488cdcf2d5fff01eba', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f8f0c28ffd7f4925854a4174a809c58e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('29c6b3c5b94245b2b1e364856eb7b0a5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('390a3b98695f44c7aefa5a9cd69907b0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f92b91e037e44015a90259bc5d1892d5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c1d5c012a8ad49d2a7525ba21065cec0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('91ee827a909b46d18ea8c46cde2b0e02', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8154a031fb0744818b75beee9e50a44f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包10', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d91771aad7bb4d4ab734e11cdbd9100d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包10', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('23008cd9af8f4734ba8808ec0299dcca', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包10', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2547436aa121499fa9b3fe1e5b0ba3ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包10', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('40f78ae620124b9b9bd1b7c6da0db3c9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包10', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6c5be859512e4b959bfb159c0406888c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包10', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9461798cea02458b8c143bd5163cd50b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包10', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1216e0eab83147e292855cee5a4a334b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包10', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3f7074c9ad324050850fde2bea693b97', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包10', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4286ea48fe4149c7b79603f0d2371886', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包10', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9eb273ec089a4d28b37dab0b08869ff2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6164734408fc4178923898529bbd24a6', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e4cf13e37d544c1a8fd1c61471eb1798', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9ae18a95f682422d8c0e07a93437ae5a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('355f39f1c144432ba740f288f130a327', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('88c3ff3a8f384e55a17065c8e279b3e5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包10', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7fc8e3eed4e546e4b1e41fe6f51039b4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3329832209e740b2987c8c455413a506', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e9dfd5b96d434f269b4d47bd0f53911c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d067fef37b774ca695539e3bc582428c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3f2174dfb09f4a099978107685ef5040', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8968671afdba453da926bee8e9e1c5e8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包10', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f859f0deac58434aa24176bb62686444', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1cdc43f7c41e467b8aa3439b2c810404', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('470d7b52947a44acad71305bd0223dc5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e10331453cd746d58d44beaf3b443a68', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f52f39874f3849dd882d91bc2995b63a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4ecffe8cbdba473a89fb6d87b85b0374', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包11', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f68a998888a74d01a3ce49f9da629c11', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '青岛鼎信通讯股份有限公司', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ebf69349806b45ccbccf4d0e8dd087cd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('794cfb759af94f3499c726415a3cb5b7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '哈尔滨市电力工贸公司', '13614514492', '1873356122@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5b7b990e8d9c42c691c31ac4d57d58af', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '浙江八达电子仪表有限公司', '13958467756', '158038651@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4bc4ff525bc6472aa21cf04fd0f2b4b0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '江苏林洋能源股份有限公司', '13914381919', '1458312399@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('32867874d5884de7a742b839c38b356d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '杭州炬华科技股份有限公司', '13656638769', '8054464@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('73ec351da1a14484be85b484590b7ef9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '002-电能表包11', '华立科技股份有限公司', '13503652612', 'ruoxun.wang@holley.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('afab8783f1b34203b3e8efcb0917164d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4c09cf91b7bc4b44843c2d9347948a30', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('754fd0a2661842ef9984dd403b421471', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a23e8c9e23c5496aab8b428674f81b12', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('89ab8c4636af46f5a40c1af9b2a775cc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '深圳安富电力有限公司', '15546118234', '18926769449@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1aad43d87caa4f2284136b571bda7f8b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e6612268e79342349622d6ba5b821d02', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4270d584a1944126baada1a68022dbb9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e2597f7a1a0541f7a5beb3c85fbae5c0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('696715ec99374ff6ba1410e0d55ed766', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '石家庄科林电气设备有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d3b1039b2eef44719086ac699ebb7a7b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0d9bf3a62fb64426a19bf8e905297d6f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ea29464a59fa443e9c3bedd31fba8155', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '003-开闭箱包11', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c0712113f8754760b826b1790c759302', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1bfe1105ef8741b7a10d19e4b89ebce2', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('30943bd503ba451190d6f77680a6eaa5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '浙江广天电力设备股份有限公司', '13274563111', '105382338@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5bc2db344475405a954d677af7d10a24', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fbd98c4f41614793a43ebbec0a24a6ac', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8a9dd0e585954bb6b5038db609d2d25f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '浙江申大电力设备有限公司', '13903653468', '317112054@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6dbee2b865734d07af5c469e2b2297f8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '004-变压器包11', '黑龙江泓晟电力有限公司', '18745721101', '125017334@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0067c7f7c44b4c18b84259d1b96a9e85', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '美仪电气有限公司', '18410119880', '1741859856@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a59052ac50e7489c984937f49553f6dd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c84b75bb88614650adbb9f6fef1bd44b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4d7876d50da44f55b4f51213f89aa72c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('059482b444be467fb6111af2e0968a62', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('495a62cf4c434f178edc935c4f8ab315', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b79a5db25586428aa6d2fc12b16efa17', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0c1770d9e9264dce9b366a21ac6b58aa', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8446ce43f37444f59dce72d3271044d3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '005-高压柜包11', '许昌许继德理施尔电气有限公司', '18637415930', '1594542294@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1d8e792bcb13491aa8dda8f65769a0c0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '丹东鑫源电力设备有限公司', '0415-4100596', '2623579967@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('fc5e6175041849b79ad67bf5f677c5e3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '江苏博世电气有限公司', '15240202827', '2436731033@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('52d23c17504d44238190d4eab9dfe269', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '扬州京隆科技有限公司', '13905264555', '1205143625@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1e3ade916d5d4f1d8cec633061313d5f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '江苏腾源电气有限公司', '13684603329', 'wangbinTX@126.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('d70501d7650c46388fe254d216cabb43', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '黑龙江中纳输配电成套设备有限公司', '18686893532', 'hljzhn@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('af3225cf00ac463f96909f2408e65012', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '北京聚能达电力技术有限公司', '15711173176', '245272373@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('4870fb9db4394db39e3e1f90c69a9529', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '哈尔滨市鑫盛达电控设备有限公司', '19904669787', 'xinshengdadiankong@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('7d942826fa424bc1a0daeb2054adeeee', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f29ab1a9b1be44e1928af56dd5e7ba65', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '黑龙江中远电气设备制造有限公司', '15004662314', 'zydq1606@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9f78468632a4474684cdfc6b31330616', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '北京华电美仪电气科技有限公司', '15724724149', '1397578611@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6b3c42b0288f4d40828aeacfadb5f470', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '白城电力镇赉变压器有限责任公司', '15834629911', '15834629911@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2ff0a8ebdff64b5592db66051896fd73', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('edec8deb9f8947a38edda8982e28fc1b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '哈尔滨亿汇达电气科技发展股份有限公司', '18245119959', 'yhddq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('9ad4be8317aa4dbfada3fff23553a53b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0c2dac93d96d4558afde7b74caf177dc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '长园电力技术有限公司', '15907565729', '524976203@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('139f7c4e6aa74c068a735ac36e72ac1c', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('6fab76d69d1644ea93a2eff7d79a365e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '哈尔滨鹏健电控设备有限公司', '13945668658', 'PJDKSB@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f3e97a976e5d402fb121091971a4fae3', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '006-低压柜包11', '大连启元电器制造有限公司', '13591806295', '526612786@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2ecec3e7c39b4244a939c514ae590051', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包11', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('2e9f3adbe933431593b9363369975e05', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包11', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('447d368f7e754adaa5e4250ddacf04b5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包11', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('21323039947c4f3599483254f9d05c17', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '007-高压电缆包11', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8b9027b8054a424698371de4f6b7e88b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包11', '黑龙江沃尔德电缆有限公司', '13936440797', '841825336@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f4487234d64a48c98d5b7c9933789299', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包11', '沈阳北阳电缆制造有限责任公司', '024-89376871', 'bydl-11@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('af8515aee59049c9b234c3cb5364b4cf', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包11', '远洋线缆有限公司', '13089993132', '168560877@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('036954e378cb498684a70b936b192445', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包11', '黑龙江哈沈电缆制造有限公司', '15145001111', 'hashenxianlan@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('65847712bcc64709a408125d5641c4b4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '008-低压电缆包11', '黑龙江省联兴通信器材有限责任公司', '18103684949', 'huangkefeng@hljlx.com.cn', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a552fd59f5a047a1b60971b464118189', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '哈尔滨市北胜金属结构制造有限公司', '15776948556', 'bsjsjg@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('607bbdbbbfe04bea81da6ee8af77d29d', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '上海振大电器成套集团有限公司', '13524066204', '280023307@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a4260a75c98e478797d0737475d57eb8', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ed63cae8d79343bb84809b01f7286919', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '哈尔滨锋云电控设备有限公司', '15114665449', '164599846@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('16e248c93d504c65a5f9dd3f7da5e7a4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '哈尔滨互成机电设备有限公司', '15124515469', '470915921@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f839b9fdd25f4b168212aadec4e6dfa0', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '009-电缆桥架包11', '哈尔滨向欣电缆桥架有限公司', '13644562077', '13644562077@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('dba46490a1904f488d1b21253729c554', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '哈尔滨龙能电气有限公司', '13804557717', 'dyhywl@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('8a8bfa2a5c4644f08624d2f9e6d0dde1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '黑龙江华控电气成套设备有限公司', '13945699161', 'hljhkdq@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('ab6b60ca60d6487191b91eff5cace4fb', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '哈尔滨德高供电设备有限公司', '84663595', '491704211@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('0b786235387942b3bccc29d6b1b4d71e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '哈尔滨阿通电站设备有限责任公司', '13936268096', '122696944@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cb1ee535c265495698ef26b5b0372ee7', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '石家庄科林电气股份有限公司', '13931179473', '13931179473@163.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('50f08579051848b1998b306a4cf30d4a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '010-DTU包11', '东方电子股份有限公司', '15331999505', '22067261@qq.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c812c246b982430cbae251287c5f9e83', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司1', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('700630cf1bb34800b843b0435290d725', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司2', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('29089a96cff14de5a55b9923624f1d24', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司3', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('16eb45f08f0e48249befd0cc7c88b89b', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司4', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('f503c4ab81fe44b09e575d001ac49e0f', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司5', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('943c675f161e4ce88b4189ea37d1d2b4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司6', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c31fe2cca0b64d1e998bbda710888a07', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司7', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('e6c97a16a07e4c37b920c38555152bde', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司8', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cf20ce6f503e4dd4b98037071161fd84', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司9', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('471eddc5745949ae934473371dc74dae', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司10', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('a02c65c7894f499bb6a5b33a164d60da', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司11', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('581b54e0872a4cffa4e97a623804194e', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司12', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('528c0e142ac34dab9cd4cd6f3beb82ad', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司13', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('16b1a43eef0b4452b4bd7a50a2d831fc', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司14', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('13d28512f7bf40989c0948b21e603b5a', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司15', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('b37182ad35854ff8962e4a0b594eb9e9', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司16', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('cfd21fdde103415285b9bb9fae47eb71', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司17', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c47b8ba7515540bba241921bea66a0ec', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司18', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('939aed6a2246454e829ab4be15c79246', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司19', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('84ec2dd912844d4ea4dbc4cab2f3c1dd', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司20', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('1682f0d881cb4d79b115c0de60d5aa46', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司21', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('c84f0cfd08c840ed831c0349669a46d1', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司22', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('78e252f35c0d439fabb18f097ab05ee4', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司23', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('3a1556c48b2048f79a7fc8a4f41014d5', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司24', '18345067348', 'liyulong@topscomm.com', '是', '是');
INSERT INTO `tbl_bbgs_tenderer` VALUES ('5b4768879ccb4ec68e30840e393225db', '国网黑龙江省电力有限公司哈尔滨供电公司2018年第一批用户工程物资招标采购项目\n', '001-电能表包1', '青岛鼎信通讯股份有限公司25', '18345067348', 'liyulong@topscomm.com', '是', '是');

-- ----------------------------
-- Table structure for tbl_bbgs_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_user`;
CREATE TABLE `tbl_bbgs_user` (
  `username` varchar(32) DEFAULT NULL,
  `userpassword` varchar(32) DEFAULT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `roletype` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_user
-- ----------------------------
INSERT INTO `tbl_bbgs_user` VALUES ('superadmin', 'admin', '超级管理员', '1');
INSERT INTO `tbl_bbgs_user` VALUES ('admin', 'admin', '管理员', '0');

-- ----------------------------
-- Table structure for tbl_bbgs_weight_percent
-- ----------------------------
DROP TABLE IF EXISTS `tbl_bbgs_weight_percent`;
CREATE TABLE `tbl_bbgs_weight_percent` (
  `ID` varchar(32) DEFAULT NULL,
  `WEIGHT_NAME` varchar(32) DEFAULT NULL COMMENT '权重名称',
  `WEIGHT_CONTENT` varchar(32) DEFAULT NULL COMMENT '权重内容',
  `ISDEFAULT` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tbl_bbgs_weight_percent
-- ----------------------------
INSERT INTO `tbl_bbgs_weight_percent` VALUES ('89771d3422a44114a27a1a7cd291bc85', '技术：40%，商务：10%，价格：50%', '技术：40%，商务：10%，价格：50%', 'N');
INSERT INTO `tbl_bbgs_weight_percent` VALUES ('a89b49678afd4e5e8686dd2d71da35ec', '技术：30%，商务：10%，价格：60%', '技术：30%，商务：10%，价格：60%', 'N');
INSERT INTO `tbl_bbgs_weight_percent` VALUES ('a3b8517b708a4d798c00722fc5e35272', '技术：80%，商务：10%，价格：10%', '技术：80%，商务：10%，价格：10%', 'N');
INSERT INTO `tbl_bbgs_weight_percent` VALUES ('bfff5692144640ffbe8993d4156d7bdf', '技术：60%，商务：10%，价格：30%', '技术：60%，商务：10%，价格：30%', 'N');

-- ----------------------------
-- Function structure for getChildLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER ;;
CREATE DEFINER=`bbgs`@`%` FUNCTION `getChildLst`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
        DECLARE sTemp VARCHAR(1000);
        DECLARE sTempChd VARCHAR(1000);
     
        SET sTemp = '$';
        SET sTempChd =cast(rootId as CHAR);
     
        WHILE sTempChd is not null DO
          SET sTemp = concat(sTemp,',',sTempChd);
          SELECT group_concat(node) INTO sTempChd FROM sql_archives where FIND_IN_SET(parent_node,sTempChd)>0;
        END WHILE;
        RETURN sTemp;
      END
;;
DELIMITER ;
