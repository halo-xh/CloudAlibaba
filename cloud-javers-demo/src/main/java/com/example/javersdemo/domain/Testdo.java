package com.example.javersdemo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @TableName testdo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Testdo extends AbstractEntity implements Serializable {


    private String name;

    private Integer value;

}