package com.example.squirrel.event;

import lombok.Getter;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Getter
public enum SquirrelTaskEvent {

    SUBMIT("提交"),

    DISPATCHED("调派"),

    TRANS("转交"),

    ACCEPT("接受单据"),

    HANDLE("处理单据"),

    HANG_UP("挂起"),

    HANG_DOWN("解挂"),

    REJECT("回退"),

    FINISH("结束单据"),

    EVALUATE("审核"),

    PASS_EVALUATE("审核通过"),

    REJECT_EVALUATE("审核拒绝"),

    CLOSE("关闭单据");

    private final String description;

    SquirrelTaskEvent(String description) {
        this.description = description;
    }
}
