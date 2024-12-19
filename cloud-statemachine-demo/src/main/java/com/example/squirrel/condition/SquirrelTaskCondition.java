package com.example.squirrel.condition;

import com.example.squirrel.context.SquirrelTaskContext;
import lombok.extern.slf4j.Slf4j;
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

        return false;
    }

    public abstract boolean satisfied(SquirrelTaskContext context);

    public String name() {
        return this.getClass().getName();
    }

}
