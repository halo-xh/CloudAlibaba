package com.example.cloudsimple.listen;

import com.example.cloudsimple.request.TestDOEvent;
import com.example.cloudsimple.request.TestDOEvent1;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TestListener {

    @TransactionalEventListener //(phase = TransactionPhase.AFTER_COMMIT)
    public void listenCommit(@Payload TestDOEvent event){
        System.out.println("AFTER_COMMIT listen event = " + event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void listenRollback(@Payload TestDOEvent event){
        System.out.println("AFTER_ROLLBACK listen event = " + event);
    }

    @EventListener
    public void listen(@Payload TestDOEvent event){
        System.out.println("EventListener listen event = " + event);
    }


    @TransactionalEventListener//(phase = TransactionPhase.AFTER_COMMIT)
    public void listenCommit(@Payload TestDOEvent1 event){
        System.out.println("AFTER_COMMIT listen event1111 = " + event);
    }

}
