package com.example.pricecalculator.dto;

import com.example.pricecalculator.domain.model.attribute.PriceAttributeEnum;
import com.example.pricecalculator.domain.model.attribute.PriceAttributeQuantityDetail;
import com.example.pricecalculator.domain.model.plan.PricePlanSKU;
import com.example.pricecalculator.domain.model.plan.PricePlanSPU;
import lombok.Data;

import java.util.List;

@Data
public class PricePlanSKUResponse {

    private Long id;

    private PricePlanSKU.PricePlanStrategy pricePlanStrategy;

    private PricePlanSPU pricePlanSPU;

    private PriceAttributeQuantityDetail quantityDetail;

    private List<PriceAttributeEnum> priceAttributeEnums;


}
