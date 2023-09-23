package com.example.pricecalculator.domain;

import com.example.pricecalculator.domain.model.product.Product;
import com.example.pricecalculator.domain.model.product.ProductLine;
import com.example.pricecalculator.domain.model.product.ProductVersion;
import com.example.pricecalculator.domain.model.attribute.*;
import com.example.pricecalculator.domain.model.plan.PricePlanSKU;
import com.example.pricecalculator.domain.model.plan.PricePlanSPU;
import com.example.pricecalculator.domain.model.plan.PricePlanSPUBuyout;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
public class PricePlanTest {

    public static void main(String[] args) {
        Product product = new Product();
        ProductLine productLine = new ProductLine();
        productLine.setName("A8");

        ProductVersion productVersion = new ProductVersion();
        productVersion.setName("V8.1SP1");

        product.setProductLine(Collections.singletonList(productLine));
        product.setProductVersion(Collections.singletonList(productVersion));

        PriceAttributeEnum attribute = new PriceAttributeEnum();
        attribute.setName("版本");
        attribute.setType(PriceAttribute.PriceAttributeType.ENUMERATE);
        PriceAttributeEnum a2 = new PriceAttributeEnum();
        a2.setName("档位");
        a2.setType(PriceAttribute.PriceAttributeType.ENUMERATE);
        PriceAttributeQuantity a3 = new PriceAttributeQuantity();
        a3.setName("用户数");
        a3.setType(PriceAttribute.PriceAttributeType.QUANTITY);

        PricePlanSPU planSPU = new PricePlanSPUBuyout();
        planSPU.setName("A8定价");
        planSPU.setStrategyEnum(PricePlanSPU.PricePlanSPUEnum.BUYOUT);
        planSPU.setDirectSellDiscountLimit(7.5f);
        planSPU.setDirectSellDiscountLimit(6.5f);

        planSPU.setProduct(product);

        PricePlanSKU pricePlanSKU = new PricePlanSKU();
        pricePlanSKU.setPricePlanSPU(planSPU);
        pricePlanSKU.setPriceAttributeEnums(Arrays.asList(a2, attribute));

        pricePlanSKU.setPricePlanStrategy(PricePlanSKU.PricePlanStrategy.TIERED_PRICING);

        PriceAttributeQuantityDetail quantityDetail = new PriceAttributeQuantityDetail();
        quantityDetail.setPriceAttributeQuantity(a3);
        quantityDetail.setMax(99999);
        quantityDetail.setMin(1);
        quantityDetail.setStep(100);

        QuantityInterval interval = new QuantityInterval();
        interval.setStart(1);
        interval.setEnd(100);
        interval.setPriceStagy(QuantityInterval.PriceStagy.UNIT_PRICE);
        interval.setPrice(new BigDecimal(1000));


        QuantityInterval interval2 = new QuantityInterval();
        interval2.setStart(100);
        interval2.setEnd(1000);
        interval2.setPriceStagy(QuantityInterval.PriceStagy.UNIT_PRICE);
        interval2.setPrice(new BigDecimal(1000));

        quantityDetail.setIntervals(Arrays.asList(interval, interval2));



        pricePlanSKU.setQuantityDetail(quantityDetail);


    }
}
