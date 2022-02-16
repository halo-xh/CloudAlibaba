package com.xh.cloudprovider8002.sn.service.impl;

import com.xh.cloudprovider8002.sn.entity.CmSnGenerate;
import com.xh.cloudprovider8002.sn.mapper.CmSnGenerateMapper;
import com.xh.cloudprovider8002.sn.service.ICmSnGenerateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 编号生成表 服务实现类
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
@Service
public class CmSnGenerateServiceImpl implements ICmSnGenerateService {

    @Resource
    private CmSnGenerateMapper generateMapper;

    @Override
    public CmSnGenerate selectForUpdate(String bizType) {
        return generateMapper.selectForUpdate(bizType);
    }

    @Override
    public int updateStock(String bizType, Long current, Long lockVersion) {
        return generateMapper.updateStock(bizType, current, lockVersion);
    }
}
