package com.example.pricecalculator.domain.model.plan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.pricecalculator.domain.model.product.Product;
import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@Data
public class PricePlanSPU {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private PricePlanSPUEnum strategyEnum;

    private Long productId;

    private Float directSellDiscountLimit;

    private Float distributeSellDiscountLimit;

    @TableField(exist = false)
    private Product product;

    public enum PricePlanSPUEnum {

        //
        COMMON,

        BUYOUT,

        SUBSCRIPTION;


    }

}
