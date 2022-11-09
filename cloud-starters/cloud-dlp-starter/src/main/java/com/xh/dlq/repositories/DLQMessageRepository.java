package com.xh.dlq.repositories;

import com.xh.dlq.models.DLQMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DLQMessageRepository extends JpaRepository<DLQMessage, Long>, JpaSpecificationExecutor<DLQMessage> {
}
