package com.vtw.pulsar.agent.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vtw.pulsar.agent.entity.Agent;

public interface AgentService {
	List<Agent> getList();
	Agent getAgentById(String id);
	void AgentInsert(Agent agent);
	Page<Agent> getList2(Pageable pageable,Agent agent);
	void deleteAgent(String id);
}
