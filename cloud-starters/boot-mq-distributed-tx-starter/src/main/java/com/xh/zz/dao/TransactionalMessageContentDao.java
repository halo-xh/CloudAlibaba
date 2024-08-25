package com.xh.zz.dao;

import com.xh.zz.entity.TransactionalMessageContent;

import java.util.List;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
public interface TransactionalMessageContentDao {

    void insert(TransactionalMessageContent record);

    List<TransactionalMessageContent> queryByMessageIds(String messageIds);
}

