package com.example.pricecalculator.support;

import com.example.pricecalculator.dto.PricePlanResponse;
import com.example.pricecalculator.dto.PricePlanRequest;
import com.example.pricecalculator.dto.PricePlanSKUResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PricePlanSupportImpl implements PricePlanSupport {


    @Override
    public PricePlanResponse save(PricePlanRequest request) {
        return null;
    }

    @Override
    public List<PricePlanSKUResponse> findPricePlansByProductId(Long productId) {
        return null;
    }


}
