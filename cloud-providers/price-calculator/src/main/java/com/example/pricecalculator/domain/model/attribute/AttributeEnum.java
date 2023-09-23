package com.example.pricecalculator.domain.model.attribute;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "attribute_enum_value")
public class AttributeEnum {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String enumName;


}
