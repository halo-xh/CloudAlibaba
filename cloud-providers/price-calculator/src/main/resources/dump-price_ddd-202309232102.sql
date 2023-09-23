-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: price_ddd
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute_enum_value`
--

DROP TABLE IF EXISTS `attribute_enum_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_enum_value` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `enum_name` varchar(200) DEFAULT NULL COMMENT '枚举值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='枚举属性值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_attribute_enum`
--

DROP TABLE IF EXISTS `price_attribute_enum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_attribute_enum` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) DEFAULT NULL COMMENT '计价属性名',
  `type` varchar(200) DEFAULT NULL COMMENT '计价属性类型',
  `parent_id` bigint DEFAULT NULL COMMENT '引用id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='枚举计价属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_attribute_quantity`
--

DROP TABLE IF EXISTS `price_attribute_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_attribute_quantity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) DEFAULT NULL COMMENT '计价属性名',
  `type` varchar(200) DEFAULT NULL COMMENT '计价属性类型',
  `unit` varchar(200) DEFAULT NULL COMMENT '计价属性单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数量计价属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_attribute_quantity_detail`
--

DROP TABLE IF EXISTS `price_attribute_quantity_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_attribute_quantity_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attribute_id` bigint NOT NULL COMMENT '数量计价区间id',
  `min` int NOT NULL COMMENT '最小',
  `max` int NOT NULL COMMENT '最大',
  `step` int NOT NULL COMMENT '步长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数量计价区间详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_plan_sku`
--

DROP TABLE IF EXISTS `price_plan_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_plan_sku` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `spu_id` bigint NOT NULL COMMENT '产品计价SPUid',
  `quantity_detail_id` bigint NOT NULL COMMENT '数量计价详情id',
  `price_plan_strategy` varchar(200) NOT NULL COMMENT '产品计价结算策略',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品计价详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_plan_spu_buyout`
--

DROP TABLE IF EXISTS `price_plan_spu_buyout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_plan_spu_buyout` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) NOT NULL COMMENT '产品计价名称',
  `strategy_enum` varchar(200) NOT NULL COMMENT '产品计价策略',
  `direct_sell_discount_limit` float NOT NULL COMMENT '直销折扣限制',
  `distribute_sell_discount_limit` float NOT NULL COMMENT '分直销折扣限制',
  `base_price` double NOT NULL COMMENT '买断底价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品计价计划-买断';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_plan_spu_common`
--

DROP TABLE IF EXISTS `price_plan_spu_common`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_plan_spu_common` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) NOT NULL COMMENT '产品计价名称',
  `strategy_enum` bigint NOT NULL COMMENT '产品计价策略',
  `direct_sell_discount_limit` float NOT NULL COMMENT '直销折扣限制',
  `distribute_sell_discount_limit` float NOT NULL COMMENT '分直销折扣限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品计价计划-基础';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_plan_spu_subscription`
--

DROP TABLE IF EXISTS `price_plan_spu_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_plan_spu_subscription` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) NOT NULL COMMENT '产品计价名称',
  `strategy_enum` bigint NOT NULL COMMENT '产品计价策略',
  `direct_sell_discount_limit` int NOT NULL COMMENT '直销折扣限制',
  `distribute_sell_discount_limit` int NOT NULL COMMENT '分直销折扣限制',
  `max_limit` int NOT NULL COMMENT '订阅数最大限制',
  `price_per` int NOT NULL COMMENT '订阅单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品计价计划-订阅';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL COMMENT '产品名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_line`
--

DROP TABLE IF EXISTS `product_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_line` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) NOT NULL COMMENT '产品线名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_version`
--

DROP TABLE IF EXISTS `product_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_version` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `name` varchar(200) NOT NULL COMMENT '产品版本名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品版本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quantity_interval`
--

DROP TABLE IF EXISTS `quantity_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quantity_interval` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `quantity_detail_id` bigint NOT NULL COMMENT '区间详情id',
  `start` int NOT NULL COMMENT '起始',
  `end` int DEFAULT NULL COMMENT '结束',
  `price_stagy` varchar(200) DEFAULT NULL COMMENT '区间价格策略',
  `price` double DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数量计价区间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'price_ddd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-23 21:02:01
