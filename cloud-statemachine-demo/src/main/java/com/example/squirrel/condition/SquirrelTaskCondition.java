package com.example.squirrel.condition;

import com.example.squirrel.context.SquirrelTaskContext;
import com.example.task.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.squirrelframework.foundation.fsm.Condition;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
public abstract class SquirrelTaskCondition implements Condition<SquirrelTaskContext> {

    @Override
    public boolean isSatisfied(SquirrelTaskContext context) {
        // 通用校验
        Task task = context.getTask();
        Assert.notNull(task, "task is null");
        return satisfied(context);
    }

    public abstract boolean satisfied(SquirrelTaskContext context);

    public String name() {
        return this.getClass().getName();
    }

}
