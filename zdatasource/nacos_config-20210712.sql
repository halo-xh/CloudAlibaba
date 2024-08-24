/*
 Navicat Premium Data Transfer

 Source Server         : local-test
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/07/2021 15:42:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (5, 'cloud-stream.yml', 'DEV_GROUP', 'spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener', '53aa0059722614f1a1352ec61799e885', '2021-07-11 03:28:56', '2021-07-11 03:28:56', NULL, '172.21.0.1', '', '239ed102-1f24-403b-9d19-4ae3dcd4b528', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (6, 'datasource-mysql.yml', 'DEV_GROUP', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 03:30:19', '2021-07-11 03:30:19', NULL, '172.21.0.1', '', '239ed102-1f24-403b-9d19-4ae3dcd4b528', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (7, 'cloud-stream.yml', 'SHARED_GROUP', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', '48286775101530dab4cca195fd6d5012', '2021-07-11 04:34:12', '2021-07-12 07:19:46', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (8, 'datasource-mysql.yml', 'SHARED_GROUP', 'spring:  \n  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false', '4a60350b7ef67b6f4d73a57315c6cd6e', '2021-07-11 04:35:17', '2021-07-11 04:37:18', 'nacos', '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (12, 'jackson.yml', 'SHARED_GROUP', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true', '4e4fc1af02bad43da69db611e19c56e1', '2021-07-11 04:37:43', '2021-07-11 04:37:43', NULL, '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (13, 'JPA.yml', 'SHARED_GROUP', 'spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n', 'af6260d9e7d1ae88432946d1987b96c0', '2021-07-11 04:38:47', '2021-07-11 04:38:47', NULL, '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (15, 'cloud-config.yml', 'APP_GROUP', 'server:\n  port: 9001\n\ntest:\n  domain: cloud-config-9001', '04164f8ed167e2628ddb777440e3117d', '2021-07-11 05:15:13', '2021-07-12 06:24:28', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (16, 'cloud-consumer.yml', 'APP_GROUP', 'server:\n  port: 7001\n\nservice-url:\n  service-provider: http://cloud-provider\n\n\nspring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', 'cd6bc030d8514aa51c94692517f76a12', '2021-07-11 10:26:38', '2021-07-11 10:45:21', 'nacos', '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (17, 'cloud-provider.yml', 'APP_GROUP', 'server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', 'f494b503702b9c9e8a6e5b0af7257551', '2021-07-11 10:29:54', '2021-07-11 10:37:51', 'nacos', '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (23, 'shared-service-registry.yml', 'SHARED_GROUP', 'spring:\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        group: APPLICATION_GROUP\r\n        server-addr: ${spring.nacos.server-addr}\r\n        username: ${spring.cloud.seeyon.config.username}\r\n        password: ${spring.cloud.seeyon.config.password}\r\n        namespace: ${spring.cloud.seeyon.config.namespace}\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    NacosDiscovery:\r\n      enabled: false\r\n    elasticsearch:\r\n      enabled: false\r\nribbon:\r\n  ReadTimeout: 60000\r\n  ConnectTimeout: 60000', 'db99efaca6425c64ce708529a7870d3b', '2021-07-12 02:56:45', '2021-07-12 02:56:45', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'shared-redis.yml', 'SHARED_GROUP', 'spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000', '0af62c78dd63d4f39de720434f1d045b', '2021-07-12 02:57:50', '2021-07-12 02:57:50', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (25, 'shared-logback.yml', 'SHARED_GROUP', 'logging:\r\n  custom:\r\n    #自定义参数 产品线\r\n    productline: mall\r\n  file:\r\n    # 当前写入的日志文件名\r\n    name: ./log/${spring.application.name}.log\r\n    # 日志保留最大天数\r\n    max-history: 30\r\n    # 每个日志文件大小限制\r\n    max-size: 10MB\r\n\r\n  pattern:\r\n    file: >-\r\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\r\n    level: INFO\r\n    # 归档文件名\r\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log', 'f0f38d207771a9a80955858e4bbe2dea', '2021-07-12 02:58:52', '2021-07-12 02:58:52', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (28, 'shared-consul.yml', 'SHARED_GROUP', 'spring:\r\n  cloud:\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\n    consul:\r\n      config:\r\n        enable: true\r\n      discovery:\r\n        health-check-path: ${server.servlet.context-path}/actuator/health\r\n        prefer-ip-address: true\r\n        tags: type=app\r\n\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    elasticsearch:\r\n      enabled: false\r\n', '95d704b58190f9702fdc7c0e7e109fd5', '2021-07-12 06:44:07', '2021-07-12 06:44:07', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (32, 'shared-jwt.yml', 'SHARED_GROUP', 'jwt:\n  header: Authorization\n  # 令牌前缀\n  token-start-with: Bearer\n  # 必须使用最少88位的Base64对该令牌进行编码\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTl777xazU3Ng==\n  token-validity-in-seconds: 432000000\n  # 在线用户key\n  online-key: online-token\n  # 验证码\n  code-key: code-key', 'fa6ca75271e7107867bd386da2f45418', '2021-07-12 07:32:22', '2021-07-12 07:33:06', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'text', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'test1', 'DEFAULT_GROUP', '', 'qweqwe', 'efe6398127928f1b2e9ef3207fb82663', '2021-07-10 21:53:03', '2021-07-10 08:53:03', NULL, '172.21.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'test1', 'DEFAULT_GROUP', '', 'qweqwe', 'efe6398127928f1b2e9ef3207fb82663', '2021-07-11 15:36:00', '2021-07-11 02:36:01', NULL, '172.21.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'spring-cloud-stream', 'DEFAULT_GROUP', '', 'spring:\r\n  application:\r\n    name: cloud-consumer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:9848\r\n    stream:\r\n      bindings:\r\n        input:\r\n          kafka:\r\n            binder:\r\n              zk-nodes: 127.0.0.1:2128\r\n              brokers: 127.0.0.1:9092\r\n            bindings:\r\n              input:\r\n                consumer: test_stream\r\n#                  ## 所有服务都可发送 dlq\r\n                  enableDlq: true\r\n                  dlqName: xtErrorTopic', '2813b702d86a284570d2268875dc4260', '2021-07-11 16:01:22', '2021-07-11 03:01:23', NULL, '172.21.0.1', 'I', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (2, 4, 'spring-cloud-stream', 'DEFAULT_GROUP', '', 'spring:\r\n  application:\r\n    name: cloud-consumer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:9848\r\n    stream:\r\n      bindings:\r\n        input:\r\n          kafka:\r\n            binder:\r\n              zk-nodes: 127.0.0.1:2128\r\n              brokers: 127.0.0.1:9092\r\n            bindings:\r\n              input:\r\n                consumer: test_stream\r\n#                  ## 所有服务都可发送 dlq\r\n                  enableDlq: true\r\n                  dlqName: xtErrorTopic', '2813b702d86a284570d2268875dc4260', '2021-07-11 16:13:40', '2021-07-11 03:13:41', 'nacos', '172.21.0.1', 'U', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (0, 5, 'datasource-mysql', 'SHARED', '', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 16:14:27', '2021-07-11 03:14:28', NULL, '172.21.0.1', 'I', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (2, 6, 'spring-cloud-stream', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          zk-nodes: 127.0.0.1:2181\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', '9d5b012b7deb57725666a290fec3dcbe', '2021-07-11 16:15:25', '2021-07-11 03:15:26', NULL, '172.21.0.1', 'D', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (0, 7, 'cloud-stream', 'DEV_GROUP', '', 'spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener', '53aa0059722614f1a1352ec61799e885', '2021-07-11 16:28:55', '2021-07-11 03:28:56', NULL, '172.21.0.1', 'I', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (4, 8, 'datasource-mysql', 'SHARED', '', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 16:30:01', '2021-07-11 03:30:01', NULL, '172.21.0.1', 'D', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (0, 9, 'datasource-mysql', 'DEV_GROUP', '', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 16:30:18', '2021-07-11 03:30:19', NULL, '172.21.0.1', 'I', '239ed102-1f24-403b-9d19-4ae3dcd4b528');
INSERT INTO `his_config_info` VALUES (0, 10, 'cloud-stream', 'SHARED_GROUP', '', 'spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener', '53aa0059722614f1a1352ec61799e885', '2021-07-11 17:34:11', '2021-07-11 04:34:12', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 11, 'datasource-mysql', 'SHARED_GROUP', '', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 17:35:17', '2021-07-11 04:35:17', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (8, 12, 'datasource-mysql', 'SHARED_GROUP', '', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 17:35:41', '2021-07-11 04:35:41', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (8, 13, 'datasource-mysql', 'SHARED_GROUP', '', '  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: 123456', '9304df305a1f220261bbae7a7d8dc55b', '2021-07-11 17:36:38', '2021-07-11 04:36:38', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (8, 14, 'datasource-mysql', 'SHARED_GROUP', '', '  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false', '6e559809ec70211fdd3a17ed773bb696', '2021-07-11 17:37:17', '2021-07-11 04:37:18', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 15, 'jackson', 'SHARED_GROUP', '', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true', '4e4fc1af02bad43da69db611e19c56e1', '2021-07-11 17:37:42', '2021-07-11 04:37:43', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 16, 'JPA', 'SHARED_GROUP', '', 'spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n', 'af6260d9e7d1ae88432946d1987b96c0', '2021-07-11 17:38:46', '2021-07-11 04:38:47', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 17, 'cloud-config', 'APP_GROUP', '', 'server:\r\n  port: 9001', '966d600486bd58d79ecbb04e62efb72c', '2021-07-11 17:50:04', '2021-07-11 04:50:04', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (14, 18, 'cloud-config', 'APP_GROUP', '', 'server:\r\n  port: 9001', '966d600486bd58d79ecbb04e62efb72c', '2021-07-11 18:14:49', '2021-07-11 05:14:49', NULL, '172.21.0.1', 'D', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 19, 'cloud-config.yml', 'APP_GROUP', '', 'server:\r\n  port: 9001', '966d600486bd58d79ecbb04e62efb72c', '2021-07-11 18:15:13', '2021-07-11 05:15:13', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 20, 'cloud-consumer.yml', 'APP_GROUP', '', 'server:\r\n  port: 7001', 'a620384f3b04f7381dea686f22eb6dde', '2021-07-11 23:26:38', '2021-07-11 10:26:38', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 21, 'cloud-provider', 'APP_GROUP', '', 'server:\r\n  port: 8001\r\nspring:\r\n  application:\r\n    name: cloud-provider', '097ba4c58d76a95ab8d0881562304cef', '2021-07-11 23:29:53', '2021-07-11 10:29:54', NULL, '172.21.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (16, 22, 'cloud-consumer.yml', 'APP_GROUP', '', 'server:\r\n  port: 7001', 'a620384f3b04f7381dea686f22eb6dde', '2021-07-11 23:34:56', '2021-07-11 10:34:57', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (17, 23, 'cloud-provider.yml', 'APP_GROUP', '', 'server:\r\n  port: 8001\r\nspring:\r\n  application:\r\n    name: cloud-provider', '097ba4c58d76a95ab8d0881562304cef', '2021-07-11 23:36:33', '2021-07-11 10:36:33', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (17, 24, 'cloud-provider.yml', 'APP_GROUP', '', 'server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n\nspring:\n  cloud:\n    stream:\n      bindings:\n        output:\n          destination: order-topic', '25a220b600d353808bc2893ee6ac829a', '2021-07-11 23:36:47', '2021-07-11 10:36:48', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (17, 25, 'cloud-provider.yml', 'APP_GROUP', '', 'server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        output:\n          destination: order-topic', '14740e322de79d7dc0b4629d95284f1b', '2021-07-11 23:37:51', '2021-07-11 10:37:51', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (16, 26, 'cloud-consumer.yml', 'APP_GROUP', '', 'server:\n  port: 7001\nspring:\n  application:\n    name: cloud-consumer\nservice-url:\n  service-provider: http://cloud-provider', '6edb8dab75912b87cbe52e902c47a072', '2021-07-11 23:45:20', '2021-07-11 10:45:21', 'nacos', '172.21.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 27, 'shared-service-registry.yml', 'SHARED_GROUP', '', 'spring:\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        group: APPLICATION_GROUP\r\n        server-addr: ${spring.nacos.server-addr}\r\n        username: ${spring.cloud.seeyon.config.username}\r\n        password: ${spring.cloud.seeyon.config.password}\r\n        namespace: ${spring.cloud.seeyon.config.namespace}\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    NacosDiscovery:\r\n      enabled: false\r\n    elasticsearch:\r\n      enabled: false\r\nribbon:\r\n  ReadTimeout: 60000\r\n  ConnectTimeout: 60000', 'db99efaca6425c64ce708529a7870d3b', '2021-07-12 10:56:45', '2021-07-12 02:56:45', 'nacos', '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 28, 'shared-redis.yml', 'SHARED_GROUP', '', 'spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000', '0af62c78dd63d4f39de720434f1d045b', '2021-07-12 10:57:50', '2021-07-12 02:57:50', 'nacos', '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 29, 'shared-logback.yml', 'SHARED_GROUP', '', 'logging:\r\n  custom:\r\n    #自定义参数 产品线\r\n    productline: mall\r\n  file:\r\n    # 当前写入的日志文件名\r\n    name: ./log/${spring.application.name}.log\r\n    # 日志保留最大天数\r\n    max-history: 30\r\n    # 每个日志文件大小限制\r\n    max-size: 10MB\r\n\r\n  pattern:\r\n    file: >-\r\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\r\n    level: INFO\r\n    # 归档文件名\r\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log', 'f0f38d207771a9a80955858e4bbe2dea', '2021-07-12 10:58:52', '2021-07-12 02:58:52', 'nacos', '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 30, 'nacos-config.yml', 'SHARED_GROUP', '', 'spring:\r\n  application:\r\n    name: cloud-provider\r\n  profile: dev\r\n  cloud:\r\n    nacos:\r\n      config:\r\n        file-extension: yml\r\n        # set default group\r\n        group: APP_GROUP\r\n        # APP config , use upper default group\r\n        prefix: ${spring.application.name}\r\n        # common config\r\n        shared-configs:\r\n          - group: SHARED_GROUP\r\n            data-id: cloud-stream.yml\r\n          - group: SHARED_GROUP\r\n            data-id: datasource-mysql.yml\r\n          - group: SHARED_GROUP\r\n            data-id: jackson.yml\r\n        # 不会自动刷新\r\n        extension-configs[0]:\r\n          group: SHARED_GROUP\r\n          data-id: JPA.yml\r\n        # 会自动刷新\r\n        # number greater -> higher priority\r\n        extension-configs[1]:\r\n          group: SHARED_GROUP\r\n          data-id: jackson.yml\r\n          refresh: true', '49cb98e7d1ebc792a302dd23376e7641', '2021-07-12 11:03:20', '2021-07-12 03:03:21', 'nacos', '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (15, 31, 'cloud-config.yml', 'APP_GROUP', '', 'server:\r\n  port: 9001', '966d600486bd58d79ecbb04e62efb72c', '2021-07-12 14:24:27', '2021-07-12 06:24:28', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (26, 32, 'nacos-config.yml', 'SHARED_GROUP', '', 'spring:\r\n  application:\r\n    name: cloud-provider\r\n  profile: dev\r\n  cloud:\r\n    nacos:\r\n      config:\r\n        file-extension: yml\r\n        # set default group\r\n        group: APP_GROUP\r\n        # APP config , use upper default group\r\n        prefix: ${spring.application.name}\r\n        # common config\r\n        shared-configs:\r\n          - group: SHARED_GROUP\r\n            data-id: cloud-stream.yml\r\n          - group: SHARED_GROUP\r\n            data-id: datasource-mysql.yml\r\n          - group: SHARED_GROUP\r\n            data-id: jackson.yml\r\n        # 不会自动刷新\r\n        extension-configs[0]:\r\n          group: SHARED_GROUP\r\n          data-id: JPA.yml\r\n        # 会自动刷新\r\n        # number greater -> higher priority\r\n        extension-configs[1]:\r\n          group: SHARED_GROUP\r\n          data-id: jackson.yml\r\n          refresh: true', '49cb98e7d1ebc792a302dd23376e7641', '2021-07-12 14:32:12', '2021-07-12 06:32:12', NULL, '127.0.0.1', 'D', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 33, 'shared-consul.yml', 'SHARED_GROUP', '', 'spring:\r\n  cloud:\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\n    consul:\r\n      config:\r\n        enable: true\r\n      discovery:\r\n        health-check-path: ${server.servlet.context-path}/actuator/health\r\n        prefer-ip-address: true\r\n        tags: type=app\r\n\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    elasticsearch:\r\n      enabled: false\r\n', '95d704b58190f9702fdc7c0e7e109fd5', '2021-07-12 14:44:07', '2021-07-12 06:44:07', NULL, '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 34, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener', '53aa0059722614f1a1352ec61799e885', '2021-07-12 14:48:24', '2021-07-12 06:48:24', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 35, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          zk-nodes: 127.0.0.1:2181\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          retries: 3\n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', 'e2a59150609ef3ab80c0410a1025ebc8', '2021-07-12 14:55:43', '2021-07-12 06:55:43', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 36, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', '1111d4a201b45d958ad39e16b824e854', '2021-07-12 15:19:46', '2021-07-12 07:19:46', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 37, 'shared-jwt.yml', 'SHARED_GROUP', '', 'jwt:\r\n  header: Authorization\r\n  # 令牌前缀\r\n  token-start-with: Bearer\r\n  # 必须使用最少88位的Base64对该令牌进行编码\r\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTlnNDUxazU3Ng==\r\n  token-validity-in-seconds: 432000000\r\n  # 在线用户key\r\n  online-key: online-token\r\n  # 验证码\r\n  code-key: code-key', 'd37055e6d55f15f47bfda317ab528ab6', '2021-07-12 15:32:21', '2021-07-12 07:32:22', NULL, '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (32, 38, 'shared-jwt.yml', 'SHARED_GROUP', '', 'jwt:\r\n  header: Authorization\r\n  # 令牌前缀\r\n  token-start-with: Bearer\r\n  # 必须使用最少88位的Base64对该令牌进行编码\r\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTlnNDUxazU3Ng==\r\n  token-validity-in-seconds: 432000000\r\n  # 在线用户key\r\n  online-key: online-token\r\n  # 验证码\r\n  code-key: code-key', 'd37055e6d55f15f47bfda317ab528ab6', '2021-07-12 15:33:05', '2021-07-12 07:33:06', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('dev', 'ced8504b-967b-4e96-8d38-17f4349e4ab5:*:*', 'rw');
INSERT INTO `permissions` VALUES ('ROLE_ADMIN', 'ced8504b-967b-4e96-8d38-17f4349e4ab5:*:*', 'rw');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('dev', 'dev');
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (5, '1', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', 'DEV', 'dev', 'nacos', 1625995989485, 1625995989485);
INSERT INTO `tenant_info` VALUES (6, '1', '5906bf2f-1303-40f2-acce-2c0a5a679865', 'TEST', 'test', 'nacos', 1625995995341, 1625995995341);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('dev', '$2a$10$1k7.WRx7muLjIu7xqhnp.Ocg98QIqXoPb6dTA.otp7JWOoxSJJK1C', 1);
INSERT INTO `users` VALUES ('nacos', '$2a$10$KQSl6ScIZsyS55oz0NiZm.hkNHLsiwwBjOc5b.rpypKFH34o.vVdC', 1);

SET FOREIGN_KEY_CHECKS = 1;
