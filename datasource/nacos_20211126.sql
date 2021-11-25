/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 26/11/2021 00:10:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
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
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (5, 'cloud-stream.yml', 'DEV_GROUP', 'spring:\r\n  cloud:\r\n    stream:\r\n      kafka:\r\n        binder:\r\n          brokers: 127.0.0.1:9092\r\n          zk-nodes: 127.0.0.1:2181\r\n          required-acks: -1\r\n          auto-add-partitions: true\r\n          # 依赖broker的 auto.create.topics.enable\r\n          auto-create-topics: true \r\n          transaction:\r\n            transaction-id-prefix: tx-\r\n        # 死信\r\n        bindings:\r\n          input:\r\n            consumer:\r\n              ## 所有服务都可发送 dlq\r\n              enableDlq: true\r\n              dlqName: ErrorTopic-listener', '53aa0059722614f1a1352ec61799e885', '2021-07-11 03:28:56', '2021-07-11 03:28:56', NULL, '172.21.0.1', '', '239ed102-1f24-403b-9d19-4ae3dcd4b528', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (6, 'datasource-mysql.yml', 'DEV_GROUP', '  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/tester?useSSL=false&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456', '6bacba66a3d39e285a8392cb8d0066ea', '2021-07-11 03:30:19', '2021-07-11 03:30:19', NULL, '172.21.0.1', '', '239ed102-1f24-403b-9d19-4ae3dcd4b528', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (7, 'cloud-stream.yml', 'SHARED_GROUP', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:19092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n      # 死信\n      bindings:\n        input:\n          consumer:\n            ## 所有服务都可发送 dlq\n            enableDlq: true\n            dlqName: ErrorTopic', '1ebf7ad7157372f47697f8467dc5d1bb', '2021-07-11 04:34:12', '2021-11-25 15:11:55', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (8, 'datasource-mysql.yml', 'SHARED_GROUP', 'spring:  \n  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false', '4a60350b7ef67b6f4d73a57315c6cd6e', '2021-07-11 04:35:17', '2021-07-11 04:37:18', 'nacos', '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (12, 'jackson.yml', 'SHARED_GROUP', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true', '4e4fc1af02bad43da69db611e19c56e1', '2021-07-11 04:37:43', '2021-07-11 04:37:43', NULL, '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (13, 'JPA.yml', 'SHARED_GROUP', 'spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n', 'af6260d9e7d1ae88432946d1987b96c0', '2021-07-11 04:38:47', '2021-07-11 04:38:47', NULL, '172.21.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (15, 'cloud-config.yml', 'APP_GROUP', 'server:\n  port: 9001\n\ntest:\n  domain: cloud-config-9001', '04164f8ed167e2628ddb777440e3117d', '2021-07-11 05:15:13', '2021-07-12 06:24:28', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (16, 'cloud-consumer.yml', 'APP_GROUP', 'spring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: consumer\n          group: cloud-consumer\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', '7d07080b35371999efce7812e51bfc0f', '2021-07-11 10:26:38', '2021-11-25 15:09:15', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (17, 'cloud-provider.yml', 'APP_GROUP', 'spring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: provider\n          group: cloud-provider\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', '485b3003e5899f31ee670ef7abfd2721', '2021-07-11 10:29:54', '2021-11-25 15:08:25', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (23, 'shared-service-registry.yml', 'SHARED_GROUP', 'spring:\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        group: APPLICATION_GROUP\r\n        server-addr: ${spring.nacos.server-addr}\r\n        username: ${spring.cloud.seeyon.config.username}\r\n        password: ${spring.cloud.seeyon.config.password}\r\n        namespace: ${spring.cloud.seeyon.config.namespace}\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    NacosDiscovery:\r\n      enabled: false\r\n    elasticsearch:\r\n      enabled: false\r\nribbon:\r\n  ReadTimeout: 60000\r\n  ConnectTimeout: 60000', 'db99efaca6425c64ce708529a7870d3b', '2021-07-12 02:56:45', '2021-07-12 02:56:45', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'shared-redis.yml', 'SHARED_GROUP', 'spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000', '0af62c78dd63d4f39de720434f1d045b', '2021-07-12 02:57:50', '2021-07-12 02:57:50', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (25, 'shared-logback.yml', 'SHARED_GROUP', 'logging:\r\n  custom:\r\n    #自定义参数 产品线\r\n    productline: mall\r\n  file:\r\n    # 当前写入的日志文件名\r\n    name: ./log/${spring.application.name}.log\r\n    # 日志保留最大天数\r\n    max-history: 30\r\n    # 每个日志文件大小限制\r\n    max-size: 10MB\r\n\r\n  pattern:\r\n    file: >-\r\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\r\n    level: INFO\r\n    # 归档文件名\r\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log', 'f0f38d207771a9a80955858e4bbe2dea', '2021-07-12 02:58:52', '2021-07-12 02:58:52', 'nacos', '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (28, 'shared-consul.yml', 'SHARED_GROUP', 'spring:\r\n  cloud:\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\n    consul:\r\n      config:\r\n        enable: true\r\n      discovery:\r\n        health-check-path: ${server.servlet.context-path}/actuator/health\r\n        prefer-ip-address: true\r\n        tags: type=app\r\n\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    elasticsearch:\r\n      enabled: false\r\n', '95d704b58190f9702fdc7c0e7e109fd5', '2021-07-12 06:44:07', '2021-07-12 06:44:07', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (32, 'shared-jwt.yml', 'SHARED_GROUP', 'jwt:\n  header: Authorization\n  # 令牌前缀\n  token-start-with: Bearer\n  # 必须使用最少88位的Base64对该令牌进行编码\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTl777xazU3Ng==\n  token-validity-in-seconds: 432000000\n  # 在线用户key\n  online-key: online-token\n  # 验证码\n  code-key: code-key', 'fa6ca75271e7107867bd386da2f45418', '2021-07-12 07:32:22', '2021-07-12 07:33:06', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'text', '');
INSERT INTO `config_info` VALUES (35, 'shared-redisson.yml', 'SHARED_GROUP', 'spring:\r\n  redis:\r\n    redisson:\r\n      config:\r\n        singleServerConfig:\r\n          idleConnectionTimeout: 10000\r\n          connectTimeout: 10000\r\n          timeout: 3000\r\n          retryAttempts: 3\r\n          retryInterval: 1500\r\n          password: null\r\n          subscriptionsPerConnection: 5\r\n          clientName: null\r\n          address: \"redis://127.0.0.1:6379\"\r\n          subscriptionConnectionMinimumIdleSize: 1\r\n          subscriptionConnectionPoolSize: 50\r\n          connectionMinimumIdleSize: 32\r\n          connectionPoolSize: 64\r\n          database: 0\r\n          dnsMonitoringInterval: 5000\r\n        threads: 0\r\n        nettyThreads: 0\r\n        codec: \"!<org.redisson.codec.JsonJacksonCodec> {}\"\r\n        transportMode: \"NIO\"', '9622f19cf2b28e111f5d8edf42010919', '2021-11-02 15:32:46', '2021-11-02 15:40:24', NULL, '0:0:0:0:0:0:0:1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (36, 'cloud-gateway.yml', 'APP_GROUP', 'spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: false\r\n      routes:\r\n        - id: api-gateway-provider\r\n          #uri: http://localhost:8001  #静态，写死了地址，只能调用一个服务\r\n          uri: lb://cloud-provider  #动态，lb://微服务名\r\n          predicates:\r\n            - Path=/api/cloud-provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          uri: lb://cloud-consumer\r\n          predicates:\r\n            - Path=/api/cloud-consumer/**\r\n          filters:\r\n            - StripPrefix=1', '9570128ae76e1fe8588e7f605041f320', '2021-11-25 13:47:17', '2021-11-25 16:04:46', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (37, 'cloud-dlq.yml', 'APP_GROUP', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false\r\n  cloud:\r\n    stream:\r\n      # 死信\r\n      bindings:\r\n        input:\r\n          destination: ErrorTopic\r\n          group: dlq', 'e537c23cf282a170a6972e6e777ad96a', '2021-11-25 13:47:37', '2021-11-25 15:09:38', NULL, '127.0.0.1', '', 'ced8504b-967b-4e96-8d38-17f4349e4ab5', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
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
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
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
  `id` bigint(20) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (7, 39, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', '48286775101530dab4cca195fd6d5012', '2021-11-02 22:10:09', '2021-11-02 14:10:09', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 40, 'shared-redisson.yml', 'SHARED_GROUP', '', 'spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000', '0af62c78dd63d4f39de720434f1d045b', '2021-11-02 23:32:45', '2021-11-02 15:32:46', NULL, '0:0:0:0:0:0:0:1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (35, 41, 'shared-redisson.yml', 'SHARED_GROUP', '', 'spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000', '0af62c78dd63d4f39de720434f1d045b', '2021-11-02 23:33:04', '2021-11-02 15:33:05', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (35, 42, 'shared-redisson.yml', 'SHARED_GROUP', '', 'singleServerConfig:\r\n  idleConnectionTimeout: 10000\r\n  connectTimeout: 10000\r\n  timeout: 3000\r\n  retryAttempts: 3\r\n  retryInterval: 1500\r\n  password: null\r\n  subscriptionsPerConnection: 5\r\n  clientName: null\r\n  address: \"redis://127.0.0.1:6379\"\r\n  subscriptionConnectionMinimumIdleSize: 1\r\n  subscriptionConnectionPoolSize: 50\r\n  connectionMinimumIdleSize: 32\r\n  connectionPoolSize: 64\r\n  database: 0\r\n  dnsMonitoringInterval: 5000\r\nthreads: 0\r\nnettyThreads: 0\r\ncodec: !<org.redisson.codec.JsonJacksonCodec> {}\r\n\"transportMode\":\"NIO\"', '13fad1b713e4677a498cf76a807abdaa', '2021-11-02 23:36:45', '2021-11-02 15:36:46', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (35, 43, 'shared-redisson.yml', 'SHARED_GROUP', '', 'singleServerConfig:\r\n  idleConnectionTimeout: 10000\r\n  connectTimeout: 10000\r\n  timeout: 3000\r\n  retryAttempts: 3\r\n  retryInterval: 1500\r\n  password: null\r\n  subscriptionsPerConnection: 5\r\n  clientName: null\r\n  address: \"redis://127.0.0.1:6379\"\r\n  subscriptionConnectionMinimumIdleSize: 1\r\n  subscriptionConnectionPoolSize: 50\r\n  connectionMinimumIdleSize: 32\r\n  connectionPoolSize: 64\r\n  database: 0\r\n  dnsMonitoringInterval: 5000\r\nthreads: 0\r\nnettyThreads: 0\r\ncodec: \r\n  !<org.redisson.codec.JsonJacksonCodec> {}\r\n\"transportMode\":\r\n  \"NIO\"', '554fa40875ff27c28a8da0f410f2d99e', '2021-11-02 23:39:10', '2021-11-02 15:39:11', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (35, 44, 'shared-redisson.yml', 'SHARED_GROUP', '', 'spring:\r\n  redis:\r\n    redisson:\r\n      config:\r\n        singleServerConfig:\r\n          idleConnectionTimeout: 10000\r\n          connectTimeout: 10000\r\n          timeout: 3000\r\n          retryAttempts: 3\r\n          retryInterval: 1500\r\n          password: null\r\n          subscriptionsPerConnection: 5\r\n          clientName: null\r\n          address: \"redis://127.0.0.1:6379\"\r\n          subscriptionConnectionMinimumIdleSize: 1\r\n          subscriptionConnectionPoolSize: 50\r\n          connectionMinimumIdleSize: 32\r\n          connectionPoolSize: 64\r\n          database: 0\r\n          dnsMonitoringInterval: 5000\r\n        threads: 0\r\n        nettyThreads: 0\r\n        codec: !<org.redisson.codec.JsonJacksonCodec> {}\r\n        transportMode: \"NIO\"', '254cd2396b716b606c32b7bf54296516', '2021-11-02 23:40:23', '2021-11-02 15:40:24', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (16, 45, 'cloud-consumer.yml', 'APP_GROUP', '', 'server:\n  port: 7001\n\nservice-url:\n  service-provider: http://cloud-provider\n\n\nspring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', 'cd6bc030d8514aa51c94692517f76a12', '2021-11-02 23:55:15', '2021-11-02 15:55:16', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 46, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:19092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n        # 死信\n        bindings:\n          input:\n            consumer:\n              ## 所有服务都可发送 dlq\n              enableDlq: true\n              dlqName: ErrorTopic-listener', 'cce05285d8bcada331e486683a5633fb', '2021-11-03 00:02:19', '2021-11-02 16:02:19', NULL, '0:0:0:0:0:0:0:1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 47, 'cloud-gateway.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      routes:\r\n        - id: api-gateway-provider\r\n          # 动态路由方式需要配合eureka、nacos注册中心使用\r\n          uri: http://localhost:8083\r\n          predicates:\r\n            - Path=/api/provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          # 动态路由方式需要配合eureka、nacos注册中心使用\r\n          uri: http://localhost:8081\r\n          predicates:\r\n            - Path=/consumer/provider/**\r\n          filters:\r\n            - StripPrefix=1', 'c2b118fe11ed161e3f8634efc160bbae', '2021-11-25 21:47:16', '2021-11-25 13:47:17', NULL, '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (0, 48, 'cloud-dlq.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/tester?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false', '294a4ffeb5181924b5028797cb74645b', '2021-11-25 21:47:36', '2021-11-25 13:47:37', NULL, '127.0.0.1', 'I', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (37, 49, 'cloud-dlq.yml', 'APP_GROUP', '', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/tester?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false', '294a4ffeb5181924b5028797cb74645b', '2021-11-25 21:53:39', '2021-11-25 13:53:40', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (36, 50, 'cloud-gateway.yml', 'APP_GROUP', '', 'spring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      routes:\r\n        - id: api-gateway-provider\r\n          # 动态路由方式需要配合eureka、nacos注册中心使用\r\n          uri: http://localhost:8083\r\n          predicates:\r\n            - Path=/api/provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          # 动态路由方式需要配合eureka、nacos注册中心使用\r\n          uri: http://localhost:8081\r\n          predicates:\r\n            - Path=/consumer/provider/**\r\n          filters:\r\n            - StripPrefix=1', 'c2b118fe11ed161e3f8634efc160bbae', '2021-11-25 21:56:19', '2021-11-25 13:56:20', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (36, 51, 'cloud-gateway.yml', 'APP_GROUP', '', 'spring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      # routes:\r\n      #   - id: api-gateway-provider\r\n      #     # 动态路由方式需要配合eureka、nacos注册中心使用\r\n      #     uri: http://localhost:8083\r\n      #     predicates:\r\n      #       - Path=/api/provider/**\r\n      #     filters:\r\n      #       - StripPrefix=1\r\n      #   - id: api-gateway-consumer\r\n      #     # 动态路由方式需要配合eureka、nacos注册中心使用\r\n      #     uri: http://localhost:8081\r\n      #     predicates:\r\n      #       - Path=/consumer/provider/**\r\n      #     filters:\r\n      #       - StripPrefix=1', '93575fc647048489c80f73519ea3d521', '2021-11-25 21:56:30', '2021-11-25 13:56:30', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (37, 52, 'cloud-dlq.yml', 'APP_GROUP', '', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false', 'e5d17b0350bd262cec08fee538ff62a5', '2021-11-25 22:01:04', '2021-11-25 14:01:05', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (37, 53, 'cloud-dlq.yml', 'APP_GROUP', '', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false', 'e5d17b0350bd262cec08fee538ff62a5', '2021-11-25 22:04:41', '2021-11-25 14:04:42', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (16, 54, 'cloud-consumer.yml', 'APP_GROUP', '', 'server:\n  port: 7001\n\nservice-url:\n  service-provider: http://cloud-provider\n\n\nspring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: cloud-consumer\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', '3d6b40ccd58f24b24c39abde64c757d6', '2021-11-25 23:05:17', '2021-11-25 15:05:17', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (17, 55, 'cloud-provider.yml', 'APP_GROUP', '', 'server:\n  port: 8001\nspring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', 'f494b503702b9c9e8a6e5b0af7257551', '2021-11-25 23:05:26', '2021-11-25 15:05:26', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 56, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:19092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n      # 死信\n      bindings:\n        input:\n          consumer:\n            ## 所有服务都可发送 dlq\n            enableDlq: true\n            dlqName: ErrorTopic-listener', '05376b298375533b8ac186ed5f5fd35b', '2021-11-25 23:07:49', '2021-11-25 15:07:49', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (36, 57, 'cloud-gateway.yml', 'APP_GROUP', '', 'spring:\r\n  application:\r\n    name: cloud-gateway\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      # routes:\r\n      #   - id: api-gateway-provider\r\n      #     # 动态路由方式需要配合eureka、nacos注册中心使用\r\n      #     uri: http://localhost:8083\r\n      #     predicates:\r\n      #       - Path=/api/provider/**\r\n      #     filters:\r\n      #       - StripPrefix=1\r\n      #   - id: api-gateway-consumer\r\n      #     # 动态路由方式需要配合eureka、nacos注册中心使用\r\n      #     uri: http://localhost:8081\r\n      #     predicates:\r\n      #       - Path=/consumer/provider/**\r\n      #     filters:\r\n      #       - StripPrefix=1', 'e91f8d8487e1d7c00b916cbfbc1e8d98', '2021-11-25 23:08:10', '2021-11-25 15:08:11', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (17, 58, 'cloud-provider.yml', 'APP_GROUP', '', 'spring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: 7\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', 'a436df655ca98446b28891b3e3665a2f', '2021-11-25 23:08:24', '2021-11-25 15:08:25', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (16, 59, 'cloud-consumer.yml', 'APP_GROUP', '', 'service-url:\n  service-provider: http://cloud-provider\nspring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: channelin2\n          group: cloud-consumer\n          consumer:\n            enableDlq: true\n            dlqName: ErrorTopic-listener\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic', '9186d0145d9bfa815a138a556079be40', '2021-11-25 23:09:14', '2021-11-25 15:09:15', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (37, 60, 'cloud-dlq.yml', 'APP_GROUP', '', 'spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false', 'e5d17b0350bd262cec08fee538ff62a5', '2021-11-25 23:09:38', '2021-11-25 15:09:38', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (7, 61, 'cloud-stream.yml', 'SHARED_GROUP', '', 'spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:9092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n      # 死信\n      bindings:\n        input:\n          consumer:\n            ## 所有服务都可发送 dlq\n            enableDlq: true\n            dlqName: ErrorTopic', 'b202a1d051c4664101ad3c6ff0556d87', '2021-11-25 23:11:55', '2021-11-25 15:11:55', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (36, 62, 'cloud-gateway.yml', 'APP_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      routes:\r\n        - id: api-gateway-provider\r\n          uri: lb://cloud-provider\r\n          predicates:\r\n            - Path=/api/provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          uri: lb://cloud-consumer\r\n          predicates:\r\n            - Path=/api/consumer/**\r\n          filters:\r\n            - StripPrefix=1', '3ed010e20b611528966b68a90602c162', '2021-11-26 00:01:19', '2021-11-25 16:01:19', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');
INSERT INTO `his_config_info` VALUES (36, 63, 'cloud-gateway.yml', 'APP_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: true\r\n      routes:\r\n        - id: api-gateway-provider\r\n          #uri: http://localhost:8001  #静态，写死了地址，只能调用一个服务\r\n          uri: lb://cloud-provider  #动态，lb://微服务名\r\n          predicates:\r\n            - Path=/api/cloud-provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          uri: lb://cloud-consumer\r\n          predicates:\r\n            - Path=/api/cloud-consumer/**\r\n          filters:\r\n            - StripPrefix=1', '8a2e716561858f8f4debf86b3d31d3e7', '2021-11-26 00:04:46', '2021-11-25 16:04:46', NULL, '127.0.0.1', 'U', 'ced8504b-967b-4e96-8d38-17f4349e4ab5');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
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
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('dev', '$2a$10$1k7.WRx7muLjIu7xqhnp.Ocg98QIqXoPb6dTA.otp7JWOoxSJJK1C', 1);
INSERT INTO `users` VALUES ('nacos', '$2a$10$KQSl6ScIZsyS55oz0NiZm.hkNHLsiwwBjOc5b.rpypKFH34o.vVdC', 1);

SET FOREIGN_KEY_CHECKS = 1;
