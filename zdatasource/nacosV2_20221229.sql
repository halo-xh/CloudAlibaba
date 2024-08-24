-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: nacos2
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
                               `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'demo-svca','seeyon','seeyon:\n  dubbo:\n    packages-to-scan: com.seeyon\n    enable: true\n    enable-nacos: true\n    provider:\n      enable: true\n      register: true\n      core-threads: 50\n      port: 20880\n    consumer:\n      retrys: 3\n    consumer-log: true\n    register: true\n  mq:\n    enable: true\n    engine: kafka\n    servers: 127.0.0.1:19092\n    producer:\n      group: demo-svcb\n      enable: true\n    consumer:\n      enable: true\n      group: demo-svcb','0d467cc54e446a10c24d2e06b18284b6','2022-11-05 12:10:16','2022-11-05 14:07:29','nacos','127.0.0.1','','seeyon-dev','','','','yaml','',''),(2,'public','seeyon','seeyon:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/v8_local\n    username: v8demo\n    password: 12345678\n  cache:\n    redis-nodes: 127.0.0.1:6379\n    redis-database: 0\n  file:\n    storage-type: minio\n    max-size: 102400\n    part-size: 1024\n    black-list: exe,jsp\n    upload-sign-expire-time: 10000\n    download-sign-expire-time: 10000\n    max-resize-image-size: 10\n    qr-upload-enable: true\n    minio:\n      access-key-id: minioadmin\n      access-key-secret: minioadmin\n      api-endpoint: 127.0.0.1:9000\n      api-endpoint-protocol: http\n      access-endpoint: 127.0.0.1:9000\n      access-endpoint-protocol: http\n      private-bucket-name: seeyon-private-local\n      public-bucket-name: seeyon-public-local','f8aa04e997ed0a74582a28ca75edc018','2022-11-05 12:10:28','2022-11-12 13:23:48','nacos','127.0.0.1','','seeyon-dev','','','','yaml','',''),(3,'demo-svcb','seeyon','seeyon:\n  config:\n    enable: true\n    server-addr: 127.0.0.1:8848\n    username: nacos\n    password: nacos\n  dubbo:\n    packages-to-scan: com.seeyon\n    enable: true\n    enable-nacos: true\n    provider:\n      enable: true\n      register: true\n      core-threads: 50\n      port: 20881\n    consumer:\n      retrys: 3\n    consumer-log: true\n    register: true\n  mq:\n    enable: true\n    engine: kafka\n    servers: 127.0.0.1:19092\n    producer:\n      group: demo-svcb\n      enable: true\n    consumer:\n      enable: true\n      group: demo-svcb','0f38d7d10554caa68355d3540a900865','2022-11-05 12:10:50','2022-11-05 14:07:50','nacos','127.0.0.1','','seeyon-dev','','','','yaml','',''),(4,'cloud-stream.yml','SHARED_GROUP','spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:19092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n      # 死信\n      bindings:\n        input:\n          consumer:\n            ## 所有服务都可发送 dlq\n            enableDlq: true\n            dlqName: ErrorTopic','1ebf7ad7157372f47697f8467dc5d1bb','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(5,'datasource-mysql.yml','SHARED_GROUP','spring:  \n  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false','4a60350b7ef67b6f4d73a57315c6cd6e','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(6,'jackson.yml','SHARED_GROUP','spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true','4e4fc1af02bad43da69db611e19c56e1','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(7,'JPA.yml','SHARED_GROUP','spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n','af6260d9e7d1ae88432946d1987b96c0','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(8,'cloud-config.yml','APP_GROUP','server:\n  port: 9001\n\ntest:\n  domain: cloud-config-9001','04164f8ed167e2628ddb777440e3117d','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(9,'cloud-consumer.yml','APP_GROUP','spring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: consumer\n          group: cloud-consumer\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','7d07080b35371999efce7812e51bfc0f','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(10,'cloud-provider.yml','APP_GROUP','spring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: provider\n          group: cloud-provider\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','485b3003e5899f31ee670ef7abfd2721','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(11,'shared-service-registry.yml','SHARED_GROUP','spring:\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        group: APPLICATION_GROUP\r\n        server-addr: ${spring.nacos.server-addr}\r\n        username: ${spring.cloud.seeyon.config.username}\r\n        password: ${spring.cloud.seeyon.config.password}\r\n        namespace: ${spring.cloud.seeyon.config.namespace}\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    NacosDiscovery:\r\n      enabled: false\r\n    elasticsearch:\r\n      enabled: false\r\nribbon:\r\n  ReadTimeout: 60000\r\n  ConnectTimeout: 60000','db99efaca6425c64ce708529a7870d3b','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(12,'shared-redis.yml','SHARED_GROUP','spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000','0af62c78dd63d4f39de720434f1d045b','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(13,'shared-logback.yml','SHARED_GROUP','logging:\n  custom:\n    #自定义参数 产品线\n    productline: mall\n  file:\n    # 当前写入的日志文件名\n    name: ./log/${spring.application.name}.log\n    # 日志保留最大天数\n    max-history: 30\n    # 每个日志文件大小限制\n    max-size: 10MB\n\n  pattern:\n    file: >-\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"traceId\":\"%tid\" ,\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\n    level: DEBUG\n    # 归档文件名\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','65dc06af777f41a8cbd5a5910ce4fbe1','2022-12-29 13:39:48','2022-12-29 15:09:09','nacos','127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5','','','','yaml','',''),(14,'shared-consul.yml','SHARED_GROUP','spring:\r\n  cloud:\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\n    consul:\r\n      config:\r\n        enable: true\r\n      discovery:\r\n        health-check-path: ${server.servlet.context-path}/actuator/health\r\n        prefer-ip-address: true\r\n        tags: type=app\r\n\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    elasticsearch:\r\n      enabled: false\r\n','95d704b58190f9702fdc7c0e7e109fd5','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(15,'shared-jwt.yml','SHARED_GROUP','jwt:\n  header: Authorization\n  # 令牌前缀\n  token-start-with: Bearer\n  # 必须使用最少88位的Base64对该令牌进行编码\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTl777xazU3Ng==\n  token-validity-in-seconds: 432000000\n  # 在线用户key\n  online-key: online-token\n  # 验证码\n  code-key: code-key','fa6ca75271e7107867bd386da2f45418','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(16,'shared-redisson.yml','SHARED_GROUP','spring:\r\n  redis:\r\n    redisson:\r\n      config:\r\n        singleServerConfig:\r\n          idleConnectionTimeout: 10000\r\n          connectTimeout: 10000\r\n          timeout: 3000\r\n          retryAttempts: 3\r\n          retryInterval: 1500\r\n          password: null\r\n          subscriptionsPerConnection: 5\r\n          clientName: null\r\n          address: \"redis://127.0.0.1:6379\"\r\n          subscriptionConnectionMinimumIdleSize: 1\r\n          subscriptionConnectionPoolSize: 50\r\n          connectionMinimumIdleSize: 32\r\n          connectionPoolSize: 64\r\n          database: 0\r\n          dnsMonitoringInterval: 5000\r\n        threads: 0\r\n        nettyThreads: 0\r\n        codec: \"!<org.redisson.codec.JsonJacksonCodec> {}\"\r\n        transportMode: \"NIO\"','9622f19cf2b28e111f5d8edf42010919','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(17,'cloud-gateway.yml','APP_GROUP','spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: false\r\n      routes:\r\n        - id: api-gateway-provider\r\n          #uri: http://localhost:8001  #静态，写死了地址，只能调用一个服务\r\n          uri: lb://cloud-provider  #动态，lb://微服务名\r\n          predicates:\r\n            - Path=/api/cloud-provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          uri: lb://cloud-consumer\r\n          predicates:\r\n            - Path=/api/cloud-consumer/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-simple\r\n          uri: http://127.0.0.1:7777\r\n          predicates:\r\n            - Path=/api/cloud-simple/**\r\n          filters:\r\n            - StripPrefix=1','01639fbaae2673c3b502f712a272f332','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,''),(18,'cloud-dlq.yml','APP_GROUP','spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false\r\n  cloud:\r\n    stream:\r\n      # 死信\r\n      bindings:\r\n        input:\r\n          destination: ErrorTopic\r\n          group: dlq','e537c23cf282a170a6972e6e777ad96a','2022-12-29 13:39:48','2022-12-29 13:39:48',NULL,'127.0.0.1','','ced8504b-967b-4e96-8d38-17f4349e4ab5',NULL,NULL,NULL,'yaml',NULL,'');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

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
                                    `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
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

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
                                   `id` bigint(20) unsigned NOT NULL,
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
                                   `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
                                   PRIMARY KEY (`nid`),
                                   KEY `idx_gmt_create` (`gmt_create`),
                                   KEY `idx_gmt_modified` (`gmt_modified`),
                                   KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (0,13,'cloud-stream.yml','SHARED_GROUP','','spring:\n  cloud:\n    stream:\n      kafka:\n        binder:\n          brokers: 127.0.0.1:19092\n          required-acks: -1\n          auto-add-partitions: true\n          # 依赖broker的 auto.create.topics.enable\n          auto-create-topics: true \n          producer-properties:\n            # 开启幂等\n            enable.idempotence: true\n            # 提交消息失败重试次数\n            retries: 7\n          consumer-properties:\n            # 消费端事务隔离级别.\n            isolation.level: read_committed\n          transaction:\n            transaction-id-prefix: tx-\n      # 死信\n      bindings:\n        input:\n          consumer:\n            ## 所有服务都可发送 dlq\n            enableDlq: true\n            dlqName: ErrorTopic','1ebf7ad7157372f47697f8467dc5d1bb','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,14,'datasource-mysql.yml','SHARED_GROUP','','spring:  \n  datasource:\n    name: testerDS\n    type: com.zaxxer.hikari.HikariDataSource\n    username: root\n    password: 123456\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    hikari:\n      poolName: Hikari\n      auto-commit: false','4a60350b7ef67b6f4d73a57315c6cd6e','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,15,'jackson.yml','SHARED_GROUP','','spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n    serialization:\r\n      indent-output: true','4e4fc1af02bad43da69db611e19c56e1','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,16,'JPA.yml','SHARED_GROUP','','spring:\r\n  jpa:\r\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\r\n    database: MYSQL\r\n    show-sql: true\r\n    properties:\r\n      hibernate.hbm2ddl.auto: none\r\n      hibernate.query.substitutions: true \'T\', false \'F\'\r\n      hibernate.jdbc.batch_size: 30\r\n      hibernate.format_sql: true\r\n      org.hibernate.envers.auditTableSuffix: _AUD\r\n      org.hibernate.envers.revisionTypeFieldName: REV_TYPE\r\n      org.hibernate.envers.doNotAuditOptimisticLockingField: false\r\n#      hibernate.ejb.interceptor:\r\n      hibernate.id.new_generator_mappings: true\r\n      hibernate.connection.provider_disables_autocommit: true\r\n#      hibernate.cache.use_second_level_cache: true\r\n#      hibernate.cache.use_query_cache: false\r\n#      hibernate.generate_statistics: false\r\n#      hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory\r\n#      hibernate.cache.hazelcast.instance_name: Ehazelcast\r\n#      hibernate.cache.use_minimal_puts: true\r\n#      hibernate.cache.hazelcast.use_lite_member: true\r\n    hibernate:\r\n      naming:\r\n        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy\r\n','af6260d9e7d1ae88432946d1987b96c0','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,17,'cloud-config.yml','APP_GROUP','','server:\n  port: 9001\n\ntest:\n  domain: cloud-config-9001','04164f8ed167e2628ddb777440e3117d','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,18,'cloud-consumer.yml','APP_GROUP','','spring:\n  application:\n    name: cloud-consumer\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: consumer\n          group: cloud-consumer\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','7d07080b35371999efce7812e51bfc0f','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,19,'cloud-provider.yml','APP_GROUP','','spring:\n  application:\n    name: cloud-provider\n  cloud:\n    stream:\n      bindings:\n        input:\n          destination: provider\n          group: cloud-provider\n          consumer:\n            autoCommitOffset: true\n            concurrency: 7\n        output:\n          destination: order-topic','485b3003e5899f31ee670ef7abfd2721','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,20,'shared-service-registry.yml','SHARED_GROUP','','spring:\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        group: APPLICATION_GROUP\r\n        server-addr: ${spring.nacos.server-addr}\r\n        username: ${spring.cloud.seeyon.config.username}\r\n        password: ${spring.cloud.seeyon.config.password}\r\n        namespace: ${spring.cloud.seeyon.config.namespace}\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    NacosDiscovery:\r\n      enabled: false\r\n    elasticsearch:\r\n      enabled: false\r\nribbon:\r\n  ReadTimeout: 60000\r\n  ConnectTimeout: 60000','db99efaca6425c64ce708529a7870d3b','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,21,'shared-redis.yml','SHARED_GROUP','','spring:\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password: 123456\r\n    #连接超时时间\r\n    timeout: 5000','0af62c78dd63d4f39de720434f1d045b','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,22,'shared-logback.yml','SHARED_GROUP','','logging:\r\n  custom:\r\n    #自定义参数 产品线\r\n    productline: mall\r\n  file:\r\n    # 当前写入的日志文件名\r\n    name: ./log/${spring.application.name}.log\r\n    # 日志保留最大天数\r\n    max-history: 30\r\n    # 每个日志文件大小限制\r\n    max-size: 10MB\r\n\r\n  pattern:\r\n    file: >-\r\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\r\n    level: INFO\r\n    # 归档文件名\r\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','f0f38d207771a9a80955858e4bbe2dea','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,23,'shared-consul.yml','SHARED_GROUP','','spring:\r\n  cloud:\r\n    service-registry:\r\n      auto-multi-registration.enabled: true\r\n      auto-registration:\r\n        enabled: true\r\n        fail-fast: true\r\n    consul:\r\n      config:\r\n        enable: true\r\n      discovery:\r\n        health-check-path: ${server.servlet.context-path}/actuator/health\r\n        prefer-ip-address: true\r\n        tags: type=app\r\n\r\nmanagement:\r\n  endpoints:\r\n    health.show-details: always\r\n    web:\r\n      base-path: /actuator\r\n      exposure.include: health,info,env,prometheus,metrics,httptrace,threaddump,heapdump,springmetrics\r\n  metrics:\r\n    export.prometheus:\r\n      descriptions: true\r\n      enabled: true\r\n      step: 1m\r\n  server:\r\n    servlet:\r\n      context-path: /\r\n  web.server.auto-time-requests: true\r\n  health:\r\n    elasticsearch:\r\n      enabled: false\r\n','95d704b58190f9702fdc7c0e7e109fd5','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,24,'shared-jwt.yml','SHARED_GROUP','','jwt:\n  header: Authorization\n  # 令牌前缀\n  token-start-with: Bearer\n  # 必须使用最少88位的Base64对该令牌进行编码\n  base64-secret: eWVOM2c5RXNOZmlhWWZvZFY2M2RJMWo4RmJrc3NrNUhhTDdXNHlhVzR5ajRNZjQ1bWZnMnY4OTl777xazU3Ng==\n  token-validity-in-seconds: 432000000\n  # 在线用户key\n  online-key: online-token\n  # 验证码\n  code-key: code-key','fa6ca75271e7107867bd386da2f45418','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,25,'shared-redisson.yml','SHARED_GROUP','','spring:\r\n  redis:\r\n    redisson:\r\n      config:\r\n        singleServerConfig:\r\n          idleConnectionTimeout: 10000\r\n          connectTimeout: 10000\r\n          timeout: 3000\r\n          retryAttempts: 3\r\n          retryInterval: 1500\r\n          password: null\r\n          subscriptionsPerConnection: 5\r\n          clientName: null\r\n          address: \"redis://127.0.0.1:6379\"\r\n          subscriptionConnectionMinimumIdleSize: 1\r\n          subscriptionConnectionPoolSize: 50\r\n          connectionMinimumIdleSize: 32\r\n          connectionPoolSize: 64\r\n          database: 0\r\n          dnsMonitoringInterval: 5000\r\n        threads: 0\r\n        nettyThreads: 0\r\n        codec: \"!<org.redisson.codec.JsonJacksonCodec> {}\"\r\n        transportMode: \"NIO\"','9622f19cf2b28e111f5d8edf42010919','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,26,'cloud-gateway.yml','APP_GROUP','','spring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由\r\n          # 只要请求地址符合规则：http://gatewayip:gatewayport/微服务名称/微服务请求地址\r\n          # 网关自动映射。把请求转发到 http://微服务名称/微服务请求地址\r\n          enabled: false\r\n      routes:\r\n        - id: api-gateway-provider\r\n          #uri: http://localhost:8001  #静态，写死了地址，只能调用一个服务\r\n          uri: lb://cloud-provider  #动态，lb://微服务名\r\n          predicates:\r\n            - Path=/api/cloud-provider/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-consumer\r\n          uri: lb://cloud-consumer\r\n          predicates:\r\n            - Path=/api/cloud-consumer/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: api-gateway-simple\r\n          uri: http://127.0.0.1:7777\r\n          predicates:\r\n            - Path=/api/cloud-simple/**\r\n          filters:\r\n            - StripPrefix=1','01639fbaae2673c3b502f712a272f332','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(0,27,'cloud-dlq.yml','APP_GROUP','','spring:\r\n  datasource:\r\n    name: testerDS\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      poolName: Hikari\r\n      auto-commit: false\r\n  cloud:\r\n    stream:\r\n      # 死信\r\n      bindings:\r\n        input:\r\n          destination: ErrorTopic\r\n          group: dlq','e537c23cf282a170a6972e6e777ad96a','2022-12-29 21:39:47','2022-12-29 13:39:48',NULL,'127.0.0.1','I','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(13,28,'shared-logback.yml','SHARED_GROUP','','logging:\r\n  custom:\r\n    #自定义参数 产品线\r\n    productline: mall\r\n  file:\r\n    # 当前写入的日志文件名\r\n    name: ./log/${spring.application.name}.log\r\n    # 日志保留最大天数\r\n    max-history: 30\r\n    # 每个日志文件大小限制\r\n    max-size: 10MB\r\n\r\n  pattern:\r\n    file: >-\r\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\r\n    level: INFO\r\n    # 归档文件名\r\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','f0f38d207771a9a80955858e4bbe2dea','2022-12-29 22:13:03','2022-12-29 14:13:03','nacos','127.0.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(13,29,'shared-logback.yml','SHARED_GROUP','','logging:\n  custom:\n    #自定义参数 产品线\n    productline: mall\n  file:\n    # 当前写入的日志文件名\n    name: ./log/${spring.application.name}.log\n    # 日志保留最大天数\n    max-history: 30\n    # 每个日志文件大小限制\n    max-size: 10MB\n\n  pattern:\n    file: >-\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\n    level: DEBUG\n    # 归档文件名\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','4c6800d90dbe56a05224bbdf45b7629c','2022-12-29 22:27:56','2022-12-29 14:27:56','nacos','127.0.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(13,30,'shared-logback.yml','SHARED_GROUP','','logging:\n  custom:\n    #自定义参数 产品线\n    productline: mall\n  file:\n    # 当前写入的日志文件名\n    name: ./log/${spring.application.name}.log\n    # 日志保留最大天数\n    max-history: 30\n    # 每个日志文件大小限制\n    max-size: 10MB\n\n  pattern:\n    file: >-\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",[%traceId] \"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\n    level: DEBUG\n    # 归档文件名\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','632cfc1365d2dbcc2f47b905c19bd5b6','2022-12-29 22:33:11','2022-12-29 14:33:12','nacos','127.0.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5',''),(13,31,'shared-logback.yml','SHARED_GROUP','','logging:\n  custom:\n    #自定义参数 产品线\n    productline: mall\n  file:\n    # 当前写入的日志文件名\n    name: ./log/${spring.application.name}.log\n    # 日志保留最大天数\n    max-history: 30\n    # 每个日志文件大小限制\n    max-size: 10MB\n\n  pattern:\n    file: >-\n      {\"date\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"thread\":\"%thread\",\"traceId\":\"%traceId\" ,\"level\":\"%-5level\",\"env\":\"%property{spring.profiles.active}\",\"appname\":\"${spring.application.name}\",\"productline\":\"${logging.custom.productline}\",\"logger\":\"%logger{50}\",\"msg\":\"%replace(%msg %replace(%replace(%replace(%xException){\"\\r\\n\", \"\\\\n\"}){\"\\n\", \"\\\\n\"}){\"\\t\", \"\\\\t\"}%nopex){\"\\\"\", \"\\\'\"}\"}%n\n    level: DEBUG\n    # 归档文件名\n    rolling-file-name: ${logging.file.name}.%d{yyyy-MM-dd}.%i.log','c9dd167d355436aba6cca4c5120e030d','2022-12-29 23:09:08','2022-12-29 15:09:09','nacos','127.0.0.1','U','ced8504b-967b-4e96-8d38-17f4349e4ab5','');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
                               `role` varchar(50) NOT NULL,
                               `resource` varchar(255) NOT NULL,
                               `action` varchar(8) NOT NULL,
                               UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES ('dev','ced8504b-967b-4e96-8d38-17f4349e4ab5:*:*','rw'),('ROLE_ADMIN','ced8504b-967b-4e96-8d38-17f4349e4ab5:*:*','rw');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
                         `username` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('dev','dev'),('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

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
INSERT INTO `tenant_info` VALUES (1,'1','seeyon-dev','seeyon-dev','seeyon-dev','nacos',1667650074634,1667650074634),(2,'1','dubbo-dev','dubbo-dev','dubbo-dev','nacos',1667656627120,1667656627120),(5,'1','ced8504b-967b-4e96-8d38-17f4349e4ab5','DEV','dev','nacos',1625995989485,1625995989485),(6,'1','5906bf2f-1303-40f2-acce-2c0a5a679865','TEST','test','nacos',1625995995341,1625995995341);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL,
                         `password` varchar(500) NOT NULL,
                         `enabled` tinyint(1) NOT NULL,
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
-- Dumping routines for database 'nacos2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-29 23:54:12
