package com.xh.cloudprovider8001.redission.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.cloudprovider8001.redission.core.model.SequenceDetail;
import com.xh.cloudprovider8001.redission.core.service.SequenceDetailService;
import com.xh.cloudprovider8001.redission.mapper.CmSnGenerateMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 编号生成表 服务实现类
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
@Service
public class SequenceDetailServiceImpl extends ServiceImpl<CmSnGenerateMapper, SequenceDetail> implements SequenceDetailService {

    @Resource
    private CmSnGenerateMapper generateMapper;

    @Override
    public SequenceDetail lockForBizType(String bizType) {
        return generateMapper.selectForUpdate(bizType);
    }

    @Override
    public int increaseCurrentWithLockVersion(String bizType, Long current, Long lockVersion) {
        return generateMapper.updateStock(bizType, current, lockVersion);
    }

    @Override
    public SequenceDetail findByBizType(String bizType) {
        return super.getOne(Wrappers.<SequenceDetail>lambdaQuery().eq(SequenceDetail::getBizType, bizType));
    }

    @Async
    @Override
    public void increaseCurrent(String bizType) {
        generateMapper.increaseByBizType(bizType);
    }

    @Override
    public List<SequenceDetail> findAllByBizTypeList(List<String> cachedBizTypeList) {
        return super.list(Wrappers.<SequenceDetail>lambdaQuery().in(SequenceDetail::getBizType, cachedBizTypeList));
    }

    @Override
    public boolean createIfNotExist(SequenceDetail entity) {
        entity.setServerCreateTime(new Date());
        int inserted = generateMapper.saveIfNotExist(entity);
        return inserted == 1;
    }

    @Override
    public boolean create(SequenceDetail sequenceDetail) {
        return super.save(sequenceDetail);
    }
}
