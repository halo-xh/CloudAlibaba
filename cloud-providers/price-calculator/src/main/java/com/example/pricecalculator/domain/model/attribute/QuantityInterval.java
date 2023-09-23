package com.example.pricecalculator.domain.model.attribute;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "quantity_interval")
public class QuantityInterval {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer start;

    private Integer end;

    private PriceStagy priceStagy;

    private BigDecimal price;

    public enum PriceStagy {

        UNIT_PRICE,

        FIXED_TOTAL_PRICE;

    }


}