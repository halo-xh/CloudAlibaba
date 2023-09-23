package com.example.pricecalculator.dto;

import com.example.pricecalculator.domain.model.attribute.PriceAttribute;
import com.example.pricecalculator.domain.model.product.Product;
import lombok.Data;

@Data
public class PriceAttributeRequest {

    private Long id;

    private String name;

    private PriceAttribute.PriceAttributeType type;

    private Product product;

    private PriceAttribute parent;
}
