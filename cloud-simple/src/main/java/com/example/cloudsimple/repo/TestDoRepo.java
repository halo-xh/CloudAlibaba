package com.example.cloudsimple.repo;

import com.example.cloudsimple.entity.TestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TestDoRepo extends JpaRepository<TestDO, Long>, JpaSpecificationExecutor<TestDO> {


}
