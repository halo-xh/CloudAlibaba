package com.example.pricecalculator.domain.model.attribute;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@Data
@TableName(value = "price_attribute_quantity_detail")
public class PriceAttributeQuantityDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(exist = false)
    private PriceAttributeQuantity priceAttributeQuantity;

    private Integer step;

    private Integer max;

    private Integer min;

    @TableField(exist = false)
    private List<QuantityInterval> intervals;


}
