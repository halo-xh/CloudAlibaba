package com.xh.dlq.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class IdGenerator implements IdentifierGenerator {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflakeIdWorker.nextId();
    }

}
