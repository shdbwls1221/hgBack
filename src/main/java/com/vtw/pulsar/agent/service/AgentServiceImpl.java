package com.vtw.pulsar.agent.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtw.pulsar.agent.entity.Agent;
import com.vtw.pulsar.agent.repository.AgentRepository;

@Service
@Transactional
public class AgentServiceImpl implements AgentService{
	@Autowired
	private AgentRepository agentRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Agent> getList() {
		// TODO Auto-generated method stub
		return agentRepository.findAll();
	}
	@Override
	public Agent getAgentById(String id) {
		// TODO Auto-generated method stub
		return agentRepository.findByAgentId(id);
	}
	@Override
	public void AgentInsert(Agent agent) {
		// TODO Auto-generated method stub
		agentRepository.save(agent);
	
	}
	@Override
	public Page<Agent> getList2(Pageable pageable,Agent agent) {
		// TODO Auto-generated method stub
	 
		ExampleMatcher matcher = ExampleMatcher.matching()     
	             .withStringMatcher(StringMatcher.CONTAINING)   // Match string containing pattern   
	             .withIgnoreCase();  
		Example<Agent> ex=	Example.of(agent,matcher);
		return agentRepository.findAll(ex,pageable);
		
	}
	@Override
	public void deleteAgent(String id) {
		// TODO Auto-generated method stub
		Long l = agentRepository.deleteByAgentId(id);
		logger.info(String.valueOf(l));
	}
	
	
	

}
