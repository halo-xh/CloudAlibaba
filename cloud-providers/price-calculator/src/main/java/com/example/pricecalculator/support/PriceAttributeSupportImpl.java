package com.example.pricecalculator.support;

import com.example.pricecalculator.domain.model.attribute.PriceAttribute;
import com.example.pricecalculator.dto.PriceAttributeResponse;
import com.example.pricecalculator.dto.PriceAttributeRequest;
import com.example.pricecalculator.dto.mapper.PriceAttributeMapper;
import com.example.pricecalculator.service.PriceAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PriceAttributeSupportImpl implements PriceAttributeSupport {

    @Autowired
    private PriceAttributeService priceAttributeService;

    @Autowired
    private PriceAttributeMapper priceAttributeMapper;

    @Override
    public PriceAttributeResponse create(PriceAttributeRequest request) {
        PriceAttribute attribute = priceAttributeService.save(priceAttributeMapper.toPriceAttribute(request));
        return priceAttributeMapper.toPriceAttributeResponse(attribute);
    }

    @Override
    public List<PriceAttributeResponse> findPriceAttributesByProductId(Long productId) {
        List<PriceAttribute> attributes = priceAttributeService.findListByProductId(productId);
        return attributes.stream().map(priceAttributeMapper::toPriceAttributeResponse).collect(Collectors.toList());
    }


}
