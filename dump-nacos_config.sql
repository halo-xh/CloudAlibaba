-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: nacos_config
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (5,'cloud-stream.yml','DEV_GROUP','spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener','53aa0059722614f1a1352ec61799e885','2021-07-11 03:28:56','2021-07-11 03:28:56',NULL,'172.21.0.1','','239ed102-1f24-403b-9d19-4ae3dcd4b528',NULL,NULL,NULL,'yaml',NULL),(6,'datasource-mysql.yml','DEV_GROUP','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 03:30:19','2021-07-11 03:30:19',NULL,'172.21.0.1','','239ed102-1f24-403b-9d19-4ae3dcd4b528',NULL,NULL,NULL,'yaml',NULL),(7,'cloud-stream.yml','SHARED_GROUP','spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener','53aa0059722614f1a1352ec61799e885','2021-07-11 04:34:12','2021-07-11 04:34:12',NULL,'172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL),(8,'datasource-mysql.yml','SHARED_GROUP','spring:  \n  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false','4a60350b7ef67b6f4d73a57315c6cd6e','2021-07-11 04:35:17','2021-07-11 04:37:18','nacos','172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5','','','','yaml',''),(12,'jackson.yml','SHARED_GROUP','spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true','4e4fc1af02bad43da69db611e19c56e1','2021-07-11 04:37:43','2021-07-11 04:37:43',NULL,'172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL),(13,'JPA.yml','SHARED_GROUP','spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n','af6260d9e7d1ae88432946d1987b96c0','2021-07-11 04:38:47','2021-07-11 04:38:47',NULL,'172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL),(15,'cloud-config.yml','APP_GROUP','server:\r\n  port: 9001','966d600486bd58d79ecbb04e62efb72c','2021-07-11 05:15:13','2021-07-11 05:15:13',NULL,'172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL),(16,'cloud-consumer.yml','APP_GROUP','server:\n  port: 7001\n\nservice-url:\n  service-provider: http://cloud-provider\n\n\nspring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','cd6bc030d8514aa51c94692517f76a12','2021-07-11 10:26:38','2021-07-11 10:45:21','nacos','172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5','','','','yaml',''),(17,'cloud-provider.yml','APP_GROUP','server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','f494b503702b9c9e8a6e5b0af7257551','2021-07-11 10:29:54','2021-07-11 10:37:51','nacos','172.21.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5','','','','yaml','');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (0,1,'test1','DEFAULT_GROUP','','qweqwe','efe6398127928f1b2e9ef3207fb82663','2021-07-10 21:53:03','2021-07-10 08:53:03',NULL,'172.21.0.1','I',''),(1,2,'test1','DEFAULT_GROUP','','qweqwe','efe6398127928f1b2e9ef3207fb82663','2021-07-11 15:36:00','2021-07-11 02:36:01',NULL,'172.21.0.1','D',''),(0,3,'spring-cloud-stream','DEFAULT_GROUP','','spring:\r\n  application:\r\n    name: cloud-consumer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:9848\r\n    stream:\r\n      bindings:\r\n        input:\r\n          kafka:\r\n            binder:\r\n              zk-nodes: 127.0.0.1:2128\r\n              brokers: 127.0.0.1:9092\r\n            bindings:\r\n              input:\r\n                consumer: test_stream\r\n#                  ## 所有服务都可发送 dlq\r\n                  enableDlq: true\r\n                  dlqName: xtErrorTopic','2813b702d86a284570d2268875dc4260','2021-07-11 16:01:22','2021-07-11 03:01:23',NULL,'172.21.0.1','I','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(2,4,'spring-cloud-stream','DEFAULT_GROUP','','spring:\r\n  application:\r\n    name: cloud-consumer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:9848\r\n    stream:\r\n      bindings:\r\n        input:\r\n          kafka:\r\n            binder:\r\n              zk-nodes: 127.0.0.1:2128\r\n              brokers: 127.0.0.1:9092\r\n            bindings:\r\n              input:\r\n                consumer: test_stream\r\n#                  ## 所有服务都可发送 dlq\r\n                  enableDlq: true\r\n                  dlqName: xtErrorTopic','2813b702d86a284570d2268875dc4260','2021-07-11 16:13:40','2021-07-11 03:13:41','nacos','172.21.0.1','U','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(0,5,'datasource-mysql','SHARED','','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 16:14:27','2021-07-11 03:14:28',NULL,'172.21.0.1','I','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(2,6,'spring-cloud-stream','DEFAULT_GROUP','','spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          zk-nodes: 127.0.0.1:2181\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener','9d5b012b7deb57725666a290fec3dcbe','2021-07-11 16:15:25','2021-07-11 03:15:26',NULL,'172.21.0.1','D','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(0,7,'cloud-stream','DEV_GROUP','','spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener','53aa0059722614f1a1352ec61799e885','2021-07-11 16:28:55','2021-07-11 03:28:56',NULL,'172.21.0.1','I','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(4,8,'datasource-mysql','SHARED','','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 16:30:01','2021-07-11 03:30:01',NULL,'172.21.0.1','D','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(0,9,'datasource-mysql','DEV_GROUP','','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 16:30:18','2021-07-11 03:30:19',NULL,'172.21.0.1','I','239ed102-1f24-403b-9d19-4ae3dcd4b528'),(0,10,'cloud-stream','SHARED_GROUP','','spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener','53aa0059722614f1a1352ec61799e885','2021-07-11 17:34:11','2021-07-11 04:34:12',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,11,'datasource-mysql','SHARED_GROUP','','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 17:35:17','2021-07-11 04:35:17',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(8,12,'datasource-mysql','SHARED_GROUP','','  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456','6bacba66a3d39e285a8392cb8d0066ea','2021-07-11 17:35:41','2021-07-11 04:35:41','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(8,13,'datasource-mysql','SHARED_GROUP','','  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: 123456','9304df305a1f220261bbae7a7d8dc55b','2021-07-11 17:36:38','2021-07-11 04:36:38','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(8,14,'datasource-mysql','SHARED_GROUP','','  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false','6e559809ec70211fdd3a17ed773bb696','2021-07-11 17:37:17','2021-07-11 04:37:18','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,15,'jackson','SHARED_GROUP','','spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true','4e4fc1af02bad43da69db611e19c56e1','2021-07-11 17:37:42','2021-07-11 04:37:43',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,16,'JPA','SHARED_GROUP','','spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n','af6260d9e7d1ae88432946d1987b96c0','2021-07-11 17:38:46','2021-07-11 04:38:47',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,17,'cloud-config','APP_GROUP','','server:\r\n  port: 9001','966d600486bd58d79ecbb04e62efb72c','2021-07-11 17:50:04','2021-07-11 04:50:04',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(14,18,'cloud-config','APP_GROUP','','server:\r\n  port: 9001','966d600486bd58d79ecbb04e62efb72c','2021-07-11 18:14:49','2021-07-11 05:14:49',NULL,'172.21.0.1','D','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,19,'cloud-config.yml','APP_GROUP','','server:\r\n  port: 9001','966d600486bd58d79ecbb04e62efb72c','2021-07-11 18:15:13','2021-07-11 05:15:13',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,20,'cloud-consumer.yml','APP_GROUP','','server:\r\n  port: 7001','a620384f3b04f7381dea686f22eb6dde','2021-07-11 23:26:38','2021-07-11 10:26:38',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(0,21,'cloud-provider','APP_GROUP','','server:\r\n  port: 8001\r\nspring:\r\n  application:\r\n    name: cloud-provider','097ba4c58d76a95ab8d0881562304cef','2021-07-11 23:29:53','2021-07-11 10:29:54',NULL,'172.21.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(16,22,'cloud-consumer.yml','APP_GROUP','','server:\r\n  port: 7001','a620384f3b04f7381dea686f22eb6dde','2021-07-11 23:34:56','2021-07-11 10:34:57','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(17,23,'cloud-provider.yml','APP_GROUP','','server:\r\n  port: 8001\r\nspring:\r\n  application:\r\n    name: cloud-provider','097ba4c58d76a95ab8d0881562304cef','2021-07-11 23:36:33','2021-07-11 10:36:33','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(17,24,'cloud-provider.yml','APP_GROUP','','server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n\nspring:\n  cloud:\n    stream:\n      bindings:\n        output:\n          destination: order-topic','25a220b600d353808bc2893ee6ac829a','2021-07-11 23:36:47','2021-07-11 10:36:48','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(17,25,'cloud-provider.yml','APP_GROUP','','server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        output:\n          destination: order-topic','14740e322de79d7dc0b4629d95284f1b','2021-07-11 23:37:51','2021-07-11 10:37:51','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5'),(16,26,'cloud-consumer.yml','APP_GROUP','','server:\n  port: 7001\nspring:\n  application:\n    name: cloud-consumer\nservice-url:\n  service-provider: http://cloud-provider','6edb8dab75912b87cbe52e902c47a072','2021-07-11 23:45:20','2021-07-11 10:45:21','nacos','172.21.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (5,'1','ced8504b-967b-4e96-8d38-17f4349e4ab5','DEV','dev','nacos',1625995989485,1625995989485),(6,'1','5906bf2f-1303-40f2-acce-2c0a5a679865','TEST','test','nacos',1625995995341,1625995995341);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nacos_config'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-11 23:58:25
