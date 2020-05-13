package com.vtw.pulsar.schedule.tempIntf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConInterfaceRepository extends JpaRepository<ConInterface, String>, JpaSpecificationExecutor<ConInterface> {
	
}
