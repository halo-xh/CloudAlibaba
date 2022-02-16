package com.xh.cloudprovider8002.sn.mapper;

import com.xh.cloudprovider8002.sn.entity.CmSnGenerate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 编号生成表 Mapper 接口
 * </p>
 *
 * @author Xiao Hong
 * @since 2022-02-16
 */
@Mapper
public interface CmSnGenerateMapper {

    CmSnGenerate selectForUpdate(@Param("bizType")String bizType);

    @Update(" update cm_sn_generate set current =#{current} , version =version + 1 where  biz_type =#{bizType} and version =#{lockVersion}")
    int updateStock(@Param("bizType") String bizType, @Param("current")Long current, @Param("lockVersion")Long lockVersion);
}
