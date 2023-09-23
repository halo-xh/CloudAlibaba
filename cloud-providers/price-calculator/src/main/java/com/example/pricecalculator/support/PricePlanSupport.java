package com.example.pricecalculator.support;

import com.example.pricecalculator.dto.PricePlanResponse;
import com.example.pricecalculator.dto.PricePlanRequest;
import com.example.pricecalculator.dto.PricePlanSKUResponse;

import java.util.List;

public interface PricePlanSupport {

    PricePlanResponse save(PricePlanRequest request);

    List<PricePlanSKUResponse> findPricePlansByProductId(Long productId);


}
