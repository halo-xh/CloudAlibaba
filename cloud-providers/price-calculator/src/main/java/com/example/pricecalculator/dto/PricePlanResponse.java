package com.example.pricecalculator.dto;


import lombok.Data;

import java.util.List;

@Data
public class PricePlanResponse {

    private List<PricePlanSKUResponse> pricePlanSKUResponses;


}
