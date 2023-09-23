package com.example.pricecalculator.domain.model.attribute;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "price_attribute_enum")
public class PriceAttributeEnum extends PriceAttribute {

    @TableField(exist = false)
    private List<AttributeEnum> enumValues;

}
