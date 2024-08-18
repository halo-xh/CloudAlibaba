package com.example.task.enums;


import lombok.Getter;

@Getter
public enum TaskStateEnum {


    TO_DISPATCH("待调派"),

    TO_ACCEPT("待接受"),

    ACCEPTED("已接受"),

    HANDLING("处理中"),

    FINISHED("已完成"),

    AUDITING("审核中"),

    AUDITED("已审核"),

    CLOSED("已关闭");

    private final String description;


    TaskStateEnum(String description) {
        this.description = description;
    }


}
