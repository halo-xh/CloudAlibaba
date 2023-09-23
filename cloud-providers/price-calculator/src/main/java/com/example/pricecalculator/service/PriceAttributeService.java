package com.example.pricecalculator.service;


import com.example.pricecalculator.domain.model.attribute.PriceAttribute;

import java.util.List;

public interface PriceAttributeService {

    PriceAttribute save(PriceAttribute attribute);

    PriceAttribute findById(Long id);

    List<PriceAttribute> findListByProductId(Long productId);
}
