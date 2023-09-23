package com.example.pricecalculator.domain.model.attribute;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.pricecalculator.domain.model.product.Product;
import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2023-09-22
 */
@Data
public class PriceAttribute {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private PriceAttributeType type;

    private Long productId;

    private Long parentId;

    @TableField(exist = false)
    private Product product;

    @TableField(exist = false)
    private PriceAttribute parent;


    public enum PriceAttributeType {

        //
        QUANTITY,

        ENUMERATE;

    }


}
