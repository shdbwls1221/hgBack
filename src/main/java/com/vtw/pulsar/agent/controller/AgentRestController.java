package com.vtw.pulsar.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vtw.pulsar.agent.entity.Agent;
import com.vtw.pulsar.agent.service.AgentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AgentRestController {
	
	@Autowired
	private AgentService agentService;
	

	@PostMapping("/agentInsert")
	public void addAgent(@RequestBody Agent agent) {
		agentService.AgentInsert(agent);
	}
	@GetMapping("/agentList")
	public List<Agent> getAgent(){
		return agentService.getList();
	}
	@GetMapping("/agentId")
	public Agent getAgentById(String id){
		return agentService.getAgentById(id);
	}
	@GetMapping("/agentList2")
	public Page<Agent> getAgentList(Pageable pageable,Agent agent){
		return agentService.getList2(pageable,agent);
	}
	@GetMapping("/agentDelete")
	public void deleteAgent(String id) {
		agentService.deleteAgent(id);
	}
	
	
}
