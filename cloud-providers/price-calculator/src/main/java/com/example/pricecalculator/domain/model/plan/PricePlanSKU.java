package com.example.pricecalculator.domain.model.plan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.pricecalculator.domain.model.attribute.PriceAttributeEnum;
import com.example.pricecalculator.domain.model.attribute.PriceAttributeQuantityDetail;
import lombok.Data;

import java.util.List;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@Data
@TableName(value = "price_plan_sku")
public class PricePlanSKU {

    @TableId(type = IdType.AUTO)
    private Long id;

    private PricePlanStrategy pricePlanStrategy;

    private Long spuId;

    private Long quantityDetailId;

    @TableField(exist = false)
    private PricePlanSPU pricePlanSPU;

    @TableField(exist = false)
    private PriceAttributeQuantityDetail quantityDetail;

    @TableField(exist = false)
    private List<PriceAttributeEnum> priceAttributeEnums;

    public enum PricePlanStrategy {

        //
        COMMON,

        TIERED_PRICING,

        TIERED_PRICING_ACCUMULATIVE;

    }


}
