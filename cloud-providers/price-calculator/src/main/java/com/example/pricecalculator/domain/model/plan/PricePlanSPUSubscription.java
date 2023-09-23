package com.example.pricecalculator.domain.model.plan;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "price_plan_spu_subscription")
public class PricePlanSPUSubscription extends PricePlanSPU {

    private Integer maxLimit;

    private BigDecimal pricePer;


}
