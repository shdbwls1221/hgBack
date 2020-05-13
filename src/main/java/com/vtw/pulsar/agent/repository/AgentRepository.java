package com.vtw.pulsar.agent.repository;


import org.hibernate.criterion.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.vtw.pulsar.agent.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long>,QueryByExampleExecutor<Agent>{
	
	public Agent findByAgentId(String agentId);
	Page<Agent> findAll(Pageable pageable);
	Long deleteByAgentId(String agentId);
	
	
	
}	
