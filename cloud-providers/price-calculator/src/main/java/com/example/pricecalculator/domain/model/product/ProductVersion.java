package com.example.pricecalculator.domain.model.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@Data
@TableName
public class ProductVersion {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

}
