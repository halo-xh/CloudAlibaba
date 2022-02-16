package com.xh.cloudprovider8002.sn.service;


import com.xh.cloudprovider8002.sn.entity.CmSnGenerate;

/**
 * <p>
 * 编号生成表 服务类
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
public interface ICmSnGenerateService  {

    CmSnGenerate selectForUpdate(String bizType);

    int updateStock(String bizType, Long current, Long lockVersion);
}
