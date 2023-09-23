package com.example.pricecalculator.domain.model.product;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField(exist = false)
    private List<ProductLine> productLine;

    @TableField(exist = false)
    private List<ProductVersion> productVersion;

}
