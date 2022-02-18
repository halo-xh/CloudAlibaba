package com.xh.cloudprovider8002.sn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.cloudprovider8002.sn.core.model.SequenceDetail;
import org.apache.ibatis.annotations.Insert;
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
public interface CmSnGenerateMapper extends BaseMapper<SequenceDetail> {

    SequenceDetail selectForUpdate(@Param("bizType") String bizType);

    @Update(" update cm_sn_generate set current =#{current} , version =version + 1 where  biz_type =#{bizType} and version =#{lockVersion}")
    int updateStock(@Param("bizType") String bizType, @Param("current") Long current, @Param("lockVersion") Long lockVersion);

    @Update(" update cm_sn_generate set current = current +1 , version =version + 1 where  biz_type =#{bizType} ")
    void increaseByBizType(@Param("bizType") String bizType);

    @Insert("INSERT INTO cm_sn_generate \n" +
            "( biz_type, max_val, step, `current`, version, create_user_id, update_user_id, server_create_time, server_update_time, status_flag) \n" +
            "select #{bizType}, #{maxVal}, #{step}, #{current}, 0, #{createUserId}, #{updateUserId}, #{serverCreateTime}, NULL, 1 FROM dual \n" +
            "WHERE not exists (select * from cm_sn_generate where biz_type = #{bizType} );")
    int saveIfNotExist(SequenceDetail entity);
}
