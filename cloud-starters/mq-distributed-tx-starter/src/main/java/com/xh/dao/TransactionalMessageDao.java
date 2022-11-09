package com.xh.dao;

import com.xh.entity.TransactionalMessage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xiao Hong
 * @since 2022-11-09
 */
public interface TransactionalMessageDao {

    void insertSelective(TransactionalMessage record);

    void updateStatusSelective(TransactionalMessage record);

    List<TransactionalMessage> queryPendingCompensationRecords(LocalDateTime minScheduleTime,
                                                               LocalDateTime maxScheduleTime,
                                                               int limit);
}

