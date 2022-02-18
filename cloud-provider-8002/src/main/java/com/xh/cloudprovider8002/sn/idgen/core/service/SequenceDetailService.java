package com.xh.cloudprovider8002.sn.idgen.core.service;

import com.xh.cloudprovider8002.sn.idgen.core.model.SequenceDetail;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 编号生成表 服务类
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
public interface SequenceDetailService {

    SequenceDetail lockForBizType(String bizType);

    void increaseCurrent(String bizType);

    int increaseCurrentWithLockVersion(String bizType, Long current, Long lockVersion);

    SequenceDetail findByBizType(String bizType);

    List<SequenceDetail> findAllByBizTypeList(List<String> bisTypeList);

    boolean createIfNotExist(SequenceDetail sequenceDetail);

    boolean create(SequenceDetail sequenceDetail);

}
