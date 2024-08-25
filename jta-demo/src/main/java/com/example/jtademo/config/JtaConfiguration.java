package com.example.jtademo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;


@Configuration
public class JtaConfiguration {


    //--------------------配置spring的JtaTransactionManager，底层委派给atomikos进行处理--------------------
//    @Bean
//    public JtaTransactionManager jtaTransactionManager () {
//        UserTransactionManager userTransactionManager = new UserTransactionManager();
//        UserTransaction userTransaction = new UserTransactionImp();
//        return new JtaTransactionManager(userTransaction, userTransactionManager);
//    }

}
