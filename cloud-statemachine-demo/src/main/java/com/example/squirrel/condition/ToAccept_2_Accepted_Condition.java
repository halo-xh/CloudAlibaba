package com.example.squirrel.condition;

import com.example.squirrel.context.SquirrelTaskContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fanyi.xh
 * @since 2024-12-19
 */
@Slf4j
@Service
public class ToAccept_2_Accepted_Condition extends SquirrelTaskCondition {
    @Override
    public boolean satisfied(SquirrelTaskContext context) {
        return true;
    }
}
