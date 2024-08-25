package com.example.jtademo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdBy", "tester", metaObject);
        setFieldValByName("modifiedBy", "tester", metaObject);
        setFieldValByName("createdAt", new Date(), metaObject);
        setFieldValByName("modifiedAt", new Date(), metaObject);
        setFieldValByName("isDeleted", Boolean.FALSE, metaObject);
        setFieldValByName("version", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("modifiedBy", "tester", metaObject);
        setFieldValByName("modifiedAt", new Date(), metaObject);
    }

}
