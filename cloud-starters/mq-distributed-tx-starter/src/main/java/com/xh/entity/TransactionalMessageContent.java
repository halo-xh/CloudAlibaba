package com.xh.entity;

import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
@Data
public class TransactionalMessageContent {

    private Long id;
    private Long messageId;
    private String content;
}