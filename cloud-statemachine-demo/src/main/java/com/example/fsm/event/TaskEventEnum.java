package com.example.fsm.event;


import lombok.Getter;

@Getter
public enum TaskEventEnum {


    CREATED("创建单据"),

    DISPATCHED("分派单据"),

    ACCEPT("接受单据"),

    HANDLE("处理单据"),

    REJECT("回退"),

    FINISH("结束单据"),

    EVALUATE("用户评价"),

    AUDIT("审核单据"),

    CLOSE("关闭单据");

    private final String description;


    TaskEventEnum(String description) {
        this.description = description;
    }
}
