-- test.fsm_task definition

CREATE TABLE `fsm_task` (
                            `id` bigint NOT NULL COMMENT 'id',
                            `task_name` varchar(100) DEFAULT NULL COMMENT '任务名称',
                            `task_content` text COMMENT '任务内容描述',
                            `task_status` varchar(100) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL COMMENT '任务状态',
                            `handler_id` bigint DEFAULT NULL COMMENT '处理人id',
                            `task_type` bigint NOT NULL COMMENT '任务类型',
                            `task_states` varchar(2000) NOT NULL COMMENT '任务节点',
                            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
                            `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                            `modified_by` varchar(50) DEFAULT NULL COMMENT '修改人',
                            `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                            `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='状态机demo任务表';

-- test.fsm_task_type definition

CREATE TABLE `fsm_task_type` (
                                 `id` bigint NOT NULL COMMENT 'id',
                                 `type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务类型名称',
                                 `task_states` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务类型所需要的节点',
                                 `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
                                 `modified_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                 `modified_by` varchar(50) DEFAULT NULL COMMENT '修改人',
                                 `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
                                 `version` int NOT NULL DEFAULT '0' COMMENT '版本',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='状态机demo任务类型表';


CREATE TABLE `fsm_state_machine`
(
    `id`                    bigint                                                        NOT NULL COMMENT 'id',
    `machine_id`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态机ID',
    `state`                 varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态机当前状态',
    `state_machine_context` blob   NOT NULL COMMENT '状态机上下文',
    `created_at`            datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by`            varchar(50)                                                            DEFAULT NULL COMMENT '创建人',
    `modified_at`           datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modified_by`           varchar(50)                                                            DEFAULT NULL COMMENT '修改人',
    `is_deleted`            bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `version`               int                                                           NOT NULL DEFAULT '0' COMMENT '版本',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='状态机表';