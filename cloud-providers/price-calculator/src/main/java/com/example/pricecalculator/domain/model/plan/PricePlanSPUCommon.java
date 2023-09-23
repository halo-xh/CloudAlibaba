package com.example.pricecalculator.domain.model.plan;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "price_plan_spu_common")
public class PricePlanSPUCommon extends PricePlanSPU {


}
