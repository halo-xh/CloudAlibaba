package com.example.pricecalculator.dto.mapper;


import com.example.pricecalculator.domain.model.attribute.PriceAttribute;
import com.example.pricecalculator.dto.PriceAttributeRequest;
import com.example.pricecalculator.dto.PriceAttributeResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceAttributeMapper {

    PriceAttribute toPriceAttribute(PriceAttributeRequest request);

    PriceAttributeResponse toPriceAttributeResponse(PriceAttribute attribute);

}
