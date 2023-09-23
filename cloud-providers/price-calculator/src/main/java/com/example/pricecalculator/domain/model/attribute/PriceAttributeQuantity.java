package com.example.pricecalculator.domain.model.attribute;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "price_attribute_quantity")
public class PriceAttributeQuantity extends PriceAttribute {

    private String unit;


}
