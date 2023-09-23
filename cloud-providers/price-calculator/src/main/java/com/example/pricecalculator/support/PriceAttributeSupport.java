package com.example.pricecalculator.support;

import com.example.pricecalculator.dto.PriceAttributeResponse;
import com.example.pricecalculator.dto.PriceAttributeRequest;

import java.util.List;

public interface PriceAttributeSupport {

    PriceAttributeResponse create(PriceAttributeRequest detail);

    List<PriceAttributeResponse> findPriceAttributesByProductId(Long productId);

}
